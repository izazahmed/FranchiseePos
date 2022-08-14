package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class XxposRegionMaster implements Serializable {

	BigDecimal regionCode
	String regionName
	String vcActiveFlag

	static mapping = {
		id composite: ["regionCode", "regionName", "vcActiveFlag"]
		version false
	}

	static constraints = {
		regionCode nullable: true
		regionName nullable: true, maxSize: 100
		vcActiveFlag nullable: true, maxSize: 1
	}
}
