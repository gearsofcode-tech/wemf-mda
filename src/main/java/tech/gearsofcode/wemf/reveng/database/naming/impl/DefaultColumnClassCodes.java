package tech.gearsofcode.wemf.reveng.database.naming.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tech.gearsofcode.wemf.reveng.database.naming.ColumnClassCode;
import tech.gearsofcode.wemf.reveng.database.naming.ColumnClassCodes;

/**
 * Each column's name must have a class code. The class enumerates some default
 * class codes.
 * 
 * @author Carlos Padoa
 * */
public class DefaultColumnClassCodes implements ColumnClassCodes {

	private static List<ColumnClassCode> lstCodes;
	private static Set<String> codes = new HashSet<String>();
	private static final ColumnClassCode nameColumnClassCode = new ColumnClassCode("NM", "Name", "");
	private static final ColumnClassCode numberColumnClassCode = new ColumnClassCode("NR", "Number", "");
	private static final ColumnClassCode dateColumnClassCode = new ColumnClassCode("DT", "Date", "");
	private static final ColumnClassCode codeColumnClassCode = new ColumnClassCode("CD", "Code", "");
	private static final ColumnClassCode valueColumnClassCode = new ColumnClassCode("VL", "Value", "");
	
	static {
		lstCodes = new ArrayList<ColumnClassCode>();
		addCode(codeColumnClassCode);
		addCode(new ColumnClassCode("DS", "Description", ""));
		addCode(valueColumnClassCode);
		addCode(new ColumnClassCode("IN", "Indicator", ""));
		addCode(dateColumnClassCode);
		addCode(nameColumnClassCode);
		addCode(numberColumnClassCode);
		addCode(new ColumnClassCode("MD", "Measure", ""));
		addCode(new ColumnClassCode("QN", "Quantity", ""));
		addCode(new ColumnClassCode("SG", "Acronym", ""));
		addCode(new ColumnClassCode("PR", "Percentage", ""));
		addCode(new ColumnClassCode("SQ", "Sequential", ""));
		addCode(new ColumnClassCode("TX", "Text", ""));
		addCode(new ColumnClassCode("MM", "Multimedia", ""));
	}



	@Override
	public boolean hasCode(String code) {
		return codes.contains(code);
	}



	private static void addCode(ColumnClassCode columnClassCode) {
		lstCodes.add(columnClassCode);
		codes.add(columnClassCode.getCode());
	}



	@Override
	public List<ColumnClassCode> getColumnClassCodeList() {
		return lstCodes;
	}



	@Override
	public ColumnClassCode fromJavaClass(Class<?> c) {
		if(c.equals(String.class)){
			return nameColumnClassCode;
		}
		else if(c.equals(java.util.Date.class)){
			return dateColumnClassCode;
		}
		else if(c.equals(Integer.class) || c.equals(Float.class) || c.equals(Double.class)){
			return numberColumnClassCode;
		}
		else if(c.equals(Long.class)){
			return codeColumnClassCode;
		}
		return valueColumnClassCode;
	}

}
