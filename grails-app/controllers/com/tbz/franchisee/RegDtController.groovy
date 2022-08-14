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
 * 01/02/2016		Sachin				Created File
 * 15/04/2016	  	Sachin				Added Logger and Exception handling
 */


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RegDtController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def regDtUtilsService
	
	/**
	 * Method Name: index
	 * Description: This method will display index page
	 * 
	 * @return : regDtInstanceCount
	 */
    def index(Integer max) {
		try {
	        params.max = Math.min(max ?: 10, 100)
	        respond RegDt.list(params), model:[regDtInstanceCount: RegDt.count()]
		} catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: show
	 * Description: This method will display list of Registration details
	 *
	 * @return : List(regDtInstance)
	 */
    def show(RegDt regDtInstance) {
			try {
		respond regDtInstance
			} catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController show method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController show method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: create
	 * Description: This method will create Registration details record
	 * @params : params
	 * @return : regDtInstance
	 */
    def create() {
		try {
			
			respond new RegDt(params)
		} catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController create method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController create method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: save
	 * Description: This method will save Registration details record
	 * @params : params
	 * @return : regDtInstance
	 */
    @Transactional
    def save(RegDt regDtInstance) {
		try {
			def regDt = regDtUtilsService.saveRegDt(regDtInstance)
			redirect (action : "index" , regDtInstance : regDtInstance)
		} catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: edit
	 * Description: This method will edit Registration details record
	 * @params : params
	 * @return : regDtInstance
	 */
    def edit(RegDt regDtInstance) {
		try {
			respond regDtInstance
		} catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController edit method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController edit method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: update
	 * Description: This method will update Registration details record
	 * @params : params
	 * @return : regDtInstance
	 */
    @Transactional
    def update(RegDt regDtInstance) {
		try {
			def regDt = regDtUtilsService.updateRegDt(params)
			redirect (action : "index" , regDtInstance : regDtInstance)
		} catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		
    }

	/**
	 * Method Name: delete
	 * Description: This method will delete Registration details record
	 * @params : params
	 * @return : regDtInstance
	 */
    @Transactional
    def delete(RegDt regDtInstance) {
		try {
			def regDtMst = regDtUtilsService.deleteRegDt(params)
			redirect (action : "index" , regDtInstance : regDtInstance)
		} catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
    }

	/**
	 * Method Name: notFound
	 * Description: This method will check whether Registration details record present or not
	 * @params : params
	 * @return : Display message for registration details record present or not
	 */
    protected void notFound() {
		try {
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'regDt.label', default: 'RegDt'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		}catch(IOException ioexception) {
			log.info("Exception in MstRateParameterController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRateParameterController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
