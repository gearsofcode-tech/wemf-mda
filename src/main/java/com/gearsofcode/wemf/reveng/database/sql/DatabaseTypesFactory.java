package com.gearsofcode.wemf.reveng.database.sql;

import com.gearsofcode.wemf.reveng.database.sql.java.DynamicDataTypes;
import com.gearsofcode.wemf.reveng.database.sql.oracle.OracleDatabaseTypes;

/**
 * Factory of database types.
 * 
 * @TODO: refactor to allow more than one technology. Currently, only ORACLE is
 *        supported.
 *        
 * @author Carlos Padoa
 * */
public class DatabaseTypesFactory {

	public enum SupportedDatabases {
		ORACLE, REVERSE_ENGINEERED
	};

	private static SupportedDatabases selectedDatabase = SupportedDatabases.ORACLE;



	public static DatabaseTypes getDatabaseTypes() {
		switch (selectedDatabase) {
			case REVERSE_ENGINEERED:
				return DynamicDataTypes.instance();
			case ORACLE:
			default:
				return new OracleDatabaseTypes();
		}
	}



	public static void selectedDatabase(SupportedDatabases selectedDatabase) {
		DatabaseTypesFactory.selectedDatabase = selectedDatabase;
	}
}
