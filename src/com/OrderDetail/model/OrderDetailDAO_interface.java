package com.OrderDetail.model;

import java.util.List;

import com.Merchant.model.MerchantVO;

public interface OrderDetailDAO_interface {

	
    public void insert(OrderDetailVO orderDetailVO);
    
    public void update(OrderDetailVO orderDetailVO);
    
    public void delete(Integer orderNumber);
    
    public List<OrderDetailVO> findByOrderNumber(Integer orderNumber);
    
    public List<OrderDetailVO> findByItemNumber(Integer itemNumber);
  
    public List<OrderDetailVO> getAll();
	
}
