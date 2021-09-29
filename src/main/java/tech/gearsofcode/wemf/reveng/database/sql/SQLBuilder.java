package tech.gearsofcode.wemf.reveng.database.sql;

import tech.gearsofcode.wemf.reveng.database.model.Column;
import tech.gearsofcode.wemf.reveng.database.model.Table;
import tech.gearsofcode.wemf.reveng.database.sql.exception.CannotBuildSQLException;

/**
 * Defines an interface for stablishing SQL syntax to be used with SQL Generation.
 * 
 * @author SamuraiCharlie
 * */
public interface SQLBuilder {
	public void startTableDefinition(Table table);
	public void endTableDefinition(Table table);
	public void defineColumn(Column column) throws CannotBuildSQLException;
	public void createForeignKey(Table childTable, Column column);
}
