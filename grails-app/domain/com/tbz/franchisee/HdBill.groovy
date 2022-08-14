package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class HdBill implements Serializable {

	String vcCompCode
	String brCode
	String vcBillNo
	Date dtBillDate
	Date dtDueDate
	String vcSupplierCode
	String vcSupplierFname
	String vcSupplierMname
	String vcSupplierLname
	String vcBillAddress1
	String vcBillAddress2
	String vcBillAddress3
	String vcBillAddress4
	String vcPhone
	String vcEmail
	String vcState
	String vcCity
	String vcPostalCode
	String vcPartyBillNo
	Integer nuAccountCode
	String vcCategory
	String vcDeptt
	String vcTermsOfPayment
	String vcGoldType
	String vcTypeOfBill
	String vcStatFlag
	String vcStatUpFlag
	Date dtModDate
	String vcAuthCode
	BigDecimal nuAmount
	String vcNarration
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	Date dtField1
	Date dtField2
	BigDecimal nuField1
	BigDecimal nuField2
	Short nuCurrencyCode
	String vcCancelFlag
	BigDecimal nuCashAmt
	BigDecimal nuChqAmt
	Short nuMcNo
	Short nuMcId
	Integer nuStatNo
	String vcFinVoucher
	Long nuCustId
	Character chStockFlag
	Date dtUpdateDate
	Date dtCreationDate
	String vcCreationBy
	String vcUpdateBy
	Character chStage
	String vcField5
	String vcField6
	String vcField7
	String vcField8
	String vcField9
	String vcField10
	String vcField11
	String vcField12
	String vcField13
	String vcField14
	BigDecimal nuField3
	BigDecimal nuField4
	BigDecimal nuField5
	BigDecimal nuField6
	BigDecimal nuField7
	BigDecimal nuField8
	BigDecimal nuField9
	BigDecimal nuField10
	BigDecimal nuField11
	BigDecimal nuField12
	Date dtField3
	Date dtField4
	Date dtField5
	Date dtField6
	Date dtField7
	Date dtField8
	Date dtField9
	Date dtField10
	Date dtField11
	Date dtField12
	String vcTokenNo
	Date dtTokenDate
	Character chCancelFlag
	String vcCountry
	String vcAdvOrdNo
	Date dtAdvOrdDate
	String exType

	static mapping = {
		id composite: ["vcCompCode", "brCode", "vcBillNo", "dtBillDate", "dtDueDate", "vcSupplierCode", "vcSupplierFname", "vcSupplierMname", "vcSupplierLname", "vcBillAddress1", "vcBillAddress2", "vcBillAddress3", "vcBillAddress4", "vcPhone", "vcEmail", "vcState", "vcCity", "vcPostalCode", "vcPartyBillNo", "nuAccountCode", "vcCategory", "vcDeptt", "vcTermsOfPayment", "vcGoldType", "vcTypeOfBill", "vcStatFlag", "vcStatUpFlag", "dtModDate", "vcAuthCode", "nuAmount", "vcNarration", "vcField1", "vcField2", "vcField3", "vcField4", "dtField1", "dtField2", "nuField1", "nuField2", "nuCurrencyCode", "vcCancelFlag", "nuCashAmt", "nuChqAmt", "nuMcNo", "nuMcId", "nuStatNo", "vcFinVoucher", "nuCustId", "chStockFlag", "dtUpdateDate", "dtCreationDate", "vcCreationBy", "vcUpdateBy", "chStage", "vcField5", "vcField6", "vcField7", "vcField8", "vcField9", "vcField10", "vcField11", "vcField12", "vcField13", "vcField14", "nuField3", "nuField4", "nuField5", "nuField6", "nuField7", "nuField8", "nuField9", "nuField10", "nuField11", "nuField12", "dtField3", "dtField4", "dtField5", "dtField6", "dtField7", "dtField8", "dtField9", "dtField10", "dtField11", "dtField12", "vcTokenNo", "dtTokenDate", "chCancelFlag", "vcCountry", "vcAdvOrdNo", "dtAdvOrdDate", "exType"]
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		vcBillNo nullable: true, maxSize: 30
		dtBillDate nullable: true
		dtDueDate nullable: true
		vcSupplierCode nullable: true, maxSize: 20
		vcSupplierFname nullable: true, maxSize: 70
		vcSupplierMname nullable: true, maxSize: 70
		vcSupplierLname nullable: true, maxSize: 70
		vcBillAddress1 nullable: true, maxSize: 500
		vcBillAddress2 nullable: true, maxSize: 500
		vcBillAddress3 nullable: true, maxSize: 500
		vcBillAddress4 nullable: true, maxSize: 500
		vcPhone nullable: true, maxSize: 30
		vcEmail nullable: true, maxSize: 100
		vcState nullable: true, maxSize: 70
		vcCity nullable: true, maxSize: 70
		vcPostalCode nullable: true, maxSize: 30
		vcPartyBillNo nullable: true, maxSize: 12
		nuAccountCode nullable: true
		vcCategory nullable: true, maxSize: 20
		vcDeptt nullable: true, maxSize: 10
		vcTermsOfPayment nullable: true, maxSize: 20
		vcGoldType nullable: true, maxSize: 2
		vcTypeOfBill nullable: true, maxSize: 2
		vcStatFlag nullable: true, maxSize: 2
		vcStatUpFlag nullable: true, maxSize: 2
		dtModDate nullable: true
		vcAuthCode nullable: true, maxSize: 2
		nuAmount nullable: true
		vcNarration nullable: true, maxSize: 500
		vcField1 nullable: true, maxSize: 30
		vcField2 nullable: true, maxSize: 30
		vcField3 nullable: true, maxSize: 30
		vcField4 nullable: true, maxSize: 30
		dtField1 nullable: true
		dtField2 nullable: true
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		nuCurrencyCode nullable: true
		vcCancelFlag nullable: true, maxSize: 2
		nuCashAmt nullable: true
		nuChqAmt nullable: true
		nuMcNo nullable: true
		nuMcId nullable: true
		nuStatNo nullable: true
		vcFinVoucher nullable: true, maxSize: 10
		nuCustId nullable: true
		chStockFlag nullable: true, maxSize: 1
		dtUpdateDate nullable: true
		dtCreationDate nullable: true
		vcCreationBy nullable: true, maxSize: 5
		vcUpdateBy nullable: true, maxSize: 5
		chStage nullable: true, maxSize: 1
		vcField5 nullable: true, maxSize: 50
		vcField6 nullable: true, maxSize: 50
		vcField7 nullable: true, maxSize: 50
		vcField8 nullable: true, maxSize: 50
		vcField9 nullable: true, maxSize: 50
		vcField10 nullable: true, maxSize: 50
		vcField11 nullable: true, maxSize: 50
		vcField12 nullable: true, maxSize: 50
		vcField13 nullable: true, maxSize: 50
		vcField14 nullable: true, maxSize: 50
		nuField3 nullable: true, scale: 3
		nuField4 nullable: true, scale: 3
		nuField5 nullable: true, scale: 3
		nuField6 nullable: true, scale: 3
		nuField7 nullable: true, scale: 3
		nuField8 nullable: true, scale: 3
		nuField9 nullable: true, scale: 3
		nuField10 nullable: true, scale: 3
		nuField11 nullable: true, scale: 3
		nuField12 nullable: true, scale: 3
		dtField3 nullable: true
		dtField4 nullable: true
		dtField5 nullable: true
		dtField6 nullable: true
		dtField7 nullable: true
		dtField8 nullable: true
		dtField9 nullable: true
		dtField10 nullable: true
		dtField11 nullable: true
		dtField12 nullable: true
		vcTokenNo nullable: true, maxSize: 50
		dtTokenDate nullable: true
		chCancelFlag nullable: true, maxSize: 1
		vcCountry nullable: true, maxSize: 50
		vcAdvOrdNo nullable: true, maxSize: 50
		dtAdvOrdDate nullable: true
		exType nullable: true, maxSize: 50
	}
}
