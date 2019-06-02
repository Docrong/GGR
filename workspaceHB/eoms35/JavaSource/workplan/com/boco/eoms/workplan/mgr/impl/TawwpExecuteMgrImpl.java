package com.boco.eoms.workplan.mgr.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.gzjhhead.interfaces.AttachInfoListType;
import com.boco.eoms.gzjhhead.interfaces.AttachInfoType;
import com.boco.eoms.gzjhhead.interfaces.NoteType;
import com.boco.eoms.gzjhhead.interfaces.ReportTypeType;
import com.boco.eoms.gzjhhead.interfaces._ReportFormRequest;
import com.boco.eoms.workplan.dao.ITawwpExecuteAssessDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentUserDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteFileDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteReportDao;
import com.boco.eoms.workplan.dao.ITawwpMmonthPlanJdbc;
import com.boco.eoms.workplan.dao.ITawwpModelExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteUserDao;
import com.boco.eoms.workplan.dao.ITawwpMonthPlanDao;
import com.boco.eoms.workplan.dao.ITawwpNewLogDao;
import com.boco.eoms.workplan.dao.ITawwpYearExecuteDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.flow.TawwpFlowManage;
import com.boco.eoms.workplan.flow.model.Step;
import com.boco.eoms.workplan.intfacewebservices.Download;
import com.boco.eoms.workplan.intfacewebservices.Service1Locator;
import com.boco.eoms.workplan.mgr.ITawwpAddonsMgr;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpTaskManageInterfaceMgr;
import com.boco.eoms.workplan.model.TawwpExecuteAssess;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;
import com.boco.eoms.workplan.model.TawwpExecuteFile;
import com.boco.eoms.workplan.model.TawwpExecuteFlag;
import com.boco.eoms.workplan.model.TawwpExecuteReport;
import com.boco.eoms.workplan.model.TawwpModelExecute;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthExecuteUser;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpNewLog;
import com.boco.eoms.workplan.model.TawwpYearExecute;
import com.boco.eoms.workplan.util.Inspection;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpIntfaceUtil;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;
import com.boco.eoms.workplan.vo.TawwpExecuteAssessVO;
import com.boco.eoms.workplan.vo.TawwpExecuteContentUserVO;
import com.boco.eoms.workplan.vo.TawwpExecuteContentVO;
import com.boco.eoms.workplan.vo.TawwpExecuteCountVO;
import com.boco.eoms.workplan.vo.TawwpExecuteFileVO;
import com.boco.eoms.workplan.vo.TawwpExecuteReportVO;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpStatVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 10:22:43 AM
 * </p>
 * 
 * 
 * 
 */
public class TawwpExecuteMgrImpl implements ITawwpExecuteMgr {

	public ITawwpMonthExecuteUserDao tawwpMonthExecuteUserDao;

	public ITawwpExecuteContentDao tawwpExecuteContentDao;

	public ITawwpExecuteContentUserDao tawwpExecuteContentUserDao;

	public ITawwpMonthPlanDao tawwpMonthPlanDao;

	public ITawwpExecuteReportDao tawwpExecuteReportDao;

	public ITawwpExecuteAssessDao tawwpExecuteAssessDao;

	public ITawwpExecuteFileDao tawwpExecuteFileDao;

	public ITawwpAddonsMgr tawwpAddonsMgr;

	public ITawwpTaskManageInterfaceMgr tawwpTaskManageInterfaceMgr;

	public ITawwpNewLogDao tawwpNewLogDao;

	private ITawwpMmonthPlanJdbc tawwpMonthPlanJDBC;

	private ITawwpMonthExecuteDao tawwpMonthExecuteDao;

	private ITawwpYearExecuteDao tawwpYearExecuteDao;

	private ITawwpModelExecuteDao tawwpModelExecuteDao;

	private TawSystemUserDao tawSystemUserDao;
	Hashtable hashtable = new Hashtable();

	public void setTawwpMonthExecuteDao(
			ITawwpMonthExecuteDao tawwpMonthExecuteDao) {
		this.tawwpMonthExecuteDao = tawwpMonthExecuteDao;
	}

	public void setTawwpYearExecuteDao(ITawwpYearExecuteDao tawwpYearExecuteDao) {
		this.tawwpYearExecuteDao = tawwpYearExecuteDao;
	}

	public void setTawwpModelExecuteDao(
			ITawwpModelExecuteDao tawwpModelExecuteDao) {
		this.tawwpModelExecuteDao = tawwpModelExecuteDao;
	}

	public void setTawSystemUserDao(TawSystemUserDao tawSystemUserDao) {
		this.tawSystemUserDao = tawSystemUserDao;
	}

	public void setTawwpMonthPlanJDBC(ITawwpMmonthPlanJdbc tawwpMonthPlanJDBC) {
		this.tawwpMonthPlanJDBC = tawwpMonthPlanJDBC;
	}

	public List listExecuteFile(String id) {
		return tawwpExecuteFileDao.listExecuteFile(id);
	}

	/**
	 * @param tawwpAddonsMgr
	 *            the tawwpAddonsMgr to set
	 */
	public void setTawwpAddonsMgr(ITawwpAddonsMgr tawwpAddonsMgr) {
		this.tawwpAddonsMgr = tawwpAddonsMgr;
	}

	/**
	 * @param tawwpExecuteAssessDao
	 *            the tawwpExecuteAssessDao to set
	 */
	public void setTawwpExecuteAssessDao(
			ITawwpExecuteAssessDao tawwpExecuteAssessDao) {
		this.tawwpExecuteAssessDao = tawwpExecuteAssessDao;
	}

	/**
	 * @param tawwpExecuteReportDao
	 *            the tawwpExecuteReportDao to set
	 */
	public void setTawwpExecuteReportDao(
			ITawwpExecuteReportDao tawwpExecuteReportDao) {
		this.tawwpExecuteReportDao = tawwpExecuteReportDao;
	}

	/**
	 * @param tawwpMonthPlanDao
	 *            the tawwpMonthPlanDao to set
	 */
	public void setTawwpMonthPlanDao(ITawwpMonthPlanDao tawwpMonthPlanDao) {
		this.tawwpMonthPlanDao = tawwpMonthPlanDao;
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
	 * @param tawwpMonthExecuteUserDao
	 *            the tawwpMonthExecuteUserDao to set
	 */
	public void setTawwpMonthExecuteUserDao(
			ITawwpMonthExecuteUserDao tawwpMonthExecuteUserDao) {
		this.tawwpMonthExecuteUserDao = tawwpMonthExecuteUserDao;
	}

	/**
	 * 业务逻辑：显示日常执行作业计�?SHOW-EXECUTE-CONTENT-001)
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _deptId
	 *            String 部门编号
	 * @throws Exception
	 *             异常
	 * @return Hashtable 返回月度作业计划VO对象集合
	 */
	public Hashtable listExecutePlan(String _userId, String _deptId)
			throws Exception {

		// 初始化数据,DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List对象
		List monthExecuteUserList = null;

		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		// model对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 岗位编号字符串�
		String orgPostIdStr = "";

		try {
			// 获取该用户所在岗位编号集合�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);
			// 取得执行人信息列表(modified by lijia 2005-11-03)
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 取出model对象(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();

				// 如果Hashtable对象中没有包括,以此月度作业计划编号为key值的value,则进行VO对象的封装
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 进行VO对象的封装�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 获取执行类型名称
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// // 获取部门名称
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// // 获取系统类型名称
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 获取网元类型名称
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 获取创建人姓名�
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// 获取制定状态名称�
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// 获取执行状态名称�
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 获取执行负责人姓名�
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 获取网元名称
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}

					// modified by lijia 2005-11-28
					tawwpMonthPlanVO
							.setMonthExecuteUserId(tawwpMonthExecuteUser
									.getId());
					tawwpMonthPlanVO
							.setMonthExecuteUserState(tawwpMonthExecuteUser
									.getState());

					// 将VO对象添加到Hashtable对象中�
					monthPlanVOHash.put(tawwpMonthPlan.getId(),
							tawwpMonthPlanVO);
				}
			}

			// --------------------------------------------------------------------

			// 返回封装月度计划VO信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteUserList = null;
		}
	}

	/**
	 * 业务逻辑：显示日常执行作业计划(SHOW-EXECUTE-CONTENT-001)
	 * 
	 * @param _userId
	 *            String 用户登录名
	 * @param _deptId
	 *            String 部门编号
	 * @param monthflag
	 *            String 月度标示
	 * @param yearflag
	 *            String 年度标示
	 * @throws Exception
	 *             异常
	 * @return Hashtable 返回月度作业计划VO对象集合
	 */

	public Hashtable listExecutePlan(String _userId, String _deptId,
			String monthflag, String yearflag) throws Exception {

		// 初始化数据,DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List对象
		List monthExecuteUserList = null;

		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		// model对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 岗位编号字符串�
		String orgPostIdStr = "";

		try {
			// 获取该用户所在岗位编号集合�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 取得执行人信息列表(modified by lijia 2005-11-03)
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 取出model对象(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();

				// 如果Hashtable对象中没有包括,以此月度作业计划编号为key值的value,则进行VO对象的封装
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 进行VO对象的封装�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 获取执行类型名称
					if (tawwpMonthPlanVO.getMonthFlag().equals(monthflag)
							&& tawwpMonthPlanVO.getYearFlag().equals(yearflag)) {
						tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
								.getExecuteTypeName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteType())));
						// 获取部门名称
						tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
								.getDeptName(tawwpMonthPlanVO.getDeptId()));
						// // 获取系统类型名称
						tawwpMonthPlanVO
								.setSysTypeName(tawwpUtilDAO
										.getSysTypeName(tawwpMonthPlanVO
												.getNetTypeId()));
						// 获取网元类型名称
						tawwpMonthPlanVO
								.setNetTypeName(tawwpUtilDAO
										.getNetTypeName(tawwpMonthPlanVO
												.getSysTypeId()));
						// 获取创建人姓名�
						tawwpMonthPlanVO.setUserName(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getCruser()));
						// 获取制定状态名称�
						tawwpMonthPlanVO
								.setConstituteStateName(TawwpMonthPlanVO
										.getConstituteStateName(Integer
												.parseInt(tawwpMonthPlanVO
														.getConstituteState())));
						// 获取执行状态名称�
						tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteState())));
						// 获取执行负责人姓名�
						tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getPrincipal()));
						// 获取网元名称
						if (tawwpMonthPlan.getTawwpNet() != null) {
							tawwpMonthPlanVO.setNetName(StaticMethod
									.null2String(tawwpMonthPlan.getTawwpNet()
											.getName()));
						} else {
							tawwpMonthPlanVO.setNetName("无网元");
						}

						// modified by lijia 2005-11-28
						tawwpMonthPlanVO
								.setMonthExecuteUserId(tawwpMonthExecuteUser
										.getId());
						tawwpMonthPlanVO
								.setMonthExecuteUserState(tawwpMonthExecuteUser
										.getState());

						// 将VO对象添加到Hashtable对象中�
						monthPlanVOHash.put(tawwpMonthPlan.getId(),
								tawwpMonthPlanVO);
					}
				}
			}

			// --------------------------------------------------------------------

			// 返回封装月度计划VO信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteUserList = null;
		}
	}
	/*EOMS-周春兴-200909221349
	开发人邮箱：v_zhouchunxing@boco.com.cn
	时间：2009-09-22 13：49
	修改目的：根据广西的需求加入代理作业计划处理列表新加入此方法
	*/
	public Hashtable listSubExecutePlan(String _userId, String _deptId,
			String monthflag, String yearflag) throws Exception {

		// 初始化数据,DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List对象
		List monthExecuteUserList = null;

		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		// model对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		Hashtable hashtable = new Hashtable();
		List list=null;
		// 岗位编号字符串�
		String orgPostIdStr = "";
		int listCount=0;
		int eflag = 0;

		try {
			// 获取该用户所在岗位编号集合�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 取得执行人信息列表(modified by lijia 2005-11-03)
			String year = TawwpUtil.getCurrentDateTime("yyyy");
			String month = TawwpUtil.getCurrentDateTime("MM");
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr, yearflag,
							monthflag);
			if(monthExecuteUserList!=null && monthExecuteUserList.size()>0){
			if(hashtable==null || hashtable.size()==0){
			list=tawwpExecuteContentDao
			.listExecuteContentCount(TawwpUtil
					.getCurrentDateTime("yyyy-MM-dd")
					+ " 00:00:00", TawwpUtil
					.getCurrentDateTime("yyyy-MM-dd")
					+ " 23:59:59");
			hashtable=this.listExecuteContentCount(hashtable,list);
			}
			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) { 

				// 取出model对象(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
				if(monthflag.equals(month)&& yearflag.equals(year)){
					listCount= StaticMethod.nullObject2int(hashtable.get(tawwpMonthPlan.getId()));
					}else{
						listCount=1;	
					}

				// 如果Hashtable对象中没有包括,以此月度作业计划编号为key值的value,则进行VO对象的封装
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null && listCount>0) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 进行VO对象的封装�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 获取执行类型名称
					if (tawwpMonthPlanVO.getMonthFlag().equals(monthflag)
							&& tawwpMonthPlanVO.getYearFlag().equals(yearflag)) {
						tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
								.getExecuteTypeName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteType())));
						// 获取部门名称
						tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
								.getDeptName(tawwpMonthPlanVO.getDeptId()));
						// // 获取系统类型名称
						tawwpMonthPlanVO
								.setSysTypeName(tawwpUtilDAO
										.getSysTypeName(tawwpMonthPlanVO
												.getNetTypeId()));
						// 获取网元类型名称
						tawwpMonthPlanVO
								.setNetTypeName(tawwpUtilDAO
										.getNetTypeName(tawwpMonthPlanVO
												.getSysTypeId()));
						// 获取创建人姓名�
						tawwpMonthPlanVO.setUserName(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getCruser()));
						// 获取制定状态名称�
						tawwpMonthPlanVO
								.setConstituteStateName(TawwpMonthPlanVO
										.getConstituteStateName(Integer
												.parseInt(tawwpMonthPlanVO
														.getConstituteState())));
						// 获取执行状态名称�
						tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteState())));
						 eflag = tawwpExecuteContentDao.countExecuteFlagByYearPlanId
						 (tawwpMonthPlanVO.getYearPlanId(),_userId,_deptId,TawwpUtil
									.getCurrentDateTime("yyyy-MM-dd"));
						    
						    if(eflag==0){
						    	tawwpMonthPlanVO.setExecuteStateName("未执行");
						    }else{
						    	tawwpMonthPlanVO.setExecuteStateName("已执行");
						    }
						 
						// 获取执行负责人姓名�
						tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getPrincipal()));
						// 获取网元名称
						if (tawwpMonthPlan.getTawwpNet() != null) {
							tawwpMonthPlanVO.setNetName(StaticMethod
									.null2String(tawwpMonthPlan.getTawwpNet()
											.getName()));
						} else {
							tawwpMonthPlanVO.setNetName("无网元");
						}

						// modified by lijia 2005-11-28
						tawwpMonthPlanVO
								.setMonthExecuteUserId(tawwpMonthExecuteUser
										.getId());
						tawwpMonthPlanVO
								.setMonthExecuteUserState(tawwpMonthExecuteUser
										.getState());

						// 将VO对象添加到Hashtable对象中�
						monthPlanVOHash.put(tawwpMonthPlan.getId(),
								tawwpMonthPlanVO);
					}
				}
			}
			}
			// --------------------------------------------------------------------

			// 返回封装月度计划VO信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteUserList = null;
		}
	}
	/*
	修改结束：EOMS-wangbeiying-20091001133040
	*/

	/**
	 * 业务逻辑：显示日常执行作业计划(SHOW-EXECUTE-CONTENT-001)
	 * 
	 * @param _userId
	 *            String 用户登录名
	 * @param _deptId
	 *            String 部门编号
	 * @throws Exception
	 *             异常
	 * @return Hashtable 返回月度作业计划执行VO对象集合
	 */
	public List listMonthExecute(String _userId, String _deptId, String month,
			String year) throws Exception {

		// 初始化数据,DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// // List对象
		List monthExecuteList = null;
		List result = new ArrayList();
		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		// model对象
		TawwpMonthExecute tawwpMonthExecute = null;

		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		// 岗位编号字符串�
		String orgPostIdStr = "";
		int dayCount = 0; // 当月天数
		try {
			// 获取该用户所在岗位编号集合�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// // 取得执行人信息列表(modified by lijia 2005-11-03)
			monthExecuteList = tawwpMonthExecuteUserDao.listMonthExecute(
					_userId, _deptId, orgPostIdStr);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteList.size(); i++) {

				// 取出model对象(modified by lijia 2005-11-28)
				tawwpMonthExecute = (TawwpMonthExecute) monthExecuteList.get(i);
				TawwpMonthPlan tawwpMonthPlan = tawwpMonthExecute
						.getTawwpMonthPlan();
				System.out.print(tawwpMonthPlan);
				// 判断是否是当月计划�
				if (tawwpMonthPlan != null
						&& tawwpMonthPlan.getMonthFlag().equals(month)
						&& tawwpMonthPlan.getYearFlag().equals(year)) {

					tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
					// // 封装月度作业计划执行内容VO对象
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
							tawwpMonthExecute);
					// 获取执行周期
					tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
							.getCycleName(Integer.parseInt(tawwpMonthExecuteVO
									.getCycle())));
					// 获取电子表单名称
					tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpMonthExecuteVO.getFormId()));
					// 获取创建人姓名�
					tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthExecuteVO.getCruser()));
					// 获取网元类型名称
					tawwpMonthExecuteVO
							.setNetTypeName(tawwpUtilDAO
									.getNetTypeName(tawwpMonthExecuteVO
											.getNetTypeId()));

					// 获取执行人类型名称�
					if (tawwpMonthExecuteVO.getExecuterType() != null
							&& !""
									.equals(tawwpMonthExecuteVO
											.getExecuterType())) {
						tawwpMonthExecuteVO
								.setExecuterTypeName(TawwpMonthExecuteVO
										.getExecuterTypeName(Integer
												.parseInt(tawwpMonthExecuteVO
														.getExecuterType())));
					}
					// 如果执行人类型为班次
					if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
						// 获取机房名称
						String roomNames = tawwpUtilDAO
								.getRoomNames(tawwpMonthExecuteVO.getExecuter());
						tawwpMonthExecuteVO.setExecuterName(roomNames);
						tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

						// 获取执行班次名称
						if (tawwpMonthExecuteVO.getExecuteDuty() != null
								&& !"".equals(tawwpMonthExecuteVO
										.getExecuteDuty())) {
							// 获取执行班次名称
							tawwpMonthExecuteVO
									.setExecuteDutyName(tawwpMonthExecuteVO
											.getExecuteDutyName(Integer
													.parseInt(tawwpMonthExecuteVO
															.getExecuteDuty())));
						}
					} else {
						// 获取执行人(或部门、岗位)姓名(或名称)
						// 执行人类型为用户
						if ("0".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getUserNames(StaticMethod
											.null2String(tawwpMonthExecuteVO
													.getExecuter())));
						}
						// 执行人类型为部门
						if ("1".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getDeptNames(tawwpMonthExecuteVO
											.getExecuter()));
						}
						// 执行人类型为岗位
						if ("2".equals(tawwpMonthExecuteVO.getExecuterType())) {
							// ///////cccccccccccccccccccccc
							/*
							 * tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO.getOrgPostNames(
							 * tawwpMonthExecuteVO.getExecuter()));
							 */
						}
					}

					// 添加到集合中

					result.add(tawwpMonthExecuteVO);
				}
			}

			// --------------------------------------------------------------------

			// 返回封装月度计划VO信息的Hashtable
			return result;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteList = null;
		}
	}

	public Hashtable listExecutePlanNew(String _userId, String _deptId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthExecuteUserList = null;

		// Hashtable瀵硅薄
		Hashtable monthPlanVOHash = new Hashtable();

		// model瀵硅薄
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 宀椾綅缂栧彿瀛楃涓�
		String orgPostIdStr = "";

		try {
			// 鑾峰彇璇ョ敤鎴锋墍鍦ㄥ矖浣嶇紪鍙烽泦鍚�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 鍙栧緱鎵ц浜轰俊鎭垪琛�(modified by lijia 2005-11-03)
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 鍙栧嚭model瀵硅薄(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();

				// 濡傛灉Hashtable瀵硅薄涓病鏈夊寘鎷�,浠ユ鏈堝害浣滀笟璁″垝缂栧彿涓簁ey鍊肩殑value,鍒欒繘琛孷O瀵硅薄鐨勫皝瑁�
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 杩涜VO瀵硅薄鐨勫皝瑁�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 鑾峰彇鎵ц绫诲瀷鍚嶇О
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 鑾峰彇閮ㄩ棬鍚嶇О
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 鑾峰彇鍒涘缓浜哄鍚�
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// 鑾峰彇鎵ц鐘舵�佸悕绉�
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 鑾峰彇鎵ц璐熻矗浜哄鍚�
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 鑾峰彇缃戝厓鍚嶇О
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}

					// modified by lijia 2005-11-28
					tawwpMonthPlanVO
							.setMonthExecuteUserId(tawwpMonthExecuteUser
									.getId());
					tawwpMonthPlanVO
							.setMonthExecuteUserState(tawwpMonthExecuteUser
									.getState());

					// 灏哣O瀵硅薄娣诲姞鍒癏ashtable瀵硅薄涓

					monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);

				}
			}

			// --------------------------------------------------------------------

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凥ashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず鏃ュ父鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			monthExecuteUserList = null;
		}
	}

	public Hashtable listExecutePlanNew(String _userId, String _deptId,
			String monthflag, String yearflag) throws Exception {

		// 初始化数据,DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
//		Hashtable hashtable = new Hashtable();

		// List对象
		List monthExecuteUserList = null;
	
		List list=null;
		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		// model对象

		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		int listCount=0;
		// 岗位编号字符串�
		String orgPostIdStr = "";

		try {
			// 获取该用户所在岗位编号集合�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 取得执行人信息列表(modified by lijia 2005-11-03)
			/*EOMS-周春兴-200909221349
			开发人邮箱：v_zhouchunxing@boco.com.cn
			时间：2009-09-22 13：49
			修改目的：根据广西的需求解决日常执行列表显示的性能问题
			*/
			String year = TawwpUtil.getCurrentDateTime("yyyy");
			String month = TawwpUtil.getCurrentDateTime("MM");
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr, yearflag,
							monthflag);
			/*list=tawwpExecuteContentDao
			.listExecuteContentCount(TawwpUtil
					.getCurrentDateTime("yyyy-MM-dd")
					+ " 00:00:00", TawwpUtil
					.getCurrentDateTime("yyyy-MM-dd")
					+ " 23:59:59");
			hashtable=this.listExecuteContentCount(hashtable,list);*/
			/*
			修改结束：EOMS-zhouchunxing-20091001133040
			*/
			// listMonthByExecuteUser
			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 取出model对象(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
//				System.out.println("tawwpMonthPlan.getId()=======>"
//						+ tawwpMonthPlan.getId());
				
				/*EOMS-周春兴-200909221349
				开发人邮箱：v_zhouchunxing@boco.com.cn
				时间：2009-09-22 13：49
				修改目的：根据广西的需求解决日常执行列表显示的性能问题
				*/
				/*if(monthflag.equals(month)&& yearflag.equals(year)){
				listCount= StaticMethod.nullObject2int(hashtable.get(tawwpMonthPlan.getId()));
				}else{
					listCount=1;	
				}*/
				/*
				修改结束：EOMS-zhouchunxing-20091001133040
				*/
				// 如果Hashtable对象中没有包括,以此月度作业计划编号为key值的value,则进行VO对象的封装&& listCount>0�
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null
						) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 进行VO对象的封装�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// // 获取执行类型名称
					if (tawwpMonthPlanVO.getMonthFlag().equals(monthflag)
							&& tawwpMonthPlanVO.getYearFlag().equals(yearflag)) {
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 获取部门名称
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// 获取系统类型名称
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 获取网元类型名称
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 获取创建人姓名�
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// // 获取制定状态名称�
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// // 获取执行状态名称
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 获取执行负责人姓名�
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));

					 if (tawwpMonthPlan.getTawwpNet() != null) {
					 tawwpMonthPlanVO.setNetName(StaticMethod
					 .null2String(tawwpMonthPlan.getTawwpNet()
					 .getName()));
					 } else {
					 tawwpMonthPlanVO.setNetName("无网元");
					 }

					// modified by lijia 2005-11-28
					tawwpMonthPlanVO
							.setMonthExecuteUserId(tawwpMonthExecuteUser
									.getId());
					tawwpMonthPlanVO
							.setMonthExecuteUserState(tawwpMonthExecuteUser
									.getState());

					// 将VO对象添加到Hashtable对象中
					

					monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);
					}
				}
			}

			// --------------------------------------------------------------------

			// 返回封装月度计划VO信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteUserList = null;
		}
	}
	public Hashtable listGxExecutePlanNew(String _userId, String _deptId,
			String monthflag, String yearflag) throws Exception {

		// 初始化数据,DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
//		Hashtable hashtable = new Hashtable();

		// List对象
		List monthExecuteUserList = null;
	
		List list=null;
		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		// model对象
		int eflag  = 0;
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		int listCount=0;
		// 岗位编号字符串�
		String orgPostIdStr = "";

		try {
			// 获取该用户所在岗位编号集合�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 取得执行人信息列表(modified by lijia 2005-11-03)
			/*EOMS-周春兴-200909221349
			开发人邮箱：v_zhouchunxing@boco.com.cn
			时间：2009-09-22 13：49
			修改目的：根据广西的需求解决日常执行列表显示的性能问题
			*/
			String year = TawwpUtil.getCurrentDateTime("yyyy");
			String month = TawwpUtil.getCurrentDateTime("MM");
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr, yearflag,
							monthflag);
			list=tawwpExecuteContentDao
			.listExecuteContentCount(TawwpUtil
					.getCurrentDateTime("yyyy-MM-dd")
					+ " 00:00:00", TawwpUtil
					.getCurrentDateTime("yyyy-MM-dd")
					+ " 23:59:59");
			Hashtable dayHashtable = new Hashtable();
			dayHashtable=this.listExecuteContentCount(dayHashtable,list);
			/*
			修改结束：EOMS-zhouchunxing-20091001133040
			*/
			// listMonthByExecuteUser
			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 取出model对象(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
//				System.out.println("tawwpMonthPlan.getId()=======>"
//						+ tawwpMonthPlan.getId());
				
				/*EOMS-周春兴-200909221349
				开发人邮箱：v_zhouchunxing@boco.com.cn
				时间：2009-09-22 13：49
				修改目的：根据广西的需求解决日常执行列表显示的性能问题
				*/
				if(monthflag.equals(month)&& yearflag.equals(year)){
					listCount= StaticMethod.nullObject2int(dayHashtable.get(tawwpMonthPlan.getId()));
				}else{
					listCount=1;	
				}
				/*
				修改结束：EOMS-zhouchunxing-20091001133040
				*/
				// 如果Hashtable对象中没有包括,以此月度作业计划编号为key值的value,则进行VO对象的封装�
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null
						&& listCount>0) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 进行VO对象的封装�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// // 获取执行类型名称
					if (tawwpMonthPlanVO.getMonthFlag().equals(monthflag)
							&& tawwpMonthPlanVO.getYearFlag().equals(yearflag)) {
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 获取部门名称
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// 获取系统类型名称
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 获取网元类型名称
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 获取创建人姓名�
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// // 获取制定状态名称�
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// // 获取执行状态名称
					/*tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));*/
				    eflag = tawwpExecuteContentDao.countExecuteFlagByYearPlanId
				    (tawwpMonthPlanVO.getYearPlanId(),_userId,_deptId,TawwpUtil
							.getCurrentDateTime("yyyy-MM-dd"));
				    System.out.println(eflag);
				    if(eflag==0){
				    	tawwpMonthPlanVO.setExecuteStateName("未执行");
				    }else{
				    	tawwpMonthPlanVO.setExecuteStateName("已执行");
				    }
				 
					 
					// 获取执行负责人姓名�
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));

//					 if (tawwpMonthPlan.getTawwpNet() != null) {
//					 tawwpMonthPlanVO.setNetName(StaticMethod
//					 .null2String(tawwpMonthPlan.getTawwpNet()
//					 .getName()));
//					 } else {
//					 tawwpMonthPlanVO.setNetName("无网元");
//					 }

					// modified by lijia 2005-11-28
					tawwpMonthPlanVO
							.setMonthExecuteUserId(tawwpMonthExecuteUser
									.getId());
					tawwpMonthPlanVO
							.setMonthExecuteUserState(tawwpMonthExecuteUser
									.getState());

					// 将VO对象添加到Hashtable对象中
					

						monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);
					}
				}
			}

			// --------------------------------------------------------------------

			// 返回封装月度计划VO信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteUserList = null;
		}
	}

	/**
	 * 广西作业计划日常值班
	 * 
	 * @param _userId
	 * @param _deptId
	 * @return
	 * @throws Exception
	 */
	public Hashtable listExecutePlann(String _userId, String _deptId)
			throws Exception {

		// 初始化数据,DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List对象
		List monthExecuteUserList = null;

		boolean s = false;

		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		// model对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 岗位编号字符串
		String orgPostIdStr = "";

		try {
			// 获取该用户所在岗位编号集合
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 取得执行人信息列表(modified by lijia 2005-11-03)
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {
				Enumeration hashKeys = null;

				// 取出model对象(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
				// 判断月计划中是否有这个作业计划名称
				if (monthPlanVOHash.size() != 0) {
					hashKeys = monthPlanVOHash.keys();
					while (hashKeys.hasMoreElements()) {
						TawwpMonthPlan tawwpMonthPlan1 = (TawwpMonthPlan) (hashKeys
								.nextElement());
						if (tawwpMonthPlan1.getName().equals(
								tawwpMonthPlan.getName())) {
							s = true;
						}
					}
				}
				if (s) {
					s = false;
					continue;

				}
				// 如果Hashtable对象中没有包括,以此月度作业计划编号为key值的value,则进行VO对象的封装
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 进行VO对象的封装
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 获取执行类型名称
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 获取部门名称
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// 获取系统类型名称
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 获取网元类型名称
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 获取创建人姓名
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// 获取制定状态名称
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// 获取执行状态名称
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 获取执行负责人姓名
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 获取网元名称
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}

					// modified by lijia 2005-11-28
					tawwpMonthPlanVO
							.setMonthExecuteUserId(tawwpMonthExecuteUser
									.getId());
					tawwpMonthPlanVO
							.setMonthExecuteUserState(tawwpMonthExecuteUser
									.getState());

					// 将VO对象添加到Hashtable对象中

					monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);
				}
			}

			// --------------------------------------------------------------------

			// 返回封装月度计划VO信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteUserList = null;
		}
	}

	/**
	 * 涓氬姟閫昏緫锛氭樉绀哄�肩彮鎵ц浣滀笟璁″垝(SHOW-EXECUTE-CONTENT-002)
	 * 鍓嶇疆鏉′欢锛氬綋鍓嶇敤鎴峰湪鐧诲綍鏈烘埧涓浜庡�肩彮鐘舵��
	 * 
	 * @param _roomId
	 *            String 鏈烘埧缂栧彿
	 * @param _yearFlag
	 *            String 璁″垝骞村害
	 * @param _monthFlag
	 *            String 璁″垝鏈堝害
	 * @param _dutyStartTime
	 *            String 鐝寮�濮嬫椂闂�
	 * @param _dutyEndTime
	 *            String 鐝缁撴潫鏃堕棿
	 * @throws Exception
	 *             寮傚父
	 * @return List 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄闆嗗悎
	 */
	public List listDutyExecutePlan(String _roomId, String _yearFlag,
			String _monthFlag, String _dutyStartTime, String _dutyEndTime)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthPlanVOList = new ArrayList();
		List monthExecuteUserList = new ArrayList();

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		String executeDuty = "";

		try {
			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭鎵ц浜哄璞￠泦鍚�
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUserDuty(_roomId, _yearFlag, _monthFlag);
			String monthplanids = ",";
			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 鍙栧嚭鎵ц鐝缂栧彿
				executeDuty = ((TawwpMonthExecuteUser) monthExecuteUserList
						.get(i)).getExecuteDuty();
				// 鍒ゆ柇鏈堝害浣滀笟璁″垝鏄惁搴旇,鍦ㄤ綔涓哄弬鏁颁紶鍏ョ殑鐝寮�濮嬨�佺粨鏉熸椂闂磋寖鍥翠互鍐呮墽琛�
				if (this.judgeDutyMonthPlan(executeDuty, _dutyStartTime,
						_dutyEndTime)) {
					// 鍙栧嚭model瀵硅薄,澶勭悊鍚庡皝瑁呮垚VO瀵硅薄
					tawwpMonthPlan = ((TawwpMonthExecuteUser) monthExecuteUserList
							.get(i)).getTawwpMonthPlan();
					if (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) {
						monthplanids = monthplanids + tawwpMonthPlan.getId()
								+ ",";

						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// 鑾峰彇鎵ц绫诲瀷鍚嶇О
						tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
								.getExecuteTypeName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteType())));
						// 鑾峰彇閮ㄩ棬鍚嶇О
						tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
								.getDeptName(tawwpMonthPlanVO.getDeptId()));
						// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
						tawwpMonthPlanVO
								.setSysTypeName(tawwpUtilDAO
										.getSysTypeName(tawwpMonthPlanVO
												.getSysTypeId()));
						// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
						tawwpMonthPlanVO
								.setNetTypeName(tawwpUtilDAO
										.getNetTypeName(tawwpMonthPlanVO
												.getNetTypeId()));
						// 鑾峰彇鍒涘缓浜哄鍚�
						tawwpMonthPlanVO.setUserName(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getCruser()));
						// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
						tawwpMonthPlanVO
								.setConstituteStateName(TawwpMonthPlanVO
										.getConstituteStateName(Integer
												.parseInt(tawwpMonthPlanVO
														.getConstituteState())));
						// 鑾峰彇鎵ц鐘舵�佸悕绉�
						tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteState())));
						// 鑾峰彇鎵ц璐熻矗浜哄鍚�
						tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getPrincipal()));
						// 鑾峰彇缃戝厓鍚嶇О
						if (tawwpMonthPlan.getTawwpNet() != null) {
							tawwpMonthPlanVO.setNetName(StaticMethod
									.null2String(tawwpMonthPlan.getTawwpNet()
											.getName()));
						} else {
							tawwpMonthPlanVO.setNetName("无网元");
						}

						// 灏哣O瀵硅薄娣诲姞绱у垪琛ㄤ腑
						monthPlanVOList.add(tawwpMonthPlanVO);
					}
				}
			}

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凩ist
			return monthPlanVOList;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず鍊肩彮鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			monthExecuteUserList = null;
		}
	}

	/**
	 * 鍒ゆ柇鏈堝害浣滀笟璁″垝鏄惁搴旇,鍦ㄤ綔涓哄弬鏁颁紶鍏ョ殑鐝寮�濮嬨�佺粨鏉熸椂闂磋寖鍥翠互鍐呮墽琛�
	 * 
	 * @param _executeDuty
	 *            String 鎵ц鐝缂栧彿
	 * @param _dutyStartTime
	 *            String 鐝寮�濮嬫椂闂�
	 * @param _dutyEndTime
	 *            String 鐝缁撴潫鏃堕棿
	 * @throws Exception
	 *             寮傚父
	 * @return boolean 杩斿洖鏄惁搴旇鎵ц鏍囧織
	 */
	private boolean judgeDutyMonthPlan(String _executeDuty,
			String _dutyStartTime, String _dutyEndTime) throws Exception {
		// 鍒濆鍖栨暟鎹�,鏄惁搴旇鎵ц鏍囧織
		boolean flag = false;
		// 濡傛灉姣忓ぉ姣忕彮娆℃墽琛屽垯杩斿洖true
		if ("0".equals(_executeDuty)) {
			flag = true;
		} else {
			// 鑾峰彇鎵ц鐝鍚嶇О
			String executeDutyName = TawwpMonthExecuteVO
					.getExecuteDutyName(StaticMethod.getIntValue(_executeDuty,
							0));
			// 缁勫悎姣旇緝鐢ㄧ殑涓存椂瀛楃涓�
			String tempStr = _dutyStartTime.substring(0, 11).trim() + " "
					+ executeDutyName + ":00";
			// 濡傛灉鍦ㄧ彮娆¤寖鍥翠互鍐�,鍒欏皢鏍囧織浣嶇疆涓簍rue
			if (tempStr.compareTo(_dutyEndTime) <= 0) {
				flag = true;
			}
		}
		// 杩斿洖鏄惁搴旇鎵ц鏍囧織
		return flag;
	}

	/**
	 * 涓氬姟閫昏緫:鎵归噺濉啓鏃ュ父鎵ц浣滀笟璁″垝鍐呭_淇濆瓨(FILL-EXECUTE-CONTENT-002)
	 * 
	 * @param _executeContentUserHash
	 *            Hashtable 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)闆嗗悎
	 *            (key鍊间负executeContentId,value涓烘湭澶勭悊鐨別xecuteContentUser瀵硅薄)
	 * @param _userId
	 *            String 褰撳墠鐢ㄦ埛鐧诲綍鍚�
	 * @param _executeType
	 *            String 鎵ц绫诲瀷
	 * @throws Exception
	 *             寮傚父
	 */
	public void addExecuteContentUsersSave(Hashtable _executeContentUserHash,
			String _userId, String _executeType) throws Exception {

		// // 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();

		// 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteContentUser tempExecuteContentUser = null;
		// 鎵ц鍐呭(鏁翠綋)缂栧彿
		String executeContentId = "";
		// 鑾峰彇鎵ц鍐呭(鏁翠綋)缂栧彿闆嗗悎
		Enumeration tempVar = _executeContentUserHash.keys();
		// 鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		try {
			while (tempVar.hasMoreElements()) {

				// 鑾峰彇hashtable鐨凨ey鍊�
				executeContentId = (String) tempVar.nextElement();

				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
				tawwpExecuteContent = tawwpExecuteContentDao
						.loadExecuteContent(executeContentId);
				// 鑾峰彇褰撳墠鏃堕棿
				String executeTime = TawwpUtil.getCurrentDateTime();
				// 瀹氫箟瓒呮椂鏍囧織
				String executeFlag = "";
				// 鍒ゆ柇濉啓鏄惁瓒呮椂
				if (this.judgeTimeOut(executeTime, tawwpExecuteContent
						.getEndDate())) {
					executeFlag = "1"; // 鎸夋椂

				} else {
					executeFlag = "2"; // 瓒呮椂
				}
				// gongyufeng
				// --------------------------------------------------------------

				// 濡傛灉澶氫汉濉啓澶氫唤
				if ("1".equals(_executeType)) {
					// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
					tempExecuteContentUser = tawwpExecuteContentUserDao
							.filterTawwpExecuteContentUser(_userId,
									tawwpExecuteContent);
					// 濡傛灉瀵硅薄涓嶄负绌�
					if (tempExecuteContentUser != null) {

						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						tempExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()); // 鎵ц鍐呭
						tempExecuteContentUser
								.setRemark(tawwpExecuteContentUser.getRemark()); // 澶囨敞淇℃伅
						tempExecuteContentUser
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
						tempExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tempExecuteContentUser
								.setWriteDate(tawwpExecuteContentUser
										.getWriteDate());
						tempExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tempExecuteContentUser
								.setNormalFlag(tawwpExecuteContentUser
										.getNormalFlag());// 姝ｅ父鏍囧織
						tempExecuteContentUser
								.setReason(tawwpExecuteContentUser.getReason());
						// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tempExecuteContentUser);
					} else {

						tawwpExecuteContentUser = new TawwpExecuteContentUser();
						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
						tawwpExecuteContentUser
								.setStartDate(tawwpExecuteContent
										.getStartDate()); // 璁″垝寮�濮嬫椂闂�
						tawwpExecuteContentUser.setEndDate(tawwpExecuteContent
								.getEndDate()); // 璁″垝缁撴潫鏃堕棿
						tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
						tawwpExecuteContentUser.setFormId(tawwpExecuteContent
								.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
						tawwpExecuteContentUser
								.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織1
						String normalFlag = (String) DictMgrLocator
								.getDictService()
								.itemId2name(
										Util.constituteDictId("dict-workplan",
												"normalFlag"),
										StaticMethod
												.null2String(tawwpExecuteContentUser
														.getNormalFlag()));
						tawwpExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()
										+ "   "
										+ userManager.getTawSystemUserByuserid(
												_userId).getUsername()
										+ "  "
										+ normalFlag + "| ");
						// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tawwpExecuteContentUser);
					}
					tawwpExecuteContent.setNormalFlag(tawwpExecuteContentUser
							.getNormalFlag());// 姝ｅ父鏍囧織

					// 澶氫汉濉啓涓�浠�
				} else {

					// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
					tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
							.get(executeContentId);

					if (tawwpExecuteContentUser.getId() != null
							&& !"".equals(tawwpExecuteContentUser.getId())) {

						tempExecuteContentUser = tawwpExecuteContentUserDao
								.loadExecuteContentUser(tawwpExecuteContentUser
										.getId());

						tempExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()); // 鎵ц鍐呭
						tempExecuteContentUser
								.setRemark(tawwpExecuteContentUser.getRemark()); // 澶囨敞淇℃伅
						tempExecuteContentUser
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
						tempExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tempExecuteContentUser
								.setWriteDate(tawwpExecuteContentUser
										.getWriteDate());
						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tempExecuteContentUser
								.setNormalFlag(tawwpExecuteContentUser
										.getNormalFlag());// 姝ｅ父鏍囧織
						tempExecuteContentUser
								.setReason(tawwpExecuteContentUser.getReason());
						// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tempExecuteContentUser);
					} else {

						tawwpExecuteContentUser = new TawwpExecuteContentUser();
						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
						tawwpExecuteContentUser
								.setStartDate(tawwpExecuteContent
										.getStartDate()); // 璁″垝寮�濮嬫椂闂�
						tawwpExecuteContentUser.setEndDate(tawwpExecuteContent
								.getEndDate()); // 璁″垝缁撴潫鏃堕棿
						tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
						tawwpExecuteContentUser.setFormId(tawwpExecuteContent
								.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
						tawwpExecuteContentUser
								.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
						tawwpExecuteContentUser.setStubUser("");

						// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tawwpExecuteContentUser);
					}
					tawwpExecuteContent.setNormalFlag(tawwpExecuteContentUser
							.getNormalFlag());// 姝ｅ父鏍囧織
					tawwpExecuteContent.setContent(tawwpExecuteContentUser
							.getContent());
					tawwpExecuteContent.setRemark(tawwpExecuteContentUser
							.getRemark());
					tawwpExecuteContent.setReason(tawwpExecuteContentUser
							.getReason());
				}

				// --------------------------------------------------------------

				// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
				if (null == tawwpExecuteContent.getFormDataId()
						|| "".equals(tawwpExecuteContent.getFormDataId())) {
					tawwpExecuteContent.setFormDataId(tawwpExecuteContentUser
							.getFormDataId());
				}

				// 淇敼濉啓鏍囧織()
				if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
					// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
					tawwpExecuteContent.setExecuteFlag(executeFlag);
				} else if (!executeFlag.equals(tawwpExecuteContent
						.getExecuteFlag())) {
					if ("2".equals(executeFlag)) {
						/*
						 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
						 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
						 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
						 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
						 */
						tawwpExecuteContent.setExecuteFlag(executeFlag);
					}
				}

				// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
				if (!"".equals(tawwpExecuteContent.getCruser())) {
					if (("," + tawwpExecuteContent.getCruser() + ",")
							.indexOf("," + _userId + ",") < 0) {
						// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
						tawwpExecuteContent.setCruser(tawwpExecuteContent
								.getCruser()
								+ "," + tawwpExecuteContentUser.getCruser());
					}
				} else {
					// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
					tawwpExecuteContent.setCruser(tawwpExecuteContentUser
							.getCruser());
				}

				// --------------------------------------------------------------

				// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
				tawwpExecuteContent.setCrtime(tawwpExecuteContentUser
						.getCrtime()); // 瀹炶鏃堕棿edit by denden 璐靛窞鏈湴
				System.out.println("@@@@@@@@@@@@@@@@@ read input Quality!");
				if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
						&& "1".equals(tawwpExecuteContent.getFileFlag())) {

					tawwpExecuteContent.setQualityCheckFlag("0");
				}
				tawwpExecuteContentDao.saveExecuteContent(tawwpExecuteContent);
			}

		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭鎵归噺淇濆瓨鍑虹幇寮傚父");
		} finally {
			tempVar = null;
		}
	}

	/**
	 * 涓氬姟閫昏緫:鍗曠嫭濉啓鏃ュ父鎵ц浣滀笟璁″垝鍐呭(FILL-EXECUTE-CONTENT-005)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _subUser
	 *            String 浠ｇ悊鐢ㄦ埛鐧诲綍鍚�
	 * @param _content
	 *            String 濉啓鐨勬墽琛屽唴瀹�
	 * @param _remark
	 *            String 澶囨敞淇℃伅
	 * @param _formDataId
	 *            String 闄勫姞琛ㄥ崟淇℃伅
	 * @param _eligibility
	 *            String 鍚堟牸鏍囧織
	 * @param _executeContentUserId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 * @param _executeContentId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return String 鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 */
	public String addExecuteContentUserSave(String _userId, String _subUser,
			String _writeDate, String _content, String _remark,
			String _formDataId, String _eligibility,
			String _executeContentUserId, String _executeContentId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		try {
			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);
			// 鑾峰彇褰撳墠鏃堕棿
			String executeTime = TawwpUtil.getCurrentDateTime();
			// 瀹氫箟瓒呮椂鏍囧織
			String executeFlag = "";
			// 鍒ゆ柇濉啓鏄惁瓒呮椂
			if (this
					.judgeTimeOut(executeTime, tawwpExecuteContent.getEndDate())) {
				executeFlag = "1"; // 鎸夋椂
			} else {
				executeFlag = "2"; // 瓒呮椂
			}

			// --------------------------------------------------------------

			if (_executeContentUserId != null
					&& !"".equals(_executeContentUserId)) {

				this.editExecuteContentUserSave(_userId, _subUser, _writeDate,
						_content, _remark, _formDataId, _eligibility,
						_executeContentUserId);
			} else {

				// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
				tawwpExecuteContentUser = new TawwpExecuteContentUser(
						tawwpExecuteContent.getStartDate(), tawwpExecuteContent
								.getEndDate(), executeTime, _writeDate,
						_userId, _subUser, _content, _remark, _eligibility,
						executeFlag, tawwpExecuteContent.getFormId(),
						_formDataId, tawwpExecuteContent, "1", "");

				// 封装执行作业计划执行内容(单一用户)对象
				// tawwpExecuteContentUser = new TawwpExecuteContentUser(
				// tawwpExecuteContent.getStartDate(), tawwpExecuteContent
				// .getEndDate(), executeTime, _writeDate,
				// _userId, _subUser, _content, _remark, _eligibility,
				// executeFlag, tawwpExecuteContent.getFormId(),
				// _formDataId, tawwpExecuteContent, "1");

				// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
				tawwpExecuteContentUserDao
						.saveExecuteContentUser(tawwpExecuteContentUser);

				_executeContentUserId = tawwpExecuteContentUser.getId();
			}

			// --------------------------------------------------------------

			if (tawwpExecuteContentUser == null) {
				tawwpExecuteContent.setContent(_content);
			} else {
				tawwpExecuteContent.setContent(tawwpExecuteContentUser
						.getContent());
				// added by lijia 2005-11-29 鏇存柊澶囨敞瀛楁
				tawwpExecuteContent.setRemark(tawwpExecuteContentUser
						.getRemark());
			}

			// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
			if ("".equals(tawwpExecuteContent.getFormDataId())) {
				tawwpExecuteContent.setFormDataId(_formDataId);
			}
			// 淇敼濉啓鏍囧織()
			if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
				// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
				tawwpExecuteContent.setExecuteFlag(executeFlag);
			} else if (!executeFlag
					.equals(tawwpExecuteContent.getExecuteFlag())) {
				if ("2".equals(executeFlag)) {
					/*
					 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
					 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
					 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 */
					tawwpExecuteContent.setExecuteFlag(executeFlag);
				}
			}

			// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
			if (!"".equals(tawwpExecuteContent.getCruser())) {
				if (("," + tawwpExecuteContent.getCruser() + ",").indexOf(","
						+ _userId + ",") < 0) {
					// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
					tawwpExecuteContent.setCruser(tawwpExecuteContent
							.getCruser()
							+ "," + _userId);
				}
			} else {
				// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
				tawwpExecuteContent.setCruser(_userId);
			}

			tawwpExecuteContent.setCrtime(executeTime);

			// --------------------------------------------------------------

			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
			System.out.println("@@@@@@@@@@@@@@@@@ read input Quality!");
			if (!tawwpExecuteContent.getExecuteFlag().equals("0")
					&& tawwpExecuteContent.getFileFlag().equals("1")) {
				System.out.println("@@@@@@@@@@@@@@@@@ input Quality!");
				tawwpExecuteContent.setQualityCheckFlag("0");
			}
			tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);

			// 杩斿洖鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
			return (_executeContentUserId);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭淇濆瓨鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓氬姟閫昏緫:鍗曠嫭濉啓鏃ュ父鎵ц浣滀笟璁″垝鍐呭(FILL-EXECUTE-CONTENT-005)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _subUser
	 *            String 浠ｇ悊鐢ㄦ埛鐧诲綍鍚�
	 * @param _content
	 *            String 濉啓鐨勬墽琛屽唴瀹�
	 * @param _remark
	 *            String 澶囨敞淇℃伅
	 * @param _formDataId
	 *            String 闄勫姞琛ㄥ崟淇℃伅
	 * @param _eligibility
	 *            String 鍚堟牸鏍囧織
	 * @param _executeContentUserId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 * @param _executeContentId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return String 鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 */
	public String addExecuteContentUserSaveAs(String _userId, String _subUser,
			String _writeDate, String _content, String _remark,
			String _formDataId, String _eligibility,
			String _executeContentUserId, String _executeContentId,
			String _normalFlag, String _reason) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		try {
			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);
			// 鑾峰彇褰撳墠鏃堕棿
			String executeTime = TawwpUtil.getCurrentDateTime();
			// 瀹氫箟瓒呮椂鏍囧織
			String executeFlag = "";
			// 鍒ゆ柇濉啓鏄惁瓒呮椂
			if (this
					.judgeTimeOut(executeTime, tawwpExecuteContent.getEndDate())) {
				executeFlag = "1"; // 鎸夋椂
			} else {
				executeFlag = "2"; // 瓒呮椂
			}

			// --------------------------------------------------------------

			if (_executeContentUserId != null
					&& !"".equals(_executeContentUserId)) {

				this.editExecuteContentUserSave(_userId, _subUser, _writeDate,
						_content, _remark, _formDataId, _eligibility,
						_executeContentUserId);
			} else {

				// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
				tawwpExecuteContentUser = new TawwpExecuteContentUser(
						tawwpExecuteContent.getStartDate(), tawwpExecuteContent
								.getEndDate(), executeTime, _writeDate,
						_userId, _subUser, _content, _remark, _eligibility,
						executeFlag, tawwpExecuteContent.getFormId(),
						_formDataId, tawwpExecuteContent, _normalFlag, _reason);

				// 封装执行作业计划执行内容(单一用户)对象
				// tawwpExecuteContentUser = new TawwpExecuteContentUser(
				// tawwpExecuteContent.getStartDate(), tawwpExecuteContent
				// .getEndDate(), executeTime, _writeDate,
				// _userId, _subUser, _content, _remark, _eligibility,
				// executeFlag, tawwpExecuteContent.getFormId(),
				// _formDataId, tawwpExecuteContent, _normalFlag);

				// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)

				// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)

				tawwpExecuteContentUserDao
						.saveExecuteContentUser(tawwpExecuteContentUser);
				_executeContentUserId = tawwpExecuteContentUser.getId();
			}

			// --------------------------------------------------------------

			if (tawwpExecuteContentUser == null) {
				tawwpExecuteContent.setContent(_content);
			} else {
				tawwpExecuteContent.setContent(tawwpExecuteContentUser
						.getContent());
				// added by lijia 2005-11-29 鏇存柊澶囨敞瀛楁
				tawwpExecuteContent.setRemark(tawwpExecuteContentUser
						.getRemark());
			}

			// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
			if ("".equals(tawwpExecuteContent.getFormDataId())) {
				tawwpExecuteContent.setFormDataId(_formDataId);
			}
			// 淇敼濉啓鏍囧織()
			if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
				// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
				tawwpExecuteContent.setExecuteFlag(executeFlag);
			} else if (!executeFlag
					.equals(tawwpExecuteContent.getExecuteFlag())) {
				if ("2".equals(executeFlag)) {
					/*
					 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
					 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
					 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 */
					tawwpExecuteContent.setExecuteFlag(executeFlag);
				}
			}

			// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
			if (!"".equals(tawwpExecuteContent.getCruser())) {
				if (("," + tawwpExecuteContent.getCruser() + ",").indexOf(","
						+ _userId + ",") < 0) {
					// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
					tawwpExecuteContent.setCruser(tawwpExecuteContent
							.getCruser()
							+ "," + _userId);
				}
			} else {
				// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
				tawwpExecuteContent.setCruser(_userId);
			}

			tawwpExecuteContent.setCrtime(executeTime);
			tawwpExecuteContent.setReason(_reason);
			// add by gongyufeng 娣诲姞涓�涓瓧娈�
			System.out.println("@@@@@@@@@@@@@@@@@ read input Quality!");
			if (!tawwpExecuteContent.getExecuteFlag().equals("0")
					&& tawwpExecuteContent.getFileFlag().equals("1")) {
				System.out.println("@@@@@@@@@@@@@@@@@ input Quality!");
				tawwpExecuteContent.setQualityCheckFlag("0");
			}
			tawwpExecuteContent.setNormalFlag(_normalFlag);

			// --------------------------------------------------------------

			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
			tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);

			// 杩斿洖鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
			return (_executeContentUserId);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭淇濆瓨鍑虹幇寮傚父");
		}
	}

	/**
	 * 
	 * 涓氬姟閫昏緫:鎵归噺濉啓鍊肩彮鎵ц浣滀笟璁″垝鍐呭_淇濆瓨(FILL-EXECUTE-CONTENT-003)
	 * 
	 * @param _executeContentUserHash
	 *            Hashtable 鎵ц(鍐呭)淇℃伅闆嗗悎
	 * @param _dutyStartTime
	 *            String 鐝寮�濮嬫椂闂�
	 * @param _dutyEndTime
	 *            String 鐝缁撴潫鏃堕棿
	 * @param _userId
	 *            String 褰撳墠鐢ㄦ埛鐧诲綍鍚�
	 * @param _executeType
	 *            String 鎵ц绫诲瀷
	 *            (key鍊间负executeContentId,value涓烘湭澶勭悊鐨別xecuteContentUser瀵硅薄)
	 * @throws Exception
	 *             寮傚父
	 */
	public void addDutyExecuteContentUsersSave(
			Hashtable _executeContentUserHash, String _dutyStartTime,
			String _dutyEndTime, String _userId, String _executeType)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();

		// 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteContentUser tempExecuteContentUser = null;
		// 鎵ц鍐呭(鏁翠綋)缂栧彿
		String executeContentId = "";
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		// 鑾峰彇鎵ц鍐呭(鏁翠綋)缂栧彿闆嗗悎
		Enumeration tempVar = _executeContentUserHash.keys();

		try {
			Iterator tawwpExecuteContentUsers = null;
			while (tempVar.hasMoreElements()) {
				Object obj = tempVar.nextElement();
				if (obj != null) {

					// 鑾峰彇hashtable鐨凨ey鍊�
					executeContentId = (String) obj;

					// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
					tawwpExecuteContent = tawwpExecuteContentDao
							.loadExecuteContent(executeContentId);
					// 鑾峰彇褰撳墠鏃堕棿
					String executeTime = TawwpUtil.getCurrentDateTime();
					// 瀹氫箟瓒呮椂鏍囧織
					String executeFlag = "";
					// 鍒ゆ柇濉啓鏄惁瓒呮椂
					if (this.judgeTimeOut(executeTime, _dutyEndTime)) {
						executeFlag = "1"; // 鎸夋椂
					} else {
						executeFlag = "2"; // 瓒呮椂
					}

					// --------------------------------------------------------------

					// 濡傛灉澶氫汉濉啓澶氫唤
					if ("1".equals(_executeType)) {
						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
						tempExecuteContentUser = tawwpExecuteContentUserDao
								.filterTawwpExecuteContentUser(_userId,
										tawwpExecuteContent);
						// 濡傛灉瀵硅薄涓嶄负绌�
						if (tempExecuteContentUser != null) {

							// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
							tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
									.get(executeContentId);

							tempExecuteContentUser
									.setContent(tawwpExecuteContentUser
											.getContent()); // 鎵ц鍐呭
							tempExecuteContentUser
									.setRemark(tawwpExecuteContentUser
											.getRemark()); // 澶囨敞淇℃伅
							tempExecuteContentUser
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							tempExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tempExecuteContentUser
									.setNormalFlag(tawwpExecuteContentUser
											.getNormalFlag());// 姝ｅ父鏍囧織
							tempExecuteContentUser
									.setReason(tawwpExecuteContentUser
											.getReason());
							// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.updateExecuteContentUser(tempExecuteContentUser);
						} else {

							tawwpExecuteContentUser = new TawwpExecuteContentUser();
							// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
							tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
									.get(executeContentId);

							// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
							tawwpExecuteContentUser
									.setStartDate(_dutyStartTime); // 璁″垝寮�濮嬫椂闂�
							tawwpExecuteContentUser.setEndDate(_dutyEndTime); // 璁″垝缁撴潫鏃堕棿
							tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
							tawwpExecuteContentUser
									.setFormId(tawwpExecuteContent.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
							tawwpExecuteContentUser
									.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
							tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
							tawwpExecuteContentUser.setStubUser("");

							// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.saveExecuteContentUser(tawwpExecuteContentUser);
						}

						// 澶氫汉濉啓涓�浠�
					} else {

						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						if (tawwpExecuteContentUser.getId() != null
								&& !"".equals(tawwpExecuteContentUser.getId())) {

							tempExecuteContentUser = tawwpExecuteContentUserDao
									.loadExecuteContentUser(tawwpExecuteContentUser
											.getId());

							tempExecuteContentUser
									.setContent(tawwpExecuteContentUser
											.getContent()); // 鎵ц鍐呭
							tempExecuteContentUser
									.setRemark(tawwpExecuteContentUser
											.getRemark()); // 澶囨敞淇℃伅
							tempExecuteContentUser
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							tempExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tempExecuteContentUser
									.setNormalFlag(tawwpExecuteContentUser
											.getNormalFlag());// 姝ｅ父鏍囧織
							tempExecuteContentUser
									.setReason(tawwpExecuteContentUser
											.getReason());
							// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.updateExecuteContentUser(tempExecuteContentUser);
						} else {

							tempExecuteContentUser = new TawwpExecuteContentUser();
							// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
							tempExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
									.get(executeContentId);

							// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
							tawwpExecuteContentUser
									.setStartDate(tawwpExecuteContent
											.getStartDate()); // 璁″垝寮�濮嬫椂闂�
							tawwpExecuteContentUser
									.setEndDate(tawwpExecuteContent
											.getEndDate()); // 璁″垝缁撴潫鏃堕棿
							tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
							tawwpExecuteContentUser
									.setFormId(tawwpExecuteContent.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
							tawwpExecuteContentUser
									.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
							tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
							tawwpExecuteContentUser.setStubUser("");

							// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.saveExecuteContentUser(tawwpExecuteContentUser);
						}

						tawwpExecuteContent.setContent(tawwpExecuteContentUser
								.getContent());
						tawwpExecuteContent.setRemark(tawwpExecuteContentUser
								.getRemark());
						tawwpExecuteContent
								.setNormalFlag(tawwpExecuteContentUser
										.getNormalFlag());
						tawwpExecuteContent.setReason(tawwpExecuteContentUser
								.getReason());

					}

					// --------------------------------------------------------------

					// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
					if ("".equals(tawwpExecuteContent.getFormDataId())) {
						tawwpExecuteContent
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId());
					}

					// 淇敼濉啓鏍囧織()
					if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
						// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
						tawwpExecuteContent.setExecuteFlag(executeFlag);
					} else if (!executeFlag.equals(tawwpExecuteContent
							.getExecuteFlag())) {
						if ("2".equals(executeFlag)) {
							/*
							 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
							 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
							 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
							 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
							 */
							tawwpExecuteContent.setExecuteFlag(executeFlag);
						}
					}

					// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
					if (!"".equals(tawwpExecuteContent.getCruser())) {
						if (("," + tawwpExecuteContent.getCruser() + ",")
								.indexOf(","
										+ tawwpExecuteContentUser.getCruser()
										+ ",") < 0) {

							// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
							tawwpExecuteContent
									.setCruser(tawwpExecuteContent.getCruser()
											+ ","
											+ tawwpExecuteContentUser
													.getCruser());
						}
					} else {
						// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
						tawwpExecuteContent.setCruser(tawwpExecuteContentUser
								.getCruser());
					}

					// --------------------------------------------------------------

					// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
					System.out.println("@@@@@@@@@@@@@@@@@ read input Quality!");
					if (!tawwpExecuteContent.getExecuteFlag().equals("0")
							&& tawwpExecuteContent.getFileFlag().equals("1")) {
						System.out.println("@@@@@@@@@@@@@@@@@ input Quality!");
						tawwpExecuteContent.setQualityCheckFlag("0");
					}
					tawwpExecuteContentDao
							.updateExecuteContent(tawwpExecuteContent);

				}
			}
		}

		catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭鎵归噺淇濆瓨鍑虹幇寮傚父");
		} finally {
			tempVar = null;
			// tawwpExecuteContentUsers = null;
		}

	}

	/**
	 * 鍒ゆ柇浣滀笟璁″垝鎵ц鏄惁瓒呮椂
	 * 
	 * @param _executeTime
	 *            String 鎵ц(淇濆瓨)鏃堕棿
	 * @param _StartDate
	 *            String 璁″垝寮�濮嬫椂闂�
	 * @param _EndDate
	 *            String 璁″垝缁撴潫鏃堕棿
	 * @throws Exception
	 *             寮傚父
	 * @return boolean 杩斿洖鏄惁瓒呮椂鏍囧織
	 */
	public boolean judgeTimeOut(String _executeTime, String _EndDate)
			throws Exception {
		// 鍒濆鍖栨暟鎹�,鏄惁瓒呮椂鏍囧織
		boolean flag = false;
		// 澧炲姞寤惰繜鏃堕棿锛圵orkplanService.xml鏂囦欢涓殑statDay灞炴�х‘瀹氱敤鎴峰彲浠ュ欢杩熷嚑澶╂墽琛岋紝鑰屼笉绠楄秴鏃讹級
		int lateDay = StaticMethod.nullObject2int(WorkplanMgrLocator
				.getAttributes().getStatday());
		if (!_EndDate.equals("")) {
			String ecexuteDate = _EndDate.substring(0, 10);
			String endDate = StaticMethod.getDateString(ecexuteDate, lateDay);
			_EndDate = endDate + _EndDate.substring(10, _EndDate.length());
			// 濡傛灉鍦ㄦ墽琛屾椂闂磋寖鍥翠互鍐�,鍒欏皢鏍囧織浣嶇疆涓簍rue
			if (_executeTime.compareTo(_EndDate) <= 0) {
				flag = true;
			}
		}
		// 杩斿洖鏄惁瓒呮椂鏍囧織
		return flag;
	}

	/**
	 * 涓氬姟閫昏緫:濉啓鎵ц浣滀笟璁″垝鍛ㄦ姤
	 * 
	 * @param _deptId
	 *            String 閮ㄩ棬缂栧彿
	 * @param _startDate
	 *            String 鍛ㄦ姤寮�濮嬫椂闂�
	 * @param _endDate
	 *            String 鍛ㄦ姤缁撴潫鏃堕棿
	 * @param _content
	 *            String 姹囨姤淇℃伅
	 * @param _reportUser
	 *            String 姹囨姤浜�
	 * @param _reportFlag
	 *            String 娉ㄦ剰鏍囧織
	 * @param _remark
	 *            String 澶囨敞
	 * @param _advice
	 *            String 寤鸿
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @throws Exception
	 *             寮傚父
	 */
	public void addExecuteWeekReport(String _deptId, String _startDate,
			String _endDate, String _content, String _reportUser,
			String _reportFlag, String _remark, String _advice,
			String _monthPlanId) throws Exception {
		// 璋冪敤濉啓鎵ц浣滀笟璁″垝鍛ㄦ姤(鎴栨湀鎶�)鐨勭鏈夋柟娉�
		this.addExecuteReport(_deptId, "0", _startDate, _endDate, _content,
				_reportUser, _reportFlag, _remark, _advice, _monthPlanId);
	}

	/**
	 * 涓氬姟閫昏緫:濉啓鎵ц浣滀笟璁″垝鏈堟姤
	 * 
	 * @param _deptId
	 *            String 閮ㄩ棬缂栧彿
	 * @param _startDate
	 *            String 鏈堟姤寮�濮嬫椂闂�
	 * @param _endDate
	 *            String 鏈堟姤缁撴潫鏃堕棿
	 * @param _content
	 *            String 姹囨姤淇℃伅
	 * @param _reportUser
	 *            String 姹囨姤浜�
	 * @param _reportFlag
	 *            String 娉ㄦ剰鏍囧織
	 * @param _remark
	 *            String 澶囨敞
	 * @param _advice
	 *            String 寤鸿
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @throws Exception
	 *             寮傚父
	 */
	public void addExecuteMonthReport(String _deptId, String _startDate,
			String _endDate, String _content, String _reportUser,
			String _reportFlag, String _remark, String _advice,
			String _monthPlanId) throws Exception {
		// 璋冪敤濉啓鎵ц浣滀笟璁″垝鍛ㄦ姤(鎴栨湀鎶�)鐨勭鏈夋柟娉�
		this.addExecuteReport(_deptId, "1", _startDate, _endDate, _content,
				_reportUser, _reportFlag, _remark, _advice, _monthPlanId);
	}

	/**
	 * 濉啓鎵ц浣滀笟璁″垝鍛ㄦ姤(鎴栨湀鎶�)
	 * 
	 * @param _deptId
	 *            String 閮ㄩ棬缂栧彿
	 * @param _reportType
	 *            String 绫诲瀷(0 鍛ㄦ姤,1 鏈堟姤)
	 * @param _startDate
	 *            String 姹囨姤寮�濮嬫椂闂�
	 * @param _endDate
	 *            String 姹囨姤缁撴潫鏃堕棿
	 * @param _content
	 *            String 姹囨姤淇℃伅
	 * @param _reportUser
	 *            String 姹囨姤浜�
	 * @param _reportFlag
	 *            String 娉ㄦ剰鏍囧織
	 * @param _remark
	 *            String 澶囨敞
	 * @param _advice
	 *            String 寤鸿
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @throws Exception
	 *             寮傚父
	 */
	private void addExecuteReport(String _deptId, String _reportType,
			String _startDate, String _endDate, String _content,
			String _reportUser, String _reportFlag, String _remark,
			String _advice, String _monthPlanId) throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteReportDAO tawwpExecuteReportDAO = new
		// TawwpExecuteReportDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// 鏈堝害浣滀笟璁″垝瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;

		try {

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);

			// 灏佽鍛ㄦ姤(鎴栨湀鎶�)淇℃伅瀵硅薄
			TawwpExecuteReport tawwpExecuteReport = new TawwpExecuteReport(
					_deptId, _reportType, _startDate, _endDate, _content,
					_reportUser, TawwpUtil.getCurrentDateTime(), _reportFlag,
					_remark, _advice, tawwpMonthPlan);

			// 淇濆瓨鍛ㄦ姤(鎴栨湀鎶�)淇℃伅
			tawwpExecuteReportDao.saveExecuteReport(tawwpExecuteReport);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鍛�(鏈�)鎶ヤ繚瀛樺嚭鐜板紓甯�");
		}
	}

	/**
	 * 涓氬姟閫昏緫:鎵ц浣滀笟璁″垝鎻愪氦鑰冩牳(PUTIN-EXECUTE-CONTENT-ASSESS-001)
	 * 
	 * @param _deptId
	 *            String 鑰冩牳閮ㄩ棬缂栧彿
	 * @param _checkUser
	 *            String 鑰冩牳浜虹櫥褰曞悕
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @param _flowSerial
	 *            String 娴佺▼缂栧彿
	 * @param _stepSerial
	 *            String 姝ラ缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return boolean 鎻愪氦鑰冩牳鏄惁鏁版嵁瀹屾垚
	 */
	public boolean addExecuteAssess(String _deptId, String _checkUser,
			String _monthPlanId, String _flowSerial, String _stepSerial)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteAssessDAO tawwpExecuteAssessDAO = new
		// TawwpExecuteAssessDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpMonthExecuteUserDAO tawwpMonthExecuteUserDAO = new
		// TawwpMonthExecuteUserDAO();
		// 鏈堝害浣滀笟璁″垝瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;

		boolean flag = true;

		try {

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			// 淇敼鏈堝害浣滀笟璁″垝鎵ц鐘舵�佷负"鑰冩牳涓�"
			tawwpMonthPlan.setExecuteState("3");
			// 灏佽鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅瀵硅薄
			TawwpExecuteAssess tawwpExecuteAssess = new TawwpExecuteAssess(
					_deptId, _checkUser, "", "0", TawwpUtil
							.getCurrentDateTime(), "", _flowSerial,
					_stepSerial, tawwpMonthPlan, null);

			// 鏇存柊鏈堝害浣滀笟璁″垝鐘舵��
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);
			// 淇濆瓨浣滀笟璁″垝鑰冩牳淇℃伅
			tawwpExecuteAssessDao.saveExecuteAssess(tawwpExecuteAssess);
			// 鎵归噺鏇存柊鎵ц鍐呭鎵ц浜哄璞�,鐘舵�佷负"鑰冩牳涓�"
			tawwpMonthExecuteUserDao.updateState(tawwpMonthPlan, "3");

			return flag;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鑰冩牳淇℃伅淇濆瓨鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓氬姟閫昏緫:鑰冩牳鎵ц浣滀笟璁″垝_閫氳繃(ASSESS-EXECUTE-PLAN-002)
	 * 
	 * @param _exexuteAssessId
	 *            String 鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅缂栧彿
	 * @param _content
	 *            String 鑰冩牳淇℃伅
	 * @param _checkUser
	 *            String 涓嬩竴姝ラ瀹℃壒浜猴紝濡傛灉姝ラ涓嶅瓨鍦ㄥ彲浠ヤ负绌�
	 * @param _currentCheckUser
	 *            String 鑰冩牳浜�
	 * @throws Exception
	 *             寮傚父
	 */
	public void passExecuteAssess(String _exexuteAssessId, String _checkUser,
			String _content, String _currentCheckUser) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteAssessDAO tawwpExecuteAssessDAO = new
		// TawwpExecuteAssessDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpMonthExecuteUserDAO tawwpMonthExecuteUserDAO = new
		// TawwpMonthExecuteUserDAO();

		// 鏈堝害浣滀笟璁″垝瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		// 鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅瀵硅薄
		TawwpExecuteAssess tawwpExecuteAssess = null;
		// 鏈堝害浣滀笟璁″垝缂栧彿
		String monthPlanId = "";

		TawwpFlowManage tawwpFlowManage = null;
		Step step = null;

		try {

			// 鑾峰彇鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅瀵硅薄
			tawwpExecuteAssess = tawwpExecuteAssessDao
					.loadExecuteAssess(_exexuteAssessId);
			// 灏佽鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅瀵硅薄
			tawwpExecuteAssess.setState("1");
			tawwpExecuteAssess.setContent(_content);
			tawwpExecuteAssess.setCheckUser(_currentCheckUser);
			tawwpExecuteAssess.setChecktime(TawwpUtil.getCurrentDateTime());
			// 鏇存柊浣滀笟璁″垝鑰冩牳淇℃伅
			tawwpExecuteAssessDao.updateExecuteAssess(tawwpExecuteAssess);

			// -----------------------------------------------------------------------

			// 鑾峰彇娴佺▼绠＄悊瀵硅薄
			tawwpFlowManage = TawwpFlowManage.getInstance();
			// 鑾峰彇涓嬩竴姝ラ瀵硅薄
			step = tawwpFlowManage.getExecuteFlowStep(tawwpExecuteAssess
					.getFlowSerial(), tawwpExecuteAssess.getStepSerial(),
					tawwpExecuteAssess.getDeptId());
			if (step == null) {

				// 鑾峰彇鏈堝害浣滀笟璁″垝缂栧彿
				monthPlanId = (tawwpExecuteAssess.getTawwpMonthPlan()).getId();
				// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(monthPlanId);
				// 淇敼鏈堝害浣滀笟璁″垝鎵ц鐘舵�佷负"褰掓。"
				tawwpMonthPlan.setExecuteState("1");

				// 鏇存柊鏈堝害浣滀笟璁″垝鎵ц鐘舵��
				tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);
				// 鎵归噺鏇存柊鎵ц鍐呭鎵ц浜哄璞�,涓庢湀搴︿綔涓氳鍒掓墽琛岀姸鎬佷竴鑷�
				tawwpMonthExecuteUserDao.updateState(tawwpMonthPlan, "1");
			} else {
				// 濡傛灉姝ラ瀵硅薄涓嶄负绌�
				if (_checkUser != null) {
					// 鎵ц浜烘槸涓嶄负绌�
					tawwpMonthPlan = tawwpExecuteAssess.getTawwpMonthPlan();

					this.addExecuteAssess(tawwpMonthPlan.getDeptId(),
							_checkUser, tawwpMonthPlan.getId(), String
									.valueOf(step.getFlowSerial()), step
									.getSerial()); // 鍒涘缓涓嬩竴姝ラ鐨勫鎵逛俊鎭�
				} else {
					// 寮傚父
					throw new TawwpException("鎵ц浣滀笟璁″垝鑰冩牳娴佺▼瀛樺湪,浣嗘病鏈夋寚瀹氳�冩牳浜�");
				}
			}

			// ----------------------------------------------------------------------

		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鑰冩牳淇℃伅淇敼鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓氬姟閫昏緫:鑰冩牳鎵ц浣滀笟璁″垝_椹冲洖(ASSESS-EXECUTE-PLAN-003)
	 * 
	 * @param _exexuteAssessId
	 *            String 鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅缂栧彿
	 * @param _content
	 *            String 鑰冩牳淇℃伅
	 * @param _checkUser
	 *            String 鑰冩牳浜�
	 * @throws Exception
	 *             寮傚父
	 */
	public void rejectExecuteAssess(String _exexuteAssessId, String _content,
			String _checkUser) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteAssessDAO tawwpExecuteAssessDAO = new
		// TawwpExecuteAssessDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpMonthExecuteUserDAO tawwpMonthExecuteUserDAO = new
		// TawwpMonthExecuteUserDAO();
		// 鏈堝害浣滀笟璁″垝瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		// 鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅瀵硅薄
		TawwpExecuteAssess tawwpExecuteAssess = null;
		// 鏈堝害浣滀笟璁″垝缂栧彿
		String monthPlanId = "";

		try {

			// 鑾峰彇鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅瀵硅薄
			tawwpExecuteAssess = tawwpExecuteAssessDao
					.loadExecuteAssess(_exexuteAssessId);

			// 灏佽鏈堝害浣滀笟璁″垝鑰冩牳淇℃伅瀵硅薄
			tawwpExecuteAssess.setState("2");
			tawwpExecuteAssess.setContent(_content);
			tawwpExecuteAssess.setCheckUser(_checkUser);
			tawwpExecuteAssess.setChecktime(TawwpUtil.getCurrentDateTime());

			tawwpExecuteAssessDao.updateExecuteAssess(tawwpExecuteAssess);

			// 鑾峰彇鏈堝害浣滀笟璁″垝缂栧彿
			monthPlanId = (tawwpExecuteAssess.getTawwpMonthPlan()).getId();
			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(monthPlanId);

			// 鏇存柊鏈堝害浣滀笟璁″垝鎵ц鐘舵��
			tawwpMonthPlan.setExecuteState("2");
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

			// 鏇存柊浣滀笟璁″垝鑰冩牳淇℃伅
			tawwpExecuteAssessDao.updateExecuteAssess(tawwpExecuteAssess);
			// 鎵归噺鏇存柊鎵ц鍐呭鎵ц浜哄璞�,涓庢湀搴︿綔涓氳鍒掓墽琛岀姸鎬佷竴鑷�
			tawwpMonthExecuteUserDao.updateState(tawwpMonthPlan, "2");
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鑰冩牳淇℃伅淇敼鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓氬姟閫昏緫:娴忚鎵ц浣滀笟璁″垝_璇︾粏淇℃伅(鑰冩牳浜�)(BROWSE-MONTHLY-PLAN-001)
	 * 
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpMonthPlanVO 鏈堝害浣滀笟璁″垝VO瀵硅薄
	 */
	public TawwpMonthPlanVO viewExecutePlan(String _monthPlanId)
			throws Exception {

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpExecuteAssessVO tawwpExecuteAssessVO = null;

		List list = null;
		Step step = null;

		tawwpMonthPlanVO = this.getMontPlan(_monthPlanId);

		list = tawwpMonthPlanVO.getExecuteAssessVOList();

		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteAssessVO = (TawwpExecuteAssessVO) list.get(i);
			if (tawwpExecuteAssessVO.getState().equals("0")) {
				list.remove(tawwpExecuteAssessVO);
			}
		}

		// 鑾峰彇娴佺▼绠＄悊瀵硅薄
		if (tawwpMonthPlanVO.getExecuteState().equals("4")
				|| tawwpMonthPlanVO.getExecuteState().equals("2")) {

			// 鑾峰彇褰撳墠鏈堝害浣滀笟璁″垝鐨勫鎵规祦绋嬩俊鎭�
			TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance(); // 鑾峰彇娴佺▼绠＄悊瀵硅薄
			step = tawwpFlowManage.getFristExecuteFlowStep(tawwpMonthPlanVO
					.getDeptId()); // 鑾峰彇绗竴涓楠や俊鎭�

			if (step != null) {
				// 瀛樺湪娴佺▼
				tawwpMonthPlanVO.setStep(step);
			}
		}

		return tawwpMonthPlanVO;
	}

	// 四川版本上的方法：viewExecutePlanCheck
	public TawwpMonthPlanVO viewExecutePlanCheck(String _monthPlanId)
			throws Exception {

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpExecuteAssessVO tawwpExecuteAssessVO = null;

		List list = null;
		Step step = null;

		tawwpMonthPlanVO = this.getMontPlanCheck(_monthPlanId);

		list = tawwpMonthPlanVO.getExecuteAssessVOList();

		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteAssessVO = (TawwpExecuteAssessVO) list.get(i);
			if (tawwpExecuteAssessVO.getState().equals("0")) {
				list.remove(tawwpExecuteAssessVO);
			}
		}

		// 获取流程管理对象

		// 鑾峰彇娴佺▼绠＄悊瀵硅薄

		if (tawwpMonthPlanVO.getExecuteState().equals("4")
				|| tawwpMonthPlanVO.getExecuteState().equals("2")) {

			// 获取当前月度作业计划的审批流程信�?
			TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance(); // 获取流程管理对象

			step = tawwpFlowManage.getFristExecuteFlowStep(tawwpMonthPlanVO

			.getDeptId()); // 获取第一个步骤信�?

			if (step != null) {
				// 瀛樺湪娴佺▼
				tawwpMonthPlanVO.setStep(step);
			}
		}

		return tawwpMonthPlanVO;
	}

	/**
	 * 涓氬姟閫昏緫:娴忚鎵ц浣滀笟璁″垝_璇︾粏淇℃伅(鑰冩牳浜�)(BROWSE-MONTHLY-PLAN-002)
	 * 
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpMonthPlanVO 鏈堝害浣滀笟璁″垝VO瀵硅薄
	 */
	public TawwpMonthPlanVO viewExecutePlanByCheck(String _monthPlanId)
			throws Exception {

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpExecuteAssessVO tawwpExecuteAssessVO = null;
		TawwpExecuteAssessVO tempTawwpExecuteAssessVO = null;
		List list = null;
		Step step = null;

		tawwpMonthPlanVO = this.getMontPlanCheck(_monthPlanId);

		list = tawwpMonthPlanVO.getExecuteAssessVOList();

		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteAssessVO = (TawwpExecuteAssessVO) list.get(i);
			if (tempTawwpExecuteAssessVO == null) {
				tempTawwpExecuteAssessVO = tawwpExecuteAssessVO;
			} else if (tempTawwpExecuteAssessVO.getCrtime().compareTo(
					tawwpExecuteAssessVO.getCrtime()) > 0) {
				tempTawwpExecuteAssessVO = tawwpExecuteAssessVO;
			}
			if (tawwpExecuteAssessVO.getState().equals("0")) {
				list.remove(tawwpExecuteAssessVO);
			}

		}

		// 鑾峰彇娴佺▼绠＄悊瀵硅薄
		TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance();
		// 鑾峰彇涓嬩竴姝ラ淇℃伅
		step = tawwpFlowManage.getExecuteFlowStep(tempTawwpExecuteAssessVO
				.getFlowSerial(), tempTawwpExecuteAssessVO.getStepSerial(),
				tawwpMonthPlanVO.getDeptId());
		if (step != null) {
			// 瀛樺湪娴佺▼
			tawwpMonthPlanVO.setStep(step);
		}

		return tawwpMonthPlanVO;
	}

	/**
	 * 涓氬姟閫昏緫锛氭祻瑙堟墽琛屼綔涓氳鍒抇璇︾粏淇℃伅(鎵ц浜�)(BROWSE-MONTHLY-PLAN-002)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鍚�
	 * @param _deptId
	 *            String 閮ㄩ棬缂栧彿
	 * @param _roomId
	 *            String 鏈烘埧缂栧彿
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @param _stubFlag
	 *            String 浠ｇ悊鏍囧織
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpMonthPlanVO 鏈堝害浣滀笟璁″垝VO瀵硅薄
	 */
	public TawwpMonthPlanVO viewExecutePlanExecuter(String _userId,
			String _deptId, String _roomId, String _monthPlanId,
			String _stubFlag) throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO瀵硅薄
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		// 闆嗗悎瀵硅薄
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecuteContents = null;
		Hashtable monthExecuteVOHash = new Hashtable();
		Hashtable executeContentVOHash = new Hashtable();
		List monthExecuteVOList = new ArrayList();

		int dayCount = 0; // 褰撴湀澶╂暟

		try {

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 灏佽鏈堝害浣滀笟璁″垝VO瀵硅薄
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan);
			// 鑾峰彇鎵ц绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
					.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteType())));
			// 鑾峰彇閮ㄩ棬鍚嶇О
			tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
					.getDeptName(tawwpMonthPlanVO.getDeptId()));
			// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
					.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
			// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
					.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
			// 鑾峰彇鍒涘缓浜哄鍚�
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));
			// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getConstituteState())));
			// 鑾峰彇鎵ц鐘舵�佸悕绉�
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteState())));
			// 鑾峰彇鎵ц璐熻矗浜哄鍚�
			tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 鑾峰彇缃戝厓鍚嶇О
			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));

			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}
			if ("stub".equals(_stubFlag)) {
				tawwpMonthPlanVO.setStubFlag("1"); // 浠ｇ悊鏍囧織
				tawwpMonthPlanVO.setUserByStub(_userId); // 浠ｇ悊鐢ㄦ埛
			} else {
				tawwpMonthPlanVO.setStubFlag("0");
				tawwpMonthPlanVO.setUserByStub("");
			}

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭闆嗗悎
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			while (tawwpMonthExecutes.hasNext()) {

				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());

				// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
				if (this.judgeExecuter(_userId, _deptId, _roomId,
						tawwpMonthExecute)) {

					tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
					// 灏佽鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
							tawwpMonthExecute);
					// 鑾峰彇鎵ц鍛ㄦ湡
					tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
							.getCycleName(tawwpMonthExecuteVO.getCycle()));
					// 鑾峰彇鐢靛瓙琛ㄥ崟鍚嶇О
					tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpMonthExecuteVO.getFormId()));
					// 鑾峰彇鍒涘缓浜哄鍚�
					tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthExecuteVO.getCruser()));
					// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
					tawwpMonthExecuteVO
							.setNetTypeName(tawwpUtilDAO
									.getNetTypeName(tawwpMonthExecuteVO
											.getNetTypeId()));

					// 鑾峰彇鎵ц浜虹被鍨嬪悕绉�
					tawwpMonthExecuteVO.setExecuterTypeName(TawwpMonthExecuteVO
							.getExecuterTypeName(Integer
									.parseInt(tawwpMonthExecuteVO
											.getExecuterType())));
					// 濡傛灉鎵ц浜虹被鍨嬩负鐝
					if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
						// 鑾峰彇鏈烘埧鍚嶇О
						String roomNames = tawwpUtilDAO
								.getRoomNames(tawwpMonthExecuteVO.getExecuter());
						tawwpMonthExecuteVO.setExecuterName(roomNames);
						tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

						// 鑾峰彇鎵ц鐝鍚嶇О
						if (tawwpMonthExecuteVO.getExecuteDuty() != null
								&& !"".equals(tawwpMonthExecuteVO
										.getExecuteDuty())) {
							// 鑾峰彇鎵ц鐝鍚嶇О
							tawwpMonthExecuteVO
									.setExecuteDutyName(tawwpMonthExecuteVO
											.getExecuteDutyName(Integer
													.parseInt(tawwpMonthExecuteVO
															.getExecuteDuty())));
						}
					} else {
						// 鑾峰彇鎵ц浜�(鎴栭儴闂ㄣ�佸矖浣�)濮撳悕(鎴栧悕绉�)
						// 鎵ц浜虹被鍨嬩负鐢ㄦ埛
						if ("0".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getUserNames(tawwpMonthExecuteVO
											.getExecuter()));
						}
						// 鎵ц浜虹被鍨嬩负閮ㄩ棬
						if ("1".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getDeptNames(tawwpMonthExecuteVO
											.getExecuter()));
						}

						// 鎵ц浜虹被鍨嬩负宀椾綅
						if ("2".equals(tawwpMonthExecuteVO.getExecuterType())) {
							// ccccccccc
							/*
							 * tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO.getOrgPostNames(
							 * tawwpMonthExecuteVO.getExecuter()));
							 */
						}
					}

					// 鑾峰彇褰撴湀澶╂暟
					dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
							.getYearFlag(), tawwpMonthPlan.getMonthFlag());
					tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));

					// 娣诲姞鍒伴泦鍚堜腑
					monthExecuteVOList.add(tawwpMonthExecuteVO);

					// ----------------------------------------------------------------------

					// 濡傛灉鎵ц鍐呭鐨勬墽琛屼汉涓嶄负鍊肩彮浜�
					if (!"3".equals(tawwpMonthExecute.getExecuterType())) {

						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)闆嗗悎
						tawwpExecuteContents = tawwpMonthExecute
								.getTawwpExecuteContents().iterator();

						while (tawwpExecuteContents.hasNext()) {
							tawwpExecuteContentVO = new TawwpExecuteContentVO();
							// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecuteContents
									.next());

							// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
							MyBeanUtils.copyPropertiesFromDBToPage(
									tawwpExecuteContentVO, tawwpExecuteContent);
							tawwpExecuteContentVO.setUserName(tawwpUtilDAO
									.getUserNames(tawwpExecuteContentVO
											.getCruser())); // 鎵ц浜哄鍚�
							tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
									.getCycleName(tawwpExecuteContentVO
											.getCycle())); // 鍛ㄦ湡鍚嶇О
							tawwpExecuteContentVO.setFormName(tawwpUtilDAO
									.getAddonsName(tawwpExecuteContentVO
											.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
							tawwpExecuteContentVO
									.setEligibilityName(tawwpExecuteContentVO
											.getEligibilityName(tawwpExecuteContentVO
													.getEligibility())); // 鍚堟牸鏍囧織
							tawwpExecuteContentVO
									.setExecuteFlagName(tawwpExecuteContentVO
											.getExecuteFlagName(Integer
													.parseInt(tawwpExecuteContentVO
															.getExecuteFlag()))); // 濉啓鏍囧織

							// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
							executeContentVOHash.put(tawwpMonthExecuteVO
									.getId()
									+ tawwpExecuteContentVO.getStartDate()
											.substring(8, 10),
									tawwpExecuteContentVO);

						}
					}

					// ----------------------------------------------------------------------

					// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)闆嗗悎瀵硅薄,娣诲姞鍒版湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O闆嗗悎涓�
					monthExecuteVOHash.put(tawwpMonthExecuteVO,
							executeContentVOHash);
				}
			}

			// ------------------------------------------------------------------------

			/*
			 * 娣诲姞 1銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O瀵硅薄闆嗗悎 2銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹笻ashtable瀵硅薄闆嗗悎
			 * (key鍊间负鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄,value涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)Hashtable瀵硅薄,
			 * 鍏朵腑鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)Hashtable瀵硅薄鐨刱ey鍊间负鎵ц鏃ユ湡,
			 * value鍊间负鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)鐨刅O瀵硅薄) 鍒版湀搴︿綔涓氳鍒扸O瀵硅薄涓�
			 */
			tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
			tawwpMonthPlanVO.setMonthExecuteVOHash(monthExecuteVOHash);

			// 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("娴忚鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			tawwpMonthExecutes = null;
			tawwpExecuteContents = null;
			monthExecuteVOHash = null;
			executeContentVOHash = null;
			monthExecuteVOList = null;
		}
	}

	// 四川版本上的方法：viewDayExecutePlanExecuter
	public TawwpMonthPlanVO viewDayExecutePlanExecuter(String _userId,
			String _deptId, String _roomId, String _monthPlanId,
			String _stubFlag, String InspectionFlag) throws Exception {
		// 初始化数�?创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO对象
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		// 集合对象
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecuteContents = null;
		Hashtable monthExecuteVOHash = new Hashtable();
		Hashtable executeContentVOHash = new Hashtable();
		List monthExecuteVOList = new ArrayList();

		int dayCount = 0; // 当月天数

		try {

			// ------------------------------------------------------------------------

			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 封装月度作业计划VO对象
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan);
			// 获取执行类型名称
			tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
					.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteType())));
			// 获取部门名称
			tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
					.getDeptName(tawwpMonthPlanVO.getDeptId()));
			// 获取系统类型名称
			tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
					.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
			// 获取网元类型名称
			tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
					.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
			// 获取创建人姓�?
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));
			// 获取制定状态名�?
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getConstituteState())));
			// 获取执行状态名�?
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteState())));
			// 获取执行负责人姓�?
			tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 获取网元名称
			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));
			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}
			if ("stub".equals(_stubFlag)) {
				tawwpMonthPlanVO.setStubFlag("1"); // 代理标志
				tawwpMonthPlanVO.setUserByStub(_userId); // 代理用户
			} else {
				tawwpMonthPlanVO.setStubFlag("0");
				tawwpMonthPlanVO.setUserByStub("");
			}

			// ------------------------------------------------------------------------

			// 获取月度作业计划执行内容集合
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			while (tawwpMonthExecutes.hasNext()) {

				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				if (InspectionFlag.equals("Inspection")
						&& !"".equals(StaticMethod
								.null2String(tawwpMonthExecute.getCommand()))) {

					// 判断当前用户是否为指定执行内容的执行�?
					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
						// 封装月度作业计划执行内容VO对象
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthExecuteVO, tawwpMonthExecute);
						// 获取执行周期
						tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
								.getCycleName(tawwpMonthExecuteVO.getCycle()));
						// 获取电子表单名称
						tawwpMonthExecuteVO
								.setFormName(tawwpUtilDAO
										.getAddonsName(tawwpMonthExecuteVO
												.getFormId()));
						// 获取创建人姓�?
						tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
								.getUserName(tawwpMonthExecuteVO.getCruser()));
						// 获取网元类型名称
						tawwpMonthExecuteVO.setNetTypeName(tawwpUtilDAO
								.getNetTypeName(tawwpMonthExecuteVO
										.getNetTypeId()));

						// 获取执行人类型名�?
						tawwpMonthExecuteVO
								.setExecuterTypeName(TawwpMonthExecuteVO
										.getExecuterTypeName(Integer
												.parseInt(tawwpMonthExecuteVO
														.getExecuterType())));
						// 如果执行人类型为班次
						if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
							// 获取机房名称
							String roomNames = tawwpUtilDAO
									.getRoomNames(tawwpMonthExecuteVO
											.getExecuter());
							tawwpMonthExecuteVO.setExecuterName(roomNames);
							tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

							// 获取执行班次名称
							if (tawwpMonthExecuteVO.getExecuteDuty() != null
									&& !"".equals(tawwpMonthExecuteVO
											.getExecuteDuty())) {
								// 获取执行班次名称
								tawwpMonthExecuteVO
										.setExecuteDutyName(tawwpMonthExecuteVO
												.getExecuteDutyName(Integer
														.parseInt(tawwpMonthExecuteVO
																.getExecuteDuty())));
							}
						} else {
							// 获取执行�?或部门、岗�?姓名(或名�?
							// 执行人类型为用户
							if ("0".equals(tawwpMonthExecuteVO
									.getExecuterType())) {
								tawwpMonthExecuteVO
										.setExecuterName(tawwpUtilDAO
												.getUserNames(tawwpMonthExecuteVO
														.getExecuter()));
							}
							// 执行人类型为部门
							if ("1".equals(tawwpMonthExecuteVO
									.getExecuterType())) {
								tawwpMonthExecuteVO
										.setExecuterName(tawwpUtilDAO
												.getDeptNames(tawwpMonthExecuteVO
														.getExecuter()));
							}
							// 执行人类型为岗位
							if ("2".equals(tawwpMonthExecuteVO
									.getExecuterType())) {
								// ccccccccc
								/*
								 * tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO.getOrgPostNames(
								 * tawwpMonthExecuteVO.getExecuter()));
								 */
							}
						}

						// 获取当月天数
						dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
								.getYearFlag(), tawwpMonthPlan.getMonthFlag());
						tawwpMonthExecuteVO.setDayCount(String
								.valueOf(dayCount));

						// 添加到集合中
						monthExecuteVOList.add(tawwpMonthExecuteVO);

						// ----------------------------------------------------------------------

						// 如果执行内容的执行人不为值班�?
						if (!"3".equals(tawwpMonthExecute.getExecuterType())) {

							// 获取执行作业计划执行内容(整体)集合
							tawwpExecuteContents = tawwpMonthExecute
									.getTawwpExecuteContents().iterator();

							while (tawwpExecuteContents.hasNext()) {
								tawwpExecuteContentVO = new TawwpExecuteContentVO();
								// 获取执行作业计划执行内容(整体)对象
								tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecuteContents
										.next());

								// 封装执行作业计划执行内容(整体)VO对象
								MyBeanUtils.copyPropertiesFromDBToPage(
										tawwpExecuteContentVO,
										tawwpExecuteContent);
								tawwpExecuteContentVO.setUserName(tawwpUtilDAO
										.getUserNames(tawwpExecuteContentVO
												.getCruser())); // 执行人姓�?
								tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
										.getCycleName(tawwpExecuteContentVO
												.getCycle())); // 周期名称
								tawwpExecuteContentVO.setFormName(tawwpUtilDAO
										.getAddonsName(tawwpExecuteContentVO
												.getFormId())); // 附加表单名称
								tawwpExecuteContentVO
										.setEligibilityName(tawwpExecuteContentVO
												.getEligibilityName(tawwpExecuteContentVO
														.getEligibility())); // 合格标志
								tawwpExecuteContentVO
										.setExecuteFlagName(tawwpExecuteContentVO
												.getExecuteFlagName(Integer
														.parseInt(tawwpExecuteContentVO
																.getExecuteFlag()))); // 填写标志

								// 将执行作业计划执行内�?整体)VO对象添加到集合中
								executeContentVOHash.put(tawwpMonthExecuteVO
										.getId()
										+ tawwpExecuteContentVO.getStartDate()
												.substring(8, 10),
										tawwpExecuteContentVO);

							}
						}

						// ----------------------------------------------------------------------

						// 将执行作业计划执行内�?整体)集合对象,添加到月度作业计划执行内容VO集合�?
						monthExecuteVOHash.put(tawwpMonthExecuteVO,
								executeContentVOHash);
					}
				}
			}

			// ------------------------------------------------------------------------

			/*
			 * 添加 1、月度作业计划执行内容VO对象集合 2、月度作业计划执行内容Hashtable对象集合
			 * (key值为月度作业计划执行内容VO对象,value为执行作业计划执行内�?整体)Hashtable对象,
			 * 其中执行作业计划执行内容(整体)Hashtable对象的key值为执行日期,
			 * value值为执行作业计划执行内容(整体)的VO对象) 到月度作业计划VO对象�?
			 */
			if (monthExecuteVOList.size() > 0) {
				tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
				tawwpMonthPlanVO.setMonthExecuteVOHash(monthExecuteVOHash);
			}
			// 返回月度作业计划VO对象
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("娴忚鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			tawwpMonthExecutes = null;
			tawwpExecuteContents = null;
			monthExecuteVOHash = null;
			executeContentVOHash = null;
			monthExecuteVOList = null;
		}
	}

	public TawwpExecuteContentVO viewExecuteContent(String _executeContentId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
		TawwpExecuteContentVO tawwpExecuteContentVO = null;
		TawwpExecuteContentUserVO tawwpExecuteContentUserVO = null;
		TawwpExecuteFileVO tawwpExecuteFileVO = null;

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteFile tawwpExecuteFile = null;

		Iterator contentUserIterator = null;
		Iterator fileIterator = null;
		List executeContentUserList = new ArrayList();
		List fileList = new ArrayList();

		tawwpExecuteContent = tawwpExecuteContentDao
				.loadExecuteContent(_executeContentId); // 鑾峰彇鎵ц鍐呭淇℃伅
		tawwpExecuteContentVO = new TawwpExecuteContentVO();

		contentUserIterator = tawwpExecuteContent.getTawwpExecuteContentUsers()
				.iterator(); // 鑾峰彇鎵ц鍐呭鍚勬墽琛屼汉鐨勫瓙淇℃伅
		fileIterator = tawwpExecuteContent.getTawwpExecuteFiles().iterator(); // 鑾峰彇鎵ц鍐呭闄勪欢

		MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteContentVO,
				tawwpExecuteContent); // 灏佽鏁版嵁
		tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
				.getCycleName(tawwpExecuteContentVO.getCycle()));

		tawwpExecuteContentVO.setFormName(tawwpUtilDAO
				.getAddonsName(tawwpExecuteContentVO.getFormId()));

		/*
		 * while (fileIterator.hasNext()) { tawwpExecuteFile =
		 * (TawwpExecuteFile) fileIterator.next(); tawwpExecuteFileVO = new
		 * TawwpExecuteFileVO();
		 * 
		 * MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteFileVO,
		 * tawwpExecuteFile); fileList.add(tawwpExecuteFileVO); }
		 */

		String fileStr = this.getFileStrOfContent(tawwpExecuteContent);
		tawwpExecuteContentVO.setFileName(StaticMethod.null2String(fileStr));
		if (!"".equals(fileStr)) {
			tawwpExecuteContentVO.setFileCount(fileStr.split(",").length);
		} else {
			tawwpExecuteContentVO.setFileCount(0);
		}

		if (tawwpExecuteContentVO.getFileName() == null) {
			tawwpExecuteContentVO.setFileName("");
		}

		// tawwpExecuteContentVO.setExecuteFileListVO(fileList);

		while (contentUserIterator.hasNext()) {
			tawwpExecuteContentUser = (TawwpExecuteContentUser) contentUserIterator
					.next();
			tawwpExecuteContentUserVO = new TawwpExecuteContentUserVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteContentUserVO,
					tawwpExecuteContentUser);

			executeContentUserList.add(tawwpExecuteContentUserVO);
		}

		tawwpExecuteContentVO
				.setExecuteContentUserListVO(executeContentUserList);

		return tawwpExecuteContentVO;
	}

	/**
	 * 娴忚鎵ц浣滀笟璁″垝_鎵ц璐熻矗浜�
	 * 
	 * @param _monthPlanId <<<<<<<
	 *            .mine String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpMonthPlanVO 鏈堝害浣滀笟璁″垝VO瀵硅薄
	 */
	private TawwpMonthPlanVO getMontPlan(String _monthPlanId) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO瀵硅薄
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;
		TawwpExecuteReportVO tawwpExecuteReportVO = null;
		TawwpExecuteAssessVO tawwpExecuteAssessVO = null;

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteReport tawwpExecuteReport = null;
		TawwpExecuteAssess tawwpExecuteAssess = null;

		// 闆嗗悎瀵硅薄
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecuteContents = null;
		Hashtable monthExecuteVOHash = new Hashtable();
		Hashtable executeContentVOHash = new Hashtable();
		List monthExecuteVOList = new ArrayList();
		List executeReportVOList = new ArrayList();
		List executeAssessVOList = new ArrayList();

		int totalCount = 0; // 闇�瑕佹墽琛岀殑鎬绘暟
		int executedCount = 0; // 宸茬粡鎵ц鐨勬�绘暟
		int inTimeCount = 0; // 鍙婃椂鎵ц鐨勬�绘暟
		int timeOutCount = 0; // 瓒呮椂鎵ц鐨勬�绘暟

		int dayCount = 0; // 褰撴湀澶╂暟

		try {

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 灏佽鏈堝害浣滀笟璁″垝VO瀵硅薄
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan);
			// 鑾峰彇鎵ц绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
					.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteType())));
			// 鑾峰彇閮ㄩ棬鍚嶇О
			tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
					.getDeptName(tawwpMonthPlanVO.getDeptId()));
			// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
					.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
			// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
					.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
			// 鑾峰彇鍒涘缓浜哄鍚�
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));
			// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getConstituteState())));
			// 鑾峰彇鎵ц鐘舵�佸悕绉�
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteState())));
			// 鑾峰彇鎵ц璐熻矗浜哄鍚�
			tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 鑾峰彇缃戝厓鍚嶇О
			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));
			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭闆嗗悎
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			while (tawwpMonthExecutes.hasNext()) {
				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
				// 灏佽鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
						tawwpMonthExecute);
				// 鑾峰彇鎵ц鍛ㄦ湡
				tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
						.getCycleName(tawwpMonthExecuteVO.getCycle()));
				// 鑾峰彇鐢靛瓙琛ㄥ崟鍚嶇О
				tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
						.getAddonsName(tawwpMonthExecuteVO.getFormId()));
				// 鑾峰彇鍒涘缓浜哄鍚�
				tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthExecuteVO.getCruser()));
				// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
				tawwpMonthExecuteVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthExecuteVO.getNetTypeId()));

				// 鑾峰彇鎵ц浜虹被鍨嬪悕绉�
				tawwpMonthExecuteVO
						.setExecuterTypeName(TawwpMonthExecuteVO
								.getExecuterTypeName(StaticMethod
										.null2int(tawwpMonthExecuteVO
												.getExecuterType())));
				// 濡傛灉鎵ц浜虹被鍨嬩负鐝
				if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
					// 鑾峰彇鏈烘埧鍚嶇О
					String roomNames = tawwpUtilDAO
							.getRoomNames(tawwpMonthExecuteVO.getExecuter());
					tawwpMonthExecuteVO.setExecuterName(roomNames);
					tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

					// 鑾峰彇鎵ц鐝鍚嶇О
					if (tawwpMonthExecuteVO.getExecuteDuty() != null
							&& !"".equals(tawwpMonthExecuteVO.getExecuteDuty())) {
						// 鑾峰彇鎵ц鐝鍚嶇О
						tawwpMonthExecuteVO
								.setExecuteDutyName(tawwpMonthExecuteVO
										.getExecuteDutyName(Integer
												.parseInt(tawwpMonthExecuteVO
														.getExecuteDuty())));
					}
				} else {
					// 鑾峰彇鎵ц浜�(鎴栭儴闂ㄣ�佸矖浣�)濮撳悕(鎴栧悕绉�)
					// 鎵ц浜虹被鍨嬩负鐢ㄦ埛
					if ("0".equals(tawwpMonthExecuteVO.getExecuterType())) {
						tawwpMonthExecuteVO
								.setExecuterName(tawwpUtilDAO
										.getUserNames(tawwpMonthExecuteVO
												.getExecuter()));
					}
					// 鎵ц浜虹被鍨嬩负閮ㄩ棬
					if ("1".equals(tawwpMonthExecuteVO.getExecuterType())) {
						tawwpMonthExecuteVO
								.setExecuterName(tawwpUtilDAO
										.getDeptNames(tawwpMonthExecuteVO
												.getExecuter()));
					}
					// 鎵ц浜虹被鍨嬩负宀椾綅
					if ("2".equals(tawwpMonthExecuteVO.getExecuterType())) {
						// cccccccccccc
						/*
						 * tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO.getOrgPostNames(
						 * tawwpMonthExecuteVO.getExecuter()));
						 */
					}
				}

				// 鑾峰彇褰撴湀澶╂暟
				dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
						.getYearFlag(), tawwpMonthPlan.getMonthFlag());
				tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));

				// 娣诲姞鍒伴泦鍚堜腑
				monthExecuteVOList.add(tawwpMonthExecuteVO);
				Collections.sort(monthExecuteVOList);
				// ----------------------------------------------------------------------

				// 濡傛灉鎵ц鍐呭鐨勬墽琛屼汉涓嶄负鍊肩彮浜�
				// if (!"3".equals(tawwpMonthExecute.getExecuterType())) {

				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)闆嗗悎
				tawwpExecuteContents = tawwpMonthExecute
						.getTawwpExecuteContents().iterator();

				while (tawwpExecuteContents.hasNext()) {

					totalCount += 1; // 闇�瑕佹墽琛屾�绘暟鍔�1

					// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
					tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecuteContents
							.next());

					if (!"0".equals(tawwpExecuteContent.getExecuteFlag())) {
						executedCount += 1; // 宸茬粡鎵ц鎬绘暟鍔�1
						// 濡傛灉涓哄強鏃舵墽琛�
						if ("1".equals(tawwpExecuteContent.getExecuteFlag())) {
							inTimeCount += 1; // 鍙婃椂鎵ц鏁板姞1
						} else {
							timeOutCount += 1; // 瓒呮椂鎵ц鏁板姞1
						}
					}

					tawwpExecuteContentVO = new TawwpExecuteContentVO();
					// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
					MyBeanUtils.copyPropertiesFromDBToPage(
							tawwpExecuteContentVO, tawwpExecuteContent);
					tawwpExecuteContentVO.setUserName(tawwpUtilDAO
							.getUserNames(tawwpExecuteContentVO.getCruser())); // 鎵ц浜哄鍚�
					tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
							.getCycleName(tawwpExecuteContentVO.getCycle())); // 鍛ㄦ湡鍚嶇О
					tawwpExecuteContentVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpExecuteContentVO.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
					tawwpExecuteContentVO
							.setEligibilityName(tawwpExecuteContentVO
									.getEligibilityName(tawwpExecuteContentVO
											.getEligibility())); // 鍚堟牸鏍囧織
					tawwpExecuteContentVO
							.setExecuteFlagName(tawwpExecuteContentVO
									.getExecuteFlagName(Integer
											.parseInt(tawwpExecuteContentVO
													.getExecuteFlag()))); // 濉啓鏍囧織

					// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
					executeContentVOHash.put(tawwpMonthExecuteVO.getId()
							+ tawwpExecuteContentVO.getStartDate().substring(8,
									10), tawwpExecuteContentVO);

				}

				// }

				// ----------------------------------------------------------------------

				// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)闆嗗悎瀵硅薄,娣诲姞鍒版湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O闆嗗悎涓�
				monthExecuteVOHash.put(tawwpMonthExecuteVO,
						executeContentVOHash);
			}

			Collections.sort(monthExecuteVOList, new Comparator() {
				public int compare(Object o1, Object o2) {
					TawwpMonthExecuteVO p1 = (TawwpMonthExecuteVO) o1;
					TawwpMonthExecuteVO p2 = (TawwpMonthExecuteVO) o2;
					int k = -1;
					if (p1.getCycle().compareTo(p2.getCycle()) > 0) {
						k = 1;
					} else if (p1.getCycle().compareTo(p2.getCycle()) == 0) {
						if (p1.getId().compareTo(p2.getId()) > 0) {
							k = 1;
						} else {
							k = -1;
						}
					} else if (p1.getCycle().compareTo(p2.getCycle()) < 0) {
						k = -1;
					}
					return k;

				}
			});
			// ----------------------------------------------------------------------

			// 鑾峰彇鎵ц浣滀笟璁″垝鑰冩牳淇℃伅闆嗗悎
			
			List executeAssessList = tawwpExecuteAssessDao
			.listExecuteAssess(tawwpMonthPlan);
			 for (int i = 0; i < executeAssessList.size(); i++) {
			tawwpExecuteAssess = (TawwpExecuteAssess) (executeAssessList
			.get(i)); tawwpExecuteAssessVO = new TawwpExecuteAssessVO(); //
			// 灏佽鎵ц浣滀笟璁″垝鑰冩牳淇℃伅VO瀵硅薄
			  MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteAssessVO,
			  tawwpExecuteAssess);
			  tawwpExecuteAssessVO.setDeptName(tawwpUtilDAO
			  .getDeptName(tawwpExecuteAssessVO.getDeptId())); // 鑰冩牳閮ㄩ棬鍚嶇О
			  tawwpExecuteAssessVO.setCheckUserName(tawwpUtilDAO
			  .getUserName(tawwpExecuteAssessVO.getCheckUser())); // 鑰冩牳浜哄鍚�
			  tawwpExecuteAssessVO.setStateName(tawwpUtilDAO
			  .getCheckStateName(Integer
			  .parseInt(tawwpExecuteAssessVO.getState()))); // 鑰冩牳鐘舵�佸悕绉� //
//			  灏嗘墽琛屼綔涓氳鍒掕�冩牳淇℃伅VO瀵硅薄娣诲姞杩涢泦鍚堜腑
			  executeAssessVOList.add(tawwpExecuteAssessVO);
			  Collections.sort(executeAssessVOList); }
			
			// ------------------------------------------------------------------------
			// 鑾峰彇鏈堟姤銆佸懆鎶ラ泦鍚�
			/*
			 * List executeReportList = tawwpExecuteReportDao
			 * .listExecuteReport(tawwpMonthPlan); for (int i = 0; i <
			 * executeReportList.size(); i++) { tawwpExecuteReport =
			 * (TawwpExecuteReport) (executeReportList .get(i));
			 * tawwpExecuteReportVO = new TawwpExecuteReportVO(); //
			 * 灏佽鎵ц浣滀笟璁″垝鍛ㄦ姤銆佹湀鎶ヤ俊鎭疺O瀵硅薄
			 * MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteReportVO,
			 * tawwpExecuteReport);
			 * tawwpExecuteReportVO.setDeptName(tawwpUtilDAO
			 * .getDeptName(tawwpExecuteReportVO.getDeptId())); // 姹囨姤閮ㄩ棬鍚嶇О
			 * tawwpExecuteReportVO .setReportTypeName(TawwpExecuteReportVO
			 * .getReportTypeName(Integer .parseInt(tawwpExecuteReportVO
			 * .getReportType()))); // 姹囨姤绫诲瀷(鍛ㄣ�佹湀鎶�)
			 * tawwpExecuteReportVO.setReportUserName(tawwpUtilDAO
			 * .getUserName(tawwpExecuteReportVO.getReportUser())); // 姹囨姤浜哄鍚�
			 * tawwpExecuteReportVO .setReportFlagName(TawwpExecuteReportVO
			 * .getReportFlagName(Integer .parseInt(tawwpExecuteReportVO
			 * .getReportFlag())) // 娉ㄦ剰鏍囧織 ); //
			 * 灏嗘墽琛屼綔涓氳鍒掑懆鎶ャ�佹湀鎶ヤ俊鎭疺O瀵硅薄娣诲姞杩涢泦鍚堜腑
			 * executeReportVOList.add(tawwpExecuteReportVO); }
			 */
			// ------------------------------------------------------------------------
			/*
			 * 娣诲姞 1銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O瀵硅薄闆嗗悎 2銆佹墽琛屼綔涓氳鍒掕�冩牳淇℃伅VO瀵硅薄闆嗗悎
			 * 3銆佹墽琛屼綔涓氳鍒掑懆鎶ャ�佹湀鎶ヤ俊鎭疺O瀵硅薄闆嗗悎 4銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹笻ashtable瀵硅薄闆嗗悎
			 * (key鍊间负鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄,value涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)Hashtable瀵硅薄,
			 * 鍏朵腑鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)Hashtable瀵硅薄鐨刱ey鍊间负鎵ц鏃ユ湡,
			 * value鍊间负鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)鐨刅O瀵硅薄)
			 * 5銆佺敤浜庢樉绀哄強鏃躲�佸畬鎴愮巼銆佽秴鏃剁巼鐨�4涓睘鎬� 鍒版湀搴︿綔涓氳鍒扸O瀵硅薄涓�
			 */
			tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
			tawwpMonthPlanVO.setExecuteAssessVOList(executeAssessVOList);
			tawwpMonthPlanVO.setExecuteReportVOList(executeReportVOList);
			tawwpMonthPlanVO.setMonthExecuteVOHash(monthExecuteVOHash);
			tawwpMonthPlanVO.setTotalCount(String.valueOf(totalCount)); // 闇�瑕佹墽琛屾�绘暟
			tawwpMonthPlanVO.setExecutedCount(String.valueOf(executedCount)); // 宸茬粡鎵ц鎬绘暟
			tawwpMonthPlanVO.setInTimeCount(String.valueOf(inTimeCount)); // 鍙婃椂鎵ц鎬绘暟
			tawwpMonthPlanVO.setTimeOutCount(String.valueOf(timeOutCount)); // 瓒呮椂鎵ц鎬绘暟

			// 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("娴忚鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			tawwpMonthExecutes = null;
			tawwpExecuteContents = null;
			monthExecuteVOHash = null;
			executeContentVOHash = null;
			monthExecuteVOList = null;
			executeReportVOList = null;
			executeAssessVOList = null;
		}
	}

	/**
	 * 浏览执行作业计划_执行负责�?四川版本方法
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号 ======= String 鏈堝害浣滀笟璁″垝缂栧彿 >>>>>>> .r18111
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpMonthPlanVO 鏈堝害浣滀笟璁″垝VO瀵硅薄
	 */
	private TawwpMonthPlanVO getMontPlanCheck(String _monthPlanId)
			throws Exception {

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO瀵硅薄
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;
		TawwpExecuteReportVO tawwpExecuteReportVO = null;
		TawwpExecuteAssessVO tawwpExecuteAssessVO = null;

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteReport tawwpExecuteReport = null;
		TawwpExecuteAssess tawwpExecuteAssess = null;

		// 闆嗗悎瀵硅薄
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecuteContents = null;
		Hashtable monthExecuteVOHash = new Hashtable();
		Hashtable executeContentVOHash = new Hashtable();
		List monthExecuteVOList = new ArrayList();
		List executeReportVOList = new ArrayList();
		List executeAssessVOList = new ArrayList();

		int totalCount = 0; // 闇�瑕佹墽琛岀殑鎬绘暟
		int executedCount = 0; // 宸茬粡鎵ц鐨勬�绘暟
		int inTimeCount = 0; // 鍙婃椂鎵ц鐨勬�绘暟
		int timeOutCount = 0; // 瓒呮椂鎵ц鐨勬�绘暟

		int dayCount = 0; // 褰撴湀澶╂暟

		try {

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			System.out.println("tawwpMonthPlanDao.loadMonthPlan"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			System.out.println("tawwpMonthPlanDao.loadMonthPlan"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 灏佽鏈堝害浣滀笟璁″垝VO瀵硅薄
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan);
			// 鑾峰彇鎵ц绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
					.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteType())));
			// 鑾峰彇閮ㄩ棬鍚嶇О
			tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
					.getDeptName(tawwpMonthPlanVO.getDeptId()));
			// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
					.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
			// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
					.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getConstituteState())));
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteState())));
			tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 鑾峰彇缃戝厓鍚嶇О
			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));
			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭闆嗗悎
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();
			System.out.println("tawwpMonthPlan.getTawwpMonthExecutes()"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			while (tawwpMonthExecutes.hasNext()) {
				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
				// 灏佽鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
						tawwpMonthExecute);
				// 鑾峰彇鎵ц鍛ㄦ湡
				tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
						.getCycleName(tawwpMonthExecuteVO.getCycle()));
				// 鑾峰彇鐢靛瓙琛ㄥ崟鍚嶇О
				tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
						.getAddonsName(tawwpMonthExecuteVO.getFormId()));
				tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthExecuteVO.getCruser()));
				// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
				tawwpMonthExecuteVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthExecuteVO.getNetTypeId()));

				tawwpMonthExecuteVO
						.setExecuterTypeName(TawwpMonthExecuteVO
								.getExecuterTypeName(StaticMethod
										.null2int(tawwpMonthExecuteVO
												.getExecuterType())));
				// 濡傛灉鎵ц浜虹被鍨嬩负鐝
				if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
					// 鑾峰彇鏈烘埧鍚嶇О
					String roomNames = tawwpUtilDAO
							.getRoomNames(tawwpMonthExecuteVO.getExecuter());
					tawwpMonthExecuteVO.setExecuterName(roomNames);
					tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

					// 鑾峰彇鎵ц鐝鍚嶇О
					if (tawwpMonthExecuteVO.getExecuteDuty() != null
							&& !"".equals(tawwpMonthExecuteVO.getExecuteDuty())) {
						// 鑾峰彇鎵ц鐝鍚嶇О
						tawwpMonthExecuteVO
								.setExecuteDutyName(tawwpMonthExecuteVO
										.getExecuteDutyName(Integer
												.parseInt(tawwpMonthExecuteVO
														.getExecuteDuty())));
					}
				} else {
					if ("0".equals(tawwpMonthExecuteVO.getExecuterType())) {
						tawwpMonthExecuteVO
								.setExecuterName(tawwpUtilDAO
										.getUserNames(tawwpMonthExecuteVO
												.getExecuter()));
					}
					// 鎵ц浜虹被鍨嬩负閮ㄩ棬
					if ("1".equals(tawwpMonthExecuteVO.getExecuterType())) {
						tawwpMonthExecuteVO
								.setExecuterName(tawwpUtilDAO
										.getDeptNames(tawwpMonthExecuteVO
												.getExecuter()));
					}
					// 鎵ц浜虹被鍨嬩负宀椾綅
					if ("2".equals(tawwpMonthExecuteVO.getExecuterType())) {
						// cccccccccccc
						/*
						 * tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO.getOrgPostNames(
						 * tawwpMonthExecuteVO.getExecuter()));
						 */
					}
				}

				// 鑾峰彇褰撴湀澶╂暟
				dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
						.getYearFlag(), tawwpMonthPlan.getMonthFlag());
				tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));

				// 娣诲姞鍒伴泦鍚堜腑
				monthExecuteVOList.add(tawwpMonthExecuteVO);
				Collections.sort(monthExecuteVOList);
				// ----------------------------------------------------------------------

				// if (!"3".equals(tawwpMonthExecute.getExecuterType())) {

				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)闆嗗悎
				tawwpExecuteContents = tawwpMonthExecute
						.getTawwpExecuteContents().iterator();

				while (tawwpExecuteContents.hasNext()) {

					// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
					tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecuteContents
							.next());
					totalCount += 1;
					if (!"0".equals(tawwpExecuteContent.getExecuteFlag())) {
						executedCount += 1; // 宸茬粡鎵ц鎬绘暟鍔�1
						// 濡傛灉涓哄強鏃舵墽琛�
						if ("1".equals(tawwpExecuteContent.getExecuteFlag())) {
							inTimeCount += 1; // 鍙婃椂鎵ц鏁板姞1
						} else {
							timeOutCount += 1; // 瓒呮椂鎵ц鏁板姞1
						}
					}
					System.out.println("/ 如果为及时执�?"
							+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
					tawwpExecuteContentVO = new TawwpExecuteContentVO();
					// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
					MyBeanUtils.copyPropertiesFromDBToPage(
							tawwpExecuteContentVO, tawwpExecuteContent);
					tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
							.getCycleName(tawwpExecuteContentVO.getCycle())); // 鍛ㄦ湡鍚嶇О
					tawwpExecuteContentVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpExecuteContentVO.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
					tawwpExecuteContentVO
							.setEligibilityName(tawwpExecuteContentVO
									.getEligibilityName(tawwpExecuteContentVO
											.getEligibility())); // 鍚堟牸鏍囧織
					tawwpExecuteContentVO
							.setExecuteFlagName(tawwpExecuteContentVO
									.getExecuteFlagName(Integer
											.parseInt(tawwpExecuteContentVO
													.getExecuteFlag()))); // 濉啓鏍囧織

					executeContentVOHash.put(tawwpMonthExecuteVO.getId()
							+ tawwpExecuteContentVO.getStartDate().substring(8,
									10), tawwpExecuteContentVO);

				}

				// }

				// ----------------------------------------------------------------------

				monthExecuteVOHash.put(tawwpMonthExecuteVO,
						executeContentVOHash);
			}

			// ----------------------------------------------------------------------
			System.out.println("// 获取执行作业计划考核信息集合"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			// 鑾峰彇鎵ц浣滀笟璁″垝鑰冩牳淇℃伅闆嗗悎
			List executeAssessList = tawwpExecuteAssessDao
					.listExecuteAssess(tawwpMonthPlan);
			System.out.println("// 获取执行作业计划考核信息集合"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			for (int i = 0; i < executeAssessList.size(); i++) {
				tawwpExecuteAssess = (TawwpExecuteAssess) (executeAssessList
						.get(i));
				tawwpExecuteAssessVO = new TawwpExecuteAssessVO();
				// 灏佽鎵ц浣滀笟璁″垝鑰冩牳淇℃伅VO瀵硅薄
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteAssessVO,
						tawwpExecuteAssess);
				tawwpExecuteAssessVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpExecuteAssessVO.getDeptId())); // 鑰冩牳閮ㄩ棬鍚嶇О
				tawwpExecuteAssessVO.setCheckUserName(tawwpUtilDAO
						.getUserName(tawwpExecuteAssessVO.getCheckUser())); // 鑰冩牳浜哄鍚�
				tawwpExecuteAssessVO.setStateName(tawwpUtilDAO
						.getCheckStateName(Integer
								.parseInt(tawwpExecuteAssessVO.getState()))); // 考核状态名�?

				// 灏嗘墽琛屼綔涓氳鍒掕�冩牳淇℃伅VO瀵硅薄娣诲姞杩涢泦鍚堜腑
				executeAssessVOList.add(tawwpExecuteAssessVO);
				Collections.sort(executeAssessVOList);
			}
			System.out.println("// 获取执行作业计划考核信息集合"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			// ------------------------------------------------------------------------

			List executeReportList = tawwpExecuteReportDao
					.listExecuteReport(tawwpMonthPlan);
			System.out.println("获取月报、周报集�?"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			for (int i = 0; i < executeReportList.size(); i++) {
				tawwpExecuteReport = (TawwpExecuteReport) (executeReportList
						.get(i));
				tawwpExecuteReportVO = new TawwpExecuteReportVO();
				// 灏佽鎵ц浣滀笟璁″垝鍛ㄦ姤銆佹湀鎶ヤ俊鎭疺O瀵硅薄
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteReportVO,
						tawwpExecuteReport);
				tawwpExecuteReportVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpExecuteReportVO.getDeptId())); // 姹囨姤閮ㄩ棬鍚嶇О
				tawwpExecuteReportVO
						.setReportTypeName(TawwpExecuteReportVO
								.getReportTypeName(Integer
										.parseInt(tawwpExecuteReportVO
												.getReportType()))); // 姹囨姤绫诲瀷(鍛ㄣ�佹湀鎶�)
				tawwpExecuteReportVO.setReportUserName(tawwpUtilDAO
						.getUserName(tawwpExecuteReportVO.getReportUser())); // 汇报人姓�?
				tawwpExecuteReportVO
						.setReportFlagName(TawwpExecuteReportVO
								.getReportFlagName(Integer
										.parseInt(tawwpExecuteReportVO
												.getReportFlag())) // 娉ㄦ剰鏍囧織
						);
				// 灏嗘墽琛屼綔涓氳鍒掑懆鎶ャ�佹湀鎶ヤ俊鎭疺O瀵硅薄娣诲姞杩涢泦鍚堜腑
				executeReportVOList.add(tawwpExecuteReportVO);
			}
			System.out.println("获取月报、周报集�?"
					+ TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss"));
			// ------------------------------------------------------------------------

			/*
			 * <<<<<<< .mine 添加 1、月度作业计划执行内容VO对象集合 2、执行作业计划考核信息VO对象集合
			 * 3、执行作业计划周报、月报信息VO对象集合 4、月度作业计划执行内容Hashtable对象集合
			 * (key值为月度作业计划执行内容VO对象,value为执行作业计划执行内�?整体)Hashtable对象,
			 * 其中执行作业计划执行内容(整体)Hashtable对象的key值为执行日期,
			 * value值为执行作业计划执行内容(整体)的VO对象) 5、用于显示及时、完成率、超时率�?个属�?到月度作业计划VO对象�?
			 * ======= 娣诲姞 1銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O瀵硅薄闆嗗悎 2銆佹墽琛屼綔涓氳鍒掕�冩牳淇℃伅VO瀵硅薄闆嗗悎
			 * 3銆佹墽琛屼綔涓氳鍒掑懆鎶ャ�佹湀鎶ヤ俊鎭疺O瀵硅薄闆嗗悎 4銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹笻ashtable瀵硅薄闆嗗悎
			 * (key鍊间负鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄,value涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)Hashtable瀵硅薄,
			 * 鍏朵腑鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)Hashtable瀵硅薄鐨刱ey鍊间负鎵ц鏃ユ湡,
			 * value鍊间负鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)鐨刅O瀵硅薄)
			 * 5銆佺敤浜庢樉绀哄強鏃躲�佸畬鎴愮巼銆佽秴鏃剁巼鐨�4涓睘鎬� 鍒版湀搴︿綔涓氳鍒扸O瀵硅薄涓� >>>>>>>
			 * .r18111
			 */
			tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
			tawwpMonthPlanVO.setExecuteAssessVOList(executeAssessVOList);
			tawwpMonthPlanVO.setExecuteReportVOList(executeReportVOList);
			tawwpMonthPlanVO.setMonthExecuteVOHash(monthExecuteVOHash);
			tawwpMonthPlanVO.setTotalCount(String.valueOf(totalCount)); // 闇�瑕佹墽琛屾�绘暟
			tawwpMonthPlanVO.setExecutedCount(String.valueOf(executedCount)); // 宸茬粡鎵ц鎬绘暟
			tawwpMonthPlanVO.setInTimeCount(String.valueOf(inTimeCount)); // 鍙婃椂鎵ц鎬绘暟
			tawwpMonthPlanVO.setTimeOutCount(String.valueOf(timeOutCount)); // 瓒呮椂鎵ц鎬绘暟

			// 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("娴忚鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			tawwpMonthExecutes = null;
			tawwpExecuteContents = null;
			monthExecuteVOHash = null;
			executeContentVOHash = null;
			monthExecuteVOList = null;
			executeReportVOList = null;
			executeAssessVOList = null;
		}
	}

	/**
	 * 业务逻辑：批量填写执行作业计划执行内容_显示(BROWSE-EXECUTE-PLAN-001)
	 * 
	 * @param _userId
	 *            String 用户�?
	 * @param _deptId
	 *            String 部门编号
	 * @param _roomId
	 *            String 机房编号
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _dutyStartTime
	 *            String 班次开始时�?如果批量填写“日常”则为空
	 * @param _dutyEndTime
	 *            String 班次结束时间,如果批量填写“日常”则为空
	 * @param _flag
	 *            String 标志�?标识填写的是日常执行作业计划执行内容,还是值班�?
	 * @param _stubFlag
	 *            String 代理标志
	 * @param _date
	 *            String 需要批量执行的日期
	 * @throws Exception
	 *             异常
	 * @return List 执行作业计划执行内容(整体)VO对象集合
	 */
	public List addExecuteContentUsersView(String _userId, String _deptId,
			String _roomId, String _monthPlanId, String _dutyStartTime,
			String _dutyEndTime, String _flag, String _stubFlag, String _date)
			throws Exception {
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// VO瀵硅薄
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 闆嗗悎瀵硅薄
		Iterator tawwpMonthExecutes = null;
		List executeContentVOList = new ArrayList();

		String executerType = "";
		String fileStr = "";
		String executeDate = "";

		try {

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);

			// 濡傛灉_date涓虹┖,琛ㄧず涓哄綋鏃ョ殑鎵归噺濉啓
			if ("".equals(_date)) {
				executeDate = TawwpUtil.getCurrentDateTime();
				// 涓嶄负绌哄垯琛ㄧず涓烘寚瀹氭棩鏈熺殑鎵归噺濉啓
			} else {
				executeDate = TawwpUtil.getCurrentDateTime(tawwpMonthPlan
						.getYearFlag()
						+ "-"
						+ tawwpMonthPlan.getMonthFlag()
						+ "-"
						+ _date
						+ " HH:mm:ss");
			}

			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭闆嗗悎
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();
			String timeStr = StaticMethod.getLocalString();
			while (tawwpMonthExecutes.hasNext()) {
				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				executerType = tawwpMonthExecute.getExecuterType();

				/*
				 * 濡傛灉 1鏍囧織涓�"鏃ュ父"銆� 2鎵ц浜虹被鍨嬩笉涓哄�肩彮浜�
				 */
				if ("daily".equalsIgnoreCase(_flag.trim())
						&& !"3".equals(executerType)) {

					// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(executeDate,
										tawwpMonthExecute);

						if (tawwpExecuteContent == null) {
							// 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鍒欑粨鏉熸娆″惊鐜�
							continue;
						}

						tawwpExecuteContentVO = new TawwpExecuteContentVO();
						// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpExecuteContentVO, tawwpExecuteContent);
						String acessories = "";

						if (tawwpMonthExecute.getTawwpYearExecute() != null) {
							String yearexecuteid = tawwpMonthExecute
									.getTawwpYearExecute().getId();
							TawwpYearExecute yearexecute = tawwpYearExecuteDao
									.loadYearExecute(yearexecuteid);
							if (yearexecute.getTawwpModelExecute() != null) {
								String modelexecuteid = yearexecute
										.getTawwpModelExecute().getId();
								TawwpModelExecute modelexecute = tawwpModelExecuteDao
										.loadModelExecute(modelexecuteid);
								acessories = modelexecute.getAccessories();
							}

						}
						tawwpExecuteContentVO.setAccessories(tawwpMonthExecute
								.getAccessories());

						/*
						 * 濡傛灉 1鏈墽琛屻��
						 * 2鎴栬�呭凡缁忔墽琛屼笖鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,浣嗗綋鍓嶇敤鎴锋病鏈夎繘琛屽～鍐�
						 */
						tawwpExecuteContentVO
								.setCycleName(tawwpUtilDAO
										.getCycleName(tawwpExecuteContentVO
												.getCycle())); // 鍛ㄦ湡鍚嶇О
						tawwpExecuteContentVO.setFormName(tawwpUtilDAO
								.getAddonsName(tawwpExecuteContentVO
										.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
						// edit by gongyufeng 淇敼姣忎釜鎵ц鍐呭鍖呭惈榛樿鎵ц椤�
						// tawwpExecuteContentVO.setContent(""); //
						// 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add
						// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
						tawwpExecuteContentVO.setRemark("");
						tawwpExecuteContentVO.setExtendremark(StaticMethod
								.null2String(tawwpMonthExecute.getRemark()));
						// by denden璐靛窞
						tawwpExecuteContentVO.setName(StaticMethod
								.null2String(tawwpMonthExecute.getName())); // 鎵ц鍐呭鍚嶇О
						tawwpExecuteContentVO.setExecuteType(tawwpMonthPlan
								.getExecuteType()); // 鎵ц绫诲瀷,鐢ㄤ簬鍖哄埆鏄惁鏄剧ず鈥滃悓缁勬墽琛屸�濋摼鎺�
						if ("stub".equals(_stubFlag)) {
							tawwpExecuteContentVO.setStubFlag("1"); // 浠ｇ悊鏍囧織
							tawwpExecuteContentVO.setUserByStub(_userId); // 琚唬鐞嗙敤鎴�
						} else {
							tawwpExecuteContentVO.setStubFlag("0"); // 浠ｇ悊鏍囧織
							tawwpExecuteContentVO.setUserByStub(""); // 琚唬鐞嗙敤鎴�
						}
						fileStr = this.getFileStrOfContent(tawwpExecuteContent);
						tawwpExecuteContentVO.setFileName(StaticMethod
								.null2String(fileStr));
						if (!"".equals(fileStr)) {
							tawwpExecuteContentVO.setFileCount(fileStr
									.split(",").length);
						} else {
							tawwpExecuteContentVO.setFileCount(0);
						}
						// 濡傛灉宸茬粡鎵ц涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠姐��
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "0".equals(tawwpMonthPlan.getExecuteType())) {
							// tawwpExecuteContentVO.setContent(new
							// String(tawwpExecuteContent.getContent().getBytes("gb2312"),"819"));
							// //澧炲姞宸茬粡濉啓杩囩殑鎵ц鍐呭add by denden璐靛窞
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent()); // 澧炲姞宸茬粡濉啓杩囩殑鎵ц鍐呭add
							// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
							tawwpExecuteContentVO.setRemark(tawwpExecuteContent
									.getRemark());
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							// by denden璐靛窞
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContent
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍
							fileStr = this
									.getFileStrOfContent(tawwpExecuteContent);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							System.out.println(tawwpExecuteContent
									.getTawwpExecuteContentUsers().size());
							if (tawwpExecuteContent
									.getTawwpExecuteContentUsers().size() > 0) {
								TawwpExecuteContentUser executecontentuser = (TawwpExecuteContentUser) tawwpExecuteContent
										.getTawwpExecuteContentUsers()
										.iterator().next();
								tawwpExecuteContentVO
										.setWriteDate(executecontentuser
												.getWriteDate());
								tawwpExecuteContentVO
										.setExecuteContentUserId(executecontentuser
												.getId());

							}
						}

						/*
						 * 濡傛灉 1宸茬粡鎵ц銆� 2澶氫汉濉啓澶氫唤銆�
						 * 3鎵ц浜哄瓧绗︿覆(鎵ц鍐呭(鏁翠綋)鐨刢ruser瀛楁)涓惈鏈夊綋鍓嶇敤鎴�
						 */
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "1".equals(tawwpMonthPlan.getExecuteType())
								&& ("," + tawwpExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") >= 0) {

							// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鍐呭)瀵硅薄
							// tawwpExecuteContentVO.setContent(tawwpExecuteContent.getContent());
							// //澧炲姞宸茬粡濉啓杩囩殑鎵ц鍐呭add by denden璐靛窞
							/*
							 * tawwpExecuteContentVO.setContent(new String(
							 * tawwpExecuteContent.getContent().getBytes(
							 * "819"), "gb2312"));
							 */// 澧炲姞宸茬粡濉啓杩囩殑鎵ц鍐呭add
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent());
							// by denden璐靛窞
							tawwpExecuteContentUser = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍
							tawwpExecuteContentVO
									.setCrtime(tawwpExecuteContentUser
											.getCrtime()); // 鎵ц鏃堕棿
							tawwpExecuteContentVO
									.setWriteDate(tawwpExecuteContentUser
											.getWriteDate());
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent()); // 澧炲姞宸茬粡濉啓杩囩殑鎵ц鍐呭add
							// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
							tawwpExecuteContentVO
									.setRemark(tawwpExecuteContentUser
											.getRemark());
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent()); // 澧炲姞宸茬粡濉啓杩囩殑鎵ц鍐呭add
							// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
							tawwpExecuteContentVO
									.setRemark(tawwpExecuteContentUser
											.getRemark());
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							// /*******************************************************************
							// tawwpExecuteContentVO.setContent("");
							// tawwpExecuteContentVO.setContent(tawwpExecuteContentUser.getContent()
							// ); //濉啓鍐呭
							// /******************************************************************8
							fileStr = this
									.getFileStrOfUser(tawwpExecuteContentUser);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							tawwpExecuteContentVO
									.setExecuteContentUserId(tawwpExecuteContentUser
											.getId());

						}

						if (tawwpExecuteContentVO.getFileName() == null) {
							tawwpExecuteContentVO.setFileName("");
						}
						if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
							tawwpExecuteContentVO.setExecuteContentUserId("");
						}

						// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
						executeContentVOList.add(tawwpExecuteContentVO);

					}
				}
				/*
				 * 濡傛灉 1鏍囧織涓�"鍊肩彮"銆� 2鎵ц鍐呭鐨勬墽琛屼汉绫诲瀷涓哄�肩彮浜恒��
				 * 3鎵ц鐝(鏃堕棿鐐�),鍦ㄥ綋鍓嶇敤鎴风櫥褰曠殑鍊肩彮鐝寮�濮嬫椂闂村拰缁撴潫鏃堕棿涔嬮棿
				 */
				else if ("duty".equals(_flag)
						&& "3".equals(tawwpMonthExecute.getExecuterType())
						&& (this
								.judgeDutyMonthPlan(tawwpMonthExecute
										.getExecuteDuty(), _dutyStartTime,
										_dutyEndTime))) {

					// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(TawwpUtil
										.getCurrentDateTime(),
										tawwpMonthExecute);

						if (tawwpExecuteContent == null) {
							// 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鍒欑粨鏉熸娆″惊鐜�
							continue;
						}
						// 改if由四川版本获得
						if (timeStr.compareTo(tawwpExecuteContent
								.getStartDate()) <= 0
								&& timeStr.compareTo(tawwpExecuteContent
										.getEndDate()) > 0) {
							continue;
						}
						tawwpExecuteContentVO = new TawwpExecuteContentVO();
						// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpExecuteContentVO, tawwpExecuteContent);
						String acessories = "";
						if (tawwpMonthExecute.getTawwpYearExecute() != null) {
							String yearexecuteid = tawwpMonthExecute
									.getTawwpYearExecute().getId();
							TawwpYearExecute yearexecute = tawwpYearExecuteDao
									.loadYearExecute(yearexecuteid);
							if (yearexecute.getTawwpModelExecute() != null) {
								String modelexecuteid = yearexecute
										.getTawwpModelExecute().getId();
								TawwpModelExecute modelexecute = tawwpModelExecuteDao
										.loadModelExecute(modelexecuteid);
								acessories = modelexecute.getAccessories();
							}

						}
						tawwpExecuteContentVO.setAccessories(tawwpMonthExecute
								.getAccessories());
						/*
						 * 濡傛灉 1鏈墽琛屻��
						 * 2鎴栬�呭凡缁忔墽琛屼笖鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,浣嗗綋鍓嶇敤鎴锋病鏈夎繘琛屽～鍐�
						 */
						tawwpExecuteContentVO
								.setCycleName(tawwpUtilDAO
										.getCycleName(tawwpExecuteContentVO
												.getCycle())); // 鍛ㄦ湡鍚嶇О
						tawwpExecuteContentVO.setFormName(tawwpUtilDAO
								.getAddonsName(tawwpExecuteContentVO
										.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
						tawwpExecuteContentVO.setName(StaticMethod
								.null2String(tawwpMonthExecute.getName())); // 鎵ц鍐呭鍚嶇О
						tawwpExecuteContentVO.setExecuteType(tawwpMonthPlan
								.getExecuteType()); // 鎵ц绫诲瀷,鐢ㄤ簬鍖哄埆鏄惁鏄剧ず鈥滃悓缁勬墽琛屸�濋摼鎺�
						tawwpExecuteContentVO.setFileName("");
						tawwpExecuteContentVO.setContent(tawwpExecuteContent
								.getContent()); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add
						tawwpExecuteContentVO.setRemark("");
						tawwpExecuteContentVO.setExtendremark(StaticMethod
								.null2String(tawwpMonthExecute.getRemark()));
						// 濡傛灉宸茬粡鎵ц涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠姐��
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "0".equals(tawwpMonthPlan.getExecuteType())) {
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent()); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add
							// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
							tawwpExecuteContentVO.setRemark(StaticMethod
									.null2String(tawwpExecuteContent
											.getRemark()));
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							fileStr = this
									.getFileStrOfContent(tawwpExecuteContent);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							tawwpExecuteContentVO
									.setExecuteContentUserId(((TawwpExecuteContentUser) tawwpExecuteContent
											.getTawwpExecuteContentUsers()
											.iterator().next()).getId());
						}

						/*
						 * 濡傛灉 1宸茬粡鎵ц銆� 2澶氫汉濉啓澶氫唤銆�
						 * 3鎵ц浜哄瓧绗︿覆(鎵ц鍐呭(鏁翠綋)鐨刢ruser瀛楁)涓惈鏈夊綋鍓嶇敤鎴�
						 */
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "1".equals(tawwpMonthPlan.getExecuteType())
								&& ("," + tawwpExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") >= 0) {

							// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鍐呭)瀵硅薄
							tawwpExecuteContentUser = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍
							tawwpExecuteContentVO
									.setCrtime(tawwpExecuteContentUser
											.getCrtime()); // 鎵ц鏃堕棿
							//
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent()); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add
							// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
							tawwpExecuteContentVO.setRemark(StaticMethod
									.null2String(tawwpExecuteContentUser
											.getRemark()));
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							//
							fileStr = this
									.getFileStrOfUser(tawwpExecuteContentUser);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							tawwpExecuteContentVO
									.setExecuteContentUserId(tawwpExecuteContentUser
											.getId());

						}

						if (tawwpExecuteContentVO.getFileName() == null) {
							tawwpExecuteContentVO.setFileName("");
						}
						if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
							tawwpExecuteContentVO.setExecuteContentUserId("");
						}

						// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
						executeContentVOList.add(tawwpExecuteContentVO);
					}
				} else if ("notInspection".equals(_flag)
						&& "".equals(StaticMethod.null2String(tawwpMonthExecute
								.getCommand()))) {

					// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�

					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(TawwpUtil
										.getCurrentDateTime(),
										tawwpMonthExecute);

						if (tawwpExecuteContent == null) {
							// 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鍒欑粨鏉熸娆″惊鐜�
							continue;
						}
						if (timeStr.compareTo(tawwpExecuteContent
								.getStartDate()) <= 0
								&& timeStr.compareTo(tawwpExecuteContent
										.getEndDate()) > 0) {
							continue;
						}
						tawwpExecuteContentVO = new TawwpExecuteContentVO();
						// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpExecuteContentVO, tawwpExecuteContent);
						String acessories = "";
						// if (tawwpMonthExecute.getTawwpYearExecute() != null)
						// {
						// String yearexecuteid = tawwpMonthExecute
						// .getTawwpYearExecute().getId();
						// TawwpYearExecute yearexecute = tawwpYearExecuteDao
						// .loadYearExecute(yearexecuteid);
						// if (yearexecute.getTawwpModelExecute() != null) {
						// String modelexecuteid = yearexecute
						// .getTawwpModelExecute().getId();
						// TawwpModelExecute modelexecute = tawwpModelExecuteDao
						// .loadModelExecute(modelexecuteid);
						// acessories = modelexecute.getAccessories();
						// }
						//
						// }
						tawwpExecuteContentVO.setAccessories(tawwpMonthExecute
								.getAccessories());
						/*
						 * 濡傛灉 1鏈墽琛屻��
						 * 2鎴栬�呭凡缁忔墽琛屼笖鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,浣嗗綋鍓嶇敤鎴锋病鏈夎繘琛屽～鍐�
						 */
						tawwpExecuteContentVO
								.setCycleName(tawwpUtilDAO
										.getCycleName(tawwpExecuteContentVO
												.getCycle())); // 鍛ㄦ湡鍚嶇О
						tawwpExecuteContentVO.setFormName(tawwpUtilDAO
								.getAddonsName(tawwpExecuteContentVO
										.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
						tawwpExecuteContentVO.setName(StaticMethod
								.null2String(tawwpMonthExecute.getName())); // 鎵ц鍐呭鍚嶇О
						tawwpExecuteContentVO.setExecuteType(tawwpMonthPlan
								.getExecuteType()); // 鎵ц绫诲瀷,鐢ㄤ簬鍖哄埆鏄惁鏄剧ず鈥滃悓缁勬墽琛屸�濋摼鎺�
						tawwpExecuteContentVO.setFileName("");
						tawwpExecuteContentVO.setContent(""); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add
						// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
						tawwpExecuteContentVO.setRemark("");
						tawwpExecuteContentVO.setExtendremark(StaticMethod
								.null2String(tawwpMonthExecute.getRemark()));

						// 濡傛灉宸茬粡鎵ц涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠姐��
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "0".equals(tawwpMonthPlan.getExecuteType())) {
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent()); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add
							// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
							tawwpExecuteContentVO.setRemark(StaticMethod
									.null2String(tawwpExecuteContent
											.getRemark()));
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));

							tawwpExecuteContentVO
									.setExecuteContentUserId(((TawwpExecuteContentUser) tawwpExecuteContent
											.getTawwpExecuteContentUsers()
											.iterator().next()).getId());
						}

						/*
						 * 濡傛灉 1宸茬粡鎵ц銆� 2澶氫汉濉啓澶氫唤銆�
						 * 3鎵ц浜哄瓧绗︿覆(鎵ц鍐呭(鏁翠綋)鐨刢ruser瀛楁)涓惈鏈夊綋鍓嶇敤鎴�
						 */
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "1".equals(tawwpMonthPlan.getExecuteType())
								&& ("," + tawwpExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") >= 0) {

							// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鍐呭)瀵硅薄
							tawwpExecuteContentUser = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍
							tawwpExecuteContentVO
									.setCrtime(tawwpExecuteContentUser
											.getCrtime()); // 鎵ц鏃堕棿
							//
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent()); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add
							// 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
							tawwpExecuteContentVO.setRemark(StaticMethod
									.null2String(tawwpExecuteContentUser
											.getRemark()));
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							//
							fileStr = this
									.getFileStrOfUser(tawwpExecuteContentUser);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							tawwpExecuteContentVO
									.setExecuteContentUserId(tawwpExecuteContentUser
											.getId());

						}

						if (tawwpExecuteContentVO.getFileName() == null) {
							tawwpExecuteContentVO.setFileName("");
						}
						if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
							tawwpExecuteContentVO.setExecuteContentUserId("");
						}

						// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
						executeContentVOList.add(tawwpExecuteContentVO);
					}
				}
				/*
				 * else if ("Inspection".equals(_flag) &&
				 * !"".equals(StaticMethod
				 * .null2String(tawwpMonthExecute.getCommand()))) { //
				 * 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
				 * 
				 * if (this.judgeExecuter(_userId, _deptId, _roomId,
				 * tawwpMonthExecute)) { // 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
				 * tawwpExecuteContent = tawwpExecuteContentDao
				 * .filterTawwpExecuteContent(TawwpUtil .getCurrentDateTime(),
				 * tawwpMonthExecute);
				 * 
				 * if (tawwpExecuteContent == null) { //
				 * 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鍒欑粨鏉熸娆″惊鐜� continue; } if
				 * (timeStr.compareTo(tawwpExecuteContent .getStartDate()) <= 0 &&
				 * timeStr.compareTo(tawwpExecuteContent .getEndDate()) > 0) {
				 * continue; } tawwpExecuteContentVO = new
				 * TawwpExecuteContentVO(); // 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
				 * MyBeanUtils.copyPropertiesFromDBToPage(
				 * tawwpExecuteContentVO, tawwpExecuteContent); String
				 * acessories = ""; // if
				 * (tawwpMonthExecute.getTawwpYearExecute() != null) // { //
				 * String yearexecuteid = tawwpMonthExecute //
				 * .getTawwpYearExecute().getId(); // TawwpYearExecute
				 * yearexecute = tawwpYearExecuteDao //
				 * .loadYearExecute(yearexecuteid); // if
				 * (yearexecute.getTawwpModelExecute() != null) { // String
				 * modelexecuteid = yearexecute //
				 * .getTawwpModelExecute().getId(); // TawwpModelExecute
				 * modelexecute = tawwpModelExecuteDao //
				 * .loadModelExecute(modelexecuteid); // acessories =
				 * modelexecute.getAccessories(); // } // // }
				 * tawwpExecuteContentVO.setAccessories(tawwpMonthExecute
				 * .getAccessories()); /* 濡傛灉 1鏈墽琛屻��
				 * 2鎴栬�呭凡缁忔墽琛屼笖鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,浣嗗綋鍓嶇敤鎴锋病鏈夎繘琛屽～鍐�
				 */
				/*
				 * tawwpExecuteContentVO .setCycleName(tawwpUtilDAO
				 * .getCycleName(tawwpExecuteContentVO .getCycle())); // 鍛ㄦ湡鍚嶇О
				 * tawwpExecuteContentVO.setFormName(tawwpUtilDAO
				 * .getAddonsName(tawwpExecuteContentVO .getFormId())); //
				 * 闄勫姞琛ㄥ崟鍚嶇О tawwpExecuteContentVO.setName(StaticMethod
				 * .null2String(tawwpMonthExecute.getName())); // 鎵ц鍐呭鍚嶇О
				 * tawwpExecuteContentVO.setExecuteType(tawwpMonthPlan
				 * .getExecuteType()); // 鎵ц绫诲瀷,鐢ㄤ簬鍖哄埆鏄惁鏄剧ず鈥滃悓缁勬墽琛屸�濋摼鎺�
				 * tawwpExecuteContentVO.setFileName("");
				 * tawwpExecuteContentVO.setContent(""); //
				 * 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add // 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
				 * tawwpExecuteContentVO.setRemark("");
				 * tawwpExecuteContentVO.setExtendremark(StaticMethod
				 * .null2String(tawwpMonthExecute.getRemark())); //
				 * 濡傛灉宸茬粡鎵ц涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠姐�� if
				 * (!"0".equals(tawwpExecuteContent.getExecuteFlag()) &&
				 * "0".equals(tawwpMonthPlan.getExecuteType())) {
				 * tawwpExecuteContentVO .setContent(tawwpExecuteContent
				 * .getContent()); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add // 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
				 * tawwpExecuteContentVO.setRemark(StaticMethod
				 * .null2String(tawwpExecuteContent .getRemark()));
				 * tawwpExecuteContentVO .setExtendremark(StaticMethod
				 * .null2String(tawwpMonthExecute .getRemark()));
				 * 
				 * tawwpExecuteContentVO
				 * .setExecuteContentUserId(((TawwpExecuteContentUser)
				 * tawwpExecuteContent .getTawwpExecuteContentUsers()
				 * .iterator().next()).getId()); }
				 */
				/*
				 * 濡傛灉 1宸茬粡鎵ц銆� 2澶氫汉濉啓澶氫唤銆�
				 * 3鎵ц浜哄瓧绗︿覆(鎵ц鍐呭(鏁翠綋)鐨刢ruser瀛楁)涓惈鏈夊綋鍓嶇敤鎴�
				 */
				/*
				 * if (!"0".equals(tawwpExecuteContent.getExecuteFlag()) &&
				 * "1".equals(tawwpMonthPlan.getExecuteType()) && ("," +
				 * tawwpExecuteContent.getCruser() + ",") .indexOf("," + _userId +
				 * ",") >= 0) { // 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鍐呭)瀵硅薄
				 * tawwpExecuteContentUser = tawwpExecuteContentUserDao
				 * .filterTawwpExecuteContentUser(_userId, tawwpExecuteContent);
				 * tawwpExecuteContentVO .setFormDataId(tawwpExecuteContentUser
				 * .getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍 tawwpExecuteContentVO
				 * .setCrtime(tawwpExecuteContentUser .getCrtime()); // 鎵ц鏃堕棿 //
				 * tawwpExecuteContentVO .setContent(tawwpExecuteContentUser
				 * .getContent()); // 淇敼姣忎釜鏈畬鎴愮殑鍐呭涓虹┖add // 瀵瑰娉ㄦ暟鎹殑澶勭悊(鏂板)
				 * tawwpExecuteContentVO.setRemark(StaticMethod
				 * .null2String(tawwpExecuteContentUser .getRemark()));
				 * tawwpExecuteContentVO .setExtendremark(StaticMethod
				 * .null2String(tawwpMonthExecute .getRemark())); // fileStr =
				 * this .getFileStrOfUser(tawwpExecuteContentUser);
				 * tawwpExecuteContentVO.setFileName(StaticMethod
				 * .null2String(fileStr)); if (!"".equals(fileStr)) {
				 * tawwpExecuteContentVO.setFileCount(fileStr
				 * .split(",").length); } else {
				 * tawwpExecuteContentVO.setFileCount(0); }
				 * tawwpExecuteContentVO
				 * .setExecuteContentUserId(tawwpExecuteContentUser .getId()); }
				 * 
				 * if (tawwpExecuteContentVO.getFileName() == null) {
				 * tawwpExecuteContentVO.setFileName(""); } if
				 * (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
				 * tawwpExecuteContentVO.setExecuteContentUserId(""); } //
				 * 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
				 * executeContentVOList.add(tawwpExecuteContentVO); } }
				 */
			}
			Collections.sort(executeContentVOList, new Comparator() {
				public int compare(Object o1, Object o2) {
					TawwpExecuteContentVO p1 = (TawwpExecuteContentVO) o1;
					TawwpExecuteContentVO p2 = (TawwpExecuteContentVO) o2;
					int k = -1;
					if (p1.getName().compareTo(p2.getName()) > 0) {
						k = 1;
					} else if (p1.getName().compareTo(p2.getName()) == 0) {
						if (p1.getId().compareTo(p2.getId()) > 0) {
							k = 1;
						} else {
							k = -1;
						}
					} else if (p1.getName().compareTo(p2.getName()) < 0) {
						k = -1;
					}
					return k;

				}
			});

			// 杩斿洖鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄闆嗗悎
			return executeContentVOList;
		}

		catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵归噺濉啓鎵ц浣滀笟璁″垝鎵ц鍐呭鍑虹幇寮傚父");
		} finally {
			tawwpMonthExecutes = null;
		}
	}

	/**
	 * 涓氬姟閫昏緫锛氱紪杈戝懆銆佹湀鎶鏄剧ず(EDIT-MONTHLY-REPORT-001)
	 * 
	 * @param _executeReportId
	 *            String 鍛ㄣ�佹湀鎶ョ紪鍙�
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpExecuteReportVO 鍛ㄣ�佹湀鎶O瀵硅薄
	 */
	public TawwpExecuteReportVO editExecuteReportView(String _executeReportId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteReportDAO tawwpExecuteReportDAO = new
		// TawwpExecuteReportDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// 鍛ㄣ�佹湀鎶O瀵硅薄
		TawwpExecuteReportVO tawwpExecuteReportVO = null;
		// 鍛ㄣ�佹湀鎶ュ璞�
		TawwpExecuteReport tawwpExecuteReport = null;

		try {

			// 鑾峰彇鍛ㄣ�佹湀鎶ュ璞�
			tawwpExecuteReport = tawwpExecuteReportDao
					.loadExecuteReport(_executeReportId);

			// 灏佽鍛ㄣ�佹湀鎶O瀵硅薄
			tawwpExecuteReportVO = new TawwpExecuteReportVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteReportVO,
					tawwpExecuteReport);
			tawwpExecuteReportVO.setDeptName(tawwpUtilDAO
					.getDeptName(tawwpExecuteReportVO.getDeptId())); // 姹囨姤閮ㄩ棬鍚嶇О
			tawwpExecuteReportVO.setReportTypeName(TawwpExecuteReportVO
					.getReportTypeName(Integer.parseInt(tawwpExecuteReportVO
							.getReportType()))); // 姹囨姤绫诲瀷(鍛ㄣ�佹湀鎶�)
			tawwpExecuteReportVO.setReportUserName(tawwpUtilDAO
					.getUserName(tawwpExecuteReportVO.getReportUser())); // 姹囨姤浜哄鍚�
			tawwpExecuteReportVO.setReportFlagName(TawwpExecuteReportVO
					.getReportFlagName(Integer.parseInt(tawwpExecuteReportVO
							.getReportFlag()))); // 娉ㄦ剰鏍囧織

			// 杩斿洖鍛ㄣ�佹湀鎶O瀵硅薄
			return tawwpExecuteReportVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鍛�(鏈�)鎶ョ紪杈戝嚭鐜板紓甯�");
		}
	}

	/**
	 * 涓氬姟閫昏緫锛氱紪杈戝懆銆佹湀鎶淇濆瓨(EDIT-MONTHLY-REPORT-002)
	 * 
	 * @param _content
	 *            String 姹囨姤淇℃伅
	 * @param _reportFlag
	 *            String 娉ㄦ剰鏍囧織
	 * @param _remark
	 *            String 澶囨敞
	 * @param _advice
	 *            String 寤鸿
	 * @param _executeReportId
	 *            String 鍛ㄣ�佹湀鎶ョ紪鍙�
	 * @throws Exception
	 *             寮傚父
	 */
	public void editExecuteReportSave(String _content, String _reportFlag,
			String _remark, String _advice, String _executeReportId)
			throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteReportDAO tawwpExecuteReportDAO = new
		// TawwpExecuteReportDAO();

		// 鍛ㄣ�佹湀鎶ュ璞�
		TawwpExecuteReport tawwpExecuteReport = null;

		try {

			// 鑾峰彇鍛ㄣ�佹湀鎶ュ璞�
			tawwpExecuteReport = tawwpExecuteReportDao
					.loadExecuteReport(_executeReportId);

			// 灏佽鍛ㄦ姤(鎴栨湀鎶�)淇℃伅瀵硅薄
			tawwpExecuteReport.setContent(_content); // 姹囨姤鍐呭
			tawwpExecuteReport.setReportFlag(_reportFlag); // 娉ㄦ剰鏍囧織
			tawwpExecuteReport.setRemark(_remark); // 澶囨敞淇℃伅
			tawwpExecuteReport.setAdvice(_advice); // 寤鸿

			// 鏇存柊鍛ㄦ姤(鎴栨湀鎶�)淇℃伅
			tawwpExecuteReportDao.updateExecuteReport(tawwpExecuteReport);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鍛�(鏈�)鎶ョ紪杈戜繚瀛樺嚭鐜板紓甯�");
		}
	}

	/**
	 * 涓氬姟閫昏緫:鍒楄〃寰呰�冩牳鎵ц浣滀笟璁″垝(LIST-EXECUTE-ASSESSTING-001)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鍚�
	 * @throws Exception
	 *             寮傚父
	 * @return monthPlanVOHash 鏈堝害浣滀笟璁″垝VO瀵硅薄闆嗗悎
	 */
	public List listExecuteAssess(String _userId) throws Exception {

		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		// TawwpExecuteAssessDAO tawwpExecuteAssessDAO = new
		// TawwpExecuteAssessDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// 闆嗗悎瀵硅薄
		List list = null;
		List newList = new ArrayList();

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpExecuteAssess tawwpExecuteAssess = null;
		// VO瀵硅薄
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 鑾峰彇鎵ц浣滀笟璁″垝鑰冩牳淇℃伅闆嗗悎
			list = tawwpExecuteAssessDao.listExecuteAssessByUser(_userId);

			for (int i = 0; i < list.size(); i++) {

				// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
				tawwpExecuteAssess = (TawwpExecuteAssess) list.get(i);
				tawwpMonthPlan = tawwpExecuteAssess.getTawwpMonthPlan();

				tawwpMonthPlanVO = new TawwpMonthPlanVO();

				// 灏佽鏈堝害浣滀笟璁″垝VO瀵硅薄
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				// 鑾峰彇鎵ц绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteType())));
				// 鑾峰彇閮ㄩ棬鍚嶇О
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 鑾峰彇鍒涘缓浜哄鍚�
				tawwpMonthPlanVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));
				// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
				tawwpMonthPlanVO
						.setConstituteStateName(TawwpMonthPlanVO
								.getConstituteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getConstituteState())));
				// 鑾峰彇鎵ц鐘舵�佸悕绉�
				tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
						.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteState())));
				// 鑾峰彇鎵ц璐熻矗浜哄鍚�
				tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				// 鑾峰彇缃戝厓鍚嶇О
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}

				tawwpMonthPlanVO.setMonthCheckId(tawwpExecuteAssess.getId());

				// 灏嗘湀搴︿綔涓氳鍒扸O瀵硅薄娣诲姞杩涢泦鍚堜腑
				newList.add(tawwpMonthPlanVO);
				Collections.sort(newList, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
						TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
						int k = -1;
						if (p1.getName().compareTo(p2.getName()) > 0) {
							k = 1;
						} else if (p1.getName().compareTo(p2.getName()) == 0) {
							if (p1.getId().compareTo(p2.getId()) > 0) {
								k = 1;
							} else {
								k = -1;
							}
						} else if (p1.getName().compareTo(p2.getName()) < 0) {
							k = -1;
						}
						return k;

					}
				});
			}
			// 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄闆嗗悎
			return newList;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鑾峰彇寰呰�冩牳鎵ц浣滀笟璁″垝鍒楄〃鍑虹幇寮傚父");
		} finally {
			list = null;
		}
	}

	/**
	 * 涓氬姟閫昏緫:鏄剧ず鍚岀粍鎵ц椤甸潰(SHOW-EXECUTE-SAME-001)
	 * 
	 * @param _executeContentId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)缂栧彿
	 * @param _userId
	 *            String 褰撳墠鐢ㄦ埛鐧诲綍鍚�
	 * @throws Exception
	 *             寮傚父
	 * @return List 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)VO瀵硅薄
	 */
	public List ShowSameExecute(String _executeContentId, String _userId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)闆嗗悎
		Iterator tawwpExecuteContentUsers = null;

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)VO瀵硅薄闆嗗悎
		List executeContentUserVOList = new ArrayList();

		// 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)VO瀵硅薄
		TawwpExecuteContentUserVO tawwpExecuteContentUserVO = null;

		// 鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;

		String fileStr = "";

		try {

			// 鑾峰彇鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);

			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)闆嗗悎
			tawwpExecuteContentUsers = tawwpExecuteContent
					.getTawwpExecuteContentUsers().iterator();

			while (tawwpExecuteContentUsers.hasNext()) {
				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄闆嗗悎
				tawwpExecuteContentUser = (TawwpExecuteContentUser) (tawwpExecuteContentUsers
						.next());
				// 濡傛灉涓嶆槸鏈汉鎵ц鐨�
				if (!_userId.equals(tawwpExecuteContentUser.getCruser())) {
					tawwpExecuteContentUserVO = new TawwpExecuteContentUserVO();
					// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)VO瀵硅薄
					MyBeanUtils.copyPropertiesFromDBToPage(
							tawwpExecuteContentUserVO, tawwpExecuteContentUser);
					tawwpExecuteContentUserVO
							.setUserName(tawwpUtilDAO
									.getUserName(tawwpExecuteContentUserVO
											.getCruser())); // 鍒涘缓鐢ㄦ埛鍚�
					tawwpExecuteContentUserVO.setStubUserName(tawwpUtilDAO
							.getUserName(tawwpExecuteContentUserVO
									.getStubUser())); // 浠ｇ悊鎵ц鐢ㄦ埛鍚�
					if (tawwpExecuteContentUserVO.getEligibility().equals("")) {
						tawwpExecuteContentUserVO
								.setEligibilityName(TawwpExecuteContentUserVO
										.getEligibilityName(0)); // 鍚堟牸鏍囧織鍚嶇О
					} else {
						tawwpExecuteContentUserVO
								.setEligibilityName(TawwpExecuteContentUserVO
										.getEligibilityName(Integer
												.parseInt(tawwpExecuteContentUserVO
														.getEligibility()))); // 鍚堟牸鏍囧織鍚嶇О

					}

					tawwpExecuteContentUserVO
							.setExecuteFlagName(TawwpExecuteContentUserVO
									.getExecuteFlagName(Integer
											.parseInt(tawwpExecuteContentUserVO
													.getExecuteFlag()))); // 鎵ц鏍囧織鍚嶇О
					tawwpExecuteContentUserVO
							.setFormDataId(tawwpExecuteContentUser
									.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍
					tawwpExecuteContentUserVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpExecuteContentUserVO
									.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
					fileStr = this
							.getFileStrOfUserById(tawwpExecuteContentUser);
					tawwpExecuteContentUserVO.setFileName(StaticMethod
							.null2String(fileStr));
					if (!"".equals(fileStr)) {
						tawwpExecuteContentUserVO.setFileCount(fileStr
								.split(",").length);
					} else {
						tawwpExecuteContentUserVO.setFileCount(0);
					}

					// 娣诲姞鍒版墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)VO瀵硅薄闆嗗悎涓�
					executeContentUserVOList.add(tawwpExecuteContentUserVO);
				}
			}
			// 杩斿洖鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)VO瀵硅薄闆嗗悎
			return executeContentUserVOList;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭鍚岀粍鏌ョ湅鍑虹幇寮傚父");
		} finally {
			tawwpExecuteContentUsers = null;
		}
	}

	/**
	 * 涓氬姟閫昏緫锛氬崟鐙～鍐欐墽琛屼綔涓氳鍒掑唴瀹筥鏄剧ず(FILL-EXECUTE-CONTENT-004)
	 * 
	 * @param _executeContentId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpExecuteContentVO 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
	 */
	public TawwpExecuteContentVO addExecuteContentUserView(
			String _executeContentId) throws Exception {

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;

		try {

			// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);

			// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
			tawwpExecuteContentVO = new TawwpExecuteContentVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteContentVO,
					tawwpExecuteContent);

			/*
			 * 濡傛灉 1鏈墽琛屻�� 2鎴栬�呭凡缁忔墽琛屼笖鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,浣嗗綋鍓嶇敤鎴锋病鏈夎繘琛屽～鍐�
			 */
			tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
					.getCycleName(tawwpExecuteContentVO.getCycle())); // 鍛ㄦ湡鍚嶇О
			tawwpExecuteContentVO.setFormName(tawwpUtilDAO
					.getAddonsName(tawwpExecuteContentVO.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
			tawwpExecuteContentVO.setFileCount(0);

			if (tawwpExecuteContentVO.getFileName() == null) {
				tawwpExecuteContentVO.setFileName("");
			}
			if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
				tawwpExecuteContentVO.setExecuteContentUserId("");
			}
			tawwpExecuteContentVO.setRemark("");
			tawwpExecuteContentVO.setExtendremark(tawwpExecuteContent
					.getTawwpMonthExecute().getRemark());
			String acessories = "";
			String mothexecuteid = tawwpExecuteContent.getTawwpMonthExecute()
					.getId();
			TawwpMonthExecute monthexecute = tawwpMonthExecuteDao
					.loadMonthExecute(mothexecuteid);
			if (monthexecute.getTawwpYearExecute() != null) {
				String yearexecuteid = monthexecute.getTawwpYearExecute()
						.getId();
				TawwpYearExecute yearexecute = tawwpYearExecuteDao
						.loadYearExecute(yearexecuteid);
				if (yearexecute.getTawwpModelExecute() != null) {
					String modelexecuteid = yearexecute.getTawwpModelExecute()
							.getId();
					TawwpModelExecute modelexecute = tawwpModelExecuteDao
							.loadModelExecute(modelexecuteid);
					acessories = modelexecute.getAccessories();
				}

			}
			tawwpExecuteContentVO.setAccessories(monthexecute.getAccessories());

			// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
			return tawwpExecuteContentVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("娣诲姞鎵ц鍐呭椤甸潰鏄剧ず鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓氬姟閫昏緫锛氬崟鐙紪杈戞墽琛屼綔涓氳鍒掑唴瀹筥鏄剧ず(FILL-EXECUTE-CONTENT-006)
	 * 
	 * @param _executeContentId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)缂栧彿
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _executetype
	 *            String 鎵ц绫诲瀷
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpExecuteContentVO 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
	 */
	public TawwpExecuteContentVO editExecuteContentUserView(
			String _executeContentId, String _userId, String _executetype)
			throws Exception {

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;

		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		String fileStr = "";

		try {

			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);

			tawwpExecuteContentVO = new TawwpExecuteContentVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteContentVO,
					tawwpExecuteContent);

			/*
			 * 濡傛灉 1鏈墽琛屻�� 2鎴栬�呭凡缁忔墽琛屼笖鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,浣嗗綋鍓嶇敤鎴锋病鏈夎繘琛屽～鍐�
			 */
			tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
					.getCycleName(tawwpExecuteContentVO.getCycle())); // 鍛ㄦ湡鍚嶇О
			tawwpExecuteContentVO.setFormName(tawwpUtilDAO
					.getAddonsName(tawwpExecuteContentVO.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
			tawwpExecuteContentVO.setExtendremark(tawwpExecuteContent
					.getTawwpMonthExecute().getRemark());
			// 濡傛灉宸茬粡鎵ц涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠姐��
			if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
					&& "0".equals(_executetype)) {
				tawwpExecuteContentVO.setFormDataId(tawwpExecuteContent
						.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍
				fileStr = this.getFileStrOfContent(tawwpExecuteContent);
				tawwpExecuteContentVO.setFileName(StaticMethod
						.null2String(fileStr));
				if (!"".equals(fileStr)) {
					tawwpExecuteContentVO
							.setFileCount(fileStr.split(",").length);
				} else {
					tawwpExecuteContentVO.setFileCount(0);
				}
				TawwpExecuteContentUser executeuser = (TawwpExecuteContentUser) tawwpExecuteContent
						.getTawwpExecuteContentUsers().iterator().next();
				tawwpExecuteContentVO.setExecuteContentUserId(executeuser
						.getId());
				tawwpExecuteContentVO.setWriteDate(executeuser.getWriteDate());
				tawwpExecuteContentVO.setExtendremark(tawwpExecuteContent
						.getTawwpMonthExecute().getRemark());
			}

			/*
			 * 濡傛灉 1宸茬粡鎵ц銆� 2澶氫汉濉啓澶氫唤銆�
			 * 3鎵ц浜哄瓧绗︿覆(鎵ц鍐呭(鏁翠綋)鐨刢ruser瀛楁)涓惈鏈夊綋鍓嶇敤鎴�
			 */
			if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
					&& "1".equals(_executetype)
					&& ("," + tawwpExecuteContent.getCruser() + ",")
							.indexOf("," + _userId + ",") >= 0) {

				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鍐呭)瀵硅薄
				tawwpExecuteContentUser = tawwpExecuteContentUserDao
						.filterTawwpExecuteContentUser(_userId,
								tawwpExecuteContent);
				tawwpExecuteContentVO.setFormDataId(tawwpExecuteContentUser
						.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍
				tawwpExecuteContentVO.setCrtime(tawwpExecuteContentUser
						.getCrtime()); // 鎵ц鏃堕棿
				tawwpExecuteContentVO.setNormalFlagName(tawwpExecuteContentUser
						.getNormalFlag());
				tawwpExecuteContentVO.setWriteDate(tawwpExecuteContentUser
						.getWriteDate());
				fileStr = this.getFileStrOfUser(tawwpExecuteContentUser);
				tawwpExecuteContentVO.setFileName(StaticMethod
						.null2String(fileStr));
				if (!"".equals(fileStr)) {
					tawwpExecuteContentVO
							.setFileCount(fileStr.split(",").length);
				} else {
					tawwpExecuteContentVO.setFileCount(0);
				}
				tawwpExecuteContentVO
						.setExecuteContentUserId(tawwpExecuteContentUser
								.getId());
				tawwpExecuteContentVO.setContent(tawwpExecuteContentUser
						.getContent());
				tawwpExecuteContentVO.setRemark(tawwpExecuteContentUser
						.getRemark());
				tawwpExecuteContentVO.setExtendremark(tawwpExecuteContent
						.getTawwpMonthExecute().getRemark());
			}

			if (tawwpExecuteContentVO.getFileName() == null) {
				tawwpExecuteContentVO.setFileName("");
			}
			if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
				tawwpExecuteContentVO.setExecuteContentUserId("");
			}

			// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
			return tawwpExecuteContentVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("缂栬緫鎵ц鍐呭椤甸潰鏄剧ず鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓氬姟閫昏緫:鍗曠嫭缂栬緫鎵ц浣滀笟璁″垝鍐呭_淇濆瓨(FILL-EXECUTE-CONTENT-007)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _subUser
	 *            String 浠ｇ悊鐢ㄦ埛鐧诲綍鍚�
	 * @param _content
	 *            String 濉啓鐨勬墽琛屽唴瀹�
	 * @param _remark
	 *            String 澶囨敞淇℃伅
	 * @param _formDataId
	 *            String 闄勫姞琛ㄥ崟淇℃伅
	 * @param _eligibility
	 *            String 鍚堟牸鏍囧織
	 * @param _executeContentUserId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 * @throws Exception
	 *             寮傚父
	 */
	public void editExecuteContentUserSave(String _userId, String _subUser,
			String _writeDate, String _content, String _remark,
			String _formDataId, String _eligibility,
			String _executeContentUserId) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		try {

			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
			tawwpExecuteContentUser = tawwpExecuteContentUserDao
					.loadExecuteContentUser(_executeContentUserId);
			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentUser
					.getTawwpExecuteContent();
			// 鑾峰彇褰撳墠鏃堕棿
			String executeTime = TawwpUtil.getCurrentDateTime();
			// 瀹氫箟瓒呮椂鏍囧織
			String executeFlag = "";
			// 鍒ゆ柇濉啓鏄惁瓒呮椂
			if (this
					.judgeTimeOut(executeTime, tawwpExecuteContent.getEndDate())) {
				executeFlag = "1"; // 鎸夋椂

			} else {
				executeFlag = "2"; // 瓒呮椂
			}

			// --------------------------------------------------------------

			// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
			tawwpExecuteContentUser.setCruser(_userId); // 鍒涘缓浜�
			tawwpExecuteContentUser.setCrtime(executeTime); // 瀹炶鏃堕棿
			tawwpExecuteContentUser.setWriteDate(_writeDate);
			tawwpExecuteContentUser.setStubUser(_subUser); // 浠ｇ悊浜�
			tawwpExecuteContentUser.setContent(_content); // 鎵ц鍐呭
			tawwpExecuteContentUser.setRemark(_remark); // 澶囨敞
			tawwpExecuteContentUser.setFormDataId(_formDataId); // 闄勫姞琛ㄨ褰曠紪鍙�
			tawwpExecuteContentUser.setEligibility(_eligibility); // 鍚堟牸鏍囧織
			tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織

			// --------------------------------------------------------------
			tawwpExecuteContent.setCrtime(executeTime); // 瀹炶鏃堕棿edit by denden
			// 璐靛窞鏈湴
			tawwpExecuteContent
					.setContent(tawwpExecuteContentUser.getContent());
			// added by lijia 2005-11-29 鏇存柊澶囨敞瀛楁
			tawwpExecuteContent.setRemark(tawwpExecuteContentUser.getRemark());

			// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
			if ("".equals(tawwpExecuteContent.getFormDataId())) {
				tawwpExecuteContent.setFormDataId(_formDataId);
			}
			// 淇敼濉啓鏍囧織()
			if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
				// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
				tawwpExecuteContent.setExecuteFlag(executeFlag);
			} else if (!executeFlag
					.equals(tawwpExecuteContent.getExecuteFlag())) {
				if ("2".equals(executeFlag)) {
					/*
					 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
					 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
					 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 */
					tawwpExecuteContent.setExecuteFlag(executeFlag);
				}
			}

			// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
			if (!"".equals(tawwpExecuteContent.getCruser())) {
				if (("," + tawwpExecuteContent.getCruser() + ",").indexOf(","
						+ _userId + ",") < 0) {

					// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
					tawwpExecuteContent.setCruser(tawwpExecuteContent
							.getCruser()
							+ "," + _userId);
				}
			} else {
				// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
				tawwpExecuteContent.setCruser(_userId);
			}

			// --------------------------------------------------------------

			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
			tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);
			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
			tawwpExecuteContentUserDao
					.updateExecuteContentUser(tawwpExecuteContentUser);

		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭缂栬緫淇濆瓨淇濆瓨鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓氬姟閫昏緫:鍗曠嫭缂栬緫鎵ц浣滀笟璁″垝鍐呭_淇濆瓨(FILL-EXECUTE-CONTENT-007)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _subUser
	 *            String 浠ｇ悊鐢ㄦ埛鐧诲綍鍚�
	 * @param _content
	 *            String 濉啓鐨勬墽琛屽唴瀹�
	 * @param _remark
	 *            String 澶囨敞淇℃伅
	 * @param _formDataId
	 *            String 闄勫姞琛ㄥ崟淇℃伅
	 * @param _eligibility
	 *            String 鍚堟牸鏍囧織
	 * @param _executeContentUserId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 * @throws Exception
	 *             寮傚父
	 */
	public void editExecuteContentUserSave(String _userId, String _subUser,
			String _writeDate, String _content, String _remark,
			String _formDataId, String _eligibility,
			String _executeContentUserId, String _normalFlag, String _reason)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		try {

			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
			tawwpExecuteContentUser = tawwpExecuteContentUserDao
					.loadExecuteContentUser(_executeContentUserId);
			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentUser
					.getTawwpExecuteContent();
			// 鑾峰彇褰撳墠鏃堕棿
			String executeTime = TawwpUtil.getCurrentDateTime();
			// 瀹氫箟瓒呮椂鏍囧織
			String executeFlag = "";
			// 鍒ゆ柇濉啓鏄惁瓒呮椂
			if (this
					.judgeTimeOut(executeTime, tawwpExecuteContent.getEndDate())) {
				executeFlag = "1"; // 鎸夋椂

			} else {
				executeFlag = "2"; // 瓒呮椂
			}

			// --------------------------------------------------------------

			// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
			tawwpExecuteContentUser.setCruser(_userId); // 鍒涘缓浜�
			tawwpExecuteContentUser.setCrtime(executeTime); // 瀹炶鏃堕棿
			tawwpExecuteContentUser.setWriteDate(_writeDate);
			tawwpExecuteContentUser.setStubUser(_subUser); // 浠ｇ悊浜�
			tawwpExecuteContentUser.setContent(_content); // 鎵ц鍐呭
			tawwpExecuteContentUser.setRemark(_remark); // 澶囨敞
			tawwpExecuteContentUser.setFormDataId(_formDataId); // 闄勫姞琛ㄨ褰曠紪鍙�
			tawwpExecuteContentUser.setEligibility(_eligibility); // 鍚堟牸鏍囧織
			tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
			tawwpExecuteContentUser.setNormalFlag(_normalFlag); // 姝ｅ父
			tawwpExecuteContentUser.setReason(_reason);// 四川版本上的
			// --------------------------------------------------------------
			tawwpExecuteContent.setCrtime(executeTime); // 瀹炶鏃堕棿edit by denden
			// 璐靛窞鏈湴
			tawwpExecuteContent
					.setContent(tawwpExecuteContentUser.getContent());
			// added by lijia 2005-11-29 鏇存柊澶囨敞瀛楁
			tawwpExecuteContent.setRemark(tawwpExecuteContentUser.getRemark());

			// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
			if ("".equals(tawwpExecuteContent.getFormDataId())) {
				tawwpExecuteContent.setFormDataId(_formDataId);
			}
			// 淇敼濉啓鏍囧織()
			if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
				// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
				tawwpExecuteContent.setExecuteFlag(executeFlag);
			} else if (!executeFlag
					.equals(tawwpExecuteContent.getExecuteFlag())) {
				if ("2".equals(executeFlag)) {
					/*
					 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
					 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
					 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
					 */
					tawwpExecuteContent.setExecuteFlag(executeFlag);
				}
			}

			// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
			if (!"".equals(tawwpExecuteContent.getCruser())) {
				if (("," + tawwpExecuteContent.getCruser() + ",").indexOf(","
						+ _userId + ",") < 0) {

					// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
					tawwpExecuteContent.setCruser(tawwpExecuteContent
							.getCruser()
							+ "," + _userId);
				}
			} else {
				// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
				tawwpExecuteContent.setCruser(_userId);
			}

			// --------------------------------------------------------------

			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
			tawwpExecuteContent.setNormalFlag(_normalFlag);
			tawwpExecuteContent.setReason(_reason);// 四川版本上的
			tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);
			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
			tawwpExecuteContentUserDao
					.updateExecuteContentUser(tawwpExecuteContentUser);

		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭缂栬緫淇濆瓨淇濆瓨鍑虹幇寮傚父");
		}
	}

	/**
	 * 鍒ゆ柇鎸囧畾鎵ц鍐呭鐨勬墽琛屼汉鑼冨洿閲屾槸鍚﹀寘鍚�,褰撳墠鐢ㄦ埛鎴栨墍鍦ㄧ殑閮ㄩ棬銆佸矖浣嶃�佹満鎴�
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _deptId
	 *            String 閮ㄩ棬缂栧彿
	 * @param _roomId
	 *            String 鏈烘埧缂栧彿
	 * @param _tawwpMonthExecute
	 *            TawwpMonthExecute 鏈堝害浣滀笟璁″垝鎵ц鍐呭瀵硅薄
	 * @throws Exception
	 *             寮傚父
	 * @return boolean 鏄惁涓烘墽琛屼汉鏍囧織浣�
	 */
	private boolean judgeExecuter(String _userId, String _deptId,
			String _roomId, TawwpMonthExecute _tawwpMonthExecute)
			throws Exception {
		// 鍒濆鍖栨暟鎹�,鏄惁涓烘墽琛屼汉鏍囧織浣�
		boolean executerFlag = false;

		// 鎵ц浜虹被鍨嬩负"浜哄憳"
		if ("0".equals(_tawwpMonthExecute.getExecuterType())) {
			if (("," + _tawwpMonthExecute.getExecuter() + ",").indexOf(_userId) > 0) {
				executerFlag = true;
			}
		}
		// 鎵ц浜虹被鍨嬩负"閮ㄩ棬"
		else if ("1".equals(_tawwpMonthExecute.getExecuterType())) {
			if (("," + _tawwpMonthExecute.getExecuter() + ",").indexOf(_deptId) > 0) {
				executerFlag = true;
			}
		}
		// 鎵ц浜虹被鍨嬩负"宀椾綅"
		else if ("2".equals(_tawwpMonthExecute.getExecuterType())) {

			TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
			// 鑾峰彇褰撳墠鐢ㄦ埛鎵�灞炵殑宀椾綅缂栧彿闆嗗悎ccccccccccccccc
			// List _postIdList = tawwpUtilDAO.getOrgPostIdList(_userId);
			ArrayList _postIdList = new ArrayList();
			for (int i = 0; i < _postIdList.size(); i++) {
				if ((("," + _tawwpMonthExecute.getExecuter() + ",")
						.indexOf((String) _postIdList.get(i))) > 0) {
					executerFlag = true;
				}
			}
		}
		// 鎵ц浜虹被鍨嬩负"鏈烘埧"
		else if ("3".equals(_tawwpMonthExecute.getExecuterType())) {
			if (_roomId.equals(_tawwpMonthExecute.getExecuter())) {
				executerFlag = true;
			} else if (_tawwpMonthExecute.getExecuter().indexOf(",") >= 0) {
				String[] st = _tawwpMonthExecute.getExecuter().split(",");
				if (st.length > 0) {
					for (int i = 0; i < st.length; i++) {
						if (_roomId.equals(st[i])) {
							executerFlag = true;
						}
					}
				}
			}

		}

		// 杩斿洖鏄惁涓烘墽琛屼汉鏍囧織浣�
		return executerFlag;
	}

	/**
	 * 鑾峰彇鎵�鏈変笌鎸囧畾鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)鍏宠仈鐨勯檮浠朵俊鎭�
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
	 * @return String 闄勪欢淇℃伅
	 */
	private String getFileStrOfContent(TawwpExecuteContent _tawwpExecuteContent) {
		// 闄勪欢淇℃伅瀛楃涓�
		String fileStr = "";
		// 闄勪欢闆嗗悎
		Iterator tawwpExecuteFlies = null;
		// 闄勪欢瀵硅薄
		TawwpExecuteFile tawwpExecuteFile = null;
		// 鑾峰彇闄勪欢闆嗗悎
		tawwpExecuteFlies = _tawwpExecuteContent.getTawwpExecuteFiles()
				.iterator();
		while (tawwpExecuteFlies.hasNext()) {
			tawwpExecuteFile = (TawwpExecuteFile) tawwpExecuteFlies.next();
			// 缁勫悎闄勪欢淇℃伅瀛楃涓�
			fileStr += (tawwpExecuteFile.getFileName() + "@"
					+ "../tawwpexecute/filedown.do?fileId="
					+ tawwpExecuteFile.getFileCodeName() + ",");
		}
		if (fileStr != null && !"".equals(fileStr)) {
			// 闄勪欢鏁伴噺涓嶄负闆�
			fileStr = fileStr.substring(0, fileStr.length() - 1);
		}
		// 杩斿洖闄勪欢淇℃伅瀛楃涓�
		return fileStr;
	}

	/**
	 * 鑾峰彇鎵�鏈変笌鎸囧畾鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)鍏宠仈鐨勯檮浠朵俊鎭�
	 * 
	 * @param _tawwpExecuteContentUser
	 *            TawwpExecuteContent 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
	 * @return String 闄勪欢淇℃伅
	 */
	private String getFileStrOfUser(
			TawwpExecuteContentUser _tawwpExecuteContentUser) {
		// 闄勪欢淇℃伅瀛楃涓�
		String fileStr = "";
		// 闄勪欢闆嗗悎
		Iterator tawwpExecuteFlies = null;
		// 闄勪欢瀵硅薄
		TawwpExecuteFile tawwpExecuteFile = null;
	
		// 鑾峰彇闄勪欢闆嗗悎
		tawwpExecuteFlies = _tawwpExecuteContentUser.getTawwpExecuteFiles()
				.iterator();
		String realPath = AccessoriesMgrLocator.getAccessoriesAttributes()
				.getUploadPath();// 四川版本上的
		while (tawwpExecuteFlies.hasNext()) {
			tawwpExecuteFile = (TawwpExecuteFile) tawwpExecuteFlies.next();
			fileStr += (tawwpExecuteFile.getFileName() + "@"
					+ "../tawwpexecute/filedown.do?fileId="
					+ tawwpExecuteFile.getFileCodeName() + ",");
		}
		if (fileStr != null && !"".equals(fileStr)) {
			// 闄勪欢鏁伴噺涓嶄负闆�
			fileStr = fileStr.substring(0, fileStr.length() - 1);
		}
		// 杩斿洖闄勪欢淇℃伅瀛楃涓�
		return fileStr;
	}

	/**
	 * 涓氬姟閫昏緫:淇濆瓨涓婁紶鐨勯檮浠�(UPLOAD-FILE-001)
	 * 
	 * @param _fileName
	 *            String 鏂囦欢鍚嶇О
	 * @param _fileCodeName
	 *            String 鏂囦欢瀛樻斁鍚嶇О
	 * @param _fileSize
	 *            String 鏂囦欢澶у皬
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _executeContentId
	 *            String 鎵ц鍐呭(鏁翠綋)
	 * @param _executeContentUserId
	 *            String 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
	 * @param _subUser
	 *            String 浠ｇ悊鎵ц浜虹櫥褰曞悕
	 * @throws Exception
	 *             寮傚父
	 * @return String 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 */
	public String uploadFile(String _fileName, String _fileCodeName,
			String _fileSize, String _userId, String _executeContentId,
			String _executeContentUserId, String _subUser) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();
		// TawwpExecuteFileDAO tawwpExecuteFileDAO = new TawwpExecuteFileDAO();
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		// 闄勪欢瀵硅薄
		TawwpExecuteFile tawwpExecuteFile = null;

		// HibernateUtil.currentCommitSession();

		try {

			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);

			// 濡傛灉鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄灏氭湭瀛樺湪
			if (_executeContentUserId == null
					|| "".equals(_executeContentUserId)) {

				if (_subUser == null || "null".equals(_subUser)) {
					_subUser = "";
				}

				// --------------------------------------------------------------

				tawwpExecuteContentUser = new TawwpExecuteContentUser(
						tawwpExecuteContent.getStartDate(), tawwpExecuteContent
								.getEndDate(), TawwpUtil.getCurrentDateTime(),
						TawwpUtil.getCurrentDateTime(), _userId, _subUser, "",
						"", "0", "0", tawwpExecuteContent.getFormId(), "",
						tawwpExecuteContent, "1", "");

				// --------------------------------------------------------------

				// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
				tawwpExecuteContentUserDao
						.saveExecuteContentUser(tawwpExecuteContentUser);

				// 灏佽闄勪欢瀵硅薄
				tawwpExecuteFile = new TawwpExecuteFile(_fileName,
						_fileCodeName, _fileSize, TawwpUtil
								.getCurrentDateTime(), _userId,
						tawwpExecuteContent, tawwpExecuteContentUser);

				tawwpExecuteFileDao.saveExecuteFile(tawwpExecuteFile);

				return (tawwpExecuteContentUser.getId());
			}
			// 濡傛灉浠ョ粡瀛樺湪
			else {
				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
				tawwpExecuteContentUser = tawwpExecuteContentUserDao
						.loadExecuteContentUser(_executeContentUserId);

				// 灏佽闄勪欢瀵硅薄
				tawwpExecuteFile = new TawwpExecuteFile(_fileName,
						_fileCodeName, _fileSize, TawwpUtil
								.getCurrentDateTime(), _userId,
						tawwpExecuteContent, tawwpExecuteContentUser);

				tawwpExecuteFileDao.saveExecuteFile(tawwpExecuteFile);

				return (_executeContentUserId);
			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			// HibernateUtil.rollbackTransaction();
			throw new TawwpException("闄勪欢涓婁紶鍑虹幇寮傚父");
		} finally {
			// HibernateUtil.closeCommitSession();
		}
	}

	// 四川版本上的
	public TawwpExecuteFile loadFile(String fileId) throws Exception {
		TawwpExecuteFile file = tawwpExecuteFileDao.loadExecuteFile(fileId);
		return file;
	}

	/**
	 * 涓氬姟閫昏緫:鍒犻櫎涓婁紶鐨勯檮浠�(REMOVE-FILE-001)
	 * 
	 * @param _fileCodeName
	 *            String 鏂囦欢瀛樻斁鍚嶇О
	 * @param _executeContentId
	 *            String 鎵ц鍐呭(鏁翠綋)
	 * @throws Exception
	 *             寮傚父
	 */
	public void removeFile(String _fileCodeName, String _executeContentId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteFileDAO tawwpExecuteFileDAO = new TawwpExecuteFileDAO();
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		// 闄勪欢瀵硅薄
		TawwpExecuteFile tawwpExecuteFile = null;

		// HibernateUtil.currentCommitSession();

		try {

			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);

			// 鑾峰彇闄勪欢瀵硅薄
			tawwpExecuteFile = tawwpExecuteFileDao.filterTawwpExecuteFile(
					_fileCodeName, tawwpExecuteContent);

			// 鍒犻櫎闄勪欢璁板綍
			tawwpExecuteFileDao.deleteExecuteFile(tawwpExecuteFile);

			// HibernateUtil.commitTransaction();
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			// HibernateUtil.rollbackTransaction();
			throw new TawwpException("闄勪欢鍒犻櫎鍑虹幇寮傚父");
		} finally {
			// HibernateUtil.closeCommitSession();
		}
	}

	/**
	 * 涓氬姟閫昏緫锛氭樉绀哄綋鍓嶇敤鎴蜂负鎵ц璐熻矗浜虹殑鎵ц浣滀笟璁″垝(SHOW-EXECUTE-CONTENT-003)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @throws Exception
	 *             寮傚父
	 * @return Hashtable 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄闆嗗悎
	 */
	public Hashtable listExecutePlan(String _userId) throws Exception {

		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthPlanList = new ArrayList();

		// Hashtable瀵硅薄
		Hashtable monthPlanVOHash = new Hashtable();

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 鍙栧緱鏈堝害浣滀笟璁″垝鍒楄〃
			monthPlanList = tawwpMonthPlanDao.listMonthPlan(_userId);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthPlanList.size(); i++) {

				// 鍙栧嚭model瀵硅薄
				tawwpMonthPlan = (TawwpMonthPlan) (monthPlanList.get(i));

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				// 杩涜VO瀵硅薄鐨勫皝瑁�
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				// 鑾峰彇鎵ц绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteType())));
				// 鑾峰彇閮ㄩ棬鍚嶇О
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 鑾峰彇鍒涘缓浜哄鍚�
				tawwpMonthPlanVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));
				// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
				tawwpMonthPlanVO
						.setConstituteStateName(TawwpMonthPlanVO
								.getConstituteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getConstituteState())));
				// 鑾峰彇鎵ц鐘舵�佸悕绉�
				tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
						.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteState())));
				// 鑾峰彇鎵ц璐熻矗浜哄鍚�
				tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				// 鑾峰彇缃戝厓鍚嶇О
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}

				// 灏哣O瀵硅薄娣诲姞鍒癏ashtable瀵硅薄涓�
				monthPlanVOHash.put(tawwpMonthPlan.getId(), tawwpMonthPlanVO);
			}

			// --------------------------------------------------------------------

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凥ashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず璐熻矗浜烘墽琛屼綔涓氳鍒掑嚭鐜板紓甯�");
		} finally {
			monthPlanList = null;
		}
	}

	/**
	 * 鑾峰彇鎵�鏈変笌鎸囧畾鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)鍏宠仈鐨勯檮浠朵俊鎭�(鍚湁model缂栧彿)
	 * 
	 * @param _tawwpExecuteContentUser
	 *            TawwpExecuteContent 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
	 * @return String 闄勪欢淇℃伅
	 */
	private String getFileStrOfUserById(
			TawwpExecuteContentUser _tawwpExecuteContentUser) {
		// 闄勪欢淇℃伅瀛楃涓�
		String fileStr = "";
		// 闄勪欢闆嗗悎
		Iterator tawwpExecuteFlies = null;
		// 闄勪欢瀵硅薄
		TawwpExecuteFile tawwpExecuteFile = null;
		// 鑾峰彇闄勪欢闆嗗悎
		tawwpExecuteFlies = _tawwpExecuteContentUser.getTawwpExecuteFiles()
				.iterator();
		while (tawwpExecuteFlies.hasNext()) {
			tawwpExecuteFile = (TawwpExecuteFile) tawwpExecuteFlies.next();
			// 缁勫悎闄勪欢淇℃伅瀛楃涓�
			fileStr += (tawwpExecuteFile.getFileName() + "@"
					+ "../tawwpexecute/filedown.do?fileId="
					+ tawwpExecuteFile.getFileCodeName() + "@"
					+ tawwpExecuteFile.getId() + ",");
		}
		if (fileStr != null && !"".equals(fileStr)) {
			// 闄勪欢鏁伴噺涓嶄负闆�
			fileStr = fileStr.substring(0, fileStr.length() - 1);
		}
		// 杩斿洖闄勪欢淇℃伅瀛楃涓�
		return fileStr;
	}

	/**
	 * 
	 * @param _year
	 *            String
	 * @param _month
	 *            String
	 * @param _day
	 *            String
	 * @param _user
	 *            String
	 * @param _user
	 *            String
	 * @return Hashtable
	 * @throws Exception
	 */
	public Hashtable countExecute(String _userId, String _deptId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		TawwpExecuteCountVO tawwpExecuteCountVO = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		Hashtable hashtable = new Hashtable();
		String orgPostIdStr = "";// ccccccccccccccccccccc
		// tawwpUtilDAO.getOrgPostIdStr(_userId);
		String currentDay = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
		String cruser = "";
		List list = tawwpExecuteContentDao.countExecuteContent(_userId,
				_deptId, orgPostIdStr, currentDay);
		String executer = "";
		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteContent = (TawwpExecuteContent) list.get(i);
			executer = "," + tawwpExecuteContent.getExecuter() + ",";
			cruser = "," + tawwpExecuteContent.getCruser() + ",";
			if (tawwpExecuteContent.getMonthPlanExecuteFlag().equals("1")
					&& cruser.indexOf("," + _userId + ",") <= 0
					&& cruser.indexOf("," + _deptId + ",") <= 0) {
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
				if (executer.indexOf("," + _userId + ",") >= 0
						|| executer.indexOf("," + _deptId + ",") >= 0) {
					if (hashtable.get(tawwpMonthPlan.getId()) == null) {
						tawwpExecuteCountVO = new TawwpExecuteCountVO();
						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// tawwpMonthPlanVO.setNetName(tawwpMonthPlan.getTawwpNet().getName());
						tawwpExecuteCountVO
								.setTawwpMonthPlanVO(tawwpMonthPlanVO);
						hashtable.put(tawwpMonthPlan.getId(),
								tawwpExecuteCountVO);
					} else {
						tawwpExecuteCountVO = (TawwpExecuteCountVO) hashtable
								.get(tawwpMonthPlan.getId());
					}
					tawwpExecuteCountVO.setDayExecute(tawwpExecuteCountVO
							.getDayExecute() + 1);
					// 四川版本无此if
					if (tawwpExecuteContent.getExecuterType().equals("3")) {
						tawwpExecuteCountVO.setDutyExecute(1);
					}
				}
			} else if (tawwpExecuteContent.getMonthPlanExecuteFlag()
					.equals("1")
					&& cruser.indexOf("," + _userId + ",") > 0
					&& cruser.indexOf("," + _deptId + ",") > 0) {
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
				if (executer.indexOf("," + _userId + ",") > 0
						|| executer.indexOf("," + _deptId + ",") > 0) {
					if (hashtable.get(tawwpMonthPlan.getId()) == null) {
						tawwpExecuteCountVO = new TawwpExecuteCountVO();
						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// tawwpMonthPlanVO.setNetName(tawwpMonthPlan.getTawwpNet().getName());
						tawwpExecuteCountVO
								.setTawwpMonthPlanVO(tawwpMonthPlanVO);
						hashtable.put(tawwpMonthPlan.getId(),
								tawwpExecuteCountVO);
					} else {
						tawwpExecuteCountVO = (TawwpExecuteCountVO) hashtable
								.get(tawwpMonthPlan.getId());
					}
					tawwpExecuteCountVO.setDayExecute(tawwpExecuteCountVO
							.getDayExecute() + 1);
					// 四川版本无此if
					if (tawwpExecuteContent.getExecuterType().equals("3")) {
						tawwpExecuteCountVO.setDutyExecute(1);
					}
				}
			} else {
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
				if (tawwpExecuteContent.getExecuteFlag().equals("0")
						&& executer.indexOf("," + _userId + ",") >= 0
						|| executer.indexOf("," + _deptId + ",") >= 0) {
					if (hashtable.get(tawwpMonthPlan.getId()) == null) {
						tawwpExecuteCountVO = new TawwpExecuteCountVO();
						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// tawwpMonthPlanVO.setNetName(tawwpMonthPlan.getTawwpNet().getName());
						tawwpExecuteCountVO
								.setTawwpMonthPlanVO(tawwpMonthPlanVO);
						hashtable.put(tawwpMonthPlan.getId(),
								tawwpExecuteCountVO);
					} else {
						tawwpExecuteCountVO = (TawwpExecuteCountVO) hashtable
								.get(tawwpMonthPlan.getId());
					}
					tawwpExecuteCountVO.setDayExecute(tawwpExecuteCountVO
							.getDayExecute() + 1);
					// 四川版本无此if
					if (tawwpExecuteContent.getExecuterType().equals("3")) {
						tawwpExecuteCountVO.setDutyExecute(1);
					}
				}
			}
		}

		return hashtable;
	}

	/**
	 * added by lijia 2005-11-28 涓氬姟閫昏緫锛氱‘璁ゆ墽琛屼綔涓氳鍒�
	 * 
	 * @param _monthExecuteUserId
	 *            String 鏈堝害浣滀笟璁″垝鎵ц浜轰俊鎭紪鍙�
	 * @throws Exception
	 *             寮傚父
	 */
	public void confirm(String _monthExecuteUserId) throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpMonthExecuteUserDAO tawwpMonthExecuteUserDAO = new
		// TawwpMonthExecuteUserDAO();

		// model瀵硅薄
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;

		try {

			tawwpMonthExecuteUser = tawwpMonthExecuteUserDao
					.loadMonthExecuteUser(_monthExecuteUserId);

			// 灏佽model瀵硅薄
			tawwpMonthExecuteUser.setState("4"); // 鐘舵�佷慨鏀逛负宸茬‘璁�;

			// 鏇存柊鏈堝害浣滀笟璁″垝鎵ц浜轰俊鎭�
			tawwpMonthExecuteUserDao
					.updateMonthExecuteUser(tawwpMonthExecuteUser);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝纭鍑虹幇寮傚父");
		}
	}

	/**
	 * 涓婃姤姣忔棩浣滀笟璁″垝鎵ц鐨勯檮鍔犺〃
	 * 
	 * @param _day
	 *            String add by scropioD
	 */
	public void reportDayExecute(String _day) throws Exception {

		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpNet tawwpNet = null;

		try {
			List list = tawwpExecuteContentDao.getTawwpPlanByDayExecute(_day);
			for (int i = 0; i < list.size(); i++) {
				tawwpExecuteContent = (TawwpExecuteContent) list.get(i);
				// 闄勫姞琛ㄥ鍑�
				String formDataId = tawwpExecuteContent.getFormDataId();
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
				tawwpNet = tawwpMonthPlan.getTawwpNet();
				String unicomType = StaticMethod.null2String(tawwpMonthPlan
						.getUnicomType());
				String serialNo = StaticMethod.null2String(tawwpExecuteContent
						.getTawwpMonthExecute().getSerialNo());
				if (null != tawwpNet && !serialNo.equals("")
						&& !unicomType.equals("")) {
					// 鐢熸垚excel鏂囦欢鍚�
					String crFileName = "RF-"
							+ WorkplanMgrLocator.getAttributes()
									.getNewstrregioncode() + "-"
							+ _day.replaceAll("-", "") + "-"
							+ tawwpExecuteContent.getCycle() + "-" + unicomType
							+ "-" + serialNo + "-" + tawwpNet.getSerialNo()
							+ ".xls";
					// 瀵煎嚭鏂囦欢
					exportAddonsToReportExcel(formDataId, crFileName, _day,
							unicomType);
				}

			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 瀵煎嚭涓婃姤闇�瑕乪xcel鏂囦欢
	 * 
	 * @param _sourceXML
	 *            String
	 * @throws TawwpException
	 * @throws IOException
	 * @throws Exception
	 * @return String add by scropioD
	 */
	public void exportAddonsToReportExcel(String _sourceEXCEL,
			String _fileName, String _day, String _unicomType)
			throws TawwpException, IOException, Exception {

		String newDir = _day.replaceAll("-", "");
		// 涓婃姤璺緞
		String wwwDir = TawwpStaticVariable.wwwDir; // 浠庨潤鎬佸彉閲忚鍙栫洰褰�
		String unicomType = _unicomType;
		TawwpUtil.mkDir(wwwDir + TawwpStaticVariable.dayPath + newDir + "/"
				+ unicomType);
		// 涓婃姤鏂囦欢
		String exportExcel = wwwDir + TawwpStaticVariable.dayPath + newDir
				+ "/" + unicomType + "/" + _fileName;
		// 鍘熸枃浠�
		String sourceExcel = wwwDir + "workplan/tawwpfile/execute/"
				+ _sourceEXCEL + ".xls";
		// 灏嗗師鏂囦欢鐨勫唴瀹瑰鍒跺埌涓婃姤鏂囦欢涓�
		StaticMethod.copyFile(sourceExcel, exportExcel);

	}

	/*
	 * public void reportExcel(String _dayTime) { String dayPath = ""; boolean
	 * submitflag = true;
	 * 
	 * try { dayPath = TawwpStaticVariable.rootDir + TawwpStaticVariable.dayPath +
	 * _dayTime.replaceAll("-", "") + "/"; java.io.File File = new
	 * java.io.File(dayPath); //璇诲彇涓撲笟绫诲瀷 String[] fileTypeArray = File.list();
	 * for (int i = 0; i < fileTypeArray.length; i++) { String fileType =
	 * fileTypeArray[i]; dayPath = dayPath + fileType + "/"; //璇诲彇鐩綍涓嬬殑鏂囦欢
	 * java.io.File f = new java.io.File(dayPath); String[] fileArray =
	 * f.list(); //澹版槑涓�涓柊鐨勬暟缁勫璞� AttachInfoType[] attachInfo = new
	 * AttachInfoType[fileArray.length]; //鏁扮粍澶嶅埗 for (int j = 0; j <
	 * fileArray.length; j++) { String fileName = fileArray[j]; java.io.File
	 * excel = new java.io.File(dayPath + fileName); AttachInfoType
	 * attachInfoType = new AttachInfoType(); attachInfoType.setAttachLength(
	 * (int) (excel.length())); attachInfoType.setAttachName(fileName);
	 * attachInfoType.setAttachURL(TawwpStaticVariable.serverIp +
	 * TawwpStaticVariable.dayPath + _dayTime.replaceAll("-", "") + "/" +
	 * fileType + "/" + fileName); attachInfo[j] = attachInfoType; } //璋冪敤鏈嶅姟 if
	 * (attachInfo.length != 0) { AttachInfoListType attachInfoListType = new
	 * AttachInfoListType(); attachInfoListType.setAttachInfo(attachInfo); try {
	 * submitflag = reportExecutePortReport(attachInfoListType); } catch
	 * (Exception ex2) { ex2.printStackTrace(); BocoLog.error(this, 0,
	 * "璋冪敤鎺ュ彛寮傚父"); } } else { BocoLog.error(this, 0, "鏈鍙栧埌鏂囦欢"); } if
	 * (submitflag) { BocoLog.debug(this, 0, _dayTime + " " + fileType + "涓撲笟" +
	 * "浣滀笟璁″垝excel涓婃姤缁撴潫"); } } } catch (Exception ex) { ex.printStackTrace(); } }
	 */

	/**
	 * 璋冪敤涓婃姤鎺ュ彛
	 * 
	 * @param attachInfoListType
	 *            AttachInfoListType
	 * @throws Exception
	 * @return boolean
	 * 
	 * public boolean reportExecutePortReport(AttachInfoListType
	 * attachInfoListType) throws Exception {
	 * com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
	 * boolean flag = false; try { binding =
	 * (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub) new
	 * com.boco.eoms.gzjhhead.interfaces.ReportExecutePortLocator().
	 * getReportExecutePort(); } catch (javax.xml.rpc.ServiceException jre) { if
	 * (jre.getLinkedCause() != null) { jre.getLinkedCause().printStackTrace(); }
	 * throw new junit.framework.AssertionFailedError( "JAX-RPC ServiceException
	 * caught: " + jre); }
	 * 
	 * //Time out after a minute binding.setTimeout(100000);
	 * 
	 * try { com.boco.eoms.gzjhhead.interfaces.ReportFormResponse value = null;
	 * ReportFormRequest reportFormRequest = new ReportFormRequest();
	 * ReportTypeType reportTypeType = new ReportTypeType(1);
	 * 
	 * reportTypeType.setValue(0);
	 * reportFormRequest.setCodeA(StaticMethod.getNodeName("STRREGIONCODE"));
	 * reportFormRequest.setCodeB("ZB");
	 * reportFormRequest.setAttNum(attachInfoListType.getAttachInfo().length);
	 * reportFormRequest.setAttachInfoList(attachInfoListType);
	 * reportFormRequest.setNoteAssign("澶囨敞");
	 * reportFormRequest.setReportType(reportTypeType);
	 * 
	 * value = binding.reportForm(reportFormRequest); if
	 * (value.getResultReportForm() == null) { return true; } else {
	 * System.out.print(value.getResultReportForm()); } } catch
	 * (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) { throw new
	 * junit.framework.AssertionFailedError( "ReportFormFault Exception caught: " +
	 * e1); }
	 * 
	 * return flag; }
	 */

	/**
	 * 涓婃姤鐩綍涓嬬殑鏂囦欢
	 * 
	 * @param _Dir
	 *            String add by scropioD
	 * 
	 * public void reportDir(String _Dir) { String dayPath = ""; boolean
	 * submitflag = true;
	 * 
	 * try { if (_Dir.equals("")) { _Dir = TawwpStaticVariable.rootDir +
	 * TawwpStaticVariable.dayPath + "/temp/"; } dayPath =
	 * TawwpStaticVariable.rootDir + TawwpStaticVariable.dayPath + "/" + _Dir +
	 * "/"; java.io.File f = new java.io.File(dayPath); String[] fileArray =
	 * f.list(); //澹版槑涓�涓柊鐨勬暟缁勫璞� AttachInfoType[] attachInfo = new
	 * AttachInfoType[fileArray.length]; //鏁扮粍澶嶅埗 for (int j = 0; j <
	 * fileArray.length; j++) { String fileName = fileArray[j]; java.io.File
	 * excel = new java.io.File(dayPath + fileName); AttachInfoType
	 * attachInfoType = new AttachInfoType(); attachInfoType.setAttachLength(
	 * (int) (excel.length())); attachInfoType.setAttachName(fileName);
	 * attachInfoType.setAttachURL(TawwpStaticVariable.serverIp +
	 * TawwpStaticVariable.dayPath + fileName); attachInfo[j] = attachInfoType; }
	 * //璋冪敤鏈嶅姟 if (attachInfo.length != 0) { AttachInfoListType
	 * attachInfoListType = new AttachInfoListType();
	 * attachInfoListType.setAttachInfo(attachInfo); try { submitflag =
	 * reportExecutePortReport(attachInfoListType); } catch (Exception ex2) {
	 * ex2.printStackTrace(); BocoLog.error(this, 0, "璋冪敤鎺ュ彛寮傚父"); } } else {
	 * BocoLog.error(this, 0, "鏈鍙栧埌鏂囦欢"); } if (submitflag) {
	 * BocoLog.debug(this, 0, "浣滀笟璁″垝鐩綍涓婃姤缁撴潫"); } } catch (Exception ex) {
	 * ex.printStackTrace(); } }
	 */

	public void specialRename(String _dayTime, String _specialPath) {
		String dayPath = "";
		boolean submitflag = true;

		try {
			dayPath = TawwpStaticVariable.rootDir + TawwpStaticVariable.dayPath
					+ "/" + _specialPath + "/";
			java.io.File f = new java.io.File(dayPath);
			String[] fileArray = f.list();
			// 婧愭枃浠舵敼鍚嶄负褰撳墠鏃ユ湡
			for (int j = 0; j < fileArray.length; j++) {
				String orgionName = fileArray[j];
				String newName = orgionName.substring(0, 6)
						+ _dayTime.replaceAll("-", "")
						+ orgionName.substring(14);
				java.io.File excel = new java.io.File(dayPath + orgionName);
				excel.renameTo(new java.io.File(dayPath + newName));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			BocoLog.error(this, 1, "鏂囦欢鏀瑰悕鏃跺嚭鐜板紓甯�");
		}

	}

	public Hashtable newlistExecutePlan(String _userId) throws Exception {

		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthPlanList = new ArrayList();

		// Hashtable瀵硅薄
		Hashtable monthPlanVOHash = new Hashtable();

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 鍙栧緱鏈堝害浣滀笟璁″垝鍒楄〃
			monthPlanList = tawwpMonthPlanDao.listMonthPlan(_userId);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthPlanList.size(); i++) {

				// 鍙栧嚭model瀵硅薄
				tawwpMonthPlan = (TawwpMonthPlan) (monthPlanList.get(i));

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				// 杩涜VO瀵硅薄鐨勫皝瑁�
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				// 鑾峰彇鎵ц绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteType())));
				// 鑾峰彇閮ㄩ棬鍚嶇О
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 鑾峰彇鍒涘缓浜哄鍚�
				tawwpMonthPlanVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));
				// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
				tawwpMonthPlanVO
						.setConstituteStateName(TawwpMonthPlanVO
								.getConstituteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getConstituteState())));
				// 鑾峰彇鎵ц鐘舵�佸悕绉�
				tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
						.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteState())));
				// 鑾峰彇鎵ц璐熻矗浜哄鍚�
				tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				// 鑾峰彇缃戝厓鍚嶇О
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}

				// 灏哣O瀵硅薄娣诲姞鍒癏ashtable瀵硅薄涓�
				monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);
			}

			// --------------------------------------------------------------------

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凥ashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず璐熻矗浜烘墽琛屼綔涓氳鍒掑嚭鐜板紓甯�");
		} finally {
			monthPlanList = null;
		}
	}

	public Hashtable newlistALLExecutePlan(String _deptId, String _yearFlag,
			String _monthFlag) throws Exception {

		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthPlanList = new ArrayList();

		// Hashtable瀵硅薄
		Hashtable monthPlanVOHash = new Hashtable();

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 鍙栧緱鏈堝害浣滀笟璁″垝鍒楄〃
			monthPlanList = tawwpMonthPlanDao.listMonthPlan(_deptId, _yearFlag,
					_monthFlag);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthPlanList.size(); i++) {

				// 鍙栧嚭model瀵硅薄
				tawwpMonthPlan = (TawwpMonthPlan) (monthPlanList.get(i));

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				// 杩涜VO瀵硅薄鐨勫皝瑁�
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				// 鑾峰彇鎵ц绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteType())));
				// 鑾峰彇閮ㄩ棬鍚嶇О
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 鑾峰彇鍒涘缓浜哄鍚�
				tawwpMonthPlanVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));
				// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
				tawwpMonthPlanVO
						.setConstituteStateName(TawwpMonthPlanVO
								.getConstituteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getConstituteState())));
				// 鑾峰彇鎵ц鐘舵�佸悕绉�
				tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
						.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteState())));
				// 鑾峰彇鎵ц璐熻矗浜哄鍚�
				tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				// 鑾峰彇缃戝厓鍚嶇О
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}

				// 灏哣O瀵硅薄娣诲姞鍒癏ashtable瀵硅薄涓�
				monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);
			}

			// --------------------------------------------------------------------

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凥ashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず璐熻矗浜烘墽琛屼綔涓氳鍒掑嚭鐜板紓甯�");
		} finally {
			monthPlanList = null;
		}
	}

	public void saveExcel(String id, String formDataId) throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		try {
			// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
			// TawwpExecuteContentDAO();
			TawwpExecuteContent tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(id);
			tawwpExecuteContent.setFormDataId(formDataId);
			tawwpExecuteContentDao.saveExecuteContent(tawwpExecuteContent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("浣滀笟璁″垝闄勫姞琛ㄦ墽琛屽唴瀹瑰嚭鐜板紓甯�");
		}
	}

	public TawwpExecuteContent loadExecuteContent(String id) throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent();
		try {

			// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
			// TawwpExecuteContentDAO();
			tawwpExecuteContent = tawwpExecuteContentDao.loadExecuteContent(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("浣滀笟璁″垝wap鎵ц鍐呭鍑虹幇寮傚父");
		}
		return tawwpExecuteContent;
	}

	/**
	 * 绌轰腑杩愮淮wap鎵ц浣滀笟璁″垝
	 * 
	 * @param id
	 *            鎵ц鍐呭id
	 * @param 濉啓鐨勬墽琛屽唴瀹�
	 * @param 鎵ц浜篿d
	 * @throws Exception
	 */
	public void wapExecute(String _id, String _executeContent, String _user)
			throws TawwpException {

		TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent();
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		try {
			// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_id);
			tawwpExecuteContent.setContent(_executeContent);

			tawwpExecuteContent.setExecuter(_user);
			tawwpExecuteContentDao.saveExecuteContent(tawwpExecuteContent);

		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("浣滀笟璁″垝wap鎵ц鍐呭鍑虹幇寮傚父");
		}
	}

	/**
	 * @param tawwpExecuteFileDao
	 *            the tawwpExecuteFileDao to set
	 */
	public void setTawwpExecuteFileDao(ITawwpExecuteFileDao tawwpExecuteFileDao) {
		this.tawwpExecuteFileDao = tawwpExecuteFileDao;
	}

	// ext

	public String addExecuteContentUserSave(String _userId, String _subUser,
			String _remark, String _formDataId, String _eligibility,
			String _executeContentUserId, String _executeContentId,
			String _reason) throws Exception {

		// TawwpTaskManageInterfaceExtendBO tawwpTaskManageInterfaceExtendBO =
		// new TawwpTaskManageInterfaceExtendBO();
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpNet tawwpNet = null;

		String content = null;
		String executeContentUserId = null;

		tawwpExecuteContent = tawwpExecuteContentDao
				.loadExecuteContent(_executeContentId);
		tawwpNet = tawwpExecuteContent.getTawwpMonthPlan().getTawwpNet();

		// 鑾峰彇鎺ュ彛鐨勬墽琛屽唴瀹规棩蹇�
		/*
		 * content = tawwpTaskManageInterfaceMgr.saveExecuteInfor(
		 * tawwpExecuteContent.getCommand(), tawwpNet, tawwpExecuteContent
		 * .getStartDate()); org.w3c.dom.Document _doc =
		 * tawwpTaskManageInterfaceMgr
		 * .getExecuteInfor(tawwpExecuteContent.getCommand(), tawwpNet,
		 * tawwpExecuteContent.getStartDate()); tawwpExecuteContent =
		 * tawwpExecuteContentDao .loadExecuteContent(_executeContentId);
		 * tawwpNet = tawwpExecuteContent.getTawwpMonthPlan().getTawwpNet();
		 */

		org.w3c.dom.Document _doc = tawwpTaskManageInterfaceMgr
				.getExecuteInfor(tawwpExecuteContent.getCommand(), tawwpNet,
						tawwpExecuteContent.getStartDate());

		if (_doc != null) {

			executeContentUserId = this.addInterfaceContentUserSave(_userId,
					_subUser, _doc, _remark, _formDataId, _eligibility,
					_executeContentUserId, _executeContentId, _reason);
		}

		/*
		 * executeContentUserId = this.addExecuteContentUserSave(_userId,
		 * _subUser, TawwpUtil.getCurrentDateTime(), content, _remark,
		 * _formDataId, _eligibility, _executeContentUserId, _executeContentId);
		 */

		if (_doc != null) {

			executeContentUserId = this.addInterfaceContentUserSave(_userId,
					_subUser, _doc, _remark, _formDataId, _eligibility,
					_executeContentUserId, _executeContentId, _reason);
		}

		/*
		 * executeContentUserId = this.addExecuteContentUserSave(_userId,
		 * _subUser, TawwpUtil.getCurrentDateTime(), content, _remark,
		 * _formDataId, _eligibility, _executeContentUserId, _executeContentId);
		 */

		return executeContentUserId;
	}

	/**
	 * @param tawwpTaskManageInterfaceMgr
	 *            the tawwpTaskManageInterfaceMgr to set
	 */
	public void setTawwpTaskManageInterfaceMgr(
			ITawwpTaskManageInterfaceMgr tawwpTaskManageInterfaceMgr) {
		this.tawwpTaskManageInterfaceMgr = tawwpTaskManageInterfaceMgr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.mgr.ITawwpExecuteMgr#getContent(java.lang.String)
	 */
	public List getContent(String id) {

		return this.tawwpExecuteContentDao.getContent(id);
	}

	/**
	 * 鏍规嵁鏃堕棿鍜屾満鎴垮緱鍒板伐浣滆鍒掑垪琛�
	 * 
	 * @param start_time
	 *            String 寮�濮嬫椂闂�
	 * @param end_time
	 *            String 缁撴潫鏃堕棿
	 * @param roomId
	 *            String 鏈烘埧id
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 */
	public List getExecuteContentList(String start_time, String end_time,
			String roomId) {
		return this.tawwpExecuteContentDao.getExecuteContentList(start_time,
				end_time, roomId);
	}

	public TawwpExecuteFile loadExecuteFile(String id) {
		return this.tawwpExecuteFileDao.loadExecuteFile(id);
	}

	/**
	 * 
	 */
	public void updateExecute(String executer, String executerType,
			String monthExecutes) {
		this.tawwpExecuteContentDao.updateExecute(executer, executerType,
				monthExecutes);
	}

	public List listExecuteContent(String condition) {
		return this.tawwpExecuteContentDao.listExecuteContent(condition);
	}
	
	public List listExecuteContent(String condition,String workplanId) {
		return this.tawwpExecuteContentDao.listExecuteContent(condition,workplanId);
	}

	public ArrayList searchByContent(String value, String startDate,
			String endDate) {
		ArrayList result = new ArrayList();
		List list = this.tawwpExecuteContentDao.searchByContent(startDate,
				endDate);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				TawwpExecuteContent content = (TawwpExecuteContent) list.get(i);
				if (isIn(value, content.getFormDataId())) {
					result.add(content);
				}
			}
		}
		return result;
	}

	// 四川版本
	public TawwpExecuteFile loadExecuteFileByCode(String id) {
		return this.tawwpExecuteFileDao.loadExecuteFileByCode(id);
	}

	public boolean isIn(String value, String fileName) {

		boolean result = false;
		try {
			SAXBuilder sb = new SAXBuilder();

			org.jdom.Document doc = sb.build(new FileInputStream(
					TawwpStaticVariable.rootDir
							+ TawwpStaticVariable.rootSaveXMLDir + "50/"
							+ fileName + ".xml"));

			org.jdom.Element root = doc.getRootElement();

			org.jdom.Element body = root.getChild("body");

			Element elements = body.getChild("elements");

			Element emterm = elements.getChild("emterm");

			List elementList = emterm.getChildren("element");

			if (elementList != null) {
				for (int i = 0; i < elementList.size(); i++) {

					Element element = (Element) elementList.get(i);

					String elementValue = element.getAttributeValue("value");

					if (elementValue.indexOf(value) != -1) {
						result = true;
						break;
					}
				}
			}

		} catch (Exception e) {

			System.out.print(e);
		}
		return result;
	}

	public int previous(String _userId, String _currUser, String _deptId,
			String _roomId, String _monthPlanId, String _dutyStartDate,
			String _dutyEndDate, String _flag, String _stubFlag, String _date,
			int countVar) {
		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContent currExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 闆嗗悎瀵硅薄
		Iterator tawwpMonthExecutes = null;
		Hashtable executeContentUserHash = new Hashtable();
		Hashtable formHash = new Hashtable();

		String executerType = ""; // 鎵ц绫诲瀷
		String executeDate = ""; // 鎵ц鏃堕棿
		String formDataId = ""; // 闄勫姞琛ㄨ褰曠紪鍙�
		String yesterday = ""; // 涓婁竴鏃ユ湡鏃堕棿

		// 鑾峰彇纭洏瀹為檯鐩綍
		String rootSaveEXCELDir = "workplan/tawwpfile/execute/";

		try {

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄

			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			// 濡傛灉_date涓虹┖,琛ㄧず涓哄綋鏃ョ殑鎵归噺澶嶅埗
			if ("".equals(_date)) {
				executeDate = TawwpUtil.getCurrentDateTime();
			}

			// 涓嶄负绌哄垯琛ㄧず涓烘寚瀹氭棩鏈熺殑鎵归噺澶嶅埗
			else {
				executeDate = TawwpUtil.getCurrentDateTime(tawwpMonthPlan
						.getYearFlag()
						+ "-"
						+ tawwpMonthPlan.getMonthFlag()
						+ "-"
						+ _date
						+ " HH:mm:ss");
			}

			// 鑾峰彇涓婁竴鏃ユ椂闂�
			if ("dailyview".equalsIgnoreCase(_flag.trim())
					|| "daily".equalsIgnoreCase(_flag.trim())) {
				yesterday = this.getPreviousDateTime(executeDate);
			} else if ("duty".equalsIgnoreCase(_flag.trim())) {
				yesterday = this.getPreviousDateTime(_dutyStartDate);
			}

			// 鍙栧緱鎵�鏈夊叧鑱斾簬鎸囧畾鏈堝害浣滀笟璁″垝鐨勬湀搴︽墽琛屽唴瀹�

			// 瀵硅繖浜涘唴瀹硅繘琛屽惊鐜鐞�
			while (tawwpMonthExecutes.hasNext()) {

				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());

				// 杩囨护鎺夊懆鏈熼潪"澶�"鐨勬墽琛屽唴瀹�
				if ("1".equals(tawwpMonthExecute.getCycle())) {

					executerType = tawwpMonthExecute.getExecuterType(); // 鎵ц浜虹被鍨�

					// 濡傛灉鎵ц绫诲瀷涓烘棩甯镐笖鎵ц浜虹被鍨嬩笉涓�"鏈烘埧"
					if (("dailyview".equalsIgnoreCase(_flag.trim()) || "daily"
							.equalsIgnoreCase(_flag.trim()))
							&& !"3".equals(executerType)) {

						// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
						if (this.judgeExecuter(_userId, _deptId, _roomId,
								tawwpMonthExecute)) {

							// 鑾峰彇涓婁竴鏃ユ墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)瀵硅薄,鐢ㄤ簬澶嶅埗
							tawwpExecuteContent = tawwpExecuteContentDao
									.filterTawwpExecuteContent(yesterday,
											tawwpMonthExecute);

							// 鑾峰彇鏈棩鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄,鐢ㄤ簬澶嶅埗
							currExecuteContent = tawwpExecuteContentDao
									.filterTawwpExecuteContent(executeDate,
											tawwpMonthExecute);

							if (tawwpExecuteContent == null
									|| "0".equals(tawwpExecuteContent
											.getExecuteFlag())) {
								// 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鎴栨湭鎵ц,鍒欑粨鏉熸娆″惊鐜�
								continue;
							}

							// 濡傛灉鏈墽琛�,涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠�,鐩存帴浠庢墽琛屽唴瀹�(鏁翠綋)瀵硅薄涓彇鐢ㄥ嵆鍙�
							if ("0".equals(currExecuteContent.getExecuteFlag())
									&& "0".equals(tawwpMonthPlan
											.getExecuteType())) {

								// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
								tawwpExecuteContentUser = new TawwpExecuteContentUser();
								tawwpExecuteContentUser
										.setContent(tawwpExecuteContent
												.getContent()); // 鎵ц鍐呭 add by
								// jql
								tawwpExecuteContentUser
										.setRemark(tawwpExecuteContent
												.getRemark()); // 澶囨敞
								// 濡傛灉濉啓杩囬檮鍔犺〃
								if (tawwpExecuteContent.getFormDataId() != null
										&& !"".equals(tawwpExecuteContent
												.getFormDataId())) {

									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
									if (formHash.get(currExecuteContent
											.getFormId()) == null) {

										// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
										formDataId = TawwpUtil
												.getCurrentDateTime("yyyyMMddHHmmss")
												+ "c"
												+ _currUser
												+ String.valueOf(countVar);

										/*
										 * 瀹炵幇闄勫姞琛ㄨ褰曟枃浠舵嫹璐�,
										 * 闄勫姞琛ㄦ牸寮忔湁鍙樺寲锛岀敱鍘熸潵鐨刋ML鍙樹负EXCEL銆�
										 * 闄勫姞琛ㄤ繚瀛樿矾寰勬湁鍙樺寲銆� 鎷疯礉鐨勬柟寮忎篃鏈夊彉鍖栥��
										 * ---- 鏆傛椂鍙槸鐩存帴澶嶅埗 ----
										 */
										String oldurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ tawwpExecuteContent
														.getFormDataId()
												+ ".xls";
										String newurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ formDataId
												+ ".xls";
										this.copyAddonsFile(oldurl, newurl);
										formHash.put(currExecuteContent
												.getFormId(), formDataId);
									}
									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
									else {
										formDataId = (String) formHash
												.get(currExecuteContent
														.getFormId());
									}
									System.out.println(tawwpExecuteContentUser
											.getContent()
											+ "---" + formDataId);
									tawwpExecuteContentUser
											.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
								}

								// 濡傛灉涓轰唬鐞嗘墽琛�
								if ("stub".equals(_stubFlag)) {
									tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
									tawwpExecuteContentUser
											.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
								}
								// 鍚﹀垯
								else {
									tawwpExecuteContentUser
											.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
									tawwpExecuteContentUser.setStubUser("");
								}

								// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
								executeContentUserHash.put(currExecuteContent
										.getId(), tawwpExecuteContentUser);
							}

							// 涓嶇鏄惁宸茬粡鎵ц,鍙鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,鑰屼笖褰撳墠鐢ㄦ埛娌℃湁杩涜濉啓
							else if ("1"
									.equals(tawwpMonthPlan.getExecuteType())
									&& ("," + currExecuteContent.getCruser() + ",")
											.indexOf("," + _userId + ",") < 0) {

								// 鍙栧嚭涓婁竴鏃ユ墽琛岀殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛瀵硅薄)
								TawwpExecuteContentUser tempVar = tawwpExecuteContentUserDao
										.filterTawwpExecuteContentUser(_userId,
												tawwpExecuteContent);

								// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
								tawwpExecuteContentUser = new TawwpExecuteContentUser();
								tawwpExecuteContentUser.setContent(tempVar
										.getContent()); // 鎵ц鍐呭
								tawwpExecuteContentUser.setRemark(tempVar
										.getRemark()); // 澶囨敞

								// 濡傛灉鎵ц杩囬檮鍔犺〃
								if (tempVar.getFormDataId() != null
										&& !"".equals(tempVar.getFormDataId())) {

									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
									if (formHash.get(currExecuteContent
											.getFormId()) == null) {

										// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
										formDataId = TawwpUtil
												.getCurrentDateTime("yyyyMMddHHmmss")
												+ "c"
												+ _currUser
												+ String.valueOf(countVar);

										/*
										 * 瀹炵幇闄勫姞琛ㄨ褰曟枃浠舵嫹璐�,
										 * 闄勫姞琛ㄦ牸寮忔湁鍙樺寲锛岀敱鍘熸潵鐨刋ML鍙樹负EXCEL銆�
										 * 闄勫姞琛ㄤ繚瀛樿矾寰勬湁鍙樺寲銆� 鎷疯礉鐨勬柟寮忎篃鏈夊彉鍖栥��
										 * ---- 鏆傛椂鍙槸鐩存帴澶嶅埗 ----
										 */
										String oldurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ tawwpExecuteContent
														.getFormDataId()
												+ ".xls";
										String newurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ formDataId
												+ ".xls";
										this.copyAddonsFile(oldurl, newurl);
										formHash.put(currExecuteContent
												.getFormId(), formDataId);
									}
									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
									else {
										formDataId = (String) formHash
												.get(currExecuteContent
														.getFormId());
									}

									tawwpExecuteContentUser
											.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
								}

								// 濡傛灉涓轰唬鐞嗘墽琛�
								if ("stub".equals(_stubFlag)) {
									tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
									tawwpExecuteContentUser
											.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
								}
								// 鍚﹀垯
								else {
									tawwpExecuteContentUser
											.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
									tawwpExecuteContentUser.setStubUser("");
								}

								// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
								executeContentUserHash.put(currExecuteContent
										.getId(), tawwpExecuteContentUser);

							} else {
								/*
								 * 宸茬粡鎵ц鐨勫垯涓嶈繘琛岃鐩栨搷浣�,
								 * 濡傛灉闇�瑕佽鍦ㄦ澶勬坊鍔犳潯浠跺垽鏂強鐩稿簲浠ｇ爜
								 */
							}

							// 璁℃暟鍣ㄥ姞涓�
							countVar++;

						}
					}
					/*
					 * 濡傛灉 1鏍囧織涓�"鍊肩彮"銆� 2鎵ц鍐呭鐨勬墽琛屼汉绫诲瀷涓哄�肩彮浜恒��
					 * 3鎵ц鐝(鏃堕棿鐐�),鍦ㄥ綋鍓嶇敤鎴风櫥褰曠殑鍊肩彮鐝寮�濮嬫椂闂村拰缁撴潫鏃堕棿涔嬮棿
					 */
					else if ("duty".equals(_flag)
							&& "3".equals(tawwpMonthExecute.getExecuterType())
							&& (this.judgeDutyMonthPlan(tawwpMonthExecute
									.getExecuteDuty(), _dutyStartDate,
									_dutyEndDate))) {

						// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
						if (this.judgeExecuter(_userId, _deptId, _roomId,
								tawwpMonthExecute)) {

							// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContent = tawwpExecuteContentDao
									.filterTawwpExecuteContent(yesterday,
											tawwpMonthExecute);

							// 鑾峰彇鏈棩鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄,鐢ㄤ簬澶嶅埗
							currExecuteContent = tawwpExecuteContentDao
									.filterTawwpExecuteContent(executeDate,
											tawwpMonthExecute);

							if (tawwpExecuteContent == null
									|| "0".equals(tawwpExecuteContent
											.getExecuteFlag())) {
								// 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鎴栨湭鎵ц,鍒欑粨鏉熸娆″惊鐜�
								continue;
							}

							// 濡傛灉鏈墽琛�,涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠�,鐩存帴浠庢墽琛屽唴瀹�(鏁翠綋)瀵硅薄涓彇鐢ㄥ嵆鍙�
							if ("0".equals(currExecuteContent.getExecuteFlag())
									&& "0".equals(tawwpMonthPlan
											.getExecuteType())) {

								// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
								tawwpExecuteContentUser = new TawwpExecuteContentUser();
								tawwpExecuteContentUser
										.setContent(tawwpExecuteContent
												.getContent()); // 鎵ц鍐呭
								tawwpExecuteContentUser
										.setRemark(tawwpExecuteContent
												.getRemark()); // 澶囨敞

								// 濡傛灉濉啓杩囬檮鍔犺〃
								if (tawwpExecuteContent.getFormDataId() != null
										&& !"".equals(tawwpExecuteContent
												.getFormDataId())) {

									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
									if (formHash.get(currExecuteContent
											.getFormId()) == null) {

										// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
										formDataId = TawwpUtil
												.getCurrentDateTime("yyyyMMddHHmmss")
												+ "c"
												+ _currUser
												+ String.valueOf(countVar);

										/*
										 * 瀹炵幇闄勫姞琛ㄨ褰曟枃浠舵嫹璐�,
										 * 闄勫姞琛ㄦ牸寮忔湁鍙樺寲锛岀敱鍘熸潵鐨刋ML鍙樹负EXCEL銆�
										 * 闄勫姞琛ㄤ繚瀛樿矾寰勬湁鍙樺寲銆� 鎷疯礉鐨勬柟寮忎篃鏈夊彉鍖栥��
										 * ---- 鏆傛椂鍙槸鐩存帴澶嶅埗 ----
										 */
										String oldurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ tawwpExecuteContent
														.getFormDataId()
												+ ".xls";
										String newurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ formDataId
												+ ".xls";
										this.copyAddonsFile(oldurl, newurl);
										formHash.put(currExecuteContent
												.getFormId(), formDataId);
									}
									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
									else {
										formDataId = (String) formHash
												.get(currExecuteContent
														.getFormId());
									}

									tawwpExecuteContentUser
											.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
								}

								// 濡傛灉涓轰唬鐞嗘墽琛�
								if ("stub".equals(_stubFlag)) {
									tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
									tawwpExecuteContentUser
											.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
								}
								// 鍚﹀垯
								else {
									tawwpExecuteContentUser
											.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
									tawwpExecuteContentUser.setStubUser("");
								}

								// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
								executeContentUserHash.put(currExecuteContent
										.getId(), tawwpExecuteContentUser);
							}

							// 涓嶇鏄惁宸茬粡鎵ц,鍙鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,鑰屼笖褰撳墠鐢ㄦ埛娌℃湁杩涜濉啓
							else if ("1"
									.equals(tawwpMonthPlan.getExecuteType())
									&& ("," + currExecuteContent.getCruser() + ",")
											.indexOf("," + _userId + ",") < 0) {

								// 鍙栧嚭涓婁竴鏃ユ墽琛岀殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛瀵硅薄)
								TawwpExecuteContentUser tempVar = tawwpExecuteContentUserDao
										.filterTawwpExecuteContentUser(_userId,
												tawwpExecuteContent);

								// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
								tawwpExecuteContentUser = new TawwpExecuteContentUser();
								tawwpExecuteContentUser.setContent(tempVar
										.getContent()); // 鎵ц鍐呭
								tawwpExecuteContentUser.setRemark(tempVar
										.getRemark()); // 澶囨敞

								// 濡傛灉鎵ц杩囬檮鍔犺〃
								if (tempVar.getFormDataId() != null
										&& !"".equals(tempVar.getFormDataId())) {

									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
									if (formHash.get(currExecuteContent
											.getFormId()) == null) {

										// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
										formDataId = TawwpUtil
												.getCurrentDateTime("yyyyMMddHHmmss")
												+ "c"
												+ _currUser
												+ String.valueOf(countVar);

										/*
										 * 瀹炵幇闄勫姞琛ㄨ褰曟枃浠舵嫹璐�,
										 * 闄勫姞琛ㄦ牸寮忔湁鍙樺寲锛岀敱鍘熸潵鐨刋ML鍙樹负EXCEL銆�
										 * 闄勫姞琛ㄤ繚瀛樿矾寰勬湁鍙樺寲銆� 鎷疯礉鐨勬柟寮忎篃鏈夊彉鍖栥��
										 * ---- 鏆傛椂鍙槸鐩存帴澶嶅埗 ----
										 */
										String oldurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ tawwpExecuteContent
														.getFormDataId()
												+ ".xls";
										String newurl = TawwpStaticVariable.wwwDir
												+ rootSaveEXCELDir
												+ formDataId
												+ ".xls";
										this.copyAddonsFile(oldurl, newurl);
										formHash.put(currExecuteContent
												.getFormId(), formDataId);
										;
									}
									// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
									else {
										formDataId = (String) formHash
												.get(currExecuteContent
														.getFormId());
									}

									tawwpExecuteContentUser
											.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
								}

								// 濡傛灉涓轰唬鐞嗘墽琛�
								if ("stub".equals(_stubFlag)) {
									tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
									tawwpExecuteContentUser
											.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
								}
								// 鍚﹀垯
								else {
									tawwpExecuteContentUser
											.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
									tawwpExecuteContentUser.setStubUser("");
								}

								// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
								executeContentUserHash.put(currExecuteContent
										.getId(), tawwpExecuteContentUser);

							} else {
								/*
								 * 宸茬粡鎵ц鐨勫垯涓嶈繘琛岃鐩栨搷浣�,
								 * 濡傛灉闇�瑕佽鍦ㄦ澶勬坊鍔犳潯浠跺垽鏂強鐩稿簲浠ｇ爜
								 */
							}

							// 璁℃暟鍣ㄥ姞涓�
							countVar++;

						}
					}
				}
			}

			// 杩涜澶嶅埗鎿嶄綔
			if (executeContentUserHash.size() > 0) {
				if ("duty".equals(_flag)) {
					// 淇濆瓨鍊肩彮鎵归噺濉啓鍐呭
					this.addDutyExecuteContentUsersSave(executeContentUserHash,
							_dutyStartDate, _dutyEndDate, _currUser,
							tawwpMonthPlan.getExecuteType());
				} else {
					// 淇濆瓨鏃ュ父鎵归噺濉啓鍐呭
					this.addExecuteContentUsersSave(executeContentUserHash,
							_currUser, tawwpMonthPlan.getExecuteType());
				}
			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			// throw new TawwpException("鎵归噺澶嶅埗鎵ц浣滀笟璁″垝鎵ц鍐呭鍑虹幇寮傚父");
		}
		return countVar; // add by jql
	}

	private void copyAddonsFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 鏂囦欢瀛樺湪鏃�
				InputStream inStream = new FileInputStream(oldPath); // 璇诲叆鍘熸枃浠�
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 瀛楄妭鏁� 鏂囦欢澶у皬
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("澶嶅埗闄勫姞琛ㄦ枃浠舵搷浣滃嚭閿�");
			e.printStackTrace();

		}
	}

	/**
	 * added by lijia 2005-12-29 涓氬姟閫昏緫锛氳幏鍙栧墠涓�鏃ユ椂闂�
	 * 
	 * @param _sourceDate
	 *            String 婧愭椂闂村瓧绗︿覆
	 * @return 鏃堕棿瀛楃涓�
	 */
	private static String getPreviousDateTime(String _sourceDate) {
		// 鍒濆鍖栨暟鎹�
		String previousDateTime = "";
		String format = "yyyy-MM-dd HH:mm:ss"; // 璁剧疆鏍煎紡
		try {
			SimpleDateFormat dtFormat = new SimpleDateFormat(format);
			Date date = dtFormat.parse(_sourceDate);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.add(java.util.Calendar.DAY_OF_MONTH, -1); // 鏃ュ噺涓�
			date = cl.getTime(); // 鑾峰彇鏃堕棿绫诲瀷瀵硅薄
			previousDateTime = dtFormat.format(date); // 杞崲鎴愬瓧绗︿覆
		} catch (Exception e) {
			System.out.println("鏃堕棿鏍煎紡涓嶆纭�");
			e.printStackTrace();
		}
		return previousDateTime; // 杩斿洖鍓嶄竴鏃ユ湡鏃堕棿
	}

	public List listExeConUserforAddons(String modelplanid, String cruser,
			String formid, String startDate, String endDate) {
		List list = tawwpExecuteContentUserDao.listExeConUserforAddons(
				modelplanid, cruser, formid, startDate, endDate);
		return list;
	}

	/**
	 * 涓婃姤鐩綍涓嬬殑鏂囦欢
	 * 
	 * @param _Dir
	 *            String add by scropioD
	 */
	public void reportDir(String _Dir) {
		String Path = "";
		boolean submitflag = true;
		String url = "";

		try {
			if (_Dir.equals("")) {
				_Dir = TawwpStaticVariable.wwwDir + TawwpStaticVariable.dayPath
						+ "/temp/";
			}
			if (_Dir.equals("year")) {
				Path = TawwpStaticVariable.wwwDir
						+ TawwpStaticVariable.yearPath;
				url = TawwpStaticVariable.serverIp
						+ TawwpStaticVariable.yearPath;
			}
			if (_Dir.equals("month")) {
				Path = TawwpStaticVariable.wwwDir
						+ TawwpStaticVariable.monthPath;
				url = TawwpStaticVariable.serverIp
						+ TawwpStaticVariable.monthPath;
			}
			String dayPath = "";

			String dayPath1 = Path;
			java.io.File File = new java.io.File(dayPath1);
			// 璇诲彇涓撲笟绫诲瀷
			String[] fileTypeArray = File.list();
			for (int i = 0; i < fileTypeArray.length; i++) {
				String fileType = fileTypeArray[i];
				dayPath = dayPath1 + fileType + "/";
				// 璇诲彇鐩綍涓嬬殑鏂囦欢
				java.io.File f = new java.io.File(dayPath);
				String[] fileArray = f.list();
				// 澹版槑涓�涓柊鐨勬暟缁勫璞�
				if (fileArray != null) {
					AttachInfoType[] attachInfo = new AttachInfoType[fileArray.length];
					// 鏁扮粍澶嶅埗
					for (int j = 0; j < fileArray.length; j++) {
						String fileName = fileArray[j];
						java.io.File excel = new java.io.File(dayPath
								+ fileName);
						AttachInfoType attachInfoType = new AttachInfoType();
						attachInfoType.setAttachLength((int) (excel.length()));
						attachInfoType.setAttachName(fileName);
						attachInfoType.setAttachURL(url + "/" + fileType + "/"
								+ fileName);
						attachInfo[j] = attachInfoType;
					}
					// 璋冪敤鏈嶅姟
					if (attachInfo.length != 0) {
						AttachInfoListType attachInfoListType = new AttachInfoListType();
						attachInfoListType.setAttachInfo(attachInfo);
						try {
							submitflag = reportExecutePortReport(attachInfoListType);
						} catch (Exception ex2) {
							ex2.printStackTrace();
							BocoLog.error(this, 0, "璋冪敤鎺ュ彛寮傚父");
						}
					} else {
						BocoLog.error(this, 0, "鏈鍙栧埌鏂囦欢");
					}
					if (submitflag) {
						BocoLog.debug(this, 0, " " + fileType + "涓撲笟"
								+ "浣滀笟璁″垝excel涓婃姤缁撴潫");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void newReportDir(String _Dir) throws Exception {
		String Path = "";
		boolean submitflag = false;
		String url = "";

		TawwpNewLog tawwpNewLog = new TawwpNewLog();

		Date dateNow = new Date();

		tawwpNewLog.setCreateTime(dateNow);

		tawwpNewLog.setLogType("浣滀笟璁″垝鎬婚儴鎺ュ彛涓婃姤");

		if (_Dir.equals("year")) {

			tawwpNewLog.setLogType("骞村害浣滀笟璁″垝鎬婚儴鎺ュ彛涓婃姤");

		} else if (_Dir.equals("month")) {

			tawwpNewLog.setLogType("鏈堝害浣滀笟璁″垝鎬婚儴鎺ュ彛涓婃姤");

		}

		StringBuffer fileLog = new StringBuffer("");

		String submitStr = null;

		// haojian_add_end
		try {
			if (_Dir.equals("")) {
				_Dir = TawwpStaticVariable.wwwDir + TawwpStaticVariable.dayPath
						+ "/temp/";
			}
			if (_Dir.equals("year")) {
				Path = TawwpStaticVariable.wwwDir
						+ TawwpStaticVariable.yearPath;
				url = TawwpStaticVariable.serverIp
						+ TawwpStaticVariable.yearPath;
			}
			if (_Dir.equals("month")) {
				Path = TawwpStaticVariable.wwwDir
						+ TawwpStaticVariable.monthPath;
				url = TawwpStaticVariable.serverIp
						+ TawwpStaticVariable.monthPath;
			}
			String dayPath = "";

			String dayPath1 = Path;
			java.io.File File = new java.io.File(dayPath1);
			// 璇诲彇涓撲笟绫诲瀷
			String[] fileTypeArray = File.list();
			for (int i = 0; i < fileTypeArray.length; i++) {
				String fileType = fileTypeArray[i];
				dayPath = dayPath1 + fileType + "/";
				// 璇诲彇鐩綍涓嬬殑鏂囦欢
				java.io.File f = new java.io.File(dayPath);
				String[] fileArray = f.list();
				// 澹版槑涓�涓柊鐨勬暟缁勫璞�
				if (fileArray != null) {
					com.boco.eoms.gzjhead.interfaces.AttachInfoType[] attachInfo = new com.boco.eoms.gzjhead.interfaces.AttachInfoType[fileArray.length];
					// 鏁扮粍澶嶅埗
					for (int j = 0; j < fileArray.length; j++) {
						String fileName = fileArray[j];
						java.io.File excel = new java.io.File(dayPath
								+ fileName);
						com.boco.eoms.gzjhead.interfaces.AttachInfoType attachInfoType = new com.boco.eoms.gzjhead.interfaces.AttachInfoType();
						attachInfoType.setAttachLength((int) (excel.length()));
						attachInfoType.setAttachName(fileName);
						attachInfoType.setAttachURL(url + "/" + fileType + "/"
								+ URLEncoder.encode(fileName, "GB2312"));
						attachInfo[j] = attachInfoType;
						System.out.println("filename=" + url + "/" + fileType
								+ "/" + fileName);

					}
					// haojian_add_start
					for (int w = 0; w < attachInfo.length; w++) {

						fileLog.append("绗�" + w + "璁板綍:");
						fileLog.append("attachInfo.AttachName:"
								+ attachInfo[w].getAttachName());

						fileLog.append("attachInfo.AttachLength:"
								+ attachInfo[w].getAttachLength());
						fileLog.append("attachInfo.AttachURL:"
								+ attachInfo[w].getAttachURL());

					}
					// haojian_add_end
					// 璋冪敤鏈嶅姟
					if (attachInfo.length != 0) {
						com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoListType = new com.boco.eoms.gzjhead.interfaces.AttachInfoListType();
						attachInfoListType.setAttachInfo(attachInfo);
						try {
							submitStr = newReportExecutePortReportNew(attachInfoListType);
						} catch (Exception ex2) {
							// haojian_add
							tawwpNewLog.setResultState("璋冪敤鎺ュ彛寮傚父");
							ex2.printStackTrace();
							BocoLog.error(this, 0, "璋冪敤鎺ュ彛寮傚父");
						}
					} else {
						// haojian_add
						tawwpNewLog.setResultState("鏈鍙栧埌鏂囦欢");
						BocoLog.error(this, 0, "鏈鍙栧埌鏂囦欢");
					}
					if (submitStr == null) {
						tawwpNewLog.setResultState(" " + fileType + "涓撲笟"
								+ "浣滀笟璁″垝excel涓婃姤缁撴潫");
						BocoLog.debug(this, 0, " " + fileType + "涓撲笟"
								+ "浣滀笟璁″垝excel涓婃姤缁撴潫");
					} else {

						tawwpNewLog.setResultState(submitStr);

					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// haojian_add_start
		tawwpNewLog.setMessage(fileLog.toString());

		tawwpNewLogDao.saveLog(tawwpNewLog);
		// haojian_add_end
	}

	public void setTawwpNewLogDao(ITawwpNewLogDao tawwpNewLogDao) {
		this.tawwpNewLogDao = tawwpNewLogDao;
	}

	/**
	 * 璋冪敤涓婃姤鎺ュ彛
	 * 
	 * @param attachInfoListType
	 *            AttachInfoListType
	 * @throws Exception
	 * @return boolean
	 */
	public boolean reportExecutePortReport(AttachInfoListType attachInfoListType)
			throws Exception {
		com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
		// com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingImpl binding;
		boolean flag = false;
		try {

			com.boco.eoms.gzjhhead.interfaces.ReportExecutePortType reportExecutePortType = new com.boco.eoms.gzjhhead.interfaces.ReportExecuteServiceLocator()
					.getReportExecutePort();
			;

			binding = (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub) reportExecutePortType;

		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null) {
				jre.getLinkedCause().printStackTrace();
			}
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}

		// Time out after a minute
		binding.setTimeout(1000000);

		try {
			com.boco.eoms.gzjhhead.interfaces._ReportFormResponse value = null;
			_ReportFormRequest reportFormRequest = new _ReportFormRequest();
			ReportTypeType reportTypeType = new ReportTypeType(1);
			NoteType noteType = new NoteType();
			reportTypeType.setValue(0);

			reportFormRequest.setCodeA(StaticMethod
					.getNodeName("STRREGIONCODE"));
			reportFormRequest.setCodeB("ZB");
			reportFormRequest
					.setAttNum(attachInfoListType.getAttachInfo().length);

			reportFormRequest.setAttachInfoList(attachInfoListType);
			reportFormRequest.setReportType(reportTypeType);
			reportFormRequest.setNoteReportForm(noteType);

			value = binding.reportForm(reportFormRequest);
			if (value.getResultReportForm() == null) {
				return true;
			} else {
				System.out.print(value.getResultReportForm());
			}
		} catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
			throw new junit.framework.AssertionFailedError(
					"ReportFormFault Exception caught: " + e1);

		}

		return flag;

	}

	public String newReportExecutePortReportNew(
			com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoListType)
			throws Exception {
		com.boco.eoms.gzjhead.interfaces.ReportExecuteBindingStub binding;
		// com.boco.eoms.gzjhead.interfaces.ReportExecuteBindingImpl binding;
		boolean flag = false;
		try {

			com.boco.eoms.gzjhead.interfaces.ReportExecutePortType reportExecutePortType = new com.boco.eoms.gzjhead.interfaces.ReportExecuteServiceLocator()
					.getReportExecutePort();
			;

			binding = (com.boco.eoms.gzjhead.interfaces.ReportExecuteBindingStub) reportExecutePortType;

		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null) {
				jre.getLinkedCause().printStackTrace();
			}
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}

		// Time out after a minute
		binding.setTimeout(1000000);

		com.boco.eoms.gzjhead.interfaces._ReportFormResponse value = null;
		com.boco.eoms.gzjhead.interfaces._ReportFormRequest reportFormRequest = new com.boco.eoms.gzjhead.interfaces._ReportFormRequest();
		com.boco.eoms.gzjhead.interfaces.ReportTypeType reportTypeType = new com.boco.eoms.gzjhead.interfaces.ReportTypeType(
				1);
		com.boco.eoms.gzjhead.interfaces.NoteType noteType = new com.boco.eoms.gzjhead.interfaces.NoteType();
		reportTypeType.setValue(0);

		reportFormRequest
				.setCodeA(StaticMethod.getNodeName("NEWSTRREGIONCODE"));
		reportFormRequest.setCodeB("ZB");
		reportFormRequest.setAttNum(attachInfoListType.getAttachInfo().length);

		reportFormRequest.setAttachInfoList(attachInfoListType);
		reportFormRequest.setReportType(reportTypeType);
		reportFormRequest.setNoteReportForm(noteType);

		value = binding.reportForm(reportFormRequest);

		return value.getResultReportForm();

	}

	public void reportExcel(String _dayTime) {

		TawwpNewLog tawwpNewLog = new TawwpNewLog();

		Date dateNow = new Date();

		tawwpNewLog.setCreateTime(dateNow);

		tawwpNewLog.setLogType("浣滀笟璁″垝鎬婚儴鎺ュ彛涓婃姤");

		StringBuffer fileLog = new StringBuffer("");

		String submitStr = null;

		String dayPath = "";
		boolean submitflag = true;

		try {
			String dayPath1 = TawwpStaticVariable.wwwDir
					+ TawwpStaticVariable.dayPath
					+ _dayTime.replaceAll("-", "") + "/";
			java.io.File File = new java.io.File(dayPath1);
			// 璇诲彇涓撲笟绫诲瀷
			String[] fileTypeArray = File.list();
			for (int i = 0; i < fileTypeArray.length; i++) {
				String fileType = fileTypeArray[i];
				dayPath = dayPath1 + fileType + "/";
				// 璇诲彇鐩綍涓嬬殑鏂囦欢
				java.io.File f = new java.io.File(dayPath);
				String[] fileArray = f.list();
				// 澹版槑涓�涓柊鐨勬暟缁勫璞�
				if (fileArray != null) {
					AttachInfoType[] attachInfo = new AttachInfoType[fileArray.length];
					// 鏁扮粍澶嶅埗
					for (int j = 0; j < fileArray.length; j++) {
						String fileName = fileArray[j];
						java.io.File excel = new java.io.File(dayPath
								+ fileName);
						AttachInfoType attachInfoType = new AttachInfoType();
						attachInfoType.setAttachLength((int) (excel.length()));
						attachInfoType.setAttachName(fileName);
						attachInfoType
								.setAttachURL(TawwpStaticVariable.serverIp
										+ TawwpStaticVariable.dayPath
										+ _dayTime.replaceAll("-", "") + "/"
										+ fileType + "/" + fileName);
						attachInfo[j] = attachInfoType;
					}

					for (int w = 0; w < attachInfo.length; w++) {

						fileLog.append("绗�" + w + "璁板綍:");
						fileLog.append("attachInfo.AttachName:"
								+ attachInfo[w].getAttachName());

						fileLog.append("attachInfo.AttachLength:"
								+ attachInfo[w].getAttachLength());
						fileLog.append("attachInfo.AttachURL:"
								+ attachInfo[w].getAttachURL());

					}

					// 璋冪敤鏈嶅姟
					if (attachInfo.length != 0) {
						AttachInfoListType attachInfoListType = new AttachInfoListType();
						attachInfoListType.setAttachInfo(attachInfo);
						try {

							submitStr = reportExecutePortReportNew(attachInfoListType);
						} catch (Exception ex2) {
							ex2.printStackTrace();
							BocoLog.error(this, 0, "璋冪敤鎺ュ彛寮傚父");
						}
					} else {
						BocoLog.error(this, 0, "鏈鍙栧埌鏂囦欢");
					}
					if (submitflag) {
						BocoLog.debug(this, 0, _dayTime + " " + fileType
								+ "涓撲笟" + "浣滀笟璁″垝excel涓婃姤缁撴潫");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		tawwpNewLog.setMessage(fileLog.toString());

		try {
			tawwpNewLogDao.saveLog(tawwpNewLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author Haojian 璋冪敤涓婃姤鎺ュ彛
	 * 
	 * @param attachInfoListType
	 *            AttachInfoListType
	 * @throws Exception
	 * @return boolean
	 */
	public String reportExecutePortReportNew(
			AttachInfoListType attachInfoListType) throws Exception {

		com.boco.eoms.gzjhhead.interfaces._ReportFormResponse value = null;
		com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
		// com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingImpl binding;
		boolean flag = false;
		try {

			com.boco.eoms.gzjhhead.interfaces.ReportExecutePortType reportExecutePortType = new com.boco.eoms.gzjhhead.interfaces.ReportExecuteServiceLocator()
					.getReportExecutePort();
			;

			binding = (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub) reportExecutePortType;

		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null) {
				jre.getLinkedCause().printStackTrace();
			}
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}

		// Time out after a minute
		binding.setTimeout(1000000);

		try {
			_ReportFormRequest reportFormRequest = new _ReportFormRequest();
			ReportTypeType reportTypeType = new ReportTypeType(1);
			NoteType noteType = new NoteType();
			reportTypeType.setValue(0);

			reportFormRequest.setCodeA(StaticMethod
					.getNodeName("STRREGIONCODE"));
			reportFormRequest.setCodeB("ZB");
			reportFormRequest
					.setAttNum(attachInfoListType.getAttachInfo().length);

			reportFormRequest.setAttachInfoList(attachInfoListType);
			reportFormRequest.setReportType(reportTypeType);
			reportFormRequest.setNoteReportForm(noteType);

			value = binding.reportForm(reportFormRequest);

		} catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
			throw new junit.framework.AssertionFailedError(
					"ReportFormFault Exception caught: " + e1);

		}

		return value.getResultReportForm();
	}

	public void newReportExcel(String _dayTime) {
		String dayPath = "";
		boolean submitflag = true;

		String submitStr = null;

		TawwpNewLog tawwpNewLog = new TawwpNewLog();

		Date dateNow = new Date();

		tawwpNewLog.setCreateTime(dateNow);

		tawwpNewLog.setLogType("浣滀笟璁″垝鎬婚儴鎺ュ彛涓婃姤");

		StringBuffer fileLog = new StringBuffer("");

		try {
			String dayPath1 = TawwpStaticVariable.wwwDir
					+ TawwpStaticVariable.dayPath
					+ _dayTime.replaceAll("-", "") + "/";
			java.io.File File = new java.io.File(dayPath1);
			// 璇诲彇涓撲笟绫诲瀷
			String[] fileTypeArray = File.list();
			for (int i = 0; i < fileTypeArray.length; i++) {
				String fileType = fileTypeArray[i];
				dayPath = dayPath1 + fileType + "/";
				// 璇诲彇鐩綍涓嬬殑鏂囦欢
				java.io.File f = new java.io.File(dayPath);
				String[] fileArray = f.list();
				// 澹版槑涓�涓柊鐨勬暟缁勫璞�
				if (fileArray != null) {
					com.boco.eoms.gzjhead.interfaces.AttachInfoType[] attachInfo = new com.boco.eoms.gzjhead.interfaces.AttachInfoType[fileArray.length];
					// 鏁扮粍澶嶅埗
					for (int j = 0; j < fileArray.length; j++) {
						String fileName = fileArray[j];
						java.io.File excel = new java.io.File(dayPath
								+ fileName);
						com.boco.eoms.gzjhead.interfaces.AttachInfoType attachInfoType = new com.boco.eoms.gzjhead.interfaces.AttachInfoType();
						attachInfoType.setAttachLength((int) (excel.length()));
						attachInfoType.setAttachName(fileName);
						attachInfoType
								.setAttachURL(TawwpStaticVariable.serverIp
										+ TawwpStaticVariable.dayPath
										+ _dayTime.replaceAll("-", "") + "/"
										+ fileType + "/"
										+ URLEncoder.encode(fileName, "GB2312"));
						attachInfo[j] = attachInfoType;
					}

					for (int w = 0; w < attachInfo.length; w++) {

						fileLog.append("绗�" + w + "璁板綍:");
						fileLog.append("attachInfo.AttachName:"
								+ attachInfo[w].getAttachName());

						fileLog.append("attachInfo.AttachLength:"
								+ attachInfo[w].getAttachLength());
						fileLog.append("attachInfo.AttachURL:"
								+ attachInfo[w].getAttachURL());

					}

					// 璋冪敤鏈嶅姟
					if (attachInfo.length != 0) {
						com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoListType = new com.boco.eoms.gzjhead.interfaces.AttachInfoListType();
						attachInfoListType.setAttachInfo(attachInfo);
						try {

							submitStr = newReportExecutePortReportNew(attachInfoListType);
						} catch (Exception ex2) {

							tawwpNewLog.setResultState("璋冪敤鎺ュ彛寮傚父");

							ex2.printStackTrace();
							BocoLog.error(this, 0, "璋冪敤鎺ュ彛寮傚父");
						}
					} else {

						tawwpNewLog.setResultState("鏈鍙栧埌鏂囦欢");
						BocoLog.error(this, 0, "鏈鍙栧埌鏂囦欢");
					}
					if (submitStr == null) {

						tawwpNewLog.setResultState(_dayTime + " " + fileType
								+ "涓撲笟" + "浣滀笟璁″垝excel涓婃姤缁撴潫");
						BocoLog.debug(this, 0, _dayTime + " " + fileType
								+ "涓撲笟" + "浣滀笟璁″垝excel涓婃姤缁撴潫");
					} else {

						tawwpNewLog.setResultState(submitStr);

					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		tawwpNewLog.setMessage(fileLog.toString());

		try {
			tawwpNewLogDao.saveLog(tawwpNewLog);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void addDutyExecuteContentUsersUniteSave(
			Hashtable _executeContentUserHash, String _dutyStartTime,
			String _dutyEndTime, String _userId, String _executeType)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄

		// 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteContentUser tempExecuteContentUser = null;
		// 鎵ц鍐呭(鏁翠綋)缂栧彿
		String executeContentId = "";
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		// 鑾峰彇鎵ц鍐呭(鏁翠綋)缂栧彿闆嗗悎
		Enumeration tempVar = _executeContentUserHash.keys();

		try {
			while (tempVar.hasMoreElements()) {
				executeContentId = (String) tempVar.nextElement();

				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
				tawwpExecuteContent = tawwpExecuteContentDao
						.loadExecuteContent(executeContentId);
				// 鑾峰彇褰撳墠鏃堕棿
				String executeTime = TawwpUtil.getCurrentDateTime();
				// 瀹氫箟瓒呮椂鏍囧織
				String executeFlag = "";
				// 鍒ゆ柇濉啓鏄惁瓒呮椂
				if (this.judgeTimeOut(executeTime, _dutyEndTime)) {
					executeFlag = "1"; // 鎸夋椂
				} else {
					executeFlag = "2"; // 瓒呮椂
				}

				// --------------------------------------------------------------

				// 濡傛灉澶氫汉濉啓澶氫唤
				if ("1".equals(_executeType)) {
					// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
					tempExecuteContentUser = tawwpExecuteContentUserDao
							.filterTawwpExecuteContentUser(_userId,
									tawwpExecuteContent);
					// 濡傛灉瀵硅薄涓嶄负绌�
					if (tempExecuteContentUser != null) {

						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						tempExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()); // 鎵ц鍐呭
						tempExecuteContentUser
								.setRemark(tawwpExecuteContentUser.getRemark()); // 澶囨敞淇℃伅
						tempExecuteContentUser
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿

						tempExecuteContentUser.setCrtime(executeTime);

						// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.updateExecuteContentUser(tempExecuteContentUser);

					} else {
						// 鍒ゆ柇鏄惁闇�瑕侀檮浠�
						/*
						 * if (tawwpExecuteContent.getFileFlag().equals("Y")) {
						 * continue; }
						 */
						tawwpExecuteContentUser = new TawwpExecuteContentUser();
						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
						tawwpExecuteContentUser.setStartDate(_dutyStartTime); // 璁″垝寮�濮嬫椂闂�
						tawwpExecuteContentUser.setEndDate(_dutyEndTime); // 璁″垝缁撴潫鏃堕棿
						tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
						tawwpExecuteContentUser.setFormId(tawwpExecuteContent
								.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
						tawwpExecuteContentUser
								.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄

						tempExecuteContentUser.setCrtime(executeTime);

						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
						tawwpExecuteContentUser.setStubUser("");
						/*
						 * tawwpExecuteContentUser.setYearFlag(tawwpExecuteContent
						 * .getYearFlag()); tawwpExecuteContentUser
						 * .setMonthFlag(tawwpExecuteContent .getMonthFlag());
						 * tawwpExecuteContentUser.setDeptId(tawwpExecuteContent
						 * .getDeptId());
						 */

						// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tawwpExecuteContentUser);
					}

					// 澶氫汉濉啓涓�浠�
				} else {

					// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
					tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
							.get(executeContentId);

					if (tawwpExecuteContentUser.getId() != null
							&& !"".equals(tawwpExecuteContentUser.getId())) {

						tempExecuteContentUser = tawwpExecuteContentUserDao
								.loadExecuteContentUser(tawwpExecuteContentUser
										.getId());

						tempExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()); // 鎵ц鍐呭
						tempExecuteContentUser
								.setRemark(tawwpExecuteContentUser.getRemark()); // 澶囨敞淇℃伅
						tempExecuteContentUser
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿

						tempExecuteContentUser.setCrtime(executeTime);

						// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.updateExecuteContentUser(tempExecuteContentUser);

					} else {
						// 鍒ゆ柇鏄惁闇�瑕侀檮浠�
						/*
						 * if (tawwpExecuteContent.getFileFlag().equals("Y")) {
						 * continue; }
						 */
						tempExecuteContentUser = new TawwpExecuteContentUser();
						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tempExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
						tawwpExecuteContentUser
								.setStartDate(tawwpExecuteContent
										.getStartDate()); // 璁″垝寮�濮嬫椂闂�
						tawwpExecuteContentUser.setEndDate(tawwpExecuteContent
								.getEndDate()); // 璁″垝缁撴潫鏃堕棿
						tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
						tawwpExecuteContentUser.setFormId(tawwpExecuteContent
								.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
						tawwpExecuteContentUser
								.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄

						tawwpExecuteContentUser.setCrtime(executeTime);

						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
						tawwpExecuteContentUser.setStubUser("");
						/*
						 * tawwpExecuteContentUser.setYearFlag(tawwpExecuteContent
						 * .getYearFlag()); tawwpExecuteContentUser
						 * .setMonthFlag(tawwpExecuteContent .getMonthFlag());
						 * tawwpExecuteContentUser.setDeptId(tawwpExecuteContent
						 * .getDeptId());
						 */

						// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tawwpExecuteContentUser);

					}

					tawwpExecuteContent.setContent(tawwpExecuteContentUser
							.getContent());

				}

				// --------------------------------------------------------------

				tawwpExecuteContent.setCrtime(executeTime);

				// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
				if ("".equals(tawwpExecuteContent.getFormDataId())) {
					tawwpExecuteContent.setFormDataId(tawwpExecuteContentUser
							.getFormDataId());
				}

				// 淇敼濉啓鏍囧織()
				tawwpExecuteContent.setExecuteFlag(executeFlag);

				// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
				if (!"".equals(tawwpExecuteContent.getCruser())) {
					if (("," + tawwpExecuteContent.getCruser() + ",")
							.indexOf("," + tawwpExecuteContentUser.getCruser()
									+ ",") < 0) {

						// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
						tawwpExecuteContent.setCruser(tawwpExecuteContent
								.getCruser()
								+ "," + tawwpExecuteContentUser.getCruser());
					}
				} else {
					// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
					tawwpExecuteContent.setCruser(tawwpExecuteContentUser
							.getCruser());
				}

				// --------------------------------------------------------------

				// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
				tawwpExecuteContentDao
						.updateExecuteContent(tawwpExecuteContent);
			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭鎵归噺淇濆瓨鍑虹幇寮傚父");
		} finally {
			tempVar = null;
		}

	}

	/**
	 * 涓氬姟閫昏緫:鍗曠嫭濉啓鏃ュ父鎵ц浣滀笟璁″垝鍐呭(FILL-EXECUTE-CONTENT-005)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _subUser
	 *            String 浠ｇ悊鐢ㄦ埛鐧诲綍鍚�
	 * @param _content
	 *            String 濉啓鐨勬墽琛屽唴瀹�
	 * @param _remark
	 *            String 澶囨敞淇℃伅
	 * @param _formDataId
	 *            String 闄勫姞琛ㄥ崟淇℃伅
	 * @param _eligibility
	 *            String 鍚堟牸鏍囧織
	 * @param _executeContentUserId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 * @param _executeContentId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return String 鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 */
	public String addExecuteContentUserSave(String _userId, String _subUser,
			String _content, String _remark, String _formDataId,
			String _eligibility, String _executeContentUserId,
			String _repReason, String _executeContentId, String _executeFlag2)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄

		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		try {
			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);
			// 鑾峰彇褰撳墠鏃堕棿
			String executeTime = TawwpUtil.getCurrentDateTime();

			if (tawwpExecuteContent.getCycle().equals("1")) {
				if (tawwpExecuteContent.getStartDate().compareTo(
						TawwpUtil.getCurrentDateTime("yyyy-MM-dd 00:00:00")) > 0) {
					return null;
				}
			}

			/*
			 * 濡傛灉鏄懆鍏懆鏃�,鍒欎笉杩涜鎵ц鍒ゆ柇 int week =
			 * TawwpUtil.getWeek(tawwpExecuteContent.getStartDate()); if (week ==
			 * 7 || week == 1) { executeTime =
			 * tawwpExecuteContent.getStartDate(); }
			 */

			// 瀹氫箟瓒呮椂鏍囧織
			String executeFlag = "";
			// 鍒ゆ柇濉啓鏄惁瓒呮椂
			if (this
					.judgeTimeOut(executeTime, tawwpExecuteContent.getEndDate())) {
				executeFlag = "1"; // 鎸夋椂
			} else {
				executeFlag = "2"; // 瓒呮椂
			}

			// --------------------------------------------------------------

			if (_executeContentUserId != null
					&& !"".equals(_executeContentUserId)) {

				this.editExecuteContentUserSave(_userId, _subUser, "",
						_content, _remark, _formDataId, _eligibility,
						_executeContentUserId);

			} else {
				// 鍒ゆ柇鏄惁闇�瑕侀檮浠�
				/*
				 * if (tawwpExecuteContent.getFileFlag().equals("Y")) { return
				 * null; }
				 */
				// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
				// TODO
				tawwpExecuteContentUser = new TawwpExecuteContentUser(
						tawwpExecuteContent.getStartDate(), tawwpExecuteContent
								.getEndDate(), executeTime, executeTime,
						_userId, _subUser, _content, _remark, _eligibility,
						executeFlag, tawwpExecuteContent.getFormId(),
						_formDataId, tawwpExecuteContent, "1", "");
				// 四川版本上的
				// tawwpExecuteContentUser = new TawwpExecuteContentUser(
				// tawwpExecuteContent.getStartDate(), tawwpExecuteContent
				// .getEndDate(), executeTime, executeTime,
				// _userId, _subUser, _content, _remark, _eligibility,
				// executeFlag, tawwpExecuteContent.getFormId(),
				// _formDataId, tawwpExecuteContent, "1");
				/*
				 * tawwpExecuteContentUser.setRepReason(_repReason);
				 * tawwpExecuteContentUser.setExecuteFlag2(_executeFlag2);
				 */

				// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
				tawwpExecuteContentUserDao
						.saveExecuteContentUser(tawwpExecuteContentUser);

				_executeContentUserId = tawwpExecuteContentUser.getId();
			}

			// --------------------------------------------------------------

			if (tawwpExecuteContentUser == null) {
				tawwpExecuteContent.setContent(_content);
				tawwpExecuteContent.setRemark(_remark);
			} else {
				tawwpExecuteContent.setContent(tawwpExecuteContentUser
						.getContent());
				tawwpExecuteContent.setRemark(tawwpExecuteContentUser
						.getRemark());
			}

			// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
			if ("".equals(tawwpExecuteContent.getFormDataId())) {
				tawwpExecuteContent.setFormDataId(_formDataId);
			}
			// 淇敼濉啓鏍囧織()
			tawwpExecuteContent.setExecuteFlag(executeFlag);
			tawwpExecuteContent.setCrtime(executeTime);
			// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
			if (tawwpExecuteContent.getCruser() != null
					&& !tawwpExecuteContent.getCruser().equals("")) {
				if (("," + tawwpExecuteContent.getCruser() + ",").indexOf(","
						+ _userId + ",") < 0) {
					// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
					tawwpExecuteContent.setCruser(tawwpExecuteContent
							.getCruser()
							+ "," + _userId);
				}
			} else {
				// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
				tawwpExecuteContent.setCruser(_userId);
			}
			tawwpExecuteContent.setCrtime(executeTime);
			if (tawwpExecuteContentUser != null) {
				/*
				 * tawwpExecuteContentUser.setYearFlag(tawwpExecuteContent
				 * .getYearFlag());
				 * tawwpExecuteContentUser.setMonthFlag(tawwpExecuteContent
				 * .getMonthFlag());
				 * tawwpExecuteContentUser.setDeptId(tawwpExecuteContent
				 * .getDeptId());
				 */
			}
			// --------------------------------------------------------------

			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
			System.out.println("@@@@@@@@@@@@@@@@@ read input Quality!");
			if (!tawwpExecuteContent.getExecuteFlag().equals("0")
					&& tawwpExecuteContent.getFileFlag().equals("1")) {
				System.out.println("@@@@@@@@@@@@@@@@@ input Quality!");
				tawwpExecuteContent.setQualityCheckFlag("0");
			}
			tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);

			// 杩斿洖鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
			return (_executeContentUserId);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭淇濆瓨鍑虹幇寮傚父");
		}
	}

	/**
	 * added by xudongsuo 2007-07-19
	 * 涓氬姟閫昏緫:閽堝鍛ㄦ湡涓烘棩鐨勪綔涓氳鍒掓墽琛屽唴瀹�,灏嗕笂涓�宸ヤ綔鏃ョ殑鎵ц鍐呭,
	 * 澶嶅埗鍒板綋鏃�,浣嗘瘡鏈堢涓�澶╀笉鑳藉鍒朵笂涓�鏈堝害鏈�鍚庝竴澶╃殑,宸茬粡 鎵ц浜嗙殑鍒欎笉杩涜瑕嗙洊
	 * 
	 * @param _userId
	 *            String 鎵ц鐢ㄦ埛鍚�(鍙兘涓哄綋鍓嶇敤鎴�)
	 * @param _currUser
	 *            String 褰撳墠鐢ㄦ埛鍚�
	 * @param _deptId
	 *            String 鎵ц鐢ㄦ埛鎵�灞為儴闂�
	 * @param _roomId
	 *            String 鎵ц鐢ㄦ埛鐧诲綍鏈烘埧
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @param _dutyStartDate
	 *            String 鐝寮�濮嬫椂闂�
	 * @param _dutyEndDate
	 *            String 鐝缁撴潫鏃堕棿
	 * @param _flag
	 *            String 鎵ц鏍囧織鍖哄埆鏃ュ父鍜屽�肩彮
	 * @param _stubFlag
	 *            String 鏄惁浠ｇ悊鏍囧織
	 * @param _date
	 *            String 鎵ц鏃ユ湡
	 * @throws Exception
	 *             寮傚父
	 */
	public int previousold(String _userId, String _currUser, String _deptId,
			String _roomId, String _monthPlanId, String _dutyStartDate,
			String _dutyEndDate, String _flag, String _stubFlag, String _date,
			String _day, int countVar) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlan newtawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpMonthExecute newtawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContent currExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 闆嗗悎瀵硅薄
		Iterator tawwpMonthExecutes = null;
		Iterator newtawwpMonthExecutes = null;
		Hashtable executeContentUserHash = new Hashtable();
		Hashtable formHash = new Hashtable();

		String executerType = ""; // 鎵ц绫诲瀷
		String executeDate = ""; // 鎵ц鏃堕棿
		String formDataId = ""; // 闄勫姞琛ㄨ褰曠紪鍙�
		String yesterday = ""; // 涓婁竴鏃ユ湡鏃堕棿
		String monthflag = _day.substring(5, 7);
		String newmonthPlanId = "";

		// 鑾峰彇纭洏瀹為檯鐩綍
		String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir;

		try {

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄

			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();
			String tawwpYearPlanid = tawwpMonthPlan.getTawwpYearPlan().getId();
			String netid = null;
			if (tawwpMonthPlan.getTawwpNet() != null) {
				netid = tawwpMonthPlan.getTawwpNet().getId();
			}
			newmonthPlanId = tawwpMonthPlanJDBC.getNewMonthplanId(
					tawwpYearPlanid, netid, monthflag);
			newtawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(newmonthPlanId);
			newtawwpMonthExecutes = newtawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			// 濡傛灉_date涓虹┖,琛ㄧず涓哄綋鏃ョ殑鎵归噺澶嶅埗
			if ("".equals(_date)) {
				executeDate = TawwpUtil.getCurrentDateTime();
			}

			// 涓嶄负绌哄垯琛ㄧず涓烘寚瀹氭棩鏈熺殑鎵归噺澶嶅埗
			else {
				executeDate = TawwpUtil.getCurrentDateTime(tawwpMonthPlan
						.getYearFlag()
						+ "-"
						+ tawwpMonthPlan.getMonthFlag()
						+ "-"
						+ _date
						+ " HH:mm:ss");
			}

			// executeDate = TawwpUtil.getCurrentDateTime(_day + "
			// HH:mm:ss");
			yesterday = TawwpUtil.getCurrentDateTime(_day + " HH:mm:ss");

			// 鑾峰彇涓婁竴鏃ユ椂闂�
			/*
			 * if
			 * ("dailyview".equalsIgnoreCase(_flag.trim())||"daily".equalsIgnoreCase(_flag.trim())) {
			 * yesterday = this.getPreviousDateTime(executeDate); } else if
			 * ("duty".equalsIgnoreCase(_flag.trim())) { yesterday =
			 * this.getPreviousDateTime(_dutyStartDate); }
			 */

			// 鍙栧緱鎵�鏈夊叧鑱斾簬鎸囧畾鏈堝害浣滀笟璁″垝鐨勬湀搴︽墽琛屽唴瀹�
			// 瀵硅繖浜涘唴瀹硅繘琛屽惊鐜鐞�
			while (tawwpMonthExecutes.hasNext()) {

				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				newtawwpMonthExecute = (TawwpMonthExecute) (newtawwpMonthExecutes
						.next());

				// 杩囨护鎺夊懆鏈熼潪"澶�"鐨勬墽琛屽唴瀹�
				// if ("1".equals(tawwpMonthExecute.getCycle())) {//edit by
				// xudongsuo 2007-07-24 for 鍏朵粬鍛ㄦ湡澶嶅埗

				executerType = tawwpMonthExecute.getExecuterType(); // 鎵ц浜虹被鍨�

				// 濡傛灉鎵ц绫诲瀷涓烘棩甯镐笖鎵ц浜虹被鍨嬩笉涓�"鏈烘埧"
				if (("dailyview".equalsIgnoreCase(_flag.trim()) || "daily"
						.equalsIgnoreCase(_flag.trim()))
						&& !"3".equals(executerType)) {

					// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						// 鑾峰彇涓婁竴鏃ユ墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)瀵硅薄,鐢ㄤ簬澶嶅埗
						tawwpExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(yesterday,
										newtawwpMonthExecute);

						// 鑾峰彇鏈棩鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄,鐢ㄤ簬澶嶅埗
						currExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(executeDate,
										tawwpMonthExecute);

						if (tawwpExecuteContent == null
								|| "0".equals(tawwpExecuteContent
										.getExecuteFlag())) {
							// 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鎴栨湭鎵ц,鍒欑粨鏉熸娆″惊鐜�
							continue;
						}

						// 濡傛灉鏈墽琛�,涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠�,鐩存帴浠庢墽琛屽唴瀹�(鏁翠綋)瀵硅薄涓彇鐢ㄥ嵆鍙�
						if ("0".equals(currExecuteContent.getExecuteFlag())
								&& "0".equals(newtawwpMonthPlan
										.getExecuteType())) {

							// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
							tawwpExecuteContentUser = new TawwpExecuteContentUser();
							tawwpExecuteContentUser
									.setContent(tawwpExecuteContent
											.getContent()); // 鎵ц鍐呭 add by jql
							tawwpExecuteContentUser
									.setRemark(tawwpExecuteContent.getRemark()); // 澶囨敞
							// 濡傛灉濉啓杩囬檮鍔犺〃
							if (tawwpExecuteContent.getFormDataId() != null
									&& !"".equals(tawwpExecuteContent
											.getFormDataId())) {

								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
								if (formHash
										.get(currExecuteContent.getFormId()) == null) {

									// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
									formDataId = TawwpUtil
											.getCurrentDateTime("yyyyMMddHHmmss")
											+ "c"
											+ _currUser
											+ String.valueOf(countVar);

									/*
									 * 瀹炵幇闄勫姞琛ㄨ褰曟枃浠跺厠闅�,
									 * 鏀惧湪杩欓噷濂戒箞?涓囦竴contentuser淇濆瓨娌℃湁鎴愬姛鎬庝箞鍔�?
									 * 涓嶆斁鍦ㄨ繖閲屽彲楹荤儲浜�,杩樿鍦ㄧ埗绫荤殑鎵归噺淇濆瓨鏂规硶閲岃皟鐢ㄥ瓙绫荤殑鍏嬮殕鏂规硶,
									 * 鎶婇偅涓柟娉曟斁鍒扮埗绫讳腑?鍙互,閭ｆ�庝箞鍛婅瘔鎵归噺淇濆瓨鐨勬柟娉�,浼犺繘鏉ョ殑
									 * Hashtable閲屽摢涓厓绱犺鍏嬮殕鍛�?瀵筴ey鍊煎姩鎵嬭剼?鍙互,
									 * 閭ｅ師鏉ョ殑鎵归噺淇濆瓨鏁翠釜鍏ㄥ緱鏀�,椋庨櫓寰堝ぇ,鍚庢灉寰堜弗閲�,杩樻槸鍏堝疄鐜板姛鑳藉惂
									 */
									this
											.copyAddonsExcel(
													tawwpExecuteContent
															.getFormDataId(),
													formDataId,
													String
															.valueOf(TawwpStaticVariable.GZJHDICTFLAG));

									formHash.put(
											currExecuteContent.getFormId(),
											formDataId);
								}
								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
								else {
									formDataId = (String) formHash
											.get(currExecuteContent.getFormId());
								}
								System.out.println(tawwpExecuteContentUser
										.getContent()
										+ "---" + formDataId);
								tawwpExecuteContentUser
										.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							}

							// 濡傛灉涓轰唬鐞嗘墽琛�
							if ("stub".equals(_stubFlag)) {
								tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
								tawwpExecuteContentUser.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
							}
							// 鍚﹀垯
							else {
								tawwpExecuteContentUser.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
								tawwpExecuteContentUser.setStubUser("");
							}

							// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
							executeContentUserHash.put(currExecuteContent
									.getId(), tawwpExecuteContentUser);
						}

						// 涓嶇鏄惁宸茬粡鎵ц,鍙鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,鑰屼笖褰撳墠鐢ㄦ埛娌℃湁杩涜濉啓
						else if ("1".equals(newtawwpMonthPlan.getExecuteType())
								&& ("," + currExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") < 0) {

							// 鍙栧嚭涓婁竴鏃ユ墽琛岀殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛瀵硅薄)
							TawwpExecuteContentUser tempVar = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);

							// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
							tawwpExecuteContentUser = new TawwpExecuteContentUser();
							tawwpExecuteContentUser.setContent(tempVar
									.getContent()); // 鎵ц鍐呭
							tawwpExecuteContentUser.setRemark(tempVar
									.getRemark()); // 澶囨敞

							// 濡傛灉鎵ц杩囬檮鍔犺〃
							if (tempVar.getFormDataId() != null
									&& !"".equals(tempVar.getFormDataId())) {

								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
								if (formHash
										.get(currExecuteContent.getFormId()) == null) {

									// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
									formDataId = TawwpUtil
											.getCurrentDateTime("yyyyMMddHHmmss")
											+ "c"
											+ _currUser
											+ String.valueOf(countVar);

									// 瀹炵幇闄勫姞琛ㄨ褰曟枃浠跺厠闅�,
									this
											.copyAddonsExcel(
													rootSaveXMLDir
															+ TawwpStaticVariable.GZJHDICTFLAG
															+ "/"
															+ tempVar
																	.getFormDataId()
															+ ".xml",
													formDataId,
													String
															.valueOf(TawwpStaticVariable.GZJHDICTFLAG));

									formHash.put(
											currExecuteContent.getFormId(),
											formDataId);
								}
								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
								else {
									formDataId = (String) formHash
											.get(currExecuteContent.getFormId());
								}

								tawwpExecuteContentUser
										.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							}

							// 濡傛灉涓轰唬鐞嗘墽琛�
							if ("stub".equals(_stubFlag)) {
								tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
								tawwpExecuteContentUser.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
							}
							// 鍚﹀垯
							else {
								tawwpExecuteContentUser.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
								tawwpExecuteContentUser.setStubUser("");
							}

							// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
							executeContentUserHash.put(currExecuteContent
									.getId(), tawwpExecuteContentUser);

						} else {
							/*
							 * 宸茬粡鎵ц鐨勫垯涓嶈繘琛岃鐩栨搷浣�,
							 * 濡傛灉闇�瑕佽鍦ㄦ澶勬坊鍔犳潯浠跺垽鏂強鐩稿簲浠ｇ爜
							 */
						}

						// 璁℃暟鍣ㄥ姞涓�
						countVar++;

					}
				}
				/*
				 * 濡傛灉 1鏍囧織涓�"鍊肩彮"銆� 2鎵ц鍐呭鐨勬墽琛屼汉绫诲瀷涓哄�肩彮浜恒��
				 * 3鎵ц鐝(鏃堕棿鐐�),鍦ㄥ綋鍓嶇敤鎴风櫥褰曠殑鍊肩彮鐝寮�濮嬫椂闂村拰缁撴潫鏃堕棿涔嬮棿
				 */
				else if ("duty".equals(_flag)
						&& "3".equals(tawwpMonthExecute.getExecuterType())
						&& (this
								.judgeDutyMonthPlan(tawwpMonthExecute
										.getExecuteDuty(), _dutyStartDate,
										_dutyEndDate))) {

					// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(yesterday,
										tawwpMonthExecute);

						// 鑾峰彇鏈棩鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄,鐢ㄤ簬澶嶅埗
						currExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(executeDate,
										tawwpMonthExecute);

						if (tawwpExecuteContent == null
								|| "0".equals(tawwpExecuteContent
										.getExecuteFlag())) {
							// 濡傛灉鍙栧嚭缁撴灉闆嗕负绌�,鎴栨湭鎵ц,鍒欑粨鏉熸娆″惊鐜�
							continue;
						}

						// 濡傛灉鏈墽琛�,涓旀墽琛岀被鍨嬩负澶氫汉濉啓涓�浠�,鐩存帴浠庢墽琛屽唴瀹�(鏁翠綋)瀵硅薄涓彇鐢ㄥ嵆鍙�
						if ("0".equals(currExecuteContent.getExecuteFlag())
								&& "0".equals(newtawwpMonthPlan
										.getExecuteType())) {

							// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
							tawwpExecuteContentUser = new TawwpExecuteContentUser();
							tawwpExecuteContentUser
									.setContent(tawwpExecuteContent
											.getContent()); // 鎵ц鍐呭
							tawwpExecuteContentUser
									.setRemark(tawwpExecuteContent.getRemark()); // 澶囨敞

							// 濡傛灉濉啓杩囬檮鍔犺〃
							if (tawwpExecuteContent.getFormDataId() != null
									&& !"".equals(tawwpExecuteContent
											.getFormDataId())) {

								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
								if (formHash
										.get(currExecuteContent.getFormId()) == null) {

									// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
									formDataId = TawwpUtil
											.getCurrentDateTime("yyyyMMddHHmmss")
											+ "c"
											+ _currUser
											+ String.valueOf(countVar);

									/*
									 * 瀹炵幇闄勫姞琛ㄨ褰曟枃浠跺厠闅�
									 */
									this
											.copyAddonsExcel(
													rootSaveXMLDir
															+ TawwpStaticVariable.GZJHDICTFLAG
															+ "/"
															+ tawwpExecuteContent
																	.getFormDataId()
															+ ".xml",
													formDataId,
													String
															.valueOf(TawwpStaticVariable.GZJHDICTFLAG));

									formHash.put(
											currExecuteContent.getFormId(),
											formDataId);
								}
								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
								else {
									formDataId = (String) formHash
											.get(currExecuteContent.getFormId());
								}

								tawwpExecuteContentUser
										.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							}

							// 濡傛灉涓轰唬鐞嗘墽琛�
							if ("stub".equals(_stubFlag)) {
								tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
								tawwpExecuteContentUser.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
							}
							// 鍚﹀垯
							else {
								tawwpExecuteContentUser.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
								tawwpExecuteContentUser.setStubUser("");
							}

							// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
							executeContentUserHash.put(currExecuteContent
									.getId(), tawwpExecuteContentUser);
						}

						// 涓嶇鏄惁宸茬粡鎵ц,鍙鎵ц绫诲瀷涓哄浜哄～鍐欏浠�,鑰屼笖褰撳墠鐢ㄦ埛娌℃湁杩涜濉啓
						else if ("1".equals(newtawwpMonthPlan.getExecuteType())
								&& ("," + currExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") < 0) {

							// 鍙栧嚭涓婁竴鏃ユ墽琛岀殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛瀵硅薄)
							TawwpExecuteContentUser tempVar = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);

							// 鍒涘缓鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄,contentUserId涓�瀹氫笉瀛樺湪
							tawwpExecuteContentUser = new TawwpExecuteContentUser();
							tawwpExecuteContentUser.setContent(tempVar
									.getContent()); // 鎵ц鍐呭
							tawwpExecuteContentUser.setRemark(tempVar
									.getRemark()); // 澶囨敞

							// 濡傛灉鎵ц杩囬檮鍔犺〃
							if (tempVar.getFormDataId() != null
									&& !"".equals(tempVar.getFormDataId())) {

								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃娌℃湁琚鍒惰繃
								if (formHash
										.get(currExecuteContent.getFormId()) == null) {

									// 鏋勯�犳柊鐨刦ormDataId,鍚嶇О涓姞"c",鏄负浜嗛槻姝笌椤甸潰濉啓鏂瑰紡鐢熸垚鐨勯噸澶�
									formDataId = TawwpUtil
											.getCurrentDateTime("yyyyMMddHHmmss")
											+ "c"
											+ _currUser
											+ String.valueOf(countVar);

									// 瀹炵幇闄勫姞琛ㄨ褰曟枃浠跺厠闅�,
									this
											.copyAddonsExcel(
													rootSaveXMLDir
															+ TawwpStaticVariable.GZJHDICTFLAG
															+ "/"
															+ tempVar
																	.getFormDataId()
															+ ".xml",
													formDataId,
													String
															.valueOf(TawwpStaticVariable.GZJHDICTFLAG));

									formHash.put(
											currExecuteContent.getFormId(),
											formDataId);
								}
								// 濡傛灉鐩稿悓妯℃澘鐨勯檮鍔犺〃宸茬粡琚鍒惰繃,鍒欑洿鎺ヤ粠formHash涓彇鍑篺ormDataId
								else {
									formDataId = (String) formHash
											.get(currExecuteContent.getFormId());
								}

								tawwpExecuteContentUser
										.setFormDataId(formDataId); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							}

							// 濡傛灉涓轰唬鐞嗘墽琛�
							if ("stub".equals(_stubFlag)) {
								tawwpExecuteContentUser.setCruser(_userId); // 鍘熷畾鎵ц鐢ㄦ埛
								tawwpExecuteContentUser.setStubUser(_currUser); // 褰撳墠鐢ㄦ埛(浠ｇ悊浜�)
							}
							// 鍚﹀垯
							else {
								tawwpExecuteContentUser.setCruser(_currUser); // 鎵ц鐢ㄦ埛(褰撳墠鐧诲綍浜�)
								tawwpExecuteContentUser.setStubUser("");
							}

							// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄娣诲姞鍒癏ashtable涓�
							executeContentUserHash.put(currExecuteContent
									.getId(), tawwpExecuteContentUser);

						} else {
							/*
							 * 宸茬粡鎵ц鐨勫垯涓嶈繘琛岃鐩栨搷浣�,
							 * 濡傛灉闇�瑕佽鍦ㄦ澶勬坊鍔犳潯浠跺垽鏂強鐩稿簲浠ｇ爜
							 */
						}

						// 璁℃暟鍣ㄥ姞涓�
						countVar++;

					}
				}
				// } edit by xudongsuo 2007-07-24 for 鍏朵粬鍛ㄦ湡澶嶅埗
			}

			// 杩涜澶嶅埗鎿嶄綔
			if (executeContentUserHash.size() > 0) {
				if ("duty".equals(_flag)) {
					// 淇濆瓨鍊肩彮鎵归噺濉啓鍐呭
					this.addDutyExecuteContentUsersSave(executeContentUserHash,
							_dutyStartDate, _dutyEndDate, _currUser,
							tawwpMonthPlan.getExecuteType());
				} else {
					// 淇濆瓨鏃ュ父鎵归噺濉啓鍐呭
					this.addExecuteContentUsersSave(executeContentUserHash,
							_currUser, tawwpMonthPlan.getExecuteType());
				}
			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵归噺澶嶅埗鎵ц浣滀笟璁″垝鎵ц鍐呭鍑虹幇寮傚父");
		}
		return countVar; // add by jql
	}

	/**
	 * 涓氬姟閫昏緫:鍗曠嫭濉啓鏃ュ父鎵ц浣滀笟璁″垝鍐呭(FILL-EXECUTE-CONTENT-005)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鐧诲綍鍚�
	 * @param _subUser
	 *            String 浠ｇ悊鐢ㄦ埛鐧诲綍鍚�
	 * @param _content
	 *            String 濉啓鐨勬墽琛屽唴瀹�
	 * @param _remark
	 *            String 澶囨敞淇℃伅
	 * @param _formDataId
	 *            String 闄勫姞琛ㄥ崟淇℃伅
	 * @param _eligibility
	 *            String 鍚堟牸鏍囧織
	 * @param _executeContentUserId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 * @param _executeContentId
	 *            String 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)缂栧彿
	 * @throws Exception
	 *             寮傚父
	 * @return String 鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
	 */
	public String addInterfaceContentUserSave(String _userId, String _subUser,
			org.w3c.dom.Document _doc, String _remark, String _formDataId,
			String _eligibility, String _executeContentUserId,
			String _executeContentId, String _reason) throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		/*
		 * TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		 * TawwpExecuteContentDAO(); TawwpExecuteContentUserDAO
		 * tawwpExecuteContentUserDAO = new TawwpExecuteContentUserDAO();
		 * TawwpExecuteFileDAO tawwpExecuteFileDAO = new TawwpExecuteFileDAO();
		 */
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		try {
			// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
			tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(_executeContentId);
			tawwpExecuteContent.setContent("");// 娓呯┖涓婃鎵ц鐨勭粨鏋�
			// 鑾峰彇褰撳墠鏃堕棿
			String executeTime = TawwpUtil.getCurrentDateTime();
			/*
			 * 四川版本上注释 if (tawwpExecuteContent.getCycle().equals("1")) { if
			 * (tawwpExecuteContent.getStartDate().compareTo(
			 * TawwpUtil.getCurrentDateTime("yyyy-MM-dd 00:00:00")) > 0) {
			 * return null; } }
			 */
			/*
			 * 濡傛灉鏄懆鍏懆鏃�,鍒欎笉杩涜鎵ц鍒ゆ柇 int week =
			 * TawwpUtil.getWeek(tawwpExecuteContent.getStartDate()); if (week ==
			 * 7 || week == 1) { executeTime =
			 * tawwpExecuteContent.getStartDate(); }
			 */

			// 瀹氫箟瓒呮椂鏍囧織
			String executeFlag = "";
			// 鍒ゆ柇濉啓鏄惁瓒呮椂
			if (this
					.judgeTimeOut(executeTime, tawwpExecuteContent.getEndDate())) {
				executeFlag = "1"; // 鎸夋椂
			} else {
				executeFlag = "2"; // 瓒呮椂
			}

			TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
			List cmdResultInfo = new java.util.ArrayList();
			String xpath = "//UDSObjectList/UDSObject/Attributes";
			cmdResultInfo = tawwpIntfaceUtil.getList(xpath, cmdResultInfo,
					_doc, "getTaskCmdResultInfo");
			String _content = "";
			String executeFTPStr = "";
			String returnStr = "";
			String fileName = "";
			String fileCnName = "";
			Download tt = new Download();
			// UDSObjectList = XPathAPI.selectNodeList(_doc, xpath);
			List fileList = new ArrayList();
			boolean isGetFile = false; // 是否取过附件
			for (int i = 0; i < cmdResultInfo.size(); i++) {
				Inspection workplanresult = (Inspection) cmdResultInfo.get(i);

				if (cmdResultInfo.size() == 1)
					_content = workplanresult.getExecuteResult();
				else
					_content += (i + 1) + ":"
							+ workplanresult.getExecuteResult();

				executeFTPStr = StaticMethod.null2String(workplanresult
						.getExecuteFTP());
				System.out
						.print(executeFTPStr + "--------------------------附件");
				if (!"".equals(executeFTPStr) && isGetFile == false) {
					TawwpExecuteFile file = tt
							.executeFTPDownload1(executeFTPStr);
					file.setFileName(StaticMethod.null2String(file
							.getFileName()));
					fileName = StaticMethod.null2String(file.getFileName());
					fileCnName = "attmentfile/"
							+ StaticMethod.null2String(file.getFileCodeName());
					fileList.add(file);
					isGetFile = true;
				}
			}

			System.out.println("executeContent" + _content);
			tawwpExecuteContentUser = new TawwpExecuteContentUser(
					tawwpExecuteContent.getStartDate(), tawwpExecuteContent
							.getEndDate(), executeTime, executeTime, _userId,
					_subUser, _content, _remark, _eligibility, executeFlag,
					tawwpExecuteContent.getFormId(), _formDataId,
					tawwpExecuteContent, "1", "");
			/*
			 * 四川版本上的 tawwpExecuteContentUser = new TawwpExecuteContentUser(
			 * tawwpExecuteContent.getStartDate(), tawwpExecuteContent
			 * .getEndDate(), executeTime, executeTime, _userId, _subUser,
			 * _content, _remark, _eligibility, executeFlag,
			 * tawwpExecuteContent.getFormId(), _formDataId,
			 * tawwpExecuteContent, "1");
			 */
			/*
			 * tawwpExecuteContentUser.setRepReason(_repReason);
			 * tawwpExecuteContentUser.setExecuteFlag2(_executeFlag2);
			 */
			// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
			tawwpExecuteContentUserDao
					.saveExecuteContentUser(tawwpExecuteContentUser);

			_executeContentUserId = tawwpExecuteContentUser.getId();
			// --------------------------------------------------------------

			if (tawwpExecuteContentUser == null) {
				tawwpExecuteContent.setContent(_content);
				tawwpExecuteContent.setRemark(_remark);
			} else {
				tawwpExecuteContent.setContent(tawwpExecuteContentUser
						.getContent());
				tawwpExecuteContent.setRemark(tawwpExecuteContentUser
						.getRemark());
			}

			// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
			if ("".equals(tawwpExecuteContent.getFormDataId())) {
				tawwpExecuteContent.setFormDataId(_formDataId);
			}
			// 淇敼濉啓鏍囧織()
			tawwpExecuteContent.setExecuteFlag(executeFlag);
			tawwpExecuteContent.setCrtime(executeTime);
			// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
			if (tawwpExecuteContent.getCruser() != null
					&& !tawwpExecuteContent.getCruser().equals("")) {
				if (("," + tawwpExecuteContent.getCruser() + ",").indexOf(","
						+ _userId + ",") < 0) {
					// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
					tawwpExecuteContent.setCruser(tawwpExecuteContent
							.getCruser()
							+ "," + _userId);
				}
			} else {
				// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
				tawwpExecuteContent.setCruser(_userId);
			}
			tawwpExecuteContent.setCrtime(executeTime);
			if (tawwpExecuteContentUser != null) {
				/*
				 * tawwpExecuteContentUser.setYearFlag(tawwpExecuteContent
				 * .getYearFlag());
				 * tawwpExecuteContentUser.setMonthFlag(tawwpExecuteContent
				 * .getMonthFlag());
				 */
				/*
				 * tawwpExecuteContentUser.setDeptId(tawwpExecuteContent
				 * .getDeptId());
				 */
			}
			// --------------------------------------------------------------\
			/*
			 * Set oldFiles = tawwpExecuteContent.getTawwpExecuteFiles();
			 * java.util.Iterator files = oldFiles.iterator(); while
			 * (files.hasNext()) { TawwpExecuteFile tawWpFile =
			 * (TawwpExecuteFile) files.next();
			 * tawWpFile.getTawwpExecuteContentUser().setTawwpExecuteFiles(
			 * null);
			 * tawWpFile.getTawwpExecuteContent().setTawwpExecuteFiles(null);
			 * tawwpExecuteFileDao.deleteExecuteFile(tawWpFile); } <<<<<<<
			 * .mine
			 */
			// 四川版本上的
			List list = new ArrayList();
			list = this.getExecuteContentFile(_executeContentId);

			java.util.Iterator files = list.iterator();
			while (files.hasNext()) {
				TawwpExecuteFile tawWpFile = (TawwpExecuteFile) files.next();
				tawWpFile.getTawwpExecuteContentUser().setTawwpExecuteFiles(
						null);
				tawWpFile.getTawwpExecuteContent().setTawwpExecuteFiles(null);
				tawwpExecuteFileDao.deleteExecuteFile(tawWpFile);
			}

			// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
			tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);
			for (int i = 0; i < fileList.size(); i++) {
				TawwpExecuteFile tawWpFile = (TawwpExecuteFile) fileList.get(i);
				tawWpFile.setTawwpExecuteContent(tawwpExecuteContent);
				tawWpFile.setTawwpExecuteContentUser(tawwpExecuteContentUser);
				tawwpExecuteFileDao.saveExecuteFile(tawWpFile);
			}
			returnStr = _content + "$" + fileCnName + "@" + fileName;
			// 杩斿洖鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
			return (returnStr);
			// 杩斿洖鏂版坊鍔犵殑鎵ц鍐呭(鍗曚竴鐢ㄦ埛)缂栧彿
			// return (_executeContentUserId);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭淇濆瓨鍑虹幇寮傚父");
		}
	}

	/**
	 * 鏌ヨ褰撳墠鐢ㄦ埛闇�瑕佹墽琛岀殑浣滀笟璁″垝
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛
	 * @param _deptId
	 *            String 閮ㄩ棬
	 * @param _roomId
	 *            String 鏈烘埧
	 * @param _startdate
	 *            String 寮�濮嬫椂闂�
	 * @param _enddate
	 *            String 缁撴潫鏃堕棿
	 * @return Hashtable 浣滀笟璁″垝闆嗗悎
	 * @throws Exception
	 *             寮傚父
	 */
	public Hashtable countExecute(String _userId, String _deptId, String _roomId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		TawwpExecuteCountVO tawwpExecuteCountVO = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		Hashtable hashtable = new Hashtable();
		String orgPostIdStr = "";// ccccccccccccccccccccc
		// tawwpUtilDAO.getOrgPostIdStr(_userId);
		String currentDay = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
		String cruser = "";
		List list = tawwpExecuteContentDao.countExecuteContent(_userId,
				_deptId, orgPostIdStr, currentDay);
		String executer = "";
		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteContent = (TawwpExecuteContent) list.get(i);
			executer = "," + tawwpExecuteContent.getExecuter() + ",";
			cruser = "," + tawwpExecuteContent.getCruser() + ",";
			if (tawwpExecuteContent.getMonthPlanExecuteFlag().equals("1")
					&& cruser.indexOf("," + _userId + ",") <= 0
					&& cruser.indexOf("," + _deptId + ",") <= 0) {
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
				if (executer.indexOf("," + _userId + ",") >= 0
						|| executer.indexOf("," + _deptId + ",") >= 0
						|| executer.indexOf("," + _roomId + ",") >= 0) {
					if (hashtable.get(tawwpMonthPlan.getId()) == null) {
						tawwpExecuteCountVO = new TawwpExecuteCountVO();
						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// tawwpMonthPlanVO.setNetName(tawwpMonthPlan.getTawwpNet().getName());
						tawwpExecuteCountVO
								.setTawwpMonthPlanVO(tawwpMonthPlanVO);
						hashtable.put(tawwpMonthPlan.getId(),
								tawwpExecuteCountVO);
					} else {
						tawwpExecuteCountVO = (TawwpExecuteCountVO) hashtable
								.get(tawwpMonthPlan.getId());
					}
					tawwpExecuteCountVO.setDayExecute(tawwpExecuteCountVO
							.getDayExecute() + 1);
					// 四川版本无此if
					if (tawwpExecuteContent.getExecuterType().equals("3")) {
						tawwpExecuteCountVO.setDutyExecute(1);
					}
				}
			} else if (tawwpExecuteContent.getMonthPlanExecuteFlag()
					.equals("1")
					&& cruser.indexOf("," + _userId + ",") > 0
					&& cruser.indexOf("," + _deptId + ",") > 0) {
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
				if (executer.indexOf("," + _userId + ",") >= 0
						|| executer.indexOf("," + _deptId + ",") >= 0
						|| executer.indexOf("," + _roomId + ",") >= 0) {
					if (hashtable.get(tawwpMonthPlan.getId()) == null) {
						tawwpExecuteCountVO = new TawwpExecuteCountVO();
						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// tawwpMonthPlanVO.setNetName(tawwpMonthPlan.getTawwpNet().getName());
						tawwpExecuteCountVO
								.setTawwpMonthPlanVO(tawwpMonthPlanVO);
						hashtable.put(tawwpMonthPlan.getId(),
								tawwpExecuteCountVO);
					} else {
						tawwpExecuteCountVO = (TawwpExecuteCountVO) hashtable
								.get(tawwpMonthPlan.getId());
					}
					tawwpExecuteCountVO.setDayExecute(tawwpExecuteCountVO
							.getDayExecute() + 1);
					// 四川版本无此if
					if (tawwpExecuteContent.getExecuterType().equals("3")) {
						tawwpExecuteCountVO.setDutyExecute(1);
					}
				}
			} else {
				tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
				if (tawwpExecuteContent.getExecuteFlag().equals("0")
						&& executer.indexOf("," + _userId + ",") >= 0
						|| executer.indexOf("," + _deptId + ",") >= 0
						|| executer.indexOf("," + _roomId + ",") >= 0) {
					if (hashtable.get(tawwpMonthPlan.getId()) == null) {
						tawwpExecuteCountVO = new TawwpExecuteCountVO();
						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// tawwpMonthPlanVO.setNetName(tawwpMonthPlan.getTawwpNet().getName());
						tawwpExecuteCountVO
								.setTawwpMonthPlanVO(tawwpMonthPlanVO);
						hashtable.put(tawwpMonthPlan.getId(),
								tawwpExecuteCountVO);
					} else {
						tawwpExecuteCountVO = (TawwpExecuteCountVO) hashtable
								.get(tawwpMonthPlan.getId());
					}
					tawwpExecuteCountVO.setDayExecute(tawwpExecuteCountVO
							.getDayExecute() + 1);
					// 四川版本无此if
					if (tawwpExecuteContent.getExecuterType().equals("3")) {
						tawwpExecuteCountVO.setDutyExecute(1);
					}
				}
			}
		}

		return hashtable;
	}

	/**
	 * 鏌ヨ褰撳墠鐢ㄦ埛闇�瑕佹墽琛岀殑浣滀笟璁″垝
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛
	 * @param _deptId
	 *            String 閮ㄩ棬
	 * @param _roomId
	 *            String 鏈烘埧
	 * @param _startdate
	 *            String 寮�濮嬫椂闂�
	 * @param _enddate
	 *            String 缁撴潫鏃堕棿
	 * @return Hashtable 浣滀笟璁″垝闆嗗悎
	 * @throws Exception
	 *             寮傚父
	 */
	public Hashtable countExecute2(String _userId, String _deptId,
			String _roomId) throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		TawwpExecuteCountVO tawwpExecuteCountVO = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		Hashtable hashtable = new Hashtable();

		String currentDay = TawwpUtil.getCurrentDateTime("yyyy-MM-dd 23:59:59");
		String dutyFlag = null;

		List list = tawwpExecuteContentDao.countExecuteContentExecute(_userId,
				_deptId, _roomId, currentDay, "");

		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteContent = (TawwpExecuteContent) list.get(i);
			tawwpMonthPlan = tawwpExecuteContent.getTawwpMonthPlan();
			if (tawwpMonthPlan.getExecuteState().equals("1")) {
				continue;
			}
			if (hashtable.get(tawwpMonthPlan.getId()) == null) {
				tawwpExecuteCountVO = new TawwpExecuteCountVO();
				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet()
							.getId());
					tawwpMonthPlanVO.setNetName(StaticMethod
							.dbNull2String(tawwpMonthPlan.getTawwpNet()
									.getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}

				tawwpExecuteCountVO.setTawwpMonthPlanVO(tawwpMonthPlanVO);
				hashtable.put(tawwpMonthPlan.getId(), tawwpExecuteCountVO);
			} else {
				tawwpExecuteCountVO = (TawwpExecuteCountVO) hashtable
						.get(tawwpMonthPlan.getId());
			}
			tawwpExecuteCountVO.setDayExecute(tawwpExecuteCountVO
					.getDayExecute() + 1);
		}

		return hashtable;
	}

	/*
	 * public List listDailyExecuteView(String _userId, String _deptId, String
	 * _roomId, String _year, String _month, String _stubFlag) throws Exception {
	 * long time1 = System.currentTimeMillis(); List resultList = new
	 * ArrayList(); TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
	 * TawwpMonthPlan tawwpMonthPlan = null; TawwpMonthPlanVO tawwpMonthPlanVO =
	 * null; List monthExecuteUserList = tawwpMonthExecuteUserDao
	 * .listMonthExecuteUser(_userId, _deptId, "", _year, _month); String
	 * monthplanids = ","; for (int i = 0; i < monthExecuteUserList.size(); i++) {
	 * tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
	 * .get(i); tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan(); if
	 * (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) { monthplanids =
	 * monthplanids + tawwpMonthPlan.getId() + ","; tawwpMonthPlanVO =
	 * this.viewExecutePlanExecuter(_userId, _deptId, _roomId,
	 * tawwpMonthPlan.getId(), _stubFlag); resultList.add(tawwpMonthPlanVO); } }
	 * long time2 = System.currentTimeMillis();
	 * System.out.println("listDailyExecuteView::"+String.valueOf(time2 -
	 * time1)); return resultList; }
	 */
	public List listDailyExecuteView(String _userId, String _deptId,
			String _roomId, String _year, String _month, String _stubFlag)
			throws Exception {
		List resultList = new ArrayList();
		List monthplanList = new ArrayList();
		List monthExecuteList = new ArrayList();
		List executelist = new ArrayList();

		HashMap monthExecutMap = new HashMap();
		HashMap executeContentMap = new HashMap();

		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent executeContent = null;

		List monthExecuteUserList = tawwpMonthExecuteUserDao
				.listMonthExecuteUser(_userId, _deptId, "", _year, _month);
		String monthplanids = "''";
		for (int i = 0; i < monthExecuteUserList.size(); i++) {
			tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
					.get(i);
			tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
			if (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) {
				monthplanids = monthplanids + ",'" + tawwpMonthPlan.getId()
						+ "'";
			}
		}
		// 鎵归噺鏌ヨ鏈堣鍒�
		monthplanList = tawwpMonthPlanDao.listMonthPlanByIds(monthplanids);
		// 鎵归噺鍙栧緱鏈堣鍒掍腑鎵ц缁撴灉
		monthExecuteList = tawwpMonthExecuteDao
				.getMonthExecuteByPlanIds(monthplanids);

		Collections.sort(monthExecuteList, new Comparator() {
			public int compare(Object o1, Object o2) {
				TawwpMonthExecute p1 = (TawwpMonthExecute) o1;
				TawwpMonthExecute p2 = (TawwpMonthExecute) o2;
				int k = -1;
				if (p1.getName().compareTo(p2.getName()) > 0) {
					k = 1;
				} else if (p1.getName().compareTo(p2.getName()) == 0) {
					if (p1.getId().compareTo(p2.getId()) > 0) {
						k = 1;
					} else {
						k = -1;
					}
				} else if (p1.getName().compareTo(p2.getName()) < 0) {
					k = -1;
				}
				return k;

			}
		});

		String monthExecuteids = "''";
		List monthExecutTemp = new ArrayList();
		for (int j = 0; j < monthExecuteList.size(); j++) {
			tawwpMonthExecute = (TawwpMonthExecute) monthExecuteList.get(j);

			monthExecuteids = monthExecuteids + ",'"
					+ tawwpMonthExecute.getId() + "'";

			if (monthExecutMap.get(tawwpMonthExecute.getTawwpMonthPlan()) != null) {
				monthExecutTemp = (ArrayList) monthExecutMap
						.get(tawwpMonthExecute.getTawwpMonthPlan());
			} else {
				monthExecutTemp = new ArrayList();
			}
			monthExecutTemp.add(tawwpMonthExecute);

			monthExecutMap.put(tawwpMonthExecute.getTawwpMonthPlan(),
					monthExecutTemp);
		}

		executelist = tawwpExecuteContentDao
				.searchContentByMonthExecute(monthExecuteids);
		List executTemp = new ArrayList();
		for (int y = 0; y < executelist.size(); y++) {
			executeContent = (TawwpExecuteContent) executelist.get(y);
			if (executeContentMap.get(executeContent.getTawwpMonthExecute()) != null) {
				executTemp = (ArrayList) executeContentMap.get(executeContent
						.getTawwpMonthExecute());
			} else {
				executTemp = new ArrayList();
			}
			executTemp.add(executeContent);
			executeContentMap.put(executeContent.getTawwpMonthExecute(),
					executTemp);
		}

		for (int x = 0; x < monthplanList.size(); x++) {
			tawwpMonthPlan = (TawwpMonthPlan) monthplanList.get(x);
			TawwpMonthPlanVO tawwpMonthPlanVO = this.viewExecutePlanExecuter(
					_userId, _deptId, _roomId, tawwpMonthPlan, _stubFlag,
					monthExecutMap, executeContentMap);
			resultList.add(tawwpMonthPlanVO);
		}
		return resultList;

	}

	/**
	 * 
	 * 
	 * 涓氬姟閫昏緫:瀵规壒閲忓～鍐欑殑鎵ц鍐呭瀵硅薄闆嗗悎杩涜鍚庣画澶勭悊, 浣垮叾渚夸簬椤甸潰鍛堢幇鏃�,鎸夐檮鍔犺〃杩涜鏁村悎
	 * 
	 * @param _contentVOList
	 *            List 鍘熸潵鐨剎ml鏂囦欢鍦板潃
	 * @throws Exception
	 *             寮傚父
	 * @return Hashtable 澶勭悊鍚庣敓鎴愮殑Hashtable瀵硅薄
	 */
	public HashMap dealList(List _contentVOList) throws Exception {
		// 鍒濆鍖栨暟鎹�
		HashMap contentHash = new LinkedHashMap();
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		String tempFormId = ""; // 闄勫姞琛ㄦā鏉�
		// String tempStr = ""; //涓存椂瀛楃涓�,涓嶅簲璇ュ湪杩欓噷鍒涘缓

		try {
			// 瀵瑰垪琛ㄨ繘琛屽惊鐜鐞�
			for (int i = 0; i < _contentVOList.size(); i++) {
				// 鑾峰彇鎵ц鍐呭瀵硅薄
				tawwpExecuteContentVO = (TawwpExecuteContentVO) _contentVOList
						.get(i);
				// 鑾峰彇闄勫姞琛ㄦā鏉�
				tempFormId = tawwpExecuteContentVO.getFormId();
				// 鍋氬鐞�,闃叉绌烘寚閽�,浠ュ強鎶�""鍜宯ull鐨勬儏鍐靛垎鎴愪袱涓�
				if (tempFormId == null) {
					tempFormId = "";
				}
				// 鏍规嵁妯℃澘,鍙栧嚭瀵瑰簲椤哄簭鍙峰瓧绗︿覆
				String tempStr = (String) contentHash.get(tempFormId);
				// 濡傛灉璇ュ厓绱犳病鏈夊瓨鍦ㄤ簬hashtable涓�
				if (tempStr == null || "".equals(tempStr)) {
					// 鍒欐柊娣诲姞
					contentHash.put(tempFormId, String.valueOf(i));
				} else {
					// 宸茬粡鏈変簡,鍒欐洿鏂皏alue,涓嶈繘琛屽厛鍒犲悗鍔犱笉琛屼箞?
					tempStr += ("," + String.valueOf(i));
					contentHash.remove(tempFormId);
					contentHash.put(tempFormId, tempStr);
				}
			}
			// 杩斿洖澶勭悊杩囩殑hashtable
			return contentHash;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц鍐呭瀵硅薄闆嗗悎鍚庣画澶勭悊鍑虹幇寮傚父");
		}
	}

	/**
	 * 
	 * 鎷疯礉鍘熸湁鏂囦欢鍒版柊鐨勪綅缃�
	 * 
	 * @param _url
	 *            String 鍘熸潵鐨剎ml鏂囦欢鍦板潃
	 * @param _myid
	 *            String 鏂扮殑myid
	 * @param _model
	 *            String 妯＄増id
	 * @throws Exception
	 *             寮傚父
	 * @return String 鐢熸垚鐨勬柊URL
	 */
	public void copyAddonsExcel(String _url, String _myid, String _model)
			throws Exception {

		String filePath = TawwpStaticVariable.wwwDir
				+ "/workplan/tawwpfile/execute" + "/";
		TawwpUtil.mkDir(filePath);
		// 鍒濆鍖栨暟鎹�
		String fileUrl = filePath + _url + ".xls";
		String newfileUrl = filePath + _myid + ".xls";
		System.out.println("oldUul=" + fileUrl);
		System.out.println("newUul=" + newfileUrl);

		try {
			File file = new File(fileUrl);
			if (!file.exists()) {
				throw new TawwpException("鏂囦欢涓嶅瓨鍦�");
			}
			StaticMethod.copyFile(fileUrl, newfileUrl);

		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎷疯礉XML鍑虹幇寮傚父");
		}
	}

	/**
	 * 
	 * 鍒犻櫎executeContent鏁版嵁
	 * 
	 * @param tawwpExecuteContent
	 *            TawwpExecuteContent
	 */
	public void deleteExecuteContent(TawwpExecuteContent tawwpExecuteContent) {
		try {
			tawwpExecuteContentDao.deleteExecuteContent(tawwpExecuteContent);
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 鍒ゆ柇鎵ц椤规槸鍚︽墽琛屽畬姣曪紝濡傛灉鎵ц瀹屾瘯锛屽垯淇敼涓哄緟璐ㄦ鐘舵��
	 * 
	 * @param tawwpExecuteContentUser
	 *            TawwpExecuteContentUser
	 */
	public void changeCheckFlag(TawwpExecuteContent tawwpExecuteContent) {
		try {
			int executeCount = 0;
			Iterator tawwpExecutecontentUsers = null;
			tawwpExecutecontentUsers = tawwpExecuteContent
					.getTawwpExecuteContentUsers().iterator();
			if (!StaticMethod.null2String(
					tawwpExecuteContent.getQualityCheckUser()).equals("")) {//
				if (tawwpExecuteContent.getMonthPlanExecuteFlag().equals("0")) {// 澶氫汉濉啓涓�鏉�
					if (!tawwpExecuteContent.getExecuteFlag().equals("0")
							&& tawwpExecuteContent.getFileFlag().equals("1")) {// 濡傛灉宸茬粡鎵ц
						tawwpExecuteContent.setQualityCheckFlag("0");
					}
					tawwpExecuteContentDao
							.updateExecuteContent(tawwpExecuteContent);
				} else {// 濡傛灉鏄浜哄～鍐欏浠斤紝鍒欏姣旀墽琛屼汉鐨勪釜鏁板拰tawwpExecutecontentUser鐨勮褰曚釜鏁帮紝濡傛灉涓�鏍凤紝鍒欒〃绀烘墍鏈夋墽琛屼汉閮藉～鍐欏畬姣曪紝鎻愪氦璐ㄦ
					if (tawwpExecuteContent.getExecuterType().equals("0")
							&& tawwpExecuteContent.getFileFlag().equals("1")) {// 鎵ц鑰呬负浜哄憳
						String[] tempUser = tawwpExecuteContent.getExecuter()
								.split(",");
						while (tawwpExecutecontentUsers.hasNext()) {
							executeCount = executeCount + 1;
						}
						if (tempUser.length == executeCount) {// 鎵ц鑰呬负閮ㄩ棬
							tawwpExecuteContent.setQualityCheckFlag("0");
							tawwpExecuteContentDao
									.updateExecuteContent(tawwpExecuteContent);
						}
					} else if (tawwpExecuteContent.getExecuterType()
							.equals("1")) {
						boolean flag = true;
						String[] tempDept = tawwpExecuteContent.getExecuter()
								.split(",");
						for (int i = 0; i < tempDept.length; i++) {
							List list = tawSystemUserDao
									.getUserBydeptids(tempDept[i]);
							while (tawwpExecutecontentUsers.hasNext()) {
								executeCount = executeCount + 1;
							}
							if (list.size() != executeCount) {
								flag = false;
								break;
							}
						}
						if (flag
								&& tawwpExecuteContent.getFileFlag()
										.equals("1")) {
							tawwpExecuteContent.setQualityCheckFlag("0");
							tawwpExecuteContentDao
									.updateExecuteContent(tawwpExecuteContent);
						}
					} else if (tawwpExecuteContent.getExecuterType()
							.equals("3")) {// 鎵ц鑰呬负鐝锛堝�肩彮浣滀笟璁″垝锛�
						if (!tawwpExecuteContent.getExecuteFlag().equals("0")) {// 濡傛灉宸茬粡鎵ц
							tawwpExecuteContent.setQualityCheckFlag("0");
						}
						tawwpExecuteContentDao
								.updateExecuteContent(tawwpExecuteContent);
					}
				}

			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 鑾峰彇寰呰川妫�鐨勬墽琛岄」
	 * 
	 * @param allQuality
	 *            String
	 * @param qualityUser
	 *            String
	 */
	public List getQualityList(String allQuality, String qualityUser) {
		List list = new ArrayList();
		String hSql = "";
		String[] tempDate = StaticMethod.getDateString(0).split("'");
		TawwpUtilDAO dao = new TawwpUtilDAO();
		String beginToday = tempDate[1] + " 00:00:00";
		String endToday = tempDate[1] + " 23:59:59";
		if (allQuality.equals("")) {// 鑾峰彇褰撳ぉ鏈川妫�鐨勬墽琛岄」鍒楄〃锛堝綋鍓嶇敤鎴锋槸璐ㄦ浜猴級
			hSql = " and tawwpexecutecontent.qualityCheckUser = '"
					+ qualityUser
					+ "' and tawwpexecutecontent.qualityCheckFlag = '0' and tawwpexecutecontent.endDate <='"
					+ endToday + "' and tawwpexecutecontent.endDate >='"
					+ beginToday + "'";
		} else {// 鑾峰彇鎵�鏈夋湭璐ㄦ鐨勬墽琛岄」鍒楄〃锛堝綋鍓嶇敤鎴锋槸璐ㄦ浜猴級
			hSql = " and tawwpexecutecontent.qualityCheckUser = '"
					+ qualityUser
					+ "' and tawwpexecutecontent.qualityCheckFlag = '0'";
		}
		List tempList = tawwpExecuteContentDao.listExecuteContent(hSql);
		try {
			for (int i = 0; i < tempList.size(); i++) {
				TawwpExecuteContentVO tawwpExecuteContentVO = new TawwpExecuteContentVO();
				TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) tempList
						.get(i);
				TawwpMonthPlan tawwpMonthPlan = tawwpExecuteContent
						.getTawwpMonthPlan();
				TawwpNet tawwpNet = tawwpMonthPlan.getTawwpNet();
				TawwpMonthPlanVO tawwpMonthPlanVO = new TawwpMonthPlanVO();
				// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
				MyBeanUtils.copyProperties(tawwpExecuteContentVO,
						tawwpExecuteContent);
				MyBeanUtils.copyProperties(tawwpMonthPlanVO, tawwpMonthPlan);
				tawwpExecuteContentVO.setMonthPlanNname(tawwpMonthPlan
						.getName());
				if (tawwpNet == null) {
					tawwpExecuteContentVO.setNetNname(dao
							.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				} else {
					tawwpExecuteContentVO.setNetNname(tawwpNet.getName());
				}
				tawwpExecuteContentVO.setUserName(dao
						.getUserNames(tawwpExecuteContent.getCruser()));
				list.add(tawwpExecuteContentVO);
			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 鏍规嵁鏉′欢鑾峰彇寰呰川妫�鏁版嵁
	 * 
	 * @param allQuality
	 *            String
	 * @param qualityUser
	 *            String
	 */
	public List getQualityListByCondition(String allQuality, String qualityUser) {
		List list = new ArrayList();
		String hSql = "";
		String[] tempDate = StaticMethod.getDateString(0).split("'");
		TawwpUtilDAO dao = new TawwpUtilDAO();
		String beginToday = tempDate[1] + " 00:00:00";
		String endToday = tempDate[1] + " 23:59:59";
		if (allQuality.equals("")) {// 鑾峰彇褰撳ぉ鏈川妫�鐨勬墽琛岄」鍒楄〃锛堝綋鍓嶇敤鎴锋槸璐ㄦ浜猴級
			hSql = " and tawwpexecutecontent.qualityCheckUser = '"
					+ qualityUser
					+ "' and tawwpexecutecontent.qualityCheckFlag = '0' and tawwpexecutecontent.endDate <='"
					+ endToday + "' and tawwpexecutecontent.endDate >='"
					+ beginToday + "'";
		} else {// 鑾峰彇鎵�鏈夋湭璐ㄦ鐨勬墽琛岄」鍒楄〃锛堝綋鍓嶇敤鎴锋槸璐ㄦ浜猴級
			hSql = " and tawwpexecutecontent.qualityCheckUser = '"
					+ qualityUser
					+ "' and tawwpexecutecontent.qualityCheckFlag = '0'";
		}
		List tempList = tawwpExecuteContentDao.listExecuteContent(hSql);
		try {
			for (int i = 0; i < tempList.size(); i++) {
				TawwpExecuteContentVO tawwpExecuteContentVO = new TawwpExecuteContentVO();
				TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) tempList
						.get(i);
				TawwpMonthPlan tawwpMonthPlan = tawwpExecuteContent
						.getTawwpMonthPlan();
				TawwpNet tawwpNet = tawwpMonthPlan.getTawwpNet();
				TawwpMonthPlanVO tawwpMonthPlanVO = new TawwpMonthPlanVO();
				// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
				MyBeanUtils.copyProperties(tawwpExecuteContentVO,
						tawwpExecuteContent);
				MyBeanUtils.copyProperties(tawwpMonthPlanVO, tawwpMonthPlan);
				if (tawwpNet == null) {
					tawwpExecuteContentVO.setNetNname(dao
							.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				} else {
					tawwpExecuteContentVO.setNetNname(tawwpNet.getName());
				}
				tawwpExecuteContentVO
						.setNetNname(tawwpMonthPlanVO.getNetName());
				list.add(tawwpExecuteContentVO);
			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 鏍规嵁鏌ヨ鏉′欢鑾峰彇璐ㄦ鏁版嵁
	 * 
	 * @param hSql
	 *            String
	 */
	public List getQualityListByCondition(String hSql) {
		List list = new ArrayList();
		List tempList = tawwpExecuteContentDao.listExecuteContent(hSql);
		TawwpUtilDAO dao = new TawwpUtilDAO();
		try {
			for (int i = 0; i < tempList.size(); i++) {
				TawwpExecuteContentVO tawwpExecuteContentVO = new TawwpExecuteContentVO();
				TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) tempList
						.get(i);
				TawwpMonthPlan tawwpMonthPlan = tawwpExecuteContent
						.getTawwpMonthPlan();
				TawwpMonthPlanVO tawwpMonthPlanVO = new TawwpMonthPlanVO();
				// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
				MyBeanUtils.copyProperties(tawwpExecuteContentVO,
						tawwpExecuteContent);
				MyBeanUtils.copyProperties(tawwpMonthPlanVO, tawwpMonthPlan);
				tawwpExecuteContentVO.setMonthPlanNname(tawwpMonthPlan
						.getName());
				tawwpExecuteContentVO
						.setNetNname(tawwpMonthPlanVO.getNetName());
				tawwpExecuteContentVO
						.setQualityCheckUserName(dao
								.getUserName(tawwpExecuteContent
										.getQualityCheckUser()));
				tawwpExecuteContentVO.setUserName(dao
						.getUserNames(tawwpExecuteContent.getCruser()));
				list.add(tawwpExecuteContentVO);
			}
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 璐ㄦ閫氳繃锛堝崟涓拰鎵归噺锛�
	 * 
	 * @param allQuality
	 *            String
	 * @param qualityUser
	 *            String
	 */
	public void qualityCheckPass(String[] ids, String device) {
		for (int i = 0; i < ids.length; i++) {
			TawwpExecuteContent tawwpExecuteContent = tawwpExecuteContentDao
					.loadExecuteContent(ids[i]);
			tawwpExecuteContent.setQualityCheckFlag("1");
			if (!device.equals("")) {
				tawwpExecuteContent.setQualityCheckDevice(device);
			}
			tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);
		}
	}

	/**
	 * 
	 * 璐ㄦ涓嶉�氳繃锛堝崟涓級
	 * 
	 * @param allQuality
	 *            String
	 * @param qualityUser
	 *            String
	 */
	public void qualityCheckReject(String id, String device) {
		TawwpExecuteContent tawwpExecuteContent = tawwpExecuteContentDao
				.loadExecuteContent(id);
		tawwpExecuteContent.setQualityCheckFlag("2");
		if (!device.equals("")) {
			tawwpExecuteContent.setQualityCheckDevice(device);
		}
		tawwpExecuteContentDao.updateExecuteContent(tawwpExecuteContent);
	}

	public Hashtable listexecutebydept(String _userId, String _deptId,
			String nettypeId) throws Exception {
		// TODO Auto-generated method stub
		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthExecuteUserList = null;

		// Hashtable瀵硅薄
		Hashtable monthPlanVOHash = new Hashtable();

		// model瀵硅薄
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 宀椾綅缂栧彿瀛楃涓�
		String orgPostIdStr = "";

		try {
			// 鑾峰彇璇ョ敤鎴锋墍鍦ㄥ矖浣嶇紪鍙烽泦鍚�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 鍙栧緱鎵ц浜轰俊鎭垪琛�(modified by lijia 2005-11-03)
			monthExecuteUserList = tawwpMonthExecuteUserDao.listExecuteByuser(
					_userId, _deptId, orgPostIdStr);

			System.out.println(monthExecuteUserList.size());
			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 鍙栧嚭model瀵硅薄(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();

				// 濡傛灉Hashtable瀵硅薄涓病鏈夊寘鎷�,浠ユ鏈堝害浣滀笟璁″垝缂栧彿涓簁ey鍊肩殑value,鍒欒繘琛孷O瀵硅薄鐨勫皝瑁�
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 杩涜VO瀵硅薄鐨勫皝瑁�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 鑾峰彇鎵ц绫诲瀷鍚嶇О
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 鑾峰彇閮ㄩ棬鍚嶇О
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 鑾峰彇鍒涘缓浜哄鍚�
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// 鑾峰彇鎵ц鐘舵�佸悕绉�
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 鑾峰彇鎵ц璐熻矗浜哄鍚�
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 鑾峰彇缃戝厓鍚嶇О
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}

					// modified by lijia 2005-11-28
					tawwpMonthPlanVO
							.setMonthExecuteUserId(tawwpMonthExecuteUser
									.getId());
					tawwpMonthPlanVO
							.setMonthExecuteUserState(tawwpMonthExecuteUser
									.getState());

					// 灏哣O瀵硅薄娣诲姞鍒癏ashtable瀵硅薄涓�

					if (tawwpMonthPlanVO.getNetTypeId().equals(nettypeId)) {

						monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);
					}
				}
			}

			// --------------------------------------------------------------------

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凥ashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず鏃ュ父鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			monthExecuteUserList = null;
		}

	}

	public Hashtable listExecutePlanbynettype(String _userId, String _deptId,
			String nettypeId) throws Exception {
		// TODO Auto-generated method stub
		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthExecuteUserList = null;

		// Hashtable瀵硅薄
		Hashtable monthPlanVOHash = new Hashtable();

		// model瀵硅薄
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 宀椾綅缂栧彿瀛楃涓�
		String orgPostIdStr = "";

		try {
			// 鑾峰彇璇ョ敤鎴锋墍鍦ㄥ矖浣嶇紪鍙烽泦鍚�
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);

			// 鍙栧緱鎵ц浜轰俊鎭垪琛�(modified by lijia 2005-11-03)
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUser(_userId, _deptId, orgPostIdStr);

			// ---------------------------------------------------------------------

			for (int i = 0; i < monthExecuteUserList.size(); i++) {

				// 鍙栧嚭model瀵硅薄(modified by lijia 2005-11-28)
				tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
						.get(i);
				tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();

				// 濡傛灉Hashtable瀵硅薄涓病鏈夊寘鎷�,浠ユ鏈堝害浣滀笟璁″垝缂栧彿涓簁ey鍊肩殑value,鍒欒繘琛孷O瀵硅薄鐨勫皝瑁�
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 杩涜VO瀵硅薄鐨勫皝瑁�
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 鑾峰彇鎵ц绫诲瀷鍚嶇О
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 鑾峰彇閮ㄩ棬鍚嶇О
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 鑾峰彇鍒涘缓浜哄鍚�
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// 鑾峰彇鎵ц鐘舵�佸悕绉�
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 鑾峰彇鎵ц璐熻矗浜哄鍚�
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 鑾峰彇缃戝厓鍚嶇О
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}

					// modified by lijia 2005-11-28
					tawwpMonthPlanVO
							.setMonthExecuteUserId(tawwpMonthExecuteUser
									.getId());
					tawwpMonthPlanVO
							.setMonthExecuteUserState(tawwpMonthExecuteUser
									.getState());

					// 灏哣O瀵硅薄娣诲姞鍒癏ashtable瀵硅薄涓�

					if (tawwpMonthPlanVO.getNetTypeId().equals(nettypeId)) {

						monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);
					}
				}
			}

			// --------------------------------------------------------------------

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凥ashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず鏃ュ父鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			monthExecuteUserList = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.mgr.ITawwpExecuteMgr#saveContentByNet(java.lang.String)
	 */
	public void saveContentByNet(String netid, Inspection workplanresult) {

		// List瀵硅薄

		List monthPlanList = null;
		Set executeContent = null;

		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;

		TawwpExecuteContent tawwpExecuteContent = null;
		String _executeContentUserId = "";
		try {
			String executeTime = TawwpUtil.getCurrentDateTime();
			String year = TawwpUtil.getCurrentDateTime("yyyy");
			String month = TawwpUtil.getCurrentDateTime("MM");
			String date = TawwpUtil.getCurrentDateTime("yyyy-MM-dd")
					+ " 00:00:00";
			monthPlanList = tawwpMonthPlanDao.getMonthPlanByNet(netid, year,
					month); // 鑾峰彇缃戝厓瀵瑰簲鐨勫綋鏈堣鍒�
			if (monthPlanList != null) {
				for (Iterator it = monthPlanList.iterator(); it.hasNext();) {
					tawwpMonthPlan = (TawwpMonthPlan) it.next();
					executeContent = tawwpMonthPlan.getTawwpExecuteContents();
					String contentStr = "";
					for (Iterator its = executeContent.iterator(); its
							.hasNext();) {
						tawwpExecuteContent = (TawwpExecuteContent) its.next();
						if (date.equals(tawwpExecuteContent.getStartDate())) { // 褰撳ぉ鎵ц
							String ExecuteId = tawwpExecuteContent
									.getTawwpMonthExecute().getId();
							String taskId = "";
							try {
								taskId = (String) DictMgrLocator
										.getDictService().itemId2name(
												Util
														.constituteDictId(
																"dict-taskid",
																"taskid"),
												ExecuteId); // 鍙栧嚭閰嶇疆鍚庣殑浠诲姟id
							} catch (Exception e) {

							}
							// 濡傛灉鍙栧嚭鏉ョ殑浠诲姟id
							System.out.println("taskId:==:" + taskId);
							System.out
									.println("taskId.equals(workplanresult.getCmdID()):==:"
											+ taskId.equals(workplanresult
													.getCmdID()));
							if (taskId.equals(workplanresult.getCmdID())) {
								contentStr = StaticMethod
										.null2String(tawwpExecuteContent
												.getContent())
										+ workplanresult.getCmdName()
										+ ": "
										+ workplanresult.getExecuteResult()
										+ "--"
										+ (String) DictMgrLocator
												.getDictService()
												.itemId2name(
														Util.constituteDictId(
																"dict-taskid",
																"state"),
														workplanresult
																.getExecuteStatus());
								Download tt = new Download();

								String executeFTPStr = StaticMethod
										.null2String(workplanresult
												.getExecuteFTP());

								tawwpExecuteContentUser = new TawwpExecuteContentUser(
										tawwpExecuteContent.getStartDate(),
										tawwpExecuteContent.getEndDate(),
										executeTime, executeTime,
										tawwpExecuteContent.getExecuter(), "",
										contentStr, "", tawwpExecuteContent
												.getEligibility(), "1",
										tawwpExecuteContent.getFormId(), "",
										tawwpExecuteContent, "1", "");
								// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
								tawwpExecuteContentUserDao
										.saveExecuteContentUser(tawwpExecuteContentUser);

								_executeContentUserId = tawwpExecuteContentUser
										.getId();

								if (tawwpExecuteContentUser == null) {
									tawwpExecuteContent.setContent(contentStr);
									tawwpExecuteContent.setRemark("");
								} else {
									tawwpExecuteContent
											.setContent(tawwpExecuteContentUser
													.getContent());
									tawwpExecuteContent
											.setRemark(tawwpExecuteContentUser
													.getRemark());
								}

								// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
								if ("".equals(tawwpExecuteContent
										.getFormDataId())) {
									tawwpExecuteContent
											.setFormDataId(tawwpExecuteContent
													.getFormDataId());
								}
								// 淇敼濉啓鏍囧織()
								tawwpExecuteContent.setExecuteFlag("1");
								tawwpExecuteContent.setCrtime(executeTime);
								tawwpExecuteContent.setNormalFlag("1");

								// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
								tawwpExecuteContent
										.setCruser(tawwpExecuteContent
												.getExecuter());
								tawwpExecuteContent.setFileFlag("0");
								tawwpExecuteContentDao
										.updateExecuteContent(tawwpExecuteContent);
								if (!"".equals(executeFTPStr)) {
									TawwpExecuteFile file = tt
											.executeFTPDownload1(executeFTPStr);
									file.setFileName(file.getFileName());
									// tawwpExecuteContentUser =
									// tawwpExecuteContent.getTawwpExecuteContentUsers();

									file
											.setTawwpExecuteContent(tawwpExecuteContent);
									file
											.setTawwpExecuteContentUser(tawwpExecuteContentUser);
									tawwpExecuteFileDao.saveExecuteFile(file);
								}
								String smscontent = "";
								if ("3".equals(workplanresult
										.getExecuteStatus())) {
									smscontent = (String) DictMgrLocator
											.getDictService().itemId2name(
													Util.constituteDictId(
															"dict-taskid",
															"state"), "3");
								} else if ("4".equals(workplanresult
										.getExecuteStatus())) {
									smscontent = (String) DictMgrLocator
											.getDictService().itemId2name(
													Util.constituteDictId(
															"dict-taskid",
															"state"), "4");
								} else if ("5".equals(workplanresult
										.getExecuteStatus())) {
									smscontent = (String) DictMgrLocator
											.getDictService().itemId2name(
													Util.constituteDictId(
															"dict-taskid",
															"state"), "5");
								} else if ("1".equals(workplanresult
										.getExecuteStatus())) {
									smscontent = (String) DictMgrLocator
											.getDictService().itemId2name(
													Util.constituteDictId(
															"dict-taskid",
															"state"), "1");
								}
								if (!"".equals(smscontent)) {
									try {
										TawwpUtil
												.sendSMS(
														tawwpExecuteContent
																.getCruser(),
														"鏈堝害浣滀笟璁″垝銆�"
																+ StaticMethod
																		.null2String(tawwpMonthPlan
																				.getName())
																+ "銆�"
																+ smscontent,
														tawwpMonthPlan.getId());
										// 鍙戦�佺煭娑堟伅
										TawwpUtil
												.sendMail(
														tawwpExecuteContent
																.getCruser(),
														"鏈堝害浣滀笟璁″垝銆�"
																+ StaticMethod
																		.null2String(tawwpMonthPlan
																				.getName())
																+ "銆�"
																+ smscontent,
														tawwpMonthPlan.getId());
									} catch (Exception e) {
										System.out.print("娑堟伅鍙戦�佸紓甯�" + e);
									}
								}
							}
						}
					}
				}
			}

			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveContentByComm(String netid, List workplanresult,
			String taskId) {

		// List瀵硅薄
		List tawwpExecuteContentList = new ArrayList();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;

		TawwpExecuteContent tawwpExecuteContent = null;
		// String _executeContentUserId = "";

		String executeType = "";
		try {
			String executeTime = TawwpUtil.getCurrentDateTime();
			// String year = TawwpUtil.getCurrentDateTime("yyyy");
			// String month = TawwpUtil.getCurrentDateTime("MM");
			String date = TawwpUtil.getCurrentDateTime("yyyy-MM-dd")
					+ " 00:00:00";
			// 鑾峰彇缃戝厓锛屼换鍔d锛屽綋鍓嶆椂闂村搴旂殑寰呮墽琛屾墽琛屼换鍔�
			tawwpExecuteContentList = tawwpExecuteContentDao
					.searchContentByTask(taskId, netid, date);
			// 閬嶅巻
			for (int i = 0; i < tawwpExecuteContentList.size(); i++) {
				tawwpExecuteContent = (TawwpExecuteContent) tawwpExecuteContentList
						.get(i);
				/*
				 * // 褰撳墠鏈堣鍒� tawwpMonthPlan =
				 * tawwpExecuteContent.getTawwpMonthPlan(); // 鎵ц绫诲瀷
				 * executeType = tawwpMonthPlan.getExecuteType(); //
				 * 濡傛灉澶氫汉濉啓澶氫唤 if ("1".equals(executeType)) { // 澶氫汉濉啓涓�浠� }
				 * else {
				 */
				String contentStr = "";
				String executeFTPStr = "";
				String executeStatus = "";
				for (Iterator it = workplanresult.iterator(); it.hasNext();) {
					Inspection resault = (Inspection) it.next();
					System.out.println("workplanresult.getCmdID()锛�"
							+ resault.getCmdID());
					System.out.println("workplanresult.getCmdName()锛�"
							+ resault.getCmdName());
					System.out.println("workplanresult.getExecuteFTP()锛�"
							+ resault.getExecuteFTP());
					System.out.println("workplanresult.getExecuteStatus()锛�"
							+ resault.getExecuteStatus());
					System.out.println("workplanresult.getExecuteResult()锛�"
							+ resault.getExecuteResult());
					/*
					 * contentStr = contentStr +
					 * StaticMethod.null2String(tawwpExecuteContent
					 * .getContent()) + resault.getCmdName() + ": " +
					 * resault.getExecuteResult() + "--" + (String)
					 * DictMgrLocator.getDictService() .itemId2name(
					 * Util.constituteDictId( "dict-taskid", "state"),
					 * resault.getExecuteStatus());
					 * 
					 * executeFTPStr = executeFTPStr + "#" +
					 * resault.getExecuteFTP(); executeStatus = executeStatus +
					 * "," + resault.getExecuteStatus();
					 */
					// 四川版本上的
					if (workplanresult.size() == 1)
						contentStr = resault.getExecuteResult();
					else
						contentStr += (i + 1) + ":"
								+ resault.getExecuteResult() + "&#13;&#10;";

					executeFTPStr = resault.getExecuteFTP();
					executeStatus = executeStatus + ","
							+ resault.getExecuteStatus();
				}

				tawwpExecuteContentUser = tawwpExecuteContentUserDao
						.filterTawwpExecuteContentUser(tawwpExecuteContent
								.getExecuter(), tawwpExecuteContent);
				if (tawwpExecuteContentUser != null) {

					tawwpExecuteContentUser.setContent(contentStr); // 鎵ц鍐呭
					tawwpExecuteContentUser.setRemark(tawwpExecuteContentUser
							.getRemark()); // 澶囨敞淇℃伅
					tawwpExecuteContentUser
							.setFormDataId(tawwpExecuteContentUser
									.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
					tawwpExecuteContentUser.setCrtime(TawwpUtil
							.getCurrentDateTime()); // 鎵ц鏃堕棿
					tawwpExecuteContentUser
							.setWriteDate(tawwpExecuteContentUser
									.getWriteDate());
					tawwpExecuteContentUser.setCruser(tawwpExecuteContent
							.getExecuter()); // 鎵ц浜�
					tawwpExecuteContentUser
							.setNormalFlag(tawwpExecuteContentUser
									.getNormalFlag());// 姝ｅ父鏍囧織
					// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
					tawwpExecuteContentUserDao
							.saveExecuteContentUser(tawwpExecuteContentUser);
					// this.changeCheckFlag(tawwpExecuteContent);
				} else {
					tawwpExecuteContentUser = new TawwpExecuteContentUser(
							tawwpExecuteContent.getStartDate(),
							tawwpExecuteContent.getEndDate(), executeTime,
							executeTime, tawwpExecuteContent.getExecuter(), "",
							contentStr, "", tawwpExecuteContent
									.getEligibility(), "1", tawwpExecuteContent
									.getFormId(), "", tawwpExecuteContent, "1",
							"");
					/*
					 * tawwpExecuteContentUser = new TawwpExecuteContentUser(
					 * tawwpExecuteContent.getStartDate(),
					 * tawwpExecuteContent.getEndDate(), executeTime,
					 * executeTime, tawwpExecuteContent.getExecuter(), "",
					 * contentStr, "", tawwpExecuteContent .getEligibility(),
					 * "1", tawwpExecuteContent .getFormId(), "",
					 * tawwpExecuteContent, "1");
					 */
					// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
					tawwpExecuteContentUserDao
							.saveExecuteContentUser(tawwpExecuteContentUser);
				}
				// _executeContentUserId = tawwpExecuteContentUser.getId();

				// x淇敼涓昏褰�
				if (tawwpExecuteContentUser == null) {
					tawwpExecuteContent.setContent(contentStr);
					tawwpExecuteContent.setRemark("");
				} else {
					tawwpExecuteContent.setContent(tawwpExecuteContentUser
							.getContent());
					tawwpExecuteContent.setRemark(tawwpExecuteContentUser
							.getRemark());
				}

				// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
				if ("".equals(tawwpExecuteContent.getFormDataId())) {
					tawwpExecuteContent.setFormDataId(tawwpExecuteContent
							.getFormDataId());
				}
				// 淇敼濉啓鏍囧織()
				tawwpExecuteContent.setExecuteFlag("1");
				tawwpExecuteContent.setCrtime(executeTime);
				tawwpExecuteContent.setNormalFlag("1");

				// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
				tawwpExecuteContent
						.setCruser(tawwpExecuteContent.getExecuter());
				tawwpExecuteContent.setFileFlag("0");
				tawwpExecuteContentDao
						.updateExecuteContent(tawwpExecuteContent);

				// 闄勪欢涓嬭浇
				Download tt = new Download();

				if (!"".equals(executeFTPStr)) {
					String[] executeFTPArray = executeFTPStr.split("#");
					for (int k = 1; k < executeFTPArray.length; k++) {
						TawwpExecuteFile file = tt
								.executeFTPDownload1(executeFTPArray[k]);
						file.setFileName(file.getFileName());
						// tawwpExecuteContentUser =
						// tawwpExecuteContent.getTawwpExecuteContentUsers();

						file.setTawwpExecuteContent(tawwpExecuteContent);
						file
								.setTawwpExecuteContentUser(tawwpExecuteContentUser);
						tawwpExecuteFileDao.saveExecuteFile(file);
					}
				}

				// 鎷煎啓鐭俊閫氱煡
				String smscontent = "";
				if (executeStatus.indexOf("3") > 0
						|| executeStatus.indexOf("1") > 0
						|| executeStatus.indexOf("4") > 0
						|| executeStatus.indexOf("5") > 0) {
					String workPlanName = tawwpExecuteContent
							.getTawwpMonthPlan().getName();
					smscontent = workPlanName
							+ "璐ㄦ缁撴灉寮傚父锛岃缁嗘儏鍐佃鏌ョ湅鍏蜂綋浣滀笟璁″垝鐨勬墽琛屽唴瀹�";
				}
				/*
				 * if ("3".equals(resault.getExecuteStatus())) { smscontent =
				 * (String) DictMgrLocator.getDictService() .itemId2name(
				 * Util.constituteDictId("dict-taskid", "state"), "3"); } else
				 * if ("4".equals(resault.getExecuteStatus())) { smscontent =
				 * (String) DictMgrLocator.getDictService() .itemId2name(
				 * Util.constituteDictId("dict-taskid", "state"), "4"); } else
				 * if ("5".equals(resault.getExecuteStatus())) { smscontent =
				 * (String) DictMgrLocator.getDictService() .itemId2name(
				 * Util.constituteDictId("dict-taskid", "state"), "5"); } else
				 * if ("1".equals(resault.getExecuteStatus())) { smscontent =
				 * (String) DictMgrLocator.getDictService() .itemId2name(
				 * Util.constituteDictId("dict-taskid", "state"), "1"); }
				 */
				if (!"".equals(smscontent)) {
					try {
						TawwpUtil
								.sendSMS(
										tawwpExecuteContent.getCruser(),
										"鏈堝害浣滀笟璁″垝銆�"
												+ StaticMethod
														.null2String(tawwpExecuteContent
																.getTawwpMonthPlan()
																.getName())
												+ "銆�" + smscontent,
										tawwpMonthPlan.getId());
						// 鍙戦�佺煭娑堟伅
						TawwpUtil
								.sendMail(
										tawwpExecuteContent.getCruser(),
										"鏈堝害浣滀笟璁″垝銆�"
												+ StaticMethod
														.null2String(tawwpExecuteContent
																.getTawwpMonthPlan()
																.getName())
												+ "銆�" + smscontent,
										tawwpMonthPlan.getId());
					} catch (Exception e) {
						System.out.print("娑堟伅鍙戦�佸紓甯�" + e);
					}
				}
			}
			// }
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List listDayExecuteView(String _userId, String _deptId,
			String _roomId, String _year, String _month, String _stubFlag,
			String InspectionFlag) throws Exception {
		List resultList = new ArrayList();
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		List monthExecuteUserList = tawwpMonthExecuteUserDao
				.listMonthExecuteUser(_userId, _deptId, "", _year, _month);
		String monthplanids = ",";
		TawwpMonthExecute tawwpMonthExecute = null;
		for (int i = 0; i < monthExecuteUserList.size(); i++) {
			tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
					.get(i);
			tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
			Iterator tawwpMonthExecutes = tawwpMonthPlan
					.getTawwpMonthExecutes().iterator();
			while (tawwpMonthExecutes.hasNext()) {

				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				if (InspectionFlag.equals("Inspection")
						&& !"".equals(StaticMethod
								.null2String(tawwpMonthExecute.getCommand()))) {

					if (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) {
						monthplanids = monthplanids + tawwpMonthPlan.getId()
								+ ",";
						tawwpMonthPlanVO = this.viewExecutePlanExecuter(
								_userId, _deptId, _roomId, tawwpMonthPlan
										.getId(), _stubFlag);
						resultList.add(tawwpMonthPlanVO);
					}

				} else if (!"Inspection".equals(InspectionFlag)
						&& "".equals(StaticMethod.null2String(tawwpMonthExecute
								.getCommand()))) {

					if (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) {
						monthplanids = monthplanids + tawwpMonthPlan.getId()
								+ ",";
						tawwpMonthPlanVO = this.viewExecutePlanExecuter(
								_userId, _deptId, _roomId, tawwpMonthPlan
										.getId(), _stubFlag);
						resultList.add(tawwpMonthPlanVO);
					}
				}
			}
			Collections.sort(resultList, new Comparator() {
				public int compare(Object o1, Object o2) {
					TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
					TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
					int k = -1;
					if (p1.getName().compareTo(p2.getName()) > 0) {
						k = 1;
					} else if (p1.getName().compareTo(p2.getName()) == 0) {
						if (p1.getId().compareTo(p2.getId()) > 0) {
							k = 1;
						} else {
							k = -1;
						}
					} else if (p1.getName().compareTo(p2.getName()) < 0) {
						k = -1;
					}
					return k;

				}
			});
		}
		return resultList;
	}

	// /**
	// * 业务逻辑：解决性能问题浏览执行作业计划_详细信息(执行�?(BROWSE-MONTHLY-PLAN- )
	// *
	// * @param _userId
	// * String 用户�?
	// * @param _deptId
	// * String 部门编号
	// * @param _roomId
	// * String 机房编号
	// * @param _monthPlanId
	// * String 月度作业计划编号
	// * @param _stubFlag
	// * String 代理标志
	// * @throws Exception
	// * 异常
	// * @return TawwpMonthPlanVO 月度作业计划VO对象
	// */
	// public TawwpMonthPlanVO viewExecutePlanExecuter(String _userId,
	// String _deptId, String _roomId, TawwpMonthPlan tawwpMonthPlan,
	// String _stubFlag, HashMap monthExecutMap, HashMap executeContentMap)
	// throws Exception {
	// // 初始化数�?创建DAO对象
	// // TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
	// TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
	//
	// // VO对象
	// TawwpMonthPlanVO tawwpMonthPlanVO = null;
	// TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
	// TawwpExecuteContentVO tawwpExecuteContentVO = null;
	//
	// // model对象
	// TawwpMonthExecute tawwpMonthExecute = null;
	// TawwpExecuteContent tawwpExecuteContent = null;
	//
	// // 集合对象
	// List tawwpMonthExecutes = new ArrayList();
	// List tawwpExecuteContents = new ArrayList();
	// Hashtable monthExecuteVOHash = new Hashtable();
	// Hashtable executeContentVOHash = new Hashtable();
	// List monthExecuteVOList = new ArrayList();
	//
	// int dayCount = 0; // 当月天数
	//
	// try {
	//
	// //
	// ------------------------------------------------------------------------
	//
	// tawwpMonthPlanVO = new TawwpMonthPlanVO();
	// // 封装月度作业计划VO对象
	// MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
	// tawwpMonthPlan);
	// // 获取执行类型名称
	// tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
	// .getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
	// .getExecuteType())));
	// // 获取部门名称
	// tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
	// .getDeptName(tawwpMonthPlanVO.getDeptId()));
	// // 获取系统类型名称
	// tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
	// .getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
	// // 获取网元类型名称
	// tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
	// .getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
	// // 获取创建人姓�?
	// tawwpMonthPlanVO.setUserName(tawwpUtilDAO
	// .getUserName(tawwpMonthPlanVO.getCruser()));
	// // 获取制定状态名�?
	// tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
	// .getConstituteStateName(Integer.parseInt(tawwpMonthPlanVO
	// .getConstituteState())));
	// // 获取执行状态名�?
	// tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
	// .getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
	// .getExecuteState())));
	// // 获取执行负责人姓�?
	// tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
	// .getUserName(tawwpMonthPlanVO.getPrincipal()));
	// // 获取网元名称
	// if (tawwpMonthPlan.getTawwpNet() != null) {
	// tawwpMonthPlanVO.setNetName(StaticMethod
	// .null2String(tawwpMonthPlan.getTawwpNet().getName()));
	// } else {
	// tawwpMonthPlanVO.setNetName("无网元");
	// }
	// if ("stub".equals(_stubFlag)) {
	// tawwpMonthPlanVO.setStubFlag("1"); // 代理标志
	// tawwpMonthPlanVO.setUserByStub(_userId); // 代理用户
	// } else {
	// tawwpMonthPlanVO.setStubFlag("0");
	// tawwpMonthPlanVO.setUserByStub("");
	// }
	//
	// //
	// ------------------------------------------------------------------------
	//
	// // 获取月度作业计划执行内容集合
	// tawwpMonthExecutes = (ArrayList) monthExecutMap.get(tawwpMonthPlan);
	//
	// for (int x = 0; x < tawwpMonthExecutes.size(); x++) {
	//
	// tawwpMonthExecute = (TawwpMonthExecute) tawwpMonthExecutes
	// .get(x);
	//
	// // 判断当前用户是否为指定执行内容的执行�?
	// if (this.judgeExecuter(_userId, _deptId, _roomId,
	// tawwpMonthExecute)) {
	//
	// tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
	// // 封装月度作业计划执行内容VO对象
	// MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
	// tawwpMonthExecute);
	// // 获取执行周期
	// tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
	// .getCycleName(tawwpMonthExecuteVO.getCycle()));
	// // 获取电子表单名称
	// tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
	// .getAddonsName(tawwpMonthExecuteVO.getFormId()));
	// // 获取创建人姓�?
	// tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
	// .getUserName(tawwpMonthExecuteVO.getCruser()));
	// // 获取网元类型名称
	// tawwpMonthExecuteVO
	// .setNetTypeName(tawwpUtilDAO
	// .getNetTypeName(tawwpMonthExecuteVO
	// .getNetTypeId()));
	//
	// // 获取执行人类型名�?
	// tawwpMonthExecuteVO.setExecuterTypeName(TawwpMonthExecuteVO
	// .getExecuterTypeName(Integer
	// .parseInt(tawwpMonthExecuteVO
	// .getExecuterType())));
	// // 如果执行人类型为班次
	// if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
	// // 获取机房名称
	// String roomNames = tawwpUtilDAO
	// .getRoomNames(tawwpMonthExecuteVO.getExecuter());
	// tawwpMonthExecuteVO.setExecuterName(roomNames);
	// tawwpMonthExecuteVO.setExecuteRoomName(roomNames);
	//
	// // 获取执行班次名称
	// if (tawwpMonthExecuteVO.getExecuteDuty() != null
	// && !"".equals(tawwpMonthExecuteVO
	// .getExecuteDuty())) {
	// // 获取执行班次名称
	// tawwpMonthExecuteVO
	// .setExecuteDutyName(tawwpMonthExecuteVO
	// .getExecuteDutyName(Integer
	// .parseInt(tawwpMonthExecuteVO
	// .getExecuteDuty())));
	// }
	// } else {
	// // 获取执行�?或部门、岗�?姓名(或名�?
	// // 执行人类型为用户
	// if ("0".equals(tawwpMonthExecuteVO.getExecuterType())) {
	// tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
	// .getUserNames(tawwpMonthExecuteVO
	// .getExecuter()));
	// }
	// // 执行人类型为部门
	// if ("1".equals(tawwpMonthExecuteVO.getExecuterType())) {
	// tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
	// .getDeptNames(tawwpMonthExecuteVO
	// .getExecuter()));
	// }
	//
	// }
	//
	// // 获取当月天数
	// dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
	// .getYearFlag(), tawwpMonthPlan.getMonthFlag());
	// tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));
	//
	// // 添加到集合中
	// monthExecuteVOList.add(tawwpMonthExecuteVO);
	//
	// // ----------------------------------------------------------------------
	//
	// // 如果执行内容的执行人不为值班�?
	// if (!"3".equals(tawwpMonthExecute.getExecuterType())) {
	//
	// // 获取执行作业计划执行内容(整体)集合
	// tawwpExecuteContents = (ArrayList) executeContentMap
	// .get(tawwpMonthExecute);
	//
	// for (int y = 0; y < tawwpExecuteContents.size(); y++) {
	// tawwpExecuteContentVO = new TawwpExecuteContentVO();
	// // 获取执行作业计划执行内容(整体)对象
	// tawwpExecuteContent = (TawwpExecuteContent) tawwpExecuteContents
	// .get(y);
	//
	// // 封装执行作业计划执行内容(整体)VO对象
	// MyBeanUtils.copyPropertiesFromDBToPage(
	// tawwpExecuteContentVO, tawwpExecuteContent);
	// tawwpExecuteContentVO.setUserName(tawwpUtilDAO
	// .getUserNames(tawwpExecuteContentVO
	// .getCruser())); // 执行人姓�?
	// tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
	// .getCycleName(tawwpExecuteContentVO
	// .getCycle())); // 周期名称
	// tawwpExecuteContentVO.setFormName(tawwpUtilDAO
	// .getAddonsName(tawwpExecuteContentVO
	// .getFormId())); // 附加表单名称
	// tawwpExecuteContentVO
	// .setEligibilityName(tawwpExecuteContentVO
	// .getEligibilityName(tawwpExecuteContentVO
	// .getEligibility())); // 合格标志
	// tawwpExecuteContentVO
	// .setExecuteFlagName(tawwpExecuteContentVO
	// .getExecuteFlagName(Integer
	// .parseInt(tawwpExecuteContentVO
	// .getExecuteFlag()))); // 填写标志
	//
	// // 将执行作业计划执行内�?整体)VO对象添加到集合中
	// executeContentVOHash.put(tawwpMonthExecuteVO
	// .getId()
	// + tawwpExecuteContentVO.getStartDate()
	// .substring(8, 10),
	// tawwpExecuteContentVO);
	//
	// }
	// }
	//
	// // ----------------------------------------------------------------------
	//
	// // 将执行作业计划执行内�?整体)集合对象,添加到月度作业计划执行内容VO集合�?
	// monthExecuteVOHash.put(tawwpMonthExecuteVO,
	// executeContentVOHash);
	// }
	// }
	//
	// //
	// ------------------------------------------------------------------------
	//
	// /*
	// * 添加 1、月度作业计划执行内容VO对象集合 2、月度作业计划执行内容Hashtable对象集合
	// * (key值为月度作业计划执行内容VO对象,value为执行作业计划执行内�?整体)Hashtable对象,
	// * 其中执行作业计划执行内容(整体)Hashtable对象的key值为执行日期,
	// * value值为执行作业计划执行内容(整体)的VO对象) 到月度作业计划VO对象�?
	// */
	// Collections.sort(monthExecuteVOList, new Comparator() {
	// public int compare(Object o1, Object o2) {
	// TawwpMonthExecuteVO p1 = (TawwpMonthExecuteVO) o1;
	// TawwpMonthExecuteVO p2 = (TawwpMonthExecuteVO) o2;
	// int k = -1;
	// if(p1.getCycle().compareTo(p2.getCycle())>0){
	// k = 1;
	// }else if(p1.getCycle().compareTo(p2.getCycle())==0){
	// if (p1.getId().compareTo(p2.getId()) > 0) {
	// k = 1;
	// }else{
	// k = -1;
	// }
	// }else
	// if(p1.getCycle().compareTo(p2.getCycle())<0){
	// k = -1;
	// }
	// return k;
	//
	// }
	// });
	// tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
	// tawwpMonthPlanVO.setMonthExecuteVOHash(monthExecuteVOHash);
	//
	// // 返回月度作业计划VO对象
	// return tawwpMonthPlanVO;
	// } catch (Exception e) {
	// // 捕获异常
	// e.printStackTrace();
	// throw new TawwpException("浏览执行作业计划出现异常");
	// } finally {
	// tawwpMonthExecutes = null;
	// tawwpExecuteContents = null;
	// monthExecuteVOHash = null;
	// executeContentVOHash = null;
	// monthExecuteVOList = null;
	// }
	// }

	/**
	 * 涓氬姟閫昏緫锛氭祻瑙堝�肩彮浣滀笟璁″垝_璇︾粏淇℃伅(鎵ц浜�)(BROWSE-MONTHLY-PLAN-002)
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鍚�
	 * @param _deptId
	 *            String 閮ㄩ棬缂栧彿
	 * @param _roomId
	 *            String 鏈烘埧缂栧彿
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @param _stubFlag
	 *            String 浠ｇ悊鏍囧織
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpMonthPlanVO 鏈堝害浣滀笟璁″垝VO瀵硅薄
	 */
	public TawwpMonthPlanVO viewDutyExecutePlanExecuter(String _userId,
			String _deptId, String _roomId, String _monthPlanId,
			String _stubFlag) throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO瀵硅薄
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		// 闆嗗悎瀵硅薄
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecuteContents = null;
		Hashtable monthExecuteVOHash = new Hashtable();
		Hashtable executeContentVOHash = new Hashtable();
		List monthExecuteVOList = new ArrayList();

		int dayCount = 0; // 褰撴湀澶╂暟

		try {

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝瀵硅薄
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 灏佽鏈堝害浣滀笟璁″垝VO瀵硅薄
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan);
			// 鑾峰彇鎵ц绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
					.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteType())));
			// 鑾峰彇閮ㄩ棬鍚嶇О
			tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
					.getDeptName(tawwpMonthPlanVO.getDeptId()));
			// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
					.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
			// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
					.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
			// 鑾峰彇鍒涘缓浜哄鍚�
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));
			// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getConstituteState())));
			// 鑾峰彇鎵ц鐘舵�佸悕绉�
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteState())));
			// 鑾峰彇鎵ц璐熻矗浜哄鍚�
			tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 鑾峰彇缃戝厓鍚嶇О
			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));
			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}
			if ("stub".equals(_stubFlag)) {
				tawwpMonthPlanVO.setStubFlag("1"); // 浠ｇ悊鏍囧織
				tawwpMonthPlanVO.setUserByStub(_userId); // 浠ｇ悊鐢ㄦ埛
			} else {
				tawwpMonthPlanVO.setStubFlag("0");
				tawwpMonthPlanVO.setUserByStub("");
			}

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭闆嗗悎
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			while (tawwpMonthExecutes.hasNext()) {

				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());

				// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
				if (this.judgeExecuter(_userId, _deptId, _roomId,
						tawwpMonthExecute)) {

					tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
					// 灏佽鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
							tawwpMonthExecute);
					// 鑾峰彇鎵ц鍛ㄦ湡
					tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
							.getCycleName(tawwpMonthExecuteVO.getCycle()));
					// 鑾峰彇鐢靛瓙琛ㄥ崟鍚嶇О
					tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpMonthExecuteVO.getFormId()));
					// 鑾峰彇鍒涘缓浜哄鍚�
					tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthExecuteVO.getCruser()));
					// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
					tawwpMonthExecuteVO
							.setNetTypeName(tawwpUtilDAO
									.getNetTypeName(tawwpMonthExecuteVO
											.getNetTypeId()));

					// 鑾峰彇鎵ц浜虹被鍨嬪悕绉�
					tawwpMonthExecuteVO.setExecuterTypeName(TawwpMonthExecuteVO
							.getExecuterTypeName(Integer
									.parseInt(tawwpMonthExecuteVO
											.getExecuterType())));
					// 濡傛灉鎵ц浜虹被鍨嬩负鐝
					if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
						// 鑾峰彇鏈烘埧鍚嶇О
						String roomNames = tawwpUtilDAO
								.getRoomNames(tawwpMonthExecuteVO.getExecuter());
						tawwpMonthExecuteVO.setExecuterName(roomNames);
						tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

						// 鑾峰彇鎵ц鐝鍚嶇О
						if (tawwpMonthExecuteVO.getExecuteDuty() != null
								&& !"".equals(tawwpMonthExecuteVO
										.getExecuteDuty())) {
							// 鑾峰彇鎵ц鐝鍚嶇О
							tawwpMonthExecuteVO
									.setExecuteDutyName(tawwpMonthExecuteVO
											.getExecuteDutyName(Integer
													.parseInt(tawwpMonthExecuteVO
															.getExecuteDuty())));
						}
					} else {
						// 鑾峰彇鎵ц浜�(鎴栭儴闂ㄣ�佸矖浣�)濮撳悕(鎴栧悕绉�)
						// 鎵ц浜虹被鍨嬩负鐢ㄦ埛
						if ("0".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getUserNames(tawwpMonthExecuteVO
											.getExecuter()));
						}
						// 鎵ц浜虹被鍨嬩负閮ㄩ棬
						if ("1".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getDeptNames(tawwpMonthExecuteVO
											.getExecuter()));
						}
						// 鎵ц浜虹被鍨嬩负宀椾綅
						if ("2".equals(tawwpMonthExecuteVO.getExecuterType())) {
							// ccccccccc
							/*
							 * tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO.getOrgPostNames(
							 * tawwpMonthExecuteVO.getExecuter()));
							 */
						}
					}

					// 鑾峰彇褰撴湀澶╂暟
					dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
							.getYearFlag(), tawwpMonthPlan.getMonthFlag());
					tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));

					// 娣诲姞鍒伴泦鍚堜腑
					monthExecuteVOList.add(tawwpMonthExecuteVO);

					// ----------------------------------------------------------------------

					// 濡傛灉鎵ц鍐呭鐨勬墽琛屼汉涓嶄负鍊肩彮浜�
					if ("3".equals(tawwpMonthExecute.getExecuterType())) {

						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)闆嗗悎
						tawwpExecuteContents = tawwpMonthExecute
								.getTawwpExecuteContents().iterator();

						while (tawwpExecuteContents.hasNext()) {
							tawwpExecuteContentVO = new TawwpExecuteContentVO();
							// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecuteContents
									.next());

							// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
							MyBeanUtils.copyPropertiesFromDBToPage(
									tawwpExecuteContentVO, tawwpExecuteContent);
							tawwpExecuteContentVO.setUserName(tawwpUtilDAO
									.getUserNames(tawwpExecuteContentVO
											.getCruser())); // 鎵ц浜哄鍚�
							tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
									.getCycleName(tawwpExecuteContentVO
											.getCycle())); // 鍛ㄦ湡鍚嶇О
							tawwpExecuteContentVO.setFormName(tawwpUtilDAO
									.getAddonsName(tawwpExecuteContentVO
											.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
							tawwpExecuteContentVO
									.setEligibilityName(tawwpExecuteContentVO
											.getEligibilityName(tawwpExecuteContentVO
													.getEligibility())); // 鍚堟牸鏍囧織
							tawwpExecuteContentVO
									.setExecuteFlagName(tawwpExecuteContentVO
											.getExecuteFlagName(Integer
													.parseInt(tawwpExecuteContentVO
															.getExecuteFlag()))); // 濉啓鏍囧織

							// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
							executeContentVOHash.put(tawwpMonthExecuteVO
									.getId()
									+ tawwpExecuteContentVO.getStartDate()
											.substring(8, 10),
									tawwpExecuteContentVO);

						}
					}

					// ----------------------------------------------------------------------

					// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)闆嗗悎瀵硅薄,娣诲姞鍒版湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O闆嗗悎涓�
					monthExecuteVOHash.put(tawwpMonthExecuteVO,
							executeContentVOHash);
				}

			}

			// ------------------------------------------------------------------------

			/*
			 * 娣诲姞 1銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O瀵硅薄闆嗗悎 2銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹笻ashtable瀵硅薄闆嗗悎
			 * (key鍊间负鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄,value涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)Hashtable瀵硅薄,
			 * 鍏朵腑鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)Hashtable瀵硅薄鐨刱ey鍊间负鎵ц鏃ユ湡,
			 * value鍊间负鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)鐨刅O瀵硅薄) 鍒版湀搴︿綔涓氳鍒扸O瀵硅薄涓�
			 */
			tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
			tawwpMonthPlanVO.setMonthExecuteVOHash(monthExecuteVOHash);

			// 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("娴忚鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			tawwpMonthExecutes = null;
			tawwpExecuteContents = null;
			monthExecuteVOHash = null;
			executeContentVOHash = null;
			monthExecuteVOList = null;
		}
	}

	public List getExecuteContentFile(String executeId) {
		return tawwpExecuteFileDao.listExecuteFile(executeId);
	}

	public Hashtable listExecuteContent(String _monthPlanId, String _startdate,
			String _enddate) throws Exception {

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpMonthPlan tawwpMonthPlan = tawwpMonthPlanDao
				.loadMonthPlan(_monthPlanId);
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		Hashtable hashtable = new Hashtable();
		List list = tawwpExecuteContentDao.listExecuteContent(tawwpMonthPlan,
				_startdate, _enddate);
		List tempList = null;
		List fileList = null;
		Iterator iterator = null;

		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteContent = (TawwpExecuteContent) list.get(i);
			tawwpExecuteContentVO = new TawwpExecuteContentVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteContentVO,
					tawwpExecuteContent);

			tawwpExecuteContentVO.setUserName(tawwpUtilDAO
					.getUserNames(tawwpExecuteContentVO.getUserName())); // 执行人姓名
			tawwpExecuteContentVO.setCruser(tawwpUtilDAO
					.getUserNames(tawwpExecuteContentVO.getCruser())); // 执行人姓名
			tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
					.getCycleName(tawwpExecuteContentVO.getCycle())); // 周期名称
			tawwpExecuteContentVO.setFormName(tawwpUtilDAO
					.getAddonsName(tawwpExecuteContentVO.getFormId())); // 附加表单名称
			tawwpExecuteContentVO
					.setEligibilityName(tawwpExecuteContentVO
							.getEligibilityName(tawwpExecuteContentVO
									.getEligibility())); // 合格标志
			tawwpExecuteContentVO.setExecuteFlagName(tawwpExecuteContentVO
					.getExecuteFlagName(Integer.parseInt(tawwpExecuteContentVO
							.getExecuteFlag()))); // 填写标志
			iterator = tawwpExecuteContent.getTawwpExecuteFiles().iterator();
			fileList = new ArrayList();
			while (iterator.hasNext()) {
				TawwpExecuteFile tawwpExecuteFile = (TawwpExecuteFile) iterator
						.next();
				TawwpExecuteFileVO tawwpExecuteFileVO = new TawwpExecuteFileVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteFileVO,
						tawwpExecuteFile);
				fileList.add(tawwpExecuteFileVO);
			}
			tawwpExecuteContentVO.setExecuteFileListVO(fileList);

			iterator = tawwpExecuteContent.getTawwpExecuteContentUsers()
					.iterator();
			TawwpExecuteContentUserVO tawwpExecuteContentUserVO = new TawwpExecuteContentUserVO();
			List tawwpExecuteContentUserListVO = new ArrayList();
			TawwpExecuteContentUser tawwpExecuteContentUser = new TawwpExecuteContentUser();
			while (iterator.hasNext()) {
				tawwpExecuteContentUser = (TawwpExecuteContentUser) iterator
						.next();
				tawwpExecuteContentVO
						.setExecuteContentUserId(tawwpExecuteContentUser
								.getId());
				MyBeanUtils.copyPropertiesFromDBToPage(
						tawwpExecuteContentUserVO, tawwpExecuteContentUser);

				tawwpExecuteContentUserListVO.add(tawwpExecuteContentUserVO);
			}

			tawwpExecuteContentVO
					.setExecuteContentUserListVO(tawwpExecuteContentUserListVO);

			tempList = (List) hashtable.get(tawwpExecuteContent.getStartDate()
					.substring(0, 10));
			if (tempList == null) {
				tempList = new ArrayList();
				tempList.add(tawwpExecuteContentVO);
				hashtable.put(tawwpExecuteContent.getStartDate().substring(0,
						10), tempList);
			} else {
				tempList.add(tawwpExecuteContentVO);
			}
		}

		return hashtable;
	}

	public void addExecuteContentInterFaceUsersSave(
			Hashtable _executeContentUserHash, String _userId,
			String _executeType) throws Exception {

		// 执行内容(单一用户)对象
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteContentUser tempExecuteContentUser = null;
		// 执行内容(整体)编号
		String executeContentId = "";
		// 获取执行内容(整体)编号集合
		Enumeration tempVar = _executeContentUserHash.keys();
		// 执行内容(整体)对象
		TawwpExecuteContent tawwpExecuteContent = null;
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		try {
			while (tempVar.hasMoreElements()) {

				// 获取hashtable的Key值
				executeContentId = (String) tempVar.nextElement();
				TawwpNet tawwpNet = null;
				// 获取执行作业计划执行内容(整体)对象
				tawwpExecuteContent = tawwpExecuteContentDao
						.loadExecuteContent(executeContentId);
				tawwpNet = tawwpExecuteContent.getTawwpMonthPlan()
						.getTawwpNet();
				String date = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
				// Service1Locator faultSheetLocator = new Service1Locator();
				// com.boco.eoms.workplan.intfacewebservices.Service1Soap
				// interSwitchEomsIP = faultSheetLocator
				// .getService1Soap();

				// 根据key值得到执行内容(单一用户)对象
				tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
						.get(executeContentId);
				TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
				org.w3c.dom.Document _doc = tawwpIntfaceUtil
						.getTaskCmdResultInfoDoc(tawwpExecuteContent
								.getCommand(), tawwpNet, tawwpExecuteContent
								.getStartDate(), tawwpExecuteContent
								.getEndDate());
				System.out.println(executeContentId);
				System.out.println("taskId = "
						+ tawwpExecuteContent.getCommand());
				System.out.println("netId  = " + tawwpNet.getSerialNo());
				System.out.println("StartDate = "
						+ tawwpExecuteContent.getStartDate());
				System.out.println("EndDate = "
						+ tawwpExecuteContent.getEndDate());
				if (_doc != null) {
					this.addInterfaceContentUserSave(_userId, "", _doc,
							tawwpExecuteContentUser.getRemark(),
							tawwpExecuteContentUser.getFormDataId(),
							tawwpExecuteContentUser.getEligibility(), "",
							executeContentId, tawwpExecuteContentUser
									.getReason());
				}

			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("执行作业计划执行内容批量保存出现异常");
		} finally {
			tempVar = null;
		}
	}

	public String addExecuteInterFaceUsersSave(
			String[] _executeContentUserHash, String _userId,
			String _executeType) throws Exception {
		List list = new ArrayList();
		TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
		// 执行内容(单一用户)对象
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteContentUser tempExecuteContentUser = null;
		// 执行内容(整体)编号
		String executeContentId = "";
		// 获取执行内容(整体)编号集合

		// 执行内容(整体)对象
		TawwpExecuteContent tawwpExecuteContent = null;
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		String returnStr = "";
		int i = 0;
		try {
			for (int k = 0; k < _executeContentUserHash.length; k++) {

				// 获取hashtable的Key值
				executeContentId = (String) _executeContentUserHash[k];
				TawwpNet tawwpNet = null;
				// 获取执行作业计划执行内容(整体)对象
				tawwpExecuteContent = tawwpExecuteContentDao
						.loadExecuteContent(executeContentId);
				tawwpNet = tawwpExecuteContent.getTawwpMonthPlan()
						.getTawwpNet();
				String date = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
				// Service1Locator faultSheetLocator = new Service1Locator();
				// com.boco.eoms.workplan.intfacewebservices.Service1Soap
				// interSwitchEomsIP = faultSheetLocator
				// .getService1Soap();

				// 根据key值得到执行内容(单一用户)对象
				org.w3c.dom.Document _doc = tawwpIntfaceUtil
						.getTaskCmdResultInfoDoc(tawwpExecuteContent
								.getCommand(), tawwpNet, tawwpExecuteContent
								.getStartDate(), tawwpExecuteContent
								.getEndDate());
				// org.w3c.dom.Document _doc = interSwitchEomsIP
				// .getTaskCmdResultInfo(tawwpExecuteContent.getCommand(),
				// tawwpNet.getSerialNo(), tawwpExecuteContent
				// .getStartDate(), tawwpExecuteContent
				// .getEndDate());
				System.out.println(executeContentId);
				System.out.println("taskId = "
						+ tawwpExecuteContent.getCommand());
				System.out.println("netId  = " + tawwpNet.getSerialNo());
				System.out.println("StartDate = "
						+ tawwpExecuteContent.getStartDate());
				System.out.println("EndDate = "
						+ tawwpExecuteContent.getEndDate());

				if (_doc != null) {
					returnStr += this.addInterfaceContentUserSave(_userId, "",
							_doc, "", "", "", "", executeContentId, "")
							+ "^";
				} else {
					returnStr += "^";
				}

			}
			return returnStr.substring(0, returnStr.length() - 1);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("执行作业计划执行内容批量保存出现异常");
		} finally {

		}
	}

	public String addExecuteContentUserSaveByCommond(String _userId,
			String _subUser, String _remark, String _formDataId,
			String _eligibility, String _executeContentUserId,
			String _executeContentId, String _reason, String commond)
			throws Exception {

		// TawwpTaskManageInterfaceExtendBO tawwpTaskManageInterfaceExtendBO =
		// new TawwpTaskManageInterfaceExtendBO();
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpNet tawwpNet = null;
		TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
		String content = null;
		String executeContentUserId = null;

		tawwpExecuteContent = tawwpExecuteContentDao
				.loadExecuteContent(_executeContentId);
		tawwpNet = tawwpExecuteContent.getTawwpMonthPlan().getTawwpNet();
		String dateStr = tawwpExecuteContent.getStartDate().substring(0, 10);

		/*
		 * String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" +"<UDSObjectList
		 * xmlns=\"\">" +"<UDSObject>" +"<Attributes>" +"<cmdId>元任务ID1</cmdId>" +"<cmdName>元任务名称1</cmdName>" +"<executeTime>执行时间1</executeTime>" +"<executeStatus>执行状态1</executeStatus>" +"<executeResult>执行结果1</executeResult>" +"<executeFTP>原始报告FTP地址1</executeFTP>" +"</Attributes>" +"</UDSObject>" +"<UDSObject>" +"<Attributes>" +"<cmdId>元任务ID2</cmdId>" +"<cmdName>元任务名称2</cmdName>" +"<executeTime>执行时间2</executeTime>" +"<executeStatus>执行状态2</executeStatus>" +"<executeResult>执行结果2</executeResult>" +"<executeFTP>原始报告FTP地址2</executeFTP>" +"</Attributes>" +"</UDSObject>" +"</UDSObjectList>";
		 */
		String date = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
		// Service1Locator faultSheetLocator = new Service1Locator();
		// com.boco.eoms.workplan.intfacewebservices.Service1Soap
		// interSwitchEomsIP = faultSheetLocator
		// .getService1Soap();
		org.w3c.dom.Document _doc = tawwpIntfaceUtil.getTaskCmdResultInfoDoc(
				tawwpExecuteContent.getCommand(), tawwpNet, tawwpExecuteContent
						.getStartDate(), tawwpExecuteContent.getEndDate());
		// = interSwitchEomsIP.getTaskCmdResultInfo(
		// commond, tawwpNet.getSerialNo(), tawwpExecuteContent
		// .getStartDate(), tawwpExecuteContent.getEndDate());
		/*
		 * List list=new java.util.ArrayList(); DocumentBuilderFactory dbf =
		 * DocumentBuilderFactory.newInstance(); TawwpIntfaceUtil
		 * tawwpIntfaceUtil =new TawwpIntfaceUtil(); DocumentBuilder dc = null;
		 * //org.w3c.dom.Document _doc = null; try { dc =
		 * dbf.newDocumentBuilder(); } catch (ParserConfigurationException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * try { _doc = dc.parse(new InputSource(new StringReader(str))); }
		 * catch (SAXException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		if (_doc != null) {

			executeContentUserId = this.addInterfaceContentUserSave(_userId,
					_subUser, _doc, _remark, _formDataId, _eligibility,
					_executeContentUserId, _executeContentId, _reason);
		}

		/*
		 * executeContentUserId = this.addExecuteContentUserSave(_userId,
		 * _subUser, TawwpUtil.getCurrentDateTime(), content, _remark,
		 * _formDataId, _eligibility, _executeContentUserId, _executeContentId);
		 */

		/*
		 * if (_doc != null) {
		 * 
		 * executeContentUserId = this.addInterfaceContentUserSave(_userId,
		 * _subUser, _doc, _remark, _formDataId, _eligibility,
		 * _executeContentUserId, _executeContentId,_reason); }
		 */

		/*
		 * executeContentUserId = this.addExecuteContentUserSave(_userId,
		 * _subUser, TawwpUtil.getCurrentDateTime(), content, _remark,
		 * _formDataId, _eligibility, _executeContentUserId, _executeContentId);
		 */

		return executeContentUserId;

	}

	public List listDutyExecuteView(String _userId, String _deptId,
			String _roomId, String _year, String _month, String _stubFlag)
			throws Exception {
		List resultList = new ArrayList();
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		List monthExecuteUserList = tawwpMonthExecuteUserDao
				.listMonthExecuteUserDuty(_roomId, _year, _month);
		String monthplanids = ",";
		for (int i = 0; i < monthExecuteUserList.size(); i++) {
			tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
					.get(i);
			tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
			if (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) {
				monthplanids = monthplanids + tawwpMonthPlan.getId() + ",";
				tawwpMonthPlanVO = this.viewDutyExecutePlanExecuter(_userId,
						_deptId, _roomId, tawwpMonthPlan.getId(), _stubFlag);
				resultList.add(tawwpMonthPlanVO);
			}
		}
		return resultList;
	}

	/**
	 * 涓氬姟閫昏緫锛氳В鍐虫�ц兘闂娴忚鎵ц浣滀笟璁″垝_璇︾粏淇℃伅(鎵ц浜�)(BROWSE-MONTHLY-PLAN- )
	 * 
	 * @param _userId
	 *            String 鐢ㄦ埛鍚�
	 * @param _deptId
	 *            String 閮ㄩ棬缂栧彿
	 * @param _roomId
	 *            String 鏈烘埧缂栧彿
	 * @param _monthPlanId
	 *            String 鏈堝害浣滀笟璁″垝缂栧彿
	 * @param _stubFlag
	 *            String 浠ｇ悊鏍囧織
	 * @throws Exception
	 *             寮傚父
	 * @return TawwpMonthPlanVO 鏈堝害浣滀笟璁″垝VO瀵硅薄
	 */
	public TawwpMonthPlanVO viewExecutePlanExecuter(String _userId,
			String _deptId, String _roomId, TawwpMonthPlan tawwpMonthPlan,
			String _stubFlag, HashMap monthExecutMap, HashMap executeContentMap)
			throws Exception {
		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO瀵硅薄
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// model瀵硅薄
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;

		// 闆嗗悎瀵硅薄
		List tawwpMonthExecutes = new ArrayList();
		List tawwpExecuteContents = new ArrayList();
		Hashtable monthExecuteVOHash = new Hashtable();
		Hashtable executeContentVOHash = new Hashtable();
		List monthExecuteVOList = new ArrayList();

		int dayCount = 0; // 褰撴湀澶╂暟

		try {

			// ------------------------------------------------------------------------

			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 灏佽鏈堝害浣滀笟璁″垝VO瀵硅薄
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan);
			// 鑾峰彇鎵ц绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
					.getExecuteTypeName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteType())));
			// 鑾峰彇閮ㄩ棬鍚嶇О
			tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
					.getDeptName(tawwpMonthPlanVO.getDeptId()));
			// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
					.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
			// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
			tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
					.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
			// 鑾峰彇鍒涘缓浜哄鍚�
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));
			// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getConstituteState())));
			// 鑾峰彇鎵ц鐘舵�佸悕绉�
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
							.getExecuteState())));
			// 鑾峰彇鎵ц璐熻矗浜哄鍚�
			tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 鑾峰彇缃戝厓鍚嶇О
			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));
			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}
			if ("stub".equals(_stubFlag)) {
				tawwpMonthPlanVO.setStubFlag("1"); // 浠ｇ悊鏍囧織
				tawwpMonthPlanVO.setUserByStub(_userId); // 浠ｇ悊鐢ㄦ埛
			} else {
				tawwpMonthPlanVO.setStubFlag("0");
				tawwpMonthPlanVO.setUserByStub("");
			}

			// ------------------------------------------------------------------------

			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭闆嗗悎
			tawwpMonthExecutes = (ArrayList) monthExecutMap.get(tawwpMonthPlan);

			for (int x = 0; x < tawwpMonthExecutes.size(); x++) {

				tawwpMonthExecute = (TawwpMonthExecute) tawwpMonthExecutes
						.get(x);

				// 鍒ゆ柇褰撳墠鐢ㄦ埛鏄惁涓烘寚瀹氭墽琛屽唴瀹圭殑鎵ц浜�
				if (this.judgeExecuter(_userId, _deptId, _roomId,
						tawwpMonthExecute)) {

					tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
					// 灏佽鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
							tawwpMonthExecute);
					// 鑾峰彇鎵ц鍛ㄦ湡
					tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
							.getCycleName(tawwpMonthExecuteVO.getCycle()));
					// 鑾峰彇鐢靛瓙琛ㄥ崟鍚嶇О
					tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpMonthExecuteVO.getFormId()));
					// 鑾峰彇鍒涘缓浜哄鍚�
					tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthExecuteVO.getCruser()));
					// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
					tawwpMonthExecuteVO
							.setNetTypeName(tawwpUtilDAO
									.getNetTypeName(tawwpMonthExecuteVO
											.getNetTypeId()));

					// 鑾峰彇鎵ц浜虹被鍨嬪悕绉�
					tawwpMonthExecuteVO.setExecuterTypeName(TawwpMonthExecuteVO
							.getExecuterTypeName(Integer
									.parseInt(tawwpMonthExecuteVO
											.getExecuterType())));
					// 濡傛灉鎵ц浜虹被鍨嬩负鐝
					if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
						// 鑾峰彇鏈烘埧鍚嶇О
						String roomNames = tawwpUtilDAO
								.getRoomNames(tawwpMonthExecuteVO.getExecuter());
						tawwpMonthExecuteVO.setExecuterName(roomNames);
						tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

						// 鑾峰彇鎵ц鐝鍚嶇О
						if (tawwpMonthExecuteVO.getExecuteDuty() != null
								&& !"".equals(tawwpMonthExecuteVO
										.getExecuteDuty())) {
							// 鑾峰彇鎵ц鐝鍚嶇О
							tawwpMonthExecuteVO
									.setExecuteDutyName(tawwpMonthExecuteVO
											.getExecuteDutyName(Integer
													.parseInt(tawwpMonthExecuteVO
															.getExecuteDuty())));
						}
					} else {
						// 鑾峰彇鎵ц浜�(鎴栭儴闂ㄣ�佸矖浣�)濮撳悕(鎴栧悕绉�)
						// 鎵ц浜虹被鍨嬩负鐢ㄦ埛
						if ("0".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getUserNames(tawwpMonthExecuteVO
											.getExecuter()));
						}
						// 鎵ц浜虹被鍨嬩负閮ㄩ棬
						if ("1".equals(tawwpMonthExecuteVO.getExecuterType())) {
							tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO
									.getDeptNames(tawwpMonthExecuteVO
											.getExecuter()));
						}

					}

					// 鑾峰彇褰撴湀澶╂暟
					dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
							.getYearFlag(), tawwpMonthPlan.getMonthFlag());
					tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));

					// 娣诲姞鍒伴泦鍚堜腑
					monthExecuteVOList.add(tawwpMonthExecuteVO);

					// ----------------------------------------------------------------------

					// 濡傛灉鎵ц鍐呭鐨勬墽琛屼汉涓嶄负鍊肩彮浜�
					if (!"3".equals(tawwpMonthExecute.getExecuterType())) {

						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)闆嗗悎
						tawwpExecuteContents = (ArrayList) executeContentMap
								.get(tawwpMonthExecute);

						for (int y = 0; y < tawwpExecuteContents.size(); y++) {
							tawwpExecuteContentVO = new TawwpExecuteContentVO();
							// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContent = (TawwpExecuteContent) tawwpExecuteContents
									.get(y);

							// 灏佽鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)VO瀵硅薄
							MyBeanUtils.copyPropertiesFromDBToPage(
									tawwpExecuteContentVO, tawwpExecuteContent);
							tawwpExecuteContentVO.setUserName(tawwpUtilDAO
									.getUserNames(tawwpExecuteContentVO
											.getCruser())); // 鎵ц浜哄鍚�
							tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
									.getCycleName(tawwpExecuteContentVO
											.getCycle())); // 鍛ㄦ湡鍚嶇О
							tawwpExecuteContentVO.setFormName(tawwpUtilDAO
									.getAddonsName(tawwpExecuteContentVO
											.getFormId())); // 闄勫姞琛ㄥ崟鍚嶇О
							tawwpExecuteContentVO
									.setEligibilityName(tawwpExecuteContentVO
											.getEligibilityName(tawwpExecuteContentVO
													.getEligibility())); // 鍚堟牸鏍囧織
							tawwpExecuteContentVO
									.setExecuteFlagName(tawwpExecuteContentVO
											.getExecuteFlagName(Integer
													.parseInt(tawwpExecuteContentVO
															.getExecuteFlag()))); // 濉啓鏍囧織

							// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)VO瀵硅薄娣诲姞鍒伴泦鍚堜腑
							executeContentVOHash.put(tawwpMonthExecuteVO
									.getId()
									+ tawwpExecuteContentVO.getStartDate()
											.substring(8, 10),
									tawwpExecuteContentVO);

						}
					}

					// ----------------------------------------------------------------------

					// 灏嗘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)闆嗗悎瀵硅薄,娣诲姞鍒版湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O闆嗗悎涓�
					monthExecuteVOHash.put(tawwpMonthExecuteVO,
							executeContentVOHash);
				}
			}

			// ------------------------------------------------------------------------

			/*
			 * 娣诲姞 1銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹筕O瀵硅薄闆嗗悎 2銆佹湀搴︿綔涓氳鍒掓墽琛屽唴瀹笻ashtable瀵硅薄闆嗗悎
			 * (key鍊间负鏈堝害浣滀笟璁″垝鎵ц鍐呭VO瀵硅薄,value涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鏁翠綋)Hashtable瀵硅薄,
			 * 鍏朵腑鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)Hashtable瀵硅薄鐨刱ey鍊间负鎵ц鏃ユ湡,
			 * value鍊间负鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)鐨刅O瀵硅薄) 鍒版湀搴︿綔涓氳鍒扸O瀵硅薄涓�
			 */
			tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
			tawwpMonthPlanVO.setMonthExecuteVOHash(monthExecuteVOHash);

			// 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("娴忚鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			tawwpMonthExecutes = null;
			tawwpExecuteContents = null;
			monthExecuteVOHash = null;
			executeContentVOHash = null;
			monthExecuteVOList = null;
		}
	}

	public List listNotExecuteView(String _userId, String _deptId,
			String _roomId, String _year, String _month, String _stubFlag,
			String monthplanid) throws Exception {
		long time1 = System.currentTimeMillis();
		List resultList = new ArrayList();
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		List monthExecuteUserList = tawwpMonthExecuteUserDao
				.getMonthExecuteUser(monthplanid);
		String monthplanids = ",";
		for (int i = 0; i < monthExecuteUserList.size(); i++) {
			tawwpMonthExecuteUser = (TawwpMonthExecuteUser) monthExecuteUserList
					.get(i);
			tawwpMonthPlan = tawwpMonthExecuteUser.getTawwpMonthPlan();
			tawwpMonthPlan.getTawwpExecuteContents().size();
			if (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) {
				monthplanids = monthplanids + tawwpMonthPlan.getId() + ",";
				tawwpMonthPlanVO = this.viewExecutePlanExecuter(_userId,
						_deptId, _roomId, tawwpMonthPlan.getId(), _stubFlag);
				resultList.add(tawwpMonthPlanVO);
			}
		}
		long time2 = System.currentTimeMillis();
		System.out.println("listDailyExecuteView::"
				+ String.valueOf(time2 - time1));
		return resultList;
	}

	public List loadExecuteContentList(String name, String userId,
			String startDate, String endDate, String _tawwpYearPlanId) {
		// 初始化数据,创建DAO对象
		List list = new ArrayList();
		TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent();

		try {

			list = tawwpExecuteContentDao.loadExecuteContentList(name, userId,
					startDate, endDate, _tawwpYearPlanId);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}

	public String exportToExcel(String _monthPlanId, String _startDate,
			String _endDate, String _cycle) {
		String exportExcelUrl = "";
		String exportExcel = "";
		int x = 0;
		int y = 0;
		String wwwDir = TawwpStaticVariable.wwwDir; // web目录
		String newDir = TawwpUtil.getCurrentDateTime("yyyy_MM_dd");

		try {
			// 创建文件夹
			TawwpUtil.mkDir(wwwDir
					+ "workplan/tawwpfile/tempfiledownload/contnet/" + newDir);
			// 生成的Excel地址
			exportExcelUrl = "/workplan/tawwpfile/tempfiledownload/contnet/"
					+ newDir + "/" + _monthPlanId + _cycle + "("
					+ _startDate.substring(0, 10) + "-"
					+ _endDate.substring(0, 10) + ")" + ".xls";

			exportExcel = wwwDir + exportExcelUrl;
			// 建立输出流
			FileOutputStream fos = new FileOutputStream(exportExcel);
			// 建立一个新的工作表
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			HSSFRow row = null; // 建立新行
			HSSFCell cell = null; // 建立新cell
			HSSFCellStyle cellStyle = wb.createCellStyle();
			; // 样式
			cellStyle.setWrapText(true);
			cellStyle.setVerticalAlignment((short) 1); // 设置垂直居中
			cellStyle.setAlignment((short) 2); // 设置水平居中

			// 写信息－start

			TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

			TawwpExecuteContent tawwpExecuteContent = null;

			Iterator iterator = null;
			List list = null;
			String fileStr = "";
			String userReason = "";

			try {
				TawwpMonthPlan tawwpMonthPlan = tawwpMonthPlanDao
						.loadMonthPlan(_monthPlanId);

				/** ********************配置常量单元格信息 start************************ */
				// 常量值
				String[] valueOfLine = { "计划名称：",
						StaticMethod.null2String(tawwpMonthPlan.getName()),
						// "巡查时间：", _startDate + "-" + _endDate,
						"巡查时间：", "", "执行内容", "周期", "计划时间", "完成时间", "记录人",
						"记录内容", "附件", "附加表", "补填修改原因" };
				// 对应的行
				int[] rowOfLine = { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
				// 对应的列
				int[] colOfLine = { 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8 };
				// xy
				int[][] xyOfLine = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 },
						{ 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 },
						{ 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } };

				/** ********************配置常量单元格信息 end************************ */

				// 如果作业计划信息不为空
				if (tawwpMonthPlan != null) {
					if ("-1".equals(_cycle)) {
						list = tawwpExecuteContentDao.listExecuteContent(
								tawwpMonthPlan, _startDate, _endDate);
					} else {
						list = tawwpExecuteContentDao.listExecuteContent(
								tawwpMonthPlan, _startDate, _endDate, _cycle);
					}

					wb.setSheetName(0, StaticMethod.null2String(tawwpMonthPlan
							.getName()), HSSFWorkbook.ENCODING_UTF_16);

					// 定义常量单元格
					for (int i = 0; i < valueOfLine.length; i++) {
						row = sheet.createRow((short) rowOfLine[i]); // 建立新行
						cell = row.createCell((short) colOfLine[i]); // 建立新cell
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(valueOfLine[i]);
						sheet.addMergedRegion(new Region((short) rowOfLine[i],
								(short) (colOfLine[i]), (short) (rowOfLine[i]
										+ xyOfLine[i][0] - 1),
								(short) (colOfLine[i] + xyOfLine[i][1] - 1)));

					}

					y = 2;
					// 循环处理执行内容
					for (int i = 0; i < list.size(); i++) {

						tawwpExecuteContent = (TawwpExecuteContent) list.get(i);

						// 新的行
						row = sheet.createRow((short) y);
						// 执行内容名称
						cell = row.createCell((short) 0);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(StaticMethod
								.null2String(tawwpExecuteContent.getName()));
						// 周期
						cell = row.createCell((short) 1);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(tawwpUtilDAO
								.getCycleName(tawwpExecuteContent.getCycle()));
						// 计划时间
						cell = row.createCell((short) 2);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(StaticMethod
								.null2String(tawwpExecuteContent.getStartDate()
										.substring(0, 10)));
						// 完成时间
						cell = row.createCell((short) 3);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						if (!tawwpExecuteContent.getExecuteFlag().equals("0")) {
							cell.setCellValue(StaticMethod
									.null2String(tawwpExecuteContent
											.getCrtime().substring(0, 10)));
						} else {
							cell.setCellValue(StaticMethod.null2String(""));
						}
						// 记录人
						cell = row.createCell((short) 4);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(StaticMethod
								.null2String(tawwpExecuteContent.getCruser()));
						// 记录内容
						cell = row.createCell((short) 5);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						if (!tawwpExecuteContent.getExecuteFlag().equals("0")) {
							cell.setCellValue(StaticMethod
									.null2String(tawwpExecuteContent
											.getContent()));
						} else {
							cell.setCellValue(StaticMethod.null2String(""));
						}
						// 附件
						iterator = tawwpExecuteContent.getTawwpExecuteFiles()
								.iterator();
						while (iterator.hasNext()) {
							TawwpExecuteFile tawwpExecuteFile = (TawwpExecuteFile) iterator
									.next();
							fileStr += (tawwpExecuteFile.getFileName() + " ");
						}
						cell = row.createCell((short) 6);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(StaticMethod.null2String(fileStr));
						fileStr = "";
						// 附加表
						cell = row.createCell((short) 7);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(StaticMethod
								.null2String(tawwpUtilDAO
										.getAddonsName(tawwpExecuteContent
												.getFormId())));
						// 补填修改原因
						/*
						 * iterator =
						 * tawwpExecuteContent.getTawwpExecuteContentUsers()
						 * .iterator(); TawwpExecuteContentUser
						 * tawwpExecuteContentUser = null; while
						 * (iterator.hasNext()) { tawwpExecuteContentUser =
						 * (TawwpExecuteContentUser) iterator .next(); if
						 * (tawwpExecuteContentUser.getRepReason() != null &&
						 * !"".equals(tawwpExecuteContentUser.getRepReason()) &&
						 * !"null".equals(tawwpExecuteContentUser.getRepReason())) {
						 * userReason += (tawwpExecuteContentUser.getRepReason() + "
						 * "); } }
						 */

						cell = row.createCell((short) 8);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(StaticMethod.null2String(userReason));
						userReason = tawwpExecuteContent.getReason();

						y++;
					}
					// 定义单元格
					row = sheet.createRow((short) 0); // 建立新行
					cell = row.createCell((short) 3); // 建立新cell
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					// cell.setCellValue(y - 2);
					cell.setCellValue(_startDate + " 到 " + _endDate);
				} else {
					throw new TawwpException("作业计划月度巡查导出出现异常");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			// 写信息－end
			// 写入输出流
			wb.write(fos);
			fos.close(); // 关闭输出流
			return exportExcelUrl;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Hashtable listExecuteContent(String _monthPlanId, String _startdate,
			String _enddate, String _cycle) throws Exception {
		// TODO Auto-generated method stub
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpMonthPlan tawwpMonthPlan = tawwpMonthPlanDao
				.loadMonthPlan(_monthPlanId);
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		Hashtable hashtable = new Hashtable();
		List list = tawwpExecuteContentDao.listExecuteContent(tawwpMonthPlan,
				_startdate, _enddate, _cycle);
		List tempList = null;
		List fileList = null;
		Iterator iterator = null;

		for (int i = 0; i < list.size(); i++) {
			tawwpExecuteContent = (TawwpExecuteContent) list.get(i);
			tawwpExecuteContentVO = new TawwpExecuteContentVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteContentVO,
					tawwpExecuteContent);

			tawwpExecuteContentVO.setUserName(tawwpUtilDAO
					.getUserNames(tawwpExecuteContentVO.getUserName())); // 执行人姓名
			tawwpExecuteContentVO.setCruser(tawwpUtilDAO
					.getUserNames(tawwpExecuteContentVO.getCruser())); // 执行人姓名
			tawwpExecuteContentVO.setCycleName(tawwpUtilDAO
					.getCycleName(tawwpExecuteContentVO.getCycle())); // 周期名称
			tawwpExecuteContentVO.setFormName(tawwpUtilDAO
					.getAddonsName(tawwpExecuteContentVO.getFormId())); // 附加表单名称
			tawwpExecuteContentVO
					.setEligibilityName(tawwpExecuteContentVO
							.getEligibilityName(tawwpExecuteContentVO
									.getEligibility())); // 合格标志
			tawwpExecuteContentVO.setExecuteFlagName(tawwpExecuteContentVO
					.getExecuteFlagName(Integer.parseInt(tawwpExecuteContentVO
							.getExecuteFlag()))); // 填写标志
			iterator = tawwpExecuteContent.getTawwpExecuteFiles().iterator();
			fileList = new ArrayList();
			while (iterator.hasNext()) {
				TawwpExecuteFile tawwpExecuteFile = (TawwpExecuteFile) iterator
						.next();
				TawwpExecuteFileVO tawwpExecuteFileVO = new TawwpExecuteFileVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteFileVO,
						tawwpExecuteFile);
				fileList.add(tawwpExecuteFileVO);
			}
			tawwpExecuteContentVO.setExecuteFileListVO(fileList);

			iterator = tawwpExecuteContent.getTawwpExecuteContentUsers()
					.iterator();
			TawwpExecuteContentUserVO tawwpExecuteContentUserVO = new TawwpExecuteContentUserVO();
			List tawwpExecuteContentUserListVO = new ArrayList();
			TawwpExecuteContentUser tawwpExecuteContentUser = new TawwpExecuteContentUser();
			while (iterator.hasNext()) {
				tawwpExecuteContentUser = (TawwpExecuteContentUser) iterator
						.next();
				tawwpExecuteContentVO
						.setExecuteContentUserId(tawwpExecuteContentUser
								.getId());
				MyBeanUtils.copyPropertiesFromDBToPage(
						tawwpExecuteContentUserVO, tawwpExecuteContentUser);

				tawwpExecuteContentUserListVO.add(tawwpExecuteContentUserVO);
			}

			tawwpExecuteContentVO
					.setExecuteContentUserListVO(tawwpExecuteContentUserListVO);

			tempList = (List) hashtable.get(tawwpExecuteContent.getStartDate()
					.substring(0, 10));
			if (tempList == null) {
				tempList = new ArrayList();
				tempList.add(tawwpExecuteContentVO);
				hashtable.put(tawwpExecuteContent.getStartDate().substring(0,
						10), tempList);
			} else {
				tempList.add(tawwpExecuteContentVO);
			}
		}

		return hashtable;
	}

	public boolean addExecuteAssess(String _deptId, String _checkUser,
			String _content, String _monthPlanId, String _flowSerial,
			String _stepSerial) throws Exception {

		TawwpMonthPlan tawwpMonthPlan = null;
		boolean flag = true;
		try {

			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			// 修改月度作业计划执行状态为"归档"
			tawwpMonthPlan.setExecuteState("1");
			// 封装月度作业计划考核信息对象
			TawwpExecuteAssess tawwpExecuteAssess = new TawwpExecuteAssess(
					_deptId, _checkUser, _content, "1", TawwpUtil
							.getCurrentDateTime(), "", _flowSerial,
					_stepSerial, tawwpMonthPlan, null);

			// 更新月度作业计划状�?
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);
			// 保存作业计划考核信息
			tawwpExecuteAssessDao.saveExecuteAssess(tawwpExecuteAssess);
			// 批量更新执行内容执行人对�?状态为"考核�?
			tawwpMonthExecuteUserDao.updateState(tawwpMonthPlan, "2");

			return flag;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("执行作业计划考核信息保存出现异常");
		}
	}

	/**
	 * 业务逻辑:考核执行作业计划_归档(ASSESS-EXECUTE-PLAN-002)
	 * 
	 * @param _exexuteAssessId
	 *            String 月度作业计划考核信息编号
	 * @param _content
	 *            String 考核信息
	 * @param _checkUser
	 *            String 下一步骤审批人，如果步骤不存在可以为�?
	 * @param _currentCheckUser
	 *            String 考核�?
	 * @throws Exception
	 *             异常
	 */
	public void pigeonhole(String _exexuteAssessId, String _content,
			String _currentCheckUser) throws Exception {

		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		// 月度作业计划考核信息对象
		TawwpExecuteAssess tawwpExecuteAssess = null;
		// 月度作业计划编号
		String monthPlanId = "";
		try {
			// 获取月度作业计划考核信息对象
			tawwpExecuteAssess = tawwpExecuteAssessDao
					.loadExecuteAssess(_exexuteAssessId);
			// 封装月度作业计划考核信息对象
			tawwpExecuteAssess.setState("1");
			tawwpExecuteAssess.setContent(_content);
			tawwpExecuteAssess.setCheckUser(_currentCheckUser);
			tawwpExecuteAssess.setChecktime(TawwpUtil.getCurrentDateTime());
			// 更新作业计划考核信息
			tawwpExecuteAssessDao.updateExecuteAssess(tawwpExecuteAssess);
			// -----------------------------------------------------------------------
			// 获取月度作业计划编号
			monthPlanId = (tawwpExecuteAssess.getTawwpMonthPlan()).getId();
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(monthPlanId);
			// 修改月度作业计划执行状态为"归档"
			tawwpMonthPlan.setExecuteState("1");

			// 更新月度作业计划执行状�?
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);
			// 批量更新执行内容执行人对�?与月度作业计划执行状态一�?
			tawwpMonthExecuteUserDao.updateState(tawwpMonthPlan, "1");
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("执行作业计划考核信息修改出现异常");
		}
	}

	public List listMonthPlan(String _userId, String _deptId) throws Exception {

		// List对象
		List monthExecuteUserList = null;

		String year = TawwpUtil.getCurrentDateTime("yyyy");
		String month = TawwpUtil.getCurrentDateTime("MM");
		// 岗位编号字符�?
		String orgPostIdStr = "";
		TawwpMonthExecuteUser monthExecuteUser = null;
		TawwpMonthPlan monthPlan = null;
		List monthList = new ArrayList();
		String ids = "";
		try {
			// 获取该用户所在岗位编号集�?
			// orgPostIdStr = tawwpUtilDAO.getOrgPostIdStr(_userId);
			// 取得执行人信息列�?modified by lijia 2005-11-03)
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthByExecuteUser(_userId, _deptId, orgPostIdStr,
							year, month);

			for (int i = 0; i < monthExecuteUserList.size(); i++) {
				monthPlan = (TawwpMonthPlan) monthExecuteUserList.get(i);
				if (ids.indexOf(monthPlan.getId()) < 0) {
					monthList.add(monthPlan);
					ids += "," + monthPlan.getId();
				}

			}
			// 返回封装月度计划VO信息的Hashtable
			return monthList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("显示日常执行作业计划出现异常");
		} finally {
			monthExecuteUserList = null;
			monthList = null;
		}
	}

	/**
	 * 业务逻辑：批量填写执行作业计划执行内容_显示(BROWSE-EXECUTE-PLAN-001)
	 * 
	 * @param _userId
	 *            String 用户�?
	 * @param _deptId
	 *            String 部门编号
	 * @param _roomId
	 *            String 机房编号
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _dutyStartTime
	 *            String 班次开始时�?如果批量填写“日常”则为空
	 * @param _dutyEndTime
	 *            String 班次结束时间,如果批量填写“日常”则为空
	 * @param _flag
	 *            String 标志�?标识填写的是日常执行作业计划执行内容,还是值班�?
	 * @param _stubFlag
	 *            String 代理标志
	 * @param _date
	 *            String 需要批量执行的日期
	 * @throws Exception
	 *             异常
	 * @return List 执行作业计划执行内容(整体)VO对象集合
	 */
	public List loadExecuteContentUsersView(String _userId, String _deptId,
			String _roomId, String _monthPlanId, String _dutyStartTime,
			String _dutyEndTime, String _flag, String _stubFlag, String _date,
			String _year, String _month) throws Exception {
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO对象
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 集合对象
		Iterator tawwpMonthExecutes = null;
		List executeContentVOList = new ArrayList();

		String executerType = "";
		String fileStr = "";
		String executeDate = "";
		List executeContentList = new ArrayList();
		try {

			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);

			// 如果_date为空,表示为当日的批量填写
			if ("".equals(_date)) {
				executeDate = TawwpUtil.getCurrentDateTime();
				// 不为空则表示为指定日期的批量填写
			} else {
				executeDate = TawwpUtil.getCurrentDateTime(_year + "-" + _month
						+ "-" + _date + " HH:mm:ss");
			}

			//
			executeContentList = tawwpExecuteContentDao
					.loadExecuteContentByMonthId(_monthPlanId, "", "",
							executeDate);

			for (int i = 0; i < executeContentList.size(); i++) {
				tawwpExecuteContent = (TawwpExecuteContent) executeContentList
						.get(i);
				executerType = tawwpExecuteContent.getExecuterType();

				/*
				 * 如果 1标志�?日常"�?2执行人类型不为值班�?
				 */
				if ("daily".equalsIgnoreCase(_flag.trim())
						&& !"3".equals(executerType)) {

					// 判断当前用户是否为指定执行内容的执行�?
					if (this.judgeExecuterByContent(_userId, _deptId, _roomId,
							tawwpExecuteContent)) {

						tawwpExecuteContentVO = new TawwpExecuteContentVO();
						// 封装执行作业计划执行内容(整体)VO对象
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpExecuteContentVO, tawwpExecuteContent);
						String acessories = "";

						/*
						 * 如果 1未执行�?2或者已经执行且执行类型为多人填写多�?但当前用户没有进行填�?
						 */
						tawwpExecuteContentVO
								.setCycleName(tawwpUtilDAO
										.getCycleName(tawwpExecuteContentVO
												.getCycle())); // 周期名称
						tawwpExecuteContentVO.setFormName(tawwpUtilDAO
								.getAddonsName(tawwpExecuteContentVO
										.getFormId())); // 附加表单名称
						// edit by gongyufeng 修改每个执行内容包含默认执行�?
						// tawwpExecuteContentVO.setContent(""); //
						// 修改每个未完成的内容为空add
						// 对备注数据的处理(新增)
						tawwpExecuteContentVO.setRemark(tawwpExecuteContent
								.getRemark());
						tawwpExecuteContentVO.setExtendremark(StaticMethod
								.null2String(tawwpExecuteContent
										.getExtendremark())); // TODO 执行帮助;
						// by denden贵州
						tawwpExecuteContentVO.setName(StaticMethod
								.null2String(tawwpExecuteContent.getName())); // 执行内容名称
						tawwpExecuteContentVO.setExecuteType(tawwpMonthPlan
								.getExecuteType()); // 执行类型,用于区别是否显示“同组执行”链�?
						if ("stub".equals(_stubFlag)) {
							tawwpExecuteContentVO.setStubFlag("1"); // 代理标志
							tawwpExecuteContentVO.setUserByStub(_userId); // 被代理用�?
						} else {
							tawwpExecuteContentVO.setStubFlag("0"); // 代理标志
							tawwpExecuteContentVO.setUserByStub(""); // 被代理用�?
						}
						fileStr = this.getFileStrOfContent(tawwpExecuteContent);
						tawwpExecuteContentVO.setFileName(StaticMethod
								.null2String(fileStr));
						if (!"".equals(fileStr)) {
							tawwpExecuteContentVO.setFileCount(fileStr
									.split(",").length);
						} else {
							tawwpExecuteContentVO.setFileCount(0);
						}
						// 如果已经执行且执行类型为多人填写一份�?
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "0".equals(tawwpMonthPlan.getExecuteType())) {
							// tawwpExecuteContentVO.setContent(new
							// String(tawwpExecuteContent.getContent().getBytes("gb2312"),"819"));
							// //增加已经填写过的执行内容add by denden贵州
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent()); // 增加已经填写过的执行内容add
							// 对备注数据的处理(新增)
							tawwpExecuteContentVO.setRemark(tawwpExecuteContent
									.getRemark());
							System.out.println(tawwpExecuteContent.getRemark());
							tawwpExecuteContentVO.setExtendremark(StaticMethod
									.null2String(tawwpExecuteContent
											.getExtendremark())); // TODO 执行帮助
							System.out.println(tawwpExecuteContent
									.getExtendremark());
							// by denden贵州
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContent
											.getFormDataId()); // 附加表单记录
							/*
							 * fileStr = this
							 * .getFileStrOfContent(tawwpExecuteContent);
							 * tawwpExecuteContentVO.setFileName(StaticMethod
							 * .null2String(fileStr)); if (!"".equals(fileStr)) {
							 * tawwpExecuteContentVO.setFileCount(fileStr
							 * .split(",").length); } else {
							 * tawwpExecuteContentVO.setFileCount(0); }
							 */

							if (tawwpExecuteContent
									.getTawwpExecuteContentUsers().size() > 0) {
								TawwpExecuteContentUser executecontentuser = (TawwpExecuteContentUser) tawwpExecuteContent
										.getTawwpExecuteContentUsers()
										.iterator().next();
								tawwpExecuteContentVO
										.setWriteDate(executecontentuser
												.getWriteDate());
								tawwpExecuteContentVO
										.setExecuteContentUserId(executecontentuser
												.getId());

							}
						}

						/*
						 * 如果 1已经执行�?2多人填写多份�?3执行人字符串(执行内容(整体)的cruser字段)中含有当前用�?
						 */
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "1".equals(tawwpMonthPlan.getExecuteType())
								&& ("," + tawwpExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") >= 0) {

							// 获取执行作业计划执行内容(单一内容)对象
							// tawwpExecuteContentVO.setContent(tawwpExecuteContent.getContent());
							// //增加已经填写过的执行内容add by denden贵州
							/*
							 * tawwpExecuteContentVO.setContent(new String(
							 * tawwpExecuteContent.getContent().getBytes(
							 * "819"), "gb2312"));
							 */// 增加已经填写过的执行内容add
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent());
							// by denden贵州
							tawwpExecuteContentUser = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 附加表单记录
							tawwpExecuteContentVO
									.setCrtime(tawwpExecuteContentUser
											.getCrtime()); // 执行时间
							tawwpExecuteContentVO
									.setWriteDate(tawwpExecuteContentUser
											.getWriteDate());
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent()); // 增加已经填写过的执行内容add
							// 对备注数据的处理(新增)
							tawwpExecuteContentVO
									.setRemark(tawwpExecuteContentUser
											.getRemark());
							tawwpExecuteContentVO.setExtendremark(StaticMethod
									.null2String(tawwpExecuteContent
											.getExtendremark()));
							System.out.println(tawwpExecuteContent
									.getExtendremark());
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent()); // 增加已经填写过的执行内容add

							// /*******************************************************************
							// tawwpExecuteContentVO.setContent("");
							// tawwpExecuteContentVO.setContent(tawwpExecuteContentUser.getContent()
							// ); //填写内容
							// /******************************************************************8
							/*
							 * fileStr = this
							 * .getFileStrOfUser(tawwpExecuteContentUser);
							 * tawwpExecuteContentVO.setFileName(StaticMethod
							 * .null2String(fileStr)); if (!"".equals(fileStr)) {
							 * tawwpExecuteContentVO.setFileCount(fileStr
							 * .split(",").length); } else {
							 * tawwpExecuteContentVO.setFileCount(0); }
							 */
							tawwpExecuteContentVO
									.setExecuteContentUserId(tawwpExecuteContentUser
											.getId());

						}

						if (tawwpExecuteContentVO.getFileName() == null) {
							tawwpExecuteContentVO.setFileName("");
						}
						if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
							tawwpExecuteContentVO.setExecuteContentUserId("");
						}

						// 将执行作业计划执行内�?整体)VO对象添加到集合中
						executeContentVOList.add(tawwpExecuteContentVO);

					}
				}

			}
			// Collections.sort(executeContentVOList, new Comparator() {
			// public int compare(Object o1, Object o2) {
			// TawwpExecuteContentVO p1 = (TawwpExecuteContentVO) o1;
			// TawwpExecuteContentVO p2 = (TawwpExecuteContentVO) o2;
			// if (p1.getId().compareTo(p2.getId()) > 0) {
			// return 1;
			// }
			// return -1;
			//
			// }
			// });

			// 返回执行作业计划执行内容(整体)VO对象集合
			return executeContentVOList;
		}

		catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("批量填写执行作业计划执行内容出现异常");
		} finally {

		}
	}

	/**
	 * 判断指定执行内容的执行人范围里是否包�?当前用户或所在的部门、岗位、机�?
	 * 
	 * @param _userId
	 *            String 用户登录�?
	 * @param _deptId
	 *            String 部门编号
	 * @param _roomId
	 *            String 机房编号
	 * @param _tawwpMonthExecute
	 *            TawwpMonthExecute 月度作业计划执行内容对象
	 * @throws Exception
	 *             异常
	 * @return boolean 是否为执行人标志�?
	 */
	private boolean judgeExecuterByContent(String _userId, String _deptId,
			String _roomId, TawwpExecuteContent _tawwpExecuteContent)
			throws Exception {
		// 初始化数�?是否为执行人标志�?
		boolean executerFlag = false;

		// 执行人类型为"人员"
		if ("0".equals(_tawwpExecuteContent.getExecuterType())) {
			if (("," + _tawwpExecuteContent.getExecuter() + ",")
					.indexOf(_userId) > 0) {
				executerFlag = true;
			}
		}
		// 执行人类型为"部门"
		else if ("1".equals(_tawwpExecuteContent.getExecuterType())) {
			if (("," + _tawwpExecuteContent.getExecuter() + ",")
					.indexOf(_deptId) > 0) {
				executerFlag = true;
			}
		}
		// 执行人类型为"岗位"
		else if ("2".equals(_tawwpExecuteContent.getExecuterType())) {

			TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
			// 获取当前用户所属的岗位编号集合ccccccccccccccc
			// List _postIdList = tawwpUtilDAO.getOrgPostIdList(_userId);
			ArrayList _postIdList = new ArrayList();
			for (int i = 0; i < _postIdList.size(); i++) {
				if ((("," + _tawwpExecuteContent.getExecuter() + ",")
						.indexOf((String) _postIdList.get(i))) > 0) {
					executerFlag = true;
				}
			}
		}
		// 执行人类型为"机房"
		else if ("3".equals(_tawwpExecuteContent.getExecuterType())) {
			if (_roomId.equals(_tawwpExecuteContent.getExecuter())) {
				executerFlag = true;
			} else if (_tawwpExecuteContent.getExecuter().indexOf(",") >= 0) {
				String[] st = _tawwpExecuteContent.getExecuter().split(",");
				if (st.length > 0) {
					for (int i = 0; i < st.length; i++) {
						if (_roomId.equals(st[i])) {
							executerFlag = true;
						}
					}
				}
			}

		}

		// 返回是否为执行人标志�?
		return executerFlag;
	}

	/**
	 * 业务逻辑：批量填写执行作业计划执行内容_显示(广西需求)
	 * 
	 * @param _userId
	 *            String 用户�?
	 * @param _deptId
	 *            String 部门编号
	 * @param _roomId
	 *            String 机房编号
	 * @param _yaerPlanId
	 *            String 年度作业计划编号
	 * @param _dutyStartTime
	 *            String 班次开始时�?如果批量填写“日常”则为空
	 * @param _dutyEndTime
	 *            String 班次结束时间,如果批量填写“日常”则为空
	 * @param _flag
	 *            String 标志�?标识填写的是日常执行作业计划执行内容,还是值班�?
	 * @param _stubFlag
	 *            String 代理标志
	 * @param _date
	 *            String 需要批量执行的日期
	 * @throws Exception
	 *             异常
	 * @return List 执行作业计划执行内容(整体)VO对象集合
	 */
	public List addGxExecuteContentUsersView(String _userId, String _deptId,
			String _roomId, String _yaerPlanId, String _dutyStartTime,
			String _dutyEndTime, String _flag, String _stubFlag, String _date)
			throws Exception {
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO对象
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		List list = new ArrayList();

		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpExecuteContent tawwpExecuteContent = null;
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		 
		// 集合对象
		Iterator tawwpMonthExecutes = null;
		List executeContentVOList = new ArrayList();

		String executerType = "";
		String fileStr = "";
		String executeDate = "";
		String executeType = "";
		try {

			// 根据年计划取出执行对象列表
			list = tawwpMonthExecuteDao.getGxExecuteMonthtList(_yaerPlanId);

			// 如果_date为空,表示为当日的批量填写
			if ("".equals(_date)) {
				executeDate = TawwpUtil.getCurrentDateTime();
				// 不为空则表示为指定日期的批量填写
			} else {
				executeDate = TawwpUtil.getCurrentDateTime(tawwpMonthPlan
						.getYearFlag()
						+ "-"
						+ tawwpMonthPlan.getMonthFlag()
						+ "-"
						+ _date
						+ " HH:mm:ss");
			}

			// 获取作业计划执行内容集合
			tawwpMonthExecutes = list.iterator();
			String timeStr = StaticMethod.getLocalString();
			while (tawwpMonthExecutes.hasNext()) {
				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				executerType = tawwpMonthExecute.getExecuterType();
				executeType = tawwpMonthExecute.getTawwpMonthPlan()
						.getExecuteType();
				/*
				 * 如果 1标志日常"�?2执行人类型不为值班�?
				 */
				if ("daily".equalsIgnoreCase(_flag.trim())
						&& !"3".equals(executerType)) {

					// 判断当前用户是否为指定执行内容的执行�
					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						// 获取执行作业计划执行内容(整体)对象
						tawwpExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(executeDate,
										tawwpMonthExecute);

						if (tawwpExecuteContent == null) {
							// 如果取出结果集为�?则结束此次循�?�
							continue;
						}
						System.out.println("timeStr:=======" + timeStr);
						System.out
								.println("tawwpExecuteContent.getStartDate():======="
										+ tawwpExecuteContent.getStartDate());
						System.out
								.println("tawwpExecuteContent.getEndDate():======="
										+ tawwpExecuteContent.getEndDate());
						System.out
								.println("timeStr.compareTo(tawwpExecuteContent.getStartDate()):======="
										+ timeStr.compareTo(tawwpExecuteContent
												.getStartDate()));
						System.out
								.println("timeStr.compareTo(tawwpExecuteContent.getEndDate()):======="
										+ timeStr.compareTo(tawwpExecuteContent
												.getStartDate()));
						if (timeStr.compareTo(tawwpExecuteContent
								.getStartDate()) <= 0
								&& timeStr.compareTo(tawwpExecuteContent
										.getEndDate()) > 0) {
							continue;
						}

						tawwpExecuteContentVO = new TawwpExecuteContentVO();
						// 封装执行作业计划执行内容(整体)VO对象
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpExecuteContentVO, tawwpExecuteContent);
						String acessories = "";

						if (tawwpMonthExecute.getTawwpYearExecute() != null) {
							String yearexecuteid = tawwpMonthExecute
									.getTawwpYearExecute().getId();
							TawwpYearExecute yearexecute = tawwpYearExecuteDao
									.loadYearExecute(yearexecuteid);
							if (yearexecute.getTawwpModelExecute() != null) {
								String modelexecuteid = yearexecute
										.getTawwpModelExecute().getId();
								TawwpModelExecute modelexecute = tawwpModelExecuteDao
										.loadModelExecute(modelexecuteid);
								acessories = modelexecute.getAccessories();
							}

						}
						tawwpExecuteContentVO.setAccessories(tawwpMonthExecute
								.getAccessories());

						/*
						 * 如果 1未执行�?2或者已经执行且执行类型为多人填写多�?但当前用户没有进行填�?
						 */
						tawwpExecuteContentVO
								.setCycleName(tawwpUtilDAO
										.getCycleName(tawwpExecuteContentVO
												.getCycle())); // 周期名称
						tawwpExecuteContentVO.setFormName(tawwpUtilDAO
								.getAddonsName(tawwpExecuteContentVO
										.getFormId())); // / 附加表单名称
						// edit by gongyufeng 修改每个执行内容包含默认执行�?
						// tawwpExecuteContentVO.setContent(""); //
						// 修改每个未完成的内容为空add
						// 对备注数据的处理(新增)
						tawwpExecuteContentVO.setRemark("");
						tawwpExecuteContentVO.setExtendremark(StaticMethod
								.null2String(tawwpMonthExecute.getRemark()));
						// by denden add
						tawwpExecuteContentVO.setName(StaticMethod
								.null2String(tawwpMonthExecute.getName())); // 执行内容名称
						tawwpExecuteContentVO.setExecuteType(executeType); // 执行类型,用于区别是否显示“同组执行”链�?�
						if ("stub".equals(_stubFlag)) {
							tawwpExecuteContentVO.setStubFlag("1"); // 代理标志
							tawwpExecuteContentVO.setUserByStub(_userId);// 被代理�
						} else {
							tawwpExecuteContentVO.setStubFlag("0"); // 代理标志
							tawwpExecuteContentVO.setUserByStub(""); // 被代理用�
						}
						fileStr = this.getFileStrOfContent(tawwpExecuteContent);
						tawwpExecuteContentVO.setFileName(StaticMethod
								.null2String(fileStr));
						if (!"".equals(fileStr)) {
							tawwpExecuteContentVO.setFileCount(fileStr
									.split(",").length);
						} else {
							tawwpExecuteContentVO.setFileCount(0);
						}
						// 如果已经执行且执行类型为多人填写一份���
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "0".equals(executeType)) {
							// tawwpExecuteContentVO.setContent(new
							// String(tawwpExecuteContent.getContent().getBytes("gb2312"),"819"));
							// //增加已经填写过的执行内容add by denden贵州
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent()); // 增加已经填写过的执行内容add
							// 对备注数据的处理(新增)
							tawwpExecuteContentVO.setRemark(tawwpExecuteContent
									.getRemark());
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							// by denden贵州
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContent
											.getFormDataId()); // 附加表单记录
							fileStr = this
									.getFileStrOfContent(tawwpExecuteContent);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							System.out.println(tawwpExecuteContent
									.getTawwpExecuteContentUsers().size());
							if (tawwpExecuteContent
									.getTawwpExecuteContentUsers().size() > 0) {
								TawwpExecuteContentUser executecontentuser = (TawwpExecuteContentUser) tawwpExecuteContent
										.getTawwpExecuteContentUsers()
										.iterator().next();
								tawwpExecuteContentVO
										.setWriteDate(executecontentuser
												.getWriteDate());
								tawwpExecuteContentVO
										.setExecuteContentUserId(executecontentuser
												.getId());

							}
						}

						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "1".equals(executeType)
								&& ("," + tawwpExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") >= 0) {

							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent());
							// by denden add
							tawwpExecuteContentUser = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId());
							tawwpExecuteContentVO
									.setCrtime(tawwpExecuteContentUser
											.getCrtime());
							tawwpExecuteContentVO
									.setWriteDate(tawwpExecuteContentUser
											.getWriteDate());
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent());
							tawwpExecuteContentVO
									.setRemark(tawwpExecuteContentUser
											.getRemark());
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent());
							tawwpExecuteContentVO
									.setRemark(tawwpExecuteContentUser
											.getRemark());
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							fileStr = this
									.getFileStrOfUser(tawwpExecuteContentUser);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							tawwpExecuteContentVO
									.setExecuteContentUserId(tawwpExecuteContentUser
											.getId());

						}

						if (tawwpExecuteContentVO.getFileName() == null) {
							tawwpExecuteContentVO.setFileName("");
						}
						if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
							tawwpExecuteContentVO.setExecuteContentUserId("");
						}

						int flag = 0;

						for (int i = 0; i < executeContentVOList.size(); i++) {
							TawwpExecuteContentVO executeContentVO = new TawwpExecuteContentVO();
							executeContentVO = (TawwpExecuteContentVO) executeContentVOList
									.get(i);
							if (executeContentVO.getName().equals(
									tawwpExecuteContentVO.getName())) {
								flag = 1;
							}
						}
						if (flag == 0) {

							executeContentVOList.add(tawwpExecuteContentVO);
						}
					}
				}
				/*
				 * 如果 1已经执行�?2多人填写多份�?3执行人字符串(执行内容(整体)的cruser字段)中含有当前用�?
				 */
				else if ("duty".equals(_flag)
						&& "3".equals(tawwpMonthExecute.getExecuterType())
						&& (this
								.judgeDutyMonthPlan(tawwpMonthExecute
										.getExecuteDuty(), _dutyStartTime,
										_dutyEndTime))) {

					// 判断当前用户是否为指定执行内容的执行�
					if (this.judgeExecuter(_userId, _deptId, _roomId,
							tawwpMonthExecute)) {

						// 获取执行作业计划执行内容(整体)对象
						tawwpExecuteContent = tawwpExecuteContentDao
								.filterTawwpExecuteContent(TawwpUtil
										.getCurrentDateTime(),
										tawwpMonthExecute);

						if (tawwpExecuteContent == null) {
							// 如果取出结果集为�?则结束此次循环�
							continue;
						}
						// 改if由四川版本获得
						if (timeStr.compareTo(tawwpExecuteContent
								.getStartDate()) <= 0
								&& timeStr.compareTo(tawwpExecuteContent
										.getEndDate()) > 0) {
							continue;
						}
						tawwpExecuteContentVO = new TawwpExecuteContentVO();
						// 封装执行作业计划执行内容(整体)VO对象
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpExecuteContentVO, tawwpExecuteContent);
						String acessories = "";
						if (tawwpMonthExecute.getTawwpYearExecute() != null) {
							String yearexecuteid = tawwpMonthExecute
									.getTawwpYearExecute().getId();
							TawwpYearExecute yearexecute = tawwpYearExecuteDao
									.loadYearExecute(yearexecuteid);
							if (yearexecute.getTawwpModelExecute() != null) {
								String modelexecuteid = yearexecute
										.getTawwpModelExecute().getId();
								TawwpModelExecute modelexecute = tawwpModelExecuteDao
										.loadModelExecute(modelexecuteid);
								acessories = modelexecute.getAccessories();
							}

						}
						tawwpExecuteContentVO.setAccessories(tawwpMonthExecute
								.getAccessories());
						/*
						 * 如果 1未执行�?2或者已经执行且执行类型为多人填写多�?但当前用户没有进行填�?
						 */
						tawwpExecuteContentVO
								.setCycleName(tawwpUtilDAO
										.getCycleName(tawwpExecuteContentVO
												.getCycle())); // 周期名称
						tawwpExecuteContentVO.setFormName(tawwpUtilDAO
								.getAddonsName(tawwpExecuteContentVO
										.getFormId()));// 附加表单名称
						tawwpExecuteContentVO.setName(StaticMethod
								.null2String(tawwpMonthExecute.getName())); // 执行内容名称
						tawwpExecuteContentVO.setExecuteType(executeType); // 执行类型,用于区别是否显示“同组执行”链�?�
						tawwpExecuteContentVO.setFileName("");
						tawwpExecuteContentVO.setContent(tawwpExecuteContent
								.getContent()); // 修改每个未完成的内容为空add
						// 对备注数据的处理(新增)
						tawwpExecuteContentVO.setRemark("");
						tawwpExecuteContentVO.setExtendremark(StaticMethod
								.null2String(tawwpMonthExecute.getRemark()));
						// 如果已经执行且执行类型为多人填写一份�?��
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "0".equals(executeType)) {
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContent
											.getContent()); // 修改每个未完成的内容为空add
							// 对备注数据的处理(新增)
							tawwpExecuteContentVO.setRemark(StaticMethod
									.null2String(tawwpExecuteContent
											.getRemark()));
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							fileStr = this
									.getFileStrOfContent(tawwpExecuteContent);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							if (tawwpExecuteContent
									.getTawwpExecuteContentUsers().size() > 0) {
								TawwpExecuteContentUser executecontentuser = (TawwpExecuteContentUser) tawwpExecuteContent
										.getTawwpExecuteContentUsers()
										.iterator().next();
								tawwpExecuteContentVO
										.setWriteDate(executecontentuser
												.getWriteDate());
								tawwpExecuteContentVO
										.setExecuteContentUserId(executecontentuser
												.getId());

							}
						}

						/*
						 * 如果 1已经执行�?2多人填写多份�?3执行人字符串(执行内容(整体)的cruser字段)中含有当前用�?
						 */
						if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
								&& "1".equals(executeType)
								&& ("," + tawwpExecuteContent.getCruser() + ",")
										.indexOf("," + _userId + ",") >= 0) {

							// 获取执行作业计划执行内容(单一内容)对象
							tawwpExecuteContentUser = tawwpExecuteContentUserDao
									.filterTawwpExecuteContentUser(_userId,
											tawwpExecuteContent);
							tawwpExecuteContentVO
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 附加表单记录
							tawwpExecuteContentVO
									.setCrtime(tawwpExecuteContentUser
											.getCrtime()); // 执行时间
							//
							tawwpExecuteContentVO
									.setContent(tawwpExecuteContentUser
											.getContent()); // 修改每个未完成的内容为空add
							// 对备注数据的处理(新增)
							tawwpExecuteContentVO.setRemark(StaticMethod
									.null2String(tawwpExecuteContentUser
											.getRemark()));
							tawwpExecuteContentVO
									.setExtendremark(StaticMethod
											.null2String(tawwpMonthExecute
													.getRemark()));
							//
							fileStr = this
									.getFileStrOfUser(tawwpExecuteContentUser);
							tawwpExecuteContentVO.setFileName(StaticMethod
									.null2String(fileStr));
							if (!"".equals(fileStr)) {
								tawwpExecuteContentVO.setFileCount(fileStr
										.split(",").length);
							} else {
								tawwpExecuteContentVO.setFileCount(0);
							}
							tawwpExecuteContentVO
									.setExecuteContentUserId(tawwpExecuteContentUser
											.getId());

						}

						if (tawwpExecuteContentVO.getFileName() == null) {
							tawwpExecuteContentVO.setFileName("");
						}
						if (tawwpExecuteContentVO.getExecuteContentUserId() == null) {
							tawwpExecuteContentVO.setExecuteContentUserId("");
						}

						// 将执行作业计划执行内�?整体)VO对象添加到集合中
						int flag = 0;

						for (int i = 0; i < executeContentVOList.size(); i++) {
							TawwpExecuteContentVO executeContentVO = new TawwpExecuteContentVO();
							executeContentVO = (TawwpExecuteContentVO) executeContentVOList
									.get(i);
							if (executeContentVO.getName().equals(
									tawwpExecuteContentVO.getName())) {
								flag = 1;
							}
						}
						if (flag == 0) {
							executeContentVOList.add(tawwpExecuteContentVO);
						}
					}
				}

			}
			
			Collections.sort(executeContentVOList, new Comparator() {
				public int compare(Object o1, Object o2) {
					TawwpExecuteContentVO p1 = (TawwpExecuteContentVO) o1;
					TawwpExecuteContentVO p2 = (TawwpExecuteContentVO) o2;
					int k = -1;
					if (p1.getName().compareTo(p2.getName()) > 0) {
						k = 1;
					} else if (p1.getName().compareTo(p2.getName()) == 0) {
						if (p1.getId().compareTo(p2.getId()) > 0) {
							k = 1;
						} else {
							k = -1;
						}
					} else if (p1.getName().compareTo(p2.getName()) < 0) {
						k = -1;
					}
					return k;

				}
			});

			// 返回执行作业计划执行内容(整体)VO对象集合
			return executeContentVOList;
		}

		catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("批量填写执行作业计划执行内容出现异常");
		} finally {
			tawwpMonthExecutes = null;
		}
	}

	/**
	 * 广西值班作业计划（网元合并填写）
	 * 鍓嶇疆鏉′欢锛氬綋鍓嶇敤鎴峰湪鐧诲綍鏈烘埧涓浜庡�肩彮鐘舵��
	 * 
	 * @param _roomId
	 *            String 鏈烘埧缂栧彿
	 * @param _yearFlag
	 *            String 璁″垝骞村害
	 * @param _monthFlag
	 *            String 璁″垝鏈堝害
	 * @param _dutyStartTime
	 *            String 鐝寮�濮嬫椂闂�
	 * @param _dutyEndTime
	 *            String 鐝缁撴潫鏃堕棿
	 * @throws Exception
	 *             寮傚父
	 * @return List 杩斿洖鏈堝害浣滀笟璁″垝VO瀵硅薄闆嗗悎
	 */
	public List listDutyExecutePlanNew(String _roomId, String _yearFlag,
			String _monthFlag, String _dutyStartTime, String _dutyEndTime)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,DAO瀵硅薄
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List瀵硅薄
		List monthPlanVOList = new ArrayList();
		List monthExecuteUserList = new ArrayList();

		// model瀵硅薄
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		int eflag = 0;
		int listCount = 0;
		String executeDuty = "";

		try {
			// 鑾峰彇鏈堝害浣滀笟璁″垝鎵ц鍐呭鎵ц浜哄璞￠泦鍚�
			monthExecuteUserList = tawwpMonthExecuteUserDao
					.listMonthExecuteUserDuty(_roomId, _yearFlag, _monthFlag);
			String monthplanids = ",";
			String year = TawwpUtil.getCurrentDateTime("yyyy");
			String month = TawwpUtil.getCurrentDateTime("MM");
			List list=tawwpExecuteContentDao
			.listExecuteContentCount(_dutyStartTime, _dutyEndTime);
			Hashtable hashtableDuty = new Hashtable();
			hashtableDuty = this.listExecuteContentCount(hashtableDuty,list);
			for (int i = 0; i < monthExecuteUserList.size(); i++) {
				//获取今天是否存在可执行项
				tawwpMonthPlan = ((TawwpMonthExecuteUser) monthExecuteUserList.get(i)).getTawwpMonthPlan();
				// 鍙栧嚭鎵ц鐝缂栧彿
				executeDuty = ((TawwpMonthExecuteUser) monthExecuteUserList
						.get(i)).getExecuteDuty();
				// 鍒ゆ柇鏈堝害浣滀笟璁″垝鏄惁搴旇,鍦ㄤ綔涓哄弬鏁颁紶鍏ョ殑鐝寮�濮嬨�佺粨鏉熸椂闂磋寖鍥翠互鍐呮墽琛�
				if(_monthFlag.equals(month)&& _yearFlag.equals(year)){
					listCount = StaticMethod.nullObject2int(hashtableDuty.get(tawwpMonthPlan.getId()));
				}else{
					listCount = 1;	
				}
				if (this.judgeDutyMonthPlan(executeDuty, _dutyStartTime,
						_dutyEndTime) && listCount > 0) {
					// 鍙栧嚭model瀵硅薄,澶勭悊鍚庡皝瑁呮垚VO瀵硅薄
					if (monthplanids.indexOf(tawwpMonthPlan.getId()) == -1) {
						monthplanids = monthplanids + tawwpMonthPlan.getId()
								+ ",";
						tawwpMonthPlanVO = new TawwpMonthPlanVO();
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
						// 鑾峰彇鎵ц绫诲瀷鍚嶇О
						tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
								.getExecuteTypeName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteType())));
						// 鑾峰彇閮ㄩ棬鍚嶇О
						tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
								.getDeptName(tawwpMonthPlanVO.getDeptId()));  
						// 鑾峰彇绯荤粺绫诲瀷鍚嶇О
						tawwpMonthPlanVO   
								.setSysTypeName(tawwpUtilDAO
										.getSysTypeName(tawwpMonthPlanVO
												.getSysTypeId()));
						// 鑾峰彇缃戝厓绫诲瀷鍚嶇О
						tawwpMonthPlanVO
								.setNetTypeName(tawwpUtilDAO
										.getNetTypeName(tawwpMonthPlanVO
												.getNetTypeId()));
						// 鑾峰彇鍒涘缓浜哄鍚�
						tawwpMonthPlanVO.setUserName(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getCruser()));
						// 鑾峰彇鍒跺畾鐘舵�佸悕绉�
						tawwpMonthPlanVO
								.setConstituteStateName(TawwpMonthPlanVO
										.getConstituteStateName(Integer
												.parseInt(tawwpMonthPlanVO
														.getConstituteState())));
						// 鑾峰彇鎵ц鐘舵�佸悕绉�
						tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getExecuteState())));
						 eflag = tawwpExecuteContentDao.countExecuteFlagByYearPlanIdDuty
						 (tawwpMonthPlanVO.getYearPlanId(),_roomId,"",TawwpUtil
									.getCurrentDateTime("yyyy-MM-dd"));
						    
						    if(eflag==0){
						    	tawwpMonthPlanVO.setExecuteStateName("未执行");
						    }else{
						    	tawwpMonthPlanVO.setExecuteStateName("已执行");
						    }
						 
						// 鑾峰彇鎵ц璐熻矗浜哄鍚�
						tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
								.getUserName(tawwpMonthPlanVO.getPrincipal()));
						// 鑾峰彇缃戝厓鍚嶇О
						if (tawwpMonthPlan.getTawwpNet() != null) {
							tawwpMonthPlanVO.setNetName(StaticMethod
									.null2String(tawwpMonthPlan.getTawwpNet()
											.getName()));
						} else {
							tawwpMonthPlanVO.setNetName("无网元");
						}

						// 灏哣O瀵硅薄娣诲姞绱у垪琛ㄤ腑
						monthPlanVOList.add(tawwpMonthPlanVO);
					}
				}
			}

			// 杩斿洖灏佽鏈堝害璁″垝VO淇℃伅鐨凩ist
			return monthPlanVOList;
		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鏄剧ず鍊肩彮鎵ц浣滀笟璁″垝鍑虹幇寮傚父");
		} finally {
			monthExecuteUserList = null;
		}
	}
	
	
	/**
	 * 
	 * 计算月度作业计划所属的executecontent数量，并将结果压入Hashtable
	 * 
	 * @param _hashtable
	 *            Hashtable 
	 * @param _list
	 *            List 
	 *
	 * @throws Exception
	 *             
	 */
	private Hashtable listExecuteContentCount(Hashtable _hashtable, List _list)
			throws TawwpException {
		ITawSystemDeptManager tawDeptDAO = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
	

		Iterator iterator = null;
		iterator = _list.iterator();

		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据

				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[0]) == null) {
					
					_hashtable.put((String) countObject[0], countObject[1]);
				}

			}
		} catch (Exception e) {
			throw new TawwpException("作业计划统计出现异常");
		}
		return _hashtable;
	}
	
	
	
	/**
	 * 
	 * 涓氬姟閫昏緫:鎵归噺濉啓鍊肩彮鎵ц浣滀笟璁″垝鍐呭_淇濆瓨(FILL-EXECUTE-CONTENT-003)
	 * 
	 * @param _executeContentUserHash
	 *            Hashtable 鎵ц(鍐呭)淇℃伅闆嗗悎
	 * @param _dutyStartTime
	 *            String 鐝寮�濮嬫椂闂�
	 * @param _dutyEndTime
	 *            String 鐝缁撴潫鏃堕棿
	 * @param _userId
	 *            String 褰撳墠鐢ㄦ埛鐧诲綍鍚�
	 * @param _executeType
	 *            String 鎵ц绫诲瀷
	 *            (key鍊间负executeContentId,value涓烘湭澶勭悊鐨別xecuteContentUser瀵硅薄)
	 * @throws Exception
	 *             寮傚父
	 */
	public void addExecuteContentUsersSave(Hashtable _executeContentUserHash,
			String _userId, String _executeType,String _yearPlanId) throws Exception {

		// // 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();

		// 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteContentUser tempExecuteContentUser = null;
		// 鎵ц鍐呭(鏁翠綋)缂栧彿
		String executeContentId = "";
		// 鑾峰彇鎵ц鍐呭(鏁翠綋)缂栧彿闆嗗悎
		Enumeration tempVar = _executeContentUserHash.keys();
		
		TawwpExecuteFlag tawwpExecuteFlag = null;
		// 鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		try {
			while (tempVar.hasMoreElements()) {

				// 鑾峰彇hashtable鐨凨ey鍊�
				executeContentId = (String) tempVar.nextElement();

				// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
				tawwpExecuteContent = tawwpExecuteContentDao
						.loadExecuteContent(executeContentId);
				// 鑾峰彇褰撳墠鏃堕棿
				String executeTime = TawwpUtil.getCurrentDateTime();
				// 瀹氫箟瓒呮椂鏍囧織
				String executeFlag = "";
				// 鍒ゆ柇濉啓鏄惁瓒呮椂
				if (this.judgeTimeOut(executeTime, tawwpExecuteContent
						.getEndDate())) {
					executeFlag = "1"; // 鎸夋椂

				} else {
					executeFlag = "2"; // 瓒呮椂
				}
				// gongyufeng
				// --------------------------------------------------------------

				// 濡傛灉澶氫汉濉啓澶氫唤
				if ("1".equals(_executeType)) {
					// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
					tempExecuteContentUser = tawwpExecuteContentUserDao
							.filterTawwpExecuteContentUser(_userId,
									tawwpExecuteContent);
					// 濡傛灉瀵硅薄涓嶄负绌�
					if (tempExecuteContentUser != null) {

						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						tempExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()); // 鎵ц鍐呭
						tempExecuteContentUser
								.setRemark(tawwpExecuteContentUser.getRemark()); // 澶囨敞淇℃伅
						tempExecuteContentUser
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
						tempExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tempExecuteContentUser
								.setWriteDate(tawwpExecuteContentUser
										.getWriteDate());
						tempExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tempExecuteContentUser
								.setNormalFlag(tawwpExecuteContentUser
										.getNormalFlag());// 姝ｅ父鏍囧織
						tempExecuteContentUser
								.setReason(tawwpExecuteContentUser.getReason());
						// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tempExecuteContentUser);
					} else {

						tawwpExecuteContentUser = new TawwpExecuteContentUser();
						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
						tawwpExecuteContentUser
								.setStartDate(tawwpExecuteContent
										.getStartDate()); // 璁″垝寮�濮嬫椂闂�
						tawwpExecuteContentUser.setEndDate(tawwpExecuteContent
								.getEndDate()); // 璁″垝缁撴潫鏃堕棿
						tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
						tawwpExecuteContentUser.setFormId(tawwpExecuteContent
								.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
						tawwpExecuteContentUser
								.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織1
						String normalFlag = (String) DictMgrLocator
								.getDictService()
								.itemId2name(
										Util.constituteDictId("dict-workplan",
												"normalFlag"),
										StaticMethod
												.null2String(tawwpExecuteContentUser
														.getNormalFlag()));
						tawwpExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()
										+ "   "
										+ userManager.getTawSystemUserByuserid(
												_userId).getUsername()
										+ "  "
										+ normalFlag + "| ");
						// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tawwpExecuteContentUser);
					}
					tawwpExecuteContent.setNormalFlag(tawwpExecuteContentUser
							.getNormalFlag());// 姝ｅ父鏍囧織

					// 澶氫汉濉啓涓�浠�
				} else {

					// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
					tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
							.get(executeContentId);

					if (tawwpExecuteContentUser.getId() != null
							&& !"".equals(tawwpExecuteContentUser.getId())) {

						tempExecuteContentUser = tawwpExecuteContentUserDao
								.loadExecuteContentUser(tawwpExecuteContentUser
										.getId());

						tempExecuteContentUser
								.setContent(tawwpExecuteContentUser
										.getContent()); // 鎵ц鍐呭
						tempExecuteContentUser
								.setRemark(tawwpExecuteContentUser.getRemark()); // 澶囨敞淇℃伅
						tempExecuteContentUser
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
						tempExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tempExecuteContentUser
								.setWriteDate(tawwpExecuteContentUser
										.getWriteDate());
						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tempExecuteContentUser
								.setNormalFlag(tawwpExecuteContentUser
										.getNormalFlag());// 姝ｅ父鏍囧織
						tempExecuteContentUser
								.setReason(tawwpExecuteContentUser.getReason());
						// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tempExecuteContentUser);
					} else {

						tawwpExecuteContentUser = new TawwpExecuteContentUser();
						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
						tawwpExecuteContentUser
								.setStartDate(tawwpExecuteContent
										.getStartDate()); // 璁″垝寮�濮嬫椂闂�
						tawwpExecuteContentUser.setEndDate(tawwpExecuteContent
								.getEndDate()); // 璁″垝缁撴潫鏃堕棿
						tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
						tawwpExecuteContentUser.setFormId(tawwpExecuteContent
								.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
						tawwpExecuteContentUser
								.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
						tawwpExecuteContentUser.setCrtime(TawwpUtil
								.getCurrentDateTime()); // 鎵ц鏃堕棿
						tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
						tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
						tawwpExecuteContentUser.setStubUser("");

						// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
						tawwpExecuteContentUserDao
								.saveExecuteContentUser(tawwpExecuteContentUser);
					}
					tawwpExecuteContent.setNormalFlag(tawwpExecuteContentUser
							.getNormalFlag());// 姝ｅ父鏍囧織
					tawwpExecuteContent.setContent(tawwpExecuteContentUser
							.getContent());
					tawwpExecuteContent.setRemark(tawwpExecuteContentUser
							.getRemark());
					tawwpExecuteContent.setReason(tawwpExecuteContentUser
							.getReason());
				}

				// --------------------------------------------------------------

				// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
				if (null == tawwpExecuteContent.getFormDataId()
						|| "".equals(tawwpExecuteContent.getFormDataId())) {
					tawwpExecuteContent.setFormDataId(tawwpExecuteContentUser
							.getFormDataId());
				}

				// 淇敼濉啓鏍囧織()
				if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
					// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
					tawwpExecuteContent.setExecuteFlag(executeFlag);
				} else if (!executeFlag.equals(tawwpExecuteContent
						.getExecuteFlag())) {
					if ("2".equals(executeFlag)) {
						/*
						 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
						 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
						 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
						 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
						 */
						tawwpExecuteContent.setExecuteFlag(executeFlag);
					}
				}

				// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
				if (!"".equals(tawwpExecuteContent.getCruser())) {
					if (("," + tawwpExecuteContent.getCruser() + ",")
							.indexOf("," + _userId + ",") < 0) {
						// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
						tawwpExecuteContent.setCruser(tawwpExecuteContent
								.getCruser()
								+ "," + tawwpExecuteContentUser.getCruser());
					}
				} else {
					// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
					tawwpExecuteContent.setCruser(tawwpExecuteContentUser
							.getCruser());
				}

				// --------------------------------------------------------------

				// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
				tawwpExecuteContent.setCrtime(tawwpExecuteContentUser
						.getCrtime()); // 瀹炶鏃堕棿edit by denden 璐靛窞鏈湴
				System.out.println("@@@@@@@@@@@@@@@@@ read input Quality!");
				if (!"0".equals(tawwpExecuteContent.getExecuteFlag())
						&& "1".equals(tawwpExecuteContent.getFileFlag())) {

					tawwpExecuteContent.setQualityCheckFlag("0");
				}
				tawwpExecuteContentDao.saveExecuteContent(tawwpExecuteContent);
			}
			
			// add by 龚玉峰 
			// 广西日常填写需要执行信息
		String date = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
		String month = TawwpUtil.getCurrentDateTime("MM");
		tawwpExecuteFlag = tawwpExecuteContentDao.loadExecuteFlagByYearPlanId(_yearPlanId,"","",date);
		if(tawwpExecuteFlag==null){
			tawwpExecuteFlag = new TawwpExecuteFlag();
			tawwpExecuteFlag.setYearPlanId(_yearPlanId);
			tawwpExecuteFlag.setExecutedate(date);
			tawwpExecuteFlag.setExecuter(_userId);
			tawwpExecuteFlag.setMonthFlag(month);
			tawwpExecuteFlag.setMonthPlanId("");
			tawwpExecuteFlag.setState("1");
			tawwpExecuteContentDao.saveTawwpExecuteFlag(tawwpExecuteFlag);
		}else{
			tawwpExecuteContentDao.updateTawwpExecuteFlag(tawwpExecuteFlag);
		}
		

		} catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭鎵归噺淇濆瓨鍑虹幇寮傚父");
		} finally {
			tempVar = null;
		}
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * 涓氬姟閫昏緫:鎵归噺濉啓鍊肩彮鎵ц浣滀笟璁″垝鍐呭_淇濆瓨(FILL-EXECUTE-CONTENT-003)
	 * 
	 * @param _executeContentUserHash
	 *            Hashtable 鎵ц(鍐呭)淇℃伅闆嗗悎
	 * @param _dutyStartTime
	 *            String 鐝寮�濮嬫椂闂�
	 * @param _dutyEndTime
	 *            String 鐝缁撴潫鏃堕棿
	 * @param _userId
	 *            String 褰撳墠鐢ㄦ埛鐧诲綍鍚�
	 * @param _executeType
	 *            String 鎵ц绫诲瀷
	 *            (key鍊间负executeContentId,value涓烘湭澶勭悊鐨別xecuteContentUser瀵硅薄)
	 * @throws Exception
	 *             寮傚父
	 */
	public void addDutyExecuteContentUsersSave(
			Hashtable _executeContentUserHash, String _dutyStartTime,
			String _dutyEndTime, String _userId, String _executeType,String _yearPlanId)
			throws Exception {

		// 鍒濆鍖栨暟鎹�,鍒涘缓DAO瀵硅薄
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpExecuteContentUserDAO tawwpExecuteContentUserDAO = new
		// TawwpExecuteContentUserDAO();

		// 鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		TawwpExecuteContentUser tempExecuteContentUser = null;
		// 鎵ц鍐呭(鏁翠綋)缂栧彿
		String executeContentId = "";
		// 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
		TawwpExecuteContent tawwpExecuteContent = null;
		// 鑾峰彇鎵ц鍐呭(鏁翠綋)缂栧彿闆嗗悎
		Enumeration tempVar = _executeContentUserHash.keys();
		TawwpExecuteFlag tawwpExecuteFlag = null;
		try {
			Iterator tawwpExecuteContentUsers = null;
			while (tempVar.hasMoreElements()) {
				Object obj = tempVar.nextElement();
				if (obj != null) {

					// 鑾峰彇hashtable鐨凨ey鍊�
					executeContentId = (String) obj;

					// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)瀵硅薄
					tawwpExecuteContent = tawwpExecuteContentDao
							.loadExecuteContent(executeContentId);
					// 鑾峰彇褰撳墠鏃堕棿
					String executeTime = TawwpUtil.getCurrentDateTime();
					// 瀹氫箟瓒呮椂鏍囧織
					String executeFlag = "";
					// 鍒ゆ柇濉啓鏄惁瓒呮椂
					if (this.judgeTimeOut(executeTime, _dutyEndTime)) {
						executeFlag = "1"; // 鎸夋椂
					} else {
						executeFlag = "2"; // 瓒呮椂
					}

					// --------------------------------------------------------------

					// 濡傛灉澶氫汉濉啓澶氫唤
					if ("1".equals(_executeType)) {
						// 鑾峰彇鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)瀵硅薄
						tempExecuteContentUser = tawwpExecuteContentUserDao
								.filterTawwpExecuteContentUser(_userId,
										tawwpExecuteContent);
						// 濡傛灉瀵硅薄涓嶄负绌�
						if (tempExecuteContentUser != null) {

							// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
							tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
									.get(executeContentId);

							tempExecuteContentUser
									.setContent(tawwpExecuteContentUser
											.getContent()); // 鎵ц鍐呭
							tempExecuteContentUser
									.setRemark(tawwpExecuteContentUser
											.getRemark()); // 澶囨敞淇℃伅
							tempExecuteContentUser
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							tempExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tempExecuteContentUser
									.setNormalFlag(tawwpExecuteContentUser
											.getNormalFlag());// 姝ｅ父鏍囧織
							tempExecuteContentUser
									.setReason(tawwpExecuteContentUser
											.getReason());
							// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.updateExecuteContentUser(tempExecuteContentUser);
						} else {

							tawwpExecuteContentUser = new TawwpExecuteContentUser();
							// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
							tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
									.get(executeContentId);

							// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
							tawwpExecuteContentUser
									.setStartDate(_dutyStartTime); // 璁″垝寮�濮嬫椂闂�
							tawwpExecuteContentUser.setEndDate(_dutyEndTime); // 璁″垝缁撴潫鏃堕棿
							tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
							tawwpExecuteContentUser
									.setFormId(tawwpExecuteContent.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
							tawwpExecuteContentUser
									.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
							tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
							tawwpExecuteContentUser.setStubUser("");

							// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.saveExecuteContentUser(tawwpExecuteContentUser);
						}

						// 澶氫汉濉啓涓�浠�
					} else {

						// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
						tawwpExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
								.get(executeContentId);

						if (tawwpExecuteContentUser.getId() != null
								&& !"".equals(tawwpExecuteContentUser.getId())) {

							tempExecuteContentUser = tawwpExecuteContentUserDao
									.loadExecuteContentUser(tawwpExecuteContentUser
											.getId());

							tempExecuteContentUser
									.setContent(tawwpExecuteContentUser
											.getContent()); // 鎵ц鍐呭
							tempExecuteContentUser
									.setRemark(tawwpExecuteContentUser
											.getRemark()); // 澶囨敞淇℃伅
							tempExecuteContentUser
									.setFormDataId(tawwpExecuteContentUser
											.getFormDataId()); // 闄勫姞琛ㄥ崟璁板綍缂栧彿
							tempExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tempExecuteContentUser
									.setNormalFlag(tawwpExecuteContentUser
											.getNormalFlag());// 姝ｅ父鏍囧織
							tempExecuteContentUser
									.setReason(tawwpExecuteContentUser
											.getReason());
							// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.updateExecuteContentUser(tempExecuteContentUser);
						} else {

							tempExecuteContentUser = new TawwpExecuteContentUser();
							// 鏍规嵁key鍊煎緱鍒版墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄
							tempExecuteContentUser = (TawwpExecuteContentUser) _executeContentUserHash
									.get(executeContentId);

							// 涓烘墽琛屼綔涓氳鍒掓墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)瀵硅薄灞炴�ц祴鍊�
							tawwpExecuteContentUser
									.setStartDate(tawwpExecuteContent
											.getStartDate()); // 璁″垝寮�濮嬫椂闂�
							tawwpExecuteContentUser
									.setEndDate(tawwpExecuteContent
											.getEndDate()); // 璁″垝缁撴潫鏃堕棿
							tawwpExecuteContentUser.setExecuteFlag(executeFlag); // 鎵ц鏍囧織
							tawwpExecuteContentUser
									.setFormId(tawwpExecuteContent.getFormId()); // 闄勪欢琛ㄥ崟鏍煎紡鏍囧織
							tawwpExecuteContentUser
									.setTawwpExecuteContent(tawwpExecuteContent); // 鎵ц鍐呭(鏁翠綋)瀵硅薄
							tawwpExecuteContentUser.setCrtime(TawwpUtil
									.getCurrentDateTime()); // 鎵ц鏃堕棿
							tawwpExecuteContentUser.setCruser(_userId); // 鎵ц浜�
							tawwpExecuteContentUser.setEligibility("0"); // 鍚堟牸鏍囧織
							tawwpExecuteContentUser.setStubUser("");

							// 淇濆瓨鎵ц浣滀笟璁″垝鎵ц鍐呭(鍗曚竴鐢ㄦ埛)
							tawwpExecuteContentUserDao
									.saveExecuteContentUser(tawwpExecuteContentUser);
						}

						tawwpExecuteContent.setContent(tawwpExecuteContentUser
								.getContent());
						tawwpExecuteContent.setRemark(tawwpExecuteContentUser
								.getRemark());
						tawwpExecuteContent
								.setNormalFlag(tawwpExecuteContentUser
										.getNormalFlag());
						tawwpExecuteContent.setReason(tawwpExecuteContentUser
								.getReason());

					}

					// --------------------------------------------------------------

					// 濡傛灉formDataId灞炴�у凡缁忔湁鍊�,鍒欎笉鍐嶈繘琛屾洿鏂�
					if ("".equals(tawwpExecuteContent.getFormDataId())) {
						tawwpExecuteContent
								.setFormDataId(tawwpExecuteContentUser
										.getFormDataId());
					}

					// 淇敼濉啓鏍囧織()
					if ("0".equals(tawwpExecuteContent.getExecuteFlag())) {
						// 濡傛灉涓�"鏈～鍐�",鍒欎互姝ゆ濉啓鐨勫～鍐欐爣蹇楀�间负鍑�
						tawwpExecuteContent.setExecuteFlag(executeFlag);
					} else if (!executeFlag.equals(tawwpExecuteContent
							.getExecuteFlag())) {
						if ("2".equals(executeFlag)) {
							/*
							 * 濡傛灉 1銆佹墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾笉涓�"鏈～鍐�"
							 * 2銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇楀拰鎵ц鍐呭(鏁翠綋)鐨勪笉涓�鑷�
							 * 3銆佹墽琛屽唴瀹�(鍗曚竴鐢ㄦ埛)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
							 * 鍒欐洿鏂版墽琛屽唴瀹�(鏁翠綋)鐨勫～鍐欐爣蹇椾负"瓒呮椂濉啓"
							 */
							tawwpExecuteContent.setExecuteFlag(executeFlag);
						}
					}

					// 淇敼鎵ц鍐呭(鏁翠綋)瀵硅薄cruser灞炴��,瀛樻斁鎵ц杩囪鍐呭鐨勭敤鎴风櫥褰曞悕,浠�","闅斿紑
					if (!"".equals(tawwpExecuteContent.getCruser())) {
						if (("," + tawwpExecuteContent.getCruser() + ",")
								.indexOf(","
										+ tawwpExecuteContentUser.getCruser()
										+ ",") < 0) {

							// 濡傛灉涓嶄负绌哄垯澧為噺娣诲姞
							tawwpExecuteContent
									.setCruser(tawwpExecuteContent.getCruser()
											+ ","
											+ tawwpExecuteContentUser
													.getCruser());
						}
					} else {
						// 濡傛灉涓虹┖鍒欑洿鎺ヨ祴鍊�
						tawwpExecuteContent.setCruser(tawwpExecuteContentUser
								.getCruser());
					}

					// --------------------------------------------------------------

					// 鏇存柊鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
					System.out.println("@@@@@@@@@@@@@@@@@ read input Quality!");
					if (!tawwpExecuteContent.getExecuteFlag().equals("0")
							&& tawwpExecuteContent.getFileFlag().equals("1")) {
						System.out.println("@@@@@@@@@@@@@@@@@ input Quality!");
						tawwpExecuteContent.setQualityCheckFlag("0");
					}
					tawwpExecuteContentDao
							.updateExecuteContent(tawwpExecuteContent);

				}
			}
			
			String date = TawwpUtil.getCurrentDateTime("yyyy-MM-dd");
			String month = TawwpUtil.getCurrentDateTime("MM");
			tawwpExecuteFlag = tawwpExecuteContentDao.loadExecuteFlagByYearPlanIdDuty(_yearPlanId,"","",date);
			if(tawwpExecuteFlag==null){
				tawwpExecuteFlag = new TawwpExecuteFlag();
				tawwpExecuteFlag.setYearPlanId(_yearPlanId);
				tawwpExecuteFlag.setExecutedate(date);
				tawwpExecuteFlag.setExecuter(_userId);
				tawwpExecuteFlag.setMonthFlag(month);
				tawwpExecuteFlag.setMonthPlanId("");
				tawwpExecuteFlag.setState("2");
				tawwpExecuteContentDao.saveTawwpExecuteFlag(tawwpExecuteFlag);
			}else{
				tawwpExecuteContentDao.updateTawwpExecuteFlag(tawwpExecuteFlag);
			}
			
		}

		catch (Exception e) {
			// 鎹曡幏寮傚父
			e.printStackTrace();
			throw new TawwpException("鎵ц浣滀笟璁″垝鎵ц鍐呭鎵归噺淇濆瓨鍑虹幇寮傚父");
		} finally {
			tempVar = null;
			// tawwpExecuteContentUsers = null;
		}

	}
}
