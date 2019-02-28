package com.springmvc.dao;

import java.math.BigDecimal;

import com.springmvc.pojo.City;
import com.springmvc.pojo.User;

public interface CityDao {
	City selectByPrimaryKey(String id);
	int insertSelective(City city);
	int insert(City city);
}
