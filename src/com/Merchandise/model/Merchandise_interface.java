package com.Merchandise.model;

import java.util.List;

public interface Merchandise_interface {

    public void insert(MerchandiseVO merchandiseVO);
    
    public void update(MerchandiseVO merchandiseVO);
    
    public void delete(Integer itemNumber);
    
    public MerchandiseVO findByItemNumber(Integer itemNumber);
    
    public List<MerchandiseVO> findByAccountNumber(Integer accountNumber);
    
    public List<MerchandiseVO> getAll();
}
