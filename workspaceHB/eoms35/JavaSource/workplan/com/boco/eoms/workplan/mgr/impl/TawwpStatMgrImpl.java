package com.boco.eoms.workplan.mgr.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.workplan.dao.ITawwpExecuteAssessDao;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentDao;
import com.boco.eoms.workplan.dao.ITawwpModelPlanDao;
import com.boco.eoms.workplan.dao.ITawwpMonthPlanDao;
import com.boco.eoms.workplan.dao.ITawwpNetDao;
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.mgr.ITawwpStatMgr;
import com.boco.eoms.workplan.model.TawwpExecuteAssess;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteReport;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpYearExecute;
import com.boco.eoms.workplan.model.TawwpYearPlan;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpExecuteReportVO;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpStatVO;
import com.boco.eoms.workplan.vo.TawwpStatYearPlanVO;
import com.boco.eoms.workplan.vo.TawwpYearPlanVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 3:08:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpStatMgrImpl implements ITawwpStatMgr {

	private ITawwpMonthPlanDao tawwpMonthPlanDao;

	private ITawwpNetDao tawwpNetDao;

	private ITawwpYearPlanDao tawwpYearPlanDao;

	private ITawwpModelPlanDao tawwpModelPlanDao;

	private ITawwpExecuteContentDao tawwpExecuteContentDao;

	private ITawwpExecuteAssessDao tawwpExecuteAssessDao;

	/**
	 * @param tawwpModelPlanDao
	 *            the tawwpModelPlanDao to set
	 */
	public void setTawwpModelPlanDao(ITawwpModelPlanDao tawwpModelPlanDao) {
		this.tawwpModelPlanDao = tawwpModelPlanDao;
	}

	/**
	 * @param tawwpYearPlanDao
	 *            the tawwpYearPlanDao to set
	 */
	public void setTawwpYearPlanDao(ITawwpYearPlanDao tawwpYearPlanDao) {
		this.tawwpYearPlanDao = tawwpYearPlanDao;
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
	 * 业务逻辑：查询作业计划执行情况(QUERY-MONTH-PLAN-001) 根据查询信息map，查询符合条件的月度作业计划 条件包括 name
	 * 月度作业计划名称 deptId 部门 sysTypeId 系统类别 netTypeId 网元类型 yearFlag 年度 monthFlag 月度
	 * constituteState 制定状态 executeState 执行状态 tawwpNet 网元
	 * 
	 * @param _mapQuery
	 *            Map 查询条件
	 * @throws Exception
	 *             异常信息
	 * @return List 月度作业计划列表
	 */
	public List queryMonthPlan(Map _mapQuery) throws Exception {
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();

		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		List list = null;
		List netList = null;

		List newList = new ArrayList();

		// 获取网元信息
		String netId = null;
		netId = (String) _mapQuery.get("netId");
		netList = tawwpNetDao.ListNet(netId);
		if (netList.size() > 0) {
			_mapQuery.put("netId", netList);
		}
		String deptId = "";
		String isNext = "";
		ITawSystemDeptManager deptMgr = (ITawSystemDeptManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
		List deptList = new ArrayList();
		deptId = (String) _mapQuery.get("deptId");
		String[] deptStr = null;
		if(deptId!=null && !"".equals(deptId)){
			isNext = (String) _mapQuery.get("isNext");
			if("1".equals(isNext)){
				deptList = deptMgr.getSubDepts(deptId);
				deptStr = new String[deptList.size()];
				for(int i = 0;i<deptList.size();i++){
					TawSystemDept dept = (TawSystemDept)deptList.get(i);
					deptStr[i] = dept.getDeptId();
				}
				_mapQuery.put("deptId",deptStr);
			}else{
				deptStr = new String[1];
				deptStr[0] = deptId;
				_mapQuery.put("deptId",deptStr);
			}
		}
		try {
			list = tawwpMonthPlanDao.searchMonthPlanByNext(_mapQuery); // 获取符合条件的月度作业计划

			// 循环处理执行数据
			for (int i = 0; i < list.size(); i++) {
				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				tawwpMonthPlan = (TawwpMonthPlan) list.get(i);
				//Set set =  tawwpMonthPlan.getTawwpExecuteContents();
				/*for (Iterator it = set.iterator();it.hasNext();){
					TawwpExecuteContent content = (TawwpExecuteContent)it.next();
					System.out.println(content.getExecuter());
				}*/
				// 整理数据
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);

				tawwpMonthPlanVO.setCruser(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));

				// 获取网元名称
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
						.getConstituteStateName(tawwpMonthPlanVO
								.getExecuteState()));
				tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
						.getConstituteStateName(tawwpMonthPlanVO
								.getConstituteState()));
				tawwpMonthPlanVO
						.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(tawwpMonthPlanVO
										.getExecuteState()));

				newList.add(tawwpMonthPlanVO); // 放入列表
				Collections.sort(newList, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
						TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
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

			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划查询出现异常");
		} finally {
			list = null;
		}
	}
	
	
	public List queryMonthPlan(Map _mapQuery,String _executeUser,String _executeType) throws Exception {
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();

		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		List list = null;
		List netList = null;

		List newList = new ArrayList();

		// 获取网元信息
		String netId = null;
		netId = (String) _mapQuery.get("netId");
		netList = tawwpNetDao.ListNet(netId);
		if (netList.size() > 0) {
			_mapQuery.put("netId", netList);
		}

		try {
			list = tawwpMonthPlanDao.searchMonthPlan(_mapQuery); // 获取符合条件的月度作业计划

			// 循环处理执行数据
			for (int i = 0; i < list.size(); i++) {
				tawwpMonthPlanVO = new TawwpMonthPlanVO();
				tawwpMonthPlan = (TawwpMonthPlan) list.get(i);
				
				/**
				 * 
				 */
				
				int flag = 0;
				if(!"".equals(_executeUser)){
				Set set =  tawwpMonthPlan.getTawwpMonthExecutes();
				// 判断页面选择的执行信息和数据库内的执行信息对照。如果有则把次作业计划作为一项
				for (Iterator it = set.iterator();it.hasNext();){ 
					TawwpMonthExecute content = (TawwpMonthExecute)it.next();
					if(StaticMethod.null2String(content.getExecuterType()).equals(_executeType)){
					  if(content.getExecuter()!=null){
						String[] userArray = content.getExecuter().split(",");
						for(int j=0;j<userArray.length;j++){
							System.out.println(userArray[j]);
							if(_executeUser.equals(userArray[j])){
								flag = 1;
								break;
							}
						  }
						}
					 }
				  }
				}
				if(!"".equals(_executeUser)&& flag==0){  // 如果没有跳回循环
					continue;
				}
				// 整理数据
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
						tawwpMonthPlan);

				tawwpMonthPlanVO.setCruser(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getCruser()));

				// 获取网元名称
				if (tawwpMonthPlan.getTawwpNet() != null) {
					tawwpMonthPlanVO
							.setNetName(StaticMethod.null2String(tawwpMonthPlan
									.getTawwpNet().getName()));
				} else {
					tawwpMonthPlanVO.setNetName("无网元");
				}
				tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpMonthPlanVO.getDeptId()));
				tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
						.getConstituteStateName(tawwpMonthPlanVO
								.getExecuteState()));
				tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
						.getConstituteStateName(tawwpMonthPlanVO
								.getConstituteState()));
				tawwpMonthPlanVO
						.setExecuteStateName(TawwpMonthPlanVO
								.getExecuteStateName(tawwpMonthPlanVO
										.getExecuteState()));

				newList.add(tawwpMonthPlanVO); // 放入列表
			}

			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("月度作业计划查询出现异常");
		} finally {
			list = null;
		}
	}

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-YEAR-PLAN-001) 根据查询信息map，查询符合条件的月度作业计划 条件包括 name
	 * 月度作业计划名称 deptId 部门 sysTypeId 系统类别 netTypeId 网元类型 yearFlag 年度 state 制定状态
	 * 
	 * @param _mapQuery
	 *            Map 查询条件
	 * @throws Exception
	 *             异常信息
	 * @return List 年度作业计划列表
	 */
	public List queryYearPlan(Map _mapQuery) throws Exception {
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();

		TawwpYearPlan tawwpYearPlan = null;
		TawwpYearPlanVO tawwpYearPlanVO = null;
		List list = null;
		List modelList = null;
		List newList = new ArrayList();

		// 获取网元信息
		String modelId = null;
		modelId = (String) _mapQuery.get("modelId");
		if (modelId != null) {
			modelList = tawwpModelPlanDao.listModelPlanByModelPlanIds(modelId);
			_mapQuery.put("modelId", modelList);
		}

		try {
			list = tawwpYearPlanDao.searchYearPlanLink(_mapQuery);

			// 循环处理执行数据
			for (int i = 0; i < list.size(); i++) {

				tawwpYearPlan = (TawwpYearPlan) list.get(i);
				tawwpYearPlanVO = new TawwpYearPlanVO();
				// 整理数据
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlanVO,
						tawwpYearPlan);

				tawwpYearPlanVO.setCruserName(tawwpUtilDAO
						.getUserName(tawwpYearPlanVO.getCruser()));
				tawwpYearPlanVO.setSysTypeName(tawwpUtilDAO
						.getSysTypeName(tawwpYearPlanVO.getSysTypeId()));
				tawwpYearPlanVO.setNetTypeName(tawwpUtilDAO
						.getNetTypeName(tawwpYearPlanVO.getNetTypeId()));
				tawwpYearPlanVO
						.setStateName(TawwpYearPlanVO.CONSTITUTESTATENAME[Integer
								.parseInt(tawwpYearPlanVO.getState())]);
				tawwpYearPlanVO.setDeptName(tawwpUtilDAO
						.getDeptName(tawwpYearPlanVO.getDeptId()));
				tawwpYearPlanVO.setNetListName(tawwpNetDao
						.getNetNameList(tawwpYearPlanVO.getNetList()));
				tawwpYearPlanVO.setCrtime(tawwpYearPlanVO.getCrtime()
						.substring(0, 10));
				newList.add(tawwpYearPlanVO); // 放入列表
			}

			return newList;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("年度作业计划查询出现异常");
		} finally {
			list = null;
		}
	}

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-SUBJECT-EXECUTE-001)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlan(String _yearFlag, String _monthFlag)
			throws TawwpException {
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		String startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
		String endDate = null;
		int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;

		if (_monthFlag.compareTo(TawwpUtil.getCurrentDateTime("MM")) >= 0) {
			endDate = _yearFlag + "-" + _monthFlag + "-"
					+ TawwpUtil.getMonthStr(day) + " 23:59:59";
		} else {
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
		}

		List list = null;
		Hashtable hashtable = new Hashtable();
		try {
			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, 0); // 获取全部执行内容
			this.manageStatCount(hashtable, list, 0);

			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, 1); // 获取全部按时执行内容
			this.manageStatCount(hashtable, list, 1);

			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, 2); // 获取全部超时执行内容
			this.manageStatCount(hashtable, list, 2);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}

		return hashtable;
	}

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-SUBJECT-EXECUTE-001)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _refreshFlag
	 *            boolean 刷新标志 true：重新生成 false：直接调用
	 * @throws TawwpException
	 *             异常信息
	 * @return String 生成统计页面路径
	 */
	public String statMonthPlan(String _yearFlag, String _monthFlag,
			boolean _refreshFlag) throws TawwpException {

		String currentYear = TawwpUtil.getCurrentDateTime("yyyy");
		String currentMonth = TawwpUtil.getCurrentDateTime("MM");
		String currentDay = TawwpUtil.getCurrentDateTime("dd");

		// 如果当前月小于查询月，没有统计数据
		if (_yearFlag.equals(currentYear)
				&& _monthFlag.compareTo(currentMonth) > 0) {
			return null;
		}

		String path = TawwpStaticVariable.wwwDir + TawwpStaticVariable.workplan
				+ TawwpStaticVariable.statYearDir + _yearFlag + File.separator
				+ _monthFlag + File.separator; // 组织文件存放路径

		String filename = "stat_all_" + _yearFlag + "_" + _monthFlag + ".jsp"; // 统计文件名称（按日期）
		String webPath = TawwpStaticVariable.statYearDir + _yearFlag
				+ File.separator + _monthFlag + File.separator; // 组织web返回路径

		String url = "../tawwpstat/statyearregion.do?yearflag=" + _yearFlag
				+ "&monthflag=" + _monthFlag + "&deptid=";
		String urltemp = null;

		// 如果是当前月的统计查询，则统计信息文件信息成为：stat_all_2005_09_02.htm
		if (currentYear.equals(_yearFlag) && currentMonth.equals(_monthFlag)) {
			filename = "stat_all_" + _yearFlag + "_" + _monthFlag + "_"
					+ currentDay + ".jsp"; // 统计文件名称（按日期）
		}

		DataOutputStream output = null;
		StringBuffer str = null;
		File file = null;

		Hashtable hashtable = null;
		Enumeration enumeration = null;
		Object object = null;
		TawwpStatVO tawwpStatVO = null;

		// 创建对应的路径文件夹
		file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(path + filename);

		try {
			// 判断是否需要刷新，或者文件不存在
			if (_refreshFlag || !file.exists()) {
				str = new StringBuffer();

				str.append(TawwpStatVO.writeHeadHTML(_yearFlag, _monthFlag,
						true)); // 文件头

				hashtable = this.statMonthPlan(_yearFlag, _monthFlag);
				enumeration = hashtable.keys();

				// 循环增加统计数据
				while (enumeration.hasMoreElements()) {
					object = enumeration.nextElement();
					tawwpStatVO = (TawwpStatVO) hashtable.get(object);

					urltemp = url + tawwpStatVO.getTawDept().getDeptId();
					str.append(tawwpStatVO.writeHTML(urltemp));
				}

				str.append(TawwpStatVO.writeFootHTML()); // 文件尾

				output = new DataOutputStream(new FileOutputStream(file)); // 按指定路径生成统计html文件

				output.writeBytes(new String(str.toString().getBytes("GB2312"),
						"ISO8859-1"));
				// output.writeUTF(str.toString()); <% out.print(new
				// String(\"年\".getBytes(\"ISO-8859-1\"),\"utf-8\"));%>

				output.close(); // 关闭输出流
			}

			return "/" + webPath + filename;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		} finally {
			str = null;
			hashtable = null;
			output = null;
			file = null;
		}
	}

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-SUBJECT-EXECUTE-002)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _deptId
	 *            String 部门信息（内部编号）
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlanByRegion(String _yearFlag,
			String _monthFlag, String _deptId) throws TawwpException {
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		ITawSystemDeptManager tawDeptDAO = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		String startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
		String endDate = null;
		int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;

		if (_monthFlag.compareTo(TawwpUtil.getCurrentDateTime("MM")) >= 0) {
			endDate = _yearFlag + "-" + _monthFlag + "-"
					+ TawwpUtil.getMonthStr(day) + " 23:59:59";
		} else {
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
		}

		List list = null;
		Hashtable hashtable = new Hashtable();
		try {
			// String _deptIdStr = tawDeptDAO.//.getRegionDept(_deptId, 0);

			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, _deptId, 0); // 获取全部执行内容
			this.manageStatCountByRegion(hashtable, list, 0);

			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, _deptId, 1); // 获取全部按时执行内容
			this.manageStatCountByRegion(hashtable, list, 1);

			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, _deptId, 2); // 获取全部超时执行内容
			this.manageStatCountByRegion(hashtable, list, 2);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}

		return hashtable;
	}

	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-SUBJECT-EXECUTE-002)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _deptId
	 *            String 部门信息（内部编号）
	 * @param _refreshFlag
	 *            boolean 刷新标志
	 * @throws TawwpException
	 *             异常信息
	 * @return String 生成统计页面路径
	 */
	public String statMonthPlanByRegion(String _yearFlag, String _monthFlag,
			String _deptId, boolean _refreshFlag) throws TawwpException {

		String path = TawwpStaticVariable.wwwDir + TawwpStaticVariable.workplan
				+ TawwpStaticVariable.statYearDir + _yearFlag + File.separator
				+ _monthFlag + File.separator; // 组织文件存放路径

		String filename = "stat_all_" + _deptId + "_" + _yearFlag + "_"
				+ _monthFlag + ".jsp"; // 统计文件名称（按日期）

		String webPath = "/" + TawwpStaticVariable.statYearDir + _yearFlag
				+ File.separator + _monthFlag + File.separator; // 组织web返回路径

		String url = "../tawwpstat/statyeardept.do?yearflag=" + _yearFlag
				+ "&monthflag=" + _monthFlag + "&deptid=";

		String currentYear = TawwpUtil.getCurrentDateTime("yyyy");
		String currentMonth = TawwpUtil.getCurrentDateTime("MM");
		String currentDay = TawwpUtil.getCurrentDateTime("dd");

		// 如果是当前月的统计查询，则统计信息文件信息成为：stat_all_2005_09_02.htm
		if (currentYear.equals(_yearFlag) && currentMonth.equals(_monthFlag)) {
			filename = "stat_all_" + _deptId + "_" + _yearFlag + "_"
					+ _monthFlag + "_" + currentDay + ".jsp"; // 统计文件名称（按日期）
		}

		DataOutputStream output = null;
		StringBuffer str = null;
		File file = null;

		Hashtable hashtable = null;
		Enumeration enumeration = null;
		Object object = null;
		TawwpStatVO tawwpStatVO = null;

		// 创建对应的路径文件夹
		file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(path + filename);

		try {
			// 判断是否需要刷新，或者文件不存在
			if (_refreshFlag || !file.exists()) {
				str = new StringBuffer();

				str.append(TawwpStatVO.writeHeadHTML(_yearFlag, _monthFlag,
						false)); // 文件头

				hashtable = this.statMonthPlanByRegion(_yearFlag, _monthFlag,
						_deptId);
				enumeration = hashtable.keys();

				// 循环增加统计数据
				while (enumeration.hasMoreElements()) {
					object = enumeration.nextElement();
					tawwpStatVO = (TawwpStatVO) hashtable.get(object);
					url = url + tawwpStatVO.getTawDept().getDeptId();
					str.append(tawwpStatVO.writeHTML(url));
				}

				str.append(TawwpStatVO.writeFootHTML()); // 文件尾

				output = new DataOutputStream(new FileOutputStream(file)); // 按指定路径生成统计html文件
				output.writeBytes(new String(str.toString().getBytes("GB2312"),
						"ISO8859-1")); // 进行编码处理
				output.close(); // 关闭输出流
			}

			return webPath + filename;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		} finally {
			str = null;
			hashtable = null;
			output = null;
			file = null;
		}
	}

	/**
	 * 业务逻辑：查询作业计划执行情况(具体月度作业计划)(QUERY-SUBJECT-EXECUTE-003)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _deptId
	 *            String 部门信息（内部编号）
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public List statMonthPlanByMonthPlan(String _yearFlag, String _monthFlag,
			String _deptId) throws TawwpException {
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		List list = null;
		List newList = new ArrayList();

		try {
			list = tawwpExecuteContentDao.getTawwpMonthPlanByExecute(_deptId,
					_yearFlag, _monthFlag); // 获取月度作业计划编号(执行存在问题的)

			// 循环处理月度作业计划
			for (int i = 0; i < list.size(); i++) {
				tawwpMonthPlan = (TawwpMonthPlan) list.get(i);

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
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
						.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteState())));
				// 获取执行负责人姓名
				tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				newList.add(tawwpMonthPlanVO);
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		} finally {
			list = null;
		}
		return newList;
	}

	/**
	 * 内部逻辑：处理统计数据,合并各部门按地市。
	 * 
	 * @param _hashtable
	 *            Hashtable 统计数据Hash
	 * @param _list
	 *            List 统计数据里列表
	 * @param _state
	 *            int 状态（state －1：全部 0：未完成，1：已完成 2：超时）
	 * @throws TawwpException
	 *             异常信息
	 */
	private void manageStatCount(Hashtable _hashtable, List _list, int _state)
			throws TawwpException {
		ITawSystemDeptManager tawDeptDAO = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		TawwpStatVO tawwpStatVO = null;
		TawSystemDept tawDeptRegion = null;
		TawSystemDept tawDept = null;

		Iterator iterator = null;
		iterator = _list.iterator();

		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据
				tawDept = tawDeptDAO.getDeptinfobydeptid(
						(String) countObject[0], "0");// 获取部门对象
				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[0]) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put((String) countObject[0], tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable
							.get((String) countObject[0]); // 获取
				}

				// 封装数据vo
				tawwpStatVO.setTawDept(tawDept);
				tawwpStatVO.putCycleCount((String) countObject[1],
						((Integer) countObject[2]).intValue(), _state);
			}
		} catch (Exception e) {
			throw new TawwpException("作业计划统计出现异常");
		}
	}

	/**
	 * 内部逻辑：处理统计数据，地市中各部门各自显示
	 * 
	 * @param _hashtable
	 *            Hashtable 统计数据Hash
	 * @param _list
	 *            List 统计数据里列表
	 * @param _state
	 *            int 状态（state －1：全部 0：未完成，1：已完成 2：超时）
	 * @throws TawwpException
	 *             异常信息
	 */
	private void manageStatCountByRegion(Hashtable _hashtable, List _list,
			int _state) throws TawwpException {
		ITawSystemDeptManager tawDeptDAO = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");

		// TawDeptDAO tawDeptDAO = new TawDeptDAO();
		TawwpStatVO tawwpStatVO = null;
		TawSystemDept tawDept = null;

		Iterator iterator = null;
		iterator = _list.iterator();

		String dept = null; // 记录部门内部编号

		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据
				dept = (String) countObject[0];
				tawDept = tawDeptDAO.getDeptinfobydeptid(dept, "0"); // 获取部门对象

				// 如果当前地市没有统计hash
				if (_hashtable.get(dept) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put(dept, tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable.get(dept); // 获取
				}

				// 封装数据vo
				tawwpStatVO.setTawDept(tawDept);
				tawwpStatVO.putCycleCount((String) countObject[1],
						((Integer) countObject[2]).intValue(), _state);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划地市统计保存出现异常");
		}
	}

	/**
	 * 业务逻辑：查询作业计划执行情况(GATHER-STENCIL-LIST-001)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _yearPlanId
	 *            String 年度作业计划标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpYearPlanVO 年度作业计划信息（包括月度汇总信息）
	 */
	public TawwpYearPlanVO reportMonthPlan(String _yearFlag, String _monthFlag,
			String _yearPlanId) throws Exception {
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		ITawSystemDeptManager tawDeptDAO = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");

		// TawDeptDAO tawDeptDAO = new TawDeptDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpYearPlan tawwpYearPlan = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpExecuteReport tawwpExecuteReport = null;
		TawSystemDept tawDeptRegion = null;
		TawSystemDept tawDept = null;

		TawwpYearPlanVO tawwpYearPlanVO = new TawwpYearPlanVO();
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpExecuteReportVO tawwpExecuteReportVO = null;

		Hashtable hashtable = new Hashtable(); // 月度汇总的结构
		List reportList = null;
		List list = null;
		List newList = null;

		String tempDeptId = "";
		String deptId = null;

		tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId); // 获取年度作业计划对象
		MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlanVO, tawwpYearPlan); // 转换年度作业计划信息
		tawwpYearPlanVO.setMonthPlanHash(hashtable); // 关联月度汇总信息

		list = tawwpMonthPlanDao.listMonthPlan(_yearFlag, _monthFlag,
				_yearPlanId, "-1"); // 获取月度作业计划集合

		// 循环处理月度作业计划
		for (int i = 0; i < list.size(); i++) {
			tawwpMonthPlan = (TawwpMonthPlan) list.get(i); // 获取一个月度作业计划
			reportList = tawwpMonthPlanDao.filterTawwpExecuteReprot(
					tawwpMonthPlan, "1"); // 获取月度作业计划月报信息

			tawwpExecuteReport = (TawwpExecuteReport) reportList.get(0);

			tawwpMonthPlanVO = new TawwpMonthPlanVO();
			tawwpExecuteReportVO = new TawwpExecuteReportVO();

			MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
					tawwpMonthPlan); // 转换月度作业计划
			tawwpMonthPlanVO.setUserName(tawwpUtilDAO
					.getUserName(tawwpMonthPlanVO.getCruser()));

			MyBeanUtils.copyPropertiesFromDBToPage(tawwpExecuteReportVO,
					tawwpExecuteReport); // 转换月报信息

			tawwpMonthPlanVO.setTawwpExecuteReportMonthVO(tawwpExecuteReportVO);

			// tawDept =
			// tawDeptDAO.getDeptinfobydeptid(Integer.parseInt(tawwpMonthPlanVO.getDeptId()));
			// //获取部门对象
			tawDept = tawDeptDAO.getDeptinfobydeptid(tawwpMonthPlanVO
					.getDeptId(), "0"); // 获取部门对象
			deptId = String.valueOf(tawDept.getId()).substring(0, 3); // 获取当前部门所在地市的内部编号，取当前部门的内部编号的前三位

			// 判断当前部门对象是否是上个月度作业对应的部门相同
			if (!tempDeptId.equals(deptId)) {
				// tawDeptRegion = tawDeptDAO.retrieveById(deptId);
				// //通过部门内部编号，获取部门对象。
				tawDeptRegion = tawDeptDAO.getDeptinfobydeptid(deptId, "0"); // 通过部门内部编号，获取部门对象。

				tempDeptId = deptId;
			}

			newList = (List) hashtable.get(tawDeptRegion); // 通过部门对象获取当前部门的月度作业计划集合

			// 如果集合为空，创建一个新集合，然后放入总体结构中
			if (newList == null) {
				newList = new ArrayList();
				hashtable.put(tawDeptRegion, newList);
			}
			newList.add(tawwpMonthPlanVO); // 将月度作业计划加入部门集合中
		}

		return tawwpYearPlanVO;
	}

	/**
	 * 获取年度作业计划的所有网元信息
	 * 
	 * @param _yearPlanId
	 *            String 年度作业计划编号
	 * @throws Exception
	 * @return TawwpStatYearPlanVO
	 */
	public List reportYearPlanNet(String _yearPlanId) throws Exception {
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();

		TawwpYearPlan tawwpYearPlan = null;
		List list = null;

		tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId); // 获取年度作业计划信息

		return list;
	}

	/**
	 * 业务逻辑：年度作业计划执行情况(STAT-YEAR-001)
	 * 
	 * @param _yearPlanId
	 *            String 年度
	 * @param _netId
	 *            String 网元编号
	 * @throws Exception
	 *             操作异常
	 * @return TawwpStatYearPlanVO 年度统计信息
	 */
	public TawwpStatYearPlanVO reportYearPlan(String _yearPlanId, String _netId)
			throws Exception {
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
		// TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();

		TawwpYearPlan tawwpYearPlan = null;
		TawwpYearExecute tawwpYearExecute = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthExecute tawwpMonthExecute = null;
		TawwpNet tawwpNet = null;

		List list = null;
		Hashtable hashtable = new Hashtable();
		Hashtable yearExecuteHashtable = null;

		Iterator monthExecuteIterator = null;

		TawwpStatYearPlanVO tawwpStatYearPlanVO = new TawwpStatYearPlanVO();

		tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId); // 获取年度作业计划信息
		tawwpNet = tawwpNetDao.loadNet(_netId); // 获取网元信息

		tawwpStatYearPlanVO.setYearPlanId(_yearPlanId); // 年度作业计划编号
		tawwpStatYearPlanVO.setYearPlanName(StaticMethod
				.null2String(tawwpYearPlan.getName())); // 年度作业计划名称
		tawwpStatYearPlanVO.setYearFlag(tawwpYearPlan.getYearFlag()); // 年度

		list = tawwpMonthPlanDao.listByYearPlanNet(tawwpYearPlan, tawwpNet);

		// 循环处理某个网元的所有符合年度作业的所有月度作业计划
		for (int i = 0; i < list.size(); i++) {
			tawwpMonthPlan = (TawwpMonthPlan) list.get(i); // 获取一个月度作业计划
			monthExecuteIterator = tawwpMonthPlan.getTawwpMonthExecutes()
					.iterator(); // 获取改月度的所有月度执行内容

			// 循环处理每个月度作业计划执行内容
			while (monthExecuteIterator.hasNext()) {
				tawwpMonthExecute = (TawwpMonthExecute) monthExecuteIterator
						.next(); // 获取某个月度作业计划的执行内容

				tawwpYearExecute = tawwpMonthExecute.getTawwpYearExecute(); // 获取月度作业计划执行内容对应的年度作业计划执行内容

				yearExecuteHashtable = (Hashtable) hashtable
						.get(tawwpYearExecute); // 获取统计hash中对应的执行内容hash

				// 如果执行内容hash不存在
				if (yearExecuteHashtable == null) {
					yearExecuteHashtable = new Hashtable();
					hashtable.put(tawwpYearExecute, yearExecuteHashtable);
				}
				yearExecuteHashtable.put(tawwpMonthPlan.getMonthFlag(),
						tawwpMonthExecute); // 将月度为key，存入当前月度作业计划执行内容
			}
		}

		tawwpStatYearPlanVO.setYearExecutehashtable(hashtable);
		return tawwpStatYearPlanVO;
	}

	/**
	 * 业务逻辑:执行意见填写保存(PUTIN-EXECUTE-ASSESS-001)
	 * 
	 * @param _deptId
	 *            String 考核部门编号
	 * @param _checkUser
	 *            String 考核人登录名
	 * @param _content
	 *            String 执行意见
	 * @param _monthPlanId
	 *            String 月度作业计划编号
	 * @throws Exception
	 *             异常
	 */
	public void saveExecuteAssess(String _deptId, String _checkUser,
			String _content, String _state, String _monthPlanId)
			throws Exception {

		// 初始化数据,创建DAO对象
		// TawwpExecuteAssessDAO tawwpExecuteAssessDAO = new
		// TawwpExecuteAssessDAO();
		// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();

		// 月度作业计划对象
		TawwpMonthPlan tawwpMonthPlan = null;

		boolean flag = true;

		try {

			// 获取月度作业计划对象
			tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(_monthPlanId);

			// 封装月度作业计划考核信息对象
			TawwpExecuteAssess tawwpExecuteAssess = new TawwpExecuteAssess(
					_deptId, _checkUser, _content, _state, TawwpUtil
							.getCurrentDateTime(), TawwpUtil
							.getCurrentDateTime(), "", "", tawwpMonthPlan, null);

			// 保存作业计划考核信息
			tawwpExecuteAssessDao.saveExecuteAssess(tawwpExecuteAssess);

		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("执行意见保存出现异常");
		}
	}

	public String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
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
	 * @param tawwpExecuteAssessDao
	 *            the tawwpExecuteAssessDao to set
	 */
	public void setTawwpExecuteAssessDao(
			ITawwpExecuteAssessDao tawwpExecuteAssessDao) {
		this.tawwpExecuteAssessDao = tawwpExecuteAssessDao;
	}
	// TODO 统计开始  向下 add by 龚玉峰
	
	/**
	 * 按照网元统计
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlan(String systype, String _netType,
			String _netList, String _yearFlag, String _monthFlag)
			throws TawwpException {
		// 拼写时间字符串
		String startDate = null;
		String endDate = null;
		if ("0".equals(_monthFlag)) {
			startDate = _yearFlag + "-01-01 00:00:00";
			endDate = _yearFlag + "-12-31 23:59:59";
		} else {
			startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
			int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
			
		}
		List list = null;
		Hashtable hashtable = new Hashtable();
		_netList = getNetListToString(_netList);
		try {
			list = tawwpExecuteContentDao.statExecuteContent(systype, _netType,
					_netList, startDate, endDate, 0); // 获取全部执行内容
			this.manageStatCountNet(hashtable, list, 0);

			list = tawwpExecuteContentDao.statExecuteContent(systype, _netType,
					_netList, startDate, endDate, 1); // 获取全部按时执行内容
			this.manageStatCountNet(hashtable, list, 1);

			list = tawwpExecuteContentDao.statExecuteContent(systype, _netType,
					_netList, startDate, endDate, 2); // 获取全部超时执行内容
			this.manageStatCountNet(hashtable, list, 2);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}

		return hashtable;
	}

	/**
	 * @param netlist
	 * @return
	 */
	public String getNetListToString(String netlist) {
		String netStr = "";
		try {
			if (!"".equals(netlist) && netlist != null) {
				// 按照逗号隔开
				String[] netArray = netlist.split(",");
				for (int i = 0; i < netArray.length; i++) {
					// 拼成'abc','bcd'的格式
					netStr = netStr + "'" + netArray[i] + "',";
				}
				netStr = netStr.substring(0, netStr.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			netStr = "";
		}
		return netStr;

	}

	/**
	 * 内部逻辑：处理统计数据,合并各部门按地市。
	 * 
	 * @param _hashtable
	 *            Hashtable 统计数据Hash
	 * @param _list
	 *            List 统计数据里列表
	 * @param _state
	 *            int 状态（state －1：全部 0：未完成，1：已完成 2：超时）
	 * @throws TawwpException
	 *             异常信息
	 */
	private void manageStatCountNet(Hashtable _hashtable, List _list, int _state)
			throws TawwpException {

		TawwpStatVO tawwpStatVO = null;
		TawSystemDept tawDeptRegion = null;
		TawSystemDept tawDept = null;
		TawwpNet tawwpNet = null;
		TawwpMonthPlan tawwpMonthPlan = null;

		Iterator iterator = null;
		iterator = _list.iterator();
		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据

				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[3]) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put((String) countObject[3], tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable
							.get((String) countObject[3]); // 获取
				}
				// 取网元名称
				if (countObject[4] == null || "".equals(countObject[4])) {
					tawwpNet = new TawwpNet();
					tawwpNet.setName("无网元");
				} else {
					tawwpNet = tawwpNetDao.loadNet(countObject[4].toString());
				}
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(countObject[3]
						.toString());
				// 封装数据vo
				Integer normalcount = tawwpExecuteContentDao.statNormalCount(countObject[3].toString(),countObject[1].toString(),1,_state);
				tawwpStatVO.setTawWpMonthPlan(tawwpMonthPlan);
				tawwpStatVO.setTawwpNet(tawwpNet);
				tawwpStatVO.setTawDept(tawDept);
				tawwpStatVO.putCycleCountNext((String) countObject[1],
						((Integer) countObject[2]).intValue(), _state,normalcount.intValue());
			}
		} catch (Exception e) {
			throw new TawwpException("作业计划统计出现异常");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.mgr.ITawwpStatMgr#statMonthPlanRoom(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public Hashtable statMonthPlanRoom(String _roomid, String _yearFlag,
			String _monthFlag) throws TawwpException {
		String startDate = null;
		String endDate = null;
		if ("0".equals(_monthFlag)) {
			startDate = _yearFlag + "-01-01 00:00:00";
			endDate = _yearFlag + "-12-31 23:59:59";
		} else {
			startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
			int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
			
		}

		List list = null;
		Hashtable hashtable = new Hashtable();
		_roomid = getNetListToString(_roomid);
		try {
			list = tawwpExecuteContentDao.statExecuteContentRoom(_roomid,
					startDate, endDate, 0); // 获取全部执行内容
			this.manageStatCountRoom(hashtable, list, 0);

			list = tawwpExecuteContentDao.statExecuteContentRoom(_roomid,
					startDate, endDate, 1); // 获取全部按时执行内容
			this.manageStatCountRoom(hashtable, list, 1);

			list = tawwpExecuteContentDao.statExecuteContentRoom(_roomid,
					startDate, endDate, 2); // 获取全部超时执行内容
			this.manageStatCountRoom(hashtable, list, 2);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}

		return hashtable;

	}

	/**
	 * 内部逻辑：处理统计数据,合并各部门按地市。
	 * 
	 * @param _hashtable
	 *            Hashtable 统计数据Hash
	 * @param _list
	 *            List 统计数据里列表
	 * @param _state
	 *            int 状态（state －1：全部 0：未完成，1：已完成 2：超时）
	 * @throws TawwpException
	 *             异常信息
	 */
	private void manageStatCountRoom(Hashtable _hashtable, List _list,
			int _state) throws TawwpException {

		TawwpStatVO tawwpStatVO = null;
		TawSystemDept tawDept = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawSystemCptroom tawSystemCptroom = null;
		Iterator iterator = null;
		iterator = _list.iterator();
		ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemCptroomManager");
		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据

				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[3]
						+ (String) countObject[4]) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put((String) countObject[3]
							+ (String) countObject[4], tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable
							.get((String) countObject[3]
									+ (String) countObject[4]); // 获取
				}
				// 取网元名称

				tawSystemCptroom = mgr.getTawSystemCptroom(countObject[4]
						.toString());
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(countObject[3]
						.toString());
				// 封装数据vo
				Integer normalcount = tawwpExecuteContentDao.statNormalCount(countObject[3].toString(),countObject[1].toString(),1,_state);
				tawwpStatVO.setTawWpMonthPlan(tawwpMonthPlan);
				tawwpStatVO.setTawSystemCptroom(tawSystemCptroom);
				tawwpStatVO.setTawDept(tawDept);
				tawwpStatVO.putCycleCountNext((String) countObject[1],
						((Integer) countObject[2]).intValue(), _state,normalcount.intValue());
			}
		} catch (Exception e) {
			throw new TawwpException("作业计划统计出现异常");
		}
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.workplan.mgr.ITawwpStatMgr#statMonthPlanUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Hashtable statMonthPlanUser(String _userid, String _deptid,
			String _yearFlag, String _monthFlag) throws TawwpException {
		String startDate = null;
		String endDate = null;
		if ("0".equals(_monthFlag)) {
			startDate = _yearFlag + "-01-01 00:00:00";
			endDate = _yearFlag + "-12-31 23:59:59";
		} else {
			startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
			int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
			 
		}
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		
		ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDeptManager");
		List list = null;
		List userList = null;
		Hashtable hashtable = new Hashtable();

		try {
			if (!"".equals(_userid)) {
				String[] userArray = _userid.split(",");
				for (int i = 0; i < userArray.length; i++) {
					list = tawwpExecuteContentDao.statExecuteContentUser(
							userArray[i], startDate, endDate, 0); // 获取全部执行内容
					this.manageStatCountUser(hashtable, list, 0, userArray[i]);

					list = tawwpExecuteContentDao.statExecuteContentUser(
							userArray[i], startDate, endDate, 1); // 获取全部按时执行内容
					this.manageStatCountUser(hashtable, list, 1, userArray[i]);

					list = tawwpExecuteContentDao.statExecuteContentUser(
							userArray[i], startDate, endDate, 2); // 获取全部超时执行内容
					this.manageStatCountUser(hashtable, list, 2, userArray[i]);
				}
			} else {
				userList = userManager.getUserLike(mgr.getDeptinfobydeptid(_deptid,"0").getLinkid());
				for (Iterator it = userList.iterator(); it.hasNext();) {
					TawSystemUser user = (TawSystemUser) it.next();
					list = tawwpExecuteContentDao.statExecuteContentUser(user
							.getUserid(), startDate, endDate, 0); // 获取全部执行内容
					this.manageStatCountUser(hashtable, list, 0, user
							.getUserid());

					list = tawwpExecuteContentDao.statExecuteContentUser(user
							.getUserid(), startDate, endDate, 1); // 获取全部按时执行内容
					this.manageStatCountUser(hashtable, list, 1, user
							.getUserid());

					list = tawwpExecuteContentDao.statExecuteContentUser(user
							.getUserid(), startDate, endDate, 2); // 获取全部超时执行内容
					this.manageStatCountUser(hashtable, list, 2, user
							.getUserid());

				}
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
		return hashtable;
	}
	
	/* (non-Javadoc)
	 * @see com.boco.eoms.workplan.mgr.ITawwpStatMgr#statMonthPlanUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Hashtable statMonthPlanUserByDept(String _deptid,String workplanid, String _yearFlag, String _monthFlag) throws TawwpException {
		String startDate = null;
		String endDate = null;
		if ("0".equals(_monthFlag)) {
			startDate = _yearFlag + "-01-01 00:00:00";
			endDate = _yearFlag + "-12-31 23:59:59";
		} else {
			startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
			int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;
 
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";

		}
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		
		ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDeptManager");
		List list = null;
		List userList = null;
		Hashtable hashtable = new Hashtable();

		try {			
				//userList = userManager.getUserLike(mgr.getDeptinfobydeptid(_deptid,"0").getLinkid());
				userList = userManager.getUserBydeptids(_deptid); // 改成取本部门内的用户
				
				for (Iterator it = userList.iterator(); it.hasNext();) {
					TawSystemUser user = (TawSystemUser) it.next();
					list = tawwpExecuteContentDao.statExecuteContentUserByDept(user
							.getUserid(),_deptid, workplanid,startDate, endDate, 0); // 获取全部执行内容
					this.manageStatCountUserByDept(hashtable, list, 0, user
							.getUserid(),_deptid);

					list = tawwpExecuteContentDao.statExecuteContentUserByDept(user
							.getUserid(),_deptid,workplanid, startDate, endDate, 1); // 获取全部按时执行内容
					this.manageStatCountUserByDept(hashtable, list, 1, user
							.getUserid(),_deptid);

					list = tawwpExecuteContentDao.statExecuteContentUserByDept(user
							.getUserid(),_deptid, workplanid,startDate, endDate, 2); // 获取全部超时执行内容
					this.manageStatCountUserByDept(hashtable, list, 2, user
							.getUserid(),_deptid);

			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
		return hashtable;
	}
	
	
	/* (non-Javadoc)
	 * @see com.boco.eoms.workplan.mgr.ITawwpStatMgr#statMonthPlanUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Hashtable statMonthPlanDept(String _userid, String _deptid,
			String _yearFlag, String _monthFlag) throws TawwpException {
		String startDate = null;
		String endDate = null;
		if ("0".equals(_monthFlag)) {
			startDate = _yearFlag + "-01-01 00:00:00";
			endDate = _yearFlag + "-12-31 23:59:59";
		} else {
			startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
			int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
			//}
		}
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		
/*		ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDeptManager");*/
		List list = null;
		List userList = null;
		Hashtable hashtable = new Hashtable();

		try {
			if (!"".equals(_userid)) {
			 
			} else {
				//userList = userManager.getUserLike(mgr.getDeptinfobydeptid(_deptid,"0").getLinkid());
				userList = userManager.getUserBydeptids(_deptid);   // 改成只取本部门的
				//getUserBydeptids
				for (Iterator it = userList.iterator(); it.hasNext();) {
					TawSystemUser user = (TawSystemUser) it.next();
					list = tawwpExecuteContentDao.statExecuteContentUser(
							user.getUserid(), startDate, endDate, 0); // 获取全部执行内容

					this.manageStatCountDept(hashtable, list, 0, _deptid);

					list = tawwpExecuteContentDao.statExecuteContentUser(user.getUserid(), startDate, endDate, 1); // 获取全部按时执行内容
					this.manageStatCountDept(hashtable, list, 1, _deptid);

					list = tawwpExecuteContentDao.statExecuteContentUser(user.getUserid(), startDate, endDate, 2); // 获取全部超时执行内容
					this.manageStatCountDept(hashtable, list, 2, _deptid);

				}
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
		return hashtable;
	}

	/**
	 * 根据人员取出人员的作业计划信息
	 * @param _hashtable
	 * @param _list
	 * @param _state
	 * @param _userid
	 * @throws TawwpException
	 */
	private void manageStatCountUser(Hashtable _hashtable, List _list,
			int _state, String _userid) throws TawwpException {

		TawwpStatVO tawwpStatVO = null;
		TawSystemUser tawSystemUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawSystemCptroom tawSystemCptroom = null;
		TawSystemDept tawDept = null;
		Iterator iterator = null;
		iterator = _list.iterator();
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");

		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据

				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[2] + "" + _userid) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put(((String) countObject[2] + "" + _userid),
							tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable
							.get(((String) countObject[2]) + "" + _userid); // 获取
				}

				tawSystemUser = userManager.getTawSystemUserByuserid(_userid);
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(countObject[2]
						.toString());
				
				// 封装数据vo
				tawwpStatVO.setTawWpMonthPlan(tawwpMonthPlan);
				tawwpStatVO.setTawSystemCptroom(tawSystemCptroom);
				tawwpStatVO.setTawDept(tawDept);
				tawwpStatVO.setTawSystemUser(tawSystemUser);
				Integer normalcount = tawwpExecuteContentDao.statNormalCount(countObject[2].toString(),countObject[0].toString(),1,_state,_userid);
				tawwpStatVO.putCycleCountNext((String) countObject[0],
						((Integer) countObject[1]).intValue(), _state,normalcount.intValue());
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
	}
	
	/**
	 * 根据部门取出人员的作业计划信息
	 * @param _hashtable
	 * @param _list
	 * @param _state
	 * @param _userid
	 * @throws TawwpException
	 */
	private void manageStatCountUserByDept(Hashtable _hashtable, List _list,
			int _state, String _userid,String _deptid) throws TawwpException {

		TawwpStatVO tawwpStatVO = null;
		TawSystemUser tawSystemUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawSystemCptroom tawSystemCptroom = null;
		TawSystemDept tawDept = null;
		Iterator iterator = null;
		iterator = _list.iterator();
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");

		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据

				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[1] + "-" + _userid+"-"+_deptid) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put(((String) countObject[1] + "-" + _userid+"-"+_deptid),
							tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable
							.get(((String) countObject[1]) + "-" + _userid+"-"+_deptid); // 获取
				}

				tawSystemUser = userManager.getTawSystemUserByuserid(_userid);
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(countObject[1]
						.toString());
				
				// 封装数据vo
				
				// 改成如果在_hashtable內存在这个月计划标示，则不在存储
				if(tawwpStatVO.getTawWpMonthPlan()==null){
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(countObject[2]
						.toString());
				tawwpStatVO.setTawWpMonthPlan(tawwpMonthPlan);
				}
				// 改成如果在	hashtable 内存在部门标示。 则不在存储
				if(tawwpStatVO.getTawSystemUser()==null){
					tawSystemUser = userManager.getTawSystemUserByuserid(_userid);
					tawwpStatVO.setTawSystemUser(tawSystemUser);
				}
				tawwpStatVO.putCycleCountNext("0",
						((Integer) countObject[0]).intValue(), _state,0);
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
	}
	  
	/**
	 * 根据部门取出作业计划信息
	 * @param _hashtable
	 * @param _list
	 * @param _state
	 * @param _userid
	 * @throws TawwpException
	 */
	private void manageStatCountDept(Hashtable _hashtable, List _list,
			int _state, String _deptid) throws TawwpException {

		TawwpStatVO tawwpStatVO = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawSystemDept tawDept = null;
		Iterator iterator = null;
		iterator = _list.iterator();
		ITawSystemDeptManager deptmgr = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据

				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[2] + "_" + _deptid) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put(((String) countObject[2] + "_" + _deptid),
							tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable
							.get(((String) countObject[2]) + "_" + _deptid); // 获取
				}

				// 封装数据vo
				if(tawwpStatVO.getTawWpMonthPlan()==null){  // 改成如果在_hashtable內存在这个月计划标示，则不在存储
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(countObject[2]
						.toString());
				tawwpStatVO.setTawWpMonthPlan(tawwpMonthPlan);
				}
				// 改成如果在	hashtable 内存在部门标示。 
				if(tawwpStatVO.getTawDept()==null){
				tawDept = deptmgr.getDeptinfobydeptid(_deptid, "0");
				tawwpStatVO.setTawDept(tawDept);
				}
				tawwpStatVO.putCycleCountNext("0",
						((Integer) countObject[1]).intValue(), _state,0);
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.boco.eoms.workplan.mgr.ITawwpStatMgr#statMonthPlanTime(java.lang.String, java.lang.String)
	 */
	public Hashtable statMonthPlanTime(String _yearFlag, String _monthFlag) throws TawwpException{
		String startDate = null;
		String endDate = null;
		List list = null;
		Hashtable hashtable = new Hashtable();
		String month = "";
 
		try {
			if ("0".equals(_monthFlag)) {
				for(int i=1;i<=12;i++){
					if(i<10) 
						month = "0"+i;
					else 
						month = "" +i;
					 
					startDate = _yearFlag + "-"+month+"-01 00:00:00";
					endDate   = _yearFlag + "-"+month+"-31 23:59:59";
					list = tawwpExecuteContentDao.statExecuteContentTime(startDate, endDate, 0); // 获取全部执行内容
					this.manageStatCountTime(hashtable, list, 0,_yearFlag,month);

					list = tawwpExecuteContentDao.statExecuteContentTime(startDate, endDate, 1); // 获取全部按时执行内容
					this.manageStatCountTime(hashtable, list, 1,_yearFlag,month);

					list = tawwpExecuteContentDao.statExecuteContentTime(startDate, endDate, 2); // 获取全部超时执行内容
					this.manageStatCountTime(hashtable, list, 2,_yearFlag,month);
				}
				
			} else {
					startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
					int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;
					endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
					 
					list = tawwpExecuteContentDao.statExecuteContentTime(startDate, endDate, 0); // 获取全部执行内容
					this.manageStatCountTime(hashtable, list, 0,_yearFlag,_monthFlag);
	
					list = tawwpExecuteContentDao.statExecuteContentTime(startDate, endDate, 1); // 获取全部按时执行内容
					this.manageStatCountTime(hashtable, list, 1,_yearFlag,_monthFlag);
	
					list = tawwpExecuteContentDao.statExecuteContentTime(startDate, endDate, 2); // 获取全部超时执行内容
					this.manageStatCountTime(hashtable, list, 2,_yearFlag,_monthFlag);
		 
				
			}
			 
					
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
		return hashtable;
	}
	
	

	/**
	 * 根据人员取出人员的作业计划信息
	 * @param _hashtable
	 * @param _list
	 * @param _state
	 * @param _userid
	 * @throws TawwpException
	 */
	private void manageStatCountTime(Hashtable _hashtable, List _list,
			int _state, String _yearFlag,String _monthFlag) throws TawwpException {

		TawwpStatVO tawwpStatVO = null;
		TawSystemUser tawSystemUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;
		TawSystemCptroom tawSystemCptroom = null;
		 
		Iterator iterator = null;
		iterator = _list.iterator();
		try {
			// 循环对查询数据进行处理
			while (iterator.hasNext()) {
				Object[] countObject = (Object[]) iterator.next(); // 获取一条查询数据

				// 如果当前地市没有统计hash
				if (_hashtable.get((String) countObject[2]) == null) {
					tawwpStatVO = new TawwpStatVO(); // 创建
					_hashtable.put(((String) countObject[2]),
							tawwpStatVO);
				} else {
					tawwpStatVO = (TawwpStatVO) _hashtable
							.get(((String) countObject[2])); // 获取
				}
 
				tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(countObject[2]
						.toString());
				Integer normalcount = tawwpExecuteContentDao.statNormalCount(countObject[2].toString(),countObject[0].toString(),1,_state);
				// 封装数据vo
				tawwpStatVO.setYearFlag(_yearFlag); 
				tawwpStatVO.setMonthFlag(_monthFlag);
				tawwpStatVO.setTawWpMonthPlan(tawwpMonthPlan);
				tawwpStatVO.setTawSystemCptroom(tawSystemCptroom);
				tawwpStatVO.setTawSystemUser(tawSystemUser);
				tawwpStatVO.putCycleCountNext((String) countObject[0],
						((Integer) countObject[1]).intValue(), _state,normalcount.intValue());
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}
	}
	
	
	// TODO 统计结束 
	
	
	public List listidbynettypeid(String mynettypeid) throws Exception {
		return tawwpNetDao.listidbymynettypie(mynettypeid);
	}
	
	public List filterMonthPlanByCycle(List _monthPlanVOList, String _cycle) throws
	      Exception {

	    List monthExecuteList = null;
	    List newList = new ArrayList();
	    TawwpMonthExecute tawwpMonthExecute = null;
	    
	    for (int i = 0; i < _monthPlanVOList.size(); i++) {
	      //获取某一月度计划的执行信息
	      TawwpMonthPlanVO tawwpMonthPlanVO = (TawwpMonthPlanVO) _monthPlanVOList.
	          get(i);
	      TawwpMonthPlan tawwpMonthPlan = tawwpMonthPlanDao.loadMonthPlan(
	          tawwpMonthPlanVO.getId());
	      //获取月度作业计划执行内容对象集合  
	      monthExecuteList = tawwpMonthPlanDao.listMonthExecute(tawwpMonthPlan.getId());
	      Set monthExecuteSet = tawwpMonthPlan.getTawwpMonthExecutes();
	      //如果执行内容列表不为空
	      if (monthExecuteList != null) {
	        //如果指定周期在其中,则保留在原列表中
	        for (int j = 0; j < monthExecuteList.size(); j++) {
	          tawwpMonthExecute = (TawwpMonthExecute)
	              monthExecuteList.get(j);
	          if ( (tawwpMonthExecute.getCycle()).equals(_cycle)) {
	            newList.add(tawwpMonthPlanVO);
	            break;
	          }
	        }
	      }
	    }

	    return newList;
	  }
	  
	  
	  public List queryYearPlanLink(Map _mapQuery) throws Exception {
			// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
			TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
			// TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
			// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();

			TawwpYearPlan tawwpYearPlan = null;
			TawwpYearPlanVO tawwpYearPlanVO = null;
			List list = null;
			List modelList = null;
			List newList = new ArrayList();

			// 获取网元信息
			String modelId = null;
			modelId = (String) _mapQuery.get("modelId");
			if (modelId != null) {
				modelList = tawwpModelPlanDao.listModelPlanByModelPlanIds(modelId);
				_mapQuery.put("modelId", modelList);
			}

			try {
				list = tawwpYearPlanDao.searchYearPlanLink(_mapQuery);
 
				// 循环处理执行数据
				for (int i = 0; i < list.size(); i++) {

					tawwpYearPlan = (TawwpYearPlan) list.get(i);
					tawwpYearPlanVO = new TawwpYearPlanVO();
					// 整理数据
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlanVO,
							tawwpYearPlan);

					tawwpYearPlanVO.setCruserName(tawwpUtilDAO
							.getUserName(tawwpYearPlanVO.getCruser()));
					tawwpYearPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpYearPlanVO.getSysTypeId()));
					tawwpYearPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpYearPlanVO.getNetTypeId()));
					tawwpYearPlanVO
							.setStateName(TawwpYearPlanVO.CONSTITUTESTATENAME[Integer
									.parseInt(tawwpYearPlanVO.getState())]);
					tawwpYearPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpYearPlanVO.getDeptId()));
					tawwpYearPlanVO.setNetListName(tawwpNetDao
							.getNetNameList(tawwpYearPlanVO.getNetList()));
					tawwpYearPlanVO.setCrtime(tawwpYearPlanVO.getCrtime()
							.substring(0, 10));
					newList.add(tawwpYearPlanVO); // 放入列表
				}

				return newList;
			} catch (Exception e) {
				// 捕获异常
				e.printStackTrace();
				throw new TawwpException("年度作业计划查询出现异常");
			} finally {
				list = null;
			}
		}

	  
	  
	 /* (non-Javadoc)
	 *  级联下级部门
	 */
	public List queryMonthPlanLink(Map _mapQuery,String _executeUser,String _executeType) throws Exception {
			// TawwpMonthPlanDAO tawwpMonthPlanDAO = new TawwpMonthPlanDAO();
			TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
			// TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();

			TawwpMonthPlan tawwpMonthPlan = null;
			TawwpMonthPlanVO tawwpMonthPlanVO = null;

			List list = null;
			List netList = null;

			List newList = new ArrayList();

			// 获取网元信息
			String netId = null;
			netId = (String) _mapQuery.get("netId");
			netList = tawwpNetDao.ListNet(netId);
			if (netList.size() > 0) {
				_mapQuery.put("netId", netList);
			}

			try {
				list = tawwpMonthPlanDao.searchMonthPlanLink(_mapQuery); // 获取符合条件的月度作业计划

				// 循环处理执行数据
				for (int i = 0; i < list.size(); i++) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					tawwpMonthPlan = (TawwpMonthPlan) list.get(i);
					
					/**
					 * 
					 */
					
					int flag = 0;
					if(!"".equals(_executeUser)){
					Set set =  tawwpMonthPlan.getTawwpMonthExecutes();
					// 判断页面选择的执行信息和数据库内的执行信息对照。如果有则把次作业计划作为一项
					for (Iterator it = set.iterator();it.hasNext();){ 
						TawwpMonthExecute content = (TawwpMonthExecute)it.next();
						if(StaticMethod.null2String(content.getExecuterType()).equals(_executeType)){
						  if(content.getExecuter()!=null){
							String[] userArray = content.getExecuter().split(",");
							for(int j=0;j<userArray.length;j++){
								System.out.println(userArray[j]);
								if(_executeUser.equals(userArray[j])){
									flag = 1;
									break;
								}
							  }
							}
						 }
					  }
					}
					if(!"".equals(_executeUser)&& flag==0){  // 如果没有跳回循环
						continue;
					}
					// 整理数据
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);

					tawwpMonthPlanVO.setCruser(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));

					// 获取网元名称
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO
								.setNetName(StaticMethod.null2String(tawwpMonthPlan
										.getTawwpNet().getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getConstituteStateName(tawwpMonthPlanVO
									.getExecuteState()));
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(tawwpMonthPlanVO
									.getConstituteState()));
					tawwpMonthPlanVO
							.setExecuteStateName(TawwpMonthPlanVO
									.getExecuteStateName(tawwpMonthPlanVO
											.getExecuteState()));

					newList.add(tawwpMonthPlanVO); // 放入列表
				}

				return newList;
			} catch (Exception e) {
				// 捕获异常
				e.printStackTrace();
				throw new TawwpException("月度作业计划查询出现异常");
			} finally {
				list = null;
			}
		}
 
	/**
	 * 业务逻辑：查询作业计划执行情况(QUERY-SUBJECT-EXECUTE-001)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public Hashtable statMonthPlan(String _yearFlag, String _monthFlag,String deptId)
			throws TawwpException {
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		String startDate = _yearFlag + "-" + _monthFlag + "-01 00:00:00";
		String endDate = null;
		int day = Integer.parseInt(TawwpUtil.getCurrentDateTime("dd")) - 1;

		if (_monthFlag.compareTo(TawwpUtil.getCurrentDateTime("MM")) >= 0) {
			endDate = _yearFlag + "-" + _monthFlag + "-"
					+ TawwpUtil.getMonthStr(day) + " 23:59:59";
		} else {
			endDate = _yearFlag + "-" + _monthFlag + "-31 23:59:59";
		}

		List list = null;
//		startDate="2009-01-01 00:00:00";
//		endDate="2009-08-31 00:00:00";
		Hashtable hashtable = new Hashtable();
		try {
			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, 0,deptId); // 获取全部执行内容
			
			this.manageStatCount(hashtable, list, 0);

			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, 1,deptId); // 获取全部按时执行内容
			this.manageStatCount(hashtable, list, 1);

			list = tawwpExecuteContentDao.statExecuteContent(startDate,
					endDate, 2,deptId); // 获取全部超时执行内容
			this.manageStatCount(hashtable, list, 2);
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		}

		return hashtable;
	}
	/**
	 * 业务逻辑：查询作业计划执行情况(具体月度作业计划)(QUERY-SUBJECT-EXECUTE-003)
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @param _deptId
	 *            String 部门信息（内部编号）
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 统计数据hash
	 */
	public List statMonthPlanByMonthPlanNew(String _yearFlag, String _monthFlag,
			String _deptId,String cycle,String executeFlag) throws TawwpException {
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		TawwpMonthPlan tawwpMonthPlan = null;
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		List list = null;
		List newList = new ArrayList();

		try {
			list = tawwpExecuteContentDao.getTawwpMonthPlanByExecuteNew(_deptId,
					_yearFlag, _monthFlag,cycle,executeFlag); // 获取月度作业计划编号(执行存在问题的)

			// 循环处理月度作业计划
			for (int i = 0; i < list.size(); i++) {
				tawwpMonthPlan = (TawwpMonthPlan) list.get(i);

				tawwpMonthPlanVO = new TawwpMonthPlanVO();
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
						.getExecuteStateName(Integer.parseInt(tawwpMonthPlanVO
								.getExecuteState())));
				// 获取执行负责人姓名
				tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
						.getUserName(tawwpMonthPlanVO.getPrincipal()));
				newList.add(tawwpMonthPlanVO);
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("作业计划统计出现异常");
		} finally {
			list = null;
		}
		return newList;
	}
 
	 
	 
}
