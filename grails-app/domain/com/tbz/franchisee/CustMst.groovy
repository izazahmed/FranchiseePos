package com.tbz.franchisee

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class CustMst implements Serializable {

	String custId
	String fname
	String mname
	String lname
	String add1
	String add2
	String city
	String state
	BigDecimal pin
	BigDecimal phoneR
	BigDecimal phoneO
	BigDecimal mobile
	String emailId
	Date dob
	Integer userid
	String panNo
	String custNominee
	String custNomineeRel
	Character custNomineeGen
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
	String vcField11
	String vcField12
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
	String kpLoyalRefOld
	Long schemeNo
	String brCode

	static mapping = {
		table "CUST_MST"
		id name: 'custId', generator : 'assigned'
		phoneO column :'phone_O'
		userid column :'USERID'		
		phoneR column :'phone_R'
		version false
	}

	static constraints = {
		custId maxSize: 20, unique: true
		fname nullable: true, maxSize: 75
		mname nullable: true, maxSize: 75
		lname nullable: true, maxSize: 75
		add1 nullable: true, maxSize: 150
		add2 nullable: true, maxSize: 150
		city nullable: true, maxSize: 80
		state nullable: true, maxSize: 80
		pin nullable: true
		phoneR nullable: true
		phoneO nullable: true
		mobile nullable: true
		emailId nullable: true, maxSize: 70, email: true
		dob nullable: true
		userid nullable: true
		panNo nullable: true, maxSize: 30
		custNominee nullable: true, maxSize: 100
		custNomineeRel nullable: true, maxSize: 94
		custNomineeGen nullable: true, maxSize: 1
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
		vcField11 nullable: true, maxSize: 50
		vcField12 nullable: true, maxSize: 50
		nuField1 nullable: true
		nuField2 nullable: true
		nuField3 nullable: true
		nuField4 nullable: true
		nuField5 nullable: true
		nuField6 nullable: true
		nuField7 nullable: true
		nuField8 nullable: true
		nuField9 nullable: true
		nuField10 nullable: true
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
		kpLoyalRefOld nullable: true, maxSize: 20
		schemeNo nullable: true
		brCode nullable: true, maxSize: 30
	}
}
