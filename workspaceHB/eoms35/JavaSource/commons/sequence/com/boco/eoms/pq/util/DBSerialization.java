package com.boco.eoms.pq.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;

/**
 * Java对象序列化到数据库
 * 
 * @author qiuzi
 * 
 */
public class DBSerialization {

	/**
	 * 将对象转化成java.sql.Blob 要求对象是序列化的
	 */
	public static Blob object2Blob(Object object) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(object);
			byte[] bytes = out.toByteArray();
			outputStream.close();
			return Hibernate.createBlob(bytes);
		} catch (IOException e) {
			System.out.println("IO Error while converting object to blob!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将java.sql.Blob 转化成对象相应对象，要求对象是序列化的
	 */
	public static Object blob2Object(Blob blob) {
		try {
			ObjectInputStream in = new ObjectInputStream(blob.getBinaryStream());
			Object obj = in.readObject();
			in.close();
			return obj;
		} catch (Exception e) {
			if (e instanceof IOException) {
				System.out.println("IO Error while converting blob to object!");
			} else if (e instanceof SQLException) {
				System.out
						.println("SQL Error while converting blob to object!");
			} else if (e instanceof ClassNotFoundException) {
				System.out
						.println("ClassNotFound Error while converting blob to object!");
			} else {
				System.out
						.println("Unknown Error while converting blob to object!");
				e.printStackTrace();
			}

			e.printStackTrace();
			return null;
		}
	}

}
