package com.boco.eoms.commons.accessories.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.util.CompressBook;
public class ZipDownload {
   
	// 将传进来的文件剪切到制定目录下
	public String ZipUtil(String[] inputFileName,String filePath) throws Exception {
		List list = new ArrayList();
		TawCommonsAccessories tawCommonsAccessories = null;
		Date date = new Date();  
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		String result = f.format(date);
		String path = filePath + result + "ZIP";
		File dirName = new File(path);
		if (!dirName.exists()) {
			dirName.mkdir();// 新建一个文件夹
		}
		
		
		
		//TawCommonsAccessories tawCommonsAccessories= null;
		//List list = new ArrayList();
		for (int i = 0; i < inputFileName.length; i++) {
			int beginindex = inputFileName[i].lastIndexOf("/");
			String filename = inputFileName[i].substring(beginindex+1);
			list =AccessoriesMgrLocator.getTawCommonsAccessoriesManagerCOS().getAllFileById("'"+filename+"'");
			tawCommonsAccessories= (TawCommonsAccessories)list.get(0);
			
			String cnName = tawCommonsAccessories.getAccessoriesCnName();
			String fileStr = cnName.substring(0,cnName.lastIndexOf("."));
			String newpath = filePath+fileStr+filename;
			File linshi = new File(filePath);
			if (!linshi.exists()) { 
				linshi.mkdir();
			}
			FileInputStream fis = new FileInputStream(inputFileName[i]);
			FileOutputStream fos = new FileOutputStream(newpath);
			byte[] buff = new byte[1024];
			int readed = -1;
			while((readed = fis.read(buff)) > 0)
			  fos.write(buff, 0, readed);
			  fis.close();
			  fos.close();

			  File subfile = new File(newpath);
			  int index = newpath.lastIndexOf("/");
			  String newfilename = newpath.substring(index);
			 
			  File newfile = new File(path +  newfilename);
			  //File newfile = new File(path +  new String(newfilename.getBytes("UTF-8"), "GBK"));
			  subfile.renameTo(newfile);
		}
		CompressBook book = new CompressBook();
		// 循环剪切
		String zipfile = path + ".zip";// 给指定生成ZIP文件
		book.zip(zipfile,new File(path));  
		return zipfile;
	}

	// 生成压缩后的ZIP文件输出流，并调用压缩
	public void zip(String inputFileName, String outputFileName)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				outputFileName));
		zip(out, new File(inputFileName), "");
		System.out.println("zip done");
		out.close();
	}

	// 递归压缩目录里面的所有文件
	private void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (System.getProperty("os.name").startsWith("Windows")) {
				out
						.putNextEntry(new org.apache.tools.zip.ZipEntry(base
								+ "\\"));
				base = base.length() == 0 ? "" : base + "\\";
			} else {
				out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
				base = base.length() == 0 ? "" : base + "/";
			}
			
			
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			base = new String(base.getBytes("UTF-8"), "GBK");
			
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

}
