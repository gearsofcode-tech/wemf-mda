package tech.gearsofcode.wemf.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EClassifierImpl;

import tech.gearsofcode.wemf.WEMFSyntaxException;
import tech.gearsofcode.wemf.parser.WEMFParser.AnnotationContext;
import tech.gearsofcode.wemf.parser.WEMFParser.AttributeContext;
import tech.gearsofcode.wemf.parser.WEMFParser.CardinalityContext;
import tech.gearsofcode.wemf.parser.WEMFParser.ConcreteClassContext;
import tech.gearsofcode.wemf.parser.WEMFParser.EnumValueContext;
import tech.gearsofcode.wemf.parser.WEMFParser.EnumerationContext;
import tech.gearsofcode.wemf.parser.WEMFParser.EpackageContext;
import tech.gearsofcode.wemf.parser.WEMFParser.MethodContext;
import tech.gearsofcode.wemf.parser.WEMFParser.ParameterContext;
import tech.gearsofcode.wemf.parser.WEMFParser.SystemContext;

/**
 * This class traverses the tree 2 times. On the first time, it maps basic classes.
 * On the second pass, it associates types correctly.
 * 
 * @author SamuraiCharlie
 */
public class EMFModelGeneratorListener extends WEMFBaseListener {

	public static final String SYSTEM_NAME_ANNOTATION = "SYSTEM_NAME";


	/**
	 * Simple mappings of strings to the actual EMF objects.
	 * */
	private static Map<String, EDataType> typeMap;
	private Map<String, EClassifier> classifierMap;
	private boolean debug = false;
	
	/**
	 * Keeps track of declared classes.
	 * */
	private List<String> userDefinedEClassifiers;
	
	/**
	 * State objects to use during traversal.
	 * */
	private EPackage ePackage;
	private EClass currentClass;
	private EEnum currentEnum;
	private EOperation currentMethod;
	private ETypedElement currentTypedElement;
	private Stack<EAnnotation> annotations = new Stack<EAnnotation>();
	
	/**
	 * Indicates whether it is the first or second pass.
	 * */
	private boolean secondPass;
	
	/**
	 * TypeMap initialization.
	 * */
	static {
		typeMap = new HashMap<String, EDataType>();
		typeMap.put("EString", EcorePackage.eINSTANCE.getEString());
		typeMap.put("ELong", EcorePackage.eINSTANCE.getELong());
		typeMap.put("ELongObject", EcorePackage.eINSTANCE.getELongObject());
		typeMap.put("EInt", EcorePackage.eINSTANCE.getEInt());
		typeMap.put("EIntegerObject", EcorePackage.eINSTANCE.getEIntegerObject());
		typeMap.put("EDate", EcorePackage.eINSTANCE.getEDate());
		typeMap.put("EBoolean", EcorePackage.eINSTANCE.getEBoolean());
		typeMap.put("EBooleanObject", EcorePackage.eINSTANCE.getEBooleanObject());
		typeMap.put("EDouble", EcorePackage.eINSTANCE.getEDouble());
		typeMap.put("EDoubleObject", EcorePackage.eINSTANCE.getEDoubleObject());
		typeMap.put("EFloat", EcorePackage.eINSTANCE.getEFloat());
		typeMap.put("EFloatObject", EcorePackage.eINSTANCE.getEFloatObject());
		typeMap.put("EShort", EcorePackage.eINSTANCE.getEShort());
		typeMap.put("EShortObject", EcorePackage.eINSTANCE.getEShortObject());
		typeMap.put("EChar", EcorePackage.eINSTANCE.getEChar());
		typeMap.put("ECharacterObject", EcorePackage.eINSTANCE.getECharacterObject());
		typeMap.put("EByte", EcorePackage.eINSTANCE.getEByte());
		typeMap.put("EByteObject", EcorePackage.eINSTANCE.getEByteObject());
		typeMap.put("EByteArray", EcorePackage.eINSTANCE.getEByteArray());
		typeMap.put("EBigDecimal", EcorePackage.eINSTANCE.getEBigDecimal());
	}


	/**
	 * Prepares the class to traverse the tree for the first time.
	 * @param userDefinedEClassifiers list of the names of the classes defined by the user. This is necessary to identify not declared data types (classes).
	 * 
	 */
	public EMFModelGeneratorListener(List<String> userDefinedEClassifiers) {
		ePackage = EcoreFactory.eINSTANCE.createEPackage();
		this.userDefinedEClassifiers = userDefinedEClassifiers;
		this.classifierMap = new HashMap<String, EClassifier>();
		secondPass = false;
	}
	
	
	/**
	 * This must be called before the second traversal to the tree is made.
	 * It indicates that this time references will be given their correct object type, instead of a proxy.
	 */
	public void setSecondPass(){
		secondPass = true;
	}



	/**
	 * @return the generated model.
	 */
	public EPackage getResult() {
		return ePackage;
	}




	@Override
	public void enterEpackage(EpackageContext ctx) {
		if (debug) System.out.println("enter package " + ctx.getText());
		ePackage.setName(ctx.getChild(1).getText());
	}



	@Override
	public void enterConcreteClass(ConcreteClassContext ctx) {
		if (debug) System.out.println("enter Class " + ctx.getText());
		int i = 0;
		while (!"class".equals(ctx.getChild(i).getText()))i++;
		String className = ctx.getChild(i+1).getText();
		if (!classifierMap.containsKey(className)) {
			EClass eClass = EcoreFactory.eINSTANCE.createEClass();
			while (!annotations.isEmpty()) {
				EAnnotation annot = annotations.pop();
				eClass.getEAnnotations().add(annot);
			}
			eClass.setName(className);
			ePackage.getEClassifiers().add(eClass);
			currentClass = eClass;
			classifierMap.put(eClass.getName(), eClass);
		}
		else {
			currentClass = (EClass) classifierMap.get(className);
		}
	}



	@Override
	public void exitConcreteClass(ConcreteClassContext ctx) {
		currentClass = null;
	}



	@Override
	public void enterAttribute(AttributeContext ctx) {
		if (debug) System.out.println(String.format("enter Attribute [%s] %s", String.valueOf(annotations.size()), ctx.getText()));
		int index = 0;
		while (index<ctx.getChildCount() && !":".equals(ctx.getChild(index).getText()))
			index++;
		String attName = ctx.getChild(index-1).getText();
		String type = ctx.getChild(index+1).getText();
		
		
		
		boolean isReference = userDefinedEClassifiers.contains(type);
		
		if (isReference){
			Optional<EReference> optRef = currentClass.getEReferences().stream().filter(ref -> ref.getName().equals(attName)).findFirst();
			if (optRef.isPresent()) {
				EReference eReference = optRef.get();
				currentTypedElement = eReference;
				eReference.setEType(getType(type));
				//Esvaziar as anotações
				while (!annotations.isEmpty()) annotations.pop();
			}
			else {
				EReference eRef = EcoreFactory.eINSTANCE.createEReference();
				while (!annotations.isEmpty()) {
					EAnnotation annot = annotations.pop();
					eRef.getEAnnotations().add(annot);
				}
				currentTypedElement = eRef;
				eRef.setName(attName);
				eRef.setEType(getType(type));
				currentClass.getEStructuralFeatures().add(eRef);
			}
		}
		else{
			//Atributo básico
			Optional<EAttribute> optAttr = currentClass.getEAttributes().stream().filter(att -> att.getName().equals(attName)).findFirst();
			if (!optAttr.isPresent()) {
				EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
				while (!annotations.isEmpty()) {
					EAnnotation annot = annotations.pop();
					eAttr.getEAnnotations().add(annot);
				}
				
				currentTypedElement = eAttr;
				eAttr.setName(attName);
				eAttr.setEType(getType(type));
				currentClass.getEStructuralFeatures().add(eAttr);
			}
			else{
				currentTypedElement = optAttr.get();
				//Esvaziar as anotações
				while (!annotations.isEmpty()) annotations.pop();
			}
		}
	}



	@Override
	public void enterMethod(MethodContext ctx) {
		if (debug) System.out.println("enter Method " + ctx.getText());
		int index = 0;
		while (!"(".equals(ctx.getChild(index).getText()))
			index++;
		String opName = ctx.getChild(index - 1).getText();
		
		
		Optional<EOperation> optOp = currentClass.getEOperations().stream().filter(op -> op.getName().equals(opName)).findFirst();
		if (optOp.isPresent()) {
			EOperation eOp = optOp.get();
			currentMethod = eOp;
			currentTypedElement = eOp;
		}
		else {
			EOperation eOp = EcoreFactory.eINSTANCE.createEOperation();
			eOp.setName(opName);
			currentClass.getEOperations().add(eOp);
			currentMethod = eOp;
			currentTypedElement = eOp;
			while (!annotations.isEmpty()) {
				EAnnotation annot = annotations.pop();
				eOp.getEAnnotations().add(annot);
			}
		}
		
		
		index = 0;
		while (index<ctx.getChildCount() && !":".equals(ctx.getChild(index).getText()))
			index++;
		boolean hasReturnType = index < ctx.getChildCount();
		String returnType = hasReturnType ? ctx.getChild(index+1).getText() : null;
		if (hasReturnType){
			currentMethod.setEType(getType(returnType));
		}
		
		
		
	}



	@Override
	public void exitMethod(MethodContext ctx) {
		currentMethod = null;
		currentTypedElement = null;
	}



	@Override
	public void enterParameter(ParameterContext ctx) {
		if (debug) System.out.println("enter parameter " + ctx.getText());
		String paramName = ctx.getChild(0).getText();
		String paramType = ctx.getChild(2).getText();
		Optional<EParameter> optParam = currentMethod.getEParameters().stream().filter(param -> param.getName().equals(paramName)).findFirst();
		if (optParam.isPresent()) {
			EParameter eParam = optParam.get();
			eParam.setEType(getType(paramType));
		}
		else {
			EParameter eParam = EcoreFactory.eINSTANCE.createEParameter();
			eParam.setName(paramName);
			eParam.setEType(getType(paramType));
			currentMethod.getEParameters().add(eParam);
		}
	}



	private EClassifier getType(String type) {
		EClassifier eClass = typeMap.get(type);
		if (eClass != null) return eClass;

		eClass = classifierMap.get(type);
		if (eClass != null) return eClass;
		
		if (secondPass){
			throw new WEMFSyntaxException("Type is not defined (in second pass): " + type);
		}

		if (!userDefinedEClassifiers.contains(type)) {
			throw new WEMFSyntaxException("Type is not defined: " + type);
		}
		return new ProxyClassifier(type);
	}


	/**
	 * On the first traversal, the user defined classes haven't been defined as EMF object yet.
	 * So, a proxy is used instead.
	 * On the second pass, proxies are replaced by the actual EMF objects.
	 *
	 */
	class ProxyClassifier extends EClassifierImpl {

		public String classifierName;


		public ProxyClassifier(String classifierName) {
			this.classifierName = classifierName;
		}


		@Override
		public EList<EAnnotation> getEAnnotations() {
			EClassifier actualClassifier = classifierMap.get(classifierName);
			if (actualClassifier != null) return actualClassifier.getEAnnotations();
			throw new UnsupportedOperationException();
		}
		
		
	}


	@Override
	public void enterCardinality(CardinalityContext ctx) {
		String lowerBound = ctx.getChild(1).getText();
		currentTypedElement.setLowerBound(Integer.parseInt(lowerBound));
		
		String upperBound = ctx.getChild(3).getText();
		currentTypedElement.setUpperBound("*".equals(upperBound)?ETypedElement.UNBOUNDED_MULTIPLICITY : Integer.parseInt(upperBound));
	}


	@Override
	public void exitAttribute(AttributeContext ctx) {
		currentTypedElement = null;
	}


	@Override
	public void enterAnnotation(AnnotationContext ctx) {
		if (debug) System.out.println("enterAnnotation " + ctx.getText());
		EAnnotation	annot = EcoreFactory.eINSTANCE.createEAnnotation();
		String annotId = ctx.getChild(1).getText();
		annot.setSource(annotId);
		if (ctx.getChildCount()>2){
			annot.getDetails().put("text", ctx.getChild(3).getText());
		}
		annotations.push(annot);
	}


	@Override
	public void enterSystem(SystemContext ctx) {
		EAnnotation eAnnot = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnot.setSource(SYSTEM_NAME_ANNOTATION);
		eAnnot.getDetails().put("name", ctx.getChild(1).getText());
		ePackage.getEAnnotations().add(eAnnot);
	}


	@Override
	public void enterEnumeration(EnumerationContext ctx) {
		if (debug) System.out.println("enter Enumeration " + ctx.getText());
		int i = 0;
		while (!"enum".equals(ctx.getChild(i).getText()))i++;
		String enumName = ctx.getChild(i+1).getText();
		if (!classifierMap.containsKey(enumName)) {
			EEnum enumClass = EcoreFactory.eINSTANCE.createEEnum();
			while (!annotations.isEmpty()) {
				EAnnotation annot = annotations.pop();
				enumClass.getEAnnotations().add(annot);
			}
			enumClass.setName(enumName);
			ePackage.getEClassifiers().add(enumClass);
			currentEnum = enumClass;
			classifierMap.put(enumClass.getName(), enumClass);
		}
		else {
			currentEnum = (EEnum) classifierMap.get(enumName);
		}
	}


	@Override
	public void enterEnumValue(EnumValueContext ctx) {
		if (!secondPass){
			if (debug) System.out.println("enter enum value " + ctx.getText());
			String enumName = ctx.getChild(0).getText();
			String enumValue = ctx.getChild(2).getText();
			EEnumLiteral eLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
			eLiteral.setName(enumName);
			eLiteral.setLiteral(enumValue.replaceAll("'", "\""));
			currentEnum.getELiterals().add(eLiteral);
		}
	}

}
