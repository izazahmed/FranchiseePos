package com.tbz.franchisee

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.sql.Sql
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional
class MtlItemLocationsUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	
	/**
	 * Method Name: getAllLocationsList
	 * Description: Get Locations Details record data from the Table MTL_ITEM_LOCATIONS
	 *
	 * @return : ResultSet of locations Details
	 */
	def getAllLocationsList(){
		def query =
		"""
			SELECT 
				INVENTORY_LOCATION_ID,SUBINVENTORY_CODE,SEGMENT1,DESCRIPTION
			FROM 
				NEWWEBTBZ.MTL_ITEM_LOCATIONS			
			"""
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	
	/**
	 * Method Name: getExactLocationData
	 * Description: Get exact location details record data from the Table MTL_ITEM_LOCATIONS
	 * @params : params
	 * @return : ResultSet of Scheme Details
	 */
	def getExactLocationData(mtlItemLocationsInstance) {
		
		def query =
			"""
				SELECT 
					INVENTORY_LOCATION_ID,SUBINVENTORY_CODE,SEGMENT1,DESCRIPTION
				FROM 
					NEWWEBTBZ.MTL_ITEM_LOCATIONS
				WHERE
					INVENTORY_LOCATION_ID='$mtlItemLocationsInstance.dd'				
			"""
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}	
	
	
	/**
	 * Method Name: saveLocation
	 * Description: Save location details record data from the Table MTL_ITEM_LOCATIONS
	 * @params : params
	 * @return : ResultSet of Scheme Details
	 */
	def saveLocation(mtlItemLocationsInstance) {
	
		def inId
		def maxInvId = "SELECT MAX(INVENTORY_LOCATION_ID) FROM NEWWEBTBZ.MTL_ITEM_LOCATIONS"
		Sql sql = new Sql(dataSource)
		def inventoryId = sql.rows(maxInvId)
		inventoryId.each {
			inId = it.values()[0]
		}
		def inLocId = inId +1 
		
		def str =
			"""
				INSERT INTO 
					NEWWEBTBZ.MTL_ITEM_LOCATIONS
				(
					INVENTORY_LOCATION_ID,SUBINVENTORY_CODE,SEGMENT1,DESCRIPTION
				) 
				VALUES 
				(
					'$inLocId','$mtlItemLocationsInstance.subinventoryCode', '$mtlItemLocationsInstance.segment1', '$mtlItemLocationsInstance.description'
				)
			"""
		Sql sql1 = new Sql(dataSource)
		sql1.execute str
	}
	
	
	/**
	 * Method Name: updateLocationData
	 * Description: Update Location Details record data into the Table MTL_ITEM_LOCATIONS
	 * @params : params
	 * @return : ResultSet of location Details record
	 */
	def updateLocationData(mtlItemLocationsInstance) {
		
		def str = "UPDATE NEWWEBTBZ.MTL_ITEM_LOCATIONS set SUBINVENTORY_CODE='$mtlItemLocationsInstance.subinventoryCode',SEGMENT1='$mtlItemLocationsInstance.segment1',DESCRIPTION='$mtlItemLocationsInstance.description' WHERE INVENTORY_LOCATION_ID='$mtlItemLocationsInstance.dd'"
		Sql sql = new Sql(dataSource)
		sql.execute str		
	}	
}