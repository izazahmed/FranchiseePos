package com.tbz.franchisee



import grails.test.mixin.*
import spock.lang.*

@TestFor(MstCompanyController)
@Mock(MstCompany)
class MstCompanyControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.mstCompanyInstanceList
            model.mstCompanyInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.mstCompanyInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def mstCompany = new MstCompany()
            mstCompany.validate()
            controller.save(mstCompany)

        then:"The create view is rendered again with the correct model"
            model.mstCompanyInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            mstCompany = new MstCompany(params)

            controller.save(mstCompany)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/mstCompany/show/1'
            controller.flash.message != null
            MstCompany.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def mstCompany = new MstCompany(params)
            controller.show(mstCompany)

        then:"A model is populated containing the domain instance"
            model.mstCompanyInstance == mstCompany
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def mstCompany = new MstCompany(params)
            controller.edit(mstCompany)

        then:"A model is populated containing the domain instance"
            model.mstCompanyInstance == mstCompany
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/mstCompany/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def mstCompany = new MstCompany()
            mstCompany.validate()
            controller.update(mstCompany)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.mstCompanyInstance == mstCompany

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            mstCompany = new MstCompany(params).save(flush: true)
            controller.update(mstCompany)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/mstCompany/show/$mstCompany.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/mstCompany/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def mstCompany = new MstCompany(params).save(flush: true)

        then:"It exists"
            MstCompany.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(mstCompany)

        then:"The instance is deleted"
            MstCompany.count() == 0
            response.redirectedUrl == '/mstCompany/index'
            flash.message != null
    }
}
