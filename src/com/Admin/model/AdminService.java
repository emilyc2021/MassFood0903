package com.Admin.model;

import java.util.List;

public class AdminService {
	
	private AdminDAO_interface dao;
	
	public AdminService() {
		dao=new AdminJDBCDAO();
	}

	public AdminVO insert(String Email,String Password) {
		AdminVO adminVO=new AdminVO();
		adminVO.setEmail(Email);
		adminVO.setPassword(Password);
		dao.insert(adminVO);

		return adminVO;
	}
	    
//	    public AdminVO update(Integer accountNumber, String Password) {
//	    	AdminVO adminVO=new AdminVO();
//			adminVO.setAccountNumber(accountNumber);
//			adminVO.setPassword(Password);
//			dao.insert(adminVO);
//
//			return adminVO;
//	    }
	    
	    public void delete(Integer accountNumber) {
	    	dao.delete(accountNumber);
	    }
	    
	    public AdminVO findByAccountNumber(Integer accountNumber) {
	    	return dao.findByAccountNumber(accountNumber);
	    }
	    
	    public AdminVO findByEmail(String email) {
	    	return dao.findByEmail(email);
	    }
	    
	    public List<AdminVO> getAll(){
	    	return dao.getAll();
	    }
	
}
