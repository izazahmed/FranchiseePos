package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class HdCash implements Serializable {

	String vcCompCode
	String brCode
	String vcVoucherNo
	Date dtVoucherDate
	String vcTokenNo
	Date dtTokenDate
	String vcAdvOrdNo
	Date dtAdvOrdDate
	String vcCategory
	String vcCustType
	String vcCustFname
	String vcCustMname
	String vcCustLname
	String vcAddress1
	String vcAddress2
	String vcAddress3
	String vcAddress4
	String vcPhone
	String vcEmail
	String vcState
	String vcPostalCode
	String vcActDesc
	String vcActType
	String vcCustAdd
	String vcCity
	Long nuPostelCode
	String vcCustClass
	String vcPayTerms
	Long nuRecAct
	Character chCancelFlag
	String vcTaxCode
	Character chClose
	Short nuMcId
	Short nuMcNo
	Integer nuStationary
	String vcFinNo
	String chGoldType
	String vcSaleVoucherNo
	Date dtUpdateDate
	Date dtCreationDate
	String vcCreationBy
	String vcUpdateBy
	Character chStage
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	String vcField5
	String vcField6
	String vcField7
	String vcField8
	String vcField9
	String vcField10
	BigDecimal nuField1
	BigDecimal nuField2
	BigDecimal nuField3
	BigDecimal nuField4
	BigDecimal nuField5
	BigDecimal nuField6
	BigDecimal nuField7
	BigDecimal nuField8
	BigDecimal nuField9
	BigDecimal nuField10
	Date dtField1
	Date dtField2
	Date dtField3
	Date dtField4
	Date dtField5
	Date dtField6
	Date dtField7
	Date dtField8
	Date dtField9
	Date dtField10
	String vcSrAdv
	Date dtSrAdv
	String vcRefSaleVoucherNo
	Date dtRefSaleVoucherDate
	String vcEbsFcust
	String vcEbsMcust
	String vcEbsLcust
	String vcKattan
	String vcCustId
	String vcEbsOrderNo
	String vcStockSaleType

	static mapping = {
		table "HD_CASH"
		id name: 'vcVoucherNo', generator : 'assigned'	
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		vcVoucherNo nullable: true, maxSize: 30
		dtVoucherDate nullable: true
		vcTokenNo nullable: true, maxSize: 30
		dtTokenDate nullable: true
		vcAdvOrdNo nullable: true, maxSize: 50
		dtAdvOrdDate nullable: true
		vcCategory nullable: true, maxSize: 20
		vcCustType nullable: true, maxSize: 2
		vcCustFname nullable: true, maxSize: 50
		vcCustMname nullable: true, maxSize: 50
		vcCustLname nullable: true, maxSize: 50
		vcAddress1 nullable: true, maxSize: 100
		vcAddress2 nullable: true, maxSize: 70
		vcAddress3 nullable: true, maxSize: 70
		vcAddress4 nullable: true, maxSize: 70
		vcPhone nullable: true, maxSize: 30
		vcEmail nullable: true, maxSize: 100
		vcState nullable: true, maxSize: 70
		vcPostalCode nullable: true, maxSize: 30
		vcActDesc nullable: true, maxSize: 2
		vcActType nullable: true, maxSize: 2
		vcCustAdd nullable: true, maxSize: 500
		vcCity nullable: true, maxSize: 50
		nuPostelCode nullable: true
		vcCustClass nullable: true, maxSize: 2
		vcPayTerms nullable: true, maxSize: 30
		nuRecAct nullable: true
		chCancelFlag nullable: true, maxSize: 1
		vcTaxCode nullable: true, maxSize: 10
		chClose nullable: true, maxSize: 1
		nuMcId nullable: true
		nuMcNo nullable: true
		nuStationary nullable: true
		vcFinNo nullable: true, maxSize: 10
		chGoldType nullable: true, maxSize: 2
		vcSaleVoucherNo nullable: true, maxSize: 10
		dtUpdateDate nullable: true
		dtCreationDate nullable: true
		vcCreationBy nullable: true, maxSize: 5
		vcUpdateBy nullable: true, maxSize: 5
		chStage nullable: true, maxSize: 1
		vcField1 nullable: true, maxSize: 50
		vcField2 nullable: true, maxSize: 50
		vcField3 nullable: true, maxSize: 50
		vcField4 nullable: true, maxSize: 50
		vcField5 nullable: true, maxSize: 50
		vcField6 nullable: true, maxSize: 50
		vcField7 nullable: true, maxSize: 50
		vcField8 nullable: true, maxSize: 50
		vcField9 nullable: true, maxSize: 50
		vcField10 nullable: true, maxSize: 50
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		nuField3 nullable: true, scale: 3
		nuField4 nullable: true, scale: 3
		nuField5 nullable: true, scale: 3
		nuField6 nullable: true, scale: 3
		nuField7 nullable: true, scale: 3
		nuField8 nullable: true, scale: 3
		nuField9 nullable: true, scale: 3
		nuField10 nullable: true, scale: 3
		dtField1 nullable: true
		dtField2 nullable: true
		dtField3 nullable: true
		dtField4 nullable: true
		dtField5 nullable: true
		dtField6 nullable: true
		dtField7 nullable: true
		dtField8 nullable: true
		dtField9 nullable: true
		dtField10 nullable: true
		vcSrAdv nullable: true, maxSize: 20
		dtSrAdv nullable: true
		vcRefSaleVoucherNo nullable: true, maxSize: 50
		dtRefSaleVoucherDate nullable: true
		vcEbsFcust nullable: true, maxSize: 150
		vcEbsMcust nullable: true, maxSize: 150
		vcEbsLcust nullable: true, maxSize: 150
		vcKattan nullable: true, maxSize: 3
		vcCustId nullable: true, maxSize: 30
		vcEbsOrderNo nullable: true, maxSize: 50
		vcStockSaleType nullable: true, maxSize: 3
	}
}
