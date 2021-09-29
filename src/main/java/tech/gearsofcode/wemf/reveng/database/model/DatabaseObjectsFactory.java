package tech.gearsofcode.wemf.reveng.database.model;

import tech.gearsofcode.wemf.reveng.database.naming.ColumnClassCodes;
import tech.gearsofcode.wemf.reveng.database.naming.DatabaseNamingRules;
import tech.gearsofcode.wemf.reveng.database.naming.impl.DefaultColumnClassCodes;
import tech.gearsofcode.wemf.reveng.database.naming.impl.DefaultDatabaseNamingRules;
import tech.gearsofcode.wemf.reveng.database.sql.DatabaseTypes;
import tech.gearsofcode.wemf.reveng.database.sql.DatabaseTypesFactory;
import tech.gearsofcode.wemf.reveng.database.sql.SQLBuilder;
import tech.gearsofcode.wemf.reveng.database.sql.oracle.OracleSQLBuilder;

/**
 * Factory for the objects that are being utilized globally, and that affect the
 * modeling process.
 * 
 * @TODO: Refactor implementation. When more objects exist, it will be needed to
 *        choose among them. Besides, when more than one file is open, they may
 *        use different objects.
 * @author SamuraiCharlie
 * */
public class DatabaseObjectsFactory {

	private static final DatabaseNamingRules databaseNamingRules = new DefaultDatabaseNamingRules();
	private static final ColumnClassCodes columnClassCodes = new DefaultColumnClassCodes();
	private static final DatabaseTypes databaseTypes = DatabaseTypesFactory.getDatabaseTypes();


	/**
	 * Gets the DatabaseNamingRules that is being used.
	 * */
	public static DatabaseNamingRules getDatabaseNamingRules() {
		return databaseNamingRules;
	}



	/**
	 * Gets the SQLBuilder that is being used.
	 * */
	public static SQLBuilder getSQLBuilder() {
		return new OracleSQLBuilder();
	}



	/**
	 * Gets the ColumnClassCodes that are being used.
	 * */
	public static ColumnClassCodes getColumnClassCodes() {
		return columnClassCodes;
	}


	/**
	 * Gets the database types of the database being used.
	 * */
	public static DatabaseTypes getDatabaseTypes() {
		return databaseTypes;
	}

}
