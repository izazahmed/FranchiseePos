package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MstApprovalAuth implements Serializable {

	String brCode
	String vcApprovalId
	String vcEmpCode
	String vcApprovalName
	Date dtStartDate
	Date dtEndDate
	String vcDesignation
	String vcEmpType
	BigDecimal nuApprovalAuth
	Character chActive
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
	String vcDeptt
	String vcGender

	static mapping = {
		table "MST_APPROVAL_AUTH"
		id name: 'vcApprovalId', generator : 'assigned'
		version false
		vcApprovalId column :'VC_APPROVAL_ID'
	}

	static constraints = {
		brCode nullable: true, maxSize: 10
		vcApprovalId nullable: true, maxSize: 10
		vcEmpCode nullable: true, maxSize: 10
		vcApprovalName nullable: true, maxSize: 150
		dtStartDate nullable: true
		dtEndDate nullable: true
		vcDesignation nullable: true, maxSize: 70
		vcEmpType nullable: true, maxSize: 10
		nuApprovalAuth nullable: true
		chActive nullable: true, maxSize: 1
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
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		nuField3 nullable: true, scale: 3
		nuField4 nullable: true, scale: 3
		nuField5 nullable: true, scale: 3
		nuField6 nullable: true, scale: 3
		nuField7 nullable: true, scale: 3
		nuField8 nullable: true, scale: 3
		nuField9 nullable: true, scale: 3
		nuField10 nullable: true, scale: 3
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
		vcDeptt nullable: true, maxSize: 10
		vcGender nullable: true, maxSize: 3
	}
}
