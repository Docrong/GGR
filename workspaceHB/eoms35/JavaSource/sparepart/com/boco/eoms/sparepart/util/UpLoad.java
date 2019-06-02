package com.boco.eoms.sparepart.util;

import org.apache.struts.upload.*;
import java.io.*;

public class UpLoad{
    public UpLoad(){
    }

    public static String UpLoadFile(FormFile file,String filePath){
      try{
            InputStream stream=file.getInputStream(); //把文件读入

            OutputStream bos=new FileOutputStream(filePath+"/"+

            file.getFileName()); //建立一个上传文件的输出流

            int bytesRead=0;
            byte[] buffer=new byte[8192];
            while((bytesRead=stream.read(buffer,0,8192))!=-1){

            bos.write(buffer,0,bytesRead); //将文件写入服务器

            }
            bos.close();
            stream.close();
        }
        catch(Exception e){
            e.printStackTrace();
            return "UPLOADFILED";
        }
        return "UPLOADOK";
    }
}