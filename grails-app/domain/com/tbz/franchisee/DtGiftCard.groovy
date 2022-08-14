package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class DtGiftCard implements Serializable {

	String vcCompCode
	String brCode
	String giftCardNo
	Date giftCardDate
	BigDecimal receiptNumber
	Date receiptDate
	String chPayMode
	Integer nuSrNo
	BigDecimal nuAmount
	BigDecimal vcChqNo
	Date dtChqDate
	BigDecimal vcCcNo
	String nuBankCode
	String vcApprovalNo
	Date dtApprovalDate
	Long nuCurrCode
	BigDecimal nuExchangeRate
	String vcGifVouNo
	BigDecimal nuFcAmount
	Character chCancelFlag
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
	String vcApprovalId
	String vcApprovalName
	String vcReferedId
	String vcReferedName
	String vcCardType
	String vcCustBank
	String nuCustBank
	Long nuReceiptId
	String tbzBank
	String vcCurrencyName
	String vcEbsOrderNo
	String chStage
	String vcCreationBy
	Date dtCreationDate
	String vcUpdateBy
	Date dtUpdateDate
	String vcRemark
	String vcMerchantId
	String vcStoreId
	String vcTerminalId
	String vcUserId
	String vcUserPwd
	String vcTxnDate
	String vcTxnTime
	String vcBatchNo
	String vcTxnSeq
	String vcReqAmt
	String vcReference
	String vcCurrencyCode
	String vcCardNo
	String vcCardPin
	String vcSalesRepId
	String vcSubmitrequest
	String vcRetResponsecode
	String vcRetResponceamount
	String vcRetBalanceamount
	String vcRetRewardamount
	String vcRetRewardbalance
	String vcRetCashbalance
	String vcRetRrno
	String vcRetApprovalcode
	String vcRetCardstatus
	String vcRetExpirydate
	String vcVoidRetRrno
	Short nuMcId
	Short nuMcNo
	String vcActionType

	static mapping = {
		id composite: ["vcCompCode", "brCode", "giftCardNo", "giftCardDate", "receiptNumber", "receiptDate", "chPayMode", "nuSrNo", "nuAmount", "vcChqNo", "dtChqDate", "vcCcNo", "nuBankCode", "vcApprovalNo", "dtApprovalDate", "nuCurrCode", "nuExchangeRate", "vcGifVouNo", "nuFcAmount", "chCancelFlag", "vcField1", "vcField2", "vcField3", "vcField4", "vcField5", "vcField6", "vcField7", "vcField8", "vcField9", "vcField10", "nuField1", "nuField2", "nuField3", "nuField4", "nuField5", "nuField6", "nuField7", "nuField8", "nuField9", "nuField10", "dtField1", "dtField2", "dtField3", "dtField4", "dtField5", "dtField6", "dtField7", "dtField8", "dtField9", "dtField10", "vcApprovalId", "vcApprovalName", "vcReferedId", "vcReferedName", "vcCardType", "vcCustBank", "nuCustBank", "nuReceiptId", "tbzBank", "vcCurrencyName", "vcEbsOrderNo", "chStage", "vcCreationBy", "dtCreationDate", "vcUpdateBy", "dtUpdateDate", "vcRemark", "vcMerchantId", "vcStoreId", "vcTerminalId", "vcUserId", "vcUserPwd", "vcTxnDate", "vcTxnTime", "vcBatchNo", "vcTxnSeq", "vcReqAmt", "vcReference", "vcCurrencyCode", "vcCardNo", "vcCardPin", "vcSalesRepId", "vcSubmitrequest", "vcRetResponsecode", "vcRetResponceamount", "vcRetBalanceamount", "vcRetRewardamount", "vcRetRewardbalance", "vcRetCashbalance", "vcRetRrno", "vcRetApprovalcode", "vcRetCardstatus", "vcRetExpirydate", "vcVoidRetRrno", "nuMcId", "nuMcNo", "vcActionType"]
		version false
	}

	static constraints = {
		vcCompCode nullable: true, maxSize: 2
		brCode nullable: true, maxSize: 10
		giftCardNo nullable: true, maxSize: 50
		giftCardDate nullable: true
		receiptNumber nullable: true
		receiptDate nullable: true
		chPayMode nullable: true, maxSize: 2
		nuSrNo nullable: true
		nuAmount nullable: true
		vcChqNo nullable: true
		dtChqDate nullable: true
		vcCcNo nullable: true
		nuBankCode nullable: true, maxSize: 30
		vcApprovalNo nullable: true, maxSize: 30
		dtApprovalDate nullable: true
		nuCurrCode nullable: true
		nuExchangeRate nullable: true
		vcGifVouNo nullable: true, maxSize: 30
		nuFcAmount nullable: true
		chCancelFlag nullable: true, maxSize: 1
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
		vcApprovalId nullable: true, maxSize: 10
		vcApprovalName nullable: true, maxSize: 100
		vcReferedId nullable: true, maxSize: 10
		vcReferedName nullable: true, maxSize: 100
		vcCardType nullable: true, maxSize: 50
		vcCustBank nullable: true, maxSize: 50
		nuCustBank nullable: true, maxSize: 50
		nuReceiptId nullable: true
		tbzBank nullable: true, maxSize: 200
		vcCurrencyName nullable: true, maxSize: 50
		vcEbsOrderNo nullable: true, maxSize: 50
		chStage nullable: true, maxSize: 1
		vcCreationBy nullable: true, maxSize: 3
		dtCreationDate nullable: true
		vcUpdateBy nullable: true, maxSize: 3
		dtUpdateDate nullable: true
		vcRemark nullable: true, maxSize: 100
		vcMerchantId nullable: true, maxSize: 40
		vcStoreId nullable: true, maxSize: 40
		vcTerminalId nullable: true, maxSize: 30
		vcUserId nullable: true, maxSize: 10
		vcUserPwd nullable: true, maxSize: 10
		vcTxnDate nullable: true, maxSize: 8
		vcTxnTime nullable: true, maxSize: 6
		vcBatchNo nullable: true, maxSize: 8
		vcTxnSeq nullable: true, maxSize: 6
		vcReqAmt nullable: true, maxSize: 8
		vcReference nullable: true, maxSize: 15
		vcCurrencyCode nullable: true, maxSize: 3
		vcCardNo nullable: true, maxSize: 16
		vcCardPin nullable: true, maxSize: 6
		vcSalesRepId nullable: true, maxSize: 6
		vcSubmitrequest nullable: true, maxSize: 100
		vcRetResponsecode nullable: true, maxSize: 100
		vcRetResponceamount nullable: true, maxSize: 100
		vcRetBalanceamount nullable: true, maxSize: 100
		vcRetRewardamount nullable: true, maxSize: 100
		vcRetRewardbalance nullable: true, maxSize: 100
		vcRetCashbalance nullable: true, maxSize: 100
		vcRetRrno nullable: true, maxSize: 100
		vcRetApprovalcode nullable: true, maxSize: 100
		vcRetCardstatus nullable: true, maxSize: 100
		vcRetExpirydate nullable: true, maxSize: 100
		vcVoidRetRrno nullable: true, maxSize: 50
		nuMcId nullable: true
		nuMcNo nullable: true
		vcActionType nullable: true, maxSize: 50
	}
}
