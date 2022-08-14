package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class XxposOtherDiscount implements Serializable {

	String brCode
	BigDecimal nuDiscount
	String vcActive

	static mapping = {
		table "XXPOS_OTHER_DISCOUNT"
		id name: 'brCode', generator : 'assigned'	
		version false
	}

	static constraints = {
		brCode nullable: true, maxSize: 10
		nuDiscount nullable: true
		vcActive nullable: true, maxSize: 3
	}
}
