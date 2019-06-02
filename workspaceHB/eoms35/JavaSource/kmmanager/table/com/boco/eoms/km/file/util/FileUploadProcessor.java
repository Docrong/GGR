package com.boco.eoms.km.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.struts.upload.FormFile;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.workbench.netdisk.fileupload.UploadProcessor;

public class FileUploadProcessor {

	/**
	 * 处理文件保存到硬盘上
	 * @param path 保存路径
	 */
	public static void processUploadedFile(FormFile formFile, String path) {
	// 创建File对象
	File file = new File(path);
	String str = path.substring(0, path.lastIndexOf(File.separator));
	File fPath = new File(str);
	if(!fPath.exists())
		fPath.mkdirs();
	BocoLog.debug(UploadProcessor.class, "file path is:" + file.getPath());
	InputStream in;
	try {
		in = formFile.getInputStream();// 获得输入数据流文件
		// 将该数据流写入到指定文件中
		FileOutputStream out = new FileOutputStream(file);
		byte[] buffer = new byte[4096]; // To hold file contents
		int bytes_read;
		while ((bytes_read = in.read(buffer)) != -1) // Read until EOF
		{
			out.write(buffer, 0, bytes_read);
		}
		if (in != null)
			try {
				in.close();
			} catch (IOException e) {
				;
			}
		if (out != null)
			try {
				out.close();
			} catch (IOException e) {
				;
			}
	} catch (IOException e1) {
		e1.printStackTrace();
	}
}
}
