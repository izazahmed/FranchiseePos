package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class TbzPosItemcodeLabelV implements Serializable {

	BigDecimal inventoryItemId
	String item
	String preciousMetalStones
	String productCategory
	String style
	String subStyle
	String variety
	String purity
	String metalColour
	String diamondUniqueIdentifier
	String diamColorClarity
	String diamDesignNumber
	String itemDescription
	BigDecimal organizationId
	String orgId
	String subinventoryCode
	String locator
	String uom
	String secondaryUomCode
	String organizationName
	String labelNumber
	String karigarCode
	BigDecimal grossWeight
	BigDecimal netWeight
	BigDecimal nang
	BigDecimal caretWt
	BigDecimal pieces
	BigDecimal makingChargesSp
	String makingChargesSpPerc
	BigDecimal otherChargesSp
	String calculationBasisGold
	String calculationBasisMaking
	BigDecimal goldRate
	String purchaseType
	BigDecimal sellingPrice
	String calculationBasis
	String perPieces
	BigDecimal goldRateFrLine
	BigDecimal otherCharges
	BigDecimal taxAmount
	BigDecimal taxPercetange
	BigDecimal discount
	BigDecimal sellingPriceWithoutTax
	BigDecimal makingChargesSpOrig
	BigDecimal sellingPriceOrig
	BigDecimal varietyCharges
	BigDecimal makingChargesSpDis
	String productCategoryFrLine
	String typeOfJewellery
	String vcCategory
	String product
	String purityFrLine
	BigDecimal makingChargesSpFrLine
	String calcSource
	BigDecimal goldRateSpOrig
	String validateLot
	Date saleDate
	String modelNumber
	String subCategory
	String sizeColorClarity
	String jewelleryCollectionFlag
	String poNumber
	String receiptNumber
	String parentLabelNumber
	String childCategory
	String karigarCodeRepair
	String karigarRefNumber
	BigDecimal ghat
	BigDecimal kundan
	String karigarDesignNumber
	Date labelDate
	String karigarName

	static mapping = {
		id composite: ["inventoryItemId", "item", "preciousMetalStones", "productCategory", "style", "subStyle", "variety", "purity", "metalColour", "diamondUniqueIdentifier", "diamColorClarity", "diamDesignNumber", "itemDescription", "organizationId", "orgId", "subinventoryCode", "locator", "uom", "secondaryUomCode", "organizationName", "labelNumber", "karigarCode", "grossWeight", "netWeight", "nang", "caretWt", "pieces", "makingChargesSp", "makingChargesSpPerc", "otherChargesSp", "calculationBasisGold", "calculationBasisMaking", "goldRate", "purchaseType", "sellingPrice", "calculationBasis", "perPieces", "goldRateFrLine", "otherCharges", "taxAmount", "taxPercetange", "discount", "sellingPriceWithoutTax", "makingChargesSpOrig", "sellingPriceOrig", "varietyCharges", "makingChargesSpDis", "productCategoryFrLine", "typeOfJewellery", "vcCategory", "product", "purityFrLine", "makingChargesSpFrLine", "calcSource", "goldRateSpOrig", "validateLot", "saleDate", "modelNumber", "subCategory", "sizeColorClarity", "jewelleryCollectionFlag", "poNumber", "receiptNumber", "parentLabelNumber", "childCategory", "karigarCodeRepair", "karigarRefNumber", "ghat", "kundan", "karigarDesignNumber", "labelDate", "karigarName"]
		version false
	}

	static constraints = {
		inventoryItemId nullable: true
		item nullable: true, maxSize: 40
		preciousMetalStones nullable: true, maxSize: 240
		productCategory nullable: true, maxSize: 240
		style nullable: true, maxSize: 240
		subStyle nullable: true, maxSize: 240
		variety nullable: true, maxSize: 240
		purity nullable: true, maxSize: 240
		metalColour nullable: true, maxSize: 240
		diamondUniqueIdentifier nullable: true, maxSize: 240
		diamColorClarity nullable: true, maxSize: 150
		diamDesignNumber nullable: true, maxSize: 150
		itemDescription nullable: true
		organizationId nullable: true
		orgId nullable: true, maxSize: 20
		subinventoryCode nullable: true, maxSize: 10
		locator nullable: true, maxSize: 40
		uom nullable: true, maxSize: 40
		secondaryUomCode nullable: true, maxSize: 3
		organizationName nullable: true, maxSize: 240
		labelNumber nullable: true, maxSize: 80, unique: true
		karigarCode nullable: true, maxSize: 150
		grossWeight nullable: true
		netWeight nullable: true
		nang nullable: true
		caretWt nullable: true
		pieces nullable: true
		makingChargesSp nullable: true
		makingChargesSpPerc nullable: true, maxSize: 150
		otherChargesSp nullable: true
		calculationBasisGold nullable: true, maxSize: 150
		calculationBasisMaking nullable: true, maxSize: 150
		goldRate nullable: true
		purchaseType nullable: true, maxSize: 150
		sellingPrice nullable: true
		calculationBasis nullable: true, maxSize: 1
		perPieces nullable: true, maxSize: 1
		goldRateFrLine nullable: true
		otherCharges nullable: true
		taxAmount nullable: true
		taxPercetange nullable: true
		discount nullable: true
		sellingPriceWithoutTax nullable: true
		makingChargesSpOrig nullable: true
		sellingPriceOrig nullable: true
		varietyCharges nullable: true
		makingChargesSpDis nullable: true
		productCategoryFrLine nullable: true, maxSize: 100
		typeOfJewellery nullable: true, maxSize: 100
		vcCategory nullable: true, maxSize: 30
		product nullable: true, maxSize: 30
		purityFrLine nullable: true, maxSize: 30
		makingChargesSpFrLine nullable: true
		calcSource nullable: true, maxSize: 100
		goldRateSpOrig nullable: true
		validateLot nullable: true, maxSize: 1
		saleDate nullable: true
		modelNumber nullable: true
		subCategory nullable: true, maxSize: 20
		sizeColorClarity nullable: true, maxSize: 20
		jewelleryCollectionFlag nullable: true, maxSize: 20
		poNumber nullable: true, maxSize: 20
		receiptNumber nullable: true, maxSize: 20
		parentLabelNumber nullable: true, maxSize: 20
		childCategory nullable: true, maxSize: 10
		karigarCodeRepair nullable: true, maxSize: 40
		karigarRefNumber nullable: true, maxSize: 50
		ghat nullable: true
		kundan nullable: true
		karigarDesignNumber nullable: true, maxSize: 100
		labelDate nullable: true
		karigarName nullable: true, maxSize: 4000
	}
}
