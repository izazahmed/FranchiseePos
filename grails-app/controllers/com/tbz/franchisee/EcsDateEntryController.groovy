package com.tbz.franchisee



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional(readOnly = true)
class EcsDateEntryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def ecsDateEntryUtilsService
    def index() {
		def customerList = ecsDateEntryUtilsService.getCustomerList()
		[customerList : customerList]
    }
	
	def getExactCustomerData() {
		try {
			def exactCustomerInstance = ecsDateEntryUtilsService.getExactCustomerList(params)
			def countVal = ecsDateEntryUtilsService.generateCountVal(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("VC_CUST_ID", exactCustomerInstance?.VC_CUST_ID)
			jsonObj.putAt("NU_INSTALL_FOR", exactCustomerInstance?.NU_INSTALL_FOR)
			
			jsonObj.putAt("VC_FIELD2", exactCustomerInstance?.VC_FIELD2)
			jsonObj.putAt("DT_FIELD2", exactCustomerInstance?.DT_FIELD2)
			jsonObj.putAt("NU_AMOUNT", exactCustomerInstance?.NU_AMOUNT)
			jsonObj.putAt("CH_STAGE", exactCustomerInstance?.CH_STAGE)
			jsonObj.putAt("CNT", countVal?.CNT)
			render jsonObj as JSON
			
			return
		} catch(IOException ioexception) {
			log.info("Exception in EcsDateEntryController getExactCustomerData method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in EcsDateEntryController getExactCustomerData method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

    def show(DtEcsRef dtEcsRefInstance) {
        respond dtEcsRefInstance
    }

    def create() {
        respond new DtEcsRef(params)
    }

    @Transactional
    def save(DtEcsRef dtEcsRefInstance) {
        if (dtEcsRefInstance == null) {
            notFound()
            return
        }

        if (dtEcsRefInstance.hasErrors()) {
            respond dtEcsRefInstance.errors, view:'create'
            return
        }

        dtEcsRefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dtEcsRef.label', default: 'DtEcsRef'), dtEcsRefInstance.id])
                redirect dtEcsRefInstance
            }
            '*' { respond dtEcsRefInstance, [status: CREATED] }
        }
    }

    def edit(DtEcsRef dtEcsRefInstance) {
        respond dtEcsRefInstance
    }

    @Transactional
    def update(DtEcsRef dtEcsRefInstance) {
        if (dtEcsRefInstance == null) {
            notFound()
            return
        }

        if (dtEcsRefInstance.hasErrors()) {
            respond dtEcsRefInstance.errors, view:'edit'
            return
        }

        dtEcsRefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DtEcsRef.label', default: 'DtEcsRef'), dtEcsRefInstance.id])
                redirect dtEcsRefInstance
            }
            '*'{ respond dtEcsRefInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DtEcsRef dtEcsRefInstance) {

        if (dtEcsRefInstance == null) {
            notFound()
            return
        }

        dtEcsRefInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DtEcsRef.label', default: 'DtEcsRef'), dtEcsRefInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dtEcsRef.label', default: 'DtEcsRef'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
