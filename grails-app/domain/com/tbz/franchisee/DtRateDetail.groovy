package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtRateDetail implements Serializable {

	String vcCompCode
	String vcCategory
	String vcPurity
	Date dtModDate
	BigDecimal nuRateParameter
	BigDecimal nuRate
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
	Date creationDate
	String createdBy
	Date lastUpdateDate
	BigDecimal lastUpdatedBy

	static mapping = {
		table "DT_RATE_DETAIL"
		id name: 'vcCompCode', generator : 'assigned'
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 20
		vcCategory nullable: true, maxSize: 20
		vcPurity nullable: true, maxSize: 20
		dtModDate nullable: true
		nuRateParameter nullable: true
		nuRate nullable: true
		nuRatePercentage nullable: true
		nuAdditionalParameter nullable: true
		nuAdditionalParameter1 nullable: true
		nuAdditionalParameter2 nullable: true
		nuAdditionalParameter3 nullable: true
		nuAdditionalParameter4 nullable: true
		nuAdditionalParameter5 nullable: true
		vcAuthCode nullable: true, maxSize: 60
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
		creationDate nullable: true
		createdBy nullable: true, maxSize: 20
		lastUpdateDate nullable: true
		lastUpdatedBy nullable: true
	}
}
