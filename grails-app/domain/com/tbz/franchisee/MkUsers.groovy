package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MkUsers implements Serializable {

	String chUserCode
	String vcUserName
	String vcPassword
	Character chUserActive
	String chStatFlag
	String chStatUpFlag
	Date dtModDate
	String vcAuthCode
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	Date dtField1
	Date dtField2
	BigDecimal nuField1
	BigDecimal nuField2
	String brCode

	static mapping = {
		table "MK_USERS"
		id name: 'chUserCode', generator: 'assigned'
		version false
	}

	static constraints = {
		chUserCode nullable: true, maxSize: 2
		vcUserName nullable: true, maxSize: 30
		vcPassword nullable: true, maxSize: 30
		chUserActive nullable: true, maxSize: 1
		chStatFlag nullable: true, maxSize: 2
		chStatUpFlag nullable: true, maxSize: 2
		dtModDate nullable: true
		vcAuthCode nullable: true, maxSize: 2
		vcField1 nullable: true, maxSize: 30
		vcField2 nullable: true, maxSize: 30
		vcField3 nullable: true, maxSize: 30
		vcField4 nullable: true, maxSize: 30
		dtField1 nullable: true
		dtField2 nullable: true
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		brCode nullable: true, maxSize: 10
	}
}
