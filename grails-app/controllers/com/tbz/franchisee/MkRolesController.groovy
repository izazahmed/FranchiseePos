package com.tbz.franchisee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray;
import com.google.gson.JsonArray;

@Transactional(readOnly = true)
class MkRolesController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def scaffold = MkRoles
	JSONObject jsonObj
	def mkRolesUtilsService
	def firstLevelMenuPage(){
		try{
			def resultMap=[:]
			def selectedsDataMap=[:]
			def moduleSelectionId=params?.moduleSelectionId
			def secondLvlSelectedVal=params?.secondLvlSelectedVal		
			def moduleCode=params?.firstlevelMenuId
			selectedsDataMap=mkRolesUtilsService.getAllselectedData(secondLvlSelectedVal,moduleCode)
			resultMap=mkRolesUtilsService.getAllMenus(moduleCode,1, null)
			[firstLevelMenu: resultMap,moduleCode:moduleCode,selectedsDataMap:selectedsDataMap]
		}catch(Exception e){
				//flash.message = message(code: e.printStackTrace() , args: [message(code: 'mkRoles.label', default: 'MkRoles'), mkRolesInstance.id])
				log.info("Error in firstLevelMenuPage() of MkRolesController class:::"+e.printStackTrace())
		}
	}	
	def secondLvlMenuPage(){
		def secmenuId,moduleCode,secondSelectionData
		def resultMap=[:]
		try{
				 secmenuId=params?.secondlevelMenuId
				 moduleCode=params?.firstlevelMenuId		
				 secondSelectionData=mkRolesUtilsService.getsecondLvlDataFrmTemp(moduleCode,secmenuId)		 
				resultMap=mkRolesUtilsService.getAllMenus(moduleCode,2, secmenuId)
				[secondLevelMenu: resultMap,firstlevelMenuId:moduleCode,secmenuId:secmenuId,secondSelectionData:secondSelectionData]
		}catch(Exception e){
			log.info("Error in secondLvlMenuPage() of MkRolesController class:::"+e.printStackTrace())
		}
	}
	def thirdLvlMenuList(){
		def secmenuId,moduleCode,thirdlevelMenuId
		def resultMap=[:]
		try{
				 secmenuId=params?.secondlevelMenuId
				 moduleCode=params?.firstlevelMenuId
				 thirdlevelMenuId=params?.thirdlevelMenuId		
				resultMap=mkRolesUtilsService.getAllMenus(moduleCode,3, thirdlevelMenuId)
				[secondLevelMenu: resultMap,firstlevelMenuId:moduleCode,secmenuId:secmenuId]
		}catch(Exception e){
				log.info("Erroe in thirdLvlMenuList() of MkRolesController class:::"+e.printStackTrace())
		}
	}

	def index() {		
		def complist = [:]
		def moduleId,viewRoleName,viewBrId,viewRoleId,viewFirLvlMenuCode
		try{
			moduleId=params?.viewModId
			viewRoleName=params?.viewRoleName
			viewBrId=params?.viewBrId
			viewFirLvlMenuCode=params?.viewFirLvlMenuCode
			viewRoleId=params?.viewRoleId
			complist=mkRolesUtilsService.getCompName()
			if(moduleId){
				[complist: complist,moduleId: moduleId,viewRoleName:viewRoleName,viewBrId:viewBrId,viewFirLvlMenuCode:viewFirLvlMenuCode,viewRoleId:viewRoleId]
			}else{
				mkRolesUtilsService.deleteModuleDataFronTempTable()				
				[complist: complist]
			}
		}catch(Exception e){
		log.info("Erroe in index() of MkRolesController class:::"+e.printStackTrace())
	}
	}

	def show(MkRoles mkRolesInstance) {
		respond mkRolesInstance
	}

	def create() {
		def moduleSelectionId,menuVal,roleNames,selectedModuleId
		def complist=[:]
		try{
		//mkRolesUtilsService.getModuleDataFronTempTable()
		moduleSelectionId=params?.moduleSelectionId
		selectedModuleId=params?.firstlevelMenuId		
		menuVal=mkRolesUtilsService.getMkAllModuleNm()
		roleNames=mkRolesUtilsService.getAllRoleName()
		complist=mkRolesUtilsService.getCompName()
		def brAndRoleData = mkRolesUtilsService.getBranchAndRoleData()
		[complist: complist , menuList:menuVal,roleNames:roleNames,selectedModuleId:selectedModuleId,brAndRoleData:brAndRoleData]
		}catch(Exception e){
		log.info("Erroe in create() of MkRolesController class:::"+e.printStackTrace())
	}
	}

	@Transactional
	def save(MkRoles mkRolesInstance) {
		def mkUsers = mkRolesUtilsService.saveMkRoles(mkRolesInstance)
		redirect (action : "index" , mkRolesInstance : mkRolesInstance)

		/*if (mkRolesInstance == null) {
		 notFound()
		 return
		 }
		 if (mkRolesInstance.hasErrors()) {
		 respond mkRolesInstance.errors, view:'create'
		 return
		 }
		 mkRolesInstance.save flush:true
		 request.withFormat {
		 form multipartForm {
		 flash.message = message(code: 'default.created.message', args: [message(code: 'mkRoles.label', default: 'MkRoles'), mkRolesInstance.id])
		 redirect mkRolesInstance
		 }
		 '*' { respond mkRolesInstance, [status: CREATED] }
		 }*/
	}
	
	/**
	 * Method Name: getViewDataBasedOnComp
	 * Description: gets the saved view details data
	 *
	 * @return : saved rate details list
	 */
	def getViewDataBasedOnBranch() {
		def viewListData
		try{
			//Calling getViewListData method of mkRolesUtilsService to get the view data
			viewListData = mkRolesUtilsService.getViewListData(params)
			jsonObj = new JSONObject()
			jsonObj.putAt("BR_CODE", viewListData?.BR_CODE);
			jsonObj.putAt("BR_NAME", viewListData?.BR_NAME);
			jsonObj.putAt("CH_ROLE_CODE", viewListData?.CH_ROLE_CODE);
			jsonObj.putAt("VC_ROLE_NAME", viewListData?.VC_ROLE_NAME);
			jsonObj.putAt("VC_MODULE_CODE", viewListData?.VC_MODULE_CODE);
			jsonObj.putAt("VC_MODULE_OBJECT", viewListData?.VC_MODULE_OBJECT);
		}catch(Exception exception){
			log.info("Exception in MkRolesController getViewDataBasedOnComp method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
		return
	}
	
	/**
	 * Method Name: getModuleList
	 * Description: used to fetch the module data
	 *
	 * @return : json data of module
	 */
	def getModuleList() {
		def moduleData
		try{
			//Calling getModuleData method of mkRolesUtilsService to get module data
			moduleData = mkRolesUtilsService.getModuleData();
			jsonObj = new JSONObject()
			jsonObj.putAt("VC_MODULE_CODE", moduleData?.VC_MODULE_CODE);
			jsonObj.putAt("VC_MODULE_OBJECT", moduleData?.VC_MODULE_OBJECT);
		}catch(Exception exception){
			log.info("Exception in MkRolesController getModuleList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	 }
	
	/**
	 * Method Name: isNextMenuAailOrNotForViewOrEdit
	 * Description: used to get count of is next menus are there or not
	 *
	 * @return : all level json data
	 */
	def isNextMenuAailOrNotForViewOrEdit(){
		def modId,roleId,levelId,menuId,countVal
		try{
			modId = params?.modId
			roleId = params?.roleId
			levelId = params?.levelId
			menuId = params?.menuId
			//Calling getNextLevelForViewOrEdit method of mkRolesUtilsService to get next level count
			countVal = mkRolesUtilsService.getNextLevelForViewOrEdit(modId,roleId,levelId,menuId)
			jsonObj = new JSONObject()
			jsonObj.putAt("countVal", countVal);
		}catch(Exception exception){
			log.info("Exception in MkRolesController isNextMenuAailOrNotForViewOrEdit method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	/**
	 * Method Name: viewFirstLevelMenuPage
	 * Description: used to display viewFirstLevelMenuPage
	 *
	 * @return : moduleId,roleId
	 */
	def viewFirstLevelMenuPage(){
		def modId,roleId,viewRoleName,viewBrId,viewFirLvlMenuCode,typeValue
		try{
			modId=params?.viewModId
			roleId=params?.viewRoleId
			viewRoleName=params?.viewRoleName
			viewBrId=params?.viewBrId
			viewFirLvlMenuCode=params?.viewFirLvlMenuCode
			typeValue=params?.typeValue
		}catch(Exception exception){
			log.info("Exception in MkRolesController viewFirstLevelMenuPage method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[modId:modId,roleId:roleId,viewBrId:viewBrId,viewRoleName:viewRoleName,viewFirLvlMenuCode:viewFirLvlMenuCode,typeValue:typeValue]
	}
	
	/**
	 * Method Name: viewSecondLevelMenuPage
	 * Description: used to display viewSecondLevelMenuPage
	 *
	 * @return : moduleId,roleId
	 */
	def viewSecondLevelMenuPage(){
		def modId,roleId,viewRoleName,viewBrId,viewFirLvlMenuCode,viewSecLvlMenuCode,typeValue
		try{
			modId=params?.viewModId
			roleId=params?.viewRoleId
			viewRoleName=params?.viewRoleName
			viewBrId=params?.viewBrId
			viewFirLvlMenuCode=params?.viewFirLvlMenuCode
			viewSecLvlMenuCode=params?.viewSecLvlMenuCode
			typeValue=params?.typeValue			
		}catch(Exception exception){
			log.info("Exception in MkRolesController viewFirstLevelMenuPage method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[modId:modId,roleId:roleId,viewRoleName:viewRoleName,viewBrId:viewBrId,viewFirLvlMenuCode:viewFirLvlMenuCode,viewSecLvlMenuCode:viewSecLvlMenuCode,typeValue:typeValue]
	}
	
	/**
	 * Method Name: viewThirdLevelMenuPage
	 * Description: used to display viewThirdLevelMenuPage
	 *
	 * @return : moduleId,roleId
	 */
	def viewThirdLevelMenuPage(){
		def modId,roleId,viewRoleName,viewBrId,viewFirLvlMenuCode,viewSecLvlMenuCode,viewThiLvlMenuCode,typeValue
		try{
			modId=params?.viewModId
			roleId=params?.viewRoleId
			viewRoleName=params?.viewRoleName
			viewBrId=params?.viewBrId
			viewFirLvlMenuCode=params?.viewFirLvlMenuCode
			viewSecLvlMenuCode=params?.viewSecLvlMenuCode
			viewThiLvlMenuCode=params?.viewThiLvlMenuCode
			typeValue=params?.typeValue
		}catch(Exception exception){
			log.info("Exception in MkRolesController viewThirdLevelMenuPage method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[modId:modId,roleId:roleId,viewRoleName:viewRoleName,viewBrId:viewBrId,viewFirLvlMenuCode:viewFirLvlMenuCode,viewSecLvlMenuCode:viewSecLvlMenuCode,viewThiLvlMenuCode:viewThiLvlMenuCode,typeValue:typeValue]
	}
	
	/**
	 * Method Name: getSelDataBasedOnLevel
	 * Description: used to get selected Data Based On Level
	 *
	 * @return : selected level json data
	 */
	def getSelDataBasedOnLevel(){
		def moduleCode,roleCode,levelId,menuCode,typeValue,selMenuViewData
		try{
			moduleCode = params?.modId
			roleCode = params?.roleId
			levelId = params?.levelId
			menuCode = params?.menuCode
			typeValue = params?.typeValue
			//Calling getSelectedLevelData method of mkRolesUtilsService to get selected level data
			selMenuViewData = mkRolesUtilsService.getSelectedLevelData(moduleCode,roleCode,levelId,menuCode,typeValue)
			jsonObj = new JSONObject()
			jsonObj.putAt("VC_MENU_CODE", selMenuViewData?.VC_MENU_CODE);
		}catch(Exception exception){
			log.info("Exception in MkRolesController getSelDataBasedOnLevel method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	/**
	 * Method Name: getAllDataBasedOnLevel
	 * Description: used to get all Data BasedOnLevel
	 *
	 * @return : all level json data
	 */
	def getAllDataBasedOnLevel(){
		def moduleCode,levelId,menuCode,allMenViewData
		try{
			moduleCode = params?.modId
			levelId = params?.levelId
			menuCode = params?.menuCode
			//Calling getAllLevelDataBasedOnLevel method of mkRolesUtilsService to get all level data
			allMenViewData = mkRolesUtilsService.getAllLevelDataBasedOnLevel(moduleCode,levelId,menuCode)
			jsonObj = new JSONObject()
			jsonObj.putAt("VC_MENU_CODE", allMenViewData?.VC_MENU_CODE);
			jsonObj.putAt("VC_MENU_OBJECT", allMenViewData?.VC_MENU_OBJECT);
		}catch(Exception exception){
			log.info("Exception in MkRolesController getAllDataBasedOnLevel method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
	}
	
	def edit(MkRoles mkRolesInstance) {
		respond mkRolesInstance
	}
	
	def getRoleNames(){
		JSONObject jsonObj = new JSONObject()
		jsonObj.putAt("ADDRESS_PROOF","ssss")
		render jsonObj as JSON
		return
	}

	def  getmodulesAvail(){
		def moduleCode=params?.moduleId
		def resultMap=[:]
		resultMap=mkRolesUtilsService.getAllMenusAvailable(moduleCode,1, null)
		render resultMap
		return
	}
	def  getFirstLvlmodulesAvail(){
		def moduleCode=params?.moduleId
		def secondlevelMenuId=params?.secondlevelMenuId
		def resultMap=[:]
		resultMap=mkRolesUtilsService.getAllMenusAvailable(moduleCode,2, secondlevelMenuId)
		render resultMap
		return
	}
	def  getThirdLvlmodulesAvail(){
		def moduleCode=params?.moduleId
		def secondlevelMenuId=params?.secondlevelMenuId
		def thirdLvlId=params?.thirdLvlId
		def resultMap=[:]
		resultMap=mkRolesUtilsService.getAllMenusAvailable(moduleCode,3, secondlevelMenuId)
		render resultMap
		return
	}
	def  insertIntoTempTable(){
		def moduleId=params?.moduleId
		mkRolesUtilsService.insertModuleIdsIntoTempTable(moduleId)
	}
	
	
	def  insertIntoTempTableForfirstLvlMenu(){
		def moduleId=params?.moduleId
		def firstlevelMenuId=params?.firstlevelMenuId
		def insert=params?.insert		
		
		mkRolesUtilsService.insertfirstLvlIdsIntoTempTable(moduleId,firstlevelMenuId,insert)
}

	def  deletefirstLvlIds(){
		def moduleId=params?.moduleId
		def firstlevelMenuId=params?.firstlevelMenuId
		def delete=params?.delete
		mkRolesUtilsService.deletefirstLvlIdsIntoTempTable(moduleId,firstlevelMenuId,delete)
}
	def  insertIntoTempTableForsecondLvlMenu(){
		def moduleId=params?.moduleId
		def firstLvlId=params?.firstLvlId
		def thirdLvlId=params?.thirdLvlId
		def insert=params?.insert		
		//{thirdLvlId:allsecondLvlMenuId,moduleId:moduleId,firstLvlId:firstLvlId,insert:'1'}  
		mkRolesUtilsService.insertsecondLvlIdsIntoTempTable(moduleId,firstLvlId,thirdLvlId,insert)
}
	def  deleteIntoTempTableForsecondLvlMenu(){
		def moduleId=params?.moduleId
		def firstLvlId=params?.firstLvlId
		def thirdLvlId=params?.thirdLvlId
		def insert=params?.insert
		mkRolesUtilsService.deletesecondLvlIdsIntoTempTable(moduleId,firstLvlId,thirdLvlId,insert)
}
	
	/**
	 * Method Name: insertIntoTempTableForThirdLvlMenu
	 * Description: used to insert third level data into temp table
	 *
	 * @return : void
	 */
	def insertIntoTempTableForThirdLvlMenu(){
		def moduleId,firstLvlId,secondLvlId,butMenuIdThi,insert
		try{			
			moduleId=params?.modId
			firstLvlId=params?.firstLvlId
			secondLvlId=params?.secondLvlId
			butMenuIdThi=params?.butMenuIdThi
			insert=params?.insert
			//Calling insertThirdLvlIdsIntoTempTable method of mkRolesUtilsService to insert third level data temp table
			mkRolesUtilsService.insertThirdLvlIdsIntoTempTable(moduleId,firstLvlId,secondLvlId,butMenuIdThi,insert)
		}catch(Exception exception){
			log.info("Exception in MkRolesController insertIntoTempTableForThirdLvlMenu method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: deleteThirdLvlIdsFromTemp
	 * Description: used to delete third level data from temp table
	 *
	 * @return : void
	 */
	def deleteThirdLvlIdsFromTemp(){
		def modId,firstLvlId,secondLvlId,butMenuIdThi,delete
		try{
			modId=params?.modId
			firstLvlId=params?.firstLvlId
			secondLvlId=params?.secondLvlId
			butMenuIdThi=params?.butMenuIdThi
			delete=params?.delete
			//Calling deleteThirdLvlIdsFromTempTable method of mkRolesUtilsService to delete third level data
			mkRolesUtilsService.deleteThirdLvlIdsFromTempTable(modId,firstLvlId,secondLvlId,butMenuIdThi,delete)
		}catch(Exception exception){
			log.info("Exception in MkRolesController deleteThirdLvlIdsFromTempTable method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	def  deleteIntoTempTable(){
		def moduleId=params?.moduleId
		mkRolesUtilsService.deleteModuleIdsIntoTempTable(moduleId)
			}	
	
	def insertIntoMainTable(){
		def brId=params?.branchId
		def rollNm=params?.rollName
		mkRolesUtilsService.insertMenuDataInMainDatraBase(brId,rollNm)
	}	
	def insertIntoTempTableBasedOnSelectedRole(){
		//{roleName:roleName,rollId:rollId}
		def roleName=params?.roleName
		def role_id=params?.role_id
		def br_code=params?.br_code
		def moduleId=mkRolesUtilsService.insertMenuinTempTableBasedOnRoleId(roleName,role_id,br_code)
		render moduleId
		return
	}
	def updateIntoMainTable(){
		def role_ids=params?.role_ids
		def rollNm=params?.rollName
		def branchCode=params?.branchCode
		mkRolesUtilsService.updateMenuDataInMainDatraBase(role_ids,rollNm,branchCode)
	}
	def deleteModuleIds(){
		def moduleId=params?.chkedKey
		def branchId=params?.branchId
		
		mkRolesUtilsService.deletemoduleDataFromTempTable(moduleId,branchId)
	}
	
}
