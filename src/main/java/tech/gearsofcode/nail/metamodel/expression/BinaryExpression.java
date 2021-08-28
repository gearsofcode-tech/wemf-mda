package tech.gearsofcode.nail.metamodel.expression;

public class BinaryExpression implements BooleanExpression{
	
	private BooleanExpression operandA;
	private BooleanExpression operandB;
	private BinaryOperator operator;
	
	
	public BinaryExpression(BinaryOperator operator) {
		super();
		this.operator = operator;
	}


	public BooleanExpression getOperandA() {
		return operandA;
	}


	public void setOperandA(BooleanExpression operandA) {
		this.operandA = operandA;
	}


	public BooleanExpression getOperandB() {
		return operandB;
	}


	public void setOperandB(BooleanExpression operandB) {
		this.operandB = operandB;
	}


	public BinaryOperator getOperator() {
		return operator;
	}


	public void setOperator(BinaryOperator operator) {
		this.operator = operator;
	}
	
	
	public String toString() {
		switch(operator) {
			case PLUS:
				return operandA.toString() + " + " + operandB.toString();
			case MINUS:
				return operandA.toString() + " - " + operandB.toString();
			case TIMES:
				return operandA.toString() + " * " + operandB.toString();
			case DIVIDED:
				return operandA.toString() + " / " + operandB.toString();
			case AND:
				return operandA.toString() + " && " + operandB.toString();
			case OR:
				return operandA.toString() + " || " + operandB.toString();
			case XOR:
				return operandA.toString() + " ^ " + operandB.toString();
			case GREATER_THAN:
				return operandA.toString() + " > " + operandB.toString();
			case GREATER_EQUALS:
				return operandA.toString() + " >= " + operandB.toString();
			case LESS_EQUALS:
				return operandA.toString() + " <= " + operandB.toString();
			case LESS_THAN:
				return operandA.toString() + " < " + operandB.toString();
			case IMPLIES:
				return "(!" + operandA.toString() + " || " + operandB.toString() + ")";
			case EQUALS:
				return printEquals();
			case NOT_EQUALS:
				return printNotEquals();
		}
		throw new IllegalArgumentException("Unknown operator:" + operator.name());
	}
	
	
	private String printEquals() {
		boolean hasIntegerOperand = operandA.toString().matches("^\\d$");
		if (hasIntegerOperand) return operandA.toString() + " == " + operandB.toString();
		return operandA.toString() + ".equals(" + operandB.toString() + ")";
	}
	
	
	private String printNotEquals() {
		boolean hasIntegerOperand = operandA.toString().matches("^\\d$");
		if (hasIntegerOperand) return operandA.toString() + " != " + operandB.toString();
		return "!" + operandA.toString() + ".equals(" + operandB.toString() + ")";
	}

}
