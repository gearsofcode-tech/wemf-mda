package tech.gearsofcode.wemf.reveng.database.model;

public class ReferencedColumn {

	private String name;
	private String table;

	public ReferencedColumn(String table, String name) {
		super();
		this.name = name;
		this.table = table;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ReferencedColumn other = (ReferencedColumn) obj;
		if (name == null) {
			if (other.name != null) return false;
		}
		else if (!name.equals(other.name)) return false;
		if (table == null) {
			if (other.table != null) return false;
		}
		else if (!table.equals(other.table)) return false;
		return true;
	}



	public String getTable() {
		return table;
	}



	public void setTable(String table) {
		this.table = table;
	}
}
