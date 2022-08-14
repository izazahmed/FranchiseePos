package com.tbz.franchisee
/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */
/**
 * LoginUtilsService
 * Service for Roles Details related information
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
 * 01/02/2016		Sachin				Created File
 * 15/04/2016	  	Sachin				Added Logger and Exception handling
 */
import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class MkRolesUtilsService {

	def dataSource
	def serviceMethod() {
	}

	def getMkRolesName(mkRolesInstance){
		def retList = []
		def x = mkRolesInstance
		def str = "SELECT VC_ROLE_NAME FROM MK_ROLES"
		Sql sql = new Sql(dataSource)
		def result = sql.rows(str)
		result.each {
			retList.add(it.values()[0])
		}
		return retList.asList();
	}

	def getMkAllModuleNm(){
		def str
		Sql sql
		def result
		def dataMap = [:]
		try{
			str = "select VC_MODULE_CODE,VC_MODULE_OBJECT from mk_module"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
			result.each {
				dataMap.put(it.values()[0].trim(), it.values()[1])
			}
		}catch(Exception e){
		}
		return dataMap
	}
	def getAllRoleName(){
		def str
		Sql sql
		def result
		def dataMap = [:]
		try{
			str = "select CH_ROLE_CODE,VC_ROLE_NAME from MK_ROLES"
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
			result.each {
				dataMap.put(it.values()[0].trim(), it.values()[1])
			}
		}catch(Exception e){
		}
		return dataMap
	}
	def getAllMenus(moduleCode,leveloneVal, menuId){
		def detaResult,str
		def dataMap = [:]
		try{
			if(menuId == null) {
				str = "select distinct VC_MENU_CODE,VC_MENU_OBJECT from MK_MODULE_MENU WHERE VC_MODULE_CODE = '"+moduleCode+"' AND LENGTH(VC_MENU_CODE)="+(leveloneVal *2)+" and CH_ACTIVE='Y' order by VC_MENU_OBJECT"
			} else {
				str = "select distinct VC_MENU_CODE,VC_MENU_OBJECT from MK_MODULE_MENU WHERE VC_MODULE_CODE = '"+moduleCode+"' AND LENGTH(VC_MENU_CODE)="+(leveloneVal*2)+" AND VC_MENU_CODE LIKE '"+menuId+"%' and CH_ACTIVE='Y' order by VC_MENU_OBJECT"
			}
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				dataMap.put(it.values()[0].trim(), it.values()[1])
			}
		}catch(Exception e){
		}
		return dataMap
	}

	def getAllleveltwoMenu(firstlevelMenuId,secmenuId){
		def detaResult,str
		def dataMap = [:]
		try{
			str = "select distinct VC_MENU_CODE,VC_MENU_OBJECT from MK_MODULE_MENU WHERE VC_MODULE_CODE = '"+firstlevelMenuId+"' AND LENGTH(VC_MENU_CODE)=4 AND VC_MENU_CODE LIKE '"+secmenuId+"%'"
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				dataMap.put(it.values()[0].trim(), it.values()[1])
			}
		}catch(Exception e){
		}
		return dataMap
	}
	def getAllMenusAvailable(moduleCode,leveloneVal, menuId){
		def detaResult,str
		def dataMap = [:]
		try{
			if(menuId == null) {
				str = "select count(*) counts from MK_MODULE_MENU WHERE VC_MODULE_CODE = '"+moduleCode+"' AND LENGTH(VC_MENU_CODE)="+(leveloneVal *2)+" order by VC_MENU_OBJECT"
			} else {
				str = "select count(*) counts from MK_MODULE_MENU WHERE VC_MODULE_CODE = '"+moduleCode+"' AND LENGTH(VC_MENU_CODE)="+(leveloneVal*2)+" AND VC_MENU_CODE LIKE '"+menuId+"%' order by VC_MENU_OBJECT"
			}
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				dataMap.put("counts",it.values()[0])
			}
		}catch(Exception e){
		}
		return dataMap
	}

	def getCompName(){
		def detaResult,str
		def complist = [:]
		try{
			str = "select distinct br_code,br_name from br_mst_tab"
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				complist.put(it.values()[0],it.values()[1])
			}
		}catch(Exception e){
		}
		return complist
	}


	def insertModuleIdsIntoTempTable(moduleId){
		def str
		Sql sql
		try{
			str="insert into temp_mst_menu(temp_module_id) values('"+moduleId+"')"
			sql = new Sql(dataSource)
			sql.execute str
		}catch(Exception e){
		}finally{
		}
	}

	def insertfirstLvlIdsIntoTempTable(moduleId,firstlevelMenuId,insert){
		def str,str1,str2
		Sql sql=null,sql1=null,sql2=null
		try{
			if(insert.equals("0")){
				str="insert into temp_mst_menu(temp_module_id,TEMP_F_MENU_ID) values('"+moduleId+"','"+firstlevelMenuId+"')"
				sql = new Sql(dataSource)
				sql.execute str
			}else if(insert.equals("1")){
				str1="DELETE FROM temp_mst_menu WHERE TEMP_MODULE_ID='"+moduleId+"' AND TEMP_F_MENU_ID IS NOT NULL"
				sql1 = new Sql(dataSource)
				sql1.execute str1
				
				def levelIdArr=firstlevelMenuId.substring(0,(firstlevelMenuId.length()-1)).split("~")
				for(def i=0;i<levelIdArr.length;i++){
					str2="insert into temp_mst_menu(temp_module_id,TEMP_F_MENU_ID) values('"+moduleId+"','"+levelIdArr[i]+"')"
					sql2 = new Sql(dataSource)
					sql2.execute str2
				}
			}
		}catch(Exception e){
		}finally{
		}
	}
	def deletefirstLvlIdsIntoTempTable(moduleId,firstlevelMenuId,delete){
		def str
		Sql sql=null
		try{
			if(delete.equals("0")){
				str="delete from temp_mst_menu where temp_module_id='"+moduleId+"' and TEMP_F_MENU_ID='"+firstlevelMenuId+"'"
				sql = new Sql(dataSource)
				sql.execute str
			}else if(delete.equals("1")){
				def levelIdArr=firstlevelMenuId.substring(0,(firstlevelMenuId.length()-1)).split("~")
				for(def i=0;i<levelIdArr.length;i++){
					str="delete from temp_mst_menu where temp_module_id='"+moduleId+"' and TEMP_F_MENU_ID='"+levelIdArr[i]+"'"
					sql = new Sql(dataSource)
					sql.execute str
				}
			}
		}catch(Exception e){
		}finally{
		}
	}
	def deleteModuleDataFronTempTable(){
		def str
		Sql sql
		try{
			str="delete from temp_mst_menu"
			sql = new Sql(dataSource)
			sql.execute str
		}catch(Exception e){
		}finally{
		}
	}
	def insertsecondLvlIdsIntoTempTable(moduleId,firstlevelMenuId,seconfLvlId,insert){
		def str
		Sql sql=null
		try{
			if(insert.equals("0")){
				str="insert into temp_mst_menu(temp_module_id,TEMP_F_MENU_ID,TEMP_S_MENU_ID) values('"+moduleId+"','"+firstlevelMenuId+"','"+seconfLvlId+"')"
				sql = new Sql(dataSource)
				sql.execute str
			}else if(insert.equals("1")){
				//moduleId=module id, firstlevelMenuId=first,seconfLvlId=all selected,insert
				def levelIdArr=seconfLvlId.substring(0,(seconfLvlId.length()-1)).split("~")
				for(def i=0;i<levelIdArr.length;i++){
					str="insert into temp_mst_menu(temp_module_id,TEMP_F_MENU_ID,TEMP_S_MENU_ID) values('"+moduleId+"','"+firstlevelMenuId+"','"+levelIdArr[i]+"')"
					sql = new Sql(dataSource)
					sql.execute str
				}
			}
		}catch(Exception e){
		}finally{
		}
	}
	def deletesecondLvlIdsIntoTempTable(moduleId,firstlevelMenuId,seconfLvlId,insert){
		def str
		Sql sql=null
		try{
			if(insert.equals("0")){
				str="delete from temp_mst_menu where temp_module_id='"+moduleId+"' and TEMP_F_MENU_ID='"+firstlevelMenuId+"' and TEMP_S_MENU_ID='"+seconfLvlId+"'"
				sql = new Sql(dataSource)
				sql.execute str
			}else if(insert.equals("1")){
				def levelIdArr=seconfLvlId.substring(0,(seconfLvlId.length()-1)).split("~")
				for(def i=0;i<levelIdArr.length;i++){
					str="delete from temp_mst_menu where temp_module_id='"+moduleId+"' and TEMP_F_MENU_ID='"+firstlevelMenuId+"' and TEMP_S_MENU_ID='"+levelIdArr[i]+"'"
					sql = new Sql(dataSource)
					sql.execute str
				}
			}
		}catch(Exception e){
		}finally{
		}
	}
	
	/**
	 * Method Name: insertThirdLvlIdsIntoTempTable
	 * Description: used to insert third level data
	 *
	 * @return : void
	 */
	def insertThirdLvlIdsIntoTempTable(moduleId,firstLvlId,secondLvlId,butMenuIdThi,insert){
		def str
		Sql sql=null
		try{
			if(insert.equals("0")){
				str="insert into temp_mst_menu(temp_module_id,TEMP_F_MENU_ID,TEMP_S_MENU_ID,TEMP_T_MENU_ID) values('"+moduleId+"','"+firstLvlId+"','"+secondLvlId+"','"+butMenuIdThi+"')"
				sql = new Sql(dataSource)
				//sql.execute str
			}else if(insert.equals("1")){
				def levelIdArr=butMenuIdThi.substring(0,(butMenuIdThi.length()-1)).split("~")
				for(def i=0;i<levelIdArr.length;i++){
					str="insert into temp_mst_menu(temp_module_id,TEMP_F_MENU_ID,TEMP_S_MENU_ID) values('"+moduleId+"','"+firstLvlId+"','"+secondLvlId+"','"+levelIdArr[i]+"')"
					sql = new Sql(dataSource)
					//sql.execute str
				}
			}
		}catch(Exception exception){
			log.info("Exception in MkRolesUtilsService insertThirdLvlIdsIntoTempTable method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
		}
	}
	
	/**
	 * Method Name: deleteThirdLvlIdsFromTempTable
	 * Description: used to delete third level data
	 *
	 * @return : void
	 */
	def deleteThirdLvlIdsFromTempTable(modId,firstLvlId,secondLvlId,butMenuIdThi,delete){
		def str
		Sql sql=null
		try{
			if(delete.equals("0")){
				str="delete from temp_mst_menu where temp_module_id='"+modId+"' and TEMP_F_MENU_ID='"+firstLvlId+"' and TEMP_S_MENU_ID='"+secondLvlId+"' and TEMP_T_MENU_ID='"+butMenuIdThi+"'"
				sql = new Sql(dataSource)
				//sql.execute str
			}else if(delete.equals("1")){
				def levelIdArr=butMenuIdThi.substring(0,(butMenuIdThi.length()-1)).split("~")
				for(def i=0;i<levelIdArr.length;i++){
					str="delete from temp_mst_menu where temp_module_id='"+modId+"' and TEMP_F_MENU_ID='"+firstLvlId+"' and TEMP_S_MENU_ID='"+secondLvlId+"' and TEMP_T_MENU_ID='"+levelIdArr[i]+"'"
					sql = new Sql(dataSource)
					//sql.execute str
				}
			}
		}catch(Exception e){
		}finally{
		}
	}
	
	def getAllselectedData(moduleSelectionId,moduleCode){
		def detaResult,str,rowData
		def dataMap = []
		try{
			str = "select TEMP_F_MENU_ID from temp_mst_menu where TEMP_MODULE_ID='"+moduleCode+"'"
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				rowData=it.values()[0]
				if(!rowData.equals(null))
					dataMap.add(rowData)
			}
		}catch(Exception e){
		}
		return dataMap
	}
	def getsecondLvlDataFrmTemp(moduleCode,secmenuId){
		def detaResult,str,rowData
		def dataMap = []
		try{
			str = "select TEMP_S_MENU_ID from TEMP_MST_MENU where TEMP_MODULE_ID='"+moduleCode+"' and TEMP_F_MENU_ID='"+secmenuId+"'"
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				rowData=it.values()[0]
				if(!rowData.equals(null))
					dataMap.add(rowData)
			}
		}catch(Exception e){
		}
		return dataMap
	}
	def insertMenuDataInMainDatraBase(brId,rollNm){
		def str,rollid,tempTableData,tempResult,moduleid,menuid,secmenuid,secstrQry
		Sql sql=null,tempSql=null,secsql=null
		try{
			rollid=getRollId(rollNm);
			insertRoles(rollid,rollNm,brId)
			tempTableData="select TEMP_MODULE_ID,TEMP_F_MENU_ID,TEMP_S_MENU_ID from TEMP_MST_MENU"
			tempSql = new Sql(dataSource)
			tempResult = tempSql.rows(tempTableData)
			tempResult.each {
				moduleid=it.values()[0]
				menuid=it.values()[1]
				secmenuid=it.values()[2]
				if(moduleid != null && menuid != null && secmenuid == null){
					str="insert into MK_ROLES_MENU (CH_ROLE_CODE,VC_MODULE_CODE,VC_MENU_CODE,BR_CODE) values('"+rollid+"','"+moduleid+"','"+menuid+"','"+brId+"')"
					sql = new Sql(dataSource)
					sql.execute str
				}
				if(moduleid != null && menuid != null && secmenuid != null){
					secstrQry="insert into MK_ROLES_MENU (CH_ROLE_CODE,VC_MODULE_CODE,VC_MENU_CODE,BR_CODE) values('"+rollid+"','"+moduleid+"','"+secmenuid+"','"+brId+"')"
					secsql = new Sql(dataSource)
					secsql.execute secstrQry
				}

			}
		}catch(Exception e){
			sql.rollback()
			secsql.rollback()
		}finally{
			deleteModuleDataFronTempTable()
		}
	}

	def getRollId(rollNm){
		def sltQry,rtnVal
		Sql sql=null
		def detaResult
		try{
			sltQry="select to_char((max(TO_NUMBER(ch_role_code))+1)) nxtRoll from mk_roles"
			sql = new Sql(dataSource)
			detaResult = sql.rows(sltQry)
			detaResult.each {
				rtnVal=it.values()[0]
			}
			if(rtnVal.toString().length()<2)
			rtnVal="0"+rtnVal;
			else
			rtnVal=rtnVal;
		}catch(Exception e){
		}finally{
		}
		return rtnVal
	}
	def insertRoles(rollid,rollNm,brId){
		def str
		Sql sql=null
		try{
			str="insert into mk_roles (CH_ROLE_CODE,VC_ROLE_NAME,BR_CODE) values('"+rollid+"','"+rollNm+"','"+brId+"')"
			sql = new Sql(dataSource)
			sql.execute str
		}catch(Exception e){
			sql.rollback()
		}
	}

	def getBranchAndRoleData(){
		def sltQry
		Sql sql=null
		def detaResult
		try{
			sltQry="select CH_ROLE_CODE,BR_NAME,VC_ROLE_NAME,b.BR_CODE from mk_roles a join br_mst_tab b on b.BR_CODE=a.BR_CODE"
			sql = new Sql(dataSource)
			detaResult = sql.rows(sltQry)
		}catch(Exception e){
		}finally{
		}
		return detaResult
	}
	def insertMenuinTempTableBasedOnRoleId(roleName,role_id,br_code){
		def sltQry,rtnVal,insertstr,module_id,menu_code
		Sql sql=null,insertsql=null
		def detaResult,TEMP_F_MENU_ID,TEMP_S_MENU_ID,TEMP_T_MENU_ID
		try{
			sltQry="select VC_MODULE_CODE,VC_MENU_CODE from MK_ROLES_MENU where BR_CODE='"+br_code.toString().trim()+"' and CH_ROLE_CODE='"+role_id+"'"
			sql = new Sql(dataSource)
			detaResult = sql.rows(sltQry)
			detaResult.each {
				module_id=it.values()[0]
				menu_code=it.values()[1]
				if(menu_code.toString().length()==2)
					TEMP_F_MENU_ID="TEMP_F_MENU_ID"
				else if(menu_code.toString().length()==4)
					TEMP_F_MENU_ID="TEMP_S_MENU_ID"
				else if(menu_code.toString().length()==6)
					TEMP_F_MENU_ID="TEMP_T_MENU_ID"					
				insertstr="insert into TEMP_MST_MENU(TEMP_MODULE_ID,'"+TEMP_F_MENU_ID+"') values('"+module_id+"','"+menu_code+"')"
				insertsql = new Sql(dataSource)
				insertsql.execute insertstr
			}
		}catch(Exception e){
		}finally{
		}
		return module_id
	}

	def updateMenuDataInMainDatraBase(role_ids,rollNm,branchCode){
		def str,rollid,tempTableData,tempResult,moduleid,menuid,secmenuid,secstrQry
		Sql sql=null,tempSql=null,secsql=null
		try{
			//rollid=getRollId(rollNm);
			//insertRoles(rollid,rollNm,role_ids)
			tempTableData="select TEMP_MODULE_ID,TEMP_F_MENU_ID,TEMP_S_MENU_ID from TEMP_MST_MENU"
			tempSql = new Sql(dataSource)
			tempResult = tempSql.rows(tempTableData)
			tempResult.each {
				moduleid=it.values()[0]
				menuid=it.values()[1]
				secmenuid=it.values()[2]
				if(moduleid != null && menuid != null){
					str="update MK_ROLES_MENU set CH_ROLE_CODE='"+role_ids.toString().trim()+"',VC_MODULE_CODE='"+moduleid+"',VC_MENU_CODE='"+menuid+"',BR_CODE='"+branchCode+"'  where BR_CODE='"+branchCode+"' and CH_ROLE_CODE='0"+role_ids.toString().trim()+"'"
					sql = new Sql(dataSource)
					sql.execute str
				}
				/*				if(moduleid != null && menuid != null && secmenuid != null){
				 secstrQry="insert into MK_ROLES_MENU (CH_ROLE_CODE,VC_MODULE_CODE,VC_MENU_CODE,BR_CODE) values("+rollid+",'"+moduleid+"','"+secmenuid+"','"+brId+"')"
				 secsql = new Sql(dataSource)
				 secsql.execute secstrQry
				 }*/

			}
		}catch(Exception e){
			sql.rollback()
			secsql.rollback()
		}finally{
			deleteModuleDataFronTempTable()
		}
	}
	
	
	
	def deletemoduleDataFromTempTable(moduleId,branchId){
		def str
		Sql sql=null
		try{
				str="delete from temp_mst_menu where temp_module_id='"+moduleId+"'"
				sql = new Sql(dataSource)
				sql.execute str	
		}catch(Exception e){
		}finally{
		}
	}
	
	/**
	 * Method Name: getViewListData
	 * Description: gets the saved roles details data
	 *
	 * @return : saved roles details list
	 */
	def getViewListData(mkRolesInstance) {
		def x = mkRolesInstance
		def viewListData
		try{
			def str = new StringBuilder("select bmt.br_code,bmt.br_name,mr.ch_role_code,mr.vc_role_name,mm.vc_module_code,mm.vc_module_object ");
						str.append("from br_mst_tab bmt,mk_roles mr,mk_module mm,mk_roles_menu mrm ");
						str.append("where bmt.br_code = mr.br_code and mrm.ch_role_code=mr.ch_role_code and mrm.vc_module_code = mm.vc_module_code and mr.br_code='$x.brCode' ");
						
			if(x?.roleName) {
				str.append("and mr.vc_role_name like'%$x.roleName%' ");
			}
			str.append("group by bmt.br_code,bmt.br_name,mr.ch_role_code,mr.vc_role_name,mm.vc_module_code,mm.vc_module_object");
					 
			Sql sql = new Sql(dataSource)
			viewListData = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkRolesUtilsService getViewListData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return viewListData		
	}
	
	/**
	 * Method Name: getModuleData
	 * Description: gets the module data
	 *
	 * @return : module list
	 */
	def getModuleData() {
		def moduleDataResult
		try{
			def str ="select vc_module_code,vc_module_object from mk_module"
			Sql sql = new Sql(dataSource)
			moduleDataResult = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkRolesUtilsService getModuleData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return moduleDataResult
	}
	
	
	/**
	 * Method Name: getNextLevelForViewOrEdit
	 * Description: gets the count of next level
	 *
	 * @return : count list
	 */
	def getNextLevelForViewOrEdit(modId,roleId,levelId,menuId){
		def detaResult,str
		def returnVal
		try{
			if(levelId.equals("1")){
				str = "select count(*) counts from MK_ROLES_MENU where ch_role_code='"+roleId+"' and VC_MODULE_CODE ='"+modId+"' AND LENGTH(VC_MENU_CODE)=2"
			}else if(levelId.equals("2")){
				str = "select count(*) counts from MK_ROLES_MENU where ch_role_code='"+roleId+"' and VC_MODULE_CODE ='"+modId+"' AND LENGTH(VC_MENU_CODE)=4 AND VC_MENU_CODE LIKE '"+menuId+"%'"
			}else if(levelId.equals("3")){
				str = "select count(*) counts from MK_ROLES_MENU where ch_role_code='"+roleId+"' and VC_MODULE_CODE ='"+modId+"' AND LENGTH(VC_MENU_CODE)=6 AND VC_MENU_CODE LIKE '"+menuId+"%'"
			}
			Sql sql = new Sql(dataSource)
			detaResult = sql.rows(str)
			detaResult.each {
				returnVal = it.values()[0]
			}
		}catch(Exception exception){
			log.info("Exception in MkRolesUtilsService getNextLevelForViewOrEdit method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return returnVal
	}
	
	/**
	 * Method Name: getSelectedLevelData
	 * Description: gets the selected first level menu data
	 *
	 * @return : menu list
	 */
	def getSelectedLevelData(moduleCode,roleCode,levelId,menuCode,typeValue) {
		def str1,selLvlMenData
		try{
			if(moduleCode.toString().length()==1){
				moduleCode="0"+moduleCode;
			}
			if(roleCode.toString().length()==1){
				roleCode="0"+roleCode;
			}
			if(menuCode.toString().length()==1){
				menuCode="0"+menuCode;
			}else if(menuCode.toString().length()>1){
				menuCode="0"+menuCode;
			}
			
			str1 = new StringBuilder("SELECT DISTINCT MRM.VC_MENU_CODE ");
				str1.append("FROM MK_ROLES_MENU MRM,MK_MODULE_MENU MMM ");
				str1.append("WHERE ");
				str1.append("MRM.VC_MODULE_CODE=MMM.VC_MODULE_CODE AND ");
				str1.append("MRM.VC_MENU_CODE=MMM.VC_MENU_CODE AND ");
				str1.append("MRM.VC_MODULE_CODE='"+moduleCode+"' AND MRM.CH_ROLE_CODE='"+roleCode+"' AND ");
				if(levelId.equals("1")){
					str1.append("LENGTH(MMM.VC_MENU_CODE)=2 AND ");
				}else if(levelId.equals("2")){
					str1.append("MRM.VC_MENU_CODE LIKE '"+menuCode+"%' AND LENGTH(MMM.VC_MENU_CODE)=4 AND ");
				}else if(levelId.equals("3")){
					str1.append("MRM.VC_MENU_CODE LIKE '"+menuCode+"%' AND LENGTH(MMM.VC_MENU_CODE)=6 AND ");
				}else if(levelId.equals("4")){
					str1.append("MRM.VC_MENU_CODE LIKE '"+menuCode+"%' AND LENGTH(MMM.VC_MENU_CODE)=8 AND ");
				}
				str1.append("MMM.CH_ACTIVE='Y' order by VC_MENU_CODE ");
			Sql sql1 = new Sql(dataSource)
			selLvlMenData = sql1.rows(str1.toString())
			if(typeValue=='edit'){
				if(levelId.equals("1")){
					selLvlMenData.each {		
						//insertfirstLvlIdsIntoTempTable(moduleCode,it.values()[0],"0")
					}
				}else if(levelId.equals("2")){
					selLvlMenData.each {
						def totVal = it.values()[0]
						def seconfLvlId = totVal.substring(2);
						//insertsecondLvlIdsIntoTempTable(moduleCode,menuCode,seconfLvlId,"0")
					}
				}else if(levelId.equals("3")){
					selLvlMenData.each {
						def totVal = it.values()[0]
						def secondLvlId = totVal.substring(2);
						def thirdLvlId = totVal.substring(4);
						//insertThirdLvlIdsIntoTempTable(moduleCode,menuCode,secondLvlId,thirdLvlId,"0")
					}
				}else if(levelId.equals("4")){
					selLvlMenData.each {
						def totVal = it.values()[0]
						def fourthLvlId = totVal.substring(6);
						//insertFourthLvlIdsIntoTempTable(moduleCode,menuCode,fourthLvlId,"0")						
					}
				}
			}
		}catch(Exception exception){
			log.info("Exception in MkRolesUtilsService getSelectedLevelData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return selLvlMenData
	}
	
	/**
	 * Method Name: getAllLevelDataBasedOnLevel
	 * Description: gets the all level data
	 *
	 * @return : level list
	 */
	def getAllLevelDataBasedOnLevel(moduleCode,levelId,menuCode) {
		def result = null,str=null
		Sql sql
		try{
			if(moduleCode.toString().length()==1){
				moduleCode="0"+moduleCode;
			}
			if(menuCode.toString().length()==1){
				menuCode="0"+menuCode;
			}else if(menuCode.toString().length()>1){
				menuCode="0"+menuCode;
			}
			str = new StringBuilder("select distinct VC_MENU_CODE,VC_MENU_OBJECT from MK_MODULE_MENU WHERE VC_MODULE_CODE = '"+moduleCode+"' AND ");
			if(levelId.equals("1")){
				levelId=1;				
				str.append("LENGTH(VC_MENU_CODE)="+(levelId *2)+" ");
			}else if(levelId.equals("2")){
				levelId=2;
				str.append("VC_MENU_CODE LIKE '"+menuCode+"%' AND LENGTH(VC_MENU_CODE)="+(levelId *2)+" ");
			}else if(levelId.equals("3")){
				levelId=3;
				str.append("VC_MENU_CODE LIKE '"+menuCode+"%' AND LENGTH(VC_MENU_CODE)="+(levelId *2)+" ");
			}else if(levelId.equals("4")){
				levelId=4;
				str.append("VC_MENU_CODE LIKE '"+menuCode+"%' AND LENGTH(VC_MENU_CODE)="+(levelId *2)+" ");
			}	
						
			str.append("and CH_ACTIVE='Y' order by VC_MENU_OBJECT");
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())
		}catch(Exception exception){
			log.info("Exception in MkRolesUtilsService getAllLevelDataBasedOnLevel method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
}
