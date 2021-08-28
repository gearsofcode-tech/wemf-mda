package tech.gearsofcode.wemf.reveng.database.model;

import java.util.Comparator;

/**
 * Helps to order columns by the ordinal position.
 * @author Carlos Padoa
 *
 */
public class OrdinalPositionComparator implements Comparator<Column> {

	@Override
	public int compare(Column o1, Column o2) {
		return o1.getOrdinalPosition()-o2.getOrdinalPosition();
	}

}
