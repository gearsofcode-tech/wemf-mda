package com.gearsofcode.wemf.reveng.database.model;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.gearsofcode.wemf.reveng.database.naming.ColumnClassCode;
import com.gearsofcode.wemf.reveng.database.naming.ColumnClassCodes;

/**
 * Represents a column of a database table.
 * @author Carlos Padoa
 *
 */
public class Column implements Comparable<Column>, Serializable {

	private static final long serialVersionUID = 3793468062400860074L;

	private Table table;
	private String name;
	private DataType type;
	private DataSize size;
	private boolean primaryKey;
	private boolean allowNull;
	private String prefix;
	private int ordinalPosition;

	public String getName() {
		return name;
	}



	public void setName(String nome) {
		this.name = nome;
	}

	
	
	private void setUnprefixedName(String name, ColumnClassCode classCode) {
		this.name = prefix + "_" + classCode.getCode() + "_" + name;
	}

	
	
	public DataType getType() {
		return type;
	}



	public void setType(DataType tipo) {
		this.type = tipo;
	}



	public DataSize getSize() {
		return size;
	}



	public void setSize(DataSize tamanho) {
		this.size = tamanho;
	}



	public boolean isPrimaryKey() {
		return primaryKey;
	}



	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}



	public Table getTable() {
		return table;
	}



	public void setTable(Table tabela) {
		this.table = tabela;
	}



	/**
	 * Sets the first four characters of the column name.
	 * */
	public void setTablePrefix(String prefix) {
		this.prefix = prefix;
		String columnName = name;
		if (columnName != null && columnName.length() > 5 && columnName.charAt(4) == '_') {
			columnName = prefix + columnName.substring(4);
		}
		else {
			columnName = prefix + "_";
		}
		name = columnName;
	}



	@Override
	public int compareTo(Column otherColumn) {
		if (table != null && name != null && this.table.equals(otherColumn.table)) {
			return name.compareTo(otherColumn.name);
		}
		else {
			if (table != null && otherColumn.table != null) {
				return table.compareTo(otherColumn.table);
			}
		}
		return -1;
	}



	public boolean isForeignKey(Table ownerTable) {
		return !table.equals(ownerTable) && isPrimaryKey();
	}



	public void allowNull(boolean columnIsNullable) {
		this.allowNull = columnIsNullable;
	}



	public boolean isNullAlowed() {
		return allowNull;
	}



	



	



	public void setJavaName(Method method) {
		String propertyName = method.getName().substring(3);
		propertyName = insertUnderlines(propertyName).toUpperCase();
		ColumnClassCodes columnClassCodes = DatabaseObjectsFactory.getColumnClassCodes();
		ColumnClassCode classCode = columnClassCodes.fromJavaClass(method.getReturnType());
		setUnprefixedName(propertyName, classCode);
	}

	
	
	private String insertUnderlines(String propertyName) {
		boolean anteriorMaiusculo = false;
		final char[] charArray = propertyName.toCharArray();
		StringBuilder strb = new StringBuilder();
		strb.append(charArray[0]);
		for(int i=1;i<charArray.length;i++){
			if(Character.isUpperCase(charArray[i])){
				if(!anteriorMaiusculo)strb.append("_");
				anteriorMaiusculo = true;
			}
			else{
				anteriorMaiusculo = false;
			}
			strb.append(charArray[i]);
		}
		return strb.toString();
	}



	@Override
	public String toString() {
		return "Column [allowNull=" + allowNull + ", name=" + name + ", size=" + size + ", table=" + table + ", type=" + type + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Column other = (Column) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		}
		else if (!table.equals(other.table))
			return false;
		return true;
	}



	public int getOrdinalPosition() {
		return ordinalPosition;
	}



	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
}
