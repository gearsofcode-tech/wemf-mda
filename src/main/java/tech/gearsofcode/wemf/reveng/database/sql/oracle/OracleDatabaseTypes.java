package tech.gearsofcode.wemf.reveng.database.sql.oracle;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EcorePackage;

import tech.gearsofcode.wemf.reveng.database.model.DataType;
import tech.gearsofcode.wemf.reveng.database.sql.DatabaseTypes;

/**
 * Enumerates Oracle's relational data types.
 * 
 * @author SamuraiCharlie
 * */
public class OracleDatabaseTypes implements DatabaseTypes {

	private final DataType nvarchar2 = new DataType("nvarchar2", 1, EcorePackage.eINSTANCE.getEString());
	private final DataType varchar2 = new DataType("varchar2", 1, EcorePackage.eINSTANCE.getEString());
	private final DataType integer = new DataType("integer", 0, EcorePackage.eINSTANCE.getEIntegerObject());
	private final DataType datatypeLong = new DataType("long", 0, EcorePackage.eINSTANCE.getELongObject());
	private final DataType xmltype = new DataType("xmltype", 0, EcorePackage.eINSTANCE.getEString());
	private final DataType timestampWithTimeZone = new DataType("timestamp(6) with time zone", 0, EcorePackage.eINSTANCE.getEDate());
	
	@Override
	public List<DataType> getDatabaseTypes() {
		List<DataType> lst = new ArrayList<DataType>();
		lst.add(new DataType("char", 1, EcorePackage.eINSTANCE.getEString()));
		lst.add(new DataType("nchar", 1, EcorePackage.eINSTANCE.getEString()));
		lst.add(nvarchar2);
		lst.add(varchar2);
		lst.add(datatypeLong);
		lst.add(xmltype);
		lst.add(timestampWithTimeZone);
		lst.add(new DataType("raw", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("long raw", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("number", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("numeric", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("float", 0, EcorePackage.eINSTANCE.getEFloat()));
		lst.add(new DataType("dec", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("decimal", 2, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(integer);
		lst.add(new DataType("int", 0, EcorePackage.eINSTANCE.getEIntegerObject()));
		lst.add(new DataType("smallint", 0, EcorePackage.eINSTANCE.getEIntegerObject()));
		lst.add(new DataType("double precision", 0, EcorePackage.eINSTANCE.getEDoubleObject()));
		lst.add(new DataType("date", 0, EcorePackage.eINSTANCE.getEDate()));
		lst.add(new DataType("timestamp", 0, EcorePackage.eINSTANCE.getEDate()));
		lst.add(new DataType("blob", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("clob", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("bfile", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("nclob", 0, EcorePackage.eINSTANCE.getEByteArray()));
		lst.add(new DataType("rowid", 0, EcorePackage.eINSTANCE.getEBigDecimal()));
		lst.add(new DataType("urowid", 1, EcorePackage.eINSTANCE.getEBigDecimal()));
		return lst;
	}


	@Override
	public DataType fromJavaClass(Class<?> c) {
		if(c.equals(String.class)){
			return nvarchar2;
		}
		else if(c.equals(Integer.class)){
			return integer;
		}
		else if(c.equals(Long.class)){
			return datatypeLong;
		}
		return null;
	}

}
