package com.Consumer.model;

import java.util.List;

import com.Admin.model.AdminVO;
import com.Merchant.model.MerchantVO;

public interface ConsumerDAO_interface {

	
    public void insert(ConsumerVO consumerVO);
    
    public void update(ConsumerVO consumerVO);
    
    public void updateAdminAuthentication(ConsumerVO consumerVO);
    
    public void delete(Integer serialNumber);
    
    public ConsumerVO findByAccountNumber(Integer accountNumber);
    
    public ConsumerVO findByEmail(String email);
    
    public List<ConsumerVO> getAll();
}
