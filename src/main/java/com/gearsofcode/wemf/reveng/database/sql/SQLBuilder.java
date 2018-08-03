package com.gearsofcode.wemf.reveng.database.sql;

import com.gearsofcode.wemf.reveng.database.model.Column;
import com.gearsofcode.wemf.reveng.database.model.Table;
import com.gearsofcode.wemf.reveng.database.sql.exception.CannotBuildSQLException;

/**
 * Defines an interface for stablishing SQL syntax to be used with SQL Generation.
 * 
 * @author Carlos Padoa
 * */
public interface SQLBuilder {
	public void startTableDefinition(Table table);
	public void endTableDefinition(Table table);
	public void defineColumn(Column column) throws CannotBuildSQLException;
	public void createForeignKey(Table childTable, Column column);
}
