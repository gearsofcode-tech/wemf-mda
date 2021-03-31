package com.gearsofcode.nail.metamodel.expression;

public enum NailFunction {

	IS_NULL("%s==null"), 
	IS_NOT_NULL("%s!=null"), 
	IS_EMPTY("(%1$s!=null && %1$s.isEmpty())"), 
	IS_NOT_EMPTY("(%1$s!=null && !%1$s.isEmpty())");
	
	private String format;
	
	private NailFunction(String format) {
		this.format = format;
	}
	
	
	
	public String getFormat() {
		return format;
	}



	public static NailFunction fromString(String text) {
		for (NailFunction n : NailFunction.values()) {
			if (n.name().replaceAll("_", "-").equals(text)) return n;
		}
		throw new IllegalArgumentException("Unknown function: "+text);
	}

}
