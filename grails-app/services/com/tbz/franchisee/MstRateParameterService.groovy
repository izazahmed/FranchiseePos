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
import groovy.sql.Sql

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * MstRateParameterService
 * Description
 *
 * @author (CTE).
 *
 * Contact () (optional)
 *
 * @version    0.1
 * @date        01/03/2016
 *
 * MOD HISTORY
 * DATE           USER      COMMENTS
 *  01/03/2016	  Izaz      Created File
 *  
 */

@Transactional
class MstRateParameterService {
	def dataSource
	Sql sql
	def resultArr = []
	def query
	def result
	def flexVal = ''
	def br
	def finalBr
	def str1
	def str
	
	def serviceMethod() {
	}
	
	/**
	 * Method Name: getCategoryList
	 * Description: gets the category list
	 *
	 * @return : category list
	 */
	def getCategoryList() {
		
		try{
			query =
					"""
					select FLEX_VALUE,DESCRIPTION FROM fnd_flex_values_vl WHERE FLEX_VALUE_SET_ID=1014211 ORDER BY FLEX_VALUE
					"""	
			sql = new Sql(dataSource)	
			result = sql.rows(query)			
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  getCategoryList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result;
	}
	
	/**
	 * Method Name: getPurity
	 * Description: gets the purity list
	 *
	 * @return : purity list
	 */
	def getPurity(fndFlexValuesVlInstance) {
		def x = fndFlexValuesVlInstance
		def query1,result1
		try{
			query1 = "select FLEX_VALUE FROM fnd_flex_values_vl WHERE DESCRIPTION='$x.desVal'"
			Sql sql1 = new Sql(dataSource)	
			result1 = sql1.rows(query1)
			result1.each {
				flexVal= (it.values()[0])
			}
			query =
					"""
					SELECT DISTINCT SUBSTR(PURITY,1,2)PURITY FROM xxtbz_item_master WHERE ITEM_CATEGORY='$flexVal'
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  getPurity method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: validatePurity
	 * Description: validate the purity data
	 *
	 * @return : countVal
	 */
	def validatePurity(mstRateParameterInstance) {
		def x = mstRateParameterInstance
		def countVal = ''
		try{
			str1 = BrMstTab.findAllByBrName(x.brName)
			br = str1?.brCode
			finalBr = br[0]
			str = "select count(vc_purity) from mst_rate_parameter where vc_purity=$x.pur1 and br_code=$finalBr "
			sql = new Sql(dataSource)
			result = sql.rows(str)
			result.each {
				countVal= (it.values()[0])
			}
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  validatePurity method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return countVal
	}
	
	/**
	 * Method Name: saveMstRateParameter
	 * Description: saves the rate parameter value
	 *
	 * @return : nothing
	 */
	def saveMstRateParameter(mstRateParameterInstance) {
		def x = mstRateParameterInstance
		try{
			str1 = BrMstTab.findAllByBrName(x.brName)
			br = str1?.brCode
			finalBr = br[0]
			str = "insert into mst_rate_parameter (vc_category,vc_purity,nu_rate_percentage,nu_additional_parameter,br_code) VALUES ('$x.description1', '$x.pur1', '$x.nuRatePercentage1', '$x.nuAdditionalParameter1', '$finalBr')"			
			sql = new Sql(dataSource)
			sql.execute str
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  saveMstRateParameter method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: validateUpdatePurity
	 * Description: at updating rate parameter it validate the purity
	 * present this method was not using
	 * @return : countVal
	 */
	def validateUpdatePurity(mstRateParameterInstance) {
		def x = mstRateParameterInstance
		def countVal = ''
		try{			
			str1 = BrMstTab.findAllByBrName(x.brName)
			br = str1?.brCode
			finalBr = br[0]
			str = "select count(vc_purity) from mst_rate_parameter where vc_purity=$x.pur and br_code=$finalBr "
			sql = new Sql(dataSource)
			result = sql.rows(str)
			result.each {
				countVal= (it.values()[0])
			}
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  validateUpdatePurity method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return countVal
	}
	
	/**
	 * Method Name: mstRateParameterListVal
	 * Description: used to return created rate parameter list value
	 * present this method was not using
	 * @return : list of result
	 */
	def mstRateParameterListVal() {
		try{
			query =
				"""
					select rownum,bmt.br_code,bmt.br_name,mrp.vc_category,mrp.vc_purity,mrp.nu_rate_percentage,mrp.nu_additional_parameter
					from br_mst_tab bmt,mst_rate_parameter mrp
					where bmt.br_code = mrp.br_code	
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  mstRateParameterListVal method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getTotalRowCount
	 * Description: used to get total row count(no primary key thats y used)
	 * present this method was not using
	 * @return : count
	 */
	def getTotalRowCount() {
		def listCount = ''
		try{
			query =
					"""
						SELECT count(*) FROM         
						(select rownum,bmt.br_code,bmt.br_name,mrp.vc_category,mrp.vc_purity,mrp.nu_rate_percentage,mrp.nu_additional_parameter
						from br_mst_tab bmt,mst_rate_parameter mrp
						where bmt.br_code = mrp.br_code)	
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
			result.each {
				listCount= (it.values()[0])
			}			
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  getTotalRowCount method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return listCount
	}
	
	/**
	 * Method Name: updateData
	 * Description: used to update rate parameter value
	 * present this method was not using
	 * @return : nothing
	 */
	def updateData(mstRateParameterInstance){
		def x = mstRateParameterInstance
		try{				
			str1 = BrMstTab.findAllByBrName(x.brName)
			br = str1?.brCode
			finalBr = br[0]	
			str = "update mst_rate_parameter set nu_rate_percentage='$x.nuRatePercentage',nu_additional_parameter='$x.nuAdditionalParameter' WHERE br_code='$finalBr' and vc_category='$x.description' and vc_purity='$x.pur'"	
			sql = new Sql(dataSource)	
			sql.execute str
		}catch(Exception exception){
			log.info("Exception in MstRateParameterService  updateData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
}
