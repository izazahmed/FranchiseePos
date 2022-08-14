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
 * BrMstTabController
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
class BrMstTabController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def brMstTabService
	
	/**
	 * Method Name: index
	 * Description: Showing index Page of Branch Master
	 *
	 * @return : void
	 */
    def index() {}
	
	/**
	 * Method Name: saveBranch
	 * Description: used to save Branch Details
	 *
	 * @return :  redirect to view page
	 */
	@Transactional
	def saveBranch() {
		try{			
			//Calling saveBranch method of brMstTabService to save Branch
			brMstTabService.saveBrMstBranch(params)
			redirect (action : "view", controller :'BrMstTab' )
			return
		}catch(Exception exception){
			log.info("Exception in BrMstTabController saveBranch method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: view
	 * Description: Shows the created Branch list
	 *
	 * @return : list of baranchyResult
	 */
	def view() {
		def baranchResult = null
		try{
			//Calling getCompanyList method of mstCompanyService to get the list of branches
			baranchResult = brMstTabService.getBranchList()
		}catch(Exception exception){
			log.info("Exception in BrMstTabController view method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[baranchResult : baranchResult]
	}
}
