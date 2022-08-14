package com.tbz.franchisee

import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class XxtbzMiscCashAnalysisService {

	def dataSource
    def serviceMethod() {

    }
	
	def getBank(){
		def bankList = []
		def str =
		"""
			select distinct bank_name, bank_account_id
			from apps.tbz_pos_recipt_methods_v
			where payment_type_code = 'CHECK'
			and end_date is null
			and bank_name is not null
		"""
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		
		return result
	}
	
	def saveCashAnalysis(cashInstance) {
		Sql sql
		def str = "INSERT INTO NEWWEBTBZ.XXTBZ_MISC_CASH_ANALYSIS(ACCOUNT_DESCRIPTION, RUN_DATE,AMOUNT, VC_FIELD1,VC_FIELD2) VALUES ('$cashInstance.accountDesc','$cashInstance.runDate', '$cashInstance.amount','$cashInstance.bankName','$cashInstance.naration')"
		sql = new Sql(dataSource)
		sql.execute str
		
	}
}
