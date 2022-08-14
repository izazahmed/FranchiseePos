
package com.tbz.franchisee


import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MstDis implements Serializable {

	String vcEmpCode
	String vcDisCode
	String vcEmpName
	String vcField1
	String vcField2
	Date dtField1
	Date dtField2
	BigDecimal nuField1
	BigDecimal nuField2

	static mapping = {
		table "MST_DIS"
		id name: 'vcEmpCode', generator : 'assigned'	
		version false
	}

	static constraints = {
		vcEmpCode nullable: true, maxSize: 10
		vcDisCode nullable: true, maxSize: 5
		vcEmpName nullable: true, maxSize: 30
		vcField1 nullable: true, maxSize: 30
		vcField2 nullable: true, maxSize: 10
		dtField1 nullable: true
		dtField2 nullable: true
		nuField1 nullable: true
		nuField2 nullable: true
	}
}
