package com.boco.eoms.sheet.interfaceInfo.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IODate {
     /** 
      * 读取数据 
      */  
     public static void ReadDate() {  
        String url = "e:/2.txt";  
        try {  
             FileReader read = new FileReader(new File(url));  
             StringBuffer sb = new StringBuffer();  
             char ch[] = new char[1024];  
             int d = read.read(ch);  
             while(d!=-1){  
                 String str = new String(ch,0,d);  
                sb.append(str);  
                d = read.read(ch);  
              }  
              System.out.print(sb.toString());  
         } catch (FileNotFoundException e) {  
              e.printStackTrace();  
         } catch (IOException e) {  
            e.printStackTrace();  
         }  
     }  
       
     /** 
      * 写入数据 
      */  
     public static void WriteDate(String sheetType,String serviceType,String serialNo,String opDetail) {  
           
            try{  
            	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            	String ad = "/opt/crmfile/" + df.format(new Date()) + ".txt";
                File file = new File(ad);
                if (file.exists()) {  
                    file.delete();  
                }  
                file.createNewFile();  
                BufferedWriter output = new BufferedWriter(new FileWriter(file));   
                String interfaceinfo = new String();
                interfaceinfo ="sheetType=" + sheetType + "\r\n" + "serviceType=" + serviceType + "\r\n" + "serialNo=" + serialNo + "\r\n" + "opDetail=" + opDetail;           
                output.write(interfaceinfo);  
                output.close();  
            } catch (Exception ex) {  
                System.out.println(ex);  
            }  
     }  
}
