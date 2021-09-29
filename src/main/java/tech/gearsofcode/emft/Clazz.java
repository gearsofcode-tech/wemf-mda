package tech.gearsofcode.emft;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;

import tech.gearsofcode.emft.metamodel.AttributeAnnotations;

/**
 * Wrapper class for the actual EMF implementation, to have more convenient methods
 * */
public class Clazz implements EClassifier, EClass{

	private EClassifier eClass;
	private EClass clazz;

	public Clazz(EClassifier eClass) {
		this.eClass = eClass;
		if (eClass instanceof EClass) clazz = (EClass) eClass;
	}

	@Override
	public String getName() {
		return eClass.getName();
	}

	@Override
	public void setName(String value) {
		eClass.setName(value);
	}

	@Override
	public EList<EAnnotation> getEAnnotations() {
		return eClass.getEAnnotations();
	}

	@Override
	public EAnnotation getEAnnotation(String source) {
		return eClass.getEAnnotation(source);
	}

	@Override
	public EClass eClass() {
		return eClass.eClass();
	}

	@Override
	public Resource eResource() {
		return eClass.eResource();
	}

	@Override
	public EObject eContainer() {
		return eClass.eContainer();
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		return eClass.eContainingFeature();
	}

	@Override
	public EReference eContainmentFeature() {
		return eClass.eContainmentFeature();
	}

	@Override
	public EList<EObject> eContents() {
		return eClass.eContents();
	}

	@Override
	public TreeIterator<EObject> eAllContents() {
		return eClass.eAllContents();
	}

	@Override
	public boolean eIsProxy() {
		return eClass.eIsProxy();
	}

	@Override
	public EList<EObject> eCrossReferences() {
		return eClass.eCrossReferences();
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		return eClass.eGet(feature);
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		return eClass.eGet(feature, resolve);
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		eClass.eSet(feature, newValue);		
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		return eClass.eIsSet(feature);
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		eClass.eUnset(feature);
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		return eClass.eInvoke(operation, arguments);
	}

	@Override
	public EList<Adapter> eAdapters() {
		return eClass.eAdapters();
	}

	@Override
	public boolean eDeliver() {
		return eClass.eDeliver();
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		eClass.eSetDeliver(deliver);		
	}

	@Override
	public void eNotify(Notification notification) {
		eClass.eNotify(notification);
	}

	@Override
	public String getInstanceClassName() {
		return eClass.getInstanceClassName();
	}

	@Override
	public void setInstanceClassName(String value) {
		eClass.setInstanceClassName(value);
		
	}

	@Override
	public Class<?> getInstanceClass() {
		return eClass.getInstanceClass();
	}

	@Override
	public void setInstanceClass(Class<?> value) {
		eClass.setInstanceClass(value);
	}

	@Override
	public Object getDefaultValue() {
		return eClass.getDefaultValue();
	}

	@Override
	public String getInstanceTypeName() {
		return eClass.getInstanceTypeName();
	}

	@Override
	public void setInstanceTypeName(String value) {
		eClass.setInstanceTypeName(value);
	}

	@Override
	public EPackage getEPackage() {
		return eClass.getEPackage();
	}

	@Override
	public EList<ETypeParameter> getETypeParameters() {
		return eClass.getETypeParameters();
	}

	@Override
	public boolean isInstance(Object object) {
		return eClass.isInstance(object);
	}

	@Override
	public int getClassifierID() {
		return eClass.getClassifierID();
	}

	

	@Override
	public boolean isAbstract() {
		return clazz.isAbstract();
	}

	@Override
	public void setAbstract(boolean value) {
		clazz.setAbstract(value);
	}

	@Override
	public boolean isInterface() {
		return clazz.isInterface();
	}

	@Override
	public void setInterface(boolean value) {
		clazz.setInterface(value);
	}

	@Override
	public EList<EClass> getESuperTypes() {
		return clazz.getESuperTypes();
	}

	@Override
	public EList<EClass> getEAllSuperTypes() {
		return clazz.getEAllSuperTypes();
	}

	@Override
	public EAttribute getEIDAttribute() {
		return clazz.getEIDAttribute();
	}

	@Override
	public EList<EStructuralFeature> getEStructuralFeatures() {
		return clazz.getEStructuralFeatures();
	}

	@Override
	public EList<EGenericType> getEGenericSuperTypes() {
		return clazz.getEGenericSuperTypes();
	}

	@Override
	public EList<EGenericType> getEAllGenericSuperTypes() {
		return clazz.getEAllGenericSuperTypes();
	}

	@Override
	public EList<EAttribute> getEAttributes() {
		return clazz.getEAttributes();
	}

	@Override
	public EList<EAttribute> getEAllAttributes() {
		return clazz.getEAllAttributes();
	}

	@Override
	public EList<EReference> getEReferences() {
		return clazz.getEReferences();
	}

	@Override
	public EList<EReference> getEAllReferences() {
		return clazz.getEAllReferences();
	}

	@Override
	public EList<EReference> getEAllContainments() {
		return clazz.getEAllContainments();
	}

	@Override
	public EList<EStructuralFeature> getEAllStructuralFeatures() {
		return clazz.getEAllStructuralFeatures();
	}

	@Override
	public EList<EOperation> getEOperations() {
		return clazz.getEOperations();
	}

	@Override
	public EList<EOperation> getEAllOperations() {
		return clazz.getEAllOperations();
	}

	@Override
	public boolean isSuperTypeOf(EClass someClass) {
		return clazz.isSuperTypeOf(someClass);
	}

	@Override
	public int getFeatureCount() {
		return clazz.getFeatureCount();
	}

	@Override
	public EStructuralFeature getEStructuralFeature(int featureID) {
		return clazz.getEStructuralFeature(featureID);
	}

	@Override
	public EStructuralFeature getEStructuralFeature(String featureName) {
		return clazz.getEStructuralFeature(featureName);
	}

	@Override
	public int getOperationCount() {
		return clazz.getOperationCount();
	}

	@Override
	public EOperation getEOperation(int operationID) {
		return clazz.getEOperation(operationID);
	}

	@Override
	public int getOperationID(EOperation operation) {
		return clazz.getOperationID(operation);
	}

	@Override
	public EOperation getOverride(EOperation operation) {
		return clazz.getOverride(operation);
	}

	@Override
	public EGenericType getFeatureType(EStructuralFeature feature) {
		return clazz.getFeatureType(feature);
	}

	@Override
	public int getFeatureID(EStructuralFeature feature) {
		return clazz.getFeatureID(feature);
	}
	
	
	/**
	 * Returns a set of the referenced types.
	 * It is useful when we have attributes of the same type and do not want to repeat. 
	 * Ex.: import declarations
	 * */
	public Set<EClassifier> getReferencedETypes() {
		EList<EReference> lstRefs = clazz.getEReferences();
		Set<EClassifier> classifiers = new HashSet<EClassifier>();
		for (EReference ref: lstRefs) {
			classifiers.add(ref.getEType());
		}
		return classifiers;
	}
	
	
	/**
	 * Returns the camel cased name with first letter lowercased
	 * */
	public String getLcc() {
		String elementName = eClass.getName();
		return elementName.substring(0, 1).toLowerCase()+elementName.substring(1);
	}
	
	
	
	public List<EAttribute> getIdAttributes(){
		return getEAllAttributes().stream().filter(attr -> attr.getEAnnotation(AttributeAnnotations.ID)!=null).collect(Collectors.toList());
	}
	
}
