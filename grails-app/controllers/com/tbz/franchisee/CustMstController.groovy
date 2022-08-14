package com.tbz.franchisee

/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */
import org.apache.commons.validator.GenericValidator

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional

import com.tbz.franchisee.SchemeMst
import java.text.DateFormat
import java.text.SimpleDateFormat

import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * CustMstController
 * Customer Controller
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
class CustMstController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def custMstUtilsService
	
    def index(Integer max) {
		
		def schemeName = SchemeMst.findAllBySchemeName(params?.schemeName)
		def brName = BrMstTab.findAllByBrName(params?.brName)
		
		session.schemeNo = schemeName?.schemeNo
		session.brCode = brName?.brCode
    }

	/**
	 * Method Name: show
	 * Description: Showing customer page
	 *
	 * @return : CustMst(instance)
	 */
    def show(CustMst custMstInstance) {
		respond custMstInstance
    }
	
	/**
	 * Method Name: create
	 * Description: creating customer page
	 *
	 * @return : List(customerDetails)
	 */
    def create() {
		
		try{
			def customerDetails = custMstUtilsService.selectCustMst(params)
			[customerDetails:customerDetails]
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController create method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController create method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: save
	 * Description: save the customer
	 *
	 * @return : CustMst(CustomerInstance),custId,randomCustId,randomCustId, pidRadio, apRadio, DOB
	 */
    @Transactional
    def saveCustDetails(CustMst custMstInstance) {
		
		try {
			log.info("custMstInstance....Saving Controller"+custMstInstance.toString())
			def DOB = params?.birthDate
			def oldCustId = params?.customerId
			
			String phNoO=params?.phoneO
			String phNoR=params?.phoneR
			String mobile=params?.mobileNo
			def brcode = session.schemeNo
			def schemeNo= session.brCode
			
			def custId = Math.abs(new Random().nextInt() % 600) + 1
			def randomCustId = brcode+schemeNo+'-'+'0000000'+custId
			
			def pidRadio = params?.pidRadio
			def apRadio = params?.apRadio
			log.info("-----------------------------------------randomCustId---------------------: "+randomCustId)
			if(oldCustId){
				//Calling updateCustomerId method of custMstUtilsService to update the customer by passing custMstInstance,oldCustId,randomCustId,DOB,phNoO,phNoR,mobile
				def updateCustomer = custMstUtilsService.updateCustomerId(custMstInstance,oldCustId,randomCustId,DOB,phNoO,phNoR,mobile)
				
			}else{
				//Calling updateCustomerId method of custMstUtilsService to update the customer by passing custMstInstance, randomCustId, pidRadio, apRadio, DOB,phNoO,phNoR,mobile
				def NewCustomer = custMstUtilsService.saveCustMst(custMstInstance, randomCustId, pidRadio, apRadio, DOB,phNoO,phNoR,mobile)
			}
			//redirecting to the Create page after saving customer
			redirect (action : "create" , custMstInstance : custMstInstance, custId: randomCustId, pidRadio:pidRadio, apRadio:apRadio, DOB:DOB)
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController saveCustDetails method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController saveCustDetails method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	
	/**
	 * Method Name: edit
	 * Description: edit the customer
	 *
	 * @return : CustMst(CustomerInstance)
	 */
    def edit(CustMst custMstInstance) {		
        respond custMstInstance
    }

	/**
	 * Method Name: update
	 * Description: update the customer
	 *
	 * @return : CustMst(CustomerInstance)
	 */
    @Transactional
    def update(CustMst custMstInstance) {
		try {
			def custMst = custMstUtilsService.updateCustMst(params)
			//redirecting to the index page
			redirect (action : "index" , custMstInstance : custMstInstance)
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: delete
	 * Description: delete the customer
	 *
	 * @return : CustMst(CustomerInstance)
	 */
    @Transactional
    def delete(CustMst custMstInstance) {
		try {
			//Calling deleteCustMst method of custMstUtilsService to delete the customer by passing params value
			def delCustMst= custMstUtilsService.deleteCustMst(params)
			redirect (action : "index" , custMstInstance : custMstInstance)
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: findCustomer
	 * Description: find the customer
	 * @param customer instance
	 * @return : JSON Object
	 */
	@Transactional
	def findCustomer(CustMst custMstInstance) {
		
		try {
			def searchQuery = params?.findCustId
			//Calling searchCustMst method of custMstUtilsService to search the customer by passing params value
			def custInstance = custMstUtilsService.searchCustMst(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("CUST_ID", custInstance?.CUST_ID)
			jsonObj.putAt("FNAME", custInstance?.FNAME)
			jsonObj.putAt("MNAME", custInstance?.MNAME)
			jsonObj.putAt("LNAME", custInstance?.LNAME)
			jsonObj.putAt("ADD1", custInstance?.ADD1)
			jsonObj.putAt("ADD2", custInstance?.ADD2)
			jsonObj.putAt("CITY", custInstance?.CITY)
			jsonObj.putAt("STATE", custInstance?.STATE)
			jsonObj.putAt("PIN", custInstance?.PIN)
			jsonObj.putAt("PHONE_R", custInstance?.PHONE_R)
			jsonObj.putAt("PHONE_O", custInstance?.PHONE_O)
			jsonObj.putAt("MOBILE", custInstance?.MOBILE)
			jsonObj.putAt("EMAIL_ID", custInstance?.EMAIL_ID)
			String dob = custInstance?.DOB
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
			Date date1 = (Date)formatter.parse(dob)
			Calendar cal = Calendar.getInstance()
			cal.setTime(date1)
			String dobFormatedDate = (cal.get(Calendar.DATE) + 1) + "-" + cal.get(Calendar.MONTH) + "-" +         cal.get(Calendar.YEAR)
			jsonObj.putAt("DOB", dobFormatedDate)
			jsonObj.putAt("PAN_NO", custInstance?.PAN_NO)
			jsonObj.putAt("CUST_NOMINEE", custInstance?.CUST_NOMINEE)
			jsonObj.putAt("CUST_NOMINEE_REL", custInstance?.CUST_NOMINEE_REL)
			jsonObj.putAt("CUST_NOMINEE_GEN", custInstance?.CUST_NOMINEE_GEN)
			jsonObj.putAt("PAN_NO", custInstance?.PAN_NO)
			jsonObj.putAt("CUST_NOMINEE", custInstance?.CUST_NOMINEE)
			jsonObj.putAt("CUST_NOMINEE_REL", custInstance?.CUST_NOMINEE_REL)
			jsonObj.putAt("PHOTO_ID", custInstance?.VC_FIELD6)
			jsonObj.putAt("ADDRESS_PROOF", custInstance?.VC_FIELD7)
			render jsonObj as JSON
			return			
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController findCustomer method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController findCustomer method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getCustData
	 * Description: Get the customer Data
	 * @param customer instance
	 * @return : JSON Object
	 */
	def getCustData(CustMst custMstInstance) {
		
		try {
			def customerDataInst = custMstUtilsService.selectCustData(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("CUST_ID", customerDataInst?.CUST_ID)
			jsonObj.putAt("FNAME", customerDataInst?.FNAME)
			jsonObj.putAt("MNAME", customerDataInst?.MNAME)
			jsonObj.putAt("LNAME", customerDataInst?.LNAME)
			jsonObj.putAt("ADD1", customerDataInst?.ADD1)
			jsonObj.putAt("ADD2", customerDataInst?.ADD2)
			jsonObj.putAt("CITY", customerDataInst?.CITY)
			jsonObj.putAt("STATE", customerDataInst?.STATE)
			jsonObj.putAt("PIN", customerDataInst?.PIN)
			jsonObj.putAt("PHONE_R", customerDataInst?.PHONE_R)
			jsonObj.putAt("PHONE_O", customerDataInst?.PHONE_O)
			jsonObj.putAt("MOBILE", customerDataInst?.MOBILE)
			jsonObj.putAt("EMAIL_ID", customerDataInst?.EMAIL_ID)
			
			String dob = customerDataInst?.DOB
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
			
			Date date1 = (Date)formatter.parse(dob)
			
			Calendar cal = Calendar.getInstance()
			cal.setTime(date1)
			String dobFormatedDate = (cal.get(Calendar.DATE) + 1) + "-" + cal.get(Calendar.MONTH) + "-" +         cal.get(Calendar.YEAR)
			
			jsonObj.putAt("DOB", dobFormatedDate)
			jsonObj.putAt("PAN_NO", customerDataInst?.PAN_NO)
			jsonObj.putAt("CUST_NOMINEE", customerDataInst?.CUST_NOMINEE)
			jsonObj.putAt("CUST_NOMINEE_REL", customerDataInst?.CUST_NOMINEE_REL)
			jsonObj.putAt("CUST_NOMINEE_GEN", customerDataInst?.CUST_NOMINEE_GEN)
			jsonObj.putAt("PAN_NO", customerDataInst?.PAN_NO)
			jsonObj.putAt("CUST_NOMINEE", customerDataInst?.CUST_NOMINEE)
			jsonObj.putAt("CUST_NOMINEE_REL", customerDataInst?.CUST_NOMINEE_REL)
			jsonObj.putAt("PHOTO_ID", customerDataInst?.VC_FIELD6)
			jsonObj.putAt("ADDRESS_PROOF", customerDataInst?.VC_FIELD7)
			render jsonObj as JSON
			return
			
		}  catch(IOException ioexception) {
			log.info("Exception in CustMstController getCustData method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController getCustData method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
	}	
	
	
	/**
	 * Method Name: notFound
	 * Description: Not Found Method
	 * @param customer instance
	 * @return : JSON Object
	 */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'custMst.label', default: 'CustMst'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
