package com.boco.eoms.commons.accessories.util;

import java.io.*;
import java.util.*;
import java.io.File;
import org.apache.tools.zip.*;

public class CDGGzip {
	// boolean packFrame = false;
	private File srcPath = new File("E:/TOMCAT性能说明.txt");

	private String outFilename = new String("E:" + File.separator + "邢晓宁.zip");

	private int  len = srcPath.listFiles().length;

	private String[] filenames = new String[len];

	public void setSrcPath(String src) {
		srcPath = new File(src);
	}

	public File getSrcPath() {
		return srcPath;
	}

	public void setOutFilename(String out) {
		outFilename = out;
	}

	public String getOutFilename() {
		return outFilename;
	}

	public void gzip() {
		byte[] buf = new byte[1024];
		try {
			File[] files = srcPath.listFiles();
			for (int i = 0; i < len; i++) {
				// if(!files[i].isDirectory())
				filenames[i] = srcPath.getPath() + File.separator
						+ files[i].getName();
			}
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					outFilename));
			for (int i = 0; i < filenames.length; i++) {
				FileInputStream in = new FileInputStream(filenames[i]);
				out.putNextEntry(new org.apache.tools.zip.ZipEntry(files[i]
						.getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				out.closeEntry();
				in.close();
			}

			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String arg[]) {
		CDGGzip cdggzip = new CDGGzip();
		cdggzip.gzip();
	}

}
