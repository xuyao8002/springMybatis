package com.xy.sm.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestBatchUpdate {
	
	@Autowired
	protected SqlSessionTemplate sessionTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static int length = 50000000;

	private static String content = "多写代码，才能进步";
	
	private static String rn = "\r\n";
	public static void main(String[] args) throws IOException {
		String filePath = "F:\\a.txt";
		//useFileChannelWrite(filePath);
		//useRandomAccessFileWrite(filePath);
		//useBufferedWriter(filePath);
		//useBufferedReader(filePath);
		//useScanner(filePath);
		//useLineIterator(filePath);
		//useRandomAccessFileRead(filePath);
		//useFileChannelRead(filePath);
	}

	//效率高，同bufferedRead
	public static void useLineIterator(String filePath){
		long start = System.currentTimeMillis();
		LineIterator it = null;
		try {
			 it = FileUtils.lineIterator(new File(filePath), "UTF-8");
			while(it.hasNext()){
				String line = it.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			LineIterator.closeQuietly(it);
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}

	//效率一般，不适合操作大文件
	public static void useScanner(String filePath){
		long start = System.currentTimeMillis();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream(filePath),"UTF-8");
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			scanner.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}

	

	//效率低，内存占用较稳定，可指定位置操作文件
	public static void useRandomAccessFileRead(String filePath){
		long start = System.currentTimeMillis();
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(filePath, "rw");
			String line = null;
			while((line = file.readLine())!= null){

			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(file != null){
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}

	//效率低
	public static void useRandomAccessFileWrite(String filePath){
		long start = System.currentTimeMillis();
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(filePath, "rw");
			byte[] bytes = content.getBytes();
			byte[] rns = rn.getBytes();
			for(int i = 0; i < length; i++){
				file.write(bytes);
				file.write(rns);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(file != null){
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}
	
	//效率高，跟bufferedRead差不多
	public static void useFileChannelRead(String filePath){
		long start = System.currentTimeMillis();
		FileChannel fc = null;
		ByteBuffer bytedata = ByteBuffer.allocate(1024);
		Charset cs = Charset.defaultCharset();
		try{
			fc = new FileInputStream(filePath).getChannel();
			while(fc.read(bytedata)!= -1){
				bytedata.flip();
				CharBuffer str = cs.decode(bytedata);
				bytedata.clear();
			}


		}catch(IOException e){

		}finally {
			if(fc != null){
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}

	//写文件慢了些
	public static void useFileChannelWrite(String filePath){
		long start = System.currentTimeMillis();
		FileChannel fc = null;
		try{
			fc = new FileOutputStream(filePath).getChannel();
			for(int i = 0; i < length; i++){
				fc.write(ByteBuffer.wrap(content.getBytes()));
				fc.write(ByteBuffer.wrap(rn.getBytes()));
			}
		}catch(IOException e){

		}finally {
			if(fc != null){
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}

	//效率高
	public static void useBufferedReader(String filePath){
		long start = System.currentTimeMillis();
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			while((line = br.readLine()) != null){
				//System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}

	//效率高
	public static void useBufferedWriter(String filePath){
		long start = System.currentTimeMillis();
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
			for(int i = 0; i < length; i++){
				bw.write(content);
				//bw.write("18321704496");
				bw.write(rn);
			}
		}catch(IOException e){

		}finally {
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("finish :"+(end-start)/1000+"秒");
	}

	//@Test
	public void aa() throws IOException{
		for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= i; j++){
				System.out.print(j);
			}
			System.out.println();
		}
	}

	public void mybatis(){
		long a=System.currentTimeMillis(); 
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>(100005);
		Map<String, Object> arg = new HashMap<String, Object>();
		for(int i = 0; i < 1000000; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", "18321704496");
			map.put("guid", UUID.randomUUID().toString());
			map.put("type", i % 2 == 0 ? 1 : 2);
			lists.add(map);
			if(lists.size() == 50000){
				arg.put("list", lists);
				sessionTemplate.insert("insertBatch", arg);
				//insertPhones(lists);
				lists.clear();
			}
		}
		if(!lists.isEmpty()){
			arg.put("list", lists);
			sessionTemplate.insert("insertBatch", arg);
			//insertPhones(lists);
		}
		long b=System.currentTimeMillis();  
        System.out.println("用时"+ (b-a)+" ms");  
	}
	
	public void jdbcTemplate(){
		long a=System.currentTimeMillis(); 
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>(100005);
		for(int i = 0; i < 1000000; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", "18321704496");
			map.put("guid", UUID.randomUUID().toString());
			map.put("type", i % 2 == 0 ? 1 : 2);
			lists.add(map);
			if(lists.size() == 100000){
				insertPhones(lists);
				lists.clear();
			}
		}
		if(!lists.isEmpty()){
			insertPhones(lists);
		}
		long b=System.currentTimeMillis();  
        System.out.println("用时"+ (b-a)+" ms");  
	}
	
	public void jdbcTemplatePhone(){
		long a=System.currentTimeMillis(); 
		List<String> lists = new ArrayList<String>(100005);
		for(int i = 0; i < 1000000; i++){
			lists.add(String.valueOf(18321204496L+i));
			if(lists.size() == 100000){
				insertTempPhone(lists);
				lists.clear();
			}
		}
		if(!lists.isEmpty()){
			insertTempPhone(lists);
		}
		long b=System.currentTimeMillis();  
        System.out.println("用时"+ (b-a)+" ms");  
	}
	
	public void jdbc(){
		String url="jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true";  
        String userName="root";  
        String password="";  
        Connection conn=null;  
        try {        
              Class.forName("com.mysql.jdbc.Driver");        
              conn =  DriverManager.getConnection(url, userName, password);        
              conn.setAutoCommit(false);        
              String sql = "insert into phones(phone,guid,type) values(?,?,?)";        
              PreparedStatement prest = conn.prepareStatement(sql);        
              long a=System.currentTimeMillis();  
              for(int x = 0; x < 3000000; x++){        
                 prest.setString(1, String.valueOf(18318704496L+x));        
                 prest.setString(2, UUID.randomUUID().toString());     
                 prest.setInt(3, x % 2 == 0 ? 1 : 2);
                 prest.addBatch();  
                 if(x > 0 && x % 100000 == 0){
                	 prest.executeBatch();
                     conn.commit();
                 }
                 
              }        
              prest.executeBatch();
              conn.commit();        
              conn.setAutoCommit(true);
              long b=System.currentTimeMillis();  
              System.out.println("用时"+ (b-a)+" ms");  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }finally{  
            try {  
                if(conn!=null)conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }     
        }  
	}
	
	public boolean insertPhones(List<Map<String, Object>> list){
        final List<Map<String, Object>> personList = list;
        //String sql = "insert into personInfo values(?,?,?,?)";
        String sql = "insert into phones(phone,guid,type) values(?,?,?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
            public void setValues(PreparedStatement ps,int i)throws SQLException
               {
            	String phone = personList.get(i).get("phone").toString();
            	String guid = personList.get(i).get("guid").toString();
            	int type = (int) personList.get(i).get("type");
            	ps.setString(1, phone);
            	ps.setString(2, guid);
            	ps.setInt(3, type);
               }
               public int getBatchSize()
               {
                return personList.size();
               }
        });
        return true;
    }
	
	public boolean insertTempPhone(List<String> list){
        final List<String> personList = list;
        //String sql = "insert into personInfo values(?,?,?,?)";
        String sql = "insert into tempPhone(phone) values(?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
            public void setValues(PreparedStatement ps,int i)throws SQLException
               {
            	String phone = personList.get(i);
            	ps.setString(1, phone);
               }
               public int getBatchSize()
               {
                return personList.size();
               }
        });
        return true;
    }
}
