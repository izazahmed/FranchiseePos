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
 * LoginController
 * Controller for Login
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

import grails.converters.JSON

import java.text.DateFormat
import java.text.SimpleDateFormat

import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.grails.web.json.JSONObject

class LoginController {
	def loginUtilsService
	
	/**
	 * Method Name: index
	 * Description: using this method landing page should redirect to auth page.
	 * 
	 * @return : Redirect the auth page
	 */
    def index() {
		redirect action: 'auth', params: params
	}
	
	/**
	 * Method Name: auth
	 * Description: Validate username, password with company and branch details
	 * @params : username and password  
	 */
	
	def auth(){
		try {
			if (StringUtils.isNotBlank(params?.username?.toString()) && StringUtils.isNotBlank(params?.password?.toString())) {
				def validUser = MkUsers.findByVcUserNameAndVcPassword(params?.username,params?.password)
				if (validUser) {
					def userInstance = loginUtilsService.getUserId(params)		
					def userId = userInstance?.CH_USER_CODE		
					if (userId[0]) {
						MkUsers user = MkUsers.findByChUserCode(userId[0])			
						session.sessionUser = user
						MkRolesComp roleId = MkRolesComp.findByChUserCode(user?.chUserCode)
						session.userId = userId[0]			
						session.roleId = roleId?.chRoleCode
						session.brCode = params?.branchCode
						session.companyCode = params?.companyCode
						session.loginYear = params?.year
						session.machineNo = params?.machineNo
						session.counterId = params?.counterId
						
						redirect  (controller: "company" , action:"dashboard")
					} else {
						flash.message = message(code: 'default.not.found.message', args: [message(code: 'login.label', default: 'username'), params.j_username])
					}
				} else {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'login.label', default: 'username'), params.j_username])
				}
			}
		} catch(IOException ioexception) {
			log.info("Exception in LoginController auth method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in LoginController auth method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getDetailUserId
	 * Description: Validate username, password with company and branch details
	 * @params : username and password
	 * @return : JSON object of comapny,Branch and year
	 */
	def getDetailUserId() {
		try {
			
			def validUser = MkUsers.findByVcUserName(params?.usernameVal)
			if(!validUser)
			{
				JSONObject jsonObj = new JSONObject()
				jsonObj.putAt("NOT_VALID_USER", null)
				render jsonObj as JSON
				return
			}
		} catch(IOException ioexception) {
			log.info("Exception in LoginController getDetailUserId method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in LoginController getDetailUserId method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
	}
	
	/**
	 * Method Name: getDetailPassowrd
	 * Description: Validate username, password with company and branch details
	 * @params : username and password
	 * @return : JSON object of comapny,Branch and year
	 */
	def getDetailPassword() {
		try {
			
			def validUserandPassword = MkUsers.findByVcUserNameAndVcPassword(params?.usernameVal,params?.passwordVal)
			if(validUserandPassword)
			{
				def userInstance = loginUtilsService.getUserInfo(params?.usernameVal)
				def userId = userInstance?.CH_USER_CODE
				def tempUserId = userId[0]
				
				def companyInstance = loginUtilsService.getCompanyName(tempUserId)
				def branchInstance = loginUtilsService.getBranchName(tempUserId)
				def yearInstance = loginUtilsService.getYear(tempUserId)
				
				JSONObject jsonObj = new JSONObject()
				
				jsonObj.putAt("VC_COMP_CODE", companyInstance?.VC_COMP_CODE)
				jsonObj.putAt("VC_COMPANY_NAME", companyInstance?.VC_COMPANY_NAME)
				jsonObj.putAt("BR_NAME", branchInstance?.BR_NAME)
				jsonObj.putAt("BR_CODE", branchInstance?.BR_CODE)
				jsonObj.putAt("DT_FIN_START_DATE", yearInstance?.DT_FIN_START_DATE)
				render jsonObj as JSON
				return
			}
			else
			{
				JSONObject jsonObj = new JSONObject()
				jsonObj.putAt("NOT_VALID_PASSWORD", null)
				jsonObj.putAt("VC_COMPANY_NAME", null)
				jsonObj.putAt("DT_FIN_START_DATE", null)
				render jsonObj as JSON
				return
			}
		} catch(IOException ioexception) {
			log.info("Exception in LoginController getDetailUserId method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in LoginController getDetailUserId method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
}
