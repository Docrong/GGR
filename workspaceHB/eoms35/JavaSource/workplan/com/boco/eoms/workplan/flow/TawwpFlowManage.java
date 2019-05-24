package com.boco.eoms.workplan.flow;

/**
 * <p>Title: 审批流程定义管理类</p>
 * <p>Description: 负责初始化定义的流程信息，缓存流程对象，提供流程步骤的分析</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco</p>
 * @author not attributable
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.flow.model.Flow;
import com.boco.eoms.workplan.flow.model.Step;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;

public class TawwpFlowManage {
	/**
	 * log4j
	 */
	private Logger logger = Logger.getLogger(TawwpFlowManage.class);

	private static Hashtable flowHash;

	private static TawwpFlowManage tawwpFlowManage = null;

	private static String defaultPath = "com/boco/eoms/workplan/config/workflow.xml";

	public TawwpFlowManage() {
		importXML();
	}

	public TawwpFlowManage(String _path) {
		importXML(_path);
	}

	/**
	 * 获取一个流程管理实例
	 * 
	 * @return TawwpFlowManage 流程管理类
	 */
	public static TawwpFlowManage getInstance() {
		if (tawwpFlowManage == null)
			tawwpFlowManage = new TawwpFlowManage();
		return tawwpFlowManage;
	}

	/**
	 * 获取一个流程管理实例
	 * 
	 * @param _path
	 *            String 配置文件路径
	 * @return TawwpFlowManage 流程管理类
	 */
	public static TawwpFlowManage getInstance(String _path) {
		if (tawwpFlowManage == null)
			tawwpFlowManage = new TawwpFlowManage(_path);
		return tawwpFlowManage;
	}

	/**
	 * 初始化配置信息，组织流程管理实例
	 */
	private void importXML() {
		importXML(defaultPath);
	}

	/**
	 * 初始化配置信息，组织流程管理实例
	 * 
	 * @param _path
	 *            String 配置文件路径
	 */
	private void importXML(String _path) {
		SAXBuilder builder = new SAXBuilder();
		// File file = new File(_path + defaultPath);
		File file = null;
		try {
			file = new File(StaticMethod.getFilePathForUrl(_path));
		} catch (FileNotFoundException e1) {
			logger.error(e1);
		}
		Document doc = null;
		Element element = null;

		try {
			doc = builder.build(file); // 初始化xml配置文件
		} catch (Exception e) {
			System.err.println("Error creating XML parser in "
					+ "PropertyManager.java");
			e.printStackTrace();
		}

		// 获取xml配置文件信息
		Element rootElement = doc.getRootElement();

		// 获取模版管理流程
		element = rootElement.getChild("flowmodel");
		importXMLFlowHash(element, "flowmodel");

		// 获取年度管理流程
		element = rootElement.getChild("flowyear");
		importXMLFlowHash(element, "flowyear");

		// 获取月度管理流程
		element = rootElement.getChild("flowmonth");
		importXMLFlowHash(element, "flowmonth");

		// 获取月度管理修改网元流程
		element = rootElement.getChild("flowmonthupdate");
		importXMLFlowHash(element, "flowmonthupdate");

		// 获取执行管理流程
		element = rootElement.getChild("flowexecute");
		importXMLFlowHash(element, "flowexecute");
	}

	/**
	 * 生成流程对象（Flow）
	 * 
	 * @param _element
	 *            Element xml配置信息一个流程元素
	 * @param _flowType
	 *            String 流程类型
	 */
	private void importXMLFlowHash(Element _element, String _flowType) {
		Element element = null;
		Flow flow = null;

		// 判断总流程结构hash是否存在
		if (flowHash == null) {
			flowHash = new Hashtable();
		}

		Hashtable hashtable = null;
		List list = null;

		hashtable = (Hashtable) flowHash.get(_flowType); // 获取对应的流程类型的hash

		// 如果对应的流程类型hash不存在，则创建一个。
		if (hashtable == null) {
			hashtable = new Hashtable();
			flowHash.put(_flowType, hashtable); // 放入总流程结构中
		}

		list = _element.getChildren(); // 获取该流程模块中各流程元素

		// 循环创建每个流程元素对象
		for (int i = 0; i < list.size(); i++) {
			element = (Element) list.get(i);
			flow = Flow.importXML(element); // 生成一个新流程对象
			hashtable.put(String.valueOf(flow.getSerial()), flow);
		}
	}

	/**
	 * 获取一个流程中的步骤对象
	 * 
	 * @param _flowSerial
	 *            String 流程标识
	 * @param _stepSerial
	 *            String 步骤标识
	 * @param _flowType
	 *            String 模块分类
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在下一步骤（流程结束）
	 */
	private Step getFlowStep(String _flowSerial, String _stepSerial,
			String _flowType, String deptId) {

		Hashtable hashtable = (Hashtable) this.flowHash.get(_flowType); // 获取该模块的流程集合

		Flow flow = null;
		Step step = null;
		Step newStep = null;
		List checkuser = null;
		String checkuserIdStr = "";
		String checkuserNameStr = "";
		TawSystemUser tawRmUser = null;

		try {
			// 如果该模块存在
			if (hashtable != null) {
				flow = (Flow) hashtable.get(_flowSerial); // 获取对应的流程信息
				if(StaticMethod.null2int(_stepSerial)>0)
				step = flow.getNextStep(StaticMethod.null2int(_stepSerial)); // 获取当前步骤的下一个步骤
				
				// 如果下一个步骤存在
				if (step != null) {
					newStep = new Step();
					newStep.setSerial(step.getSerial());
					newStep.setRoles(step.getRoles());
					newStep.setType(step.getType());
					newStep.setFlag(step.getFlag());
					newStep.setDeptId(step.getDeptId());
					newStep.setName(step.getName());
					checkuser = this.getCheckUser(newStep, deptId); // 根据步骤定义的信息，获取需要审批的人员集合
					newStep.setCheckUserList(checkuser); // 放入新步骤对象中

					if (checkuser.size() > 0) {
						for (int i = 0; i < checkuser.size(); i++) {
							tawRmUser = (TawSystemUser) checkuser.get(i);
							checkuserIdStr = checkuserIdStr + ","
									+ tawRmUser.getUserid();
							checkuserNameStr = checkuserNameStr + ","
									+ tawRmUser.getUsername();
						}
						newStep.setCheckUserIdStr(checkuserIdStr.substring(1,
								checkuserIdStr.length()));
						newStep.setCheckUserNameStr(checkuserNameStr.substring(
								1, checkuserNameStr.length()));
					} else {
						newStep.setCheckUserIdStr("");
						newStep.setCheckUserNameStr("");
					}
					newStep.setFlowSerial(flow.getSerial()); // 流程编号
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newStep; // 返回下一步骤的对象，如果没有下一步骤，则返回空
	}

	/**
	 * 获取第一个步骤信息
	 * 
	 * @param _flowType
	 *            String 模块分类
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在任何流程
	 */
	private Step getFristFlowStep(String _flowType, String deptId) {
		Hashtable hashtable = (Hashtable) this.flowHash.get(_flowType); // 获取该模块的流程集合

		Flow flow = null;
		Step step = null;
		Step newStep = null;
		List checkuser = null;
		String checkuserIdStr = "";
		String checkuserNameStr = "";
		TawSystemUser tawRmUser = null;

		try {
			// 如果该模块存在
			if (hashtable != null) {
				// 如果模块存在流程
				if (hashtable.size() > 0) {
					flow = (Flow) hashtable.get("0"); // 取第一个流程对象
				}

				if (flow != null) {
					step = flow.getFristStep(); // 调用流程的第一个步骤

					// 如果步骤不为空
					if (step != null) {
						newStep = new Step();
						newStep.setSerial(step.getSerial());
						newStep.setRoles(step.getRoles());
						newStep.setType(step.getType());
						newStep.setFlag(step.getFlag());
						newStep.setDeptId(step.getDeptId());
						newStep.setName(step.getName());
						checkuser = this.getCheckUser(newStep, deptId); // 根据步骤定义的信息，获取需要审批的人员集合
						newStep.setCheckUserList(checkuser); // 放入新步骤对象中
						if(checkuser!=null&&checkuser.size() > 0) {
								for (int i = 0; i < checkuser.size(); i++) {
									tawRmUser = (TawSystemUser) checkuser.get(i);
									checkuserIdStr = checkuserIdStr + ","
											+ tawRmUser.getUserid();
									checkuserNameStr = checkuserNameStr + ","
											+ tawRmUser.getUsername();
								}
								newStep.setCheckUserIdStr(checkuserIdStr.substring(
										1, checkuserIdStr.length()));
								newStep.setCheckUserNameStr(checkuserNameStr
										.substring(1, checkuserNameStr.length()));
						} else {
								newStep.setCheckUserIdStr("");
								newStep.setCheckUserNameStr("");
						}
						newStep.setFlowSerial(flow.getSerial()); // 流程编号
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newStep; // 返回步骤对象，如果没有步骤，则返回空
	}

	/**
	 * 获取模版管理流程步骤
	 * 
	 * @param _flowSerial
	 *            String 流程标识
	 * @param _stepSerial
	 *            String 步骤标识
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在下一步骤（流程结束）
	 */
	public Step getModelFlowStep(String _flowSerial, String _stepSerial,
			String deptId) {
		return this.getFlowStep(_flowSerial, _stepSerial, "flowmodel", deptId);
	}

	/**
	 * 获取年度管理流程步骤
	 * 
	 * @param _flowSerial
	 *            String 流程标识
	 * @param _stepSerial
	 *            String 步骤标识
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在下一步骤（流程结束）
	 */
	public Step getYearFlowStep(String _flowSerial, String _stepSerial,
			String deptId) {
		return this.getFlowStep(_flowSerial, _stepSerial, "flowyear", deptId);
	}

	/**
	 * 获取月度管理流程步骤
	 * 
	 * @param _flowSerial
	 *            String 流程标识
	 * @param _stepSerial
	 *            String 步骤标识
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在下一步骤（流程结束）
	 */
	public Step getMonthFlowStep(String _flowSerial, String _stepSerial,
			String deptId) {
		if(_flowSerial!=null&&_stepSerial!=null&&deptId!=null){
		    return this.getFlowStep(_flowSerial, _stepSerial, "flowmonth", deptId);
		}else{
			return null;
		}
	}

	/**
	 * 获取执行管理流程步骤
	 * 
	 * @param _flowSerial
	 *            String 流程标识
	 * @param _stepSerial
	 *            String 步骤标识
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在下一步骤（流程结束）
	 */
	public Step getExecuteFlowStep(String _flowSerial, String _stepSerial,
			String deptId) {
		return this
				.getFlowStep(_flowSerial, _stepSerial, "flowexecute", deptId);
	}

	/**
	 * 获取模版管理流程第一个步骤
	 * 
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在任何流程
	 */
	public Step getFristModelFlowStep(String deptId) {
		return this.getFristFlowStep("flowmodel", deptId);
	}

	/**
	 * 获取年度管理流程第一个步骤
	 * 
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在任何流程
	 */
	public Step getFristYearFlowStep(String deptId) {
		return this.getFristFlowStep("flowyear", deptId);
	}

	/**
	 * 获取月度管理流程第一个步骤
	 * 
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在任何流程
	 */
	public Step getFristMonthFlowStep(String deptId) {
		return this.getFristFlowStep("flowmonth", deptId);
	}

	/**
	 * 获取月度管理删除网元流程第一个步骤
	 * 
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在任何流程
	 */
	public Step getFristMonthUpdateFlowStep(String deptId) {
		return this.getFristFlowStep("flowmonthupdate", deptId);
	}

	/**
	 * 获取执行管理流程第一个步骤
	 * 
	 * @param deptId
	 *            String 部门
	 * @return Step 步骤信息，返回空则表明不存在任何流程
	 */
	public Step getFristExecuteFlowStep(String deptId) {
		return this.getFristFlowStep("flowexecute", deptId);
	}

	/**
	 * 根据步骤信息获取当前步骤的审批人员列表
	 * 
	 * @param _step
	 *            Step 步骤对象
	 * @param deptId
	 *            String 部门
	 * @return List 审批人员列表
	 */
	private List getCheckUser(Step _step, String deptId) {

		ITawSystemDeptManager tawDeptBO = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		ITawSystemPrivUserAssignManager tawUserPrivManager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		 String deptAudits="0";
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		
		
		List checkUserList = new ArrayList(); // 审批人列表
		String deptIdStr = null; // 部门列表

		try {
			if (_step.getCheckuser() != null
					&& !_step.getCheckuser().equals("")) {
				// 如果步骤中指定了审批人，则直接返回指定的审批人列表
				checkUserList = tawwpUtilDAO.getUser(_step.getCheckuser());
			} else {
				// 判断flag的不同状态，获取部门的字符串
				switch (_step.getFlag()) {
				case 0: // 当前部门
					deptIdStr = deptId;
					deptAudits="0";
					break;
				case 1: // 当前部门及下属部门
					// cccccccccccccccccc
					// deptIdStr = tawDeptBO.getNextLevecDepts(deptId,"1");
					deptAudits="1";
					break;
				case 2: // 当前地市
					// deptIdStr =
					// tawDeptBO.getRegionDept(Integer.parseInt(deptId));
					TawSystemDept depttemp = tawDeptBO.getDeptinfobydeptid(
							deptId, "0");

					deptIdStr = String.valueOf(depttemp.getRegionflag());
//					deptAudits="2";
					break;
				case 3: // 上级部门
					// cccccccccccccccccc
					// /deptIdStr =
					// tawDeptBO.getSuperDept(Integer.parseInt(deptId));
					deptAudits="2";
					break;
				case 4: // 指定部门
					deptIdStr = _step.getDeptId();
					break;
				case 5: // 全部审核人
					deptIdStr = _step.getDeptId();
					deptAudits="3";
					break;
				
			}
			// cccccccccccc
			Set  users = PrivMgrLocator.getPrivMgr().listUserByUrl(_step.getRoles()); //根据部门信息，获取部门中符合指定权限的人员列表
			if(users!=null){
				for(Iterator it=users.iterator();it.hasNext();){
					TawSystemUser user = (TawSystemUser)it.next();
					if(user.getId()!=null&&!user.getId().equals("")){
						if(deptAudits.equals("0")){
							if(user.getDeptid().equals(deptId)){
							checkUserList.add(user);
							}
						}else if((deptAudits.equals("1"))){
						TawSystemDept tawSystemDept =new TawSystemDept();
							tawSystemDept=tawDeptBO.getDeptinfobydeptid(deptId,"0");
						if(user.getDeptid().equals(tawSystemDept.getParentDeptid()) || user.getDeptid().equals(deptId)){
								checkUserList.add(user);
							}
						}
						else if((deptAudits.equals("2"))){
							TawSystemDept tawSystemDept =new TawSystemDept();
							tawSystemDept=tawDeptBO.getDeptinfobydeptid(deptId,"0");
							if(user.getDeptid().equals(tawSystemDept.getParentDeptid())){
								checkUserList.add(user);
							}
							}
						else if((deptAudits.equals("3"))){
							checkUserList.add(user);	
						}
						}
				
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return checkUserList; // 返回人员列表
	}
}
