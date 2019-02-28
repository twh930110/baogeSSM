package com.springmvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.CityDao;
import com.springmvc.pojo.City;
import com.springmvc.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService{
	@Resource  
	private CityDao cityDao;
	
	@Override
	public City getCityById(String id) {
		return cityDao.selectByPrimaryKey(id);
	}

	@Override
	public int insertCity(City city) {
		return cityDao.insert(city);
	}

}
