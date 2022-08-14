package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MstCode implements Serializable {

	String vcCompCode
	String vcCode
	String vcCodeDesc
	String chStatFlag
	String chStatUpFlag
	Date dtModDate
	String vcDefaultComp
	String vcAuthCode
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	BigDecimal nuField1
	BigDecimal nuField2
	Date dtField1
	Date dtField2
	String brCode

	static mapping = {
		table "MST_CODE"
		id name: 'vcCompCode', generator : 'assigned'
		id name: 'vcCode', generator : 'assigned'
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		vcCode nullable: true, maxSize: 12
		vcCodeDesc nullable: true, maxSize: 35
		chStatFlag nullable: true, maxSize: 2
		chStatUpFlag nullable: true, maxSize: 2
		dtModDate nullable: true
		vcDefaultComp nullable: true, maxSize: 2
		vcAuthCode nullable: true, maxSize: 2
		vcField1 nullable: true, maxSize: 30
		vcField2 nullable: true, maxSize: 30
		vcField3 nullable: true, maxSize: 30
		vcField4 nullable: true, maxSize: 30
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		dtField1 nullable: true
		dtField2 nullable: true
		brCode nullable: true, maxSize: 10
	}
}
