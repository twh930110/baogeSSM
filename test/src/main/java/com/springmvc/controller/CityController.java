package com.springmvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.springmvc.pojo.User;
import com.springmvc.service.CityService;

import redis.clients.jedis.Jedis;

@Controller  
@RequestMapping("/city") 
public class CityController {
	@Resource  
    private CityService cityService;  
	
	@RequestMapping("/showCity")  
    public String toIndex(HttpServletRequest request,Model model){  
        return "showCity";
    }  
	
	//查询天气方法
	@RequestMapping("/getCity") 
	public void getWeatherInform(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObj=null;
		String city=request.getParameter("city")==null?"":request.getParameter("city").toString();
		//先去缓存里面查看是否有数据，如果有，一分钟内的数据从缓存里面查询
		Jedis jedis=new Jedis("localhost",6379);
		//先去缓存中获取数据
		String str=jedis.get(city)==null?"":jedis.get(city);
		//为空则说明缓存中无数据
		if("".equals(str)) {
			//从网站获取数据
			jsonObj = getWeather(city);
			str = jsonObj.toString();
			//保存进缓存中
			jedis.setex(city, 60, str); //Redis特殊set延时方法.60秒Key值有效.60秒后自动失效
		}
		//返回前台页面
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(str);
	}
	
	//通过网站获取天气信息
	public JSONObject getWeather(String city) {
		// 天气API 网址
		String weatherurl = null;
		try {
			weatherurl = "http://api.help.bj.cn/apis/weather2d/?id="
					+ URLEncoder.encode(city, "utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		StringBuffer strBuf;

		strBuf = new StringBuffer();

		try {
			URL url = new URL(weatherurl);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));// 转码。
			String line = null;
			while ((line = reader.readLine()) != null)
				strBuf.append(line + "");
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.parseObject(strBuf.toString(), JSONObject.class);
	}
}
