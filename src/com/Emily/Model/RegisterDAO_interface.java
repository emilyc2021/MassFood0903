package com.Emily.Model;

import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorVO;
import com.Merchant.model.MerchantVO;

public interface RegisterDAO_interface {
	
	//消費者-註冊表單更新(前次修改時間、前次修改人員、是否啟用、帳號狀態)
	  public void updateRegisterM1(ConsumerVO consumerVO);
	  
	//商家-註冊表單更新(前次修改時間、前次修改人員、是否啟用、帳號狀態)
	  public void updateRegisterM2(MerchantVO merchantVO);
	  
	//外送員-註冊表單更新(前次修改時間、前次修改人員、是否啟用、帳號狀態)
	  public void updateRegisterM3(CouriorVO couriorVO);
	

}
