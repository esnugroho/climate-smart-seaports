/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package war.model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Class representing a CSIRO Data Element
 * @author Guillaume Prevost
 * @since 25th Jan. 2013
 */
@Entity
@DiscriminatorValue(value = "EngineeringModel")
public class DataElementEngineeringModel extends DataElement {

	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The list of engineering model data contained in this data element
	 */
    @ManyToMany
    @JoinTable(name="data_element_engineering_model_data", joinColumns={@JoinColumn(name="data_element_id")}, inverseJoinColumns={@JoinColumn(name="engineering_model_data_id")})
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<EngineeringModelData> engineeringModelDataList;
    
	/**
	 * Default constructor of DataElementCsiro
	 */
	public DataElementEngineeringModel() {
		super();
	}
	
	/**
	 * Constructor of File specifying the name, login and password
	 * @param creationDate: the date when the data element was created
	 * @param name: the name of the data element
	 * @param position: the position of the data element in the user story it belongs to
	 * @param displayType: the way the data element should be displayed
	 * @param userStory: the user story to which this data element belongs
	 * @param csiroDataList: the list of CSIRO data contained in this CSIRO data element
	 */
	public DataElementEngineeringModel(Date creationDate, String name, boolean included, int position, DisplayType displayType, UserStory userStory, List<EngineeringModelData> engineeringModelDataList) {
		super(creationDate, name, included, position, displayType, userStory);
		this.engineeringModelDataList = engineeringModelDataList;
	}
	
	/**
	 * Getter for the list of engineering model data in the Data Element
	 * @return The current list of engineering model data in the Data Element
	 */
	public List<EngineeringModelData> getEngineeringModelDataList() {
		return this.engineeringModelDataList;
	}
	
	/**
	 * Setter for the list of engineering model data in the Data Element
	 * @return The new list of engineering model data in the Data Element
	 */
	public void setEngineeringModelDataList(List<EngineeringModelData> engineeringModelDataList) {
		this.engineeringModelDataList = engineeringModelDataList;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}