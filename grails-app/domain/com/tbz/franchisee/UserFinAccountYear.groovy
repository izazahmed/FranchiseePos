package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class UserFinAccountYear implements Serializable {

	String chUserCode
	Date dtFinStartDate
	Date dtFinEndDate
	String brCode
	Character chActive

	static mapping = {
		table "USER_FIN_ACCOUNT_YEAR"
		id name: 'chUserCode', generator : 'assigned'	
		version false
	}

	static constraints = {
		chUserCode nullable: true, maxSize: 3
		dtFinStartDate nullable: true
		dtFinEndDate nullable: true
		brCode nullable: true, maxSize: 10
		chActive nullable: true, maxSize: 1
	}
}
