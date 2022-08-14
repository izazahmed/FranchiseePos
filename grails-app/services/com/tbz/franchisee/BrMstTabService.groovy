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

import com.tbz.franchisee.MstCompany;

/**
 * BrMstTabService
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
class BrMstTabService {
	def dataSource
	Sql sql
	def str
	def query
	def result
	
    def serviceMethod() {

    }
	
	/**
	 * Method Name: getCompaniesList
	 * Description: used to get companies list
	 *
	 * @return : complist
	 */
	def getCompaniesList(){
		def detaResult
		def complist = [:]
		try{
			str = "select distinct vc_comp_code,vc_company_name from mst_company"
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				complist.put(it.values()[0],it.values()[1])
			}
		}catch(Exception exception){
			log.info("Exception in BrMstTabService getCompaniesList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return complist
	}
	
	/**
	 * Method Name: saveBrMstBranch
	 * Description: saves the branch
	 *
	 * @return : nothing
	 */
	def saveBrMstBranch(brMstTabInstance) {
		def x=brMstTabInstance		
		def nextQry,nextVal,brCode,brCodeVal,compCode,finalCompCode
		int brCodeInt
		try{			
			def str1 = MstCompany.findAllByVcCompanyName(x.compName)
			compCode = str1?.vcCompCode
			finalCompCode = compCode[0]
			
			nextQry = "select case when length(max(to_number(br_code))+1)<2 then LPAD(to_char(max(to_number(br_code)))+1, 2, '0') else to_char(max(to_number(br_code))+1) end as br_code from br_mst_tab"
			sql = new Sql(dataSource)
			nextVal = sql.rows(nextQry.toString())
			nextVal.each{
				brCode = it.values()[0]
			}
			query = """
				insert into br_mst_tab (BR_CODE,EBS_ORG_ID,BR_NAME,VC_COMP_CODE,VC_CITY,VC_STATE,VC_COUNTRY,VC_TELEPHONE1,CH_ACTIVE,VC_IEC_NO,VC_ADDRESS7,CH_STAT_FLAG,EBS_ORG_MAP,FULL_ADDRESS)
				VALUES ('$brCode','$brCode','$x.branchName','$finalCompCode','$x.city','$x.state','$x.country','$x.telephone','$x.chActive','$x.iecNo','$x.add','$x.statFlag','$x.ebsOrgMap','$x.fullAdd')
			"""
			Sql sql1 = new Sql(dataSource)
			sql1.execute query
		}catch(Exception exception){
			log.info("Exception in BrMstTabService saveBrMstBranch method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getBranchList
	 * Description: used to return created branches
	 * @return : list of result
	 */
	def getBranchList() {
		try{
			query =
				"""
				select br_code,BR_NAME from br_mst_tab
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
