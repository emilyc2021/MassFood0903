package com.Admin.model;

import java.util.List;


public interface AdminDAO_interface {

    public void insert(AdminVO adminVO);
    
    public void update(AdminVO adminVO);
    
    public void delete(Integer accountNumber);
    
    public AdminVO findByAccountNumber(Integer accountNumber);
    
    public AdminVO findByEmail(String email);    
        
    public List<AdminVO> getAll();

}
