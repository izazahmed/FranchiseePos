package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtCashPayment implements Serializable {

	String vcCompCode
	String brCode
	String vcVoucherNo
	Date dtVoucherDate
	String vcCategory
	String vcTokenNo
	Date dtTokenDate
	String chPayMode
	BigDecimal vcChqNo
	Date dtChqDate
	BigDecimal nuAmount
	String vcRemarks
	BigDecimal nuBankCode
	String vcCustBank
	String vcApprovalId
	String vcApprovalName
	String vcReferedId
	String vcReferedName
	Integer nuSrNo
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	String vcField5
	String vcField6
	String vcField7
	String vcField8
	String vcField9
	String vcField10
	BigDecimal nuField1
	BigDecimal nuField2
	BigDecimal nuField3
	BigDecimal nuField4
	BigDecimal nuField5
	BigDecimal nuField6
	BigDecimal nuField7
	BigDecimal nuField8
	BigDecimal nuField9
	BigDecimal nuField10
	Date dtField1
	Date dtField2
	Date dtField3
	Date dtField4
	Date dtField5
	String vcDefaultComp
	Long nuReceiptId

	static mapping = {
		table "DT_CASH_PAYMENT"
		
		id name: 'vcVoucherNo', generator : 'assigned'
		vcRemarks column: 'VC_REMARKS'
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		vcVoucherNo nullable: true, maxSize: 30
		dtVoucherDate nullable: true
		vcCategory nullable: true, maxSize: 20
		vcTokenNo nullable: true, maxSize: 30
		dtTokenDate nullable: true
		chPayMode nullable: true, maxSize: 2
		vcChqNo nullable: true
		dtChqDate nullable: true
		nuAmount nullable: true
		vcRemarks nullable: true, maxSize: 500
		nuBankCode nullable: true
		vcCustBank nullable: true, maxSize: 150
		vcApprovalId nullable: true, maxSize: 10
		vcApprovalName nullable: true, maxSize: 100
		vcReferedId nullable: true, maxSize: 10
		vcReferedName nullable: true, maxSize: 100
		nuSrNo nullable: true
		vcField1 nullable: true, maxSize: 50
		vcField2 nullable: true, maxSize: 50
		vcField3 nullable: true, maxSize: 50
		vcField4 nullable: true, maxSize: 50
		vcField5 nullable: true, maxSize: 50
		vcField6 nullable: true, maxSize: 50
		vcField7 nullable: true, maxSize: 50
		vcField8 nullable: true, maxSize: 50
		vcField9 nullable: true, maxSize: 50
		vcField10 nullable: true, maxSize: 50
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		nuField3 nullable: true, scale: 3
		nuField4 nullable: true, scale: 3
		nuField5 nullable: true, scale: 3
		nuField6 nullable: true, scale: 3
		nuField7 nullable: true, scale: 3
		nuField8 nullable: true, scale: 3
		nuField9 nullable: true, scale: 3
		nuField10 nullable: true, scale: 3
		dtField1 nullable: true
		dtField2 nullable: true
		dtField3 nullable: true
		dtField4 nullable: true
		dtField5 nullable: true
		vcDefaultComp nullable: true, maxSize: 2
		nuReceiptId nullable: true
	}
}
