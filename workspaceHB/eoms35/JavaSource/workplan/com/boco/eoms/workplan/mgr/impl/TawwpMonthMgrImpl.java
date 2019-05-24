package com.boco.eoms.workplan.mgr.impl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.CellType;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.util.DutyMgrLocator;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentUserDao;
import com.boco.eoms.workplan.dao.ITawwpMonthCheckDao;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpMonthExecuteUserDao;
import com.boco.eoms.workplan.dao.ITawwpMonthPlanDao;
import com.boco.eoms.workplan.dao.ITawwpNetDao;
import com.boco.eoms.workplan.dao.ITawwpYearExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.flow.TawwpFlowManage;
import com.boco.eoms.workplan.flow.model.Step;
import com.boco.eoms.workplan.mgr.ITawwpMonthMgr;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;
import com.boco.eoms.workplan.model.TawwpMonthCheck;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthExecuteUser;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpYearExecute;
import com.boco.eoms.workplan.model.TawwpYearPlan;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthCheckVO;
import com.boco.eoms.workplan.vo.TawwpMonthExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpNetVO;
import com.boco.eoms.workplan.vo.TawwpYearExecuteVO;
import com.boco.eoms.workplan.vo.TawwpYearPlanVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 2:29:13 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpMonthMgrImpl implements ITawwpMonthMgr {

	private ITawwpYearPlanDao tawwpYearPlanDao;

	private ITawwpMonthPlanDao tawwpMonthPlanDao;

	private ITawwpMonthExecuteDao tawwpMonthExecuteDao;

	private ITawwpNetDao tawwpNetDao;

	private ITawwpYearExecuteDao tawwpYearExecuteDao;

	private ITawwpMonthCheckDao tawwpMonthCheckDao;

	private ITawwpMonthExecuteUserDao tawwpMonthExecuteUserDao;

	private ITawwpExecuteContentDao tawwpExecuteContentDao;

	private ITawwpExecuteContentUserDao tawwpExecuteContentUserDao;

	/**
	 * @param tawwpMonthCheckDao
	 *            the tawwpMonthCheckDao to set
	 */
	public void setTawwpMonthCheckDao(ITawwpMonthCheckDao tawwpMonthCheckDao) {
		this.tawwpMonthCheckDao = tawwpMonthCheckDao;
	}

	public void setTawwpExecuteContentUserDao(
			ITawwpExecuteContentUserDao tawwpExecuteContentUserDao) {
		this.tawwpExecuteContentUserDao = tawwpExecuteContentUserDao;
	}

	/**
	 * 业务逻辑：制定月度作业计划信息_单一网元(CUSTOMIZE-MONTHLY-PLAN-001)
	 * 
	 * @param _executeType
	 *            String 执行类型(0：多人填写一份 1：多人填写多份 2：顺序填写)
	 * @param _netId
	 *            String 网元编号
	 * @param _monthFlag
	 *            String 计划所属月度
	 * @param _cruser
	 *            String 创建人
	 * @param _principal
	 *            String 执行负责人
	 * @param _yearPlanId
	 *            String 年度作业计划编号
	 * @return 月度作业计划对象
	 * @throws Exception
	 *             异常
	 */
	public TawwpMonthPlan addMonthPlan(String _executeType, String _netId,
			String _monthFlag, String _cruser, String _principal,
			String _yearPlanId) throws Exception {

		// 初始化数据,创建DAO对象

		// 年度作业计划对象
		TawwpYearPlan tawwpYearPlan = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		// 网元对象
		TawwpNet tawwpNet = null;
		// 月度作业计划执行内容列表
		List monthExecuteList = null;

		// 是否存在网元的月度作业计划
		boolean flag = false;

		try {

			// 获取年度作业计划对象
			tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId);

			// 获取指定月度作业计划对象集合
			if (!_netId.equals("0")) {
				tawwpNet = tawwpNetDao.loadNet(_netId);
			}

			System.out.println("@@@@@@@@@@@@ set flag");
			flag = tawwpMonthPlanDao
					.exitMonthPlanOfNet(TawwpStaticVariable.MONTH[StaticMethod
							.null2int(_monthFlag)], tawwpYearPlan, tawwpNet);
			System.out.println("@@@@@@@@@@@@ set flag over");
			if (!flag) {
				// 封装月度作业计划对象
				tawwpMonthPlan = new TawwpMonthPlan(tawwpYearPlan.getName(),
						_executeType, tawwpNet, tawwpYearPlan.getDeptId(),
						tawwpYearPlan.getSysTypeId(), tawwpYearPlan
								.getNetTypeId(), tawwpYearPlan.getYearFlag(),
						TawwpStaticVariable.MONTH[StaticMethod
								.null2int(_monthFlag)], "0", _cruser, TawwpUtil
								.getCurrentDateTime(), "0", "0", _principal,
						tawwpYearPlan.getTawwpModelPlan(), tawwpYearPlan);
				// 保存月度作业计划信息
				tawwpMonthPlanDao.saveMonthPlan(tawwpMonthPlan);
				System.out.println("@@@@@@@@@@@@ save MonthPlan over");

				// --------------------------------------------------------------------
				/*
				 * 月度作业计划制定后,系统会自动根据年度作业计划的执行内容,生成默认的月度作业计划
				 * 执行内容,插入TAW_WP_MONTHEXECUTE表中。
				 */
				// 获取月度作业计划执行内容列表
				monthExecuteList = this.getMonthExecutes(tawwpYearPlan,
						tawwpMonthPlan);
				System.out.println("@@@@@@@@@@@@ get MonthExecutes over");
				if (monthExecuteList != null) {
					// 向数据库中插入月度作业计划执行内容记录
					for (int i = 0; i < monthExecuteList.size(); i++) {
						tawwpMonthExecuteDao
								.saveMonthExecute((TawwpMonthExecute) monthExecuteList
										.get(i));
					}
				}
			}
			// --------------------------------------------------------------------

			return tawwpMonthPlan;
		} catch (Exception e) {
			// 捕获异常
			System.out.println("@@@@@@@@@@@@@@ addMonthPlan" + e);
			e.printStackTrace();
			throw new TawwpException("月度作业计划保存出现异常");
		} finally {
			monthExecuteList = null;
		}
	}

	/**
	 * 业务逻辑：制定月度作业计划信息_按网元分解(CUSTOMIZE-MONTHLY-PLAN-002)
	 * 
	 * @param _executeType
	 *            String 执行类型
	 * @param _netIdList
	 *            String 网元编号字符串(以“,”分隔)
	 * @param _monthFlag
	 *            String 执行月度
	 * @param _cruser
	 *            String 创建人
	 * @param _principal
	 *            String 执行负责人
	 * @param _yearPlanId
	 *            String 年度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void addMonthPlans(String _executeType, String _netIdList,
			String _monthFlag, String _cruser, String _principal,
			String _yearPlanId) throws Exception {

		// 以分隔符将网元编号字符串转化为数组
		String[] tempStr = _netIdList.split(",");
		try {
			for (int i = 0; i < tempStr.length; i++) {
				// 循环调用添加单条月度作业计划(关联单个网元)的方法
				this.addMonthPlan(_executeType, tempStr[i], _monthFlag,
						_cruser, _principal, _yearPlanId);
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划保存出现异常");
		}
	}

	/**
	 * 业务逻辑：制定月度作业计划_按网元分解(CUSTOMIZE-MONTHLY-PLAN-003)
	 * 
	 * @param _netIdList
	 *            String 网元编号字符串(以“,”分隔)
	 * @param _cruser
	 *            String 创建人
	 * @param _monthFlag
	 *            String 计划月度
	 * @param _yearPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void addMonthPlans(String _netIdList, String _cruser,
			String _monthFlag, String _yearPlanId) throws Exception {

		// 以分隔符将网元编号字符串转化为数组
		String[] tempStr = _netIdList.split(",");

		try {

			for (int i = 0; i < tempStr.length; i++) {
				// 循环调用添加单条月度作业计划(关联单个网元)的方法

				/*
				 * 注意：如果想修改默认的执行类型,只需修改下方参数列表的第一个字符串中的数字
				 * 具体对应关系参照monthPlanVO中的静态数组
				 */
				this.addMonthPlan("0", tempStr[i], _monthFlag, _cruser, "",
						_yearPlanId);
			}
		} catch (Exception e) {
			// 捕获异常
			System.out.println("@@@@@@@@@@@@@error addMonthPlans is " + e);
			throw new TawwpException("月度作业计划保存出现异常");
		}
	}

	/**
	 * 业务逻辑：修改月度作业计划_显示(EDIT-MONTHLY-PLAN-001)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthPlanVO 月度作业计划VO对象
	 */
	public TawwpMonthPlanVO editMonthPlanView(String _monthPlanId)
			throws Exception {

		// 初始化数据,创建DAO对象
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		// 月度作业计划VO对象
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 封装月度作业计划VO对象
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan);
			// 获取执行类型名称
			tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
					.getExecuteTypeName(StaticMethod.null2int(tawwpMonthPlanVO
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
			// 获取创建人姓名
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));
			// 获取制定状态名称
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(StaticMethod
							.null2int(tawwpMonthPlanVO.getConstituteState())));
			// 获取执行状态名称
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(StaticMethod.null2int(tawwpMonthPlanVO
							.getExecuteState())));
			//获取是否审核制定月度计划
			tawwpMonthPlanVO.setIsApp(tawwpMonthPlan.getTawwpYearPlan().getIsApp());
			// 获取执行负责人姓名
			tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 获取网元名称
			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet().getId());
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));
			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}
			// 返回月度作业计划VO对象
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划修改出现异常");
		}
	}

	/**
	 * 业务逻辑：修改月度作业计划_保存(EDIT-MONTHLY-PLAN-002)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _executeType
	 *            String 执行类型(0：多人填写一份 1：多人填写多份 2：顺序填写)
	 * @param _principal
	 *            String 执行负责人
	 * @throws Exception
	 *             异常
	 */
	public void editMonthPlanSave(String _monthPlanId, String _executeType,
			String _principal) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;

		try {
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);

			// 封装月度作业计划对象
			tawwpMonthPlan.setExecuteType(_executeType);
			tawwpMonthPlan.setPrincipal(_principal);

			// 修改月度作业计划信息
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划修改出现异常");
		}
	}
	/**
	 *业务逻辑：修改年度作业计划是否自动送审状态(EDIT-MONTHLY-PLAN-002)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _executeType
	 *            String 执行类型(0：多人填写一份 1：多人填写多份 2：顺序填写)
	 * @param _principal
	 *            String 执行负责人
	 * @param _isApp
	 *            String 是否制定 自动送审
	 * @throws Exception
	 *             异常
	 */
	public void editMonthPlanSave(String _monthPlanId, String _executeType,
			String _principal,String _isApp) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpYearPlan tawwpYearPlan=null;

		try {
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);

			// 封装月度作业计划对象
			tawwpMonthPlan.setExecuteType(_executeType);
			tawwpMonthPlan.setPrincipal(_principal);
			tawwpYearPlan=tawwpMonthPlan.getTawwpYearPlan();
			tawwpYearPlan.setIsApp("1");

			// 修改月度作业计划信息
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

			tawwpYearPlanDao.updateYearPlan(tawwpYearPlan);

		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划修改出现异常");
		}
	}
	/**
	 *业务逻辑：修改年度作业计划是否自动送审状态(EDIT-MONTHLY-PLAN-002)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _isApp
	 *            String 是否制定 自动送审
	 * @throws Exception
	 *             异常
	 */
	public void editMonthPlanSave(String _monthPlanId) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpYearPlan tawwpYearPlan=null;

		try {
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			tawwpYearPlan=tawwpMonthPlan.getTawwpYearPlan();
			if("0".equals(tawwpYearPlan.getIsApp())){
				tawwpYearPlan.setIsApp("1");
			
			tawwpYearPlanDao.updateYearPlan(tawwpYearPlan);
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划修改出现异常");
		}
	}
	/**
	 * 业务逻辑：删除月度作业计划(DELETE-MONTHLY-PLAN-001)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void removeMonthPlan(String _monthPlanId) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;

		try {
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			// 如果月度作业计划存在
			if (tawwpMonthPlan != null) {
				// 删除月度作业计划信息
				tawwpMonthPlanDao.deleteMonthPlan(tawwpMonthPlan);
			} else {
				throw new TawwpException("月度作业计划不存在");
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划删除出现异常");
		}
	}

	/**
	 * 获取指定月度的月度作业计划默认的执行内容集合(用于月度作业计划制定)
	 * 
	 * @param _tawwpYearPlan
	 *            年度作业计划对象
	 * @param _tawwpMonthPlan
	 *            月度作业计划对象
	 * @throws Exception
	 *             异常
	 * @return List 月度作业计划执行内容集合
	 */
	private List getMonthExecutes(TawwpYearPlan _tawwpYearPlan,
			TawwpMonthPlan _tawwpMonthPlan) throws Exception {

		// 初始化数据
		List monthExecuteList = new ArrayList();
		Iterator tawwpYearExecutes = null;
		// 年度作业计划执行内容对象
		TawwpYearExecute tawwpYearExecute = null;
		// 执行日期字符串
		String executeDate = "";

		try {
			// 获取年度作业计划执行内容集合
			tawwpYearExecutes = _tawwpYearPlan.getTawwpYearExecutes()
					.iterator();
			// 根据年度作业计划执行内容,获得相应月度作业计划执行内容
			while (tawwpYearExecutes.hasNext()) {

				// 获取年度作业计划执行内容集合中下一元素
				tawwpYearExecute = (TawwpYearExecute) tawwpYearExecutes.next();

				if (tawwpYearExecute.getCycle().equals("0")) {
					continue; // 如果周期为“其他”，不放入自动生成的月度作业计划执行内容中
				}

				// 判断该执行内容是否应该在指定月执行
				if (this.judgeMonthExecute(_tawwpMonthPlan.getMonthFlag(),
						tawwpYearExecute.getMonthFlag(), tawwpYearExecute
								.getCycle())) {
					// 根据年、月份获得该月度作业计划执行内容应执行的日期表示字符串(0,1形式)
					executeDate = this.getExecuteDate(_tawwpYearPlan
							.getYearFlag(), _tawwpMonthPlan.getMonthFlag(),
							tawwpYearExecute.getCycle(), tawwpYearExecute
									.getIsHoliday(), tawwpYearExecute
									.getIsWeekend(), tawwpYearExecute
									.getExecuteDay());
					// 封装月度作业计划执行内容对象

					TawwpMonthExecute tawwpMonthExecute = new TawwpMonthExecute(
							tawwpYearExecute.getName(), tawwpYearExecute
									.getBotype(), tawwpYearExecute
									.getExecutedeptlevel(), tawwpYearExecute
									.getAppdesc(), tawwpYearExecute.getCycle(),
							tawwpYearExecute.getRemark(),tawwpYearExecute.getNote(), tawwpYearExecute
									.getFormat(), tawwpYearExecute
									.getValidate(), tawwpYearExecute
									.getFormId(), "0", _tawwpMonthPlan
									.getCruser(), TawwpUtil
									.getCurrentDateTime(), tawwpYearExecute
									.getSerialNo(), tawwpYearExecute.getXlsX(),
							tawwpYearExecute.getXlsY(), _tawwpYearPlan
									.getNetTypeId(), tawwpYearExecute
									.getCommand(), tawwpYearExecute
									.getExecuter(), tawwpYearExecute
									.getExecuterType(), tawwpYearExecute
									.getExecuteRoom(), tawwpYearExecute
									.getExecuteDuty(), executeDate, "0",
							tawwpYearExecute.getTawwpModelGroup(),
							_tawwpMonthPlan, tawwpYearExecute, new HashSet(),
							"0", "0", tawwpYearExecute.getFileFlag(), "",
							tawwpYearExecute.getExecuteDay());
					tawwpMonthExecute.setAccessories(tawwpYearExecute.getAccessories());
					// 添加到集合中
					monthExecuteList.add(tawwpMonthExecute);
				}
			}
			// 返回月度作业计划执行内容集合
			return monthExecuteList;
		} catch (Exception e) {
			// 捕获异常
			System.out.println("@@@@@@@@@@@@@@@ getMonthExecutes error" + e);
			e.printStackTrace();
			throw new TawwpException("获取月度作业计划执行内容出现异常");
		} finally {
			tawwpYearExecutes = null;
		}
	}

	/**
	 * 根据年、月份获得该月度作业计划执行内容应执行的日期表示字符串(0,1形式)
	 * 
	 * @param _yearFlag
	 *            String 执行年份
	 * @param _monthFlag
	 *            String 执行月份
	 * @param _cycle
	 *            String 执行周期
	 * @throws Exception
	 *             异常
	 * @return String 返回执行日期字符串
	 */
	private String getExecuteDate(String _yearFlag, String _monthFlag,
			String _cycle, String _isHoliday, String _isWeekend, String _weekDay)
			throws Exception {
		// 初始化数据
		String executeDate = "";
		// 当月天数
		int dayCount = 0;
		// 取得指定月度的总天数
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		// 创建日期对象
		Calendar calendar = new GregorianCalendar();
		date = dtFormat.parse(_yearFlag + "-" + _monthFlag + "-01 00:00:00");
		calendar.setTime(date);
		// 获取日期该月的最大天数,5表示天
		dayCount = calendar.getActualMaximum(calendar.DATE);

		// 根据周期,获得执行日期字符串
		/*
		 * (0 其他, 1 天, 2 周, 3 半月 4 月, 5 季度, 6 半年, 7 年 8 两月, 9 四月)
		 */
		// 周期为天返回当月天数个“1”
		if ("1".equals(_cycle)) {
			if ((_isHoliday == null || _isHoliday.equals("0"))
					&& (_isWeekend == null || _isWeekend.equals("0"))) {
				String tempStr = "1111111111111111111111111111111";
				executeDate = tempStr.substring(0, dayCount);
			} else {

				ArrayList restDay = TawwpUtil.getRestDay(StaticMethod
						.null2int(_yearFlag),
						StaticMethod.null2int(_monthFlag), _isHoliday,
						_isWeekend);
				StringBuffer execute = new StringBuffer();
				for (int i = 1; i <= dayCount; i++) {
					if (restDay.indexOf(new Integer(i)) != -1) {
						execute.append("0");
					} else {
						execute.append("1");
					}
				}
				executeDate = execute.toString();
			}

		}
		// 周期为周返回序数为每周五日期为“1”的字符串(即每周五执行)
		else if ("2".equals(_cycle)) {
			// 获得指定月第一个用户指定星期几的日期
				_weekDay = StaticMethod.null2String(_weekDay);
				if (_weekDay==null || _weekDay.equals("") || _weekDay.equals("0")) {
				calendar.set(GregorianCalendar.DAY_OF_WEEK,
						GregorianCalendar.FRIDAY);
				} else {
				if (_weekDay.equals("1")) {
					calendar.set(GregorianCalendar.DAY_OF_WEEK,
							GregorianCalendar.MONDAY);
				} else if (_weekDay.equals("2")) {
					calendar.set(GregorianCalendar.DAY_OF_WEEK,
							GregorianCalendar.TUESDAY);
				} else if (_weekDay.equals("3")) {
					calendar.set(GregorianCalendar.DAY_OF_WEEK,
							GregorianCalendar.WEDNESDAY);
				} else if (_weekDay.equals("4")) {
					calendar.set(GregorianCalendar.DAY_OF_WEEK,
							GregorianCalendar.THURSDAY);
				} else if (_weekDay.equals("5")) {
					calendar.set(GregorianCalendar.DAY_OF_WEEK,
							GregorianCalendar.FRIDAY);
				} else if (_weekDay.equals("6")) {
					calendar.set(GregorianCalendar.DAY_OF_WEEK,
							GregorianCalendar.SATURDAY);
				} else if (_weekDay.equals("7")) {
					calendar.set(GregorianCalendar.DAY_OF_WEEK,
							GregorianCalendar.SUNDAY);
				}
			}
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			int fitstWeekday = calendar.get(Calendar.DAY_OF_MONTH);
			fitstWeekday = fitstWeekday % 7;

			String tempStr = "";
			tempStr = "000000100000010000001000000100000010000001";
			executeDate = tempStr.substring(7 - fitstWeekday, dayCount
					+ (7 - fitstWeekday));
		}
		// 周期为半月返回序数为15和当月最后一天为“1”的字符串(即15日和月末最后一天执行)
		else if ("3".equals(_cycle)) {
			String tempStr = "0000000000000010000000000000000";
			executeDate = tempStr.substring(0, dayCount - 1);
			executeDate += "1";
		}
		// 周期为其他的返回当月天数个“0”
		else if ("0".equals(_cycle)) {
			String tempStr = "0000000000000000000000000000000";
			executeDate = tempStr.substring(0, dayCount);
		}
		// 其他周期类型在月末最后一天执行
		else {
			String tempStr = "0000000000000000000000000000001";
			executeDate = tempStr.substring(31 - dayCount, 31);
		}
		// 返回执行日期字符串
		return executeDate;
	}

	/**
	 * 判断指定执行内容是否应该在指定月度执行
	 * 
	 * @param _monthOfPlan
	 *            String 月度作业计划月份
	 * @param _monthOfExecute
	 *            String 年度作业计划执行内容执行月份序数(周期大于月)
	 * @param _cycle
	 *            String 执行内容周期
	 * @throws Exception
	 *             异常
	 * @return boolean 是否应该执行标志
	 */
	private boolean judgeMonthExecute(String _monthOfPlan,
			String _monthOfExecute, String _cycle) throws Exception {
		// 初始化数据,是否应该执行标志
		boolean flag = false;
		/*
		 * (0 其他, 1 天, 2 周, 3 半月 4 月, 5 季度, 6 半年, 7 年 8 两月, 9 四月)
		 */
		// 如果周期小于月则一定需要执行
		if (StaticMethod.null2int(_cycle) <= 4) {
			flag = true;
		} else {
			// 定义数组对应周期包括的月度数
			int[] tempArray = { 3, 6, 12, 2, 4 };
			// 算法
			/*
			 * 如: 计划月度为8,执行内容周期为季度(包含3个月),执行内容指定每季度第二个月执行,
			 * 则按如下算法(8-1)%3+1=2与每周期需要执行的月份序数吻合 注: %代表取余数
			 */
			int tempInt = ((StaticMethod.null2int(_monthOfPlan) - 1) % tempArray[Integer
					.parseInt(_cycle) - 5]) + 1;
			if (tempInt == StaticMethod.nullObject2int(_monthOfExecute)) {
				flag = true;
			}
		}
		// 返回是否应该执行标志
		return flag;
	}

	/**
	 * 业务逻辑：修改月度作业计划执行内容_显示(EDIT-EXECUTION-001)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return List 月度作业计划执行内容VO对象集合
	 */
	public List editMonthExecuteView(String _monthPlanId) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// 月度作业计划执行内容集合
		List monthExecuteList = null;
		// 月度作业计划执行内容VO对象集合
		List monthExecuteVOList = new ArrayList();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;
		// 月度作业计划执行内容VO对象
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		// 作业计划模块工具方法类
		TawwpUtil tawwpUtil = new TawwpUtil();
		int dayCount = 0; // 当月天数

		try {
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);

			// 获取月度作业计划执行内容对象集合
			monthExecuteList = tawwpMonthExecuteDao
					.listMonthExecute(tawwpMonthPlan);

			for (int i = 0; i < monthExecuteList.size(); i++) {
				tawwpMonthExecute = (TawwpMonthExecute) (monthExecuteList
						.get(i));
				tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
				// 封装月度作业计划执行内容VO对象
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
						tawwpMonthExecute);
				// 获取执行周期
				tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
						.getCycleName(StaticMethod.null2int(tawwpMonthExecuteVO
								.getCycle())));
				// 获取电子表单名称
				tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
						.getAddonsName(tawwpMonthExecuteVO.getFormId()));
				// 获取创建人姓名
				tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthExecuteVO.getCruser()));
				// 获取网元类型名称
				tawwpMonthExecuteVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthExecuteVO.getNetTypeId()));

				// 获取执行人类型名称
				if (tawwpMonthExecuteVO.getExecuterType() != null
						&& !"".equals(tawwpMonthExecuteVO.getExecuterType())) {
					tawwpMonthExecuteVO.setExecuterTypeName(TawwpMonthExecuteVO
							.getExecuterTypeName(Integer
									.parseInt(tawwpMonthExecuteVO
											.getExecuterType())));
				}
				// 如果执行人类型为班次
				if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
					// 获取机房名称
					String roomNames = tawwpUtilDAO
							.getRoomNames(tawwpMonthExecuteVO.getExecuter());
					tawwpMonthExecuteVO.setExecuterName(roomNames);
					tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

					// 获取执行班次名称
					if (tawwpMonthExecuteVO.getExecuteDuty() != null
							&& !"".equals(tawwpMonthExecuteVO.getExecuteDuty())) {
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
						tawwpMonthExecuteVO
								.setExecuterName(tawwpUtilDAO
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
				// 获取该月天数
				dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
						.getYearFlag(), tawwpMonthPlan.getMonthFlag());
				tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));

				// 添加到集合中
				monthExecuteVOList.add(tawwpMonthExecuteVO);
				Collections.sort(monthExecuteVOList, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthExecuteVO p1 = (TawwpMonthExecuteVO) o1;
						TawwpMonthExecuteVO p2 = (TawwpMonthExecuteVO) o2;
						int k = -1;
						if(p1.getCrtime().compareTo(p2.getCrtime())>0){
							k = 1;
						}else if(p1.getCrtime().compareTo(p2.getCrtime())==0){
							if (p1.getId().compareTo(p2.getId()) > 0) {
								k = 1;
							}else{
								k = -1;
							}
						}else 
							if(p1.getCrtime().compareTo(p2.getCrtime())<0){
								k = -1;
						}
						return k;

					}
				});
			}

			// 返回月度作业计划执行内容VO对象集合
			return monthExecuteVOList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容修改出现异常");
		} finally {
			monthExecuteList = null;
		}
	}

	/**
	 * 业务逻辑：修改指定月度作业计划执行内容的执行人(EDIT - EXECUTIVE - 001)
	 * 
	 * @param _monthExecuteId
	 *            String 月度作业计划执行内容编号
	 * @param _executer
	 *            String 执行人
	 * @param _executerType
	 *            String 执行人类型(0：表示人员，1：表示部门，2：表示岗位 3：表示班次)
	 * @param _executeRoom
	 *            String 执行机房
	 * @param _executeDuty
	 *            String 执行班次
	 * @throws Exception
	 *             异常
	 */
	public void editExecuter(String _monthExecuteId, String _executer,
			String _executerType, String _executeRoom, String _executeDuty)
			throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;

		try {
			// 获取月度作业计划执行内容对象
			tawwpMonthExecute = tawwpMonthExecuteDao
					.loadMonthExecute(_monthExecuteId);

			// 封装月度作业计划执行内容对象
			tawwpMonthExecute.setExecuter(_executer);
			tawwpMonthExecute.setExecuterType(_executerType);

			// 如果执行类型为班次,则还需要相应修改执行机房和执行班次
			if ("3".equals(_executerType)) {
				tawwpMonthExecute.setExecuteRoom(_executeRoom);
				tawwpMonthExecute.setExecuteDuty(_executeDuty);
			}

			// 修改月度作业计划执行内容的执行人及相关信息
			tawwpMonthExecuteDao.updateMonthExecute(tawwpMonthExecute);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容执行人修改出现异常");
		}
	}

	/**
	 * 业务逻辑：修改指定月度作业计划执行内容的执行时间(EDIT-EXECUTIONDATE-001)
	 * 
	 * @param _monthExecuteId
	 *            String 月度作业计划执行内容编号
	 * @param _executeDate
	 *            String 执行时间
	 * @throws Exception
	 *             异常
	 */
	public void editExecuteDate(String _monthExecuteId, String _executeDate)
			throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;

		try {
			// 获取月度作业计划执行内容对象
			tawwpMonthExecute = tawwpMonthExecuteDao
					.loadMonthExecute(_monthExecuteId);

			// 用户是否对执行时间进行了修改
			if (!_executeDate.equals(tawwpMonthExecute.getExecuteDate())) {

				// 封装月度作业计划执行内容对象
				tawwpMonthExecute.setExecuteDate(_executeDate);

				// 修改月度作业计划执行内容的执行时间
				tawwpMonthExecuteDao.updateMonthExecute(tawwpMonthExecute);
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容执行时间修改出现异常");
		}
	}

	/**
	 * 业务逻辑：删除月度作业计划执行内容(单个或批量)(DELETE-MONTHLY-PLAN-CONTENT-001)
	 * 
	 * @param _monthExecuteIdStr
	 *            String 月度作业计划执行内容编号
	 * @throws Exception
	 *             异常
	 */
	public void removeMonthExecute(String _monthExecuteIdStr) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;

		try {
			// 处理月度作业计划执行内容编号字符串
			String[] tempStr = _monthExecuteIdStr.split(",");

			for (int i = 0; i < tempStr.length; i++) {
				// 获取月度作业计划执行内容对象
				tawwpMonthExecute = tawwpMonthExecuteDao
						.loadMonthExecute(tempStr[i]);
				// 如果月度作业计划存在
				if (tawwpMonthExecute != null) {
					// 删除月度作业计划执行内容信息
					tawwpMonthExecuteDao.deleteMonthExecute(tawwpMonthExecute);
				} else {
					throw new TawwpException("月度作业计划执行内容不存在");
				}
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容删除出现异常");
		}
	}

	/**
	 * 业务逻辑：新增月度计划的执行内容_显示(ADD-MONTHLY-PLAN-CONTENT-001)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return List 年度作业计划执行内容VO对象集合
	 */
	public List addMonthExecuteView(String _monthPlanId) throws Exception {

		// 初始化数据
		List yearExecuteVOList = new ArrayList();
		Iterator tawwpYearExecutes = null;
		// 年度作业计划执行内容对象
		TawwpYearExecute tawwpYearExecute = null;
		// 年度作业计划执行内容VO对象
		TawwpYearExecuteVO tawwpYearExecuteVO = null;
		// DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		// 年度作业计划对象
		TawwpYearPlan tawwpYearPlan = null;

		try {
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			// 获取年度作业计划对象
			tawwpYearPlan = tawwpMonthPlan.getTawwpYearPlan();
			// 获取年度作业计划执行内容集合
			tawwpYearExecutes = tawwpYearPlan.getTawwpYearExecutes().iterator();
			// 根据年度作业计划执行内容,获得相应月度作业计划执行内容
			while (tawwpYearExecutes.hasNext()) {

				// 获取年度作业计划执行内容集合中下一元素
				tawwpYearExecute = (TawwpYearExecute) tawwpYearExecutes.next();

				// 如果该执行内容在月度作业计划中尚未存在
				if (this.judgeExecuteExist(tawwpMonthPlan, tawwpYearExecute)) {
					tawwpYearExecuteVO = new TawwpYearExecuteVO();
					// 封装年度作业计划执行内容VO对象
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearExecuteVO,
							tawwpYearExecute);
					// 获取周期名称
					tawwpYearExecuteVO.setCycleName(tawwpUtilDAO
							.getCycleName(tawwpYearExecuteVO.getCycle()));

					// 获取附加表单名称,以后要添加进来
					// tawwpYearExecuteVO.setFormName(tawwpUtilDAO.getFormName(StaticMethod.null2int(tawwpYearExecuteVO.getFormId())));
					// 添加到集合中
					yearExecuteVOList.add(tawwpYearExecuteVO);
				}
			}
			// 返回年度作业计划执行内容VO对象集合
			return yearExecuteVOList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容新增出现异常");
		} finally {
			tawwpYearExecutes = null;
		}
	}

	/**
	 * 业务逻辑:新增月度计划的执行内容_保存(ADD-MONTHLY-PLAN-CONTENT-002)
	 * 
	 * @param _yearExecuteId
	 *            String 年度作业计划执行内容编号
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void addMonthExecuteSave(String _yearExecuteId, String _monthPlanId)
			throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpYearExecuteDAO tawwpYearExecuteDAO = new TawwpYearExecuteDAO();
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// 年度作业计划执行内容对象
		TawwpYearExecute tawwpYearExecute = null;
		// 年度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		// 执行日期字符串
		String executeDate = "";

		try {
			// 获取年度作业计划执行内容对象
			tawwpYearExecute = tawwpYearExecuteDao
					.loadYearExecute(_yearExecuteId);
			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			// 根据年、月份获得该月度作业计划执行内容应执行的日期表示字符串(0,1形式)
			executeDate = this.getExecuteDate(tawwpMonthPlan.getYearFlag(),
					tawwpMonthPlan.getMonthFlag(), tawwpYearExecute.getCycle(),
					tawwpYearExecute.getIsHoliday(), tawwpYearExecute
							.getIsWeekend(), tawwpYearExecute.getExecuteDay());

			// 封装月度作业计划执行内容对象
			TawwpMonthExecute tawwpMonthExecute = new TawwpMonthExecute(
					tawwpYearExecute.getName(), tawwpYearExecute.getBotype(),
					tawwpYearExecute.getExecutedeptlevel(), tawwpYearExecute
							.getAppdesc(), tawwpYearExecute.getCycle(),
					tawwpYearExecute.getRemark(), tawwpYearExecute.getFormat(),
					tawwpYearExecute.getValidate(), tawwpYearExecute
							.getFormId(), "0", tawwpMonthPlan.getCruser(),
					TawwpUtil.getCurrentDateTime(), tawwpYearExecute
							.getSerialNo(), tawwpYearExecute.getXlsX(),
					tawwpYearExecute.getXlsY(), "", tawwpYearExecute
							.getCommand(), "", "0", "", "", executeDate, "0",
					tawwpYearExecute.getTawwpModelGroup(), tawwpMonthPlan,
					tawwpYearExecute, new HashSet(), "0", "0", tawwpYearExecute
							.getFileFlag(), "", tawwpYearExecute
							.getExecuteDay());

			// 新增月度作业计划执行内容
			tawwpMonthExecuteDao.saveMonthExecute(tawwpMonthExecute);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容新增出现异常");
		}
	}

	/**
	 * 业务逻辑:新增月度计划的执行内容_保存(ADD-MONTHLY-PLAN-CONTENT-002)
	 * 
	 * @param _yearExecuteId
	 *            String 年度作业计划执行内容编号
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void addMonthExecuteSave(String _monthPlanId,
			TawwpMonthExecute _tawwpMonthExecute) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();

		// 年度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		// 执行日期字符串
		String executeDate = "";

		try {

			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			executeDate = this.getExecuteDate(tawwpMonthPlan.getYearFlag(),
					tawwpMonthPlan.getMonthFlag(), _tawwpMonthExecute
							.getCycle(), _tawwpMonthExecute.getIsHoliday(),
					_tawwpMonthExecute.getIsWeekend(), _tawwpMonthExecute
							.getExecuteDay());

			_tawwpMonthExecute.setTawwpMonthPlan(tawwpMonthPlan);
			_tawwpMonthExecute.setExecuteDate(executeDate);
			// 新增月度作业计划执行内容
			tawwpMonthExecuteDao.saveMonthExecute(_tawwpMonthExecute);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容新增出现异常");
		}
	}

	/**
	 * 业务逻辑:月度作业计划提交审批(PUTIN-MONTHLY-PLAN-AUDITING-001)
	 * 
	 * @param _checkDeptId
	 *            String 审批人所在部门
	 * @param _checkUser
	 *            String 审批人
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @param _flowSerial
	 *            String 流程标识
	 * @param _stepSerial
	 *            String 步骤标识
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthCheck 操作结果，如果月度作业计划不完整则返回false
	 */
	public TawwpMonthCheck addMonthCheck(String _checkDeptId,
			String _checkUser, String _monthPlanId, String _flowSerial,
			String _stepSerial, String _type) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpMonthCheckDAO tawwpMonthCheckDAO = new TawwpMonthCheckDAO();
		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpMonthCheck tawwpMonthCheck = null;

		Iterator iterator = null;

		boolean flag = true;

		try {

			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);
			iterator = tawwpMonthPlan.getTawwpMonthExecutes().iterator();

			// 验证月度作业计划的完整性
			if (tawwpMonthPlan.getPrincipal().equals("")) {
				flag = false;
			}

			// 验证月度作业计划执行内容的完整性
			while (iterator.hasNext()) {
				tawwpMonthExecute = (TawwpMonthExecute) iterator.next();
				if (tawwpMonthExecute.getExecuter()==null||tawwpMonthExecute.getExecuter().equals("")
						|| tawwpMonthExecute.getExecuteDate().equals("")) {
					flag = false;
					break;
				}
			}

			if (flag) {
				// 封装月度作业计划审批信息对象
				tawwpMonthCheck = new TawwpMonthCheck(_checkDeptId, _checkUser,
						"", "0", TawwpUtil.getCurrentDateTime(), "",
						_flowSerial, _stepSerial, _type, tawwpMonthPlan, null);

				// 提交月度作业计划审批
				tawwpMonthCheckDao.saveMonthCheck(tawwpMonthCheck);

				tawwpMonthPlan.setConstituteState("3");
				tawwpMonthPlanDao.saveMonthPlan(tawwpMonthPlan);

				// 发送短信信息给审批人
				/*
				 * TawwpUtil.sendSMS(_checkUser, "月度作业计划《" +
				 * StaticMethod.null2String(tawwpMonthPlan.getName()) +
				 * "》需要您审批");
				 */
				try {
					TawwpUtil.sendSMS(_checkUser, "月度作业计划《"
							+ StaticMethod
									.null2String(tawwpMonthPlan.getName())
							+ "》需要您审批", tawwpMonthPlan.getId());
					// 发送短消息
					TawwpUtil.sendMail(_checkUser, "月度作业计划《"
							+ StaticMethod
									.null2String(tawwpMonthPlan.getName())
							+ "》需要您审批", tawwpMonthPlan.getId());
				} catch (Exception e) {
					System.out.print("消息发送异常" + e);
				}
			}
			return tawwpMonthCheck;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划提交审批出现异常");
		}
	}

	/**
	 * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
	 * 
	 * @param _monthCheckId
	 *            String 审批信息编号
	 * @param _content
	 *            String 审批信息
	 * @param _checkUser
	 *            String 下一步骤审批人，如果步骤不存在可以为空
	 * @param _currentCheckUser
	 *            String 审批人
	 * @throws Exception
	 *             异常
	 */
	public void passMonthCheck(String _monthCheckId, String _checkUser,
			String _content, String _currentCheckUser) throws Exception {

		// 集合对象
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecutecontents = null;
		Iterator tawwpExecutecontentUsers = null;
		TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
		TawwpMonthExecute tawwpMonthExecute = new TawwpMonthExecute();
		// 执行人字符串
		String executeUserStr = "";
		// 创建执行人信息字符串
		String executerInfo = "";
		// 执行日期字符串
		String executeDate = "";
		// 日期格式基础字符串
		String baseDate = "";
		String startHH = "";
		String endHH = "";
		String startMM = "";
		String endMM = "";

		// 月度作业计划审批信息对象
		TawwpMonthCheck tawwpMonthCheck = new TawwpMonthCheck();
		// 月度作业计划执行内容执行人信息对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = new TawwpMonthExecuteUser();

		TawwpFlowManage tawwpFlowManage = null;
		Step step = null;
		String j = "";

		try {

			// 获取月度作业计划审批信息对象
			tawwpMonthCheck = tawwpMonthCheckDao.loadMonthCheck(_monthCheckId);

			// 封装月度作业计划审批信息对象/main.jsp?id=15
			tawwpMonthCheck.setContent(_content);
			tawwpMonthCheck.setState("1");
			tawwpMonthCheck.setCheckUser(_currentCheckUser);
			tawwpMonthCheck.setChecktime(TawwpUtil.getCurrentDateTime());
			String type = StaticMethod.null2String(tawwpMonthCheck.getType());

			// 修改月度作业计划审批信息(通过或驳回)
			tawwpMonthCheckDao.updateMonthCheck(tawwpMonthCheck);

			// 获取月度作业计划
			tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();
			// 将月度作业计划执行状态改为"执行中"
			tawwpMonthPlan.setExecuteState("4");
			// 制定状态改为“通过”
			tawwpMonthPlan.setConstituteState("1");
			// 如果是删除类型的审批通过，则将该作业计划的删除标志位置为“1”
			if (type.equals("1")) {
				tawwpMonthPlan.setDeleted("1");
			}
			// 更新月度作业计划执行状态
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

			// 获取月度作业计划执行内容集合
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			// -----------------------------------------------------------------------

			// 获取流程管理对象
			tawwpFlowManage = TawwpFlowManage.getInstance();
			// 获取下一步骤对象
			step = tawwpFlowManage.getMonthFlowStep(tawwpMonthCheck
					.getFlowSerial(), tawwpMonthCheck.getStepSerial(),
					tawwpMonthCheck.getDeptId());

			if (step == null) {
				// 如果是删除类型的审批通过，则将该月度作业计划下属的执行项(未执行的)删除
				if (type.equals("1")) {
					tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();
					while (tawwpMonthExecutes.hasNext()) {
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());
						tawwpExecutecontents = tawwpMonthExecute
						.getTawwpExecuteContents().iterator();
						while (tawwpExecutecontents.hasNext()){
							TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecutecontents.next());
							tawwpExecutecontentUsers = tawwpExecuteContent
									.getTawwpExecuteContentUsers().iterator();
							while (tawwpExecutecontentUsers.hasNext()) {
								TawwpExecuteContentUser tawwpExecuteContentUser = (TawwpExecuteContentUser) (tawwpExecutecontentUsers
										.next());
								tawwpExecutecontentUsers.remove();
								tawwpExecuteContent.getTawwpExecuteContentUsers().remove(
										tawwpExecuteContentUser);
								tawwpExecuteContentUser.setTawwpExecuteContent(null);
								tawwpExecuteContentUserDao.deleteExecuteContentUser(tawwpExecuteContentUser);
							}
							// 下面的几行代码是解除model中的关联关系
							tawwpExecutecontents.remove();
							tawwpMonthExecute.getTawwpExecuteContents().remove(
									tawwpExecuteContent);
							tawwpExecuteContent.setTawwpMonthExecute(null);
							tawwpExecuteContentDao
									.deleteExecuteContent(tawwpExecuteContent);
						}
						tawwpMonthExecute.setDeleted("1");
						tawwpMonthExecuteDao
								.updateMonthExecute(tawwpMonthExecute);
					}
					List deleteMonthPlan = tawwpMonthExecuteUserDao
							.getMonthExecuteUser(tawwpMonthPlan.getId());
					for (int m = 0; m < deleteMonthPlan.size(); m++) {
						TawwpMonthExecuteUser tawwpMonthExecuteUserDel = (TawwpMonthExecuteUser) deleteMonthPlan
								.get(m);
						tawwpMonthExecuteUserDao
								.deleteMonthExecuteUser(tawwpMonthExecuteUserDel);
					}
				} else {// 正常的增加操作
					/*
					 * 如果步骤对象为空，整体流程结束 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 * 2.将执行人信息写入执行人表中
					 * 3.添加默认执行内容(整体)信息对象TawwpExecuteContent到数据库中
					 */

					/*--------------------------------------------------
					 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 */

					while (tawwpMonthExecutes.hasNext()) {
						// 获取月度作业计划执行内容
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());

						/*--------------------------------------------------
						 2.将执行人信息写入执行人表中
						 */

						// 获取执行人字符串
						executeUserStr = tawwpMonthExecute.getExecuter();

						if (executeUserStr != null
								&& !"".equals(executeUserStr)) {
							// 处理执行人字符串
							String[] temp = executeUserStr.split(",");
							for (int i = 0; i < temp.length; i++) {

								/*
								 * 保证用户名(或部门、岗位编号)、执行人类型、 月度作业计划的唯一对应性的算法
								 */
								// 组合临时字符串,如"用户名,执行人类型"
								String tempStr = temp[i] + ","
										+ tawwpMonthExecute.getExecuterType();
								// 如果执行人信息字符串不包含以上信息,就创建执行人对象,并更新执行人信息字符串
								if (executerInfo.indexOf(tempStr) == -1) {
									executerInfo += (tempStr + "#");
									tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
									// 封装月度作业计划执行内容执行人对象
									tawwpMonthExecuteUser.setExecuter(temp[i]);
									tawwpMonthExecuteUser
											.setExecuterType(tawwpMonthExecute
													.getExecuterType());
									tawwpMonthExecuteUser
											.setExecuteRoom(tawwpMonthExecute
													.getExecuteRoom());
									tawwpMonthExecuteUser
											.setExecuteDuty(tawwpMonthExecute
													.getExecuteDuty());
									tawwpMonthExecuteUser.setContent("");
									tawwpMonthExecuteUser.setState("0");
									tawwpMonthExecuteUser
											.setYearFlag(tawwpMonthPlan
													.getYearFlag());
									tawwpMonthExecuteUser
											.setMonthFlag(tawwpMonthPlan
													.getMonthFlag());
									tawwpMonthExecuteUser
											.setTawwpMonthPlan(tawwpMonthPlan);
									// 保存月度作业计划执行内容执行人信息
									tawwpMonthExecuteUserDao
											.saveMonthExecuteUser(tawwpMonthExecuteUser);
								}
							}
						}

						/*---------------------------------------------------
						 3.添加默认执行内容(整体)信息对象(TawwpExecuteContent)
						 */

						executeDate = tawwpMonthExecute.getExecuteDate();
						String[] tempSt = StaticMethod.null2String(tawwpMonthExecute.getStartHH()).split(":");
						startHH = tempSt[0];
						if(tempSt.length>1){
							startMM = tempSt[1];
						}else{
							startMM = "00";
						}
						if (StaticMethod.null2int(startHH) > 0
								&& StaticMethod.null2int(startHH) < 10) {
							startHH = "0" + StaticMethod.null2int(startHH);
						}
						if (StaticMethod.null2int(startMM) > 0
								&& StaticMethod.null2int(startMM) < 10) {
							startMM = "0" + StaticMethod.null2int(startMM);
						}
						String[] tempEt = StaticMethod.null2String(tawwpMonthExecute.getEndHH()).split(":");
						endHH = tempEt[0];
						if(tempEt.length>1){
							endMM = tempEt[1];
						}else{
							endMM = "59";
						}
						if (StaticMethod.null2int(endHH) > 0
								&& StaticMethod.null2int(endHH) < 10) {
							endHH = "0" + StaticMethod.null2int(endHH);
						}
						if (StaticMethod.null2int(endMM) > 0
								&& StaticMethod.null2int(endMM) < 10) {
							endMM = "0" + StaticMethod.null2int(endMM);
						}
						// 转化成字符数组
						char[] tempArr = executeDate.toCharArray();
						// 组合基础字符串如"2005-09-"
						baseDate = tawwpMonthPlan.getYearFlag() + "-"
								+ tawwpMonthPlan.getMonthFlag() + "-";
						for (int i = 1; i < (tempArr.length + 1); i++) {
							// 如果该日需要执行
							if ('1' == tempArr[i - 1]) {

								// 字符串处理不足两位补零
								j = String.valueOf(i);
								if (i < 10) {
									j = "0" + j;
								}

								// 封装执行内容(整体)对象
								TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent(
										tawwpMonthExecute.getName(),
										tawwpMonthExecute.getBotype(),
										tawwpMonthExecute.getExecutedeptlevel(),
										tawwpMonthExecute.getAppdesc(),
										baseDate + j + " " + startHH + ":" + startMM + ":00",
										baseDate + j + " " + endHH + ":" + endMM + ":59",
										TawwpUtil.getCurrentDateTime(),
										"",
										tawwpMonthExecute.getFormat(),
										StaticMethod.null2String(tawwpMonthExecute.getNote()),
										tawwpMonthExecute.getCycle(),
										tawwpMonthExecute.getFormId(),
										"",
										tawwpMonthPlan.getDeptId(),
										"",
										"0",
										"0",
										"",
										tawwpMonthExecute.getCommand(), // edit
										// by
										// gongyufeng
										// 智能巡检
										tawwpMonthExecute.getCommandName(),
										tawwpMonthExecute.getExecuter(),
										tawwpMonthExecute.getExecuterType(),
										tawwpMonthExecute.getExecuteRoom(),
										tawwpMonthExecute.getExecuteDuty(),
										tawwpMonthPlan.getExecuteType(),
										tawwpMonthExecute.getFileFlag(),
										tawwpMonthExecute.getQualityCheckUser(),
										tawwpMonthExecute, tawwpMonthPlan,"");
										tawwpExecuteContent.setNormalFlag("1");
										tawwpExecuteContent.setExtendremark(tawwpMonthExecute.getRemark());
										tawwpExecuteContent.setAccessories(tawwpMonthExecute.getAccessories());
								// 保存执行内容(整体)对象
								tawwpExecuteContentDao
										.saveExecuteContent(tawwpExecuteContent);
							}
						}
					}
					try {
						// 发送短信信息给创建人
						TawwpUtil.sendSMS(tawwpMonthPlan.getCruser(), "月度作业计划《"
								+ StaticMethod.null2String(tawwpMonthPlan
										.getName()) + "》通过", tawwpMonthPlan
								.getId());

						TawwpUtil.closeSMS(tawwpMonthPlan.getId());

						TawwpUtil.sendMail(tawwpMonthPlan.getCruser(),
								"月度作业计划《"
										+ StaticMethod
												.null2String(tawwpMonthPlan
														.getName()) + "》通过",
								tawwpMonthPlan.getId());
						TawwpUtil.closeEmail(tawwpMonthPlan.getId());
					} catch (Exception e) {
						System.out.print("消息发送异常" + e);
					}
				}
			} else {
				// 如果步骤对象不为空
				if (_checkUser != null) {
					// 执行人是不为空
					this.addMonthCheck(tawwpMonthPlan.getDeptId(), _checkUser,
							tawwpMonthPlan.getId(), tawwpMonthCheck
									.getFlowSerial(), step.getSerial(), type); // 创建下一步骤的审批信息

					/*
					 * //发送短信信息给审批人 TawwpUtil.sendSMS(_checkUser, "月度作业计划《" +
					 * StaticMethod.null2String(tawwpMonthPlan.getName()) +
					 * "》需要您审批");
					 */
					try {
						// 发送短信信息给审批人
						TawwpUtil.sendSMS(_checkUser, "月度作业计划《"
								+ StaticMethod.null2String(tawwpMonthPlan
										.getName()) + "》需要您审批", tawwpMonthPlan
								.getId());
						// 删除前一个审批人的短信提醒
						TawwpUtil.closeSMS(tawwpMonthPlan.getId());
						TawwpUtil.sendMail(_checkUser, "月度作业计划《"
								+ StaticMethod.null2String(tawwpMonthPlan
										.getName()) + "》需要您审批", tawwpMonthPlan
								.getId());
						// 删除前一个审批人的Email
						TawwpUtil.closeEmail(tawwpMonthPlan.getId());
					} catch (Exception e) {
						System.out.print("消息发送异常" + e);
					}

				} else {
					// 异常
					throw new TawwpException("月度作业计划审批流程存在,但没有指定审批人");
				}
			}
			// ----------------------------------------------------------------------
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划审批通过出现异常");
		} finally {
			tawwpMonthExecutes = null;
		}
	}

	/**
	 * 业务逻辑:月度作业计划审批(驳回)(AUDITING-MONTHLY-PLAN-003)
	 * 
	 * @param _monthCheckId
	 *            String 审批信息编号
	 * @param _content
	 *            String 审批信息
	 * @param _checkUser
	 *            String 审批人
	 * @throws Exception
	 *             异常
	 */
	public void rejectMonthCheck(String _monthCheckId, String _content,
			String _checkUser) throws Exception {

		// 初始化数据
		// TawwpMonthCheckDAO tawwpMonthCheckDAO = new TawwpMonthCheckDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();

		// 月度作业计划审批信息对象
		TawwpMonthCheck tawwpMonthCheck = null;
		TawwpMonthPlan tawwpMonthPlan = null;

		try {

			// 获取月度作业计划审批信息对象
			tawwpMonthCheck = tawwpMonthCheckDao.loadMonthCheck(_monthCheckId);
			tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();

			// 封装月度作业计划审批信息对象
			tawwpMonthCheck.setContent(_content);
			tawwpMonthCheck.setState("2");
			tawwpMonthCheck.setCheckUser(_checkUser);
			tawwpMonthCheck.setChecktime(TawwpUtil.getCurrentDateTime());

			// 修改月度作业计划审批信息
			tawwpMonthCheckDao.updateMonthCheck(tawwpMonthCheck);

			tawwpMonthPlan.setConstituteState("2");
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

			/*
			 * //发送短信信息给创建人 TawwpUtil.sendSMS(tawwpMonthPlan.getCruser(),
			 * "月度作业计划《" + StaticMethod.null2String(tawwpMonthPlan.getName()) +
			 * "》被驳回");
			 */
			try {
				// 发送短信信息给创建人
				TawwpUtil.sendSMS(tawwpMonthPlan.getCruser(), "月度作业计划《"
						+ StaticMethod.null2String(tawwpMonthPlan.getName())
						+ "》被驳回", tawwpMonthPlan.getId());
				// 删除短信审批人的短信设定
				TawwpUtil.closeSMS(tawwpMonthPlan.getId());
				TawwpUtil.sendMail(tawwpMonthPlan.getCruser(), "月度作业计划《"
						+ StaticMethod.null2String(tawwpMonthPlan.getName())
						+ "》被驳回", tawwpMonthPlan.getId());

				TawwpUtil.closeEmail(tawwpMonthPlan.getId());
			} catch (Exception e) {
				System.out.print("消息发送异常" + e);
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划审批驳回出现异常");
		}
	}

	/**
	 * 业务逻辑:浏览月度作业计划_列表(BROWSE-MONTHLY-PLAN-001)
	 * 
	 * @param _deptId
	 *            String 部门编号
	 * @param _yearFlag
	 *            String 计划年度
	 * @param _monthFlag
	 *            String 计划月度
	 * @throws Exception
	 *             异常
	 * @return monthPlanVOHash 封装月度计划信息的Hashtable
	 */
	public Hashtable listMonthPlan(String _deptId, String _yearFlag,
			String _monthFlag) throws Exception {

		// 初始化数据
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthPlanVO tempMonthPlanVO = null;

		// 集合对象
		List monthPlanList = null;
		Hashtable monthPlanVOHash = new Hashtable();
		List monthPlanVOList = new ArrayList();
		List yearPlanList = new ArrayList();
		// 临时集合用于存放关联与某年度计划,但网元不同的月度计划
		List tempList = null;

		try {

			// 获取年度作业计划集合
			yearPlanList = tawwpYearPlanDao.listYearPlanByDept(_deptId,
					_yearFlag);

			// 将年度作业计划对象作为key值put到Hashtable中
			for (int k = 0; k < yearPlanList.size(); k++) {
				monthPlanVOHash.put((TawwpYearPlan) (yearPlanList.get(k)),
						new ArrayList());
			}

			// ---------------------------------------------------------------------

			// 获取月度作业计划对象集合
			monthPlanList = tawwpMonthPlanDao
					.listMonthPlan(_deptId, _yearFlag,
							TawwpStaticVariable.MONTH[StaticMethod
									.null2int(_monthFlag)]);

			// 取出model对象,处理后封装成VO对象
			for (int i = 0; i < monthPlanList.size(); i++) {

				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(i);

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);

				// 获取执行类型名称
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(tawwpMonthPlanVO.getExecuteType()));
				// 获取部门名称
				// tawwpMonthPlanVO.setDeptName(tawwpMonthPlanVO.getDeptId());
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 获取系统类型名称
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 获取网元类型名称
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 获取创建人姓名
				tawwpMonthPlanVO.setUserName(tawwpMonthPlanVO.getCruser());
				// tawwpMonthPlanVO.setUserName(tawwpUtilDAO
				// .getUserName(tawwpMonthPlanVO.getCruser()));
				// 获取制定状态名称
				tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
						.getConstituteStateName(tawwpMonthPlanVO
								.getConstituteState()));
				if (null != tawwpMonthPlanVO.getUnicomType()
						&& !tawwpMonthPlanVO.getUnicomType().equals("")
						&& tawwpMonthPlanVO.getConstituteState().equals("1")) {
					tawwpMonthPlanVO
							.setConstituteStateName(TawwpMonthPlanVO.MONTHREPORTSTATENAME[StaticMethod
									.null2int(tawwpMonthPlanVO.getReportFlag())]);

					// 获取执行状态名称
				}
				tawwpMonthPlanVO
						.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(tawwpMonthPlanVO
										.getExecuteState()));
				// 获取执行负责人姓名
				// tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
				// .getUserName(tawwpMonthPlanVO.getPrincipal()));
				tawwpMonthPlanVO.setPrincipalName(tawwpMonthPlanVO
						.getPrincipal());
				/*
				 * 获取网元名称 (解决中文问题,不用静态方法处理的话会产生乱码"????????",
				 * 从model中取值用于页面显示必须经过如下处理)
				 */
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet()
							.getId());
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}
				// 将VO对象添加进列表中
				monthPlanVOList.add(tawwpMonthPlanVO);
			}

			// ---------------------------------------------------------------------

			// 将月度作业计划VO信息封装进Hashtable对象
			for (int j = 0; j < monthPlanVOList.size(); j++) {

				// 创建月度作业计划、VO对象
				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(j);
				tempMonthPlanVO = (TawwpMonthPlanVO) monthPlanVOList.get(j);

				// 以年度作业计划对象作为key值,获取月度作业计划列表
				tempList = (List) monthPlanVOHash.get(tawwpMonthPlan
						.getTawwpYearPlan());

				// 1.如果为空,说明Hashtable对象中,不含有key值为指定年度作业计划的月度作业计划VO对象列表
				if (tempList == null) {
					// 新建VO列表
					List monthPlanVOlist = new ArrayList();
					// 将月度作业计划VO对象添加进列表
					monthPlanVOlist.add(tempMonthPlanVO);
					// 将key和value,put进Hashtable对象中
					monthPlanVOHash.put(tawwpMonthPlan.getTawwpYearPlan(),
							monthPlanVOlist);
				}

				// 2.如果不为空,说明Hashtable对象中,已含有key值为指定年度作业计划的月度作业计划VO对象列表
				else {
					// 不需新建,只需将月度作业计划VO对象添加进列表
					tempList.add(tempMonthPlanVO);
				}
			}

			// ---------------------------------------------------------------------

			// 返回封装月度计划信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划列表浏览出现异常");
		} finally {
			monthPlanList = null;
			monthPlanVOList = null;
			tempList = null;
		}
	}

	/**
	 * 业务逻辑:月度作业计划审批_页面显示(AUDITING-MONTHLY-PLAN-001)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthPlanVO 月度作业计划VO对象
	 */
	public TawwpMonthPlanVO viewMonthPlan(String _monthPlanId) throws Exception {

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthCheckVO tawwpMonthCheckVO = null;
		List list = null;
		Step step = null;

		tawwpMonthPlanVO = this.getMonthPlan(_monthPlanId);

		list = tawwpMonthPlanVO.getMonthCheckVOList();

		for (int i = 0; i < list.size(); i++) {
			tawwpMonthCheckVO = (TawwpMonthCheckVO) list.get(i);
			if (tawwpMonthCheckVO.getState().equals("0")) {
				list.remove(tawwpMonthCheckVO);
			}
		}

		/*-----------------------------------------------------------------
		 审批流程代码部分
		 */
		// 如果月度作业计划制定状态为"0:新建"
		if (tawwpMonthPlanVO.getConstituteState().equals("0")
				|| tawwpMonthPlanVO.getConstituteState().equals("2")) {

			// 获取当前月度作业计划的审批流程信息
			TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance(); // 获取流程管理对象
			step = tawwpFlowManage.getFristMonthFlowStep(tawwpMonthPlanVO
					.getDeptId()); // 获取第一个步骤信息

			if (step != null) {
				// 存在流程
				tawwpMonthPlanVO.setStep(step);
				// added by lijia 2005-12-09
				TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
				tawwpMonthPlanVO.setCheckUsers(tawwpUtilDAO.getUserNames(step
						.getCheckUserIdStr()));

			}
		} else if (tawwpMonthPlanVO.getConstituteState().equals("1")) {
			// 获取当前月度作业计划的网元修改审批流程信息
			TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance(); // 获取流程管理对象
			step = tawwpFlowManage.getFristMonthUpdateFlowStep(tawwpMonthPlanVO
					.getDeptId()); // 获取第一个步骤信息

			if (step != null) {
				// 存在流程
				tawwpMonthPlanVO.setStep(step);
				// added by lijia 2005-12-09
				TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
				tawwpMonthPlanVO.setCheckUsers(tawwpUtilDAO.getUserNames(step
						.getCheckUserIdStr()));
			}
		}
		// 返回月度作业计划VO对象
		return tawwpMonthPlanVO;
	}

	/**
	 * 业务逻辑:浏览月度作业计划_详细信息(BROWSE-MONTHLY-PLAN-002)
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthPlanVO 月度作业计划VO对象
	 */
	public TawwpMonthPlanVO viewMonthPlanByCheck(String _monthPlanId)
			throws Exception {

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthCheckVO tawwpMonthCheckVO = null;
		TawwpMonthCheckVO tempTawwpMonthCheckVO = null;

		List list = null;
		Step step = null;

		tawwpMonthPlanVO = this.getMonthPlan(_monthPlanId);

		list = tawwpMonthPlanVO.getMonthCheckVOList();

		/*----------------------------------------------------------
		 审批流程代码部分
		 */
		// 如果当前审批信息的步骤编号大于记录步骤编号，则更新记录步骤编号
		for (int i = 0; i < list.size(); i++) {
			tawwpMonthCheckVO = (TawwpMonthCheckVO) list.get(i);

			if (tawwpMonthCheckVO.getState().equals("0")) {
				tempTawwpMonthCheckVO = tawwpMonthCheckVO;
				list.remove(tawwpMonthCheckVO);
			}
		}

		// 获取流程管理对象
		TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance();
		// 获取下一步骤信息
		step = tawwpFlowManage.getMonthFlowStep(tempTawwpMonthCheckVO
				.getFlowSerial(), tempTawwpMonthCheckVO.getStepSerial(),
				tawwpMonthPlanVO.getDeptId());
		if (step != null) {
			// 存在流程
			tawwpMonthPlanVO.setStep(step);
			// added by lijia 2005-12-09
			TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
			tawwpMonthPlanVO.setCheckUsers(tawwpUtilDAO.getUserNames(step
					.getCheckUserIdStr()));
		}

		// 返回月度作业计划VO对象
		return tawwpMonthPlanVO;
	}

	/**
	 * 业务逻辑：复制月度作业计划(COPY-MONTHLY-PLAN-CONTENT-001) 解释：
	 * 将1.选定月度的上一月的,2.与指定年度作业计划关联的,
	 * 3.已经制定过的(与审批无关),4.选定月度没有相同网元的、关联到指定年度作业计划、的月度作业计划 5.由本部门制定的
	 * 符合以上条件的月度作业计划复制到选定月度 即：如选定月度为8月的年度计划1,则系统会将7月份的以年度作业1为基础制定的,
	 * 各月度计划复制到8月份,但一月份不能复制前一年12月份的月度作业计划
	 * 
	 * @param _yearPlanId
	 *            String 年度作业计划编号
	 * @param _monthFlag
	 *            String 计划月度
	 * @param _userId
	 *            String 创建用户名
	 * @throws Exception
	 *             异常
	 * @return boolean 复制成功标志
	 */

	public boolean copyMonthPlan(String _monthFlag, String _yearPlanId,
			String _userId) throws Exception {

		// 初始化数据
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();

		// model对象
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpYearPlan tawwpYearPlan = null;

		// 集合对象
		Iterator tawwpMonthExecutes = null;

		// 执行日期字符串
		String executeDate = "";

		// 复制成功标志
		boolean flag = true;

		try {
			// 获取年度作业计划对象
			tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId);
			// 获取当月月度作业计划对象集合(含已审批和未审批)
			List monthAllPlanList = tawwpMonthPlanDao.listByYearPlan(TawwpStaticVariable.MONTH[Integer
			        .parseInt(_monthFlag)], tawwpYearPlan, "-1");
			List monthPLans = tawwpMonthPlanDao.getEarlyCrrMonthPlan(tawwpYearPlan,_monthFlag);		
			List needCopyAdd = new ArrayList();
			List needCopyUpdateNew = new ArrayList();
			List needCopyUpdateOld = new ArrayList();
			Map map = this.getCopyMonthFlags(monthPLans,tawwpYearPlan,_monthFlag);
			if(!map.isEmpty()){
				if(monthAllPlanList.size()>0){//当月存在作业计划
					//如果当月存在作业计划，则进行比对，如果相关网元的作业计划已经通过审批，则不再复制；如果还未通过审批，则进行属性的替换
					for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
						TawwpMonthPlan tawwpMonthPlan = (TawwpMonthPlan)iter.next();
						boolean isNotExist = true;
						String tawwpNetId = "";
						if(tawwpMonthPlan.getTawwpNet() != null){
							tawwpNetId = StaticMethod.null2String(tawwpMonthPlan.getTawwpNet().getId());
					    }else{
					    	tawwpNetId = "null";
					    }
					    for(int i=0;i<monthAllPlanList.size();i++){
					    	TawwpMonthPlan tempMonthPlan = (TawwpMonthPlan)monthAllPlanList.get(i);
					    	String tempNetId = "";
					    	if(tempMonthPlan.getTawwpNet() != null){
					    		tempNetId = StaticMethod.null2String(tempMonthPlan.getTawwpNet().getId());
					    	}else{
					    		tempNetId = "null";
					    	}
					    	if(tawwpNetId.equals(tempNetId) && 
					    			StaticMethod.null2String(tempMonthPlan.getConstituteState()).equals("0")){
					    		needCopyUpdateOld.add(tawwpMonthPlan);
					    		needCopyUpdateNew.add(tempMonthPlan);
					    		isNotExist = false;
					    		break;
					    	}else if(tawwpNetId.equals(tempNetId) && 
					    			StaticMethod.null2String(tempMonthPlan.getConstituteState()).equals("1")){
					    		map.remove(tawwpMonthPlan);
					    	}else if(tawwpNetId.equals(tempNetId)){
					    		isNotExist = false;
					    		break;
					    	}
					    }
			    		if(isNotExist){
						    needCopyAdd.add(tawwpMonthPlan);
			    		}
					}
					
					for (int i=0;i<needCopyAdd.size();i++) {
						TawwpMonthPlan tempMonthPlan = (TawwpMonthPlan)needCopyAdd.get(i);
						// 封装月度作业计划对象
						TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan(tempMonthPlan
								.getName(), tempMonthPlan.getExecuteType(),
								tempMonthPlan.getTawwpNet(), tempMonthPlan
										.getDeptId(), tempMonthPlan
										.getSysTypeId(), tempMonthPlan
										.getNetTypeId(), tempMonthPlan
										.getYearFlag(),
								TawwpStaticVariable.MONTH[Integer
										.parseInt(_monthFlag)], "0", _userId,
								TawwpUtil.getCurrentDateTime(), "0", "0",
								tempMonthPlan.getPrincipal(), tempMonthPlan
										.getTawwpModelPlan(), tawwpYearPlan);
						tawwpMonthPlan.setCheckUserId(this.getCheckUsers(tempMonthPlan));

						// 保存月度作业计划
						tawwpMonthPlanDao.saveMonthPlan(tawwpMonthPlan);

						// -------------------------------------------------------------------

						// 获取月度作业计划执行内容集合
						List tawwpMonthExecutesList = (ArrayList)map.get(tempMonthPlan);

						for(int j=0;j<tawwpMonthExecutesList.size();j++) {
							
							// 复制月度作业计划执行内容
							TawwpMonthExecute tempMonthExecute = (TawwpMonthExecute) tawwpMonthExecutesList.get(j);

							// 判断该执行内容是否应该在指定月执行
							if (this.judgeMonthExecute(_monthFlag,
									tempMonthExecute.getTawwpYearExecute()
											.getMonthFlag(),
									tempMonthExecute.getCycle())) {
								// 根据年、月份获得该月度作业计划执行内容应执行的日期表示字符串(0,1形式)
								executeDate = this.getExecuteDate(
										tawwpMonthPlan.getYearFlag(),
										tawwpMonthPlan.getMonthFlag(),
										tempMonthExecute.getCycle(),
										tempMonthExecute.getIsHoliday(),
										tempMonthExecute.getIsWeekend(),
										StaticMethod.null2String(tempMonthExecute.getExecuteDay()));

								// 封装月度作业计划执行内容对象
								tawwpMonthExecute = new TawwpMonthExecute(
										tempMonthExecute.getName(),
										tempMonthExecute.getBotype(),
										tempMonthExecute
												.getExecutedeptlevel(),
										tempMonthExecute.getAppdesc(),
										tempMonthExecute.getCycle(),
										tempMonthExecute.getRemark(),
										tempMonthExecute.getFormat(),
										tempMonthExecute.getValidate(),
										tempMonthExecute.getFormId(), "0",
										_userId, TawwpUtil
												.getCurrentDateTime(),
										tempMonthExecute.getSerialNo(),
										tempMonthExecute.getXlsX(),
										tempMonthExecute.getXlsY(), "",
										tempMonthExecute.getCommand(),
										tempMonthExecute.getExecuter(),
										tempMonthExecute.getExecuterType(),
										tempMonthExecute.getExecuteRoom(),
										tempMonthExecute.getExecuteDuty(),
										executeDate, "0", tempMonthExecute
												.getTawwpModelGroup(),
										tawwpMonthPlan, tempMonthExecute
												.getTawwpYearExecute(),
										new HashSet(), "0", "0",
										tempMonthExecute.getFileFlag(), "",
										tempMonthExecute.getExecuteDay());
								tawwpMonthExecute.setEndHH(tempMonthExecute
										.getEndHH());
								tawwpMonthExecute
										.setStartHH(tempMonthExecute
												.getStartHH());
								tawwpMonthExecute
										.setCommandName(tempMonthExecute
												.getCommandName()); // edit
								// by
								// gongyufeng
								// 保存月度作业计划执行内容
								tawwpMonthExecuteDao.saveMonthExecute(tawwpMonthExecute);

							}
						}
					    
					}
					for (int i=0;i<needCopyUpdateOld.size();i++) {
						TawwpMonthPlan tawwpMonthPlanOld = (TawwpMonthPlan)needCopyUpdateOld.get(i);
						TawwpMonthPlan tawwpMonthPlanNew = (TawwpMonthPlan)needCopyUpdateNew.get(i);
						tawwpMonthPlanNew.setCheckUserId(this.getCheckUsers(tawwpMonthPlanOld));
						tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlanNew);
					}
				}else{//当月不存在作业计划的情况
					for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
						TawwpMonthPlan tempMonthPlan = (TawwpMonthPlan)iter.next();
						// 封装月度作业计划对象
						TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan(tempMonthPlan
								.getName(), tempMonthPlan.getExecuteType(),
								tempMonthPlan.getTawwpNet(), tempMonthPlan
										.getDeptId(), tempMonthPlan
										.getSysTypeId(), tempMonthPlan
										.getNetTypeId(), tempMonthPlan
										.getYearFlag(),
								TawwpStaticVariable.MONTH[Integer
										.parseInt(_monthFlag)], "0", _userId,
								TawwpUtil.getCurrentDateTime(), "0", "0",
								tempMonthPlan.getPrincipal(), tempMonthPlan
										.getTawwpModelPlan(), tawwpYearPlan);
						tawwpMonthPlan.setCheckUserId(this.getCheckUsers(tempMonthPlan));

						// 保存月度作业计划
						tawwpMonthPlanDao.saveMonthPlan(tawwpMonthPlan);

						// -------------------------------------------------------------------

						// 获取月度作业计划执行内容集合
						List tawwpMonthExecutesList = (ArrayList)map.get(tempMonthPlan);

						for(int i=0;i<tawwpMonthExecutesList.size();i++) {
							
							// 复制月度作业计划执行内容
							TawwpMonthExecute tempMonthExecute = (TawwpMonthExecute) tawwpMonthExecutesList.get(i);

							// 判断该执行内容是否应该在指定月执行
							if (this.judgeMonthExecute(_monthFlag,
									tempMonthExecute.getTawwpYearExecute()
											.getMonthFlag(),
									tempMonthExecute.getCycle())) {
								// 根据年、月份获得该月度作业计划执行内容应执行的日期表示字符串(0,1形式)
								executeDate = this.getExecuteDate(
										tawwpMonthPlan.getYearFlag(),
										tawwpMonthPlan.getMonthFlag(),
										tempMonthExecute.getCycle(),
										tempMonthExecute.getIsHoliday(),
										tempMonthExecute.getIsWeekend(),
										StaticMethod.null2String(tempMonthExecute.getExecuteDay()));

								// 封装月度作业计划执行内容对象
								tawwpMonthExecute = new TawwpMonthExecute(
										tempMonthExecute.getName(),
										tempMonthExecute.getBotype(),
										tempMonthExecute
												.getExecutedeptlevel(),
										tempMonthExecute.getAppdesc(),
										tempMonthExecute.getCycle(),
										tempMonthExecute.getRemark(),
										tempMonthExecute.getFormat(),
										tempMonthExecute.getValidate(),
										tempMonthExecute.getFormId(), "0",
										_userId, TawwpUtil
												.getCurrentDateTime(),
										tempMonthExecute.getSerialNo(),
										tempMonthExecute.getXlsX(),
										tempMonthExecute.getXlsY(), "",
										tempMonthExecute.getCommand(),
										tempMonthExecute.getExecuter(),
										tempMonthExecute.getExecuterType(),
										tempMonthExecute.getExecuteRoom(),
										tempMonthExecute.getExecuteDuty(),
										executeDate, "0", tempMonthExecute
												.getTawwpModelGroup(),
										tawwpMonthPlan, tempMonthExecute
												.getTawwpYearExecute(),
										new HashSet(), "0", "0",
										tempMonthExecute.getFileFlag(), "",
										tempMonthExecute.getExecuteDay());
								tawwpMonthExecute.setEndHH(tempMonthExecute
										.getEndHH());
								tawwpMonthExecute
										.setStartHH(tempMonthExecute
												.getStartHH());
								tawwpMonthExecute
										.setCommandName(tempMonthExecute
												.getCommandName()); // edit
								// by
								// gongyufeng
								// 保存月度作业计划执行内容
								tawwpMonthExecuteDao.saveMonthExecute(tawwpMonthExecute);

							}
						}
					}
				}
			}else{
				flag = false;
			}
			// 返回复制成功标志位
			return flag;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			System.out.println("@@@@@@@@@@@@@@@@@copyMonthPlan is error " + e);
			throw new TawwpException("月度作业计划复制出现异常");
		} finally {
			tawwpMonthExecutes = null;
		}
	}

	/**
	 * 业务逻辑:列表待审批月度作业计划(LIST-MONTHLY-PLAN-ADJUSTING-001)
	 * 
	 * @param _userId
	 *            String 用户名
	 * @throws Exception
	 *             异常
	 * @return List月度作业计划VO对象集合
	 */
	public List listMonthCheck1(String _userId) throws Exception {

		// 初始化数据,DAO对象
		// TawwpMonthCheckDAO tawwpMonthCheckDAO = new TawwpMonthCheckDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// 集合对象
		List list = null;
		List newList = new ArrayList();
		// model对象
		TawwpMonthCheck tawwpMonthCheck = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		// VO对象
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 获取月度作业计划审批信息集合
			list = tawwpMonthCheckDao.listMonthCheck(_userId);

			for (int i = 0; i < list.size(); i++) {

				// 获取月度作业计划对象
				tawwpMonthCheck = (TawwpMonthCheck) list.get(i);
				tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				// 封装月度作业计划VO对象
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				// 获取执行类型名称
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(StaticMethod
								.null2int(tawwpMonthPlanVO.getExecuteType())));
				// 获取部门名称
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 获取系统类型名称
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 获取网元类型名称
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 获取创建人姓名
				tawwpMonthPlanVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));
				// 获取制定状态名称
				tawwpMonthPlanVO
						.setConstituteStateName(TawwpMonthPlanVO
								.getConstituteStateName(Integer
										.parseInt(tawwpMonthPlanVO
												.getConstituteState())));
				// 获取执行状态名称
				tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
						.getExecuteStateName(StaticMethod
								.null2int(tawwpMonthPlanVO.getExecuteState())));
				// 获取执行负责人姓名
				tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				// 获取网元名称

				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet()
							.getId());
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}
				if (StaticMethod.null2String(tawwpMonthCheck.getType()).equals(
						"1")) {
					tawwpMonthPlanVO.setName(tawwpMonthPlanVO.getName()
							+ "(删除网元)");
				}
				tawwpMonthPlanVO.setMonthCheckId(tawwpMonthCheck.getId());

				// 将月度作业计划VO对象添加进集合中
				newList.add(tawwpMonthPlanVO);
				Collections.sort(newList, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
						TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
						int k = -1;
						if(p1.getCrtime().compareTo(p2.getCrtime())>0){
							k = 1;
						}else if(p1.getCrtime().compareTo(p2.getCrtime())==0){
							if (p1.getId().compareTo(p2.getId()) > 0) {
								k = 1;
							}else{
								k = -1;
							}
						}else 
							if(p1.getCrtime().compareTo(p2.getCrtime())<0){
								k = -1;
						}
						return k;

					}
				});
			}
			// 返回月度作业计划VO对象集合
			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("获取待审批月度作业计划列表出现异常");
		} finally {
			list = null;
		}
	}
	public List listMonthCheck(String _userId) throws Exception {

		// 初始化数据,DAO对象
		// TawwpMonthCheckDAO tawwpMonthCheckDAO = new TawwpMonthCheckDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		Hashtable _hashtable = new Hashtable();
		// 集合对象
		List list = null;
		List newList = new ArrayList();
		// model对象
		TawwpMonthCheck tawwpMonthCheck = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		// VO对象
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 获取月度作业计划审批信息集合
			list = tawwpMonthCheckDao.listMonthCheck(_userId);
			_hashtable = getSameToYearPlanId(list);
			String flag = "";

			for (int i = 0; i < list.size(); i++) {
				tawwpMonthCheck = (TawwpMonthCheck) list.get(i);
				tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();

				if (flag.indexOf(tawwpMonthPlan.getYearPlanId()
						+ tawwpMonthPlan.getMonthFlag()) > 0) {
					continue;
				}
				tawwpMonthPlanVO = (TawwpMonthPlanVO) _hashtable
						.get(tawwpMonthPlan.getYearPlanId()
								+ tawwpMonthPlan.getMonthFlag());
				flag = flag + "_" + tawwpMonthPlan.getYearPlanId()
						+ tawwpMonthPlan.getMonthFlag();
		
				newList.add(tawwpMonthPlanVO);
			}

			// 返回月度作业计划VO对象集合
			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("获取待审批月度作业计划列表出现异常");
		} finally {
			list = null;
		}
	}

	/**
	 * 业务逻辑:列表待审批月度作业计划(LIST-MONTHLY-PLAN-ADJUSTING-001)
	 * 
	 * @param _userId
	 *            String 用户名
	 * @throws Exception
	 *             异常
	 * @return List月度作业计划VO对象集合
	 */
	public List listMonthCheck(String _userId,String _monthPlanId) throws Exception {

		// 初始化数据,DAO对象
		// TawwpMonthCheckDAO tawwpMonthCheckDAO = new TawwpMonthCheckDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		Hashtable _hashtable = new Hashtable();
		// 集合对象
		List list = null;
		List newList = new ArrayList();
		// model对象
		TawwpMonthCheck tawwpMonthCheck = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		// VO对象
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		String monthPlanStr = "";
		try {
			String[] monthPlanArray = _monthPlanId.split(",");
			for (int i = 0; i < monthPlanArray.length; i++) {
				// 拼成'abc','bcd'的格式
				monthPlanStr = monthPlanStr + "'" + monthPlanArray[i] + "',";
			}
			monthPlanStr = monthPlanStr.substring(0, monthPlanStr.length() - 1);
			// 获取月度作业计划审批信息集合
			list = tawwpMonthCheckDao.listMonthCheck(_userId,monthPlanStr);
			 

			for (int i = 0; i < list.size(); i++) {
				tawwpMonthCheck = (TawwpMonthCheck) list.get(i);
				tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();
				tawwpMonthPlanVO = new TawwpMonthPlanVO();
//				 封装月度作业计划VO对象
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				// 获取执行类型名称
				tawwpMonthPlanVO
						.setExecuteTypeName(TawwpMonthPlanVO
								.getExecuteTypeName(StaticMethod
										.null2int(tawwpMonthPlanVO
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
						.getExecuteStateName(StaticMethod
								.null2int(tawwpMonthPlanVO
										.getExecuteState())));
				// 获取执行负责人姓名
				tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				// 获取网元名称

				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet()
							.getId());
					tawwpMonthPlanVO.setNetName(StaticMethod
							.null2String(tawwpMonthPlan.getTawwpNet()
									.getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}
				if (StaticMethod.null2String(tawwpMonthCheck.getType())
						.equals("1")) {
					tawwpMonthPlanVO.setName(tawwpMonthPlanVO.getName()
							+ "(删除网元)");
				}
				tawwpMonthPlanVO.setMonthCheckId(tawwpMonthCheck.getId());
		
				newList.add(tawwpMonthPlanVO);
			}

			// 返回月度作业计划VO对象集合
			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("获取待审批月度作业计划列表出现异常");
		} finally {
			list = null;
		}
	}

	/**
	 * 根据年计划id相同的和月份相同的合并到一起
	 * 
	 * @param list
	 * @return
	 */
	public Hashtable getSameToYearPlanId(List list) {
		Hashtable _hashtable = new Hashtable();
		TawwpMonthCheck tawwpMonthCheck = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		try {
			for (int i = 0; i < list.size(); i++) {
				tawwpMonthCheck = (TawwpMonthCheck) list.get(i);
				tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();

				if (_hashtable.get(tawwpMonthPlan.getTawwpYearPlan().getId()
						+ (String) tawwpMonthPlan.getMonthFlag()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO(); // 创建
					_hashtable.put(tawwpMonthPlan.getTawwpYearPlan().getId()
							+ (String) tawwpMonthPlan.getMonthFlag(),
							tawwpMonthPlanVO);
					 
					// 封装月度作业计划VO对象
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					// 获取执行类型名称
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(StaticMethod
											.null2int(tawwpMonthPlanVO
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
							.getExecuteStateName(StaticMethod
									.null2int(tawwpMonthPlanVO
											.getExecuteState())));
					// 获取执行负责人姓名
					tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 获取网元名称

					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet()
								.getId());
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}
					if (StaticMethod.null2String(tawwpMonthCheck.getType())
							.equals("1")) {
						tawwpMonthPlanVO.setName(tawwpMonthPlanVO.getName()
								+ "(删除网元)");
					}
					tawwpMonthPlanVO.setMonthCheckId(tawwpMonthCheck.getId());

				} else {
					tawwpMonthPlanVO = (TawwpMonthPlanVO) _hashtable
							.get(tawwpMonthPlan.getTawwpYearPlan().getId()
									+ (String) tawwpMonthPlan.getMonthFlag()); // 获取
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet()
								.getId());
						tawwpMonthPlanVO.setNetName(tawwpMonthPlanVO
								.getNetName()
								+ ","
								+ StaticMethod.null2String(tawwpMonthPlan
										.getTawwpNet().getName()));
					} else {
						tawwpMonthPlanVO.setNetName(tawwpMonthPlanVO
								.getNetName()
								+ ",无网元");
					}
					tawwpMonthPlanVO.setMonthCheckId(tawwpMonthPlanVO.getMonthCheckId()+","+tawwpMonthCheck.getId());
					tawwpMonthPlanVO.setId(tawwpMonthPlanVO.getId()+","+tawwpMonthPlan.getId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _hashtable;
	}

	/**
	 * 获取月度作业计划基本信息
	 * 
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 * @return TawwpMonthPlanVO 月度作业计划VO对象
	 */
	private TawwpMonthPlanVO getMonthPlan(String _monthPlanId) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// VO对象
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpMonthCheckVO tawwpMonthCheckVO = null;
		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpMonthCheck tawwpMonthCheck = null;

		// 月度作业计划执行内容对象、VO集合
		Iterator tawwpMonthExecutes = null;
		List monthExecuteVOList = new ArrayList();

		// 月度作业计划审批信息对象VO集合
		Iterator tawwpMonthChecks = null;
		List monthCheckVOList = new ArrayList();

		// 当月天数
		int dayCount = 0;

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
					.getExecuteTypeName(StaticMethod.null2int(tawwpMonthPlanVO
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
			// 获取创建人姓名
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));
			// 获取制定状态名称
			tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
					.getConstituteStateName(StaticMethod
							.null2int(tawwpMonthPlanVO.getConstituteState())));
			// modify by scropioD 获得上报的状态
			if (null != tawwpMonthPlanVO.getUnicomType()
					&& !tawwpMonthPlanVO.getUnicomType().equals("")
					&& tawwpMonthPlanVO.getConstituteState().equals("1")) {
				tawwpMonthPlanVO
						.setConstituteStateName(TawwpMonthPlanVO.MONTHREPORTSTATENAME[StaticMethod
								.null2int(tawwpMonthPlanVO.getReportFlag())]);
				// 获取执行状态名称
			}
			tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
					.getExecuteStateName(StaticMethod.null2int(tawwpMonthPlanVO
							.getExecuteState())));
			// 获取执行负责人姓名
			tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getPrincipal()));
			// 获取网元名称

			if (tawwpMonthPlan.getTawwpNet() != null) {
				tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet().getId());
				tawwpMonthPlanVO.setNetName(StaticMethod
						.null2String(tawwpMonthPlan.getTawwpNet().getName()));
				tawwpMonthPlanVO.setNetSerialNo(tawwpMonthPlan.getTawwpNet()
						.getSerialNo());
			} else {
				tawwpMonthPlanVO.setNetName("无网元");
			}

			// ------------------------------------------------------------------------

			// 获取月度作业计划执行内容集合
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			while (tawwpMonthExecutes.hasNext()) {
				tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
						.next());
				tawwpMonthExecuteVO = new TawwpMonthExecuteVO();
				// 封装月度作业计划执行内容VO对象
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthExecuteVO,
						tawwpMonthExecute);
				// 获取执行周期
				tawwpMonthExecuteVO.setCycleName(tawwpUtilDAO
						.getCycleName(StaticMethod.null2int(tawwpMonthExecuteVO
								.getCycle())));
				// 获取电子表单名称
				tawwpMonthExecuteVO.setFormName(tawwpUtilDAO
						.getAddonsName(tawwpMonthExecuteVO.getFormId()));
				// 获取创建人姓名
				tawwpMonthExecuteVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthExecuteVO.getCruser()));
				// 获取网元类型名称
				tawwpMonthExecuteVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthExecuteVO.getNetTypeId()));

				// 获取执行人类型名称
				if (tawwpMonthExecuteVO.getExecuterType() != null
						&& !"".equals(tawwpMonthExecuteVO.getExecuterType())) {
					tawwpMonthExecuteVO.setExecuterTypeName(TawwpMonthExecuteVO
							.getExecuterTypeName(Integer
									.parseInt(tawwpMonthExecuteVO
											.getExecuterType())));
				}
				// 如果执行人类型为班次
				if ("3".equals(tawwpMonthExecuteVO.getExecuterType())) {
					// 获取机房名称
					String roomNames = tawwpUtilDAO.getRoomNames(StaticMethod
							.null2String(tawwpMonthExecuteVO.getExecuter()));
					tawwpMonthExecuteVO.setExecuterName(roomNames);
					tawwpMonthExecuteVO.setExecuteRoomName(roomNames);

					// 获取执行班次名称
					if (tawwpMonthExecuteVO.getExecuteDuty() != null
							&& !"".equals(tawwpMonthExecuteVO.getExecuteDuty())) {
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
								.getDeptNames(StaticMethod
										.null2String(tawwpMonthExecuteVO
												.getExecuter())));
					}
					// 执行人类型为岗位
					if ("2".equals(tawwpMonthExecuteVO.getExecuterType())) {
						// ccccccccccccccc
						/*
						 * tawwpMonthExecuteVO.setExecuterName(tawwpUtilDAO.getOrgPostNames(
						 * tawwpMonthExecuteVO.getExecuter()));
						 */
					}
				}
				// 获取该月天数
				dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlan
						.getYearFlag(), tawwpMonthPlan.getMonthFlag());
				tawwpMonthExecuteVO.setDayCount(String.valueOf(dayCount));
				String switchCheck = StaticMethod
						.null2String(WorkplanMgrLocator.getAttributes()
								.getSwitchQualityCheck());
				if (switchCheck.equals("1")
						&& StaticMethod.null2String(
								tawwpMonthExecuteVO.getFileFlag()).equals("1")) {
					tawwpMonthExecuteVO.setQualityCheckUser(StaticMethod
							.null2String(tawwpMonthExecuteVO
									.getQualityCheckUser()));
					tawwpMonthExecuteVO.setQualityCheckUserName(tawwpUtilDAO
							.getUserNames(StaticMethod
									.null2String(tawwpMonthExecuteVO
											.getQualityCheckUser())));
				}
				// 添加到集合中
				monthExecuteVOList.add(tawwpMonthExecuteVO);
//				Collections.sort(monthExecuteVOList);
				Collections.sort(monthExecuteVOList, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthExecuteVO p1 = (TawwpMonthExecuteVO) o1;
						TawwpMonthExecuteVO p2 = (TawwpMonthExecuteVO) o2;
						int k = -1;
						if(p1.getName().compareTo(p2.getName())>0){
							k = 1;
						}else if(p1.getName().compareTo(p2.getName())==0){
							if (p1.getId().compareTo(p2.getId()) > 0) {
								k = 1;
							}else{
								k = -1;
							}
						}else 
							if(p1.getName().compareTo(p2.getName())<0){
								k = -1;
						}
						return k;

					}
				});
			}

			// ------------------------------------------------------------------------

			tawwpMonthChecks = tawwpMonthPlan.getTawwpMonthChecks().iterator();

			while (tawwpMonthChecks.hasNext()) {
				tawwpMonthCheck = (TawwpMonthCheck) (tawwpMonthChecks.next());

				tawwpMonthCheckVO = new TawwpMonthCheckVO();
				// 封装月度作业计划审批信息VO对象
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthCheckVO,
						tawwpMonthCheck);
				// 获取部门名称
				tawwpMonthCheckVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthCheckVO.getDeptId()));
				// 获取审批人姓名
				tawwpMonthCheckVO.setCheckUserName(tawwpUtilDAO
						.getUserName(tawwpMonthCheckVO.getCheckUser()));
				// 获取审批状态
				tawwpMonthCheckVO.setStateName(tawwpUtilDAO
						.getCheckStateName(StaticMethod
								.null2int(tawwpMonthCheckVO.getState())));

				// 添加到集合中
				monthCheckVOList.add(tawwpMonthCheckVO);
//				Collections.sort(monthCheckVOList);
				Collections.sort(monthCheckVOList, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthExecuteVO p1 = (TawwpMonthExecuteVO) o1;
						TawwpMonthExecuteVO p2 = (TawwpMonthExecuteVO) o2;
						int k = -1;
						if(p1.getCrtime().compareTo(p2.getCrtime())>0){
							k = 1;
						}else if(p1.getCrtime().compareTo(p2.getCrtime())==0){
							if (p1.getId().compareTo(p2.getId()) > 0) {
								k = 1;
							}else{
								k = -1;
							}
						}else 
							if(p1.getCrtime().compareTo(p2.getCrtime())<0){
								k = -1;
						}
						return k;

					}
				});
			}

			// ------------------------------------------------------------------------

			// 将执行内容集合和审批信息集合添加到月度作业计划VO对象中
			tawwpMonthPlanVO.setMonthExecuteVOList(monthExecuteVOList);
			tawwpMonthPlanVO.setMonthCheckVOList(monthCheckVOList);
			// 返回月度作业计划VO对象
			return tawwpMonthPlanVO;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划审批信息详细浏览出现异常");
		} finally {
			tawwpMonthExecutes = null;
			monthExecuteVOList = null;
			tawwpMonthChecks = null;
			monthCheckVOList = null;
		}
	}

	/**
	 * 判断指定月度作业计划中,是否已经存在以指定年度作业计划执行内容为基础,制定的月度作业计划执行内容
	 * 
	 * @param _tawwpMonthPlan
	 *            TawwpMonthPlan 月度作业计划对象
	 * @param _tawwpYearExecute
	 *            TawwpYearExecute 年度作业计划执行内容对象
	 * @throws Exception
	 *             异常
	 * @return boolean 是否存在标志位
	 */
	private boolean judgeExecuteExist(TawwpMonthPlan _tawwpMonthPlan,
			TawwpYearExecute _tawwpYearExecute) throws Exception {
		// 初始化数据,是否存在标志
		boolean flag = true;
		// 月度作业计划执行内容集合对象
		Iterator tawwpMonthExecutes = null;
		// 获取月度作业计划执行内容集合
		tawwpMonthExecutes = _tawwpMonthPlan.getTawwpMonthExecutes().iterator();

		while (tawwpMonthExecutes.hasNext()) {
			TawwpMonthExecute tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
					.next());
			try {
				if (tawwpMonthExecute.getTawwpYearExecute().getId().equals(
						_tawwpYearExecute.getId()))
				{
					// 已经存在了
					flag = false;
				}
			} catch (Exception e) {
				System.out.print(e);
			}
		}
		// 返回是否存在标志
		return flag;
	}

	/**
	 * 业务逻辑：修改月度作业计划执行内容_保存(EDIT-EXECUTION-002)
	 * 
	 * @param _monthExecuteId
	 *            String 月度作业计划执行内容编号
	 * @param _executer
	 *            String 执行人
	 * @param _executerType
	 *            String 执行人类型(0：表示人员，1：表示部门，2：表示岗位 3：表示班次)
	 * @param _executeRoom
	 *            String 执行机房
	 * @param _executeDuty
	 *            String 执行班次
	 * @param _executeDate
	 *            String 执行日期
	 * @throws Exception
	 *             异常
	 */
	public void editMonthExecuteSave(String _monthExecuteId, String _executer,
			String _executerType, String _executeRoom, String _executeDuty,
			String _executeDate, String _startHH, String _endHH,
			String _checkUser) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;

		try {
			// 获取月度作业计划执行内容对象
			tawwpMonthExecute = tawwpMonthExecuteDao
					.loadMonthExecute(_monthExecuteId);

			// 封装月度作业计划执行内容对象
			tawwpMonthExecute.setExecuter(_executer);
			tawwpMonthExecute.setExecuterType(_executerType);
			tawwpMonthExecute.setExecuteDate(_executeDate);
			tawwpMonthExecute.setStartHH(_startHH);
			tawwpMonthExecute.setEndHH(_endHH);
			if (StaticMethod.nullObject2String(tawwpMonthExecute.getFileFlag())
					.equals("1")) {
				tawwpMonthExecute.setQualityCheckUser(_checkUser);
			}
			tawwpMonthExecute.setQualityCheckUser(_checkUser);
			// 如果执行类型为班次,则还需要相应修改执行机房和执行班次
			if ("3".equals(_executerType)) {
				tawwpMonthExecute.setExecuteRoom(_executeRoom);
				tawwpMonthExecute.setExecuteDuty(_executeDuty);
			}

			// 修改月度作业计划执行内容的执行人及相关信息
			tawwpMonthExecuteDao.updateMonthExecute(tawwpMonthExecute);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容执行人修改保存出现异常");
		}
	}

	/**
	 * 业务逻辑：为每个执行项添加任务id
	 * 
	 * @param _monthExecuteId
	 *            String 月度作业计划执行内容编号
	 * @param _taskid
	 *            String 执行人
	 * 
	 * @throws Exception
	 *             异常
	 */
	public void editMonthExecuteSave(String _monthExecuteId, String _taskid)
			throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;

		try {
			// 获取月度作业计划执行内容对象
			tawwpMonthExecute = tawwpMonthExecuteDao
					.loadMonthExecute(_monthExecuteId);
			String[] command = _taskid.split("#");
			if (command.length == 2) {
				tawwpMonthExecute.setCommand(command[0]);
				tawwpMonthExecute.setCommandName(command[1]);
			} else {
				tawwpMonthExecute.setCommand("");
				tawwpMonthExecute.setCommandName("");
			}
			tawwpMonthExecuteDao.updateMonthExecute(tawwpMonthExecute);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容任务id修改保存出现异常");
		}
	}

	/**
	 * 业务逻辑：修改月度作业计划执行内容_保存(EDIT-EXECUTION-002)
	 * 
	 * @param _monthExecuteId
	 *            String 月度作业计划执行内容编号
	 * @param _executer
	 *            String 执行人
	 * @param _executerType
	 *            String 执行人类型(0：表示人员，1：表示部门，2：表示岗位 3：表示班次)
	 * @param _executeRoom
	 *            String 执行机房
	 * @param _executeDuty
	 *            String 执行班次
	 * @param _executeDate
	 *            String 执行日期
	 * @throws Exception
	 *             异常
	 */
	public void editMonthExecuteSave(String _monthExecuteId, String _executer,
			String _executerType, String _executeRoom, String _executeDuty,
			String _executeDate, String fileFlag) throws Exception {

		// 初始化数据,创建DAO对象
		/*
		 * TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		 * TawwpMonthExecuteDAO();
		 */
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;

		try {
			// 获取月度作业计划执行内容对象
			tawwpMonthExecute = tawwpMonthExecuteDao
					.loadMonthExecute(_monthExecuteId);

			// 封装月度作业计划执行内容对象
			tawwpMonthExecute.setExecuter(_executer);
			tawwpMonthExecute.setExecuterType(_executerType);
			tawwpMonthExecute.setExecuteDate(_executeDate);
			tawwpMonthExecute.setFileFlag(fileFlag);

			// 如果执行类型为班次,则还需要相应修改执行机房和执行班次
			if ("3".equals(_executerType)) {
				tawwpMonthExecute.setExecuteRoom(_executeRoom);
				tawwpMonthExecute.setExecuteDuty(_executeDuty);
			}

			// 修改月度作业计划执行内容的执行人及相关信息
			tawwpMonthExecuteDao.updateMonthExecute(tawwpMonthExecute);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容执行人修改保存出现异常");
		}
	}

	/**
	 * 业务逻辑：修改月度作业计划执行内容网元命令(EDIT-COMMAND-001)
	 * 
	 * @param _monthExecuteId
	 *            String 月度作业计划执行内容编号
	 * @param _taskId
	 *            String 任务id
	 * @param _taskName
	 *            String 任务名称
	 * @throws Exception
	 *             异常
	 */

	public void editCommand(String _monthExecuteId, String _taskId,
			String _taskName) throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpMonthExecuteDAO tawwpMonthExecuteDAO = new
		// TawwpMonthExecuteDAO();
		// 月度作业计划执行内容对象
		TawwpMonthExecute tawwpMonthExecute = null;

		try {
			// 获取月度作业计划执行内容对象
			tawwpMonthExecute = tawwpMonthExecuteDao
					.loadMonthExecute(_monthExecuteId);

			// 封装月度作业计划执行内容对象
			tawwpMonthExecute.setCommand(_taskId);
			tawwpMonthExecute.setCommandName(_taskName);

			// 修改月度作业计划执行内容的网元命令信息
			tawwpMonthExecuteDao.updateMonthExecute(tawwpMonthExecute);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划执行内容网元命令修改出现异常");
		}
	}

	/**
	 * 月度作业计划导出
	 * 
	 * @param _monthId
	 *            String
	 * @return String
	 */
	public String exportMonthToExcel(String _monthId) {
		String exportExcelUrl = "";
		String exportExcel = "";
		int x = 0;
		int y = 0;
		int tempLength = 0;
		String wwwDir = TawwpStaticVariable.wwwDir; // web目录
		String newDir = TawwpUtil.getCurrentDateTime("yyyy_MM_dd");

		try {
			// 创建文件夹
			TawwpUtil.mkDir(wwwDir
					+ "workplan/tawwpfile/tempfiledownload/month/" + newDir);
			// 生成的Excel地址
			exportExcelUrl = "/workplan/tawwpfile/tempfiledownload/month/"
					+ newDir + "/" + TawwpUtil.getCurrentDateTime("hhmmss")
					+ _monthId + ".xls";

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

			// cellStyle.s
			// 写信息－start

			TawwpMonthPlanVO tawwpMonthPlanVO = null;
			TawwpMonthExecuteVO tawwpMonthExecuteVO = null;

			try {
				// 浏览月度作业计划
				tawwpMonthPlanVO = viewMonthPlan(_monthId);

				// 如果作业计划模版信息不为空
				if (tawwpMonthPlanVO != null) {

					List monthExecuteVOList = tawwpMonthPlanVO
							.getMonthExecuteVOList();

					/**
					 * ********************配置常量单元格信息
					 * start************************
					 */
					// 常量值
					String[] valueOfLine = {
							"" + tawwpMonthPlanVO.getName() + "",
							"制表日期：",
							"" + TawwpUtil.getCurrentDateTime("yyyy-MM-dd")
									+ "", "本月执行计划", "内　容", "周期" };
					// 对应的行
					int[] rowOfLine = { 0, 1, 1, 1, 2, 2 };
					// 对应的列
					int[] colOfLine = { 0, 0, 1, 2, 0, 1 };
					// 合并的行、列
					int[][] xyOfLine = { { 1, 33 }, { 1, 1 }, { 1, 1 },
							{ 1, 31 }, { 1, 1 }, { 1, 1 } };
					/** ********************配置常量单元格信息 end************************ */
					wb
							.setSheetName(0, "月度计划执行内容",
									HSSFWorkbook.ENCODING_UTF_16);
					// 每列最大的设置该列的宽度
					for (int i = 0; i < valueOfLine.length; i++) {
						tempLength = 0;
						for (int c = 0; c < 3; c++) {
							if (c == rowOfLine[i]
									&& ((valueOfLine[i].length()) * (2 * 256)) > tempLength) {
								tempLength = (valueOfLine[c].length())
										* (2 * 256);

							}
						}
						sheet.setColumnWidth((short) (colOfLine[i]),
								(short) tempLength);
					}

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
						cell.setCellStyle(cellStyle);
					}
					// 定义常量单元格
					for (int i = 1; i < 32; i++) {
						row = sheet.createRow((short) 2); // 建立新行
						cell = row.createCell((short) (i + 1)); // 建立新cell
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(i);
						sheet
								.setColumnWidth((short) (i + 1),
										(short) (3 * 256));
						cell.setCellStyle(cellStyle);
					}

					y = 3;
					// 循环处理执行内容
					for (int i = 0; i < monthExecuteVOList.size(); i++) {

						tawwpMonthExecuteVO = (TawwpMonthExecuteVO) (monthExecuteVOList
								.get(i));

						// 新的行
						row = sheet.createRow((short) y);

						cell = row.createCell((short) 0);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(tawwpMonthExecuteVO.getName());

						cell = row.createCell((short) 1);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(tawwpMonthExecuteVO.getCycleName());
						cell.setCellStyle(cellStyle);

						char[] temp = (tawwpMonthExecuteVO.getExecuteDate())
								.toCharArray();
						for (int k = 0; k < temp.length; k++) {
							if (temp[k] == '1') {
								cell = row.createCell((short) (k + 2));
								cell.setEncoding(HSSFCell.ENCODING_UTF_16);
								cell.setCellStyle(cellStyle);
								cell.setCellValue("√");

							}
						}
						y++;
					}
				} else {
					throw new TawwpException("月度作业计划查询出现异常");
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
			return null;
		}
	}

	/**
	 * 提交总部月度作业计划并生成文件
	 * 
	 * @param _monthId
	 *            String add by scropioD
	 * 
	 * public String monthReport(String _monthId) {
	 * 
	 * TawwpMonthExecuteVO tawwpMonthExecuteVO = null; TawwpMonthPlan
	 * tawwpMonthPlan = null; TawwpNet tawwpNet = null; TawwpMonthPlanDAO
	 * tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
	 * 
	 * String wwwDir = TawwpStaticVariable.wwwDir; //从静态变量读取目录 String monthPath =
	 * wwwDir + TawwpStaticVariable.monthPath; //生成月度excel的目录 String modelPath =
	 * wwwDir + TawwpStaticVariable.modelPath; //源文件的目录 String reportFlag = "1";
	 * String fileName = ""; String yearmonth = ""; int x = 0; int y = 0;
	 * 
	 * try {
	 * 
	 * tawwpMonthPlan = tawwpMonthPlanDAO.loadMonthPlan(_monthId);
	 * 
	 * if (tawwpMonthPlan != null) { //生成月度execl文件名
	 * 
	 * String specialName = tawwpMonthPlan.getUnicomType(); String
	 * originExcelName = "MONTH-" + specialName + ".xls"; //年度作业计划员模板的文件名
	 * yearmonth = tawwpMonthPlan.getYearFlag() + tawwpMonthPlan.getMonthFlag();
	 * String NetSerial = "";
	 * 
	 * try { tawwpNet = tawwpMonthPlan.getTawwpNet(); NetSerial =
	 * tawwpNet.getSerialNo(); } catch (Exception ex) { ex.printStackTrace();
	 * throw new TawwpException("作业计划没有对应网元信息"); }
	 * 
	 * //拼出excel名 fileName = "RS-" + StaticMethod.getNodeName("STRREGIONCODE") +
	 * "-" + yearmonth + "01-M-" + specialName + "-002-" + NetSerial + ".xls";
	 * 
	 * //读取源文件 jxl.Workbook rw = jxl.Workbook.getWorkbook(new
	 * java.io.File(modelPath + originExcelName)); //创建要写出的文件
	 * jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new java.io.
	 * File(monthPath + fileName), rw); //获取sheet对象 jxl.write.WritableSheet ws =
	 * wwb.getSheet(0);
	 * 
	 * TawwpMonthPlanVO tawwpMonthPlanVO = viewMonthPlan(_monthId); List
	 * monthExecuteVOList = tawwpMonthPlanVO.getMonthExecuteVOList(); for (int i =
	 * 0; i < monthExecuteVOList.size(); i++) { tawwpMonthExecuteVO =
	 * (TawwpMonthExecuteVO) (monthExecuteVOList.get(i)); char[] temp =
	 * (tawwpMonthExecuteVO.getExecuteDate()).toCharArray(); x =
	 * StaticMethod.null2int(tawwpMonthExecuteVO.getXlsX()); for (int k = 0; k <
	 * temp.length; k++) { y =
	 * StaticMethod.null2int(tawwpMonthExecuteVO.getXlsY()) + k; if (temp[k] ==
	 * '1') { jxl.write.WritableCell wc = ws.getWritableCell(y, x); //获取可写入的单元格
	 * if (wc.getType() == CellType.EMPTY) { jxl.write.Label labelC = new
	 * jxl.write.Label(y, x, "√"); ws.addCell(labelC); } else if (wc.getType() ==
	 * CellType.LABEL) { jxl.write.Label wL = (jxl.write.Label) wc;
	 * wL.setString("√"); } } } }
	 * 
	 * //写入文件 wwb.write(); //关闭处理 rw.close(); wwb.close(); //调用接口程序并改变提交总部状态
	 * boolean submitstate = reportExecutePortReport(fileName); if
	 * (!submitstate) { reportFlag = "2"; }
	 * tawwpMonthPlan.setReportFlag(reportFlag);
	 * tawwpMonthPlanDAO.update(tawwpMonthPlan); } else { throw new
	 * TawwpException("月度作业计划查询出现异常"); } } catch (Exception e) {
	 * e.printStackTrace(); } return yearmonth; }
	 */

	/**
	 * 调用总部作业计划的接口
	 * 
	 * @param attachInfoListType
	 *            AttachInfoListType
	 * @throws Exception
	 * @return boolean
	 * 
	 * public boolean reportExecutePortReport(String fileName) throws Exception {
	 * com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
	 * boolean flag = false; AttachInfoType attachInfoType = new
	 * AttachInfoType(); AttachInfoListType attachInfoListType = new
	 * AttachInfoListType(); try { //数据封装 File file = new
	 * File(TawwpStaticVariable.yearPath + fileName);
	 * attachInfoType.setAttachLength( (int) (file.length()));
	 * attachInfoType.setAttachName(fileName);
	 * attachInfoType.setAttachURL(TawwpStaticVariable.serverIp +
	 * TawwpStaticVariable.monthPath + fileName); AttachInfoType[]
	 * attachInfoTypeArray = new AttachInfoType[1]; attachInfoTypeArray[0] =
	 * attachInfoType; attachInfoListType.setAttachInfo(attachInfoTypeArray);
	 * 
	 * binding = (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub)
	 * new com.boco.eoms.gzjhhead.interfaces.ReportExecutePortLocator().
	 * getReportExecutePort(); } catch (javax.xml.rpc.ServiceException jre) { if
	 * (jre.getLinkedCause() != null) { jre.getLinkedCause().printStackTrace(); }
	 * throw new junit.framework.AssertionFailedError( "JAX-RPC ServiceException
	 * caught: " + jre); } binding.setTimeout(60000); try {
	 * com.boco.eoms.gzjhhead.interfaces.ReportFormResponse value = null;
	 * ReportFormRequest reportFormRequest = new ReportFormRequest();
	 * ReportTypeType reportTypeType = new ReportTypeType(1);
	 * reportTypeType.setValue(0);
	 * reportFormRequest.setCodeA(StaticMethod.getNodeName("STRREGIONCODE"));
	 * reportFormRequest.setCodeB("ZB"); reportFormRequest.setAttNum(1);
	 * reportFormRequest.setAttachInfoList(attachInfoListType);
	 * reportFormRequest.setNoteAssign("");
	 * reportFormRequest.setReportType(reportTypeType); value =
	 * binding.reportForm(reportFormRequest); if (value.getResultReportForm() ==
	 * null) { return true; } } catch
	 * (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) { throw new
	 * junit.framework.AssertionFailedError( "ReportFormFault Exception caught: " +
	 * e1); } return flag; }
	 */

	/**
	 * added by lijia 2005-11-28 业务逻辑：过滤掉已经制定过月度作业计划的网元
	 * 
	 * @param _netVOList
	 *            List 待选网元范围
	 * @param _yearPlanId
	 *            String 年度作业计划编号
	 * @param _monthFlag
	 *            String 计划月度
	 * @throws Exception
	 *             异常
	 * @return List 过滤后的网元集合
	 */

	public HashMap netFilter(List _netVOList, String _yearPlanId,
			String _monthFlag) throws Exception {

		// 初始化数据
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();

		List monthPlanList = null;
		List newNetList = _netVOList;
		TawwpYearPlan tawwpYearPlan = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpNetVO tawwpNetVO = null;
		HashMap map = new HashMap();
		String isNull = "0";
		try {

			tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId);

			// 获取月度作业计划对象集合
			monthPlanList = tawwpMonthPlanDao
					.filterTawwpMonthByDept(
							TawwpStaticVariable.MONTH[StaticMethod
									.null2int(_monthFlag)], tawwpYearPlan,
							tawwpYearPlan.getDeptId());

			for (int j = 0; j < monthPlanList.size(); j++) {
				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(j);

				for (int i = 0; i < newNetList.size(); i++) {
					tawwpNetVO = (TawwpNetVO) newNetList.get(i);
					// 判断该网元月度计划是否已经制定
					if (tawwpMonthPlan.getTawwpNet() == null) {
						isNull = "1";
						break;
					} else if (tawwpNetVO.getId().equals(
							tawwpMonthPlan.getTawwpNet().getId())) {
						newNetList.remove(i);
						break;
					}
				}

			}
			map.put("isNull", isNull);
			map.put("result", newNetList);
			// 返回过滤后的网元集合
			return map;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("网元过滤出现异常");
		}
	}

	/**
	 * added by lijia 2005-11-28 私有方法：判断网元是否有遗漏
	 * 
	 * @param _tawwpYearPlan
	 *            TawwpYearPlan 年度作业计划对象
	 * @param _monthFlag
	 *            String 计划月度
	 * @throws Exception
	 *             异常
	 * @return boolean 遗漏标志
	 */

	private boolean netFilter(TawwpYearPlan _tawwpYearPlan, String _monthFlag)
			throws Exception {

		// 初始化数据
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();

		List monthPlanList = null;
		List netList = new ArrayList();

		TawwpYearPlan tawwpYearPlan = _tawwpYearPlan;
		TawwpMonthPlan tawwpMonthPlan = null;

		String netStr = "";

		boolean flag = true;

		String[] tempStr = {};

		String tempNetList;

		try {

			// 获取月度作业计划对象集合
			monthPlanList = tawwpMonthPlanDao
					.filterTawwpMonthByDept(
							TawwpStaticVariable.MONTH[StaticMethod
									.null2int(_monthFlag)], tawwpYearPlan,
							tawwpYearPlan.getDeptId());

			// 将年度计划中的备选网元编号添加到集合中
			// modify by scropioD
			tempNetList = tawwpYearPlan.getNetList();
			if (tempNetList != null) {
				tempStr = tempNetList.split(",");
				// end by scropioD
			}
			for (int k = 0; k < tempStr.length; k++) {
				netList.add(tempStr[k]);
			}

			for (int j = 0; j < monthPlanList.size(); j++) {
				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(j);

				for (int i = 0; i < netList.size(); i++) {
					netStr = (String) netList.get(i);
					// 判断该网元月度计划是否已经制定
					if (netStr != null && !"".equals(netStr)) {
						if (tawwpMonthPlan.getTawwpNet() != null
								&& netStr.equals(tawwpMonthPlan.getTawwpNet()
										.getId())) {
							netList.remove(i);
							break;
						}
					}
				}

			}
			// 如果还有未制定相应月度作业计划的网元
			if (netList.size() > 0) {
				flag = false;
			}

			// 返回缺漏标志
			return flag;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("网元缺漏判断出现异常");
		}
	}

	/**
	 * added by lijia 2005-11-28 业务逻辑:为年度作业计划添加缺漏网元标记
	 * 
	 * @param _deptId
	 *            String 部门编号
	 * @param _yearFlag
	 *            String 计划年度
	 * @param _monthFlag
	 *            String 计划月度
	 * @throws Exception
	 *             异常
	 * @return monthPlanVOHash 封装月度计划信息的Hashtable
	 */
	public Hashtable markYearPlan(String _deptId, String _yearFlag,
			String _monthFlag) throws Exception {

		// 初始化数据
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();

		// model对象
		TawwpYearPlan tawwpYearPlan = null;

		// 集合对象
		Hashtable yearPlanVOHash = new Hashtable();
		List yearPlanList = new ArrayList();

		boolean tempStr = true;

		try {

			// 获取年度作业计划集合
			yearPlanList = tawwpYearPlanDao.listYearPlanByDept(_deptId,
					_yearFlag);

			// 将年度作业计划对象作为key值put到Hashtable中
			for (int k = 0; k < yearPlanList.size(); k++) {
				tawwpYearPlan = (TawwpYearPlan) (yearPlanList.get(k));
				tempStr = this.netFilter(tawwpYearPlan, _monthFlag);
				if (tempStr) {
					yearPlanVOHash.put(tawwpYearPlan, "n");
				} else {
					yearPlanVOHash.put(tawwpYearPlan, "y");
				}
			}

			// 返回封装年度作业计划信息的Hashtable
			return yearPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("年度作业计划添加缺漏标记出现异常");
		}
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
	 * @param tawwpMonthPlanDao
	 *            the tawwpMonthPlanDao to set
	 */
	public void setTawwpMonthPlanDao(ITawwpMonthPlanDao tawwpMonthPlanDao) {
		this.tawwpMonthPlanDao = tawwpMonthPlanDao;
	}

	/**
	 * @param tawwpNetDao
	 *            the tawwpNetDao to set
	 */
	public void setTawwpNetDao(ITawwpNetDao tawwpNetDao) {
		this.tawwpNetDao = tawwpNetDao;
	}

	/**
	 * @param tawwpYearPlanDao
	 *            the tawwpYearPlanDao to set
	 */
	public void setTawwpYearPlanDao(ITawwpYearPlanDao tawwpYearPlanDao) {
		this.tawwpYearPlanDao = tawwpYearPlanDao;
	}

	/**
	 * @param tawwpYearExecuteDao
	 *            the tawwpYearExecuteDao to set
	 */
	public void setTawwpYearExecuteDao(ITawwpYearExecuteDao tawwpYearExecuteDao) {
		this.tawwpYearExecuteDao = tawwpYearExecuteDao;
	}

	/**
	 * @param tawwpExecuteContentDao
	 *            the tawwpExecuteContentDao to set
	 */
	public void setTawwpExecuteContentDao(
			ITawwpExecuteContentDao tawwpExecuteContentDao) {
		this.tawwpExecuteContentDao = tawwpExecuteContentDao;
	}

	// ext
	/**
	 * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
	 * 
	 * @param _monthCheckIdStr
	 *            String 审批信息编号字符串
	 * @param _content
	 *            String 审批信息
	 * @param _currentCheckUser
	 *            String 审批人
	 * @throws Exception
	 *             异常
	 */
	public void passMonthCheck(String _monthCheckIdStr, String _content,
			String _currentCheckUser) throws Exception {

		// 初始化数据1
		// TawwpMonthCheckDAO tawwpMonthCheckDAO = new TawwpMonthCheckDAO();
		// TawwpMonthExecuteUserDAO tawwpMonthExecuteUserDAO = new
		// TawwpMonthExecuteUserDAO();
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();

		// 集合对象
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecutecontents = null;
		Iterator tawwpExecutecontentUsers = null;
		TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
		TawwpMonthExecute tawwpMonthExecute = new TawwpMonthExecute();

		// 执行人字符串
		String executeUserStr = "";
		// 创建执行人信息字符串
		String executerInfo = "";
		// 执行日期字符串
		String executeDate = "";
		// 日期格式基础字符串
		String baseDate = "";
		String startHH = "";
		String endHH = "";
		String startMM = "";
		String endMM = "";

		// 月度作业计划审批信息对象
		TawwpMonthCheck tawwpMonthCheck = new TawwpMonthCheck();
		// 月度作业计划执行内容执行人信息对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = new TawwpMonthExecuteUser();

		String j = "";

		try {

			// 处理审批记录编号字符串
			String[] tempStrArray = _monthCheckIdStr.split(",");

			for (int k = 0; k < tempStrArray.length; k++) {

				// 获取月度作业计划审批信息对象
				tawwpMonthCheck = tawwpMonthCheckDao
						.loadMonthCheck(tempStrArray[k]);

				// 封装月度作业计划审批信息对象
				tawwpMonthCheck.setContent(_content);
				tawwpMonthCheck.setState("1");
				tawwpMonthCheck.setCheckUser(_currentCheckUser);
				tawwpMonthCheck.setChecktime(TawwpUtil.getCurrentDateTime());
				String type = StaticMethod.null2String(tawwpMonthCheck
						.getType());
				// 修改月度作业计划审批信息(通过)
				tawwpMonthCheckDao.updateMonthCheck(tawwpMonthCheck);

				// 获取月度作业计划
				tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();
				// 将月度作业计划执行状态改为"执行中"
				tawwpMonthPlan.setExecuteState("4");
				// 制定状态改为“通过”
				tawwpMonthPlan.setConstituteState("1");
				// 如果是删除类型的审批通过，则将该作业计划的删除标志位置为“1”
				if (type.equals("1")) {
					tawwpMonthPlan.setDeleted("1");
				}
				// 更新月度作业计划执行状态
				tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

				// 获取月度作业计划执行内容集合
				tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
						.iterator();

				if (type.equals("1")) {
					tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();
					while (tawwpMonthExecutes.hasNext()) {
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());
						tawwpExecutecontents = tawwpMonthExecute
								.getTawwpExecuteContents().iterator();
						while (tawwpExecutecontents.hasNext()){
							TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecutecontents.next());
							tawwpExecutecontentUsers = tawwpExecuteContent
									.getTawwpExecuteContentUsers().iterator();
							while (tawwpExecutecontentUsers.hasNext()) {
								TawwpExecuteContentUser tawwpExecuteContentUser = (TawwpExecuteContentUser) (tawwpExecutecontentUsers
										.next());
								tawwpExecutecontentUsers.remove();
								tawwpExecuteContent.getTawwpExecuteContentUsers().remove(
										tawwpExecuteContentUser);
								tawwpExecuteContentUser.setTawwpExecuteContent(null);
								tawwpExecuteContentUserDao.deleteExecuteContentUser(tawwpExecuteContentUser);
							}
							// 下面的几行代码是解除model中的关联关系
							tawwpExecutecontents.remove();
							tawwpMonthExecute.getTawwpExecuteContents().remove(
									tawwpExecuteContent);
							tawwpExecuteContent.setTawwpMonthExecute(null);
							tawwpExecuteContentDao
									.deleteExecuteContent(tawwpExecuteContent);
						}
						tawwpMonthExecute.setDeleted("1");
						tawwpMonthExecuteDao
								.updateMonthExecute(tawwpMonthExecute);
					}
					List deleteMonthPlan = tawwpMonthExecuteUserDao
							.getMonthExecuteUser(tawwpMonthPlan.getId());
					for (int m = 0; m < deleteMonthPlan.size(); m++) {
						TawwpMonthExecuteUser tawwpMonthExecuteUserDel = (TawwpMonthExecuteUser) deleteMonthPlan
								.get(m);
						tawwpMonthExecuteUserDao
								.deleteMonthExecuteUser(tawwpMonthExecuteUserDel);
					}
				} else {// 正常的增加操作
					// -----------------------------------------------------------------------
					/*
					 * 如果步骤对象为空，整体流程结束 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 * 2.将执行人信息写入执行人表中
					 * 3.添加默认执行内容(整体)信息对象TawwpExecuteContent到数据库中
					 */

					/*--------------------------------------------------
					 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 */

					while (tawwpMonthExecutes.hasNext()) {
						// 获取月度作业计划执行内容
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());

						// 如果是删除类型的审批通过，则将该月度作业计划下属的执行项(未执行的)的删除标志位置为“1”
						if (type.equals("1")) {
							if (tawwpMonthExecute.getExecuteFlag().equals("0")) {
								tawwpExecutecontents = tawwpMonthExecute
										.getTawwpExecuteContents().iterator();
								while (tawwpMonthExecutes.hasNext()) {
									TawwpExecuteContent tawwpExecutecontent = (TawwpExecuteContent) (tawwpExecutecontents
											.next());
									if (tawwpExecutecontent.getExecuteFlag()
											.equals("0")) {
										tawwpExecuteContentDao
												.deleteExecuteContent(tawwpExecutecontent);
									}
								}
								tawwpMonthExecuteDao
										.deleteMonthExecute(tawwpMonthExecute);
							}
						} else {
							/*--------------------------------------------------
							 2.将执行人信息写入执行人表中
							 */

							// 获取执行人字符串
							executeUserStr = tawwpMonthExecute.getExecuter();

							if (executeUserStr != null
									&& !"".equals(executeUserStr)) {
								// 处理执行人字符串
								String[] temp = executeUserStr.split(",");
								for (int i = 0; i < temp.length; i++) {

									/*
									 * 保证用户名(或部门、岗位编号)、执行人类型、 月度作业计划的唯一对应性的算法
									 */
									// 组合临时字符串,如"用户名,执行人类型"
									String tempStr = temp[i]
											+ ","
											+ tawwpMonthExecute
													.getExecuterType();
									// 如果执行人信息字符串不包含以上信息,就创建执行人对象,并更新执行人信息字符串
									if (executerInfo.indexOf(tempStr) == -1) {
										executerInfo += (tempStr + "#");
										tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
										// 封装月度作业计划执行内容执行人对象
										tawwpMonthExecuteUser
												.setExecuter(temp[i]);
										tawwpMonthExecuteUser
												.setExecuterType(tawwpMonthExecute
														.getExecuterType());
										tawwpMonthExecuteUser
												.setExecuteRoom(tawwpMonthExecute
														.getExecuteRoom());
										tawwpMonthExecuteUser
												.setExecuteDuty(tawwpMonthExecute
														.getExecuteDuty());
										tawwpMonthExecuteUser.setContent("");
										tawwpMonthExecuteUser.setState("0");
										tawwpMonthExecuteUser
												.setYearFlag(tawwpMonthPlan
														.getYearFlag());
										tawwpMonthExecuteUser
												.setMonthFlag(tawwpMonthPlan
														.getMonthFlag());
										tawwpMonthExecuteUser
												.setTawwpMonthPlan(tawwpMonthPlan);
										// 保存月度作业计划执行内容执行人信息
										tawwpMonthExecuteUserDao
												.saveMonthExecuteUser(tawwpMonthExecuteUser);
									}
								}
							}
							// add by gongyufeng
							executerInfo = "";
							/*---------------------------------------------------
							 3.添加默认执行内容(整体)信息对象(TawwpExecuteContent)
							 */

							executeDate = tawwpMonthExecute.getExecuteDate();
							// 如果是删除类型的审批通过，则将该月度作业计划下属的执行项的删除标志位置为“1”
							String[] tempSt = StaticMethod.null2String(tawwpMonthExecute.getStartHH()).split(":");
							startHH = tempSt[0];
							if(tempSt.length>1){
								startMM = tempSt[1];
							}else{
								startMM = "00";
							}
							if (StaticMethod.null2int(startHH) > 0
									&& StaticMethod.null2int(startHH) < 10) {
								startHH = "0" + StaticMethod.null2int(startHH);
							}
							if (StaticMethod.null2int(startMM) > 0
									&& StaticMethod.null2int(startMM) < 10) {
								startMM = "0" + StaticMethod.null2int(startMM);
							}
							String[] tempEt = StaticMethod.null2String(tawwpMonthExecute.getEndHH()).split(":");
							endHH = tempEt[0];
							if(tempEt.length>1){
								endMM = tempEt[1];
							}else{
								endMM = "59";
							}
							if (StaticMethod.null2int(endHH) > 0
									&& StaticMethod.null2int(endHH) < 10) {
								endHH = "0" + StaticMethod.null2int(endHH);
							}
							if (StaticMethod.null2int(endMM) > 0
									&& StaticMethod.null2int(endMM) < 10) {
								endMM = "0" + StaticMethod.null2int(endMM);
							}
							// 转化成字符数组
							char[] tempArr = executeDate.toCharArray();
							// 组合基础字符串如"2005-09-"
							baseDate = tawwpMonthPlan.getYearFlag() + "-"
									+ tawwpMonthPlan.getMonthFlag() + "-";
							for (int i = 1; i < (tempArr.length + 1); i++) {
								// 如果该日需要执行
								if ('1' == tempArr[i - 1]) {

									// 字符串处理不足两位补零
									j = String.valueOf(i);
									if (i < 10) {
										j = "0" + j;
									}

									// 封装执行内容(整体)对象
									TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent(
											tawwpMonthExecute.getName(),
											tawwpMonthExecute.getBotype(),
											tawwpMonthExecute
													.getExecutedeptlevel(),
											tawwpMonthExecute.getAppdesc(),
											baseDate + j + " " + startHH + ":" + startMM
													+ ":00",
											baseDate + j + " " + endHH + ":" + endMM
													+ ":59",
											TawwpUtil.getCurrentDateTime(),
											"",
											tawwpMonthExecute.getFormat(),
											StaticMethod.null2String(tawwpMonthExecute.getNote()),
											tawwpMonthExecute.getCycle(),
											tawwpMonthExecute.getFormId(),
											"",
											tawwpMonthPlan.getDeptId(),
											"",
											"0",
											"0",
											"",
											tawwpMonthExecute.getCommand(), // edit
											// by
											// gongyufeng
											// add
											// 智能巡检
											tawwpMonthExecute.getCommandName(),
											tawwpMonthExecute.getExecuter(),
											tawwpMonthExecute.getExecuterType(),
											tawwpMonthExecute.getExecuteRoom(),
											tawwpMonthExecute.getExecuteDuty(),
											tawwpMonthPlan.getExecuteType(),
											tawwpMonthExecute.getFileFlag(),
											tawwpMonthExecute
													.getQualityCheckUser(),
											tawwpMonthExecute, tawwpMonthPlan,"");
									tawwpExecuteContent.setNormalFlag("1"); // 默认填写为1
									tawwpExecuteContent.setExtendremark(tawwpMonthExecute.getRemark());
									tawwpExecuteContent.setAccessories(tawwpMonthExecute.getAccessories());
									// 保存执行内容(整体)对象
									tawwpExecuteContentDao
											.saveExecuteContent(tawwpExecuteContent);
								}
							}
						}
					}
					try {
						// 发送短信信息给创建人
						TawwpUtil.sendSMS(tawwpMonthPlan.getCruser(), "月度作业计划《"
								+ StaticMethod.null2String(tawwpMonthPlan
										.getName()) + "》通过", tawwpMonthPlan
								.getId());

						TawwpUtil.closeSMS(tawwpMonthPlan.getId());

						TawwpUtil.sendMail(tawwpMonthPlan.getCruser(),
								"月度作业计划《"
										+ StaticMethod
												.null2String(tawwpMonthPlan
														.getName()) + "》通过",
								tawwpMonthPlan.getId());
						TawwpUtil.closeEmail(tawwpMonthPlan.getId());
					} catch (Exception e) {
						System.out.print("消息发送异常" + e);
					}
				}
			}

			// ----------------------------------------------------------------------
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划审批通过出现异常");
		} finally {
			tawwpMonthExecutes = null;
		}
	}

	/**
	 * @param tawwpMonthExecuteUserDao
	 *            the tawwpMonthExecuteUserDao to set
	 */
	public void setTawwpMonthExecuteUserDao(
			ITawwpMonthExecuteUserDao tawwpMonthExecuteUserDao) {
		this.tawwpMonthExecuteUserDao = tawwpMonthExecuteUserDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.mgr.ITawwpMonthMgr#loadMonthPlan(java.lang.String)
	 */
	public TawwpMonthPlan loadMonthPlan(String id) {
		return tawwpMonthPlanDao.loadMonthPlan(id);
	}

	/**
	 * 
	 */
	public void updateMonthExecute(String executer, String executerType,
			String monthExecuteIds) {
		tawwpMonthExecuteDao.updateMonthExecute(executer, executerType,
				monthExecuteIds);

	}

	public List loadMonthExecute(String id) {
		return this.tawwpMonthExecuteDao.getMonthExecute(id);
	}

	public void updateMonthExecuteUser(String monthId) {
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpMonthPlan tawwpMonthPlan = tawwpMonthPlanDao
				.loadMonthPlan(monthId);

		this.tawwpMonthExecuteUserDao.deleteMonthExecuteUser(tawwpMonthPlan);

		Iterator tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
				.iterator();
		StringBuffer executer = new StringBuffer(",");
		String executerType = "";
		StringBuffer executeRoom = new StringBuffer(",");
		StringBuffer executeDept = new StringBuffer(",");
		String temp = "";
		while (tawwpMonthExecutes.hasNext()) {
			tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes.next());
			executerType = tawwpMonthExecute.getExecuterType();
			temp = tawwpMonthExecute.getExecuter();
			if (executerType.equals("3")) {
				String executers[] = temp.split(",");
				for (int i = 0; i < executers.length; i++) {
					if (executeRoom.indexOf("," + executers[i] + ",") == -1) {
						executeRoom.append(executers[i] + ",");
						TawwpMonthExecuteUser _tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
						_tawwpMonthExecuteUser
								.setExecuterType(tawwpMonthExecute
										.getExecuterType());
						_tawwpMonthExecuteUser.setExecuteRoom(tawwpMonthExecute
								.getExecuteRoom());
						_tawwpMonthExecuteUser.setExecuteDuty(tawwpMonthExecute
								.getExecuteDuty());
						_tawwpMonthExecuteUser.setContent("");
						_tawwpMonthExecuteUser.setState("0");
						_tawwpMonthExecuteUser.setYearFlag(tawwpMonthPlan
								.getYearFlag());
						_tawwpMonthExecuteUser.setMonthFlag(tawwpMonthPlan
								.getMonthFlag());
						_tawwpMonthExecuteUser
								.setTawwpMonthPlan(tawwpMonthPlan);
						_tawwpMonthExecuteUser.setExecuter(executers[i]);
						this.tawwpMonthExecuteUserDao
								.saveMonthExecuteUser(_tawwpMonthExecuteUser);
					}
				}
			} else if (executerType.equals("1")) {
				String executers[] = temp.split(",");
				for (int i = 0; i < executers.length; i++) {
					if (executeDept.indexOf("," + executers[i] + ",") == -1) {
						executeDept.append(executers[i] + ",");
						TawwpMonthExecuteUser _tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
						_tawwpMonthExecuteUser
								.setExecuterType(tawwpMonthExecute
										.getExecuterType());
						_tawwpMonthExecuteUser.setExecuteRoom(tawwpMonthExecute
								.getExecuteRoom());
						_tawwpMonthExecuteUser.setExecuteDuty(tawwpMonthExecute
								.getExecuteDuty());
						_tawwpMonthExecuteUser.setContent("");
						_tawwpMonthExecuteUser.setState("0");
						_tawwpMonthExecuteUser.setYearFlag(tawwpMonthPlan
								.getYearFlag());
						_tawwpMonthExecuteUser.setMonthFlag(tawwpMonthPlan
								.getMonthFlag());
						_tawwpMonthExecuteUser
								.setTawwpMonthPlan(tawwpMonthPlan);
						_tawwpMonthExecuteUser.setExecuter(executers[i]);
						this.tawwpMonthExecuteUserDao
								.saveMonthExecuteUser(_tawwpMonthExecuteUser);
					}
				}
			} else if (executerType.equals("0")) {
				String executers[] = temp.split(",");
				for (int i = 0; i < executers.length; i++) {
					if (executer.indexOf("," + executers[i] + ",") == -1) {
						executer.append(executers[i] + ",");
						TawwpMonthExecuteUser _tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
						_tawwpMonthExecuteUser
								.setExecuterType(tawwpMonthExecute
										.getExecuterType());
						_tawwpMonthExecuteUser.setExecuteRoom(tawwpMonthExecute
								.getExecuteRoom());
						_tawwpMonthExecuteUser.setExecuteDuty(tawwpMonthExecute
								.getExecuteDuty());
						_tawwpMonthExecuteUser.setContent("");
						_tawwpMonthExecuteUser.setState("0");
						_tawwpMonthExecuteUser.setYearFlag(tawwpMonthPlan
								.getYearFlag());
						_tawwpMonthExecuteUser.setMonthFlag(tawwpMonthPlan
								.getMonthFlag());
						_tawwpMonthExecuteUser
								.setTawwpMonthPlan(tawwpMonthPlan);
						_tawwpMonthExecuteUser.setExecuter(executers[i]);
						this.tawwpMonthExecuteUserDao
								.saveMonthExecuteUser(_tawwpMonthExecuteUser);
					}
				}
			}
		}
	}

	public List getMonthExecuteUser(String monthplanid) {
		return tawwpMonthExecuteUserDao.getMonthExecuteUser(monthplanid);
	}

	public List getMonthExecuteUserAll(String monthplanid, int flag) {
		return tawwpMonthExecuteUserDao.getMonthExecuteUserAll(monthplanid,
				flag);
	}

	public List getMonthExecuteStat(final String monthplanid, String startDate,
			String endDate) {
		return tawwpMonthExecuteUserDao.getMonthExecuteStat(monthplanid,
				startDate, endDate);
	}
	public List listMonthPlan(String _yearFlag,
			String _monthFlag) throws Exception{
		// 集合对象
		return tawwpMonthPlanDao.listMonthPlan("",_yearFlag,
				TawwpStaticVariable.MONTH[StaticMethod
						.null2int(_monthFlag)]);
	}
	public List listMonthPlanOfChildDeptSpec(String _deptId, String _yearFlag,
			String _monthFlag, String _advice) {

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 集合对象
		List monthPlanList = null;
		List newList = new ArrayList();

		String deptIdStr = null;

		deptIdStr = tawwpUtilDAO.getChildDept(_deptId);
		try {

			// 获取年度作业计划集合
			monthPlanList = tawwpMonthPlanDao.listMonthPlanOfChildDeptSpec(
					deptIdStr, _yearFlag, _monthFlag, _advice);

			// 取出model对象,处理后封装成VO对象
			for (int i = 0; i < monthPlanList.size(); i++) {
				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(i);

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);

				// 获取执行类型名称
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(tawwpMonthPlanVO.getExecuteType()));
				// 获取部门名称
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 获取系统类型名称
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 获取网元类型名称
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 获取创建人姓名
				tawwpMonthPlanVO.setUserName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));
				// 获取制定状态名称
				tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
						.getConstituteStateName(tawwpMonthPlanVO
								.getConstituteState()));
				// 获取执行状态名称
				tawwpMonthPlanVO
						.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(tawwpMonthPlanVO
										.getExecuteState()));
				// 获取执行负责人姓名
				tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				tawwpMonthPlanVO.setNetName(tawwpMonthPlan.getTawwpNet()
						.getName());
				// 将VO对象添加进列表中
				newList.add(tawwpMonthPlanVO);
			}

			// 返回封装月度计划信息的Hashtable
			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			return newList;
		} finally {
			monthPlanList = null;
		}
	}

	public boolean PjudgeMonthExecute(String _monthOfPlan,
			String _monthOfExecute, String _cycle) {
		// 初始化数据,是否应该执行标志
		boolean flag = false;
		/*
		 * (0 其他, 1 天, 2 周, 3 半月 4 月, 5 季度, 6 半年, 7 年 8 两月, 9 四月)
		 */
		// 如果周期小于月则一定需要执行
		if (StaticMethod.null2int(_cycle) <= 4) {
			flag = true;
		} else {
			// 定义数组对应周期包括的月度数
			int[] tempArray = { 3, 6, 12, 2, 4 };
			// 算法
			/*
			 * 如: 计划月度为8,执行内容周期为季度(包含3个月),执行内容指定每季度第二个月执行,
			 * 则按如下算法(8-1)%3+1=2与每周期需要执行的月份序数吻合 注: %代表取余数
			 */
			int tempInt = ((StaticMethod.null2int(_monthOfPlan) - 1) % tempArray[Integer
					.parseInt(_cycle) - 5]) + 1;
			if (tempInt == StaticMethod.null2int(_monthOfExecute)) {
				flag = true;
			}
		}
		// 返回是否应该执行标志
		return flag;
	}

	public String crMonthReport(String _monthId) {

		TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpNet tawwpNet = null;

		String wwwDir = TawwpStaticVariable.wwwDir; // 从静态变量读取目录
		String monthPath = wwwDir + TawwpStaticVariable.monthPath; // 生成月度excel的目录
		String modelPath = wwwDir + TawwpStaticVariable.modelPath; // 源文件的目录
		String fileName = "";
		String yearmonth = "";
		int x = 0;
		int y = 0;
		String cycle = "";
		String cycleStr = "";

		try {

			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthId);

			if (tawwpMonthPlan != null) {
				// 生成月度execl文件名

				String specialName = tawwpMonthPlan.getUnicomType();
				String originExcelName = "MONTH-" + specialName + ".xls"; // 年度作业计划员模板的文件名
				yearmonth = tawwpMonthPlan.getYearFlag()
						+ tawwpMonthPlan.getMonthFlag();
				String NetSerial = "";

				try {
					tawwpNet = tawwpMonthPlan.getTawwpNet();

				} catch (Exception ex) {
					ex.printStackTrace();
					throw new TawwpException(tawwpMonthPlan.getId()
							+ "月作业计划没有对应网元信息");
				}
				if (tawwpNet != null) {
					NetSerial = tawwpNet.getSerialNo();
					String province = WorkplanMgrLocator.getAttributes()
							.getNewstrregioncode();
					// 拼出excel名
					fileName = "RS--" + province + "--" + yearmonth + "01--M--"
							+ specialName + "--002--" + NetSerial + ".xls";

					fileName = fileName.replaceAll(" ", "");

					// 读取源文件
					jxl.Workbook rw = jxl.Workbook
							.getWorkbook(new java.io.File(modelPath
									+ originExcelName));
					// 创建要写出的文件
					java.io.File dir = new java.io.File(monthPath + "/"
							+ specialName);
					if (!dir.exists()) {
						dir.mkdir();
					}
					jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(
							new java.io.File(monthPath + "/" + specialName
									+ "/" + fileName), rw);
					// 获取sheet对象
					jxl.write.WritableSheet ws = wwb.getSheet(0);

					TawwpMonthPlanVO tawwpMonthPlanVO = viewMonthPlan(_monthId);
					List monthExecuteVOList = tawwpMonthPlanVO
							.getMonthExecuteVOList();
					for (int i = 0; i < monthExecuteVOList.size(); i++) {
						tawwpMonthExecuteVO = (TawwpMonthExecuteVO) (monthExecuteVOList
								.get(i));
						char[] temp = (tawwpMonthExecuteVO.getExecuteDate())
								.toCharArray();
						x = StaticMethod
								.null2int(tawwpMonthExecuteVO.getXlsX());
						y = StaticMethod
								.null2int(tawwpMonthExecuteVO.getXlsY());
						cycle = tawwpMonthExecuteVO.getCycle();
						cycleStr = TawwpUtil.getCycleStr(Integer
								.parseInt(cycle));

						// WY专业自动填入周期
						if (specialName.equals("WY")) {
							jxl.write.WritableCell wc = ws.getWritableCell(
									y - 2, x); // 获取可写入的单元格
							if (wc.getType() == CellType.EMPTY) {
								jxl.write.Label labelC = new jxl.write.Label(
										y - 2, x, cycleStr);
								ws.addCell(labelC);
							} else if (wc.getType() == CellType.LABEL) {
								jxl.write.Label wL = (jxl.write.Label) wc;
								wL.setString(cycleStr);
							}
						}

						// 如果周期为 “按需”， 即cycle=0， 则不打勾
						if (Integer.parseInt(cycle) == 0)
							continue;

						for (int k = 0; k < temp.length; k++) {
							y = StaticMethod.null2int(tawwpMonthExecuteVO
									.getXlsY())
									+ k;
							if (temp[k] == '1') {
								jxl.write.WritableCell wc = ws.getWritableCell(
										y, x); // 获取可写入的单元格
								if (wc.getType() == CellType.EMPTY) {
									jxl.write.Label labelC = new jxl.write.Label(
											y, x, "√");
									ws.addCell(labelC);
								} else if (wc.getType() == CellType.LABEL) {
									jxl.write.Label wL = (jxl.write.Label) wc;
									wL.setString("√");
								}
							}
						}
					}

					// 写入文件
					wwb.write();
					// 关闭处理
					rw.close();
					wwb.close();
				}
			} else {
				throw new TawwpException("月度作业计划查询出现异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return yearmonth;
	}

	/**
	 * 业务逻辑：浏览月作业计划 （RECEIVE-STENCIL-001）
	 * 
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 月作业计划 的Hashtable结构，其中按系统类别、网元类型分级。
	 */
	public Hashtable listMonthPlan() throws TawwpException {

		Hashtable sysTypeHashtable = new Hashtable(); // 系统类别hashtable
		Hashtable netTypeHashtable = null; // 网元类型hashtable
		List tawwpMonthPlanVOList = null; // 作业计划模版列表
		List list = null;

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {
			list = tawwpMonthPlanDao.listMonthPlan(); // 获取所有作业计划模版的集合
			if (list != null) {
				// 循环对每个作业计划模版进行分类处理
				for (int i = 0; i < list.size(); i++) {
					tawwpMonthPlan = (TawwpMonthPlan) list.get(i); // 获取一个作业计划模版
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan); // 将作业计划模版信息导入到作业计划模版显示类中

					tawwpMonthPlanVO.setCrtime(tawwpMonthPlanVO.getCrtime()
							.substring(0, 10));
					// System.out.println("@@@@@"+tawwpMonthPlanVO.getSysTypeId());
					if (sysTypeHashtable.get(tawwpMonthPlanVO.getSysTypeId()) == null) {
						// 如果当前网元类型不存在hashtable对象
						netTypeHashtable = new Hashtable(); // 建立一个网元类型hashtable
						tawwpMonthPlanVOList = new ArrayList(); // 建立作业计划模版信息列表
						tawwpMonthPlanVOList.add(tawwpMonthPlanVO); // 将作业计划模版信息加入列表
						netTypeHashtable.put(tawwpMonthPlanVO.getNetTypeId(),
								tawwpMonthPlanVOList); // 将列表放入网元类型hashtable
						sysTypeHashtable.put(tawwpMonthPlanVO.getSysTypeId(),
								netTypeHashtable); // 将网元类型hashtable放入系统类型hashtable
					} else {
						// 如果当前网元类型存在hashtable对象
						netTypeHashtable = (Hashtable) sysTypeHashtable
								.get(tawwpMonthPlanVO.getSysTypeId()); // 获取一个网元类型hashtable
						tawwpMonthPlanVOList = (List) netTypeHashtable
								.get(tawwpMonthPlanVO.getNetTypeId()); // 获取作业计划模版信息列表

						// 如果网元类型列表不存在，则创建
						if (tawwpMonthPlanVOList == null) {
							tawwpMonthPlanVOList = new ArrayList(); // 建立作业计划模版信息列表
							netTypeHashtable.put(tawwpMonthPlanVO
									.getNetTypeId(), tawwpMonthPlanVOList);
						}

						tawwpMonthPlanVOList.add(tawwpMonthPlanVO); // 将作业计划模版信息加入列表
					}
				}
			}
			return sysTypeHashtable;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板信息查询出现异常");
		} finally {
			list = null;
		}
	}

	public Hashtable monthListCode() throws TawwpException {// 改成从年计划表里查询

		Hashtable sysTypeHashtable = new Hashtable(); // 系统类别hashtable
		Hashtable netTypeHashtable = null; // 网元类型hashtable
		List tawwpMonthPlanVOList = null; // 作业计划模版列表
		List list = null;

		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpYearPlan tawwpMonthPlan = null;
		TawwpYearPlanVO tawwpMonthPlanVO = null;

		try {
			list = tawwpMonthPlanDao.monthListCode(); // 获取所有作业计划模版的集合
			if (list != null) {
				// 循环对每个作业计划模版进行分类处理
				for (int i = 0; i < list.size(); i++) {
					tawwpMonthPlan = (TawwpYearPlan) list.get(i); // 获取一个作业计划模版
					tawwpMonthPlanVO = new TawwpYearPlanVO();
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan); // 将作业计划模版信息导入到作业计划模版显示类中

					tawwpMonthPlanVO.setCrtime(tawwpMonthPlanVO.getCrtime()
							.substring(0, 10));
					// System.out.println("@@@@@"+tawwpMonthPlanVO.getSysTypeId());
					if (sysTypeHashtable.get(tawwpMonthPlanVO.getSysTypeId()) == null) {
						// 如果当前网元类型不存在hashtable对象
						netTypeHashtable = new Hashtable(); // 建立一个网元类型hashtable
						tawwpMonthPlanVOList = new ArrayList(); // 建立作业计划模版信息列表
						tawwpMonthPlanVOList.add(tawwpMonthPlanVO); // 将作业计划模版信息加入列表
						netTypeHashtable.put(tawwpMonthPlanVO.getNetTypeId(),
								tawwpMonthPlanVOList); // 将列表放入网元类型hashtable
						sysTypeHashtable.put(tawwpMonthPlanVO.getSysTypeId(),
								netTypeHashtable); // 将网元类型hashtable放入系统类型hashtable
					} else {
						// 如果当前网元类型存在hashtable对象
						netTypeHashtable = (Hashtable) sysTypeHashtable
								.get(tawwpMonthPlanVO.getSysTypeId()); // 获取一个网元类型hashtable
						tawwpMonthPlanVOList = (List) netTypeHashtable
								.get(tawwpMonthPlanVO.getNetTypeId()); // 获取作业计划模版信息列表

						// 如果网元类型列表不存在，则创建
						if (tawwpMonthPlanVOList == null) {
							tawwpMonthPlanVOList = new ArrayList(); // 建立作业计划模版信息列表
							netTypeHashtable.put(tawwpMonthPlanVO
									.getNetTypeId(), tawwpMonthPlanVOList);
						}

						tawwpMonthPlanVOList.add(tawwpMonthPlanVO); // 将作业计划模版信息加入列表
					}
				}
			}
			return sysTypeHashtable;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板信息查询出现异常");
		} finally {
			list = null;
		}
	}

	/**
	 * add by gongyufeng 按月份将某年度作业计划中一年的作业计划导出到一个EXCEL文件中
	 * 
	 * @param year
	 * @return
	 */
	public String YearOrderByMonthUrl(String year) {

		List list = new ArrayList();
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		String url = "";
		try {
			list = tawwpMonthPlanDao.ListYearOrderByMonth(year);

			String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHH");
			String uploadPath = DutyMgrLocator.getAttributes()
					.getDutyRootPath()
					+ "/";
			String filePath = "/" + TawwpStaticVariable.wwwDir
					+ "/workplan/tawwpfile/model" + "/";

			TawwpUtil.mkDir(filePath);
			url = filePath + timeTag + ".xls";
			System.out.println("-------" + url + "----------------");
			/*
			 * String sysTemPaht =
			 * com.boco.eoms.base.util.StaticMethod.getWebPath();
			 * 
			 * url = sysTemPaht + "/" + uploadPath + timeTag + ".xls";
			 */
			// String path = sysTemPaht + url;
			// 建立输出流
			FileOutputStream fos = new FileOutputStream(url);
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
			int y = 0;

			/** ********************配置常量单元格信息 start************************ */
			// 常量值
			String[] valueOfLine = { "月度", "年度", "名称", "系统类型", "网元类型", "是否执行",
					"部门", "创建人", "创建时间", "年度名称", "模板名称", "网元名称" };
			// 对应的行
			int[] rowOfLine = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			// 对应的列
			int[] colOfLine = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
			// xy
			int[][] xyOfLine = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 },
					{ 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 },
					{ 1, 1 }, { 1, 1 } };

			/** ********************配置常量单元格信息 end************************ */

			wb.setSheetName(0, "作业计划", HSSFWorkbook.ENCODING_UTF_16);
			ITawSystemUserManager user = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserManager");
			ITawSystemDeptManager dept = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			ITawSystemDictTypeManager dict = (ITawSystemDictTypeManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDictTypeManager");
			// 定义常量单元格
			for (int i = 0; i < valueOfLine.length; i++) {
				row = sheet.createRow((short) rowOfLine[i]); // 建立新行
				cell = row.createCell((short) colOfLine[i]); // 建立新cell
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(valueOfLine[i]);

				sheet.addMergedRegion(new Region((short) rowOfLine[i],
						(short) (colOfLine[i]), (short) (rowOfLine[i]
								+ xyOfLine[i][0] - 1), (short) (colOfLine[i]
								+ xyOfLine[i][1] - 1)));
			}

			y = 1;
			if (list != null) {
				// 循环对每个作业计划模版进行分类处理
				for (int i = 0; i < list.size(); i++) {
					tawwpMonthPlan = (TawwpMonthPlan) list.get(i); // 获取一个作业计划模版
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan); // 将作业计划模版信息导入到作业计划模版显示类中

					// 新的行
					row = sheet.createRow((short) ((i + 1)));

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 0, (short) ((i) + y), (short) 0));

					cell = row.createCell((short) 0);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(tawwpMonthPlanVO.getMonthFlag());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 1, (short) ((i) + y), (short) 1));
					cell = row.createCell((short) 1);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(tawwpMonthPlanVO.getYearFlag());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 2, (short) ((i) + y), (short) 2));
					cell = row.createCell((short) 2);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(tawwpMonthPlanVO.getName());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 3, (short) ((i) + y), (short) 3));
					cell = row.createCell((short) 3);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(dict.getDictByDictId(
							tawwpMonthPlanVO.getSysTypeId()).getDictName());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 4, (short) ((i) + y), (short) 4));
					cell = row.createCell((short) 4);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(dict.getDictByDictId(
							tawwpMonthPlanVO.getNetTypeId()).getDictName());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 5, (short) ((i) + y), (short) 5));
					cell = row.createCell((short) 5);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(tawwpMonthPlanVO
							.getExecuteStateName(tawwpMonthPlanVO
									.getExecuteState()));

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 6, (short) ((i) + y), (short) 6));
					cell = row.createCell((short) 6);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(dept
							.id2Name(tawwpMonthPlanVO.getDeptId()));

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 7, (short) ((i) + y), (short) 7));
					cell = row.createCell((short) 7);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(user.getUserByuserid(
							tawwpMonthPlanVO.getCruser()).getUsername());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 8, (short) ((i) + y), (short) 8));
					cell = row.createCell((short) 8);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(tawwpMonthPlanVO.getCrtime());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 9, (short) ((i) + y), (short) 9));
					cell = row.createCell((short) 9);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					String modelName = "";
					if (tawwpMonthPlan.getTawwpModelPlan() != null) {
						modelName = tawwpMonthPlan.getTawwpModelPlan()
								.getName();
					} else {
						modelName = "";
					}
					cell.setCellValue(modelName);// 年度名称

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 10, (short) ((i) + y), (short) 10));
					cell = row.createCell((short) 10);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(tawwpMonthPlan.getTawwpYearPlan()
							.getName());

					sheet.addMergedRegion(new Region((short) ((i) + y),
							(short) 11, (short) ((i) + y), (short) 11));
					cell = row.createCell((short) 11);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					String netName = "";
					if (tawwpMonthPlan.getTawwpNet() != null) {
						netName = tawwpMonthPlan.getTawwpNet().getName();
					} else {
						netName = "无网元";
					}
					cell.setCellValue(netName);

					y++;

				}

				wb.write(fos);
				fos.close(); // 关闭输出流

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			list = null;
		}

		return url;
	}

	/**
	 * 业务逻辑：获得当月已经选择的网元列表
	 * 
	 * @throws TawwpException
	 *             异常信息
	 * 
	 * @return list 月作业计划的已选网元列表。
	 */
	public List getSelectedNetList(String yearPlanId, String monthFlag) {
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		List selectedNetList = new ArrayList();
		monthFlag = "'" + monthFlag + "'";
		List temp = tawwpMonthPlanDao.getMonthPlan(yearPlanId, monthFlag);
		try {
			for (int i = 0; i < temp.size(); i++) {
				TawwpMonthPlan tawwpMonthPlan = (TawwpMonthPlan) temp.get(i);
				TawwpNet tawwpNet = tawwpMonthPlan.getTawwpNet();
				TawwpNetVO tawwpNetVO = new TawwpNetVO();
				if (tawwpNet != null) {
					MyBeanUtils.copyProperties(tawwpNetVO, tawwpNet); // 网元数据转换
					tawwpNetVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpNetVO.getNetTypeId()));
					tawwpNetVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpNetVO.getSysTypeId()));
					tawwpNetVO.setRoomName(tawwpUtilDAO.getRoomName(tawwpNetVO
							.getRoomId()));
					tawwpNetVO.setDeptName(tawwpUtilDAO.getDeptNames(tawwpNetVO
							.getDeptId()));
					selectedNetList.add(tawwpNetVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			temp = null;
		}
		return selectedNetList;
	}

	/**
	 * 获取待删除的月度作业计划列表
	 * 
	 * @throws TawwpException
	 *             异常信息
	 * 
	 * @return list 月作业计划的已选网元列表。
	 */
	public List getWaitRemoveMonthPlan(String yearPlanId, String[] months) {
		List list = new ArrayList();
		String monthStr = "";
		for (int i = 0; i < months.length; i++) {
			monthStr = monthStr + "'" + months[i] + "',";
		}
		monthStr = monthStr.substring(0, monthStr.length() - 1);
		list = tawwpMonthPlanDao.getMonthPlan(yearPlanId, monthStr);
		return list;
	}

	public List getListbynetid(String netid) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		String year = String.valueOf(date.getYear());
		String months = String.valueOf(date.getMonth() + 1);
		return tawwpMonthPlanDao.getMonthPlanByNet(netid, year, months);
	}
	/**
	 * 业务逻辑:列表待审批月度作业计划(LIST-MONTHLY-PLAN-ADJUSTING-001)
	 * 
	 * @throws Exception
	 *             异常
	 * @return List月度作业计划VO对象集合
	 */
	public List listMonthPlanOfChildDept(String _deptId, String _yearFlag,
	                                       String _monthFlag,String cycle) throws
	      Exception {

	    //初始化数据
	     
	    TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

	    //model对象
	    TawwpMonthPlan tawwpMonthPlan = null;
	    TawwpMonthPlanVO tawwpMonthPlanVO = null;

	    //集合对象
	    List monthPlanList = null;
	    List newList = new ArrayList();

	    String deptIdStr = null;

	    deptIdStr = tawwpUtilDAO.getChildDept(_deptId);
	    try {

	      //获取年度作业计划集合
	      monthPlanList = tawwpMonthPlanDao.listMonthPlanOfChildDept(deptIdStr,
	          _yearFlag, _monthFlag);

	      //取出model对象,处理后封装成VO对象
	      for (int i = 0; i < monthPlanList.size(); i++) {
	        tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(i);

	        tawwpMonthPlanVO = new TawwpMonthPlanVO();
	        MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
	                                               tawwpMonthPlan);

	        //获取执行类型名称
	        tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO.
	                                            getExecuteTypeName(
	            tawwpMonthPlanVO.getExecuteType()));
	        //获取部门名称
	        tawwpMonthPlanVO.setDeptName(tawwpUtilDAO.getDeptName(
	            tawwpMonthPlanVO.
	            getDeptId()));
	        //获取系统类型名称
	        tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO.getSysTypeName(
	            tawwpMonthPlanVO.getSysTypeId()));
	        //获取网元类型名称
	        tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO.getNetTypeName(
	            tawwpMonthPlanVO.getNetTypeId()));
	        //获取创建人姓名
	        tawwpMonthPlanVO.setUserName(tawwpUtilDAO.getUserName(
	            tawwpMonthPlanVO.
	            getCruser()));
	        //获取制定状态名称
	        tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO.
	                                                getConstituteStateName(
	            tawwpMonthPlanVO.
	            getConstituteState()));
	        //获取执行状态名称
	        tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO.
	                                             getExecuteStateName(
	            tawwpMonthPlanVO.getExecuteState()));
	        //获取执行负责人姓名
	        tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO.getUserName(
	            tawwpMonthPlanVO.getPrincipal()));
	        /*
	          获取网元名称
	          (解决中文问题,不用静态方法处理的话会产生乱码"????????",
	           从model中取值用于页面显示必须经过如下处理)
	         */
	        if (tawwpMonthPlan.getTawwpNet() != null) {
	          tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet().getId());
	          tawwpMonthPlanVO.setNetName(StaticMethod.null2String(
	              tawwpMonthPlan.
	              getTawwpNet().getName()));
	        }
	        else {
	          tawwpMonthPlanVO.setNetName("无网元");
	        }
	      
	        if (cycle != null && !"".equals(cycle) && !"-1".equals(cycle)) {
	        Set monthExecuteSet = tawwpMonthPlan.getTawwpMonthExecutes();
	        Iterator it = monthExecuteSet.iterator();
	        while(it.hasNext()){
	        	TawwpMonthExecute  tawwpMonthExecute = (TawwpMonthExecute)it.next();
	        	if ((tawwpMonthExecute.getCycle()).equals(cycle)) {
		            newList.add(tawwpMonthPlanVO);
		            break;
		          }
	        	}
	        }else{
	        //将VO对象添加进列表中
	        newList.add(tawwpMonthPlanVO);
	        }
	      }

	      //返回封装月度计划信息的Hashtable
	      return newList;
	    }
	    catch (Exception e) {
	      //捕获异常
	      e.printStackTrace();
	      throw new TawwpException("月度作业计划列表浏览出现异常");
	    }
	    finally {
	      monthPlanList = null;
	    }
	  } 
	
	public List listMonthCheck() throws Exception {

		// 初始化数据,DAO对象
		// TawwpMonthCheckDAO tawwpMonthCheckDAO = new TawwpMonthCheckDAO();
		//TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		Hashtable _hashtable = new Hashtable();
		// 集合对象
		List list = null;
		List newList = new ArrayList();
		// model对象
		TawwpMonthCheck tawwpMonthCheck = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		// VO对象
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 获取月度作业计划审批信息集合
			list = tawwpMonthCheckDao.listUnPassMonthCheck();
			_hashtable = getSameToYearPlanId(list);
			String flag = "";

			for (int i = 0; i < list.size(); i++) {
				tawwpMonthCheck = (TawwpMonthCheck) list.get(i);
				tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();

				if (flag.indexOf(tawwpMonthPlan.getYearPlanId()
						+ tawwpMonthPlan.getMonthFlag()) > 0) {
					continue;
				}
				tawwpMonthPlanVO = (TawwpMonthPlanVO) _hashtable
						.get(tawwpMonthPlan.getYearPlanId()
								+ tawwpMonthPlan.getMonthFlag());
				flag = flag + "_" + tawwpMonthPlan.getYearPlanId()
						+ tawwpMonthPlan.getMonthFlag();
		
				newList.add(tawwpMonthPlanVO);
			}

			// 返回月度作业计划VO对象集合
			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("获取待审批月度作业计划列表出现异常");
		} finally {
			list = null;
		}
	}

	public List getMonthExecuteUser(String monthplanid, String state) {
		
			return tawwpMonthExecuteUserDao.getMonthExecuteUser(monthplanid,state);

	}


	//获取需要复制的月度作业计划和相关执行项的对象
	public HashMap getCopyMonthFlags(List monthPLans,TawwpYearPlan tawwpYearPlan,String currMonthFlag){
		HashMap map = new HashMap();
		for(int i=0;i<monthPLans.size();i++){
			TawwpMonthPlan tawwpMonthPlan = (TawwpMonthPlan)monthPLans.get(i);
			String tawwpNetId = "";
			if(tawwpMonthPlan.getTawwpNet() != null){
				tawwpNetId = StaticMethod.null2String(tawwpMonthPlan.getTawwpNet().getId());
			}else{
				tawwpNetId = "null";
			}
			List needCopyMonthexecute = new ArrayList();
			Iterator tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes().iterator();
			while (tawwpMonthExecutes.hasNext()) {
				TawwpMonthExecute tawwpMonthExecute = (TawwpMonthExecute) tawwpMonthExecutes
						.next();
				String copyMonth = this.getOppositeMonth(tawwpMonthExecute,currMonthFlag,tawwpMonthPlan.getMonthFlag());
				if(!copyMonth.equals("")){
					needCopyMonthexecute.add(tawwpMonthExecute);
				}
			}
			for(int j=i+1;j<monthPLans.size();j++){
				TawwpMonthPlan tempMonthPlan = (TawwpMonthPlan)monthPLans.get(j);
				String tempNetId = "";
				if(tempMonthPlan.getTawwpNet() != null){
					tempNetId = StaticMethod.null2String(tempMonthPlan.getTawwpNet().getId()); 
				}else{
					tempNetId = "null";
				}
				if(tawwpNetId.equals(tempNetId)){
					Iterator tempMonthExecutes = tempMonthPlan.getTawwpMonthExecutes().iterator();
					while (tempMonthExecutes.hasNext()) {
						TawwpMonthExecute tempMonthExecute = (TawwpMonthExecute) tempMonthExecutes
								.next();
						String copyMonth = this.getOppositeMonth(tempMonthExecute,currMonthFlag,tempMonthPlan.getMonthFlag());
						if(!copyMonth.equals("")){
							needCopyMonthexecute.add(tempMonthExecute);
						}
					}
					monthPLans.remove(tempMonthPlan);
					j = j - 1;
				}
			}
			if(needCopyMonthexecute.size()>0){
				map.put(tawwpMonthPlan,needCopyMonthexecute);
			}
		}
		
		return map;
	}

	//计算需要复制上一次作业计划的月份
	public String getOppositeMonth(TawwpMonthExecute tawwpMonthExecute,String currMonthFlag,String lastTimeMonthFlag){
		String month = "";
		int cycle = StaticMethod.null2int(tawwpMonthExecute.getCycle());
		switch(cycle) {
		case 1://周期为天
			int subDay = StaticMethod.null2int(currMonthFlag) - StaticMethod.null2int(lastTimeMonthFlag);
			if(subDay == 1){
				month = lastTimeMonthFlag;
			}
			break;
		case 2://周期为周
			int subWeek = StaticMethod.null2int(currMonthFlag) - StaticMethod.null2int(lastTimeMonthFlag);
			if(subWeek == 1){
				month = lastTimeMonthFlag;
			}
			break;
		case 3://周期为半月
			int subHalfMonth = StaticMethod.null2int(currMonthFlag) - StaticMethod.null2int(lastTimeMonthFlag);
			if(subHalfMonth == 1){
				month = lastTimeMonthFlag;
			}
			break;
		case 4://周期为月
			int subMonth = StaticMethod.null2int(currMonthFlag) - StaticMethod.null2int(lastTimeMonthFlag);
			if(subMonth == 1){
				month = lastTimeMonthFlag;
			}
			break;
		case 5://周期为季度
			int tempSeasonLastTimeMonthFlag = StaticMethod.null2int(lastTimeMonthFlag);
			int tempSeasonCurrMonthFlag = StaticMethod.null2int(currMonthFlag);
			double flagSeasonLastTimeMonthFlag  = tempSeasonLastTimeMonthFlag/3;
			double flagSeasonCurrMonthFlag = tempSeasonCurrMonthFlag/3;
			double subSeason = Math.floor(flagSeasonCurrMonthFlag) - Math.floor(flagSeasonLastTimeMonthFlag);
			if(tempSeasonLastTimeMonthFlag%3 == tempSeasonCurrMonthFlag%3 && subSeason == 1){
				month = lastTimeMonthFlag;
			}
			break;
		case 6://周期为半年
			int tempSixLastTimeMonthFlag = StaticMethod.null2int(lastTimeMonthFlag);
			int tempSixCurrMonthFlag = StaticMethod.null2int(currMonthFlag);
			double flagSixLastTimeMonthFlag  = tempSixLastTimeMonthFlag/6;
			double flagSixCurrMonthFlag = tempSixCurrMonthFlag/6;
			double subSix = Math.floor(flagSixCurrMonthFlag) - Math.floor(flagSixLastTimeMonthFlag);
			if(tempSixLastTimeMonthFlag%6 == tempSixCurrMonthFlag%6 && subSix == 1){
				month = lastTimeMonthFlag;
			}
			break;
		case 7://周期为年
			month = "";
			break;
		case 8://周期为两月
			int tempTowLastTimeMonthFlag = StaticMethod.null2int(lastTimeMonthFlag);
			int tempTowCurrMonthFlag = StaticMethod.null2int(currMonthFlag);
			double flagTowLastTimeMonthFlag  = tempTowLastTimeMonthFlag/2;
			double flagTowCurrMonthFlag = tempTowCurrMonthFlag/2;
			double subTow = Math.floor(flagTowCurrMonthFlag) - Math.floor(flagTowLastTimeMonthFlag);
			if(tempTowLastTimeMonthFlag%2 == tempTowCurrMonthFlag%2 && subTow == 1){
				month = lastTimeMonthFlag;
			}
			break;
		case 9://周期为四月
			int tempFourLastTimeMonthFlag = StaticMethod.null2int(lastTimeMonthFlag);
			int tempFourCurrMonthFlag = StaticMethod.null2int(currMonthFlag);
			double flagFourLastTimeMonthFlag  = tempFourLastTimeMonthFlag/4;
			double flagFourCurrMonthFlag = tempFourCurrMonthFlag/4;
			double subFour = Math.floor(flagFourCurrMonthFlag) - Math.floor(flagFourLastTimeMonthFlag);
			if(tempFourLastTimeMonthFlag%4 == tempFourCurrMonthFlag%4 && subFour == 1 ){
				month = lastTimeMonthFlag;
			}
			break;
		};
		return month;
	}

	//获取已经审核通过的月度作业计划的审核人，以便复制时使用
	public String getCheckUsers(TawwpMonthPlan tawwpMonthPlan){
		String checkUsers = ",";
		Iterator tawwpMonthChecks = tawwpMonthPlan.getTawwpMonthChecks().iterator();
		while(tawwpMonthChecks.hasNext()){
			TawwpMonthCheck TawwpMonthCheck = (TawwpMonthCheck)tawwpMonthChecks.next();
			String[] tempCheckUser = TawwpMonthCheck.getCheckUser().split(",");
			for(int i=0;i<tempCheckUser.length;i++){
				String tempUser = "," + tempCheckUser[i] + ",";
				if(checkUsers.indexOf(tempUser)<0 && !tempCheckUser[i].equals("null")){
					checkUsers = checkUsers + TawwpMonthCheck.getCheckUser() + ",";
				}
			}
		}
		if(!checkUsers.equals(",")){
			checkUsers = checkUsers.substring(1,checkUsers.length()-1);
		}
		return checkUsers;
	}
	/**
	 * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
	 * @throws Exception
	 *             异常
	 */
	public void passMonthCheckTime() throws Exception {

		// 集合对象
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecutecontents = null;
		Iterator tawwpExecutecontentUsers = null;
		TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
		TawwpMonthExecute tawwpMonthExecute = new TawwpMonthExecute();
		// 执行人字符串
		String executeUserStr = "";
		// 创建执行人信息字符串
		String executerInfo = "";
		// 执行日期字符串
		String executeDate = "";
		// 日期格式基础字符串
		String baseDate = "";
		String startHH = "";
		String endHH = "";
		Calendar c =  Calendar.getInstance() ; 
		String month =  ""+(c.get(Calendar.MONTH)+1);
		// 月度作业计划审批信息对象
		TawwpMonthCheck tawwpMonthCheck = new TawwpMonthCheck();
		// 月度作业计划执行内容执行人信息对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = new TawwpMonthExecuteUser();

		TawwpFlowManage tawwpFlowManage = null;
		Step step = null;
		String j = "";

		try {

			// 获取月度作业计划审批信息对象
			List list = tawwpMonthCheckDao.listUnPassMonthCheck();
			for (int h = 0; h < list.size(); h++) {
			// 封装月度作业计划审批信息对象/main.jsp?id=15
				tawwpMonthCheck = (TawwpMonthCheck) list.get(h);
			tawwpMonthCheck.setState("1");
			tawwpMonthCheck.setChecktime(TawwpUtil.getCurrentDateTime());
			String type = StaticMethod.null2String(tawwpMonthCheck.getType());

			// 修改月度作业计划审批信息(通过或驳回)
			tawwpMonthCheckDao.updateMonthCheck(tawwpMonthCheck);

			// 获取月度作业计划
			tawwpMonthPlan = tawwpMonthCheck.getTawwpMonthPlan();
			
			if("0".equals(tawwpMonthPlan.getTawwpYearPlan().getIsApp())&&(month).equals(tawwpMonthPlan.getMonthFlag())){
		 
			// 将月度作业计划执行状态改为"执行中"
			tawwpMonthPlan.setExecuteState("4");
			// 制定状态改为“通过”
			tawwpMonthPlan.setConstituteState("1");
			// 如果是删除类型的审批通过，则将该作业计划的删除标志位置为“1”
			if (type.equals("1")) {
				tawwpMonthPlan.setDeleted("1");
			}
			// 更新月度作业计划执行状态
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

			// 获取月度作业计划执行内容集合
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			// -----------------------------------------------------------------------

			// 获取流程管理对象
			tawwpFlowManage = TawwpFlowManage.getInstance();
			// 获取下一步骤对象
			step = tawwpFlowManage.getMonthFlowStep(tawwpMonthCheck
					.getFlowSerial(), tawwpMonthCheck.getStepSerial(),
					tawwpMonthCheck.getDeptId());

			if (step == null) {
				// 如果是删除类型的审批通过，则将该月度作业计划下属的执行项(未执行的)删除
				if (type.equals("1")) {
					tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();
					while (tawwpMonthExecutes.hasNext()) {
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());
						tawwpExecutecontents = tawwpMonthExecute
						.getTawwpExecuteContents().iterator();
						while (tawwpExecutecontents.hasNext()){
							TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecutecontents.next());
							tawwpExecuteContentDao.deleteExecuteContent(tawwpExecuteContent);
						}
						tawwpMonthExecutes.remove();
						tawwpMonthPlan.getTawwpMonthExecutes().remove(tawwpMonthExecute);
						tawwpMonthExecute.setTawwpMonthPlan(null);
						tawwpMonthExecuteDao.deleteMonthExecute(tawwpMonthExecute);
					}
					List deleteMonthPlan = tawwpMonthExecuteUserDao
							.getMonthExecuteUser(tawwpMonthPlan.getId());
					for (int m = 0; m < deleteMonthPlan.size(); m++) {
						TawwpMonthExecuteUser tawwpMonthExecuteUserDel = (TawwpMonthExecuteUser) deleteMonthPlan
								.get(m);
						tawwpMonthExecuteUserDao
								.deleteMonthExecuteUser(tawwpMonthExecuteUserDel);
					}
				} else {// 正常的增加操作
					/*
					 * 如果步骤对象为空，整体流程结束 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 * 2.将执行人信息写入执行人表中
					 * 3.添加默认执行内容(整体)信息对象TawwpExecuteContent到数据库中
					 */

					/*--------------------------------------------------
					 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 */

					while (tawwpMonthExecutes.hasNext()) {
						// 获取月度作业计划执行内容
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());

						/*--------------------------------------------------
						 2.将执行人信息写入执行人表中
						 */

						// 获取执行人字符串
						executeUserStr = tawwpMonthExecute.getExecuter();

						if (executeUserStr != null
								&& !"".equals(executeUserStr)) {
							// 处理执行人字符串
							String[] temp = executeUserStr.split(",");
							for (int i = 0; i < temp.length; i++) {

								/*
								 * 保证用户名(或部门、岗位编号)、执行人类型、 月度作业计划的唯一对应性的算法
								 */
								// 组合临时字符串,如"用户名,执行人类型"
								String tempStr = temp[i] + ","
										+ tawwpMonthExecute.getExecuterType();
								// 如果执行人信息字符串不包含以上信息,就创建执行人对象,并更新执行人信息字符串
								if (executerInfo.indexOf(tempStr) == -1) {
									executerInfo += (tempStr + "#");
									tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
									// 封装月度作业计划执行内容执行人对象
									tawwpMonthExecuteUser.setExecuter(temp[i]);
									tawwpMonthExecuteUser
											.setExecuterType(tawwpMonthExecute
													.getExecuterType());
									tawwpMonthExecuteUser
											.setExecuteRoom(tawwpMonthExecute
													.getExecuteRoom());
									tawwpMonthExecuteUser
											.setExecuteDuty(tawwpMonthExecute
													.getExecuteDuty());
									tawwpMonthExecuteUser.setContent("");
									tawwpMonthExecuteUser.setState("0");
									tawwpMonthExecuteUser
											.setYearFlag(tawwpMonthPlan
													.getYearFlag());
									tawwpMonthExecuteUser
											.setMonthFlag(tawwpMonthPlan
													.getMonthFlag());
									tawwpMonthExecuteUser
											.setTawwpMonthPlan(tawwpMonthPlan);
									// 保存月度作业计划执行内容执行人信息
									tawwpMonthExecuteUserDao
											.saveMonthExecuteUser(tawwpMonthExecuteUser);
								}
							}
						}

						/*---------------------------------------------------
						 3.添加默认执行内容(整体)信息对象(TawwpExecuteContent)
						 */

						executeDate = tawwpMonthExecute.getExecuteDate();
						startHH = tawwpMonthExecute.getStartHH();
						if (StaticMethod.null2int(startHH) > 0
								&& StaticMethod.null2int(startHH) < 10) {
							startHH = "0" + startHH;
						}
						endHH = tawwpMonthExecute.getEndHH();
						if (StaticMethod.null2int(endHH) > 0
								&& StaticMethod.null2int(endHH) < 10) {
							endHH = "0" + endHH;
						}
						// 转化成字符数组
						char[] tempArr = executeDate.toCharArray();
						// 组合基础字符串如"2005-09-"
						baseDate = tawwpMonthPlan.getYearFlag() + "-"
								+ tawwpMonthPlan.getMonthFlag() + "-";
						for (int i = 1; i < (tempArr.length + 1); i++) {
							// 如果该日需要执行
							if ('1' == tempArr[i - 1]) {

								// 字符串处理不足两位补零
								j = String.valueOf(i);
								if (i < 10) {
									j = "0" + j;
								}

								// 封装执行内容(整体)对象
								TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent(
										tawwpMonthExecute.getName(),
										tawwpMonthExecute.getBotype(),
										tawwpMonthExecute.getExecutedeptlevel(),
										tawwpMonthExecute.getAppdesc(),
										baseDate + j + " " + startHH + ":00:00",
										baseDate + j + " " + endHH + ":59:59",
										TawwpUtil.getCurrentDateTime(),
										"",
										tawwpMonthExecute.getFormat(),
										"",
										tawwpMonthExecute.getCycle(),
										tawwpMonthExecute.getFormId(),
										"",
										tawwpMonthPlan.getDeptId(),
										"",
										"0",
										"0",
										"",
										tawwpMonthExecute.getCommand(), // edit
										// by
										// gongyufeng
										// 智能巡检
										tawwpMonthExecute.getCommandName(),
										tawwpMonthExecute.getExecuter(),
										tawwpMonthExecute.getExecuterType(),
										tawwpMonthExecute.getExecuteRoom(),
										tawwpMonthExecute.getExecuteDuty(),
										tawwpMonthPlan.getExecuteType(),
										tawwpMonthExecute.getFileFlag(),
										tawwpMonthExecute.getQualityCheckUser(),
										tawwpMonthExecute, tawwpMonthPlan,"");
								tawwpExecuteContent.setNormalFlag("1");
								// 保存执行内容(整体)对象
								tawwpExecuteContentDao
										.saveExecuteContent(tawwpExecuteContent);
							}
						}
					}
					}
				}
	 }
			} 
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划审批通过出现异常");
		} finally {
			tawwpMonthExecutes = null;
		}
	}
	/**
	 * 业务逻辑:月度作业计划审批(通过)(AUDITING-MONTHLY-PLAN-002)
	 * 
	 * @param _monthCheckId
	 *            String 审批信息编号
	 * @param _content
	 *            String 审批信息
	 * @param _checkUser
	 *            String 下一步骤审批人，如果步骤不存在可以为空
	 * @param _currentCheckUser
	 *            String 审批人
	 * @throws Exception
	 *             异常
	 */
	public void passMonthCheckTime(String _monthFlag, String _yearFlag) throws Exception {

		// 集合对象
		Iterator tawwpMonthExecutes = null;
		Iterator tawwpExecutecontents = null;
		Iterator tawwpExecutecontentUsers = null;
		TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
		TawwpMonthExecute tawwpMonthExecute = new TawwpMonthExecute();
		// 执行人字符串
		String executeUserStr = "";
		// 创建执行人信息字符串
		String executerInfo = "";
		// 执行日期字符串
		String executeDate = "";
		// 日期格式基础字符串
		String baseDate = "";
		String startHH = "";
		String endHH = "";

		// 月度作业计划审批信息对象
		//TawwpMonthCheck tawwpMonthCheck = new TawwpMonthCheck();
		// 月度作业计划执行内容执行人信息对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = new TawwpMonthExecuteUser();

		TawwpFlowManage tawwpFlowManage = null;
		Step step = null;
		String j = "";

		try {

			List list = tawwpMonthPlanDao.listMonthPlan("", _yearFlag, _monthFlag);
			
			/*list=tawwpMonthMgr.listMonthCheck();*/
			for (int h = 0; h < list.size(); h++) {
			// 获取月度作业计划
				tawwpMonthPlan = (TawwpMonthPlan) list.get(h);
			
			if("0".equals(tawwpMonthPlan.getTawwpYearPlan().getIsApp())){
		 
			// 将月度作业计划执行状态改为"执行中"
			tawwpMonthPlan.setExecuteState("4");
			// 制定状态改为“通过”
			tawwpMonthPlan.setConstituteState("1");
			
			// 更新月度作业计划执行状态
			tawwpMonthPlanDao.updateMonthPlan(tawwpMonthPlan);

			// 获取月度作业计划执行内容集合
			tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();

			// -----------------------------------------------------------------------

			// 获取流程管理对象
			tawwpFlowManage = TawwpFlowManage.getInstance();
			// 获取下一步骤对象
			/*step = tawwpFlowManage.getMonthFlowStep(tawwpMonthCheck
					.getFlowSerial(), tawwpMonthCheck.getStepSerial(),
					tawwpMonthCheck.getDeptId());
*/
		/*	if (step == null) {
				// 如果是删除类型的审批通过，则将该月度作业计划下属的执行项(未执行的)删除
				if (type.equals("1")) {
					tawwpMonthExecutes = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator();
					while (tawwpMonthExecutes.hasNext()) {
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());
						tawwpExecutecontents = tawwpMonthExecute
						.getTawwpExecuteContents().iterator();
						while (tawwpExecutecontents.hasNext()){
							TawwpExecuteContent tawwpExecuteContent = (TawwpExecuteContent) (tawwpExecutecontents.next());
							tawwpExecuteContentDao.deleteExecuteContent(tawwpExecuteContent);
						}
						tawwpMonthExecutes.remove();
						tawwpMonthPlan.getTawwpMonthExecutes().remove(tawwpMonthExecute);
						tawwpMonthExecute.setTawwpMonthPlan(null);
						tawwpMonthExecuteDao.deleteMonthExecute(tawwpMonthExecute);
					}
					List deleteMonthPlan = tawwpMonthExecuteUserDao
							.getMonthExecuteUser(tawwpMonthPlan.getId());
					for (int m = 0; m < deleteMonthPlan.size(); m++) {
						TawwpMonthExecuteUser tawwpMonthExecuteUserDel = (TawwpMonthExecuteUser) deleteMonthPlan
								.get(m);
						tawwpMonthExecuteUserDao
								.deleteMonthExecuteUser(tawwpMonthExecuteUserDel);
					}
				} else {*/// 正常的增加操作
					/*
					 * 如果步骤对象为空，整体流程结束 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 * 2.将执行人信息写入执行人表中
					 * 3.添加默认执行内容(整体)信息对象TawwpExecuteContent到数据库中
					 */

					/*--------------------------------------------------
					 1.将月度作业计划执行状态改为"执行中",制定状态改为“通过”
					 */

					while (tawwpMonthExecutes.hasNext()) {
						// 获取月度作业计划执行内容
						tawwpMonthExecute = (TawwpMonthExecute) (tawwpMonthExecutes
								.next());

						/*--------------------------------------------------
						 2.将执行人信息写入执行人表中
						 */

						// 获取执行人字符串
						executeUserStr = tawwpMonthExecute.getExecuter();

						if (executeUserStr != null
								&& !"".equals(executeUserStr)) {
							// 处理执行人字符串
							String[] temp = executeUserStr.split(",");
							for (int i = 0; i < temp.length; i++) {

								/*
								 * 保证用户名(或部门、岗位编号)、执行人类型、 月度作业计划的唯一对应性的算法
								 */
								// 组合临时字符串,如"用户名,执行人类型"
								String tempStr = temp[i] + ","
										+ tawwpMonthExecute.getExecuterType();
								// 如果执行人信息字符串不包含以上信息,就创建执行人对象,并更新执行人信息字符串
								if (executerInfo.indexOf(tempStr) == -1) {
									executerInfo += (tempStr + "#");
									tawwpMonthExecuteUser = new TawwpMonthExecuteUser();
									// 封装月度作业计划执行内容执行人对象
									tawwpMonthExecuteUser.setExecuter(temp[i]);
									tawwpMonthExecuteUser
											.setExecuterType(tawwpMonthExecute
													.getExecuterType());
									tawwpMonthExecuteUser
											.setExecuteRoom(tawwpMonthExecute
													.getExecuteRoom());
									tawwpMonthExecuteUser
											.setExecuteDuty(tawwpMonthExecute
													.getExecuteDuty());
									tawwpMonthExecuteUser.setContent("");
									tawwpMonthExecuteUser.setState("0");
									tawwpMonthExecuteUser
											.setYearFlag(tawwpMonthPlan
													.getYearFlag());
									tawwpMonthExecuteUser
											.setMonthFlag(tawwpMonthPlan
													.getMonthFlag());
									tawwpMonthExecuteUser
											.setTawwpMonthPlan(tawwpMonthPlan);
									// 保存月度作业计划执行内容执行人信息
									tawwpMonthExecuteUserDao
											.saveMonthExecuteUser(tawwpMonthExecuteUser);
								}
							}
						}

						/*---------------------------------------------------
						 3.添加默认执行内容(整体)信息对象(TawwpExecuteContent)
						 */

						executeDate = tawwpMonthExecute.getExecuteDate();
						startHH = tawwpMonthExecute.getStartHH();
						if (StaticMethod.null2int(startHH) > 0
								&& StaticMethod.null2int(startHH) < 10) {
							startHH = "0" + startHH;
						}
						endHH = tawwpMonthExecute.getEndHH();
						if (StaticMethod.null2int(endHH) > 0
								&& StaticMethod.null2int(endHH) < 10) {
							endHH = "0" + endHH;
						}
						// 转化成字符数组
						char[] tempArr = executeDate.toCharArray();
						// 组合基础字符串如"2005-09-"
						baseDate = tawwpMonthPlan.getYearFlag() + "-"
								+ tawwpMonthPlan.getMonthFlag() + "-";
						for (int i = 1; i < (tempArr.length + 1); i++) {
							// 如果该日需要执行
							if ('1' == tempArr[i - 1]) {

								// 字符串处理不足两位补零
								j = String.valueOf(i);
								if (i < 10) {
									j = "0" + j;
								}

								// 封装执行内容(整体)对象
								TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent(
										tawwpMonthExecute.getName(),
										tawwpMonthExecute.getBotype(),
										tawwpMonthExecute.getExecutedeptlevel(),
										tawwpMonthExecute.getAppdesc(),
										baseDate + j + " " + startHH + ":00:00",
										baseDate + j + " " + endHH + ":59:59",
										TawwpUtil.getCurrentDateTime(),
										"",
										tawwpMonthExecute.getFormat(),
										"",
										tawwpMonthExecute.getCycle(),
										tawwpMonthExecute.getFormId(),
										"",
										tawwpMonthPlan.getDeptId(),
										"",
										"0",
										"0",
										"",
										tawwpMonthExecute.getCommand(), // edit
										// by
										// gongyufeng
										// 智能巡检
										tawwpMonthExecute.getCommandName(),
										tawwpMonthExecute.getExecuter(),
										tawwpMonthExecute.getExecuterType(),
										tawwpMonthExecute.getExecuteRoom(),
										tawwpMonthExecute.getExecuteDuty(),
										tawwpMonthPlan.getExecuteType(),
										tawwpMonthExecute.getFileFlag(),
										tawwpMonthExecute.getQualityCheckUser(),
										tawwpMonthExecute, tawwpMonthPlan,"");
								tawwpExecuteContent.setNormalFlag("1");
								// 保存执行内容(整体)对象
								tawwpExecuteContentDao
										.saveExecuteContent(tawwpExecuteContent);
							}
						}
					//}
					}
					try {
						// 发送短信信息给创建人
						TawwpUtil.sendSMS(tawwpMonthPlan.getCruser(), "月度作业计划《"
								+ StaticMethod.null2String(tawwpMonthPlan
										.getName()) + "》通过", tawwpMonthPlan
								.getId());

						TawwpUtil.closeSMS(tawwpMonthPlan.getId());

						TawwpUtil.sendMail(tawwpMonthPlan.getCruser(),
								"月度作业计划《"
										+ StaticMethod
												.null2String(tawwpMonthPlan
														.getName()) + "》通过",
								tawwpMonthPlan.getId());
						TawwpUtil.closeEmail(tawwpMonthPlan.getId());
					} catch (Exception e) {
						System.out.print("消息发送异常" + e);
					}
				}
			} 
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划审批通过出现异常");
		} finally {
			tawwpMonthExecutes = null;
		}
	}
	public void updateMonthPlan(TawwpMonthPlan monthPlan) {
		// TODO Auto-generated method stub
		tawwpMonthPlanDao.updateMonthPlan(monthPlan);
		
	}

	/**
	 * 业务逻辑：获取可以删除的月度作业计划 （RECEIVE-STENCIL-001）
	 * 
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 月作业计划 的Hashtable结构，其中按系统类别、网元类型分级。
	 */
	public Hashtable listMonthPlanDelete(String _deptId, String _yearFlag, String _monthFlag) throws TawwpException {

		// 初始化数据
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// model对象
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpMonthPlanVO tempMonthPlanVO = null;

		// 集合对象
		List monthPlanList = null;
		Hashtable monthPlanVOHash = new Hashtable();
		List monthPlanVOList = new ArrayList();
		List yearPlanList = new ArrayList();
		// 临时集合用于存放关联与某年度计划,但网元不同的月度计划
		List tempList = null;

		try {

			// 获取年度作业计划集合
			yearPlanList = tawwpYearPlanDao.listYearPlanByDept(_deptId,
					_yearFlag);

			// 将年度作业计划对象作为key值put到Hashtable中
			for (int k = 0; k < yearPlanList.size(); k++) {
				monthPlanVOHash.put((TawwpYearPlan) (yearPlanList.get(k)),
						new ArrayList());
			}

			// ---------------------------------------------------------------------

			// 获取月度作业计划对象集合
			monthPlanList = tawwpMonthPlanDao
					.listMonthPlan(_deptId, _yearFlag,
							TawwpStaticVariable.MONTH[StaticMethod
									.null2int(_monthFlag)]);

			// 取出model对象,处理后封装成VO对象
			for (int i = 0; i < monthPlanList.size(); i++) {

				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(i);
	    		HashMap map = this.getNoDeleteMonthPlanMap(_yearFlag, TawwpStaticVariable.MONTH[StaticMethod.null2int(_monthFlag)]);
    			String flag = StaticMethod.nullObject2String(map.get(tawwpMonthPlan.getId()));
				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);
				//如果该作业计划未执行，则将执行状态修改为“未执行”
				if(flag.equals("")){
    				tawwpMonthPlan.setExecuteState("0");
    			}
				// 获取执行类型名称
				tawwpMonthPlanVO.setExecuteTypeName(TawwpMonthPlanVO
						.getExecuteTypeName(tawwpMonthPlanVO.getExecuteType()));
				// 获取部门名称
				// tawwpMonthPlanVO.setDeptName(tawwpMonthPlanVO.getDeptId());
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				// 获取系统类型名称
				tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
				// 获取网元类型名称
				tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
				// 获取创建人姓名
				tawwpMonthPlanVO.setUserName(tawwpMonthPlanVO.getCruser());
				// tawwpMonthPlanVO.setUserName(tawwpUtilDAO
				// .getUserName(tawwpMonthPlanVO.getCruser()));
				// 获取制定状态名称
				tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
						.getConstituteStateName(tawwpMonthPlanVO
								.getConstituteState()));
    			
				if (null != tawwpMonthPlanVO.getUnicomType()
						&& !tawwpMonthPlanVO.getUnicomType().equals("")
						&& tawwpMonthPlanVO.getConstituteState().equals("1")) {
					tawwpMonthPlanVO
							.setConstituteStateName(TawwpMonthPlanVO.MONTHREPORTSTATENAME[StaticMethod
									.null2int(tawwpMonthPlanVO.getReportFlag())]);

					// 获取执行状态名称
				}
				tawwpMonthPlanVO
						.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(tawwpMonthPlanVO
										.getExecuteState()));
				// 获取执行负责人姓名
				// tawwpMonthPlanVO.setPrincipalName(tawwpUtilDAO
				// .getUserName(tawwpMonthPlanVO.getPrincipal()));
				tawwpMonthPlanVO.setPrincipalName(tawwpMonthPlanVO
						.getPrincipal());
				/*
				 * 获取网元名称 (解决中文问题,不用静态方法处理的话会产生乱码"????????",
				 * 从model中取值用于页面显示必须经过如下处理)
				 */
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO.setNetId(tawwpMonthPlan.getTawwpNet()
							.getId());
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}
				// 将VO对象添加进列表中
				monthPlanVOList.add(tawwpMonthPlanVO);
			}

			// ---------------------------------------------------------------------

			// 将月度作业计划VO信息封装进Hashtable对象
			for (int j = 0; j < monthPlanVOList.size(); j++) {

				// 创建月度作业计划、VO对象
				tawwpMonthPlan = (TawwpMonthPlan) monthPlanList.get(j);
				tempMonthPlanVO = (TawwpMonthPlanVO) monthPlanVOList.get(j);

				// 以年度作业计划对象作为key值,获取月度作业计划列表
				tempList = (List) monthPlanVOHash.get(tawwpMonthPlan
						.getTawwpYearPlan());

				// 1.如果为空,说明Hashtable对象中,不含有key值为指定年度作业计划的月度作业计划VO对象列表
				if (tempList == null) {
					// 新建VO列表
					List monthPlanVOlist = new ArrayList();
					// 将月度作业计划VO对象添加进列表
					monthPlanVOlist.add(tempMonthPlanVO);
					// 将key和value,put进Hashtable对象中
					monthPlanVOHash.put(tawwpMonthPlan.getTawwpYearPlan(),
							monthPlanVOlist);
				}

				// 2.如果不为空,说明Hashtable对象中,已含有key值为指定年度作业计划的月度作业计划VO对象列表
				else {
					// 不需新建,只需将月度作业计划VO对象添加进列表
					tempList.add(tempMonthPlanVO);
				}
			}

			// ---------------------------------------------------------------------

			// 返回封装月度计划信息的Hashtable
			return monthPlanVOHash;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划列表浏览出现异常");
		} finally {
			monthPlanList = null;
			monthPlanVOList = null;
			tempList = null;
		}
	}
    
	/**
	 * 业务逻辑：获取未执行的作业计划 （RECEIVE-STENCIL-001）
	 * 
	 * @throws TawwpException
	 *             异常信息
	 * @return map 月作业计划 的Hashtable结构，其中按系统类别、网元类型分级。
	 */
	public HashMap getNoDeleteMonthPlanMap(String _yearFlag, String _monthFlag){
        HashMap map = new HashMap();
        List list = tawwpMonthPlanDao.listNoDeleteMonthPlans(_yearFlag, _monthFlag);
        for(int i = 0; i < list.size(); i++){
            Object obj[] = (Object[])list.get(i);
            String countMonthPlan = StaticMethod.nullObject2String(obj[0]);
            String tawwpMonthPlanId = StaticMethod.nullObject2String(obj[1]);
            map.put(tawwpMonthPlanId, countMonthPlan);
        }
        return map;
    }
}
