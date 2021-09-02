package com.Courior.model;

import java.util.List;

import com.Admin.model.AdminVO;
import com.Consumer.model.ConsumerVO;

public interface CouriorDAO_interface {

    public void insert(CouriorVO couriorVO);
    
    public void update(CouriorVO couriorVO);

    public void delete(Integer serialNumber);
   
    public void updateAdminAuthentication(CouriorVO couriorVO);
    
    public CouriorVO findByAccountNumber(Integer accountNumber);
    
    public CouriorVO findByEmail(String email);
    
    public List<CouriorVO> getAll();
}
