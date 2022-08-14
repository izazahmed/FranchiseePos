package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class FndFlexValuesVl implements Serializable {

	String rowId
	Long flexValueSetId
	Long flexValueId
	String flexValue
	Date lastUpdateDate
	Long lastUpdatedBy
	Date creationDate
	Long createdBy
	Long lastUpdateLogin
	String enabledFlag
	String summaryFlag
	Date startDateActive
	Date endDateActive
	String parentFlexValueLow
	String parentFlexValueHigh
	Long structuredHierarchyLevel
	String hierarchyLevel
	String compiledValueAttributes
	String valueCategory
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
	String attribute16
	String attribute17
	String attribute18
	String attribute19
	String attribute20
	String attribute21
	String attribute22
	String attribute23
	String attribute24
	String attribute25
	String attribute26
	String attribute27
	String attribute28
	String attribute29
	String attribute30
	String attribute31
	String attribute32
	String attribute33
	String attribute34
	String attribute35
	String attribute36
	String attribute37
	String attribute38
	String attribute39
	String attribute40
	String attribute41
	String attribute42
	String attribute43
	String attribute44
	String attribute45
	String attribute46
	String attribute47
	String attribute48
	String attribute49
	String attribute50
	String flexValueMeaning
	String description
	Long attributeSortOrder

	static mapping = {
		table "FND_FLEX_VALUES_VL"
		id name: 'flexValue', generator : 'assigned'
		version false
	}

	static constraints = {
		rowId nullable: true
		flexValue maxSize: 150
		lastUpdateLogin nullable: true
		enabledFlag maxSize: 1
		summaryFlag maxSize: 1
		startDateActive nullable: true
		endDateActive nullable: true
		parentFlexValueLow nullable: true, maxSize: 60
		parentFlexValueHigh nullable: true, maxSize: 60
		structuredHierarchyLevel nullable: true
		hierarchyLevel nullable: true, maxSize: 30
		compiledValueAttributes nullable: true, maxSize: 2000
		valueCategory nullable: true, maxSize: 30
		attribute1 nullable: true, maxSize: 240
		attribute2 nullable: true, maxSize: 240
		attribute3 nullable: true, maxSize: 240
		attribute4 nullable: true, maxSize: 240
		attribute5 nullable: true, maxSize: 240
		attribute6 nullable: true, maxSize: 240
		attribute7 nullable: true, maxSize: 240
		attribute8 nullable: true, maxSize: 240
		attribute9 nullable: true, maxSize: 240
		attribute10 nullable: true, maxSize: 240
		attribute11 nullable: true, maxSize: 240
		attribute12 nullable: true, maxSize: 240
		attribute13 nullable: true, maxSize: 240
		attribute14 nullable: true, maxSize: 240
		attribute15 nullable: true, maxSize: 240
		attribute16 nullable: true, maxSize: 240
		attribute17 nullable: true, maxSize: 240
		attribute18 nullable: true, maxSize: 240
		attribute19 nullable: true, maxSize: 240
		attribute20 nullable: true, maxSize: 240
		attribute21 nullable: true, maxSize: 240
		attribute22 nullable: true, maxSize: 240
		attribute23 nullable: true, maxSize: 240
		attribute24 nullable: true, maxSize: 240
		attribute25 nullable: true, maxSize: 240
		attribute26 nullable: true, maxSize: 240
		attribute27 nullable: true, maxSize: 240
		attribute28 nullable: true, maxSize: 240
		attribute29 nullable: true, maxSize: 240
		attribute30 nullable: true, maxSize: 240
		attribute31 nullable: true, maxSize: 240
		attribute32 nullable: true, maxSize: 240
		attribute33 nullable: true, maxSize: 240
		attribute34 nullable: true, maxSize: 240
		attribute35 nullable: true, maxSize: 240
		attribute36 nullable: true, maxSize: 240
		attribute37 nullable: true, maxSize: 240
		attribute38 nullable: true, maxSize: 240
		attribute39 nullable: true, maxSize: 240
		attribute40 nullable: true, maxSize: 240
		attribute41 nullable: true, maxSize: 240
		attribute42 nullable: true, maxSize: 240
		attribute43 nullable: true, maxSize: 240
		attribute44 nullable: true, maxSize: 240
		attribute45 nullable: true, maxSize: 240
		attribute46 nullable: true, maxSize: 240
		attribute47 nullable: true, maxSize: 240
		attribute48 nullable: true, maxSize: 240
		attribute49 nullable: true, maxSize: 240
		attribute50 nullable: true, maxSize: 240
		flexValueMeaning maxSize: 150
		description nullable: true, maxSize: 240
		attributeSortOrder nullable: true
	}
}
