package com.Emily.Model;


import com.Admin.model.AdminVO;
import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorVO;
import com.Merchant.model.MerchantVO;

public interface Admin1DAO_interface {
	//登入
	public AdminVO findLogin(String email,String password);

	//查詢消費者待審核人數
	public Integer findM1Status1();
	//查詢消費者通過人數
	public Integer findM1Status2();
	
	//查詢商家待審核人數
	public Integer findM2Status1();
	//查詢商家通過人數
	public Integer findM2Status2();
	
	//查詢外送員待審核人數
	public Integer findM3Status1();
	//查詢外送員通過人數
	public Integer findM3Status2();
	
	//更新消費者資訊(密碼、身分證、前次修改時間、前次修改人員、是否啟用、帳號狀態)
	  public void updateM1(ConsumerVO consumerVO);
	//更新商家資訊(密碼、統編、身分證、前次修改時間、前次修改人員、是否啟用、帳號狀態)
	  public void updateM2(MerchantVO merchantVO);
	//更新外送員資訊(密碼、身分證、前次修改時間、前次修改人員、是否啟用、帳號狀態)
	  public void updateM3(CouriorVO couriorVO);

}
