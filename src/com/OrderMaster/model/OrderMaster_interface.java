package com.OrderMaster.model;

import java.util.List;

public interface OrderMaster_interface {

	
    public void insert(OrderMasterVO orderMasterVO);
    
    public void update(OrderMasterVO orderMasterVO);
    
    public List<OrderMasterVO> getAll();
    
    public void delete(Integer orderNumber);
    
    public OrderMasterVO findByOrderNumber(Integer orderNumber);
    
    public List<OrderMasterVO> findByMerchantAccountnumber(Integer merchantAccountNumber); //商家
    
    public List<OrderMasterVO> findByCouriorAccountnumber(Integer couriorAccountNumber); //外送員
    
    public List<OrderMasterVO> findByConsumerAccountnumber(Integer consumerAccountNumber); //訂餐人員
    
    public List<OrderMasterVO> findByDinigAccountnumber(Integer diningAccountNumber); //用餐人員-->好友動態牆
    
 
	
	
}
