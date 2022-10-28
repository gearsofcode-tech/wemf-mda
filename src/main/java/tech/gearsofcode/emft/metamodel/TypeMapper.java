package tech.gearsofcode.emft.metamodel;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Helps to map EMF types to Java types. 
 * @author SamuraiCharlie
 *
 */
public class TypeMapper {
	
	public static final EDataType E_LONG = EcorePackage.eINSTANCE.getELong();
	public static final EDataType E_BOOLEAN = EcorePackage.eINSTANCE.getEBoolean();
	public static final EDataType E_DATE = EcorePackage.eINSTANCE.getEDate();
	public static final EDataType E_BIGDECIMAL = EcorePackage.eINSTANCE.getEBigDecimal();
	public static final EDataType E_INT = EcorePackage.eINSTANCE.getEInt();
	public static final EDataType E_STRING = EcorePackage.eINSTANCE.getEString();
	public static final EDataType E_DOUBLE = EcorePackage.eINSTANCE.getEDouble();


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
			case "EDouble":
				javaType = "double";
				break;
			case "EDoubleObject":
				javaType = "Double";
				break;
			case "EFloat":
				javaType = "float";
				break;
			case "EFloatObject":
				javaType = "Float";
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
			case "EIntegerObject":
				javaType = "Integer";
				break;
			case "EDate":
				javaType = "Date";
				break;
			case "EBigDecimal":
				javaType = "BigDecimal";
				break;
			case "EByteArray":
				javaType = "byte[]";
				break;
			default:
				javaType = emfType;
		}	
		return javaType;
	}
	
	
	
	public static String getTypescriptType(String emfType) {
		String javaType = null;
		switch (emfType) {
			case "EString":
				javaType = "string";
				break;
			case "ELong":
				javaType = "number";
				break;
			case "ELongObject":
				javaType = "number";
				break;
			case "EDouble":
				javaType = "number";
				break;
			case "EDoubleObject":
				javaType = "number";
				break;
			case "EFloat":
				javaType = "number";
				break;
			case "EFloatObject":
				javaType = "number";
				break;
			case "EBoolean":
				javaType = "boolean";
				break;
			case "EBooleanObject":
				javaType = "boolean";
				break;
			case "EInt":
				javaType = "number";
				break;
			case "EIntegerObject":
				javaType = "number";
				break;
			case "EDate":
				javaType = "number";
				break;
			case "EBigDecimal":
				javaType = "number";
				break;
			case "EByteArray":
				javaType = "number";
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
			case "Float":
				eType = "EFloatObject";
				break;
			case "float":
				eType = "EFloat";
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
				eType = "EIntegerObject";
				break;			
			case "Date":
				eType = "EDate";
				break;
			case "BigDecimal":
				eType = "EBigDecimal";
				break;
			case "byte[]":
				eType = "EByteArray";
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
			case "EDouble":
				javaType = "Double";
				break;
			case "EDoubleObject":
				javaType = "Double";
				break;
			case "EFloat":
				javaType = "Float";
				break;
			case "EFloatObject":
				javaType = "Float";
				break;
			case "EBoolean":
				javaType = "boolean";
				break;
			case "EBooleanObject":
				javaType = "Boolean";
				break;
			case "EInt":
				javaType = "Integer";
				break;
			case "EIntegerObject":
				javaType = "Integer";
				break;
			case "EDate":
				javaType = "String";
				break;
			case "EBigDecimal":
				javaType = "String";
				break;
			case "EByteArray":
				javaType = "byte[]";
				break;
			default:
				javaType = emfType + "DTO";
		}	
		return javaType;
	}
}
