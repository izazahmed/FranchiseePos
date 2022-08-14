package com.tbz.franchisee

class DtSaleAdvPay {

	String vcAdvOrdNo
	String vcCompCode
	String brCode
	Date dtAdvOrdDate
	String chPayMode
	Integer nuSrNo
	BigDecimal nuAmount
	BigDecimal vcChqNo
	Date dtChqDate
	BigDecimal vcCcNo
	String nuBankCode
	String vcApprovalNo
	Date dtApprovalDate
	Long nuCurrCode
	BigDecimal nuExchangeRate
	String vcGifVouNo
	BigDecimal nuFcAmount
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
	String vcApprovalId
	String vcApprovalName
	String vcReferedId
	String vcReferedName
	String vcCardType
	String vcCustBank
	String nuCustBank
	Long nuReceiptId
	String tbzBank
	String vcCurrencyName
	String vcEbsOrderNo

	static mapping = {
		id name: "vcAdvOrdNo", generator: "assigned"
		version false
	}

	static constraints = {
		vcAdvOrdNo maxSize: 50
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		dtAdvOrdDate nullable: true
		chPayMode nullable: true, maxSize: 2
		nuSrNo nullable: true
		nuAmount nullable: true
		vcChqNo nullable: true
		dtChqDate nullable: true
		vcCcNo nullable: true
		nuBankCode nullable: true, maxSize: 30
		vcApprovalNo nullable: true, maxSize: 30
		dtApprovalDate nullable: true
		nuCurrCode nullable: true
		nuExchangeRate nullable: true
		vcGifVouNo nullable: true, maxSize: 30
		nuFcAmount nullable: true
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
		vcApprovalId nullable: true, maxSize: 10
		vcApprovalName nullable: true, maxSize: 100
		vcReferedId nullable: true, maxSize: 10
		vcReferedName nullable: true, maxSize: 100
		vcCardType nullable: true, maxSize: 50
		vcCustBank nullable: true, maxSize: 50
		nuCustBank nullable: true, maxSize: 50
		nuReceiptId nullable: true
		tbzBank nullable: true, maxSize: 200
		vcCurrencyName nullable: true, maxSize: 50
		vcEbsOrderNo nullable: true, maxSize: 50
	}
}
