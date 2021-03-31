package com.gearsofcode.nail.metamodel.expression;

public class FunctionExpression implements BooleanExpression {

	private NailFunction function;
	private String functionArgument;

	public enum Functions {
		IS_NULL("%s==null"), IS_NOT_NULL("%s!=null"), IS_EMPTY("(%1$s!=null && \"\".equals(%1$s))"), IS_NOT_EMPTY("(%1$s!=null && !%1$s.isEmpty())");
		
		private String format;
		private Functions(String format) {
			this.format = format;
		}
		
		public String getFormat() {
			return format;
		}
	}

	public FunctionExpression(NailFunction function, String functionArgument) {
		super();
		this.function = function;
		this.functionArgument = functionArgument;
	}






	public String getFunctionArgument() {
		return functionArgument;
	}




	public NailFunction getFunction() {
		return function;
	}
	
	
	public String toString() {
		return String.format(function.getFormat(), functionArgument);
	}
}
