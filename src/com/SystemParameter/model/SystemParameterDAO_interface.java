package com.SystemParameter.model;

import java.util.*;

public interface SystemParameterDAO_interface {

    public void insert(SystemParameterVO systemParameterVO);
    
    public void update(SystemParameterVO systemParameterVO);
    
    public void delete(int serialNumber);
    
    public List<SystemParameterVO> findByTypeName(String typeName);
    
    public List<SystemParameterVO> getAll();

}
