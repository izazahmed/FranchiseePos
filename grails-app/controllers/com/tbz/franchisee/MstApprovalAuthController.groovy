package com.tbz.franchisee

/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.sql.Sql
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * MstApprovalAuthController
 * Controller for Scheme master
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
class MstApprovalAuthController {
	def mstApprovalAuthService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def dataSource
	
	/**
	 * Method Name: Index
	 * Description: Display index page using this method
	 * 
	 * @return : inDtInstance
	 */
	def index(Integer max) {
		
		try {
			def schemeList = mstApprovalAuthService.getAllSchemeList()
			
			List<String> categoryList = new ArrayList<String>();
			categoryList.add("GOLD")
			categoryList.add("DIAMOND")
			
			List<String> departmentList = new ArrayList<String>();
			departmentList.add("ORDER DEPTT")
			departmentList.add( "EARING")
			departmentList.add("BRACELET")
			departmentList.add("NECKLACE")
			departmentList.add( "CHAIN")
			departmentList.add("PENDANT")
			departmentList.add( "KANGAN")
			departmentList.add("SET")
			departmentList.add( "DIAMOND")
			departmentList.add("CUPID DIAMOND")
			departmentList.add("ZAVERAT")
			departmentList.add("PEARLS")
			departmentList.add("BRANCH")
			
			List<String> genderList = new ArrayList<String>();
			genderList.add("MALE")
			genderList.add("FEMALE")
			
			List<String> activeList = new ArrayList<String>();
			activeList.add("ACTIVE")
			activeList.add("INACTIVE")
			
			List<String> designationList = new ArrayList<String>();
			designationList.add("PRO")
			designationList.add("MANAGER")
			designationList.add("SALES OFFICER")
			designationList.add("COUNTER SALES")

			//def salesPersonList = mstApprovalAuthService.getSalesPersonData()
		
	        params.max = Math.min(max ?: 10, 100)
	        respond MstApprovalAuth.list(params), model:[mstApprovalAuthInstanceCount: MstApprovalAuth.count() , schemeList : schemeList ,departmentList:departmentList,categoryList : categoryList , genderList: genderList,schemeList:schemeList, designationList : designationList , activeList:activeList]
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController dashboard method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: show
	 * Description: Display show page using this method
	 *
	 * @return : mstApprovalAuthInstance
	 */
    def show(MstApprovalAuth mstApprovalAuthInstance) {
		try {
			respond mstApprovalAuthInstance
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController dashboard method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: getActualSalesPersonData
	 * Description: get Actual Sales Person Data for view popup this method
	 *
	 * @return : JSONobject
	 */
	def getActualSalesPersonData(){
		try {
			def employeeInstance = mstApprovalAuthService.getExactEmployeeData(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("VC_SALES_ID", employeeInstance?.VC_SALES_ID)
			jsonObj.putAt("EMP_NAME", employeeInstance?.EMP_NAME)
			render jsonObj as JSON
			return
		}catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController index method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController dashboard method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getActualSchemData
	 * Description: get Actual Scheme Data for view popup this method
	 *
	 * @return : JSONobject
	 */
	def getActualSchemData(){
		try {
			def schemeInstance = mstApprovalAuthService.getExactSchemeData(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("VC_APPROVAL_ID", schemeInstance?.VC_APPROVAL_ID)
			jsonObj.putAt("VC_EMP_CODE", schemeInstance?.VC_EMP_CODE)
			jsonObj.putAt("VC_APPROVAL_NAME", schemeInstance?.VC_APPROVAL_NAME)
			jsonObj.putAt("DT_START_DATE", schemeInstance?.DT_START_DATE)
			jsonObj.putAt("DT_END_DATE", schemeInstance?.DT_END_DATE)
			jsonObj.putAt("VC_DESIGNATION", schemeInstance?.VC_DESIGNATION)
			jsonObj.putAt("VC_GENDER", schemeInstance?.VC_GENDER)
			jsonObj.putAt("VC_DEPTT", schemeInstance?.VC_DEPTT)
			jsonObj.putAt("VC_EMP_TYPE", schemeInstance?.VC_EMP_TYPE)
			jsonObj.putAt("NU_APPROVAL_AUTH", schemeInstance?.NU_APPROVAL_AUTH)
			jsonObj.putAt("CH_ACTIVE", schemeInstance?.CH_ACTIVE)
			render jsonObj as JSON
			return
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController getActualSchemData method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController getActualSchemData method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getActualSchemData
	 * Description: get Actual Scheme Data for view popup this method
	 *
	 * @return : JSONobject
	 */
	def getSchemeList() {
		
		try {
			def schemeInstance = mstApprovalAuthService.getAllSchemeList()
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("VC_APPROVAL_ID", schemeInstance?.VC_APPROVAL_ID)
			jsonObj.putAt("vc_emp_code", schemeInstance?.vc_emp_code)
			jsonObj.putAt("VC_APPROVAL_NAME", schemeInstance?.VC_APPROVAL_NAME)
			jsonObj.putAt("DT_START_DATE", schemeInstance?.DT_START_DATE)
			jsonObj.putAt("DT_END_DATE", schemeInstance?.DT_END_DATE)
			jsonObj.putAt("vc_deptt", schemeInstance?.vc_deptt)
			jsonObj.putAt("VC_DESIGNATION", schemeInstance?.VC_DESIGNATION)
			jsonObj.putAt("VC_GENDER", schemeInstance?.VC_GENDER)
			jsonObj.putAt("NU_APPROVAL_AUTH", schemeInstance?.NU_APPROVAL_AUTH)
			jsonObj.putAt("CH_ACTIVE", schemeInstance?.CH_ACTIVE)
			render jsonObj as JSON
			return
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController getSchemeList method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController getSchemeList method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: saveDiscount
	 * Description: save Scheme Data using this method
	 *
	 * @return : JSONobject
	 */
    @Transactional
    def saveDiscount(MstApprovalAuth mstApprovalAuthInstance) {
		try {
			if (!mstApprovalAuthInstance?.vcApprovalId) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstApprovalAuth.label', default: 'Approval Id'), mstApprovalAuthInstance?.vcApprovalId])
				redirect(action: "index")
				return
			}
			mstApprovalAuthService.saveScheme(mstApprovalAuthInstance)
			flash.message="${mstApprovalAuthInstance?.vcApprovalId} ApprovalID created successfully"
			
		}  catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController saveDiscount method "+ioexception.getMessage())
			flash.warning = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController saveDiscount method "+exception.getMessage())
			flash.warning = message(code: 'default.exception.message', args: [exception.getMessage()])
		}	
		redirect action: "index"
		return
    }

	/**
	 * Method Name: edit
	 * Description: edit Scheme Data using this method
	 *
	 * @return : JSONobject
	 */
    def edit(MstApprovalAuth mstApprovalAuthInstance) {
		try {
 			def schemeMst = mstApprovalAuthService.updateSchemeData(params, params?.tempApprovalId)
			redirect (action : "index" , mstApprovalAuthInstance : mstApprovalAuthInstance)
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController edit method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController edit method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: update
	 * Description: update Scheme Data using this method
	 *
	 * @return : JSONobject
	 */
    @Transactional
    def update(MstApprovalAuth mstApprovalAuthInstance) {
		try {
	        if (mstApprovalAuthInstance == null) {
	            notFound()
	            return
	        }
	
	        if (mstApprovalAuthInstance.hasErrors()) {
	            respond mstApprovalAuthInstance.errors, view:'edit'
	            return
	        }
	
	        mstApprovalAuthInstance.save flush:true
	
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.updated.message', args: [message(code: 'MstApprovalAuth.label', default: 'MstApprovalAuth'), mstApprovalAuthInstance.id])
	                redirect mstApprovalAuthInstance
	            }
	            '*'{ respond mstApprovalAuthInstance, [status: OK] }
	        }
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: delete
	 * Description: delete Scheme Data using this method
	 *
	 * @return : JSONobject
	 */
    @Transactional
    def delete(MstApprovalAuth mstApprovalAuthInstance) {
		
		try {
			if (mstApprovalAuthInstance == null) {
				notFound()
				return
			}
			
			mstApprovalAuthInstance.delete flush:true
			
			request.withFormat {
					form multipartForm {
						flash.message = message(code: 'default.deleted.message', args: [message(code: 'MstApprovalAuth.label', default: 'MstApprovalAuth'), mstApprovalAuthInstance.id])
								redirect action:"index", method:"GET"
					}
					'*'{ render status: NO_CONTENT }
				}
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}

    }

	/**
	 * Method Name: notFound
	 * Description: Scheme Data available or not  using this method
	 *
	 * @return : JSONobject
	 */
    protected void notFound() {
		try {
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstApprovalAuth.label', default: 'MstApprovalAuth'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		} catch(IOException ioexception) {
			log.info("Exception in MstApprovalAuthController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstApprovalAuthController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
