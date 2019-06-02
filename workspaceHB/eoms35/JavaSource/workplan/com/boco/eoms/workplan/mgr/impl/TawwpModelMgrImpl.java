package com.boco.eoms.workplan.mgr.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.file.service.IFMImportFileManager;
import com.boco.eoms.workplan.dao.ITawwpAddonsTableDao;
import com.boco.eoms.workplan.dao.ITawwpModelDispatchDao;
import com.boco.eoms.workplan.dao.ITawwpModelExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpModelGroupDao;
import com.boco.eoms.workplan.dao.ITawwpModelPlanDao;
import com.boco.eoms.workplan.dao.ITawwpYearExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.mgr.ITawwpModelMgr;
import com.boco.eoms.workplan.model.TawwpAddonsTable;
import com.boco.eoms.workplan.model.TawwpModelDispatch;
import com.boco.eoms.workplan.model.TawwpModelExecute;
import com.boco.eoms.workplan.model.TawwpModelGroup;
import com.boco.eoms.workplan.model.TawwpModelPlan;
import com.boco.eoms.workplan.model.TawwpYearExecute;
import com.boco.eoms.workplan.model.TawwpYearPlan;
import com.boco.eoms.workplan.util.FMImportModel;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpAddonsTableVO;
import com.boco.eoms.workplan.vo.TawwpModelDispatchVO;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpModelGroupManageVO;
import com.boco.eoms.workplan.vo.TawwpModelGroupVO;
import com.boco.eoms.workplan.vo.TawwpModelPlanVO;
import com.boco.eoms.workplan.vo.TawwpMonthExecuteVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 11:39:55 AM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpModelMgrImpl implements ITawwpModelMgr {

	private ITawwpModelPlanDao tawwpModelPlanDao;

	private ITawwpModelExecuteDao tawwpModelExecuteDao;

	private ITawwpModelGroupDao tawwpModelGroupDao;

	private ITawwpModelDispatchDao tawwpModelDispatchDao;

	private ITawwpYearPlanDao tawwpYearPlanDao;

	private ITawwpYearExecuteDao tawwpYearExecuteDao;

	// TawwpAddonsTableDAO
	private ITawwpAddonsTableDao tawwpAddonsTableDao;

	/**
	 * 业务逻辑：增加作业计划模版（ADD-STENCIL-001）
	 * 
	 * @param _name
	 *            String 模版名称
	 * @param _sysTypeId
	 *            String 系统类型
	 * @param _netTypeId
	 *            String 网元类别
	 * @param _cruser
	 *            String 创建人
	 * @return TawwpModelPlan 作业计划模版
	 * @throws TawwpException
	 *             异常信息
	 */
	public TawwpModelPlan addModelPlan(String _name, String _sysTypeId,
			String _netTypeId, String _typeIndex, String _cruser)
			throws TawwpException {
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();

		TawwpModelPlan tawwpModelPlan = new TawwpModelPlan(_name, _sysTypeId,
				_netTypeId, _typeIndex, "0", "0", _cruser, TawwpUtil
						.getCurrentDateTime()); // 形成作业计划模板对象信息

		try {
			tawwpModelPlanDao.saveModelPlan(tawwpModelPlan); // 保存作业计划模板
			return tawwpModelPlan;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板保存出现异常");
		}
	}

	/**
	 * 业务逻辑：修改作业计划模版（EDIT-STENCIL-001）
	 * 
	 * @param _name
	 *            String 模版名称
	 * @param _id
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void editModelPlan(String _name, String _id) throws TawwpException {
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		TawwpModelPlan tawwpModelPlan = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_id); // 获取作业计划模板对象
			if (!tawwpModelPlan.getName().equals(_name)) {
				tawwpModelPlan.setName(_name);
				tawwpModelPlanDao.updateModelPlan(tawwpModelPlan); // 保存作业计划模板
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板保存出现异常");
		}
	}

	public void editModelPlan(String _typeIndex, String _name, String _id)
			throws TawwpException {
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		TawwpModelPlan tawwpModelPlan = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_id); // 获取作业计划模板对象

			tawwpModelPlan.setName(_name);
			tawwpModelPlan.setTypeIndex(_typeIndex);
			tawwpModelPlanDao.updateModelPlan(tawwpModelPlan); // 保存作业计划模板

		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板保存出现异常");
		}
	}

	/**
	 * 业务逻辑：删除作业计划模版（DELETE-STENCIL-001）
	 * 
	 * @param _id
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void removeModelPlan(String _id) throws TawwpException {
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		TawwpModelPlan tawwpModelPlan = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_id); // 获取作业计划模板对象
			if (tawwpModelPlan != null
					&& tawwpModelPlan.getTawwpYearPlan().size() == 0) {
				tawwpModelPlanDao.deleteModelPlan(tawwpModelPlan); // 删除作业计划模板
			} else {
				tawwpModelPlan.setDeleted("1");
				tawwpModelPlanDao.updateModelPlan(tawwpModelPlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板删除出现异常");
		}
	}

	/**
	 * 业务逻辑：增加作业计划模板执行内容（ADD-STENCIL-CONTENT-001）
	 * 
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _modelPlanId
	 *            String 作业计划模板标识
	 * @param _groupId
	 *            String 作业计划模板组织信息标识
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @param _cruser
	 *            String 创建人
	 * @throws TawwpException
	 *             异常信息
	 */
	public void addModelExecute(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark,
			String _accessories, String _formId, String _format,
			String _cruser, String _isHoliday, String _isWeekend,
			String _fileFlag, int executeTimes, String executeDay)
			throws TawwpException {

		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelGroup tawwpModelGroup = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId); // 获取作业计划模板信息

			if (_groupId != null) {
				tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_groupId); // 获取作业计划模板组织信息
			}

			if (tawwpModelPlan != null) {

				tawwpModelExecute = new TawwpModelExecute(_name, _botype,
						_executedeptlevel, _appdesc, tawwpModelPlan,
						tawwpModelGroup, "0", _cycle, _remark, _accessories,
						_format, "", _formId, "", "0", _cruser, TawwpUtil
								.getCurrentDateTime(), "", "0", "0",
						_isHoliday, _isWeekend, _fileFlag, "", executeTimes,
						executeDay); // 形成作业计划执行内容信息

				tawwpModelExecuteDao.saveModelExecute(tawwpModelExecute); // 保存作业计划执行内容信息
			} else {
				throw new TawwpException("作业计划模板信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板执行内容保存出现异常");
		}
	}
	
	/**
	 * 方法重写。保存模板
	 * 业务逻辑：增加作业计划模板执行内容（ADD-STENCIL-CONTENT-001）
	 * 
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _modelPlanId
	 *            String 作业计划模板标识
	 * @param _groupId
	 *            String 作业计划模板组织信息标识
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @param _cruser
	 *            String 创建人
	 * @throws TawwpException
	 *             异常信息
	 */
	public void addModelExecute(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark,
			String _accessories, String _formId, String _format,
			String _cruser, String _isHoliday, String _isWeekend,
			String _fileFlag, int executeTimes, String executeDay,String monthFlag)
			throws TawwpException {

		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelGroup tawwpModelGroup = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId); // 获取作业计划模板信息

			if (_groupId != null) {
				tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_groupId); // 获取作业计划模板组织信息
			}

			if (tawwpModelPlan != null) {

				tawwpModelExecute = new TawwpModelExecute(_name, _botype,
						_executedeptlevel, _appdesc, tawwpModelPlan,
						tawwpModelGroup, "0", _cycle, _remark, _accessories,
						_format, "", _formId, "", "0", _cruser, TawwpUtil
								.getCurrentDateTime(), "", "0", "0",
						_isHoliday, _isWeekend, _fileFlag, "", executeTimes,
						executeDay); // 形成作业计划执行内容信息
				tawwpModelExecute.setMonthFlag(monthFlag);
				tawwpModelExecuteDao.saveModelExecute(tawwpModelExecute); // 保存作业计划执行内容信息
			} else {
				throw new TawwpException("作业计划模板信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板执行内容保存出现异常");
		}
	}
	/**
	 * 业务逻辑：增加作业计划模板执行内容（ADD-STENCIL-CONTENT-001�?
	 * 
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _modelPlanId
	 *            String 作业计划模板标识
	 * @param _groupId
	 *            String 作业计划模板组织信息标识
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @param _cruser
	 *            String 创建�?
	 * @throws TawwpException
	 *             异常信息
	 */
	public void addModelExecute(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark,String _note,
			String _accessories, String _formId, String _format,
			String _cruser, String _isHoliday, String _isWeekend,
			String _fileFlag, int executeTimes, String executeDay,String taskid,String monthFlag)
			throws TawwpException {
 
		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelGroup tawwpModelGroup = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId); // 获取作业计划模板信息

			if (_groupId != null) {
				tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_groupId); // 获取作业计划模板组织信息
			}

			if (tawwpModelPlan != null) {

				tawwpModelExecute = new TawwpModelExecute(_name, _botype,
						_executedeptlevel, _appdesc, tawwpModelPlan,
						tawwpModelGroup, "0", _cycle, _remark,_note, _accessories,
						_format, "", _formId, "", "0", _cruser, TawwpUtil
								.getCurrentDateTime(), "", "0", "0",
						_isHoliday, _isWeekend, _fileFlag, "", executeTimes,
						executeDay); // 形成作业计划执行内容信息
				tawwpModelExecute.setMonthFlag(monthFlag);
				tawwpModelExecute.setTaskid(taskid);
				tawwpModelExecuteDao.saveModelExecute(tawwpModelExecute); // 保存作业计划执行内容信息
			} else {
				throw new TawwpException("作业计划模板信息不存�“在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板执行内容保存出现异常");
		}
	}
 
	/**
	 * 方法重写。保存模板 添加巡检任务
	 * 业务逻辑：增加作业计划模板执行内容（ADD-STENCIL-CONTENT-001）
	 * 
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _modelPlanId
	 *            String 作业计划模板标识
	 * @param _groupId
	 *            String 作业计划模板组织信息标识
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @param _cruser
	 *            String 创建人
	 * @throws TawwpException
	 *             异常信息
	 */
	public void addModelExecute(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _modelPlanId,
			String _groupId, String _cycle, String _remark,
			String _accessories, String _formId, String _format,
			String _cruser, String _isHoliday, String _isWeekend,
			String _fileFlag, int executeTimes, String executeDay,String monthFlag,String taskId)
			throws TawwpException {

		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelGroup tawwpModelGroup = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId); // 获取作业计划模板信息

			if (_groupId != null) {
				tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_groupId); // 获取作业计划模板组织信息
			}

			if (tawwpModelPlan != null) {

				tawwpModelExecute = new TawwpModelExecute(_name, _botype,
						_executedeptlevel, _appdesc, tawwpModelPlan,
						tawwpModelGroup, "0", _cycle, _remark, _accessories,
						_format, "", _formId, "", "0", _cruser, TawwpUtil
								.getCurrentDateTime(), "", "0", "0",
						_isHoliday, _isWeekend, _fileFlag, "", executeTimes,
						executeDay); // 形成作业计划执行内容信息
				tawwpModelExecute.setMonthFlag(monthFlag);
				tawwpModelExecute.setTaskid(taskId);
				tawwpModelExecuteDao.saveModelExecute(tawwpModelExecute); // 保存作业计划执行内容信息
			} else {
				throw new TawwpException("作业计划模板信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板执行内容保存出现异常");
		}
	}
	/**
	 * 业务逻辑：修改作业计划模版执行内容（EDIT-STENCIL-CONTENT-001）
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @throws TawwpException
	 *             异常信息
	 */
	public void editModelExecute(String _id, String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _cycle,
			String _remark, String _accessories, String _formId,
			String _format, String _isHoliday, String _isWeekend,
			String _fileFlag, String executeDay) throws TawwpException {

		TawwpModelExecute tawwpModelExecute = null;

		try {
			tawwpModelExecute = tawwpModelExecuteDao.loadModelExecute(_id); // 获取作业计划模板执行内容信息
			if (tawwpModelExecute != null) {
				tawwpModelExecute.setName(_name);
				tawwpModelExecute.setBotype(_botype);
				tawwpModelExecute.setExecutedeptlevel(_executedeptlevel);
				tawwpModelExecute.setAppdesc(_appdesc);
				tawwpModelExecute.setCycle(_cycle);
				tawwpModelExecute.setFormId(_formId);
				tawwpModelExecute.setFormat(_format);
				tawwpModelExecute.setIsHoliday(_isHoliday);
				tawwpModelExecute.setIsWeekend(_isWeekend);
				tawwpModelExecute.setRemark(_remark);
				tawwpModelExecute.setAccessories(_accessories);
				tawwpModelExecute.setFileFlag(_fileFlag);
				tawwpModelExecute.setExecuteDay(executeDay);
				tawwpModelExecuteDao.updateModelExecute(tawwpModelExecute); // 修改作业计划模板执行内容信息
			} else {
				throw new TawwpException("作业计划模板执行内容信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new TawwpException("作业计划模板执行内容修改出现异常");
		}
	}
 
	
	/**
	 * 业务逻辑：修改作业计划模版执行内容（EDIT-STENCIL-CONTENT-001�?
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @param _name
	 *            String 作业计划模板执行内容名称
	 * @param _cycle
	 *            String 周期
	 * @param _formId
	 *            String 特殊表格标识
	 * @param _format
	 *            String 填写格式
	 * @throws TawwpException
	 *             异常信息
	 */
	public void editModelExecute(String _id, String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _cycle,
			String _remark, String _accessories, String _formId,
			String _format, String _isHoliday, String _isWeekend,
			String _fileFlag, String executeDay,String taskid,String monthFlag) throws TawwpException {

		TawwpModelExecute tawwpModelExecute = null;

		try {
			tawwpModelExecute = tawwpModelExecuteDao.loadModelExecute(_id); // 获取作业计划模板执行内容信息
			if (tawwpModelExecute != null) {
				tawwpModelExecute.setName(_name);
				tawwpModelExecute.setBotype(_botype);
				tawwpModelExecute.setExecutedeptlevel(_executedeptlevel);
				tawwpModelExecute.setAppdesc(_appdesc);
				tawwpModelExecute.setCycle(_cycle);
				tawwpModelExecute.setFormId(_formId);
				tawwpModelExecute.setFormat(_format);
				tawwpModelExecute.setIsHoliday(_isHoliday);
				tawwpModelExecute.setIsWeekend(_isWeekend);
				tawwpModelExecute.setRemark(_remark);
				tawwpModelExecute.setAccessories(_accessories);
				tawwpModelExecute.setFileFlag(_fileFlag);
				tawwpModelExecute.setExecuteDay(executeDay);
				tawwpModelExecute.setTaskid(taskid);
				tawwpModelExecute.setMonthFlag(monthFlag);
				tawwpModelExecuteDao.updateModelExecute(tawwpModelExecute); // 修改作业计划模板执行内容信息
			} else {
				throw new TawwpException("作业计划模板执行内容信息不存��“在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new TawwpException("作业计划模板执行内容修改出现异常");
		}
	}
	public void editModelExecute(String _id, String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _cycle,
			String _remark,String _note, String _accessories, String _formId,
			String _format, String _isHoliday, String _isWeekend,
			String _fileFlag, String executeDay,String monthFlag,String taskid) throws TawwpException {

		TawwpModelExecute tawwpModelExecute = null;

		try {
			tawwpModelExecute = tawwpModelExecuteDao.loadModelExecute(_id); // 获取作业计划模板执行内容信息
			if (tawwpModelExecute != null) {
				tawwpModelExecute.setName(_name);
				tawwpModelExecute.setBotype(_botype);
				tawwpModelExecute.setExecutedeptlevel(_executedeptlevel);
				tawwpModelExecute.setAppdesc(_appdesc);
				tawwpModelExecute.setCycle(_cycle);
				tawwpModelExecute.setFormId(_formId);
				tawwpModelExecute.setFormat(_format);
				tawwpModelExecute.setIsHoliday(_isHoliday);
				tawwpModelExecute.setIsWeekend(_isWeekend);
				tawwpModelExecute.setRemark(_remark);
				tawwpModelExecute.setAccessories(_accessories);
				tawwpModelExecute.setFileFlag(_fileFlag);
				tawwpModelExecute.setExecuteDay(executeDay);
				tawwpModelExecute.setTaskid(taskid);
				tawwpModelExecute.setMonthFlag(monthFlag);
				tawwpModelExecute.setNote(_note);
				tawwpModelExecuteDao.updateModelExecute(tawwpModelExecute); // 修改作业计划模板执行内容信息
			} else {
				throw new TawwpException("作业计划模板执行内容信息不存��“在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new TawwpException("作业计划模板执行内容修改出现异常");
		}
	}
	/**
	 * 业务逻辑：删除作业计划模版执行内容（DELETE-STENCIL-CONTENT-001）
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void removeModelExecute(String _id) throws TawwpException {
		// TawwpModelExecuteDAO tawwpModelExecuteDAO = new
		// TawwpModelExecuteDAO();
		TawwpModelExecute tawwpModelExecute = null;

		try {
			tawwpModelExecute = tawwpModelExecuteDao.loadModelExecute(_id); // 获取作业计划模板执行内容信息
			if (tawwpModelExecute != null) {
				tawwpModelExecuteDao.deleteModelExecute(tawwpModelExecute); // 删除作业计划模板执行内容
			} else {
				throw new TawwpException("作业计划模板执行内容信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板执行内容删除出现异常");
		}
	}

	/**
	 * 业务逻辑：派发作业计划模版（SEND-STENCIL-001）
	 * 
	 * @param _id
	 *            String 作业计划模板标识
	 * @param _deptIdList
	 *            String 接收部门列表
	 * @param _yearFlag
	 *            String 年度
	 * @param _sendDeptId
	 *            String 发送部门
	 * @param _sendUser
	 *            String 发送人
	 * @param _content
	 *            String 派发信息
	 * @param _modelDispatchId
	 *            String 父派发信息标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void dispatchModelPlan(String _id, String _deptIdList,
			String _yearFlag, String _sendDeptId, String _sendUser,
			String _content, String _modelDispatchId) throws TawwpException {
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		// TawwpModelDispatchDAO tawwpModelDispatchDAO = new
		// TawwpModelDispatchDAO();

		TawwpModelDispatch tawwpModelDispatch = null;
		TawwpModelDispatch parentTawwpModelDispatch = null;
		TawwpModelPlan tawwpModelPlan = null;

		String inceptDeptIdArray[] = _deptIdList.split(","); // 获得派发部门数组

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_id); // 获取作业计划模板信息

			// 派发标识不为空
			if (_modelDispatchId != null) {
				parentTawwpModelDispatch = tawwpModelDispatchDao
						.loadModelDispatch(_modelDispatchId); // 获取作业计划模板派发信息
			}

			// 如果作业计划模板存在
			if (tawwpModelPlan != null) {
				// 对派发部门进行循环处理
				for (int i = 0; i < inceptDeptIdArray.length; i++) {
					tawwpModelDispatch = new TawwpModelDispatch(_sendDeptId,
							TawwpUtil.getCurrentDateTime(), _sendUser,
							inceptDeptIdArray[i], "", "", "0", _content,
							_yearFlag, parentTawwpModelDispatch, tawwpModelPlan); // 生成作业计划模板派发信息

					tawwpModelDispatchDao.saveModelDispatch(tawwpModelDispatch); // 保存作业计划模板派发信息
				}
			} else {
				throw new TawwpException("作业计划模板信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板派发出现异常");
		}
	}

	/**
	 * 业务逻辑：取消作业计划模版的派发（ANNUL-SEND-STENCIL-001）
	 * 
	 * @param _id
	 *            String 作业计划模版派发信息标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void annulModelDispatch(String _id) throws TawwpException {
		// TawwpModelDispatchDAO tawwpModelDispatchDAO = new
		// TawwpModelDispatchDAO();
		TawwpModelDispatch tawwpModelDispatch = null;

		try {
			tawwpModelDispatch = tawwpModelDispatchDao.loadModelDispatch(_id); // 获取作业计划模板派发信息

			// 如果作业计划模板派发信息存在
			if (tawwpModelDispatch != null) {
				tawwpModelDispatchDao.deleteModelDispatch(tawwpModelDispatch); // 删除作业计划模板派发信息
			} else {
				throw new TawwpException("作业计划模板派发信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板派发信息删除出现异常");
		}
	}

	/**
	 * 业务逻辑：接收作业计划模版（损-STENCIL-001）
	 * 
	 * @param _id
	 *            String 作业计划模版派发信息标识
	 * @param inceptDeptId
	 *            String 接收部门
	 * @param _inceptUser
	 *            String 接收人
	 * @throws TawwpException
	 *             异常信息
	 */
	public void receiveModelDispatch(String _id, String inceptDeptId,
			String _inceptUser) throws TawwpException {
		// TawwpModelDispatchDAO tawwpModelDispatchDAO = new
		// TawwpModelDispatchDAO();
		TawwpModelDispatch tawwpModelDispatch = null;

		try {
			tawwpModelDispatch = tawwpModelDispatchDao.loadModelDispatch(_id); // 获取作业计划模板派发信息

			// 如果作业计划模板派发信息存在
			if (tawwpModelDispatch != null) {
				tawwpModelDispatch.setState("1");
				tawwpModelDispatch.setInceptCrtime(TawwpUtil
						.getCurrentDateTime());
				tawwpModelDispatchDao.updateModelDispatch(tawwpModelDispatch); // 修改作业计划模板派发信息状态为“1：接收”

				this.addYearPlan(inceptDeptId, _inceptUser, tawwpModelDispatch); // 生成年度作业计划以及年度作业计划执行内容
			} else {
				throw new TawwpException("作业计划模板派发信息不存在");
			}
		} catch (TawwpException e) {
			e.printStackTrace();
			throw new TawwpException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板派发信息删除出现异常");
		}
	}

	/**
	 * 内部逻辑：增加年度作业计划以及年度作业计划执行内容
	 * 
	 * @param _inceptDeptId
	 *            String 创建部门
	 * @param _inceptUser
	 *            String 创建人
	 * @param _tawwpModelDispatch
	 *            TawwpModelDispatch 作业计划模版派发信息
	 * @throws TawwpException
	 *             异常信息
	 */
	private void addYearPlan(String _inceptDeptId, String _inceptUser,
			TawwpModelDispatch _tawwpModelDispatch) throws TawwpException {
		// TawwpYearPlanDAO tawwpYearPlanDAO = new TawwpYearPlanDAO();
		// TawwpYearExecuteDAO tawwpYearExecuteDAO = new TawwpYearExecuteDAO();

		TawwpModelPlan tawwpModelPlan = _tawwpModelDispatch.getTawwpModelPlan(); // 获取作业计划模版信息
		Iterator iterator = tawwpModelPlan.getTawwpModelExecutes().iterator(); // 获取作业计划模版执行内容列表集合

		TawwpModelExecute tawwpModelExecute = null;
		TawwpYearPlan tawwpYearPlan = null;
		TawwpYearExecute tawwpYearExecute = null;

		try {
			tawwpYearPlan = new TawwpYearPlan();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlan,
					tawwpModelPlan); // 将作业计划模版信息导入到年度作业计划中

			// 完善年度作业计划信息
			tawwpYearPlan.setTawwpModelPlan(tawwpModelPlan);
			tawwpYearPlan.setYearFlag(_tawwpModelDispatch.getYearFlag());
			tawwpYearPlan.setDeptId(_inceptDeptId);
			tawwpYearPlan.setCruser(_inceptUser);
			tawwpYearPlan.setCrtime(TawwpUtil.getCurrentDateTime());

			tawwpYearPlanDao.saveYearPlan(tawwpYearPlan); // 保存年度作业计划信息

			// 循环处理作业计划模版执行内容，生成对应的年度作业计划执行内容
			while (iterator.hasNext()) {
				tawwpModelExecute = (TawwpModelExecute) iterator.next(); // 获取一个作业计划模版执行内容
				tawwpYearExecute = new TawwpYearExecute();

				MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearExecute,
						tawwpModelExecute); // 将作业计划模版执行内容信息导入到年度作业计划执行内容中

				// 完善年度作业计划执行内容信息
				tawwpYearExecute.setTawwpYearPlan(tawwpYearPlan);
				tawwpYearExecute.setCruser(_inceptUser);
				tawwpYearExecute.setCrtime(TawwpUtil.getCurrentDateTime());

				tawwpYearExecuteDao.saveYearExecute(tawwpYearExecute); // 保存年度作业计划执行内容信息
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("年度作业计划生成操作出现异常");
		}
	}

	/**
	 * 业务逻辑：浏览作业计划模版（RECEIVE-STENCIL-001）
	 * 
	 * @throws TawwpException
	 *             异常信息
	 * @return Hashtable 作业计划模版的Hashtable结构，其中按系统类别、网元类型分级。
	 */
	public Hashtable listModeLPlan() throws TawwpException {

		Hashtable sysTypeHashtable = new Hashtable(); // 系统类别hashtable
		Hashtable netTypeHashtable = null; // 网元类型hashtable
		List tawwpModelPlanVOList = null; // 作业计划模版列表
		List list = null;

		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelPlanVO tawwpModelPlanVO = null;

		try {
			list = tawwpModelPlanDao.listModelPlan(); // 获取所有作业计划模版的集合
			if (list != null) {
				// 循环对每个作业计划模版进行分类处理
				for (int i = 0; i < list.size(); i++) {
					tawwpModelPlan = (TawwpModelPlan) list.get(i); // 获取一个作业计划模版
					tawwpModelPlanVO = new TawwpModelPlanVO();
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelPlanVO,
							tawwpModelPlan); // 将作业计划模版信息导入到作业计划模版显示类中
					tawwpModelPlanVO.setCrusername(tawwpUtilDAO
							.getUserName(tawwpModelPlanVO.getCruser()));
					tawwpModelPlanVO.setCrtime(tawwpModelPlanVO.getCrtime()
							.substring(0, 10));

					if (sysTypeHashtable.get(tawwpModelPlanVO.getSysTypeId()) == null) {
						// 如果当前网元类型不存在hashtable对象
						netTypeHashtable = new Hashtable(); // 建立一个网元类型hashtable
						tawwpModelPlanVOList = new ArrayList(); // 建立作业计划模版信息列表
						tawwpModelPlanVOList.add(tawwpModelPlanVO); // 将作业计划模版信息加入列表
						netTypeHashtable.put(tawwpModelPlanVO.getNetTypeId(),
								tawwpModelPlanVOList); // 将列表放入网元类型hashtable
						sysTypeHashtable.put(tawwpModelPlanVO.getSysTypeId(),
								netTypeHashtable); // 将网元类型hashtable放入系统类型hashtable
					} else {
						// 如果当前网元类型存在hashtable对象
						netTypeHashtable = (Hashtable) sysTypeHashtable
								.get(tawwpModelPlanVO.getSysTypeId()); // 获取一个网元类型hashtable
						tawwpModelPlanVOList = (List) netTypeHashtable
								.get(tawwpModelPlanVO.getNetTypeId()); // 获取作业计划模版信息列表

						// 如果网元类型列表不存在，则创建
						if (tawwpModelPlanVOList == null) {
							tawwpModelPlanVOList = new ArrayList(); // 建立作业计划模版信息列表
							netTypeHashtable.put(tawwpModelPlanVO
									.getNetTypeId(), tawwpModelPlanVOList);
						}

						tawwpModelPlanVOList.add(tawwpModelPlanVO); // 将作业计划模版信息加入列表
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
	 * 业务逻辑：浏览作业计划模版，按网元类别（RECEIVE-STENCIL-002）
	 * 
	 * @param _netTypeId
	 *            String 网元类别
	 * @throws TawwpException
	 *             异常信息
	 * @return List 作业计划模版列表
	 */
	public List listModeLPlanByNetType(String _netTypeId) throws TawwpException {
		List list = null;
		List netList = null;

		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();

		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelPlanVO tawwpModelPlanVO = null;

		try {
			list = tawwpModelPlanDao.listModelPlan(_netTypeId); // 获取所有作业计划模版的集合
			netList = new ArrayList();

			// 循环对每个作业计划模版进行处理
			for (int i = 0; i < list.size(); i++) {
				tawwpModelPlan = (TawwpModelPlan) list.get(i); // 获取一个作业计划模版
				tawwpModelPlanVO = new TawwpModelPlanVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelPlanVO,
						tawwpModelPlan); // 将作业计划模版信息导入到作业计划模版显示类中

				netList.add(tawwpModelPlanVO);
			}
			return netList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板信息查询出现异常");
		} finally {
			list = null;
		}
	}

	/**
	 * 业务逻辑：增加作业计划模版组织信息（ADD-GROUP-001）
	 * 
	 * @param _name
	 *            String 作业计划模版组织信息名称
	 * @param _parentModelGroupId
	 *            String 父作业计划模版组织信息标识
	 * @param _modelPlanId
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void addModelGroup(String _name, String _parentModelGroupId,
			String _modelPlanId) throws TawwpException {
		// TawwpModelGroupDAO tawwpModelGroupDAO = new TawwpModelGroupDAO();
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		// TawwpModelExecuteDAO tawwpModelExecuteDAO = new
		// TawwpModelExecuteDAO();

		TawwpModelGroup tawwpModelGroup = null;
		TawwpModelGroup parentModelGroup = null;
		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelExecute tawwpModelExecute = null;
		Iterator tawwpModelExecuteIterator = null;
		try {
			// 如果没有父类组织信息，则不引导作业计划模版组织信息
			if (_parentModelGroupId != null) {
				parentModelGroup = tawwpModelGroupDao
						.loadModelGroup(_parentModelGroupId);
				tawwpModelExecuteIterator = parentModelGroup
						.getTawwpModelExecutes().iterator(); // 获取父类组织信息中的所有作业计划模版执行内容
			}

			// 如果不是顶级作业计划模版组织信息，则作业计划模版为空
			if (_modelPlanId != null) {
				tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId);
			}

			tawwpModelGroup = new TawwpModelGroup(_name, parentModelGroup,
					tawwpModelPlan); // 建立作业计划模版组织信息

			if (tawwpModelGroup != null) {
				tawwpModelGroupDao.saveModelGroup(tawwpModelGroup); // 保存作业计划模版组织信息

				if (tawwpModelExecuteIterator != null) {
					// 将父类的作业计划模版执行内容转移到新建子类组织信息中
					while (tawwpModelExecuteIterator.hasNext()) {
						tawwpModelExecute = (TawwpModelExecute) tawwpModelExecuteIterator
								.next();
						tawwpModelExecute.setTawwpModelGroup(tawwpModelGroup); // 修改关联关系
						tawwpModelExecuteDao
								.updateModelExecute(tawwpModelExecute);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板组织信息保存出现异常");
		}
	}

	/**
	 * 业务逻辑：修改作业计划模版组织信息（EDIT-GROUP-001）
	 * 
	 * @param _name
	 *            String 作业计划模版组织信息名称
	 * @param _id
	 *            String 作业计划模版组织信息标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void editModelGroup(String _name, String _id) throws TawwpException {
		// TawwpModelGroupDAO tawwpModelGroupDAO = new TawwpModelGroupDAO();
		TawwpModelGroup tawwpModelGroup = null;

		try {
			tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_id); // 获取作业计划模版组织信息

			// 如果修改名称与存在的名称不相同
			if (!tawwpModelGroup.getName().equals(_name)) {
				tawwpModelGroup.setName(_name);
				tawwpModelGroupDao.updateModelGroup(tawwpModelGroup); // 保存作业计划模版组织信息
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板组织信息修改出现异常");
		}
	}

	/**
	 * 业务逻辑：删除作业计划模版组织信息（DELETE-GROUP-001）
	 * 
	 * @param _id
	 *            String 作业计划模版组织标识
	 * @throws TawwpException
	 *             异常信息
	 */
	public void removeModelGroup(String _id) throws TawwpException {
		// TawwpModelGroupDAO tawwpModelGroupDAO = new TawwpModelGroupDAO();
		TawwpModelGroup tawwpModelGroup = null;

		try {
			tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_id); // 获取作业计划模版组织信息
			tawwpModelGroup.setDeleted("1");
			tawwpModelGroupDao.updateModelGroup(tawwpModelGroup);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板组织信息删除出现异常");
		}
	}

	/**
	 * 业务逻辑：浏览作业计划模版（BROWSE-STENCIL-001）
	 * 
	 * @param _id
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelPlanVO 作业计划模版
	 */
	public TawwpModelPlanVO viewModeLPlan(String _id) throws TawwpException {

		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		// TawwpAddonsTableDAO tawwpAddonsTableDAO = new TawwpAddonsTableDAO();

		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelDispatch tawwpModelDispatch = null;
		TawwpModelGroup tawwpModelGroup = null;
		TawwpAddonsTable tawwpAddonsTable = null;

		TawwpModelPlanVO tawwpModelPlanVO = null;
		TawwpModelExecuteVO tawwpModelExecuteVO = null;
		TawwpModelDispatchVO tawwpModelDispatchVO = null;
		TawwpAddonsTableVO tawwpAddonsTableVO = null;
		TawwpModelGroupManageVO tawwpModelGroupManageVO = new TawwpModelGroupManageVO();

		Iterator modelExecutesIterator = null;
		Iterator modelDispatchIterator = null;
		Iterator modelGroupIterator = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_id); // 获取作业计划模版信息

			// 如果作业计划模版信息不为空
			if (tawwpModelPlan != null) {

				tawwpModelPlanVO = new TawwpModelPlanVO(true); // 建立作业计划模版信息VO类

				MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelPlanVO,
						tawwpModelPlan); // 数据转换
				tawwpModelPlanVO.setNetTypeIdName(tawwpUtilDAO
						.getNetTypeName(tawwpModelPlanVO.getNetTypeId()));
				tawwpModelPlanVO.setSysTypeIdName(tawwpUtilDAO
						.getSysTypeName(tawwpModelPlanVO.getSysTypeId()));

				modelExecutesIterator = tawwpModelPlan.getTawwpModelExecutes()
						.iterator(); // 获取作业计划模版执行内容列表
				modelDispatchIterator = tawwpModelPlan.getTawwpModelDispatch()
						.iterator(); // 获取作业计划模版派发列表
				modelGroupIterator = tawwpModelPlan.getTawwpModelGroup()
						.iterator(); // 获取作业计划模版组织列表

				// 循环处理执行内容
				while (modelExecutesIterator.hasNext()) {
					tawwpModelExecute = (TawwpModelExecute) modelExecutesIterator
							.next();
					tawwpModelExecuteVO = new TawwpModelExecuteVO();
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelExecuteVO,
							tawwpModelExecute);
					tawwpModelExecuteVO.setCycleName(tawwpUtilDAO
							.getCycleName(tawwpModelExecuteVO.getCycle()));
					tawwpModelExecuteVO.setFormName(tawwpUtilDAO
							.getAddonsName(tawwpModelExecuteVO.getFormId()));

					tawwpModelPlanVO.getModelExecuteList().add(
							tawwpModelExecuteVO);
//					Collections.sort(tawwpModelPlanVO.getModelExecuteList());
					Collections.sort(tawwpModelPlanVO.getModelExecuteList(), new Comparator() {
						public int compare(Object o1, Object o2) {
							TawwpModelExecuteVO p1 = (TawwpModelExecuteVO) o1;
							TawwpModelExecuteVO p2 = (TawwpModelExecuteVO) o2;
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
				// 循环处理派发内容
				while (modelDispatchIterator.hasNext()) {
					tawwpModelDispatch = (TawwpModelDispatch) modelDispatchIterator
							.next();
					tawwpModelDispatchVO = new TawwpModelDispatchVO();
					MyBeanUtils.copyPropertiesFromDBToPage(
							tawwpModelDispatchVO, tawwpModelDispatch);
					tawwpModelPlanVO.getModelDispatchList().add(
							tawwpModelDispatchVO);
					Collections.sort(tawwpModelPlanVO.getModelDispatchList(), new Comparator() {
						public int compare(Object o1, Object o2) {
							TawwpModelExecuteVO p1 = (TawwpModelExecuteVO) o1;
							TawwpModelExecuteVO p2 = (TawwpModelExecuteVO) o2;
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

				// 循环处理组织信息
				while (modelGroupIterator.hasNext()) {
					tawwpModelGroup = (TawwpModelGroup) modelGroupIterator
							.next();
					creatModelGroup(tawwpModelGroupManageVO, tawwpModelGroup); // 进行递归处理
				}
				tawwpModelPlanVO
						.setTawwpModelGroupManageVO(tawwpModelGroupManageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板信息查询出现异常");
		}

		return tawwpModelPlanVO;
	}

	/**
	 * 业务逻辑：获取某个年度作业计划的组织结构（LIST-GROUP-001）
	 * 
	 * @param _modelPlanId
	 *            String 作业计划模版标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelGroupManageVO 作业计划的组织结构
	 */
	public TawwpModelGroupManageVO viewGroupList(String _modelPlanId)
			throws TawwpException {
		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		// TawwpModelGroupDAO tawwpModelGroupDAO = new TawwpModelGroupDAO();

		TawwpModelPlan tawwpModelPlan = null;
		TawwpModelGroup tawwpModelGroup = null;
		TawwpModelGroupManageVO tawwpModelGroupManageVO = new TawwpModelGroupManageVO();

		List modelGroupList = null;

		try {
			tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId); // 获取作业计划模版信息
			modelGroupList = tawwpModelGroupDao
					.filterTawwpModelGroup(tawwpModelPlan); // 获取作业计划模版组织列表

			// 循环处理组织信息
			for (int i = 0; i < modelGroupList.size(); i++) {
				tawwpModelGroup = (TawwpModelGroup) modelGroupList.get(i);
				creatModelGroup(tawwpModelGroupManageVO, tawwpModelGroup); // 进行递归处理
			}
			return tawwpModelGroupManageVO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板信息查询出现异常");
		}
	}

	/**
	 * 业务逻辑：获取作业计划组织信息（VIEW-GROUP-001）
	 * 
	 * @param _id
	 *            String 作业计划组织信息标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelGroupVO 作业计划组织信息
	 */
	public TawwpModelGroupVO viewModeLGroup(String _id) throws TawwpException {
		// TawwpModelGroupDAO tawwpModelGroupDAO = new TawwpModelGroupDAO();
		TawwpModelGroup tawwpModelGroup = null;
		TawwpModelGroupVO tawwpModelGroupVO = null;
		try {
			tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_id); // 获取作业计划组织信息
			tawwpModelGroupVO = new TawwpModelGroupVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelGroupVO,
					tawwpModelGroup); // 数据封装
			return tawwpModelGroupVO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划模板信息查询出现异常");
		}

	}

	/**
	 * 浏览作业计划模版执行内容信息
	 * 
	 * @param _id
	 *            String 作业计划模版执行内容标识
	 * @throws TawwpException
	 *             异常信息
	 * @return TawwpModelExecuteVO 作业计划模版执行内容信息
	 */
	public TawwpModelExecuteVO viewModeLExecute(String _id) {
		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelExecuteVO tawwpModelExecuteVO = null;
		try {
			System.out
					.println("add by zdl @@@@@@@@@@@@@@@@@@@@@@@@ _id " + _id);
			tawwpModelExecute = tawwpModelExecuteDao.loadModelExecute(_id); // 获取作业计划模板执行内容信息
			System.out.println("add by zdl @@@@@@@@@@@@@@@@@@@@@@@@ over");
			tawwpModelExecuteVO = new TawwpModelExecuteVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelExecuteVO,
					tawwpModelExecute);
			return tawwpModelExecuteVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// throw new TawwpException("作业计划模板执行内容信息查询出现异常");
		}
	}

	/**
	 * 将模版信息导出成Excel
	 * 
	 * @param _modelId
	 *            String 模版标识
	 * @return String 存储地址
	 */
	public String exportModelToExcel(String _modelId) {
		String exportExcelUrl = "";
		String exportExcel = "";
		int x = 0;
		int y = 0;

		String wwwDir = TawwpStaticVariable.wwwDir; // web目录
		String newDir = TawwpUtil.getCurrentDateTime("yyyy_MM_dd");

		try {
			// 创建文件夹
			TawwpUtil.mkDir(wwwDir
					+ "workplan/tawwpfile/tempfiledownload/model/" + newDir);
			// 生成的Excel地址
			exportExcelUrl = "/workplan/tawwpfile/tempfiledownload/model/"
					+ newDir + "/" + _modelId + ".xls";

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
			// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
			TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

			TawwpModelPlan tawwpModelPlan = null;
			TawwpModelExecute tawwpModelExecute = null;

			Iterator modelExecutesIterator = null;

			try {
				tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelId); // 获取作业计划模版信息

				/** ********************配置常量单元格信息 start************************ */
				// 常量值
				String[] valueOfLine = { "模版名称：",
						StaticMethod.strFromDBToPage(tawwpModelPlan.getName()),
						"执行内容数量：", "执行内容（必填）", "周期（必填）", "执行内容（必填）", "备注" };
				// 对应的行
				int[] rowOfLine = { 0, 0, 0, 1, 1, 1, 1 };
				// 对应的列
				int[] colOfLine = { 0, 1, 2, 0, 1, 2, 3 };
				// xy
				int[][] xyOfLine = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 },
						{ 1, 1 }, { 1, 1 }, { 1, 1 } };

				/** ********************配置常量单元格信息 end************************ */

				// 如果作业计划模版信息不为空
				if (tawwpModelPlan != null) {
					modelExecutesIterator = tawwpModelPlan
							.getTawwpModelExecutes().iterator(); // 获取作业计划模版执行内容列表

					wb.setSheetName(0, tawwpModelPlan.getName(),
							HSSFWorkbook.ENCODING_UTF_16);

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
					while (modelExecutesIterator.hasNext()) {

						tawwpModelExecute = (TawwpModelExecute) modelExecutesIterator
								.next();
						tawwpModelExecute.getFormat();
						// 新的行
						row = sheet.createRow((short) y);

						cell = row.createCell((short) 0);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(StaticMethod
								.strFromDBToPage(tawwpModelExecute.getName()));

						cell = row.createCell((short) 1);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellValue(tawwpUtilDAO
								.getCycleName(tawwpModelExecute.getCycle()));

						cell = row.createCell((short) 2);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell
								.setCellValue(StaticMethod
										.strFromDBToPage(tawwpModelExecute
												.getFormat()));

						cell = row.createCell((short) 3);
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell
								.setCellValue(StaticMethod
										.strFromDBToPage(tawwpModelExecute
												.getRemark()));
						y++;
					}
					// 定义单元格
					row = sheet.createRow((short) 0); // 建立新行
					cell = row.createCell((short) 3); // 建立新cell
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(y - 2);
				} else {
					throw new TawwpException("作业计划模板查询出现异常");
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
	 * 从Excel导入作业计划模板
	 * 
	 * @param _sourceExcel
	 *            String 上传的Excel
	 * @param _sysTypeId
	 *            String 系统标识
	 * @param _netTypeId
	 *            String 网元标识
	 * @param _cruser
	 *            String 创建人
	 */

	public void importModelFromExcel(String _sourceExcel, String _sysTypeId,
			String _netTypeId, String _cruser, String _typeIndex) {

		// TawwpModelPlanDAO tawwpModelPlanDAO = new TawwpModelPlanDAO();
		// TawwpModelExecuteDAO tawwpModelExecuteDAO = new
		// TawwpModelExecuteDAO();

		String value = "";
		String modelName = "";
		int contentNum = 0;

		List contentList = new ArrayList();
		List contentRowsList = null;
		/** **************配置Excel信息 start******************* */
		// 验证信息，1为必填，0为可不填写
		int[] contentVal = { 1, 1, 0, 0, 0 };
		// 定义循环区域开始的y
		int startY = 2;
		/** **************配置Excel信息 end********************* */
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;

		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;

		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelPlan tawwpModelPlan = null;

		try {

			fs = new POIFSFileSystem(new FileInputStream(_sourceExcel));
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(0);

			try {
				// 获得模版名称
				cell = row.getCell((short) 1);
				modelName = cell.getStringCellValue();
				// 获得循环数量
				cell = row.getCell((short) 3);
				contentNum = (int) cell.getNumericCellValue();
				// 根据记录条数循环导入数据
				for (int i = 0; i < contentNum; i++) {
					contentRowsList = new ArrayList();
					// 分别获得数据
					for (int c = 0; c < 5; c++) {
						// 获取该行
						row = sheet.getRow(i + startY);
						cell = row.getCell((short) c);
						// 如果是备注则允许为空
						if (contentVal[c] == 0) {
							if (cell != null) {
								if (cell.getCellType() == 1) {
									value = cell.getStringCellValue();
									contentRowsList.add(value);
								} else if (cell.getCellType() == 0) {
									value = "" + cell.getNumericCellValue();
									contentRowsList.add(value);
								} else {
									contentRowsList.add("");
								}
							} else {
								contentRowsList.add("");
							}
						} // 如果不是备注,则不允许为空
						else {
							if (cell.getCellType() == 1) {
								value = cell.getStringCellValue();
								contentRowsList.add(value);
							} else if (cell.getCellType() == 0) {
								value = "" + cell.getNumericCellValue();
								contentRowsList.add(value);
							} else {
								value = "";
								contentRowsList.add(value);
							}
						}
					}
					// 将信息content加入到contentList
					contentList.add(contentRowsList);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new TawwpException("作业计划模版出错，应填写项为空");
			}
			// 进入部分
			// 写模版信息
			tawwpModelPlan = new TawwpModelPlan(StaticMethod
					.null2String(modelName), _sysTypeId, _netTypeId,
					_typeIndex, "0", "0", _cruser, TawwpUtil
							.getCurrentDateTime()); // 形成作业计划模板对象信息

			try {
				tawwpModelPlanDao.saveModelPlan(tawwpModelPlan); // 保存作业计划模板

				tawwpModelExecute = new TawwpModelExecute();
				// contentList输出
				for (int i = 0; i < contentList.size(); i++) {
					if (tawwpModelPlan != null) {
						String contentName = (String) ((List) contentList
								.get(i)).get(0);
						String contentCycleName = (String) ((List) contentList
								.get(i)).get(1);
						String contentCycleNum = String.valueOf(TawwpUtil
								.getCycleNum(contentCycleName));

						String contentFormat = (String) ((List) contentList
								.get(i)).get(2);
						String contentRemark = (String) ((List) contentList
								.get(i)).get(3);
						String executeDay = StaticMethod
								.nullObject2String(((List) contentList.get(i))
										.get(4));
						if (executeDay.indexOf(".") > -1) {
							executeDay = executeDay.substring(0, executeDay
									.indexOf("."));
						}
						// 写执行内容信息
						tawwpModelExecute = new TawwpModelExecute(StaticMethod
								.null2String(contentName), "", "", "",
								tawwpModelPlan, null, "0", contentCycleNum,
								StaticMethod.null2String(contentRemark), "",
								StaticMethod.null2String(contentFormat), "",
								"0", "", "0", _cruser, TawwpUtil
										.getCurrentDateTime(), "", "0", "0",
								"0", "0", "0", "", 0, executeDay); // 形成作业计划执行内容信息

						tawwpModelExecuteDao
								.saveModelExecute(tawwpModelExecute); // 保存作业计划执行内容信息
					} else {
						throw new TawwpException("作业计划模板信息不存在");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new TawwpException("作业计划模板保存出现异常");
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 从Excel导入作业计划模板
	 * 
	 * @param _sourceExcel
	 *            String 上传的Excel
	 * @param _sysTypeId
	 *            String 系统标识
	 * @param _netTypeId
	 *            String 网元标识
	 * @param _cruser
	 *            String 创建人
	 */

	public String importModelFromExcel(String _sourceExcel, String _cruser) {
		// xml路径
		String xmlStr = StaticMethod.CLASSPATH_FLAG
				+ "com/boco/eoms/workplan/util/FMImportModel.xml";
		// 注入
		IFMImportFileManager mgr = (IFMImportFileManager) com.boco.eoms.base.util.ApplicationContextHolder
				.getInstance().getBean("FMImportExcelFileManagerImpl");
		Map map = new HashMap();
		TawwpModelExecute tawwpModelExecute = null;
		TawwpModelPlan tawwpModelPlan = null;
		String returnFlag = "";
		try {
			// 根据xml配置文件和文件路径获取信息集合
			map = mgr.impt(xmlStr, _sourceExcel);
			// 获取第一个页面的值 0
			List list = (List) map.get(new Integer(0));

			String modelNames = "";
			String systeTeyeId = "";
			// String sysTypeName = "";
			String netTypeId = "";
			// String netTypeName="";
			// String modelName = "";
			String cycle = "";
			String executeName = "";
			String remark = "";
			String formart = "";
			int flag = 0;
			String[] nettypeid = null;
			Object[] tawwpModelOjb = new Object[100];
			// 跌待
			for (Iterator it = list.iterator(); it.hasNext();) {
				// 转型
				FMImportModel model = (FMImportModel) it.next();
				System.out.println(model.getCycle() + "" + it.hashCode());
				// 根据穿过来的字典名称得到字典id为数据库准备数据
				if (!"".equals(StaticMethod.null2String(model.getSysTypeId()))) {
					systeTeyeId = tawwpModelPlanDao
							.getDictIdByName(StaticMethod.null2String(model
									.getSysTypeId()));
				}

				// 如果为空 则表示excel文件传过来的值在数据字典内没有值
				if ("".equals(systeTeyeId)) {
					returnFlag = "systeTeyeId";
					throw new Exception(model.getSysTypeId() + "】系统类型没有对应的值");
				}

				if (!"".equals(StaticMethod.null2String(model.getNetTypeid()))) {
					nettypeid = StaticMethod.null2String(model.getNetTypeid())
							.split("/");

				}   

				// 形成作业计划模板对象信息
				for (int j = 0; j < nettypeid.length; j++) {
					 
					// 根据穿过来的字典名称得到字典id为数据库准备数据

					netTypeId = tawwpModelPlanDao.getDictIdByNameAndSysId(
							StaticMethod.null2String(new String(nettypeid[j].getBytes("ISO-8859-1"), "UTF-8")),
							systeTeyeId);
					// 如果为空 则表示excel文件传过来的值在数据字典内没有值
					if ("".equals(netTypeId)) {
						returnFlag = "netTypeId";
						throw new Exception(model.getNetTypeid()
								+ "】网元类型没有对应的值");
					}

					if (!"".equals(StaticMethod.null2String(model
							.getModelNames()))) {
						tawwpModelPlan = new TawwpModelPlan(StaticMethod
								.null2String(model.getModelNames()),
								systeTeyeId, netTypeId, "", "0", "0", _cruser,
								TawwpUtil.getCurrentDateTime());
						// 保存作业计划模板  
						tawwpModelPlanDao.saveModelPlan(tawwpModelPlan);
						tawwpModelOjb[j] = tawwpModelPlan;
						System.out.println(tawwpModelPlan.getId());
						// modelName =
						// StaticMethod.null2String(model.getExecuteNames());
					}
					// 周期
					cycle = getCycleByName(model.getCycle());
					// 执行项名称
					executeName = model.getExecuteNames();
					// 默认填写项
					remark = model.getRemark();

					formart = model.getFormat();
					// 新增执行项对像
					tawwpModelExecute = new TawwpModelExecute(StaticMethod
							.null2String(executeName), "", "", "",
							(TawwpModelPlan)tawwpModelOjb[j], null, "0", cycle, StaticMethod
									.null2String(remark), "", StaticMethod
									.null2String(formart), "", "0", "", "0",
							_cruser, TawwpUtil.getCurrentDateTime(), "", "0",
							"0", "0", "0", "0", "", 0, "0"); // 形成作业计划执行内容信息
					System.out.println(tawwpModelPlan.getId());
					// 保存
					tawwpModelExecuteDao.saveModelExecute(tawwpModelExecute); // 保存作业计划执行内容信息
				}
			}
			/*
			 * List list1 = (List) map.get(new Integer(1)); if (list1 != null) {
			 * System.out.println(list1.size()); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnFlag;
	}

	/**
	 * 通过周期名称获取周期id
	 * 
	 * @param cycleName
	 * @return
	 */
	public static String getCycleByName(String cycleName) {

		String cycle = "0";
		if (cycle != null) {
			if ("其他".equals(cycleName))
				cycle = "0";
			if ("天".equals(cycleName))
				cycle = "1";
			if ("周".equals(cycleName))
				cycle = "2";
			if ("半月".equals(cycleName))
				cycle = "3";
			if ("月".equals(cycleName))
				cycle = "4";
			if ("季度".equals(cycleName))
				cycle = "5";
			if ("半年".equals(cycleName))
				cycle = "6";
			if ("年".equals(cycleName))
				cycle = "7";
			if ("两月".equals(cycleName))
				cycle = "8";
			if ("两月".equals(cycleName))
				cycle = "9";
			if ("班次".equals(cycleName))
				cycle = "1";
		}

		return cycle;
	}

	/**
	 * 创建顶级组织信息
	 * 
	 * @param _tawwpModelGroupManageVO
	 *            TawwpModelGroupManageVO 组织执行管理类
	 * @param _tawwpModelGroup
	 *            TawwpModelGroup 组织信息类
	 */
	private void creatModelGroup(
			TawwpModelGroupManageVO _tawwpModelGroupManageVO,
			TawwpModelGroup _tawwpModelGroup) {

		// TawwpModelGroupDAO tawwpModelGroupDAO = new TawwpModelGroupDAO();

		Hashtable allHashtable = _tawwpModelGroupManageVO.getAllHashtable(); // 获取管理类中的顶级Hashtable

		Hashtable childHashtable = null;
		TawwpModelGroup tawwpModelGroup = null;
		TawwpModelGroupVO tawwpModelGroupVO = new TawwpModelGroupVO();
		tawwpModelGroupVO.setCol(1); // 初始化层次数 1

		try {
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelGroupVO,
					_tawwpModelGroup); // 转换数据

			childHashtable = new Hashtable(); // 建立下属组织信息的Hashtable

			allHashtable.put(tawwpModelGroupVO, childHashtable);

			_tawwpModelGroupManageVO.setAllRow(_tawwpModelGroupManageVO
					.getAllRow() + 1); // 整理总行数

			List modelGroupList = null;

			// 对下属组织信息进行循环处理
			modelGroupList = tawwpModelGroupDao
					.filterTawwpModelGroup(_tawwpModelGroup);
			if (modelGroupList != null) {
				for (int i = 0; i < modelGroupList.size(); i++) {
					tawwpModelGroup = (TawwpModelGroup) modelGroupList.get(i);
					tawwpModelGroupVO.setCol(tawwpModelGroupVO.getCol() + 1); // 层次数累加

					// 对比总列数 与 该子组织的列数，如果小于子组织的列数，则改变总列数
					if (_tawwpModelGroupManageVO.getAllCol() < tawwpModelGroupVO
							.getCol()) {
						_tawwpModelGroupManageVO.setAllCol(tawwpModelGroupVO
								.getCol());
					}

					creatChildModelGroup(_tawwpModelGroupManageVO,
							tawwpModelGroupVO, childHashtable, tawwpModelGroup); // 处理子组织信息
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 创建子组织信息
	 * 
	 * @param _tawwpModelGroupManageVO
	 *            TawwpModelGroupManageVO 组织执行管理类
	 * @param _tawwpModelGroupVO
	 *            TawwpModelGroupVO 组织信息VO类
	 * @param _hashtable
	 *            Hashtable 父类Hashtable
	 * @param _tawwpModelGroup
	 *            TawwpModelGroup 组织信息model类
	 */
	private void creatChildModelGroup(
			TawwpModelGroupManageVO _tawwpModelGroupManageVO,
			TawwpModelGroupVO _tawwpModelGroupVO, Hashtable _hashtable,
			TawwpModelGroup _tawwpModelGroup) {
		// TawwpModelGroupDAO tawwpModelGroupDAO = new TawwpModelGroupDAO();

		Hashtable childHashtable = new Hashtable();
		TawwpModelGroup tawwpModelGroup = null;
		TawwpModelGroupVO tawwpModelGroupVO = new TawwpModelGroupVO();

		try {
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpModelGroupVO,
					_tawwpModelGroup);

			tawwpModelGroupVO.setParentModelGroupVO(_tawwpModelGroupVO);

			if (_hashtable.size() > 0) {
				_tawwpModelGroupManageVO.setAllRow(_tawwpModelGroupManageVO
						.getAllRow() + 1);
				addModelGroupRow(tawwpModelGroupVO.getParentModelGroupVO()); // 递归处理行数
			}

			_tawwpModelGroupVO.setRow(_tawwpModelGroupVO.getRow() + 1);

			tawwpModelGroupVO.setParentModelGroupVO(_tawwpModelGroupVO); // 记录父组织信息
			// _hashtable.put(tawwpModelGroupVO, childHashtable);
			_tawwpModelGroupVO.setChildModelGroupVO(_hashtable);

			List modelGroupList = null;
			modelGroupList = tawwpModelGroupDao
					.filterTawwpModelGroup(_tawwpModelGroup);

			if (modelGroupList != null && modelGroupList.size() > 0) {
				// 如果该子组织信息包含下属信息
				for (int i = 0; i < modelGroupList.size(); i++) {
					tawwpModelGroup = (TawwpModelGroup) modelGroupList.get(i);
					creatChildModelGroup(_tawwpModelGroupManageVO,
							tawwpModelGroupVO, childHashtable, tawwpModelGroup); // 递归调用
				}
			} else {
				// 不包含，则获取执行内容
				_tawwpModelGroup.getTawwpModelExecutes().iterator();
				if (_tawwpModelGroup.getTawwpModelExecutes().size() > 1) {
					_tawwpModelGroupManageVO.setAllRow(_tawwpModelGroupManageVO
							.getAllRow() + 1);
					addModelGroupRow(tawwpModelGroupVO.getParentModelGroupVO());
				}
				_tawwpModelGroupVO.setRow(_tawwpModelGroupVO.getRow()
						+ _tawwpModelGroup.getTawwpModelExecutes().size());
			}
			if (tawwpModelGroupVO != null) {
				_hashtable.put(String.valueOf(_hashtable.size() + 1),
						tawwpModelGroupVO);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进行行数累加处理
	 * 
	 * @param _tawwpModelGroupVO
	 *            TawwpModelGroupVO 父类的组织信息
	 */
	private void addModelGroupRow(TawwpModelGroupVO _tawwpModelGroupVO) {
		TawwpModelGroupVO tawwpModelGroupVO = null;
		_tawwpModelGroupVO.setRow(_tawwpModelGroupVO.getRow() + 1);

		if (_tawwpModelGroupVO.getParentModelGroupVO() != null) {
			tawwpModelGroupVO = _tawwpModelGroupVO.getParentModelGroupVO();
			addModelGroupRow(tawwpModelGroupVO);
		}

	}

	/**
	 * @param tawwpAddonsTableDao
	 *            the tawwpAddonsTableDao to set
	 */
	public void setTawwpAddonsTableDao(ITawwpAddonsTableDao tawwpAddonsTableDao) {
		this.tawwpAddonsTableDao = tawwpAddonsTableDao;
	}

	/**
	 * @param tawwpModelDispatchDao
	 *            the tawwpModelDispatchDao to set
	 */
	public void setTawwpModelDispatchDao(
			ITawwpModelDispatchDao tawwpModelDispatchDao) {
		this.tawwpModelDispatchDao = tawwpModelDispatchDao;
	}

	/**
	 * @param tawwpModelExecuteDao
	 *            the tawwpModelExecuteDao to set
	 */
	public void setTawwpModelExecuteDao(
			ITawwpModelExecuteDao tawwpModelExecuteDao) {
		this.tawwpModelExecuteDao = tawwpModelExecuteDao;
	}

	/**
	 * @param tawwpModelGroupDao
	 *            the tawwpModelGroupDao to set
	 */
	public void setTawwpModelGroupDao(ITawwpModelGroupDao tawwpModelGroupDao) {
		this.tawwpModelGroupDao = tawwpModelGroupDao;
	}

	/**
	 * @param tawwpModelPlanDao
	 *            the tawwpModelPlanDao to set
	 */
	public void setTawwpModelPlanDao(ITawwpModelPlanDao tawwpModelPlanDao) {
		this.tawwpModelPlanDao = tawwpModelPlanDao;
	}

	/**
	 * @param tawwpYearExecuteDao
	 *            the tawwpYearExecuteDao to set
	 */
	public void setTawwpYearExecuteDao(ITawwpYearExecuteDao tawwpYearExecuteDao) {
		this.tawwpYearExecuteDao = tawwpYearExecuteDao;
	}

	/**
	 * @param tawwpYearPlanDao
	 *            the tawwpYearPlanDao to set
	 */
	public void setTawwpYearPlanDao(ITawwpYearPlanDao tawwpYearPlanDao) {
		this.tawwpYearPlanDao = tawwpYearPlanDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workplan.mgr.ITawwpModelMgr#loadModelPlan(java.lang.String)
	 */
	public TawwpModelPlan loadModelPlan(String id) {
		return tawwpModelPlanDao.loadModelPlan(id);
	}

}
