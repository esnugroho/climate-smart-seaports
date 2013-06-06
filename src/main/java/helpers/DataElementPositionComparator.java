/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package helpers;

import java.util.Comparator;

import war.model.DataElement;

/**
 * Implementation of the Comparator interface enabling the comparison of 
 * DataElements objects in function of their position in the User Story 
 * they belong to.
 * @author Guillaume Prevost
 */
public class DataElementPositionComparator implements Comparator<DataElement> {
	@Override
    public int compare(DataElement de1, DataElement de2) {
        return Integer.compare(de1.getPosition(), de2.getPosition());
    }
}