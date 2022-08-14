package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class XxtbzMiscCashAnalysis implements Serializable {

	BigDecimal headerId
	String brCode
	Date runDate
	String accountDescription
	BigDecimal amount
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	String vcField5
	BigDecimal nuField1
	BigDecimal nuField2
	BigDecimal nuField3
	BigDecimal nuField4
	BigDecimal nuField5
	Date dtField1
	Date dtField2
	Date dtField3
	BigDecimal createdBy
	Date creationDate
	BigDecimal lastUpdatedBy
	Date lastUpdatedDate

	static mapping = {
		id composite: ["headerId", "brCode", "runDate", "accountDescription", "amount", "vcField1", "vcField2", "vcField3", "vcField4", "vcField5", "nuField1", "nuField2", "nuField3", "nuField4", "nuField5", "dtField1", "dtField2", "dtField3", "createdBy", "creationDate", "lastUpdatedBy", "lastUpdatedDate"]
		version false
	}

	static constraints = {
		headerId nullable: true
		brCode nullable: true, maxSize: 10
		runDate nullable: true
		accountDescription nullable: true, maxSize: 50
		amount nullable: true
		vcField1 nullable: true
		vcField2 nullable: true
		vcField3 nullable: true, maxSize: 50
		vcField4 nullable: true, maxSize: 50
		vcField5 nullable: true, maxSize: 50
		nuField1 nullable: true
		nuField2 nullable: true
		nuField3 nullable: true
		nuField4 nullable: true
		nuField5 nullable: true
		dtField1 nullable: true
		dtField2 nullable: true
		dtField3 nullable: true
		createdBy nullable: true
		creationDate nullable: true
		lastUpdatedBy nullable: true
		lastUpdatedDate nullable: true
	}
}
