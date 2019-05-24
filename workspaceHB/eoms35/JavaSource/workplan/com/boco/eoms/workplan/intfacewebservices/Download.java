package com.boco.eoms.workplan.intfacewebservices;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentUserDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteFileDao;
import com.boco.eoms.workplan.dao.ITawwpMonthPlanDao;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;
import com.boco.eoms.workplan.model.TawwpExecuteFile;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;

import sun.net.TelnetOutputStream;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

public class Download {
	String localfilename;

	String remotefilename;

	FtpClient ftpClient;
	
	

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

	public int download(String remoteFile, String localFile) {
		int fileSize = 0;
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
				fileSize += c;
			}

			System.out.println("download success");
			os.close();
			is.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("not download");
			System.out.println(ex);
		}
		return fileSize;
	}

	public void getExecuteFTP(org.w3c.dom.Document doc,
			String getPerformCmdResultInfoCmdID,
			String getPerformCmdResultInfoStartTime,
			String getPerformCmdResultInfoEndTime,
			String getPerformCmdResultInfoNetID) {
		Element root = doc.getDocumentElement();
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element rootNode = (Element) nodeList.item(i);
			NodeList list = rootNode.getChildNodes();
			if (list.getLength() > 0) {
				for (int j = 0; j < list.getLength(); j++) {
					Element node = (Element) list.item(i);
					if (node.getNodeName() != null) {
						if (node.getNodeName().equals("Attributes")) {
							System.out.println(node.getNodeName());
							NodeList listNode = node.getChildNodes();
							if (listNode.getLength() > 0) {
								for (int h = 0; h < listNode.getLength(); h++) {
									Element nodeTmp = (Element) listNode
											.item(h);
									System.out.println(nodeTmp.getNodeName());
									System.out.println(nodeTmp.getNodeName()
											+ "="
											+ nodeTmp.getLastChild()
													.getNodeValue());
									if (nodeTmp.getNodeName().equals(
											"executeFTP")
											&& nodeTmp.getLastChild()
													.getNodeValue() != null) {
										executeFTPDownload(
												nodeTmp.getLastChild()
														.getNodeValue(),
												getPerformCmdResultInfoCmdID,
												getPerformCmdResultInfoStartTime,
												getPerformCmdResultInfoEndTime,
												getPerformCmdResultInfoNetID);
									}
								}
							}
						}
					}
					// if (node.getNodeName().equals("executeFTP")
					// && node.getLastChild().getNodeValue()!=null) {
					// System.out.println(node.getNodeName()+"="+node.getLastChild().getNodeValue());
					// executeFTPDownload(node.getNodeValue(),
					// getPerformCmdResultInfoCmdID,
					// getPerformCmdResultInfoStartTime,
					// getPerformCmdResultInfoEndTime,
					// getPerformCmdResultInfoNetID);
					// }
				}

			}
		}

	}

	public void executeFTPDownload(String executeFTP,
			String getPerformCmdResultInfoCmdID,
			String getPerformCmdResultInfoStartTime,
			String getPerformCmdResultInfoEndTime,
			String getPerformCmdResultInfoNetID) {

		try {
			if (!"".equals(executeFTP) && executeFTP != null) {

				ITawwpExecuteContentUserDao tawwpExecuteContentUserDAO = (ITawwpExecuteContentUserDao) ApplicationContextHolder
						.getInstance().getBean("tawwpExecuteContentUserDao");
				ITawwpExecuteFileDao tawwpExecuteFileDAO = (ITawwpExecuteFileDao) ApplicationContextHolder
				.getInstance().getBean("tawwpExecuteFileDao");
				ITawwpExecuteContentDao tawwpExecuteContentDAO = (ITawwpExecuteContentDao) ApplicationContextHolder
				.getInstance().getBean("tawwpExecuteContentDao");
				ITawwpMonthPlanDao tawwpMonthPlanDAO = (ITawwpMonthPlanDao) ApplicationContextHolder
				.getInstance().getBean("tawwpMonthPlanDao");
				 
				/*String downloadFilePath = StaticMethod
						.getNodeName("Interface.Ftp.DownloadFilePath");*/
				String downloadFilePath = WorkplanMgrLocator.getAttributes().getPath();
				int sublength = executeFTP.lastIndexOf("/");
				String fileName = executeFTP.substring(sublength + 1,
						executeFTP.length());
				String path = executeFTP.substring(0, sublength);
				int length = path.lastIndexOf("/");
				String ip = path.substring(6, 18);
				Download fu = new Download();
				System.out.println(path.substring(18, path.length()));
				fu.connectServer(ip, 21, "administrator", "boco123znxj", path
						.substring(18, path.length()));
				fu.download(fileName, downloadFilePath + fileName);

				TawwpMonthPlan tawwpMonthPlan = tawwpMonthPlanDAO	
						.loadMonthPlan(getPerformCmdResultInfoCmdID);

				if (getPerformCmdResultInfoNetID.equals(tawwpMonthPlan
						.getTawwpNet().getId())) {

					if (!"".equals(getPerformCmdResultInfoStartTime)) {

						List list = tawwpExecuteContentDAO.listExecuteContent(
								tawwpMonthPlan,
								getPerformCmdResultInfoStartTime.substring(0,
										getPerformCmdResultInfoStartTime
												.length() - 9)
										+ " 00:00:00",
								getPerformCmdResultInfoStartTime.substring(0,
										getPerformCmdResultInfoStartTime
												.length() - 9)
										+ " 23:59:59");
						if (list.size() > 0) {
							for (int i = 0; i < list.size(); i++) {
								TawwpExecuteContentUser tawwpExecuteContentUser = null;
								TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) list
										.get(i);
								String _executeContentUserId = "";
								String _subUser = "";
						 
								if (_executeContentUserId == null
										|| "".equals(_executeContentUserId)) {

									if (_subUser == null
											|| "null".equals(_subUser)) {
										_subUser = "";
									}
									//	 add by gongyufeng  娣诲姞涓€涓甯镐笉姝ｅ父鐨勫瓧娈?鐜板湪杩樻湁浜涘紕涓嶆竻妤?榛樿涓?
								 tawwpExecuteContentUser = new TawwpExecuteContentUser(
											tawwpExecuteContent.getStartDate(),
											tawwpExecuteContent.getEndDate(),TawwpUtil.getCurrentDateTime(),
											TawwpUtil.getCurrentDateTime(),
											tawwpExecuteContent.getExecuter(),
											_subUser, "", "", "0", "0",
											tawwpExecuteContent.getFormId(),
											"", tawwpExecuteContent,"1",tawwpExecuteContent.getReason());

									// --------------------------------------------------------------

									// 锟斤拷锟斤拷执锟斤拷锟斤拷业锟狡伙拷执锟斤拷锟斤拷锟斤拷(锟斤拷一锟矫伙拷)
									tawwpExecuteContentUserDAO
											.saveExecuteContentUser(tawwpExecuteContentUser);

									// 锟斤拷锟斤拷执锟斤拷锟斤拷业锟狡伙拷执锟斤拷锟斤拷锟斤拷
									tawwpExecuteContent.setExecuteFlag("1");
									tawwpExecuteContentDAO
											.saveExecuteContent(tawwpExecuteContent);

									// 锟斤拷装锟斤拷锟斤拷锟斤拷锟斤拷
									TawwpExecuteFile tawwpExecuteFile = new TawwpExecuteFile(
											fileName, fileName, "100",
											TawwpUtil.getCurrentDateTime(), "",
											tawwpExecuteContent,
											tawwpExecuteContentUser);

									tawwpExecuteFileDAO
											.saveExecuteFile(tawwpExecuteFile);

									HibernateUtil.commitTransaction();

									// 锟斤拷锟斤拷锟斤拷锟斤拷拥锟街达拷锟斤拷锟斤拷锟?锟斤拷一锟矫伙拷)锟斤拷锟?
									// return (tawwpExecuteContentUser.getId());
								}
								// tawwpExecuteContent.

							}

						}
					}

					System.out.println("Download OK");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TawwpExecuteFile executeFTPDownload1(String executeFTP) {
		TawwpExecuteFile tawwpExecuteFile = null;
		try {
			if (!"".equals(executeFTP) && executeFTP != null) {
				/*String downloadFilePath = StaticMethod
						.getNodeName("Interface.Ftp.DownloadFilePath");*/
				String downloadFilePath = WorkplanMgrLocator.getAttributes().getPath();
				int sublength = executeFTP.lastIndexOf("/");
				String fileName = executeFTP.substring(sublength + 1,
						executeFTP.length());
				String exeName = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
				String path = executeFTP.substring(0, sublength);
				String ip = path.substring(6, path.length());
				ip = ip.substring(0,ip.indexOf("/"));
				Download fu = new Download();
				System.out.println("downloadPath:"
						+ path.substring(ip.length(), path.length()));
				fu.connectServer(ip, 21, "anonymous", "anonymous", path.substring(6+ip.length(), path.length()));
				// fu.connectServer(ip, 21, "anonymous", "anonymous", path.substring(16, path.length()));
				String fileCodeName = GenerateFileCodeName() + exeName;
				System.out.println("SaveFileName" + downloadFilePath
						+ fileCodeName);
				int fileSize = fu.download(fileName, downloadFilePath
						+ fileCodeName);
				tawwpExecuteFile = new TawwpExecuteFile(fileName, fileCodeName,
						fileSize + "", TawwpUtil.getCurrentDateTime(), "",
						null, null);
				System.out.println("Download OK");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tawwpExecuteFile;
	}

	// 锟斤拷锟斤拷巡锟斤拷取锟斤拷锟斤拷锟斤拷志 add by liukun 2008-10-30 begin
	public void downloadPatrolLog(String fileName) {
		try {
			if (!"".equals(fileName) && fileName != null) {
				String downloadFilePath =WorkplanMgrLocator.getAttributes().getErrorLogPath();
				// String downloadFilePath =
				// "/export/home/eoms2008/tomcat_cluster/tomcat1/webapps/ROOT/workplan/tawwpfile/znxjlogfile/";
				System.out.println(downloadFilePath);
				Download fu = new Download();
				String ip = WorkplanMgrLocator.getAttributes().getFtpLogIP();
				int port = Integer.parseInt(WorkplanMgrLocator.getAttributes().getFtpLogPort());
				String useracc =WorkplanMgrLocator.getAttributes().getFtpLogAcc();
				String userpwd = WorkplanMgrLocator.getAttributes().getFtpLogPwd();
				// String ip = "10.101.16.87";
				// fu.connectServer(ip, 8021,"administrator","boco123znxj","");
				fu.connectServer(ip, port, useracc, userpwd, "");
				System.out
						.println("SaveFileName" + downloadFilePath + fileName);
				int fileSize = fu.download(fileName, downloadFilePath
						+ fileName);
				if (fileSize != 0) {
					System.out.println("Download OK");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// add by liukun 2008-10-30 end
	public String GenerateFileCodeName() {
		String tempfilename = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
		Random r = new Random();
		int iRandom = r.nextInt(900000); // 取10之锟叫碉拷锟斤拷锟揭伙拷锟?
		String myTag = (1000000 - iRandom) + "_";
		tempfilename = myTag + tempfilename;
		return tempfilename;
	}

	public String GenerateRandomStr() {
		String str = "";
		int[] arr = new int[10];

		for (int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 40) + 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] == arr[i]) {
					i--;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			str += arr[i];
		}
		System.out.print(str.substring(0, 6));
		return str;
	}

	public static void main(String agrs[]) {

		// String filepath[] = { "/callcenter/index.jsp", "/callcenter/ip.txt",
		// "/callcenter/mainframe/image/processing_bar_2.gif",
		// "/callcenter/mainframe/image/logo_01.jpg" };
		// String localfilepath[] = { "C:\\FTP_Test\\index.jsp",
		// "C:\\FTP_Test\\ip.txt", "C:\\FTP_Test\\processing_bar_2.gif",
		// "C:\\FTP_Test\\logo_01.jpg" };
		//
		// Download fu = new Download();

		// fu.connectServer("10.0.0.22", 21, "anonymous", "123", "/zcx");
		// for(int i=0;i<filepath.length;i++){
		// fu.download(filepath[i],localfilepath[i]);
		// }

		// fu.upload("D://test2//test.txt", "test.txt");
		// //fu.download();
		// fu.closeConnect();

		// DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		//
		// DocumentBuilder dombuilder = null;
		// try {
		// dombuilder = domfac.newDocumentBuilder();
		// } catch (ParserConfigurationException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// InputStream is = null;
		// try {
		// is = new FileInputStream("D:/ceshi.xml");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		//
		// try {
		//
		// Document doc = dombuilder.parse(is);
		Download fu = new Download();
		fu.GenerateRandomStr();
		// fu.getExecuteFTP(doc, "", "", "", "");
		// } catch (SAXException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
	}

}
