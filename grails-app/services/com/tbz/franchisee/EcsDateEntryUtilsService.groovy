package com.tbz.franchisee

import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class EcsDateEntryUtilsService {
	def dataSource
	Sql sql
	def result
    def serviceMethod() {

    }
	
	/**
	 * Method Name: getBranchWiseSummaryReport
	 * Description: gets branchwiseSummary list
	 *
	 * @return : list of branch wise summary
	 */
	def getCustomerList(){
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					select cust_id,fname||' '||mname||' '||lname cust_name
					from sales.hd_ecs_det a, sales.cust_mst b
					where a.vc_cust_id = b.cust_id
					and a.br_code = substr(b.cust_id,1,instr(b.cust_id,'-')-2)
					and a.br_code = decode('01','01',a.br_code,decode('82','98',a.br_code,'82'))
					and a.ch_active = 'Y'
					and not exists ( select * from sales.in_dt where vc_rcpt_settle = 'Y' and cust_id = a.vc_cust_id )
					order by a.br_code
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getGiftVoucherActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	
	/**
	 * Method Name: getBranchWiseSummaryReport
	 * Description: gets branchwiseSummary list
	 *
	 * @return : list of branch wise summary
	 */
	def getExactCustomerList(customerInstance){
		def query =
			"""
				SELECT 
					NU_INSTALL_FOR,VC_FIELD2,DT_FIELD2,VC_CUST_ID,NU_AMOUNT,CH_STAGE
				FROM 
					NEWWEBTBZ.DT_ECS_REF
				WHERE
					VC_CUST_ID='$customerInstance.dd'				
			"""
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	def generateCountVal(customerInstance){
		def query =
			"""
				SELECT 
					COUNT(*) as CNT
				FROM 
					NEWWEBTBZ.DT_ECS_REF
				WHERE
					VC_CUST_ID='$customerInstance.dd'				
			"""
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
}
