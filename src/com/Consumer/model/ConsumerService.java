package com.Consumer.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.Admin.model.AdminJDBCDAO;
import com.Admin.model.AdminVO;

public class ConsumerService {
	
	private ConsumerDAO_interface dao;
	
	
	 public ConsumerService() {
		 dao=new ConsumerJDBCDAO();
	}

	public ConsumerVO insert(String Email,String Password,String Name,String IdentityNumber,String MobilePhone,String Address,String Photo,String DeliveryAddresses,
			String CreditCardNumber,Date CreditCardExpirationDate,String CreditSecurityCode,Integer LastUpdateAccountNumber,Timestamp LastUpdateDatetime,
			Boolean IsEnable,	Date EnrollmentDate,	Boolean IsExposeMealInformation,Integer Status) {
		ConsumerVO consumerVO=new ConsumerVO();
		consumerVO.setEmail(Email);
		consumerVO.setPassword(Password);
	
		dao.insert(consumerVO);

		return consumerVO;
		
		
	};
	    
//	    public ConsumerVO  update(String Email,String Password,String Name,String IdentityNumber,String MobilePhone,String Address,String Photo,String DeliveryAddresses,
//	    		String CreditCardNumber,Date CreditCardExpirationDate,String CreditSecurityCode,Integer LastUpdateAccountNumber,Timestamp LastUpdateDatetime,
//	    		Boolean IsEnable,	Date EnrollmentDate,	Boolean IsExposeMealInformation,Integer Status) {
//	    	
//	    }

	    
	    public ConsumerVO findByAccountNumber(Integer accountNumber) {
	    	return dao.findByAccountNumber(accountNumber);
	    }
	    
	    public ConsumerVO findByEmail(String email) {
	    	return dao.findByEmail(email);
	    }
	    
	    public List<ConsumerVO> getAll(){
	    	return dao.getAll();
	    }

}
