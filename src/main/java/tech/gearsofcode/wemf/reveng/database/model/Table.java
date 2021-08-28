package tech.gearsofcode.wemf.reveng.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a database table.
 * 
 * @author Carlos Padoa
 * */
public class Table implements Comparable<Table>, Serializable {

	private static final long serialVersionUID = 2526715559972216434L;

	private DataModel dataModel;
	private TreeSet<Column> columns;
	private String name;

	private static Logger logger = LoggerFactory.getLogger(Table.class);


	/**
	 * @param dataModel the data model the table will belong to. Constructor for
	 *            a new table.
	 * */
	public Table(DataModel dataModel) {
		this(dataModel, "NEW_TABLE");
	}



	public Table (DataModel dm, String tableName) {
		columns = new TreeSet<Column>(new OrdinalPositionComparator());
		this.dataModel = dm;
		setName(tableName);
		dm.addTable(this);
	}



	public PrimaryKey getPrimaryKey() {
		List<Column> lst = new ArrayList<Column>();
		for (Column col : columns) {
			if (col.isPrimaryKey() && !col.isForeignKey(this)){
				lst.add(col);
			}
		}
		if (lst.size() > 0) {
			return new PrimaryKey(lst.toArray(new Column[lst.size()]));
		}
		else {
			return null;
		}
	}



	public DataModel getDataModel() {
		return dataModel;
	}



	public void addColumn(Column newColumn) {
		columns.add(newColumn);
		newColumn.setTable(this);
	}



	public void addForeignKeyColumn(Column newColumn) {
		columns.add(newColumn);
	}



	public void addForeignKey(PrimaryKey selectedPrimaryKey) {
		Column[] pkColumns = selectedPrimaryKey.getColumns();
		for (Column pkColumn : pkColumns)
			addForeignKeyColumn(pkColumn);
	}



	public Set<Column> getColumns() {
		return columns;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public int compareTo(Table otherTable) {
		if (name != null && otherTable.name != null) {
			return name.compareTo(otherTable.name);
		}
		return -1;
	}



	@Override
	public boolean equals(Object obj) {
		if (name == null || obj == null)
			return false;
		if (!(obj instanceof Table))
			return false;
		Table otherTable = (Table) obj;
		return name.equals(otherTable.name);
	}



	@Override
	public int hashCode() {
		if (name == null)
			return 0;
		return name.hashCode();
	}



	public void deleteColumn(Column column) {
		columns.remove(column);
	}



	public void removeColumn(Column column) {
		columns.remove(column);
	}

	
	public String toString(){
		return name;
	}



	public List<Column> getForeignKeys() {
		//logger.debug("Foreign Keys of "+name);
		List<Column> lstFks = new ArrayList<Column>();
		for(Column column:this.columns){
			if(column.isForeignKey(this)){
				//logger.debug("ForeignKey",column.getName());
				lstFks.add(column);
			}
		}
		return lstFks;
	}
}
