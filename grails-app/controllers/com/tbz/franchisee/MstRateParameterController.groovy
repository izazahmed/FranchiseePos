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
import grails.transaction.Transactional

import org.apache.commons.validator.GenericValidator
import org.codehaus.groovy.grails.web.json.JSONArray;
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

import com.google.gson.JsonArray;
import com.tbz.franchisee.FndFlexValuesVl

/**
 * MstRateParameterController
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
 * 01/03/2016	  Izaz      Created File
 *  
 */


@Transactional(readOnly = true)
class MstRateParameterController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def scaffold = MstRateParameter

	def mstRateParameterService
	JSONObject jsonObj
	
	/**
	 * Method Name: index
	 * Description: Showing index Page after clicking on Rate Parameter Link
	 *
	 * @return : list of sizeResult
	 */
	def index() {		
		List<String> sizeList = new ArrayList<String>();
		try{
			sizeList.add("1")
			sizeList.add("2")
			sizeList.add("3")
			sizeList.add("4")
			sizeList.add("5")
			sizeList.add("6")
			sizeList.add("7")
			sizeList.add("8")
			sizeList.add("9")
			sizeList.add("10")
			
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController index method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}	
		[sizeResult : sizeList]
	}
	
	/**
	 * Method Name: view
	 * Description: Shows the created rate parameter list
	 *
	 * @return : list of mstRateParameterResult
	 */
	def view() {
		def mstRateParameterResult
		try{
			//Calling mstRateParameterListVal method of mstRateParameterService to get created parameter list
			mstRateParameterResult = mstRateParameterService.mstRateParameterListVal()
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController view method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[mstRateParameterResult : mstRateParameterResult]
	}
		
	/**
	 * Method Name: getPurityList
	 * Description: used to get the purity list based on category
	 *
	 * @return :  purity's json data
	 */
	def getPurityList() {
		def purityListResult
		try{
			//Calling getPurity method of mstRateParameterService to get purity list
			purityListResult = mstRateParameterService.getPurity(params);	
			jsonObj = new JSONObject()	
			jsonObj.putAt("PURITY", purityListResult?.PURITY)	
			render jsonObj as JSON
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController  getPurityList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
		
	/**
	 * Method Name: saveRatePar
	 * Description: used to save the rate parameter
	 *
	 * @return :  if it saves redirect to index page else shows error message
	 */
	@Transactional
	def saveRatePar() {
		def validatePurityResult,mstRateParameterInstance,mstRateParameter
		try{
			//Calling validatePurity method of mstRateParameterService to validate purity
			validatePurityResult = mstRateParameterService.validatePurity(params);	
			if(validatePurityResult >=1){
				mstRateParameterInstance = new MstRateParameter(params)
				flash.warning = message(code: 'Purity Is Already Exist For This Branch', args: [
					message(code: 'mstRateParameter.label', default: 'MstRateParameter'),
					mstRateParameterInstance.vcPurity])	
				redirect (action : "index")
			}else{
				//Calling saveMstRateParameter method of mstRateParameterService to save rate parameter
				mstRateParameter = mstRateParameterService.saveMstRateParameter(params)
				redirect (action : "view", controller: 'MstRateParameter')
				return
			}
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController  saveRatePar method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: edit
	 * Description: used to update the rate parameter
	 *
	 * @return :  multiple list of mstRateParameterResult,categoryListResult and rowCount
	 */
	def edit() {
		def categoryListResult,mstRateParameterResult,rowCount
		try{
			//Calling getCategoryList method of mstRateParameterService to get the category list
			categoryListResult = mstRateParameterService.getCategoryList();
			//Calling mstRateParameterListVal method of mstRateParameterService to get the saved rate parameter list
			mstRateParameterResult = mstRateParameterService.mstRateParameterListVal()
			//Calling getTotalRowCount method of mstRateParameterService to get the total count
			rowCount = mstRateParameterService.getTotalRowCount()
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController  edit method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[mstRateParameterResult : mstRateParameterResult, categoryListResult : categoryListResult, rowCount : rowCount]
	}
	
	/**
	 * Method Name: checkUnique
	 * Description: used to check the already purity is available or not
	 *
	 * @return :  json object
	 */
	def checkUnique() {
		def brName,pur,oldBrNm,oldPur
		try{
			brName = params.brName
			pur = params.pur
			oldBrNm = params.oldBrNm
			oldPur = params.oldPur
			jsonObj = new JSONObject()	
			def validatePurityResult=0	
			if(oldBrNm.equals(brName) && oldPur == pur){	
				jsonObj.putAt("chkRslt", validatePurityResult)
			}else{	
				//Calling validateUpdatePurity method of mstRateParameterService to validate the purity
				validatePurityResult = mstRateParameterService.validateUpdatePurity(params);
				if(validatePurityResult >=1){	
					jsonObj.putAt("chkRslt", validatePurityResult)
				}else{	
					jsonObj.putAt("chkRslt", validatePurityResult)
				}
			}			
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController  checkUnique method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	/**
	 * Method Name: updateRatePar
	 * Description: used to update the rate parameter
	 *
	 * @return :  multiple list of mstRateParameterResult,categoryListResult and rowCount
	 */
	@Transactional
	def updateRatePar() {
		def update
		try{
			//Calling updateData method of mstRateParameterService to update the rate parameter
			update = mstRateParameterService.updateData(params)
			jsonObj = new JSONObject()	
			jsonObj.putAt("dataUpdate", update)
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController  updateRatePar method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
}
