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
 * Service for Jan Se Jama Details related information
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
class XxposCashReceiptsUtilsService {

	def dataSource
    def serviceMethod() {

    }
	
	/**
	 * Method Name: getCustInfo
	 * Description: Get Customer information Details record from the Database
	 * @params : xxposCashReceiptsUtilsInstance
	 * @return : ResultSet of Customer details record from database
	 */
	def getCustInfo(xxposCashReceiptsUtilsInstance) {
		def x= xxposCashReceiptsUtilsInstance
		def voucherNo = xxposCashReceiptsUtilsInstance?.voucherNo
		def retList = []
		def string1 = "select distinct cmt.cust_id, fname||' '||mname||' '||lname customer_name, rdt.scheme_no, rdt.due_amount, mobile from reg_dt rdt, cust_mst cmt, in_dt idt where rdt.vc_field4 = 'Y' and idt.vc_rcpt_settle = 'N' and cmt.cust_id = rdt.cust_id and cmt.cust_id = idt.cust_id and rdt.reg_no = idt.reg_no order by rdt.scheme_no desc, cmt.cust_id" 
		def str = "SELECT CUST_ID,FNAME,MOBILE FROM CUST_MST WHERE CUST_ID = '$voucherNo'"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		return result
	}
	
	/**
	 * Method Name: saveJanSeJama
	 * Description: Save Jan Se Jama Details record into the Database
	 * @params : xxposCashReceiptsUtilsInstance
	 * @return : ResultSet of Jan se jama details record from database
	 */
	def saveJanSeJama(xxposCashReceiptsUtilsInstance) {
		
		def x = xxposCashReceiptsUtilsInstance
		String dateStr = "$x.voucherDate"
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		Date date = (Date)formatter.parse(dateStr)
		
		Calendar cal = Calendar.getInstance()
		cal.setTime(date)
		String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR)
		
		def str = "INSERT INTO WEBTBZ.XXPOS_CASH_RECEIPTS(RECEIPT_NUMBER,VOUCHER_CATEGORY,VOUCHER_NO, CUSTOMER_NAME,VOUCHER_DATE,BALANCE_AMOUNT,PAY_MODE,RECEIPT_AMOUNT,EMPLOYEE_NAME,MOBILE_NUMBER,RECEIPT_REMARK) VALUES ('$x.receiptNumber',$x.voucherCategory, $x.customerId, $x.customerName,TO_DATE('$formatedDate','DD/MM/YYYY'),$x.balanceAmount,$x.payMode,$x.receiptAmount,$x.employeeName,$x.mobileNumber,$x.receiptRemark)"
		Sql sql = new Sql(dataSource)
		sql.execute str
	}
}
