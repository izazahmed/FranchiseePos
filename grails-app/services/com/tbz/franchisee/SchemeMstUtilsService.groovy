package com.tbz.franchisee

import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class SchemeMstUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: selectScheme
	 * Description: Fetch all scheme List
	 *
	 * @return : ResultSet of Customer table
	 */
	def selectScheme(schemeMstInstance) {
		def str =
			"""
				SELECT SCHEME_NAME
				FROM 
				NEWWEBTBZ.SCHEME_MST	
			"""
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str.toString())
		return result
	}
	
	def getExactCustomerData(schemeMstInstance) {
		
		def query =
			"""
				SELECT 
					FNAME,MNAME,LNAME
				FROM 
					NEWWEBTBZ.CUST_MST
				WHERE
					CUST_ID='$schemeMstInstance.dd'				
			"""
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
		
	}
}
