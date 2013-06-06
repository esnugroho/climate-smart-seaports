/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package war.dao;

import helpers.EngineeringModelHelper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import war.model.* ;

/**
 * Data Access Object for the CSIRO variable
 * @author Guillaume Prevost
 */
@Repository
public class CsiroVariableDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The name of the table in the database where the Climate Variables are stored
	 */
	public final static String TABLE_NAME = "CsiroVariable";
	
	/**
	 * Retrieve the climate variable in the database associated to a unique ID
	 * @param id: the unique ID of the required climate variable
	 * @return the climate variable associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public Variable find(Integer id) {
		Variable climateVariable = entityManager.find(Variable.class, id);
		if (climateVariable == null)
			throw new NoResultException(ERR_NO_RESULT);
		return climateVariable;
	}
	
	/**
	 * Retrieve the (unique) Climate variable matching a name
	 * @param variableName: the name of the variable to match
	 * @return the Climate variable from the database
	 * @throws NoResultException if no variable matches the given name 
	 */
	@Transactional
	public Variable find(String variableName) throws NoResultException {
		try {
			Query query = entityManager.createQuery("SELECT v FROM " + TABLE_NAME + " v WHERE v.name = :variableName");
			query.setParameter("variableName", variableName);
			return (Variable)(query.getSingleResult());
		}
		catch (Exception e)
		{
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve a list of all the different climate variables in the Database
	 * @return the list of all the different climate variables in the Database
	 */
	@Transactional
	public List<Variable> getAll() {
		Query query = entityManager.createQuery("SELECT v FROM " + TABLE_NAME + " v");
		
		List<Variable> results = new ArrayList<Variable>();
		for (Object obj : query.getResultList()) {
			if (obj instanceof Variable)
			results.add((Variable)(obj));
		}
		
		return results;
	}
	
	/**
	 * Returns a list of all the different engineering model variables (static listing, not actually querying the database)
	 * @return the list of all the different engineering model variables in the Database
	 */
	public List<Variable> getEngineeringModelVariables() {
		List<Variable> results = new ArrayList<Variable>();
		
		List<Variable> allVars = getAll();
		for (Variable var : allVars) {
			if (EngineeringModelHelper.ENGINEERING_MODEL_VARIABLES.containsKey("Chloride|" + var.getName())
				|| EngineeringModelHelper.ENGINEERING_MODEL_VARIABLES.containsKey("Carbonation|" + var.getName()))
				results.add(var);
		}
		
		return results;
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No climate variable found corresponding to the specified name";
}
