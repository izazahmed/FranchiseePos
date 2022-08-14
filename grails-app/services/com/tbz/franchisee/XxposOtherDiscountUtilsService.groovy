package com.tbz.franchisee

import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class XxposOtherDiscountUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	def updateXxposOtherDiscount(xxposOtherDiscountInstance){
		
		def str =
			"""
				UPDATE 
					NEWWEBTBZ.XXPOS_OTHER_DISCOUNT
				SET
					NU_DISCOUNT=$xxposOtherDiscountInstance.nuDays
				WHERE
					BR_CODE='$xxposOtherDiscountInstance.brCode'

			"""
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
}
