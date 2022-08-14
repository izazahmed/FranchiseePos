package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MkUsersMkRoles implements Serializable {

	String mkUserId
	String mkRoleId

	static mapping = {
		table "MK_USERS_Mk_ROLES"
		id name: 'mkUserId', generator : 'assigned'	
		version false
	}

	static constraints = {
		mkUserId nullable: true, maxSize: 2
		mkRoleId nullable: true, maxSize: 2
	}
}
