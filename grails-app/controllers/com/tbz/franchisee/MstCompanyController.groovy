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
 * MstCompanyController
 * Description
 *
 * @author (CTE).
 *
 * Contact () (optional)
 *
 * @version    0.1
 * @date        09/05/2016
 *
 * MOD HISTORY
 * DATE           USER      COMMENTS
 *  09/05/2016	  Izaz      Created File
 *
 */

@Transactional(readOnly = true)
class MstCompanyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def mstCompanyService
	
	/**
	 * Method Name: index
	 * Description: Showing index Page of Company
	 *
	 * @return : void
	 */
    def index() {}
		
	/**
	 * Method Name: saveCompany
	 * Description: used to save Mst Company
	 *
	 * @return :  redirect to index page
	 */
	@Transactional
	def saveCompany() {
		try{			
			//Calling saveMstCompany method of mstCompanyService to save company
			mstCompanyService.saveMstCompany(params.companyName,session.brCode)
			redirect (action : "view", controller:'MstCompany')
			return
		}catch(Exception exception){
			log.info("Exception in MstRateParameterController  saveRatePar method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: view
	 * Description: Shows the created company list
	 *
	 * @return : list of mstCompanyResult
	 */
	def view() {
		def mstCompanyResult
		try{
			//Calling getCompanyList method of mstCompanyService to get the list of created companies
			mstCompanyResult = mstCompanyService.getCompanyList()
		}catch(Exception exception){
			log.info("Exception in MstCompanyController view method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[mstCompanyResult : mstCompanyResult]
	}
}
