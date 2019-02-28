package org.springmvc.testmybatis;  
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;  
import org.apache.log4j.Logger;  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import com.alibaba.fastjson.JSON;
import com.springmvc.pojo.City;
import com.springmvc.pojo.User;
import com.springmvc.service.CityService;
  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);
    @Resource  
    private CityService cityService = null;  

    
  //  @Test  
    public void test1() {  
        City city = cityService.getCityById("0");  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(city));  
    }  
    
    
    @Test
    public void test2() {
        List list=new ArrayList();
    	try {
    		String s = new String();
    		File f = new File("E:\\CityCode.txt");
    		InputStreamReader isr = new InputStreamReader(new FileInputStream(f), "GBK");
    		BufferedReader reader = new BufferedReader(isr);
    		while ((s = reader.readLine()) != null) { // 判断是否读到了最后一行
    			list.add(s);
    		}
    		reader.close();
    		} catch (Exception e) {
    		e.printStackTrace();
    	}
    	for(int i=0;i<list.size();i++){
        	String value=(String) list.get(i);
        	String id=value.split(",")[0];
        	String num=value.split(",")[1];
        	String name=value.split(",")[2];
        	String district=value.split(",")[3];
        	String city=value.split(",")[4];
        	String province=value.split(",")[5];
        	City city1 = new City();
        	city1.setId(id);
        	city1.setNum(num);
        	city1.setName(name);
        	city1.setDistrict(district);
        	city1.setCity(city);
        	city1.setProvince(province);
        	int count = cityService.insertCity(city1);
        	if(count>0) {
        		System.out.println("插入数据成功");
        	}
    	}
    }
} 
