package com.tbz.franchisee

class HdSaleAdvOrd {

	String vcAdvOrdNo
	String vcCompCode
	String brCode
	Character vcAdvanceType
	Date dtAdvOrdDate
	String vcOrdNo
	Date dtOrdDate
	String vcCustId
	String vcCustFname
	String vcCustMname
	String vcCustLname
	String vcAddress1
	String vcAddress2
	String vcAddress3
	String vcAddress4
	String vcNarration
	String vcContactNo
	String vcCity
	Long vcPin
	String vcState
	String vcCountry
	Date dtDeliveryDate
	Date dtExpiryDate
	BigDecimal nuTotAdvAmt
	String vcTokenNo
	Date dtTokenDate
	Character chActiveFlag
	String vcRefNo
	Character chCancelFlag
	Short nuMcNo
	Short nuMcId
	BigDecimal nuBalance
	Integer nuAdvAccountCode
	String vcPayTerm
	String vcFinNo
	Date dtSettleDate
	String vcSettleTermsOfPayment
	BigDecimal nuSettleCashAmt
	BigDecimal nuSettleChqAmt
	String vcSettleChqNo
	Integer nuSettleBankCode
	Integer nuSettleCashAcc
	Integer nuSettleAdvAcc
	String vcSettleFinNo
	String vcSettleRemarks
	Date dtSettleChqDate
	String vcCpFinNo
	String vcBpFinNo
	String vcCreationBy
	Date dtCreationDate
	String vcUpdateBy
	Date dtUpdateDate
	Character chStage
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
	String vcCustomerName
	BigDecimal nuNettWtt
	BigDecimal nuRate
	String vcAddress
	String vcCategory
	String vcBillNo
	Date dtBillDate
	BigDecimal nuAdvTrAmt
	String vcType
	BigDecimal nuResvGrms

	static mapping = {
		id name: "vcAdvOrdNo", generator: "assigned"
		version false
	}

	static constraints = {
		vcAdvOrdNo maxSize: 50
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		vcAdvanceType nullable: true, maxSize: 1
		dtAdvOrdDate nullable: true
		vcOrdNo nullable: true, maxSize: 20
		dtOrdDate nullable: true
		vcCustId nullable: true, maxSize: 30
		vcCustFname nullable: true, maxSize: 50
		vcCustMname nullable: true, maxSize: 50
		vcCustLname nullable: true, maxSize: 50
		vcAddress1 nullable: true, maxSize: 70
		vcAddress2 nullable: true, maxSize: 70
		vcAddress3 nullable: true, maxSize: 70
		vcAddress4 nullable: true, maxSize: 70
		vcNarration nullable: true, maxSize: 500
		vcContactNo nullable: true, maxSize: 50
		vcCity nullable: true, maxSize: 50
		vcPin nullable: true
		vcState nullable: true, maxSize: 50
		vcCountry nullable: true, maxSize: 50
		dtDeliveryDate nullable: true
		dtExpiryDate nullable: true
		nuTotAdvAmt nullable: true
		vcTokenNo nullable: true, maxSize: 30
		dtTokenDate nullable: true
		chActiveFlag nullable: true, maxSize: 1
		vcRefNo nullable: true, maxSize: 50
		chCancelFlag nullable: true, maxSize: 1
		nuMcNo nullable: true
		nuMcId nullable: true
		nuBalance nullable: true
		nuAdvAccountCode nullable: true
		vcPayTerm nullable: true, maxSize: 20
		vcFinNo nullable: true, maxSize: 10
		dtSettleDate nullable: true
		vcSettleTermsOfPayment nullable: true, maxSize: 20
		nuSettleCashAmt nullable: true
		nuSettleChqAmt nullable: true
		vcSettleChqNo nullable: true, maxSize: 20
		nuSettleBankCode nullable: true
		nuSettleCashAcc nullable: true
		nuSettleAdvAcc nullable: true
		vcSettleFinNo nullable: true, maxSize: 10
		vcSettleRemarks nullable: true, maxSize: 500
		dtSettleChqDate nullable: true
		vcCpFinNo nullable: true, maxSize: 10
		vcBpFinNo nullable: true, maxSize: 10
		vcCreationBy nullable: true, maxSize: 3
		dtCreationDate nullable: true
		vcUpdateBy nullable: true, maxSize: 3
		dtUpdateDate nullable: true
		chStage nullable: true, maxSize: 1
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
		vcCustomerName nullable: true, maxSize: 100
		nuNettWtt nullable: true, scale: 3
		nuRate nullable: true
		vcAddress nullable: true, maxSize: 100
		vcCategory nullable: true, maxSize: 5
		vcBillNo nullable: true, maxSize: 50
		dtBillDate nullable: true
		nuAdvTrAmt nullable: true, scale: 3
		vcType nullable: true, maxSize: 10
		nuResvGrms nullable: true, scale: 3
	}
}
