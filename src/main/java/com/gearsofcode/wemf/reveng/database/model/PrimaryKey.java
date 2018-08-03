package com.gearsofcode.wemf.reveng.database.model;

/**
 * Represents a table's primary key.
 * 
 * @author Carlos Padoa
 * */
public class PrimaryKey {

	private Column[] columns;



	public PrimaryKey(Column... columns) {
		this.columns = columns;
	}



	public Column[] getColumns() {
		return columns;
	}



	@Override
	public String toString() {
		String txt = "";
		for (Column col : columns)
			txt += col.getName() + ", ";
		if (txt.length() > 2) {
			txt = txt.substring(0, txt.length() - 2);
		}
		return txt;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof PrimaryKey))
			return false;
		PrimaryKey other = (PrimaryKey) obj;
		return columns.equals(other.columns);
	}



	@Override
	public int hashCode() {
		return columns.hashCode();
	}

}
