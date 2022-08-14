package com.tbz.franchisee

class SchemeMst {

	Long schemeNo
	String schemeName
	Date schemeStartDate
	Date schemeEndDate
	BigDecimal enrollamount
	Short makDisc
	BigDecimal amt
	BigDecimal wegiht
	Byte referalCount
	Short exMakDisc
	String vcShortName
	Character chActive
	Long nuTotalMonth
	BigDecimal nuDays
	BigDecimal nuCustRefAmt
	String closeFlag

	static mapping = {
		table "SCHEME_MST"
		//id column: "schemeNo", generator: "assigned"
		id name: 'schemeNo', generator : 'assigned'
		version false
	}

	static constraints = {
		schemeNo maxSize: 10
		schemeName nullable: true, maxSize: 75
		schemeStartDate nullable: true
		schemeEndDate nullable: true
		enrollamount nullable: true
		makDisc nullable: true
		amt nullable: true
		wegiht nullable: true
		referalCount nullable: true
		exMakDisc nullable: true
		vcShortName nullable: true, maxSize: 50
		chActive nullable: true, maxSize: 1
		nuTotalMonth nullable: true
		nuDays nullable: true
		nuCustRefAmt nullable: true
		closeFlag nullable: true, maxSize: 1
	}
}
