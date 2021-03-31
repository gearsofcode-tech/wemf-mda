package com.gearsofcode.nail.metamodel.expression;

public class GroupExpression implements BooleanExpression{

	private BooleanExpression groupedExpression;

	public GroupExpression(BooleanExpression groupedExpression) {
		super();
		this.groupedExpression = groupedExpression;
	}

	
	public String toString() {
		return "(" + groupedExpression.toString() + ")";
	}
}
