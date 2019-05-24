package com.boco.eoms.version;

import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;

public class VersionLog {
	

	public static void log(String msg) {
		String path=((EOMSAttributes)ApplicationContextHolder.getInstance().getBean("eomsAttributes")).getVersionPath();

		try {
			java.util.Date date = new java.util.Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String d = dateFormat.format(date);
			// 将异常输出到指定的文件下面
			RandomAccessFile raf = new RandomAccessFile(path, "rw");
			raf.seek(raf.length());
			String message = "time is " + d + ":::" + msg + "\n";
			System.out.print(message);
			raf.write(message.getBytes("GBK"));
			raf.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		VersionLog.log("--------------你好---------------");
	}
}
