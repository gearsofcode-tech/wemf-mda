package com.gearsofcode.nail.metamodel.expression;

public class NegationExpression implements BooleanExpression{
	
	private BooleanExpression negatedValue;

	public NegationExpression(BooleanExpression negatedValue) {
		super();
		this.negatedValue = negatedValue;
	}

	public String toString() {
		return "!(" + negatedValue.toString() + ")";
		
	}

}
