package com.boco.eoms.commons.accessories.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.boco.eoms.commons.accessories.zip.ZipEntry;
import com.boco.eoms.commons.accessories.zip.ZipInputStream;
/**
 * @author lianjzh
 *
 */
public class unziptest {

 static final int BUFFER = 2048;
    public static void main(String argv[]) {
        try {
            unziptest uz=new unziptest();
            String zippath = "d:\\duty\\";// /解压到的目标文件路径
            String zipDir = "d:\\duty.zip";// 要解压的压缩文件的存放路径
         
          File file = new File(zipDir);
          
  			if (!file.exists()) {
  			file.mkdir();// 新建一个文件夹
  			}
          String realname = file.getName();
          System.out.println(realname);
          
          System.out.println("要解压缩的文件名.........."+zipDir);
          System.out.println("解压到的目录" +zippath);
          uz.ReadZip(zippath,zipDir);
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public void ReadZip(String zippath, String unzipPath) throws Exception {
     try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new
                                  FileInputStream(unzipPath);//"d:\\abc.zip"
            ZipInputStream zis = new
                                 ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Extracting: " + entry);
                int count;
                byte data[] = new byte[BUFFER];
                
                if (entry.isDirectory())// 如果为目录条目，则返回 true，执行下列语句
             {
              System.out.println("Dir: " + entry.getName() + " skipped..");
              continue;
             }
                
                int begin = unzipPath.lastIndexOf("\\") + 1;
             int end = unzipPath.lastIndexOf(".");
             String zipRealName = unzipPath.substring(begin, end);              
             dest = new BufferedOutputStream(
                new FileOutputStream(getRealFileName(zippath + "\\"
                  + zipRealName, entry.getName())));
        
                while ((count = zis.read(data, 0, BUFFER))
                                != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    /**
   * 给定根目录，返回一个相对路径所对应的实际文件名.
   * 
   * @param zippath
   *            指定根目录
   * @param absFileName
   *            相对路径名，来自于ZipEntry中的name
   * @return java.io.File 实际的文件
   */
  private  File getRealFileName(String zippath, String absFileName) {

   String[] dirs = absFileName.split("/", absFileName.length());

   File ret = new File(zippath);// 创建文件对象

   if (dirs.length > 1) {
    for (int i = 0; i < dirs.length - 1; i++) {
     ret = new File(ret, dirs[i]);

    }
   }

   if (!ret.exists()) {// 检测文件是否存在
    ret.mkdirs();// 创建此抽象路径名指定的目录
   }
   ret = new File(ret, dirs[dirs.length - 1]);// 根据 ret 抽象路径名和 child
              // 路径名字符串创建一个新 File 实例

   return ret;
  }

}

