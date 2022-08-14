package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtCrossAdvSettle implements Serializable {

	String brCode
	String vcTransferedBr
	String vcAdvOrdNo
	Date dtAdvOrdDate
	String vcCustFname
	String vcCustMname
	String vcCustLname
	String vcRefAdv
	Date dtRefAdv
	BigDecimal nuTotAmt
	BigDecimal nuBalAmt
	Character chActive
	String chSettleFlag
	String vcCreationBy
	Date dtCreationDate
	String vcType

	static mapping = {
		table "DT_CROSS_ADV_SETTLE" 
		//id composite: ["brCode", "vcTransferedBr", "vcAdvOrdNo", "dtAdvOrdDate", "vcCustFname", "vcCustMname", "vcCustLname", "vcRefAdv", "dtRefAdv", "nuTotAmt", "nuBalAmt", "chActive", "chSettleFlag", "vcCreationBy", "dtCreationDate", "vcType"]
		//id name: 'brCode', generator : 'assigned'
		version false
	}

	static constraints = {
		brCode nullable: true, maxSize: 10
		vcTransferedBr nullable: true, maxSize: 10
		vcAdvOrdNo nullable: true, maxSize: 50
		dtAdvOrdDate nullable: true
		vcCustFname nullable: true, maxSize: 70
		vcCustMname nullable: true, maxSize: 70
		vcCustLname nullable: true, maxSize: 70
		vcRefAdv nullable: true, maxSize: 20
		dtRefAdv nullable: true
		nuTotAmt nullable: true
		nuBalAmt nullable: true
		chActive nullable: true, maxSize: 1
		chSettleFlag nullable: true, maxSize: 1
		vcCreationBy nullable: true, maxSize: 5
		dtCreationDate nullable: true
		vcType nullable: true, maxSize: 10
	}
}
