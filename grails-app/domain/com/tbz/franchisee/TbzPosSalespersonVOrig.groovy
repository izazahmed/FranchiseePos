package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class TbzPosSalespersonVOrig implements Serializable {

	String salespersonName
	String employeeNumber
	String orgId
	String orgName
	String salesrepNumber
	Date assignmentStartDate
	Date assignmentEndDate
	String approvalPercentage

	static mapping = {
		table "TBZ_POS_SALESPERSON_V_ORIG"
		id name: 'salespersonName', generator : 'assigned'
		version false
	}

	static constraints = {
		salespersonName nullable: true, maxSize: 4000
		employeeNumber nullable: true, maxSize: 30
		orgId nullable: true, maxSize: 2
		orgName maxSize: 240
		salesrepNumber nullable: true, maxSize: 30
		assignmentEndDate nullable: true
		approvalPercentage nullable: true, maxSize: 150
	}
}
