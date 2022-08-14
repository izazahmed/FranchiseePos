package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtBill implements Serializable {

	String vcCompCode
	String brCode
	String vcOrganizationName
	String vcBillNo
	Date dtBillDate
	String vcPurity
	String vcItemDet
	String vcItemCode
	String vcItemDesc
	String vcSubCode
	String vcSubInvCode
	String vcDeptt
	String vcDepttDesc
	String vcPrimaryUom
	String vcSecondryUom
	String vcMetalColour
	String vcVarityCode
	String vcType
	BigDecimal nuNang
	BigDecimal nuNangPcs
	BigDecimal nuPcs
	BigDecimal nuNettWtt
	BigDecimal nuGrossWtt
	String vcCalFlag
	BigDecimal nuRate
	String vcTaxCode
	String vcTaxName
	BigDecimal nuTax
	BigDecimal nuCarratWtt
	String vcStatFlag
	String vcStatUpFlag
	Date dtModDate
	String vcAuthCode
	BigDecimal nuAmount
	String vcField1
	String vcField2
	String vcField3
	String vcField4
	Date dtField1
	Date dtField2
	BigDecimal nuField1
	BigDecimal nuField2
	String vcRefNo
	Date dtRefDate
	BigDecimal nuOtherCharges
	BigDecimal nuNettWtt1
	BigDecimal nuGrossWtt1
	BigDecimal nuCarratWtt1
	BigDecimal nuPcs1
	String vcField5
	String vcField6
	String vcField7
	String vcField8
	String vcField9
	String vcField10
	String vcField11
	String vcField12
	String vcField13
	String vcField14
	BigDecimal nuField3
	BigDecimal nuField4
	BigDecimal nuField5
	BigDecimal nuField6
	BigDecimal nuField7
	BigDecimal nuField8
	BigDecimal nuField9
	BigDecimal nuField10
	BigDecimal nuField11
	BigDecimal nuField12
	Date dtField3
	Date dtField4
	Date dtField5
	Date dtField6
	Date dtField7
	Date dtField8
	Date dtField9
	Date dtField10
	Date dtField11
	Date dtField12
	String vcCategory
	BigDecimal nuActualRate
	String vcEbsOrderNo

	static mapping = {
		id composite: ["vcCompCode", "brCode", "vcOrganizationName", "vcBillNo", "dtBillDate", "vcPurity", "vcItemDet", "vcItemCode", "vcItemDesc", "vcSubCode", "vcSubInvCode", "vcDeptt", "vcDepttDesc", "vcPrimaryUom", "vcSecondryUom", "vcMetalColour", "vcVarityCode", "vcType", "nuNang", "nuNangPcs", "nuPcs", "nuNettWtt", "nuGrossWtt", "vcCalFlag", "nuRate", "vcTaxCode", "vcTaxName", "nuTax", "nuCarratWtt", "vcStatFlag", "vcStatUpFlag", "dtModDate", "vcAuthCode", "nuAmount", "vcField1", "vcField2", "vcField3", "vcField4", "dtField1", "dtField2", "nuField1", "nuField2", "vcRefNo", "dtRefDate", "nuOtherCharges", "nuNettWtt1", "nuGrossWtt1", "nuCarratWtt1", "nuPcs1", "vcField5", "vcField6", "vcField7", "vcField8", "vcField9", "vcField10", "vcField11", "vcField12", "vcField13", "vcField14", "nuField3", "nuField4", "nuField5", "nuField6", "nuField7", "nuField8", "nuField9", "nuField10", "nuField11", "nuField12", "dtField3", "dtField4", "dtField5", "dtField6", "dtField7", "dtField8", "dtField9", "dtField10", "dtField11", "dtField12", "vcCategory", "nuActualRate", "vcEbsOrderNo"]
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		vcOrganizationName nullable: true, maxSize: 200
		vcBillNo maxSize: 30
		dtBillDate nullable: true
		vcPurity nullable: true, maxSize: 20
		vcItemDet nullable: true, maxSize: 50
		vcItemCode nullable: true, maxSize: 30
		vcItemDesc nullable: true, maxSize: 150
		vcSubCode nullable: true, maxSize: 20
		vcSubInvCode nullable: true, maxSize: 15
		vcDeptt nullable: true, maxSize: 20
		vcDepttDesc nullable: true, maxSize: 20
		vcPrimaryUom nullable: true, maxSize: 10
		vcSecondryUom nullable: true, maxSize: 10
		vcMetalColour nullable: true, maxSize: 10
		vcVarityCode nullable: true, maxSize: 20
		vcType nullable: true, maxSize: 20
		nuNang nullable: true, scale: 3
		nuNangPcs nullable: true, scale: 3
		nuPcs nullable: true, scale: 3
		nuNettWtt nullable: true, scale: 3
		nuGrossWtt nullable: true, scale: 3
		vcCalFlag nullable: true, maxSize: 2
		nuRate nullable: true
		vcTaxCode nullable: true, maxSize: 50
		vcTaxName nullable: true, maxSize: 50
		nuTax nullable: true
		nuCarratWtt nullable: true, scale: 3
		vcStatFlag nullable: true, maxSize: 2
		vcStatUpFlag nullable: true, maxSize: 2
		dtModDate nullable: true
		vcAuthCode nullable: true, maxSize: 2
		nuAmount nullable: true
		vcField1 nullable: true, maxSize: 30
		vcField2 nullable: true, maxSize: 30
		vcField3 nullable: true, maxSize: 30
		vcField4 nullable: true, maxSize: 30
		dtField1 nullable: true
		dtField2 nullable: true
		nuField1 nullable: true, scale: 3
		nuField2 nullable: true, scale: 3
		vcRefNo nullable: true, maxSize: 12
		dtRefDate nullable: true
		nuOtherCharges nullable: true
		nuNettWtt1 nullable: true, scale: 3
		nuGrossWtt1 nullable: true, scale: 3
		nuCarratWtt1 nullable: true, scale: 3
		nuPcs1 nullable: true, scale: 3
		vcField5 nullable: true, maxSize: 50
		vcField6 nullable: true, maxSize: 50
		vcField7 nullable: true, maxSize: 50
		vcField8 nullable: true, maxSize: 50
		vcField9 nullable: true, maxSize: 50
		vcField10 nullable: true, maxSize: 50
		vcField11 nullable: true, maxSize: 50
		vcField12 nullable: true, maxSize: 50
		vcField13 nullable: true, maxSize: 50
		vcField14 nullable: true, maxSize: 50
		nuField3 nullable: true, scale: 3
		nuField4 nullable: true, scale: 3
		nuField5 nullable: true, scale: 3
		nuField6 nullable: true, scale: 3
		nuField7 nullable: true, scale: 3
		nuField8 nullable: true, scale: 3
		nuField9 nullable: true, scale: 3
		nuField10 nullable: true, scale: 3
		nuField11 nullable: true, scale: 3
		nuField12 nullable: true, scale: 3
		dtField3 nullable: true
		dtField4 nullable: true
		dtField5 nullable: true
		dtField6 nullable: true
		dtField7 nullable: true
		dtField8 nullable: true
		dtField9 nullable: true
		dtField10 nullable: true
		dtField11 nullable: true
		dtField12 nullable: true
		vcCategory nullable: true, maxSize: 20
		nuActualRate nullable: true
		vcEbsOrderNo nullable: true, maxSize: 50
	}
}
