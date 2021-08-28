package tech.gearsofcode.nail.metamodel.expression;

public class ValueExpression implements BooleanExpression {

	private String value;

	public ValueExpression(String value) {
		super();
		this.value = value;
	}

	
	public String toString() {
		String[] parts = value.split("\\.");
		if (parts.length>1) {
			StringBuilder strb = new StringBuilder();
			strb.append(parts[0]);
			for (int i=1;i<parts.length;i++) {
				strb.append(".get");
				strb.append(parts[i].substring(0,1).toUpperCase());
				strb.append(parts[i].substring(1));
				strb.append("()");
			}
			value = strb.toString();
		}
		return value.replaceAll("'", "\"");
	}
}
