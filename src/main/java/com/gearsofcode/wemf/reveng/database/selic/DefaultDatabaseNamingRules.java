package com.gearsofcode.wemf.reveng.database.selic;

import com.gearsofcode.wemf.reveng.database.model.Column;
import com.gearsofcode.wemf.reveng.database.model.Table;
import com.gearsofcode.wemf.reveng.database.naming.DatabaseNamingRules;

/**
 * This class stablishes some default database naming rules:
 * 
 * @author Carlos Padoa
 * */
public class DefaultDatabaseNamingRules implements DatabaseNamingRules {

	private DefaultColumnClassCodes felixColumnClassCodes = new DefaultColumnClassCodes();



	/**
	 * Table column prefix vary according to the number of names (separated by
	 * underline) the table name has: 1) First 4 digits of a table with a single
	 * name 2) 2 digits from the first name, 2 digits from the second name of a
	 * table with two names. 3) 2 digits from the first name, 1 digits from the
	 * second and third names of a table with 3 or more names.
	 * 
	 * */
	@Override
	public String tableColumnPrefix(String tableName) {
		if (tableName.length() < 4)
			return "????";
		String[] names = tableName.split("_");
		switch (names.length) {
			case 1:
				return tableName.substring(0, 4);
			case 2:
				return (names[0].length() >= 2 ? names[0].substring(0, 2) : "??") + (names[1].length() >= 2 ? names[1].substring(0, 2) : "??");
			case 3:
				return (names[0].length() >= 2 ? names[0].substring(0, 2) : "??") + (names[1].length() >= 1 ? names[1].substring(0, 1) : "?") + (names[2].length() >= 1 ? names[2].substring(0, 1) : "?");
			default:
				return (names[0].length() >= 1 ? names[0].substring(0, 1) : "??") + (names[1].length() >= 1 ? names[1].substring(0, 1) : "?") + (names[2].length() >= 1 ? names[2].substring(0, 1) : "?")
						+ (names[3].length() >= 1 ? names[3].substring(0, 1) : "?");
		}
	}



	/**
	 * Primary key constraint is formed by "PK" plus the table column prefix.
	 * */
	@Override
	public String primaryKey(String tableName) {
		return "PK_" + tableColumnPrefix(tableName);
	}



	/**
	 * Foreign key constraint is formed by "FK" plus the parent table column
	 * prefix, the child table column prefix, and the column name.
	 * */
	@Override
	public String foreignKey(String childTable, String parentTable, String columnName) {
		return "FK_" + tableColumnPrefix(parentTable) + "_" + tableColumnPrefix(childTable) + "_" + columnName.substring(5);
	}



	/**
	 * Column name is valid if: 1)Constains 4 digit prefix. 2)Constains 2 digit
	 * class code. 3)Column name has at least 2 digits.
	 * */
	@Override
	public boolean isValidColumnName(String columnName) {
		String[] names = columnName.split("_");
		if (names.length < 3)
			return false;
		if (names[0].length() != 4)
			return false;
		if (!this.felixColumnClassCodes.hasCode(names[1]))
			return false;
		return names[2].length() >= 2;
	}



	@Override
	public String getJavaMemberName(Column column) {
		return camelCaseWithoutPrefix(column.getName());
	}



	private String camelCaseWithoutPrefix(String string) {
		String name = string;
		int index = string.indexOf("_");
		if (index > 0) {
			name = string.substring(index+1);
		}
		StringBuilder strb = new StringBuilder();
		boolean upperCase = false;
		for (int i=0;i<name.length();i++) {
			if (name.toCharArray()[i]=='_')continue;
			upperCase = i>0 && name.toCharArray()[i-1]=='_';
			if (upperCase)strb.append(name.substring(i, i+1).toUpperCase());
			else strb.append(name.substring(i, i+1).toLowerCase());
		}
		return strb.toString();
	}



	@Override
	public String getClassName(Table table) {
		String camelCase = camelCaseWithoutPrefix(table.getName());
		return camelCase.substring(0, 1).toUpperCase()+camelCase.substring(1);
	}

}
