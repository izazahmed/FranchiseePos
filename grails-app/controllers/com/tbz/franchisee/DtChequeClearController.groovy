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
 * DtChequeClearController
 * Controller for Cheque Clearance related things
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
class DtChequeClearController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def dtChequeClearUtilsService
	
	/**
	 * Method Name: index
	 * Description: Showing Index page of DtChequeClear
	 *
	 * @return : dtChequeClearInstanceCount
	 */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DtChequeClear.list(params), model:[dtChequeClearInstanceCount: DtChequeClear.count()]
    }

	/**
	 * Method Name: index
	 * Description: Showing show page of Cheque Clearance
	 *
	 * @return : dtChequeClearInstance
	 */
    def show(DtChequeClear dtChequeClearInstance) {
        respond dtChequeClearInstance
    }

	/**
	 * Method Name: create
	 * Description: Create cheque Clearance
	 *
	 * @return : Params
	 */
    def create() {
        respond new DtChequeClear(params)
    }

	/**
	 * Method Name: save
	 * Description: Save the Cheque
	 *
	 * @return : dtChequeClearInstance
	 */
    @Transactional
    def save(DtChequeClear dtChequeClearInstance) {
		
		try {
			def dtChqClear = dtChequeClearUtilsService.saveDtChequeClear(dtChequeClearInstance)
			redirect (action : "index" , dtChequeClearInstance : dtChequeClearInstance)
		} catch(IOException ioexception) {
			log.info("Exception in DtChequeClearController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in DtChequeClearController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: edit
	 * Description: Edit page of DtChequeClear
	 *
	 * @return : dtChequeClearInstance
	 */
    def edit(DtChequeClear dtChequeClearInstance) {
		respond dtChequeClearInstance
    }

	/**
	 * Method Name: update
	 * Description: Update the cheque
	 *
	 * @return : dtChequeClearInstance
	 */
    @Transactional
    def update(DtChequeClear dtChequeClearInstance) {
		
		try {
			if (dtChequeClearInstance == null) {
				notFound()
				return
			}
	
			if (dtChequeClearInstance.hasErrors()) {
				respond dtChequeClearInstance.errors, view:'edit'
				return
			}
	
			dtChequeClearInstance.save flush:true
	
			request.withFormat {
				form multipartForm {
					flash.message = message(code: 'default.updated.message', args: [message(code: 'DtChequeClear.label', default: 'DtChequeClear'), dtChequeClearInstance.id])
					redirect dtChequeClearInstance
				}
				'*'{ respond dtChequeClearInstance, [status: OK] }
			}
		} catch(IOException ioexception) {
			log.info("Exception in DtChequeClearController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in DtChequeClearController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}        
    }

	/**
	 * Method Name: update
	 * Description: Delete the cheque 
	 *
	 * @return : dtChequeClearInstance
	 */
    @Transactional
    def delete(DtChequeClear dtChequeClearInstance) {
		try{			
			if (dtChequeClearInstance == null) {
				notFound()
				return
			}
			dtChequeClearInstance.delete flush:true	
			request.withFormat {
				form multipartForm {
					flash.message = message(code: 'default.deleted.message', args: [message(code: 'DtChequeClear.label', default: 'DtChequeClear'), dtChequeClearInstance.id])
					redirect action:"index", method:"GET"
				}
				'*'{ render status: NO_CONTENT }
			}
		} catch(IOException ioexception) {
			log.info("Exception in DtChequeClearController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in DtChequeClearController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: getData
	 * Description: Get data of cheque clearance
	 *
	 * @return : dtChequeClearInstance,frmDtInst,toDtInst
	 */
	def getData(DtChequeClear dtChequeClearInstance){
		
		try{
			def fromDate = request.getAttribute(params?.fromDate)
			def todate = request.getAttribute(params?.toDate)
			
			def frmDtInst = DtChequeClear.findByDtChqDate(fromDate)
			def toDtInst = DtChequeClear.findByDtChqDate(todate)
			
			def srchData = dtChequeClearUtilsService.srchByDte(dtChequeClearInstance)
			redirect (action : "index" , frmDtInst : frmDtInst, toDtInst: toDtInst)
		} catch(IOException ioexception) {
			log.info("Exception in DtChequeClearController getData method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in DtChequeClearController getData method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
	}
	
	/**
	 * Method Name: search
	 * Description:Search cheque clearance
	 *
	 * @return : dtChequeClearInstance,frmDtInst,toDtInst
	 */
	def search(DtChequeClear dtChequeClearInstance){
		
		try {
			def srchData = dtChequeClearUtilsService.searchData(params)
			def searchQuery = params?.searchChqNo
			if(searchQuery){
				//def dtChqInstance = DtChequeClear.findAllByVcChqNoAndnuAmountBetween(params?.searchChqNo,params?.fromAmt,params?.fromAmt)
				def dtChqInstance = DtChequeClear.findAllByVcChqNoAndNuAmountBetween(params?.searchChqNo,params?.fromAmt,params?.fromAmt)
				//custMstUtilsService.searchCustMst(params)
				//redirect (action : "index" , custMstInstance : custMstInstance)
				render(view: "searchResult", model: [dtChqInstance: dtChqInstance])
				
			}else{		
				redirect(action: "index")
			}
		} catch(IOException ioexception) {
			log.info("Exception in DtChequeClearController search method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in DtChequeClearController search method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: notFound
	 * Description:NotFound cheque clearance
	 *
	 * @return : dtChequeClearInstance
	 */
    protected void notFound() {
		try {
			request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dtChequeClear.label', default: 'DtChequeClear'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		}  catch(IOException ioexception) {
			log.info("Exception in DtChequeClearController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in DtChequeClearController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
