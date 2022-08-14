package com.tbz.franchisee

/*
 * This is an unpublished work containing TBZ confidential and proprietary
 * information.  Disclosure, use or reproduction without the written
 * authorization of TBZ is prohibited.  If publication occurs, the following
 * notice applies:
 *
 * Copyright (C) 2015-2016, TBZ All rights reserved.
 */

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.gsp.PageRenderer
import grails.transaction.Transactional

import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * ReportsController
 * Description
 *
 * @author (CTE).
 *
 * Contact () (optional)
 *
 * @version    0.1
 * @date        11/02/2016
 *
 * MOD HISTORY
 * DATE           USER      		COMMENTS
 *  11/02/2016	  Izaz     Created File
 *
 */

@Transactional(readOnly = true)
class ReportsController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def reportService
	def exportService	
	def ecsDateEntryUtilsService
	def grailsApplication
	def summaryCashList,summaryCashBackList
	JSONObject jsonObj
	def outputStream = null
	PageRenderer groovyPageRenderer
	def pdfRenderingService
	def renderingService
	
	/**
	 * Method Name: index
	 * Description: Showing home page of reports index
	 *
	 * @return : nothing
	 */
	def index() {
	}
		
	/**
	 * Method Name: rateDetailsReport
	 * Description: displays the rate details landing page
	 *
	 * @return : void
	 */
	def rateDetailsReport() {
		
	}
	
	/**
	 * Method Name: createRateDtReport
	 * Description: displays the rate details report page
	 *
	 * @return : list of dtRateDetailResult
	 */
	def createRateDtReport(DtRateDetail dtRateDetailInstance) {
		def dtRateDetailResult
		try{							
			if(params?.format1 && params.format1 != "html"){
				//Calling generateRateDtReport method of reportService to get the data of rate details report
				dtRateDetailResult = reportService.generateRateDtReport(params.fromDate, params.toDate, params.brName)	
				if(params?.format1 == 'view') {
					[dtRateDetailResult : dtRateDetailResult]					
				} else {
					outputStream = response.outputStream
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=rateDetailsReport.${params.extension}")
					if(dtRateDetailResult!=null && !dtRateDetailResult.isEmpty()){
						exportService.export(params.format1, outputStream,dtRateDetailResult, [:], [:])
					}else{
						redirect (action : "rateDetailsReport", params:[dataVal:"No Data Found"])
					}
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createRateDtReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
			if (outputStream != null){
				try {
					outputStream.close()
				} catch (IOException e) {
					log.info('Exception on close', e)
				}
			}
		}		
	}
	
	/**
	 * Method Name: kpCountReport
	 * Description: displays the kp count landing page
	 *
	 * @return : void
	 */
	def kpCountReport() {
	}
	
	/**
	 * Method Name: createKpCountReport
	 * Description: displays the kp count report page
	 *
	 * @return : multiple list of custMstResult,countVal
	 */
	def createKpCountReport(RegDt regDtInstance) {
		def custMstResult
		def countVal
		try{			
			if(params?.format1 && params.format1 != "html"){
				//Calling generateKpCountReport method of reportService to get the data of kp count report
				custMstResult = reportService.generateKpCountReport(params.fromDate, params.toDate,params?.schemeNo,params?.brName, session.companyCode, session.brCode)
				//Calling generateKpCountVal method of reportService to get the count of kp count report
				countVal = reportService.generateKpCountVal(params.fromDate, params.toDate,params?.schemeNo,params?.brName, session.companyCode, session.brCode)				
				if(params?.format1 == 'view') {							
					[custMstResult : custMstResult, countVal:countVal]
				} else {
					outputStream = response.outputStream
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=KpCountReport.${params.extension}")
					if(custMstResult!=null && !custMstResult.isEmpty()){
						exportService.export(params.format1, outputStream,custMstResult, [:], [:])
					}else{
						redirect (action : "kpCountReport", params:[dataVal:"No Data Found"])
					}					
				}
			}			
		}catch(Exception exception){
			log.info("Exception in ReportsController createKpCountReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
			if (outputStream != null){
				try {
					outputStream.close()
				} catch (IOException e) {
					log.info('Exception on close', e)
				}
			}
		}
	}

	/**
	 * Method Name: getSchemeNames
	 * Description: gets the scheme name based on scheme no
	 *
	 * @return : list of schemeNameList(scheme data)
	 */
	def getSchemeName(SchemeMst schemeMstInstance) {
		def schemeNameList
		try{
			//Calling getSchemeName method of reportService to get the scheme name
			schemeNameList = reportService.getSchemeName(schemeMstInstance)			
		}catch(Exception exception){
			log.info("Exception in ReportsController getSchemeName method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render template: "description", model: [nameList:schemeNameList]
	}
	
	/**
	 * Method Name: advTransferReport
	 * Description: displays the advance transfer report landing page
	 *
	 * @return : list of transactionTypeResult
	 */
	def advTransferReport() {
		List<String> transactionTypeList = new ArrayList<String>();
		def transactionTypeResult,branchResult
		try{			
			transactionTypeList.add("ADVANCE TRANSFER")
			transactionTypeList.add("KP TRANSFER")
			transactionTypeResult = transactionTypeList		
			branchResult = reportService.getCompName()
			[transactionTypeResult : transactionTypeResult,branchResult : branchResult]
		}catch(Exception exception){
			log.info("Exception in ReportsController advTransferReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
	}

	/**
	 * Method Name: createAdvTransferReport
	 * Description: displays the advance transfer report page
	 *
	 * @return : list of dtCrossAdvSettleResult
	 */
	def createAdvTransferReport(DtCrossAdvSettle dtCrossAdvSettleInstance) {
		def dtCrossAdvSettleResult
		//brcd:922(hd),fdt:1-1-2015,tdt:cur
		try{						
			if(params?.format1 && params.format1 != "html"){
				//Calling generateAdvTransferReport method of reportService to get the data of advance transfer report
				dtCrossAdvSettleResult = reportService.generateAdvTransferReport(params.fromDate, params.toDate, params.brName, params.trType)				
				if(params?.format1 == 'view') {					
					[dtCrossAdvSettleResult : dtCrossAdvSettleResult]
				} else {
					outputStream = response.outputStream
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=advTransferReport.${params.extension}")
					if(dtCrossAdvSettleResult!=null && !dtCrossAdvSettleResult.isEmpty()){
						exportService.export(params.format1, outputStream,dtCrossAdvSettleResult, [:], [:])
					}else{
						redirect (action : "advTransferReport", params:[dataVal:"No Data Found"])
					}					
				}
			}				
		}catch(Exception exception){
			log.info("Exception in ReportsController createAdvTransferReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
			if (outputStream != null){
				try {
					outputStream.close()
				} catch (IOException e) {
					log.info('Exception on close', e)
				}
			}
		}		
	}
	
	/**
	 * Method Name: chkClrnsReport
	 * Description: displays the check clearance landing page
	 *
	 * @return : void
	 */
	def chkClrnsReport() {		
	}
	
	/**
	 * Method Name: createChkClrnsReport
	 * Description: displays the check clearance report page
	 *
	 * @return : list of chkClrnsVal
	 */
	def createChkClrnsReport() {
		def chkClrnsVal
		//brcd:922(hd),fdt:1-1-2015,tdt:cur
		try {						
			if(params?.format1 && params.format1 != "html"){
				//Calling generateChkClrnsReport method of reportService to get the data of check clearance report
				chkClrnsVal = reportService.generateChkClrnsReport(params.fromDate, params.toDate, session.companyCode, session.brCode)				
				if(params?.format1 == 'view') {	
					[chkClrnsVal : chkClrnsVal]
				} else {
					outputStream = response.outputStream
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=chkClrnsReport.${params.extension}")	
					if(chkClrnsVal!=null && !chkClrnsVal.isEmpty()){
						exportService.export(params.format1, outputStream,chkClrnsVal, [:], [:])
					}else{
						redirect (action : "chkClrnsReport", params:[dataVal:"No Data Found"])
					}
				}
			}
		} catch(Exception exception) {
			log.info("Exception in ReportsController createChkClrnsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
			if (outputStream != null){
				try {
					outputStream.close()
				} catch (IOException e) {
					log.info('Exception on close', e)
				}
			}
		}
	}

	/**
	 * Method Name: salesPanDtlsReport
	 * Description: displays the pan details landing page
	 *
	 * @return : multiple list of coNameResult,typeListResult
	 */
	def salesPanDtlsReport() {
		def branchResult
		List<String> typeList = new ArrayList<String>();
		def typeListResult
		try{							
			typeList.add("SALE")
			typeList.add("PURCHASE")
			typeListResult = typeList				
			//Calling getCompName method of reportService to get the data of company name
			branchResult = reportService.getCompName()
			[typeListResult:typeListResult,branchResult:branchResult]
		}catch(Exception exception){
			log.info("Exception in ReportsController  salesPanDtlsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}				
	}

	/**
	 * Method Name: createPanCardReport
	 * Description: displays the pan card report page
	 *
	 * @return : list of panCardReport
	 */
	def createPanCardReport() {
		def panCardReport
		//coname:01 (hd),type:sale,fdt:1-1-2015,tdt:cur
		try{				
			if(params?.format1 && params.format1 != "html"){
				//Calling generatePanCardReport method of reportService to get the data of panCardReport
				panCardReport = reportService.generatePanCardReport(params.fromDate, params.toDate, params.brName, params.type)
				if(params?.format1 == 'view') {					
					[panCardReport : panCardReport]
				} else {
					outputStream = response.outputStream
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=salesPanDtlsReport.${params.extension}")
					if(panCardReport!=null && !panCardReport.isEmpty()){
						exportService.export(params.format1, outputStream,panCardReport, [:], [:])
					}else{
						redirect (action : "salesPanDtlsReport", params:[dataVal:"No Data Found"])
					}
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createPanCardReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
			if (outputStream != null){
				try {
					outputStream.close()
				} catch (IOException e) {
					log.info('Exception on close', e)
				}
			}
		}		
	}

	/**
	 * Method Name: misReport
	 * Description: displays the mis report landing page
	 *
	 * @return : void
	 */
	def misReport() {
		try{				
		}catch(Exception exception){
			log.info("Exception in ReportsController misReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: createMisReport
	 * Description: displays the mis report page
	 *
	 * @return : list of misReportVal
	 */
	def createMisReport() {
		def misReportVal
		//cat:gold,fdt:1-1-2015,tdt:cur
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling createMisReport method of reportService to get the data of mis report
				misReportVal = reportService.createMisReport(params.fromDate,params.toDate,params?.category,session.companyCode,session.brCode)
				if(params?.format1 == 'view') {							
					[misReportVal : misReportVal]
				} else {
					outputStream = response.outputStream
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=misReport.${params.extension}")
					if(misReportVal!=null && !misReportVal.isEmpty()){
						exportService.export(params.format1, outputStream,misReportVal, [:], [:])
					}else{
						redirect (action : "misReport", params:[dataVal:"No Data Found"])
					}
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createMisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
			if (outputStream != null){
				try {
					outputStream.close()
				} catch (IOException e) {
					log.info('Exception on close', e)
				}
			}
		}	
	}

	/**
	 * Method Name: uwbcReport
	 * Description: displays the un bill count report landing page
	 *
	 * @return : void
	 */
	def uwbcReport() {
		try{
		}catch(Exception exception){
			log.info("Exception in ReportsController uwbcReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createUWBillCountReport
	 * Description: displays the un bill count report page
	 *
	 * @return : multiple list of uwbCountResult,getUWBCdata
	 */
	
/*	byte[] generatePdf(String content) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
			byte[] bytes = null
			ITextRenderer renderer = new ITextRenderer()
			SharedContext sharedContext = renderer.getSharedContext()
			sharedContext.setPrint(true)
			sharedContext.setInteractive(false)
			sharedContext.setReplacedElementFactory(new B64ImgReplacedElementFactoryService()())
			sharedContext.getTextRenderer().setSmoothingThreshold(0)
			ITextUserAgent callback = new ITextUserAgent(renderer.getOutputDevice())
			callback.setSharedContext(renderer.getSharedContext())
			renderer.getSharedContext().setUserAgentCallback(callback)
			if (content != null) {
			renderer.setDocumentFromString(content)
			}
			
			renderer.layout()
			renderer.createPDF(byteArrayOutputStream)
			renderer.finishPDF()
			bytes = byteArrayOutputStream.toByteArray()
			return bytes
		}
		catch (Throwable e) {
			e.printStackTrace(System.out)
		}
			return null
		}*/
	
	def createUWBillCountReport() {
		def getUWBCdata,uwbCountResult
		def resultMap = [:]
		//br:testBranch,fdt : 1-1-2015,tdt:cur
		try{			
			if(params?.format1 && params.format1 != "html"){
				//Calling getBillCountData method of reportService to get the data of un bill count report
				getUWBCdata = reportService.getBillCountData(params.fromDate, params.toDate, params?.brName)
				if(params?.format1 == 'view') {					
					[getUWBCdata:getUWBCdata]					
				} else if(params?.format1 == 'pdf'){				 	 
					if(getUWBCdata!=null && !getUWBCdata.isEmpty()){
						def mypdf = new ByteArrayOutputStream().withStream { outputStream ->
							pdfRenderingService.render(
							[controller:this,
							template: "/reports/createUWBillCountReport",
							  model:[getUWBCdata:getUWBCdata]],
							outputStream // <- in the documentation use the outputstream http://gpc.github.io/grails-rendering/guide/single.html#5.%20Rendering%20To%20The%20Response
							).toByteArray()   // <- parse to byteArray for render file
							}
							response.contentType = 'application/pdf'
							response.setHeader 'Content-disposition', "attachment; filename=uwbcReport.pdf" // comment this to open in browser
							response.outputStream << mypdf
							response.outputStream.flush()
					}else{
						redirect (action : "uwbcReport", params:[dataVal:"No Data Found"])
					}
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createUWBillCountReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])		
		}
	}

	/**
	 * Method Name: cashBackReport
	 * Description: displays the cash back report landing page
	 *
	 * @return : list of getPaymentMode
	 */
	def cashBackReport() {
		def getPaymentMode
		try{
			//Calling getPaymentModeForCashBackReport method of reportService to get the data of cash pay mode
			getPaymentMode = reportService.getPaymentModeForCashBackReport()			
		}catch(Exception exception){
			log.info("Exception in ReportsController cashBackReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[getPaymentMode:getPaymentMode]
	}

	/**
	 * Method Name: createCashBackReport
	 * Description: displays the cash back report page
	 *
	 * @return : list of cashBackReport
	 */
	def createCashBackReport() {
		def cashBackReportList
		//br:01,fdt:1-1-2015,tdt:cur
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getReport method of reportService to get the data of cash back report
				cashBackReportList = reportService.getReport(params.fromDate, params.toDate, params?.chPayMode , session.brCode)
				if(params?.format1 == 'view') {					
					[cashBackReportList:cashBackReportList]
				}else {
					outputStream = response.outputStream
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=cashBackReport.${params.extension}")
					if(cashBackReportList!=null && !cashBackReportList.isEmpty()){
						exportService.export(params.format1, outputStream,cashBackReportList, [:], [:])
					}else{
						redirect (action : "cashBackReport", params:[dataVal:"No Data Found"])
					}
				}
			}				
		}catch(Exception exception){
			log.info("Exception in ReportsController createCashBackReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}finally{
			if (outputStream != null){
				try {
					outputStream.close()
				} catch (IOException e) {
					log.info('Exception on close', e)
				}
			}
		}	
	}

	/**
	 * Method Name: creditCardReport
	 * Description: displays the credit card report landing page
	 *
	 * @return : nothing
	 */
	def creditCardReport() {
	}

	/**
	 * Method Name: createCreditCardReport
	 * Description: displays the credit card report page
	 *
	 * @return : multiple list of creditCardReport,creditCardCountReport
	 */
	def createCreditCardReport() {
		def creditCardReport
		def creditCardCountReport
		try{
			//Calling getCCReport method of reportService to get the data of credit card report
			creditCardReport = reportService.getCCReport(params.fromDate, params.toDate, session.companyCode, session.brCode)
			//Calling amtCount method of reportService to get the data of amtCount
			creditCardCountReport = reportService.amtCount(params.fromDate, params.toDate,session.companyCode, session.brCode)			
		}catch(Exception exception){
			log.info("Exception in ReportsController  createCreditCardReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[creditCardReport:creditCardReport, creditCardCountReport:creditCardCountReport]
	}

	/**
	 * Method Name: salesChkListReport
	 * Description: displays the sales check list report landing page
	 *
	 * @return : multiple lists
	 */
	def salesChkListReport() {
		def empList,subcode,deptt,item,customer
		try{
			//Calling getEmployeeList method of reportService to get the data of Employee list
			empList = reportService.getEmployeeList(session.brCode)			
			//Calling getItem method of reportService to get the data of Item list
			//item = reportService.getItem(session.brCode)			
			//Calling getSubCode method of reportService to get the data of SubCode list
			//subcode = reportService.getSubCode(params)
			//Calling getDeptt method of reportService to get the data of Deptt list
			//deptt = reportService.getDeptt(params)			
			//Calling getCustomer method of reportService to get the data of Customer list
			customer = reportService.getCustomer(session.brCode,session.companyCode)			
		}catch(Exception exception){
			log.info("Exception in ReportsController  salesChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[empList:empList, subcode:subcode, deptt:deptt,item:item, customer:customer]
	}

	/**
	 * Method Name: createChkListReport
	 * Description: displays the check list report page
	 *
	 * @return : list of salesChkList
	 */
	def createChkListReport() {
		def salesChkList
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getSalesList method of reportService to get the data of check list report
				salesChkList = reportService.getSalesList(params.fromDate, params.toDate,params.category,params.empId,params.purity,params.item,params.subCode,params.deptt,params.custName,session.companyCode, session.brCode)
				if(params?.format1 == 'view') {
					[salesChkList:salesChkList]
				}else {
				response.contentType = grailsApplication.config.grails.mime.types[params.format1]
				response.setHeader("Content-disposition", "attachment; filename=cashBackReport.${params.extension}")
				exportService.export(params.format1, response.outputStream,cashBackReport, [:], [:])
			}
		}
		}catch(Exception exception){
			log.info("Exception in ReportsController createChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
	}
	
	/**
	 * Method Name: purchageChkListReport
	 * Description: displays the purchase check list report landing page
	 *
	 * @return : multiple list of categoryList,itemList,supplierList
	 */
	def purchageChkListReport() {
		def categoryList
		def itemList
		def supplierList
		try{
			//Calling getPurCategory method of reportService to get the data of PurCategory
			categoryList = reportService.getPurCategory(session.companyCode, session.brCode)
			//Calling getItemList method of reportService to get the data of Item list
			itemList = reportService.getItemList(params)
			//Calling getSupplier method of reportService to get the data of Supplier list
			supplierList = reportService.getSupplier(params)			
		}catch(Exception exception){
			log.info("Exception in ReportsController  purchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[categoryList:categoryList,itemList:itemList, supplierList:supplierList ]
	}

	/**
	 * Method Name: getPurity
	 * Description: used to get the purity list based on category
	 *
	 * @return :  purity's json data
	 */
	def getPurity() {
		def purityList
		try{
			//Calling getPur method of reportService to get the purity data
			purityList = reportService.getPur(params)
			jsonObj = new JSONObject()
			jsonObj.putAt("PURITY", purityList?.VC_PURITY)			
		}catch(Exception exception){
			log.info("Exception in ReportsController  getPurity method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		render jsonObj as JSON
		return
	}

	/**
	 * Method Name: createPurchageChkListReport
	 * Description: displays the purchase check list report
	 *
	 * @return : list of purchageChkList
	 */
	def createPurchageChkListReport() {
		def purchageChkList
		try{
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			purchageChkList = reportService.getPurchageList(params?.fromDate, params?.toDate,params?.category,params?.empId,params?.purity,params?.item,params?.subCode,params?.deptt,params?.custName,session.companyCode, session.brCode)			
		}catch(Exception exception){
			log.info("Exception in ReportsController  createPurchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[purchageChkList:purchageChkList]
	}
	
	
	/**
	 * Method Name: moduleListReport
	 * Description: displays the module list report
	 *
	 * @return : list of module List
	 */
	def moduleListReport() {
		def moduleList
		try{
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			moduleList = reportService.getModuleList(params)
		}catch(Exception exception){
			log.info("Exception in ReportsController  createPurchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[moduleList:moduleList]
	}
	
	def createModuleListReport(){
		def moduleList
		def brCode = session?.brCode
		try{
			if(params?.format1 && params.format1 != "html"){
				moduleList = reportService.getModuleList(params )
				if(params?.format1 == 'view') {
					[moduleList : moduleList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=moduleList.${params.extension}")
					exportService.export(params.format1, response.outputStream,moduleList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  purchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	def usersRolesListing() {
		def userRoleList
		try{
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			userRoleList = reportService.getUserRoleList(params)
		}catch(Exception exception){
			log.info("Exception in ReportsController  createPurchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[userRoleList:userRoleList]
	}
	
	def createUserRoleListReport(){
		def userRoleList
		def brCode = session?.brCode
		try{
			if(params?.format1 && params.format1 != "html"){
				userRoleList = reportService.getUserRoleList(params )
				if(params?.format1 == 'view') {
					[userRoleList : userRoleList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=UserRoleList.${params.extension}")
					exportService.export(params.format1, response.outputStream,userRoleList, [:], [:])
				}
			}
			
		}catch(Exception exception){
			log.info("Exception in ReportsController  purchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	def menuListingReport(){
		def menuList
		try{
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			menuList = reportService.getMenuList()
		}catch(Exception exception){
			log.info("Exception in ReportsController  createPurchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[menuList:menuList]
	}
	
	def createMenuListReport(){
		def menuList
		def brCode = session?.brCode
		try{
			if(params?.format1 && params.format1 != "html"){
				menuList = reportService.getMenuList()
				if(params?.format1 == 'view') {
					[menuList : menuList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=MenuList.${params.extension}")
					exportService.export(params.format1, response.outputStream,menuList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  purchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	def roleListingReport(){
		def roleList
		try{
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			roleList = reportService.getRoleList()
		}catch(Exception exception){
			log.info("Exception in ReportsController  createPurchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[roleList:roleList]
	}
	
	def createRoleListReport(){
		def roleList
		def brCode = session?.brCode
		try{
			if(params?.format1 && params.format1 != "html"){
				roleList = reportService.getRoleList()
				if(params?.format1 == 'view') {
					[roleList : roleList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=RolesList.${params.extension}")
					exportService.export(params.format1, response.outputStream,roleList, [:], [:])
				}
			}
			
		}catch(Exception exception){
			log.info("Exception in ReportsController  purchageChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	def advanceActiveReport(){
		def categoryListResult
		def goldTypeListResult
		def payModeListResult
		def categoryMap = [:]
		def goldTypeMap = [:]
		def payModeMap = [:]
		
		try{
			
			categoryMap.put("GOLD", 'G')
			categoryMap.put("DIAMOND", 'D')
			categoryMap.put("SILVER", 'S')
			categoryMap.put("ALL", 'A')
			categoryListResult = categoryMap
			
			
			goldTypeMap.put("Custom Order", 'G')
			goldTypeMap.put("DIAMOND", 'D')
			goldTypeMap.put("SILVER", 'S')
			goldTypeMap.put("ALL", 'A')
			goldTypeListResult = categoryMap
			
			
			payModeMap.put("GOLD", 'G')
			payModeMap.put("DIAMOND", 'D')
			payModeMap.put("SILVER", 'S')
			payModeMap.put("ALL", 'A')
			payModeListResult = payModeMap
		}catch(Exception exception){
			log.info("Exception in ReportsController  advanceActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[categoryListResult : categoryListResult]
	}
	
	def createAdvanceActiveReport(){
		def advanceActiveList
		def brCode = session?.brCode
		try{
			if(params?.format1 && params.format1 != "html"){
				advanceActiveList = reportService.getAdvanceActiveReport(params.fromDate, params.toDate,params?.category,params?.goldType,params?.payMode, session.companyCode, session.brCode)
				if(params?.format1 == 'view') {
					[advanceActiveList : advanceActiveList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=RolesList.${params.extension}")
					exportService.export(params.format1, response.outputStream,advanceActiveList, [:], [:])
				}
			}
			
		}catch(Exception exception){
			log.info("Exception in ReportsController  createAdvanceActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	def giftVoucherActiveReport(){
	}
	
	def createGiftVoucherActiveReport(){
		def giftVoucherActiveReportVal
		def brCode = session?.brCode
		try{
			if(params?.format1 && params.format1 != "html"){
				giftVoucherActiveReportVal = reportService.getGiftVoucherActiveReport(params.fromDate, params.toDate,session.companyCode, session.brCode)
				if(params?.format1 == 'view') {
					[giftVoucherActiveReportVal : giftVoucherActiveReportVal]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=giftVoucherActiveReportVal.${params.extension}")
					exportService.export(params.format1, response.outputStream,giftVoucherActiveReportVal, [:], [:])
				}
			}		
		}catch(Exception exception){
			log.info("Exception in ReportsController  createGiftVoucherActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: advanceSettleReport
	 * Description: displays the advanceSettleReport landing page
	 *
	 * @return : list of payModeList
	 */
	def advanceSettleReport(){
		def payModeList,descList
		try{
			//Calling getPayModeDataForAdvSetlReport method of reportService to get the data of paymode
			payModeList = reportService.getPayModeDataForAdvSetlReport()
			//Calling getDescriptionDataForAdvSetlReport method of reportService to get the description
			descList = reportService.getDescriptionDataForAdvSetlReport()
			[payModeList:payModeList,descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController advanceSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createPurchageChkListReport
	 * Description: displays the AdvanceSettleReport report
	 *
	 * @return : list of advanceSettleList
	 */
	def createAdvanceSettleReport(){
		def advanceSettleList,AccRefNo
		try{
			if(params?.format1 && params.format1 != "html"){
				advanceSettleList = reportService.getAdvanceSettleReport(params.fromDate, params.toDate, params.paymode, params.category, session.companyCode, session.brCode)
				if(advanceSettleList){
					AccRefNo = reportService.getAccRefNo()
				}
				if(params?.format1 == 'view') {						
					[advanceSettleList : advanceSettleList,AccRefNo:AccRefNo]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=advanceSettleReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,advanceSettleList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createAdvanceSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: travellerForeignCurrencyReport
	 * Description: displays the travellerForeignCurrencyReport landing page
	 *
	 * @return : void
	 */
	def travellerForeignCurrencyReport(){
	}
	
	/**
	 * Method Name: createTravFrgnCrncyReport
	 * Description: displays the TravFrgnCrncyReport
	 *
	 * @return : map of travelFrgnCrncyMap
	 */
	def createTravFrgnCrncyReport(){
		def travelFrgnCrncyMap
		def tr,co,str,coName,traFrgncAddress,category
		def mainHead=[:]
		try{
			if(params?.format1 && params.format1 != "html"){
				tr = MstCompany.findAllByVcCompCode(session.companyCode)
				co = tr?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				traFrgncAddress = reportService.getOgSaleCheckListReportAddress(session)
				mainHead.put("fromDate",params.fromDate);
				mainHead.put("toDate",params.toDate);
				category = params.category
				mainHead.put("cat",category);
				mainHead.put("coName",coName);
				Date today = new Date();
				mainHead.put("today",today);
				travelFrgnCrncyMap = reportService.getTravFrgnCrncyReport(params.fromDate,session.companyCode,session.brCode)
				if(params?.format1 == 'view') {					
					[travelFrgnCrncyMap : travelFrgnCrncyMap,mainHead:mainHead,traFrgncAddress:traFrgncAddress]
				}else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=travellerForeignCurrencyReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,travelFrgnCrncyMap, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createTravFrgnCrncyReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: chequeToBeDepositReport
	 * Description: displays the chequeToBeDepositReport landing page
	 *
	 * @return : void
	 */
	def chequeToBeDepositReport(){
	}
	
	/**
	 * Method Name: createChequeToBeDepositReport
	 * Description: displays the ChequeToBeDepositReport
	 *
	 * @return : list of chequeToBeDepositList
	 */
	def createChequeToBeDepositReport(){
		def chequeToBeDepositList
		try{
			if(params?.format1 && params.format1 != "html"){
				chequeToBeDepositList = reportService.getChequeToBeDepositReport(params.fromDate,params.toDate,session.companyCode,session.brCode)
				if(params?.format1 == 'view') {					
					[chequeToBeDepositList : chequeToBeDepositList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=chequeToBeDepositReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,chequeToBeDepositList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createChequeToBeDepositReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: branchWiseSummaryReport
	 * Description: displays the branchWiseSummaryReport landing page
	 *
	 * @return : void
	 */
	def branchWiseSummaryReport(){
	
	}
	
	/**
	 * Method Name: createBranchWiseSummaryReport
	 * Description: displays the ChequeToBeDepositReport
	 *
	 * @return : list of createBranchWiseSummary
	 */
	def createBranchWiseSummaryReport(){
		def branchwiseSummaryList
		def brCode = session?.brCode
		try{
			
			if(params?.format1 && params.format1 != "html"){
				branchwiseSummaryList = reportService.getBranchWiseSummaryReport(params.fromDate, params.toDate,params?.schemeName,session.companyCode, session.brCode)
				if(params?.format1 == 'view') {
					[branchwiseSummaryList : branchwiseSummaryList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=BranchwiseSummaryList.${params.extension}")
					exportService.export(params.format1, response.outputStream,branchwiseSummaryList, [:], [:])
				}
			}
			
		}catch(Exception exception){
			log.info("Exception in ReportsController  createBranchWiseSummaryReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: miscellaneousRecieptsReport
	 * Description: displays the miscellaneousRecieptsReport landing page
	 *
	 * @return : void
	 */
	def miscellaneousRecieptsReport() {
	}
	
	/**
	 * Method Name: createMiscellaneousRecieptsReport
	 * Description: displays the MiscellaneousRecieptsReport
	 *
	 * @return : list of miscellaneousRecieptsReportList
	 */
	def createMiscellaneousRecieptsReport(){
		def miscellaneousRecieptsReportList
		try{
			if(params?.format1 && params.format1 != "html"){
				miscellaneousRecieptsReportList = reportService.getMiscellaneousRecieptsReport(params.fromDate, params.toDate,params.category,session.companyCode, session.brCode)					
				if(params?.format1 == 'view') {
					[miscellaneousRecieptsReportList : miscellaneousRecieptsReportList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=miscellaneousRecieptsReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,miscellaneousRecieptsReportList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createMiscellaneousRecieptsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: cashCounterReport
	 * Description: displays the cashCounterReport landing page
	 *
	 * @return : void
	 */
	def cashCounterReport() {
	}
	
	/**
	 * Method Name: createCashCounterReport
	 * Description: displays the CashCounterReport
	 *
	 * @return : list of cashCounterReportList
	 */
	def createCashCounterReport(){
		def cashCounterReportList		
		try{
			if(params?.format1 && params.format1 != "html"){
				cashCounterReportList = reportService.getCashCounterReport(params.fromDate, session.companyCode, session.brCode,params.machineId,params.machineNo)
				if(params?.format1 == 'view') {					
					[cashCounterReportList : cashCounterReportList]
				}else{
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=cashCounterReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,cashCounterReportList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createCashCounterReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: errorLogReport
	 * Description: displays the errorLogReport landing page
	 *
	 * @return : void
	 */
	def errorLogReport() {
	}
	
	/**
	 * Method Name: createErrorLogReport
	 * Description: displays the ErrorLogReport
	 *
	 * @return : list of ErrorLogReportList
	 */
	def createErrorLogReport(){
		def ErrorLogReportList
		try{
			if(params?.format1 && params.format1 != "html"){
				ErrorLogReportList = reportService.getErrorLogReport(params.fromDate, params.toDate,session.companyCode, session.brCode, params.location, params.reportType)
				if(params?.format1 == 'view') {									
					[ErrorLogReportList : ErrorLogReportList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=errorLogReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,ErrorLogReportList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createErrorLogReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: franchiseePurchaseCheckReport
	 * Description: displays the Purchase Checklist landing page
	 *
	 * @return : void
	 */
	def franchiseePurchaseCheckReport() {
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createErrorLogReport
	 * Description: displays the ErrorLogReport
	 *
	 * @return : list of ErrorLogReportList
	 */
	def createFranchiseePurchaseCheckReport(){
		def FranchiseePurchaseCheckReportList
		def FranchiseeStonePurchaseCheckReportList
		try{
			if(params?.format1 && params.format1 != "html"){
				FranchiseePurchaseCheckReportList = reportService.franchiseePurchaseCheckReportService(params.fromDate, params.toDate,params?.category,session.companyCode, session.brCode)
				FranchiseeStonePurchaseCheckReportList = reportService.franchiseeStonePurchaseCheckReportService(params.fromDate, params.toDate,params?.category,session.companyCode, session.brCode)
				if(params?.format1 == 'view') {
					[FranchiseePurchaseCheckReportList : FranchiseePurchaseCheckReportList,FranchiseeStonePurchaseCheckReportList:FranchiseeStonePurchaseCheckReportList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=FranchiseePurchaseCheckReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,FranchiseePurchaseCheckReportList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createErrorLogReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: franchiseePurchaseCheckReport
	 * Description: displays the Purchase Checklist landing page
	 *
	 * @return : void
	 */
	def franchiseeIdealStockReport() {
		
	}
	
	/**
	 * Method Name: createErrorLogReport
	 * Description: displays the ErrorLogReport
	 *
	 * @return : list of ErrorLogReportList
	 */
	def createfranchiseeIdealStockReport(){
		def FranchiseePurchaseCheckReportList
		def FranchiseeStonePurchaseCheckReportList
		try{
			if(params?.format1 && params.format1 != "html"){
				FranchiseePurchaseCheckReportList = reportService.franchiseeIdealStockReport(params.fromDate, params.toDate,params?.brCode,params?.purity,session.companyCode, session.brCode)
				if(params?.format1 == 'view') {
					[FranchiseePurchaseCheckReportList : FranchiseePurchaseCheckReportList,FranchiseeStonePurchaseCheckReportList:FranchiseeStonePurchaseCheckReportList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=FranchiseePurchaseCheckReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,FranchiseePurchaseCheckReportList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createErrorLogReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: labelWiseStockReport
	 * Description: displays the labelWiseStockReport landing page
	 *
	 * @return : void
	 */
	def labelWiseStockReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createBranchWiseSummaryReport
	 * Description: displays the ChequeToBeDepositReport
	 *
	 * @return : list of createBranchWiseSummary
	 */
	def createLabelWiseStockReport(){
		def labelWiseStockList
		def labelWiseStockSumList
		
		def preciousMetalStones = params?.preciousMetalStones
		try{
			
			if(params?.format1 && params.format1 != "html"){
				labelWiseStockList= reportService.getLabelWiseStockReport( params)
				labelWiseStockSumList= reportService.getLabelWiseStockSumReport( params)
				if(params?.format1 == 'view') {
					[labelWiseStockList : labelWiseStockList, labelWiseStockSumList : labelWiseStockSumList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=labelWiseStockReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,labelWiseStockList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createLabelWiseStockListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: franchiseeWeightWiseStockReport
	 * Description: displays the Franchisee weight wise stock Checklist landing page
	 *
	 * @return : void
	 */
	def franchiseeWeightWiseStockReport() {
		
	}
	
	/**
	 * Method Name: createfranchiseeWeightWiseStockReport
	 * Description: displays the report of franchisee wise weight stock report
	 *
	 * @return : list of ErrorLogReportList
	 */
	def createfranchiseeWeightWiseStockReport(){
		def FranchiseePurchaseCheckReportList
		def FranchiseeStonePurchaseCheckReportList
		try{
			if(params?.format1 && params.format1 != "html"){
				FranchiseePurchaseCheckReportList = reportService.franchiseeWeightWiseStockReport()
				if(params?.format1 == 'view') {
					[FranchiseePurchaseCheckReportList : FranchiseePurchaseCheckReportList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=FranchiseePurchaseCheckReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,FranchiseePurchaseCheckReportList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createfranchiseeWeightWiseStockReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: cashAnalysis
	 * Description: displays the landing page of cashAnalysis
	 *
	 * @return : void
	 */
	def cashAnalysisReport() {
	}
	
	/**
	 * Method Name: createCashAnalysis
	 * Description: displays the createCashAnalysis
	 *
	 * @return : list of cashAnalysisList
	 */
	def createCashAnalysis(){
		def reportType,saCashAnalysisList,ssCashAnalysisList,pbCashAnalysisList,gvCashAnalysisList,drCashAnalysisList,atwbCashAnalysisList
		def kpCashAnalysisList,adCashAnalysisList,mrCashAnalysisList,prCashAnalysisList,gcCashAnalysisList,sumCashAnaList		
		try{
			if(params?.format1 && params.format1 != "html"){
					reportType = params.reportType
					if(reportType=='Summary'){
						summaryCashList = reportService.getSummaryCashReport(params,session)
						summaryCashBackList = reportService.getSummaryCashBackReport(params,session)
					}else{
						kpCashAnalysisList = reportService.getKPCashAnalysisReport(params,session)
						adCashAnalysisList = reportService.getADCashAnalysisReport(params,session)
						mrCashAnalysisList = reportService.getMRCashAnalysisReport(params,session)
						saCashAnalysisList = reportService.getSACashAnalysisReport(params,session)
						ssCashAnalysisList = reportService.getSSCashAnalysisReport(params,session)
						pbCashAnalysisList = reportService.getPBCashAnalysisReport(params,session)
						prCashAnalysisList = reportService.getPRCashAnalysisReport(params,session)
						gvCashAnalysisList = reportService.getGVCashAnalysisReport(params,session)
						gcCashAnalysisList = reportService.getGCCashAnalysisReport(params,session)
						drCashAnalysisList = reportService.getDRCashAnalysisReport(params,session)
						atwbCashAnalysisList = reportService.getATWBCashAnalysisReport(params,session)
						sumCashAnaList = reportService.getSumCashAnalysisReport(params,session)						
					}
				if(params?.format1 == 'view') {
					if(reportType=='Summary'){
						redirect (action : "cashAnalysisSummaryReport" , summaryCashList : summaryCashList,summaryCashBackList:summaryCashBackList)
					}else{
						[kpCashAnalysisList:kpCashAnalysisList,adCashAnalysisList:adCashAnalysisList,mrCashAnalysisList:mrCashAnalysisList,saCashAnalysisList:saCashAnalysisList,pbCashAnalysisList:pbCashAnalysisList,prCashAnalysisList:prCashAnalysisList,gvCashAnalysisList:gvCashAnalysisList,gcCashAnalysisList:gcCashAnalysisList,drCashAnalysisList:drCashAnalysisList,atwbCashAnalysisList:atwbCashAnalysisList,sumCashAnaList:sumCashAnaList]
					}
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=cashAnalysisReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,kpCashAnalysisList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createCashAnalysis method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: cashAnalysisSummaryReport
	 * Description: displays the cashAnalysisSummaryReport data
	 *
	 * @return : void
	 */
	def cashAnalysisSummaryReport(summaryCashList,summaryCashBackList) {
		[summaryCashList:summaryCashList,summaryCashBackList:summaryCashBackList]
	}
	
	/**
	 * Method Name: scanningReport
	 * Description: displays the landing page of cashAnalysis
	 *
	 * @return : list of cat,pur,item
	 */
	def scanningReport() {
		def catList,purList,itemCodeList
		try{
			catList = reportService.getCatListForScanningReport(session?.brCode,session.companyCode)
			purList = reportService.getPurListForScanningReport(session?.brCode)
			itemCodeList = reportService.getItemListForScanningReport(session?.brCode)
		}catch(Exception exception){
			log.info("Exception in ReportsController scanningReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[catList:catList,purList:purList,itemCodeList:itemCodeList]
	}
	
	/**
	 * Method Name: createScanningReport
	 * Description: displays the ScanningReport data
	 *
	 * @return : list of scanningDataList
	 */
	def createScanningReport(){
		def mainScaList,phyScaList,deptScaList,purScaList,itemScaList
		try{
			if(params?.format1 && params.format1 != "html"){
				mainScaList = reportService.getMainScanningReport(params,session)
				phyScaList = reportService.getPhyScaReport(params,session)
				deptScaList = reportService.getDeptScaReport(params,session)
				purScaList = reportService.getPurScaReport(params,session)
				itemScaList = reportService.getItemScaReport(params,session)
				if(params?.format1 == 'view') {					
					[mainScaList : mainScaList, phyScaList : phyScaList, deptScaList : deptScaList, purScaList : purScaList, itemScaList : itemScaList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=scanningReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,itemScaList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createScanningReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: kpSettleReport
	 * Description: displays the KP settle page
	 *
	 * @return : void
	 */
	def kpSettleReport(){
		def payModeListforKP
		def kpSettleList
		try{
			payModeListforKP = reportService.getPayModeDataForKpSetlReport()
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			kpSettleList = reportService.getKpSettleReport(params.fromDate,params.toDate,params?.brCode,session.companyCode)
		}catch(Exception exception){
			log.info("Exception in ReportsController  kpUnSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[kpSettleVal:kpSettleList, payModeListforKP : payModeListforKP]
	}
	
	/**
	 * Method Name: createKpSettleReport
	 * Description: displays the KP settle Report data
	 *
	 * @return : list of KPsettleList
	 */
	def createKpSettleReport(){
		def kpSettleList
		try{
			if(params?.format1 && params.format1 != "html"){
				kpSettleList = reportService.getKpSettleReport(params.fromDate,params.toDate,params?.brCode,session.companyCode)
				if(params?.format1 == 'view') {
					[kpSettleVal : kpSettleList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=kpSettleReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,kpSettleList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createkpSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: kpSettleReport
	 * Description: displays the KP settle page
	 *
	 * @return : void
	 */
	def kpUnSettleReport(){
		def kpUnSettleList
		try{
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			kpUnSettleList = reportService.getKpUnSettleReport(params?.brCode,params?.schemeNo)
		}catch(Exception exception){
			log.info("Exception in ReportsController  kpUnSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[kpUnSettleList:kpUnSettleList]
	}
	
	/**
	 * Method Name: createKpSettleReport
	 * Description: displays the KP settle Report data
	 *
	 * @return : list of KPsettleList
	 */
	def createKpUnSettleReport(){
		def kpUnSettleList
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getKpUnSettleReport method of reportService to get the data of KP unsettle
				kpUnSettleList = reportService.getKpUnSettleReport(params?.brCode,params?.schemeNo)
				if(params?.format1 == 'view') {
					[kpUnSettleList : kpUnSettleList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=KPUnsettledReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,kpUnSettleList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  kpUnSettleList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	
	/**
	 * Method Name: monthwisePendingInstallmentReport
	 * Description: displays the KP settle page
	 *
	 * @return : void
	 */
	def monthwisePendingInstallmentReport(){
		def monthwisePendingList
		try{
			//Calling getPurchageList method of reportService to get the data of purchase check list report
			monthwisePendingList = reportService.getMonthWiseSettlementReport(params?.brCode,params?.schemeNo,params?.year,params?.month)
		}catch(Exception exception){
			log.info("Exception in ReportsController  monthwisePendingInstallmentReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[monthwisePendingList:monthwisePendingList]
	}
	
	/**
	 * Method Name: createMonthwisePendingInstallmentReport
	 * Description: displays the KP settle Report data
	 *
	 * @return : list of KPsettleList
	 */
	def createMonthwisePendingInstallmentReport(){
		
		def monthwisePendingList
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getMonthWiseSettlementReport method of reportService to get the data of monthwisePendingList
				monthwisePendingList = reportService.getMonthWiseSettlementReport(params?.brCode,params?.schemeNo,params?.year,params?.month)
				if(params?.format1 == 'view') {
					[monthwisePendingList : monthwisePendingList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=MonthwisePendingListReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,monthwisePendingList, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createMonthwisePendingInstallmentReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: advanceBalanceReport
	 * Description: displays the Advance balance landing page
	 *
	 * @return : void
	 */
	def advanceBalanceReport(){
	
	}
	
	/**
	 * Method Name: createAdvanceBalanceReport
	 * Description: displays the Advance Balance report page
	 *
	 * @return : list of Advance Balance
	 */
	def createAdvanceBalanceReport(){
		def advanceBalanceList
		try{
			if(params?.format1 && params.format1 != "html"){
				advanceBalanceList= reportService.getAdvanceBalanceReport( params)
				if(params?.format1 == 'view') {
					[advanceBalanceList: advanceBalanceList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=advanceBalanceReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,advanceBalanceList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController  createAdvanceBalanceReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: kpPayModeWiseDetailReport
	 * Description: displays the Advance balance landing page
	 *
	 * @return : void
	 */
	def kpPayModeWiseDetailReport(){
	
	}
	
	/**
	 * Method Name: createAdvanceBalanceReport
	 * Description: displays the Advance Balance report page
	 *
	 * @return : list of Advance Balance
	 */
	def createkpPaymodeWiseDetailReport(){
		def kpPayModeWiseDetailList
		try{
			if(params?.format1 && params.format1 != "html"){
				kpPayModeWiseDetailList= reportService.getKpPayModeWiseDetailReport(params?.fromDate,params?.toDate,params?.schemeNo,params?.paymode)
				if(params?.format1 == 'view') {
					[kpPayModeWiseDetailList: kpPayModeWiseDetailList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=advanceBalanceReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,kpPayModeWiseDetailList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createKpPayModeWiseDetailReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: employeeIncentiveReport
	 * Description: displays the Advance balance landing page
	 *
	 * @return : void
	 */
	def employeeIncentiveReport(){
		
	}
	
	/**
	 * Method Name: createEmployeeIncentiveReport
	 * Description: displays the employee incentive report page
	 *
	 * @return : list of Advance Balance
	 */
	def createEmployeeIncentiveReport(){
		def employeeIncentiveList
		try{
			if(params?.format1 && params.format1 != "html"){
				employeeIncentiveList= reportService.getEmployeeIncentiveReport(params?.fromDate,params?.toDate,params?.branchNo,params?.type)
				if(params?.format1 == 'view') {
					[employeeIncentiveList: employeeIncentiveList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=EmployeeIncentiveReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,employeeIncentiveList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createEmployeeIncentiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: employeePerformanaceReport
	 * Description: displays the Employee Performanance landing page
	 *
	 * @return : void
	 */
	def employeePerformanceReport(){
		
	}
	
	/**
	 * Method Name: createEmployeePerformanceReport
	 * Description: displays the  Employee Performanance report page
	 *
	 * @return : list of Employee Performanance
	 */
	def createEmployeePerformanceReport(){
		def employeePerformanaceList
		try{
			if(params?.format1 && params.format1 != "html"){
				employeePerformanaceList= reportService.getEmployeePerformanaceReport(params?.fromDate,params?.toDate,params?.branchNo,params?.schemeName)
				if(params?.format1 == 'view') {
					[employeePerformanaceList: employeePerformanaceList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=EmployeePerformanaceList.${params.extension}")
					exportService.export(params.format1, response.outputStream,employeePerformanaceList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createEmployeeIncentiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: branchwisePaymentDetailReport
	 * Description: displays the Branch Wise PaymentDetail landing page
	 *
	 * @return : void
	 */
	def branchwisePaymentDetailReport(){
		def brNameResult,descList
		try{			
			//Calling getDescriptionDataForBrWisePayDtlsReport method of reportService to get the data of category
			descList = reportService.getDescriptionDataForBrWisePayDtlsReport()		
			//Calling getCompName method of reportService to get the data of branch
			brNameResult = reportService.getCompName()			
			[descList:descList,brNameResult:brNameResult]
		}catch(Exception exception){
			log.info("Exception in ReportsController branchwisePaymentDetailReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createBranchwisePaymentDetail
	 * Description: displays the BranchwisePaymentDetail report page
	 *
	 * @return : list of BranchwisePaymentDetail
	 */
	def createBranchwisePaymentDetail(){
		def branchwisePaymentDetailList
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getBranchwisePaymentDetailReport method of reportService to get the data of BranchwisePaymentDetailReport
				branchwisePaymentDetailList= reportService.getBranchwisePaymentDetailReport(params?.fromDate,params?.toDate,params?.schemeNo,params?.paymode)
				if(params?.format1 == 'view') {					
					[branchwisePaymentDetailList: branchwisePaymentDetailList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=branchwisePaymentDetailReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,branchwisePaymentDetailList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createBranchwisePaymentDetail method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: katanWadharaReport
	 * Description: displays the katanWadharaReport landing page
	 *
	 * @return : list of description
	 */
	def katanWadharaReport(){
		def descList,itemList,subList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			//Calling getItemForKatanWadhara method of reportService to get item list
			itemList = reportService.getItemForKatanWadhara(session?.brCode,session.companyCode)
			//Calling getSubCodeForKatanWadhara method of reportService to get subCode list
			subList = reportService.getSubCodeForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList,itemList:itemList,subList:subList]
		}catch(Exception exception){
			log.info("Exception in ReportsController branchwisePaymentDetailReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: getPurityList
	 * Description: used to get the purity list based on category
	 *
	 * @return :  purity's json data
	 */
	def getKatanPurityList() {
		def purityListResult
		try{
			//Calling getPurityForKatanWadhara method of reportService to get purity list
			purityListResult = reportService.getPurityForKatanWadhara(params?.desVal,session?.brCode,session.companyCode);
			jsonObj = new JSONObject()
			jsonObj.putAt("PURITY", purityListResult?.VC_PURITY)
			render jsonObj as JSON
		}catch(Exception exception){
			log.info("Exception in ReportsController getKatanPurityList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	/**
	 * Method Name: createKatanWadharaReport
	 * Description: displays the createKatanWadhara report page
	 *
	 * @return : list of katanWadhara
	 */
	def createKatanWadharaReport(){
		def katanWadharaList
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getKatanWadharaReport method of reportService to get the data of KatanWadharaReport
				katanWadharaList= reportService.getKatanWadharaReport(params,session)
				if(params?.format1 == 'view') {					
					[katanWadharaList:katanWadharaList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=katanWadharaReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,katanWadharaList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createKatanWadharaReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: salesReturnCheckListReport
	 * Description: displays the sales return check list report landing page
	 *
	 * @return : multiple lists
	 */
	def salesReturnCheckListReport() {
		def descList,branchResult,purity,subcodeList,itemList,customerList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			//Calling getCompName method of reportService to get the data of branch list
			branchResult = reportService.getCompName()
			//Calling getPurity method of reportService to get the data of purity list
			//item = reportService.getPurity(session.brCode)
			//Calling getItem method of reportService to get the data of Item list
			//item = reportService.getItem(session.brCode)
			//Calling getSubCode method of reportService to get the data of SubCode list
			//subcode = reportService.getSubCode(params)
			//Calling getDeptt method of reportService to get the data of Deptt list
			customerList = reportService.getCustomer(session.brCode,session.companyCode)
		}catch(Exception exception){
			log.info("Exception in ReportsController salesReturnCheckListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[descList:descList,branchResult:branchResult,purity:purity,subcodeList:subcodeList,itemList:itemList,customerList:customerList]
	}
	
	/**
	 * Method Name: createSalesRetChkListReport
	 * Description: displays the SalesRetChkListReport page
	 *
	 * @return : list of salesRetChk
	 */
	def createSalesRetChkListReport(){
		def salesRetChkList,stoneData
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getSalesRetChkLstReport method of reportService to get the data of SalesRetChkLstReport
				salesRetChkList = reportService.getSalesRetChkLstReport(params,session)
				//Calling getStoneDataForSalesRetChkLst method of reportService to get the data of SalesRetChkLstReport's stone data
				stoneData = reportService.getStoneDataForSalesRetChkLst(params,session)
				if(params?.format1 == 'view') {					
					[salesRetChkList:salesRetChkList,stoneData:stoneData]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=salesReturnCheckListReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,salesRetChkList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createSalesRetChkListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: consolidateSalesReport
	 * Description: displays the consolidateSalesReport landing page
	 *
	 * @return : list
	 */
	def consolidateSalesReport(){
		def descList,branchResult,purity,subcodeList,itemList,customerList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			//Calling getCompName method of reportService to get the data of branch list
			branchResult = reportService.getCompName()
			//Calling getPurity method of reportService to get the data of purity list
			//item = reportService.getPurity(session.brCode)
			//Calling getItem method of reportService to get the data of Item list
			//item = reportService.getItem(session.brCode)
			//Calling getCustomer method of reportService to get the data of Deptt list
			customerList = reportService.getCustomer(session.brCode,session.companyCode)
		}catch(Exception exception){
			log.info("Exception in ReportsController salesReturnCheckListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		[descList:descList,branchResult:branchResult,purity:purity,subcodeList:subcodeList,itemList:itemList,customerList:customerList]
	}
	/**
	 * Method Name: createConsolidateSalesReport
	 * Description: displays the ConsolidateSalesReport page
	 *
	 * @return : list of salesRetChk
	 */
	def createConsolidateSalesReport(){
		def saleType,consolidateSalesCat,consolidateSales,item
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				saleType = params.saleType
				mainHead.put("fromDate", params.fromDate);
				mainHead.put("toDate", params.toDate);
				if(params.item == null){
					item = 'ALL'
				}
				mainHead.put("item",item);
				if(saleType=='SA'){
					//Calling getConsolidateSaleReport method of reportService to get the data of Sale Type Report
					consolidateSales = reportService.getConsolidateSaleReport(params,session)
				}else{
					//Calling getSalesRetChkLstReport method of reportService to get the data of Sale Return Type Report
					consolidateSales = reportService.getConsolidateSaleReturnReport(params,session)
				}
				if(params?.format1 == 'view') {													
					[mainHead: mainHead, consolidateSales: consolidateSales]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=consolidateSalesReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,consolidateSales , [:], [:])
				}
			}			
		}catch(Exception exception){
			log.info("Exception in ReportsController createConsolidateSalesReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: kpSaleCountReport
	 * Description: displays the kpSaleCountReport landing page
	 *
	 * @return : void
	 */
	def kpSaleCountReport(){
		def brNameResult,descList
		try{
			//Calling getCompName method of reportService to get the data of branch
			brNameResult = reportService.getCompName()
			[brNameResult:brNameResult]
		}catch(Exception exception){
			log.info("Exception in ReportsController kpSaleCountReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createKpSaleCount
	 * Description: displays the createKpSaleCount report page
	 *
	 * @return : list of kpSaleCountList
	 */
	def createKpSaleCount(){
		def kpSaleCountList
		try{
			if(params?.format1 && params.format1 != "html"){
				//Calling getKpSaleCountReport method of reportService to get the data of KpSaleCountReport
				kpSaleCountList= reportService.getKpSaleCountReport(params)
				if(params?.format1 == 'view') {					
					[kpSaleCountList: kpSaleCountList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=kpSaleCountReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,kpSaleCountList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createKpSaleCount method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: ogSaleCheckListReport
	 * Description: displays the ogSaleCheckListReport landing page
	 *
	 * @return : void
	 */
	def ogSaleCheckListReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController ogSaleCheckListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createOgSaleCheckListReport
	 * Description: displays the createOgSaleCheckListReport page
	 *
	 * @return : list of ogSaleCheckMap
	 */
	def createOgSaleCheckListReport(){
		def str,co,coName,ogSaleAddress,ogSaleCheckMap,category
		def mainHead = [:]
		try{						
			if(params?.format1 && params.format1 != "html"){
					str = MstCompany.findAllByVcCompCode(session.companyCode)
					co = str?.vcCompanyName
					coName = co[0]
					//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
					ogSaleAddress = reportService.getOgSaleCheckListReportAddress(session)
					mainHead.put("fromDate",params.fromDate);
					mainHead.put("toDate",params.toDate);
					category = params.category
					mainHead.put("cat",category);
					mainHead.put("coName",coName);
					Date today = new Date();
					mainHead.put("today",today);
					//Calling getOgSaleCheckListReport method of reportService to get the data of getOgSaleCheckListReport
					ogSaleCheckMap = reportService.getOgSaleCheckListReport(params)
				if(params?.format1 == 'view') {														
					[ogSaleAddress:ogSaleAddress,mainHead:mainHead,ogSaleCheckMap:ogSaleCheckMap]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=SalesChecklistReport.${params.extension}")
					exportService.export(params.format, response.outputStream,ogSaleCheckMap, [:], [:])
						
				}
			}
		}catch(Exception exception){
 			log.info("Exception in ReportsController createOgSaleCheckListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: labelTransferChecklistReport
	 * Description: displays the labelTransferChecklistReport landing page
	 *
	 * @return : void
	 */
	def labelTransferChecklistReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController labelTransferChecklistReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createLabelTransferChecklistReport
	 * Description: displays the createLabelTransferChecklistReport page
	 *
	 * @return : list of BranchwisePaymentDetail
	 */
	def createLabelTransferChecklistReport(){
		def str,co,coName,lblTrChkAddress,labelTrChLst,category
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				str = MstCompany.findAllByVcCompCode(session.companyCode)
				co = str?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				lblTrChkAddress = reportService.getOgSaleCheckListReportAddress(session)
				mainHead.put("fromDate",params.fromDate);
				mainHead.put("toDate",params.toDate);
				category = params.category
				mainHead.put("cat",category);
				mainHead.put("coName",coName);
				Date today = new Date();
				mainHead.put("today",today);
				//Calling getLabelTransferChecklistReport method of reportService to get the data of LabelTransferChecklistReport
				labelTrChLst = reportService.getLabelTransferChecklistReport(params)
				if(params?.format1 == 'view') {
					[lblTrChkAddress:lblTrChkAddress,mainHead:mainHead,labelTrChLst:labelTrChLst]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=labelTransferChecklistReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,labelTrChLst , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createLabelTransferChecklistReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: ecsStatusReport
	 * Description: displays the ECS staus landing page
	 *
	 * @return : void
	 */
	def ecsStatusReport(){
		def customerList = ecsDateEntryUtilsService.getCustomerList()
		[customerList : customerList]
	}
	
	/**
	 * Method Name: createEcsStatusReportReport
	 * Description: displays the  ECS status report page
	 *
	 * @return : list of ECS status
	 */
	def createEcsStatusReport(){
		def ecsStatusList
		try{
			if(params?.format1 && params.format1 != "html"){
				ecsStatusList= reportService.getEcsStautsReport(params?.fromDate,params?.branchNo,params?.schemeName,params?.custId)
				if(params?.format1 == 'view') {
					[ecsStatusList: ecsStatusList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=EcsStatusListReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,ecsStatusList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createEcsStatusReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: userWiseSalesReport
	 * Description: displays the Userwise Sales landing page
	 *
	 * @return : void
	 */
	def userWiseSalesReport(){
		
	}
	
	/**
	 * Method Name: createUserWiseSalesReport
	 * Description: displays the  Userwise sales report page
	 *
	 * @return : list of ECS status
	 */
	def createUserWiseSalesReport(){
		def userWiseSalesList
		try{
			if(params?.format1 && params.format1 != "html"){
				userWiseSalesList= reportService.getUserwiseSalesReport(params?.fromDate,params?.toDate,params?.branchNo,params?.categoryName,params?.voucherNo)
				if(params?.format1 == 'view') {
					[userWiseSalesList: userWiseSalesList]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=userWiseSalesListReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,userWiseSalesList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createUserWiseSalesReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: purchaseReturnCheckListReport
	 * Description: displays the purchase Return checklist landing page
	 *
	 * @return : void
	 */
	def purchaseReturnCheckListReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createPurchaseReturnCheckListReport
	 * Description: displays the purchase Return checklist report page
	 *
	 * @return : list of ECS status
	 */
	def createPurchaseReturnCheckListReport(){
		def purchaseReturnCheckList
		def stonePurchaseReturnCheckList
		def stoneDetailsCheckList
	
		try{
			if(params?.format1 && params.format1 != "html"){
				purchaseReturnCheckList= reportService.getPurchaseReturnChecklistReportSachin(params?.fromDate,params?.toDate,params?.categoryName)
				stoneDetailsCheckList= reportService.steonDetailslistReport(params?.fromDate,params?.toDate,params?.categoryName)
				stonePurchaseReturnCheckList= reportService.getStonePurchaseReturnChecklistReport(params?.fromDate,params?.toDate,params?.categoryName)
				
				if(params?.format1 == 'view') {
					[purchaseReturnCheckList: purchaseReturnCheckList , stonePurchaseReturnCheckList : stonePurchaseReturnCheckList, stoneDetailsCheckList : stoneDetailsCheckList]
					
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=purchaseReturnCheckListReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,purchaseReturnCheckList , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createpurchaseReturnChecklistReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: salesCheckListTargetReport
	 * Description: displays the salesCheckListTargetReport landing page
	 *
	 * @return : void
	 */
	def salesCheckListTargetReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createSalesChecklistWithTargetReport
	 * Description: displays the createSalesChecklistWithTargetReport page
	 *
	 * @return : list of BranchwisePaymentDetail
	 */
	def createSalesChecklistWithTargetReport(){
		def str,co,coName,salesChecklistTarget,labelTrChLst,category
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				str = MstCompany.findAllByVcCompCode(session.companyCode)
				co = str?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				salesChecklistTarget = reportService.getSalesChecklistWithTarget(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.purity,params?.department)
				mainHead.put("fromDate",params.fromDate);
				mainHead.put("toDate",params.toDate);
				category = params.category
				mainHead.put("cat",category);
				mainHead.put("coName",coName);
				Date today = new Date();
				mainHead.put("today",today);
				//Calling getLabelTransferChecklistReport method of reportService to get the data of LabelTransferChecklistReport
				labelTrChLst = reportService.getLabelTransferChecklistReport(params)
				if(params?.format1 == 'view') {
					[salesChecklistTarget:salesChecklistTarget,mainHead:mainHead,labelTrChLst:labelTrChLst]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=SalesChecklistWithTargetReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,salesChecklistTarget , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createSalesChecklistWithTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: salesCheckListTargetReport
	 * Description: displays the salesCheckListTargetReport landing page
	 *
	 * @return : void
	 */
	def gs11STDReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}

	/**
	 * Method Name: gs11OgReport
	 * Description: displays the gs11Og report landing page
	 *
	 * @return : void
	 */
	def gs11OgReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createGs11OgReport
	 * Description: displays the Gs11Og Report page
	 *
	 * @return : list of Gs11Og
	 */
	def createGs11OgReport(){
		def str,co,coName,gs11Og,totalOG,category
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				str = MstCompany.findAllByVcCompCode(session.companyCode)
				co = str?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				gs11Og = reportService.getGS11OGreport(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.purity,params?.department)
				totalOG = reportService.totalGS11OGreport(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.purity,params?.department)
				
				mainHead.put("fromDate",params.fromDate)
				mainHead.put("toDate",params.toDate)
				category = params.category
				mainHead.put("cat",category)
				mainHead.put("coName",coName)
				Date today = new Date()
				mainHead.put("today",today)

				if(params?.format1 == 'view') {
					[gs11Og:gs11Og,mainHead:mainHead,totalOG:totalOG]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=TotalOG Report.${params.extension}")
					exportService.export(params.format1, response.outputStream,gs11Og , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createGs11OgReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createSalesChecklistWithTargetReport
	 * Description: displays the createSalesChecklistWithTargetReport page
	 *
	 * @return : list of Gs11 STD
	 */
	def createGs11STDReport(){
		def str,co,coName,gs11STD,totalOG,category
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				str = MstCompany.findAllByVcCompCode(session.companyCode)
				co = str?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				gs11STD = reportService.getGS11STDreport(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.purity,params?.department)
				totalOG = reportService.totalGS11STDreport(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.purity,params?.department)
				
				mainHead.put("fromDate",params.fromDate);
				mainHead.put("toDate",params.toDate);
				category = params.category
				mainHead.put("cat",category);
				mainHead.put("coName",coName);
				Date today = new Date();
				mainHead.put("today",today);

				if(params?.format1 == 'view') {
					[gs11STD:gs11STD,mainHead:mainHead,totalOG:totalOG]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=SalesChecklistWithTargetReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,gs11STD , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createSalesChecklistWithTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: gs12Report
	 * Description: displays the gs12 report landing page
	 *
	 * @return : void
	 */
	def gs12Report(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createGs12Report
	 * Description: displays the Gs12 Report page
	 *
	 * @return : list of BranchwisePaymentDetail
	 */
	def createGs12Report(){
		def str,co,coName,gs12,totalOG,category
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				str = MstCompany.findAllByVcCompCode(session.companyCode)
				co = str?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				gs12 = reportService.getGs12report(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.purity,params?.department)
				totalOG = reportService.totalGs12report(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.purity,params?.department)
				
				mainHead.put("fromDate",params.fromDate);
				mainHead.put("toDate",params.toDate);
				category = params.category
				mainHead.put("cat",category);
				mainHead.put("coName",coName);
				Date today = new Date();
				mainHead.put("today",today);
				
				if(params?.format1 == 'view') {
					[gs12:gs12,mainHead:mainHead,totalOG:totalOG]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params?.format1]
					response.setHeader("Content-disposition", "attachment; filename=gs12.${params?.extension}")
					exportService.export(params?.format1, response.outputStream,gs12, [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createSalesChecklistWithTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: nangmelReport
	 * Description: displays the nangmel report landing page
	 *
	 * @return : void
	 */
	def nangmelReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createNangmelReport
	 * Description: displays the Nangmel Report page
	 *
	 * @return : list of BranchwisePaymentDetail
	 */
	def createNangmelReport(){
		def str,co,coName,nangmel,totalOG,category
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				str = MstCompany.findAllByVcCompCode(session.companyCode)
				co = str?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				nangmel = reportService.getNangmelreport(params?.nangmelDate,params?.category,session?.companyCode,session?.brCode)
				totalOG = reportService.totalNangmelreport(params?.nangmelDate,params?.category,session?.companyCode,session?.brCode)
				
				mainHead.put("nangmelDate",params?.nangmelDate)
				category = params?.category
				mainHead.put("cat",category)
				mainHead.put("coName",coName)
				Date today = new Date()
				mainHead.put("today",today)

				if(params?.format1 == 'view') {
					[nangmel:nangmel,mainHead:mainHead,totalOG:totalOG]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=NangmelReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,nangmel , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createSalesChecklistWithTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	
	/**
	 * Method Name: salesStockPercentReport
	 * Description: displays the sales Stock Percent report landing page
	 *
	 * @return : void
	 */
	def salesStockPercentReport(){
		def descList
		try{
			//Calling getCategoryForKatanWadhara method of reportService to get the data of category list
			descList = reportService.getCategoryForKatanWadhara(session?.brCode,session.companyCode)
			[descList:descList]
		}catch(Exception exception){
			log.info("Exception in ReportsController salesCheckListTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
	/**
	 * Method Name: createSalesStockPercentReport
	 * Description: displays the create Sales Stock Percent Report page
	 *
	 * @return : list of SalesStockPercent
	 */
	def createSalesStockPercentReport(){
		def str,co,coName,salesStockPercent,totalOG,category
		def mainHead = [:]
		try{
			if(params?.format1 && params.format1 != "html"){
				str = MstCompany.findAllByVcCompCode(session.companyCode)
				co = str?.vcCompanyName
				coName = co[0]
				//Calling getOgSaleCheckListReportAddress method of reportService to get the data of address
				salesStockPercent = reportService.getSalesStockPercentreport(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.location)
				totalOG = reportService.totalSalesStockPercentreport(params?.fromDate,params?.toDate,params?.category,session?.companyCode,session.brCode,params?.location)
				
				mainHead.put("fromDate",params.fromDate)
				mainHead.put("toDate",params.toDate)
				category = params.category
				mainHead.put("cat",category)
				mainHead.put("coName",coName)
				Date today = new Date()
				mainHead.put("today",today)

				if(params?.format1 == 'view') {
					[salesStockPercent:salesStockPercent,mainHead:mainHead,totalOG:totalOG]
				} else {
					response.contentType = grailsApplication.config.grails.mime.types[params.format1]
					response.setHeader("Content-disposition", "attachment; filename=SalesChecklistWithTargetReport.${params.extension}")
					exportService.export(params.format1, response.outputStream,salesStockPercent , [:], [:])
				}
			}
		}catch(Exception exception){
			log.info("Exception in ReportsController createSalesChecklistWithTargetReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
	}
	
}