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
 * CustMstUtilsService
 * Service for Customer related information
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

import com.tbz.franchisee.CustMst;

@Transactional
class CustMstUtilsService {

	def dataSource
    def serviceMethod() {
    }
	
	/**
	 * Method Name: selectCustMst
	 * Description: Fetch all customer List
	 *
	 * @return : ResultSet of Customer table
	 */
	def selectCustMst(custMstInstance) {
		def str =
			"""
				SELECT * 
				FROM 
				NEWWEBTBZ.CUST_MST	
			"""
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str.toString())
		return result
	}
	
	/**
	 * Method Name: viewCustDetails
	 * Description: Fetch particular customer details information
	 * @params: Customer Id
	 *
	 * @return : ResultSet of Customer table
	 */
	def viewCustDetails(custMstInstance, custId){
		def x = custMstInstance
		def str =
			"""
				SELECT * 
				FROM 
					NEWWEBTBZ.CUST_MST 
				WHERE
					CUST_ID ='$custId'
			""" 
		Sql sql = new Sql(dataSource)
		def row = sql.executeQuery str
		return row
	}
	
	/**
	 * Method Name: selectIdCustMst
	 * Description: Fetch Customer Id from Customer Table
	 *
	 * @return : ResultSet of Customer table
	 */
	def selectIdCustMst(custMstInstance){
		
		def x = custMstInstance
		def dtStr =
			"""
				SELECT
					CUST_ID 
				FROM
					NEWWEBTBZ.CUST_MST
			""" 
		Sql sql = new Sql(dataSource)
		sql.execute dtStr
	}
	
	/**
	 * Method Name: saveCustMst
	 * Description: Save Customer details information in Database
	 * @params : randomCustId, pidRadio, apRadio, DOB,phNoO,phNoR,mobile
	 * @return : ResultSet of inserted Customer
	 */
	def saveCustMst(custMstInstance, randomCustId, pidRadio, apRadio, DOB,phNoO,phNoR,mobile) {
		def x = custMstInstance
		String dateStr = DOB
		//DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")
		Date date = (Date)formatter.parse(dateStr)
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		def mname = (x?.mname==null) ? '' : x?.mname
		def add1 = (x?.add1==null)  ?  '' : x?.add1
		def add2 = (x?.add2==null)  ?  '' : x?.add2
		def city = (x?.city==null)  ?  '' : x?.city
		def state = (x?.state==null)  ?  '' : x?.state
		def custNominee = (x?.custNominee==null)  ?  '' : x?.custNominee
		def custNomineeRel = (x?.custNomineeRel==null)  ?  '' : x?.custNomineeRel
		//def add1 = x?.
		mname?:''
			
		def str1 = 
			"""
				INSERT INTO 
					NEWWEBTBZ.CUST_MST
					(
						CUST_ID,FNAME, MNAME, LNAME,ADD1,ADD2,CITY,STATE,PIN,PHONE_R,PHONE_O,MOBILE,EMAIL_ID,DOB,PAN_NO,CUST_NOMINEE,CUST_NOMINEE_GEN,CUST_NOMINEE_REL, VC_FIELD6, VC_FIELD7
					)
					VALUES 
					(
						'"+randomCustId+"', '$x.fname','"+mname+"','$x.lname','"+add1+"','"+add2+"','"+city+"','"+state+"','$x.pin','"+phNoR+"','"+phNoO+"','"+mobile+"','$x.emailId', TO_DATE('$formatedDate','MM/DD/YYYY'),'$x.panNo','"+custNominee+"','$x.custNomineeGen','"+custNomineeRel+"','"+pidRadio+"','"+apRadio+"'
					)
			"""
		
	//	def str = "INSERT INTO NEWWEBTBZ.CUST_MST(CUST_ID,FNAME, MNAME, LNAME,ADD1,ADD2,CITY,STATE,PIN,PHONE_R,PHONE_O,MOBILE,EMAIL_ID,DOB,PAN_NO,CUST_NOMINEE,CUST_NOMINEE_GEN,CUST_NOMINEE_REL) VALUES ('"+randomCustId+"','$x.fname','$x.mname','$x.lname','$x.add1','$x.add2','$x.city','$x.state','$x.pin','$x.phoneR','$x.phoneO','$x.mobile','$x.emailId', TO_DATE('$formatedDate','MM/DD/YYYY'),'$x.panNo',$x.custNominee,$x.custNomineeGen,$x.custNomineeRel)"
		log.info("Customer Insert String--------->"+str1)
		Sql sql = new Sql(dataSource)
		sql.execute str1
	}
	
	/**
	 * Method Name: updateCustMst
	 * Description: Update Customer details information in Database
	 * @params : Customer Id
	 * @return : ResultSet of updated Customer
	 */
	def updateCustMst(custMstInstance) {
		
		def x = custMstInstance
		
		String dateStr = "$x.dob"
		DateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.S") //("E MMM dd HH:mm:ss Z yyyy") 
		Date date = (Date)formatter.parse(dateStr)
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		def str =
			"""
				UPDATE 
					NEWWEBTBZ.CUST_MST 
				SET
					FNAME='$x.fname',MNAME='$x.mname',LNAME='$x.lname',ADD1='$x.add1',ADD2='$x.add2',CITY='$x.city',STATE='$x.state',PIN='$x.pin',PHONE_R='$x.phoneR',PHONE_O='$x.phoneO',MOBILE='$x.mobile',EMAIL_ID='$x.emailId',DOB=TO_DATE('$formatedDate','MM/DD/YYYY'),PAN_NO='$x.panNo',CUST_NOMINEE='$x.custNominee',CUST_NOMINEE_GEN='$x.custNomineeGen',CUST_NOMINEE_REL='$x.custNomineeRel' 
				WHERE
					CUST_ID='$x.custId'
			"""
		log.info("UPDATE STRING-->"+str)
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: deleteCustMst
	 * Description: Delete Customer details information from Database
	 * @params : Customer Id
	 * @return : ResultSet of updated Customer
	 */
	def deleteCustMst(custMstInstance) {
		def x = custMstInstance
		def str =
			"""
				DELETE 
				FROM 
					NEWWEBTBZ.CUST_MST 
				WHERE
					CUST_ID='$x.custId'
			""" 
		log.info("DELETE STRING-->"+str)
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: searchCustMst
	 * Description: Search Customer details information from Database
	 * 
	 * @return : ResultSet of updated Customer
	 */
	def searchCustMst(custMstInstance){
		def str =
			"""
				SELECT * 
				FROM 
					NEWWEBTBZ.CUST_MST 
				WHERE
					CUST_ID='$custMstInstance.findCustId'
			"""
		log.info("SEARCH STRING-->"+str)
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str.toString())
		log.info("customer details are ..."+result[0])
		return result[0]
	}
	
	/**
	 * Method Name: selectCustData
	 * Description: Select Customer details information from Database
	 * 
	 * @return : ResultSet of selected Customer
	 */
	def selectCustData(custMstInstance){
		
		def str =
			"""
				SELECT * 
				FROM
					NEWWEBTBZ.CUST_MST 
				WHERE
					cust_id='$custMstInstance?.customerID'
			"""
		log.info("SEARCH STRING-->"+str)
		Sql sql = new Sql(dataSource)
		//sql.execute str
		def result = sql.rows(str.toString())
		return result[0]
	}
	
	/**
	 * Method Name: updateCustomerId
	 * Description: Update Customer details information from Database
	 * @params :  oldCustId,randomCustId,DOB,phNoO,phNoR,mobile
	 * @return : ResultSet of updated Customer
	 */
	def updateCustomerId(custMstInstance, oldCustId,randomCustId,DOB,phNoO,phNoR,mobile){
		def x = custMstInstance
		//DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")
		Date date = (Date)formatter.parse(DOB)
		
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
		
		def str =
			"""
				UPDATE 
					NEWWEBTBZ.CUST_MST 
				SET
					CUST_ID='"+randomCustId+"', FNAME='$x.fname',MNAME='$x.mname',LNAME='$x.lname',ADD1='$x.add1',ADD2='$x.add2',CITY='$x.city',STATE='$x.state',PIN='$x.pin',PHONE_R='"+phNoR+"',PHONE_O='"+phNoO+"',MOBILE='"+mobile+"',EMAIL_ID='$x.emailId',DOB=TO_DATE('$formatedDate','MM/DD/YYYY'),PAN_NO='$x.panNo',CUST_NOMINEE='$x.custNominee',CUST_NOMINEE_GEN='$x.custNomineeGen',CUST_NOMINEE_REL='$x.custNomineeRel', KP_LOYAL_REF_OLD='"+oldCustId+"' where CUST_ID='"+oldCustId+"'
			"""		
	//	def str = "UPDATE NEWWEBTBZ.CUST_MST set KP_LOYAL_REF_OLD='"+oldCustId+"' where CUST_ID='"+randomCustId+"'"
		log.info("SEARCH STRING-->"+str)
		Sql sql = new Sql(dataSource)
		sql.execute str	
	}
}
