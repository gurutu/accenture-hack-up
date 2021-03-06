package com.digital_hack_up.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.digital_hack_up.dto.CreditCardDTO;
import com.digital_hack_up.mapper.CreditCardRowMapper;

@Repository
public class CreditDaoImp implements CreditDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<CreditCardDTO> getDataEducationVsLimitBal() {
		return jdbcTemplate.query("SELECT education,SUM(limit_bal) AS bal FROM credit_card GROUP BY education ORDER BY bal", new CreditCardRowMapper());
	}

	
	
}
