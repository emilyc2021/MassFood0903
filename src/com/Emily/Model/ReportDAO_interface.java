package com.Emily.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.OrderMaster.model.OrderMasterVO;

public interface ReportDAO_interface {
	
	//報表查詢-for 商家
	//訂單OK
	public List<ReportVO>  report_ForOrderOKM2(String date,int account);
	//訂單取消
	public List<ReportVO>   report_ForOrderCancelM2(String date,int account);
	//應付帳款
	public List<ReportVO>  report_ForOrderPayableM2(String date,int account);
	
	//圖表每日訂單數、訂單金額、訂單日期
	public ArrayList  report_ForOrderAmountM2(String date,int account);	
	public ArrayList report_ForOrdersM2(String date,int account);	
	public ArrayList report_ForOrderDuringM2(String date,int account);	
	//圖表 熱門餐點+數量
	public ArrayList report_ForPopMealM2(String date,int account);	
	public ArrayList report_ForPopMealTotalM2(String date,int account);	
	
	//報表查詢-for 外送員
	//訂單OK
	public List<ReportVO>  report_ForOrderOKM3(String date,int account);
	//訂單取消
//	public List<ReportVO>   report_ForOrderCancelM3(String date,int account);
	//應付帳款
	public List<ReportVO>  report_ForOrderPayableM3(String date,int account);
	
	
}
