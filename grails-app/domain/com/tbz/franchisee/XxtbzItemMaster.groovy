package com.tbz.franchisee

class XxtbzItemMaster {

	BigDecimal inventoryItemId
	String itemCode
	String itemCategory
	String product
	String productStyle
	String subStyle
	String itemVariety
	String purity
	String colour
	String description
	BigDecimal createdBy
	Date creationDate
	BigDecimal lastUpdatedBy
	Date lastUpdateDate
	String primaryUomCode
	String diamondIdentifier
	String lotControl

	static mapping = {
		table "XXTBZ_ITEM_MASTER"
		
		id name: 'purity', generator : 'assigned'

		version false
	}

	static constraints = {
		itemCode nullable: true, maxSize: 40
		itemCategory nullable: true, maxSize: 240
		product nullable: true, maxSize: 240
		productStyle nullable: true, maxSize: 240
		subStyle nullable: true, maxSize: 240
		itemVariety nullable: true, maxSize: 240
		purity nullable: true, maxSize: 240
		colour nullable: true, maxSize: 240
		description nullable: true
		createdBy nullable: true
		creationDate nullable: true
		lastUpdatedBy nullable: true
		lastUpdateDate nullable: true
		primaryUomCode nullable: true, maxSize: 40
		diamondIdentifier nullable: true, maxSize: 20
		lotControl nullable: true, maxSize: 20
	}
}
