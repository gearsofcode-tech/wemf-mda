package tech.gearsofcode.wemf.reveng.database.model;

import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.gearsofcode.wemf.reveng.database.sql.DatabaseTypes;
import tech.gearsofcode.wemf.reveng.database.sql.SQLBuilder;
import tech.gearsofcode.wemf.reveng.database.sql.exception.CannotBuildSQLException;
import tech.gearsofcode.wemf.reveng.database.sql.java.DynamicDataTypes;

/**
 * Represents a data model.
 * 
 * @author Carlos Padoa
 * */
public class DataModel implements Serializable {

	private static final long serialVersionUID = 5839606007222908509L;
	private static Logger logger = LoggerFactory.getLogger(DataModel.class);

	private List<Table> tables = new ArrayList<Table>();
	private String name;



	public DataModel(String modelName) {
		this.name = modelName;
	}


	public static DataModel reverseEngineer(DatabaseMetaData metaData, String schema) {
		logger.debug("Reverse Engineer started.");
		DataModel dm = new DataModel(schema);
		try {
			ResultSet rsColumns = null;
			ResultSet rsPrimaryKeys = null;
			ResultSet rsTables = metaData.getTables(null, schema, null, new String[]{"TABLE"});
			ResultSet rsForeignKeys = null;
			Set<String> primaryKeys;
			
			String tableName;
			String columnName;
			String typeName;
			int typeId;
			int columnSize;
			int decimalDigits;
			int ordinalPosition;
			boolean isNullable;
			boolean isPrimaryKey;
	
			Table table;
			Column column;
			DataType dataType;
			while(rsTables.next()){
				tableName = rsTables.getString("TABLE_NAME");
				logger.info(tableName);
				table = new Table(dm, tableName);
				primaryKeys = new HashSet<String>();
				
				rsPrimaryKeys = metaData.getPrimaryKeys(null, schema, tableName);
				while(rsPrimaryKeys.next()){
					primaryKeys.add(rsPrimaryKeys.getString("COLUMN_NAME"));
				}
				
				Map<String, ReferencedColumn> mapForeignKeys = new HashMap<String, ReferencedColumn>();
				ReferencedColumn refColumn;
				
				rsForeignKeys = metaData.getImportedKeys(null, schema, tableName);
				while(rsForeignKeys.next()) {
					refColumn = new ReferencedColumn(rsForeignKeys.getString("PKTABLE_NAME"), rsForeignKeys.getString("PKCOLUMN_NAME"));
					mapForeignKeys.put(rsForeignKeys.getString("FKCOLUMN_NAME"), refColumn);
				}
				rsForeignKeys.close();
				
				rsColumns = metaData.getColumns(null, schema, tableName, null);
				while(rsColumns.next()){
					columnName = rsColumns.getString("COLUMN_NAME");
					typeId = rsColumns.getInt("DATA_TYPE");
					dataType = DynamicDataTypes.get(typeId);
					typeName = rsColumns.getString("TYPE_NAME");
					columnSize = rsColumns.getInt("COLUMN_SIZE");
					decimalDigits = rsColumns.getInt("DECIMAL_DIGITS");
					ordinalPosition = rsColumns.getInt("ORDINAL_POSITION");
					if(dataType==null){
						dataType = DynamicDataTypes.registerType(typeId,typeName,columnSize,decimalDigits);
					}
					isNullable = rsColumns.getString("IS_NULLABLE").equals("YES");
					isPrimaryKey = primaryKeys.contains(columnName);
					logger.info("--"+columnName+" "+typeName+"("+columnSize+"," + decimalDigits+") "+ (isNullable?"NULL":"NOT NULL"));
					
					column = new Column();
					column.setName(columnName);
					column.setType(dataType);
					column.setSize(new DataSize(columnSize,decimalDigits));
					column.allowNull(isNullable);
					column.setPrimaryKey(isPrimaryKey);
					column.setOrdinalPosition(ordinalPosition);
					column.setReferencedColumn(mapForeignKeys.get(columnName));
					table.addColumn(column);
				}
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Reverse Engineer finished.");
		return dm;
	}



	public void addTable(Table t) {
		logger.debug("Adding table to data model: "+t);
		tables.add(t);
	}



	public List<PrimaryKey> getPrimaryKeys() {
		List<PrimaryKey> lst = new ArrayList<PrimaryKey>();
		PrimaryKey primaryKey;
		for (Table t : tables) {
			primaryKey = t.getPrimaryKey();
			if (primaryKey != null)
				lst.add(primaryKey);
		}
		return lst;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<Table> getTables() {
		ArrayList<Table> tabelas = new ArrayList<Table>();
		tabelas.addAll(tables);
		return tabelas;
	}



	public SQLBuilder toSQL() throws CannotBuildSQLException {
		SQLBuilder sqlb = DatabaseObjectsFactory.getSQLBuilder();
		Set<Column> columns;
		Set<Column> foreignKeys;
		Map<Table, Set<Column>> mapFKs = new HashMap<Table, Set<Column>>();
		for (Table table : tables) {
			sqlb.startTableDefinition(table);
			columns = table.getColumns();
			for (Column column : columns) {
				if (!column.isForeignKey(table)) {
					sqlb.defineColumn(column);
				}
				else {
					foreignKeys = mapFKs.get(table);
					if (foreignKeys == null) {
						foreignKeys = new HashSet<Column>();
						mapFKs.put(table, foreignKeys);
					}
					foreignKeys.add(column);
				}
			}
			sqlb.endTableDefinition(table);
		}
		for (Table table : mapFKs.keySet()) {
			foreignKeys = mapFKs.get(table);
			for (Column fk : foreignKeys) {
				sqlb.createForeignKey(table, fk);
			}
		}
		return sqlb;
	}



	public void deleteColumn(Column column) {
		for (Table table : tables) {
			table.deleteColumn(column);
		}
	}

}
