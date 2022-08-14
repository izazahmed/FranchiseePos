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
 * XxposCashReceiptsController
 * Controller for Jan se Jama
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
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional(readOnly = true)
class XxposCashReceiptsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def xxposCashReceiptsUtilsService
	
	/**
	 * Method Name: index
	 * Description: This method display index page
	 * 
	 * @return : xxposCashReceiptsInstanceCount
	 */
    def index(Integer max) {
		try {
			params.max = Math.min(max ?: 10, 100)
	        respond XxposCashReceipts.list(params), model:[xxposCashReceiptsInstanceCount: XxposCashReceipts.count()]
		} catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: show
	 * Description: This method display list of jan se jama list
	 *
	 * @return : xxposCashReceiptsInstanceCount
	 */
    def show(XxposCashReceipts xxposCashReceiptsInstance) {
		try {
			respond xxposCashReceiptsInstance
		} catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController show method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController show method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: create
	 * Description: Using This method create Jan se jama record
	 *
	 * @return : params
	 */
    def create() {
		try{
			respond new XxposCashReceipts(params)
		}catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController create method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController create method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: getCustInfo
	 * Description: Using This method get customer information
	 *
	 * @return : JSONobject
	 */
	def getCustInfo()
	{
		try {
			def custInfoInstance = xxposCashReceiptsUtilsService.getCustInfo(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("CUST_ID", custInfoInstance?.CUST_ID)
			jsonObj.putAt("FNAME", custInfoInstance?.FNAME)
			jsonObj.putAt("MOBILE", custInfoInstance?.MOBILE)
			
			render jsonObj as JSON
			return
		} catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController getCustInfo method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController getCustInfo method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
	}

	/**
	 * Method Name: save
	 * Description: Using This method save Jan se Jama record
	 *
	 * @return : xxposCashReceiptsInstance
	 */
    @Transactional
    def save(XxposCashReceipts xxposCashReceiptsInstance) {
		try {
			def mstCode = xxposCashReceiptsUtilsService.saveJanSeJama(xxposCashReceiptsInstance)
			redirect (action : "index" , xxposCashReceiptsInstance : xxposCashReceiptsInstance)
		} catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: edit
	 * Description: Using This method edit Jan se Jama record
	 * @params : params
	 * @return : xxposCashReceiptsInstance
	 */
    def edit(XxposCashReceipts xxposCashReceiptsInstance) {
		try {
			respond xxposCashReceiptsInstance
		} catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController edit method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController edit method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: update
	 * Description: Using This method update Jan se Jama record
	 * @params : params
	 * @return : xxposCashReceiptsInstance
	 */
    @Transactional
    def update(XxposCashReceipts xxposCashReceiptsInstance) {
		try {
	        if (xxposCashReceiptsInstance == null) {
	            notFound()
	            return
	        }
	
	        if (xxposCashReceiptsInstance.hasErrors()) {
	            respond xxposCashReceiptsInstance.errors, view:'edit'
	            return
	        }
	
	        xxposCashReceiptsInstance.save flush:true
	
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.updated.message', args: [message(code: 'XxposCashReceipts.label', default: 'XxposCashReceipts'), xxposCashReceiptsInstance.id])
	                redirect xxposCashReceiptsInstance
	            }
	            '*'{ respond xxposCashReceiptsInstance, [status: OK] }
	        }
		} catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: delete
	 * Description: Using This method update Jan se Jama record
	 * @params : params
	 * @return : xxposCashReceiptsInstance
	 */
    @Transactional
    def delete(XxposCashReceipts xxposCashReceiptsInstance) {
		try {
	        if (xxposCashReceiptsInstance == null) {
	            notFound()
	            return
	        }
	        xxposCashReceiptsInstance.delete flush:true
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.deleted.message', args: [message(code: 'XxposCashReceipts.label', default: 'XxposCashReceipts'), xxposCashReceiptsInstance.id])
	                redirect action:"index", method:"GET"
	            }
	            '*'{ render status: NO_CONTENT }
	        }
		} catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: notFound
	 * Description: Using This method check whether Jan se Jama record present or not
	 * @params : params
	 * @return : message whether jan se jama record present or not
	 */
    protected void notFound() {
		try {
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'xxposCashReceipts.label', default: 'XxposCashReceipts'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		}catch(IOException ioexception) {
			log.info("Exception in XxposCashReceiptsController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in XxposCashReceiptsController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
