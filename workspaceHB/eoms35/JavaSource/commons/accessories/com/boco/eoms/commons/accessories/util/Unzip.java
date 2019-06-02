package com.boco.eoms.commons.accessories.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Unzip {

	public void unzip(String zipFileName, String outputDirectory)
			  {
		try {
			org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(
					zipFileName);
			java.util.Enumeration e = zipFile.getEntries();
			org.apache.tools.zip.ZipEntry zipEntry = null;
			while (e.hasMoreElements()) {
				zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
				System.out.println("unziping " + zipEntry.getName());
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					System.out.println("输出路径：" + outputDirectory
							+ File.separator + name);
					File f1 = new File(outputDirectory + File.separator);
					f1.mkdir();
					File f = new File(outputDirectory + File.separator + name);
					f.mkdir();
					System.out.println("创建目录：" + outputDirectory
							+ File.separator + name);
				} else {
					File f = new File(outputDirectory + File.separator
							+ zipEntry.getName());
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);
					// --------解决了图片失真的情况
					int c;
					byte[] by = new byte[1024];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
			}
		} catch (Exception ex) {
		}
	}
	
	public static void main(String arg[]) {
		Unzip zip = new Unzip();
		String a = "TOMCAT性能说明.txt";
		String b = "E:/TOMCAT性能说明.zip";
		 
		zip.unzip(a, b);
		 
	}
}
