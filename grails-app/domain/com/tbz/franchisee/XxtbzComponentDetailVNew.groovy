package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class XxtbzComponentDetailVNew implements Serializable {

	String stoneType
	BigDecimal pieces
	BigDecimal caratWeight
	String lotNumber
	BigDecimal organizationId
	BigDecimal sellingPrice
	BigDecimal cpPrice
	String stoneQuality
	BigDecimal crtRange
	String rateBasis
	BigDecimal gramWeight
	BigDecimal detailId
	String quality
	String clarity
	String compRowid
	String itemRange

	static mapping = {
		id composite: ["stoneType", "pieces", "caratWeight", "lotNumber", "organizationId", "sellingPrice", "cpPrice", "stoneQuality", "crtRange", "rateBasis", "gramWeight", "detailId", "quality", "clarity", "compRowid", "itemRange"]
		version false
	}

	static constraints = {
		stoneType nullable: true, maxSize: 30
		pieces nullable: true
		caratWeight nullable: true
		lotNumber nullable: true, maxSize: 20
		organizationId nullable: true
		sellingPrice nullable: true
		cpPrice nullable: true
		stoneQuality nullable: true, maxSize: 20
		crtRange nullable: true
		rateBasis nullable: true, maxSize: 1
		gramWeight nullable: true
		detailId nullable: true
		quality nullable: true, maxSize: 20
		clarity nullable: true, maxSize: 20
		compRowid nullable: true
		itemRange nullable: true, maxSize: 20
	}
}
