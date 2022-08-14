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
 * MstRegionController
 * Controller for Region Master
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

@Transactional(readOnly = true)
class MstRegionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def mstRegionUtilsService
	
	/**
	 * Method Name: index
	 * Description: Display Index page using this method
	 *
	 * @return : CityList,StateList,CountryList
	 */
    def index(Integer max) {
		try {
			params.max = Math.min(max ?: 10, 100)
			def brCode = session.brCode
			def ctyLst = mstRegionUtilsService.getCity(brCode)
			def stateLst = mstRegionUtilsService.getState(brCode)
			def countryLst = mstRegionUtilsService.getCountry(brCode)
			def mstRegionLst = mstRegionUtilsService.getMstRegionList(brCode)
			//respond MstRegion.list(params), model:[mstRegionInstanceCount: MstRegion.count(),ctyLst: ctyLst]
			[ctyLst:ctyLst, stateLst:stateLst,countryLst:countryLst,brCode:brCode,mstRegionLst:mstRegionLst]
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController view method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController view method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: saveCityName
	 * Description: This method will save City Name in the table
	 * @params : params
	 * @return : mstRegionInstance
	 */
	def saveCityName(MstRegion mstRegionInstance){
		try {
			def city = mstRegionUtilsService.saveCity(params)
			redirect (action : "index" ,mstRegionInstance:mstRegionInstance)
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController saveCityName method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController saveCityName method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: show
	 * Description: This method will show region master related information
	 * @params : params
	 * @return : mstRegionInstance
	 */
    def show(MstRegion mstRegionInstance) {
		try {
			respond mstRegionInstance
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController show method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController show method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: create
	 * Description: This method will show City Name in the table
	 * @params : params
	 * @return : mstRegionInstance
	 */
    def create() {
		try {
			respond new MstRegion(params)
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController create method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController create method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	
	/**
	 * Method Name: save
	 * Description: This method will save City,state and country name
	 * @params : params
	 * @return : mstRegionInstance
	 */
    @Transactional
    def save(MstRegion mstRegionInstance) {
		try {
			def x = mstRegionInstance
	        def city = params?.cityName 
			def state = params?.stateName
			def country = params?.countryName
			def saveData = mstRegionUtilsService.saveMstRegion(params)
			redirect (action : "index" ,mstRegionInstance:mstRegionInstance)
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: edit
	 * Description: This method will edit City,state and country name
	 * @params : params
	 * @return : mstRegionInstance
	 */
    def edit(MstRegion mstRegionInstance) {
		try {
			respond mstRegionInstance
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController edit method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController edit method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: update
	 * Description: This method will update City,state and country name
	 * @params : params
	 * @return : mstRegionInstance
	 */
    @Transactional
    def update(MstRegion mstRegionInstance) {
		try {
	        if (mstRegionInstance == null) {
	            notFound()
	            return
	        }
	
	        if (mstRegionInstance.hasErrors()) {
	            respond mstRegionInstance.errors, view:'edit'
	            return
	        }
	
	        mstRegionInstance.save flush:true
	
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.updated.message', args: [message(code: 'MstRegion.label', default: 'MstRegion'), mstRegionInstance.id])
	                redirect mstRegionInstance
	            }
	            '*'{ respond mstRegionInstance, [status: OK] }
	        }
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	/**
	 * Method Name: delete
	 * Description: This method will delete City,state and country name
	 * @params : params
	 * @return : mstRegionInstance
	 */
    @Transactional
    def delete(MstRegion mstRegionInstance) {
		try {
			if (mstRegionInstance == null) {
				notFound()
				return
			}
			
			mstRegionInstance.delete flush:true
			
			request.withFormat {
					form multipartForm {
						flash.message = message(code: 'default.deleted.message', args: [message(code: 'MstRegion.label', default: 'MstRegion'), mstRegionInstance.id])
								redirect action:"index", method:"GET"
					}
					'*'{ render status: NO_CONTENT }
				}
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController delete method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController delete method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
	
	/**
	 * Method Name: getMasterData
	 * Description: Retrieve data from database for edit
	 * @params : params
	 * @return : mstRegionInstance
	 */
	def getMasterData() {
		println"MST REGION Controller Start....."
		try {
			def masterList = mstRegionUtilsService.getCtyStCtrData()
			
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController edit method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController edit method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		
	}
	
	def saveMaster(){
		try {
			redirect (action : "index")
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	def deleteMaster(){
		try {
			def saveMst = mstRegionUtilsService.deleteMstRegion(params)
			redirect (action : "index")
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: notFound
	 * Description: This method will check particular region record is present or not
	 * @params : params
	 * @return : message whether region record present or not
	 */
    protected void notFound() {
		try {
	        request.withFormat {
	            form multipartForm {
	                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstRegion.label', default: 'MstRegion'), params.id])
	                redirect action: "index", method: "GET"
	            }
	            '*'{ render status: NOT_FOUND }
	        }
		} catch(IOException ioexception) {
			log.info("Exception in MstRegionController notFound method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MstRegionController notFound method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }
}
