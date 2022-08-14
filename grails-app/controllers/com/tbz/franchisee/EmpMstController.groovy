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

/**
 * EmpMstController
 * Controller for showing Employee master related thigs
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

@Transactional(readOnly = true)
class EmpMstController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def scaffold = EmpMst
	def empMstUtilsService 
	
	/**
	 * Method Name: index
	 * Description: Display employee index page
	 *
	 * @return : empMstInstanceCount
	 */
    def index(Integer max) {
		try {
			params.max = Math.min(max ?: 100, 100)
			respond EmpMst.list(params), model:[empMstInstanceCount: EmpMst.count()]
		} catch(IOException ioexception) {
			log.info("Exception in EmpMstController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}	
    }

	/**
	 * Method Name: show
	 * Description: Display employee show page
	 *
	 * @return : empMstInstance
	 */
    def show(EmpMst empMstInstance) {
		try {
			respond empMstInstance
		} catch(IOException ioexception) {
			log.info("Exception in EmpMstController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController index method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}	
    }
	
	/**
	 * Method Name: add
	 * Description: Add Employee using add method
	 *
	 * @return : params 
	 */
	def add() {
		try {
			respond new EmpMst(params)
		}  catch(IOException ioexception) {
			log.info("Exception in EmpMstController add method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController add method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}	
	}

	/**
	 * Method Name: create
	 * Description: Create employee using this method
	 *
	 * @return : empMstInstanceCount
	 */
    def create() {
		try {
			respond new EmpMst(params)
		} catch(IOException ioexception) {
			log.info("Exception in EmpMstController create method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController create method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}	
    }

	/**
	 * Method Name: save
	 * Description: Save Employee using this method
	 * @params : params for saving employee
	 * @return : empMstInstance
	 */
    @Transactional
    def save(EmpMst empMstInstance) {
		
		try {
			def empMst = empMstUtilsService.saveEmpMst(empMstInstance)
			redirect (action : "index" , empMstInstance : empMstInstance)
		} catch(IOException ioexception) {
			log.info("Exception in EmpMstController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: edit
	 * Description: Edit Employee
	 * @params : employeeInstance
	 * @return : empMstInstance
	 */
    def edit(EmpMst empMstInstance) {
        respond empMstInstance
    }

	/**
	 * Method Name: update
	 * Description: Update employye using this method
	 * @params : empMstInstance
	 * @return : empMstInstance
	 */
    @Transactional
    def update(EmpMst empMstInstance) {
    	
		try {		
			def empMst = empMstUtilsService.updateEmpMst(params)
			redirect (action : "index" , empMstInstance : empMstInstance)
		} catch(IOException ioexception) {
			log.info("Exception in EmpMstController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: delete
	 * Description: Delete employee 
	 *
	 * @return : empMstInstance
	 */
    @Transactional
    def delete(EmpMst empMstInstance) {
		
		try {	
			def empMst = empMstUtilsService.deleteEmpMst(params)
			redirect (action : "index" , empMstInstance : empMstInstance)
		}  catch(IOException ioexception) {
			log.info("Exception in EmpMstController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: notFound
	 * Description: Check whether particular present in the DB or not
	 * @params : employeeId
	 * @return : Employee not found message
	 */
    protected void notFound() {
		try {
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'empMst.label', default: 'EmpMst'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		} catch(IOException ioexception) {
			log.info("Exception in EmpMstController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EmpMstController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
