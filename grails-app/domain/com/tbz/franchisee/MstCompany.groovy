package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class MstCompany implements Serializable {

	String vcDefaultComp
	String vcCompCode
	String vcCompanyName
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
	Character chAuthReqd
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
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	Date dtField1
	Date dtField2
	BigDecimal nuField1
	BigDecimal nuField2
	String brCode

	static mapping = {
		table "MST_COMPANY"
		id name: 'vcCompCode', generator : 'assigned'
		version false
	}

	static constraints = {
		vcDefaultComp nullable: true, maxSize: 2
		vcCompCode nullable: true, maxSize: 2
		vcCompanyName nullable: true, maxSize: 50
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
		chAuthReqd nullable: true, maxSize: 1
		vcPanNo nullable: true, maxSize: 25
		vcTanNo nullable: true, maxSize: 25
		vcTdsCircle nullable: true, maxSize: 50
		chActive nullable: true, maxSize: 1
		vcIecNo nullable: true, maxSize: 20
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
		vcField1 nullable: true, maxSize: 30
		vcField2 nullable: true, maxSize: 30
		vcField3 nullable: true, maxSize: 30
		vcField4 nullable: true, maxSize: 30
		dtField1 nullable: true
		dtField2 nullable: true
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		brCode nullable: true, maxSize: 10
	}
}
