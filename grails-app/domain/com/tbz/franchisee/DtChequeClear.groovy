package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtChequeClear implements Serializable {

	String vcCompCode
	String brCode
	String vcCustFname
	String vcCustMname
	String vcCustLname
	String vcVoucherNo
	Date dtVoucherDate
	String vcChqNo
	Date dtChqDate
	Date dtClearDate
	Character chAuthFlag
	Date dtCreationDate
	String vcCreationBy
	Date dtUpdateDate
	String vcUpdateBy
	String vcCustId
	BigDecimal nuAmount
	String vcTranType
	BigDecimal nuReceiptId
	BigDecimal nuBankCode
	String vcBankName
	String vcOldPaymentType

	static mapping = {
		table "DT_CHEQUE_CLEAR"
		
		id name: 'vcCustId', generator : 'assigned'	
		
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 3
		brCode nullable: true, maxSize: 10
		vcCustFname nullable: true, maxSize: 70
		vcCustMname nullable: true, maxSize: 70
		vcCustLname nullable: true, maxSize: 70
		vcVoucherNo nullable: true, maxSize: 30
		dtVoucherDate nullable: true
		vcChqNo nullable: true, maxSize: 30
		dtChqDate nullable: true
		dtClearDate nullable: true
		chAuthFlag nullable: true, maxSize: 1
		dtCreationDate nullable: true
		vcCreationBy nullable: true, maxSize: 5
		dtUpdateDate nullable: true
		vcUpdateBy nullable: true, maxSize: 5
		vcCustId nullable: true, maxSize: 30
		nuAmount nullable: true
		vcTranType nullable: true, maxSize: 50
		nuReceiptId nullable: true
		nuBankCode nullable: true
		vcBankName nullable: true, maxSize: 100
		vcOldPaymentType nullable: true, maxSize: 50
	}
}
