package tech.gearsofcode.wemf.reveng.database.model;

import java.io.Serializable;

/**
 * Represents the size of a column.
 * 
 * @author SamuraiCharlie
 * */
public class DataSize implements Serializable {

	private static final long serialVersionUID = 846463728688045936L;

	private String size;
	private int columnSize;
	private int decimalDigits;





	public DataSize(String size) {
		this.size = size;
		if (size.indexOf(',')<0) {
			this.columnSize = Integer.parseInt(size);
		}
	}



	public DataSize(int columnSize, int decimalDigits) {
		this(columnSize+ (decimalDigits>0?(","+decimalDigits):""));
		this.columnSize = columnSize;
		this.decimalDigits = decimalDigits;
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
	

	public int getColumnSize() {
		return columnSize;
	}



	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}



	public int getDecimalDigits() {
		return decimalDigits;
	}



	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}



	public int getIntegerDigits() {
		return getColumnSize()-getDecimalDigits();
	}

}
