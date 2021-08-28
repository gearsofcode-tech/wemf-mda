package tech.gearsofcode.wemf.reveng.database.naming;

/**
 * Represents an acronym used in the column's nomenclature.
 * 
 * @author Carlos Padoa
 * */
public class ColumnClassCode {

	public ColumnClassCode(String code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}

	private String code, name, description;



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

}
