package com.gearsofcode.emft.metamodel;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Helps to map EMF types to Java types. 
 * @author Carlos Padoa
 *
 */
public class TypeMapper {
	
	public static final EDataType E_LONG = EcorePackage.eINSTANCE.getELong();
	public static final EDataType E_BOOLEAN = EcorePackage.eINSTANCE.getEBoolean();
	public static final EDataType E_DATE = EcorePackage.eINSTANCE.getEDate();
	public static final EDataType E_BIGDECIMAL = EcorePackage.eINSTANCE.getEBigDecimal();
	public static final EDataType E_INT = EcorePackage.eINSTANCE.getEInt();
	public static final EDataType E_STRING = EcorePackage.eINSTANCE.getEString();


	/**
	 * Gets the java type correspondent to the given EMF type. 
	 * @param emfType the EMF type to be converted ('EString' for example). 
	 * @return the correspondent java type ('String', for example).
	 */
	public static String getJavaType(String emfType) {
		String javaType = null;
		switch (emfType) {
			case "EString":
				javaType = "String";
				break;
			case "ELong":
				javaType = "long";
				break;
			case "ELongObject":
				javaType = "Long";
				break;
			case "EBoolean":
				javaType = "boolean";
				break;
			case "EBooleanObject":
				javaType = "Boolean";
				break;
			case "EInt":
				javaType = "int";
				break;
			case "EIntObject":
				javaType = "Integer";
				break;
			case "EDate":
				javaType = "Date";
				break;
			default:
				javaType = emfType;
		}	
		return javaType;
	}
	
	
	
	/**
	 * Converts the given Java class into the correspondent EMF type.
	 * @param clazz java type to be converted ('String.class', for example).
	 * @return correspondent EMF type ('EString', for example).
	 */
	public static String getEMFType(Class<?> clazz) {
		String javaType = clazz.getSimpleName();
		String eType = null;
		switch (javaType) {
			case "String":
				eType = "EString";
				break;
			case "Long":
				eType = "ELongObject";
				break;
			case "long":
				eType = "ELong";
				break;
			case "Boolean":
				eType = "EBooleanObject";
				break;
			case "boolean":
				eType = "EBoolean";
				break;
			case "int":
				eType = "EInt";
				break;	
			case "Integer":
				eType = "EIntObject";
				break;			
			case "Date":
				eType = "EDate";
				break;			
			default:
				eType = javaType;
		}	

		return eType;
	}


	/**
	 * Convenient method to get the correspondent Java DTO type for the given EMFType. 
	 * java.util.Date objects do not have a good JSON translation, so they return String, for example.
	 * DTOs always use wrapper objects because they can be null. 
	 * 
	 * @param emfType EMFType to be converted ('ELong', for example).
	 * @return correspondent Java type to be used on DTOs (Data Transfer Object) ('Long', for example).
	 */
	public static String getJavaDTOType(String emfType) {
		String javaType = null;
		switch (emfType) {
			case "EString":
				javaType = "String";
				break;
			case "ELong":
				javaType = "Long";
				break;
			case "ELongObject":
				javaType = "Long";
				break;
			case "EBoolean":
				javaType = "Boolean";
				break;
			case "EBooleanObject":
				javaType = "Boolean";
				break;
			case "EInt":
				javaType = "Integer";
				break;
			case "EIntObject":
				javaType = "Integer";
				break;
			case "EDate":
				javaType = "String";
				break;
			default:
				javaType = emfType;
		}	
		return javaType;
	}
}
