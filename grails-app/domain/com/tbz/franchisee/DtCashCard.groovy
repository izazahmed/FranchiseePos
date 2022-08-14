package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtCashCard implements Serializable {

	String vcCompCode
	String brCode
	String vcVoucherNo
	Date dtVoucherDate
	String vcTokenNo
	Date dtTokenDate
	String vcCategory
	String chPayMode
	Integer nuBankCode
	String vcCcNo
	String vcApprovalNo
	String dtApprovalDate
	Date dtBillDate
	BigDecimal nuSettAmt
	String vcRemarks
	String vcCardType
	String chApprovedFlg
	BigDecimal nuAppAmt
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
	String vcCustBank
	String vcTbzBank
	String vcPaymentType

	static mapping = {
		id composite: ["vcCompCode", "brCode", "vcVoucherNo", "dtVoucherDate", "vcTokenNo", "dtTokenDate", "vcCategory", "chPayMode", "nuBankCode", "vcCcNo", "vcApprovalNo", "dtApprovalDate", "dtBillDate", "nuSettAmt", "vcRemarks", "vcCardType", "chApprovedFlg", "nuAppAmt", "vcField1", "vcField2", "vcField3", "vcField4", "vcField5", "vcField6", "vcField7", "vcField8", "vcField9", "vcField10", "nuField1", "nuField2", "nuField3", "nuField4", "nuField5", "nuField6", "nuField7", "nuField8", "nuField9", "nuField10", "dtField1", "dtField2", "dtField3", "dtField4", "dtField5", "vcDefaultComp", "nuReceiptId", "vcCustBank", "vcTbzBank", "vcPaymentType"]
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		vcVoucherNo nullable: true, maxSize: 30
		dtVoucherDate nullable: true
		vcTokenNo nullable: true, maxSize: 30
		dtTokenDate nullable: true
		vcCategory nullable: true, maxSize: 20
		chPayMode nullable: true, maxSize: 2
		nuBankCode nullable: true
		vcCcNo nullable: true, maxSize: 400
		vcApprovalNo nullable: true, maxSize: 30
		dtApprovalDate nullable: true, maxSize: 30
		dtBillDate nullable: true
		nuSettAmt nullable: true
		vcRemarks nullable: true, maxSize: 300
		vcCardType nullable: true, maxSize: 12
		chApprovedFlg nullable: true, maxSize: 2
		nuAppAmt nullable: true
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
		vcDefaultComp nullable: true, maxSize: 3
		nuReceiptId nullable: true
		vcCustBank nullable: true, maxSize: 150
		vcTbzBank nullable: true, maxSize: 150
		vcPaymentType nullable: true, maxSize: 50
	}
}
