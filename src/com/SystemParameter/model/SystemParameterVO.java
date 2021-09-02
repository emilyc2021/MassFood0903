package com.SystemParameter.model;

import java.io.Serializable;

public class SystemParameterVO implements Serializable{
	
	private Integer SerialNumber;
	private String TypeName;
	private String Code;
	private String Description;
	

	public Integer getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
}
