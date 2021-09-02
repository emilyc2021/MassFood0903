package com.Merchant.model;

import java.util.List;


	public class MerchantService {
		
		private MerchantDAO_interface dao;
		
		public MerchantService() {
			 dao=new MerchantJDBCDAO();
		}
	
//    public void insert(MerchantVO merchantVO);
    
//    public void update(MerchantVO merchantVO);
    
//    public void updateBusinessInformation(MerchantVO merchantVO);
    
//    public void updateBaseInformation(MerchantVO merchantVO);
    
//    public void updateAdminAuthentication(MerchantVO merchantVO);
    
//    public void delete(Integer serialNumber);
    
    public MerchantVO findByAccountNumber(Integer accountNumber) {
    	return dao.findByAccountNumber(accountNumber);
    }
    
   // public MerchantVO findByEmail(String email);

//    public List<MerchantVO> getAll();

}
