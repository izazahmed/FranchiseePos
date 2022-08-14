package com.tbz.franchisee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MstDisController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		def criteria = MstDis.createCriteria()
		def result = criteria.list {
			isNotNull('vcDisCode')
		}
		[mstDisInstanceList : result]
		
     
    }

    def show(MstDis mstDisInstance) {
        respond mstDisInstance
    }

    def create() {
        respond new MstDis(params)
    }

    @Transactional
    def save(MstDis mstDisInstance) {
        if (mstDisInstance == null) {
            notFound()
            return
        }

        if (mstDisInstance.hasErrors()) {
            respond mstDisInstance.errors, view:'create'
            return
        }

        mstDisInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mstDis.label', default: 'MstDis'), mstDisInstance.id])
                redirect mstDisInstance
            }
            '*' { respond mstDisInstance, [status: CREATED] }
        }
    }

    def edit(MstDis mstDisInstance) {
        respond mstDisInstance
    }

    @Transactional
    def update(MstDis mstDisInstance) {
        if (mstDisInstance == null) {
            notFound()
            return
        }

        if (mstDisInstance.hasErrors()) {
            respond mstDisInstance.errors, view:'edit'
            return
        }

        mstDisInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MstDis.label', default: 'MstDis'), mstDisInstance.id])
                redirect mstDisInstance
            }
            '*'{ respond mstDisInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MstDis mstDisInstance) {

        if (mstDisInstance == null) {
            notFound()
            return
        }

        mstDisInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MstDis.label', default: 'MstDis'), mstDisInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstDis.label', default: 'MstDis'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
