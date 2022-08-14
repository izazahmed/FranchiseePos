package com.tbz.franchisee

/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */

import grails.transaction.Transactional
import groovy.sql.Sql;

/**
 * MstCompanyService
 * Description
 *
 * @author (CTE).
 *
 * Contact () (optional)
 *
 * @version    0.1
 * @date        09/05/2016
 *
 * MOD HISTORY
 * DATE           USER      COMMENTS
 *  09/05/2016	  Izaz      Created File
 *
 */

@Transactional
class MstCompanyService {
	def dataSource
	Sql sql
	def str
	def query
	def result
	
    def serviceMethod() {

    }
	
	/**
	 * Method Name: saveMstCompany
	 * Description: saves the company
	 *
	 * @return : nothing
	 */
	def saveMstCompany(compName,brCode) {
		def nextQry,nextVal,compCode,compCodeVal
		int compCodeInt
		try{			
			nextQry = "select case when length(max(to_number(vc_comp_code))+1)<2 then LPAD(to_char(max(to_number(vc_comp_code)))+1, 2, '0') else to_char(max(to_number(vc_comp_code))+1) end as vc_comp_code from mst_company"
			sql = new Sql(dataSource)
			nextVal = sql.rows(nextQry.toString())
			nextVal.each{
				compCode = it.values()[0]				
			}						
			str = "insert into mst_company (vc_comp_code,vc_company_name,br_code) VALUES ('$compCode','$compName','$brCode')"
			Sql sql1 = new Sql(dataSource)
			sql1.execute str
		}catch(Exception exception){
			log.info("Exception in MstCompanyService saveMstCompany method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getCompanyList
	 * Description: used to return created companies
	 * @return : list of result
	 */
	def getCompanyList() {
		try{
			query =
				"""			
				select vc_comp_code,vc_company_name from mst_company
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in MstCompanyService getCompanyList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
}
