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
import java.util.Calendar;
import java.util.Date;

/**
 * DtRateDetailUtilsService
 * Description
 *
 * @author (CTE).
 *
 * Contact () (optional)
 *
 * @version    0.1
 * @date        01/02/2016
 *
 * MOD HISTORY
 * DATE           USER      COMMENTS
 *  01/04/2016	  Izaz      Created File
 *  15/4/2016     Izaz		error msg displayed if no data for rate,saveRgSelection method added and file modified based on comments of our tl
 */

@Transactional
class DtRateDetailUtilsService {

	def dataSource
	Sql sql
	def query
	def result
	def str
	DateFormat formatter
	Calendar cal
	String dateStr1
	String formatedDate1
	Date date1
	def serviceMethod() {
	}
	
	/**
	 * Method Name: getRate
	 * Description: gets the rate data
	 *
	 * @return : rate data list
	 */
	def getRate(dtRateDetailInstance){
		def x = dtRateDetailInstance
		def rate,brSel,brSelNextId,result
		def retList = []	
		try{
		    rate = x?.rate
			brSel = x?.brSel
		    brSelNextId = x?.brSelNextId
			query =
			"""	
			SELECT b.br_code, a.br_name, b.vc_category, b.vc_purity, b.nu_rate_percentage, nu_additional_parameter,nu_additional_parameter1, nu_additional_parameter2, nu_additional_parameter3,
				   nu_additional_parameter4, nu_additional_parameter5,round((('$rate'+nvl(b.nu_additional_parameter,0))*b.nu_rate_percentage)/100,0)rate 
			FROM 
				   sales.br_mst_tab a, sales.mst_rate_parameter b WHERE a.vc_comp_code = '02' AND a.br_code = b.br_code 	
				   AND (('$brSel' = 'S'  and exists(select 'YES' from xxpos_rate_branch_selection c where c.br_code = b.br_code and c.detail_id = '$brSelNextId')) or '$brSel' = 'A')	
			order by to_number(b.br_code),3,4	
			"""
		    sql = new Sql(dataSource)
			result = sql.rows(query)
			if(result.isEmpty()){
				result = 1;
			}		
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  getRate method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result;
	}
	
	/**
	 * Method Name: saveDetails
	 * Description: saves the rate data
	 *
	 * @return : nothing
	 */
	def saveDetails(rateData,dtRateDetailInstance){
		def x = dtRateDetailInstance
		def category,purity,dtModDate,rtpara,nuRate,rtepercentage,brcde,creationDate
		String todayAsString		
		try{
			todayAsString = new SimpleDateFormat("MMddyyyy").format(new Date());
			for(def i=0; i<rateData.size(); i++){
				category = rateData[i]?.vc_category;
				purity = rateData[i]?.vc_purity;
				rtpara = rateData[i]?.NU_ADDITIONAL_PARAMETER;
				rtepercentage = rateData[i]?.NU_RATE_PERCENTAGE;
				nuRate = rateData[i]?.RATE;
				brcde = rateData[i]?.br_code;
				
				str = "INSERT INTO DT_RATE_DETAIL(VC_COMP_CODE,VC_CATEGORY,VC_PURITY,DT_MOD_DATE,NU_RATE_PARAMETER,NU_RATE,NU_RATE_PERCENTAGE,BR_CODE,CREATION_DATE) VALUES ('02','${category}','${purity}',TO_DATE('${todayAsString}','MM/DD/YYYY'),'$x.rateVal','${nuRate}','${rtepercentage}','${brcde}',TO_DATE('${todayAsString}','MM/DD/YYYY'))"
				
				sql = new Sql(dataSource)
				sql.execute str
			}
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  saveDetails method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getSavedDtRateDetails
	 * Description: gets the saved rate details data
	 *
	 * @return : saved rate details list
	 */
	def getSavedDtRateDetails() {
		try{
			query =
			"""
			select DRD.DT_MOD_DATE,DRD.VC_CATEGORY,BMT.BR_NAME,DRD.BR_CODE
			FROM DT_RATE_DETAIL DRD,BR_MST_TAB BMT
			WHERE DRD.BR_CODE = BMT.BR_CODE
			GROUP BY DRD.DT_MOD_DATE,DRD.VC_CATEGORY,BMT.BR_NAME,DRD.BR_CODE
			"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  getSavedDtRateDetails method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result;
	}
	
	/**
	 * Method Name: getBranchList
	 * Description: gets the branch data
	 *
	 * @return : branch list
	 */
	def getBranchList(){
		def retList = []
		try{
			str = "SELECT BR_CODE, BR_NAME FROM BR_MST_TAB"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  getBranchList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getRegionList
	 * Description: gets the region data
	 *
	 * @return : region list
	 */
	def getRegionList(){
		def retList = []
		try{
			str = "SELECT REGION_CODE, REGION_NAME FROM xxpos_region_master"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  getRegionList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getRegionBranchList
	 * Description: gets the region wise branch data
	 *
	 * @return : region list
	 */
	def getRegionBranchList(dtRateDetailInstance){
		def x = dtRateDetailInstance;
		try{
			str = "SELECT BMT.BR_CODE, BMT.BR_NAME FROM BR_MST_TAB BMT,XXPOS_REGION_BRANCH_RELATIONS XRBR WHERE BMT.BR_CODE=XRBR.BR_CODE AND XRBR.REGION_CODE='$x.rgcd'"
			log.info("getRegionBranchList qry :"+str)
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  getRegionList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	/**
	 * Method Name: getBrSelNextValue
	 * Description: gets the branch selection table's next detail id
	 *
	 * @return : next detail id of branch selection table
	 */
	def getBrSelNextValue(){
		def nextId,nextQry,nextVal
		try{
			nextQry = "select max(detail_id) from xxpos_rate_branch_selection"
			sql = new Sql(dataSource)
			nextVal = sql.rows(nextQry.toString())
			nextVal.each{
				nextId = it.values()[0]
			}
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  getBrSelNextValue method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return nextId+1
	}
	
	/**
	 * Method Name: saveBrSel
	 * Description: saves the checked branch data
	 *
	 * @return : integer value after saving branch data
	 */
	def saveBrSel(dtRateDetailInstance){
		def x = dtRateDetailInstance;
		def brData,brSelNextId,regQry,regId,brid,instBrSelQry
		def resultData = 0
		def brIds  = []
		String todayAsString
		Sql sql1,sql2
		try{
			brData = x.chBrData
			brSelNextId = x.brSelNextId
			todayAsString = new SimpleDateFormat("MMddyyyy").format(new Date());
			if (brData.contains(",")) {
				brIds = brData.split(",");
			} else {
				brIds = new String[1];
				brIds[0] = brData;
			}
			for (int i = 0; i < brIds.length; i++) {
				brid = Integer.parseInt(brIds[i]);
				regQry ="SELECT REGION_CODE FROM XXPOS_REGION_BRANCH_RELATIONS WHERE BR_CODE='$brid'"
				sql1 = new Sql(dataSource)
				result = sql1.rows(regQry.toString())
				if(result!=null && result!=''){
					result.each{
						regId = it.values()[0]
					}
					instBrSelQry = "insert into xxpos_rate_branch_selection (DETAIL_ID,REGION_CODE,BR_CODE,CREATION_DATE) VALUES('$brSelNextId','$regId','$brid',TO_DATE('${todayAsString}','MM/DD/YYYY'))"					
					sql2 = new Sql(dataSource)
					sql2.execute instBrSelQry
					resultData = 1
				}
			}
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  saveBrSel method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultData
	}
	
	/**
	 * Method Name: saveRgSel
	 * Description: saves the checked region data
	 *
	 * @return : integer value after saving region data
	 */
	def saveRgSel(dtRateDetailInstance){
		def x = dtRateDetailInstance;
		def rgData,brSelNextId,rgQry,brId,rgid,instBrSelQry,brIdResult
		String todayAsString
		def resultData = 0
		def rgIds  = []
		Sql sql1,sql2
		try{
			rgData = x.chRgData
			brSelNextId = x.brSelNextId
			todayAsString = new SimpleDateFormat("MMddyyyy").format(new Date());
			if (rgData.contains(",")) {
				rgIds = rgData.split(",");
			} else {
				rgIds = new String[1];
				rgIds[0] = rgData;
			}
			for (int i = 0; i < rgIds.length; i++) {
				rgid = Integer.parseInt(rgIds[i]);
				rgQry ="SELECT DISTINCT BR_CODE FROM XXPOS_REGION_BRANCH_RELATIONS WHERE REGION_CODE='$rgid'"
				println"rgQry :"+rgQry
				sql1 = new Sql(dataSource)
				brIdResult = sql1.rows(rgQry.toString())
				if(brIdResult!=null && brIdResult!=''){
					log.info("brIdResult LEN :"+brIdResult.size())
					println"brIdResult LEN :"+brIdResult.size()
					brIdResult.each{
						brId = it.values()[0]
						instBrSelQry = "insert into xxpos_rate_branch_selection (DETAIL_ID,REGION_CODE,BR_CODE,CREATION_DATE) VALUES('$brSelNextId','$rgid','$brId',TO_DATE('${todayAsString}','MM/DD/YYYY'))"						
						sql2 = new Sql(dataSource)
						sql2.execute instBrSelQry
						resultData = 1
					}
				}
			}
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService  saveRgSel method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultData
	}
	
	/**
	 * Method Name: getParBrData
	 * Description: gets the rate data
	 *
	 * @return : rate data list
	 */
	def getParBrData(dtRateDetailInstance){
		def x = dtRateDetailInstance
		def cat,brId
		def result
		def y = []
		try{
			cat = x?.cat
			brId = x?.brId
			dateStr1 = x?.date			
			y = dateStr1.split(" ");
			def dtval = y[0]
			query =
			"""
			select BMT.BR_NAME,DRD.VC_CATEGORY,DRD.VC_PURITY,DRD.NU_RATE_PERCENTAGE,DRD.NU_RATE as RATE
			FROM DT_RATE_DETAIL DRD,BR_MST_TAB BMT
			WHERE DRD.BR_CODE = BMT.BR_CODE
			AND DRD.BR_CODE= '$brId'
			AND DRD.VC_CATEGORY = '$cat'
			AND trunc(DRD.DT_MOD_DATE) = TO_DATE('$dtval','YYYY/MM/DD/')

			"""			
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in DtRateDetailUtilsService getParBrData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result;
	}
}
