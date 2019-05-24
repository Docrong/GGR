package com.boco.eoms.sheet.base.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;

public class SheetStaticMethod extends StaticMethod{
		
	/**
	 * 时间类型转换
	 * @param dateTime
	 * @return
	 */
	public static Timestamp tranferDateToDate(Date dateTime) {
		java.sql.Timestamp date=null;
		if(dateTime!=null){
		  long dateTimeMillion=dateTime.getTime();
		   date=new java.sql.Timestamp(dateTimeMillion);
		}
		return date;
	}

	/**
	 * String 转换为 Date类型  格式为 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	  public static Date StringToDate( String date ){
	      SimpleDateFormat dateformat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	      Date todate = null;
	      
	      try {
	        todate = dateformat.parse(date);
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	      return todate;
	  }

	    /**
	     * 根据节点名，获得短信服务信息文件中服务信息数据值。
	     * @param filePath 短信服务信息文件路径
	     * @param nodeName  节点名，应该是全路径 
	     * @return
	     */
		public static String getNodeName(String filePath,String nodeName) {
			String info = "";
			try {
				XMLPropertyFile properties=XMLPropertyFile.getInstance(filePath);				
				info = StaticMethod.nullObject2String(properties.getProperty(nodeName));
			} catch (Exception e) {
				
			} 
		      return info;
			
		}
		
		  /**
		   * 得到url指定页面超文本文件的源代码(html)
		   * @param url：指定页面地址
		   * @return
		   * @throws MalformedURLException, IOException
		   */
	    public static StringBuffer getPage(String url)
	    throws MalformedURLException, IOException {    	
	        StringBuffer sb = new StringBuffer("");
	        URL remoteUrl = new URL(url);
	        BufferedReader sread = new BufferedReader(new InputStreamReader(remoteUrl.openStream(),"UTF-8"));
	        String tmpStr = "";
	        
	        while ((tmpStr = sread.readLine()) != null) {
	            sb.append(tmpStr);
	            System.out.println(tmpStr);
	            }
	        return sb;
	        }
		  /**
		   * 在path下生成一个url指定页面超文本文件,文件名为fileName
		   * @param path：生成文件的目录
		   * @param url：指定页面地址
		   * @param fileName：生成的文件名
		   * @return
		   * @throws HibernateException
		   */
		public static void UrlToHtml(String url,String path,String fileName) {
			// TODO Auto-generated method stub
		    DataOutputStream output = null;
		    StringBuffer str = null;
		    File file = null;
			try{
			    StringBuffer out = getPage(url);
			    file = new File(path);
			    if (!file.exists()) {
			      file.mkdirs();
			    }	
			    System.out.println(fileName);
			    file = new File(path + fileName);

			    output = new DataOutputStream(new FileOutputStream(file)); //按指定路径生成统计html文件
			    output.writeBytes(new String(out.toString().getBytes("UTF-8"), "ISO8859-1")); //进行编码处理
	            output.close(); //关闭输出流
			    
			}catch(Exception e){			
				e.printStackTrace();
			}
		    finally {
		        str = null;
		        output = null;
		        file = null;
		      }
		}
		
		public static String getDBDateToString(String dateFormatStr){
			String dbType = XmlManage.getBoco().getProperty("database.type");
			if(dbType.equalsIgnoreCase("oracle"))
				return "to_date('"+dateFormatStr+"',   'yyyy-mm-dd hh24:mi:ss')";
			else
				return "'" + dateFormatStr + "'";
				
		}
		 /**  
	     * 字符串按字节截取  
	     * @param str 原字符  
	     * @param len 截取长度  
	     * @param elide 省略符  
	     * @return String  
	     * @author   
	     * @since  
	     */  
	     public static String splitString(String str,int len,String elide) {   
	            if (str == null) {   
	                   return "";   
	            }   
	            byte[] strByte = str.getBytes();   
	            int strLen = strByte.length;   
	            int elideLen = (elide.trim().length() == 0) ? 0 : elide.getBytes().length;   
	            if (len >= strLen || len < 1) {   
	                   return str;   
	            }   
	            if (len - elideLen > 0) {   
	                   len = len - elideLen;   
	            }   
	            int count = 0;   
	            for (int i = 0; i < len; i++) {   
	                   int value = (int) strByte[i];   
	                   if (value < 0) {   
	                          count++;   
	                   }   
	            }   
	            if (count % 2 != 0) {   
	                   len = (len == 1) ? len + 1 : len - 1;   
	            }   
	            return new String(strByte, 0, len) + elide.trim();   
	     } 
}
