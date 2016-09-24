package com.xy.sm.controller;

import com.xy.sm.entity.TestInfo;
import com.xy.sm.service.TestInfoService;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("/testInfo")
public class TestInfoController extends BaseController{

	private static Logger logger = Logger.getLogger(TestInfoController.class);
	
	@Autowired
	private TestInfoService testInfoServiceImpl;
	
	@Override
	protected String getViewPath() {
		return "testInfo/";
	}

	@RequestMapping(value="/uploadFile")
	public String uploadFile(@RequestParam(value = "file") MultipartFile file,Model model) throws Exception{
		ReadableByteChannel inChannel = Channels.newChannel(file.getInputStream());
		ByteBuffer buffer = ByteBuffer.allocate(8192);
		int read;
		StringBuilder sb = new StringBuilder();
		  while ((read=inChannel.read(buffer)) > 0) {
			  buffer.flip();
			  Charset  cs = Charset.defaultCharset();
		    sb.append(cs.decode(buffer));
		    buffer.clear();
		  }
		  System.out.println("rn"+System.getProperty("line.separator").equals("\r\n"));
		  System.out.println("n"+System.getProperty("line.separator").equals("\n"));
		  for(String str : sb.toString().split("\n")){
			  System.out.println(str.contains("\r"));
		  }
		  
		//TestInfo testInfo = new TestInfo();
		/*CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
		logger.info(file.getOriginalFilename());
		logger.info(file.getContentType());
		logger.info(file.getInputStream());
		//readZipFile(file.getInputStream(), f);
		String relativelyPath = System.getProperty("user.dir"); 
		File f1 = new File(relativelyPath+File.separator+System.currentTimeMillis()+".zip");
		file.transferTo(f1);
		ZipFile zf = new ZipFile(f1);
        ZipInputStream zin = new ZipInputStream(new FileInputStream(f1));  
		//ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\Users\\xuyao\\Desktop\\锟铰斤拷锟侥硷拷锟斤拷.rar"));  
        ZipEntry ze;  
        while ((ze = zin.getNextEntry()) != null) {  
            if (!ze.isDirectory() && ze.getName().endsWith(".txt")) { 
                System.out.println(ze.getName());
                BufferedReader bf = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
                String str = null;
                while((str = bf.readLine()) != null){
                	System.out.println(str);
                }
                bf.close();
            }  
        }  
        zin.closeEntry();  
        zin.close();
        zf.close();
		f1.delete();*/
		
		return "redirect:findInfoList";
	}
	
	public static void readZipFile(InputStream is, File file) throws Exception {  
        //InputStream in = new BufferedInputStream(is);  
		ZipFile zf = new ZipFile(file);
        ZipInputStream zin = new ZipInputStream(is);  
		//ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\Users\\xuyao\\Desktop\\锟铰斤拷锟侥硷拷锟斤拷.rar"));  
        ZipEntry ze;  
        while ((ze = zin.getNextEntry()) != null) {  
            if (!ze.isDirectory() && ze.getName().endsWith(".txt")) { 
                System.out.println(ze.getName());
                BufferedReader bf = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
                String str = null;
                while((str = bf.readLine()) != null){
                	System.out.println(str);
                }
                
            }  
        }  
        zin.closeEntry();  
    }
	
	/*public static void readZipFile(String file) throws Exception {  
        ZipFile zf = new ZipFile(file);  
        InputStream in = new BufferedInputStream(new FileInputStream(file));  
        ZipInputStream zin = new ZipInputStream(in);  
        ZipEntry ze;  
        while ((ze = zin.getNextEntry()) != null) {  
            if (ze.isDirectory()) {
            } else {  
                System.err.println("file - " + ze.getName() + " : "  
                        + ze.getSize() + " bytes");  
                long size = ze.getSize();  
                if (size > 0) {  
                    BufferedReader br = new BufferedReader(  
                            new InputStreamReader(zf.getInputStream(ze)));  
                    String line;  
                    while ((line = br.readLine()) != null) {  
                        System.out.println(line);  
                    }  
                    br.close();  
                }  
                System.out.println();  
            }  
        }  
        zin.closeEntry();  
    }*/

	@RequestMapping("downloadFile")
	public ResponseEntity<byte[]> downloadFile(@RequestParam("id") String id) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum++);
		Cell cell = row.createCell(cellNum++);
		cell.setCellValue("濮撳悕");
		cell = row.createCell(cellNum);
		cell.setCellValue("鐢佃瘽");
		wb.write(bo);
		byte[] bytes = bo.toByteArray();
		bo.close();

		//ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		HttpHeaders headers = new HttpHeaders();
		String fileName=new String("浣犲ソ.xls".getBytes("UTF-8"),"iso-8859-1");
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(bytes,
				headers, HttpStatus.CREATED);
	}

	@RequestMapping("/findInfoList")
	public String findInfoList(Model model){
		//logger.error("进入findInfoList方法");
		
		TestInfo one = new TestInfo();
		one.setId(1);
		TestInfo testInfo = testInfoServiceImpl.findOne(one);
		model.addAttribute("testInfo", testInfo);
		
		TestInfo list = new TestInfo();
		//list.setHobby("锟斤拷戏");
		//list.setAge(18);
		//list.setGender("锟斤拷");
		List<TestInfo> testInfos = testInfoServiceImpl.findPageByCondition(list);
		model.addAttribute("testInfos", testInfos);
		return getViewPath()+"listInfo";
	}
	
	@RequestMapping(value="/editInfo/{id}",method=RequestMethod.GET)
	public String editInfo(@PathVariable Integer id, Model model){
		//int id = Integer.parseInt(request.getParameter("id"));
		TestInfo info = new TestInfo();
		info.setId(id);
		TestInfo testInfo = testInfoServiceImpl.findOne(info);
		if(testInfo == null) testInfo = new TestInfo();
		model.addAttribute("testInfo", testInfo);
		return getViewPath()+"editInfo";
	}
	
	@RequestMapping(value="/addInfo",method=RequestMethod.GET)
	public String addInfo(Model model){
		TestInfo testInfo = new TestInfo();
		model.addAttribute("testInfo", testInfo);
		return getViewPath()+"editInfo";
	}
	
	@RequestMapping(value="/saveInfo",method=RequestMethod.POST)
	public String saveInfo(TestInfo testInfo, Model model){
		TestInfo temp = this.testInfoServiceImpl.saveOrUpdate(testInfo);
		System.out.println("锟斤拷锟斤拷temp:"+temp);
		return findInfoList(model);
	}
	
	@RequestMapping(value="/deleteInfo/{id}",method=RequestMethod.GET)
	public String deleteInfo(@PathVariable Integer id, Model model){
		//int id = Integer.parseInt(request.getParameter("id"));
		TestInfo delete = new TestInfo();
		delete.setId(id);
		this.testInfoServiceImpl.deleteOne(delete);
		
		return "redirect:/testInfo/findInfoList";
	}
	
	@RequestMapping("/findTest")
	public ModelAndView findTest(){
		TestInfo testInfo = new TestInfo();
		testInfo.setId(1);
		List<TestInfo> testInfos = testInfoServiceImpl.findListByCondition(testInfo);
		//model.addAttribute("testInfos", testInfos);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(getViewPath()+"listInfo");
		mv.addObject("testInfos", testInfos);
		//mv.addObject(testInfos); 锟斤拷指锟斤拷锟斤拷锟斤拷时锟斤拷锟捷讹拷锟斤拷锟斤拷锟斤拷锟皆讹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷TestInfo锟斤拷锟较ｏ拷锟斤拷锟缴碉拷锟斤拷锟斤拷锟斤拷testInfoList锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷TestInfo锟斤拷锟斤拷
		//锟斤拷锟缴碉拷锟斤拷锟斤拷锟斤拷testInfo
		return mv;
	}



	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "寰愯�";
		str = new String(str.getBytes("UTF-8"),"ISO-8859-1");
		System.out.println(str);
		str = new String(str.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(str);
	}


}
