package com.boco.eoms.interfaces.province;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import sun.net.TelnetOutputStream;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

public class Download {
	String localfilename;

	String remotefilename;

	FtpClient ftpClient;

	// server：服务器名字
	// user：用户名
	// password：密码
	// path：服务器上的路径
	public void connectServer(String ip, int port, String user,
			String password, String path) {

		try {
			ftpClient = new FtpClient();
			ftpClient.openServer(ip, port);
			ftpClient.login(user, password);
			System.out.println("login success!");
			if (path.length() != 0)
				ftpClient.cd(path);
			ftpClient.binary();
		} catch (IOException ex) {
			System.out.println("not login");
			System.out.println(ex);
		}
	}

	public void closeConnect() {
		try {
			ftpClient.closeServer();
			System.out.println("disconnect success");
		} catch (IOException ex) {
			System.out.println("not disconnect");
			System.out.println(ex);
		}
	}

	public void upload(String localfilename, String remotefilename) {

		// this.localfilename = "D://test2//test.txt";
		// this.remotefilename = "test.txt";
		this.localfilename = localfilename;
		this.remotefilename = remotefilename;
		try {
			TelnetOutputStream os = ftpClient.put(this.remotefilename);
			java.io.File file_in = new java.io.File(this.localfilename);
			FileInputStream is = new FileInputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("upload success");
			is.close();
			os.close();
		} catch (IOException ex) {
			System.out.println("not upload");
			System.out.println(ex);
		}
	}

	public void download() {

		try {
			TelnetInputStream is = ftpClient.get(this.remotefilename);
			java.io.File file_in = new java.io.File(this.localfilename);
			FileOutputStream os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				// System.out.println((char)is.read());
				// System.out.println(file_in);
				os.write(bytes, 0, c);
			}

			System.out.println("download success");
			os.close();
			is.close();
		} catch (IOException ex) {
			System.out.println("not download");
			System.out.println(ex);
		}
	}

	public void download(String remotePath, String remoteFile, String localFile) {

		try {
			if (remotePath.length() != 0)
				ftpClient.cd(remotePath);
			TelnetInputStream is = ftpClient.get(remoteFile);
			java.io.File file_in = new java.io.File(localFile);
			FileOutputStream os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				// System.out.println((char)is.read());
				// System.out.println(file_in);
				os.write(bytes, 0, c);
			}

			System.out.println("download success");
			os.close();
			is.close();
		} catch (IOException ex) {
			System.out.println("not download");
			System.out.println(ex);
		}
	}

	public void download(String remoteFile, String localFile) {

		try {
			TelnetInputStream is = ftpClient.get(remoteFile);
			java.io.File file_in = new java.io.File(localFile);
			FileOutputStream os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				// System.out.println((char)is.read());
				// System.out.println(file_in);
				os.write(bytes, 0, c);
			}

			System.out.println("download success");
			os.close();
			is.close();
		} catch (IOException ex) {
			System.out.println("not download");
			System.out.println(ex);
		}
	}

	public static void main(String agrs[]) {

		String filepath[] = { "/callcenter/index.jsp", "/callcenter/ip.txt",
				"/callcenter/mainframe/image/processing_bar_2.gif",
				"/callcenter/mainframe/image/logo_01.jpg" };
		String localfilepath[] = { "C:\\FTP_Test\\index.jsp",
				"C:\\FTP_Test\\ip.txt", "C:\\FTP_Test\\processing_bar_2.gif",
				"C:\\FTP_Test\\logo_01.jpg" };

		Download fu = new Download();

		fu.connectServer("10.0.0.22", 21, "anonymous", "123", "/zcx");
		//for(int i=0;i<filepath.length;i++){
		//fu.download(filepath[i],localfilepath[i]);
		//}

		fu.upload("D://test2//test.txt", "test.txt");
		//fu.download();
		fu.closeConnect();

	}
}
