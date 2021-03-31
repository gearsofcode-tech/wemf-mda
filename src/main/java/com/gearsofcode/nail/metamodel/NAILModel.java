package com.gearsofcode.nail.metamodel;

import java.util.LinkedList;
import java.util.List;

public class NAILModel {

	private String entity = null;
	private String wemfFile = null;
	private List<Rule> rules = new LinkedList<Rule>();
	private List<Rule> validateOnInsert = new LinkedList<Rule>();
	private List<Rule> validateOnDelete = new LinkedList<Rule>();
	private List<Rule> validateOnUpdate = new LinkedList<Rule>();



	public String getWemfFile() {
		return wemfFile;
	}



	public void setWemfFile(String wemfFile) {
		this.wemfFile = wemfFile;
	}



	public List<Rule> getRules() {
		return rules;
	}



	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}



	public List<Rule> getValidateOnInsert() {
		return validateOnInsert;
	}



	public void setValidateOnInsert(List<Rule> validateOnInsert) {
		this.validateOnInsert = validateOnInsert;
	}



	public List<Rule> getValidateOnDelete() {
		return validateOnDelete;
	}



	public void setValidateOnDelete(List<Rule> validateOnDelete) {
		this.validateOnDelete = validateOnDelete;
	}



	public List<Rule> getValidateOnUpdate() {
		return validateOnUpdate;
	}



	public void setValidateOnUpdate(List<Rule> validateOnUpdate) {
		this.validateOnUpdate = validateOnUpdate;
	}



	public String getEntity() {
		return entity;
	}



	public void setEntity(String entity) {
		this.entity = entity;
	}


}
