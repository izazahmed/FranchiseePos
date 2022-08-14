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
 * MstCodeController
 * Controller for Code Master
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
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.*
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional(readOnly = true)
class MstCodeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def mstCodeInstance
	def mstCodeUtilsService
	def brCode
	
	def ajaxGetBank = {
		try {
			def bank = MstCode.get(params.id)
			
			render bank?.vcCodeDesc as JSON
		} catch(IOException ioexception) {
			log.info("Exception in MstCodeController ajaxGetBank method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController ajaxGetBank method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: index
	 * Description: Retrieve Branch Code from the running session and return branch code to further process
	 *
	 * @return : Branch Code
	 */
    def index(Integer max) {
		try{
			def brCode = session.brCode			
			[brCode:brCode]
		} catch(IOException ioexception) {
			log.info("Exception in MstCodeController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: show
	 * Description: Display view page for Mst Code
	 *
	 * @return : List
	 */
    def show(MstCode mstCodeInstance) {
		try{
			respond mstCodeInstance
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController show method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController show method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: create
	 * Description: Create Mst Code
	 * 
	 * @return : mstCodeInstance
	 */
    def create() {
		try{
			respond new MstCode(params)
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController create method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController create method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: addParams
	 * Description: Insert Bank, City, State, Region, Country and Credit Card Bank name into Database with sequence number
	 *
	 * @return : mstCodeInstance
	 */
	def addParams(MstCode mstCodeInstance){
		try{		
			def getMstcde = mstCodeUtilsService.getMaxCode(params)
			
			redirect (action : "index" , mstCodeInstance : mstCodeInstance)
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController add method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController add method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: saveMaster
	 * Description: Save Master Code Details record into Database
	 *
	 * @return : mstCodeInstance
	 */
    @Transactional	
    def saveMaster(MstCode mstCodeInstance) {
		try{					
			def custMst = mstCodeUtilsService.saveMstCode(mstCodeInstance)
			redirect (action : "index" , mstCodeInstance : mstCodeInstance)
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: edit
	 * Description: Edit or Update Master Code Details 
	 *
	 * @return : mstCodeInstance
	 */
    def edit(MstCode mstCodeInstance) {
		try {
			def editMstCode = mstCodeUtilsService.editNames(params)
			redirect (action : "index" , mstCodeInstance : mstCodeInstance)       
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController edit method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController edit method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: getBankName
	 * Description: Get Bank, City, State, Region, Country, CreditCard Bank Names List from Database to display in Index Page
	 *
	 * @return : List
	 */
	def getBankName(MstCode mstCodeInstance){		
		brCode = session.brCode
		if (params?.size == 'bank'){
			def bankNameList = mstCodeUtilsService.bankNames(brCode)
			render template: "description", model: [nameList:bankNameList, params: params]
		}
		if (params.size=="city"){
			def cityNameList = mstCodeUtilsService.cityNames(brCode)
			render template: "description", model: [nameList:cityNameList]
		}
		if (params.size=="state"){
			def stateNameList = mstCodeUtilsService.stateNames(brCode)
			render template: "description", model: [nameList:stateNameList]
		}
		if (params.size=="region"){
			def regionNameList = mstCodeUtilsService.regionNames(brCode)
			render template: "description", model: [nameList:regionNameList]
		}
		if (params.size=="country"){
			def countryNameList = mstCodeUtilsService.countryNames(brCode)
			render template: "description", model: [nameList:countryNameList]
		}
		if (params.size=="creditCard"){
			def creditCardNameList = mstCodeUtilsService.creditCardNames(brCode)
			render template: "description", model: [nameList:creditCardNameList]
		}
	}
	
	/**
	 * Method Name: updateName
	 * Description: Update branch wise names of Bank, City, State, Region, Country and CreditCard Bank 
	 *
	 * @return : mstCodeInstance
	 */
    @Transactional	
    def updateName() {
		def name = params?.name
		def brCode = session.brCode
		def vcCode = params?.vcCode
		try{
			def mstCode = mstCodeUtilsService.editNames(name,brCode,vcCode)
			redirect (action : "index" , mstCodeInstance : mstCodeInstance)
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: deleteParam
	 * Description: Delete branch wise parameters from Database
	 *
	 * @return : mstCodeInstance
	 */
    @Transactional
    def deleteParam(MstCode mstCodeInstance) {
		def name = params?.name
		def brCde = params?.brcode
		def vcCode = params?.vcCode
		try{
			def mstCode = mstCodeUtilsService.deleteParameter(name,brCde,vcCode)
			redirect (action : "index" , mstCodeInstance : mstCodeInstance)
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

    protected void notFound() {
		try {
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstCode.label', default: 'MstCode'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		}catch(IOException ioexception) {
			log.info("Exception in MstCodeController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstCodeController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
