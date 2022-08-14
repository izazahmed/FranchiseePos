package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class GiftVoucherPayment implements Serializable {

	String vcCompCode
	String vcDefaultComp
	String vcVoucherNo
	Date dtVoucherDate
	String chPayMode
	BigDecimal nuAmount
	String vcChqNo
	Date dtChqDate
	String vcCcNo
	String nuBankCode
	String vcApprovalNo
	Date dtApprovalDate
	Long nuCurrCode
	BigDecimal nuExchangeRate
	String vcEntryNo
	BigDecimal nuForCurAmt
	String vcCategoryCode
	String brCode
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
	Date dtField6
	Date dtField7
	Date dtField8
	Date dtField9
	Date dtField10
	String vcBankName
	String vcCustBank
	String nuCustBank
	BigDecimal nuReceiptId
	String vcGvVoucherNo
	Date dtGvVoucherDate

	static mapping = {
		id composite: ["vcCompCode", "vcDefaultComp", "vcVoucherNo", "dtVoucherDate", "chPayMode", "nuAmount", "vcChqNo", "dtChqDate", "vcCcNo", "nuBankCode", "vcApprovalNo", "dtApprovalDate", "nuCurrCode", "nuExchangeRate", "vcEntryNo", "nuForCurAmt", "vcCategoryCode", "brCode", "vcField1", "vcField2", "vcField3", "vcField4", "vcField5", "vcField6", "vcField7", "vcField8", "vcField9", "vcField10", "nuField1", "nuField2", "nuField3", "nuField4", "nuField5", "nuField6", "nuField7", "nuField8", "nuField9", "nuField10", "dtField1", "dtField2", "dtField3", "dtField4", "dtField5", "dtField6", "dtField7", "dtField8", "dtField9", "dtField10", "vcBankName", "vcCustBank", "nuCustBank", "nuReceiptId", "vcGvVoucherNo", "dtGvVoucherDate"]
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		vcDefaultComp nullable: true, maxSize: 2
		vcVoucherNo nullable: true, maxSize: 20
		dtVoucherDate nullable: true
		chPayMode nullable: true, maxSize: 2
		nuAmount nullable: true
		vcChqNo nullable: true, maxSize: 20
		dtChqDate nullable: true
		vcCcNo nullable: true, maxSize: 20
		nuBankCode nullable: true, maxSize: 30
		vcApprovalNo nullable: true, maxSize: 30
		dtApprovalDate nullable: true
		nuCurrCode nullable: true
		nuExchangeRate nullable: true
		vcEntryNo nullable: true, maxSize: 30
		nuForCurAmt nullable: true
		vcCategoryCode nullable: true, maxSize: 20
		brCode nullable: true, maxSize: 10
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
		dtField6 nullable: true
		dtField7 nullable: true
		dtField8 nullable: true
		dtField9 nullable: true
		dtField10 nullable: true
		vcBankName nullable: true, maxSize: 100
		vcCustBank nullable: true, maxSize: 200
		nuCustBank nullable: true, maxSize: 10
		nuReceiptId nullable: true
		vcGvVoucherNo nullable: true, maxSize: 30
		dtGvVoucherDate nullable: true
	}
}
