package com.tbz.franchisee



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional(readOnly = true)
class SaleRetExpController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def saleRetExpUtilsService
    def index(Integer max) {
		
		def criteria = BrMstTab.createCriteria()
		def branchList = criteria.list {}
		[branchList : branchList]
    }

    def show(SaleRetExp saleRetExpInstance) {
        respond saleRetExpInstance
    }

	def getBranchData(){
		def criteria = BrMstTab.createCriteria()
		def branchList = criteria.list {
			eq("brCode",params?.customerID)
		}
		
		JSONObject jsonObj = new JSONObject()
		jsonObj.putAt("brCode", branchList?.brCode)
		jsonObj.putAt("brName", branchList?.brName)
		render jsonObj as JSON
		return
		
	}
	
    def create() {
        respond new SaleRetExp(params)
    }

    @Transactional
    def save(SaleRetExp saleRetExpInstance) {
        if (saleRetExpInstance == null) {
            notFound()
            return
        }

        if (saleRetExpInstance.hasErrors()) {
            respond saleRetExpInstance.errors, view:'create'
            return
        }

        saleRetExpInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'saleRetExp.label', default: 'SaleRetExp'), saleRetExpInstance.id])
                redirect saleRetExpInstance
            }
            '*' { respond saleRetExpInstance, [status: CREATED] }
        }
    }

    def edit(SaleRetExp saleRetExpInstance) {
         
    }

    @Transactional
    def update(SaleRetExp saleRetExpInstance) {
		try {
			def saleRetExp = saleRetExpUtilsService.updateSaleRetExp(params)
			//redirecting to the index page
			redirect (action : "index" , saleRetExpInstance : saleRetExpInstance)
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

    @Transactional
    def delete(SaleRetExp saleRetExpInstance) {

        if (saleRetExpInstance == null) {
            notFound()
            return
        }

        saleRetExpInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SaleRetExp.label', default: 'SaleRetExp'), saleRetExpInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'saleRetExp.label', default: 'SaleRetExp'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
