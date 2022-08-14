package com.tbz.franchisee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class XxtbzMiscCashAnalysisController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def xxtbzMiscCashAnalysisService
    def index(Integer max) {
    }
	
	def show(XxtbzMiscCashAnalysis xxtbzMiscCashAnalysisInstance) {
        respond xxtbzMiscCashAnalysisInstance
    }

    def create() {
        def bankList = xxtbzMiscCashAnalysisService.getBank()
		[bankList : bankList]
    }

    @Transactional
    def save(XxtbzMiscCashAnalysis xxtbzMiscCashAnalysisInstance) {
		def saveData = xxtbzMiscCashAnalysisService.saveCashAnalysis(params)
		redirect (action : "index" ,xxtbzMiscCashAnalysisInstance:xxtbzMiscCashAnalysisInstance)
    }

    def edit(XxtbzMiscCashAnalysis xxtbzMiscCashAnalysisInstance) {
        respond xxtbzMiscCashAnalysisInstance
    }

    @Transactional
    def update(XxtbzMiscCashAnalysis xxtbzMiscCashAnalysisInstance) {
        if (xxtbzMiscCashAnalysisInstance == null) {
            notFound()
            return
        }

        if (xxtbzMiscCashAnalysisInstance.hasErrors()) {
            respond xxtbzMiscCashAnalysisInstance.errors, view:'edit'
            return
        }

        xxtbzMiscCashAnalysisInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'XxtbzMiscCashAnalysis.label', default: 'XxtbzMiscCashAnalysis'), xxtbzMiscCashAnalysisInstance.id])
                redirect xxtbzMiscCashAnalysisInstance
            }
            '*'{ respond xxtbzMiscCashAnalysisInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(XxtbzMiscCashAnalysis xxtbzMiscCashAnalysisInstance) {

        if (xxtbzMiscCashAnalysisInstance == null) {
            notFound()
            return
        }

        xxtbzMiscCashAnalysisInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'XxtbzMiscCashAnalysis.label', default: 'XxtbzMiscCashAnalysis'), xxtbzMiscCashAnalysisInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'xxtbzMiscCashAnalysis.label', default: 'XxtbzMiscCashAnalysis'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
