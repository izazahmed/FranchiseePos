package com.tbz.franchisee

/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * DtRateDetailController
 * Description
 *
 * @author (CTE).
 *
 * Contact () (optional)
 *
 * @version    0.1
 * @date        01/02/2016
 *
 * MOD HISTORY
 * DATE           USER      COMMENTS
 *  01/04/2016	  Izaz      Created File  
 *  15/4/2016     Izaz		error msg displayed if no data for rate,saveRgSelection method added and file modified based on comments of our tl
 */

@Transactional(readOnly = true)
class DtRateDetailController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def scaffold = DtRateDetail
	
	def dtRateDetailUtilsService
	JSONObject jsonObj
	def rateData
	
	/**
	 * Method Name: index
	 * Description: Showing index Page after clicking on Rate Master Link
	 *
	 * @return : list of listViewDetails
	 */
	def index() {
		def listViewDetails
		List<String> rateTypeList = new ArrayList<String>();
		try{		
			rateTypeList.add("REGION")
			rateTypeList.add("BRANCH")
			rateTypeList.add("REGION BRANCH WISE")
			//Calling getSavedDtRateDetails method of dtRateDetailUtilsService to get saved dt rate details data
			listViewDetails = dtRateDetailUtilsService.getSavedDtRateDetails()
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController index method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[listViewDetails : listViewDetails,rateTypeList : rateTypeList]
	}
	
	/**
	 * Method Name: create
	 * Description: Showing create Page after clicking add button
	 * 
	 * @return : multiple lists of rateTypeList and brSelNextVl
	 */
	def create() {
		def brSelNextVl,regionListInstance,brMstListInstance
		List<String> rateTypeList = new ArrayList<String>();
		try{			
			//rateTypeList.add("PLEASE SELECT")
			rateTypeList.add("REGION")
			rateTypeList.add("BRANCH")
			rateTypeList.add("REGION BRANCH WISE")
			//rateTypeList.add("ALL")
			//Calling getBrSelNextValue method of dtRateDetailUtilsService to get branch selection table's next detail id
			brSelNextVl = dtRateDetailUtilsService.getBrSelNextValue()
			log.info("brSelNextVl :"+brSelNextVl)			
			//Calling getRegionList method of dtRateDetailUtilsService to get region list
			//regionListInstance = dtRateDetailUtilsService.getRegionList()
			//Calling getBranchList method of dtRateDetailUtilsService to get branch list
			//brMstListInstance = dtRateDetailUtilsService.getBranchList()
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  create method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
		//[rateTypeList:rateTypeList,brSelNextVl:brSelNextVl,regionListInstance:regionListInstance,brMstListInstance:brMstListInstance]
		[rateTypeList:rateTypeList,brSelNextVl:brSelNextVl]
	}
	
	/**
	 * Method Name: getBranchData
	 * Description: Shows branch list in pop up after selecting branch and clicking button
	 *
	 * @return : branch's json data
	 */
	def getBranchData() {
		def brMstListInstance
		try{
			//Calling getBranchList method of dtRateDetailUtilsService to get branch list
			brMstListInstance = dtRateDetailUtilsService.getBranchList()
			jsonObj = new JSONObject()
			jsonObj.putAt("BR_CODE", brMstListInstance?.BR_CODE);
			jsonObj.putAt("BR_NAME", brMstListInstance?.BR_NAME);
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  getBranchData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	/**
	 * Method Name: getRegionData
	 * Description: Shows region list pop up after selecting region and clicking button
	 *
	 * @return : region's json data
	 */
	def getRegionData() {
		def regionListInstance
		try{
			//Calling getRegionList method of dtRateDetailUtilsService to get region list
			regionListInstance = dtRateDetailUtilsService.getRegionList()
			jsonObj = new JSONObject()
			jsonObj.putAt("REGION_CODE", regionListInstance?.REGION_CODE);
			jsonObj.putAt("REGION_NAME", regionListInstance?.REGION_NAME);
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  getRegionData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	/**
	 * Method Name: getRegionBranchData
	 * Description: Shows region wise branch list pop up after selecting region and clicking button
	 *
	 * @return : region's json data
	 */
	def getRegionBranchData() {
		def regionBranchListInstance
		try{
			//Calling getRegionBranchList method of dtRateDetailUtilsService to get region wise branch list
			regionBranchListInstance = dtRateDetailUtilsService.getRegionBranchList(params)
			jsonObj = new JSONObject()
			jsonObj.putAt("BR_CODE", regionBranchListInstance?.BR_CODE);
			jsonObj.putAt("BR_NAME", regionBranchListInstance?.BR_NAME);
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  getRegionBranchData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	/**
	 * Method Name: saveBrSelection
	 * Description: saves the branch popup's checked data
	 *
	 * @return : branch's json data
	 */
	def saveBrSelection() {
		def resultData
		try{
			//Calling saveBrSel method of dtRateDetailUtilsService to save the branch data
			resultData = dtRateDetailUtilsService.saveBrSel(params)
			jsonObj = new JSONObject()
			jsonObj.putAt("resultData", resultData);
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  saveBrSelection method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	/**
	 * Method Name: saveRgSelection
	 * Description: saves the region popup's checked data
	 *
	 * @return : region's json data
	 */
	def saveRgSelection() {
		def saveRegData
		try{
			//Calling saveRgSel method of dtRateDetailUtilsService to save the region data
			saveRegData = dtRateDetailUtilsService.saveRgSel(params)
			jsonObj = new JSONObject()
			jsonObj.putAt("saveRegData", saveRegData);
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  saveRgSelection method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	/**
	 * Method Name: getrate
	 * Description: gets the rate data
	 *
	 * @return : if data available for selected branch/region it shows the data else shows the error message
	 */
	def getrate(){
		try{
			//Calling getRate method of dtRateDetailUtilsService to get rate data
			rateData = dtRateDetailUtilsService.getRate(params)
			if(rateData == 1){
				jsonObj = new JSONObject()
				jsonObj.putAt("rateData", "No Data");
				render jsonObj as JSON
			}else{		
				render template:"dataDescription" , model: [rateData:rateData]
			}
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  getrate method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: save
	 * Description: saves the rate Master's form  data
	 *
	 * @return : after inserting successfully it will redirect to index page of rate master form
	 */
	@Transactional
	def save(DtRateDetail dtRateDetailInstance) {
		def saveData
		try{
			//Calling saveDetails method of dtRateDetailUtilsService to save the rate data
			saveData =  dtRateDetailUtilsService.saveDetails(rateData,params)
			redirect (action : "index" , dtRateDetailInstance : dtRateDetailInstance)
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController  save method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getPerticularBranchData
	 * Description: gets the particular branch data
	 *
	 * @return : list of rateData
	 */	
	def getParticularBranchData() {
		try{			
			//Calling getParBrData method of dtRateDetailUtilsService to get the particular branch data
			rateData =  dtRateDetailUtilsService.getParBrData(params)
			render template:"dataDescription" , model: [rateData:rateData]
		}catch(Exception exception){
			log.info("Exception in DtRateDetailController getPerticularBranchData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
}
