package com.tbz.franchisee
/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */
/**
 * MstRegionUtilsService
 * Service for Region Master Details related information
 *
 * @author (CTE).
 *
 * Contact ("") (optional)
 *
 * @version    0.1
 * @date        01/02/2016
 *
 * MOD HISTORY
 * DATE           	USER				COMMENTS
 * 01/02/2016		Abhijit				Created File
 * 15/04/2016	  	Sachin				Added Logger and Exception handling
 */
import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class MstRegionUtilsService {
	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: saveMstRegion
	 * Description: Save Region Master Details record into the Database
	 *
	 * @return : ResultSet of saved mst code details record from database
	 */
	def saveMstRegion(mstRegionInstance) {
		def x = mstRegionInstance
		
		Sql sql
		def str = "INSERT INTO NEWWEBTBZ.MST_REGION(VC_CITY, VC_STATE, VC_COUNTRY,BR_CODE) VALUES ('$x.city','$x.state', '$x.country','$x.brCode')"
		sql = new Sql(dataSource)
		sql.execute str
		
		def delStr = "DELETE FROM NEWWEBTBZ.MST_CODE WHERE VC_CODE_DESC IN ('$x.city' ,'$x.state', '$x.country') and BR_CODE='$x.brCode'"
		sql = new Sql(dataSource)
		sql.execute delStr 
	}
	
	def deleteMstRegion(mstRegionInstance){
		def x = mstRegionInstance		
		Sql sql
	
		def delStr = "DELETE FROM NEWWEBTBZ.MST_REGION WHERE VC_CITY='$x.city' and VC_STATE='$x.state' and VC_COUNTRY='$x.country' and BR_CODE='$x.brCode'"
		sql = new Sql(dataSource)
		sql.execute delStr
	}
	
	/**
	 * Method Name: getCity
	 * Description: Get city names Details record from the Database
	 *
	 * @return : ResultSet of city Card Details record from database
	 */
	def getCity(brCode){
		
		def retList = []
		def str = "select vc_code_desc from NEWWEBTBZ.MST_CODE where substr(VC_CODE,1,1) = 'C' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		result.each {
			retList.add(it.values()[0])
		}
		return retList.asList();
	}
	
	/**
	 * Method Name: getState
	 * Description: Get Credit Card names Details record from the Database
	 *
	 * @return : ResultSet of state Details record from database
	 */
	def getState(brCode){
		def retList = []
		def str = "select vc_code_desc from NEWWEBTBZ.MST_CODE where substr(VC_CODE,1,1) = 'S' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		result.each {
			retList.add(it.values()[0])
		}
		
		return retList.asList();
	}
	
	/**
	 * Method Name: getCountry
	 * Description: Get Country names Details record from the Database
	 *
	 * @return : ResultSet of country Details record from database
	 */
	def getCountry(brCode){
		
		def retList = []
		def str = "select vc_code_desc from NEWWEBTBZ.MST_CODE where substr(VC_CODE,1,1) = 'T' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		result.each {
			retList.add(it.values()[0])
		}
		
		return retList.asList();
	}
	
	def getMstRegionList(brCode){
	//	def regList = []
		def str = "select vc_city CITY, vc_state STATE, vc_country COUNTRY from NEWWEBTBZ.MST_REGION where br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		//def result = sql.rows(str)
		def result = sql.rows(str.toString())
		/*result.each {
			regList.add(it.values()[])
		}*/
		
		return result;
	}
	
	def getCtyStCtrData(mstRegionInstance){
		def x = mstRegionInstance
		
	//	def strQry = 
	}
	
	def saveData(mstRegionInstance) {
		def x = mstRegionInstance
	}
}
