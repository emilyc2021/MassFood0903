package com.Courior.model;

import java.util.List;




public class CouriorService {
	
	private CouriorDAO_interface dao;
	
	public CouriorService() {
		 dao=new CouriorJDBCDAO();
	}
	
//    public void insert(CouriorVO couriorVO);
//    
//    public void update(CouriorVO couriorVO);
//
//    public void delete(Integer serialNumber);
//   
//    public void updateAdminAuthentication(CouriorVO couriorVO);
    
    public CouriorVO findByAccountNumber(Integer accountNumber) {
    	return dao.findByAccountNumber(accountNumber);
    }
    
//    public CouriorVO findByEmail(String email);
//    
//    public List<CouriorVO> getAll();

}
