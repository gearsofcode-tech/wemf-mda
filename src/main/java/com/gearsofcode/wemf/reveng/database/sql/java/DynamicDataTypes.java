package com.gearsofcode.wemf.reveng.database.sql.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

import com.gearsofcode.wemf.reveng.database.model.DataType;
import com.gearsofcode.wemf.reveng.database.model.DatabaseObjectsFactory;
import com.gearsofcode.wemf.reveng.database.sql.DatabaseTypes;

public class DynamicDataTypes implements DatabaseTypes {

	private static Map<Integer,DataType> datatypes;
	
	private static DynamicDataTypes instance;
	
	private DynamicDataTypes(){
		
	}
	
	static{
		datatypes = new HashMap<Integer,DataType>();
	}
	
	@Override
	public DataType fromJavaClass(Class<?> c) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<DataType> getDatabaseTypes() {
		return new ArrayList<DataType>(datatypes.values());
	}



	public static DataType get(Integer typeId) {
		return datatypes.get(typeId);
	}



	public static DataType registerType(int typeId, String typeName, int columnSize, int decimalDigits) {
		DatabaseTypes dbTypes = DatabaseObjectsFactory.getDatabaseTypes();
		DataType dt = dbTypes.getByTypeName(typeName);
		EDataType eDataType = dt.getEMFType();
		if (EcorePackage.eINSTANCE.getEBigDecimal().equals(eDataType) && decimalDigits == 0) {
			eDataType = EcorePackage.eINSTANCE.getELongObject();
		}
		DataType dataType = new DataType(typeName, decimalDigits>0?2:1, eDataType);
		datatypes.put(typeId, dataType);
		return dataType;
	}



	public static DatabaseTypes instance() {
		if(instance==null){
			instance = new DynamicDataTypes();
		}
		return instance;
	}

}
