package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class SaleRetExp implements Serializable {

	BigDecimal nuDays
	String brCode

	static mapping = {
		table "SALE_RET_EXP"
		id name: 'nuDays', generator : 'assigned'	
		version false
	}

	static constraints = {
		nuDays nullable: true
		brCode nullable: true, maxSize: 10
	}
}
