package com.boco.eoms.workplan.mgr.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPServiceLocator;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentUserDao;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteDao;
import com.boco.eoms.workplan.intfacewebservices.Service1Locator;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.mgr.ITawwpTaskManageInterfaceMgr;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.util.Inspection;
import com.boco.eoms.workplan.util.TawwpIntfaceUtil;
import com.boco.eoms.workplan.vo.TawwpTaskInforVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 4:33:01 PM
 * </p>
 * 
 * @author 曲静�?
 * @version 3.5.1
 * 
 */
public class TawwpTaskManageInterfaceMgrImpl implements
		ITawwpTaskManageInterfaceMgr {

	// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new TawwpMonthExecuteDAO();
	// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
	// TawwpExecuteContentDAO();
	// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
	// TawwpExecuteContentUserDAO();

	private ITawwpMonthExecuteDao tawwpMonthExecuteDao;

	private ITawwpExecuteContentDao tawwpExecuteContentDao;

	private ITawwpExecuteContentUserDao tawwpExecuteContentUserDao;

	private ITawwpNetMgr tawwpNetMgr;

	/**
	 * @param tawwpNetMgr
	 *            the tawwpNetMgr to set
	 */
	public void setTawwpNetMgr(ITawwpNetMgr tawwpNetMgr) {
		this.tawwpNetMgr = tawwpNetMgr;
	}

	/**
	 * @param tawwpExecuteContentDao
	 *            the tawwpExecuteContentDao to set
	 */
	public void setTawwpExecuteContentDao(
			ITawwpExecuteContentDao tawwpExecuteContentDao) {
		this.tawwpExecuteContentDao = tawwpExecuteContentDao;
	}

	/**
	 * @param tawwpExecuteContentUserDao
	 *            the tawwpExecuteContentUserDao to set
	 */
	public void setTawwpExecuteContentUserDao(
			ITawwpExecuteContentUserDao tawwpExecuteContentUserDao) {
		this.tawwpExecuteContentUserDao = tawwpExecuteContentUserDao;
	}

	/**
	 * @param tawwpMonthExecuteDao
	 *            the tawwpMonthExecuteDao to set
	 */
	public void setTawwpMonthExecuteDao(
			ITawwpMonthExecuteDao tawwpMonthExecuteDao) {
		this.tawwpMonthExecuteDao = tawwpMonthExecuteDao;
	}

	/**
	 * 获取接口传递的执行信息文件，通过分析，将网元信息导入到网元维护表�?
	 * 
	 * @return boolean
	 */
	public boolean saveNetInfor() {
		Node netID = null;
		Node netName = null;
		Node devVendor = null;
		Node UDSObject = null;

		String netIDStr = "";
		String netNameStr = "";
		String devVendorStr = "";
		NodeList UDSObjectList = null;
		TawwpIntfaceUtil tawwpIntfaceUtil=new TawwpIntfaceUtil(); 
		com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIP interSwitchEomsIP;
		try {
			InterSwitchEomsIPServiceLocator faultSheetLocator = new InterSwitchEomsIPServiceLocator();
			interSwitchEomsIP = faultSheetLocator.getInterSwitchEomsIP();
			Document _doc = interSwitchEomsIP.netInfo();
//			Document _doc =tawwpIntfaceUtil.getNetInfoDoc();
			// TawwpNetBO tawwpNetBO = new TawwpNetBO();
			Map _map = new HashMap();
			// 定义xpath
			String xpath = "//UDSObjectList/UDSObject";
			// 得到UDSObjectList子元�?
			try {
				UDSObjectList = XPathAPI.selectNodeList(_doc, xpath);

			} catch (TransformerException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}

			// 逐个获得信息
			for (int i = 0; i < UDSObjectList.getLength(); i++) {
				UDSObject = UDSObjectList.item(i);

				// 获取netID
				xpath = "Attributes/netID";
				netID = XPathAPI.selectSingleNode(UDSObject, xpath);
				netIDStr = netID.getFirstChild().getNodeValue();

				// netIDStr = netID.getFirstChild().getNodeValue();
				// 获取netName
				xpath = "Attributes/netName";
				netName = XPathAPI.selectSingleNode(UDSObject, xpath);
				netNameStr = netName.getFirstChild().getNodeValue();

				// 获取netTypeId
				xpath = "Attributes/devVendor";
				devVendor = XPathAPI.selectSingleNode(UDSObject, xpath);
				devVendorStr = StaticMethod.strFromPageToDB(devVendor
						.getFirstChild().getNodeValue());
				// 进入查询
				_map.put("serialNo", netIDStr);

				List searchedNetList = tawwpNetMgr.searchNet(_map);
				// 如果不存在该网元
				if (searchedNetList.size() == 0) {
					// 保存网元
					tawwpNetMgr.addNetByInterface(netNameStr, netIDStr,
							devVendorStr);
				}
			}
			return true;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查询网元的执行内容列�?
	 * 
	 * @param _netId
	 *            String 网元标识
	 * @return List
	 */
	public List getTaskInfor(String _netId) {
		// 获取taskID
		Node taskID = null;
		Node taskName = null;
		Node manageState = null;
		Node lastFireTime = null;
		Node description = null;

		String taskIDStr = "";
		String taskNameStr = "";
		String manageStateStr = "";
		String lastFireTimeStr = "";
		String descriptionStr = "";

		// VO对象
		TawwpTaskInforVO tawwpTaskInforVO = null;

		NodeList UDSObjectList = null;
		Node UDSObject = null;
		// 获得VO list
		List taskInforList = new ArrayList();

		com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIP interSwitchEomsIP;
		try {
			InterSwitchEomsIPServiceLocator faultSheetLocator = new InterSwitchEomsIPServiceLocator();
			interSwitchEomsIP = faultSheetLocator.getInterSwitchEomsIP();
			org.w3c.dom.Document _doc = interSwitchEomsIP.taskInfo(_netId);

			String xpath = "//UDSObjectList/UDSObject";
			// 得到UDSObjectList子元�?
			try {
				UDSObjectList = XPathAPI.selectNodeList(_doc, xpath);
			} catch (TransformerException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}
			// 逐个获得信息
			for (int i = 0; i < UDSObjectList.getLength(); i++) {
				UDSObject = UDSObjectList.item(i);
				tawwpTaskInforVO = new TawwpTaskInforVO();

				xpath = "Attributes/taskID";
				taskID = XPathAPI.selectSingleNode(UDSObject, xpath);
				taskIDStr = taskID.getFirstChild().getNodeValue();

				xpath = "Attributes/taskName";
				taskName = XPathAPI.selectSingleNode(UDSObject, xpath);
				System.out.println(taskName.getFirstChild().getNodeValue());
				taskNameStr = taskName.getFirstChild().getNodeValue();

				xpath = "Attributes/manageState";
				manageState = XPathAPI.selectSingleNode(UDSObject, xpath);
				manageStateStr = manageState.getFirstChild().getNodeValue();

				xpath = "Attributes/lastFireTime";
				lastFireTime = XPathAPI.selectSingleNode(UDSObject, xpath);
				lastFireTimeStr = lastFireTime.getFirstChild().getNodeValue();

				xpath = "Attributes/description";
				description = XPathAPI.selectSingleNode(UDSObject, xpath);
				descriptionStr = StaticMethod.strFromPageToDB(description
						.getFirstChild().getNodeValue());
				// VO
				tawwpTaskInforVO.setTaskID(taskIDStr);
				tawwpTaskInforVO.setTaskName(taskNameStr);
				tawwpTaskInforVO.setManageState(manageStateStr);
				tawwpTaskInforVO.setLastFireTime(lastFireTimeStr);
				tawwpTaskInforVO.setDescription(descriptionStr);

				taskInforList.add(tawwpTaskInforVO);
			}

			return taskInforList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取接口传递的执行信息文件，通过分析，获取TawwpExecuteContentUser
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 作业计划执行内容
	 * @param _tawwpNet
	 *            TawwpNet 网元
	 * @param _startDate
	 *            String 日期
	 * @return TawwpExecuteContentUser 保存的作业计划执行内�?
	 */
	public TawwpExecuteContentUser saveExecuteInfor(
			TawwpExecuteContent _tawwpExecuteContent, TawwpNet _tawwpNet,
			String _startDate) {

		// 1 使用网元信息、executecontent、_startDate三个条件，调用远程接�?
		// 2 获取xml格式的文件流，进行数据分�?
		// 3 创建一个TawwpExecuteContentUser新类
		// 4 返回TawwpExecuteContentUser
		Node executeTime = null; // 执行时间
		Node indexName = null; // 指标名称
		Node executeResult = null; // 执行结果

		String executeTimeStr = "";
		String indexNameStr = "";
		String executeResultStr = "";

		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		NodeList UDSObjectList = null;
		Node UDSObject = null;
		List taskInforList = new ArrayList();
		com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIP interSwitchEomsIP;

		try {
			InterSwitchEomsIPServiceLocator faultSheetLocator = new InterSwitchEomsIPServiceLocator();
			interSwitchEomsIP = faultSheetLocator.getInterSwitchEomsIP();
			org.w3c.dom.Document _doc = interSwitchEomsIP
					.performResultInfo(_tawwpExecuteContent.getId(), _tawwpNet
							.getId(), _startDate);

			String xpath = "//UDSObjectList/UDSObject";
			// 得到UDSObjectList子元�?
			try {
				UDSObjectList = XPathAPI.selectNodeList(_doc, xpath);
			} catch (TransformerException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}

			if (UDSObjectList.getLength() > 0) {
				UDSObject = UDSObjectList.item(0);

				xpath = "Attributes/executeTime";
				executeTime = XPathAPI.selectSingleNode(UDSObject, xpath);
				executeTimeStr = executeTime.getFirstChild().getNodeValue();
				// 指标名称
				xpath = "Attributes/indexName";
				indexName = XPathAPI.selectSingleNode(UDSObject, xpath);
				indexNameStr = StaticMethod.strFromPageToDB(indexName
						.getFirstChild().getNodeValue());

				xpath = "Attributes/executeResult";
				executeResult = XPathAPI.selectSingleNode(UDSObject, xpath);
				executeResultStr = StaticMethod.strFromPageToDB(executeResult
						.getFirstChild().getNodeValue());

				// 执行内容单一对象
				// add by gongyufeng 添加一个正常不正常的字段 现在还有些弄不清楚.默认为1
				tawwpExecuteContentUser = new TawwpExecuteContentUser(
						_tawwpExecuteContent.getStartDate(),
						_tawwpExecuteContent.getEndDate(), executeTimeStr, "",
						_tawwpExecuteContent.getCruser(), executeResultStr, "",
						indexNameStr, "1", "",
						_tawwpExecuteContent.getFormId(), "",
						_tawwpExecuteContent, "1","");

			}
			// 返回
			return tawwpExecuteContentUser;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			return null;
		}
	}

	public String saveExecuteInfor(String _command, TawwpNet _tawwpNet,
			String _startDate) {

		Node executeResult = null; // 执行结果
		String executeResultStr = "";

		NodeList UDSObjectList = null;
		Node UDSObject = null;

		com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIP interSwitchEomsIP;

		try {
			InterSwitchEomsIPServiceLocator faultSheetLocator = new InterSwitchEomsIPServiceLocator();
			interSwitchEomsIP = faultSheetLocator.getInterSwitchEomsIP();
			org.w3c.dom.Document _doc = interSwitchEomsIP.performResultInfo(
					_command, _tawwpNet.getSerialNo(), _startDate.substring(0,
							10));

			String xpath = "//UDSObjectList/UDSObject";

			// 得到UDSObjectList子元�?
			try {
				UDSObjectList = XPathAPI.selectNodeList(_doc, xpath);
			} catch (TransformerException e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}

			if (UDSObjectList.getLength() > 0) {
				UDSObject = UDSObjectList.item(0);

				xpath = "Attributes/executeResult";
				executeResult = XPathAPI.selectSingleNode(UDSObject, xpath);
				executeResultStr = StaticMethod.strFromPageToDB(executeResult
						.getFirstChild().getNodeValue());
			}

			// 返回
			return executeResultStr;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询当前时间段内容是否有需要调用接口的执行内容，如果有循环调用接口信息�?br>
	 * 保存执行信息,并根据信息进行合格判断。如果不合格,则发送信息给执行人员
	 * 
	 * @param _startDate
	 *            String 开始时�?
	 * @param _endDate
	 *            String 结束时间
	 * @return boolean
	 */
	public boolean getExecuteInfor(String _startDate, String _endDate) {

		// 1 查询command不为空的所有montexecute集合
		// 2
		// 循环处理montexecute集合，查询montexecute对应的executecontent集合中符合_startDate和_endDate之间的记�?
		// 2.1 根据montexecute获取monthplan对象，从monthplan对象中获取网元信�?
		// 2.2 使用网元信息、executecontent、_startDate三个条件调用saveExecuteInfor 方法
		// 2.3 获取返回的TawwpExecuteContentUser，就行保存处�?

		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();
		//
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		try {

			// 查询command不为空的所有montexecute集合
			List executeContentList = tawwpMonthExecuteDao
					.listMonthExecuteCommand();
			// 遍历所有执行内�?
			for (int i = 0; i < executeContentList.size(); i++) {

				// 执行内容
				tawwpExecuteContent = (TawwpExecuteContent) executeContentList
						.get(i);
				tawwpMonthExecute = tawwpExecuteContent.getTawwpMonthExecute();

				// 获得执行内容
				tawwpExecuteContent = tawwpExecuteContentDao
						.filterTawwpExecuteContent(_startDate,
								tawwpMonthExecute);

				// 获得执行内容标识
				String executeContentId = tawwpExecuteContent.getId();
				// 月度作业计划Model
				TawwpMonthPlan tawwpMonthPlan = tawwpMonthExecute
						.getTawwpMonthPlan();
				// 网元信息
				TawwpNet net = tawwpMonthPlan.getTawwpNet();
				// 获得执行内容人信�?
				TawwpExecuteContentUser tawwpExecuteContentUser = saveExecuteInfor(
						tawwpExecuteContent, net, executeContentId);
				if (tawwpExecuteContentUser != null) {
					// 保存执行人信�?
					tawwpExecuteContentUserDao
							.saveExecuteContentUser(tawwpExecuteContentUser);

				}

			}
			return true;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * saveExecuteInfor
	 * 
	 * @param _document
	 *            Document
	 * @param _executePlanId
	 *            String
	 * @return TawwpExecuteContentUser
	 */
	public TawwpExecuteContentUser saveExecuteInfor(Document _document,
			String _executePlanId) {
		return null;
	}

	public org.w3c.dom.Document getExecuteInfor(String _command,
			TawwpNet _tawwpNet, String _startDate) {
		com.boco.eoms.workplan.intfacewebservices.Service1Soap interSwitchEomsIP;
	
		try {
			TawwpIntfaceUtil tawwpIntfaceUtil=new TawwpIntfaceUtil(); 
//			Service1Locator faultSheetLocator = new Service1Locator();
//			interSwitchEomsIP = faultSheetLocator.getService1Soap();
			
			System.out.println("_command:" + _command + " SerialNo:"
					+ _tawwpNet.getSerialNo() + " _startDate" + _startDate);
//			org.w3c.dom.Document _doc = interSwitchEomsIP.getTaskCmdResultInfo(
//					_command, _tawwpNet.getSerialNo(), _startDate, _startDate
//							.substring(0, 10)
//							+ " 23:59:59");
			org.w3c.dom.Document _doc=tawwpIntfaceUtil.getTaskCmdResultInfoDoc(_command, _tawwpNet, _startDate, _startDate
							.substring(0, 10)
							+ " 23:59:59");
			System.out.println("interface return result:" + _doc.toString());
			return _doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean getExecuteInfor(String _endDate) {

		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		String content = null;

		try {

			List executeContentList = tawwpExecuteContentDao
					.listExecuteContent(_endDate);
			// ��������ִ������
			for (int i = 0; i < executeContentList.size(); i++) {

				// ִ������
				tawwpExecuteContent = (TawwpExecuteContent) executeContentList
						.get(i);
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();

				TawwpExecuteContentUser tawwpExecuteContentUser = new TawwpExecuteContentUser();

				content = this.saveExecuteInfor(tawwpExecuteContent
						.getCommand(), tawwpMonthPlan.getTawwpNet(),
						tawwpExecuteContent.getStartDate());
				tawwpExecuteContentUser.setStartDate(tawwpExecuteContent
						.getStartDate());
				tawwpExecuteContentUser.setEndDate(tawwpExecuteContent
						.getEndDate());
				tawwpExecuteContentUser.setContent(content);
				tawwpExecuteContentUser.setCrtime(tawwpExecuteContent
						.getEndDate());
				tawwpExecuteContentUser.setEligibility("0");
				tawwpExecuteContentUser.setCruser(tawwpExecuteContent
						.getCruser().split(",")[0]);
				tawwpExecuteContentUser
						.setTawwpExecuteContent(tawwpExecuteContent);
				tawwpExecuteContentUserDao
						.saveExecuteContentUser(tawwpExecuteContentUser);

				tawwpExecuteContent.setContent(content);
				tawwpExecuteContent.setExecuteFlag("1");
				tawwpExecuteContentDao.saveExecuteContent(tawwpExecuteContent);
			}
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据网元序列号获得网元下的元任务id
	 * @param _netserialno
	 * @return
	 */
	public List getTaskByNet(String _netserialno) {
		
		String xpath = "//UDSObjectList/UDSObject/Attributes";
		TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
		List cmdTask = new ArrayList(); //
		try {
			TawwpIntfaceUtil tawwpIntfaceUtil1= new TawwpIntfaceUtil();
			Service1Locator faultSheetLocator = new Service1Locator();
			com.boco.eoms.workplan.intfacewebservices.Service1Soap interSwitchEomsIP = faultSheetLocator
					.getService1Soap();
			org.w3c.dom.Document cTask =tawwpIntfaceUtil1.getTaskInfoDoc(_netserialno,"");
//			org.w3c.dom.Document cTask = interSwitchEomsIP.getTaskInfo(
//					_netserialno, "");
			
			cmdTask = tawwpIntfaceUtil1.getList(xpath, cmdTask, cTask,
					"getTaskInfo"); // 任務list
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cmdTask;
	}

}
