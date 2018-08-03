package com.gearsofcode.wemf.reveng.database.model;

import java.io.Serializable;

import org.eclipse.emf.ecore.EDataType;

/**
 * Represents the type of a database column.
 * 
 * @author Carlos Padoa
 * */
public class DataType implements Serializable {

	private static final long serialVersionUID = 165962663767736378L;

	private String name;
	private int sizeArguments;
	private EDataType eDataType;


	public DataType(String name, int sizeArguments, EDataType eDataType) {
		this.name = name;
		this.sizeArguments = sizeArguments;
		this.eDataType = eDataType;
	}



	public String getName() {
		return name;
	}



	public int getSizeArguments() {
		return sizeArguments;
	}



	@Override
	public String toString() {
		return name;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof DataType))
			return false;
		DataType otherType = (DataType) obj;
		if (name == null || otherType.name == null)
			return false;
		return name.equals(otherType.name);
	}



	@Override
	public int hashCode() {
		if (name == null)
			return 0;
		return name.hashCode();
	}



	public EDataType getEMFType() {
		return eDataType;
	}

}
