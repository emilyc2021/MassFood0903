package com.Emily.Model;

import java.util.List;
import java.util.Map;

import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorVO;
import com.Merchant.model.MerchantVO;
import com.OrderMaster.model.OrderMasterVO;

public interface MemberDAO_interface {
	
	  //會員查詢複合查詢(傳入參數型態Map)(回傳 List)
	//消費者
    public List<ConsumerVO> getAll_M1(Map<String, String[]> map); 
    //商家
    public List<MerchantVO> getAll_M2(Map<String, String[]> map); 
    //消費者
    public List<CouriorVO> getAll_M3(Map<String, String[]> map); 
    
    //註冊表單查詢複合查詢(傳入參數型態Map)(回傳 List)
  	//消費者
      public List<ConsumerVO> register_M1(Map<String, String[]> map); 
      //商家
      public List<MerchantVO> register_M2(Map<String, String[]> map); 
      //消費者
      public List<CouriorVO> register_M3(Map<String, String[]> map); 
      
      //訂單複合查詢(傳入參數型態Map)(回傳 List)
      //消費者
        public List<OrderMasterVO> order_search(Map<String, String> map); 

    
    
}
