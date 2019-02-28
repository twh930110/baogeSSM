package com.springmvc.service;

import com.springmvc.pojo.City;

public interface CityService {
	public City getCityById(String id);
	public int insertCity(City city);
}
