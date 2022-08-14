package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class CheckBounce implements Serializable {

	String vcCompCode
	String vcVoucherNo
	Date dtVoucherDate
	Integer nuAccountCode
	String chPayType
	BigDecimal nuAmount
	String vcNarration
	String brCode
	Integer nuMcId
	Integer nuMcNo
	String vcCategory
	String vcSaleVoucherNo
	Date dtSaleVoucherDate
	String vcCancelFlag
	String vcCustomerName
	BigDecimal nuSaleBillAmount
	String vcReceiptType
	String vcCustId
	BigDecimal cashReceiptNo
	String formType
	BigDecimal rate

	static mapping = {
		id composite: ["vcCompCode", "vcVoucherNo", "dtVoucherDate", "nuAccountCode", "chPayType", "nuAmount", "vcNarration", "brCode", "nuMcId", "nuMcNo", "vcCategory", "vcSaleVoucherNo", "dtSaleVoucherDate", "vcCancelFlag", "vcCustomerName", "nuSaleBillAmount", "vcReceiptType", "vcCustId", "cashReceiptNo", "formType", "rate"]
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		vcVoucherNo nullable: true, maxSize: 30
		dtVoucherDate nullable: true
		nuAccountCode nullable: true
		chPayType nullable: true, maxSize: 2
		nuAmount nullable: true, scale: 3
		vcNarration nullable: true, maxSize: 500
		brCode nullable: true, maxSize: 10
		nuMcId nullable: true
		nuMcNo nullable: true
		vcCategory nullable: true, maxSize: 10
		vcSaleVoucherNo nullable: true, maxSize: 50
		dtSaleVoucherDate nullable: true
		vcCancelFlag nullable: true, maxSize: 2
		vcCustomerName nullable: true, maxSize: 200
		nuSaleBillAmount nullable: true
		vcReceiptType nullable: true, maxSize: 20
		vcCustId nullable: true, maxSize: 30
		cashReceiptNo nullable: true
		formType nullable: true, maxSize: 20
		rate nullable: true
	}
}
