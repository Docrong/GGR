package com.boco.eoms.workplan.util;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.intfacewebservices.NeCheckerServiceLocator;
import com.boco.eoms.workplan.intfacewebservices.Service1Locator;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.util.Inspection;

public class TawwpIntfaceUtil {

	public List getList(String xpath, List list, Document doc,
			String opDetailFieldName) {

		NodeList UDSObjectList = null;
		Node UDSObject = null;
		String fieldValue = "";

		System.out.println("xpath:" + xpath);
		System.out.println("opDetailFieldName:" + opDetailFieldName);

		if (doc != null) {
			try {
				UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
			} catch (TransformerException e1) {

				e1.printStackTrace();
			}
			String uu = "";
			if (opDetailFieldName.equals("getNetInfo")) {
				uu = "netID,netName";
			} else if (opDetailFieldName.equals("getCmdTaskInfo")) {
				uu = "cmdID,cmdName";
			} else if (opDetailFieldName.equals("getTaskInfo")) {
				uu = "taskID,taskName";
			} else if (opDetailFieldName.equals("getTaskCmdResultInfo")) {
				uu = "cmdId,cmdName,executeTime,executeStatus,executeResult,executeFTP";
			} else if (opDetailFieldName.equals("getPerformCmdResultInfo")) {
				uu = "executeTime,executeStatus,executeResult,executeFTP";
			} else if (opDetailFieldName.equals("getPerformTaskResultInfo")) {
				uu = "executeTime,urlPath";
			}

			if (UDSObjectList.getLength() > 0) {
				for (int i = 0; i < UDSObjectList.getLength(); i++) {
					UDSObject = UDSObjectList.item(i);
					Inspection inspection = new Inspection();

					String[] str = uu.split(",");
					if (str.length > 0) {
						for (int u = 0; u < str.length; u++) {
							xpath = str[u];
							try {

								if (XPathAPI.selectSingleNode(UDSObject, xpath)
										.getFirstChild() != null) {
									System.out.println("节点:"
											+ xpath
											+ XPathAPI.selectSingleNode(
													UDSObject, xpath)
													.getFirstChild()
													.getNodeValue());
									if (xpath.equals("netID")) {
										inspection.setNetID(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("taskName")) {
										inspection.setTaskName(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("cmdID")) {
										inspection.setCmdID(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("cmdName")) {
										inspection.setCmdName(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("taskID")) {
										inspection.setTaskID(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("cmdId")) {
										inspection.setCmdID(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("executeTime")) {
										inspection.setExecuteTime(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("executeStatus")) {
										inspection.setExecuteStatus(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("executeResult")) {
										inspection.setExecuteResult(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("executeFTP")) {
										inspection.setExecuteFTP(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									} else if (xpath.equals("urlPath")) {
										inspection.setUrlPath(XPathAPI
												.selectSingleNode(UDSObject,
														xpath).getFirstChild()
												.getNodeValue());
									}
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}

					list.add(inspection);
				}
				Inspection inspectiontmp;
				Inspection inspectiontmps;
			}
		}
		return list;
	}

	public static String getLocalString() {
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd hh");
		String date = dateFormat.format(currentDate);

		return date;
	}

	public static Document getDOC(String str) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		if (str.indexOf("?xml") <= 0)
			str = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + str;
		DocumentBuilder dc = null;
		try {
			dc = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dc.parse(new InputSource(new StringReader(str)));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doc;
	}

	public Document getTaskCmdResultInfoDoc(String _command,
			TawwpNet _tawwpNet, String _startDate, String _endDate) {

		org.w3c.dom.Document _doc = null;
		String str = "";
		if (("NM").equals(WorkplanMgrLocator.getAttributes().getIntfaceDept())) {
			NeCheckerServiceLocator faultSheetLocator = new NeCheckerServiceLocator();

			try {
				str = faultSheetLocator.getNeCheckerServiceHttpPort()
						.getTaskCmdResultInfo(_command,
								_tawwpNet.getSerialNo(), _startDate, _endDate);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_doc = getDOC(str);
		} else {
			Service1Locator faultSheetLocator = new Service1Locator();
			com.boco.eoms.workplan.intfacewebservices.Service1Soap interSwitchEomsIP = null;
			try {
				interSwitchEomsIP = faultSheetLocator.getService1Soap();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 根据key值得到执行内容(单一用户)对象

			try {
				_doc = interSwitchEomsIP.getTaskCmdResultInfo(_command,
						_tawwpNet.getSerialNo(), _startDate, _endDate);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _doc;
	}

	public Document getTaskInfoDoc(String _netserialno, String userId) {

		org.w3c.dom.Document _doc = null;
		String str = "";
		System.out.println("WorkplanMgrLocator.getAttributes().getIntfaceDept()========="+WorkplanMgrLocator.getAttributes().getIntfaceDept());
		if (WorkplanMgrLocator.getAttributes().getIntfaceDept().equals("NM")) {
			NeCheckerServiceLocator faultSheetLocator = new NeCheckerServiceLocator();

			try {
				str = faultSheetLocator.getNeCheckerServiceHttpPort()
						.getTaskInfo(_netserialno, userId);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_doc = getDOC(str);
		} else {
			Service1Locator faultSheetLocator = new Service1Locator();
			com.boco.eoms.workplan.intfacewebservices.Service1Soap interSwitchEomsIP = null;
			try {
				interSwitchEomsIP = faultSheetLocator.getService1Soap();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 根据key值得到执行内容(单一用户)对象

			try {
				_doc = interSwitchEomsIP.getTaskInfo(_netserialno, userId);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _doc;
	}
	public Document getNetInfoDoc() {

		org.w3c.dom.Document _doc = null;
		String str = "";
		if (WorkplanMgrLocator.getAttributes().getIntfaceDept().equals("NM")) {
			NeCheckerServiceLocator faultSheetLocator = new NeCheckerServiceLocator();

			try {
				str = faultSheetLocator.getNeCheckerServiceHttpPort()
						.getNetInfo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_doc = getDOC(str);
		} else {
			Service1Locator faultSheetLocator = new Service1Locator();
			com.boco.eoms.workplan.intfacewebservices.Service1Soap interSwitchEomsIP = null;
			try {
				interSwitchEomsIP = faultSheetLocator.getService1Soap();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 根据key值得到执行内容(单一用户)对象

			try {
				_doc = interSwitchEomsIP.getNetInfo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _doc;
	}
	

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		//		String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
		//                             +"<UDSObjectList xmlns=\"\">"
		//                            +"<UDSObject>"
		//                            +"<Attributes>"
		//                            +"<netID>网元编号1</netID>"
		//                            +"<netName>网元名称1</netName>"
		//                            +"</Attributes>"
		//                            +"</UDSObject>"
		//                            +"<UDSObject>"
		//                            +"<Attributes>"
		//                            +"<netID>网元编号2</netID>"
		//                            +"<netName>网元名称2</netName>"
		//                            +"</Attributes>"
		//                            +"</UDSObject>"
		//                            +"</UDSObjectList>";
		//		String taskInfo="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
		//            +"<UDSObjectList xmlns=\"\">"
		//           +"<UDSObject>"
		//           +"<Attributes>"
		//           +"<taskID>任务编号1</taskID>"
		//           +"<taskName>任务名称1</taskName>"
		//           +"</Attributes>"
		//           +"</UDSObject>"
		//           +"<UDSObject>"
		//           +"<Attributes>"
		//           +"<taskID>任务编号1</taskID>"
		//           +"<taskName>任务名称1</taskName>"
		//           +"</Attributes>"
		//           +"</UDSObject>"
		//           +"<UDSObject>"
		//           +"<Attributes>"
		//           +"<taskID>任务编号2</taskID>"
		//           +"<taskName>任务名称2</taskName>"
		//           +"</Attributes>"
		//           +"</UDSObject>"
		//           +"</UDSObjectList>";
		//		String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
		//            +"<UDSObjectList xmlns=\"\">"
		//           +"<UDSObject>"
		//           +"<Attributes>"
		//           +"<cmdId>元任务ID1</cmdId>"
		//           +"<cmdName>元任务名称1</cmdName>"
		//           +"<executeTime>执行时间1</executeTime>"
		//           +"<executeStatus>执行状态1</executeStatus>"
		//           +"<executeResult>执行结果1</executeResult>"
		//           +"<executeFTP>原始报告FTP地址1</executeFTP>"
		//           +"</Attributes>"
		//           +"</UDSObject>"
		//           +"<UDSObject>"
		//           +"<Attributes>"
		//           +"<cmdId>元任务ID2</cmdId>"
		//           +"<cmdName>元任务名称2</cmdName>"
		//           +"<executeTime>执行时间2</executeTime>"
		//           +"<executeStatus>执行状态2</executeStatus>"
		//           +"<executeResult>执行结果2</executeResult>"
		//           +"<executeFTP>原始报告FTP地址2</executeFTP>"
		//           +"</Attributes>"
		//           +"</UDSObject>"
		//           +"</UDSObjectList>";

		String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<UDSObjectList xmlns=\"\">" + "<UDSObject>" + "<Attributes>"
				+ "<cmdID>元任务ID1</cmdID>" + "<cmdName>元任务名称1</cmdName>"
				+ "</Attributes>" + "</UDSObject>" + "<UDSObject>"
				+ "<Attributes>" + "<cmdID>元任务ID2</cmdID>"
				+ "<cmdName>元任务名称2</cmdName>" + "</Attributes>"
				+ "</UDSObject>" + "</UDSObjectList>";
		List list = new java.util.ArrayList();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
		DocumentBuilder dc = null;
		Document doc = null;
		try {
			dc = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			doc = dc.parse(new InputSource(new StringReader(str)));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xpath = "//UDSObjectList/UDSObject/Attributes";
		list = tawwpIntfaceUtil.getList(xpath, list, doc, "getCmdTaskInfo");
		System.out.print("list.size():" + String.valueOf(list.size()));
		for (Iterator it = list.iterator(); it.hasNext();) {
			Inspection workplanresult = (Inspection) it.next();
			System.out.println("workplanresult.getCmdID()："
					+ workplanresult.getCmdID());
			System.out.println("workplanresult.getCmdName()："
					+ workplanresult.getCmdName());

			//	String NetId = tawwpNetMgr
			//			.loadNetBySerial(inspection
			//					.getNetID());
			//	tawwpExecuteMgr.saveContentByNet(NetId,
			//			workplanresult);

		}
		//		String _startDate = tawwpIntfaceUtil
		//		.getLocalString();
		//		System.out.print("_startDate:"+_startDate);
	}

}
