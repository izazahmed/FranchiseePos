package com.tbz.franchisee
/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */
/**
 * DtChequeClearUtilsService
 * Service for Cheque Clear clearance related information
 *
 * @author (CTE).
 *
 * Contact ("") (optional)
 *
 * @version    0.1
 * @date        01/02/2016
 *
 * MOD HISTORY
 * DATE           	USER				COMMENTS
 * 01/02/2016		Sachin				Created File
 * 15/04/2016	  	Sachin				Added Logger and Exception handling
 */
import grails.transaction.Transactional
import groovy.sql.Sql
import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional
class DtChequeClearUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: saveDtChequeClear
	 * Description: Save Scheme details information into Database
	 * @params :  params
	 * @return : ResultSet of Saved Scheme
	 */
	def saveDtChequeClear(dtChequeClearInstance) {
		def x = dtChequeClearInstance
		String voucherDate = "$x.dtVoucherDate"
		String clearDate = "$x.dtClearDate"
		String chqDate = "$x.dtChqDate"
		
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		
		Date date1 = (Date)formatter.parse(voucherDate)
		Date date2 = (Date)formatter.parse(clearDate)
		Date date3 = (Date)formatter.parse(chqDate)
		
		Calendar cal = Calendar.getInstance()
		cal.setTime(date1)
		String voucherFormatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		cal.setTime(date2)
		String clearFormatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		cal.setTime(date3)
		String chqFormatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		def flag = "$x.chAuthFlag"
			
		def str1 =
			"""
				INSERT 
					INTO 
				WEBTBZ.DT_CHEQUE_CLEAR
				(
					VC_CUST_ID,VC_CUST_FNAME, VC_CUST_MNAME, VC_CUST_LNAME,VC_VOUCHER_NO,DT_VOUCHER_DATE,VC_OLD_PAYMENT_TYPE,DT_CLEAR_DATE,VC_CHQ_NO,DT_CHQ_DATE,NU_AMOUNT,VC_BANK_NAME
				)
				VALUES 
				(
					'$x.vcCustId', '$x.vcCustFname','$x.vcCustMname','$x.vcCustLname','$x.vcVoucherNo',TO_DATE('$voucherFormatedDate','MM/DD/YYYY'),'$x.vcOldPaymentType',TO_DATE('$clearFormatedDate','MM/DD/YYYY'),'$x.vcChqNo',TO_DATE('$chqFormatedDate','MM/DD/YYYY'),$x.nuAmount,'$x.vcBankName'
				)
			""" 											
		Sql sql = new Sql(dataSource)
		sql.execute str1
	}
	
	/**
	 * Method Name: srchByDte
	 * Description: Search Scheme according to date from Database
	 * 
	 * @return : ResultSet of scheme according to Date
	 */
	def srchByDte(dtChequeClearInstance){
		
		def x = dtChequeClearInstance
		def stDte = "$x.dtChqDate"
		def str1 =
			"""
				SELECT 
					VC_CUST_ID,VC_CUST_FNAME, VC_CUST_MNAME, VC_CUST_LNAME,VC_VOUCHER_NO,DT_VOUCHER_DATE,VC_OLD_PAYMENT_TYPE,DT_CLEAR_DATE,VC_CHQ_NO,DT_CHQ_DATE,NU_AMOUNT,VC_BANK_NAME 
				FROM 
					WEBTBZ.DT_CHEQUE_CLEAR 
				WHERE 
					DT_CHQ_DATE BEETWEEN 
			"""
		Sql sql = new Sql(dataSource)
		sql.execute str1
	}
	
	/**
	 * Method Name: searchData
	 * Description: Search Scheme according to date from Database
	 *
	 * @return : ResultSet of scheme according to chequeNo,From_Amount and To_Amount
	 */
	def searchData(dtChequeClearInstance){
		def x = dtChequeClearInstance
				
		String dateStr = "$x.fromDate"
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date = (Date)formatter.parse(dateStr)
		
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR)
		
		String dateStr1 = "$x.toDate"
		DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date1 = (Date)formatter.parse(dateStr)
		
		Calendar cal1 = Calendar.getInstance()
		cal1.setTime(date1)
		String formatedDate1 = cal1.get(Calendar.DATE) + "/" + (cal1.get(Calendar.MONTH) + 1) + "/" + cal1.get(Calendar.YEAR)
		
		def resultArr=[]
		
		def str =
			"""
				SELECT * 
				FROM 
					NEWWEBTBZ.DT_CHEQUE_CLEAR 
				WHERE 
					VC_CHQ_NO='$x.searchChqNo' 
				AND 
					NU_AMOUNT BETWEEN $x.fromAmt 
				AND 
					$x.toAmt 
				AND 
					DT_VOUCHER_DATE BETWEEN TO_DATE('$formatedDate','DD/MM/YYYY') 
				AND 
					TO_DATE('$formatedDate1','DD/MM/YYYY')
			"""
		Sql sql = new Sql(dataSource)
		
		def result = sql.rows(str)
		result.each{
			resultArr.add(it.values())
		}
		return resultArr.asList()
	}
	
	
}
