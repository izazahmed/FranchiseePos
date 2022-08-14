package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class BrMstTab implements Serializable {

	String brCode
	Integer ebsOrgId
	String brName
	String vcCompCode
	String vcAddress1
	String vcAddress2
	String vcAddress3
	String vcCity
	String vcState
	String vcCountry
	String vcCstNo
	Date dtCstDate
	String vcLstNo
	Date dtLstDate
	String vcTelephone1
	String vcTelephone2
	String vcPanNo
	String vcTanNo
	String vcTdsCircle
	Character chActive
	String vcIecNo
	String vcAddress4
	String vcAddress5
	String vcAddress6
	String vcAddress7
	String vcClassCode
	String vcFaxNo
	String vcEmailNo
	String vcChallanNo
	String vcRegNo
	String vcPfNo
	String vcEsiNo
	String vcEccNo
	String vcDivision
	String vcRange
	String vcCompInitial
	String chStatUpFlag
	String chStatFlag
	Date dtModDate
	String vcAuthCode
	String ebsOrgMap
	String fullAddress
	BigDecimal vendorId
	String franchiseName

	static mapping = {
		table "BR_MST_TAB"
		
		id name: 'brCode', generator : 'assigned'

		version false
	}

	static constraints = {
		brCode maxSize: 10
		ebsOrgId nullable: true
		brName nullable: true, maxSize: 50
		vcCompCode maxSize: 2
		vcAddress1 nullable: true, maxSize: 30
		vcAddress2 nullable: true, maxSize: 30
		vcAddress3 nullable: true, maxSize: 30
		vcCity nullable: true, maxSize: 30
		vcState nullable: true, maxSize: 30
		vcCountry nullable: true, maxSize: 30
		vcCstNo nullable: true, maxSize: 20
		dtCstDate nullable: true
		vcLstNo nullable: true, maxSize: 20
		dtLstDate nullable: true
		vcTelephone1 nullable: true, maxSize: 30
		vcTelephone2 nullable: true, maxSize: 30
		vcPanNo nullable: true, maxSize: 25
		vcTanNo nullable: true, maxSize: 25
		vcTdsCircle nullable: true, maxSize: 50
		chActive nullable: true, maxSize: 1
		vcIecNo nullable: true, maxSize: 100
		vcAddress4 nullable: true, maxSize: 30
		vcAddress5 nullable: true, maxSize: 30
		vcAddress6 nullable: true, maxSize: 30
		vcAddress7 nullable: true, maxSize: 30
		vcClassCode nullable: true, maxSize: 4
		vcFaxNo nullable: true, maxSize: 30
		vcEmailNo nullable: true, maxSize: 30
		vcChallanNo nullable: true, maxSize: 4
		vcRegNo nullable: true, maxSize: 30
		vcPfNo nullable: true, maxSize: 30
		vcEsiNo nullable: true, maxSize: 30
		vcEccNo nullable: true, maxSize: 30
		vcDivision nullable: true, maxSize: 30
		vcRange nullable: true, maxSize: 30
		vcCompInitial nullable: true, maxSize: 3
		chStatUpFlag nullable: true, maxSize: 2
		chStatFlag nullable: true, maxSize: 2
		dtModDate nullable: true
		vcAuthCode nullable: true, maxSize: 2
		ebsOrgMap nullable: true, maxSize: 10
		fullAddress nullable: true, maxSize: 100
		vendorId nullable: true
		franchiseName nullable: true, maxSize: 200
	}
}
