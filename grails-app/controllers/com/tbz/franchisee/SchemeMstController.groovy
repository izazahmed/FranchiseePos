package com.tbz.franchisee



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional(readOnly = true)
class SchemeMstController {

	def custMstUtilsService
	def schemeMstUtilsService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		try{		
			def brNameInst
			if(session.roleId == '01') {
				//brNameInst = BrMstTab.findAllByBrName()
			}
			else {
	        	brNameInst = BrMstTab.findByBrCode(session.brCode)
			}
			def schemeList = schemeMstUtilsService.selectScheme()
			println "schemeList"+schemeList
			def customerList = custMstUtilsService.selectCustMst(params)
			[brNameInst : brNameInst?.brName, customerList:customerList,schemeList:schemeList] 
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController create method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController create method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

	def customerShow() {
		redirect (controller: "custMst" , action:"index" , params: [schemeName: params?.schemeName, brName:params?.brName]) 	
	}
	
	def schemeShow() {
		redirect (controller: "regDt" , action:"create" , params: [schemeName: params?.schemeName.toString(), brName:params?.brName.toString(), customerId:params?.customerId.toString()])
	}
	
    def show(SchemeMst schemeMstInstance) {
        respond schemeMstInstance
    }

    def create() {
        respond new SchemeMst(params)
    }

    @Transactional
    def save(SchemeMst schemeMstInstance) {
        if (schemeMstInstance == null) {
            notFound()
            return
        }

        if (schemeMstInstance.hasErrors()) {
            respond schemeMstInstance.errors, view:'create'
            return
        }

        schemeMstInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'schemeMst.label', default: 'SchemeMst'), schemeMstInstance.id])
                redirect schemeMstInstance
            }
            '*' { respond schemeMstInstance, [status: CREATED] }
        }
    }

    def edit(SchemeMst schemeMstInstance) {
        respond schemeMstInstance
    }

    @Transactional
    def update(SchemeMst schemeMstInstance) {
        if (schemeMstInstance == null) {
            notFound()
            return
        }

        if (schemeMstInstance.hasErrors()) {
            respond schemeMstInstance.errors, view:'edit'
            return
        }

        schemeMstInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SchemeMst.label', default: 'SchemeMst'), schemeMstInstance.id])
                redirect schemeMstInstance
            }
            '*'{ respond schemeMstInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SchemeMst schemeMstInstance) {

        if (schemeMstInstance == null) {
            notFound()
            return
        }

        schemeMstInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SchemeMst.label', default: 'SchemeMst'), schemeMstInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
	def getActualCustomer() {
		try {
			def schemeInstance = schemeMstUtilsService.getExactCustomerData(params)
			JSONObject jsonObj = new JSONObject()
			jsonObj.putAt("FNAME", schemeInstance?.FNAME)
			jsonObj.putAt("MNAME", schemeInstance?.MNAME)
			jsonObj.putAt("LNAME", schemeInstance?.LNAME)
			
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

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'schemeMst.label', default: 'SchemeMst'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
