package tech.gearsofcode.nail.metamodel.expression;

import java.util.List;

public class RepositoryFunctionExpression implements BooleanExpression {

	private String entityName;
	private String function;
	private List<String> functionArguments;


	public RepositoryFunctionExpression(String entityName, String functionName, List<String> functionArguments) {
		super();
		this.entityName = entityName;
		this.function = getJavaName(functionName);
		this.functionArguments = functionArguments;
	}



	private String getJavaName(String functionName) {
		StringBuilder strb = new StringBuilder();
		String[] parts = functionName.substring("REPOSITORY-".length()).split("-");
		String part;
		for (int i=0; i<parts.length;i++) {
			part = parts[i].toLowerCase();
			if (i>0) {
				part = part.substring(0,1).toUpperCase()+part.substring(1);
			}
			strb.append(part);
		}
		return strb.toString();
	}



	public List<String> getFunctionArguments() {
		return functionArguments;
	}




	public String getFunction() {
		return function;
	}
	
	
	public String toString() {
		StringBuilder strb = new StringBuilder();
		String repositoryName = entityName.substring(0,1).toLowerCase()+entityName.substring(1) + "Repository";
		strb.append(repositoryName).append(".").append(function).append("(");
		strb.append(functionArguments.get(0));
		for (int i=1; i<functionArguments.size();i++) {
			strb.append(",").append(functionArguments.get(i));
		}
		strb.append(")");
		return strb.toString();
	}
}
