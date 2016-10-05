package com.xy.sm.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.util.*;

/**
 * Created by xuyao on 2016/9/27.
 */
class MyComparator implements Comparator<String>{ 

    @Override
    public int compare(String o1, String o2) {
        return -Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
    }
}
public class Test {

    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(new File("G:\\9.30\\附件1：51国际通道报价单20160928.xls"));
        Workbook wb = new HSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);
        Row row = null;
        Cell cell = null;
        Map<String, Map<String, String>> code_price = new TreeMap<>();
        for(int i = 0;i <= 9; i++){
            code_price.put(String.valueOf(i), new TreeMap(new MyComparator()));
        }
        for(int i = 2; i <= 213; i++){
            row = sheet.getRow(i);
            String code = String.valueOf(row.getCell(3).getNumericCellValue());
            code = code.substring(0, code.indexOf("."));
            String price = String.valueOf(row.getCell(5).getNumericCellValue());
            code_price.get(code.substring(0,1)).put(code, price);
        }

        for(Map.Entry<String, Map<String, String>> mm : code_price.entrySet()){
            System.out.println(mm.getKey()+", "+mm.getValue());
        }
        /*String[] files = {"51-2016-06.csv","51-2016-07.csv","51-2016-08.csv"};
        String path = "G:\\9.30\\";
        for(String file : files){
            BufferedReader br = new BufferedReader(new FileReader(path+file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(path+file.replace("51","51_new")));
            String line = null;
            int i = 0;
            while((line = br.readLine()) != null){
                if(i == 0){
                    line += ",单价";
                }else{
                    String[] fields = line.split(",");
                    line += ","+getPrice(code_price.get(fields[2].substring(0,1)), fields[2]);
                }
                bw.write(line);
                bw.newLine();
                i++;
            }
            bw.flush();
        }*/
    }

    public static String getPrice(Map<String, String> price, String no){
        String p = "";
        if(!price.isEmpty()){
            for(Map.Entry<String, String> pr : price.entrySet()){
                if(no.startsWith(pr.getKey())) return pr.getValue();
            }
        }
        return p;
    }


    public void test(){
        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "xuyao");
        map.put("sex", "boy");
        map.put("age", 18);
        Map<String, Object> map1 = new HashMap<String, Object>();
        BeanUtils.copyProperties(map, map1);
        System.out.println("map" + map);
        System.out.println("map1" + map1);*/
        A a = new A();
        a.setAge(18);
        a.setHeight(170);
        a.setName("ye");
        A a1 = new A();
        a1.setAge(28);
        a1.setHeight(180);
        a1.setName("nai");
        System.out.println(a);
        System.out.println(a1);
        //swapA(a, a1);
        A temp = a;
        a = a1;
        a1 = temp;
        System.out.println(a);
        System.out.println(a1);
        //B b = new B();
    }
}
class A {
    private int age;
    private String name;
    private int height;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "A{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}

class B{
    private int age;
    private String name;
    private String width;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "B{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", width='" + width + '\'' +
                '}';
    }
}