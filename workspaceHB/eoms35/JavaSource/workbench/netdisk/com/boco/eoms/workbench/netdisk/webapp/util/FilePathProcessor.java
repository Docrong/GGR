package com.boco.eoms.workbench.netdisk.webapp.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class FilePathProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "ajax+jsp文件上传进度条.rar";
		System.out.println(processPath(path));
		System.out.println(recoverPath(processPath(path)));
	}

	/**
	 * 对路径两次转码,避免传输过程中被自动处理
	 * 
	 * @param path
	 * @return
	 */
	public static String processPath(String path) {
		String encodepath = path;
		try {
			encodepath = URLEncoder.encode(path, "utf-8");
			encodepath = URLEncoder.encode(encodepath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			encodepath = path;
		}
		return encodepath;
	}

	/**
	 * 恢复转码后的路径
	 * 
	 * @param path
	 * @return
	 */
	public static String recoverPath(String path) {
		String decodepath = path;
		try {
			decodepath = URLDecoder.decode(path, "utf-8");
			decodepath = decodepath.replaceAll("[+]", "%2b");
			decodepath = URLDecoder.decode(decodepath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			decodepath = path;
		}
		return decodepath;
	}

	/**
	 * 截取用户Id以后的相对路径
	 * 
	 * @param userId
	 * @param path
	 * @return
	 */
	public static String getRelativePath(String userId, String path) {
		int index = path.indexOf(userId);
		return path.substring(index + userId.length(), path.length());
	}

	/**
	 * 将getRelativePath()方法处理过的相对路径恢复为绝对路径
	 * 
	 * @param userId
	 * @param path
	 * @return
	 */
	public static String getAbsolulePath(String userId, String path) {
		String absolutePath = NetDiskAttriubuteLocator.getNetDiskAttributes()
				.getNetDiskRootPath()
				+ userId + File.separator + path;
		return absolutePath;
	}
}
