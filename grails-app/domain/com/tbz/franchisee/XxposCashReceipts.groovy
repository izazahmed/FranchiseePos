package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class XxposCashReceipts implements Serializable {

	BigDecimal detailId
	String receiptType
	String voucherCategory
	String voucherNo
	Date voucherDate
	String customerId
	String customerName
	String payMode
	BigDecimal serialNumber
	BigDecimal balanceAmount
	BigDecimal receiptAmount
	String brCode
	BigDecimal nuMcId
	BigDecimal nuMcNo
	BigDecimal nu999rate
	BigDecimal nu995rate
	BigDecimal nuS9rate
	BigDecimal nuS5rate
	BigDecimal nu24rate
	BigDecimal nu23rate
	BigDecimal nu22rate
	BigDecimal nu18rate
	BigDecimal goldRate
	String receiptRemark
	String creationBy
	Date creationDate
	String updatedBy
	Date updateDate
	String postFlag
	String cancelFlag
	BigDecimal nuField1
	BigDecimal nuField2
	BigDecimal nuField3
	BigDecimal nuField4
	BigDecimal nuField5
	Date dtField1
	Date dtField2
	Date dtField3
	Date dtField4
	Date dtField5
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	String vcField5
	String receiptNumber
	String employeeId
	String employeeName
	BigDecimal mobileNumber

	static mapping = {
		table "XXPOS_CASH_RECEIPTS"
		id name: 'receiptNumber', generator : 'assigned'	
		nu18rate column: 'NU_18RATE'
		nu22rate column: 'NU_22RATE'
		nu23rate column: 'NU_23RATE'
		nu24rate column: 'NU_24RATE'
		nuS9rate column: 'NU_S9RATE'
		nu999rate column: 'NU_999RATE'
		nuS5rate column: 'NU_S5RATE'
		nu995rate column: 'NU_995RATE'
		version false
	}

	static constraints = {
		detailId nullable: true
		receiptType nullable: true, maxSize: 10
		voucherCategory nullable: true, maxSize: 10
		voucherNo nullable: true, maxSize: 60
		voucherDate nullable: true
		customerId nullable: true, maxSize: 40
		customerName nullable: true, maxSize: 200
		payMode nullable: true, maxSize: 10
		serialNumber nullable: true
		balanceAmount nullable: true
		receiptAmount nullable: true
		brCode nullable: true, maxSize: 40
		nuMcId nullable: true
		nuMcNo nullable: true
		nu999rate nullable: true
		nu995rate nullable: true
		nuS9rate nullable: true
		nuS5rate nullable: true
		nu24rate nullable: true
		nu23rate nullable: true
		nu22rate nullable: true
		nu18rate nullable: true
		goldRate nullable: true
		receiptRemark nullable: true, maxSize: 200
		creationBy nullable: true, maxSize: 10
		creationDate nullable: true
		updatedBy nullable: true, maxSize: 10
		updateDate nullable: true
		postFlag nullable: true, maxSize: 1
		cancelFlag nullable: true, maxSize: 1
		nuField1 nullable: true
		nuField2 nullable: true
		nuField3 nullable: true
		nuField4 nullable: true
		nuField5 nullable: true
		dtField1 nullable: true
		dtField2 nullable: true
		dtField3 nullable: true
		dtField4 nullable: true
		dtField5 nullable: true
		vcField1 nullable: true, maxSize: 80
		vcField2 nullable: true, maxSize: 80
		vcField3 nullable: true, maxSize: 80
		vcField4 nullable: true, maxSize: 80
		vcField5 nullable: true, maxSize: 80
		receiptNumber nullable: true, maxSize: 60
		employeeId nullable: true, maxSize: 30
		employeeName nullable: true, maxSize: 100
		mobileNumber nullable: true
	}
}
