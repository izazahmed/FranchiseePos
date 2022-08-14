package com.tbz.franchisee

class MtlItemLocations {

	BigDecimal inventoryLocationId
	BigDecimal organizationId
	Date disableDate
	String subinventoryCode
	String segment1
	String attributeCategory
	String attribute1
	String attribute2
	String attribute3
	String attribute4
	String attribute5
	String attribute6
	String attribute7
	String attribute8
	String attribute9
	String attribute10
	String attribute11
	String attribute12
	String attribute13
	String attribute14
	String attribute15
	Date lastUpdateDate
	BigDecimal lastUpdatedBy
	Date creationDate
	BigDecimal createdBy
	BigDecimal lastUpdateLogin
	String description

	static mapping = {
		table "MTL_ITEM_LOCATIONS"
		id name: 'subinventoryCode', generator : 'assigned'
		version false
	}

	static constraints = {
		organizationId nullable: true
		disableDate nullable: true
		subinventoryCode nullable: true, maxSize: 10
		segment1 nullable: true, maxSize: 40
		attributeCategory nullable: true, maxSize: 30
		attribute1 nullable: true, maxSize: 150
		attribute2 nullable: true, maxSize: 150
		attribute3 nullable: true, maxSize: 150
		attribute4 nullable: true, maxSize: 150
		attribute5 nullable: true, maxSize: 150
		attribute6 nullable: true, maxSize: 150
		attribute7 nullable: true, maxSize: 150
		attribute8 nullable: true, maxSize: 150
		attribute9 nullable: true, maxSize: 150
		attribute10 nullable: true, maxSize: 150
		attribute11 nullable: true, maxSize: 150
		attribute12 nullable: true, maxSize: 150
		attribute13 nullable: true, maxSize: 150
		attribute14 nullable: true, maxSize: 150
		attribute15 nullable: true, maxSize: 150
		lastUpdateDate nullable: true
		lastUpdatedBy nullable: true
		creationDate nullable: true
		createdBy nullable: true
		lastUpdateLogin nullable: true
		description nullable: true, maxSize: 50
	}
}
