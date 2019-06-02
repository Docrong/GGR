package com.boco.eoms.message.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.internet.MimeUtility;

import org.springframework.mail.SimpleMailMessage;
public class CommonUtil {
	
	//private Message msg;
	private SimpleMailMessage msg=new SimpleMailMessage();
    //将页面传过来的值映射成EmailInfo的属性
	
	

	//中文乱码问题
	public String showChinese(String sss) throws UnsupportedEncodingException{	
	
		    try{ 		      
		      if(sss.startsWith("=?GB")||sss.startsWith("=?gb")){ 
		    	  sss=MimeUtility.decodeText(sss); 
		    }else{ 		
		    	  sss=CommonUtil.toChinese(sss); 
		      } 
		    }catch(Exception e){ 
		      e.printStackTrace(); 
		    } 
		   // from=this.replaceStr(from,"<",">");// replaceStr为字符串替换函数 
		   // from=StringUtil.replaceStr(from,">",">");	    
		    return sss; 
		  

	}
	public static String toChinese(String strvalue){ 
	    try{ 
	      if(strvalue==null) 
	        return null; 
	      else{ 
	    	  //strvalue=MimeUtility.decodeText(strvalue);
	        strvalue = new String(strvalue.getBytes("UTF-8"), "UTF-8"); 
	        return strvalue; 
	} 
	    }catch(Exception e){ 
	      return null; 
	    } 
	  }

	
	//以逗号为线分离字符串为数组
	public String[] splitString(String arg){
		String[] con=arg.split(",");		
		return con;
	}
	//以@号为界分离字符串为汉字
	public String[] splitSender(String arg){
		String[] con=arg.split("@");
		return con;
	}
	//分离邮件名
	public String splitFileName(String arg){
		int k=arg.lastIndexOf("\\");
		arg=arg.substring(k+1, arg.length());
		return arg;
	}	
	//分离地址，方便带署名的邮件地址不出现乱码。
	public String[] splitAddress(String str){
		String[] arg=new String[2];
		arg[0]="";
		arg[1]=str;	
		return arg;
	}
	
	//将地址数组元素加上逗号组成字符串。
	public String getStringFromArray(Object[] address) throws UnsupportedEncodingException{
		if(address!=null&&address.length>0){
		String khj="";
		for(int i=0;i<address.length-1;i++){			
			String sss=address[i].toString();
			sss=showChinese(sss);
			khj+=(sss+",");
		}
		String only=address[address.length-1].toString();
		khj=showChinese(khj)+ showChinese(only);	
		return khj;}
		else return null;
	}
	
	public Date getDate()   {   
        SimpleDateFormat lFormat;   
        Date date  =  null;   
        Calendar MyDate = Calendar.getInstance();   
        //MyDate.setTime(new  java.util.Date());   
        date = MyDate.getTime();   
        //格式可以自己根据需要修改   
        lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        String gRtnStr = lFormat.format(date);   
        return date;   
        }   
	
	/**
	 * 将xml转成字符串
	 * @param sourceXML
	 * @return
	 */
	public static String getXMLString(File file) {
		
		try {
			FileInputStream fin = new FileInputStream(file);
			byte[] b = new byte[fin.available()];
			fin.read(b);
			fin.close();

			String strXML = new String(b);
			return strXML;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
