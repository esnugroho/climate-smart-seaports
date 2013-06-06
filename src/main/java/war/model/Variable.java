/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package war.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Class representing a climate variable
 * @author Guillaume Prevost
 * @since 20th Dec. 2012
 */
@MappedSuperclass
public abstract class Variable {
	
	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * Unique ID of the variable
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	
	/**
	 * The name of the variable
	 */
	@Column
	protected String name;
	
	/**
	 * The short name of the variable
	 */
	@Column
	protected String shortName;

	/**
	 * The description of the variable
	 */
	@Column
	protected String description;
	
	/**
	 * The unit of measure of the variable
	 */
	@Column
	protected String uom;
	
	/**
	 * The unit of measure of the variation of the variable
	 */
	@Column
	protected String uomVariation;
	
	/**
	 * Getter for the unique ID of the variable
	 * @return The unique ID of the variable
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Getter for the name of the variable
	 * @return the current name of the variable
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the name of the variable
	 * @param name: the new name of the variable
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the short name of the variable
	 * @return the current short name of the variable
	 */
	public String getShortName() {
		return this.shortName;
	}
	
	/**
	 * Setter for the short name of the variable
	 * @param name: the new short name of the variable
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	/**
	 * Getter for the description of the variable
	 * @return the current description of the variable
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Setter for the description of the variable
	 * @param name: the new description of the variable
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for the unit of measure of the variable
	 * @return the unit of measure of the variable, as a String
	 */
	public String getUom() {
		return this.uom;
	}
	
	/**
	 * Setter for the unit of measure of the variable
	 * @param uom : the new unit of measure of the variable
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	/**
	 * Getter for the unit of measure of the variation of the variable
	 * @return the unit of measure of the variation of the variable, as a String
	 */
	public String getUomVariation() {
		return this.uomVariation;
	}
	
	/**
	 * Setter for the unit of measure of the variation of the variable
	 * @param uomVariation : the new unit of measure of the variation of the variable
	 */
	public void setUomVariation(String uomVariation) {
		this.uomVariation = uomVariation;
	}
	
	/**
	 * String representation of a Variable object
	 * @return the short name and the unit of measure of the Variable, as a string
	 */
	@Override
	public String toString() {
		return (this.shortName + " (" + this.uom + ")");
	}
}