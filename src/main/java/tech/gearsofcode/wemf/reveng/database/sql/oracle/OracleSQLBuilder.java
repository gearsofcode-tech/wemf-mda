package tech.gearsofcode.wemf.reveng.database.sql.oracle;

import tech.gearsofcode.wemf.reveng.database.model.Column;
import tech.gearsofcode.wemf.reveng.database.model.DataSize;
import tech.gearsofcode.wemf.reveng.database.model.DataType;
import tech.gearsofcode.wemf.reveng.database.model.DatabaseObjectsFactory;
import tech.gearsofcode.wemf.reveng.database.model.Table;
import tech.gearsofcode.wemf.reveng.database.naming.DatabaseNamingRules;
import tech.gearsofcode.wemf.reveng.database.sql.SQLBuilder;
import tech.gearsofcode.wemf.reveng.database.sql.exception.CannotBuildSQLException;

/**
 * Specifies Oracle SQL syntax.
 * 
 * @author Carlos Padoa
 *
 */
public class OracleSQLBuilder implements SQLBuilder {

	private StringBuilder strb = new StringBuilder();
	private DatabaseNamingRules databaseNamingRules = DatabaseObjectsFactory.getDatabaseNamingRules();



	@Override
	public void createForeignKey(Table childTable, Column column) {
		DataType type = column.getType();
		strb.append("ALTER TABLE ").append(childTable.getName()).append("\n\t");
		strb.append("ADD ").append(column.getName()).append(" ").append(column.getType());
		if (type.getSizeArguments() > 0) {
			strb.append("(").append(column.getSize()).append(") ");
		}
		strb.append(" CONSTRAINT ").append(databaseNamingRules.foreignKey(childTable.getName(), column.getTable().getName(), column.getName())).append(" REFERENCES ").append(column.getTable().getName()).append("(").append(column.getName()).append(")");
		strb.append(";\nGO\n\n");
	}



	@Override
	public void defineColumn(Column column) throws CannotBuildSQLException {
		DataType type = column.getType();
		DataSize size = column.getSize();
		if (type == null)
			throw new CannotBuildSQLException("Missing type definition for column: " + column.getName());
		if (type.getSizeArguments() > 0 && size == null)
			throw new CannotBuildSQLException("Missing size definition for column: " + column.getName());
		strb.append("\t");
		strb.append(column.getName()).append(" ");
		strb.append(type).append(" ");
		if (type.getSizeArguments() > 0) {
			strb.append("(").append(column.getSize()).append(") ");
		}
		if (!column.isNullAlowed()) {
			strb.append("NOT ");
		}
		strb.append("NULL ");
		if (column.isPrimaryKey()) {
			strb.append("CONSTRAINT ").append(databaseNamingRules.primaryKey(column.getTable().getName())).append(" PRIMARY KEY ");
		}
		strb.append(",\n");
	}



	@Override
	public void endTableDefinition(Table table) {
		strb.delete(strb.length() - 2, strb.length());
		strb.append("\n);\nGO\n\n");
	}



	@Override
	public void startTableDefinition(Table table) {
		strb.append("CREATE TABLE ").append(table.getName()).append(" (\n");
	}



	@Override
	public String toString() {
		return strb.toString();
	}

}
