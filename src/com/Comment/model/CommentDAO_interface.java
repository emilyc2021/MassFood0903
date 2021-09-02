package com.Comment.model;

import java.util.List;


public interface CommentDAO_interface {

    public void insert(CommentVO commentVO);
    
    public void update(CommentVO commentVO);
    
    public void delete(Integer serialNumber);
    
 
    public List<CommentVO> findByOrderNumber(Integer orderNumber);//依訂單編號取得評論，這邊供顯示於商家基本資料。
    
    
    public List<CommentVO> findByMerchantAccountNumber(Integer merchantAccountNumber);//依商家編號取得評論，這邊供顯示於商家基本資料。
    
   
	//廠商專區-找出廠商有回覆的訂單編號-這邊加上面的「GET_ALL_BY_MERCHANTACCOUNTNUMBER_SHOW_lASTEST_STMT」就可以列示出廠商是否有回覆過。
    public List<Integer> findByMerchantAccountNumberForHadResponseOrderNumber(Integer merchantAccountNumber);
    	
    //因為因為同一筆訂單編號可找到多筆Comment，系統僅顯示最新的一筆。
    
//    findByMerchantAccountNumberForLastest
    
    public List<CommentVO> findByMerchantAccountNumberForLastest(Integer accountNumber); //依MerchantAccountNumber取得最新的一筆評論
    
    //findByAccountNumberForLastest
    public List<CommentVO> findByAccountNumberForLastest(Integer accountNumber, Boolean IsFromConsumer); //依AccountNumber取得最新的一筆評論
    
    public List<CommentVO> getAll();
    
	
}
