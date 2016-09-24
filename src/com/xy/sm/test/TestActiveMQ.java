package com.xy.sm.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestActiveMQ {
	
	@Autowired
	protected SqlSessionTemplate sessionTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void aa() throws IOException{
		
		Workbook wb = new XSSFWorkbook("C:\\Users\\xuyao\\Desktop\\abc.xlsx");
		Sheet sheet = wb.getSheetAt(0);
		Row row = null;
		Cell cell = null;
		int last = sheet.getLastRowNum();
		System.out.println(last);
		/*List<Object> maps = new ArrayList<Object>();
		while(maps.size() < 1000000){
			maps.addAll(jdbcTemplate.queryForList("select number from test"));
		}
		System.out.println(maps);*/
		/*Set<String> sets = new HashSet<String>(1333334);
		for(int i = 0; i < 1000000; i ++){
			sets.add(String.format("%011d", i));
			System.out.println(i);
		}
		System.out.println("end");*/
	}
	
	//@Test
	public void test(){
		
	}
}
