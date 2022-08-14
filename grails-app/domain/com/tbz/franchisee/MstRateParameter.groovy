package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MstRateParameter implements Serializable {

	String vcCompCode
	String vcCategory
	String vcPurity
	Date dtModDate
	BigDecimal nuRatePercentage
	BigDecimal nuAdditionalParameter
	BigDecimal nuAdditionalParameter1
	BigDecimal nuAdditionalParameter2
	BigDecimal nuAdditionalParameter3
	BigDecimal nuAdditionalParameter4
	BigDecimal nuAdditionalParameter5
	String vcAuthCode
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	String vcField5
	Date dtField1
	Date dtField2
	Date dtField3
	Date dtField4
	Date dtField5
	BigDecimal nuField1
	BigDecimal nuField2
	BigDecimal nuField3
	BigDecimal nuField4
	BigDecimal nuField5
	String brCode

	static mapping = {
		table "MST_RATE_PARAMETER"
		id name: 'vcPurity', generator : 'assigned'
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		vcCategory nullable: true, maxSize: 20
		vcPurity nullable: false, maxSize: 20 , unique:true, blank : false
		dtModDate nullable: true
		nuRatePercentage nullable: true, scale: 3
		nuAdditionalParameter nullable: true, scale: 3
		nuAdditionalParameter1 nullable: true, scale: 3
		nuAdditionalParameter2 nullable: true, scale: 3
		nuAdditionalParameter3 nullable: true, scale: 3
		nuAdditionalParameter4 nullable: true, scale: 3
		nuAdditionalParameter5 nullable: true, scale: 3
		vcAuthCode nullable: true, maxSize: 20
		vcField1 nullable: true, maxSize: 80
		vcField2 nullable: true, maxSize: 80
		vcField3 nullable: true, maxSize: 80
		vcField4 nullable: true, maxSize: 80
		vcField5 nullable: true, maxSize: 80
		dtField1 nullable: true
		dtField2 nullable: true
		dtField3 nullable: true
		dtField4 nullable: true
		dtField5 nullable: true
		nuField1 nullable: true
		nuField2 nullable: true
		nuField3 nullable: true
		nuField4 nullable: true
		nuField5 nullable: true
		brCode nullable: true, maxSize: 60
	}
}
