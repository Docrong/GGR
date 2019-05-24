package com.boco.eoms.sequence.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * <p>
 * Title:辅助测试的助手类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 26, 2008 11:18:50 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class Helper {
	/**
	 * 输出msg
	 * 
	 * @param msg
	 *            打印的信息
	 */
	public void out(String msg) {
		System.out.println(msg);
	}

	/**
	 * 输出10000次
	 * 
	 * @param msg
	 */
	public void outtenthousand(String msg) {
		FileOutputStream outSTr;
		BufferedOutputStream buff;
		try {
			outSTr = new FileOutputStream(new File("C:/log.txt"), true);
			buff = new BufferedOutputStream(outSTr);
			buff.write(msg.getBytes());
			buff.write("\r\n".getBytes());
			buff.flush();
			outSTr.close();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	

}
