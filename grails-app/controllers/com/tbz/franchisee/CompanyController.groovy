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
 * CompanyController
 * Controller for showing dashboard related thigs
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
class CompanyController {
	
	def companyUtilsService
	
	/**
	 * Method Name: dashboard
	 * Description: Showing Dashboard page after Login
	 * 
	 * @return : List(moduleMenu)
	 */
	def dashboard() {
		try {
			log.info("session.roleId"+session.roleId)
			//Calling getModuleMenu method of companyUtilsService to get module menu list by passing RoleId
			def moduleMenu = companyUtilsService.getModuleMenu(session.roleId)
			[moduleMenu : moduleMenu ]
		} catch(IOException ioexception) {
			log.info("Exception in CompanyController dashboard method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CompanyController dashboard method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: dashboard
	 * Description: Showing mainModule page after Login
	 *
	 * @return : List(menuList and menuDetailsMap)
	 */
    def mainModule() {
		try {
			log.info("params?.moduleCode"+params?.moduleCode)
			//setting ModuleValue in to the session
			session.moduleValue = params?.moduleCode
			//Calling getModuleInfo method of companyUtilsService to get module menu list by passing RoleId and moduleValue
			def moduleInstance = companyUtilsService.getModuleInfo(session.roleId,session.moduleValue)
			//setting menuCodeList in to the session
			session.menuCodeList = moduleInstance?.vc_menu_code
			Map<String, List> menuDetailsMap = new HashMap<String, List>()
			for (var in moduleInstance) {
				def menuList = new ArrayList<>()
				menuList.add(var[1])
				menuList.add(var[2])
				menuDetailsMap.put(var[0], menuList)			
			}
			//setting menuDetailsMap in to the session
			session.menuDetailsMap = menuDetailsMap
		} catch(IOException ioexception) {
			log.info("Exception in CompanyController mainModule method "+ioexception.getMessage())
		} catch(Exception exception) {
			log.info("Exception in CompanyController mainModule method "+exception.getMessage())
		}		
    }
    
	/**
	 * Method Name: logout
	 * Description: using this method Logout the application
	 *
	 * @return : List(menuList and menuDetailsMap)
	 */
	def logout(){		
		Enumeration attrs =  session.getAttributeNames();
		while(attrs.hasMoreElements()) {
			session.removeAttribute(attrs.nextElement())	   
		}
		session.invalidate()
		redirect  (controller: "login" , action:"auth")
	}
}