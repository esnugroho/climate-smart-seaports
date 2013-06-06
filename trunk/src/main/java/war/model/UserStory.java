/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package war.model ;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import war.controller.RIFCSController;
import war.model.User;

/**
 * Class representing
 * @author Guillaume Prevost
 * @since 11th Jan. 2013
 */
@Entity
@Table(name = "UserStory")
public class UserStory {
	
	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The unique ID of the user story
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The name of the user story
	 */
	@Column
	private String name;
	
	/**
	 * The purpose of the user story
	 */
	@Column
	private String purpose;
	
	/**
	 * The mode of the user story. It can be 'active', 'passive' or 'published'.
	 * Active: there is only one active user story per user, it is considered as its 'Workboard'. The user can add and remove data elements form the user story.
	 * Passive: the user story is being edited by the user
	 * Published: nothing can be modified by the user as the story is published. 
	 */
	@Column
	private String mode;
	
	/**
	 * The level of privacy for this user story. It can be private or public.
	 * Private: only the owner can view the user story
	 * Public: everyone can view the user story
	 */
	@Column
	private String access;
	
	/**
	 * The user who created the user story
	 */
	@ManyToOne
	@JoinColumn(name="owner_login")
	private User owner;
	
	/**
	 * The seaport to which the user story is related
	 */
	@ManyToOne
	@JoinColumn(name="seaport_id")
	private Seaport seaport;
	
	/**
	 * The list of data elements contained in the user story
	 */
	@OneToMany(targetEntity=DataElement.class, mappedBy="userStory", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DataElement> dataElements;
	
	/**
	 * The date when the user story has been created
	 */
	@Column
	private Date creationDate;
	
	/**
	 * The date when the user story has been published
	 */
	@Column
	private Date publishDate;
	
	/**
	 * Default constructor of User Story
	 */
	public UserStory() {
	}
	
	/**
	 * Constructor of User Story specifying all its fields
	 * @param name: the name of the user story
	 * @param purpose: the purpose of the user story
	 * @param mode: the mode of the user story
	 * @param access: the level of privacy for this suer story
	 * @param owner: the user who created the user story
	 * @param seaport: the seaport to which the user stories is related
	 * @param dataElements: the list of data elements contained in the user story
	 */
	public UserStory(String name, String purpose, String mode, String access, User owner, Seaport seaport, List<DataElement> dataElements) {
		setName(name);
		setPurpose(purpose);
		setMode(mode);
		setAccess(access);
		setOwner(owner);
		setSeaport(seaport);
		setDataElements(dataElements);
	}

	/**
	 * Getter for the ID of the user story
	 * @return The unique ID of the user story
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Setter for the unique ID of the user story
	 * @return The new unique ID of the user story
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for the name of the user story
	 * @return The current name of the user story
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the purpose of the user story
	 * @return The new purpose of the user story
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	/**
	 * Getter for the purpose of the user story
	 * @return The current purpose of the user story
	 */
	public String getPurpose() {
		return this.purpose;
	}
	
	/**
	 * Setter for the name of the user story
	 * @return The new name of the user story
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the owner of the user story
	 * @return The current owner of the user story
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Setter for the owner of the user story
	 * @return The new owner of the user story
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	/**
	 * Getter for the mode of the user story
	 * @return The current mode of the user story
	 */
	public String getMode() {
		return this.mode;
	}
	
	/**
	 * Setter for the mode of the user story
	 * Active: there is only one active user story per user, it is considered as its 'Workboard'. The user can add and remove data elements form the user story.
	 * Passive: the user story is being edited by the user
	 * Published: nothing can be modified by the user as the story is published. 
	 * @param mode: The new mode of the user story
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	/**
	 * Getter for the privacy level of the user story
	 * @return The current privacy level of the user story
	 */
	public String getAccess() {
		return this.access;
	}
	
	/**
	 * Setter for the privacy level of the user story
	 * Private: only the owner can view the user story
	 * Public: everyone can view the user story
	 * @return The new privacy level of the user story
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	/**
	 * Getter for the data elements list of the user story
	 * @return The current data elements list of the user story
	 */
	public List<DataElement> getDataElements() {
		return this.dataElements;
	}
	
	/**
	 * Setter for the data elements list of the user story
	 * @return The new data elements list of the user story
	 */
	public void setDataElements(List<DataElement> dataElements) {
		this.dataElements = dataElements;
	}
	
	/**
	 * Getter for the seaport to which the user story is related
	 * @return The seaport to which the user story is related
	 */
	public Seaport getSeaport() {
		return this.seaport;
	}
	
	/**
	 * Setter for the seaport to which the user story is related
	 * @param seaport: the new seaport to which the user story is related
	 */
	public void setSeaport(Seaport seaport) {
		this.seaport = seaport;
	}

	/**
	 * Getter for the user story creation date
	 * @return The date when the user story was created
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	/**
	 * Setter for the user story creation date
	 * @param creationDate: the new date when the user story was created
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Getter for the user story publish date
	 * @return The date when the user story was published
	 */
	public Date getPublishDate() {
		return this.publishDate;
	}
	
	/**
	 * Setter for the user story publish date
	 * @param publishDate: the new date when the user story was published
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	/**
	 * Returns a short summary of the User Story, based on several of the User Story's properties
	 * @return a short summary of the User Story
	 */
	public String getShortDescription() {
		return "Report about climate change in the " + seaport.getRegion().getName() 
		+ " NRM region of Australia, focused on " + seaport.getName() + ".";
	}
	
	/**
	 * Returns a full summary of the User Story, based on several of the User Story's properties.
	 * @return a full summary of the User Story
	 */
	public String getFullDescription() {
		String str = "";
        
		// Generates a (string) list of the different types of data elements that have been added to the user story
		int i = 1;
        for (DataElement dataElement : dataElements) {        	
 			if (dataElement.getClass().equals(DataElementAbs.class) && !str.contains("ABS data")) {
 				str += "ABS data";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementBitre.class) && !str.contains("ports Australia data")) {
 				str += "ports Australia data";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementFile.class) && !str.contains("custom data")) {
 				str += owner.getFirstname() + " " + owner.getLastname() + "'s custom data";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementPast.class) && !str.contains("CSIRO & BoM trend data")) {
 				str += "CSIRO & BoM trend data";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementAcornSat.class) && !str.contains("measurements from ACORN-SAT stations")) {
 				str += "measurements from ACORN-SAT stations";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementCmar.class) && !str.contains("CMAR future data")) {
 				str += "CMAR future data";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementCsiro.class) && !str.contains("CSIRO future data")) {
 				str += "CSIRO future data";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementEngineeringModel.class) && !str.contains("concrete deterioration")) {
 				str += "concrete deterioration forecast model data";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementVulnerability.class) && !str.contains("vulnerability assessment")) {
 				str += seaport.getName() + " vulnerability assessment";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			else if (dataElement.getClass().equals(DataElementText.class) && !str.contains("personal analysis")) {
 				str += owner.getFirstname() + " " + owner.getLastname() + "'s personal analysis";
 				str += (i < dataElements.size() ? ", " : "");
 			}
 			i++;
        }
        
        return "This report was created in reference to " + seaport.getName() + " (" + seaport.getCode() 
        + "), located in the ABC NRM region " + seaport.getRegion().getName() 
		+ ". The report is composed of " + str + ". " + "It has been created by " + owner.getFirstname() 
		+ " " + owner.getLastname() + " using the Climate Smart Seaports tool available at " 
		+ RIFCSController.CSS_URL + ".";
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}