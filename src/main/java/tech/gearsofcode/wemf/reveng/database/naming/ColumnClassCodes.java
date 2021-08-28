package tech.gearsofcode.wemf.reveng.database.naming;

import java.util.List;

/**
 * Represents a group of Column Class Codes to use in a naming scheme.
 * 
 * @author Carlos Padoa
 * */
public interface ColumnClassCodes {

	/**
	 * @return true if the specified code is in the list of column class codes.
	 * */
	public boolean hasCode(String code);



	/**
	 * @return a list containing all the column class codes.
	 * */
	public List<ColumnClassCode> getColumnClassCodeList();
	
	
	/**
	 * @return the column class code more suitable to the specified java class.
	 */
	public ColumnClassCode fromJavaClass(Class<?> c);
}
