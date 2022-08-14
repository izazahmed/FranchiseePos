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
 * MstCodeUtilsService
 * Service for Code Master Details related information
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
class MstCodeUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: getMaxCode
	 * Description: Get Master Code Details record from the Database
	 *
	 * @return : ResultSet of Saved Inward Details
	 */
	def getMaxCode(mstCodeInstance){
		def getMaxCodeStr
		def x = mstCodeInstance
		
		def selectSize =x?.selectSize
		
		if(selectSize=='bank'){	
			getMaxCodeStr = "select max(vc_code) from  mst_code where substr(VC_CODE,1,1) = 'Q' and br_code='$x.brcode'"
		}else if(selectSize=='city'){
			getMaxCodeStr = "select max(vc_code) from  mst_code where substr(VC_CODE,1,1) = 'C' and br_code='$x.brcode'"
		}else if(selectSize=='state'){
			getMaxCodeStr = "select max(vc_code) from  mst_code where substr(VC_CODE,1,1) = 'S' and br_code='$x.brcode'"
		}else if(selectSize=='country'){
			getMaxCodeStr = "select max(vc_code) from  mst_code where substr(VC_CODE,1,1) = 'T' and br_code='$x.brcode'"
		}else if(selectSize=='region'){
			getMaxCodeStr = "select max(vc_code) from  mst_code where substr(VC_CODE,1,1) = 'G' and br_code='$x.brcode'"
		}else if(selectSize=='creditCard'){
			getMaxCodeStr = "select max(vc_code) from  mst_code where substr(VC_CODE,1,1) = 'Y' and br_code='$x.brcode'"
		}	
		
		Sql sql = new Sql(dataSource)
		def val = sql.rows(getMaxCodeStr)
		val.each {
			def vcCode = (it.values()[0]).toString()
			def vcCodeSubStr = vcCode.substring(1) //003
			def vcCde = vcCode.take(1)   //Q
			def vcd = Integer.parseInt(vcCodeSubStr)
			def vcde = (vcd+1).toString()
			def vc_code = vcCde+vcde
			def str = "INSERT INTO MST_CODE(VC_CODE,VC_CODE_DESC,BR_CODE)VALUES ('${vc_code}', '${x.newDesc}','$x.brcode')"
			sql.execute str
		}
	}
	
	/**
	 * Method Name: saveMstCode
	 * Description: Save Master Code Details record into Database
	 *
	 * @return : ResultSet of Saved Master Details
	 */
	def saveMstCode(mstCodeInstance) {		
		def str = "INSERT INTO MST_CODE(VC_CODE,VC_CODE_DESC, BR_CODE)VALUES (vc_code, '$mstCodeInstance.vcCodeDesc','$mstCodeInstance.brCode')"
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: bankNames
	 * Description: Get Master Code Details record from the Database
	 *
	 * @return : ResultSet of Bank Names Master Details
	 */
	def bankNames(brCode){
		def resultArr
		def str = "select * from MST_CODE where substr(VC_CODE,1,1) = 'Q' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		return result
	}
	
	/**
	 * Method Name: cityNames
	 * Description: Get City Details record from the Database
	 *
	 * @return : ResultSet of City Details record
	 */
	def cityNames(brCode){
		def resultArr
		def str = "select * from MST_CODE where substr(VC_CODE,1,1) = 'C' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		return result
	}
	
	/**
	 * Method Name: regionNames
	 * Description: Get Region names Details record from the Database
	 *
	 * @return : ResultSet of regions Details record from database
	 */
	def regionNames(brCode){
		def resultArr
		def str = "select * from MST_CODE where substr(VC_CODE,1,1) = 'G' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		return result
	}
	
	/**
	 * Method Name: stateNames
	 * Description: Get State names Details record from the Database
	 *
	 * @return : ResultSet of state Details record from database
	 */
	def stateNames(brCode){
		def resultArr
		def str = "select * from MST_CODE where substr(VC_CODE,1,1) = 'S' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		return result
	}
	
	/**
	 * Method Name: countryNames
	 * Description: Get Country names Details record from the Database
	 *
	 * @return : ResultSet of country Details record from database
	 */
	def countryNames(brCode){
		def resultArr
		def str = "select * from MST_CODE where substr(VC_CODE,1,1) = 'T' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		return result
	}
	
	/**
	 * Method Name: creditCardNames
	 * Description: Get Credit Card names Details record from the Database
	 *
	 * @return : ResultSet of credit Card Details record from database
	 */
	def creditCardNames(brCode){
		def resultArr
		def str = "select * from MST_CODE where substr(VC_CODE,1,1) = 'Y' and br_code='$brCode'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		return result
	}
	
	/**
	 * Method Name: editNames
	 * Description: Edit data from the Database follow up by vcCode and branch code
	 *
	 * @return : ResultSet of edited Master Details
	 */
	def editNames(name,brCode,vcCode) {		
		def str = "UPDATE MST_CODE set VC_CODE_DESC='$name' where VC_CODE='$vcCode' and BR_CODE = '$brCode'"
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: deleteParameter
	 * Description: Delete data from the Database follow up by vcCode and branch code
	 *
	 * @return : ResultSet of edited Master Details
	 */
	def deleteParameter(name,brCode,vcCode) {	
		def str = "DELETE FROM MST_CODE WHERE VC_CODE='$vcCode' and BR_CODE = '$brCode'"
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
}
