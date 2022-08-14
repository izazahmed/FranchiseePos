package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MstReason implements Serializable {

	String vcCompCode
	String vcReasonCode
	String vcReasonDesc
	String vcReasonFlg
	Date dtModDate
	String chStatFlag
	String chStatUpFlag
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
		id composite: ["vcCompCode", "vcReasonCode", "vcReasonDesc", "vcReasonFlg", "dtModDate", "chStatFlag", "chStatUpFlag", "vcAuthCode", "vcField1", "vcField2", "vcField3", "vcField4", "nuField1", "nuField2", "dtField1", "dtField2", "brCode"]
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		vcReasonCode nullable: true, maxSize: 4
		vcReasonDesc nullable: true, maxSize: 30
		vcReasonFlg nullable: true, maxSize: 1
		dtModDate nullable: true
		chStatFlag nullable: true, maxSize: 2
		chStatUpFlag nullable: true, maxSize: 2
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
