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
import grails.converters.JSON
import grails.transaction.Transactional
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
class MtlItemLocationsController {

	def mtlItemLocationsUtilsService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MtlItemLocations.list(params), model:[mtlItemLocationsInstanceCount: MtlItemLocations.count()]
    }

    def show(MtlItemLocations mtlItemLocationsInstance) {
        respond mtlItemLocationsInstance
    }
	
	/**
	 * Method Name: getActualLocationsData
	 * Description: get Actual location Data for view popup this method
	 *
	 * @return : JSONobject
	 */
	def getActualLocationsData(){
		try {
			def locationInstance = mtlItemLocationsUtilsService.getExactLocationData(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("INVENTORY_LOCATION_ID", locationInstance?.INVENTORY_LOCATION_ID)
			jsonObj.putAt("SUBINVENTORY_CODE", locationInstance?.SUBINVENTORY_CODE)
			jsonObj.putAt("SEGMENT1", locationInstance?.SEGMENT1)
			jsonObj.putAt("DESCRIPTION", locationInstance?.DESCRIPTION)
			
			render jsonObj as JSON
			
			return
		} catch(IOException ioexception) {
			log.info("Exception in MtlItemLocationsController getActualSchemData method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in MtlItemLocationsController getActualSchemData method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: create
	 * Description: This method will create location master
	 *
	 * @return : JSONobject
	 */
    def create() {
		
        def locationList = ''
		locationList = mtlItemLocationsUtilsService.getAllLocationsList()
					
	    [locationList:locationList]
    }

	/**
	 * Method Name: save
	 * Description: This method will save location master
	 *
	 * @return : JSONobject
	 */
    @Transactional
    def save(MtlItemLocations mtlItemLocationsInstance) {
        try {
			if (!params?.segment1) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'mtlItemLocations.label', default: 'segment1'), params.segment1])
				redirect(action: "create")
				return
			}
			else if(params?.editVar=='TRUE'){
				def locationMst = mtlItemLocationsUtilsService.updateLocationData(params)
				redirect (action : "create" , mtlItemLocationsInstance : mtlItemLocationsInstance)				
			}else{
				def schemeInstance = mtlItemLocationsUtilsService.saveLocation(mtlItemLocationsInstance)
				redirect action:"create"
			}
			
		}  catch(IOException ioexception) {
			log.info("Exception in MtlItemLocationsController save method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
			redirect (action : "create" , mtlItemLocationsInstance : mtlItemLocationsInstance)
		} catch(Exception exception) {
			log.info("Exception in MtlItemLocationsController save method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
			redirect (action : "create" , mtlItemLocationsInstance : mtlItemLocationsInstance)
		}
    }

    def edit(MtlItemLocations mtlItemLocationsInstance) {
        
    }

    @Transactional
    def update(MtlItemLocations mtlItemLocationsInstance) {
        
    }

    @Transactional
    def delete(MtlItemLocations mtlItemLocationsInstance) {
       
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mtlItemLocations.label', default: 'MtlItemLocations'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
