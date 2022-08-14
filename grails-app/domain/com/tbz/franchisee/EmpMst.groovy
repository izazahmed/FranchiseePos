package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class EmpMst implements Serializable {

	String empId
	String empName
	Long deptId
	String brCode
	Character chActive
	String vcSalesId
	String vcEmpType
	String vcDeptDesc
	String vcDesigCode
	String vcDesignation
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

	static mapping = {
		table "EMP_MST"
		id name: 'empId', generator : 'assigned'
		version false
	}

	static constraints = {
		empId maxSize: 15
		empName nullable: true, maxSize: 100
		deptId nullable: true
		brCode nullable: true, maxSize: 10
		chActive nullable: true, maxSize: 1
		vcSalesId nullable: true, maxSize: 20
		vcEmpType nullable: true, maxSize: 20
		vcField1 nullable: true, maxSize: 20
		vcDeptDesc nullable: true, maxSize: 50
		vcDesigCode nullable: true, maxSize: 50
		vcDesignation nullable: true, maxSize: 50
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
	}
}
