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
 * InDtUtilsService
 * Service for Inward Details related information
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
class InDtUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: saveInDt
	 * Description: Save Inward Details record data into the Database
	 * 
	 * @return : ResultSet of Saved Inward Details
	 */
	def saveInDt(inDtInstance) {
		
		def x = inDtInstance
		
		String dateStr = "$x.inwardDate"
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date = (Date)formatter.parse(dateStr)
		
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		String dateStr1 = "$x.dtCreationDate"
		DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date1 = (Date)formatter1.parse(dateStr1)
		
		Calendar cal1 = Calendar.getInstance()
		cal1.setTime(date1)
		String formatedDate1 = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		def str =
			"""
				INSERT INTO 
					NEWWEBTBZ.IN_DT
				(
					INWARD_ID, INWARD_DATE, AMOUNT, PAY_MODE, VC_CHEQUE, DT_CREATION_DATE,VC_CARD_TYPE,DRAWN_ON, DRAWN_ON_ADD, FORMONTH, DEFAULT_GOLD_RATE
				)
				VALUES
				(
					'$x.inwardId', TO_DATE('$formatedDate','MM/DD/YYYY') , $x.amount, $x.payMode, $x.vcCheque, TO_DATE('$formatedDate1','MM/DD/YYYY'), $x.vcCardType, $x.drawnOn, $x.drawnOnAdd,$x.formonth,$x.defaultGoldRate
				)
			""" 
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: updateInDt
	 * Description: Update Inward Details record data into the Database
	 * @params : Inward Id
	 * @return : ResultSet of updated inward record
	 */
	def updateInDt(inDtInstance) {
		def x = inDtInstance
		def str =
			"""
				UPDATE 
					NEWWEBTBZ.IN_DT 
				SET
					AMOUNT='$x.amount'
				WHERE
					INWARD_ID=$x.inwardId
			""" 
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: deleteInDt
	 * Description: Delete Inward Details record data into the Database
	 * @params : Inward Id
	 * @return : ResultSet of deleted Inward Id
	 */
	def deleteInDt(inDtInstance) {
		def x = inDtInstance
		def str = 
			"""
				DELETE 
					NEWWEBTBZ.IN_DT 
				WHERE
					INWARD_ID=$x.inwardId
			"""
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: showInDt
	 * Description: returns inward Details record data according to Scheme Number and PayMode
	 * 
	 * @return : ResultSet of Inward Details information
	 */
	def showInDt(inDtInstance)
	{
		
		def resultArr  = []
		def x = inDtInstance
		
		def str =
			"""
				SELECT * 
				FROM 
					NEWWEBTBZ.IN_DT 
				WHERE 
					SCHEME_NO=$x.schemeNo 
				AND 
					PAY_MODE='$x.payMode'
			""" 
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		result.each{
			resultArr.add(it.values())
		}
		return resultArr.asList()
	}
}
