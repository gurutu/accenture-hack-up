package com.digital_hack_up.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.digital_hack_up.bean.CreDitCardIndexBean;
import com.digital_hack_up.bean.CreditCard;


import com.digital_hack_up.dao.CreditDao;
import com.digital_hack_up.dao.ElasticSearchRepositery;
import com.digital_hack_up.dao.UserRepository;
import com.digital_hack_up.dto.CreditCardDTO;
import com.digital_hack_up.querybuilder.SearchQueryBuilder;

@Service
public class CreditServiceImp implements CreditService {

	@Autowired
	CreditDao dao;
	
	
	  @Autowired UserRepository repo;
	  
	  @Autowired ElasticSearchRepositery elasticDao;
	  
	  @Autowired CreditDao creditDao;
	  
	  @Autowired SearchQueryBuilder search;
	
	
	@Override
	public void saveAll(List<CreditCard> read) {
		repo.saveAll(read);
		
	}


	@Override
	public CreditCard findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<CreditCard> c=repo.findById(id);
		CreditCard card=c.get();
		return card;
	}


	@Override
	public List<CreditCard> getAllUserData() {
		// TODO Auto-generated method stub
		return (List<CreditCard>) repo.findAll();
	}


	@Override
	public String indexerData() {
		List<CreditCard> l=(List<CreditCard>) repo.findAll();
		for (CreditCard c : l) {
			elasticDao.save(new CreDitCardIndexBean(c.getId(),c.getName(),c.getLimit_BAL(),c.getSex(),c.getEducation(),c.getMarriage(),
					c.getAge(),c.getPay_0(),c.getPay_2(),c.getPay_3(),c.getPay_4(),c.getPay_5(),c.getPay_6(),c.getBill_AMT1(),c.getBill_AMT2(),c.getBill_AMT3(),c.getBill_AMT4(),c.getBill_AMT5(),c.getBill_AMT6(),
					c.getPay_AMT1(),c.getPay_AMT2(),c.getPay_AMT3(),c.getPay_AMT4(),c.getPay_AMT5(),c.getPay_AMT6(),c.getLast()));
		}
		return "Successfull IndexData In Elastic";
	}


	@Override
	public Iterable<CreDitCardIndexBean> getAllDataFromElastic() {
		return elasticDao.findAll();
	}


	@Override
	public List<CreDitCardIndexBean> getInElastic(String param) {
		return search.getAll(param);
	}


	@Override
	public List<CreditCardDTO> getDataEducationVsLimitBal() {
		return creditDao.getDataEducationVsLimitBal();
	}

}
