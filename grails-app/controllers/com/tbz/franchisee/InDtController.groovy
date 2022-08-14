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
 * InDtController
 * Controller for Inward details 
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
class InDtController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def inDtUtilsService
	
	/**
	 * Method Name: index
	 * Description: Display index page using this method
	 * 
	 * @return : count of inDtInstance
	 */
    def index(Integer max) {
		try {
	        params.max = Math.min(max ?: 10, 100)
	        respond InDt.list(params), model:[inDtInstanceCount: InDt.count()]
		} catch(IOException ioexception) {
			log.info("Exception in InDtController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: show
	 * Description: Display show page using this method
	 * @params : inDtinstance
	 * @return : inDtInstance
	 */
    def show(InDt inDtInstance) {
		try {
			def inInst = InDt.findByInwardId(params?.id)
			respond inInst
		} catch(IOException ioexception) {
			log.info("Exception in InDtController show method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController show method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
		
	/**
	 * Method Name: create
	 * Description: Create inward details using this method
	 * 
	 * @return : params
	 */
    def create() {
		try {
			respond new InDt(params)
		}  catch(IOException ioexception) {
			log.info("Exception in InDtController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: save
	 * Description: save Inward Details using this method
	 * @params : inDtinstance
	 * @return : inDtInstance
	 */
    @Transactional
    def save(InDt inDtInstance) {
		try {
			def inDt = inDtUtilsService.saveInDt(inDtInstance)
			redirect (action : "index" , inDtInstance : inDtInstance)
		} catch(IOException ioexception) {
			log.info("Exception in InDtController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: edit
	 * Description: edit Inward Details using this method
	 * @params : id of inward details
	 * @return : inDtInstance
	 */
    def edit() {
		try {
			def inDT = InDt.findByInwardId(params?.id)
	        respond inDT
		} catch(IOException ioexception) {
			log.info("Exception in InDtController edit method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: update
	 * Description: update Inward Details using this method
	 * @params : inDtinstance
	 * @return : inDtInstance
	 */
    @Transactional
    def update(InDt inDtInstance) {
		try {
			def inDt = inDtUtilsService.updateInDt(params)
			redirect (action : "index" , inDtInstance : inDtInstance)
		}  catch(IOException ioexception) {
			log.info("Exception in InDtController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
    }

	/**
	 * Method Name: delete
	 * Description: delete Inward Details using this method
	 * 
	 * @return : inDtInstance
	 */
    @Transactional
    def delete() {

		try {
			def inDt = inDtUtilsService.deleteInDt(params)
			redirect (action : "index" , inDt : inDt)		
		} catch(IOException ioexception) {
			log.info("Exception in InDtController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: notFound
	 * Description: Check whether inward details record present or not
	 * @params : inDt id using params
	 * @return : inDt id present or not
	 */
    protected void notFound() {
		try {
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'inDt.label', default: 'InDt'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		}  catch(IOException ioexception) {
			log.info("Exception in InDtController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in InDtController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
