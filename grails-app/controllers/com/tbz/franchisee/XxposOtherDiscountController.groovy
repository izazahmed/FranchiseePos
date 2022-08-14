package com.tbz.franchisee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class XxposOtherDiscountController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def xxposOtherDiscountUtilsService
    def index(Integer max) {
        def criteria = BrMstTab.createCriteria()
		def branchList = criteria.list {}
		[branchList : branchList]
    }

    def show(XxposOtherDiscount xxposOtherDiscountInstance) {
        respond xxposOtherDiscountInstance
    }

    def create() {
        respond new XxposOtherDiscount(params)
    }

    @Transactional
    def save(XxposOtherDiscount xxposOtherDiscountInstance) {
        if (xxposOtherDiscountInstance == null) {
            notFound()
            return
        }

        if (xxposOtherDiscountInstance.hasErrors()) {
            respond xxposOtherDiscountInstance.errors, view:'create'
            return
        }

        xxposOtherDiscountInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'xxposOtherDiscount.label', default: 'XxposOtherDiscount'), xxposOtherDiscountInstance.id])
                redirect xxposOtherDiscountInstance
            }
            '*' { respond xxposOtherDiscountInstance, [status: CREATED] }
        }
    }

    def edit(XxposOtherDiscount xxposOtherDiscountInstance) {
        respond xxposOtherDiscountInstance
    }

    @Transactional
    def update(XxposOtherDiscount xxposOtherDiscountInstance) {
       try {
			def xxposOtherDiscount = xxposOtherDiscountUtilsService.updateXxposOtherDiscount(params)
			//redirecting to the index page
			redirect (action : "index" , xxposOtherDiscountInstance : xxposOtherDiscountInstance)
		} catch(IOException ioexception) {
			log.info("Exception in CustMstController update method "+ioexception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [ioexception.getMessage()])
		} catch(Exception exception) {
			log.info("Exception in CustMstController update method "+exception.getMessage())
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
    }

    @Transactional
    def delete(XxposOtherDiscount xxposOtherDiscountInstance) {

        if (xxposOtherDiscountInstance == null) {
            notFound()
            return
        }

        xxposOtherDiscountInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'XxposOtherDiscount.label', default: 'XxposOtherDiscount'), xxposOtherDiscountInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'xxposOtherDiscount.label', default: 'XxposOtherDiscount'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
