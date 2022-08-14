package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class RegDt implements Serializable {

	String regNo
	String custId
	Date regDate
	String custRefno
	Long schemeNo
	Date enrollmentStartDate
	Date enrollmentEndDate
	Short totalMonth
	BigDecimal dueAmount
	String empId
	Character chCancelFlag
	String vcRemark
	String vcWantToBuy
	String vcCreationBy
	Date dtCreationDate
	String vcUpdateBy
	Date dtUpdateDate
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
	Short nuPeriod
	String defaultPurity

	static mapping = {
		table "REG_DT"
		id name: 'regNo', generator : 'assigned'
		version false
	}

	static constraints = {
		regNo maxSize: 20
		custId maxSize: 20, unique: true
		custRefno nullable: true, maxSize: 20
		schemeNo nullable: true
		enrollmentStartDate nullable: true
		enrollmentEndDate nullable: true
		totalMonth nullable: true
		dueAmount nullable: true
		empId nullable: true, maxSize: 15
		chCancelFlag nullable: true, maxSize: 1
		vcRemark nullable: true, maxSize: 100
		vcWantToBuy nullable: true, maxSize: 3
		vcCreationBy nullable: true, maxSize: 5
		dtCreationDate nullable: true
		vcUpdateBy nullable: true, maxSize: 5
		dtUpdateDate nullable: true
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
		nuPeriod nullable: true
		defaultPurity nullable: true, maxSize: 10
	}
}
