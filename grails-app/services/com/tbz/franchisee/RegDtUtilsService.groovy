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
 * RegDtUtilsService
 * Service for Registration Details related information
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
class RegDtUtilsService {
	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: saveRegDt
	 * Description: Save Registration Details record from the Database
	 *
	 * @return : ResultSet of registration details record from database
	 */
	def saveRegDt(regDtInstance) {
		
		String dateStr = "$regDtInstance.regDate"
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date = (Date)formatter.parse(dateStr)
		
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		def str = "INSERT INTO WEBTBZ.REG_DT(REG_NO, REG_DATE, SCHEME_NO, NU_PERIOD, CUST_ID, CUST_REFNO,ENROLLMENT_START_DATE,ENROLLMENT_END_DATE, TOTAL_MONTH, DUE_AMOUNT, VC_WANT_TO_BUY,VC_REMARK) VALUES ('$regDtInstance.regNo', TO_DATE('$formatedDate','MM/DD/YYYY') , $regDtInstance.schemeNo, $regDtInstance.nuPeriod, $regDtInstance.custId, $regDtInstance.custRefno, $regDtInstance.enrollmentStartDate, $regDtInstance.enrollmentEndDate, $regDtInstance.totalMonth,$regDtInstance.dueAmount,$regDtInstance.vcWantToBuy,$regDtInstance.vcRemark)"
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: showRegDt
	 * Description: Show Registration Details record from the Database
	 * @params : registrationInstance
	 * @return : ResultSet of registration details record from database
	 */
	def showRegDt(regDtInstance) {
		
		def str = "SELECT * FROM WEBTBZ.REG_DT WHERE REG_NO = '$regDtInstance'"
		Sql sql = new Sql(dataSource)
		def row = sql.executeQuery str
		return row
	}

	/**
	 * Method Name: updateRegDt
	 * Description: Update Registration Details record from the Database
	 * @params : registrationInstance
	 * @return : ResultSet of updated registration details record from database
	 */
	def updateRegDt(regDtInstance) {
		
		def str = "UPDATE WEBTBZ.REG_DT set SCHEME_NO='$regDtInstance.schemeNo' where REG_NO=$regDtInstance.regNo"
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: deleteRegDt
	 * Description: Delete Registration Details record from the Database
	 * @params : registrationInstance
	 * @return : ResultSet of deleted registration details record from database
	 */
	def deleteRegDt(regDtInstance) {
		def str = "DELETE FROM WEBTBZ.REG_DT where REG_NO='$regDtInstance.regNo'"
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
}
