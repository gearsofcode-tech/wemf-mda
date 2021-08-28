package tech.gearsofcode.nail.metamodel.expression;

public enum BinaryOperator {

	AND, OR, XOR, IMPLIES, EQUALS, GREATER_THAN, GREATER_EQUALS, LESS_THAN, LESS_EQUALS, NOT_EQUALS, PLUS, MINUS, TIMES, DIVIDED;

	public static BinaryOperator fromString(String text) {
		return valueOf(text.replaceAll("-", "_"));
	}

}
