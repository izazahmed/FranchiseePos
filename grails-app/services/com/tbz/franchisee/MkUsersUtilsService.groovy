package com.tbz.franchisee
/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */

import grails.transaction.Transactional
import groovy.sql.Sql
import java.security.MessageDigest
import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * MkUsersUtilsService
 * Service for Users Management related information
 *
 * @author (CTE).
 *
 * Contact ("") (optional)
 *
 * @version    0.1
 * @date        01/02/2016
 *
 * MOD HISTORY
 * DATE           	USER				COMMENTS
 * 15/04/2016	  	Izaz				Created File
 */

@Transactional
class MkUsersUtilsService {
	def str,result
	Sql sql
	def dataSource;
	def serviceMethod() {
	}
	
	/**
	 * Method Name: getFinanceYearDate
	 * Description: Retrieve all Details record data from Database
	 *
	 * @return : ResultSet Details
	 */
	def getFinanceYearDate() {		
		try{
			str = "SELECT * FROM FIN_ACCOUNT_YEAR"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getFinanceYearDate method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}

	/**
	 * Method Name: getBrMstTabList
	 * Description: Retrieve all Branches Details from Database
	 *
	 * @return : ResultSet Details
	 */
	def getBrMstTabList() {		
		try{
			str = "SELECT * FROM BR_MST_TAB"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getBrMstTabList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}

	/**
	 * Method Name: getMkRolesList
	 * Description: Retrieve all Roles Details from Database
	 *
	 * @return : ResultSet Details
	 */
	def getMkRolesList(mkUsersInstance) {	
		try{
			def x = mkUsersInstance
			str= "SELECT * FROM MK_ROLES"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getMkRolesList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}

	/**
	 * Method Name: getMstCompanyList
	 * Description: Retrieve companies Details from Database
	 *
	 * @return : ResultSet Details
	 */
	def getMstCompanyList() {		
		try{
			str = "SELECT * FROM MST_COMPANY"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getMstCompanyList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}

	/**
	 * Method Name: saveMkUsers
	 * Description: Save user's details into Database
	 *
	 * @return : ResultSet Details
	 */
	def saveMkUsers(mkUsersInstance,companyCode,branchCode) {
		def x = mkUsersInstance
		def maxUserQry,maxUsrId,insertMkUsrQry,compCodeQry,compCd,compCdval,roleCdQry,roleCdRol,rlCdval,insertMkRoleComp,stDt,edDt,dateQry,brcd,cpcd,rcd
		def comp = []
		def role = []
		try{
			if(x?.cIds){
				cpcd = x?.cIds
				if(cpcd.trim().contains(",")){
					comp = cpcd.split(",");
				}
			}else{
				comp = companyCode
			}
			if(x?.rIds){
				rcd = x?.rIds
				if(rcd.trim().contains(",")){
					role = rcd.split(",");
				}
			}else{
				role = ""
			}
			if(x?.brCode){
				brcd = x?.brCode
			}else{
				brcd = branchCode
			}
			
			maxUserQry = "select max(CH_USER_CODE)+1 from mk_users"
			Sql sql1 = new Sql(dataSource)
			maxUsrId = sql1.rows(maxUserQry)
			String val
			maxUsrId.each{
				val=it.values()[0]
			}
			String addVal=0;
			if(val.size() == 1){
				addVal = addVal+""+val
			}else{
				addVal = val
			}
			
			insertMkUsrQry = "INSERT INTO NEWWEBTBZ.MK_USERS(CH_USER_CODE,VC_USER_NAME,VC_PASSWORD,CH_USER_ACTIVE,BR_CODE) VALUES ('$addVal', '$x.name','$x.password','$x.actRadio','$brcd')"			
			/*Sql sql2 = new Sql(dataSource)
			sql2.execute insertMkUsrQry
			compCodeQry = "select vc_comp_code from mst_company where vc_company_name = '$x.compName'"
			Sql sql3 = new Sql(dataSource)
			compCd = sql3.rows(compCodeQry)
			compCd.each{
				compCdval=it.values()[0]
			}
			roleCdQry = "select ch_role_code from mk_roles where vc_role_name='$x.roleName' and br_code='$x.brCode'"
			Sql sql5 = new Sql(dataSource)
			roleCdRol = sql5.rows(roleCdQry)
			roleCdRol.each{
				rlCdval=it.values()[0]
			}
			insertMkRoleComp = "INSERT INTO NEWWEBTBZ.MK_ROLES_COMP(VC_DEFAULT_COMP,CH_USER_CODE,VC_COMP_CODE,CH_ROLE_CODE,BR_CODE) VALUES ('01', '$addVal','$compCdval','$rlCdval','$x.brCode')"
				Sql sql6 = new Sql(dataSource)
				sql6.execute insertMkRoleComp	*/
			if(addVal){
				for (int j = 0; j < comp.length; j++) {
					def cid,rid
					cid = Integer.parseInt(comp[j]);
					
					for (int q = 0; q < role.length; q++) {
						println"comp in for :"+role[q]
						rid = Integer.parseInt(role[q]);
					}
					insertMkRoleComp = "INSERT INTO NEWWEBTBZ.MK_ROLES_COMP(VC_DEFAULT_COMP,CH_USER_CODE,VC_COMP_CODE,CH_ROLE_CODE,BR_CODE) VALUES ('01', '$addVal','$cid','$rid','$brcd')"
					Sql sql6 = new Sql(dataSource)
					sql6.execute insertMkRoleComp		
				}
						
				def cDt = x?.cur
				def cDtArr = []
				if(cDt.contains(",")){
					cDtArr = cDt.split(",");
				}
				def j=1
				for(def i=0;i<=(cDtArr.length-1); i++){
					stDt = cDtArr[i]
					edDt = cDtArr[j]
					dateQry = "INSERT INTO NEWWEBTBZ.user_fin_account_year(CH_USER_CODE,DT_FIN_START_DATE,DT_FIN_END_DATE,CH_ACTIVE,BR_CODE) VALUES ('$addVal', TO_DATE('$stDt','DD-MM-YYYY'),TO_DATE('$edDt','DD-MM-YYYY'),'$x.actRadio','$brcd')"
					i=i+1;
					j =j+2;
					Sql sql7 = new Sql(dataSource)
					sql7.execute dateQry
				}
			}
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService saveMkUsers method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: updateMkUsers
	 * Description: Update user's details into Database
	 *
	 * @return : ResultSet Details
	 */
	def updateMkUsers(mkUsersInstance,companyCode,branchCode) {	
		def x = mkUsersInstance
		def brCodeQry,brCodeRes,brCd,insertMkUsrQry,compCodeQry,compCd,compCdval,roleCdQry,roleCdRol,rlCdval,insertMkRoleComp,stDt,edDt,brcd,cpcd,rcd
		def comp = []
		def role = []
		try{
			if(x?.cIds){
				cpcd = x?.cIds
				if(cpcd.trim().contains(",")){
					comp = cpcd.split(",");
				}
			}else{
				comp = companyCode
			}
			if(x?.rIds){
				rcd = x?.rIds
				if(rcd.trim().contains(",")){
					role = rcd.split(",");
				}
			}else{
				role = ""
			}
			if(x?.brCode){
				brcd = x?.brCode
			}else{
				brcd = branchCode
			}
			
			/*brCodeQry = "select br_code from mk_users where ch_user_code = '$x.hiduserCD'"
			Sql sql1 = new Sql(dataSource)
			brCodeRes = sql1.rows(brCodeQry)
			for(def i=1;i<=brCodeRes.size();i++) {
				brCodeRes = brCodeRes[0]
				brCd = brCodeRes?.br_code
			}*/
			insertMkUsrQry = "UPDATE NEWWEBTBZ.MK_USERS set VC_USER_NAME='$x.name',CH_USER_ACTIVE='$x.actRadio'  where CH_USER_CODE = '$x.hiduserCD'"			
			Sql sql2 = new Sql(dataSource)
			sql2.execute insertMkUsrQry
			/*compCodeQry = "select vc_comp_code from mst_company where vc_company_name = '$x.compName'"
			Sql sql3 = new Sql(dataSource)
			compCd = sql3.rows(compCodeQry)
			compCd.each{
				compCdval=it.values()[0]
			}				
			roleCdQry = "select ch_role_code from mk_roles where vc_role_name='$x.roleName' and br_code='$brCd'"
			Sql sql5 = new Sql(dataSource)
			roleCdRol = sql5.rows(roleCdQry)
			roleCdRol.each{
				rlCdval=it.values()[0]
			}
			insertMkRoleComp = "UPDATE NEWWEBTBZ.MK_ROLES_COMP set VC_COMP_CODE='$compCdval', CH_ROLE_CODE='$rlCdval' where CH_USER_CODE='$x.hiduserCD'"
				Sql sql6 = new Sql(dataSource)
				sql6.execute insertMkRoleComp	*/
			for (int j = 0; j < comp.length; j++) {
				def cid,rid
				cid = Integer.parseInt(comp[j]);
				
				for (int q = 0; q < role.length; q++) {
					println"comp in for :"+role[q]
					rid = Integer.parseInt(role[q]);
				}
				insertMkRoleComp = "UPDATE NEWWEBTBZ.MK_ROLES_COMP set VC_COMP_CODE='$cid', CH_ROLE_CODE='$rid' where CH_USER_CODE='$x.hiduserCD'"
				Sql sql6 = new Sql(dataSource)
				sql6.execute insertMkRoleComp	
			}			
			def cDt = x?.cur
			def cDtArr = []
			if(cDt.contains(",")){
				cDtArr = cDt.split(",");
			}
			def j=1
			for(def i=0;i<=(cDtArr.length-1); i++) {
				stDt = cDtArr[i]
				edDt = cDtArr[j]
				def dateQry
				if(i==0 && j==1){
					dateQry = "UPDATE NEWWEBTBZ.user_fin_account_year set DT_FIN_START_DATE=TO_DATE('$stDt','DD-MM-YYYY'), DT_FIN_END_DATE=TO_DATE('$edDt','DD-MM-YYYY'),CH_ACTIVE='$x.actRadio', BR_CODE='$brCd' where  CH_USER_CODE='$x.hiduserCD'"
				}else{
					dateQry = "INSERT INTO NEWWEBTBZ.user_fin_account_year(CH_USER_CODE,DT_FIN_START_DATE,DT_FIN_END_DATE,CH_ACTIVE,BR_CODE) VALUES ('$x.hiduserCD', TO_DATE('$stDt','DD-MM-YYYY'),TO_DATE('$edDt','DD-MM-YYYY'),'$x.actRadio','$brCd')"
				}
				i=i+1;
				j =j+2;
				Sql sql7 = new Sql(dataSource)
				sql7.execute dateQry
			}	
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService updateMkUsers method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getMkUsersList
	 * Description: Retrieve user's code from Database
	 *
	 * @return : ResultSet Details
	 */
	def getMkUsersList() {
		try{
			str = "SELECT CH_USER_CODE,VC_USER_NAME FROM MK_USERS"		
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getMkUsersList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getUserDataForEdit
	 * Description: Retrieve user's Details from Database
	 *
	 * @return : ResultSet Details
	 */
	def getUserDataForEdit(mkUserInstance) {
		def x = mkUserInstance
		def brCodeQry,brCodeRes,brCd
		try{
			brCodeQry = "select br_code from mk_users where ch_user_code = '$x.userCode'"
			Sql sql1 = new Sql(dataSource)
			brCodeRes = sql1.rows(brCodeQry)
			for(def i=1;i<=brCodeRes.size();i++) {
				brCodeRes = brCodeRes[0]
				brCd = brCodeRes?.br_code
			}		
			def query =
			"""
			Select br.BR_NAME,b.CH_USER_CODE,b.VC_USER_NAME,b.VC_PASSWORD,b.CH_USER_ACTIVE,
				e.vc_company_name,c.VC_ROLE_NAME
				FROM MK_ROLES_COMP a, MK_USERS  b,mk_roles c,user_fin_account_year d, br_mst_tab br,mst_company e
				WHERE  a.br_code=b.br_code
				and a.br_code=b.br_code
				and b.br_code=c.br_code
				and a.br_code=d.br_code
				and a.br_code=br.br_code
				and a.br_code=e.br_code
				and a.ch_role_code= c.ch_role_code
				and a.ch_user_code=d.ch_user_code
				and a.CH_USER_CODE=b.CH_USER_CODE
				and a.CH_USER_CODE='$x.userCode' 
				and a.br_code='$brCd'
			group by br.BR_NAME,b.CH_USER_CODE,b.VC_USER_NAME,b.VC_PASSWORD,b.CH_USER_ACTIVE,
				e.vc_company_name,c.VC_ROLE_NAME
			"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getUserDataForEdit method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	
	/**
	 * Method Name: getDateForEdit
	 * Description: Retrieve user's authorized date from Database
	 *
	 * @return : ResultSet Details
	 */
	def getDateForEdit(mkUserInstance) {
		def x = mkUserInstance
		def brCd,brCodeQry,brCodeRes
		try{
			brCodeQry = "select br_code from mk_users where ch_user_code = '$x.userCode'"
			Sql sql1 = new Sql(dataSource)
			brCodeRes = sql1.rows(brCodeQry)
			for(def i=1;i<=brCodeRes.size();i++){
				brCodeRes = brCodeRes[0]
				brCd = brCodeRes?.br_code
			}
			def query =
			"""
			Select d.DT_FIN_START_DATE,d.DT_FIN_END_DATE 
				FROM MK_ROLES_COMP a, MK_USERS  b,mk_roles c,user_fin_account_year d, br_mst_tab br,mst_company e
				WHERE  a.br_code=b.br_code
				and a.br_code=b.br_code
				and b.br_code=c.br_code
				and a.br_code=d.br_code
				and a.br_code=br.br_code
				and a.br_code=e.br_code
				and a.ch_role_code= c.ch_role_code
				and a.ch_user_code=d.ch_user_code
				and a.CH_USER_CODE=b.CH_USER_CODE
				and a.CH_USER_CODE='$x.userCode' 
				and a.br_code='$brCd'
			"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getDateForEdit method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getUserFinAccountStDt
	 * Description: Retrieve all dates from Database
	 *
	 * @return : ResultSet Details
	 */
	def getUserFinAccountStDt(mkUserInstance) {	
		def x = mkUserInstance
		try{
			str ="SELECT * FROM FIN_ACCOUNT_YEAR"
			sql = new Sql(dataSource)
			result = sql.rows(str)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getUserFinAccountStDt method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getRolesDataBasedOnBranch
	 * Description: Retrieve role code and name based on 
	 * 				branch code from Database
	 *
	 * @return : ResultSet Details
	 */
	def getRolesDataBasedOnBranch(roleInstance) {	
		def x = roleInstance
		def roleData
		try{
			roleData = "select ch_role_code,vc_role_name from MK_ROLES where br_code='$x.brCode'"
			sql = new Sql(dataSource)
			result = sql.rows(roleData)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getRolesDataBasedOnBranch method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getCompanyData
	 * Description: Retrieve company code and name from Database
	 *
	 * @return : ResultSet Details
	 */
	def getCompanyData(roleInstance) {		
		def vcCompNameQry,companyResult
		try{
			vcCompNameQry = "select vc_comp_code,vc_company_name FROM MST_COMPANY"		
			sql = new Sql(dataSource)
			companyResult = sql.rows(vcCompNameQry)		
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getCompanyData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return companyResult
	}
	
	/**
	 * Method Name: getUserDataByUserId
	 * Description: Retrieve user's details from Database by User
	 *
	 * @return : ResultSet Details
	 */
	def getUserDataByUserId(userCode) {
		def userQry,userResult
		try{
			userQry = "select * from MK_USERS where CH_USER_CODE='$userCode'"
			sql = new Sql(dataSource)
			userResult = sql.rows(userQry)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getUserDataByUserId method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return userResult
	}
	
	/**
	 * Method Name: getUserData
	 * Description: Retrieve user's details from Database by Administrator
	 *
	 * @return : ResultSet Details
	 */
	def getUserData(mkUserInstance) {
		def userQry,userResult
		try{
			userQry = "select * from MK_USERS"
			sql = new Sql(dataSource)
			userResult = sql.rows(userQry)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getUserData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return userResult
	}
	
	/**
	 * Method Name: getUserDataById
	 * Description: Retrieve user's password details from Database
	 *
	 * @return : ResultSet Details
	 */
	def getUserDataById(mkUserInstance){
		def x = mkUserInstance
		def userQry,userResult
		try{
			userQry = "select VC_PASSWORD from MK_USERS where CH_USER_CODE='$x.userId'"
			sql = new Sql(dataSource)
			userResult = sql.rows(userQry)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getUserDataById method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return userResult
	}
	
	/**
	 * Method Name: updateUserPassword
	 * Description: Update user's password into Database by administrator
	 *
	 * @return : ResultSet Details
	 */
	def updateUserPassword(mkUserInstance){
		def x = mkUserInstance
		def updateUserQry
		int rowcount
		try{
			String password = x?.newPasswordConf
			/*MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			
			byte byteData[] = md.digest();*/
			updateUserQry = "UPDATE MK_USERS SET VC_PASSWORD='$x.newPasswordConf' where CH_USER_CODE='$x.userId'"
			sql = new Sql(dataSource)
			rowcount = sql.executeUpdate(updateUserQry) 
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService updateUserPassword method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return rowcount
	}
	
	/**
	 * Method Name: getBrData
	 * Description: gets the branch data
	 *
	 * @return : branch list
	 */
	def getBrData(){
		try{
			str = "select br_code,br_name from br_mst_tab"
			sql = new Sql(dataSource)
			result = sql.rows(str)
		}catch(Exception exception){
			log.info("Exception in MkUsersUtilsService getBrData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
}
