package com.boco.eoms.message.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	public static String encode(String file)
	{
		String base64Str = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] b=new byte[fis.available()];
			fis.read(b);
			fis.close();
			base64Str = new BASE64Encoder().encode(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64Str;
	}
	public static void decoder(String base64Str, String file)
	{
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(base64Str);
			FileOutputStream out = new FileOutputStream(file);
			out.write(b);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
