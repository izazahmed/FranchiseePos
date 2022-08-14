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
 * CompanyUtilsService
 * Service for Dashboard related information
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
class CompanyUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: getModuleMenu
	 * Description: This method will required to show module menu
	 *
	 * @return : ResultSet including vc_module_code and vc_module_object
	 */
	def getModuleMenu(roleId){
		
		def x = roleId
		def query =
			"""
				SELECT DISTINCT
					mrm.vc_module_code,mm.vc_module_object 
				FROM 
					MK_ROLES_MENU mrm , MK_MODULE mm 
				WHERE
					mrm.vc_module_code = mm.vc_module_code 
				AND
					mrm.ch_role_code='$x'			
			"""
		log.info("SELECT query -->"+query)		
		Sql sql = new Sql(dataSource)		
		def detaResult = sql.rows(query)		
		return detaResult
	}
	
	/**
	 * Method Name: getModuleInfo
	 * Description: This method is required to show module information like Link Action on menu and title of menu
	 *
	 * @return : ResultSet including vc_menu_code,VC_SOURCE_OBJECT and VC_MENU_OBJECT
	 */
	def getModuleInfo(roleId,moduleValue){
		def query =
			"""
				SELECT 
					mmm.vc_menu_code, mmm.VC_MENU_OBJECT, mmm.VC_SOURCE_OBJECT 
				FROM
					MK_MODULE_MENU mmm, MK_ROLES_MENU mrm 
				WHERE
					mmm.vc_module_code = mrm.vc_module_code 
				AND
					mmm.vc_menu_code = mrm.vc_menu_code 
				AND
					CH_ACTIVE = 'Y' 
				AND 
					mrm.ch_role_code='$roleId' 
				AND
					mrm.vc_module_code='$moduleValue'			
			"""
		
		log.info("SELECT query -->"+query)		
		Sql sql = new Sql(dataSource)		
		def detaResult = sql.rows(query)
		return detaResult
	}
}
