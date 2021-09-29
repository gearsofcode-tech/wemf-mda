package tech.gearsofcode.wemf.reveng.database.sql.mysql;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EcorePackage;

import tech.gearsofcode.wemf.reveng.database.model.DataType;
import tech.gearsofcode.wemf.reveng.database.sql.DatabaseTypes;

/**
 * Enumerates MySQL's relational data types.
 * 
 * @author SamuraiCharlie
 * */
public class MySQLDatabaseTypes implements DatabaseTypes {

	
	private static DataType varchar = new DataType("VARCHAR", 1, EcorePackage.eINSTANCE.getEString());
	private static DataType integer = new DataType("INTEGER", 0, EcorePackage.eINSTANCE.getEIntegerObject());
	private static DataType bigInt = new DataType("BIGINT", 0, EcorePackage.eINSTANCE.getELongObject());

	
	@Override
	public List<DataType> getDatabaseTypes() {
		List<DataType> lst = new ArrayList<DataType>();
		lst.add(new DataType("char", 1, EcorePackage.eINSTANCE.getEString()));
		lst.add(bigInt);
		lst.add(varchar);
		lst.add(new DataType("long", 0, EcorePackage.eINSTANCE.getELongObject()));
		lst.add(new DataType("raw", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("long raw", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("number", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("numeric", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("float", 0, EcorePackage.eINSTANCE.getEFloat()));
		lst.add(new DataType("double", 0, EcorePackage.eINSTANCE.getEDouble()));
		lst.add(new DataType("dec", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("decimal", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(integer);
		lst.add(new DataType("int", 0, EcorePackage.eINSTANCE.getEIntegerObject()));
		lst.add(new DataType("tinyint", 0, EcorePackage.eINSTANCE.getEBoolean()));
		lst.add(new DataType("smallint", 0, EcorePackage.eINSTANCE.getEIntegerObject()));
		lst.add(new DataType("double precision", 0, EcorePackage.eINSTANCE.getEDoubleObject()));
		lst.add(new DataType("date", 0, EcorePackage.eINSTANCE.getEDate()));
		lst.add(new DataType("datetime", 0, EcorePackage.eINSTANCE.getEDate()));
		lst.add(new DataType("timestamp", 0, EcorePackage.eINSTANCE.getEDate()));
		lst.add(new DataType("blob", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("clob", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("bfile", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("nclob", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("rowid", 0, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("urowid", 1, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("json", 0, EcorePackage.eINSTANCE.getEString()));
		lst.add(new DataType("text", 0, EcorePackage.eINSTANCE.getEString()));
		return lst;
	}


	@Override
	public DataType fromJavaClass(Class<?> c) {
		if(c.equals(String.class)){
			return varchar;
		}
		else if(c.equals(Integer.class)){
			return integer;
		}
		else if(c.equals(Long.class)){
			return bigInt;
		}
		return null;
	}

}
