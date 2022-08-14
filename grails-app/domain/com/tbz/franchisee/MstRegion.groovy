package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MstRegion implements Serializable {

	String vcCity
	String vcState
	String vcCountry
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	String vcField5
	String brCode

	static mapping = {
		id composite: ["vcCity", "vcState", "vcCountry", "vcField1", "vcField2", "vcField3", "vcField4", "vcField5", "brCode"]
		version false
	}

	static constraints = {
		vcCity nullable: true, maxSize: 100
		vcState nullable: true, maxSize: 100
		vcCountry nullable: true, maxSize: 100
		vcField1 nullable: true, maxSize: 100
		vcField2 nullable: true, maxSize: 100
		vcField3 nullable: true, maxSize: 100
		vcField4 nullable: true, maxSize: 100
		vcField5 nullable: true, maxSize: 100
		brCode nullable: true, maxSize: 10
	}
}
