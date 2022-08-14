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
 * LoginUtilsService
 * Service for Scheme Master Details related information
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
class MstApprovalAuthService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: getSalesPersonData
	 * Description: Get Employee Details record data from the Table Emp_mst
	 *
	 * @return : ResultSet of Employee Details
	 */
	def getSalesPersonData(){
		def query =
		"""
			SELECT 
				VC_SALES_ID,BR_CODE,EMP_NAME
			FROM 
				sales.EMP_MST
			"""
		log.info("SELECT query -->"+query)		
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: getExactEmployeeData
	 * Description: Get exact Employee Details record data from the Table Emp_mst
	 *
	 * @return : ResultSet of Employee Details
	 */
	def getExactEmployeeData(mstApprovalauthInstance){
		
		def query =
			"""
			SELECT 
				VC_SALES_ID,BR_CODE,EMP_NAME
			FROM 
				sales.EMP_MST
			WHERE
				VC_SALES_ID='$mstApprovalauthInstance.dd'
			"""
		
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: getAllSchemeList
	 * Description: Get Scheme Details record data from the Table MST_APPROVAL_AUTH
	 *
	 * @return : ResultSet of Employee Details
	 */
	def getAllSchemeList(){
		def query =
		"""
			SELECT 
				br_code,VC_APPROVAL_ID,vc_emp_code,VC_APPROVAL_NAME,DT_START_DATE,DT_END_DATE,VC_DESIGNATION,NU_APPROVAL_AUTH,CH_ACTIVE,vc_emp_type,br_code, vc_deptt, VC_FIELD3,VC_GENDER
			FROM 
				sales.MST_APPROVAL_AUTH 
			order by 
				to_number(decode( sales.xxtbz_find_char(vc_emp_code),'Y',9999, vc_emp_code ))
			"""
		log.info("SELECT query -->"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: getExactSchemeData
	 * Description: Get exact scheme details record data from the Table MST_APPROVAL_AUTH
	 * @params : VC_APPROVAL_ID
	 * @return : ResultSet of Scheme Details
	 */
	def getExactSchemeData(mstApprovalauthInstance) {
		
		def x = mstApprovalauthInstance
		
		def query =
			"""
				SELECT 
					BR_CODE,VC_APPROVAL_ID,VC_EMP_CODE,VC_APPROVAL_NAME,TO_DATE(TO_CHAR(DT_START_DATE,'MM/DD/YYYY'),'MM/DD/YYYY'),DT_END_DATE,VC_DESIGNATION,NU_APPROVAL_AUTH,CH_ACTIVE,VC_EMP_TYPE,BR_CODE, VC_DEPTT, VC_FIELD3,VC_GENDER
				FROM 
					sales.MST_APPROVAL_AUTH
				WHERE
					VC_APPROVAL_ID='$x.dd'
				order by 
					to_number(decode( sales.xxtbz_find_char(vc_emp_code),'Y',9999, vc_emp_code ))
			"""
		log.info("getExactSchemeData............................................"+query)
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(query)
		return detaResult
	}
	
	/**
	 * Method Name: saveScheme
	 * Description: Save Scheme Details record data into the Table MST_APPROVAL_AUTH
	 * @params : params
	 * @return : ResultSet of Scheme Details
	 */
	def saveScheme(mstApprovalAuthInstance) {
		def x = mstApprovalAuthInstance
		
		def department=''
		if(x?.vcDeptt == "ORDER DEPTT")
			department = 0
		else if(x?.vcDeptt == "EARING")
			department = 1
		else if(x?.vcDeptt == "BRACELET")
			department = 2
		else if(x?.vcDeptt == "NECKLACE")
			department = 3
		else if(x?.vcDeptt == "CHAIN")	
			department = 4
		else if(x?.vcDeptt == "PENDANT")
			department = 5
		else if(x?.vcDeptt == "KANGAN")
			department = 6
		else if(x?.vcDeptt == "SET")
			department = 7
		else if(x?.vcDeptt == "DIAMOND")
			department = 8
		else if(x?.vcDeptt == "CUPID DIAMOND")
			department = 9
		else if(x?.vcDeptt == "ZAVERAT")
			department = 10
		else if(x?.vcDeptt == "PEARLS")
			department = 11
		else if(x?.vcDeptt == "BRANCH")
			department = 12
		
		def gender=''
		if(x?.vcGender == "MALE")
			gender = 'M'
		else	
			gender = 'F'
		
		def category=''
		if(x?.vcField3 == "GOLD")
			category = 'G'
		else
			category = 'D'
		
		def active=''
		if(x?.chActive == "ACTIVE")
			active = 'Y'
		else
			active = 'N'
			
		def empType=''
		if(x?.vcEmpType == "MANAGER")
			empType = 'M'
		if(x?.vcEmpType == "PRO")
			empType = 'P'
		if(x?.vcEmpType == "SALES OFFICER")
			empType = 'S'
		else
			empType = 'C'
		
			
		String dateStr = "$x.dtStartDate"
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date = (Date)formatter.parse(dateStr)
		
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR)
		
		String endDate = "$x.dtEndDate"
		DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date1 = (Date)formatter.parse(endDate)
		
		Calendar cal1 = Calendar.getInstance()
		cal1.setTime(date1)
		String formatedDate1 = cal1.get(Calendar.DATE) + "/" + (cal1.get(Calendar.MONTH) + 1) + "/" + cal1.get(Calendar.YEAR)
			
		def str =
			"""
				SELECT
					COUNT(*) as count
				FROM 
					sales.MST_APPROVAL_AUTH
				WHERE
					VC_APPROVAL_ID='$x.vcApprovalId' 
				AND 
					NU_APPROVAL_AUTH='$x.nuApprovalAuth'
				AND
					VC_APPROVAL_NAME='$x.vcApprovalName'
				AND
					VC_DESIGNATION='$empType'
				AND
					CH_ACTIVE='$active'
				AND 
					VC_EMP_TYPE='$empType'
				AND 
					VC_DEPTT='$department'
				AND 
					VC_FIELD3='$category'
				AND 
					VC_GENDER='$gender'				
				
			""" 
			println "str"+str
		Sql sql = new Sql(dataSource)
		def detaResult = sql.rows(str)
		if (detaResult.count?.get(0) ==  0) {
			def str1 =
			"""INSERT INTO NEWWEBTBZ.MST_APPROVAL_AUTH
			(
				VC_APPROVAL_ID,VC_EMP_CODE,VC_APPROVAL_NAME,DT_START_DATE,DT_END_DATE,VC_EMP_TYPE,VC_GENDER,VC_DEPTT,NU_APPROVAL_AUTH,VC_FIELD3,CH_ACTIVE,VC_DESIGNATION
			)
			VALUES
			(
				'$x.vcApprovalId', '$x.vcEmpCode', '$x.vcApprovalName', TO_DATE('$formatedDate','DD/MM/YYYY'),TO_DATE('$formatedDate1','DD/MM/YYYY'),'$empType','$gender','$department','$x.nuApprovalAuth','$category','$active','$empType'
			)"""
			Sql sql1 = new Sql(dataSource)
			sql1.execute str1
		} else {
		
			def str2 = "UPD1ATE NEWWEBTBZ.MST_APPROVAL_AUTH set DT_START_DATE=TO_DATE('$formatedDate','DD/MM/YYYY'),DT_END_DATE=TO_DATE('$formatedDate','DD/MM/YYYY'),VC_EMP_TYPE='$empType',VC_GENDER='$gender',VC_DEPTT='$department',NU_APPROVAL_AUTH='$x.nuApprovalAuth',VC_FIELD3='$category',CH_ACTIVE='$active',VC_DESIGNATION='$empType' where VC_APPROVAL_ID='$vcApprovalId'"
			Sql sql2 = new Sql(dataSource)
			sql2.execute str2
		}
	}
	
	/**
	 * Method Name: updateSchemeData
	 * Description: Update Scheme Details record data into the Table MST_APPROVAL_AUTH
	 * @params : params, vcApprovalId
	 * @return : ResultSet of Scheme Details record
	 */
	def updateSchemeData(mstApprovalAuthInstance , vcApprovalId) {
 		def x = mstApprovalAuthInstance
		def department=''
		if(x?.vcDeptt == "ORDER DEPTT")
			department = 0
		else if(x?.vcDeptt == "EARING")
			department = 1
		else if(x?.vcDeptt == "BRACELET")
			department = 2
		else if(x?.vcDeptt == "NECKLACE")
			department = 3
		else if(x?.vcDeptt == "CHAIN")
			department = 4
		else if(x?.vcDeptt == "PENDANT")
			department = 5
		else if(x?.vcDeptt == "KANGAN")
			department = 6
		else if(x?.vcDeptt == "SET")
			department = 7
		else if(x?.vcDeptt == "DIAMOND")
			department = 8
		else if(x?.vcDeptt == "CUPID DIAMOND")
			department = 9
		else if(x?.vcDeptt == "ZAVERAT")
			department = 10
		else if(x?.vcDeptt == "PEARLS")
			department = 11
		else if(x?.vcDeptt == "BRANCH")
			department = 12
		
		def gender=''
		if(x?.vcGender == "MALE")
			gender = 'M'
		else	
			gender = 'F'
		
		def category=''
		if(x?.vcField3 == "GOLD")
			category = 'G'
		else
			category = 'D'
		
		def active=''
		if(x?.chActive == "ACTIVE")
			active = 'Y'
		else
			active = 'N'
			
		def empType=''
		if(x?.vcEmpType == "MANAGER")
			empType = 'M'
		if(x?.vcEmpType == "PRO")
			empType = 'P'
		if(x?.vcEmpType == "SALES OFFICER")
			empType = 'S'
		else
			empType = 'C'
		
		def str = "UPDATE NEWWEBTBZ.MST_APPROVAL_AUTH set DT_START_DATE='',DT_END_DATE='',VC_EMP_TYPE='$empType',VC_GENDER='$gender',VC_DEPTT='$department',NU_APPROVAL_AUTH='$x.nuApprovalAuth',VC_FIELD3='$category',CH_ACTIVE='$active' where VC_APPROVAL_ID='$vcApprovalId'"
		log.info("UPDATE STRING-->"+str)
		Sql sql = new Sql(dataSource)
		sql.execute str
		
	}
}
