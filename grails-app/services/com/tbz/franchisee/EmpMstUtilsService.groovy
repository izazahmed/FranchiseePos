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
 * EmpMstUtilsService
 * Service for Employee Master related information
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
import groovy.sql.Sql;
import grails.transaction.Transactional

@Transactional
class EmpMstUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: saveEmpMst
	 * Description: Save Employee record data into the Database
	 *
	 * @return : ResultSet of Saved Employee
	 */
	def saveEmpMst(empMstInstance) {
		
		def x = empMstInstance
		def str =
			"""
				INSERT INTO 
					NEWWEBTBZ.EMP_MST
				(
					EMP_ID, VC_SALES_ID, EMP_NAME, CH_ACTIVE, VC_EMP_TYPE, VC_FIELD1, VC_DEPT_DESC, VC_DESIG_CODE, VC_DESIGNATION
				)
				VALUES ('$x.empId', '$x.vcSalesId', '$x.empName', $x.chActive, '$x.vcEmpType', '$x.vcField1', '$x.vcDeptDesc', '$x.vcDesigCode', '$x.vcDesignation')
			""" 
		log.info("string"+str)
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: updateEmpMst
	 * Description: Update Employee record data into the Database
	 * @params : Employee Id
	 * @return : ResultSet of updated Employee
	 */
	def updateEmpMst(empMstInstance) {
		
		def x = empMstInstance
		def str =
			"""
				UPDATE 
					NEWWEBTBZ.EMP_MST 
				SET
					EMP_NAME='$x.empName',VC_SALES_ID='$x.vcSalesId',VC_EMP_TYPE='$x.vcEmpType',VC_FIELD1='$x.vcField1',VC_DEPT_DESC='$x.vcDeptDesc',VC_DESIG_CODE='$x.vcDesigCode',VC_DESIGNATION='$x.vcDesignation' 
				WHERE
					EMP_ID=$x.empId
			"""
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
	
	/**
	 * Method Name: deleteEmpMst
	 * Description: Delete Employee record data into the Database
	 * @params : employee Id
	 * @return : ResultSet of deleted Employee
	 */
	def deleteEmpMst(empMstInstance) {
		def x = empMstInstance
		
		def str =
			"""
				DELETE 
					FROM 
					NEWWEBTBZ.EMP_MST 
				WHERE
					EMP_ID='$x.empId'
			"""
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
}