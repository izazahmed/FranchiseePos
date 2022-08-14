package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtEcsRef implements Serializable {

	String vcCustId
	Short nuInstallFor
	Character chActive
	Date dtInstallDate
	Long nuAmount
	Long nuBalance
	Character chTextGen
	Date dtField1
	Date dtField2
	Long nuField1
	Long nuField2
	String vcField1
	String vcField2
	Character chCancelFlag
	Character chStage

	static mapping = {
		table "DT_ECS_REF"
		id name: 'vcCustId', generator : 'assigned'
		version false
	}

	static constraints = {
		vcCustId nullable: true, maxSize: 20
		nuInstallFor nullable: true
		chActive nullable: true, maxSize: 1
		dtInstallDate nullable: true
		nuAmount nullable: true
		nuBalance nullable: true
		chTextGen nullable: true, maxSize: 1
		dtField1 nullable: true
		dtField2 nullable: true
		nuField1 nullable: true
		nuField2 nullable: true
		vcField1 nullable: true, maxSize: 20
		vcField2 nullable: true, maxSize: 20
		chCancelFlag nullable: true, maxSize: 1
		chStage nullable: true, maxSize: 1
	}
}
