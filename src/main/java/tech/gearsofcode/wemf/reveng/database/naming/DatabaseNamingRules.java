package tech.gearsofcode.wemf.reveng.database.naming;

import tech.gearsofcode.wemf.reveng.database.model.Column;
import tech.gearsofcode.wemf.reveng.database.model.Table;

/**
 * This interface defines naming rules to use with the database objects.
 * 
 * @author Carlos Padoa
 * */
public interface DatabaseNamingRules {

	/**
	 * @return the prefix to be used in columns' names.
	 * */
	public abstract String tableColumnPrefix(String tableName);



	/**
	 * @return the primary key's constraint name.
	 * */
	public abstract String primaryKey(String tableName);



	/**
	 * @return the foreign key's constraint name.
	 * */
	public abstract String foreignKey(String childTable, String parentTable, String columnName);



	/**
	 * @return true if the specified columnName obeys all of the naming rules.
	 * */
	public abstract boolean isValidColumnName(String columnName);
	
	
	/**
	 * Generates name for correspondent java member.
	 * */
	public abstract String getJavaMemberName(Column colum);


	/**
	 * Gets a Java class name for a given table.
	 * */
	public abstract String getClassName(Table table);
}
