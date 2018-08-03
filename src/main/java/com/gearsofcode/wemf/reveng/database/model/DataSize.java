package com.gearsofcode.wemf.reveng.database.model;

import java.io.Serializable;

/**
 * Represents the size of a column.
 * 
 * @author Carlos Padoa
 * */
public class DataSize implements Serializable {

	private static final long serialVersionUID = 846463728688045936L;

	private String size;



	public DataSize(String size) {
		this.size = size;
	}



	public DataSize(int columnSize, int decimalDigits) {
		this(columnSize+ (decimalDigits>0?(","+decimalDigits):""));
	}



	public String toString() {
		return size;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (size == null)
			return false;
		DataSize otherSize = (DataSize) obj;
		return size.equals(otherSize.size);
	}



	@Override
	public int hashCode() {
		if (size == null)
			return 0;
		return size.hashCode();
	}

}
