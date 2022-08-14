package com.tbz.franchisee

import grails.transaction.Transactional
import groovy.sql.Sql
import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional
class SaleRetExpUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	def updateSaleRetExp(saleRetExpInstance){
		def x = saleRetExpInstance
		
		def str =
			"""
				UPDATE 
					NEWWEBTBZ.SALE_RET_EXP
				SET
					NU_DAYS='$saleRetExpInstance.nuDays'
				WHERE
					BR_CODE='$saleRetExpInstance.brCode'
			"""
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
}
