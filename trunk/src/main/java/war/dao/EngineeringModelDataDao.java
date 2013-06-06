/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package war.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import war.model.* ;

/**
 * Data Access Object for the engineering model data (concrete deterioration model)
 * @author Guillaume Prevost
 */
@Repository
public class EngineeringModelDataDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ClimateParamsDao climateParamsDao;
	
	/**
	 * The name of the table in the database where the Engineering Model Data is stored
	 */
	public final static String TABLE_NAME = "EngineeringModelData";
	
	/**
	 * The name of the table in the database where the Engineering Model Data is stored
	 */
	public final static int EXAMPLE_ASSET_COUNT = 3;//(3 * 16 * 7); // 3 Regions, 16 variables, 7 climate parameters 
	
	/**
	 * Retrieve an engineering model data in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required engineering model data
	 * @return the engineering model data object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public EngineeringModelData find(Integer id) throws NoResultException {
		EngineeringModelData data = entityManager.find(EngineeringModelData.class, id);
		if (data == null)
			throw new NoResultException(ERR_NO_RESULT);
		return data;
	}
	
	/**
	 * Retrieve an engineering model data matching the required variable and region
	 * @param region: the unique ID of the required engineering model data
	 * @param variable: the unique ID of the required engineering model data
	 * @return the list of engineering model data matching the given region and variable
	 * @throws NoResultException if the search didn't return any result
	 */
	@Transactional
	public List<EngineeringModelData> find(Region region, EngineeringModelVariable variable) throws NoResultException {
		List<EngineeringModelData> results = new ArrayList<EngineeringModelData>();
		List<ClimateParams> parametersList = climateParamsDao.getAllInRegion(region.getName()); 
		
		for (ClimateParams parameters : parametersList) {
			try {
				Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.parameters = :parameters AND d.variable = :variable");
				query.setParameter("parameters", parameters);
				query.setParameter("variable",variable);
				results.addAll(performQueryAndCheckResultList(query));
			}
			catch (NoResultException e) {
				continue;
			}
		}
		if (results.isEmpty())
			throw new NoResultException(ERR_NO_RESULT);
		return results;
	}
	
	/**
	 * Retrieve an engineering model data matching the required variable and region
	 * @param asset: the asset for which the Engineering Model data should be retrieved
	 * @return the list of engineering model data matching the given asset
	 * @throws NoResultException if the search didn't return any result
	 */
	@Transactional
	public List<EngineeringModelData> find(EngineeringModelAsset asset) throws NoResultException {		
		Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.asset = :asset");
		query.setParameter("asset", asset);
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of EngineeringModelData returned by the query and checked
	 */
	private List<EngineeringModelData> performQueryAndCheckResultList(Query query) {
		try {
			List<EngineeringModelData> engineeringModelData = new ArrayList<EngineeringModelData>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof EngineeringModelData)
					engineeringModelData.add((EngineeringModelData)(obj));
			}
			return engineeringModelData;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	
	/**
	 * Saves a given engineering model data into the database, by adding it or updating it
	 * @param asset: the engineering model data to save in the database
	 * @return the saved engineering model data
	 */
	@Transactional
	public EngineeringModelData save(EngineeringModelData data) {
		if (data.getId() == 0) {
			entityManager.persist(data);
			return data;
		}
		else {
			entityManager.merge(data);
			return data;
		}		
	}
	
	/**
	 * Delete from the database the engineering model data associated to the unique ID
	 * @param id: the unique ID of the engineering model data to delete
	 */
	@Transactional
	public void delete(int id) {
		Query query = entityManager.createQuery("DELETE FROM " + EngineeringModelDataDao.TABLE_NAME + " d WHERE d.id = :Id") ;
		query.setParameter("id", id).executeUpdate();
	}
	
	/**
	 * Delete from the database the engineering model data associated to an asset
	 * @param id: the unique ID of the asset to engineering model data to delete
	 */
	@Transactional
	public void deleteForAsset(EngineeringModelAsset asset) {
		Query query = entityManager.createQuery("DELETE FROM " + EngineeringModelDataDao.TABLE_NAME + " d WHERE d.asset = :asset") ;
		query.setParameter("asset", asset).executeUpdate();
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No Engineering Model data found corresponding to the specified parameters";
}
