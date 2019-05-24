package com.boco.eoms.commons.accessories.util;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import sun.io.*;

public class linzip
{
 public static String make8859toGB(String str)
 {
  try{
   String str8859 = new String(str.getBytes("8859_1"),"GB2312");
   return str8859;
  }catch(UnsupportedEncodingException ioe){
   return str;
  }
 }
 
 public static void main(String[] args)
 {
  /*if (args.length < 1){
   System.err.println("Required parameter missing!");
   System.exit(-1);
  }*/
  
  
  File infile = new File("E:/TOMCAT性能说明.zip");

  try{
   //检查是否是ZIP文件
   ZipFile zip = new ZipFile(infile);
   zip.close();
   
   //建立与目标文件的输入连接
   ZipInputStream in = new ZipInputStream(new FileInputStream(infile));
   ZipEntry file = in.getNextEntry();
   
     
   int i =infile.getAbsolutePath().lastIndexOf(".");
   String dirname = new String();
   if ( i != -1 )
    dirname = infile.getAbsolutePath().substring(0,i);
   else
    dirname = infile.getAbsolutePath();
   File newdir = new File(dirname);
   newdir.mkdir();
   
   byte[] c = new byte[1024];
   int len;
   int slen;
   
   while (file != null){
    
	 i = make8859toGB(file.getName()).replace("/","").lastIndexOf("");
    if ( i != -1 ){
     File dirs = new File(dirname+File.separator+make8859toGB(file.getName()).replace("/","").substring(0,i));
     dirs.mkdirs();
     dirs = null;
    }
    
    System.out.print("Extract "+make8859toGB(file.getName()).replace("/","")+" ........  ");
    
    if (file.isDirectory()){
     File dirs = new File(make8859toGB(file.getName()).replace("/",""));
     dirs.mkdir();
     dirs = null;
    }
    else{ 
     FileOutputStream out = new FileOutputStream(dirname+File.separator+make8859toGB(file.getName()).replace("/",""));
     while((slen = in.read(c,0,c.length)) != -1)
      out.write(c,0,slen);
     out.close();
    }
    System.out.print("O.K. ");
    file = in.getNextEntry();
   }
   in.close();
  }catch(ZipException zipe){
   MessageBox(0,infile.getName()+"不是一个ZIP文件！","文件格式错误",16);
  }catch(IOException ioe){
   MessageBox(0,"读取"+args[0]+"时错误！","文件读取错误",16);
  }catch(Exception i){
    System.out.println("over");
  }
 }

 /**
  * @dll.import("USER32", auto) 
  */
 public static native int MessageBox(int hWnd, String lpText, String lpCaption, int uType);
}

