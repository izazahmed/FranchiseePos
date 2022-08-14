package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class InDt implements Serializable {

	String inwardId
	Date inwardDate
	String custId
	String regNo
	Long schemeNo
	BigDecimal amount
	String payMode
	Date payDate
	String drawnOn
	String drawnOnAdd
	String brCode
	String formonth
	String processEnd
	Integer userid
	BigDecimal vcCheque
	Short nuMcId
	Short nuMcNo
	Long nuBankCode
	String vcFinNo
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
	String vcField11
	String vcField12
	String vcField13
	String vcField14
	String vcField15
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
	BigDecimal nuField11
	BigDecimal nuField12
	BigDecimal nuField13
	BigDecimal nuField14
	BigDecimal nuField15
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
	Date dtField11
	Date dtField12
	Date dtField13
	Date dtField14
	Date dtField15
	String vcRcptNo
	String vcCardType
	Long nuReceiptId
	Integer nuSrNo
	Character vcRcptSettle
	String vcEbsOrderNo
	BigDecimal nu999rate
	BigDecimal nu995rate
	BigDecimal nuS9rate
	BigDecimal nuS5rate
	BigDecimal nu24rate
	BigDecimal nu23rate
	BigDecimal nu22rate
	BigDecimal nu18rate
	BigDecimal defaultGoldRate
	BigDecimal cashReceiptNo

	static mapping = {
		table "IN_DT"
		id name: 'inwardId', generator : 'assigned'	
		nuS9rate column: 'NU_S9RATE'
		nu999rate column: 'NU_999RATE'
		nu995rate column: 'NU_995RATE'
		nuS5rate column: 'NU_S5RATE'
		nu24rate column: 'NU_24RATE'
		nu23rate column: 'NU_23RATE'
		nu22rate column: 'NU_22RATE'
		nu18rate column: 'NU_18RATE'
		version false
	}

	static constraints = {
		inwardId nullable: true, maxSize: 20
		inwardDate nullable: true
		custId nullable: true, maxSize: 20
		regNo nullable: true, maxSize: 20
		schemeNo nullable: true
		amount nullable: true
		payMode nullable: true, maxSize: 10
		payDate nullable: true
		drawnOn nullable: true, maxSize: 150
		drawnOnAdd nullable: true, maxSize: 100
		brCode nullable: true, maxSize: 10
		formonth nullable: true, maxSize: 12
		processEnd nullable: true, maxSize: 2
		userid nullable: true
		vcCheque nullable: true
		nuMcId nullable: true
		nuMcNo nullable: true
		nuBankCode nullable: true
		vcFinNo nullable: true, maxSize: 15
		vcCreationBy nullable: true, maxSize: 5
		dtCreationDate nullable: true
		vcUpdateBy nullable: true, maxSize: 5
		dtUpdateDate nullable: true
		chStage nullable: true, maxSize: 1
		vcField1 nullable: true, maxSize: 50
		vcField2 nullable: true, maxSize: 100
		vcField3 nullable: true, maxSize: 50
		vcField4 nullable: true, maxSize: 50
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
		vcField15 nullable: true, maxSize: 50
		nuField1 nullable: true
		nuField2 nullable: true
		nuField3 nullable: true
		nuField4 nullable: true
		nuField5 nullable: true
		nuField6 nullable: true
		nuField7 nullable: true
		nuField8 nullable: true
		nuField9 nullable: true
		nuField10 nullable: true
		nuField11 nullable: true
		nuField12 nullable: true
		nuField13 nullable: true
		nuField14 nullable: true
		nuField15 nullable: true
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
		dtField11 nullable: true
		dtField12 nullable: true
		dtField13 nullable: true
		dtField14 nullable: true
		dtField15 nullable: true
		vcRcptNo nullable: true, maxSize: 50
		vcCardType nullable: true, maxSize: 50
		nuReceiptId nullable: true
		nuSrNo nullable: true
		vcRcptSettle nullable: true, maxSize: 1
		vcEbsOrderNo nullable: true, maxSize: 100
		nu999rate nullable: true
		nu995rate nullable: true
		nuS9rate nullable: true
		nuS5rate nullable: true
		nu24rate nullable: true
		nu23rate nullable: true
		nu22rate nullable: true
		nu18rate nullable: true
		defaultGoldRate nullable: true
		cashReceiptNo nullable: true
	}
}
