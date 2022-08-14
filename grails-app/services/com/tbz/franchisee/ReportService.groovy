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

import java.text.DateFormat
import java.text.SimpleDateFormat

import com.tbz.franchisee.BrMstTab
import com.tbz.franchisee.DtCashPayment
import com.tbz.franchisee.SchemeMst

/**
 * MstRateParameterService
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
 *  11/02/2016	  Izaz/Abhijit      Created File
 *
 */

@Transactional
class ReportService {
	def dataSource
	Sql sql
	Sql sql1
	DateFormat formatter
	Calendar cal
	Calendar cal1
	String dateStr1
	String dateStr2
	String formatedDate1
	String formatedDate2
	Date date1
	Date date2
	def br
	def finalBr
	def query
	def query1
	def str1
	def resultArr  = []
	def result
	
	def resultCat
	def accRefAdvStlCustId
	def serviceMethod() {
	}
	
	def ReportService() {
		formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
		cal = Calendar.getInstance()
		cal1 = Calendar.getInstance()
	}	
		
	/**
	 * Method Name: generateRateDtReport
	 * Description: gets the rate details report list
	 *
	 * @return : dtRateDetailResult list
	 */
	def generateRateDtReport(fromDate, toDate, brName) {
		def dtRateDetailResult
		try{
			
			str1 = BrMstTab.findAllByBrName(brName)
			br = str1?.brCode
			finalBr = br[0] 
			query =
			"""
					SELECT 
						br.BR_NAME,
						dtrtdt.DT_MOD_DATE,
						dtrtdt.VC_PURITY,
						dtrtdt.NU_RATE 
					FROM 
						BR_MST_TAB br
						INNER JOIN DT_RATE_DETAIL dtrtdt 
					ON 
						dtrtdt.BR_CODE = br.BR_CODE 
					WHERE 
						dtrtdt.DT_MOD_DATE BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') 
					AND 
						TO_DATE('$toDate','MM/DD/YYYY') 
					AND 
						dtrtdt.BR_CODE = '$finalBr' 
				"""
			sql = new Sql(dataSource)
			dtRateDetailResult = sql.rows(query)				
		}catch(Exception exception){
			log.info("Exception in ReportService generateRateDtReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return dtRateDetailResult
	}
	
	/**
	 * Method Name: generateKpCountReport
	 * Description: gets the kp count report list
	 *
	 * @return : custMstResult list
	 */
	def generateKpCountReport(fromDate, toDate,schemeNo,brName, companyCode, brCode) {
		def custMstResult
		try{
			str1 = BrMstTab.findAllByBrName(brName)
			br = str1?.brCode
			finalBr = br[0]
			query =
				"""
					SELECT c.cust_id, c.fname||' '||c.mname||' '||c.lname AS CUST_NAME
					FROM reg_dt a,br_mst_tab b, cust_mst c
					WHERE a.cust_id = c.cust_id
			        AND a.SCHEME_NO = DECODE('$schemeNo','ALL',a.SCHEME_NO,'$schemeNo')
			        AND b.br_code =DECODE('$finalBr','ALL',b.br_code,'$finalBr')
					AND(b.br_code=SUBSTR(a.cust_id,1,3) OR b.br_code=SUBSTR(a.cust_id,1,2) OR b.br_code=SUBSTR(a.cust_id,1,4))
					AND b.br_code=c.br_code
					AND a.ch_cancel_flag = 'N'
					AND a.scheme_no IS NOT NULL
					AND a.reg_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
					GROUP BY c.cust_id, c.fname||' '||c.mname||' '||c.lname
					order by c.cust_id
				"""
			sql = new Sql(dataSource)			
			custMstResult = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService generateKpCountReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return custMstResult
	}
	
	/**
	 * Method Name: generateKpCountVal
	 * Description: gets the rate details report list
	 *
	 * @return : result list
	 */
	def generateKpCountVal(fromDate, toDate,schemeNo,brName, companyCode, brCode) {
		try{
			
			str1 = BrMstTab.findAllByBrName(brName)
			br = str1?.brCode
			finalBr = br[0]
			
			query =
				"""
					SELECT COUNT(*) as CNT FROM         
					(
						SELECT 
							cm.CUST_ID ,cm.FNAME||cm.mname||cm.lname CUST_NAME
						FROM 
							cust_mst cm,reg_dt rd 
						WHERE
							cm.cust_id=rd.cust_id
						AND
							rd.scheme_no=$schemeNo
						AND
							cm.br_code=$finalBr
						AND
							rd.reg_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
					) A
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService generateKpCountVal method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSchemeName
	 * Description: gets the scheme name
	 *
	 * @return : list of result 
	 */
	def getSchemeName(schemeMstInstance) {
		def x = schemeMstInstance
		def result
		def sName
		try{
			query =
				"""
					select scheme_name from SCHEME_MST where SCHEME_NO=$x.schemeNo
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)			
			result.each{
				sName = it.values()[0]
			}
		}catch(Exception exception){
			log.info("Exception in ReportService getSchemeName method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return sName
	}
	
	/**
	 * Method Name: generateAdvTransferReport
	 * Description: gets the AdvTransfer report list
	 *
	 * @return : list of dtCrossAdvSettleResult
	 */
	def generateAdvTransferReport(fromDate, toDate, brName, trType) {
		def dt_cross_adv_settle,finalTBr,dtCrossAdvSettleResult
		try{
			finalBr = brName
			if(trType == 'KP TRANSFER'){
				trType = 'KP'
			}else if(trType == 'ADVANCE TRANSFER'){
				trType = 'AD'
			}			
			query1 = "SELECT vc_transfered_br from dt_cross_adv_settle WHERE BR_CODE='$finalBr' AND dt_creation_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') AND vc_type = '$trType'"			
			sql1 = new Sql(dataSource)
			dt_cross_adv_settle = sql1.rows(query1)
			
			dt_cross_adv_settle.each{
				finalTBr= (it.values()[0])
				if(finalTBr){
					query =
					"""
					select a.vc_adv_ord_no, a.dt_adv_ord_date, a.vc_cust_fname||' '||a.vc_cust_mname||' '||a.vc_cust_lname customer_name, 
						a.nu_tot_amt, a.nu_bal_amt,
						decode(ch_settle_flag,'Y','Not Received','S','Received') status, 
						b.br_name transferred_from_branch, c.br_name transferred_to_branch
						from dt_cross_adv_settle a, NEWWEBTBZ.br_mst_tab b, NEWWEBTBZ.br_mst_tab c 
						where a.br_code = b.br_code
						and a.vc_transfered_br = $finalTBr 
						and a.dt_creation_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and a.br_code = '$finalBr'
						and a.vc_transfered_br = '$finalTBr'
						and vc_type = '$trType'
					"""
					sql = new Sql(dataSource)
					dtCrossAdvSettleResult = sql.rows(query)
				}
			}			
		}catch(Exception exception){
			log.info("Exception in ReportService generateAdvTransferReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return dtCrossAdvSettleResult
	}
	
	/**
	 * Method Name: generateChkClrnsReport
	 * Description: gets the check clearance report list
	 *
	 * @return : list of chkClrnsVal
	 */
	def generateChkClrnsReport(fromDate, toDate, companyCode, brCode) {
		def chkClrnsVal
		try{
			query =
				"""
					SELECT VC_CUST_FNAME||' '||VC_CUST_MNAME||' '||VC_CUST_LNAME CUSTOMER_NAME,VC_VOUCHER_NO,DT_VOUCHER_DATE,VC_CHQ_NO,DT_CHQ_DATE,NU_AMOUNT,
					VC_TRAN_TYPE,DT_CLEAR_DATE,VC_CUST_ID 
					FROM dt_cheque_clear
					WHERE CH_AUTH_FLAG ='Y' 
					AND DT_CLEAR_DATE between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
					and br_code='$brCode' and vc_comp_code='$companyCode'
			"""
			sql = new Sql(dataSource)
			chkClrnsVal = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService generateChkClrnsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return chkClrnsVal
	}
	
	/**
	 * Method Name: getCompName
	 * Description: gets the company name list
	 *
	 * @return : list of company name
	 */
	def getCompName(){
		def branchList = [:]
		try{
			query =
				"""
					SELECT BR_CODE,BR_NAME FROM BR_MST_TAB WHERE NVL(CH_ACTIVE,'N') ='Y'
					UNION
					SELECT 'ALL','ALL' FROM DUAL order by 2
				"""			
			sql = new Sql(dataSource)
			result = sql.rows(query)
			result.each {
				branchList.put(it.values()[0],it.values()[1])
			}			
		}catch(Exception exception){
			log.info("Exception in ReportService getCompName method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return branchList
	}
	
	/**
	 * Method Name: generatePanCardReport
	 * Description: gets the PanCardReport list
	 *
	 * @return : list of panCardResultVal
	 */
	def generatePanCardReport(fromDate, toDate, brName, type) {
		def panCardResultVal
		try{
			query =
			"""
					select br.BR_NAME,h.vc_cust_add "PAN_NO",
						decode(h.vc_field6,'PN','PANCARD','PS','PASSPORT','OT','OTHERS',h.vc_field6)ID_type,
						h.vc_voucher_no,h.dt_voucher_date,d.vc_category, h.vc_cust_fname||' '||h.vc_cust_mname||' ' || h.vc_cust_lname Name, 
						sum(d.nu_amount) amount
					from hd_cash h ,dt_cash_payment d,br_mst_tab br
						where  h.vc_comp_code = d.vc_comp_code
						and h.br_Code = d.br_code
						and br.br_Code = d.br_code
						and h.br_Code = br.br_code
						and h.vc_voucher_no = d.vc_voucher_no
						and h.vc_category = d.vc_category
						and h.dt_voucher_date = d.dt_voucher_date
						and h.ch_cancel_flag = 'N'
						and h.br_code = decode('$brName', 'ALL', h.br_code, '$brName')
						and h.dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
					group by h.vc_cust_add,h.vc_voucher_no,h.dt_voucher_date,h.vc_category,h.vc_cust_fname,h.vc_cust_mname,h.vc_cust_lname,d.vc_category,br.BR_NAME,br.br_code,h.VC_ACT_TYPE,h.vc_field6
					having sum(d.nu_amount) >200000
					order by 1,4
			"""			
			sql = new Sql(dataSource)		
			panCardResultVal = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService generatePanCardReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return panCardResultVal
	}
	
	/**
	 * Method Name: createMisReport
	 * Description: gets the mis report list
	 *
	 * @return : list of misReportVal
	 */
	def createMisReport(fromDate,toDate,category,companyCode,brCode) {
		def finalCat
		def vc_comp_code
		def finalCompCode =''
		def misReportVal
		try{		
			query =
				"""
					select vc_voucher_no,dt_voucher_date,nu_account_code,b.vc_pay_type Pay_type,nu_amount,vc_narration,a.br_code,vc_category,
					vc_category||vc_sale_voucher_no voucher_no,dt_sale_voucher_date
					from check_bounce a, mst_pay b
					where vc_comp_code ='$companyCode'
					and a.br_code = b.br_code
					and a.ch_pay_type = b.vc_pay_mode
					and b.ch_tran_type = 'MR'
					and a.br_code = '$brCode'
					and dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
					and nvl(vc_category, 'X') = decode('$category','ALL',nvl(vc_category,'X'),'$category')
					order by dt_voucher_date
				"""
			sql = new Sql(dataSource)			
			misReportVal = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService createMisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return misReportVal
	}
	
	/**
	 * Method Name: getBillCountData
	 * Description: gets the bill count list
	 *
	 * @return : list of uwbcResult
	 */
	def getBillCountData(fromDate, toDate, brName){
		def brcode,brname,uwbcResult,uwbCountResult
		try{			
			str1 = BrMstTab.findAllByBrName(brName)
			brcode = str1?.brCode
			brname = str1?.brName
			finalBr = brcode[0]		
			
			query =
			"""
			SELECT
						b.br_name, c.vc_user_name, h.vc_category, (SELECT COUNT(*) FROM         
					(SELECT 
						h.br_code, c.vc_user_name, h.vc_category
					FROM 
						HD_CASH h, MK_USERS c
					WHERE 
						h.br_code = '$finalBr' 
					AND 
						h.vc_creation_by = c.ch_user_code
					AND
						h.ch_cancel_flag = 'N'
					AND 
						h.dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
					GROUP BY h.br_code, c.vc_user_name, h.vc_category)) as cnt
					FROM 
						BR_MST_TAB b, HD_CASH h, MK_USERS c
					WHERE 
						h.br_code = b.br_code
					AND
						h.br_code = '$finalBr' 
					AND 
						h.vc_creation_by = c.ch_user_code
					AND
						h.ch_cancel_flag = 'N'
					AND 
						h.dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
					GROUP BY b.br_name, h.br_code, c.vc_user_name, h.vc_category
			"""
			sql = new Sql(dataSource)
			uwbcResult = sql.rows(query)			
		}catch(Exception exception){
			log.info("Exception in ReportService getBillCountData method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return uwbcResult
	}
	
	/**
	 * Method Name: getPaymentModeForCashBackReport
	 * Description: gets the payment mode report list
	 *
	 * @return : list 
	 */
	def getPaymentModeForCashBackReport() {
		def retList = []
		def str1,value,cb,b
		try{						
			query =
			"""
				SELECT DISTINCT CH_PAY_MODE FROM DT_CASH_PAYMENT where CH_PAY_MODE IN('CB','B')
			"""					
			sql = new Sql(dataSource)
			result = sql.rows(query)		
			result.each {
				value = (it.values()[0])		
				cb = 'CASH BACK'
				b = 'BALANCE'
				if(value!=null||value!=''){
					if(value=='CB'){
						value=cb
					}else if(value=='B'){
						value=b
					}
				}	
				retList.add(value)
			}
		}catch(Exception exception){
			log.info("Exception in ReportService getPaymentModeForCashBackReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return retList.asList();
	}
	
	/**
	 * Method Name: getReport
	 * Description: gets the cash back report list
	 *
	 * @return : list of cbResult
	 */
	def getReport(fromDate, toDate, chPayMode, brCode) {
		def selectMode,cb,b,cbResult
		try{
			selectMode = chPayMode
			cb = 'CB'
			b = 'B'
			if(selectMode!=''){
				if(selectMode=='CASH BACK'){
					selectMode=cb
				}else if(selectMode=='BALANCE'){
					selectMode=b
				}				
			}			
			query =
			"""
				SELECT
				      a.VC_VOUCHER_NO, a.DT_VOUCHER_DATE, a.VC_CATEGORY, a.VC_CUST_FNAME||' '||a.VC_CUST_LNAME 
				      CUSTOMER_NAME, a.VC_ADDRESS1||' '||a.VC_ADDRESS2||' '||a.VC_ADDRESS3 ADDRESS,
				       decode(b.CH_PAY_MODE,'B','BALANCE','CB','CASH BACK')PAYMODE, ABS(b.NU_AMOUNT) AMOUNT     
				      
				      FROM HD_CASH a, DT_CASH_PAYMENT b
				      
				         WHERE a.BR_CODE = b.BR_CODE
				      AND        
				        a.VC_VOUCHER_NO = b.VC_VOUCHER_NO
				      AND
				        a.DT_VOUCHER_DATE = b.DT_VOUCHER_DATE
				      AND
				        a.VC_CATEGORY = b.VC_CATEGORY
				      AND 
				        ch_pay_mode ='$selectMode'
				      AND
				        a.CH_CanCEL_FLAG='N'
				      AND
				        a.BR_CODE = '$brCode'
				      AND 
				        a.DT_VOUCHER_DATE BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')			
				
			"""
			sql = new Sql(dataSource)
			cbResult = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return cbResult
	}
	
	/**
	 * Method Name: getCCReport
	 * Description: gets the credit card report list
	 *
	 * @return : list of credit card
	 */
	def getCCReport(fromDate, toDate,companyCode, brCode) {
		def ccResult
		try{
			query =
			"""
					SELECT a.VC_VOUCHER_NO VC_VOUCHER_NO,(a.vc_category||'-'||substr(a.vc_voucher_no,5,10)) vouch,--02SI00000001
						a.DT_VOUCHER_DATE DT_VOUCHER_DATE, a.vc_approval_no vc_approval_no,
						a.VC_CATEGORY  VC_CATEGORY, a.CH_PAY_MODE CH_PAY_MODE,
						to_char(a.NU_BANK_CODE) nu_bank_code, to_char(a.VC_CC_NO) VC_CC_NO, to_char(a.nu_bank_code) nu_bank_code1,
						a.DT_BILL_DATE DT_BILL_DATE, a.NU_SETT_AMT NU_SETT_AMT,
						a.VC_REMARKS VC_REMARKS, a.VC_TBZ_BANK VC_CARD_TYPE, 
						substr(a.vc_voucher_no,instr(a.vc_voucher_no,'SI',1,1),2) vouch_type
						FROM DT_CASH_CARD a, hd_Cash b
						WHERE a.vc_comp_code = b.vc_comp_code
						and a.vc_voucher_no = b.vc_voucher_no
						and a.dt_voucher_date = b.dt_voucher_date
						and a.br_Code = b.br_code
						and a.vc_Category = b.vc_category  
						and a.VC_COMP_CODE='$companyCode' 
						and a.DT_VOUCHER_DATE BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and a.ch_pay_mode = 'CC'
						and nvl(b.ch_cancel_flag,'N') = 'N' 
						and a.vc_voucher_no like '%SS%'
						--and substr(a.vc_voucher_no,3,2) in  ('SI','VS')
						--and substr(a.vc_voucher_no, instr(a.vc_voucher_no, a.br_code)+length(a.br_code),2) in ('SI','VS')
						and a.br_code='$brCode'
						--and a.vc_Category = 'A'
					union all						
					select a.vc_adv_ord_no, (a.vc_category || '-' || a.vc_adv_ord_no) vouch, a.dt_adv_ord_date, b.vc_approval_no,
						a.vc_category, b.ch_pay_mode, to_char(b.nu_bank_code)nu_bank_code, to_char(B.vc_cc_no)vc_cc_no, to_char(b.nu_bank_code)nu_bank_code,to_date('01-jan-01') dt_bill_date,
						 b.nu_amount,null ,TBZ_BANK VC_CARD_TYPE, 'AD' vouch_type
						from hd_sale_adv_ord a, DT_SALE_ADV_PAY b
						where a.vc_adv_ord_no = b.vc_adv_ord_no 
						and a.dt_adv_ord_date = b.dt_adv_ord_date 
						and a.vc_comp_code = b.vc_comp_code 
						and a.vc_comp_code = '$companyCode'
						and a.dt_adv_ord_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
						and b.ch_pay_mode = 'CC'
						and a.br_code=b.br_code
						and a.br_code= '88'
						and nvl(a.ch_cancel_flag,'N') = 'N'
					union all
					SELECT a.VC_VOUCHER_NO,(a.vc_category||'-'||substr(a.vc_voucher_no,5,10)) vouch, a.DT_VOUCHER_DATE, a.vc_approval_no, 
						a.VC_CATEGORY, a.CH_PAY_MODE, to_char(a.NU_BANK_CODE) nu_bank_code, to_char(a.VC_CC_NO) vc_cc_no, to_char(a.nu_bank_code) nu_bank_code1,
						a.DT_BILL_DATE, Nvl((a.NU_SETT_AMT*-1),0) nu_sett_amt,
						a.VC_REMARKS, a.VC_TBZ_BANK VC_CARD_TYPE, substr(a.vc_voucher_no, instr(a.vc_voucher_no, a.br_code)+length(a.br_code),2) vouch_type
						FROM DT_CASH_CARD a, hd_cash b
						WHERE a.vc_comp_code = b.vc_comp_code
						and a.vc_voucher_no = b.vc_voucher_no
						and a.dt_voucher_date = b.dt_voucher_date
						and a.br_Code = b.br_code
						and a.vc_Category = b.vc_category
						and a.VC_COMP_CODE= '$companyCode'
						and a.DT_VOUCHER_DATE BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and a.ch_pay_mode='CC' 
						--and substr (a.vc_voucher_no,3,2) IN ( 'SS','VR')
						and substr(a.vc_voucher_no, instr(a.vc_voucher_no, a.br_code)+length(a.br_code),2) in ( 'SS','VR')
						and a.br_code= '88'
						and nvl(b.ch_cancel_flag,'N') = 'N' 
					union all
					Select vc_voucher_no,(vc_category_code||'-'||vc_voucher_no) vouch,dt_voucher_date,vc_approval_no,
						vc_category_code,ch_pay_mode, to_char(nu_bank_code) nu_bank_code, to_char(vc_cc_no)vc_cc_no, to_char(nu_bank_code) nu_bank_code1,
						dt_voucher_date,nu_amount nu_sett_amt,null vc_remarks,VC_BANK_NAME nu_bank_code,'GV' vouch_type 
						from NEWWEBTBZ.GIFT_VOUCHER_PAYMENT 
						where vc_comp_code = '$companyCode'
						and dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and ch_pay_mode = 'CC' 
						and br_code= '88'
						union all
						SELECT b.cust_id vc_voucher_no, b.cust_id vouch, inward_date dt_voucher_date, drawn_on_add vc_approval_no,
						a.vc_want_to_buy vc_category, pay_mode ch_pay_mode, to_char(nu_bank_code) nu_bank_code, to_char(vc_cheque) vc_cc_no, 
						to_char(nu_bank_code) nu_bank_code1, inward_date dt_bill_date, amount nu_sett_amt, ' ' vc_remarks, 
						DRAWN_ON vc_card_type, 'KP' vouch_type
						FROM NEWWEBTBZ.reg_dt a, NEWWEBTBZ.in_dt b
						WHERE b.inward_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
						and b.pay_mode='CC' 
						and b.br_code= '88' 
						and a.cust_id = b.cust_id 
						and a.reg_no = b.reg_no
					Union all
					select vc_voucher_no, null vouch, dt_voucher_date, null vc_approval_no, vc_category,
						ch_pay_type ch_pay_mode, null nu_bank_code, null VC_CC_NO, vc_sale_voucher_no nu_bank_code1, dt_sale_voucher_date dt_bill_date, 
						nu_amount nu_sett_amt, vc_narration vc_remarks, null VC_CARD_TYPE, 'MR' vouch_type     
						from NEWWEBTBZ.check_bounce 
						where ch_pay_type = 'CC'
						and dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and br_code = '88'
					union all
					select dgc.gift_card_no, dgc.gift_card_no, dgc.receipt_date, dgc.vc_approval_no, null vc_category,
						ch_pay_mode, nu_bank_code, to_char(vc_cc_no), nu_bank_code nu_bank_code1, null dt_bill_date,
						dgc.nu_amount, hgc.vc_narration, dgc.vc_card_type, 'GC' vouch_type
						from NEWWEBTBZ.hd_gift_card hgc, NEWWEBTBZ.dt_gift_card dgc
						where hgc.gift_card_no = dgc.gift_card_no
						and hgc.gift_card_date = dgc.gift_card_date
						and ch_pay_mode = 'CC'
						and hgc.ch_cancel_flag = 'N'
						and trunc(receipt_date) BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and dgc.br_code = '88'
					order by 1		
				"""
			sql = new Sql(dataSource)
			ccResult = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getCCReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return ccResult
	}
	
	/**
	 * Method Name: amtCount
	 * Description: gets the amtCount report list
	 *
	 * @return : list of amtCount
	 */
	def amtCount(fromDate, toDate, companyCode, brCode) {
		def ccCountResult
		try{
			query =
			"""					
				select sum(NU_SETT_AMT) AS AMT from(
						SELECT a.NU_SETT_AMT NU_SETT_AMT
											FROM DT_CASH_CARD a, hd_Cash b
						WHERE a.vc_comp_code = b.vc_comp_code
						and a.vc_voucher_no = b.vc_voucher_no
						and a.dt_voucher_date = b.dt_voucher_date
						and a.br_Code = b.br_code
						and a.vc_Category = b.vc_category  
						and a.VC_COMP_CODE= '$companyCode'
						and a.DT_VOUCHER_DATE BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and a.ch_pay_mode = 'CC'
						and nvl(b.ch_cancel_flag,'N') = 'N' 
						and a.vc_voucher_no like '%SS%'
						and a.br_code= '88'
						union all
						select 
						 b.nu_amount NU_SETT_AMT
						from hd_sale_adv_ord a, DT_SALE_ADV_PAY b
						where a.vc_adv_ord_no = b.vc_adv_ord_no 
						and a.dt_adv_ord_date = b.dt_adv_ord_date 
						and a.vc_comp_code = b.vc_comp_code 
						and a.vc_comp_code = '$companyCode'
						and a.dt_adv_ord_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')   
						and b.ch_pay_mode = 'CC'
						and a.br_code=b.br_code
						and a.br_code= '88'
						and nvl(a.ch_cancel_flag,'N') = 'N'
						union all
						SELECT Nvl((a.NU_SETT_AMT*-1),0) NU_SETT_AMT
						FROM DT_CASH_CARD a, hd_cash b
						WHERE a.vc_comp_code = b.vc_comp_code
						and a.vc_voucher_no = b.vc_voucher_no
						and a.dt_voucher_date = b.dt_voucher_date
						and a.br_Code = b.br_code
						and a.vc_Category = b.vc_category
						and a.VC_COMP_CODE= '$companyCode'
						and a.DT_VOUCHER_DATE BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and a.ch_pay_mode='CC' 
						--and substr (a.vc_voucher_no,3,2) IN ( 'SS','VR')
						and substr(a.vc_voucher_no, instr(a.vc_voucher_no, a.br_code)+length(a.br_code),2) in ( 'SS','VR')
						and a.br_code='88'
						and nvl(b.ch_cancel_flag,'N') = 'N' 
						union all
						Select nu_amount NU_SETT_AMT 
						from NEWWEBTBZ.GIFT_VOUCHER_PAYMENT 
						where vc_comp_code = '$companyCode'
						and dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and ch_pay_mode = 'CC' 
						and br_code= '88'
						union all
						SELECT amount NU_SETT_AMT
						FROM NEWWEBTBZ.reg_dt a, NEWWEBTBZ.in_dt b
						WHERE b.inward_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and b.pay_mode='CC' 
						and b.br_code='88'
						and a.cust_id = b.cust_id 
						and a.reg_no = b.reg_no
						Union all
						select nu_amount NU_SETT_AMT    
						from NEWWEBTBZ.check_bounce 
						where ch_pay_type = 'CC'
						and dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and br_code = '88'
						union all
						select dgc.nu_amount NU_SETT_AMT
						from NEWWEBTBZ.hd_gift_card hgc, NEWWEBTBZ.dt_gift_card dgc
						where hgc.gift_card_no = dgc.gift_card_no
						and hgc.gift_card_date = dgc.gift_card_date
						and ch_pay_mode = 'CC'
						and hgc.ch_cancel_flag = 'N'
						and trunc(receipt_date) BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
						and dgc.br_code = '88'
						) a	
					
				"""		
			sql = new Sql(dataSource)			
			ccCountResult = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService amtCount method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return ccCountResult
	}
	
	/**
	 * Method Name: getEmployeeList
	 * Description: gets the employee list
	 *
	 * @return : list of purity
	 */
	def getEmployeeList(brCode) {
		def str
		try{
			str =
			"""
				select VC_SALES_ID,EMP_NAME from EMP_MST WHERE BR_CODE='$brCode'
			"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
		}catch(Exception exception){
			log.info("Exception in ReportService getEmployeeList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
		
	/**
	 * Method Name: getDeptt
	 * Description: gets the dept list
	 *
	 * @return : list of dept
	 */
	def getDeptt(hdCashInstance) {
		def str
		try{
			str =
			"""
				select distinct vc_deptt from NEWWEBTBZ.dt_cash where br_code = '' and vc_category = ''
			"""
			sql = new Sql(dataSource)
			result = sql.rows(str)			
		}catch(Exception exception){
			log.info("Exception in ReportService getDeptt method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSubCode
	 * Description: gets the sub code list
	 *
	 * @return : list of sub code
	 */
	def getSubCode(hdCashInstance) {
		def str
		try{
			str =
			"""
				select distinct VC_SUB_CODE
					from NEWWEBTBZ.dt_cash
					where br_code = ''
					and vc_item_code = ''
			"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
		}catch(Exception exception){
			log.info("Exception in ReportService getSubCode method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getItem
	 * Description: gets the item list
	 *
	 * @return : list of item
	 */
	def getItem(){
		def str
		try{
			str =
			"""
				SELECT distinct vc_item_code
				FROM NEWWEBTBZ.dt_cash
				where br_Code = '' 
				and vc_category = ''
			"""			
			sql = new Sql(dataSource)
			result = sql.rows(str)
		}catch(Exception exception){
			log.info("Exception in ReportService getItem method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getCustomer
	 * Description: gets the customer list
	 *
	 * @return : list of item
	 */
	def getCustomer(brCode,compCode) {
		def str
		try{
			str =
			"""
				Select distinct(vc_cust_Fname) from NEWWEBTBZ.hd_cash a,dt_cash_payment b
				where a.vc_comp_code = b.vc_comp_code
				and a.vc_voucher_no = b.vc_voucher_no 
				and a.dt_voucher_date = b.dt_voucher_date 
				and a.vc_category = b.vc_Category 
				and a.ch_cancel_flag = 'N'
				and b.CH_PAY_MODE = 'B'
				and a.br_code =b.br_code
				and a.br_code ='$brCode'
				and a.vc_comp_code ='$compCode'
			"""
			sql = new Sql(dataSource)
			result = sql.rows(str)			
		}catch(Exception exception){
			log.info("Exception in ReportService getCustomer method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSalesList
	 * Description: gets the sales list
	 *
	 * @return : list of sales
	 */
	def getSalesList(fromDate,toDate,category,empId,purity,item,subCode,deptt,custName,companyCode,brCode) {
		def selectCategory,result1,flexVal,cbResult
		String todayAsString
		try{
			if(empId==null || empId==''){
				empId='ALL'
			}
			if(purity==null || purity==''){
				purity='ALL'
			}
			if(item==null || item==''){
				item='ALL'
			}
			if(subCode==null || subCode==''){
				subCode='ALL'
			}
			if(deptt==null || deptt==''){
				deptt='ALL'
			}
			if(custName==null || custName==''){
				custName='ALL'
			}
			query1 = "select FLEX_VALUE FROM fnd_flex_values_vl WHERE DESCRIPTION='$category'"
			Sql sql1 = new Sql(dataSource)
			result1 = sql1.rows(query1)
			result1.each {
				flexVal= (it.values()[0])
			}
			query =
			"""
					SELECT a.vc_ebs_order_no, a.dt_voucher_date dt_voucher_date,
						a.vc_voucher_no vc_voucher_no,
						a.ch_stage,
						b.vc_emp_code,
						
						substr(a.vc_voucher_no,instr(a.vc_voucher_no, a.br_code)+(length(a.br_code)+2)) s,
						a.vc_category,
						
						decode(b.vc_sale_type,'K',-(NVL(b.nu_amount,0) - NVL(b.nu_discount,0)),(NVL(b.nu_amount,0) - NVL(b.nu_discount,0))) nu_bill_amount ,
						b.vc_label_no ,
						decode(b.vc_item_code, 'CO', 'PDS', b.vc_item_code) vc_item_code, 
						b.vc_sub_code,
						b.vc_purity vc_purity1,
						
						decode(b.vc_sale_type,'K',-b.nu_nang,b.nu_nang) nu_nang,
						decode(b.vc_sale_type,'K',-b.nu_nang_pcs,b.nu_nang_pcs) nu_nang_pcs,
						decode(b.vc_sale_type,'K',-b.nu_pcs,b.nu_pcs) nu_pcs,
						decode(b.vc_sale_type,'K',-b.nu_gross_wtt,b.nu_gross_wtt) nu_gross_wtt,
						decode(b.vc_sale_type,'K',-b.nu_nett_wtt,b.nu_nett_wtt) nu_nett_wtt,
						a.dt_voucher_date date1 , 
						a.vc_voucher_no no1 , 
						b.nu_sr_no nu_sr_no1,
						b.vc_sale_type,
						b.nu_rate,
						a.VC_CUST_FNAME||' '||a.VC_CUST_MNAME||'  '||A.VC_CUST_LNAME vc_design_no
						FROM NEWWEBTBZ.hd_cash a, NEWWEBTBZ.dt_cash b 
						WHERE a.vc_voucher_no = b.vc_voucher_no AND
						a.dt_voucher_date=b.dt_voucher_date AND
						
						substr(a.vc_voucher_no,instr(a.vc_voucher_no, a.br_code)+length(a.br_code),2) IN ('SS' ,'CI','EI','FI') AND
						a.ch_cancel_flag='N' AND
						a.vc_cust_fname||' '||a.vc_cust_lname= DECODE('$custName','ALL',a.vc_cust_fname||' '||a.vc_cust_lname,'$custName') AND
						b.vc_item_code = DECODE('$item','ALL',b.vc_item_code ,'$item') AND
						b.vc_deptt = DECODE('$deptt','ALL',b.vc_deptt ,'deptt') AND
						b.vc_sub_code = DECODE('$subCode','ALL',b.vc_sub_code ,'$subCode') AND
						b.dt_voucher_date BETWEEN TO_DATE('$fromDate','DD/MM/YYYY') AND TO_DATE('$toDate','DD/MM/YYYY') AND
						b.vc_purity  =DECODE('$purity','ALL',b.vc_purity,'$purity') AND 
						a.vc_category = '$flexVal' AND 
						a.vc_comp_code = '$companyCode' AND 
						b.vc_comp_code = a.vc_comp_code  AND 
						a.vc_category = b.vc_category AND 
						a.br_code='$brCode' AND 
						b.br_code=a.br_code AND
						b.vc_sale_type not in('SS')						 
						ORDER BY 2							
				"""
			sql = new Sql(dataSource)			
			cbResult = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSalesList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return cbResult
	}
	
	/**
	 * Method Name: getPurCategory
	 * Description: gets the purity cat list
	 *
	 * @return : list of purity cat
	 */
	def getPurCategory(companyCode, brCode) {
		def str
		try{
			str =
				"""
				select b.vc_reason_desc vc_reason_desc,b.VC_REASON_CODE vc_category from 
					NEWWEBTBZ.mst_reason b where b.vc_reason_flg='C'
					and b.vc_comp_code='02'
					and
					b.br_code = '88'
				"""
			sql = new Sql(dataSource)
			resultCat = sql.rows(str)			
		}catch(Exception exception){
			log.info("Exception in ReportService getPurCategory method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultCat
	}
	
	/**
	 * Method Name: getPur
	 * Description: gets the purity list
	 *
	 * @return : list of purity 
	 */
	def getPur(hdCashInstance){
		def x = hdCashInstance
		def fromDate
		def toDate
		def str
		try{
			dateStr1 = x?.fromDate
			date1 = (Date)formatter.parse(dateStr1)
			cal.setTime(date1)
			formatedDate1 = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR)
			dateStr2 = x?.toDate		
			date2 = (Date)formatter.parse(dateStr2)		
			cal1.setTime(date2)
			formatedDate2 = (cal1.get(Calendar.MONTH) + 1) + "/" + cal1.get(Calendar.DATE) + "/" +         cal1.get(Calendar.YEAR)			
			fromDate = x.fromDate
			toDate = x.toDate
			
			str =
				"""
					select distinct a.vc_purity,b.vc_category from
						 NEWWEBTBZ.dt_bill a,NEWWEBTBZ.hd_bill b
						 where a.vc_bill_no=b.vc_bill_no and
						 a.dt_bill_date=b.dt_bill_date
						 and a.vc_comp_code=b.vc_comp_code and
						 b.vc_comp_code='02' and
						a.br_code = b.br_code and
						a.br_code = '88' and
						b.vc_category ='G' and
						b.vc_type_of_bill = 'PB'
						and a.dt_bill_date between TO_DATE('$formatedDate1','MM/DD/YYYY') AND TO_DATE('$formatedDate2','MM/DD/YYYY') 
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
		}catch(Exception exception){
			log.info("Exception in ReportService getPur method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getItemList
	 * Description: gets the item list
	 *
	 * @return : list of item
	 */
	def getItemList(hdCashInstance) {
		def str
		try{
			str =
				"""
				SELECT ITEM vc_item_code
		            FROM APPS.TBZ_POS_ITEMCODE_LABEL_V
		            WHERE  ORG_ID='01'
		            and PRECIOUS_METAL_STONES='G'
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)			
		}catch(Exception exception){
			log.info("Exception in ReportService getItemList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSupplier
	 * Description: gets the supplier list
	 *
	 * @return : list of supplier
	 */
	def getSupplier(hdCashInstance) {
		def x = hdCashInstance
		def str
		try{
			str =
				"""
				select distinct vc_supplier_code nu_supplier_code,VC_SUPPLIER_FNAME vc_supplier_name 
					from NEWWEBTBZ.hd_bill
					where vc_comp_code ='02'
					and br_code ='88'
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getSupplier method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPurchageList
	 * Description: gets the purchase list
	 *
	 * @return : list of purchase
	 */
	def getPurchageList(fromDate, toDate,category,empId,purity,item,subCode,deptt,custName,companyCode, brCode){
		def catIdStr
		Sql sqlCat
		def catCde
		def categoryCde
		def selectPurity
		def type
		def goldType
		def dept
		def itemCode
		def supplier
		def supplierIdStr
		def supCde
		def supplierCde
		try{
			
			catIdStr = "select VC_REASON_CODE from mst_reason where vc_reason_desc ='category'"
			sqlCat = new Sql(dataSource)
			catCde = sqlCat.rows(catIdStr)
			categoryCde = catCde?.VC_REASON_CODE[0]			
			selectPurity = purity
			brCode = brCode
			type = type
			goldType = goldType
			dept = deptt
			itemCode = item
			
			//supplier = opName
			supplierIdStr = "select vc_supplier_code from NEWWEBTBZ.hd_bill where VC_SUPPLIER_FNAME ='ALL'"
			sql1 = new Sql(dataSource)
			supCde = sql1.rows(supplierIdStr)
			supplierCde = supCde?.VC_SUPPLIER_CODE[0]
			
			query =
			"""
					SELECT ALL a.dt_bill_date,
				           SUBSTR (a.vc_bill_no, LENGTH (a.br_code)+1) substr_a_vc_bill_no_3_8,
				           b.vc_purity, b.vc_item_code, b.vc_sub_code, b.nu_nang,
				           DECODE ('G', 'W', b.nu_pcs, b.nu_nang_pcs) pcs,
				           b.nu_nett_wtt, b.nu_gross_wtt, b.nu_amount,b.nu_other_charges,         
				          b.nu_rate,
				           a.vc_field1 qc_no,
				           a.dt_field1 qc_date, b.nu_field1 nu_sr_no,
				              a.vc_supplier_fname
				           || ' '
				           || SUBSTR (a.vc_supplier_mname, 1, 1)
				           || ' '
				           || a.vc_supplier_lname vc_supplier_fname,
				           (SELECT vc_range
				              FROM mst_crt_range
				             WHERE vc_range_code = b.vc_field1) vc_field1, vc_adv_ord_no,
				           dt_adv_ord_date, b.vc_ebs_order_no, a.ch_stage,b.nu_other_charges
				      FROM NEWWEBTBZ.hd_bill a, NEWWEBTBZ.dt_bill b
				      WHERE a.br_code = b.br_code
				       AND a.br_code = '$brCode'
				       AND a.vc_bill_no = b.vc_bill_no 
				       AND a.dt_bill_date = b.dt_bill_date
				       AND a.dt_bill_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
				       AND UPPER (a.vc_category) = UPPER ('$categoryCde')
				       AND a.vc_type_of_bill IN ('PB', 'TB')
				       AND vc_cancel_flag = 'N'
				       AND b.vc_purity = DECODE ('$selectPurity', 'ALL', b.vc_purity, '$selectPurity')
				       AND b.vc_item_code = DECODE ('OG', 'ALL', b.vc_item_code,'OG')
				       AND a.vc_supplier_code = DECODE ('$supplierCde', 'ALL', a.vc_supplier_code, '$supplierCde' )
				       AND a.vc_gold_type = '$goldType'
				       AND b.vc_type = DECODE ('$type', 'ALL', b.vc_type, '$type')					
				"""
			sql = new Sql(dataSource)			
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getPurchageList method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	
	/**
	 * Method Name: getSupplier
	 * Description: gets the supplier list
	 *
	 * @return : list of supplier
	 */
	def getModuleList(moduleInstance ) {
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					select VC_MODULE_CODE, VC_MODULE_OBJECT
					from MK_MODULE
					where br_code = '01'
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getSupplier method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getUserRoleList
	 * Description: gets the User Role list
	 *
	 * @return : list of User Role
	 */
	def getUserRoleList(params) {
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					SELECT     b.vc_user_name,a.vc_company_name,a.vc_comp_code, a.vc_comp_code comp_code, 
						b.ch_user_code,  d.vc_role_name role_name 
						FROM     MST_COMPANY a, MK_USERS b, MK_ROLES_COMP c,MK_ROLES d 
						WHERE     c.ch_user_code = b.ch_user_code
						    AND a.vc_comp_code = c.vc_comp_code
						--    AND B.CH_USER_CODE = '01'
						    AND a.vc_comp_code = c.vc_comp_code
						    and c.ch_role_code=d.ch_role_code
						    AND c.br_code = d.br_code
						    AND b.br_code = c.br_code
						    AND a.vc_comp_code = '02'
						    AND b.br_code = '98'
						ORDER BY b.ch_user_code

				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getSupplier method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result		
	}
	
	/**
	 * Method Name: getMenuList
	 * Description: gets the Menu list
	 *
	 * @return : list of Menu
	 */
	def getMenuList(){
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					select VC_MENU_CODE, lpad(' ',length(vc_menu_code)*3,' ') ||vc_menu_object, VC_MODULE_CODE from MK_MODULE_MENU 
					where br_code = '01' order by vc_menu_code
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getSupplier method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getRoleList
	 * Description: gets the Role list
	 *
	 * @return : list of Role
	 */
	def getRoleList(){
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					SELECT NEWWEBTBZ.MK_ROLES.br_code,NEWWEBTBZ.MK_ROLES.VC_ROLE_NAME,NEWWEBTBZ.MK_MODULE.VC_MODULE_OBJECT, 
					LPAD(' ',LENGTH(NEWWEBTBZ.MK_MODULE_MENU.VC_MENU_CODE)*3,' ' )||NEWWEBTBZ.MK_MODULE_MENU.VC_MENU_OBJECT menu
					FROM NEWWEBTBZ.MK_ROLES,NEWWEBTBZ.MK_MODULE, NEWWEBTBZ.MK_MODULE_MENU, NEWWEBTBZ.MK_ROLES_MODULE,NEWWEBTBZ.MK_ROLES_MENU
					WHERE MK_ROLES.CH_ROLE_CODE 		  = MK_ROLES_MODULE.CH_ROLE_CODE
					  AND MK_ROLES.CH_ROLE_CODE			  = MK_ROLES_MODULE.CH_ROLE_CODE
					  AND MK_ROLES.CH_ROLE_CODE			  = MK_ROLES_MENU.CH_ROLE_CODE
					  AND MK_ROLES_MODULE.CH_MODULE_CODE  = MK_MODULE.VC_MODULE_CODE
					  AND MK_ROLES_MODULE.CH_MODULE_CODE  = MK_ROLES_MENU.VC_MODULE_CODE
					  AND MK_MODULE_MENU.VC_MODULE_CODE   = MK_MODULE.VC_MODULE_CODE
					  AND MK_ROLES.br_code = 82
					 ORDER BY NEWWEBTBZ.MK_ROLES.VC_ROLE_NAME ASC, NEWWEBTBZ.MK_MODULE.VC_MODULE_OBJECT ASC

				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getSupplier method : "+exception)	
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getAdvanceActiveReport
	 * Description: gets the Advance Active 
	 *
	 * @return : list of Advance Active
	 */
	def getAdvanceActiveReport(fromDate, toDate,category,goldType,paymode,companyCode, brCode){
		def str
		def str2
		//def brCode = session?.brCode
		try{
			str =
				"""
					SELECT DISTINCT a.vc_adv_ord_no,a.dt_adv_ord_date,a.nu_tot_adv_amt, a.VC_CUST_FNAME||' '||a.VC_CUST_MNAME||' '||a.VC_CUST_LNAME VC_CUST_FNAME,a.vc_address1||' '||VC_ADDRESS2||' '||VC_ADDRESS3 VC_ADDRESS1,a.nu_mc_id,a.nu_mc_no,decode(a.VC_ADVANCE_TYPE,'R','Ready Stock','C','Customer Order','A','Garmage Booked','F','Advance Only') adv_type,
					b.vc_category, a.ch_stage
					FROM sales.hd_sale_adv_ord a, sales.dt_sale_adv_ord b, sales.dt_sale_adv_pay c
					WHERE  a.dt_adv_ord_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') AND
					a.vc_adv_ord_no=b.vc_adv_ord_no and 
					a.vc_adv_ord_no=c.vc_adv_ord_no and
					a.dt_adv_ord_date=b.dt_adv_ord_date and 
					a.dt_adv_ord_date=c.dt_adv_ord_date and
					a.br_code =	b.br_code and
					a.br_code=c.br_code AND
					a.ch_cancel_flag='N' AND
					a.nu_tot_adv_amt IS NOT NULL AND
					a.vc_comp_code='02' AND
					a.br_code='01'
					ORDER BY a.dt_adv_ord_date,a.vc_adv_ord_no
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
			
			str2 =
			"""
					SELECT DISTINCT a.vc_adv_ord_no,a.dt_adv_ord_date, b.vc_category, b.nu_nett_wtt,  b.nu_carrat_wtt, b.nu_rate
					FROM sales.hd_sale_adv_ord a, sales.dt_sale_adv_ord b
					WHERE a.dt_adv_ord_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
					a.vc_adv_ord_no=b.vc_adv_ord_no and 
					a.dt_adv_ord_date=b.dt_adv_ord_date and 
					a.ch_cancel_flag='N' AND
					a.nu_tot_adv_amt IS NOT NULL AND
					a.vc_comp_code='02' AND
					a.br_code='01' AND
					b.vc_category = DECODE('G','ALL',b.vc_category,'G')
					and a.vc_advance_type=DECODE('Advance','ALL',a.vc_advance_type,'Advance')
					ORDER BY a.dt_adv_ord_date,a.vc_adv_ord_no
				"""
		sql = new Sql(dataSource)
		def result1 = sql.rows(str2)
			
		
			
		}catch(Exception exception){
			log.info("Exception in ReportService getSupplier method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getGiftVoucherActiveReport
	 * Description: gets the Advance Active
	 *
	 * @return : list of Gift Voucher Active
	 */
	def getGiftVoucherActiveReport(fromDate, toDate,companyCode, brCode){
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					select a.vc_comp_code q1_comp,a.vc_category_code q1_catg , a.vc_voucher_no q1_vno, a.dt_issue_date q1_idate, a.VC_CUST_FNAME||' '||a.VC_CUST_MNAME||' '||a.VC_CUST_LNAME q1_name,
					a.VC_ADDRESS1||' '|| a.VC_ADDRESS2||' '|| a.VC_ADDRESS3 q1_addr, a.vc_field2 q1_iss_to,b.nu_amount q1_amt,b.ch_pay_mode, a.vc_ebs_order_no 
					 from mst_vouch_activation a, gift_voucher_payment b
					where a.dt_issue_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') AND
					    a.vc_comp_code = b.vc_comp_code and
					    a.vc_comp_code ='02' and
					     a.vc_field1=b.vc_entry_no and
					    a.vc_voucher_no = b.vc_voucher_no and
					   a.dt_issue_date = b.dt_voucher_date 
					and a.br_code='82'
					and a.br_code=b.br_code
					 order by 4,5 asc

				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getGiftVoucherActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getAdvanceSettleReport
	 * Description: gets the AdvanceSettleReport list
	 *
	 * @return : list of AdvanceSettleReport
	 */
	def getAdvanceSettleReport(fromDate, toDate, paymode, category, companyCode, brCode){
		def AdvanceSettleList
		try{
			if(paymode==null || paymode==''){
				paymode='ALL'
			}
			if(category==null || category==''){
				category='ALL'
			}			
			query =
				"""					
				select a.vc_cust_id, a.vc_adv_ord_no ,a.dt_adv_ord_date,nu_sett_amt nu_tot_adv_amt ,a.vc_category,
				a.vc_cust_fname||' '||a.vc_cust_mname||' '||a.vc_cust_lname vc_customer_name,
				a.vc_address1||' '||a.vc_address2||' '||a.vc_address3 vc_address,a.nu_mc_id,
				a.nu_mc_no,ch_active_flag,a.ch_cancel_flag,
				decode(ch_pay_mode,'C','CASH','A','ADVANCE','T','ADV TRF','CQ','CHECK','CC','CREDIT CARD') pay_mode,
				substr(b.vc_voucher_no, instr(b.vc_voucher_no, 'SI'))inv_no, 
				b.dt_voucher_date inv_date, a.nu_nett_wtt,a.vc_comp_code,a.br_code, 
				'' vc_settle_chq_no, 0 nu_settle_bank_code, a.vc_field1 adv_type
				from hd_sale_adv_ord a,dt_cash_advance b, hd_cash c
				where b.dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
				and c.dt_voucher_date = b.dt_voucher_date 
				and c.vc_voucher_no = b.vc_voucher_no
				and c.vc_category = b.vc_category
				and c.ch_cancel_flag = 'N'
				and a.ch_cancel_flag='N' 
				and nu_tot_adv_amt is not null 
				and a.vc_category = decode('$category','ALL',a.vc_category,'$category')
				and ch_pay_mode = decode('$paymode','ALL',ch_pay_mode,'$paymode')
				and a.vc_comp_code=b.vc_comp_code 
				and b.vc_comp_code=c.vc_comp_code 
				and a.vc_adv_ord_no=b.vc_adv_ord_no
				and a.vc_comp_code='$companyCode'
				and a.br_code=b.br_code
				and b.br_code=c.br_code
				and a.br_code= decode('$brCode','ALL',a.br_code,'$brCode')
				union all
				select a.vc_cust_id, a.vc_adv_ord_no ,a.dt_adv_ord_date,nu_sett_amt nu_tot_adv_amt ,a.vc_category,
				a.vc_cust_fname||' '||a.vc_cust_mname||' '||a.vc_cust_lname vc_customer_name,
				a.vc_address1||' '||a.vc_address2||' '||a.vc_address3 vc_address,a.nu_mc_id,
				a.nu_mc_no,ch_active_flag,a.ch_cancel_flag,
				decode(ch_pay_mode,'C','CASH','A','ADVANCE','T','ADV TRF','CQ','CHECK','CC','CREDIT CARD') pay_mode,
				substr(b.vc_voucher_no, instr(b.vc_voucher_no, 'SI'))inv_no, 
				b.dt_voucher_date inv_date, a.nu_nett_wtt,a.vc_comp_code,a.br_code, 
				'' vc_settle_chq_no, 0 nu_settle_bank_code, a.vc_field1 adv_type
				from hd_sale_adv_ord a,dt_cash_advance_mr b, hd_cash c
				where b.dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
				and to_date(b.vc_field2,'DD-MON-RRRR') = c.dt_voucher_date 
				and b.vc_field1 = c.vc_voucher_no
				and c.vc_category = b.vc_category
				and c.ch_cancel_flag = 'N'
				and a.ch_cancel_flag='N' 
				and nu_tot_adv_amt is not null 
				and a.vc_category = decode('$category','ALL',a.vc_category,'$category')
				and ch_pay_mode = decode('$paymode','ALL',ch_pay_mode,'$paymode')
				and a.vc_comp_code=b.vc_comp_code 
				and b.vc_comp_code=c.vc_comp_code 
				and a.vc_adv_ord_no=b.vc_adv_ord_no
				and a.vc_comp_code='$companyCode'
				and a.br_code=b.br_code
				and b.br_code=c.br_code
				and a.br_code= decode('$brCode','ALL',a.br_code,'$brCode')
				union all
				select a.vc_cust_id, a.vc_adv_ord_no ,a.dt_adv_ord_date,nu_sett_amt nu_tot_adv_amt ,a.vc_category,
				a.vc_cust_fname||' '||a.vc_cust_mname||' '||a.vc_cust_lname vc_customer_name,
				a.vc_address1||' '||a.vc_address2||' '||a.vc_address3 vc_address,a.nu_mc_id,
				a.nu_mc_no,ch_active_flag,a.ch_cancel_flag,
				decode(ch_pay_mode,'C','CASH','A','ADVANCE','T','ADV TRF','CQ','CHECK','CC','CREDIT CARD') pay_mode,
				substr(b.vc_voucher_no, instr(b.vc_voucher_no, 'SI'))inv_no, /*substr(b.vc_voucher_no,3) inv_no,*/----Changes Done on 16-Mar-2012------
				b.dt_voucher_date inv_date, a.nu_nett_wtt,a.vc_comp_code,a.br_code, 
				'' vc_settle_chq_no, 0 nu_settle_bank_code, a.vc_field1 adv_type
				from hd_sale_adv_ord a, dt_cash_advance_mr b, tbzcustom.xxtbz_fr_headers c
				where b.dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
				and to_date(b.vc_field2,'DD-MON-RRRR') = c.dt_voucher_date 
				and b.vc_field1 = to_char(c.vc_voucher_no)
				and c.vc_category = b.vc_category
				and c.cancel_flag = 'N'
				and a.ch_cancel_flag='N' 
				and nu_tot_adv_amt is not null 
				and a.vc_category = decode('$category','ALL',a.vc_category,'$category')
				and ch_pay_mode = decode('$paymode','ALL',ch_pay_mode,'$paymode')
				and a.vc_comp_code=b.vc_comp_code 
				and a.vc_adv_ord_no=b.vc_adv_ord_no
				and a.vc_comp_code='$companyCode'
				and a.br_code=b.br_code
				and b.br_code=c.operating_unit
				and a.br_code= decode('$brCode','ALL',a.br_code,'$brCode')
				union all
				select b.vc_cust_id, a.vc_adv_ord_no, b.dt_adv_ord_date dt_adv_ord_date,nvl(a.nu_settle_cash_amt,0)+nvl(a.nu_settle_chq_amt,0) nu_tot_adv_amt,
				b.vc_category,b.vc_cust_fname||' '||b.vc_cust_mname||' '||b.vc_cust_lname vc_customer_name,b.vc_address1||' '||b.vc_address2||' '||b.vc_address3 vc_address, a.nu_mc_id,a.nu_mc_no,b.ch_active_flag,b.ch_cancel_flag,
				decode(a.vc_settle_terms_of_payment,'C','CASH','A','ADVANCE','T','ADV TRF','CQ','CHECK','CC','CREDIT CARD') pay_mode, 
				decode(a.vc_settle_terms_of_payment, 'AB', 'Agst Balance Bill', 'Refund') inv_no, a.dt_settle_date  inv_date, b.nu_nett_wtt,a.vc_comp_code,a.br_code,
				a.vc_settle_chq_no, a.nu_settle_bank_code, b.vc_field1 adv_type
				from dt_sale_adv_settle a,hd_sale_adv_ord b
				where a.vc_adv_ord_no=b.vc_adv_ord_no
				and a.vc_comp_code=b.vc_comp_code
				and a.dt_settle_date between   TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') 
				and a.ch_cancel_flag='N'
				and a.dt_settle_date is not null
				and a.vc_settle_terms_of_payment = decode('$paymode','ALL',a.vc_settle_terms_of_payment,'$paymode')
				and exists (select 'Yes'
				from sales.dt_sale_adv_ord 
				where vc_adv_ord_no = b.vc_adv_ord_no
				and vc_category = decode('$category','ALL',vc_category,'$category'))
				and a.vc_comp_code='$companyCode'
				and a.br_code=b.br_code
				and a.br_code= decode('$brCode','ALL',a.br_code,'$brCode')
				order by br_code, inv_date, vc_adv_ord_no
				"""
			sql = new Sql(dataSource)
			AdvanceSettleList = sql.rows(query)
			if(AdvanceSettleList){
				AdvanceSettleList.each{
					accRefAdvStlCustId = it.values()[0]
				}
			}			
		}catch(Exception exception){
			log.info("Exception in ReportService getAdvanceSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return AdvanceSettleList
	}
	
	/**
	 * Method Name: getAccRefNo
	 * Description: gets the AccRefNo 
	 *
	 * @return : list of AccRefNo
	 */
	def getAccRefNo(){
		try{
			query =
				"""
				SELECT CUSTOMER_NUMBER FROM AR_CUSTOMERS where orig_system_reference='$accRefAdvStlCustId'
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)	
		}catch(Exception exception){
			log.info("Exception in ReportService getAccRefNo method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}	
	
	/**
	 * Method Name: getTravFrgnCrncyReport
	 * Description: gets the TravFrgnCrncyReport list
	 *
	 * @return : map of resultMap
	 */
	def getTravFrgnCrncyReport(fromDate,companyCode,brCode){
		def cashRs,chequeRs,cashCurrency,chqCurrency,cashCurrRs,funCashCurrency,chqCurRs,funChqCurrency
		def cashInvNo,cashExAmt,cashRate,cashRupees,cashPartyName
		def chqInvNo,chqExAmt,chqRate,chqRupees,chqPartyName
		def resultListOne = []
		def resultListTwo = []
		def resultMap = [:]
		try{
			query =
				"""
				select rownum,a.vc_voucher_no vc_voucher_no,(a.vc_currency_code) nu_currency_code,a.dt_voucher_date dt_voucher_date,
				a.vc_category||'-'||substr(a.vc_voucher_no, instr(a.vc_voucher_no, a.br_code)+length(a.br_code)+2) vouch,
				a.Ch_Pay_Mode ch_pay_mode,a.nu_ex_amt nu_ex_amt,a.nu_conv_factor nu_conv_factor ,a.nu_sett_amt nu_sett_amt,
				B.VC_CUST_fNAME||' '||B.VC_CUST_MNAME||' '||B.VC_CUST_LNAME vc_cust_name
				from dt_cash_currency a,hd_cash b
				where a.vc_comp_code = b.vc_comp_code
				and a.br_Code = b.br_code
				and a.vc_voucher_no = b.vc_voucher_no
				and a.dt_voucher_date = b.dt_voucher_date
				and a.vc_category = b.vc_category
				and a.vc_comp_code='$companyCode' 
				and a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY') 
				and a.vc_comp_code=b.vc_comp_code 
				and a.vc_voucher_no=b.vc_voucher_no 
				and a.dt_voucher_date=b.dt_voucher_date 
				and a.vc_category = b.vc_category
				and a.br_code=b.br_code
				and a.br_code='$brCode'
				and nvl(b.ch_cancel_flag ,'N') = 'N'
				UNION
				select rownum,(a.VC_ADV_ORD_NO) vc_voucher_no,b.VC_CURRENCY_NAME nu_currency_code,a.dt_adv_ord_date dt_voucher_date, 
				(a.VC_ADV_ORD_NO) vouch,b.CH_PAY_MODE ch_pay_mode,b.nu_fc_amount nu_ex_amt,
				b.nu_exchange_rate nu_conv_factor,b.NU_AMOUNT nu_sett_amt,a.VC_CUST_fNAME||' '||a.VC_CUST_MNAME||' '||a.VC_CUST_LNAME vc_cust_name 
				from hd_sale_adv_ord a ,dt_sale_adv_pay b 
				where a.vc_comp_code = '$companyCode' 
				and a.vc_comp_code = b.vc_comp_code 
				and a.dt_adv_ord_date = TO_DATE('$fromDate','MM/DD/YYYY') 
				and a.dt_ADV_ORD_date =b.dt_ADV_ORD_date 
				and a.VC_ADV_ORD_NO = b.VC_ADV_ORD_NO 
				and b.ch_pay_mode in('FC','TQ') 
				and b.VC_CURRENCY_NAME is not null
				and a.br_code=b.br_code
				and a.br_code='$brCode'
				and nvl(a.ch_cancel_flag,'N') = 'N'
				"""
			sql = new Sql(dataSource)
			cashRs = sql.rows(query)			
			if(cashRs!=null && !cashRs.isEmpty()){
				cashRs.each {
					def cashMap = [:]
					cashInvNo = it.values()[1]
					cashCurrency = it.values()[2]
					cashExAmt = it.values()[6]
					cashRate = it.values()[7]
					cashRupees = it.values()[8]
					cashPartyName = it.values()[9]
					
					if(cashInvNo==null || cashInvNo=="null") {
						cashInvNo = 0
					}
					if(cashExAmt==null || cashExAmt=="null") {
						cashExAmt = 0
					}
					if(cashRate==null || cashRate=="null") {
						cashRate = 0
					}
					if(cashRupees==null || cashRupees=="null") {
						cashRupees = 0
					}
					if(cashPartyName==null || cashPartyName=="null") {
						cashPartyName = ''
					}
					
					cashMap.put("cashInvNo", cashInvNo)
					cashMap.put("cashExAmt", cashExAmt)
					cashMap.put("cashRate", cashRate)
					cashMap.put("cashRupees", cashRupees)
					cashMap.put("cashPartyName", cashPartyName)
					
					if(cashCurrency){
						def cashQry = """
										select DISTINCT currency AS m_desc
										from apps.tbz_pos_buy_exchange_rate
										where currency = '$cashCurrency'
									  """
						Sql sql2 =new Sql(dataSource)
						cashCurrRs = sql2.rows(cashQry)
						cashCurrRs.each{
							funCashCurrency =it.values()[0]
							cashMap.put("funCashCurrency", funCashCurrency)
						}
					}
					resultListOne.add(cashMap)
				}				
			}
			resultMap.put("resultListOne", resultListOne)
			query1 =
			"""
			select a.vc_voucher_no,a.VC_currency_code nu_currency_code,a.dt_voucher_date,
			a.vc_category||'-'||substr(a.vc_voucher_no, instr(a.vc_voucher_no, a.br_code)+length(a.br_code)+2) vouch,
			a.Ch_Pay_Mode Ch_Pay_Mode, a.nu_ex_amt,a.nu_conv_factor conv,a.nu_sett_amt, b.vc_cust_fname CUSTOMER
			from dt_cash_traveller a,hd_cash b
			where a.vc_comp_code='$companyCode'  and
			a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY') and
			a.vc_comp_code=b.vc_comp_code and
			a.vc_voucher_no=b.vc_voucher_no and
			a.dt_voucher_date=b.dt_voucher_date and
			a.vc_Category = b.vc_category and
			CH_PAY_MODE = 'TQ'
			and a.br_code=b.br_code
			and a.br_code='$brCode'
			and nvl(b.ch_cancel_flag,'N') = 'N'
			"""			
			sql1 = new Sql(dataSource)
			chequeRs = sql1.rows(query1)
			if(chequeRs!=null && !chequeRs.isEmpty()){
				chequeRs.each {
					def chqMap = [:]
					chqCurrency =it.values()[2]
					chqInvNo = it.values()[1]
					chqExAmt = it.values()[6]
					chqRate = it.values()[7]
					chqRupees = it.values()[8]
					chqPartyName = it.values()[9]
					
					if(chqInvNo==null || chqInvNo=="null") {
						chqInvNo = 0
					}
					if(chqExAmt==null || chqExAmt=="null") {
						chqExAmt = 0
					}
					if(chqRate==null || chqRate=="null") {
						chqRate = 0
					}
					if(chqRupees==null || chqRupees=="null") {
						chqRupees = 0
					}
					if(chqPartyName==null || chqPartyName=="null") {
						chqPartyName = ''
					}
					
					chqMap.put("chqInvNo", chqInvNo)
					chqMap.put("chqExAmt", chqExAmt)
					chqMap.put("chqRate", chqRate)
					chqMap.put("chqRupees", chqRupees)
					chqMap.put("chqPartyName", chqPartyName)
					
					if(chqCurrency){
						def chqQry = """
							select DISTINCT currency into m_desc
							from apps.tbz_pos_buy_exchange_rate
							where currency = '$chqCurrency'
						"""
						Sql sql3 =new Sql(dataSource)
						chqCurRs = sql3.rows(chqQry)
						chqCurRs.each{
							funChqCurrency =it.values()[0]
							chqMap.put("funChqCurrency", funChqCurrency)
						}
					}
					resultListTwo.add(chqMap)
				}
			}
			resultMap.put("resultListTwo", resultListTwo)
		}catch(Exception exception){
			log.info("Exception in ReportService getTravFrgnCrncyReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultMap
	}
	
	/**
	 * Method Name: getChequeToBeDepositReport
	 * Description: gets the ChequeToBeDepositReport list
	 *
	 * @return : list of ChequeToBeDepositReport
	 */
	def getChequeToBeDepositReport(fromDate,toDate,companyCode,brCode){
		def data
		try{
			def chkToBeDepQry =
				"""
				select dcc.vc_cust_fname||' '||dcc.vc_cust_lname customer_name,
				decode(dcc.vc_tran_type,'KALPVRUKSHA',dcc.vc_cust_id,null) vc_cust_id,
				dcc.vc_voucher_no,
				dcc.dt_voucher_date,
				dcc.vc_chq_no,
				dcc.dt_chq_date,
				dcc.nu_amount,
				dcc.vc_tran_type,
				nu_cust_bank vc_card_type,
				dcc.br_code
				from sales.dt_cheque_clear dcc, sales.hd_sale_adv_ord hsao, sales.dt_sale_adv_pay dsao
				where dcc.ch_auth_flag = 'N'
				and dcc.dt_chq_date between TO_DATE('$fromDate','MM/DD/YYYY') and TO_DATE('$toDate','MM/DD/YYYY') 
				and dcc.br_code = '$brCode'
				and dcc.vc_comp_code = '$companyCode'
				and dcc.vc_voucher_no = hsao.vc_adv_ord_no 
				and dcc.dt_voucher_date = hsao.dt_adv_ord_date
				and dcc.vc_voucher_no = dsao.vc_adv_ord_no 
				and dcc.dt_voucher_date = dsao.dt_adv_ord_date
				and hsao.ch_cancel_flag = 'N'
				group by  dcc.vc_cust_fname||' '||dcc.vc_cust_lname ,
				decode(dcc.vc_tran_type,'KALPVRUKSHA',dcc.vc_cust_id,null),
				dcc.vc_voucher_no,
				dcc.dt_voucher_date,
				dcc.vc_chq_no,
				dcc.dt_chq_date,
				dcc.nu_amount,
				dcc.vc_tran_type,
				nu_cust_bank,
				dcc.br_code
				union all
				select dcc.vc_cust_fname||' '||dcc.vc_cust_lname customer_name,
				decode(dcc.vc_tran_type,'KALPVRUKSHA',dcc.vc_cust_id,null) vc_cust_id,
				dcc.vc_voucher_no,
				dcc.dt_voucher_date,
				dcc.vc_chq_no,
				dcc.dt_chq_date,
				dcc.nu_amount,
				dcc.vc_tran_type,
				id1.vc_card_type,
				dcc.br_code
				from sales.dt_cheque_clear dcc, sales.in_dt id1
				where dcc.ch_auth_flag = 'N'
				and dcc.dt_chq_date between TO_DATE('$fromDate','MM/DD/YYYY') and TO_DATE('$toDate','MM/DD/YYYY')  
				and dcc.br_code = '$brCode' 
				and dcc.vc_comp_code = '$companyCode'
				and dcc.br_code = id1.br_code
				and dcc.vc_voucher_no = id1.inward_id
				and dcc.dt_voucher_date = id1.inward_date
				and id1.vc_rcpt_settle = 'N'
				group by dcc.vc_cust_fname||' '||dcc.vc_cust_lname,
				decode(dcc.vc_tran_type,'KALPVRUKSHA',dcc.vc_cust_id,null) ,
				dcc.vc_voucher_no,
				dcc.dt_voucher_date,
				dcc.vc_chq_no,
				dcc.dt_chq_date,
				dcc.nu_amount,
				dcc.vc_tran_type,
				id1.vc_card_type,
				dcc.br_code
				"""
			Sql sqlObj = new Sql(dataSource)
			data = sqlObj.rows(chkToBeDepQry)		
		}catch(Exception exception){
			log.info("Exception in ReportService getChequeToBeDepositReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return data
	}
	
	/**
	 * Method Name: getPayModeDataForAdvSetlReport
	 * Description: gets paymode list
	 *
	 * @return : list of payModeList
	 */
	def getPayModeDataForAdvSetlReport(){
		try{
			query =
				"""
				select distinct vc_pay_mode,vc_pay_type from mst_pay
				union ALL
				SELECT 'ALL','ALL' FROM DUAL
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)	
		}catch(Exception exception){
			log.info("Exception in ReportService getPayModeDataForAdvSetlReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getDescriptionDataForAdvSetlReport
	 * Description: gets description list
	 *
	 * @return : list of description
	 */
	def getDescriptionDataForAdvSetlReport(){
		try{
			query =
				"""
				SELECT description,flex_value_meaning FROM fnd_flex_values_vl
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getDescriptionDataForAdvSetlReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getBranchWiseSummaryReport
	 * Description: gets branchwiseSummary list
	 *
	 * @return : list of branch wise summary
	 */
	def getBranchWiseSummaryReport(fromDate, toDate,schemeName,companyCode, brCode){
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					SELECT a.BR_CODE,' '||d.BR_NAME br_name, SUM(a.AMOUNT)Amt 
					FROM IN_DT a,CUST_MST b,SCHEME_MST c,BR_MST_Tab d
					WHERE a.INWARD_DATE BETWEEN :fromdate AND :todate
					AND a.CUST_ID = b.CUST_ID
					AND a.SCHEME_NO = c.SCHEME_NO
					AND a.BR_CODE = d.BR_CODE
					AND a.BR_CODE = DECODE(:P_Brcode,'ALL',a.BR_CODE,:P_Brcode)
					AND a.SCHEME_NO = DECODE(:P_Schno,'ALL',a.SCHEME_NO,:P_Schno)
					---AND NVL(a.amount,0)>0
					and a.scheme_no is not null
					GROUP BY a.BR_CODE,d.BR_NAME, a.SCHEME_NO,c.SCHEME_NAME
					ORDER BY 1,2
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getGiftVoucherActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getMiscellaneousRecieptsReport
	 * Description: gets MiscellaneousReciepts list
	 *
	 * @return : list of MiscellaneousReciepts
	 */
	def getMiscellaneousRecieptsReport(fromDate,toDate,category,companyCode,brCode){
		def result1,flexVal
		try{
			query1 = "select FLEX_VALUE FROM fnd_flex_values_vl WHERE DESCRIPTION='$category'"
			Sql sql1 = new Sql(dataSource)
			result1 = sql1.rows(query1)
			result1.each {
				flexVal= (it.values()[0])
			}
			query =
				"""
				select vc_voucher_no,
				dt_voucher_date,
				nu_account_code,
				b.vc_pay_type Pay_type,
				nu_amount,
				vc_narration,
				a.br_code,
				vc_category,
				vc_category||vc_sale_voucher_no voucher_no,
				dt_sale_voucher_date 
				from sales.check_bounce a, mst_pay b
				where vc_comp_code = '$companyCode'
				and a.br_code = b.br_code
				and a.ch_pay_type = b.vc_pay_mode
				and b.ch_tran_type = 'MR'
				and a.br_code = '$brCode'
				and dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
				and nvl(vc_category,'X') = decode('$flexVal','ALL',nvl(vc_category,'X'),'$flexVal')
				order by dt_voucher_date
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getMiscellaneousRecieptsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getCashCounterReport
	 * Description: gets CashCounterReport list
	 *
	 * @return : list of CashCounterReport
	 */
	def getCashCounterReport(fromDate,companyCode,brCode,machineId,machineNo){
		def catList  = []
		def sbGrWtList  = []
		def sbNtWtList  = []
		def sbAmtList  = []
		def srGrWtList  = []
		def srNtWtList  = []
		def srAmtList  = []
		def result1
		def resultList  = []
		try{			
			query =
				"""
				SELECT a.vc_category,
				sum(decode(b.vc_sale_type,'K',-(NVL(b.nu_amount,0) - NVL(b.nu_discount,0)),(NVL(b.nu_amount,0) - NVL(b.nu_discount,0)))) nu_amount ,
				sum(decode(b.vc_sale_type,'K',-b.nu_gross_wtt,b.nu_gross_wtt)) nu_gross_wtt,
				sum(decode(b.vc_sale_type,'K',-b.nu_nett_wtt,b.nu_nett_wtt)) nu_nett_wtt
				FROM sales.hd_cash a, sales.dt_cash b 
				WHERE a.vc_voucher_no = b.vc_voucher_no 
				AND a.dt_voucher_date=b.dt_voucher_date 
				AND SUBSTR(a.vc_voucher_no,length(a.br_code)+1,2) IN ('SI' ,'CI','EI','FI') 
				AND a.ch_cancel_flag='N' 
				AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY') 
				AND nu_mc_id = DECODE( '$machineId','ALL',nu_mc_id,'$machineId')
				AND nu_mc_no = DECODE( '$machineNo','ALL',nu_mc_no,'$machineNo') 
				AND a.vc_comp_code = '$companyCode' 
				AND b.vc_comp_code = a.vc_comp_code
				AND a.vc_category = b.vc_category 
				AND a.br_code='$brCode' 
				AND b.br_code=a.br_code 
				AND b.vc_sale_type not in('SS')
				group by a.vc_category
				ORDER BY 2
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
			result.each{
				catList.add(it.values()[0])	
				sbAmtList.add(it.values()[1])
				sbGrWtList.add(it.values()[2])
				sbNtWtList.add(it.values()[3])
			}
			resultList.add(result)
			for(int i=0;i<catList.size();i++){
				def cat = catList[i]
				query1 =
				"""
					select a.VC_CATEGORY as cat,sum(nvl(nu_gross_wtt,0)) as gwt,sum(nu_nett_wtt) as nwt,
					  sum(nvl(nu_amount,0)-nvl(nu_discount,0)) as amt
					  from hd_cash a,dt_cash b
					  where a.vc_voucher_no=b.vc_voucher_no and
					  a.dt_voucher_date=b.dt_voucher_date and
					  a.vc_category=b.vc_category and 
					  a.vc_category='$cat' and 
					  a.vc_comp_code=b.vc_comp_code and
					  a.vc_comp_code='$companyCode' and
					  a.dt_voucher_date=TO_DATE('$fromDate','MM/DD/YYYY')  and
					  a.nu_mc_id='$machineId' and
					  a.nu_mc_no='$machineNo' and
					  a.ch_cancel_flag='N'
					  and a.br_code='$brCode'
					  and a.br_code=b.br_code
					  and b.vc_sale_type = 'SS'
					  group by a.vc_category
					  ORDER BY 2
					"""
				Sql sql1 = new Sql(dataSource)
				result1 = sql1.rows(query1)
				resultList.add(result1)
			}			
			
			result1.each{		
				srGrWtList.add(it.values()[1])
				srNtWtList.add(it.values()[2])
				srAmtList.add(it.values()[3])
			}
			def totAmtLst = (sbAmtList - srAmtList)
			def totGrWtLst = (sbGrWtList - srGrWtList)
			def totNtLst = (sbNtWtList - srNtWtList)
			resultList.add(totAmtLst)
			resultList.add(totGrWtLst)
			resultList.add(totNtLst)
		}catch(Exception exception){
			log.info("Exception in ReportService getCashCounterReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultList
	}
	
	/**
	 * Method Name: getErrorLogReport
	 * Description: gets ErrorLogReport list
	 *
	 * @return : list of ErrorLogReport
	 */
	def getErrorLogReport(fromDate, toDate,companyCode, brCode, location, reportType){
		def salesStgQry,salesStgRslt,salesUnStgQry,salesUnStgRslt,advStgQry,advStgRslt,advUnStgQry,advUnStgRslt,purStgQry,purStgRslt,purUnStgQry,purUnStgRslt
		def selDate,str,kpStgQry,kpStgRslt,kpUnStgQry,kpUnStgRslt,chequeQry,chequeRslt,result1
		String output	
		def dtList  = []
		def resultList  = []
		try{
			query =
				"""
				select x.br_code, x.vou_date dt_voucher_date,  y.br_name
				from 
				(
				select distinct(dt_voucher_date) Vou_date,br_code
				from sales.hd_cash
				where ch_cancel_flag = 'N'
				and br_code = decode('$brCode','ALL',br_code,'$brCode')
				and dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
				union
				select distinct(dt_adv_ord_date)Vou_date,br_code
				from sales.hd_sale_adv_ord
				where ch_cancel_flag = 'N'
				and br_code = decode('$brCode','ALL',br_code,'$brCode')
				and dt_adv_ord_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
				union
				select distinct(dt_bill_date)Vou_date,br_code
				from sales.hd_bill
				where ch_cancel_flag = 'N'
				and br_code = decode('$brCode','ALL',br_code,'$brCode')
				and dt_bill_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
				union
				select distinct(inward_date)Vou_date,br_code
				from sales.in_dt
				where br_code = decode('$brCode','ALL',br_code,'$brCode')
				and inward_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
				) x, sales.br_mst_tab y
				where x.br_code = y.br_code
				and y.vc_comp_code = '$companyCode'
				order by x.vou_date
				"""

			sql = new Sql(dataSource)
			result = sql.rows(query)
			result.each{	
				dtList.add(it.values()[1])
			}
			if(reportType=='P'){
				for(int i=0;i<dtList.size();i++){	
					def dataMap = [:]
					output = dtList[i].toString().substring(0, 10);
					dataMap.put("dt", output)
					salesStgQry = """
						select count(*) as salesstg
						from sales.hd_cash
						where ch_stage = 'Y'
						and ch_cancel_flag = 'N'
						and dt_voucher_date = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""					
					sql1 = new Sql(dataSource)
					salesStgRslt = sql1.rows(salesStgQry)
					salesStgRslt.each {
						dataMap.put("SALESSTG", it.values()[0])
					}				
					salesUnStgQry = """
						select count(*) as saleunsstg
						from sales.hd_cash
						where ch_stage = 'N'
						and ch_cancel_flag = 'N'
						and dt_voucher_date = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""										
					Sql sql2 = new Sql(dataSource)
					salesUnStgRslt = sql2.rows(salesUnStgQry)					
					salesUnStgRslt.each {
						dataMap.put("SALESUNSTG", it.values()[0])
					}
					advStgQry = """
						select count(*) as advstg
						from sales.hd_sale_adv_ord
						where ch_stage = 'Y'
						and ch_cancel_flag = 'N'
						and dt_adv_ord_date = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""					
					Sql sql3 = new Sql(dataSource)					
					advStgRslt = sql3.rows(advStgQry)
					advStgRslt.each {
						dataMap.put("ADVSTG", it.values()[0])
					}
					advUnStgQry = """
						select count(*) as advunstg
						from sales.hd_sale_adv_ord
						where ch_stage = 'N'
						and ch_cancel_flag = 'N'
						and dt_adv_ord_date  = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""					
					Sql sql4 = new Sql(dataSource)
					advUnStgRslt = sql4.rows(advUnStgQry)
					advUnStgRslt.each {
						dataMap.put("ADVUNSTG", it.values()[0])
					}
					purStgQry = """
						select count(*) as purstg
						from sales.hd_bill
						where ch_stage = 'Y'
						and vc_cancel_flag = 'N'
						and dt_bill_date = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""					
					Sql sql5 = new Sql(dataSource)
					purStgRslt = sql5.rows(purStgQry)
					purStgRslt.each {
						dataMap.put("PURSTG", it.values()[0])
					}
					purUnStgQry = """
						select count(*) as purunstg
						from sales.hd_bill
						where ch_stage = 'N'
						and vc_cancel_flag = 'N'
						and dt_bill_date = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""					
					Sql sql6 = new Sql(dataSource)
					purUnStgRslt = sql6.rows(purUnStgQry)
					purUnStgRslt.each {
						dataMap.put("PURUNSTG", it.values()[0])
					}
					kpStgQry = """
						select count(*) as kpstg
						from sales.in_dt
						where ch_stage = 'Y'
						and inward_date = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""					
					Sql sql7 = new Sql(dataSource)
					kpStgRslt = sql7.rows(kpStgQry)
					kpStgRslt.each {
						dataMap.put("KPSTG", it.values()[0])
					}
					kpUnStgQry = """
						select count(*) as kpunstg
						from sales.in_dt
						where ch_stage = 'N'
						and inward_date = TO_DATE('$output','YYYY/MM/DD')
						and br_code = decode('$brCode','ALL',br_code,'$brCode')
					"""					
					Sql sql8 = new Sql(dataSource)
					kpUnStgRslt = sql8.rows(kpUnStgQry)
					kpUnStgRslt.each {
						dataMap.put("KPUNSTG", it.values()[0])
					}
					
					chequeQry = """
						SELECT SUM (cnt) as cpchqclr
						  FROM (SELECT COUNT (*) cnt                               
						          FROM sales.hd_sale_adv_ord a, sales.dt_sale_adv_pay b, sales.dt_cheque_clear c
						         WHERE a.br_code = b.br_code
						           AND a.vc_adv_ord_no = b.vc_adv_ord_no
						           AND a.vc_adv_ord_no = c.vc_voucher_no
						           AND a.br_code = c.br_code
						           AND a.dt_adv_ord_date = b.dt_adv_ord_date
						           AND a.ch_stage = 'N'
						           AND a.ch_cancel_flag = 'N'
						           AND b.ch_pay_mode = 'CQ'
						           AND c.dt_clear_date = TO_DATE('$output','YYYY/MM/DD')
						           AND a.br_code = DECODE ('$brCode', 'ALL', a.br_code, '$brCode')
						        UNION
						        SELECT COUNT (*) cnt
						          FROM sales.in_dt a, sales.dt_cheque_clear b
						         WHERE a.cust_id = b.vc_cust_id
						           AND a.inward_id = b.vc_voucher_no
						           AND a.pay_date <> b.dt_chq_date
						           AND a.ch_stage = 'N'
						           AND b.dt_clear_date = TO_DATE('$output','YYYY/MM/DD')
						           AND a.br_code = DECODE ('$brCode', 'ALL', a.br_code, '$brCode')
						        UNION
						        SELECT COUNT (*) cnt
						          FROM sales.in_dt a, sales.dt_cheque_clear b
						         WHERE a.cust_id = b.vc_cust_id
						           AND a.inward_id = b.vc_voucher_no
						           AND a.pay_date = b.dt_chq_date
						           AND a.ch_stage = 'N'
						           AND b.dt_clear_date = TO_DATE('$output','YYYY/MM/DD')
						           AND a.br_code = DECODE ('$brCode', 'ALL', a.br_code, '$brCode'))
					"""					
					Sql sql9 = new Sql(dataSource)
					chequeRslt = sql8.rows(chequeQry)
					chequeRslt.each {
						dataMap.put("cpchqclr", it.values()[0])
					}
					resultList.add(dataMap)
				}
			}			
		}catch(Exception exception){
			log.info("Exception in ReportService getErrorLogReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultList
	}
	
	/**
	 * Method Name: franchiseePurchaseCheckReportService
	 * Description: gets franchiseePurchaseCheck list
	 *
	 * @return : list of franchisee purchase Report
	 */
	def franchiseePurchaseCheckReportService(fromDate, toDate,category,companyCode, brCode) {
		
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					select xfh.dt_voucher_date,xfh.header_id,xfh.fr_NAME,xfh.CH_STAGE,xfh.SALE_TYPE,SUM(xfl.SELLING_PRICE) SELLING,
					xfl.purity,sum(xfl.nang) nang,sum(xfl.pieces) pcs,sum(xfl.GROSS_WEIGHT) grt,sum(xfl.NET_WEIGHT) net,sum(xfl.CARAT_WEIGHT) crt
					,xfh.vc_category
					from xxtbz_fr_headers xfh, xxtbz_fr_lines xfl
					where xfh.header_id = xfl.header_id
					and nvl(xfh.cancel_flag,'N') = 'N'
					and xfh.vc_voucher_no is not null
					and xfh.dt_voucher_date >= to_date('01-JAN-2016','DD-MON-RRRR')
					and xfh.dt_voucher_date < to_date('20-JAN-2016','DD-MON-RRRR')+1 --23-SEP-2015
					and xfh.vc_category = decode('ALL','ALL',xfh.vc_category,'ALL')
					group by xfl.purity, xfh.dt_voucher_date,xfh.header_id,xfh.fr_NAME,xfh.CH_STAGE,xfh.SALE_TYPE,xfh.vc_category

				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService franchiseePurchaseCheckReportService method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: franchiseeStonePurchaseCheckReportService
	 * Description: gets franchiseeStonePurchaseCheckReport list
	 *
	 * @return : list of franchisee purchase Stone Report
	 */
	def franchiseeStonePurchaseCheckReportService(fromDate, toDate,category,companyCode, brCode) {
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					select 
					xfh.header_id,xcd.stone_type,sum(xcd.CARAT_WEIGHT) stonecrt,sum(xcd.PIECES) stonepcs
					from tbzcustom.xxtbz_fr_headers xfh, tbzcustom.xxtbz_fr_lines xfl,apps.xxtbz_component_detail_v_new xcd
					where xfh.header_id = xfl.header_id
					and xfl.lot_number = xcd.LOT_NUMBER(+)
					and nvl(xfh.cancel_flag,'N') = 'N'
					and xfh.vc_voucher_no is not null
					--and xfh.header_id = 65181162
					and xfh.vc_category = xfh.vc_category
					and xfh.dt_voucher_date >= to_date('01-JAN-2016','DD-MoN-RRRR')
					and xfh.dt_voucher_date < to_date('20-JAN-2016','DD-MON-RRRR')+1
					and xcd.stone_type is not null
					group by xcd.stone_type,xfh.header_id
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService franchiseeStonePurchaseCheckReportService method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}

	/**
	 * Method Name: franchiseeIdealStockReport
	 * Description: gets franchiseeIdealStockReport list
	 *
	 * @return : list of franchisee Ideal stock Report
	 */
	def franchiseeIdealStockReport(fromDate, toDate,category,companyCode, brCode) {
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					(select xim.product item,
					to_number(bmt.ebs_org_map) loc,
					mdrn.nu_from_no,
					mdrn.nu_to_no,
					to_number(mdrn.vc_varity_code) ideal,
					0 pcs,
					0 crt,
					0-(to_number(mdrn.vc_varity_code)) bal
					from xxtbz_item_master xim,
					sales.mst_dia_range mdrn,
					sales.br_mst_tab bmt
					where xim.item_category = 'D'
					and nvl(xim.purity,'X') = nvl(:p_purity,nvl(xim.purity,'X'))
					and xim.product = nvl(:p_item,xim.product)
					and bmt.br_code = nvl(:br_code,bmt.br_code)
					and xim.primary_uom_code = 'CT'
					and bmt.br_code = mdrn.br_code
					and xim.product = mdrn.vc_item_code
					group by xim.product, to_number(bmt.ebs_org_map), mdrn.nu_from_no, mdrn.nu_to_no, to_number(mdrn.vc_varity_code)
					minus
					select moqd.product item,
					to_number(bmt.ebs_org_map) loc,
					mdrn.nu_from_no,
					mdrn.nu_to_no,
					to_number(mdrn.vc_varity_code) ideal,
					0 pcs,
					0 crt,
					0 -(to_number(mdrn.vc_varity_code)) bal
					from apps.tbz_pos_itemcode_label_v moqd,
					sales.mst_dia_range mdrn, 
					sales.br_mst_tab bmt
					where  moqd.PRECIOUS_METAL_STONES = 'D'
					and nvl(moqd.purity,'X') = nvl(:p_purity,nvl(moqd.purity,'X'))
					and moqd.product = nvl(:p_item,moqd.product) 
					and moqd.org_id =nvl(:br_code,moqd.org_id)
					and (moqd.locator like '%CS%' or moqd.locator like '%CAS%')
					and moqd.uom = 'CT'
					and bmt.br_code = mdrn.br_code
					and moqd.product = mdrn.vc_item_code (+)
					--and mdrn.nu_from_no = '15.01'
					--and moqd.product='EG'
					and moqd.NET_WEIGHT between mdrn.nu_from_no and mdrn.nu_to_no
					group by moqd.product, to_number(bmt.ebs_org_map), mdrn.nu_from_no, mdrn.nu_to_no, to_number(mdrn.vc_varity_code)
					union all
					(select moqd.product item,
					to_number(bmt.ebs_org_map) loc,
					mdrn.nu_from_no,
					mdrn.nu_to_no,
					to_number(mdrn.vc_varity_code) ideal,
					sum(round(moqd.PIECES)) pcs,
					sum(moqd.CARET_WT) crt,
					sum(moqd.PIECES)-(to_number(mdrn.vc_varity_code)) bal
					from apps.tbz_pos_itemcode_label_v moqd,
					sales.mst_dia_range mdrn, 
					sales.br_mst_tab bmt
					where  moqd.PRECIOUS_METAL_STONES = 'D'
					and nvl(moqd.purity,'X') = nvl(:p_purity,nvl(moqd.purity,'X'))
					and moqd.product = nvl(:p_item,moqd.product)
					and moqd.org_id = nvl(:br_code,moqd.org_id)
					and (moqd.locator like '%CS%' or moqd.locator like '%CAS%')
					and moqd.uom='CT'
					and bmt.br_code = mdrn.br_code
					and moqd.product = mdrn.vc_item_code (+)
					and moqd.CARET_WT between mdrn.nu_from_no and mdrn.nu_to_no
					group by product, to_number(bmt.ebs_org_map), mdrn.nu_from_no, mdrn.nu_to_no, to_number(mdrn.vc_varity_code)))
					order by 1, 2, 3, 4, 5

				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService franchiseeIdealStockReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
		
	}
	
	/**
	 * Method Name: franchiseeWeightWiseStockReport
	 * Description: gets franchiseeWeightWiseStock list
	 *
	 * @return : list of franchisee weight stock Report
	 */
	def franchiseeWeightWiseStockReport() {
		def str
		//def brCode = session?.brCode
		try{
			str =
				"""
					select 
				       v.product_category vc_item_code, 
				       v.style vc_sub_code,
				       t.description ,
				       sum(nvl(v.nang,0)) NANG,
				       sum(nvl(v.pieces,0)) pcs,
				       sum(nvl(v.net_weight,0)) WTT,
				       sum(nvl(v.caret_wt,0)) CARR, 
				       mrr.nu_range_no ,
				       sum(nvl(v.nang,0))||'/'||sum(nvl(v.pieces,0)) sho_pcs,
				       branch_name(v.org_id ) name,
				       v.org_id operating_unit
				     from apps.tbz_pos_itemcode_label_v v, 
				       sales.mst_reorder_range mrr, 
				       apps.FND_FLEX_VALUES fv,
				       apps.fnd_flex_values_tl t
				 	where v.org_id = mrr.br_code
				       and v.precious_metal_stones = mrr.vc_category
				       and v.product_category = mrr.vc_item_code
				       --and v.style=mrr.vc_sub_code
				       and v.purity = mrr.vc_purity
				       and v.subinventory_code like '%-CS%'
				       and v.net_weight  <= mrr.nu_range_no
				       and v.net_weight >(select max (in_mrr.nu_range_no)
                     from sales.mst_reorder_range in_mrr
                    where in_mrr.br_code = mrr.br_code
                      and in_mrr.vc_category = mrr.vc_category
                      and in_mrr.vc_item_code = mrr.vc_item_code
                      and in_mrr.vc_purity = mrr.vc_purity
                      and in_mrr.nu_range_no < mrr.nu_range_no) 
					and v.org_id = NVL(:br_code,v.org_id)                       
				       and v.precious_metal_stones = nvl('ALL',v.precious_metal_stones) 
				       and v.product_category = nvl(:p_item,v.product_category) 
				       and v.style = nvl(:p_sub,v.style)
				       and v.variety = nvl(:p_var,v.variety)
				       and v.purity = nvl(:p_purity ,v.purity) 
				       and fv.FLEX_VALUE_SET_ID =1014193 -- FLEX_VALUE_SET_ID for 'TBZ_Item_Style'
				       and fv.flex_value_id = t.flex_value_id
				       and fv.flex_value = v.style
				       and t.LANGUAGE = USERENV ('LANG')          
					group by v.product_category,v.style,mrr.nu_range_no,branch_name(v.org_id ),v.org_id,t.description
					order by mrr.nu_range_no
				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService franchiseeIdealStockReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
		
	}
	/**
	 * Method Name: getLabelWiseStockReport
	 * Description: gets Labelwise stock list
	 *
	 * @return : list of branch wise summary
	 */
	def getLabelWiseStockReport(preciousMetalStones){
		def str
		
		
		//def brCode = session?.brCode
		try{
			str =
				"""
				    SELECT a.precious_metal_stones, a.product_category,a.label_number,a.STYLE,a.SUB_STYLE,a.METAL_COLOUR, a.purity ,a.locator,
                     nvl(a.nang,0) nang1, nvl(a.pieces,0) pcs1, nvl(a.gross_weight,0) grswt1, nvl(a.net_weight,0) netwt1,nvl(a.caret_wt,0) crtwt1,
                     nvl(b.STONE_TYPE,0) STONE_TYPE,
                     nvl(b.PIECES,0) pcs_st ,nvl(b.CARAT_WEIGHT,0) crtwt_st
                     FROM apps.tbz_pos_itemcode_label_v a,apps.xxtbz_component_detail_v_new b 
                       WHERE precious_metal_stones = '$preciousMetalStones.category'
                        AND a.label_number=b.lot_number
                        AND product_category NOT IN ('DH', 'DM', 'OD', 'OG', 'UL')
                        ORDER BY purity, product_category

				"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getGiftVoucherActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getLabelWiseStockSumReport
	 * Description: gets Label wise stone summary list
	 *
	 * @return : list of branch wise summary
	 */
	def getLabelWiseStockSumReport(preciousMetalStones){
		def str
		
		
		//def brCode = session?.brCode
		try{
			str =
				"""
				    SELECT  b.stone_type,sum(nvl(b.pieces,0)) pcs_st ,sum(nvl(b.carat_weight,0)) crtwt_st
				     FROM apps.tbz_pos_itemcode_label_v a,apps.xxtbz_component_detail_v_new b 
  				      WHERE a.label_number=b.lot_number AND
				       a.product_category NOT IN ('DH', 'DM', 'OD', 'OG', 'UL')
				    GROUP BY b.stone_type


				"""
				
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getGiftVoucherActiveReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getCashAnalysisReport
	 * Description: gets CashAnalysisReport data
	 *
	 * @return : list of cash analysis data
	 */
	def getCashAnalysisReport(fromDate,brCode,companyCode){
		def str
		try{
			str =
				"""
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getScanningReport
	 * Description: gets ScanningReport data
	 *
	 * @return : list of ScanningReport data
	 */
	def getMainScanningReport(mainSca,session){
		def category,purity,itemCode
		def x = mainSca
		try{
			category = x?.hidCat
			query =
				"""
				select 'Label Not Found In Item Master', vc_label_no, decode(xln.Label_number,vc_label_no,'Parent Label') label_type 
				from sales.mst_label_rep mlr, apps.tbz_pos_itemcode_label_v xln
				where mlr.br_code = '$session.brCode'
				and mlr.vc_label_no = xln.Label_number (+)
				and nvl(mlr.vc_category,'X') <> '$category'	
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
			/*mainResult.each {
				def dataMap = [:]
				dataMap.put("LABELNOTFOUNDINITEMMASTER", it.values()[0])
				dataMap.put("MAIN_LABELNO", it.values()[1])
				dataMap.put("MAIN_LABELTYPE", it.values()[2])
				resultList.add(dataMap)
			}
			/*query1 =
			"""
				select vc_label_no, vc_purity, vc_item_code, nu_nang, nu_nang_pcs, nu_gross_wtt, nu_nett_wtt, nu_carrat_wtt, 
				nu_making_charges, vc_kar_code, vc_category, vc_deptt
				from mst_label_rep
				where br_code = '$brCode'
				and vc_category = '$category'
			"""
			//and vc_deptt not like '%'||:locator1||'%'(ROHIL SAID WILL SEE LATER ABOUT 'locator1' PARAM)
			Sql sql1 = new Sql(dataSource)
			deptResult = sql1.rows(query1)
			deptResult.each {
				def dataMap = [:]
				dataMap.put("DEPT_LABEL_NO", it.values()[0])
				dataMap.put("DEPT_VC_PURITY", it.values()[1])
				dataMap.put("DEPT_NU_NANG_PCS", it.values()[4])			
				dataMap.put("DEPT_NU_GROSS_WTT", it.values()[5])				
				dataMap.put("DEPT_NU_NETT_WTT", it.values()[6])				
				dataMap.put("DEPT_NU_CARRAT_WTT", it.values()[7])				
				dataMap.put("DEPT_NU_MAKING_CHARGES", it.values()[8])
				dataMap.put("DEPT_VC_KAR_CODE", it.values()[9])
				resultList.add(dataMap)
			}
			/*def query2 =
			"""
				select vc_label_no, vc_purity, vc_item_code, nu_nang, nu_nang_pcs, nu_gross_wtt, nu_nett_wtt, nu_carrat_wtt, 
				nu_making_charges, vc_kar_code, vc_category, vc_deptt
				from mst_label_rep
				where br_code = '$brCode'
				and vc_category = '$category'
				and vc_purity not like '%'||'$purity'||'%'
			"""
			Sql sql2 = new Sql(dataSource)
			purResult = sql2.rows(query2)
			purResult.each {
				def dataMap = [:]
				dataMap.put("PUR_LABEL_NO", it.values()[0])
				dataMap.put("PUR_VC_PURITY", it.values()[1])
				dataMap.put("PUR_NU_NANG_PCS", it.values()[4])
				dataMap.put("PUR_NU_GROSS_WTT", it.values()[5])
				dataMap.put("PUR_NU_NETT_WTT", it.values()[6])
				dataMap.put("PUR_NU_CARRAT_WTT", it.values()[7])
				dataMap.put("PUR_NU_MAKING_CHARGES", it.values()[8])
				dataMap.put("PUR_VC_KAR_CODE", it.values()[9])
				resultList.add(dataMap)
			}
			/*def query3 =
			"""
				select vc_label_no, vc_purity, vc_item_code, nu_nang, nu_nang_pcs, nu_gross_wtt, nu_nett_wtt, nu_carrat_wtt, 
				nu_making_charges, vc_kar_code, vc_category, vc_deptt
				from mst_label_rep
				where br_code = '$brCode'
				and vc_category = '$category'
				and vc_item_code not like '%'||'$itemCode'||'%'
			"""
			Sql sql3= new Sql(dataSource)
			itemResult = sql3.rows(query3)
			itemResult.each {
				def dataMap = [:]
				dataMap.put("ITEM_LABEL_NO", it.values()[0])
				dataMap.put("ITEM_VC_PURITY", it.values()[1])
				dataMap.put("ITEM_NU_NANG_PCS", it.values()[4])
				dataMap.put("ITEM_NU_GROSS_WTT", it.values()[5])
				dataMap.put("ITEM_NU_NETT_WTT", it.values()[6])
				dataMap.put("ITEM_NU_CARRAT_WTT", it.values()[7])
				dataMap.put("ITEM_NU_MAKING_CHARGES", it.values()[8])
				dataMap.put("ITEM_VC_KAR_CODE", it.values()[9])
				resultList.add(dataMap)
			}
			/*def query4 =
			"""
				select vc_label_no, vc_purity, vc_item_code, nu_nang, nu_nang_pcs, nu_gross_wtt, nu_nett_wtt, nu_carrat_wtt, 
				nu_making_charges, vc_kar_code, vc_category, vc_deptt
				from mst_label_rep
				where br_code = '$brCode'
				and vc_category = '$category'
				and vc_item_code not like '%'||'$itemCode'||'%'
			"""
			Sql sql4= new Sql(dataSource)
			physicalResult = sql4.rows(query4)
			physicalResult.each {
				def dataMap = [:]
				dataMap.put("PHY_ITEM_LABEL_NO", it.values()[0])
				dataMap.put("PHY_ITEM_VC_PURITY", it.values()[4])
				dataMap.put("PHY_ITEM_NU_NANG_PCS", it.values()[6])
				dataMap.put("PHY_ITEM_NU_GROSS_WTT", it.values()[7])
				dataMap.put("PHY_ITEM_NU_NETT_WTT", it.values()[8])
				dataMap.put("PHY_ITEM_NU_CARRAT_WTT", it.values()[9])
				dataMap.put("PHY_ITEM_NU_MAKING_CHARGES", it.values()[11])
				dataMap.put("PHY_ITEM_VC_KAR_CODE", it.values()[10])
				resultList.add(dataMap)
			}		
			*/
		}catch(Exception exception){
			log.info("Exception in ReportService getScanningReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		//return resultList
		return result
	}
			
	/**
	 * Method Name: getPhyScaReport
	 * Description: gets physical List data
	 *
	 * @return : physical list
	 */
	def getPhyScaReport(phySca,session){
		def category,purity,itemCode
		def x = phySca
		try{			
			category = x?.hidCat
			purity = x?.purity
			itemCode = x?.itemCode
			query =
			"""
				select moqd.label_number, xim.item_category, xim.product, mil.segment1, moqd.purity, moqd.nang, moqd.PIECES pieces, 
				moqd.GROSS_WEIGHT gross_weight, moqd.NET_WEIGHT net_weight,moqd.CARET_WT carat_weight, 
				moqd.karigar_code karigar_code, moqd.selling_price total_amount_sp
				from apps.tbz_pos_itemcode_label_v moqd, apps.mtl_item_locations mil, 
				xxtbz_item_master xim, xxtbz_organization_information xoi
				where moqd.inventory_item_id = xim.inventory_item_id
				and moqd.locator = mil.segment1
				and moqd.subinventory_code like '%CS%'
				and xoi.organization_id = moqd.organization_id
				and xoi.operating_unit = '$session.brCode'
				and xim.item_category = '$category'
				and xim.product like '%'||'$itemCode'||'%'
				and xim.purity like '%'||'$purity'||'%'
				and not exists (select 'Yes'
			    from mst_label_rep
			    where vc_label_no = moqd.label_number
			    and br_code = xoi.operating_unit)
			"""
			
			/*select moqd.label_number, xim.item_category, xim.product, mil.segment1, moqd.purity, moqd.nang, moqd.PIECES pieces,
			moqd.GROSS_WEIGHT gross_weight, moqd.NET_WEIGHT net_weight,moqd.CARET_WT carat_weight,
			moqd.karigar_code karigar_code, moqd.selling_price total_amount_sp
			from apps.tbz_pos_itemcode_label_v moqd, apps.mtl_item_locations mil,
			xxtbz_item_master xim, xxtbz_organization_information xoi--, xxtbz_lot_numbers xln
			where moqd.inventory_item_id = xim.inventory_item_id
			and moqd.locator = mil.segment1
			--and moqd.lot_number = xln.lot_number
			and moqd.subinventory_code like '%CS%'
			and xoi.organization_id = moqd.organization_id
			and xoi.operating_unit = :br_code
			and xim.item_category = :category
			and xim.product like '%'||:item||'%'
			and xim.purity like '%'||:purity||'%'
			and case when mil.segment1 like '%Sets%' then replace(mil.segment1,'Sets','SEts') --for Sets Item Code
			when mil.segment1 not like '%'||xim.product||'%' then xim.product
				   else mil.segment1 end like '%'||:locator1||'%'
			and not exists (select 'Yes'
				from mst_label_rep
				where vc_label_no = moqd.label_number
				and br_code = xoi.operating_unit);*/
			
			
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getPhyScaReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getDeptScaReport
	 * Description: gets dept List data
	 *
	 * @return : dept list
	 */
	def getDeptScaReport(deptSca,session){
		def category,purity,itemCode
		def x = deptSca
		try{			
			category = x?.hidCat
			query =
			"""
				select vc_label_no, vc_purity, vc_item_code, nu_nang, nu_nang_pcs, nu_gross_wtt, nu_nett_wtt, nu_carrat_wtt, 
				nu_making_charges, vc_kar_code, vc_category, vc_deptt
				from mst_label_rep
				where br_code = '$session.brCode'
				and vc_category = '$category'
			"""
			//and vc_deptt not like '%'||:locator1||'%'   (ROHIL SAID WILL SEE LATER ABOUT 'locator1' PARAM)
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getDeptScaReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPurScaReport
	 * Description: gets purity List data
	 *
	 * @return : purity list
	 */
	def getPurScaReport(purSca,session){
		def category,purity,itemCode
		def x = purSca
		try{			
			category = x?.hidCat
			purity = x?.purity
			query =
			"""
				select vc_label_no, vc_purity, vc_item_code, nu_nang, nu_nang_pcs, nu_gross_wtt, nu_nett_wtt, nu_carrat_wtt, 
				nu_making_charges, vc_kar_code, vc_category, vc_deptt
				from mst_label_rep
				where br_code = '$session.brCode'
				and vc_category = '$category'
				and vc_purity not like '%'||'$purity'||'%'
			"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getPurScaReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getItemScaReport
	 * Description: gets item List data
	 *
	 * @return : item list
	 */
	def getItemScaReport(itemSca,session){
		def category,purity,itemCode
		def x = itemSca
		try{			
			category = x?.hidCat
			itemCode = x?.itemCode
			query =
			"""
				select vc_label_no, vc_purity, vc_item_code, nu_nang, nu_nang_pcs, nu_gross_wtt, nu_nett_wtt, nu_carrat_wtt, 
				nu_making_charges, vc_kar_code, vc_category, vc_deptt
				from mst_label_rep
				where br_code = '$session.brCode'
				and vc_category = '$category'
				and vc_item_code not like '%'||'$itemCode'||'%'
			"""
			sql= new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getItemScaReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getCatListForScanningReport
	 * Description: gets CatList data
	 *
	 * @return : list of Category
	 */
	def getCatListForScanningReport(brCode,companyCode){
		try{
			query =
				"""
				select distinct mlr.vc_category, mr.vc_reason_desc
				from sales.mst_label_rep mlr, sales.mst_reason mr
				where mlr.br_code = '$brCode'
				and mlr.vc_category is not null
				and mlr.vc_category = mr.vc_reason_code
				and mlr.br_code = mr.br_code
				and mr.vc_reason_flg = 'C'
				and mr.vc_comp_code = '$companyCode'
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)			
		}catch(Exception exception){
			log.info("Exception in ReportService getCatListForScanningReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPurListForScanningReport
	 * Description: gets Puriry data
	 *
	 * @return : list of Puriry
	 */
	def getPurListForScanningReport(brCode){
		try{
			query =
				"""
				select distinct replace(vc_purity,'K') vc_purity, replace(vc_purity,'K') vc_purity
				from sales.mst_label_rep
				where br_code = '$brCode'
				and vc_purity is not null
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)			
		}catch(Exception exception){
			log.info("Exception in ReportService getPurListForScanningReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getItemListForScanningReport
	 * Description: gets ItemCode data
	 *
	 * @return : list of ItemCode
	 */
	def getItemListForScanningReport(brCode){
		try{
			query =
				"""
				select distinct vc_item_code, vc_item_code
				from sales.mst_label_rep
				where br_code = '$brCode'
				and vc_category is not null
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)			
		}catch(Exception exception){
			log.info("Exception in ReportService getItemListForScanningReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPayModeDataForKpSetlReport
	 * Description: gets ItemCode data
	 *
	 * @return : list of ItemCode
	 */
	def getPayModeDataForKpSetlReport(brCode){
		try{
			query =
				"""
					SELECT DISTINCT VC_PAY_MODE,VC_PAY_TYPE FROM MST_PAY 
					where VC_PAY_MODE in ('AB','ALL','C','CC','CQ','T','WB')
					union 
					SELECT 'ALL','ALL' FROM DUAL
					order by vc_pay_mode desc
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getItemListForScanningReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}

	/**
	 * Method Name: getKpSettleReport
	 * Description: gets getKpSettleReport data
	 *
	 * @return : list of ItemCode
	 */
	def getKpSettleReport(fromDate,toDate,brCode,companyCode){
		try{
			query =
				"""
				SELECT c.cust_id,b.br_code,
				e.reg_date, 
				d.vc_category,
				b.dt_voucher_date, 
				a.fname||' '||a.mname||' '||a.lname name, 
				a.add1||' '||a.add2||' '||a.city address,
				b.nu_sett_amt, b.nu_mc_id, b.nu_mc_no, b.vc_voucher_no,decode(b.VC_SETTLE_TERMS_OF_PAYMENT,'KP','INVOICE','WB','WB') TERMS_OF_PAYMENT
				FROM sales.cust_mst a, sales.dt_kp_settle b, 
				(SELECT cust_id, reg_no, scheme_no, SUM(amount), nu_mc_id, nu_mc_no, COUNT(*) installments 
				FROM sales.in_dt WHERE nvl(vc_rcpt_settle,'N') = 'Y'
				GROUP BY cust_id, reg_no, scheme_no, nu_mc_id, nu_mc_no) c, sales.hd_cash d, sales.reg_dt e 
				WHERE /*a.vc_field2 = 'Y' AND */
				b.br_code = decode('82','ALL',b.br_code,'82')
				AND d.dt_voucher_date BETWEEN '02-MAY-2015' AND '02-MAY-2015'
				AND b.vc_settle_terms_of_payment = decode('T','ALL',b.vc_settle_terms_of_payment,'T')
				AND e.ch_cancel_flag = 'N'
				AND d.ch_cancel_flag = 'N'----Changes Done By Prasad Punekar
				AND a.cust_id = b.vc_cust_id
				AND a.cust_id = c.cust_id
				AND b.vc_cust_id = c.cust_id
				AND c.scheme_no = e.scheme_no
				AND c.cust_id = e.cust_id
				AND c.reg_no = e.reg_no
				AND b.br_code = d.br_code
				AND b.vc_voucher_no = d.vc_voucher_no
				AND b.dt_voucher_date = d.dt_voucher_date
				AND b.vc_category = d.vc_category
				union
				SELECT b.VC_CUST_ID cust_id, b.br_code,
				B.DT_REG_DATE, 
				'' vc_category,
				b.DT_SETTLE_DATE dt_voucher_date, 
				a.fname||' '||a.mname||' '||a.lname name, 
				a.add1||' '||a.add2||' '||a.city address,
				b.NU_SETTLE_CASH_AMT nu_sett_amt, b.nu_mc_id, b.nu_mc_no, nvl(b.vc_voucher_no,'Settle'),DECODE(b.VC_SETTLE_TERMS_OF_PAYMENT,'C','CASH','CQ','CHECK','E','ECS','T','ADV TRF','WB','WB') TERMS_OF_PAYMENT
				FROM sales.cust_mst a, sales.dt_kp_settle b
				WHERE  b.br_code = decode('82','ALL',b.br_code,'82')
				AND b.DT_SETTLE_DATE BETWEEN '02-MAY-2015' AND '02-MAY-2015'
				AND a.cust_id = b.vc_cust_id
				AND b.vc_settle_terms_of_payment = decode('T','ALL',b.vc_settle_terms_of_payment,'T')
				AND VC_SETTLE_TERMS_OF_PAYMENT <> 'KP'
				and b.nu_sr_no is not null
				and b.ch_cancel_flag = 'N'

				"""		
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getKpSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getKpUnSettleReport
	 * Description: gets getKpUnSettleReport data
	 *
	 * @return : list of ItemCode
	 */
	def getKpUnSettleReport(brCode,schemeNo){
		try{
			query =
				"""
				select c.cust_id, c.nu_period, c.customer_name, 
				 c.resident_phone, c.mobile, c.last_month, c.scheme_close, c.can_settle, 
				 c.cust_ref_amount, c.sp_amount, c.paid_amount, c.paid_installments, c.chq_uncleared_amt, case when d.cnt > 0 then 'ECS' end Paymode,
				 e.reg_date enrollment_start_date, 
				 to_char(add_months(to_date( last_month,'MON-RRRR'),(c.nu_period-c.paid_installments)+2),'DD-MON-RRRR') enrollment_end_date,
				 decode(e.scheme_no,8,e.vc_field1,e.vc_want_to_buy) category,
				 e.scheme_no
				 from apps.tbz_pos_kp_customer_summary_v c, (select a.vc_cust_id, count(*) cnt
				                                            from hd_ecs_det a, dt_ecs_ref b
				                                            where a.vc_cust_id = b.vc_cust_id
				                                            and nvl(b.ch_cancel_flag,'N') = 'N'
				                                            group by a.vc_cust_id) d, reg_dt e 
				 where c.cust_id = d.vc_cust_id (+)
				 and c.cust_id = e.cust_id
				 and c.vc_rcpt_settle = 'N'
				--and substr(c.cust_id,1,instr(c.cust_id,'-')-2) = :br_code
				--and e.scheme_no = nvl(:scheme,e.scheme_no)
				"""		
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getKpUnSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getKpUnSettleReport
	 * Description: gets getKpUnSettleReport data
	 *
	 * @return : list of ItemCode
	 */
	def getMonthWiseSettlementReport(brCode,schemeNo,year,month){
		try{
			query =
				"""
				select paid_installments, substr(csv.cust_id,1,instr(csv.cust_id,'-',1,1)-2) br_code,
				substr(csv.cust_id,instr(csv.cust_id,'-',1,1)-1,1) scheme_no,
				instr(csv.cust_id,'-',1,1) instr_pos,
				csv.cust_id, csv.reg_date scheme_start_date,
				csv.nu_period||' Months' scheme_period, 
				csv.due_amount tot_install_amt,
				csv.nu_period-paid_installments pending_installments,
				( select emp_name 
				  from sales.emp_mst
				  where emp_id = rd.emp_id 
				  and br_code = substr(csv.cust_id,1,instr(csv.cust_id,'-',1,1)-2) ) emp_name,
				nvl(mobile, resident_phone)mobile
				from apps.tbz_pos_kp_customer_summary_v csv, sales.reg_dt rd
				where csv.cust_id = rd.cust_id
				and substr(csv.cust_id,1,instr(csv.cust_id,'-',1,1)-2) = decode('01' ,'ALL',substr(csv.cust_id,1,instr(csv.cust_id,'-',1,1)-2),'01')
				and substr(csv.cust_id,instr(csv.cust_id,'-',1,1)-1,1) = decode('01','ALL',substr(csv.cust_id,instr(csv.cust_id,'-',1,1)-1,1),'01')
				and scheme_close = 'N'
				and vc_rcpt_settle = 'N'
				and not exists
				( select 'Yes' 
				  from (select distinct cust_id 
				  from apps.tbz_pos_kp_branchwise_det_v
				  where ( formonth = 'May-2015' or 
				  to_date(formonth,'MON-RRRR') > to_date('May-2015','MON-RRRR')))
				  where cust_id = csv.cust_id )
			

				"""		
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getKpUnSettleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getAdvanceBalanceReport
	 * Description: gets advanceBalanceReport data
	 *
	 * @return : list of ItemCode
	 */
	def getAdvanceBalanceReport(instance){
		def str
		
		try{
			str =
			
			"""
			select a.vc_comp_code,
			a.vc_adv_ord_no,
			b.vc_label_no,
			b.nu_rate nu_rate,
			b.nu_gross_wtt nu_gross_wtt,
			b.nu_carrat_wtt nu_carrat_wtt,
			a.dt_adv_ord_date,
			a.vc_cust_fname||' '||vc_cust_mname||' '||vc_cust_lname vc_customer_name,
			a.vc_address1 vc_address,
			a.vc_narration,
			a.vc_category,
			a.vc_narration,
			a.vc_category,
			a.nu_tot_adv_amt,
			case when a.vc_adv_ord_no like '%SS%' then
			a.nu_balance -  (select sum(dcp.nu_amount)+
			nvl((
			select sum(dcam.nu_sett_amt)
			from sales.check_bounce cb, sales.dt_cash_advance_mr dcam
			where cb.vc_voucher_no = dcam.vc_voucher_no
			and cb.dt_voucher_date = dcam.dt_voucher_date
			--and dcam.vc_adv_ord_no = i.vc_adv_ord_no
			and cb.vc_sale_voucher_no = hc.vc_ref_sale_voucher_no
			and cb.dt_sale_voucher_date = hc.dt_ref_sale_voucher_date
			and cb.vc_receipt_type = 'B'
			and dcam.vc_cancel_flag = 'N'),0)
			from hd_cash hc,dt_cash_payment dcp
			where dcp.ch_pay_mode = 'B'
			and hc.br_code = dcp.br_code
			and hc.vc_comp_code = dcp.vc_comp_code
			and hc.vc_category = dcp.vc_category
			and hc.vc_ref_sale_voucher_no = dcp.vc_voucher_no
			and hc.dt_ref_sale_voucher_date = dcp.dt_voucher_date
			and hc.vc_sr_adv = a.vc_adv_ord_no
			and hc.ch_cancel_flag= 'N'
			group by hc.vc_ref_sale_voucher_no,hc.dt_ref_sale_voucher_date
			/*and c.vc_voucher_no = ''*/ )
			else a.nu_balance
			end balance_amount, --a.nu_balance balance,
			a.ch_active_flag,
			a.ch_cancel_flag
			FROM sales.hd_sale_adv_ord a, sales.dt_sale_adv_ord b--,sales.dt_cash_payment c --hd_cash
			WHERE
			--a.vc_comp_code = :comp_code
			--a.br_code = :br_code
			--and a.dt_adv_ord_date <= :dt_date
			a.ch_active_flag='A'
			and a.nu_balance>0
			and a.ch_cancel_flag='N'
			and a.vc_adv_ord_no = b.vc_adv_ord_no
			and a.dt_adv_ord_date = b.dt_adv_ord_date
			and a.br_code = b.br_code
			order by a.dt_adv_ord_date asc
			
			"""
			sql = new Sql(dataSource)
			result = sql.rows(str)
			
		}catch(Exception exception){
			log.info("Exception in ReportService getAdvanceBalanceReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getCategory
	 * Description: gets Category value
	 *
	 * @return : Category
	 */
	def getCategory(category){
		try{
			query1 = "select FLEX_VALUE FROM fnd_flex_values_vl WHERE DESCRIPTION='$category'"
			Sql sql1 = new Sql(dataSource)
			result = sql1.rows(query1)
			result.each {
				category= (it.values()[0])
			}
		}catch(Exception exception){
			log.info("Exception in ReportService getCategory method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return category
	}
	
	/**
	 * Method Name: getSummaryCashReport
	 * Description: gets SummaryCash data
	 *
	 * @return : list of SummaryCash
	 */
	def getSummaryCashReport(sumCash,session){
		def fromDate,category,mid
		def x = sumCash
		def type99,result1
		def resultList  = []
		def dataMap = [:]
		def adVal = 0,gvVal = 0,kpVal = 0,mrVal = 0,ogVal = 0,pbVal = 0,prVal = 0,psVal = 0,saVal = 0,ssVal = 0,gcVal = 0,drVal = 0,totCash
		try{			
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
					SELECT  'SA' type99 , 
					0 bill_amount99 , 
					0 discount99 , 
					        SUM( DECODE( c.ch_pay_mode , 'C' , c.nu_amount , 0 )) cash99 ,
					        SUM( DECODE( c.ch_pay_mode , 'CB' , c.nu_amount , 0 )) cashback99 ,
					         SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque99 ,  
					          SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr99 ,
					         SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard99 ,
					          SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance99 ,
					          SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) kp_settle99 ,          
					         SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv99 ,
					         SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan99 ,
					          SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance99 ,
					          SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel99,
					          0 og_advance, 0 sr_advance, 0,
					        SUM(DECODE(c.ch_pay_mode , 'GC' , c.nu_amount , 0 )) GC
					          FROM sales.hd_cash a , sales.dt_cash_payment c WHERE
					a.vc_comp_code = c.vc_comp_code  AND
					 a.vc_voucher_no = c.vc_voucher_no AND 
					 a.dt_voucher_Date = c.dt_voucher_date AND
					 a.vc_category = c.vc_category AND a.ch_gold_type <> 'S' AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND a.vc_comp_code = '$session.companyCode' AND 
					a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')      
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=c.br_code
					UNION
					SELECT   'SS' TYPE ,
					0 bill_amount , 
					0 discount , 
					        SUM( DECODE( c.ch_pay_mode , 'C' , c.nu_amount , 0 )) cash ,
					        SUM( DECODE( c.ch_pay_mode , 'CB' , c.nu_amount , 0 )) cashback ,
					         SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque ,  
					          SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr ,
					         SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard ,
					          SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance ,
					          SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) kp_settle ,
					         SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv ,
					         SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan ,
					          SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance ,
					          SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel,
					          0 og_advance, 0 sr_advance, 0,
					        SUM(DECODE(c.ch_pay_mode , 'GC' , c.nu_amount , 0 )) GC  FROM
					sales.hd_cash a , sales.dt_cash_payment c WHERE
					 a.vc_comp_code = c.vc_comp_code  AND
					 a.vc_voucher_no = c.vc_voucher_no AND 
					 a.dt_voucher_Date = c.dt_voucher_date AND
					 a.vc_category = c.vc_category AND a.ch_gold_type = 'S' AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND a.vc_comp_code = '$session.companyCode' AND 
					a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')      
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=c.br_code
					UNION
					SELECT 'OG' TYPE , SUM(a.nu_amount) , 0 discount , SUM(a.nu_cash_amt) cash ,
					 0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0, 0, 0, 0, 0 FROM
					sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category LIKE 'ALL' AND a.nu_mc_id LIKE a.nu_mc_id 
					AND vc_type_of_bill = 'OG' AND vc_cancel_flag<>'Y'
					AND a.br_code='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT  'PB' , SUM(b.nu_amount) , 0 ,SUM(DECODE(b.ch_pay_mode,'C',b.nu_amount,0)) cash,
					 0 cashback , SUM(DECODE(b.ch_pay_mode,'CQ',b.nu_amount,0)) cheque, 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0, 0, 0, 0, 0 FROM
					sales.hd_bill a , sales.dt_pb_payment b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND vc_type_of_bill = 'PB' AND vc_gold_type = 'N' 
					AND a.vc_cancel_flag <>'Y'
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT   'PS' , SUM(a.nu_amount) , 0 , SUM(a.nu_cash_amt) cash ,
					 0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0 ,0 ,0, 0, 0   FROM
					sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND vc_type_of_bill = 'PB' AND vc_gold_type = 'S'
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT 'PR' , SUM(a.nu_amount) , 0 , SUM(a.nu_cash_amt) cash ,
					 0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0 , 0, 0, 0, 0   FROM
					sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND vc_type_of_bill = 'PR'  
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT   'AD' TYPE , SUM( b.nu_amount ) bill_amount , 
					0 discount , 
					        SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
					        SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
					         SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,  
					          SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
					         SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
					          SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
					          SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) kp_settle ,
					         SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
					         SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
					          SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
					          SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
					          SUM( DECODE( b.ch_pay_mode , 'OG' , b.nu_amount , 0 )) OG_Advance,
					          SUM( DECODE( b.ch_pay_mode , 'SR' , b.nu_amount , 0 )) SR_Advance, 0,
					        SUM(DECODE(b.ch_pay_mode , 'GC' , nu_amount , 0 )) GC  FROM
					sales.hd_sale_adv_ord a , sales.dt_sale_adv_pay b  WHERE
					a.vc_comp_code = b.vc_comp_code AND 
					a.vc_adv_ord_no  = b.vc_adv_ord_no AND 
					a.dt_adv_ord_date = b.dt_adv_ord_DATE   
					AND a.ch_cancel_flag = 'N' AND 
					( ch_active_flag = 'N' OR ch_active_flag = 'A' ) AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND a.vc_comp_code = '$session.companyCode' AND 
					a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.dt_adv_ord_date  = TO_DATE('$fromDate','MM/DD/YYYY') 
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT  'GV' TYPE , 
					SUM(a.nu_amount) bill_amount , 
					0 discount , 
					        SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
					        SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
					         SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,  
					          SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
					         SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
					          SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
					          SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) kp_settle ,
					         SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
					         SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
					          SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
					          SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
					          0, 0, 0,
					        SUM(DECODE(b.ch_pay_mode , 'GC' , b.nu_amount , 0 )) GC
					FROM
					sales.mst_vouch_activation a , sales.gift_voucher_payment b WHERE a.vc_comp_code = '$session.companyCode' 
					AND a.vc_comp_code = b.vc_comp_code AND 
					a.dt_issue_date = b.dt_voucher_date  AND a.vc_voucher_no = b.vc_voucher_no 
					AND a.dt_issue_date = TO_DATE('$fromDate','MM/DD/YYYY') AND
					 a.vc_category_code =DECODE( '$category','ALL',A.VC_CATEGORY_CODE,'$category') AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT 'KP' TYPE , SUM( b.amount ) bill_amount , 0 discount , 
					        SUM( DECODE( b.pay_mode , 'C' , b.amount , 0 )) cash ,
					        SUM( DECODE( b.pay_mode , 'CB' , b.amount , 0 )) cashback ,
					        SUM( DECODE( b.pay_mode , 'CQ' , b.amount , 0 )) cheque ,  
					        SUM( DECODE( b.pay_mode , 'FC' , b.amount , 0 )) foreigncurr ,
					        SUM( DECODE( b.pay_mode , 'CC' , b.amount , 0 )) creditcard ,
					        SUM( DECODE( b.pay_mode , 'A' , b.amount , 0 )) advance ,
					        SUM( DECODE( b.pay_mode , 'KP' , b.amount , 0 )) kp_settle ,
					        SUM( DECODE( b.pay_mode , 'GV' , b.amount , 0 )) giftv ,
					        SUM( DECODE( b.pay_mode , 'L' , b.amount , 0 )) loan ,
							SUM( DECODE( b.pay_mode , 'B' , b.amount , 0 )) balance ,
							SUM( DECODE( b.pay_mode , 'TQ' , b.amount , 0 )) travel,
					        0, 0, 0,
					        SUM(DECODE(b.pay_mode , 'GC' , b.amount , 0 )) GC
					FROM sales.reg_dt a, sales.in_dt b, sales.scheme_mst c  
					WHERE a.reg_no = b.reg_no AND 
					a.cust_id  = b.cust_id AND
					a.scheme_no = c.scheme_no AND 
					a.ch_cancel_flag = 'N' AND 
					b.nu_mc_id =DECODE( '$mid','ALL',b.nu_mc_id,'$mid') AND 
					b.inward_date  = TO_DATE('$fromDate','MM/DD/YYYY') AND
					b.br_code = '$session.brCode'
					union
					select 'MR' type, SUM(nu_amount) bill_amount , 0 discount ,  
					        SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
					        SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
					        SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,  
					        SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
					        SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
					        SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
					        SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
					        SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
					        SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
					        SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
					        SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
					        SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
					        SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
					        SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance,
					        SUM(DECODE(ch_pay_type , 'GC' , nu_amount , 0 )) GC
					from sales.CHECK_BOUNCE 
					where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
					and vc_category =DECODE('$category','ALL',vc_category,'$category')
					and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
					and br_code = '$session.brCode'
					UNION ALL
					select 'GC' type, SUM(nu_amount) bill_amount , 0 discount,  
					          SUM(DECODE(ch_pay_mode , 'C' ,  nu_amount , 0 )) cash ,
					        SUM(DECODE(ch_pay_mode , 'CB' , nu_amount , 0 )) cashback ,
					        SUM(DECODE(ch_pay_mode , 'CQ' , nu_amount , 0 )) cheque ,  
					        SUM(DECODE(ch_pay_mode , 'FC' , nu_amount , 0 )) foreigncurr ,
					        SUM(DECODE(ch_pay_mode , 'CC' , nu_amount , 0 )) creditcard ,
					        SUM(DECODE(ch_pay_mode , 'A' ,  nu_amount , 0 )) advance ,
					        SUM(DECODE(ch_pay_mode , 'KP' , nu_amount , 0 )) KP_SETTLE ,
					        SUM(DECODE(ch_pay_mode , 'GV' , nu_amount , 0 )) giftv ,
					        SUM(DECODE(ch_pay_mode , 'L' ,  nu_amount , 0 )) loan ,
					        SUM(DECODE(ch_pay_mode , 'B' ,  nu_amount , 0 )) balance ,
					        SUM(DECODE(ch_pay_mode , 'TQ' , nu_amount , 0 )) travel,
					        SUM(DECODE(ch_pay_mode , 'OG' , nu_amount , 0 )) og_advance,
					        SUM(DECODE(ch_pay_mode , 'SR' , nu_amount , 0 )) sr_advance,
					        SUM(DECODE(ch_pay_mode , 'T' ,  nu_amount , 0 )) Tr_advance,
					        SUM(DECODE(ch_pay_mode , 'GC' , nu_amount , 0 )) GC
					from dt_gift_card
					where receipt_date = TO_DATE('$fromDate','MM/DD/YYYY')
					and nu_mc_id = DECODE( '$mid','ALL',nu_mc_id,'$mid')
					and ch_cancel_flag = 'N'
					and br_code = '$session.brCode'
					union all
					select 'DR' type, SUM(nu_amount) bill_amount , 0 discount ,  
					        SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
					        SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
					        SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,  
					        SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
					        SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
					        SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
					        SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
					        SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
					        SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
					        SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
					        SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
					        SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
					        SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
					        SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance,
					        SUM(DECODE(ch_pay_type , 'GC' , nu_amount , 0 )) GC
					from sales.xxpos_donation_receipts
					where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
					and vc_category =DECODE('$category','ALL',vc_category,'$category')
					and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
					and br_code = '$session.brCode'
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
			result.each {
				type99 =it.values()[0]				
				if(type99=='AD'){					
					def adQry = """
						Select coalesce(sum(a.NU_AMOUNT),0) AS temp from SALES.dt_sale_adv_pay a ,
						SALES.HD_SALE_ADV_ORD b where
						a.dt_ADV_ORD_date = TO_DATE('$fromDate','MM/DD/YYYY') and
						a.vc_comp_code = '$session.companyCode' and
						A.VC_ADV_ORD_NO = B.VC_ADV_ORD_NO AND
						A.DT_ADV_ORD_DATE = B.DT_ADV_ORD_DATE AND
						a.CH_PAY_MODE = 'C' and
						b.nu_mc_id =DECODE('$mid','ALL',b.NU_MC_ID,'$mid') and
						B.vc_category=DECODE( '$category','ALL',B.VC_CATEGORY,'$category')
						and a.br_code='$session.brCode'
						and a.br_code=b.br_code
						AND b.ch_cancel_flag = 'N'
						and a.vc_comp_code=b.vc_comp_code
					"""
					Sql sql1 = new Sql(dataSource)
					def adResult = sql1.rows(adQry)
					if(adResult!=null && !adResult.isEmpty()){
						adResult.each {
							adVal = it.values()[0]
							dataMap.put("ADVANCE", it.values()[0])
						}
					}
				}else if(type99=='GV'){
					def gvQry = """
						SELECT coalesce(SUM(b.nu_amount),0) AS temp 
						FROM sales.mst_vouch_activation a , sales.gift_voucher_payment b 
						WHERE a.vc_comp_code = '$session.companyCode' 
						AND a.vc_comp_code = b.vc_comp_code 
						AND a.dt_issue_date = b.dt_voucher_date  
						AND a.vc_voucher_no = b.vc_voucher_no 
						AND a.dt_issue_date = TO_DATE('$fromDate','MM/DD/YYYY') 
						AND b.ch_pay_mode = 'C'
						AND a.vc_category_code =DECODE( '$category','ALL',a.vc_category_code,'$category') 
						AND a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid')
						AND a.br_code='$session.brCode'   
						AND a.br_Code=b.br_code
					"""
					Sql sql2 = new Sql(dataSource)
					def gvResult = sql2.rows(gvQry)
					if(gvResult!=null && !gvResult.isEmpty()){
						gvResult.each {
							gvVal = it.values()[0]
							dataMap.put("GV", it.values()[0])
						}
					}
				}else if(type99=='KP'){
					def kpQry = """
						SELECT NVL(SUM(abs(NVL(b.AMOUNT,0))),0) AS temp
						FROM sales.reg_dt a, sales.in_dt b, sales.scheme_mst c
						WHERE a.reg_no = b.reg_no AND
						a.cust_id  = b.cust_id AND
						a.scheme_no = c.scheme_no AND 
						a.ch_cancel_flag = 'N' AND
						b.pay_mode in('C') AND
						b.nu_mc_id = DECODE('$mid','ALL',b.nu_mc_id,'$mid') AND
						b.br_code = '$session.brCode' and
						b.inward_date = TO_DATE('$fromDate','MM/DD/YYYY') 
					"""
					Sql sql3 = new Sql(dataSource)
					def kpResult = sql3.rows(kpQry)
					if(kpResult!=null && !kpResult.isEmpty()){
						kpResult.each {
							kpVal = it.values()[0]
							dataMap.put("KP", it.values()[0])
						}
					}
				}else if(type99=='MR'){
					def mrQry = """
						select coalesce(SUM(nu_amount),0) AS temp 
						from CHECK_BOUNCE 
						where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
						and ch_pay_type = 'C'
						and vc_category =DECODE('$category','ALL',$category,'ALL')
						and nu_mc_id =DECODE('$mid','ALL',nu_mc_id,'$mid')
						and br_code = '$session.brCode'
					"""
					Sql sql4 = new Sql(dataSource)
					def mrResult = sql4.rows(mrQry)
					if(mrResult!=null && !mrResult.isEmpty()){
						mrResult.each {
							mrVal = it.values()[0]
							dataMap.put("MR", it.values()[0])
						}
					}
				}else if(type99=='OG'){
					def ogQry = """
						Select coalesce(-sum(NU_Cash_AMT),0) AS temp from sales.hd_bill a where  
						a.VC_TYPE_OF_BILL='OG' and
						a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') and
						a.vc_comp_code = '$session.companyCode' and
						a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') and
						a.vc_cancel_flag != 'Y' and
						a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid')
						and a.br_code='$session.brCode'
					"""
					Sql sql5 = new Sql(dataSource)
					def ogResult = sql5.rows(ogQry)
					if(ogResult!=null && !ogResult.isEmpty()){
						ogResult.each {
							ogVal = it.values()[0]
							dataMap.put("OG", it.values()[0])
						}
					}
				}else if(type99=='PB'){
					def pbQry = """
						Select coalesce(-1*sum(B.NU_AMOUNT),0) AS temp from sales.hd_bill a, sales.DT_PB_PAYMENT B  
						where a.VC_TYPE_OF_BILL='PB' and
						A.VC_BILL_NO = B.VC_BILL_NO AND
						a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') and
						A.DT_BILL_DATE = B.DT_BILL_DATE AND
						a.vc_comp_code = '$session.companyCode' and
						B.vc_comp_code = '$session.companyCode' and
						a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') and
						a.vc_cancel_flag != 'Y' and
						b.CH_PAY_MODE ='C' and 
						a.vc_gold_type='N' and
						a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid')
						and a.br_code='$session.brCode'
						and a.br_code=b.br_code
					"""
					Sql sql6 = new Sql(dataSource)
					def pbResult = sql6.rows(pbQry)
					if(pbResult!=null && !pbResult.isEmpty()){
						pbResult.each {
							pbVal = it.values()[0]
							dataMap.put("PB", it.values()[0])
						}
					}
				}else if(type99=='PR'){
					def prQry = """
						Select coalesce(sum(NU_cash_AMT),0) AS temp from sales.hd_bill a where  
						a.VC_TYPE_OF_BILL='PR' and
						a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') and
						a.vc_comp_code = '$session.companyCode' and
						a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') and
						a.vc_cancel_flag != 'Y' and
						a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid')
						and a.br_code='$session.brCode'
					"""
					Sql sql7 = new Sql(dataSource)
					def prResult = sql7.rows(prQry)
					if(prResult!=null && !prResult.isEmpty()){
						prResult.each {
							prVal = it.values()[0]
							dataMap.put("PR", it.values()[0])
						}
					}
				}else if(type99=='PS'){
					def psQry = """
						Select coalesce(-1*sum(B.NU_AMOUNT),0) AS temp from sales.hd_bill a ,sales.DT_PB_PAYMENT B where  
						a.VC_TYPE_OF_BILL='PB' and
						A.VC_BILL_NO = B.VC_BILL_NO AND
						a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') and
						A.DT_BILL_DATE = B.DT_BILL_DATE AND
						a.vc_comp_code = '$session.companyCode' and
						B.vc_comp_code = '$session.companyCode' and
						a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') and
						a.vc_cancel_flag != 'Y' and
						b.CH_PAY_MODE ='C' and 
						a.vc_gold_type='S' and
						a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid')
						and a.br_code='$session.brCode'
						and a.br_code=b.br_code
					"""
					Sql sql8 = new Sql(dataSource)
					def psResult = sql8.rows(psQry)
					if(psResult!=null && !psResult.isEmpty()){
						psResult.each {
							psVal = it.values()[0]
							dataMap.put("PS", it.values()[0])
						}
					}
				}else if(type99=='SA'){
					def saQry = """
						Select coalesce(sum(nu_amount),0) AS temp
						from sales.hd_cash a,sales.dt_cash_payment b     where
						a.vc_comp_code='$session.companyCode' and
						a.vc_comp_code = b.vc_comp_code and
						b.ch_pay_mode = 'C' and
						a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY') and
						b.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY') and
						a.dt_voucher_date = b.dt_voucher_date and
						a.vc_voucher_no = b.vc_voucher_no and
						a.ch_gold_type = 'N' and
						a.vc_voucher_no = b.vc_voucher_no and
						a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') and
						a.vc_category = b.vc_category and
						a.ch_cancel_flag != 'Y' and
						a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid')
						and a.br_code='$session.brCode'
						and a.br_code=b.br_code
					"""
					Sql sql9 = new Sql(dataSource)
					def saResult = sql9.rows(saQry)
					if(saResult!=null && !saResult.isEmpty()){
						saResult.each {
							saVal = it.values()[0]
							dataMap.put("SA", it.values()[0])
						}
					}
				}else if(type99=='SS'){
					def ssQry = """
						select coalesce(sum(nu_amount),0) AS temp
						from sales.hd_cash a,sales.dt_cash_payment b     where
						a.vc_comp_code='$session.companyCode' and
						a.vc_comp_code = b.vc_comp_code and
						b.ch_pay_mode = 'C' and
						a.dt_voucher_date =TO_DATE('$fromDate','MM/DD/YYYY') and  
						b.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY') and  
						a.dt_voucher_date = b.dt_voucher_date and
						a.vc_voucher_no = b.vc_voucher_no and
						a.ch_gold_type = 'S' and
						a.vc_voucher_no = b.vc_voucher_no and
						a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') and
						a.vc_category = b.vc_category and
						a.ch_cancel_flag != 'Y' and
						a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid')
						and a.br_code='$session.brCode'
						and a.br_code=b.br_code
					"""
					Sql sql10 = new Sql(dataSource)
					def ssResult = sql10.rows(ssQry)
					if(ssResult!=null && !ssResult.isEmpty()){
						ssResult.each {
							ssVal = it.values()[0]
							dataMap.put("SS", it.values()[0])
						}
					}
				}else if(type99=='GC'){
					def gcQry = """
						select coalesce(sum(dgc.nu_amount),0) AS temp
						from sales.hd_gift_card hgc, sales.dt_gift_card dgc
						where hgc.gift_card_no = dgc.gift_card_no
						and hgc.ch_cancel_flag = 'N'
						and dgc.ch_cancel_flag = 'N'
						and trunc(dgc.receipt_date) = TO_DATE('$fromDate','MM/DD/YYYY')   
						and dgc.nu_mc_id = decode('$mid','ALL',dgc.nu_mc_id,'$mid') 
						and dgc.ch_pay_mode = 'C'
						and dgc.br_code = '$session.brCode'
						"""
					Sql sql11 = new Sql(dataSource)
					def gcResult = sql11.rows(gcQry)
					if(gcResult!=null && !gcResult.isEmpty()){
						gcResult.each {
							gcVal = it.values()[0]
							dataMap.put("GC", it.values()[0])
						}
					}
				}else if(type99=='DR'){
					def drQry = """
						select coalesce(SUM(nu_amount), 0)  AS temp
						from xxpos_donation_receipts
						where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY') 
						and ch_pay_type = 'C'
						and vc_category =DECODE('$category','ALL',vc_category,'$category')
						and nu_mc_id =DECODE('$mid','ALL',nu_mc_id,'$mid')
						and br_code = '$session.brCode'
						"""					
					Sql sql12 = new Sql(dataSource)
					def drResult = sql12.rows(drQry)
					if(drResult!=null && !drResult.isEmpty()){
						drResult.each {
							drVal = it.values()[0]
							dataMap.put("DR", it.values()[0])
						}
					}				
				}				
			}			
			totCash= adVal+gvVal+kpVal+mrVal+ogVal+pbVal+prVal+psVal+saVal+ssVal+gcVal+drVal
			dataMap.put("totCash", totCash)
			resultList.add(dataMap)
		}catch(Exception exception){
			log.info("Exception in ReportService getSummaryCashReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultList[0]
	}
	
	/**
	 * Method Name: getSummaryCashBackReport
	 * Description: gets SummaryCashBack data
	 *
	 * @return : list of SummaryCashBack
	 */
	def getSummaryCashBackReport(sumCashBack,session){		
		def fromDate,category,mid
		def x = sumCashBack
		def type99,result1,adVal = 0,saVal = 0,totCashBck
		def resultList  = []
		def dataMap = [:]
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
					SELECT  'SA' type99 , 
					0 bill_amount99 , 
					0 discount99 , 
					        SUM( DECODE( c.ch_pay_mode , 'C' , c.nu_amount , 0 )) cash99 ,
					        SUM( DECODE( c.ch_pay_mode , 'CB' , c.nu_amount , 0 )) cashback99 ,
					         SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque99 ,  
					          SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr99 ,
					         SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard99 ,
					          SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance99 ,
					          SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) kp_settle99 ,          
					         SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv99 ,
					         SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan99 ,
					          SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance99 ,
					          SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel99,
					          0 og_advance, 0 sr_advance, 0,
					        SUM(DECODE(c.ch_pay_mode , 'GC' , c.nu_amount , 0 )) GC
					          FROM sales.hd_cash a , sales.dt_cash_payment c WHERE
					a.vc_comp_code = c.vc_comp_code  AND
					 a.vc_voucher_no = c.vc_voucher_no AND 
					 a.dt_voucher_Date = c.dt_voucher_date AND
					 a.vc_category = c.vc_category AND a.ch_gold_type <> 'S' AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND a.vc_comp_code = '$session.companyCode' AND 
					a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')      
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=c.br_code
					UNION
					SELECT   'SS' TYPE ,
					0 bill_amount , 
					0 discount , 
					        SUM( DECODE( c.ch_pay_mode , 'C' , c.nu_amount , 0 )) cash ,
					        SUM( DECODE( c.ch_pay_mode , 'CB' , c.nu_amount , 0 )) cashback ,
					         SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque ,  
					          SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr ,
					         SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard ,
					          SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance ,
					          SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) kp_settle ,
					         SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv ,
					         SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan ,
					          SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance ,
					          SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel,
					          0 og_advance, 0 sr_advance, 0,
					        SUM(DECODE(c.ch_pay_mode , 'GC' , c.nu_amount , 0 )) GC  FROM
					sales.hd_cash a , sales.dt_cash_payment c WHERE
					 a.vc_comp_code = c.vc_comp_code  AND
					 a.vc_voucher_no = c.vc_voucher_no AND 
					 a.dt_voucher_Date = c.dt_voucher_date AND
					 a.vc_category = c.vc_category AND a.ch_gold_type = 'S' AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND a.vc_comp_code = '$session.companyCode' AND 
					a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')      
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=c.br_code
					UNION
					SELECT 'OG' TYPE , SUM(a.nu_amount) , 0 discount , SUM(a.nu_cash_amt) cash ,
					 0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0, 0, 0, 0, 0 FROM
					sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category LIKE 'ALL' AND a.nu_mc_id LIKE a.nu_mc_id 
					AND vc_type_of_bill = 'OG' AND vc_cancel_flag<>'Y'
					AND a.br_code='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT  'PB' , SUM(b.nu_amount) , 0 ,SUM(DECODE(b.ch_pay_mode,'C',b.nu_amount,0)) cash,
					 0 cashback , SUM(DECODE(b.ch_pay_mode,'CQ',b.nu_amount,0)) cheque, 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0, 0, 0, 0, 0 FROM
					sales.hd_bill a , sales.dt_pb_payment b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND vc_type_of_bill = 'PB' AND vc_gold_type = 'N' 
					AND a.vc_cancel_flag <>'Y'
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT   'PS' , SUM(a.nu_amount) , 0 , SUM(a.nu_cash_amt) cash ,
					 0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0 ,0 ,0, 0, 0   FROM
					sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND vc_type_of_bill = 'PB' AND vc_gold_type = 'S'
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT 'PR' , SUM(a.nu_amount) , 0 , SUM(a.nu_cash_amt) cash ,
					 0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0 , 0, 0, 0, 0   FROM
					sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
					a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode' 
					AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND vc_type_of_bill = 'PR'  
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT   'AD' TYPE , SUM( b.nu_amount ) bill_amount , 
					0 discount , 
					        SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
					        SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
					         SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,  
					          SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
					         SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
					          SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
					          SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) kp_settle ,
					         SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
					         SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
					          SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
					          SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
					          SUM( DECODE( b.ch_pay_mode , 'OG' , b.nu_amount , 0 )) OG_Advance,
					          SUM( DECODE( b.ch_pay_mode , 'SR' , b.nu_amount , 0 )) SR_Advance, 0,
					        SUM(DECODE(b.ch_pay_mode , 'GC' , nu_amount , 0 )) GC  FROM
					sales.hd_sale_adv_ord a , sales.dt_sale_adv_pay b  WHERE
					a.vc_comp_code = b.vc_comp_code AND 
					a.vc_adv_ord_no  = b.vc_adv_ord_no AND 
					a.dt_adv_ord_date = b.dt_adv_ord_DATE   
					AND a.ch_cancel_flag = 'N' AND 
					( ch_active_flag = 'N' OR ch_active_flag = 'A' ) AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND a.vc_comp_code = '$session.companyCode' AND 
					a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') 
					AND a.dt_adv_ord_date  = TO_DATE('$fromDate','MM/DD/YYYY') 
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT  'GV' TYPE , 
					SUM(a.nu_amount) bill_amount , 
					0 discount , 
					        SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
					        SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
					         SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,  
					          SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
					         SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
					          SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
					          SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) kp_settle ,
					         SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
					         SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
					          SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
					          SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
					          0, 0, 0,
					        SUM(DECODE(b.ch_pay_mode , 'GC' , b.nu_amount , 0 )) GC
					FROM
					sales.mst_vouch_activation a , sales.gift_voucher_payment b WHERE a.vc_comp_code = '$session.companyCode' 
					AND a.vc_comp_code = b.vc_comp_code AND 
					a.dt_issue_date = b.dt_voucher_date  AND a.vc_voucher_no = b.vc_voucher_no 
					AND a.dt_issue_date = TO_DATE('$fromDate','MM/DD/YYYY') AND
					 a.vc_category_code =DECODE( '$category','ALL',A.VC_CATEGORY_CODE,'$category') AND 
					a.nu_mc_id =DECODE('$mid','ALL',A.NU_MC_ID,'$mid') 
					AND A.BR_CODE='$session.brCode'
					AND a.br_code=b.br_code
					UNION
					SELECT 'KP' TYPE , SUM( b.amount ) bill_amount , 0 discount , 
					        SUM( DECODE( b.pay_mode , 'C' , b.amount , 0 )) cash ,
					        SUM( DECODE( b.pay_mode , 'CB' , b.amount , 0 )) cashback ,
					        SUM( DECODE( b.pay_mode , 'CQ' , b.amount , 0 )) cheque ,  
					        SUM( DECODE( b.pay_mode , 'FC' , b.amount , 0 )) foreigncurr ,
					        SUM( DECODE( b.pay_mode , 'CC' , b.amount , 0 )) creditcard ,
					        SUM( DECODE( b.pay_mode , 'A' , b.amount , 0 )) advance ,
					        SUM( DECODE( b.pay_mode , 'KP' , b.amount , 0 )) kp_settle ,
					        SUM( DECODE( b.pay_mode , 'GV' , b.amount , 0 )) giftv ,
					        SUM( DECODE( b.pay_mode , 'L' , b.amount , 0 )) loan ,
							SUM( DECODE( b.pay_mode , 'B' , b.amount , 0 )) balance ,
							SUM( DECODE( b.pay_mode , 'TQ' , b.amount , 0 )) travel,
					        0, 0, 0,
					        SUM(DECODE(b.pay_mode , 'GC' , b.amount , 0 )) GC
					FROM sales.reg_dt a, sales.in_dt b, sales.scheme_mst c  
					WHERE a.reg_no = b.reg_no AND 
					a.cust_id  = b.cust_id AND
					a.scheme_no = c.scheme_no AND 
					a.ch_cancel_flag = 'N' AND 
					b.nu_mc_id =DECODE( '$mid','ALL',b.nu_mc_id,'$mid') AND 
					b.inward_date  = TO_DATE('$fromDate','MM/DD/YYYY') AND
					b.br_code = '$session.brCode'
					union
					select 'MR' type, SUM(nu_amount) bill_amount , 0 discount ,  
					        SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
					        SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
					        SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,  
					        SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
					        SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
					        SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
					        SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
					        SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
					        SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
					        SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
					        SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
					        SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
					        SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
					        SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance,
					        SUM(DECODE(ch_pay_type , 'GC' , nu_amount , 0 )) GC
					from sales.CHECK_BOUNCE 
					where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
					and vc_category =DECODE('$category','ALL',vc_category,'$category')
					and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
					and br_code = '$session.brCode'
					UNION ALL
					select 'GC' type, SUM(nu_amount) bill_amount , 0 discount,  
					          SUM(DECODE(ch_pay_mode , 'C' ,  nu_amount , 0 )) cash ,
					        SUM(DECODE(ch_pay_mode , 'CB' , nu_amount , 0 )) cashback ,
					        SUM(DECODE(ch_pay_mode , 'CQ' , nu_amount , 0 )) cheque ,  
					        SUM(DECODE(ch_pay_mode , 'FC' , nu_amount , 0 )) foreigncurr ,
					        SUM(DECODE(ch_pay_mode , 'CC' , nu_amount , 0 )) creditcard ,
					        SUM(DECODE(ch_pay_mode , 'A' ,  nu_amount , 0 )) advance ,
					        SUM(DECODE(ch_pay_mode , 'KP' , nu_amount , 0 )) KP_SETTLE ,
					        SUM(DECODE(ch_pay_mode , 'GV' , nu_amount , 0 )) giftv ,
					        SUM(DECODE(ch_pay_mode , 'L' ,  nu_amount , 0 )) loan ,
					        SUM(DECODE(ch_pay_mode , 'B' ,  nu_amount , 0 )) balance ,
					        SUM(DECODE(ch_pay_mode , 'TQ' , nu_amount , 0 )) travel,
					        SUM(DECODE(ch_pay_mode , 'OG' , nu_amount , 0 )) og_advance,
					        SUM(DECODE(ch_pay_mode , 'SR' , nu_amount , 0 )) sr_advance,
					        SUM(DECODE(ch_pay_mode , 'T' ,  nu_amount , 0 )) Tr_advance,
					        SUM(DECODE(ch_pay_mode , 'GC' , nu_amount , 0 )) GC
					from dt_gift_card
					where receipt_date = TO_DATE('$fromDate','MM/DD/YYYY')
					and nu_mc_id = DECODE( '$mid','ALL',nu_mc_id,'$mid')
					and ch_cancel_flag = 'N'
					and br_code = '$session.brCode'
					union all
					select 'DR' type, SUM(nu_amount) bill_amount , 0 discount ,  
					        SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
					        SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
					        SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,  
					        SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
					        SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
					        SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
					        SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
					        SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
					        SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
					        SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
					        SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
					        SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
					        SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
					        SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance,
					        SUM(DECODE(ch_pay_type , 'GC' , nu_amount , 0 )) GC
					from sales.xxpos_donation_receipts
					where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
					and vc_category =DECODE('$category','ALL',vc_category,'$category')
					and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
					and br_code = '$session.brCode'
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
			result.each {
				type99 =it.values()[0]				
				if(type99=='AD'){					
					def adQry = """
						select  sum(nvl(round(b.nu_amount),0))  AS temp1
						from
						sales.hd_sale_adv_ord a , sales.dt_sale_adv_pay b  where
						a.vc_comp_code = b.vc_comp_code and 
						a.vc_adv_ord_no  = b.vc_adv_ord_no and 
						a.dt_adv_ord_date = b.dt_adv_ord_DATE   
						and b.ch_pay_mode = 'CB'
						and a.ch_cancel_flag = 'N' and 
						( ch_active_flag = 'N' or ch_active_flag = 'A' ) and 
						a.nu_mc_id  =DECODE('$mid','ALL',A.NU_MC_ID,'$mid')  
						and a.vc_comp_code = '$session.companyCode' and 
						a.vc_category =DECODE( '$category','ALL',A.VC_CATEGORY,'$category')
						and a.dt_adv_ord_date  = TO_DATE('$fromDate','MM/DD/YYYY') 
						and a.br_code='$session.brCode'
						and a.br_code=b.br_code
						group by a.nu_mc_id , a.vc_category , a.vc_adv_ord_no
					"""
					Sql sql1 = new Sql(dataSource)
					def adResult = sql1.rows(adQry)
					if(adResult!=null && !adResult.isEmpty()){
						adResult.each {
							adVal = it.values()[0]
							dataMap.put("ADVANCE", it.values()[0])
						}
					}
				}else if(type99=='SA'){
					def saQry = """
						Select coalesce(sum(NU_AMOUNT),0) AS TEMP from SALES.dt_cash_payment a,SALES.HD_CASH B where
						a.VC_COMP_CODE = '$session.companyCode' and
						A.VC_COMP_CODE = B.VC_COMP_CODE AND
						a.DT_VOUCHER_DATE = TO_DATE('$fromDate','MM/DD/YYYY') and
						a.DT_VOUCHER_DATE = B.DT_VOUCHER_DATE AND
						A.VC_VOUCHER_NO = B.VC_VOUCHER_NO AND
						a.VC_CATEGORY =DECODE( '$category','ALL',A.VC_CATEGORY,'$category') and
						a.CH_PAY_MODE ='CB' AND
						A.VC_CATEGORY = B.VC_CATEGORY AND
						B.NU_MC_ID =DECODE('$mid','ALL',b.NU_MC_ID,'$mid')  AND
						B.CH_CANCEL_FLAG != 'Y'
						and a.br_code='$session.brCode'
						and a.br_code=b.br_code
					"""
					Sql sql9 = new Sql(dataSource)
					def saResult = sql9.rows(saQry)
					if(saResult!=null && !saResult.isEmpty()){
						saResult.each {
							saVal = it.values()[0]
							dataMap.put("SA", it.values()[0])
						}
					}					
				}
			}			
			totCashBck= adVal+saVal
			dataMap.put("mid", mid)
			dataMap.put("totCashBck", totCashBck)
			resultList.add(dataMap)
		}catch(Exception exception){
			log.info("Exception in ReportService getSummaryCashBackReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultList[0]
	}
	
	/**
	 * Method Name: getKPCashAnalysisReport
	 * Description: gets KPCashAnalysis data
	 *
	 * @return : list of cash analysis data
	 */
	def getKPCashAnalysisReport(kpCashAna,session){
		def fromDate,category,mid
		def x = kpCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			def str = new StringBuilder(" SELECT b.nu_mc_id , c.vc_short_name, b.inward_id , 'KP' TYPE , SUM( abs(b.amount) ) bill_amount , 0 discount ,");
				str.append(" SUM( DECODE( b.pay_mode , 'C' , b.amount , 0 )+abs(DECODE( b.pay_mode , 'CQB' , b.amount , 0 ))) cash ,");
				str.append(" SUM( DECODE( b.pay_mode , 'CB' , b.amount , 0 )) cashback ,");
				str.append(" SUM( DECODE( b.pay_mode , 'CQ' , b.amount , 0 )) cheque ,");
				str.append(" SUM( DECODE( b.pay_mode , 'FC' , b.amount , 0 )) foreigncurr ,");
				str.append(" SUM( DECODE( b.pay_mode , 'CC' , b.amount , 0 )) creditcard ,");
				str.append(" SUM( DECODE( b.pay_mode , 'A' , b.amount , 0 )) advance ,");
				str.append(" SUM( DECODE( b.pay_mode , 'KP' , b.amount , 0 )) kp_settle ,");
				str.append(" SUM( DECODE( b.pay_mode , 'GV' , b.amount , 0 )) giftv ,");
				str.append(" SUM( DECODE( b.pay_mode , 'L' , b.amount , 0  )) loan ,");
				str.append(" SUM( DECODE( b.pay_mode , 'B' , b.amount , 0 )) balance ,");
				str.append(" SUM( DECODE( b.pay_mode , 'TQ' , b.amount , 0 )) travel,");
				str.append(" SUM( DECODE( b.pay_mode , 'OG' , b.amount , 0 )) og_advance ,");
				str.append(" SUM( DECODE( b.pay_mode , 'SR' , b.amount , 0 )) sr_advance ,");
				str.append(" SUM( DECODE( b.pay_mode , 'T' , b.amount , 0 )) Tr_advance,");
				str.append(" SUM( DECODE( b.pay_mode , 'WB' , b.amount , 0 )) WB_advance ,");
				str.append(" SUM(DECODE( b.pay_mode , 'GC' , b.amount , 0 )) GCard");
				str.append(" FROM sales.reg_dt a, sales.in_dt b, sales.scheme_mst c");
				str.append(" WHERE a.reg_no = b.reg_no AND");
				str.append(" a.cust_id  = b.cust_id AND");
				str.append(" a.scheme_no = c.scheme_no AND");
				str.append(" a.ch_cancel_flag = 'N' AND");
				str.append(" b.pay_mode not in ('CQB') and");	
				str.append(" b.nu_mc_id =DECODE( '$mid','ALL',b.nu_mc_id,'$mid') AND");
				str.append(" b.inward_date  = TO_DATE('$fromDate','MM/DD/YYYY')  AND");
				str.append(" b.br_code = '$session.brCode'");
				str.append(" GROUP BY b.nu_mc_id , c.vc_short_name , b.inward_id");
			sql = new Sql(dataSource)
			result = sql.rows(str.toString())		
		}catch(Exception exception){
			log.info("Exception in ReportService getKPCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getADCashAnalysisReport
	 * Description: gets ADCashAnalysisReport data
	 *
	 * @return : list of cash analysis advance data
	 */
	def getADCashAnalysisReport(adCashAna,session){
		def fromDate,category,mid
		def x = adCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				SELECT a.nu_mc_id nu_mc_id , a.vc_category vc_category , a.vc_adv_ord_no , 'AD' TYPE , SUM( b.nu_amount ) bill_amount ,
				0 discount ,
				SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount ,  0 )) cash ,
				SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
				SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,
				SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
				SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
				SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 'OG' , b.nu_amount , 'OD' , b.nu_amount , 'SR' , b.nu_amount , 'ST' , b.nu_amount ,0 )) advance ,
				SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) KP_SETTLE ,
				SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
				SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
				SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
				SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
				SUM( DECODE( b.ch_pay_mode , 'OG' , b.nu_amount , 0 )) og_advance,
				SUM( DECODE( b.ch_pay_mode , 'SR' , b.nu_amount , 0 )) sr_advance,
				SUM( DECODE( b.ch_pay_mode , 'T' , b.nu_amount , 0 )) Tr_advance
				, SUM( DECODE( b.ch_pay_mode , 'WB' , b.nu_amount , 0 )) WB_advance ,
				SUM(DECODE( b.ch_pay_mode , 'GC' , nu_amount , 0 )) GC
				FROM sales.hd_sale_adv_ord a , sales.dt_sale_adv_pay b  WHERE
				a.vc_comp_code = b.vc_comp_code AND
				a.vc_adv_ord_no  = b.vc_adv_ord_no AND
				a.dt_adv_ord_date = b.dt_adv_ord_DATE
				AND a.ch_cancel_flag = 'N' AND
				( ch_active_flag = 'N' OR ch_active_flag = 'A' ) AND
				a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid')
				 AND a.vc_comp_code = '$session.companyCode' AND
				a.vc_category =DECODE( '$category','ALL',a.vc_category,'$category')
				 AND a.dt_adv_ord_date  = TO_DATE('$fromDate','MM/DD/YYYY')
				AND a.br_code='$session.brCode'
				AND a.br_Code=b.br_code
				GROUP BY a.nu_mc_id , a.vc_category , a.vc_adv_ord_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getADCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getMRCashAnalysisReport
	 * Description: gets MRCashAnalysisReport data
	 *
	 * @return : list of MISCELLANEOUS RECEIPT data
	 */
	def getMRCashAnalysisReport(mrCashAna,session){
		def fromDate,category,mid
		def x = mrCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				select nu_mc_id, vc_category, vc_voucher_no, 'MR' type,SUM(nu_amount ) bill_amount , 0 discount ,
				SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
				SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
				SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,
				SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
				SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
				SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
				SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
				SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
				SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
				SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
				SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
				SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
				SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
				SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance,
				SUM( DECODE( ch_pay_type , 'WB' , nu_amount , 0 )) WB_advance,
				SUM(DECODE( ch_pay_type , 'GC' , nu_amount , 0 )) GC
				from CHECK_BOUNCE
				where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
				and vc_category =DECODE('$category','ALL',vc_category,'$category')
				and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
				and br_code = '$session.brCode'
				group by nu_mc_id, vc_category, vc_voucher_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getMRCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSACashAnalysisReport
	 * Description: gets SACashAnalysisReport data
	 *
	 * @return : list of SACashAnalysis data
	 */
	def getSACashAnalysisReport(saCashAna,session){
		def fromDate,category,mid
		def x = saCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				SELECT a.nu_mc_id nu_mc_id , a.vc_category vc_category , a.vc_voucher_no vc_voucher_no , 'SA' TYPE ,
				0 bill_amount ,
				0 discount ,
				SUM( DECODE( c.ch_pay_mode , 'C' , ROUND(c.nu_amount) , 0 )) cash ,
				SUM( DECODE( c.ch_pay_mode , 'CB' , ROUND(c.nu_amount) , 0 )) cashback ,
				SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque ,
				SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr ,
				SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard ,
				SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance ,
				SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) KP_settle ,
				SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv ,
				SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan ,
				SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance ,
				SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel,
				SUM( DECODE( c.ch_pay_mode , 'OG' , c.nu_amount , 0 )) og_advance,
				SUM( DECODE( c.ch_pay_mode , 'SR' , c.nu_amount , 0 )) sr_advance,
				SUM( DECODE( c.ch_pay_mode , 'T' , c.nu_amount , 0 )) Tr_advance
				, SUM( DECODE( c.ch_pay_mode , 'WB' , c.nu_amount , 0 )) WB_advance,
				SUM(DECODE( c.ch_pay_mode , 'GC' , nu_amount , 0 )) GC FROM
				sales.hd_cash a , sales.dt_cash_payment c WHERE
				a.vc_comp_code = c.vc_comp_code  AND
				a.vc_voucher_no = c.vc_voucher_no AND
				a.dt_voucher_Date = c.dt_voucher_date AND
				a.ch_cancel_flag = 'N' AND
				a.vc_category = c.vc_category AND  
	            a.ch_gold_type <> 'S' AND
				a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid') AND a.vc_comp_code = '$session.companyCode' AND
				a.vc_category =DECODE( '$category','ALL',a.vc_category,'$category') AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
				AND a.br_code='$session.brCode' and
				a.br_Code=c.br_code
				GROUP BY a.nu_mc_id , a.vc_category , a.vc_voucher_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSACashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSSCashAnalysisReport
	 * Description: gets SSCashAnalysisReport data
	 *
	 * @return : list of SSCashAnalysis data
	 */
	def getSSCashAnalysisReport(ssCashAna,session){
		def fromDate,category,mid
		def x = ssCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				SELECT a.nu_mc_id nu_mc_id , a.vc_category vc_category , a.vc_voucher_no , 'SS' TYPE ,
				0 bill_amount ,
				0 discount ,
				SUM( DECODE( c.ch_pay_mode , 'C' , c.nu_amount , 0 )) cash ,
				SUM( DECODE( c.ch_pay_mode , 'CB' , ROUND(c.nu_amount) , 0 )) cashback ,
				SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque ,
				SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr ,
				SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard ,
				SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance ,
				SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) KP_SETTLE ,
				SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv ,
				SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan ,
				SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance ,
				SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel,
				SUM( DECODE( c.ch_pay_mode , 'OG' , c.nu_amount , 0 )) og_advance,
				SUM( DECODE( c.ch_pay_mode , 'SR' , c.nu_amount , 0 )) sr_advance,
				SUM( DECODE( c.ch_pay_mode , 'T' , c.nu_amount , 0 )) Tr_advance
				, SUM( DECODE( c.ch_pay_mode , 'WB' , c.nu_amount , 0 )) WB_advance,
				SUM(DECODE( c.ch_pay_mode , 'GC' , nu_amount , 0 )) GC
				FROM
				sales.hd_cash a , sales.dt_cash_payment c
				WHERE
				a.vc_comp_code = c.vc_comp_code  AND
				a.vc_voucher_no = c.vc_voucher_no AND
				a.dt_voucher_Date = c.dt_voucher_date AND
				a.vc_category = c.vc_category AND a.ch_gold_type = 'S' AND
				a.ch_cancel_flag = 'N' AND
				a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid') AND a.vc_comp_code = '$session.companyCode' AND
				a.vc_category =DECODE( '$category','ALL',a.vc_category,'$category') AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
				AND a.br_code='$session.brCode' AND
				a.br_Code=c.br_code
				GROUP BY a.nu_mc_id , a.vc_category , a.vc_voucher_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSSCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPBCashAnalysisReport
	 * Description: gets PBCashAnalysisReport data
	 *
	 * @return : list of PBCashAnalysis data
	 */
	def getPBCashAnalysisReport(pbCashAna,session){
		def fromDate,category,mid
		def x = pbCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				SELECT a.nu_mc_id , a.vc_category , a.vc_bill_no , 'PB' , SUM(b.nu_amount) , 0 , SUM(DECODE(b.ch_pay_mode,'C',b.nu_amount,0)) cash,
				0 cashback , SUM(DECODE(b.Ch_pay_mode,'CQ',b.nu_amount,0)) cheque , 0 , 0 , 0 ,0 , 0 , 0 , SUM(DECODE(b.ch_pay_mode,'B',b.nu_amount,0))  , 0, 0, 0, 0, 0, 0 FROM
				sales.hd_bill a , sales.dt_pb_payment b WHERE a.vc_comp_code = b.vc_comp_code AND
				a.vc_bill_no = b.vc_bill_no
				AND a.dt_bill_date = b.dt_bill_date
				AND a.vc_comp_code = '$session.companyCode'
				AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',a.vc_category,'$category')
				AND a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid')
				AND a.nu_mc_id LIKE a.nu_mc_id
				AND vc_type_of_bill IN ('PB','VP')  AND vc_gold_type = 'N' AND
				a.vc_cancel_flag <>'Y'
				AND a.br_code='$session.brCode'
				AND a.br_Code=b.br_code
				GROUP BY nu_mc_id , a.vc_category , a.vc_bill_no
				UNION
				SELECT a.nu_mc_id , a.vc_category , a.vc_bill_no , 'PS' , SUM(b.nu_amount) , 0 , SUM(DECODE(b.ch_pay_mode,'C',b.nu_amount,0)) cash,
				0 cashback , SUM(DECODE(b.Ch_pay_mode,'CQ',b.nu_amount,0)) cheque , 0 , 0 , 0 ,0 , 0 , 0 , SUM(DECODE(b.ch_pay_mode,'B',b.nu_amount,0))  , 0, 0 , 0 ,0 ,0, 0
				FROM
				sales.hd_bill a , sales.dt_pb_payment b
				WHERE a.vc_comp_code = b.vc_comp_code and
				a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode'
				AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',a.vc_category,'$category')
				AND a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid')
				AND a.nu_mc_id LIKE a.nu_mc_id
				AND vc_type_of_bill IN ( 'PB','VP') AND vc_gold_type = 'S' AND
				a.vc_cancel_flag <>'Y'
				AND a.br_code='$session.brCode'
				AND a.br_Code=b.br_code
				GROUP BY nu_mc_id , a.vc_category , a.vc_bill_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getPBCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPRCashAnalysisReport
	 * Description: gets PRCashAnalysisReport data
	 *
	 * @return : list of PRCashAnalysis data
	 */
	def getPRCashAnalysisReport(prCashAna,session){
		def fromDate,category,mid
		def x = prCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				 SELECT a.nu_mc_id , a.vc_category , a.vc_bill_no , 'PR' , SUM(a.nu_amount) , 0 , NVL(SUM(a.nu_cash_amt),0) cash ,
				0 cashback , NVL(SUM(a.nu_chq_amt),0) cheque , 0 , 0 , 0 ,0 , 0 , 0 , 0 ,0, 0, 0,0 ,0, 0 FROM
				sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
				a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '02'
				AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE( '$category','ALL',a.vc_category,'$category')
				AND a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid')
				AND vc_type_of_bill = 'PR'
				AND a.vc_cancel_flag <> 'Y'
				AND a.br_code='$session.brCode'
				AND a.br_Code=b.br_code
				GROUP BY nu_mc_id , a.vc_category , a.vc_bill_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getPRCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getGVCashAnalysisReport
	 * Description: gets GVCashAnalysisReport data
	 *
	 * @return : list of GVCashAnalysis data
	 */
	def getGVCashAnalysisReport(gvCashAna,session){
		def fromDate,category,mid
		def x = gvCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				 SELECT a.nu_mc_id ,NULL   vc_category_code , a.vc_voucher_no , 'GV' TYPE ,
				SUM(a.nu_amount) bill_amount ,
				0 discount ,
				SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
				SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
				SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,
				SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
				SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
				SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
				SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) kp_settle ,
				SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
				SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
				SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
				SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
				SUM( DECODE( b.ch_pay_mode , 'OG' , b.nu_amount , 0 )) og_advance,
				SUM( DECODE( b.ch_pay_mode , 'SR' , b.nu_amount , 0 )) sr_advance ,
				SUM( DECODE( b.ch_pay_mode , 'T' , b.nu_amount , 0 )) Tr_advance,
				SUM( DECODE( b.ch_pay_mode , 'WB' , b.nu_amount , 0 )) WB_advance ,
				SUM(DECODE( b.ch_pay_mode , 'GC' , b.nu_amount , 0 )) GC
				FROM
				sales.mst_vouch_activation a , sales.gift_voucher_payment b WHERE a.vc_comp_code = $session.companyCode and
				a.vc_comp_code = b.vc_comp_code AND
				a.dt_issue_date = b.dt_voucher_date
				AND a.vc_voucher_no = b.vc_voucher_no
				AND a.dt_issue_date = TO_DATE('$fromDate','MM/DD/YYYY') AND
				 a.vc_category_code =DECODE( '$category','ALL',a.vc_category_code,'$category') AND
				 a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid')
				AND a.br_code='$session.brCode'
				AND a.br_Code=b.br_code
				GROUP BY a.nu_mc_id , a.vc_category_code , a.vc_voucher_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getGVCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getGCCashAnalysisReport
	 * Description: gets GCCashAnalysisReport data
	 *
	 * @return : list of GCCashAnalysis data
	 */
	def getGCCashAnalysisReport(gcCashAna,session){
		def fromDate,category,mid
		def x = gcCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				 select nu_mc_id, 'GC', to_char(receipt_number), 'GC' type, SUM(nu_amount) bill_amount , 0 discount,
				SUM(DECODE(ch_pay_mode , 'C' ,  nu_amount , 0 )) cash ,
				SUM(DECODE(ch_pay_mode , 'CB' , nu_amount , 0 )) cashback ,
				SUM(DECODE(ch_pay_mode , 'CQ' , nu_amount , 0 )) cheque ,
				SUM(DECODE(ch_pay_mode , 'FC' , nu_amount , 0 )) foreigncurr ,
				SUM(DECODE(ch_pay_mode , 'CC' , nu_amount , 0 )) creditcard ,
				SUM(DECODE(ch_pay_mode , 'A' ,  nu_amount , 0 )) advance ,
				SUM(DECODE(ch_pay_mode , 'KP' , nu_amount , 0 )) KP_SETTLE ,
				SUM(DECODE(ch_pay_mode , 'GV' , nu_amount , 0 )) giftv ,
				SUM(DECODE(ch_pay_mode , 'L' ,  nu_amount , 0 )) loan ,
				SUM(DECODE(ch_pay_mode , 'B' ,  nu_amount , 0 )) balance ,
				SUM(DECODE(ch_pay_mode , 'TQ' , nu_amount , 0 )) travel,
				SUM(DECODE(ch_pay_mode , 'OG' , nu_amount , 0 )) og_advance,
				SUM(DECODE(ch_pay_mode , 'SR' , nu_amount , 0 )) sr_advance,
				SUM(DECODE(ch_pay_mode , 'T' ,  nu_amount , 0 )) Tr_advance,
				SUM(DECODE( ch_pay_mode , 'WB' , nu_amount , 0 )) WB_advance,
				SUM(DECODE( ch_pay_mode , 'GC' , nu_amount , 0 )) GC
				from dt_gift_card
				where receipt_date = TO_DATE('$fromDate','MM/DD/YYYY')
				and nu_mc_id = DECODE( '$mid','ALL',nu_mc_id,'$mid') and
				ch_cancel_flag = 'N'
				and br_code = '$session.brCode'
				group by nu_mc_id, to_char(receipt_number)
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getGCCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getDRCashAnalysisReport
	 * Description: gets DRCashAnalysisReport data
	 *
	 * @return : list of DRCashAnalysis data
	 */
	def getDRCashAnalysisReport(drCashAna,session){
		def fromDate,category,mid
		def x = drCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				 select nu_mc_id, vc_category, vc_voucher_no, 'DR' type,SUM(nu_amount ) bill_amount , 0 discount ,
					SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
					SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
					SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,
					SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
					SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
					SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
					SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
					SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
					SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
					SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
					SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
					SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
					SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
					SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance,
		      SUM( DECODE( ch_pay_type , 'WB' , nu_amount , 0 )) WB_advance,
					SUM(DECODE( ch_pay_type , 'GC' , nu_amount , 0 )) GC
					from xxpos_donation_receipts
					where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
					and vc_category =DECODE('$category','ALL',vc_category,'$category')
					and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
					and br_code = '$session.brCode'
					group by nu_mc_id, vc_category, vc_voucher_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getDRCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getATWBCashAnalysisReport
	 * Description: gets ATWBCashAnalysisReport data
	 *
	 * @return : list of ATWBCashAnalysis data
	 */
	def getATWBCashAnalysisReport(atwbCashAna,session){
		def fromDate,category,mid
		def x = atwbCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				 SELECT a.nu_mc_id nu_mc_id , a.vc_category vc_category , a.vc_adv_ord_no , decode(b.ch_pay_mode,'T','AT','WB','WB') TYPE , SUM( b.nu_amount ) bill_amount ,0 discount ,
				SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
				SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
				SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,
				SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
				SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
				SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
				SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) KP_SETTLE ,
				SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
				SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
				SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
				SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
				SUM( DECODE( b.ch_pay_mode , 'OG' , b.nu_amount , 0 )) og_advance,
				SUM( DECODE( b.ch_pay_mode , 'SR' , b.nu_amount , 0 )) sr_advance,
				SUM( DECODE( b.ch_pay_mode , 'T' , b.nu_amount , 0 )) Tr_advance,
				SUM( DECODE( b.ch_pay_mode , 'WB' , b.nu_amount , 0 )) WB_advance,
				SUM(DECODE( b.ch_pay_mode , 'GC' , nu_amount , 0 )) GC   FROM
				sales.hd_sale_adv_ord a , sales.dt_sale_adv_pay b  WHERE
				a.vc_comp_code = b.vc_comp_code AND
				a.vc_adv_ord_no  = b.vc_adv_ord_no AND
				a.dt_adv_ord_date = b.dt_adv_ord_DATE
				AND a.ch_cancel_flag = 'N' AND
				( ch_active_flag = 'N' OR ch_active_flag = 'A' ) AND
				a.nu_mc_id =DECODE( '$mid','ALL',a.nu_mc_id,'$mid')
				 AND a.vc_comp_code = '$session.companyCode' AND
				a.vc_category =DECODE( '$category','ALL',a.vc_category,'$category')
				 AND a.dt_adv_ord_date  = TO_DATE('$fromDate','MM/DD/YYYY')
				AND a.br_code='$session.brCode'
				AND a.br_Code=b.br_code
				and b.ch_pay_mode IN ('T','WB')
				GROUP BY a.nu_mc_id , a.vc_category , a.vc_adv_ord_no, b.ch_pay_mode
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getATWBCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSumCashAnalysisReport
	 * Description: gets SumCashAnalysisReport data
	 *
	 * @return : list of SumCashAnalysis data
	 */
	def getSumCashAnalysisReport(sumCashAna,session){
		def fromDate,category,mid
		def x = sumCashAna
		try{
			fromDate=x?.fromDate								
			if(x?.category){				
				category = getCategory(category)
			}else{
				category = 'ALL'
			}
			if(x?.mid){
				mid = x?.mid
			}else{
				mid = 'ALL'
			}
			query =
				"""
				 SELECT  a.vc_category vc_category9 , a.vc_voucher_no vc_voucher_no9 , 'SA' type9 ,
			0 bill_amount9 ,
			0 discount9 ,
			SUM( DECODE( c.ch_pay_mode , 'C' ,c.nu_amount , 0 ))  cash9 ,
			SUM( DECODE( c.ch_pay_mode , 'CB' , c.nu_amount , 0 )) cashback9 ,
			SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque9 ,
			SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr9 ,
			SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard9 ,
			SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance9 ,
			SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) kp_settle9 ,
			SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv9 ,
			SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan9 ,
			SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance9 ,
			SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel9,
			SUM( DECODE( c.ch_pay_mode , 'OG' , c.nu_amount , 0 )) og_advance9,
			SUM( DECODE( c.ch_pay_mode , 'SR' , c.nu_amount , 0 )) sr_advance9,
			SUM(DECODE(c.ch_pay_mode , 'T' ,  nu_amount , 0 )) Tr_advance
			, SUM(DECODE(c.ch_pay_mode , 'WB' ,  nu_amount , 0 )) WB_advance,
			SUM(DECODE(c.ch_pay_mode , 'GC' , nu_amount , 0 )) GC
			FROM
			sales.hd_cash a , sales.dt_cash_payment c WHERE
			a.vc_comp_code = c.vc_comp_code  AND
			a.vc_voucher_no = c.vc_voucher_no AND
			a.dt_voucher_Date = c.dt_voucher_date AND
			a.ch_cancel_flag = 'N' AND
			a.vc_category = c.vc_category AND a.ch_gold_type <> 'S' and
			a.nu_mc_id =DECODE('$mid','ALL',a.nu_mc_id,'$mid') AND a.vc_comp_code = '$session.companyCode' AND
     a.vc_category =DECODE('$category','ALL',a.vc_category,'$category') AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
			AND A.BR_CODE  ='$session.brCode'
			AND a.br_Code=c.br_code
			GROUP BY a.vc_category , a.vc_voucher_no
			UNION
			SELECT  a.vc_category vc_category , a.vc_voucher_no , 'SS' TYPE ,
			0 bill_amount ,
			0 discount ,
			SUM( DECODE( c.ch_pay_mode , 'C' , c.nu_amount , 0 )) cash ,
			SUM( DECODE( c.ch_pay_mode , 'CB' , c.nu_amount , 0 )) cashback ,
			SUM( DECODE( c.ch_pay_mode , 'CQ' , c.nu_amount , 0 )) cheque ,
			SUM( DECODE( c.ch_pay_mode , 'FC' , c.nu_amount , 0 )) foreigncurr ,
			SUM( DECODE( c.ch_pay_mode , 'CC' , c.nu_amount , 0 )) creditcard ,
			SUM( DECODE( c.ch_pay_mode , 'A' , c.nu_amount , 0 )) advance ,
			SUM( DECODE( c.ch_pay_mode , 'KP' , c.nu_amount , 0 )) kp_settle ,
			SUM( DECODE( c.ch_pay_mode , 'GV' , c.nu_amount , 0 )) giftv ,
			SUM( DECODE( c.ch_pay_mode , 'L' , c.nu_amount , 0 )) loan ,
			SUM( DECODE( c.ch_pay_mode , 'B' , c.nu_amount , 0 )) balance ,
			SUM( DECODE( c.ch_pay_mode , 'TQ' , c.nu_amount , 0 )) travel,
			SUM( DECODE( c.ch_pay_mode , 'OG' , c.nu_amount , 0 )) og_advance,
			SUM( DECODE( c.ch_pay_mode , 'SR' , c.nu_amount , 0 )) sr_advance,
			SUM(DECODE(c.ch_pay_mode , 'T' ,  nu_amount , 0 )) Tr_advance
			, SUM(DECODE(c.ch_pay_mode , 'WB' ,  nu_amount , 0 )) WB_advance,
			SUM(DECODE(c.ch_pay_mode , 'GC' , nu_amount , 0 )) GC
			FROM
			sales.hd_cash a , sales.dt_cash_payment c WHERE
			a.vc_comp_code = c.vc_comp_code  AND
			a.vc_voucher_no = c.vc_voucher_no AND
			a.ch_cancel_flag = 'N' AND
			a.dt_voucher_Date = c.dt_voucher_date AND
			a.vc_category = c.vc_category AND a.ch_gold_type = 'S' and
			a.nu_mc_id  =DECODE('$mid','ALL',a.nu_mc_id,'$mid')
			 AND a.vc_comp_code = '$session.companyCode' AND
			a.vc_category =DECODE('$category','ALL',a.vc_category,'$category') AND a.dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
			AND A.BR_CODE  ='$session.brCode'
			AND a.br_Code=c.br_code
			GROUP BY  a.vc_category , a.vc_voucher_no
			UNION
			SELECT  a.vc_category , a.vc_bill_no , 'OG' TYPE , SUM(a.nu_amount) , 0 discount , SUM(a.nu_cash_amt) cash ,
			0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0, 0, 0, 0  ,0, 0 FROM
			sales.hd_bill a
			WHERE 
			a.nu_mc_id LIKE a.nu_mc_id
			AND a.nu_mc_id =DECODE('$mid','ALL',a.nu_mc_id,'$mid')
			AND vc_type_of_bill = 'OG' AND a.vc_cancel_flag<>'Y'
			AND A.BR_CODE  ='$session.brCode'
			GROUP BY  vc_category , a.vc_bill_no
			UNION
			SELECT  a.vc_category , a.vc_bill_no , 'PB' , NVL(SUM(b.nu_amount),0) balance9 , 0 ,
			SUM(DECODE(b.ch_pay_mode,'C',b.nu_amount,0)) cash ,
			0 cashback , SUM(DECODE(b.ch_pay_mode,'CQ',b.nu_amount,0)) cheque , 0 , 0 , 0 , 0 , 0 , 0 , NVL(SUM(b.nu_amount),0) ,0 ,0 ,0,0,0, 0
			FROM sales.hd_bill a , sales.dt_pb_payment b WHERE a.vc_comp_code = b.vc_comp_code AND
			a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode'
			AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE('$category','ALL',a.vc_category,'$category')
			AND a.nu_mc_id LIKE a.nu_mc_id
			AND a.nu_mc_id  =DECODE('$mid','ALL',a.nu_mc_id,'$mid')
			AND vc_type_of_bill  IN ('PB','VP') AND vc_gold_type = 'N'
			AND a.vc_cancel_flag <>'Y'
			AND A.BR_CODE  ='$session.brCode'
			AND a.br_Code=b.br_code
			GROUP BY  a.vc_category , a.vc_bill_no
			UNION
			SELECT  a.vc_category , a.vc_bill_no , 'PS' , NVL(SUM(a.nu_amount),0) ,0, NVL(SUM(a.nu_cash_amt),0) cash ,
			0 cashback , NVL(SUM(a.nu_chq_amt),0) cheque ,0, 0 , 0 , 0 , 0 , 0 ,
			NVL(SUM(a.nu_amount),0) - (NVL(SUM(a.nu_cash_amt),0)+NVL(SUM(a.nu_chq_amt),0)) balance,0, 0, 0, 0 ,0, 0
			FROM sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code AND
			a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode'
			AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE('$category','ALL',a.vc_category,'$category')
			AND a.nu_mc_id LIKE a.nu_mc_id
			AND a.nu_mc_id  =DECODE('$mid','ALL',a.nu_mc_id,'$mid')
			AND vc_type_of_bill IN ( 'PB','VP') AND vc_gold_type = 'S'
			AND a.vc_cancel_flag <>'Y'
			AND A.BR_CODE  ='$session.brCode'
			AND a.br_Code=b.br_code
			GROUP BY  a.vc_category , a.vc_bill_no
			UNION
			SELECT  a.vc_category , a.vc_bill_no , 'PR' , SUM(a.nu_amount) ,0, SUM(a.nu_cash_amt) cash ,
			0 cashback , SUM(a.nu_chq_amt) cheque , 0 , 0 , 0 , 0 , 0 , 0 , SUM(a.nu_amount)  ,0 ,0 ,0, 0    ,0, 0 FROM
			sales.hd_bill a , sales.dt_bill b WHERE a.vc_comp_code = b.vc_comp_code and
			a.vc_bill_no = b.vc_bill_no AND a.dt_bill_date = b.dt_bill_date AND a.vc_comp_code = '$session.companyCode'
			AND a.dt_bill_date = TO_DATE('$fromDate','MM/DD/YYYY') AND a.vc_category =DECODE('$category','ALL',a.vc_category,'$category')
			AND a.nu_mc_id LIKE a.nu_mc_id
			AND a.nu_mc_id  =DECODE('$mid','ALL',a.nu_mc_id,'$mid')
			AND vc_type_of_bill  IN ( 'PR' ,'VV')
			AND a.vc_cancel_flag <> 'Y'
			AND A.BR_CODE  ='$session.brCode'
			AND a.br_Code=b.br_code
			GROUP BY  a.vc_category , a.vc_bill_no
			UNION
			SELECT  a.vc_category vc_category , a.vc_adv_ord_no , decode(b.ch_pay_mode,'T','AT','WB','WB','AD') TYPE , SUM( b.nu_amount ) bill_amount ,
			0 discount ,
			SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
			SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
			SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,
			SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
			SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
			SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
			SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) kp_settle ,
			SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
			SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
			SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
			SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
			SUM( DECODE( b.ch_pay_mode , 'OG' , b.nu_amount , 0 )) og_advance,
			SUM( DECODE( b.ch_pay_mode , 'SR' , b.nu_amount , 0 )) sr_advance,
			SUM(DECODE(b.ch_pay_mode , 'T' ,  nu_amount , 0 )) Tr_advance
			, SUM(DECODE(b.ch_pay_mode , 'WB' ,  nu_amount , 0 )) WB_advance,
			SUM(DECODE(b.ch_pay_mode, 'GC' , nu_amount , 0 )) GC
			FROM sales.hd_sale_adv_ord a , sales.dt_sale_adv_pay b  WHERE
			a.vc_comp_code = b.vc_comp_code AND
			a.vc_adv_ord_no  = b.vc_adv_ord_no AND
			a.dt_adv_ord_date = b.dt_adv_ord_DATE
			AND a.nu_mc_id  =DECODE('$mid','ALL',a.nu_mc_id,'$mid')
			AND a.ch_cancel_flag = 'N' and ( ch_active_flag = 'N' OR ch_active_flag = 'A' ) and
			a.nu_mc_id LIKE 'ALL' AND a.vc_comp_code = '$session.companyCode' AND
			a.vc_category =DECODE('$category','ALL',a.vc_category,'$category')
			 AND a.dt_adv_ord_date  = TO_DATE('$fromDate','MM/DD/YYYY')
			AND A.BR_CODE  ='$session.brCode'
			AND a.br_Code=b.br_code
			GROUP BY  a.vc_category , a.vc_adv_ord_no, b.ch_pay_mode
			UNION
			SELECT  NULL vc_category_code , a.vc_voucher_no , 'GV' TYPE ,
			SUM(a.nu_amount) bill_amount ,
			0 discount ,
			SUM( DECODE( b.ch_pay_mode , 'C' , b.nu_amount , 0 )) cash ,
			SUM( DECODE( b.ch_pay_mode , 'CB' , b.nu_amount , 0 )) cashback ,
			SUM( DECODE( b.ch_pay_mode , 'CQ' , b.nu_amount , 0 )) cheque ,
			SUM( DECODE( b.ch_pay_mode , 'FC' , b.nu_amount , 0 )) foreigncurr ,
			SUM( DECODE( b.ch_pay_mode , 'CC' , b.nu_amount , 0 )) creditcard ,
			SUM( DECODE( b.ch_pay_mode , 'A' , b.nu_amount , 0 )) advance ,
			SUM( DECODE( b.ch_pay_mode , 'KP' , b.nu_amount , 0 )) kp_settle ,
			SUM( DECODE( b.ch_pay_mode , 'GV' , b.nu_amount , 0 )) giftv ,
			SUM( DECODE( b.ch_pay_mode , 'L' , b.nu_amount , 0 )) loan ,
			SUM( DECODE( b.ch_pay_mode , 'B' , b.nu_amount , 0 )) balance ,
			SUM( DECODE( b.ch_pay_mode , 'TQ' , b.nu_amount , 0 )) travel,
			SUM( DECODE( b.ch_pay_mode , 'OG' , b.nu_amount , 0 )) og_advance,
			SUM( DECODE( b.ch_pay_mode , 'SR' , b.nu_amount , 0 )) sr_advance,
			SUM(DECODE(b.ch_pay_mode , 'T' ,  b.nu_amount , 0 )) Tr_advance
			, SUM(DECODE(b.ch_pay_mode , 'WB' ,  b.nu_amount , 0 )) WB_advance,
			SUM(DECODE(b.ch_pay_mode , 'GC' , b.nu_amount , 0 )) GC
			FROM
			sales.mst_vouch_activation a , sales.gift_voucher_payment b
			WHERE a.vc_comp_code = '$session.companyCode' AND
			a.vc_comp_code = b.vc_comp_code
			AND a.dt_issue_date = b.dt_voucher_date
			AND a.vc_voucher_no = b.vc_voucher_no
			AND a.dt_issue_date = TO_DATE('$fromDate','MM/DD/YYYY') AND
			a.vc_category_code =DECODE('$category','ALL',a.vc_category_CODE,'$category') 
			AND a.nu_mc_id LIKE a.nu_mc_id
			AND a.nu_mc_id  =DECODE('$mid','ALL',a.nu_mc_id,'$mid')
			AND A.BR_CODE  ='$session.brCode'
			AND a.br_Code=b.br_code
			GROUP BY  a.vc_category_code , a.vc_voucher_no
			UNION
			SELECT  c.vc_short_name, b.inward_id , 'KP' TYPE , SUM( abs(b.amount)) bill_amount , 0 discount ,
			SUM( DECODE( b.pay_mode , 'C' , b.amount , 0 )+abs(DECODE( b.pay_mode , 'CQB' , b.amount , 0 ))) cash ,
			SUM( DECODE( b.pay_mode , 'CB' , b.amount , 0 )) cashback ,
			SUM( DECODE( b.pay_mode , 'CQ' , b.amount , 0 )) cheque ,
			SUM( DECODE( b.pay_mode , 'FC' , b.amount , 0 )) foreigncurr ,
			SUM( DECODE( b.pay_mode , 'CC' , b.amount , 0 )) creditcard ,
			SUM( DECODE( b.pay_mode , 'A' , b.amount , 0 )) advance ,
			SUM( DECODE( b.pay_mode , 'KP' , b.amount , 0 )) kp_settle ,
			SUM( DECODE( b.pay_mode , 'GV' , b.amount , 0 )) giftv ,
			SUM( DECODE( b.pay_mode , 'L' , b.amount , 0 )) loan ,
			SUM( DECODE( b.pay_mode , 'B' , b.amount , 0 )) balance ,
			SUM( DECODE( b.pay_mode , 'TQ' , b.amount , 0 )) travel,
			SUM( DECODE( b.pay_mode , 'OG' , b.amount , 0 )) og_advance,
			SUM( DECODE( b.pay_mode , 'SR' , b.amount , 0 )) sr_advance,
			SUM(DECODE( b.pay_mode , 'T' ,  b.amount , 0 )) Tr_advance
			, SUM(DECODE(b.pay_mode , 'WB' ,  b.amount , 0 )) WB_advance,
			SUM(DECODE(b.pay_mode  , 'GC' , b.amount , 0 )) GC
			FROM sales.reg_dt a, sales.in_dt b, sales.scheme_mst c
			WHERE a.reg_no = b.reg_no AND
			a.cust_id  = b.cust_id AND
			a.scheme_no = c.scheme_no AND
			a.ch_cancel_flag = 'N' AND
			b.pay_mode not in ('CQB') and
			b.nu_mc_id =DECODE( '$mid','ALL',b.nu_mc_id,'$mid') AND
			b.inward_date  = TO_DATE('$fromDate','MM/DD/YYYY')  AND
			b.br_code = '$session.brCode'
			GROUP BY c.vc_short_name , b.inward_id
			UNION
			select vc_category, vc_voucher_no, 'MR' type, SUM(nu_amount) bill_amount , 0 discount ,
			SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
			SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
			SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,
			SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
			SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
			SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
			SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
			SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
			SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
			SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
			SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
			SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
			SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
			SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance
			, SUM(DECODE(ch_pay_type , 'WB' ,  nu_amount , 0 )) WB_advance,
			SUM(DECODE(ch_pay_type , 'GC' , nu_amount , 0 )) GC
			from CHECK_BOUNCE
			where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
			and vc_category =DECODE('$category','ALL',vc_category,'$category')
			and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
			and br_code = '$session.brCode'
			group by nu_mc_id, vc_category, vc_voucher_no
			UNION ALL
			select 'GC', to_char(receipt_number), 'GC' type, SUM(nu_amount) bill_amount , 0 discount,
			SUM(DECODE(ch_pay_mode , 'C' ,  nu_amount , 0 )) cash ,
			SUM(DECODE(ch_pay_mode , 'CB' , nu_amount , 0 )) cashback ,
			SUM(DECODE(ch_pay_mode , 'CQ' , nu_amount , 0 )) cheque ,
			SUM(DECODE(ch_pay_mode , 'FC' , nu_amount , 0 )) foreigncurr ,
			SUM(DECODE(ch_pay_mode , 'CC' , nu_amount , 0 )) creditcard ,
			SUM(DECODE(ch_pay_mode , 'A' ,  nu_amount , 0 )) advance ,
			SUM(DECODE(ch_pay_mode , 'KP' , nu_amount , 0 )) KP_SETTLE ,
			SUM(DECODE(ch_pay_mode , 'GV' , nu_amount , 0 )) giftv ,
			SUM(DECODE(ch_pay_mode , 'L' ,  nu_amount , 0 )) loan ,
			SUM(DECODE(ch_pay_mode , 'B' ,  nu_amount , 0 )) balance ,
			SUM(DECODE(ch_pay_mode , 'TQ' , nu_amount , 0 )) travel,
			SUM(DECODE(ch_pay_mode , 'OG' , nu_amount , 0 )) og_advance,
			SUM(DECODE(ch_pay_mode , 'SR' , nu_amount , 0 )) sr_advance,
			SUM(DECODE(ch_pay_mode , 'T' ,  nu_amount , 0 )) Tr_advance,
			SUM(DECODE(ch_pay_mode , 'WB' , nu_amount , 0 )) WB_advance,
			SUM(DECODE(ch_pay_mode , 'GC' , nu_amount , 0 )) GC
			from dt_gift_card
			where receipt_date = TO_DATE('$fromDate','MM/DD/YYYY') and
			 nu_mc_id = DECODE( '$mid','ALL',nu_mc_id,'$mid') and
			ch_cancel_flag = 'N'
			and br_code = '$session.brCode'
			group by to_char(receipt_number)
			UNION ALL
			select vc_category, vc_voucher_no, 'DR' type, SUM(nu_amount) bill_amount , 0 discount ,
			SUM(DECODE(ch_pay_type , 'C' ,  nu_amount , 0 )) cash ,
			SUM(DECODE(ch_pay_type , 'CB' , nu_amount , 0 )) cashback ,
			SUM(DECODE(ch_pay_type , 'CQ' , nu_amount , 0 )) cheque ,
			SUM(DECODE(ch_pay_type , 'FC' , nu_amount , 0 )) foreigncurr ,
			SUM(DECODE(ch_pay_type , 'CC' , nu_amount , 0 )) creditcard ,
			SUM(DECODE(ch_pay_type , 'A' ,  nu_amount , 0 )) advance ,
			SUM(DECODE(ch_pay_type , 'KP' , nu_amount , 0 )) KP_SETTLE ,
			SUM(DECODE(ch_pay_type , 'GV' , nu_amount , 0 )) giftv ,
			SUM(DECODE(ch_pay_type , 'L' ,  nu_amount , 0 )) loan ,
			SUM(DECODE(ch_pay_type , 'B' ,  nu_amount , 0 )) balance ,
			SUM(DECODE(ch_pay_type , 'TQ' , nu_amount , 0 )) travel,
			SUM(DECODE(ch_pay_type , 'OG' , nu_amount , 0 )) og_advance,
			SUM(DECODE(ch_pay_type , 'SR' , nu_amount , 0 )) sr_advance,
			SUM(DECODE(ch_pay_type , 'T' ,  nu_amount , 0 )) Tr_advance
			, SUM(DECODE(ch_pay_type , 'WB' ,  nu_amount , 0 )) WB_advance,
			SUM(DECODE(ch_pay_type , 'GC' , nu_amount , 0 )) GC
			from xxpos_donation_receipts
			where dt_voucher_date = TO_DATE('$fromDate','MM/DD/YYYY')
			and vc_category =DECODE('$category','ALL',vc_category,'$category')
			and nu_mc_id =DECODE( '$mid','ALL',nu_mc_id,'$mid')
			and br_code = '$session.brCode'
			group by nu_mc_id, vc_category, vc_voucher_no
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSumCashAnalysisReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getMRCashAnalysisReport
	 * Description: gets MRCashAnalysisReport data
	 *
	 * @return : list of MISCELLANEOUS RECEIPT data
	 */
	def getKpPayModeWiseDetailReport(fromDate,toDate,schemeNo,paymode){
		def result1
		try{
			query =
				"""
				SELECT a.INWARD_DATE, a.INWARD_ID, a.CUST_ID, b.FNAME||' '||b.MNAME||' '||b.LNAME Cust_name, a.REG_NO, a.SCHEME_NO, 
				c.SCHEME_NAME, d.vc_pay_mode,a.vc_cheque,a.pay_date,SUM(a.AMOUNT)Amt 
				FROM in_dt a,cust_mst b,scheme_mst c,mst_pay d
				WHERE a.inward_DATE BETWEEN '01-JAN-2015' AND '01-JAN-2016'
				AND a.br_code = d.br_code
				AND a.CUST_ID = b.CUST_ID
				AND a.SCHEME_NO = c.SCHEME_NO
				AND a.pay_mode = d.vc_pay_MODE
				AND a.BR_CODE = DECODE('82','ALL',a.BR_CODE,'82')
				AND a.SCHEME_NO = DECODE('01','ALL',a.SCHEME_NO,'01')
				and a.scheme_no is not null
				AND d.ch_tran_type = 'KP'
				AND a.pay_mode = DECODE('C','ALL',a.pay_mode,'C')
				GROUP BY a.BR_CODE, a.INWARD_DATE, a.INWARD_ID, a.CUST_ID, b.FNAME||' '||b.MNAME||' '||b.LNAME, a.REG_NO, a.SCHEME_NO,
				a.vc_cheque,d.vc_pay_mode,c.SCHEME_NAME,a.pay_date
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getKpPayModeWiseDetailReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getEmployeeIncentiveReport
	 * Description: gets Employee IncentiveReport data
	 *
	 * @return : list of employee incentive data
	 */
	def getEmployeeIncentiveReport(fromDate,toDate,branchNo,type){
		def result1
		try{
			query =
				"""
				SELECT a.INWARD_DATE, a.INWARD_ID, a.CUST_ID, b.FNAME||' '||b.MNAME||' '||b.LNAME Cust_name, a.REG_NO, a.SCHEME_NO, 
				c.SCHEME_NAME, d.vc_pay_mode,a.vc_cheque,a.pay_date,SUM(a.AMOUNT)Amt 
				FROM in_dt a,cust_mst b,scheme_mst c,mst_pay d
				WHERE a.inward_DATE BETWEEN '01-JAN-2015' AND '01-JAN-2016'
				AND a.br_code = d.br_code
				AND a.CUST_ID = b.CUST_ID
				AND a.SCHEME_NO = c.SCHEME_NO
				AND a.pay_mode = d.vc_pay_MODE
				AND a.BR_CODE = DECODE('82','ALL',a.BR_CODE,'82')
				AND a.SCHEME_NO = DECODE('01','ALL',a.SCHEME_NO,'01')
				and a.scheme_no is not null
				AND d.ch_tran_type = 'KP'
				AND a.pay_mode = DECODE('C','ALL',a.pay_mode,'C')
				GROUP BY a.BR_CODE, a.INWARD_DATE, a.INWARD_ID, a.CUST_ID, b.FNAME||' '||b.MNAME||' '||b.LNAME, a.REG_NO, a.SCHEME_NO,
				a.vc_cheque,d.vc_pay_mode,c.SCHEME_NAME,a.pay_date
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getKpPayModeWiseDetailReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getEmployeeIncentiveReport
	 * Description: gets Employee IncentiveReport data
	 *
	 * @return : list of employee incentive data
	 */
	def getEmployeePerformanaceReport(fromDate,toDate,branchNo,schemeName){
		def result1
		try{
			query =
				"""
				SELECT a.INWARD_DATE, a.INWARD_ID, a.CUST_ID, b.FNAME||' '||b.MNAME||' '||b.LNAME Cust_name, a.REG_NO, a.SCHEME_NO, 
				c.SCHEME_NAME, d.vc_pay_mode,a.vc_cheque,a.pay_date,SUM(a.AMOUNT)Amt 
				FROM in_dt a,cust_mst b,scheme_mst c,mst_pay d
				WHERE a.inward_DATE BETWEEN '01-JAN-2015' AND '01-JAN-2016'
				AND a.br_code = d.br_code
				AND a.CUST_ID = b.CUST_ID
				AND a.SCHEME_NO = c.SCHEME_NO
				AND a.pay_mode = d.vc_pay_MODE
				AND a.BR_CODE = DECODE('82','ALL',a.BR_CODE,'82')
				AND a.SCHEME_NO = DECODE('01','ALL',a.SCHEME_NO,'01')
				and a.scheme_no is not null
				AND d.ch_tran_type = 'KP'
				AND a.pay_mode = DECODE('C','ALL',a.pay_mode,'C')
				GROUP BY a.BR_CODE, a.INWARD_DATE, a.INWARD_ID, a.CUST_ID, b.FNAME||' '||b.MNAME||' '||b.LNAME, a.REG_NO, a.SCHEME_NO,
				a.vc_cheque,d.vc_pay_mode,c.SCHEME_NAME,a.pay_date
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getKpPayModeWiseDetailReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getBranchwisePaymentDetailReport
	 * Description: gets BranchwisePaymentDetailReport data
	 *
	 * @return : list of BranchwisePaymentDetailReport data
	 */
	def getBranchwisePaymentDetailReport(fromDate,toDate,schemeNo,paymode){
		def result1
		try{
			query =
				"""				
				"""				
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getBranchwisePaymentDetailReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	/**
	 * Method Name: getDescriptionDataForBrWisePayDtlsReport
	 * Description: gets description list
	 *
	 * @return : list of description
	 */
	def getDescriptionDataForBrWisePayDtlsReport(){
		def complist = [:]
		try{
			query =
				"""
				SELECT flex_value_meaning,description FROM fnd_flex_values_vl
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
			
			result.each {
				complist.put(it.values()[0],it.values()[1])
			}
			
		}catch(Exception exception){
			log.info("Exception in ReportService getDescriptionDataForBrWisePayDtlsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return complist
	}
	
	/**
	 * Method Name: getKatanWadharaReport
	 * Description: gets KatanWadharaReport data
	 *
	 * @return : list of KatanWadhara
	 */
	def getKatanWadharaReport(katanWadhara,session){
		def fromDate,toDate,hidCat,item,sub,purity,kwSel		
		def x = katanWadhara
		def katanReport
		try{			
			fromDate=x?.fromDate 
			toDate=x?.toDate
			hidCat=x?.hidCat
			kwSel=x?.kwSel
			
			if(x?.item){
				item = x?.item
			}else{
				item = 'ALL'
			}
			if(x?.sub){
				sub = x?.sub
			}else{
				sub = 'ALL'
			}
			if(x?.purity){
				purity = x?.purity
			}else{
				purity = 'ALL'
			}
			def str = new StringBuilder(" select b.VC_ITEM_CODE, a.vc_voucher_no , a.dt_voucher_date, b.vc_label_no , b.dt_label_date, b.VC_DEPTT_DESC , b.nu_nang ,");
			str.append(" b.nu_nang_pcs , b.nu_gross_wtt , b.nu_nett_wtt, b.vc_purity , b.NU_MAKING_CHARGES, b.VC_KAR_CODE, b.VC_KW_LABEL");
			str.append(" from hd_cash a, DT_CASH b where VC_SALE_TYPE = '$kwSel' and a.vc_comp_code = '$session.companyCode'");
			str.append(" and a.dt_voucher_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')");		
			str.append(" and a.vc_category LIKE '$hidCat' and vc_item_code = DECODE('$item','ALL',vc_item_code,'$item')");			
			str.append(" and vc_sub_code = DECODE('$sub','ALL',vc_sub_code,'$sub') and vc_purity = DECODE('$purity','ALL',vc_purity,'$purity')");			
			str.append(" and a.br_code = '$session.brCode' and a.vc_voucher_no = b.vc_voucher_no and a.dt_voucher_date = b.dt_voucher_date");			
			str.append(" and a.vc_category = b.vc_category and a.br_code = b.br_code and a.vc_comp_code = b.vc_comp_code and a.ch_cancel_flag = 'N'");
			
			Sql sql1 = new Sql(dataSource)
			katanReport = sql1.rows(str.toString())			
		}catch(Exception exception){
			log.info("Exception in ReportService getKatanWadharaReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return katanReport
	}
	
	/**
	 * Method Name: getCategoryForKatanWadhara
	 * Description: gets cat list
	 *
	 * @return : list of item
	 */
	def getCategoryForKatanWadhara(brCode,companyCode){
		try{
			query =
				"""
				select vc_reason_code,vc_reason_desc from sales.mst_reason
				where vc_reason_flg='C'
				and vc_comp_code='$companyCode'
				and br_code  = '$brCode'
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getCategoryForKatanWadhara method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getItemForKatanWadhara
	 * Description: gets item list
	 *
	 * @return : list of item
	 */
	def getItemForKatanWadhara(brCode,companyCode){
		try{
			query =
				"""		
				select distinct VC_ITEM_CODE vc_group_code,VC_ITEM_CODE vc_group_desc
				from sales.dt_cash
				where
				vc_comp_code='$companyCode'
				and br_code = '$brCode'
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getItemForKatanWadhara method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSubForKatanWadhara
	 * Description: gets sub list
	 *
	 * @return : list of sub
	 */
	def getSubCodeForKatanWadhara(brCode,companyCode){
		try{
			query =
				"""
				select distinct vc_sub_code vc_reason_code,vc_sub_code vc_reason_desc
				from sales.dt_cash
				where vc_comp_code='$companyCode'
				and br_code = '$brCode'
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSubForKatanWadhara method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPurityForKatanWadhara
	 * Description: gets the purity list
	 *
	 * @return : purity list
	 */
	def getPurityForKatanWadhara(category,brCode,companyCode) {
		def query1,result1
		try{			
			query =
					"""
					select distinct vc_purity  from
					sales.dt_cash
					where vc_comp_code='$companyCode' 
					and  vc_category= '$category'
					and br_code = '$brCode'
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getPurityForKatanWadhara method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getSalesRetChkLstReport
	 * Description: gets SalesRetChkLstReport list
	 *
	 * @return : list of SalesRetChkLstReport
	 */
	def getSalesRetChkLstReport(saleRetChkData,session){
		def fromDate,toDate,hidCat,location,subCode,purity,item,customer
		def x = saleRetChkData
		try{
			fromDate=x?.fromDate 
			toDate=x?.toDate
			hidCat=x?.hidCat
			
			if(x?.item){
				item = x?.item
			}else{
				item = 'ALL'
			}
			if(x?.subCode){
				subCode = x?.subCode
			}else{
				subCode = 'ALL'
			}
			if(x?.purity){
				purity = x?.purity
			}else{
				purity = 'ALL'
			}
			if(x?.customer){
				customer = x?.customer
			}else{
				customer = 'ALL'
			}			
			if(x?.location){
				location = x?.location
			}else{
				location = 'ALL'
			}
			query =
				"""
				SELECT   a.vc_comp_code, a.br_code, a.vc_voucher_no, a.dt_voucher_date,
		         a.vc_token_no, a.dt_token_date, a.vc_adv_ord_no, a.dt_adv_ord_date,
		         a.vc_category, a.vc_cust_type, a.vc_cust_fname, a.vc_cust_mname,
		         a.vc_cust_lname, a.vc_address1, a.vc_address2, a.vc_address3,
		         a.vc_address4, a.vc_phone, a.vc_email, a.vc_state, a.vc_postal_code,
		         a.vc_act_desc, a.vc_act_type, a.vc_cust_add, a.vc_city,
		         a.nu_postel_code, a.vc_cust_class, a.vc_pay_terms, a.nu_rec_act,
		         a.ch_cancel_flag, a.vc_tax_code, a.ch_close, a.nu_mc_id, a.nu_mc_no,
		         a.nu_stationary, a.vc_fin_no, a.ch_gold_type, a.vc_sale_voucher_no,
		         a.dt_update_date, a.dt_creation_date, a.vc_creation_by,
		         a.vc_update_by, a.ch_stage, a.vc_field1, a.vc_field2, a.vc_field3,
		         a.vc_field4, a.vc_field5, a.vc_field6, a.vc_field7, a.vc_field8,
		         a.vc_field9, a.vc_field10, a.nu_field1, a.nu_field2, a.nu_field3,
		         a.nu_field4, a.nu_field5, a.nu_field6, a.nu_field7, a.nu_field8,
		         a.nu_field9, a.nu_field10, a.dt_field1, a.dt_field2, a.dt_field3,
		         a.dt_field4, a.dt_field5, a.dt_field6, a.dt_field7, a.dt_field8,
		         a.dt_field9, a.dt_field10, a.vc_sr_adv, a.dt_sr_adv,
		         a.vc_ref_sale_voucher_no, a.dt_ref_sale_voucher_date, a.vc_ebs_fcust,
		         a.vc_ebs_mcust, a.vc_ebs_lcust, a.vc_kattan, a.vc_cust_id,
		         a.vc_ebs_order_no, a.vc_stock_sale_type, b.vc_comp_code, b.br_code,
		         b.vc_organization_id, b.vc_organization_name, b.vc_voucher_no,
		         b.dt_voucher_date, b.vc_token_no, b.dt_token_date, b.vc_adv_ord_no,
		         b.dt_adv_ord_date, b.vc_inv_id, b.vc_sub_inv_code, b.vc_deptt,
		         b.vc_deptt_desc, b.vc_label_no, b.dt_label_date, b.vc_kar_code,
		         b.ch_kw_flag, b.vc_kw_label, b.vc_item_code, b.vc_item_det,
		         b.vc_item_name, b.vc_sub_code, b.vc_sub_style, b.vc_metal_colour,
		         b.vc_dia_color_clarity, b.vc_tran_source, b.vc_varity_code,
		         b.vc_design_no, b.vc_primary_uom, b.vc_secondry_uom, b.nu_gross_wtt,
		         b.nu_nett_wtt, b.nu_carrat_wtt, b.nu_nang, b.nu_nang_pcs, b.nu_pcs,
		         b.nu_making_charges, b.nu_org_making, b.nu_other_charges,
		         b.nu_org_oth_charges, b.ch_cal_flag, b.ch_wtt_flag, b.ch_rate_type,
		         b.nu_rate, b.nu_label_rate, b.vc_pur_type, b.vc_emp_code,
		         b.nu_tax_code, b.nu_tax_per, b.vc_tax_name, b.nu_amount, b.nu_vat,
		         b.nu_discount, b.nu_discount1, b.nu_discount2, b.nu_discount3,
		         b.vc_pro_id, b.vc_category, b.vc_purity, b.ch_c_t_type,
		         b.ch_gold_type, b.nu_sr_no, b.vc_pt_type, b.vc_remarks,
		         b.ch_fixed_label_flag, b.nu_cost_price, b.vc_lot_serial_ref,
		         b.vc_lot_number, b.scheme_no, b.vc_field1, b.vc_field2, b.vc_field3,
		         b.vc_field4, b.vc_field5, b.vc_field6, b.vc_field7, b.vc_field8,
		         b.vc_field9, b.vc_field10, b.nu_field1, b.nu_field2, b.nu_field3,
		         b.nu_field4, b.nu_field5, b.nu_field6, b.nu_field7, b.nu_field8,
		         b.nu_field9, b.nu_field10, b.dt_field1, b.dt_field2, b.dt_field3,
		         b.dt_field4, b.dt_field5, b.nu_actual_rate, b.vc_sale_type,
		         b.vc_ref_sale_voucher_no, b.dt_ref_sale_voucher_date, b.nu_ss_no,
		         b.nu_multi_rate, b.nu_dia_gold_rate, b.nu_lumpsum_making,
		         b.vc_sales_rep, b.nu_avg_rate
		    FROM hd_cash a, dt_cash b
		    WHERE a.vc_comp_code = '$session.companyCode'
		     	AND a.vc_comp_code = b.vc_comp_code
		     	AND a.vc_category = '$hidCat'
				 AND a.vc_category = b.vc_category
				 AND a.dt_voucher_date BETWEEN TO_DATE (TO_DATE('$fromDate','MM/DD/YYYY'), 'dd-mm-rrrr')
		         AND TO_DATE (TO_DATE('$toDate','MM/DD/YYYY'), 'dd-mm-rrrr')
									   AND a.dt_voucher_date = b.dt_voucher_date
				 AND a.vc_voucher_no = b.vc_voucher_no
				 AND a.ch_cancel_flag = 'N'
				 AND a.vc_cust_fname || ' ' || a.vc_cust_lname =
		            DECODE ('$customer', 'ALL', a.vc_cust_fname || ' ' || a.vc_cust_lname, '$customer')
				 AND b.vc_item_code = DECODE ('$item', 'ALL', b.vc_item_code, '$item')
				 AND b.vc_sub_code = DECODE ('$subCode', 'ALL', b.vc_sub_code, '$subCode')
				 AND b.vc_purity = DECODE ('$purity', 'ALL', b.vc_purity, '$purity')
				 AND b.vc_sale_type IN ('SS')
				 AND a.br_code = '$session.brCode'
				AND a.br_code = b.br_code
				AND UPPER (vc_cust_fname) NOT LIKE ('%BRANCH%')
				ORDER BY b.dt_voucher_date, a.vc_voucher_no
				"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSalesRetChkLstReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getStoneDataForSalesRetChkLst
	 * Description: gets the stone list
	 *
	 * @return : stone list
	 */
	def getStoneDataForSalesRetChkLst(stoneData,session) {
		def fromDate,toDate,hidCat,location,subCode,purity,item,customer
		def x = stoneData
		try{
			fromDate=x?.fromDate 
			toDate=x?.toDate
			hidCat=x?.hidCat
			
			if(x?.item){
				item = x?.item
			}else{
				item = 'ALL'
			}
			if(x?.subCode){
				subCode = x?.subCode
			}else{
				subCode = 'ALL'
			}
			if(x?.purity){
				purity = x?.purity
			}else{
				purity = 'ALL'
			}
			if(x?.customer){
				customer = x?.customer
			}else{
				customer = 'ALL'
			}			
			if(x?.location){
				location = x?.location
			}else{
				location = 'ALL'
			}
			query =
					"""
					select  vc_stone_cat,sum(NU_PCS_CARRAT_WTT)
					from   sales.dt_CASH_stone b,sales.hd_CASH a
					where  a.vc_comp_code='$session.companyCode'
					and a.vc_comp_code = b.vc_comp_code
					and a.vc_category='$hidCat'
					and a.vc_category = b.vc_category
					and a.dt_voucher_date between  to_date( TO_DATE('$fromDate','MM/DD/YYYY'),'dd-mm-rrrr')  and  to_date( TO_DATE('$toDate','MM/DD/YYYY'),'dd-mm-rrrr')
					and a.dt_voucher_date = b.dt_voucher_date
					and a.vc_voucher_no = b.vc_voucher_no and
					a.vc_cust_fname||' '||a.vc_cust_lname= DECODE('$customer','ALL',a.vc_cust_fname||' '||a.vc_cust_lname,'$customer') AND
					b.vc_item_code = DECODE('$item','ALL',b.vc_item_code ,'$item') AND
					b.vc_sub_code = DECODE('$subCode','ALL',b.vc_sub_code ,'$subCode') AND
					b.vc_purity  =DECODE('$purity','ALL',b.vc_purity,'$purity') AND 
					substr(a.vc_voucher_no, instr(a.vc_voucher_no, a.br_code)+length(a.br_code),2) in ('SI','CR','ER','FR')
					and CH_CANCEL_FLAG = 'N'
					and a.br_code='$session.brCode'
					and a.br_code=b.br_code
					and b.vc_stone_cat is not null
					and b.vc_sale_type in ('SS')
					group by vc_stone_cat
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getStoneDataForSalesRetChkLst method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getConsolidateSaleReport
	 * Description: gets the saleType list
	 *
	 * @return : purity list
	 */
	def getConsolidateSaleReport(saleType,session) {
		def branchCode,fromDate,toDate,hidCat,purity,item,custName,gross=0,grossTotAmt,nett=0,nettTotAmt,carrat=0,carratTotAmt,billAmt=0,billTotAmt
		def x = saleType
		def resultList  = []
		def dataMap = [:]
		def perCatGrossTotal,perCatNetTotal,perCatCaratTotal,perCatAmtTotal,allCatGrossTotal,allCatNetTotal,allCatCaratTotal,allCatAmtTotal
		try{
			fromDate=x?.fromDate
			toDate=x?.toDate
			
			if(x?.hidCat){
				hidCat = x?.hidCat
			}else{
				hidCat = 'ALL'
			}
			if(x?.item){
				item = x?.item
			}else{
				item = 'ALL'
			}			
			if(x?.purity){
				purity = x?.purity
			}else{
				purity = 'ALL'
			}
			if(x?.custName){
				custName = x?.custName
			}else{
				custName = 'ALL'
			}	
			if(x?.branchCode){
				branchCode = x?.branchCode
			}else{
				branchCode = 'ALL'
			}						
			def str = """
						SELECT vc_reason_code as cat 
						FROM SALES.mst_reason
						WHERE br_code = DECODE('$branchCode','ALL',BR_CODE,'$branchCode') 
				        AND vc_comp_code = '$session.companyCode' 
				        AND vc_reason_flg = 'C' 
				        AND vc_reason_code = DECODE('$hidCat','ALL',vc_reason_code,'$hidCat')
					  """
			Sql sql1 = new Sql(dataSource)
			def rs = sql1.rows(str)
			rs.each {
				hidCat = it.values()[0]			
				query =
						"""					
						select (select br_name from sales.br_mst_tab where br_code = br_code) branch_name,
						vc_category,
						sum(nvl(nu_bill_amount,0)) nu_bill_amount,
						sum(nvl(decode(vc_category,'W',0,nu_gross_wtt),0)) nu_gross_wtt, 
						sum(nvl(decode(vc_category,'W',0,nu_nett_wtt),0)) nu_nett_wtt,
						sum(nvl(decode(vc_category,'W',0,nu_carrat_wtt),0)) nu_carrat_wtt  
						 from (
						SELECT 
						a.br_code,
						    a.vc_voucher_no,
						    a.dt_voucher_date, 
						a.vc_category,
						sum(decode(b.vc_sale_type,'K',-(NVL(b.nu_amount,0) - NVL(b.nu_discount,0)),(NVL(b.nu_amount,0) - NVL(b.nu_discount,0))))
						+ nvl(sum(a.vc_field2),0)/count(*) nu_bill_amount ,
						sum(decode(b.vc_sale_type,'K',-b.nu_gross_wtt,b.nu_gross_wtt)) nu_gross_wtt,
						sum(decode(b.vc_sale_type,'K',-b.nu_nett_wtt,b.nu_nett_wtt)) nu_nett_wtt,
						sum(decode(b.vc_sale_type,'K',-b.nu_carrat_wtt,b.nu_carrat_wtt)) nu_carrat_wtt
						FROM sales.hd_cash a, sales.dt_cash b, sales.dt_cash c 
						WHERE a.vc_voucher_no = b.vc_voucher_no AND
						a.dt_voucher_date=b.dt_voucher_date AND
						a.vc_voucher_no like '%SI%' AND
						b.dt_voucher_date = c.dt_voucher_date AND
						b.vc_voucher_no = c.vc_voucher_no AND
						a.ch_cancel_flag = 'N' AND
						nvl(a.vc_cust_fname||' '||a.vc_cust_lname,'XX') = DECODE('$custName','ALL',nvl(a.vc_cust_fname||' '||a.vc_cust_lname,'XX'),'$custName') AND
						c.vc_item_code = DECODE('$item','ALL',c.vc_item_code ,'$item') AND
						b.dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') AND 
						nvl(b.vc_purity,'X')  = DECODE('$purity','ALL',nvl(b.vc_purity,'X'),'$purity') AND 
						a.vc_category = DECODE('$hidCat','ALL',a.vc_category, '$hidCat') AND 
						a.vc_comp_code = '$session.companyCode' AND 
						b.vc_comp_code = a.vc_comp_code AND
						a.vc_category = b.vc_category AND
						b.vc_kw_label = c.vc_label_no AND
						c.br_code = b.br_code AND 
						a.br_code = DECODE('$branchCode','ALL',a.br_code, '$branchCode') AND 
						b.br_code = a.br_code AND
						b.vc_sale_type in('K')
						GROUP BY a.br_code, a.vc_category,    a.vc_voucher_no,
						    a.dt_voucher_date
						Union
						SELECT 
						a.br_code, 
						    a.vc_voucher_no,
						    a.dt_voucher_date,
						a.vc_category,
						sum(decode(b.vc_sale_type,'K',-(NVL(b.nu_amount,0) - NVL(b.nu_discount,0)),(NVL(b.nu_amount,0) - NVL(b.nu_discount,0))))
						+nvl(sum(a.vc_field2),0)/count(*) nu_bill_amount ,
						sum(decode(b.vc_sale_type,'K',-b.nu_gross_wtt,b.nu_gross_wtt)) nu_gross_wtt,
						sum(decode(b.vc_sale_type,'K',-b.nu_nett_wtt,b.nu_nett_wtt)) nu_nett_wtt,
						sum(decode(b.vc_sale_type,'K',-b.nu_carrat_wtt,b.nu_carrat_wtt)) nu_carrat_wtt
						FROM sales.hd_cash a, sales.dt_cash b 
						WHERE a.vc_voucher_no = b.vc_voucher_no AND
						a.dt_voucher_date=b.dt_voucher_date AND
						a.vc_voucher_no like '%SI%' AND
						a.ch_cancel_flag='N' AND
						nvl(a.vc_cust_fname||' '||a.vc_cust_lname,'XX') = DECODE('$custName','ALL',nvl(a.vc_cust_fname||' '||a.vc_cust_lname,'XX'),'$custName') AND
						b.dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') AND 
						b.vc_item_code = DECODE('$item','ALL',b.vc_item_code ,'$item') AND
						b.vc_purity  =DECODE('$purity','ALL',b.vc_purity,'$purity') AND 
						a.vc_category = DECODE('$hidCat','ALL',a.vc_category, '$hidCat') AND 
						a.vc_comp_code = '$session.companyCode' AND 
						b.vc_comp_code = a.vc_comp_code  AND 
						a.vc_category = b.vc_category AND
						a.br_code=DECODE('$branchCode','ALL',a.br_code, '$branchCode') AND 
						b.br_code=a.br_code AND
						b.vc_sale_type in ('W','SA')
						GROUP BY a.br_code, a.vc_category,
						    a.vc_voucher_no,
						    a.dt_voucher_date)
						GROUP BY br_code, vc_category
						ORDER BY 1,2
						"""		
				sql = new Sql(dataSource)				
				def adResult = sql.rows(query)
				if(adResult!=null && !adResult.isEmpty()){
					adResult.each {					
						dataMap.put("Branch_Name", it.values()[0])
						dataMap.put("Category", it.values()[1])
						billAmt = it.values()[2]
						if(billAmt==null || billAmt=="null") {
							billAmt = 0
						}
						dataMap.put("Bill_Amount", billAmt)
						gross = it.values()[3]						
						if(gross==null || gross=="null") {
							gross = 0
						}
						dataMap.put("Gross_Wtt", gross)
						perCatGrossTotal = gross + gross
						dataMap.put("perCatGrossTotal", perCatGrossTotal)
						nett = it.values()[4]
						if(nett==null || nett=="null") {
							nett = 0
						}						
						dataMap.put("Nett_Wtt", nett)
						carrat = it.values()[5]
						if(carrat==null || carrat=="null") {
							carrat = 0
						}
						dataMap.put("Carrat_Wtt", carrat)
					}
				}
				grossTotAmt = gross+gross
				dataMap.put("grossTotAmt", grossTotAmt)				
				nettTotAmt = nett+nett
				dataMap.put("nettTotAmt", nettTotAmt)				
				carratTotAmt = carrat+carrat
				dataMap.put("carratTotAmt", carratTotAmt)				
				billTotAmt = billAmt+billAmt
				dataMap.put("billTotAmt", billTotAmt)
				
				resultList.add(dataMap)
			}
		}catch(Exception exception){
			log.info("Exception in ReportService getConsolidateSaleReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultList
	}
	
	/**
	 * Method Name: getConsolidateSaleReturnReport
	 * Description: gets the saleTypeReturn list
	 *
	 * @return : purity list
	 */
	def getConsolidateSaleReturnReport(saleTypeReturn,session) {
		def branchCode,fromDate,toDate,hidCat,purity,item,custName,gross=0,grossTotAmt,nett=0,nettTotAmt,carrat=0,carratTotAmt,billAmt=0,billTotAmt
		def x = saleTypeReturn
		def resultList  = []
		def dataMap = [:]
		try{
			fromDate=x?.fromDate
			toDate=x?.toDate
			
			if(x?.hidCat){
				hidCat = x?.hidCat
			}else{
				hidCat = 'ALL'
			}
			if(x?.item){
				item = x?.item
			}else{
				item = 'ALL'
			}			
			if(x?.purity){
				purity = x?.purity
			}else{
				purity = 'ALL'
			}
			if(x?.custName){
				custName = x?.custName
			}else{
				custName = 'ALL'
			}	
			if(x?.branchCode){
				branchCode = x?.branchCode
			}else{
				branchCode = 'ALL'
			}
			def str = """
						SELECT vc_reason_desc as cat 
						FROM SALES.mst_reason
						WHERE br_code = DECODE('$branchCode','ALL',BR_CODE,'$branchCode') 
				        AND vc_comp_code = '$session.companyCode' 
				        AND vc_reason_flg = 'C' 
				        AND vc_reason_code = DECODE('$hidCat','ALL',vc_reason_code,'$hidCat')
					  """
			Sql sql1 = new Sql(dataSource)
			def rs = sql1.rows(str)
			rs.each {
				hidCat =it.values()[0]
				query =
						"""
						select (select br_name from sales.br_mst_tab where br_code = a.br_code) branch_name,
						a.vc_category,
						sum(nvl(b.nu_amount,0))-sum(nvl(b.nu_discount,0)) nu_bill_amount,  
						sum(nvl(decode(a.vc_category,'W',0,nu_gross_wtt),0)) nu_gross_wtt, 
						sum(nvl(decode(a.vc_category,'W',0,nu_nett_wtt),0)) nu_nett_wtt,
						sum(nvl(decode(a.vc_category,'W',0,nu_carrat_wtt),0)) nu_carrat_wtt 
						from hd_cash a, dt_cash b
						where a.vc_comp_code='$session.companyCode' and 
						a.vc_comp_code = b.vc_comp_code  and
						a.vc_category=decode('$hidCat','ALL',a.vc_category,'$hidCat') and
						a.vc_category = b.vc_category and
						a.dt_voucher_date between to_date( TO_DATE('$fromDate','MM/DD/YYYY'),'dd-mm-rrrr')  and  to_date( TO_DATE('$toDate','MM/DD/YYYY'),'dd-mm-rrrr') and
						a.dt_voucher_date = b.dt_voucher_date and
						a.vc_voucher_no = b.vc_voucher_no and
						a.ch_cancel_flag = 'N' and
						a.vc_cust_fname||' '||a.vc_cust_lname= DECODE('$custName','ALL',a.vc_cust_fname||' '||a.vc_cust_lname,'$custName') AND
						b.vc_item_code = DECODE('$item','ALL',b.vc_item_code ,'$item') AND					
						b.vc_purity  =DECODE('$purity','ALL',b.vc_purity,'$purity') AND
						b.VC_SALE_TYPE in ('SS')
						and a.br_code=decode('$branchCode','ALL',a.br_code,'$branchCode')
						and a.br_code=b.br_code
						and upper(vc_cust_fname) not like ('%BRANCH%')
						group by a.br_code, a.vc_category
						order by a.br_code, a.vc_category
						"""
						//b.vc_sub_code = DECODE(:SUB_CODE,'ALL',b.vc_sub_code ,:SUB_CODE) AND(rohil said taht comment)				
				def adResult = sql.rows(query)
				if(adResult!=null && !adResult.isEmpty()){
					adResult.each {
						dataMap.put("Branch_Name", it.values()[0])
						dataMap.put("Category", it.values()[1])
						billAmt = it.values()[2]
						if(billAmt==null || billAmt=="null") {
							billAmt = 0
						}
						dataMap.put("Bill_Amount", billAmt)
						gross = it.values()[3]
						if(gross==null || gross=="null") {
							gross = 0
						}
						dataMap.put("Gross_Wtt", gross)
						nett = it.values()[4]
						if(nett==null || nett=="null") {
							nett = 0
						}
						dataMap.put("Nett_Wtt", nett)
						carrat = it.values()[5]
						if(carrat==null || carrat=="null") {
							carrat = 0
						}
						dataMap.put("Carrat_Wtt", carrat)
					}
				}
				grossTotAmt= gross+gross
				dataMap.put("grossTotAmt", grossTotAmt)				
				nettTotAmt= nett+nett
				dataMap.put("nettTotAmt", nettTotAmt)				
				carratTotAmt= carrat+carrat
				dataMap.put("carratTotAmt", carratTotAmt)				
				billTotAmt= billAmt+billAmt
				dataMap.put("billTotAmt", billTotAmt)
				
				resultList.add(dataMap)
			}
		}catch(Exception exception){
			log.info("Exception in ReportService getConsolidateSaleReturnReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}		
		return resultList
	}
	
	/**
	 * Method Name: getKpSaleCountReport
	 * Description: gets the kpSaleCount list
	 *
	 * @return : purity list
	 */
	def getKpSaleCountReport(kpSaleCnt) {
		def x = kpSaleCnt
		def resultList  = []
		def map3
		def fromDate,toDate,dept,branchCode,brcd,month,schemeNo,enrollNo,enrollAmt	
		try{
			fromDate=x?.fromDate
			toDate=x?.toDate
			dept=x?.dept
			branchCode=x?.brName
			query =
					"""
					select substr(cust_id,1,instr(cust_id,'-')-2) brcode, to_char(reg_date,'RRRR-MM') month, 
					substr(cust_id,instr(cust_id,'-')-1,1) scheme_no, count(*) new_count, sum(due_amount) amount
					from apps.tbz_pos_kp_customer_summary_v
					where 
					substr(cust_id,1,instr(cust_id,'-')-2) in ((decode('$branchCode','ALL',substr(cust_id,1,2),'$branchCode')),(decode('$branchCode','ALL',substr(cust_id,1,2),'$branchCode')),(decode('$branchCode','ALL',substr(cust_id,1,3),'$branchCode')),(decode('$branchCode','ALL',substr(cust_id,1,4),'$branchCode')))					
					and reg_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
					and dept_name = decode('$dept', 'ALL', dept_name, '$dept')
					group by substr(cust_id,1,instr(cust_id,'-')-2), to_char(reg_date,'RRRR-MM'), substr(cust_id,instr(cust_id,'-')-1,1)
					order by 2,3,1
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)		
			result.each {
				def dataMap = [:]
				brcd =it.values()[0]
				month =it.values()[1]
				schemeNo =it.values()[2]
				enrollNo =it.values()[3]
				enrollAmt =it.values()[4]
				
				dataMap.put("month", month)
				dataMap.put("schemeNo", schemeNo)
				dataMap.put("enrollNo", enrollNo)
				dataMap.put("enrollAmt", enrollAmt)
				
				def qry1 = "select br_name from br_mst_tab where br_code='$brcd'"
				Sql sql1 = new Sql(dataSource)
				def brNmRs = sql1.rows(qry1)
				if(brNmRs!=null && !brNmRs.isEmpty()){
					brNmRs.each {
						dataMap.put("BrName", it.values()[0])
					}
				}
				
				def qry2 =
				"""
					select sum(paid_amount) as TotRecAmt
					from apps.tbz_pos_kp_branchwise_det_v
					where br_code = decode('$branchCode','ALL',substr(cust_id,1,instr(cust_id,'-')-2),'$branchCode') and
					scheme_no = '$schemeNo' and ( to_char(pay_date,'RRRR-MM') = '$month' )
					and dept_name = decode('$dept', 'ALL', dept_name, '$dept')
					and (pay_date between TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY'))
					"""
				Sql sql2 = new Sql(dataSource)
				def rs = sql2.rows(qry2)		
				if(rs!=null && !rs.isEmpty()){
					rs.each {
						dataMap.put("TotRecAmt", it.values()[0])
					}
				}
				resultList.add(dataMap)
			}			
		}catch(Exception exception){
			log.info("Exception in ReportService getKpSaleCountReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultList
	}
	
	/**
	 * Method Name: getOgSaleCheckListReportAddress
	 * Description: gets the OgSaleCheckListReportAddress data
	 *
	 * @return : address list
	 */
	def getOgSaleCheckListReportAddress(session) {
		try{
			query =
					"""
					  select VC_ADDRESS1||' '||VC_ADDRESS2||' '||VC_ADDRESS3 ||' '||VC_CITY||' '||VC_STATE||' '||VC_COUNTRY 
				        as v_from_org_address 
				        from sales.br_mst_tab where br_code = '$session.brCode'
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
			result.each{
				resultArr.add(it.values()[0])
			}
		}catch(Exception exception){
			log.info("Exception in ReportService getOgSaleCheckListReportAddress method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultArr.asList()
	}
	
	/**
	 * Method Name: getOgSaleCheckListReport
	 * Description: gets the getOgSaleCheckListReport data
	 *
	 * @return : OgSaleCheckListReport list
	 */
	def getOgSaleCheckListReport(ogSaleChkLst) {
		def x = ogSaleChkLst
		def fromDate,toDate,category,hidCat,cat,sumsttype,sumstpcs,sumstcrt
		def billDt,billNo,pur,nang,pcs,gwt,nwt,cwt,amt,itemName,stone_type,stonecrt,stonepcs		
		def resultListOne = []
		def resultListTwo = []
		def resultMap = [:]
		try{
			fromDate=x?.fromDate
			toDate=x?.toDate
			if(x?.hidCat){
				cat = x?.hidCat
			}else{
				cat = x?.category
			}
			query =
					"""
					SELECT xth.dt_voucher_date,xth.sale_type,
				         xth.header_id,
				         sum(xtl.nu_amount)amount,
				         xtl.ITEM_NAME,
				         substr(xtl.item_category,6,2) Purity,
				         sum(xtl.nang) nang,sum(xtl.pieces) pcs,
				         sum(xtl.gross_weight) gt,sum(xtl.net_weight) nt,
				         sum(xtl.carat_weight) ct,
				         xtl.organization_name from_org,
				         xth.operating_unit_name to_org,
				         xth.operating_unit, xtl.item_category, -- sales.XXTBZ_CAT_ITEM (xtl.inventory_item_id) item_cate,
				         xth.organization_id, 
				         xth.transaction_type,substr(xtl.item_category,1,1) cat,xcd.stone_type,sum(xcd.CARAT_WEIGHT) stonecrt,sum(xcd.PIECES) stonepcs
				    FROM tbzcustom.xxtbz_transfer_headers xth,
				         tbzcustom.xxtbz_transfer_lines xtl,
				         apps.xxtbz_component_detail_v_NEW xcd
				   WHERE xth.header_id = xtl.header_id
				      and xtl.lot_number = xcd.LOT_NUMBER(+)
				     AND xth.sale_type IN('OG')
				and xtl.FROM_LOCATOR_NAME =decode('$cat','D','FR-CAS-OD','G','FR-CAS-OG')
				     and xth.dt_voucher_date >= to_date(TO_DATE('$fromDate','MM/DD/YYYY'),'DD-MM-RRRR')
				and xth.dt_voucher_date < to_date(TO_DATE('$toDate','MM/DD/YYYY'),'DD-MM-RRRR')+1
				group by substr(xtl.item_category,6,2),xth.dt_voucher_date,xth.sale_type,xth.header_id,xtl.ITEM_NAME,
				xtl.organization_name ,xth.operating_unit_name, xth.operating_unit,xth.organization_id,xth.transaction_type,substr(xtl.item_category,1,1), xtl.item_category,xcd.stone_type -- sales.xxtbz_cat_item (xtl.inventory_item_id)
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
			if(result!=null && !result.isEmpty()){
				result.each {
					def dataMap = [:]
					billDt =it.values()[0]
					amt =it.values()[3]
					billNo =it.values()[2]
					pur =it.values()[5]
					nang =it.values()[6]
					pcs =it.values()[7]
					gwt =it.values()[8]
					nwt =it.values()[9]
					cwt =it.values()[10]	
					itemName =it.values()[14]
					stone_type =it.values()[18]
					stonecrt =it.values()[19]
					stonepcs =it.values()[20]
					
					if(stone_type==null || stone_type=="null") {						
						stone_type = 0
					}	
					if(stonecrt==null || stonecrt=="null") {						
						stonecrt = 0
					}	
					if(stonepcs==null || stonepcs=="null") {						
						stonepcs = 0
					}						
					if(nang==null || nang=="null") {
						nang = 0
					}
					if(pcs==null || pcs=="null") {
						pcs = 0
					}
					if(cwt==null || cwt=="null") {
						cwt = 0
					}
					dataMap.put("billDt", billDt)
					dataMap.put("billNo", billNo)
					dataMap.put("pur", pur)
					dataMap.put("nang", nang)
					dataMap.put("pcs", pcs)
					dataMap.put("gwt", gwt)
					dataMap.put("nwt", nwt)
					dataMap.put("cwt", cwt)
					dataMap.put("amt", amt)
					dataMap.put("itemName", itemName)					
					dataMap.put("stone_type", stone_type)
					dataMap.put("stonepcs", stonepcs)
					dataMap.put("stonecrt", stonecrt)
										
					/*def qry2 =
					"""
						select 
						xth.header_id,xcd.stone_type,sum(xcd.CARAT_WEIGHT) stonecrt,sum(xcd.PIECES) stonepcs
						from tbzcustom.xxtbz_transfer_headers xth,
						tbzcustom.xxtbz_transfer_lines xtl,apps.xxtbz_component_detail_v_new xcd
						where xth.header_id = xtl.header_id
						and xtl.lot_number = xcd.LOT_NUMBER(+)
						and nvl(xth.cancel_flag,'N') = 'N'
						and xth.header_id is not null
						AND xth.sale_type IN('OG')
						and xth.dt_voucher_date >= to_date(TO_DATE('$fromDate','MM/DD/YYYY'),'DD-MM-RRRR')
						and xth.dt_voucher_date < to_date(TO_DATE('$toDate','MM/DD/YYYY'),'DD-MM-RRRR')+1
						and xcd.stone_type is not null
						group by xcd.stone_type,xth.header_id
						"""
					Sql sql2 = new Sql(dataSource)
					def rs = sql2.rows(qry2)
					if(rs!=null && !rs.isEmpty()){
						rs.each {
							dataMap.put("stone_type", it.values()[1])
							dataMap.put("stonecrt", it.values()[2])
							dataMap.put("stonepcs", it.values()[3])
						}
					}					*/				
					resultListOne.add(dataMap)
				}
		}
			resultMap.put("resultListOne", resultListOne)
			def qry3 =
			"""
							SELECT xcd.stone_type,sum(xcd.PIECES) stonepcs,sum(xcd.CARAT_WEIGHT) stonecrt
							    FROM tbzcustom.xxtbz_transfer_headers xth,
							tbzcustom.xxtbz_transfer_lines xtl,
							         apps.xxtbz_component_detail_v_NEW xcd
							   WHERE xth.header_id = xtl.header_id
							      and xtl.lot_number = xcd.LOT_NUMBER(+)
							     AND xth.sale_type IN('OG')
							and xth.dt_voucher_date >= to_date(TO_DATE('$fromDate','MM/DD/YYYY'),'DD-MM-RRRR')
							and xth.dt_voucher_date < to_date(TO_DATE('$toDate','MM/DD/YYYY'),'DD-MM-RRRR')+1
							and xcd.stone_type is not null 
							     group by xcd.stone_type
							"""
			Sql sql3 = new Sql(dataSource)
			def rs1 = sql3.rows(qry3)
			if(rs1!=null && !rs1.isEmpty()){
				rs1.each {
					def dataMap1 = [:]
					sumsttype = it.values()[0]
					sumstpcs = it.values()[1]
					sumstcrt = it.values()[2]
					if(sumsttype==null || sumsttype=="null") {
						sumsttype = 0
					}
					if(sumstpcs==null || sumstpcs=="null") {
						sumstpcs = 0
					}
					if(sumstcrt==null || sumstcrt=="null") {
						sumstcrt = 0
					}
					dataMap1.put("sumsttype", sumsttype)
					dataMap1.put("sumstpcs", sumstpcs)
					dataMap1.put("sumstcrt", sumstcrt)
					resultListTwo.add(dataMap1)
				}
			}
			resultMap.put("resultListTwo", resultListTwo)						
		}catch(Exception exception){
			log.info("Exception in ReportService getOgSaleCheckListReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return resultMap
	}
		
	/**
	 * Method Name: getLabelTransferChecklistReport
	 * Description: gets the LabelTransferChecklistReport data
	 *
	 * @return : ColourStoneSummary list
	 */
	def getLabelTransferChecklistReport(labelTrChkLst) {
		def fromDate,toDate,cat
		def x=labelTrChkLst
		try{
			fromDate=x?.fromDate
			toDate=x?.toDate
			cat = x?.hidCat
			query =
					"""
					SELECT xth.operating_unit operating_unit, xtl.organization_name,
				       xth.dt_voucher_date voucher_date, xtl.header_id voucher_number,
				       xtl.lot_number, xtl.from_locator_name, xtl.to_locator_name,
				       xtl.item_category item_cate,--sales.xxtbz_cat_item (xtl.inventory_item_id) item_cate,
				       xtl.item_category, xtl.item_name, xtl.nang nang, xtl.pieces pieces,xim.Purity,
				       xtl.gross_weight gross_weight, xtl.net_weight net_weight,CARAT_WEIGHT
				  FROM xxtbz_transfer_headers xth, xxtbz_transfer_lines xtl,tbzcustom.xxtbz_item_master xim
				 WHERE sale_type = 'TL'
				   AND xth.header_id = xtl.header_id
				   AND xth.dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY')
				   AND xth.vc_voucher_no IS NOT NULL
				   AND xtl.inventory_item_id = xim.inventory_item_id
				   and xtl.from_locator_name = nvl('',xtl.from_locator_name)
				   and xtl.to_locator_name = nvl('',xtl.to_locator_name)
				and  (substr(xtl.ITEM_CATEGORY,1,1)) = nvl('$cat', (substr(xtl.ITEM_CATEGORY,1,1)) )
					"""
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getLabelTransferChecklistReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getEcsStautsReport
	 * Description: gets ECS status data
	 *
	 * @return : list of ECS status data
	 */
	def getEcsStautsReport(fromDate,branchNo,schemeName,custId){
		def result1
		try{
			query =
				"""
				select a.*
				from dt_ecs_ref a, in_dt b 
				where a.ch_cancel_flag = 'N' 
				and a.ch_text_gen = 'Y' 
				and a.dt_field2 is not null
				and a.dt_field2 between '10-SEP-2014' and '01-MAY-2016'
				and SUBSTR(a.vc_cust_id,1,instr(b.cust_id,'-')-2) = '82'
				and a.vc_cust_id = Decode('ALL','ALL',a.vc_cust_id,'ALL')
				and substr(b.cust_id,instr(b.cust_id,'-')-1,1) = Decode('ALL','ALL',substr(b.cust_id,instr(b.cust_id,'-')-1,1),'ALL')
				and decode(pay_mode,'DM',b.vc_field1,pay_mode) = 'E'
				and a.vc_cust_id = b.cust_id
				and a.vc_field1 = b.inward_id
				order by a.dt_field2 asc, a.nu_install_for
				"""	
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getEcsStautsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}

	/**
	 * Method Name: getUserwiseSalesReport
	 * Description: gets Userwise Sales data
	 *
	 * @return : list of Userwise sales data
	 */
	def getUserwiseSalesReport(fromDate,toDate,branchNo,categoryName,voucherNo){
		def result1
		try{
			query =
				"""
				select mu.VC_USER_NAME,hc.VC_VOUCHER_NO,hc.DT_VOUCHER_DATE Voucher_Date,hou.NAME Showroom,hc.VC_CATEGORY Sales_Type,hc.vc_cust_fname||' '||vc_cust_mname||' '||vc_cust_lname Customer_Name
				from sales.mk_users mu,sales.hd_cash hc,hr_operating_units hou
				where mu.BR_CODE = decode(:br_code,'ALL',mu.BR_CODE,:br_code)
				--AND trunc(mu.DT_FIELD1) = hc.DT_VOUCHER_DATE
				AND mu.CH_USER_CODE = hc.VC_CREATION_BY
				AND mu.CH_USER_ACTIVE IN ('Y','N')
				--AND hc.CH_CLOSE = 'Y'
				--AND hc.CH_STAGE = mu.CH_STAT_FLAG
				AND hc.VC_VOUCHER_NO = nvl(:voucher_no,hc.VC_VOUCHER_NO)
				AND hc.DT_VOUCHER_DATE between :From_Date AND :to_date
				AND mu.BR_CODE = hou.ORGANIZATION_ID
				AND hc.BR_CODE = hou.ORGANIZATION_ID
				AND hc.BR_CODE = mu.BR_CODE
				AND hc.VC_CATEGORY = decode(:vc_category,'ALL',hc.VC_CATEGORY,:vc_category)
				--ORDER BY 3
				union all 
				select mu.VC_USER_NAME,hc.VC_VOUCHER_NO,hc.DT_VOUCHER_DATE Voucher_Date,hou.NAME Showroom,hc.VC_CATEGORY Category,hc.vc_cust_fname||' '||vc_cust_mname||' '||vc_cust_lname Customer_Name
				from sales.mk_users mu,sales.hd_cash hc,hr_operating_units hou
				WHERE mu.BR_CODE = nvl(:br_code,mu.BR_CODE)
				AND mu.CH_USER_CODE = hc.VC_CREATION_BY
				AND mu.CH_USER_ACTIVE IN ('Y','N')
				AND hc.VC_VOUCHER_NO = nvl(:voucher_no,hc.vc_voucher_no)
				--AND hc.VC_VOUCHER_NO = decode(:voucher_no,'ALL',hc.VC_VOUCHER_NO,:voucher_no)
				AND hc.DT_VOUCHER_DATE between nvl(:From_Date,hc.DT_VOUCHER_DATE) AND nvl(:To_date,hc.DT_VOUCHER_DATE)
				AND mu.BR_CODE = hou.ORGANIZATION_ID
				AND hc.BR_CODE = hou.ORGANIZATION_ID
				AND hc.BR_CODE = mu.BR_CODE
				AND hc.VC_CATEGORY = nvl(:vc_category,hc.vc_category)
				ORDER BY 3
				"""	
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getEcsStautsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: getPurchaseReturnChecklistReport
	 * Description: gets Purchase return checklist data
	 *
	 * @return : list of Purchase return checklist data
	 */
	def getPurchaseReturnChecklistReportSachin(fromDate,toDate,categoryName){
		def result2
		try{
			def test =
				"""
					select xth.dt_voucher_date,xth.header_id,xth.OPERATING_UNIT_NAME,xth.CH_STAGE,xth.SALE_TYPE,sum(xtl.nu_amount)  selling,xth.OPERATING_UNIT,
					xim.purity PURITY,sum(xtl.nang) nang,sum(xtl.pieces) pcs,sum(xtl.GROSS_WEIGHT) grt,
					sum(xtl.NET_WEIGHT) net,sum(xtl.CARAT_WEIGHT) crt,substr(xtl.ITEM_CATEGORY,1,1) cat
					from tbzcustom.xxtbz_transfer_headers xth, tbzcustom.XXTBZ_TRANSFER_LINES xtl,
					xxtbz_item_master xim
					where xth.header_id = xtl.header_id
					and xtl.inventory_item_id = xim.inventory_item_id
					and nvl(xth.cancel_flag,'N') = 'N'
					and xth.vc_voucher_no is not null
					and xth.sale_type in ('PR','PRU')
					and xth.dt_voucher_date >= to_date(TO_DATE('01-JAN-2016','DD/MM/YYYY'),'DD-MM-RRRR')
					and xth.dt_voucher_date < to_date(TO_DATE('01-MAR-2016','DD/MM/YYYY'),'DD-MM-RRRR')+1 
					and substr(xtl.ITEM_CATEGORY,1,1) = decode('G','ALL',substr(xtl.ITEM_CATEGORY,1,1),'G')
					group by xim.purity,xth.dt_voucher_date,xth.header_id,xth.OPERATING_UNIT_NAME,xth.CH_STAGE,xth.SALE_TYPE,xth.OPERATING_UNIT,
					substr(xtl.ITEM_CATEGORY,1,1)
				"""	
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(test)
		}catch(Exception exception){
			log.info("Exception in ReportService getEcsStautsReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: getStonePurchaseReturnChecklistReport
	 * Description: gets Stone Purchase Return Checklist data
	 *
	 * @return : list of Stone Purchase Return Checklist data
	 */
	def getStonePurchaseReturnChecklistReport(fromDate,toDate,categoryName){
		def result1
		try{
			query =
				"""
					select 
					xcd.stone_type,sum(xcd.CARAT_WEIGHT) stonecrt
					from tbzcustom.xxtbz_transfer_headers xth, tbzcustom.XXTBZ_TRANSFER_LINES xtl,apps.xxtbz_component_detail_v_new xcd
					where xth.header_id = xtl.header_id
					and xtl.lot_number = xcd.LOT_NUMBER(+)
					and nvl(xth.cancel_flag,'N') = 'N'
					and xth.vc_voucher_no is not null
					and xth.sale_type in ('PR','PRU')
					--and xfh.header_id = 65181162
					and substr(xtl.ITEM_CATEGORY,1,1) = 'G'
					and xth.dt_voucher_date >= to_date(TO_DATE('01-JAN-2016','DD/MM/YYYY'),'DD-MM-RRRR')
					and xth.dt_voucher_date < to_date(TO_DATE('01-MAR-2016','DD/MM/YYYY'),'DD-MM-RRRR')+1 
					and xcd.stone_type is not null
					group by xcd.stone_type
				"""	
			sql = new Sql(dataSource)
			result = sql.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getStonePurchaseReturnChecklistReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result
	}
	
	/**
	 * Method Name: steonDetailslistReport
	 * Description: gets Stone list data
	 *
	 * @return : list of Stone data
	 */
	def steonDetailslistReport(fromDate,toDate,category){
		def result2
		try{
			query =
				"""
					select xth.header_id,xim.purity,xcd.stone_type stone_type,sum(xcd.PIECES) stonepcs,sum(xcd.CARAT_WEIGHT) stonecrt
					from tbzcustom.xxtbz_transfer_headers xth, tbzcustom.XXTBZ_TRANSFER_LINES xtl,apps.xxtbz_component_detail_v_new xcd,
					xxtbz_item_master xim
					where xth.header_id = xtl.header_id
					and xtl.inventory_item_id = xim.inventory_item_id
					and xtl.lot_number = xcd.LOT_NUMBER(+)
					and nvl(xth.cancel_flag,'N') = 'N'
					and xth.vc_voucher_no is not null
					and xth.sale_type in ('PR','PRU')					and xth.dt_voucher_date >= to_date(TO_DATE('01-JAN-2016','DD/MM/YYYY'),'DD-MM-RRRR')
					and xth.dt_voucher_date < to_date(TO_DATE('01-MAR-2016','DD/MM/YYYY'),'DD-MM-RRRR')+1
					and substr(xtl.ITEM_CATEGORY,1,1) = decode('G','ALL',substr(xtl.ITEM_CATEGORY,1,1),'G')
					group by xcd.stone_type,xim.purity,xth.header_id
				"""
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getStonePurchaseReturnChecklistReport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: getSalesChecklistWithTarget
	 * Description: gets Sales Checklist target data
	 *
	 * @return : list of Sales checklist  data
	 */
	def getSalesChecklistWithTarget(fromDate,toDate,category,compCode,brCode,purity,department){
		def result2
		try{
			query =
				"""
					SELECT a.vc_ebs_order_no, a.dt_voucher_date dt_voucher_date,
					a.vc_voucher_no vc_voucher_no,
					a.ch_stage,
					b.vc_emp_code,
					substr(a.vc_voucher_no,instr(a.vc_voucher_no, a.br_code)+(length(a.br_code)+2)) s,
					a.vc_category,
					decode(b.vc_sale_type,'K',-(NVL(b.nu_amount,0) - NVL(b.nu_discount,0)),(NVL(b.nu_amount,0) - NVL(b.nu_discount,0))) nu_bill_amount ,
					b.vc_label_no ,
					decode(b.vc_item_code, 'CO', 'PDS', b.vc_item_code) vc_item_code, 
					b.vc_sub_code,
					b.vc_purity vc_purity1,
					decode(b.vc_sale_type,'K',-b.nu_nang,b.nu_nang) nu_nang,
					decode(b.vc_sale_type,'K',-b.nu_nang_pcs,b.nu_nang_pcs) nu_nang_pcs,
					decode(b.vc_sale_type,'K',-b.nu_pcs,b.nu_pcs) nu_pcs,
					decode(b.vc_sale_type,'K',-b.nu_gross_wtt,b.nu_gross_wtt) nu_gross_wtt,
					decode(b.vc_sale_type,'K',-b.nu_nett_wtt,b.nu_nett_wtt) nu_nett_wtt,
					a.dt_voucher_date date1 , 
					a.vc_voucher_no no1 , 
					b.nu_sr_no nu_sr_no1,
					b.vc_sale_type,
					b.nu_rate,
					a.VC_CUST_FNAME||' '||a.VC_CUST_MNAME||'  '||A.VC_CUST_LNAME vc_design_no,b.NU_VAT
					FROM sales.hd_cash a, sales.dt_cash b 
					WHERE a.vc_voucher_no = b.vc_voucher_no AND
					a.dt_voucher_date=b.dt_voucher_date AND
					substr(a.vc_voucher_no,instr(a.vc_voucher_no, a.br_code)+length(a.br_code),2) IN ('SI' ,'CI','EI','FI') AND
					a.ch_cancel_flag='N' AND
					a.vc_cust_fname||' '||a.vc_cust_lname= DECODE('ALL','ALL',a.vc_cust_fname||' '||a.vc_cust_lname,'ALL') AND
					b.vc_item_code = DECODE('ALL','ALL',b.vc_item_code ,'ALL') AND
					b.vc_deptt = DECODE('ALL','ALL',b.vc_deptt ,'ALL') AND
					b.vc_sub_code = DECODE('ALL','ALL',b.vc_sub_code ,'ALL') AND
					b.dt_voucher_date BETWEEN TO_DATE('$fromDate','MM/DD/YYYY') AND TO_DATE('$toDate','MM/DD/YYYY') AND 
					b.vc_purity  =DECODE('ALL','ALL',b.vc_purity,'ALL') AND 
					a.vc_category = 'ALL' AND 
					a.vc_comp_code = 'ALL' AND 
					b.vc_comp_code = a.vc_comp_code  AND 
					a.vc_category = b.vc_category AND 
					a.br_code='ALL' AND 
					b.br_code=a.br_code AND
					b.vc_sale_type not in('SS')
					ORDER BY 2

				"""	
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSalesChecklistWithTarget method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: getGS11OGreport
	 * Description: gets GS11 OG report data
	 *
	 * @return : list of GS11 OG report data
	 */
	def getGS11OGreport(fromDate,toDate,category,compCode,brCode,purity,department){
		def result2
		try{
			query =
				"""
					select voucher_date, vc_voucher_no,xtd.transaction_type,nvl(xta.default_Party_Name,party_name) party_name,purity, nvl(nang,0) Issue_Nang, nvl(pcs,0) Issue_pcs,nvl( gross_wt,0) Issue_grosswt,nvl(net_wt,0) Issue_net,nvl(carat_wt,0) Issue_carat,
					0 receipt_nang, 0 receipt_pieces,0 receipt_grosswt, 0 receipt_netwt,0 receipt_caratwt,nvl(amount,0)
					from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					where
					 xtd.TRANSACTION_TYPE =xta.TRANSACTION_TYPE
					and xta.type_of_TRANSACTION in( 'ISSUE','SALE')
					and xta.report_type = 'GS11OG'
					and voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
					union all
					select voucher_date, vc_voucher_no,xtd.transaction_type,nvl(xta.default_Party_Name,party_name) party_name, purity,  0 Issue_Nang, 0 Issue_Nang,0 Issue_Nang, 0 Issue_Nang, 0 Issue_Nang,
					nvl(nang,0) receipt_nang,nvl(pcs,0) receipt_pieces,nvl(gross_wt,0) receipt_grosswt,nvl(net_wt,0) receipt_netwt,nvl(carat_wt,0) receipt_caratwt,nvl(amount,0) 
					from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					WHERE
					 xtd.TRANSACTION_TYPE =xta.TRANSACTION_TYPE
					and xta.type_of_TRANSACTION= 'RECEIPT'
					and xta.report_type = 'GS11OG'
					and voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
				"""	
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getGS11OGreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: totalGS11OGreport
	 * Description: total GS11 OG report data
	 *
	 * @return : list of total GS11 OG report data
	 */
	def totalGS11OGreport(fromDate,toDate,category,compCode,brCode,purity,department){
		def result2
		try{
			query =
				"""
					select order_type, transaction_type, purity,
					sum(nvl(gross_wt,0)) gross_wt, sum(nvl(net_wt,0)) net_wt, sum(nvl(carat_wt,0)) carat_wt
					from (
						select 1 order_type, 'Opening' transaction_type, purity,
						case when xta.type_of_transaction in ('RECEIPT') then abs(gross_wt)
							 when xta.type_of_transaction in ('ISSUE','SALE') then abs(gross_wt)*-1 end gross_wt,
						case when xta.type_of_transaction in ('RECEIPT') then abs(net_wt)
							 when xta.type_of_transaction in ('ISSUE','SALE') then abs(net_wt)*-1 end net_wt,
						case when xta.type_of_transaction in ('RECEIPT') then abs(carat_wt)
							 when xta.type_of_transaction in ('ISSUE','SALE') then abs(carat_wt)*-1 end carat_wt
						from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
						where voucher_date < TO_DATE('$fromDate','MM/DD/YYYY')
						and xtd.transaction_type = xta.transaction_type
						and xta.type_of_transaction in ('ISSUE','SALE','RECEIPT')
						and xta.report_type = 'GS11OG'
						and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
						union all
						select 2 order_type,xtd.transaction_type, purity,
						case when xta.type_of_transaction in ('RECEIPT') then abs(gross_wt)
							 when xta.type_of_transaction in ('ISSUE','SALE') then abs(gross_wt)*-1 end gross_wt,
						case when xta.type_of_transaction in ('RECEIPT') then abs(net_wt)
							 when xta.type_of_transaction in ('ISSUE','SALE') then abs(net_wt)*-1 end net_wt,
						case when xta.type_of_transaction in ('RECEIPT') then abs(carat_wt)
							 when xta.type_of_transaction in ('ISSUE','SALE') then abs(carat_wt)*-1 end carat_wt
						from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
						where voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
						and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
						 and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
						and xtd.transaction_type = xta.transaction_type
						and xta.type_of_transaction in ( 'ISSUE','SALE','RECEIPT')
						and xta.report_type = 'GS11OG')
					group by order_type, transaction_type, purity
					order by order_type, transaction_type, purity
				"""
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService totalGS11OGreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	/**
	 * Method Name: getGS11STDreport
	 * Description: gets GS11 STD report data
	 *
	 * @return : list of GS11 OG report data
	 */
	def getGS11STDreport(fromDate,toDate,category,compCode,brCode,purity,department){
		def result2
		try{
			query =
				"""
					select voucher_date,vc_voucher_no,xtd.transaction_type,nvl(xta.default_Party_Name,party_name) party_name,purity, nvl(nang,0) Issue_Nang, nvl(pcs,0) Issue_pcs,nvl( gross_wt,0) Issue_grosswt,nvl(net_wt,0) Issue_net,nvl(carat_wt,0) Issue_carat,
					0 receipt_nang, 0 receipt_pieces,0 receipt_grosswt, 0 receipt_netwt,0 receipt_caratwt,nvl(amount,0)
					from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					where
					 xtd.TRANSACTION_TYPE =xta.TRANSACTION_TYPE
					and xta.type_of_TRANSACTION in( 'ISSUE','SALE')
					and xta.report_type = 'GS11STD'
					and voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
					union all
					select voucher_date,vc_voucher_no,xtd.transaction_type,nvl(xta.default_Party_Name,party_name) party_name, purity,  0 Issue_Nang, 0 Issue_Nang,0 Issue_Nang, 0 Issue_Nang, 0 Issue_Nang,
					nvl(nang,0) receipt_nang,nvl(pcs,0) receipt_pieces,nvl(gross_wt,0) receipt_grosswt,nvl(net_wt,0) receipt_netwt,nvl(carat_wt,0) receipt_caratwt,nvl(amount,0) 
					from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					WHERE
					 xtd.TRANSACTION_TYPE =xta.TRANSACTION_TYPE
					and xta.type_of_TRANSACTION= 'RECEIPT'
					and xta.report_type = 'GS11STD'
					and voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
				"""	
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getGS11STDreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: totalGS11STDreport
	 * Description: total GS11 STD report data
	 *
	 * @return : list of total GS11 STD report data
	 */
	def totalGS11STDreport(fromDate,toDate,category,compCode,brCode,purity,department){
		def result2
		try{
			query =
				"""
					select order_type, transaction_type, purity,
					sum(nvl(gross_wt,0)) gross_wt, sum(nvl(net_wt,0)) net_wt, sum(nvl(carat_wt,0)) carat_wt
					from (
					    select 1 order_type, 'Opening' transaction_type, purity,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(gross_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(gross_wt)*-1 end gross_wt, 
					    case when xta.type_of_transaction in ('RECEIPT') then abs(net_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(net_wt)*-1 end net_wt,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(carat_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(carat_wt)*-1 end carat_wt 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where voucher_date < TO_DATE('$fromDate','MM/DD/YYYY') 
					    and xtd.transaction_type = xta.transaction_type
					    and xta.type_of_transaction in ('ISSUE','SALE','RECEIPT')
					    and xta.report_type = 'GS11STD'
					    and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
					    union all
					    select 2 order_type,xtd.transaction_type, purity,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(gross_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(gross_wt)*-1 end gross_wt, 
					    case when xta.type_of_transaction in ('RECEIPT') then abs(net_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(net_wt)*-1 end net_wt,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(carat_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(carat_wt)*-1 end carat_wt
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					    and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					     and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
					    and xtd.transaction_type = xta.transaction_type
					    and xta.type_of_transaction in ( 'ISSUE','SALE','RECEIPT')
					    and xta.report_type = 'GS11STD')
					group by order_type, transaction_type, purity
					order by order_type, transaction_type, purity
				"""
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService totalGS11STDreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: getGS11OG report
	 * Description: gets GS11 OG report data
	 *
	 * @return : list of GS11 OG report data
	 */
	def getGs12report(fromDate,toDate,category,compCode,brCode,purity,department){
		def result2
		try{
			query =
				"""
					select voucher_date, vc_voucher_no,xtd.transaction_type,nvl(xta.default_Party_Name,party_name) party_name,purity, nvl(nang,0) Issue_Nang, nvl(pcs,0) Issue_pcs,nvl( gross_wt,0) Issue_grosswt,nvl(net_wt,0) Issue_net,nvl(carat_wt,0) Issue_carat,
					0 receipt_nang, 0 receipt_pieces,0 receipt_grosswt, 0 receipt_netwt,0 receipt_caratwt,nvl(amount,0)
					from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					where
					 xtd.TRANSACTION_TYPE =xta.TRANSACTION_TYPE
					and xta.type_of_TRANSACTION in( 'ISSUE','SALE')
					and xta.report_type = 'GS12'
					and voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
					union all
					select voucher_date, vc_voucher_no,xtd.transaction_type,nvl(xta.default_Party_Name,party_name) party_name, purity,  0 Issue_Nang, 0 Issue_Nang,0 Issue_Nang, 0 Issue_Nang, 0 Issue_Nang,
					nvl(nang,0) receipt_nang,nvl(pcs,0) receipt_pieces,nvl(gross_wt,0) receipt_grosswt,nvl(net_wt,0) receipt_netwt,nvl(carat_wt,0) receipt_caratwt,nvl(amount,0) 
					from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					WHERE
					 xtd.TRANSACTION_TYPE =xta.TRANSACTION_TYPE
					and xta.type_of_TRANSACTION= 'RECEIPT'
					and xta.report_type = 'GS12'
					and voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')

				"""	
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getGs12report method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: totalgs12report
	 * Description: total GS11 OG report data
	 *
	 * @return : list of total GS11 OG report data
	 */
	def totalGs12report(fromDate,toDate,category,compCode,brCode,purity,department){
		def result2
		try{
			query =
				"""
					select order_type, transaction_type, purity,
					sum(nvl(gross_wt,0)) gross_wt, sum(nvl(net_wt,0)) net_wt, sum(nvl(carat_wt,0)) carat_wt
					from (
					    select 1 order_type, 'Opening' transaction_type, purity,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(gross_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(gross_wt)*-1 end gross_wt, 
					    case when xta.type_of_transaction in ('RECEIPT') then abs(net_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(net_wt)*-1 end net_wt,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(carat_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(carat_wt)*-1 end carat_wt 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where voucher_date < TO_DATE('$fromDate','MM/DD/YYYY') 
					    and xtd.transaction_type = xta.transaction_type
					    and xta.type_of_transaction in ('ISSUE','SALE','RECEIPT')
					    and xta.report_type = 'GS12'
					    and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
					    union all
					    select 2 order_type,xtd.transaction_type, purity,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(gross_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(gross_wt)*-1 end gross_wt, 
					    case when xta.type_of_transaction in ('RECEIPT') then abs(net_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(net_wt)*-1 end net_wt,
					    case when xta.type_of_transaction in ('RECEIPT') then abs(carat_wt)
					         when xta.type_of_transaction in ('ISSUE','SALE') then abs(carat_wt)*-1 end carat_wt
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where voucher_date >= TO_DATE('$fromDate','MM/DD/YYYY')
					    and voucher_date < TO_DATE('$toDate','MM/DD/YYYY')+1
					     and CATEGORY = decode('ALL','ALL',CATEGORY,'ALL')
					    and xtd.transaction_type = xta.transaction_type
					    and xta.type_of_transaction in ( 'ISSUE','SALE','RECEIPT')
					    and xta.report_type = 'GS12')
					group by order_type, transaction_type, purity
					order by order_type, transaction_type, purity

				"""
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService totalGs12report method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: getNangmelreport
	 * Description: gets Nangmel report data
	 *
	 * @return : list of Nangmel report data
	 */
	def getNangmelreport(nangmelDate,category,compCode,brCode){
		def result2
		try{
			query =
				"""
					select locator_name, purity,
					sum(nvl(nang_op,0)) nang_op, sum(nvl(pcs_op,0)) pcs_op, sum(nvl(gross_wt_op,0)) gross_wt_op, sum(nvl(net_wt_op,0)) net_wt_op, sum(nvl(carat_wt_op,0)) carat_wt_op,
					sum(nvl(nang_or,0)) nang_or, sum(nvl(pcs_or,0)) pcs_or, sum(nvl(gross_wt_or,0)) gross_wt_or, sum(nvl(net_wt_or,0)) net_wt_or, sum(nvl(carat_wt_or,0)) carat_wt_or,
					sum(nvl(nang_sa,0)) nang_sa, sum(nvl(pcs_sa,0)) pcs_sa, sum(nvl(gross_wt_sa,0)) gross_wt_sa, sum(nvl(net_wt_sa,0)) net_wt_sa, sum(nvl(carat_wt_sa,0)) carat_wt_sa,
					sum(nvl(nang_oi,0)) nang_oi, sum(nvl(pcs_oi,0)) pcs_oi, sum(nvl(gross_wt_oi,0)) gross_wt_oi, sum(nvl(net_wt_oi,0)) net_wt_oi, sum(nvl(carat_wt_oi,0)) carat_wt_oi,
					sum(nvl(nang_op,0)) + sum(nvl(nang_or,0)) - sum(nvl(nang_sa,0)) - sum(nvl(nang_oi,0)) nang_cl,
					sum(nvl(pcs_op,0)) + sum(nvl(pcs_or,0)) - sum(nvl(pcs_sa,0)) - sum(nvl(pcs_oi,0)) pcs_cl,
					sum(nvl(gross_wt_op,0)) + sum(nvl(gross_wt_or,0)) - sum(nvl(gross_wt_sa,0)) - sum(nvl(gross_wt_oi,0)) gross_wt_cl,
					sum(nvl(net_wt_op,0)) + sum(nvl(net_wt_or,0)) - sum(nvl(net_wt_sa,0)) - sum(nvl(net_wt_oi,0)) net_wt_cl,
					sum(nvl(carat_wt_op,0)) + sum(nvl(carat_wt_or,0)) - sum(nvl(carat_wt_sa,0)) - sum(nvl(carat_wt_oi,0)) carat_wt_cl
					from (
					select locator_name, purity,
					nvl(nang_or,0)      - nvl(nang_sa,0)    - nvl(nang_oi,0) nang_op,
					nvl(pcs_or,0)       - nvl(pcs_sa,0)     - nvl(pcs_oi,0) pcs_op,
					nvl(gross_wt_or,0)  - nvl(gross_wt_sa,0)- nvl(gross_wt_oi,0) gross_wt_op,
					nvl(net_wt_or,0)    - nvl(net_wt_sa,0)  - nvl(net_wt_oi,0) net_wt_op,
					nvl(carat_wt_or,0)  - nvl(carat_wt_sa,0)- nvl(carat_wt_oi,0) carat_wt_op,
					0 nang_or, 0 pcs_or, 0 gross_wt_or, 0 net_wt_or, 0 carat_wt_or,
					0 nang_sa, 0 pcs_sa, 0 gross_wt_sa, 0 net_wt_sa, 0 carat_wt_sa,
					0 nang_oi, 0 pcs_oi, 0 gross_wt_oi, 0 net_wt_oi, 0 carat_wt_oi 
					from (
					    select locator_name, purity, 
					    nang nang_or, pcs pcs_or, gross_wt gross_wt_or, net_wt net_wt_or, carat_wt carat_wt_or, amount  amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa, 
					    0 nang_oi,0 pcs_oi,0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(xtd.voucher_date) < TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and xtd.category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='RECEIPT'
					    union all
					    select locator_name,purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    nang nang_sa, pcs pcs_sa, gross_wt gross_wt_sa, net_wt net_wt_sa, carat_wt carat_wt_sa,
					    amount amount_sa, 0 nang_oi,0 pcs_oi,0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) < 
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='SALE'
					    union all
					    select locator_name,purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa,
					    nang nang_oi,pcs pcs_oi,gross_wt gross_wt_oi,net_wt net_wt_oi,carat_wt carat_wt_oi, amount amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) < 
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='ISSUE')
					union all
					select locator_name, purity, 
					0 nang_op, 0 pcs_op, 0 gross_wt_op, 0 net_wt_op, 0 carat_wt_op,
					nang_or, pcs_or, gross_wt_or, net_wt_or, carat_wt_or,
					nang_sa, pcs_sa, gross_wt_sa, net_wt_sa, carat_wt_sa,
					nang_oi, pcs_oi, gross_wt_oi, net_wt_oi, carat_wt_oi
					from (
					    select locator_name,purity, nang  nang_or, pcs pcs_or, gross_wt gross_wt_or,net_wt net_wt_or ,
					    carat_wt carat_wt_or, amount  amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa, 
					    0 nang_oi,0 pcs_oi,0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) = TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='RECEIPT'
					    union all
					    select locator_name,purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    nang nang_sa, pcs pcs_sa, gross_wt gross_wt_sa, net_wt net_wt_sa, carat_wt carat_wt_sa,
					    amount amount_sa,
					    0 nang_oi,0 pcs_oi,0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) = TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='SALE'
					    union all
					    select locator_name,purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa,
					    nang nang_oi,pcs pcs_oi,gross_wt gross_wt_oi,net_wt net_wt_oi,carat_wt carat_wt_oi,
					    amount amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) = TO_DATE('$nangmelDate','MM/DD/YYYY')  
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='ISSUE'))
					group by locator_name, purity
					order by locator_name, purity
				"""	
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getNangmelreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: totalNangmel report
	 * Description: total GS11 OG report data
	 *
	 * @return : list of total GS11 OG report data
	 */
	def totalNangmelreport(nangmelDate,category,compCode,brCode){
		def result2
		try{
			query =
				"""
					select purity,
					sum(nvl(nang_op,0)) nang_op, sum(nvl(pcs_op,0)) pcs_op, sum(nvl(gross_wt_op,0)) gross_wt_op, sum(nvl(net_wt_op,0)) net_wt_op, sum(nvl(carat_wt_op,0)) carat_wt_op,
					sum(nvl(nang_or,0)) nang_or, sum(nvl(pcs_or,0)) pcs_or, sum(nvl(gross_wt_or,0)) gross_wt_or, sum(nvl(net_wt_or,0)) net_wt_or, sum(nvl(carat_wt_or,0)) carat_wt_or,
					sum(nvl(nang_sa,0)) nang_sa, sum(nvl(pcs_sa,0)) pcs_sa, sum(nvl(gross_wt_sa,0)) gross_wt_sa, sum(nvl(net_wt_sa,0)) net_wt_sa, sum(nvl(carat_wt_sa,0)) carat_wt_sa,
					sum(nvl(nang_oi,0)) nang_oi, sum(nvl(pcs_oi,0)) pcs_oi, sum(nvl(gross_wt_oi,0)) gross_wt_oi, sum(nvl(net_wt_oi,0)) net_wt_oi, sum(nvl(carat_wt_oi,0)) carat_wt_oi,
					sum(nvl(nang_op,0)) + sum(nvl(nang_or,0)) - sum(nvl(nang_sa,0)) - sum(nvl(nang_oi,0)) nang_cl,
					sum(nvl(pcs_op,0)) + sum(nvl(pcs_or,0)) - sum(nvl(pcs_sa,0)) - sum(nvl(pcs_oi,0)) pcs_cl,
					sum(nvl(gross_wt_op,0)) + sum(nvl(gross_wt_or,0)) - sum(nvl(gross_wt_sa,0)) - sum(nvl(gross_wt_oi,0)) gross_wt_cl,
					sum(nvl(net_wt_op,0)) + sum(nvl(net_wt_or,0)) - sum(nvl(net_wt_sa,0)) - sum(nvl(net_wt_oi,0)) net_wt_cl,
					sum(nvl(carat_wt_op,0)) + sum(nvl(carat_wt_or,0)) - sum(nvl(carat_wt_sa,0)) - sum(nvl(carat_wt_oi,0)) carat_wt_cl
					from (
					select purity,
					nvl(nang_or,0)      - nvl(nang_sa,0)    - nvl(nang_oi,0) nang_op,
					nvl(pcs_or,0)       - nvl(pcs_sa,0)     - nvl(pcs_oi,0) pcs_op,
					nvl(gross_wt_or,0)  - nvl(gross_wt_sa,0)- nvl(gross_wt_oi,0) gross_wt_op,
					nvl(net_wt_or,0)    - nvl(net_wt_sa,0)  - nvl(net_wt_oi,0) net_wt_op,
					nvl(carat_wt_or,0)  - nvl(carat_wt_sa,0)- nvl(carat_wt_oi,0) carat_wt_op,
					0 nang_or, 0 pcs_or, 0 gross_wt_or, 0 net_wt_or, 0 carat_wt_or,
					0 nang_sa, 0 pcs_sa, 0 gross_wt_sa, 0 net_wt_sa, 0 carat_wt_sa,
					0 nang_oi, 0 pcs_oi, 0 gross_wt_oi, 0 net_wt_oi, 0 carat_wt_oi 
					from (
					    select purity,
					    nang nang_or, pcs pcs_or, gross_wt gross_wt_or, net_wt net_wt_or, carat_wt carat_wt_or, amount  amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa, 
					    0 nang_oi,0 pcs_oi,	0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) < TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='RECEIPT'
					    union all
					    select purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    nang nang_sa, pcs pcs_sa, gross_wt gross_wt_sa, net_wt net_wt_sa, carat_wt carat_wt_sa,
					    amount amount_sa, 0 nang_oi,0 pcs_oi,0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) < TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='SALE'
					    union all
					    select purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa,
					    nang nang_oi,pcs pcs_oi,gross_wt gross_wt_oi,net_wt net_wt_oi,carat_wt carat_wt_oi, amount amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) < TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='ISSUE')
					union all
					select purity, 
					0 nang_op, 0 pcs_op, 0 gross_wt_op, 0 net_wt_op, 0 carat_wt_op,
					nang_or, pcs_or, gross_wt_or, net_wt_or, carat_wt_or,
					nang_sa, pcs_sa, gross_wt_sa, net_wt_sa, carat_wt_sa,
					nang_oi, pcs_oi, gross_wt_oi, net_wt_oi, carat_wt_oi
					from (
					    select purity, nang  nang_or, pcs pcs_or, gross_wt gross_wt_or,net_wt net_wt_or ,
					    carat_wt carat_wt_or, amount  amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa, 
					    0 nang_oi,0 pcs_oi,0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) = TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='RECEIPT'
					    union all
					    select purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    nang nang_sa, pcs pcs_sa, gross_wt gross_wt_sa, net_wt net_wt_sa, carat_wt carat_wt_sa,
					    amount amount_sa,
					    0 nang_oi,0 pcs_oi,0 gross_wt_oi,0 net_wt_oi,0 carat_wt_oi,0 amount_oi
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) = TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='SALE'
					    union all
					    select purity,0 nang_or ,0 pcs_or,0 gross_wt_or,0 net_wt_or,0 carat_wt_or,0 amount_or,
					    0 nang_sa,0 pcs_sa,0 gross_wt_sa,0 net_wt_sa,0 carat_wt_sa,0 amount_sa,
					    nang nang_oi,pcs pcs_oi,gross_wt gross_wt_oi,net_wt net_wt_oi,carat_wt carat_wt_oi,
					    amount amount_oi 
					    from apps.xxtbz_transaction_details_v xtd,tbzcustom.xxtbz_trx_actions xta
					    where trunc(voucher_date) = TO_DATE('$nangmelDate','MM/DD/YYYY')
					    --and category='ALL'
					    and xta.report_type='NANGMEL'
					    and xtd.transaction_type=xta.transaction_type
					    and xta.type_of_transaction='ISSUE'))
					group by purity
					order by purity
				"""
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService totalNangmelreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	
	/**
	 * Method Name: getSalesStockPercentreport report
	 * Description: gets Sales Stock Percent report data
	 *
	 * @return : list of Sales Stock Percent report data
	 */
	def getSalesStockPercentreport(fromDate,toDate,category,compCode,brCode,location){
		def result2
		try{
			query =
				"""
					select category, product, purity,
					sum(Nang) Nang, 
					sum(Pcs) Pcs,
					sum(Gross) Gross,
					sum(Net) Net,
					sum(Crt) Crt,
					sum(sum(Nang)) over (partition by category, product, purity) tot_nang,
					sum(sum(pcs)) over (partition by category, product, purity) tot_pcs,
					sum(sum(Gross)) over (partition by category, product, purity) tot_gross,
					sum(sum(Net)) over (partition by category, product, purity) tot_net,
					sum(sum(Crt)) over (partition by category, product, purity) tot_crt
					from( 
					select  i.category, i.product, i.purity, 
					sum(nvl(nang_op,0)) + sum(nvl(nang_or,0)) - sum(nvl(nang_sa,0)) - sum(nvl(nang_oi,0)) Nang,
					sum(nvl(pcs_op,0)) + sum(nvl(pcs_or,0)) - sum(nvl(pcs_sa,0)) - sum(nvl(pcs_oi,0)) Pcs,
					sum(nvl(gross_wt_op,0)) + sum(nvl(gross_wt_or,0)) - sum(nvl(gross_wt_sa,0)) - sum(nvl(gross_wt_oi,0)) Gross,
					sum(nvl(net_wt_op,0)) + sum(nvl(net_wt_or,0)) - sum(nvl(net_wt_sa,0)) - sum(nvl(net_wt_oi,0)) Net,
					sum(nvl(carat_wt_op,0)) + sum(nvl(carat_wt_or,0)) - sum(nvl(carat_wt_sa,0)) - sum(nvl(carat_wt_oi,0)) Crt
					from apps.xxtbz_nangmel_daywise_op_v i, apps.xxtbz_nangmel_daywise_trx_v j 
					where i.voucher_date = j.voucher_date(+)
					and i.category = j.category(+)
					and i.product = j.product(+)
					and i.purity = j.purity(+)
					and i.voucher_date between :from_date and :to_date
					and i.category = :vc_category1
					group by i.category, i.product, i.purity)
					group by category, product, purity
				"""	
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService getSalesStockPercentreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
	
	/**
	 * Method Name: totalSalesStockPercentreport
	 * Description: total SalesStockPercent report data
	 *
	 * @return : list of total Sales Stock Percent report data
	 */
	def totalSalesStockPercentreport(fromDate,toDate,category,compCode,brCode,location){
		def result2
		try{
			query =
				"""
					SELECT   br_code, vc_category, vc_item_code, vc_purity||'K' vc_purity,
				         NVL (SUM (nu_nang), 0) nu_nang,
				         NVL (SUM (nu_nang_pcs), 0) nu_nang_pcs,
				         NVL (SUM (nu_gross_wtt), 0) nu_gross_wtt,
				         NVL (SUM (nu_nett_wtt), 0) nu_nett_wtt,
				         NVL (SUM (nu_carrat_wtt), 0) nu_carrat_wtt
				    FROM (SELECT   a.br_code, a.vc_category, b.vc_item_code, b.vc_sub_code, b.vc_purity, 
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_nang,
				                                     b.nu_nang
				                                    )
				                            ),
				                        0
				                       ) nu_nang,
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_nang_pcs,
				                                     b.nu_nang_pcs
				                                    )
				                            ),
				                        0
				                       ) nu_nang_pcs,
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_gross_wtt,
				                                     b.nu_gross_wtt
				                                    )
				                            ),
				                        0
				                       ) nu_gross_wtt,
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_nett_wtt,
				                                     b.nu_nett_wtt
				                                    )
				                            ),
				                        0
				                       ) nu_nett_wtt,
				                  NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_carrat_wtt,
				                                     b.nu_carrat_wtt
				                                    )
				                            ),
				                        0
				                       ) nu_carrat_wtt
				              FROM sales.hd_cash a, sales.dt_cash b
				             WHERE a.vc_voucher_no = b.vc_voucher_no
				               AND a.dt_voucher_date = b.dt_voucher_date
				               AND SUBSTR (a.vc_voucher_no, length(a.br_code)+1, 2) IN ('SI', 'CI', 'EI', 'FI')
				               AND a.ch_cancel_flag = 'N'
				               AND b.dt_voucher_date BETWEEN :from_date AND :to_date
				               AND b.vc_comp_code = a.vc_comp_code
				               AND a.vc_category = b.vc_category
				               AND b.br_code = a.br_code
				               AND b.vc_sale_type IN ('SA','W')
				          GROUP BY a.br_code, a.vc_category, b.vc_item_code, b.vc_sub_code, b.vc_purity
				          UNION
				          SELECT   a.br_code, a.vc_category, c.vc_item_code, c.vc_sub_code, c.vc_purity,
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_nang,
				                                     b.nu_nang
				                                    )
				                            ),
				                        0
				                       ) nu_nang,
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_nang_pcs,
				                                     b.nu_nang_pcs
				                                    )
				                            ),
				                        0
				                       ) nu_nang_pcs,
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_gross_wtt,
				                                     b.nu_gross_wtt
				                                    )
				                            ),
				                        0
				                       ) nu_gross_wtt,
				                   NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_nett_wtt,
				                                     b.nu_nett_wtt
				                                    )
				                            ),
				                        0
				                       ) nu_nett_wtt,
				                  NVL (SUM (DECODE (b.vc_sale_type,
				                                     'K', -b.nu_carrat_wtt,
				                                     b.nu_carrat_wtt
				                                    )
				                            ),
				                        0
				                       ) nu_carrat_wtt
				              FROM sales.hd_cash a, sales.dt_cash b, sales.dt_cash c
				             WHERE a.vc_voucher_no = b.vc_voucher_no
				               AND a.dt_voucher_date = b.dt_voucher_date
				               AND b.dt_voucher_date = c.dt_voucher_date
				               AND SUBSTR (a.vc_voucher_no, length(a.br_code)+1, 2) IN ('SI', 'CI', 'EI', 'FI')
				               AND a.ch_cancel_flag = 'N'
				              AND b.dt_voucher_date BETWEEN :from_date AND :to_date
				               AND b.vc_comp_code = a.vc_comp_code
				               AND a.vc_category = b.vc_category
				               AND b.vc_kw_label = c.vc_label_no
				               AND b.br_code = a.br_code
				               AND c.br_code = a.br_code
				               AND b.vc_item_code IN ('UL')
				               AND b.vc_sale_type IN ('K')
				          GROUP BY a.br_code, a.vc_category, c.vc_item_code, c.vc_sub_code, c.vc_purity)
				GROUP BY br_code, vc_category, vc_item_code, vc_purity
				"""
			Sql sql1 = new Sql(dataSource)
			result2 = sql1.rows(query)
		}catch(Exception exception){
			log.info("Exception in ReportService totalSalesStockPercentreport method : "+exception)
			flash.message = message(code: 'default.exception.message', args: [exception.getMessage()])
		}
		return result2
	}
}

