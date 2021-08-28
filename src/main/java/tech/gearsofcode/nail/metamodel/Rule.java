package tech.gearsofcode.nail.metamodel;

import java.util.LinkedList;
import java.util.List;

import tech.gearsofcode.nail.metamodel.expression.BooleanExpression;

/**
 * Represents a validation rule.
 * */
public class Rule {
	
	/** Name of the rule. */
	private String name;
	
	/** Name of the field that the validation is being applied. */
	private List<String> fields = new LinkedList<String>();
	
	/** Message that is shown to the user in case of a validation error. */
	private String message;
	
	/** Software requirements */
	private String documentation;
	
	/** Indicates if field is required*/
	private boolean required;
	
	/** Indicates if field is unique*/
	private boolean unique;
	
	/** Indicates max length of field*/
	private int maxLength;
	
	/** Condition must be evaluated to true */
	private BooleanExpression assertTrue;
	
	/** Condition must be evaluated to false*/
	private BooleanExpression assertFalse;
	
	/** Field must match pattern*/
	private String pattern;
	
	/** Indicates that the rule is meant to be applied only during the modification of the entity */
	private boolean modifyOnly;
	
	/** Indicates that the rule is meant to be applied only during the exclusion of the entity */
	private boolean deleteOnly;
	
	/** Indicates that the rule depends on data accessible through the repository */
	private boolean requiresRepository;

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getFields() {
		return fields;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public BooleanExpression getAssertTrue() {
		return assertTrue;
	}
	
	public BooleanExpression getAssertFalse() {
		return assertFalse;
	}

	public void setAssertTrue(BooleanExpression assertTrue) {
		this.assertTrue = assertTrue;
	}

	public void setAssertFalse(BooleanExpression assertFalse) {
		this.assertFalse = assertFalse;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isModifyOnly() {
		return modifyOnly;
	}
	
	public void setModifyOnly(boolean modifyOnly) {
		this.modifyOnly = modifyOnly;
	}

	public boolean isRequiresRepository() {
		return requiresRepository;
	}

	public void setRequiresRepository(boolean requiresRepository) {
		this.requiresRepository = requiresRepository;
	}

	public boolean isDeleteOnly() {
		return deleteOnly;
	}

	public void setDeleteOnly(boolean deleteOnly) {
		this.deleteOnly = deleteOnly;
	}
}