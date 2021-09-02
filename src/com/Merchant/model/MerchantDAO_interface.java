package com.Merchant.model;

import java.util.List;

import com.Admin.model.AdminVO;

public interface MerchantDAO_interface {

    public void insert(MerchantVO merchantVO);
    
    public void update(MerchantVO merchantVO);
    
    public void updateBusinessInformation(MerchantVO merchantVO);
    
    public void updateBaseInformation(MerchantVO merchantVO);
    
    public void updateAdminAuthentication(MerchantVO merchantVO);
    
    public void delete(Integer serialNumber);
    
    public MerchantVO findByAccountNumber(Integer accountNumber);
    
    public MerchantVO findByEmail(String email);

    public List<MerchantVO> getAll();
}
