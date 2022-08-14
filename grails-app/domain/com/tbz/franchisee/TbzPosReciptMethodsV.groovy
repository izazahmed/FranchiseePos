package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class TbzPosReciptMethodsV implements Serializable {

	Long receiptMethodId
	String receiptMethodName
	String paymentTypeCode
	Date receiptMethodStartDate
	Date receiptMethodEndDate
	String orgId
	String organizationName
	String bankName
	Long bankAccountId
	String bankAccountName
	String bankAccountNum
	Date startDate
	Date endDate
	String cashAccount
	String cashAccountDesc

	static mapping = {
		table "tbz_pos_recipt_methods_v"
		id name: 'bankAccountId', generator : 'assigned'
		version false
	}

	static constraints = {
		receiptMethodName maxSize: 30
		paymentTypeCode nullable: true, maxSize: 150
		receiptMethodEndDate nullable: true
		orgId nullable: true, maxSize: 2
		organizationName nullable: true, maxSize: 240
		bankName nullable: true, maxSize: 100
		bankAccountId nullable: true
		bankAccountName nullable: true, maxSize: 100
		bankAccountNum nullable: true, maxSize: 30
		startDate nullable: true
		endDate nullable: true
		cashAccount nullable: true, maxSize: 25
		cashAccountDesc nullable: true, maxSize: 240
	}
}
