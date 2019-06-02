package com.boco.eoms.commons.statistic.base.util;

import java.io.File;
import java.io.FileNotFoundException;

import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

public class StatManagerMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		deleteFileExcel();
		deleteFileXml();
	}
	
	public static boolean deleteFileExcel()
	{
		String classpath = Constants.CLASSPATH;
		String filePath = Constants.EXPORTEXCELFILEPATH;
		String postfix = ".xls";
		
		return deleteFile(classpath,filePath,postfix);
	}
	
	public static boolean deleteFileXml()
	{
		String classpath = Constants.CLASSPATH;
		String filePath = Constants.GRAPHICSFILEPATH;
		String postfix = ".xml";
		
		return deleteFile(classpath,filePath,postfix);
	}
	
	public static boolean deleteFile(String ABSfilePath)
	{
		boolean flg = false;
		try
		{
			File file = new File(ABSfilePath);
			file.delete();
			flg = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("删除文件失败 filePath :" + ABSfilePath);
		}
		
		return flg;
	}
	
	/**
	 * 删除postfix后缀名字的所有文件
	 * @param filePath
	 * @param postfix
	 * @return
	 */
	private static boolean deleteFile(String classpath,String filePath,String postfix)
	{
		boolean flag = true;
		
		String ABSURL = "";
		try {
			ABSURL = StaticMethod.getFilePathForUrl(classpath);
		} catch (FileNotFoundException e) {
			flag=false;
			e.printStackTrace();
		}
		ABSURL = ABSURL.substring(0, ABSURL.indexOf(Constants.WEB_INF)) + filePath;
		
		File file = new File(ABSURL);
		
		File[] files = file.listFiles();
		for(int i =0;i<files.length;i++)
		{
			String fname = files[i].getName();
			String filepostfix = fname.substring(fname.lastIndexOf("."));
			if(filepostfix.equalsIgnoreCase(postfix))
			{
				System.err.println(fname);
				files[i].delete();
			}
		}
		
		return flag;
	}

}
