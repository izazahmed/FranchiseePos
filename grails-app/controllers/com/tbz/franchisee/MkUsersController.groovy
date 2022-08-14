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
  * MkUsersController
  * Controller for user management related things
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
  * 15/04/2016	  	Abhijit				Added method for User Management form
  */

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.lang.String
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * MkUsersController
 * Description
 *
 * @author (CTE).
 *
 * Contact () (optional)
 *
 * @version    0.1
 * @date        01/03/2016
 *
 * MOD HISTORY
 * DATE           USER      COMMENTS
 *  15/04/2016	  Izaz      Created File
 *
 */

@Transactional(readOnly = true)
class MkUsersController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def mkUsersUtilsService;
	def brMstTabUtilsService;
	def mkRolesUtilsService;
	def mstCompanyUtilsService;
	def userFinAccountYearUtilsService	
	
	/**
	 * Method Name: index
	 * Description: displays the landing page with companies,financial date and branch data.
	 *
	 * @return : multiple list
	 */
	def index() {
		String frmtCurStDate
		String frmtCurEndDate
		String frmtPrevStDate
		String finFrmtPrevEndDate
		List<String> curSDtList = new ArrayList<String>()
		List<String> prevSDtList = new ArrayList<String>()
		List<String> curEDtList = new ArrayList<String>()
		List<String> prevEDtList = new ArrayList<String>()
		List<String> sizeList = new ArrayList<String>();
		def fnCDtTranspose,fnPDtTranspose,brMstTabList,mstCompanyList,mkUsersLstEdit
		try{
			//Calling getFinanceYearDate method of MkUsersUtilsService to get the date
			def userFinYearDate = mkUsersUtilsService.getFinanceYearDate()
			
			def finCurStartDate = userFinYearDate?.DT_CURRENT_START_DATE
			def finCurEndDate = userFinYearDate?.DT_CURRENT_END_DATE
			def finPrevStartDate = userFinYearDate?.DT_PREVIOUS_START_DATE
			def finPrevEndDate = userFinYearDate?.DT_PREVIOUS_END_DATE
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
			Calendar cal = Calendar.getInstance()
			for(def i=0;i<finCurStartDate.size();i++) {
				Date curStartDate = (Date)formatter.parse(finCurStartDate[i].toString())
				cal.setTime(curStartDate)
				frmtCurStDate = cal.get(Calendar.DATE)  + "-" + (cal.get(Calendar.MONTH) +1)+ "-" +         cal.get(Calendar.YEAR)
				curSDtList.add(frmtCurStDate)
			}
			for(def j=0;j<finCurEndDate.size();j++) {
				Date curEndDate = (Date)formatter.parse(finCurEndDate[j].toString())
				cal.setTime(curEndDate)
				frmtCurEndDate = cal.get(Calendar.DATE)  + "-" + (cal.get(Calendar.MONTH) +1)+ "-" +         cal.get(Calendar.YEAR)
				curEDtList.add(frmtCurEndDate)
			}
			for(def k=0;k<finPrevStartDate.size();k++) {
				Date prevStartDate = (Date)formatter.parse(finPrevStartDate[k].toString())
				cal.setTime(prevStartDate)
				frmtPrevStDate = cal.get(Calendar.DATE)  + "-" + (cal.get(Calendar.MONTH) +1)+ "-" +         cal.get(Calendar.YEAR)
				prevSDtList.add(frmtPrevStDate)
			}
			for(def l=0;l<finPrevEndDate.size();l++) {
				Date endDate = (Date)formatter.parse(finPrevEndDate[l].toString())
				cal.setTime(endDate)
				finFrmtPrevEndDate = cal.get(Calendar.DATE)  + "-" + (cal.get(Calendar.MONTH) +1)+ "-" +         cal.get(Calendar.YEAR)
				prevEDtList.add(finFrmtPrevEndDate)
			}
			fnCDtTranspose =  [curSDtList,curEDtList].transpose()
			fnPDtTranspose =  [prevSDtList,prevEDtList].transpose()
			//Calling getBrMstTabList method of MkUsersUtilsService to get the branch list
			brMstTabList = 	mkUsersUtilsService.getBrMstTabList()
			//Calling getMstCompanyList method of MkUsersUtilsService to get the companies list
			mstCompanyList = mkUsersUtilsService.getMstCompanyList()
			//Calling getMkUsersList method of MkUsersUtilsService to get the users list
			mkUsersLstEdit = mkUsersUtilsService.getMkUsersList()
			sizeList.add("1")
			sizeList.add("2")
			sizeList.add("3")
			sizeList.add("4")
			sizeList.add("5")
		}catch(Exception exception){
			log.info("Exception in MkUsersController index method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[fnCDtTranspose:fnCDtTranspose,fnPDtTranspose:fnPDtTranspose,brMstTabList:brMstTabList,mstCompanyList:mstCompanyList,mkUsersLstEdit:mkUsersLstEdit,sizeList:sizeList]
	}
	
	/**
	 * Method Name: saveUser
	 * Description: Save and Update user's details into multiple related table.
	 *
	 * @return : redirect to create.gsp page
	 */			
    def saveUser(MkUsers mkUsersInstance) {	
		def flag,mkUsersNextVal
		try{
			flag = params?.hidFlag		
			if(flag=='0') {
				//Calling saveMkUsers method of MkUsersUtilsService to save user data
				mkUsersNextVal = mkUsersUtilsService.saveMkUsers(params,session.companyCode,session.brCode)
				println"mkUsersNextVal :"+mkUsersNextVal
			}else{
				//Calling updateMkUsers method of MkUsersUtilsService to update user data
				mkUsersNextVal = mkUsersUtilsService.updateMkUsers(params,session.companyCode,session.brCode)			
			}		
			redirect (action : "index", mkUsersInstance:mkUsersInstance )
		}catch(Exception exception){
			log.info("Exception in MkUsersController saveUser method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: getUserDataEdit
	 * Description: Retrieve Data from multiple tables and send data to GSP using by json for Edit
	 *
	 * @return : List of user data
	 */	
	def getUserDataEdit() throws IOException {	
		String stDate,edDate
		def dtValS,userCD,flag,userDataRet,getDate,startDate,endDate
		List<String> stDtList = new ArrayList<String>()
		List<String> edDtList = new ArrayList<String>()
		try{	
			flag = params?.flag
			userCD = params?.userCode
			//Calling getUserDataForEdit method of MkUsersUtilsService to get the perticular user data
			userDataRet = mkUsersUtilsService.getUserDataForEdit(params)
			//Calling getDateForEdit method of MkUsersUtilsService to get the date
			getDate =  mkUsersUtilsService.getDateForEdit(params)
			
			startDate = getDate?.DT_FIN_START_DATE
			endDate = getDate?.DT_FIN_END_DATE
			
			if(startDate==[null] && endDate==[null]){
				startDate='';
				endDate='';			
			}		
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
			Calendar cal = Calendar.getInstance()
			for(def i=0;i<startDate.size();i++){
				Date startDte = (Date)formatter.parse(startDate[i].toString())
				cal.setTime(startDte)
				stDate = cal.get(Calendar.DATE)  + "-" + (cal.get(Calendar.MONTH) +1)+ "-" +         cal.get(Calendar.YEAR)
				if(dtValS==null){
					dtValS="";
				}
				dtValS =dtValS+stDate+","
				stDtList.add(stDate)
			}			
			for(def i=0;i<endDate.size();i++){
				Date endDte = (Date)formatter.parse(endDate[i].toString())
				cal.setTime(endDte)
				edDate = cal.get(Calendar.DATE)  + "-" + (cal.get(Calendar.MONTH) +1)+ "-" +         cal.get(Calendar.YEAR)			
				dtValS =dtValS+edDate+","
				edDtList.add(edDate)
			}			
			def dateTranspose = [stDtList,edDtList].transpose()
					
			JSONObject jsonObj = new JSONObject()					
			jsonObj.putAt("BR_NAME", userDataRet?.BR_NAME)
			jsonObj.putAt("VC_USER_NAME", userDataRet?.VC_USER_NAME)
			jsonObj.putAt("VC_PASSWORD", userDataRet?.VC_PASSWORD)
			jsonObj.putAt("CH_USER_ACTIVE", userDataRet?.CH_USER_ACTIVE)
			jsonObj.putAt("CH_USER_CODE", userDataRet?.CH_USER_CODE)
			jsonObj.putAt("vc_company_name", userDataRet?.vc_company_name)
			jsonObj.putAt("VC_ROLE_NAME", userDataRet?.VC_ROLE_NAME)
			jsonObj.putAt("DT_FIN_START_DATE", stDate)
			jsonObj.putAt("DT_FIN_END_DATE", edDate)
			jsonObj.putAt("FLAG", flag)
			jsonObj.putAt("userCD", userCD)			
			jsonObj.putAt("dtValS", dtValS)					
			render jsonObj as JSON			
		}catch(Exception exception){
			log.info("Exception in MkUsersController getUserDataEdit method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return		
	}	
	
	/**
	 * Method Name: getRolesBasedOnBranch
	 * Description: Retrieve Roles Data from table and return data to
	 * respective GSP page using by json 
	 *
	 * @return : Roles List
	 */	
	def getRolesBasedOnBranch() {	
		def roleData
		try{
			//Calling getRolesDataBasedOnBranch method of MkUsersUtilsService to get branch based roles
			roleData = mkUsersUtilsService.getRolesDataBasedOnBranch(params)
			JSONObject jsonObj = new JSONObject()		
			jsonObj.putAt("CH_ROLE_CODE", roleData?.CH_ROLE_CODE)
			jsonObj.putAt("VC_ROLE_NAME", roleData?.VC_ROLE_NAME)		
			render jsonObj as JSON	
		}catch(Exception exception){
			log.info("Exception in MkUsersController getRolesBasedOnBranch method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return
	}
	
	/**
	 * Method Name: getCompanies
	 * Description: Retrieve Companies Data from table and return data to
	 * respective GSP page using by json
	 *
	 * @return : Company List
	 */	
	def getCompanies() {	
		def compData
		try{
			//Calling getCompanyData method of MkUsersUtilsService to get the companies
			compData = mkUsersUtilsService.getCompanyData()
			JSONObject jsonObj = new JSONObject()		
			jsonObj.putAt("VC_COMP_CODE", compData?.VC_COMP_CODE)
			jsonObj.putAt("VC_COMPANY_NAME", compData?.VC_COMPANY_NAME)		
			render jsonObj as JSON	
		}catch(Exception exception){
			log.info("Exception in MkUsersController getCompanies method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return
	}
	
	/**
	 * Method Name: changePasswordUser
	 * Description: used to change the password of the user
	 *
	 * @return : userListForUser
	 */
	def changePasswordUser() {
		def userListForUser
		def userCode = session.userId
		try {
			//Calling getUserDataByUserId method of MkUsersUtilsService to get the particular user data
			userListForUser = mkUsersUtilsService.getUserDataByUserId(userCode)
		}catch(Exception exception){
			log.info("Exception in MkUsersController changePasswordUser method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}	
		[userListForUser:userListForUser]
	}
	
	/**
	 * Method Name: changePasswordAdmin
	 * Description: used to change the password of the user(admin)
	 *
	 * @return : userListForAdmin
	 */
	def changePasswordAdmin() {
		def userListForAdmin
		try {
			//Calling getUserData method of MkUsersUtilsService to get the particular user data
			userListForAdmin = mkUsersUtilsService.getUserData()
		}catch(Exception exception){
			log.info("Exception in MkUsersController changePasswordAdmin method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[userListForAdmin:userListForAdmin]
	}
	
	/**
	 * Method Name: getUserDetailsAdmin
	 * Description: used to get the admin user details
	 *
	 * @return : json data of user List admin details List
	 */
	def getUserDetailsAdmin() {
		def userList
		try{
			//Calling getUserDataById method of MkUsersUtilsService to get the particular user data of the admin 
			userList = mkUsersUtilsService.getUserDataById(params)
			
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("VC_PASSWORD", userList?.VC_PASSWORD)
			render jsonObj as JSON
		}catch(Exception exception){
			log.info("Exception in MkUsersController getUserDetailsAdmin method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return
	}
	
	/**
	 * Method Name: getUserDetailsUser
	 * Description: gets the details of the user
	 *
	 * @return : json data of user List
	 */
	def getUserDetailsUser() {
		def userList
		try{
			//Calling getUserDataById method of MkUsersUtilsService to get the particular user data 
			userList = mkUsersUtilsService.getUserDataById(params)
			
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("VC_PASSWORD", userList?.VC_PASSWORD)
			render jsonObj as JSON
		}catch(Exception exception){
			log.info("Exception in MkUsersController getUserDetailsUser method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return
	}
	
	/**
	 * Method Name: updatePassword
	 * Description: used to change the password
	 *
	 * @return : json data of update status
	 */
	def updatePassword() {
		int updatecount
		try{
			//Calling updateUserPassword method of MkUsersUtilsService to update the password
			updatecount = mkUsersUtilsService.updateUserPassword(params)
			JSONObject jsonObj = new JSONObject()
			if(updatecount == 1) {
				jsonObj.putAt("result","success")
			} else {
				jsonObj.putAt("result","fail")
			}
			render jsonObj as JSON
		}catch(Exception exception){
			log.info("Exception in MkUsersController updatePassword method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return	
	}
	
	/**
	 * Method Name: getBranchList
	 * Description: Retrieve Branches Data from table and return data to
	 * 				respective GSP page using by json
	 *
	 * @return : Branch List
	 */
	def getBranchList(){
		def brList
		try{
			//Calling getBrData method of MkUsersUtilsService to get the branch list
			brList = mkUsersUtilsService.getBrData()
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("BR_CODE", brList?.BR_CODE)
			jsonObj.putAt("BR_NAME", brList?.BR_NAME)
			render jsonObj as JSON
		}catch(Exception exception){
			log.info("Exception in MkUsersController getBranchList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return
	}
}
