package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MkModuleMenu implements Serializable {

	String vcModuleCode
	String vcMenuCode
	String vcMenuObject
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
	String vcCompCode
	String vcSourceObject
	Character chActive
	String vcType

	static mapping = {
		id composite: ["vcModuleCode", "vcMenuCode", "vcMenuObject", "chStatFlag", "chStatUpFlag", "dtModDate", "vcAuthCode", "vcField1", "vcField2", "vcField3", "vcField4", "dtField1", "dtField2", "nuField1", "nuField2", "brCode", "vcCompCode", "vcSourceObject", "chActive", "vcType"]
		version false
	}

	static constraints = {
		vcModuleCode nullable: true, maxSize: 2
		vcMenuCode nullable: true, maxSize: 10
		vcMenuObject nullable: true, maxSize: 50
		chStatFlag nullable: true, maxSize: 2
		chStatUpFlag nullable: true, maxSize: 2
		dtModDate nullable: true
		vcAuthCode nullable: true, maxSize: 2
		vcField1 nullable: true, maxSize: 30
		vcField2 nullable: true, maxSize: 100
		vcField3 nullable: true, maxSize: 30
		vcField4 nullable: true, maxSize: 30
		dtField1 nullable: true
		dtField2 nullable: true
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		brCode nullable: true, maxSize: 10
		vcCompCode nullable: true, maxSize: 25
		vcSourceObject nullable: true, maxSize: 100
		chActive nullable: true, maxSize: 1
		vcType nullable: true, maxSize: 30
	}
}
