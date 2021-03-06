package com.digital_hack_up.service;

import java.util.List;

import com.digital_hack_up.bean.CreDitCardIndexBean;
import com.digital_hack_up.bean.CreditCard;
import com.digital_hack_up.dto.CreditCardDTO;

public interface CreditService {

	void saveAll(List<CreditCard> read);

	CreditCard findById(Integer i);

	List<CreditCard> getAllUserData();

	String indexerData();

	Iterable<CreDitCardIndexBean> getAllDataFromElastic();

	List<CreDitCardIndexBean> getInElastic(String param);
	
    List<CreditCardDTO> getDataEducationVsLimitBal();

}
