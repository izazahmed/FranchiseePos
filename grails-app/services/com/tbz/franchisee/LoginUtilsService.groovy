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
 * LoginUtilsService
 * Service for Login Details related information
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
 * 01/02/2016		Sachin				Created File
 * 15/04/2016	  	Sachin				Added Logger and Exception handling
 */
import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class LoginUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: getUserId
	 * Description: Get User Code from the Database
	 * @params : username
	 * @return : ResultSet of UserCode
	 */
	def getUserId(userName){
		def x = userName
		def query =
		"""
			SELECT 
				MU.CH_USER_CODE,MU.VC_USER_NAME,BM.BR_NAME
			FROM 
				MK_USERS MU,BR_MST_TAB BM
			WHERE
			MU.BR_CODE=BM.BR_CODE AND
				VC_USER_NAME='$x.username' AND VC_PASSWORD='$x.password'
			"""
		
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: getUserId
	 * Description: Get User Code from the Database according to Username and Active State
	 * @params : username and User active
	 * @return : ResultSet of UserCode
	 */
	def getUserInfo(userName){
		
		def query =
			"""
				SELECT 
					CH_USER_CODE
				FROM 
					NEWWEBTBZ.MK_USERS
				WHERE
					VC_USER_NAME='$userName'
				AND 
					CH_USER_ACTIVE = 'Y'
			"""
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: getRoleId
	 * Description: Get User Role Id from the Database according to User Code
	 * @params : UserId
	 * @return : ResultSet of UserCode
	 */
	def getRoleId(userIdInstance){
		def query =
			"""
				SELECT 
					MK_ROLE_ID
				FROM 
					NEWWEBTBZ.MK_ROLES_COMP
				WHERE
					CH_USER_CODE='$userIdInstance'
			"""
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: getRoleId
	 * Description: Get Comapny Name from the Database according to VC_COMP_Code,Br_code,User Code
	 * @params : UserId
	 * @return : ResultSet of UserCode
	 */
	def getCompanyName(userIdInstance){
		
		def query =
			"""
				SELECT 
					MC.VC_COMP_CODE,MC.VC_COMPANY_NAME
				FROM 
					MST_COMPANY MC, BR_MST_TAB BMT, MK_USERS MU
				where
					MC.vc_comp_code = BMT.vc_comp_code
			    and
			        BMT.BR_CODE = MU.BR_CODE 
			    and
			        MU.CH_USER_CODE = '$userIdInstance'
			"""
		
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: getBranchName
	 * Description: Get Branch Name from the Database according to User Id
	 * @params : BR_CODE,USER_CODE
	 * @return : ResultSet of BR_code and BR_NAME
	 */
	def getBranchName(userIdInstance){
		
		def x = userIdInstance
		
		log.info("userIdInstance"+userIdInstance)
		def query =
			"""
				SELECT 
					BMT.BR_CODE,BMT.BR_NAME
				FROM 
					BR_MST_TAB BMT, MK_USERS MU
				where
					BMT.BR_CODE = MU.BR_CODE 
			    and
			        MU.CH_USER_CODE = '$userIdInstance'			
			"""
		
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}	
	
	/**
	 * Method Name: getYear
	 * Description: Get Year from the Database according to User Id and Active State
	 * @params : User Id and Active State
	 * @return : ResultSet of financial Start Date and end Date 
	 */
	def getYear(userIdInstance){
		
		def query =
			"""
				SELECT 
					TO_CHAR(DT_FIN_START_DATE, 'YYYY')||'-'||TO_CHAR(DT_FIN_END_DATE, 'YYYY') as DT_FIN_START_DATE
				FROM 
					USER_FIN_ACCOUNT_YEAR
				WHERE
					CH_USER_CODE = '$userIdInstance'
				AND 
					CH_ACTIVE = 'Y'
			"""
		
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
}
