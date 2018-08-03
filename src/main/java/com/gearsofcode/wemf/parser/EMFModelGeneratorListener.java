package com.gearsofcode.wemf.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EClassifierImpl;

import com.gearsofcode.wemf.WEMFSyntaxException;
import com.gearsofcode.wemf.parser.WEMFParser.AnnotParamContext;
import com.gearsofcode.wemf.parser.WEMFParser.AnnotationContext;
import com.gearsofcode.wemf.parser.WEMFParser.AttributeContext;
import com.gearsofcode.wemf.parser.WEMFParser.CardinalityContext;
import com.gearsofcode.wemf.parser.WEMFParser.ConcreteClassContext;
import com.gearsofcode.wemf.parser.WEMFParser.EpackageContext;
import com.gearsofcode.wemf.parser.WEMFParser.MethodContext;
import com.gearsofcode.wemf.parser.WEMFParser.ParameterContext;
import com.gearsofcode.wemf.parser.WEMFParser.SystemContext;

/**
 * This class traverses the tree 2 times. On the first time, it maps basic classes.
 * On the second pass, it associates types correctly.
 * 
 * @author Carlos Padoa
 */
public class EMFModelGeneratorListener extends WEMFBaseListener {

	public static final String SYSTEM_NAME_ANNOTATION = "SYSTEM_NAME";
	public static final EDataType E_LONG = EcorePackage.eINSTANCE.getELong();
	public static final EDataType E_BOOLEAN = EcorePackage.eINSTANCE.getEBoolean();
	public static final EDataType E_DATE = EcorePackage.eINSTANCE.getEDate();
	public static final EDataType E_BIGDECIMAL = EcorePackage.eINSTANCE.getEBigDecimal();
	public static final EDataType E_INT = EcorePackage.eINSTANCE.getEInt();
	public static final EDataType E_STRING = EcorePackage.eINSTANCE.getEString();
	public static final EDataType E_LONG_OBJECT = EcorePackage.eINSTANCE.getELongObject();

	/**
	 * Simple mappings of strings to the actual EMF objects.
	 * */
	private static Map<String, EDataType> typeMap;
	private Map<String, EClassifier> classifierMap;
	
	/**
	 * Keeps track of declared classes.
	 * */
	private List<String> userDefinedEClassifiers;
	
	/**
	 * State objects to use during traversal.
	 * */
	private EPackage ePackage;
	private EClass currentClass;
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
		typeMap.put("EString", E_STRING);
		typeMap.put("ELong", E_LONG);
		typeMap.put("ELongObject", E_LONG_OBJECT);
		typeMap.put("EInt", E_INT);
		typeMap.put("EDate", E_DATE);
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
		ePackage.setName(ctx.getChild(1).getText());
	}



	@Override
	public void enterConcreteClass(ConcreteClassContext ctx) {
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
			}
		}
	}



	@Override
	public void enterMethod(MethodContext ctx) {
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
		EAnnotation	annot = EcoreFactory.eINSTANCE.createEAnnotation();
		String annotId = ctx.getChild(1).getText();
		annot.setSource(annotId);
		annotations.push(annot);
	}



	@Override
	public void enterAnnotParam(AnnotParamContext ctx) {
		EAnnotation annot = annotations.pop();
		int i=0;
		while(!"=".equals(ctx.getChild(i).getText()))i++;
		String name = ctx.getChild(i-1).getText();
		String value = ctx.getChild(i+1).getText();
		value = value.substring(1, value.length()-1);
		annot.getDetails().put(name, value);
		
		annotations.push(annot);
	}


	@Override
	public void enterSystem(SystemContext ctx) {
		EAnnotation eAnnot = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnot.setSource(SYSTEM_NAME_ANNOTATION);
		eAnnot.getDetails().put("name", ctx.getChild(1).getText());
		ePackage.getEAnnotations().add(eAnnot);
	}

}
