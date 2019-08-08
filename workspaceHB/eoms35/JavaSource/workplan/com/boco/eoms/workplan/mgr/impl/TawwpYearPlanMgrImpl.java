package com.boco.eoms.workplan.mgr.impl;

/**
 * <p>Title: 年度作业计划业务逻辑</p>
 * <p>Description: 年度作业计划信息各功能点的业务逻辑实现</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 * ------------------------------------------------------------------------
 * 此BO类 包含以下业务逻辑:
 * <p>
 * 业务逻辑(用例标识)                                             对应方法名
 * <p>
 * 定制年度作业计划，只生成年度作业计划(CUSTOMIZE-YEAR-PLAN-001)     addYearPlan
 * 定制年度作业计划，根据模版生成(CUSTOMIZE-YEAR-PLAN-002)          addYearPlan
 * 增加年度作业计划执行内容(ADD-PLAN-CONTENT-001)                  addYearExecute
 * 提交审批(PUTIN-YEAR-PLAN-AUDITING-001)                        addYearCheck
 * 编辑年度作业计划执行内容(EDIT-PLAN-CONTENT-001)                 editYearExecute
 * 审批年度作业计划(AUDITING-YEAR-PLAN-001)                       editYearCheck
 * 删除年度作业计划(DELETE-PLAN-001)                              removeYearPlan
 * 删除年度作业计划执行内容(DELETE-PLAN-CONTENT-001)               removeYearExecute
 * <p>
 * <p>
 * ------------------------------------------------------------------------
 * ------------------------------------------------------------------------
 * 此BO类 包含以下业务逻辑:
 * <p>
 * 业务逻辑(用例标识)                                             对应方法名
 * <p>
 * 定制年度作业计划，只生成年度作业计划(CUSTOMIZE-YEAR-PLAN-001)     addYearPlan
 * 定制年度作业计划，根据模版生成(CUSTOMIZE-YEAR-PLAN-002)          addYearPlan
 * 增加年度作业计划执行内容(ADD-PLAN-CONTENT-001)                  addYearExecute
 * 提交审批(PUTIN-YEAR-PLAN-AUDITING-001)                        addYearCheck
 * 编辑年度作业计划执行内容(EDIT-PLAN-CONTENT-001)                 editYearExecute
 * 审批年度作业计划(AUDITING-YEAR-PLAN-001)                       editYearCheck
 * 删除年度作业计划(DELETE-PLAN-001)                              removeYearPlan
 * 删除年度作业计划执行内容(DELETE-PLAN-CONTENT-001)               removeYearExecute
 * <p>
 * <p>
 * ------------------------------------------------------------------------
 */

/**------------------------------------------------------------------------
 * 此BO类 包含以下业务逻辑:
 *
 * 业务逻辑(用例标识)                                             对应方法名
 *
 * 定制年度作业计划，只生成年度作业计划(CUSTOMIZE-YEAR-PLAN-001)     addYearPlan
 * 定制年度作业计划，根据模版生成(CUSTOMIZE-YEAR-PLAN-002)          addYearPlan
 * 增加年度作业计划执行内容(ADD-PLAN-CONTENT-001)                  addYearExecute
 * 提交审批(PUTIN-YEAR-PLAN-AUDITING-001)                        addYearCheck
 * 编辑年度作业计划执行内容(EDIT-PLAN-CONTENT-001)                 editYearExecute
 * 审批年度作业计划(AUDITING-YEAR-PLAN-001)                       editYearCheck
 * 删除年度作业计划(DELETE-PLAN-001)                              removeYearPlan
 * 删除年度作业计划执行内容(DELETE-PLAN-CONTENT-001)               removeYearExecute
 *
 *
 ------------------------------------------------------------------------*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import jxl.CellType;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpModelExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpModelGroupDao;
import com.boco.eoms.workplan.dao.ITawwpModelPlanDao;
import com.boco.eoms.workplan.dao.ITawwpNetDao;
import com.boco.eoms.workplan.dao.ITawwpYearCheckDao;
import com.boco.eoms.workplan.dao.ITawwpYearExecuteDao;
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.flow.TawwpFlowManage;
import com.boco.eoms.workplan.flow.model.Step;
import com.boco.eoms.workplan.mgr.ITawwpYearPlanMgr;
import com.boco.eoms.workplan.model.TawwpModelExecute;
import com.boco.eoms.workplan.model.TawwpModelGroup;
import com.boco.eoms.workplan.model.TawwpModelPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpYearCheck;
import com.boco.eoms.workplan.model.TawwpYearExecute;
import com.boco.eoms.workplan.model.TawwpYearPlan;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpYearCheckVO;
import com.boco.eoms.workplan.vo.TawwpYearExecuteVO;
import com.boco.eoms.workplan.vo.TawwpYearPlanVO;

// import com.boco.eoms.gzjhhead.interfaces.*;

public class TawwpYearPlanMgrImpl implements ITawwpYearPlanMgr {

    private ITawwpYearPlanDao tawwpYearPlanDao;

    private ITawwpModelPlanDao tawwpModelPlanDao;

    private ITawwpYearExecuteDao tawwpYearExecuteDao;

    private ITawwpModelGroupDao tawwpModelGroupDao;

    private ITawwpModelExecuteDao tawwpModelExecuteDao;

    private ITawwpYearCheckDao tawwpYearCheckDao;

    private ITawwpNetDao tawwpNetDao;

    /**
     * 业务逻辑：定制年度作业计划，不根据模版生成(CUSTOMIZE-YEAR-PLAN-002)
     *
     * @param _sysType
     *            String 系统类型
     * @param _netType
     *            String 网元类型
     * @param _content
     *            String 作业计划内容
     * @param _cruser
     *            String 创建人
     * @param _deptId
     *            String 部门ID
     * @param _name
     *            String 年度作业计划名称
     * @param _netList
     *            String 对应的网元信息
     * @param _remark
     *            String 备注
     * @param _yearFlag
     *            String 年度信
     * @return TawwpYearPlan 年度作业计划对象
     * @throws Exception
     *             异常信息
     */

    public TawwpYearPlan addYearPlanByNoModel(String _sysType, String _netType,
                                              String _content, String _cruser, String _deptId, String _name,
                                              String _netList, String _isApp, String _remark, String _yearFlag, String _typeIndex)
            throws Exception {

        TawwpModelPlan tawwpModelPlan = null;
        // 获取年度作业计划对象
        TawwpYearPlan tawwpYearPlan = new TawwpYearPlan(_name, _deptId,
                _sysType, _netType, "0", _cruser, _content, _remark, "", _isApp,
                TawwpUtil.getCurrentDateTime(), "0", _yearFlag, tawwpModelPlan,
                _typeIndex);

        try {
            // 保存年度作业计划
            tawwpYearPlanDao.saveYearPlan(tawwpYearPlan);
            return tawwpYearPlan;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划保存出现异常");
        }
    }

    /**
     * 业务逻辑：定制年度作业计划，根据模版生成(CUSTOMIZE-YEAR-PLAN-002)
     *
     * @param _sysType
     *            String 系统类型
     * @param _netType
     *            String 网元类型
     * @param _content
     *            String 作业计划内容
     * @param _cruser
     *            String 创建人
     * @param _deptId
     *            String 部门ID
     * @param _modelPlanId
     *            String 作业计划模版ID
     * @param _netList
     *            String 对应的网元信息
     * @param _remark
     *            String 备注
     * @param _yearFlag
     *            String 年度信
     * @return TawwpYearPlan 年度作业计划对象
     * @throws Exception
     *             异常信息
     */
    public TawwpYearPlan addYearPlan(String _sysType, String _netType,
                                     String _content, String _cruser, String _deptId,
                                     String _modelPlanId, String _netList, String _isApp, String _remark,
                                     String _yearFlag) throws Exception {

        TawwpModelPlan tawwpModelPlan = null;
        TawwpModelExecute tawwpModelExecute = null;
        TawwpYearExecute tawwpYearExecute = null;
        TawwpYearPlan tawwpYearPlan = null;

        // 获取作业计划模板对象
        tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId);
        if (tawwpYearPlanDao.countYearplanByYearDept(_deptId, _yearFlag,
                tawwpModelPlan) == 0) {
            // 获取年度作业计划对象
            tawwpYearPlan = new TawwpYearPlan(tawwpModelPlan.getName(),
                    _deptId, _sysType, _netType, "0", _cruser, _content,
                    _remark, "", _isApp, TawwpUtil.getCurrentDateTime(), "0",
                    _yearFlag, tawwpModelPlan);

            try {
                // 保存年度作业计划
                tawwpYearPlanDao.saveYearPlan(tawwpYearPlan);

                // 获取作业计划模版执行内容集合
                Iterator iterator = tawwpModelPlan.getTawwpModelExecutes()
                        .iterator();

                // 循环处理作业计划模版执行内容，生成年度作业计划执行内容，并进行保存操作
                while (iterator.hasNext()) {
                    tawwpModelExecute = (TawwpModelExecute) iterator.next(); // 获取一个作业计划模版执行内容对象

                    tawwpYearExecute = new TawwpYearExecute();
                    tawwpYearExecute.setName(tawwpModelExecute.getName());
                    tawwpYearExecute.setCycle(tawwpModelExecute.getCycle());
                    tawwpYearExecute.setFormId(tawwpModelExecute.getFormId());
                    tawwpYearExecute.setRemark(tawwpModelExecute.getRemark());
                    tawwpYearExecute.setDeleted(tawwpModelExecute.getDeleted());
                    tawwpYearExecute.setFormat(tawwpModelExecute.getFormat());
                    tawwpYearExecute.setNetTypeId(tawwpModelExecute
                            .getNetTypeId());
                    tawwpYearExecute.setSerialNo(tawwpModelExecute
                            .getSerialNo());
                    tawwpYearExecute.setXlsX(tawwpModelExecute.getXlsX());
                    tawwpYearExecute.setXlsY(tawwpModelExecute.getXlsY());
                    tawwpYearExecute.setIsHoliday(tawwpModelExecute.getIsHoliday());
                    tawwpYearExecute.setIsWeekend(tawwpModelExecute.getIsWeekend());
                    tawwpYearExecute.setTawwpModelExecute(tawwpModelExecute);
                    if (Integer.parseInt(tawwpModelExecute.getCycle()) > 4) {
                        tawwpYearExecute.setMonthFlag("1"); // 如果周期为月以上的,则需要进行月度执行表示
                    } else {
                        tawwpYearExecute.setMonthFlag("");
                    }
                    tawwpYearExecute.setTawwpYearPlan(tawwpYearPlan);

                    // 完善其他年度作业计划执行内容属性
                    tawwpYearExecute.setCruser(_cruser);
                    tawwpYearExecute.setCrtime(TawwpUtil.getCurrentDateTime()); // 获取当前系统时间

                    tawwpYearExecuteDao.saveYearExecute(tawwpYearExecute);
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new TawwpException("年度作业计划保存出现异常");
            }
        }
        return tawwpYearPlan;
    }

    public TawwpYearPlan addYearPlan(String _sysType, String _netType, String _planname,
                                     String _content, String _cruser, String _deptId,
                                     String _modelPlanId, String _netList, String _isApp, String _remark,
                                     String _yearFlag, String typeIndex) throws Exception {
        // 创建模板、年度对象

        TawwpModelPlan tawwpModelPlan = null;
        TawwpModelExecute tawwpModelExecute = null;
        TawwpYearExecute tawwpYearExecute = null;
        TawwpYearPlan tawwpYearPlan = null;

        // 获取作业计划模板对象
        tawwpModelPlan = tawwpModelPlanDao.loadModelPlan(_modelPlanId);
        if (tawwpYearPlanDao.countYearplanByYearDept(_deptId, _yearFlag,
                tawwpModelPlan) == 0) {
            // 获取年度作业计划对象
            tawwpYearPlan = new TawwpYearPlan(_planname,
                    _deptId, _sysType, _netType, "0", _cruser, _content,
                    _remark, "", _isApp, TawwpUtil.getCurrentDateTime(), "0",
                    _yearFlag, tawwpModelPlan, typeIndex);

            try {
                // 保存年度作业计划
                tawwpYearPlanDao.saveYearPlan(tawwpYearPlan);

                // 获取作业计划模版执行内容集合
                Iterator iterator = tawwpModelPlan.getTawwpModelExecutes()
                        .iterator();

                // 循环处理作业计划模版执行内容，生成年度作业计划执行内容，并进行保存操作
                while (iterator.hasNext()) {
                    tawwpModelExecute = (TawwpModelExecute) iterator.next(); // 获取一个作业计划模版执行内容对象

                    tawwpYearExecute = new TawwpYearExecute();
                    tawwpYearExecute.setName(tawwpModelExecute.getName());
                    tawwpYearExecute.setBotype(tawwpModelExecute.getBotype());
                    tawwpYearExecute.setExecutedeptlevel(tawwpModelExecute.getExecutedeptlevel());
                    tawwpYearExecute.setAppdesc(tawwpModelExecute.getAppdesc());
                    tawwpYearExecute.setCycle(tawwpModelExecute.getCycle());
                    tawwpYearExecute.setFormId(tawwpModelExecute.getFormId());
                    tawwpYearExecute.setRemark(tawwpModelExecute.getRemark());
                    tawwpYearExecute.setDeleted(tawwpModelExecute.getDeleted());
                    tawwpYearExecute.setFormat(tawwpModelExecute.getFormat());
                    tawwpYearExecute.setCommand(tawwpModelExecute.getTaskid());
                    tawwpYearExecute.setNetTypeId(tawwpModelExecute
                            .getNetTypeId());
                    tawwpYearExecute.setSerialNo(tawwpModelExecute
                            .getSerialNo());

                    tawwpYearExecute.setXlsX(tawwpModelExecute.getXlsX());
                    tawwpYearExecute.setXlsY(tawwpModelExecute.getXlsY());
                    tawwpYearExecute.setIsHoliday(tawwpModelExecute.getIsHoliday());
                    tawwpYearExecute.setIsWeekend(tawwpModelExecute.getIsWeekend());
                    tawwpYearExecute.setTawwpModelExecute(tawwpModelExecute);
                    tawwpYearExecute.setNote(tawwpModelExecute.getNote());
                    if (Integer.parseInt(tawwpModelExecute.getCycle()) > 4) {
                        tawwpYearExecute.setMonthFlag(tawwpModelExecute.getMonthFlag()); // 如果周期为月以上的,则需要进行月度执行表示
                    } else {
                        tawwpYearExecute.setMonthFlag("");
                    }
                    tawwpYearExecute.setTawwpYearPlan(tawwpYearPlan);
                    tawwpYearExecute.setAccessories(tawwpModelExecute.getAccessories());    // add by gongyufeng  执行指导文件
                    // 完善其他年度作业计划执行内容属性
                    tawwpYearExecute.setCruser(_cruser);
                    tawwpYearExecute.setCrtime(TawwpUtil.getCurrentDateTime()); // 获取当前系统时间
                    tawwpYearExecute.setFileFlag(tawwpModelExecute.getFileFlag());
                    tawwpYearExecute.setExecuteDay(tawwpModelExecute.getExecuteDay());
                    tawwpYearExecuteDao.saveYearExecute(tawwpYearExecute);
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new TawwpException("年度作业计划保存出现异常");
            }
        }
        return tawwpYearPlan;
    }

    /**
     * 业务逻辑：增加年度作业计划执行内容(ADD-PLAN-CONTENT-001)
     *
     * @param _command
     *            String 命令标识(智能巡检接口)
     * @param _cruser
     *            String 创建人
     * @param _cycle
     *            String 周期
     * @param _executeId
     *            String 执行内容ID
     * @param _format
     *            String 填写格式
     * @param _formId
     *            String 执行表格标识
     * @param _groupId
     *            String 组织信息表示标识
     * @param _monthFlag
     *            String 周期为月以上的周期，制定执行在第几个月执行
     * @param _name
     *            String 年度作业计划执行内容名称
     * @param _netTypeId
     *            String 网元类型
     * @param _remark
     *            String 备注
     * @param _yearPlanId
     *            String 年度作业计划标识
     * @param _isHoliday
     *            String 是否假日排班 0 是 1 否
     * @param _isWeekend
     *            String 是否周末排班 0 是 1 否
     *
     * @throws Exception
     *             异常信息
     */

    public void addYearExecute(String _command, String _cruser, String _cycle,
                               String _executeId, String _format, String _formId, String _groupId,
                               String _monthFlag, String _name, String _botype, String _executedeptlevel, String _appdesc, String _netTypeId, String _remark,
                               String _yearPlanId, String _isHoliday, String _isWeekend, String _fileFlag, String _executer, String _executeDuty, String _executeRoom, String _executerType, String executeDay, String accessories)
            throws Exception {

        // 初始化数据,创建DAO对象

        TawwpYearExecute tawwpYearExecute = null;
        TawwpModelExecute tawwpModelExecute = null;
        TawwpModelGroup tawwpModelGroup = null;

        TawwpYearPlan tawwpYearPlan = null;

        try {
            tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId); // 获取年度作业计划信息

            if (_groupId != null) {
                tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_groupId); // 获取年度作业计划组织信息
            }
            if (_executeId != null) {
                tawwpModelExecute = tawwpModelExecuteDao
                        .loadModelExecute(_executeId); // 获取模版作业计划执行内容
            }

            if (tawwpYearPlan != null) { // 如果年度作业计划存在
                tawwpYearExecute = new TawwpYearExecute(_name, _botype, _executedeptlevel, _appdesc, _cycle, _remark,
                        _format, "", _formId, "0", _cruser, TawwpUtil
                        .getCurrentDateTime(), "", "0", "0",
                        _netTypeId, _command, _monthFlag, tawwpModelGroup,
                        tawwpYearPlan, tawwpModelExecute, _isHoliday,
                        _isWeekend, _fileFlag, "", 0, _executer, _executeDuty, _executeRoom, _executerType, executeDay); // 形成年度作业计划执行内容信息
                tawwpYearExecute.setAccessories(accessories);
                tawwpYearExecuteDao.saveYearExecute(tawwpYearExecute); // 保存作业计划执行内容信息
            } else {
                throw new TawwpException("年度作业计划信息不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划执行内容保存出现异常");
        }

    }

    public void addYearExecute(String _command, String _cruser, String _cycle,
                               String _executeId, String _format, String _formId, String _groupId,
                               String _monthFlag, String _name, String _botype, String _executedeptlevel, String _appdesc, String _netTypeId, String _remark, String _note,
                               String _yearPlanId, String _isHoliday, String _isWeekend, String _fileFlag, String _executer, String _executeDuty, String _executeRoom, String _executerType, String executeDay, String accessories)
            throws Exception {

        // 初始化数�?创建DAO对象

        TawwpYearExecute tawwpYearExecute = null;
        TawwpModelExecute tawwpModelExecute = null;
        TawwpModelGroup tawwpModelGroup = null;

        TawwpYearPlan tawwpYearPlan = null;

        try {
            tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId); // 获取年度作业计划信息

            if (_groupId != null) {
                tawwpModelGroup = tawwpModelGroupDao.loadModelGroup(_groupId); // 获取年度作业计划组织信息
            }
            if (_executeId != null) {
                tawwpModelExecute = tawwpModelExecuteDao
                        .loadModelExecute(_executeId); // 获取模版作业计划执行内容
            }

            if (tawwpYearPlan != null) { // 如果年度作业计划存在
                tawwpYearExecute = new TawwpYearExecute(_name, _botype, _executedeptlevel, _appdesc, _cycle, _remark, _note,
                        _format, "", _formId, "0", _cruser, TawwpUtil
                        .getCurrentDateTime(), "", "0", "0",
                        _netTypeId, _command, _monthFlag, tawwpModelGroup,
                        tawwpYearPlan, tawwpModelExecute, _isHoliday,
                        _isWeekend, _fileFlag, "", 0, _executer, _executeDuty, _executeRoom, _executerType, executeDay); // 形成年度作业计划执行内容信息
                tawwpYearExecute.setAccessories(accessories);
                tawwpYearExecuteDao.saveYearExecute(tawwpYearExecute); // 保存作业计划执行内容信息
            } else {
                throw new TawwpException("年度作业计划信息不存��“在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划执行内容保存出现异常");
        }

    }

    /**
     * 业务逻辑：修改年度作业计划执行内容(EDIT-PLAN-CONTENT-001)
     *
     * @param _command
     *            String 命令标识(智能巡检接口)
     * @param _cycle
     *            String 周期
     * @param _executeId
     *            String 执行内容标识
     * @param _formId
     *            String 执行表格标识
     * @param _format
     *            String 填写格式
     * @param _monthFlag
     *            String 周期为月以上的周期，制定执行在第几个月执行
     * @param _name
     *            String 年度作业计划执行内容名称
     * @param _netTypeId
     *            String 网元类型
     * @param _remark
     *            String 备注
     * @param _isHoliday
     *            String 是否假日排班 0 是 1 否
     * @param _isWeekend
     *            String 是否周末排班 0 是 1 否
     *
     * @throws Exception
     *             异常信息
     */
    public void editYearExecute(String _cycle, String _command,
                                String _executeId, String _formId, String _format,
                                String _monthFlag, String _name, String _botype, String _executedeptlevel, String _appdesc, String _netTypeId, String _remark,
                                String _isHoliday, String _isWeekend, String _fileFlag, String _executer, String _executeDuty, String _executeRoom, String _executerType, String executeDay) throws Exception {

        // 创建对象
        TawwpYearExecute tawwpYearExecute = null;

        try {
            // 获取年度作业计划执行内容信息
            tawwpYearExecute = tawwpYearExecuteDao.loadYearExecute(_executeId);

            if (tawwpYearExecute != null) {
                tawwpYearExecute.setCycle(_cycle);
                tawwpYearExecute.setCommand(_command);
                tawwpYearExecute.setFormId(_formId);
                tawwpYearExecute.setFormat(_format);
                tawwpYearExecute.setMonthFlag(_monthFlag);
                tawwpYearExecute.setName(_name);
                tawwpYearExecute.setBotype(_botype);
                tawwpYearExecute.setExecutedeptlevel(_executedeptlevel);
                tawwpYearExecute.setAppdesc(_appdesc);
                tawwpYearExecute.setNetTypeId(_netTypeId);
                tawwpYearExecute.setRemark(_remark);
                tawwpYearExecute.setIsHoliday(_isHoliday);
                tawwpYearExecute.setIsWeekend(_isWeekend);
                tawwpYearExecute.setExecuter(_executer);
                tawwpYearExecute.setExecuteDuty(_executeDuty);
                tawwpYearExecute.setExecuteRoom(_executeRoom);
                tawwpYearExecute.setExecuterType(_executerType);
                tawwpYearExecute.setFileFlag(_fileFlag);
                tawwpYearExecute.setExecuteDay(executeDay);
                // 更新作业计划模板执行内容信息
                tawwpYearExecuteDao.updateYearExecute(tawwpYearExecute);
            } else {
                throw new TawwpException("年度作业计划执行内容信息不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划执行内容修改出现异常");
        }

    }

    public void editYearExecute(String _cycle, String _command,
                                String _executeId, String _formId, String _format,
                                String _monthFlag, String _name, String _botype, String _executedeptlevel, String _appdesc, String _netTypeId, String _remark, String _note,
                                String _isHoliday, String _isWeekend, String _fileFlag, String _executer, String _executeDuty, String _executeRoom, String _executerType, String executeDay) throws Exception {

        // 创建对象
        TawwpYearExecute tawwpYearExecute = null;

        try {
            // 获取年度作业计划执行内容信息
            tawwpYearExecute = tawwpYearExecuteDao.loadYearExecute(_executeId);

            if (tawwpYearExecute != null) {
                tawwpYearExecute.setCycle(_cycle);
                tawwpYearExecute.setCommand(_command);
                tawwpYearExecute.setFormId(_formId);
                tawwpYearExecute.setFormat(_format);
                tawwpYearExecute.setMonthFlag(_monthFlag);
                tawwpYearExecute.setName(_name);
                tawwpYearExecute.setBotype(_botype);
                tawwpYearExecute.setExecutedeptlevel(_executedeptlevel);
                tawwpYearExecute.setAppdesc(_appdesc);
                tawwpYearExecute.setNetTypeId(_netTypeId);
                tawwpYearExecute.setRemark(_remark);
                tawwpYearExecute.setIsHoliday(_isHoliday);
                tawwpYearExecute.setIsWeekend(_isWeekend);
                tawwpYearExecute.setExecuter(_executer);
                tawwpYearExecute.setExecuteDuty(_executeDuty);
                tawwpYearExecute.setExecuteRoom(_executeRoom);
                tawwpYearExecute.setExecuterType(_executerType);
                tawwpYearExecute.setFileFlag(_fileFlag);
                tawwpYearExecute.setExecuteDay(executeDay);
                tawwpYearExecute.setNote(_note);
                // 更新作业计划模板执行内容信息
                tawwpYearExecuteDao.updateYearExecute(tawwpYearExecute);
            } else {
                throw new TawwpException("年度作业计划执行内容信息不存��“在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划执行内容修改出现异常");
        }

    }

    /**
     * 业务逻辑：删除年度作业计划执行内容(DELETE-PLAN-CONTENT-001)
     *
     * @param _executeId
     *            String 执行内容标识
     * @throws Exception
     *             异常
     */
    public void removeYearExecute(String _executeId) throws Exception {
        try {

            TawwpYearExecute tawwpYearExecute = null;

            // 获取年度作业计划执行内容信息
            tawwpYearExecute = tawwpYearExecuteDao.loadYearExecute(_executeId);

            if (tawwpYearExecute != null) {
                // 删除年度作业计划执行内容
                tawwpYearExecuteDao.deleteYearExecute(tawwpYearExecute);
            } else {
                throw new TawwpException("年度作业计划执行内容信息不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划执行内容删除出现异常");
        }
    }

    /**
     * 业务逻辑：删除年度作业计划(DELETE-PLAN-001)
     *
     * @param _yearPlanId
     *            String 年度作业计划标识
     * @throws Exception
     *             异常信息
     */
    public void removeYearPlan(String _yearPlanId) throws Exception {
        // 初始化数据,创建DAO对象

        TawwpYearPlan tawwpYearPlan = null;
        try {
            tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId); // 获取年度作业计划对象
            if (tawwpYearPlan != null) {
                Set set = tawwpYearPlan.getTawwpMonthPlans();
                if (set != null && set.size() > 0) {
                    tawwpYearPlan.setDeleted("1");
                    tawwpYearPlanDao.updateYearPlan(tawwpYearPlan); // 删除年度作业计划(逻辑删除)
                } else {
                    tawwpYearPlanDao.deleteYearPlan(tawwpYearPlan); // 删除年度作业计划(物理删除)
                }
            } else {
                throw new TawwpException("年度作业计划执行内容信息不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划删除出现异常");
        }

    }

    /**
     * 业务逻辑：提交审批(PUTIN-YEAR-PLAN-AUDITING-001)
     *
     * @param _checkUser
     *            String 审批人
     * @param _deptId
     *            String 部门
     * @param _yearPlanId
     *            String 年度作业计划标识
     * @param _flowSerial
     *            String 流程标识
     * @param _stepSerial
     *            String 步骤标识
     * @throws Exception
     *             异常信息
     * @return boolean 操作结果，如果年度作业计划不完整则返回false
     */
    public TawwpYearCheck addYearCheck(String _checkUser, String _deptId,
                                       String _yearPlanId, String _flowSerial, String _stepSerial)
            throws Exception {

        // 创建对象
        TawwpYearPlan _tawwpYearPlan = new TawwpYearPlan();

        TawwpYearCheck tawwpYearCheck = null;

        boolean flag = true;

        _tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_yearPlanId);

        // 验证年度作业计划的完整性
        if (_tawwpYearPlan.getTawwpYearExecutes().isEmpty()) {
            flag = false;
        }

        try {
            if (flag) {
                if (_tawwpYearPlan != null) {
                    _tawwpYearPlan.setState("3");
                    tawwpYearPlanDao.updateYearPlan(_tawwpYearPlan); // 更新年度作业计划
                    tawwpYearCheck = new TawwpYearCheck(_deptId, _checkUser,
                            "", "0", TawwpUtil.getCurrentDateTime(), null,
                            _flowSerial, _stepSerial, _tawwpYearPlan, null); // 生成审批对象

                    tawwpYearCheckDao.saveYearCheck(tawwpYearCheck); // 保存

                    // 发送短信信息给审批人ccccccccc
                    /*
                     * TawwpUtil.sendSMS(_checkUser, "年度作业计划《" +
                     * StaticMethod.null2String(_tawwpYearPlan.getName()) +
                     * "》需要您审批");
                     */
                    try {
                        TawwpUtil.sendSMS(_checkUser, "年度作业计划《"
                                + StaticMethod.null2String(_tawwpYearPlan
                                .getName()) + "》需要您审批", _tawwpYearPlan
                                .getId());

                        TawwpUtil.sendMail(_checkUser, "年度作业计划《"
                                + StaticMethod.null2String(_tawwpYearPlan
                                .getName()) + "》需要您审批", _tawwpYearPlan
                                .getId());
                    } catch (Exception e) {
                        System.out.print("消息发送异常" + e);
                    }
                } else {
                    throw new TawwpException("年度作业计划不存在");
                }
            }

            return tawwpYearCheck;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("提交审批保存出现异常");
        }
    }

    /**
     * 业务逻辑：通过审批(PASS-YEAR-PLAN-AUDITING-001)
     *
     * @param _yearCheckId
     *            String 年度作业计划审批标识
     * @param _checkUser
     *            String 下一步骤审批人，如果步骤不存在可以为空
     * @param _content
     *            String 审批内容
     * @param _currentCheckUser
     *            String 审批人
     * @throws Exception
     *             异常信息
     */
    public void passYearCheck(String _yearCheckId, String _checkUser,
                              String _content, String _currentCheckUser) throws Exception {
        // 初始化数据,创建DAO对象

        TawwpFlowManage tawwpFlowManage = null;

        // 创建对象
        TawwpYearCheck tawwpYearCheck = null;
        TawwpYearPlan tawwpYearPlan = null;
        Step step = null;

        try {
            tawwpYearCheck = tawwpYearCheckDao.loadYearCheck(_yearCheckId); // 引导年度作业计划审批信息
            tawwpYearPlan = tawwpYearCheck.getTawwpYearPlan(); // 获取年度作业计划信息

            // 如果年度作业计划审批信息存在
            if (tawwpYearCheck != null) {

                tawwpYearCheck.setState("1"); // 将当前年度作业计划审批信息状态改为“通过”
                tawwpYearCheck.setContent(_content); // 审批内容
                tawwpYearCheck.setChecktime(TawwpUtil.getCurrentDateTime()); // 审批时间
                tawwpYearCheck.setCheckUser(_currentCheckUser); // 审批人
                tawwpYearCheckDao.updateYearCheck(tawwpYearCheck); // 更新年度审批

                // 获取流程管理对象
                tawwpFlowManage = TawwpFlowManage.getInstance();
                step = tawwpFlowManage.getYearFlowStep(tawwpYearCheck
                                .getFlowSerial(), tawwpYearCheck.getStepSerial(),
                        tawwpYearPlan.getDeptId()); // 获取下一步骤对象

                if (step == null) {
                    // 如果步骤对象为空，整体流程结束
                    tawwpYearPlan.setState("1");
                    tawwpYearPlanDao.updateYearPlan(tawwpYearPlan); // 修改年度作业计划状态“通过”

                    // 发送短信信息给创建人
                    /*
                     * TawwpUtil.sendSMS(tawwpYearPlan.getCruser(), "年度作业计划《" +
                     * StaticMethod.null2String(tawwpYearPlan.getName()) +
                     * "》通过");
                     */
                    try {
                        TawwpUtil.sendSMS(tawwpYearPlan.getCruser(), "年度作业计划《"
                                + StaticMethod.null2String(tawwpYearPlan
                                .getName()) + "》通过", tawwpYearPlan
                                .getId());
                        TawwpUtil.closeSMS(tawwpYearPlan.getId());
                        TawwpUtil.sendMail(tawwpYearPlan.getCruser(), "年度作业计划《"
                                + StaticMethod.null2String(tawwpYearPlan
                                .getName()) + "》通过", tawwpYearPlan
                                .getId());
                        TawwpUtil.closeEmail(tawwpYearPlan.getId());
                    } catch (Exception e) {
                        System.out.print("消息发送异常" + e);
                    }

                } else {
                    // 如果步骤对象不为空
                    if (_checkUser != null) {
                        // 执行人是不为空
                        this.addYearCheck(_checkUser,
                                tawwpYearPlan.getDeptId(), tawwpYearPlan
                                        .getId(), tawwpYearCheck
                                        .getFlowSerial(), step.getSerial()); // 创建下一步骤的审批信息

                        /*
                         * //发送短信信息给审批人 TawwpUtil.sendSMS(_checkUser, "年度作业计划《" +
                         * StaticMethod.null2String(tawwpYearPlan.getName()) +
                         * "》需要您审批");
                         */
                        try {
                            TawwpUtil.sendSMS(_checkUser, "年度作业计划《"
                                    + StaticMethod.null2String(tawwpYearPlan
                                    .getName()) + "》需要您审批", tawwpYearPlan
                                    .getId());
                            TawwpUtil.closeSMS(tawwpYearPlan.getId());
                            TawwpUtil.sendMail(_checkUser, "年度作业计划《"
                                    + StaticMethod.null2String(tawwpYearPlan
                                    .getName()) + "》需要您审批", tawwpYearPlan
                                    .getId());
                            TawwpUtil.closeEmail(tawwpYearPlan.getId());
                        } catch (Exception e) {
                            System.out.print("消息发送异常" + e);
                        }

                    } else {
                        // 异常
                        throw new TawwpException("年度作业计划审批流程存在，但没有指定审批人");
                    }
                }
            } else {
                throw new TawwpException("年度作业计划审批不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("提交审批保存出现异常");
        }

    }

    /**
     * 业务逻辑：驳回审批(REJECT-YEAR-PLAN-AUDITING-001)
     *
     * @param _yearCheckId
     *            String 年度作业计划审批标识
     * @param _content
     *            String 审批内容
     * @param _checkUser
     *            String 审批人
     * @throws Exception
     *             异常信息
     */
    public void rejectYearCheck(String _yearCheckId, String _content,
                                String _checkUser) throws Exception {

        // 初始化数据,创建DAO对象

        // 创建对象
        TawwpYearCheck tawwpYearCheck = null;
        TawwpYearPlan tawwpYearPlan = null;

        try {
            tawwpYearCheck = tawwpYearCheckDao.loadYearCheck(_yearCheckId); // 引导年度作业计划审批信息
            tawwpYearPlan = tawwpYearCheck.getTawwpYearPlan(); // 获取年度作业计划信息

            // 如果年度作业计划审批信息存在
            if (tawwpYearCheck != null) {

                tawwpYearCheck.setState("2"); // 将当前年度作业计划审批信息状态改为“驳回”
                tawwpYearCheck.setContent(_content); // 审批内容
                tawwpYearCheck.setCheckUser(_checkUser); // 具体审批人
                tawwpYearCheck.setChecktime(TawwpUtil.getCurrentDateTime()); // 审批时间

                tawwpYearCheckDao.updateYearCheck(tawwpYearCheck); // 保存年度作业计划

                tawwpYearPlan.setState("2"); // 将当前年度作业计划状态改为“驳回”
                tawwpYearPlanDao.updateYearPlan(tawwpYearPlan); // 保存年度作业计划审批信息

                // 发送短信信息给创建人
                /*
                 * TawwpUtil.sendSMS(tawwpYearPlan.getCruser(), "年度作业计划《" +
                 * StaticMethod.null2String(tawwpYearPlan.getName()) +
                 * "》被驳回");
                 */
                try {
                    TawwpUtil
                            .sendSMS(tawwpYearPlan.getCruser(), "年度作业计划《"
                                    + StaticMethod.null2String(tawwpYearPlan
                                    .getName()) + "》被驳回", tawwpYearPlan
                                    .getId());
                    TawwpUtil.closeSMS(tawwpYearPlan.getId());
                    TawwpUtil
                            .sendMail(tawwpYearPlan.getCruser(), "年度作业计划《"
                                    + StaticMethod.null2String(tawwpYearPlan
                                    .getName()) + "》被驳回", tawwpYearPlan
                                    .getId());
                    TawwpUtil.closeEmail(tawwpYearPlan.getId());
                } catch (Exception e) {
                    System.out.print("消息发送异常" + e);
                }

            } else {
                throw new TawwpException("年度作业计划审批不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("提交审批保存出现异常");
        }

    }

    /**
     * 业务逻辑：浏览年度作业计划(VIEW-YEAR-PLAN-001)
     *
     * @param _id
     *            String 年度作业计划标识
     * @throws TawwpException
     *             异常信息
     * @return TawwpYearPlanVO 年度作业计划对象
     */
    public TawwpYearPlanVO viewYearPlanVO(String _id) throws TawwpException {
        TawwpYearPlanVO tawwpYearPlanVO = null;
        TawwpYearCheckVO tawwpYearCheckVO = null;
        TawwpYearCheckVO tempTawwpYearCheckVO = null;
        List list = null;
        Step step = null;

        tawwpYearPlanVO = this.getYearPlan(_id);

        list = tawwpYearPlanVO.getYearCheckList();

        for (int i = 0; i < list.size(); i++) {
            tawwpYearCheckVO = (TawwpYearCheckVO) list.get(i);
            if (tempTawwpYearCheckVO == null) {
                tempTawwpYearCheckVO = tawwpYearCheckVO;
            } else if (tempTawwpYearCheckVO.getCrtime().compareTo(
                    tawwpYearCheckVO.getCrtime()) > 0) {
                tempTawwpYearCheckVO = tawwpYearCheckVO;
            }

            if (tawwpYearCheckVO.getState().equals("0")) {
                list.remove(tawwpYearCheckVO);
            }
        }

        // 获取流程管理对象
        TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance();
        // 获取下一步骤信息
        step = tawwpFlowManage
                .getFristYearFlowStep(tawwpYearPlanVO.getDeptId());
        if (step != null) {
            // 存在流程
            tawwpYearPlanVO.setStep(step);
            // added by lijia 2005-12-09
            TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
            tawwpYearPlanVO.setCheckUsers(tawwpUtilDAO.getUserNames(step
                    .getCheckUserIdStr()));
        }
        return tawwpYearPlanVO;
    }

    private TawwpYearPlanVO getYearPlan(String _id) throws TawwpException {

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

        TawwpYearPlan tawwpYearPlan = null;
        TawwpYearExecute tawwpYearExecute = null;
        TawwpYearCheck tawwpYearCheck = null;

        TawwpYearPlanVO tawwpYearPlanVO = null;
        TawwpYearExecuteVO tawwpYearExecuteVO = null;
        TawwpYearCheckVO tawwpYearCheckVO = null;

        Iterator iterator = null;

        try {
            tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_id); // 引导年度作业计划

            if (tawwpYearPlan != null) {
                // 不为空
                tawwpYearPlanVO = new TawwpYearPlanVO(true); // 初始化年度作业计划VO类
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlanVO,
                        tawwpYearPlan); // 转换数据

                // 整理数据信息
                tawwpYearPlanVO.setCruserName(tawwpUtilDAO
                        .getUserName(tawwpYearPlanVO.getCruser()));
                tawwpYearPlanVO.setSysTypeName(tawwpUtilDAO
                        .getSysTypeName(tawwpYearPlanVO.getSysTypeId()));
                tawwpYearPlanVO.setNetTypeName(tawwpUtilDAO
                        .getNetTypeName(tawwpYearPlanVO.getNetTypeId()));
                tawwpYearPlanVO.setDeptName(tawwpUtilDAO
                        .getDeptName(tawwpYearPlanVO.getDeptId()));
                tawwpYearPlanVO.setContent(StaticMethod
                        .null2String(tawwpYearPlanVO.getContent()));
                tawwpYearPlanVO.setRemark(StaticMethod
                        .null2String(tawwpYearPlanVO.getRemark()));
                tawwpYearPlanVO.setNetListName(StaticMethod
                        .null2String(tawwpNetDao.getNetNameList(StaticMethod
                                .null2String(tawwpYearPlanVO.getNetList()))));
                tawwpYearPlanVO.setCrtime(tawwpYearPlanVO.getCrtime()
                        .substring(0, 10));
                tawwpYearPlanVO
                        .setStateName(TawwpYearPlanVO.CONSTITUTESTATENAME[Integer
                                .parseInt(tawwpYearPlanVO.getState())]);
                tawwpYearPlanVO.setModelPlanId("");
                if (tawwpYearPlan.getTawwpModelPlan() != null) {
                    tawwpYearPlanVO.setModelPlanId(StaticMethod
                            .null2String(tawwpYearPlan.getTawwpModelPlan()
                                    .getId()));
                }

                iterator = tawwpYearPlan.getTawwpYearExecutes().iterator(); // 获取年度作业计划执行内容集合

                // 循环处理执行内容集合
                while (iterator.hasNext()) {
                    tawwpYearExecute = (TawwpYearExecute) iterator.next(); // 获取一个执行内容信息
                    tawwpYearExecuteVO = new TawwpYearExecuteVO();

                    MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearExecuteVO,
                            tawwpYearExecute); // 转换数据
                    // 整理数据信息
                    tawwpYearExecuteVO.setCycleName(tawwpUtilDAO
                            .getCycleName(tawwpYearExecuteVO.getCycle()));
                    tawwpYearExecuteVO.setFormName(tawwpUtilDAO
                            .getAddonsName(tawwpYearExecuteVO.getFormId()));
                    tawwpYearExecuteVO.setFormat(StaticMethod
                            .null2String(tawwpYearExecuteVO.getFormat()));
                    tawwpYearPlanVO.getYearExecuteList()
                            .add(tawwpYearExecuteVO); // 加入集合中
//					Collections.sort(tawwpYearPlanVO.getYearExecuteList());
                    Collections.sort(tawwpYearPlanVO.getYearExecuteList(), new Comparator() {
                        public int compare(Object o1, Object o2) {
                            TawwpYearExecuteVO p1 = (TawwpYearExecuteVO) o1;
                            TawwpYearExecuteVO p2 = (TawwpYearExecuteVO) o2;
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

                // 不为“新建”，则获取年度作业计划审批集合
                iterator = tawwpYearPlan.getTawwpYearChecks().iterator(); // 获取年度作业计划审批集合
                while (iterator.hasNext()) {
                    tawwpYearCheck = (TawwpYearCheck) iterator.next(); // 获取一个审批信息

                    // 如果审批状态不为“新建”，则加入审批列表中
                    if (!tawwpYearCheck.getState().equals("0")) {
                        tawwpYearCheckVO = new TawwpYearCheckVO();

                        MyBeanUtils.copyPropertiesFromDBToPage(
                                tawwpYearCheckVO, tawwpYearCheck); // 转换数据
                        // 整理数据信息
                        tawwpYearCheckVO.setCheckUserName(tawwpUtilDAO
                                .getUserName(tawwpYearCheckVO.getCheckUser()));
                        tawwpYearCheckVO.setCrtime(tawwpYearPlanVO.getCrtime()
                                .substring(0, 10));
                        tawwpYearCheckVO
                                .setStateName(TawwpYearCheckVO.STATENAME[Integer
                                        .parseInt(tawwpYearCheckVO.getState())]);

                        tawwpYearPlanVO.getYearCheckList()
                                .add(tawwpYearCheckVO); // 加入集合中
//						Collections.sort(tawwpYearPlanVO.getYearCheckList());
                        Collections.sort(tawwpYearPlanVO.getYearCheckList(), new Comparator() {
                            public int compare(Object o1, Object o2) {
                                TawwpYearCheckVO p1 = (TawwpYearCheckVO) o1;
                                TawwpYearCheckVO p2 = (TawwpYearCheckVO) o2;
                                int k = -1;
                                if (p1.getCrtime().compareTo(p2.getCrtime()) > 0) {
                                    k = 1;
                                } else if (p1.getCrtime().compareTo(p2.getCrtime()) == 0) {
                                    if (p1.getId().compareTo(p2.getId()) > 0) {
                                        k = 1;
                                    } else {
                                        k = -1;
                                    }
                                } else if (p1.getCrtime().compareTo(p2.getCrtime()) < 0) {
                                    k = -1;
                                }
                                return k;

                            }
                        });

                    }
                }
            } else {
                throw new TawwpException("年度作业计划不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划浏览出现异常");
        }
        return tawwpYearPlanVO;

    }

    /**
     * 业务逻辑：浏览年度作业计划执行内容信息
     *
     * @param _id
     *            String 作业计划模版执行内容标识
     * @throws TawwpException
     *             异常信息
     * @return TawwpModelExecuteVO 作业计划模版执行内容信息
     */
    public TawwpYearExecuteVO viewYearExecute(String _id) throws TawwpException {
        ;
        TawwpYearExecute tawwpYearExecute = null;
        TawwpYearExecuteVO tawwpYearExecuteVO = null;
        try {
            tawwpYearExecute = tawwpYearExecuteDao.loadYearExecute(_id);
            tawwpYearExecuteVO = new TawwpYearExecuteVO();
            MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearExecuteVO,
                    tawwpYearExecute);
            return tawwpYearExecuteVO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划执行内容信息查询出现异常");
        }

    }

    /**
     * 业务逻辑：浏览年度作业计划，审批中(VIEW-YEAR-PLAN-002)
     *
     * @param _id
     *            String 年度作业计划标识
     * @throws TawwpException
     *             异常信息
     * @return TawwpYearPlanVO 年度作业计划对象
     */
    public TawwpYearPlanVO viewYearPlanVOByCheck(String _id)
            throws TawwpException {

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

        TawwpYearPlan tawwpYearPlan = null;
        TawwpYearExecute tawwpYearExecute = null;
        TawwpYearCheck tawwpYearCheck = null;

        TawwpYearPlanVO tawwpYearPlanVO = null;
        TawwpYearExecuteVO tawwpYearExecuteVO = null;
        TawwpYearCheckVO tawwpYearCheckVO = null;
        TawwpYearCheckVO tempTawwpYearCheckVO = null;

        Iterator iterator = null;
        String flowSerial = null;
        String stepSerial = null;

        try {
            tawwpYearPlan = tawwpYearPlanDao.loadYearPlan(_id); // 引导年度作业计划

            if (tawwpYearPlan != null) {
                // 不为空
                tawwpYearPlanVO = new TawwpYearPlanVO(true); // 初始化年度作业计划VO类
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlanVO,
                        tawwpYearPlan); // 转换数据

                // 整理数据信息
                tawwpYearPlanVO.setCruserName(tawwpUtilDAO
                        .getUserName(tawwpYearPlanVO.getCruser()));
                tawwpYearPlanVO.setSysTypeName(tawwpUtilDAO
                        .getSysTypeName(tawwpYearPlanVO.getSysTypeId()));
                tawwpYearPlanVO.setNetTypeName(tawwpUtilDAO
                        .getNetTypeName(tawwpYearPlanVO.getNetTypeId()));
                tawwpYearPlanVO.setDeptName(tawwpUtilDAO
                        .getDeptName(tawwpYearPlanVO.getDeptId()));
                tawwpYearPlanVO.setNetListName(StaticMethod
                        .null2String(tawwpNetDao
                                .getNetNameList(tawwpYearPlanVO.getNetList())));
                tawwpYearPlanVO.setCrtime(tawwpYearPlanVO.getCrtime()
                        .substring(0, 10));
                tawwpYearPlanVO
                        .setStateName(TawwpYearPlanVO.CONSTITUTESTATENAME[Integer
                                .parseInt(tawwpYearPlanVO.getState())]);

                iterator = tawwpYearPlan.getTawwpYearExecutes().iterator(); // 获取年度作业计划执行内容集合
                // 循环处理执行内容集合
                while (iterator.hasNext()) {
                    tawwpYearExecute = (TawwpYearExecute) iterator.next(); // 获取一个执行内容信息
                    tawwpYearExecuteVO = new TawwpYearExecuteVO();

                    MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearExecuteVO,
                            tawwpYearExecute); // 转换数据
                    // 整理数据信息
                    tawwpYearExecuteVO.setCycleName(tawwpUtilDAO
                            .getCycleName(tawwpYearExecuteVO.getCycle()));
                    tawwpYearExecuteVO.setFormName(tawwpUtilDAO
                            .getAddonsName(tawwpYearExecuteVO.getFormId()));
                    tawwpYearPlanVO.getYearExecuteList()
                            .add(tawwpYearExecuteVO); // 加入集合中
                }

                iterator = tawwpYearPlan.getTawwpYearChecks().iterator(); // 获取年度作业计划审批集合

                // 循环处理审批集合
                while (iterator.hasNext()) {
                    tawwpYearCheck = (TawwpYearCheck) iterator.next(); // 获取一个审批信息

                    // 如果审批状态不为“新建”，则加入审批列表中
                    if (tawwpYearCheck != null) {
                        tawwpYearCheckVO = new TawwpYearCheckVO();

                        MyBeanUtils.copyPropertiesFromDBToPage(
                                tawwpYearCheckVO, tawwpYearCheck); // 转换数据
                        // 整理数据信息
                        tawwpYearCheckVO.setCheckUserName(tawwpUtilDAO
                                .getUserName(tawwpYearCheckVO.getCheckUser()));
                        tawwpYearCheckVO.setCrtime(tawwpYearPlanVO.getCrtime()
                                .substring(0, 10));
                        tawwpYearCheckVO
                                .setStateName(TawwpYearCheckVO.STATENAME[Integer
                                        .parseInt(tawwpYearCheckVO.getState())]);
                        if (!tawwpYearCheckVO.getState().equals("0")) {
                            tawwpYearPlanVO.getYearCheckList().add(
                                    tawwpYearCheckVO); // 加入集合中
                        } else {
                            tempTawwpYearCheckVO = tawwpYearCheckVO;
                        }
                    }
                }

                // 获取当前年度作业计划的审批流程信息
                TawwpFlowManage tawwpFlowManage = TawwpFlowManage.getInstance(); // 获取流程管理对象

                System.out.println("getStepSerial="
                        + tempTawwpYearCheckVO.getStepSerial());

                Step step = tawwpFlowManage.getYearFlowStep(
                        tempTawwpYearCheckVO.getFlowSerial(),
                        tempTawwpYearCheckVO.getStepSerial(), tawwpYearPlan
                                .getDeptId()); // 获取下一步骤信息

                if (step != null) {
                    // 存在流程
                    tawwpYearPlanVO.setStep(step);
                    // added by lijia 2005-12-09
                    tawwpYearPlanVO.setCheckUsers(tawwpUtilDAO
                            .getUserNames(step.getCheckUserIdStr()));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划不存在");
        }

        return tawwpYearPlanVO;
    }

    /**
     * 业务逻辑：年度作业计划列表(VIEW-YEAR-PLAN-001)
     *
     * @param _deptId
     *            String 部门标识
     * @param _yearFlag
     *            String 年度
     * @throws TawwpException
     *             异常信息
     * @return List 年度作业计划列表
     */
    public List listYearPlan(String _deptId, String _yearFlag)
            throws TawwpException {

        List list = null;
        List newlist = null;

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

        TawwpYearPlan tawwpYearPlan = null;
        TawwpYearPlanVO tawwpYearPlanVO = null;

        try {
            list = tawwpYearPlanDao.listYearPlanByYearDept(_deptId, _yearFlag); // 获取所有年度作业计划的集合
            newlist = new ArrayList();

            // 循环对每个年度作业计划进行分类处理
            for (int i = 0; i < list.size(); i++) {
                tawwpYearPlan = (TawwpYearPlan) list.get(i); // 获取一个年度作业计划
                tawwpYearPlanVO = new TawwpYearPlanVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlanVO,
                        tawwpYearPlan); // 将年度作业计划信息导入到作业计划模版显示类中
                tawwpYearPlanVO.setCruserName(tawwpUtilDAO
                        .getUserName(tawwpYearPlanVO.getCruser()));
                tawwpYearPlanVO.setSysTypeName(tawwpUtilDAO
                        .getSysTypeName(tawwpYearPlanVO.getSysTypeId()));
                tawwpYearPlanVO.setNetTypeName(tawwpUtilDAO
                        .getNetTypeName(tawwpYearPlanVO.getNetTypeId()));
                tawwpYearPlanVO
                        .setStateName(TawwpYearPlanVO.CONSTITUTESTATENAME[Integer
                                .parseInt(tawwpYearPlanVO.getState())]);
                if (null != tawwpYearPlanVO.getUnicomType()
                        && !tawwpYearPlanVO.getUnicomType().equals("")
                        && tawwpYearPlanVO.getState().equals("1")) {
                    tawwpYearPlanVO
                            .setStateName(TawwpYearPlanVO.REPORTSTATENAME[StaticMethod
                                    .null2int(tawwpYearPlanVO.getReportFlag())]);
                }
                tawwpYearPlanVO.setCrtime(tawwpYearPlanVO.getCrtime()
                        .substring(0, 10));
                newlist.add(tawwpYearPlanVO);
            }
            return newlist;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("年度作业计划列表信息查询出现异常");
        } finally {
            list = null;
        }
    }

    /**
     * 业务逻辑：年度审批作业计划列表(LIST-YEAR-CHECK-001)
     *
     * @param _userId
     *            String 用户
     * @throws TawwpException
     *             异常信息
     * @return Hashtable 审批年度作业计划列表
     */
    public List listYearCheck(String _userId) throws TawwpException {

        // 初始化数据,DAO对象

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

        // 集合对象
        List list = null;
        List newList = new ArrayList();

        // model对象
        TawwpYearPlan tawwpYearPlan = null;
        TawwpYearCheck tawwpYearCheck = null;

        // VO对象
        TawwpYearPlanVO tawwpYearPlanVO = null;
        // 匹配用户标识
        String tempUser = "," + _userId.trim() + ",";

        try {

            // 获取月度作业计划审批信息集合
            list = tawwpYearCheckDao.listYearCheck(_userId);

            for (int i = 0; i < list.size(); i++) {

                tawwpYearCheck = (TawwpYearCheck) list.get(i);

                tawwpYearCheck.setCheckUser(","
                        + tawwpYearCheck.getCheckUser().trim() + ",");

                if (tawwpYearCheck.getCheckUser().indexOf(tempUser) >= 0) {
                    // 获取月度作业计划对象
                    tawwpYearPlan = tawwpYearCheck.getTawwpYearPlan();

                    tawwpYearPlanVO = new TawwpYearPlanVO();

                    // 封装月度作业计划VO对象
                    MyBeanUtils.copyPropertiesFromDBToPage(tawwpYearPlanVO,
                            tawwpYearPlan);
                    tawwpYearPlanVO.setDeptName(tawwpUtilDAO
                            .getDeptName(tawwpYearPlanVO.getDeptId()));
                    tawwpYearPlanVO.setNetListName(StaticMethod
                            .null2String(tawwpNetDao
                                    .getNetNameList(tawwpYearPlanVO
                                            .getNetList())));
                    tawwpYearPlanVO.setYearCheckId(tawwpYearCheck.getId());
                    // 将月度作业计划VO对象添加进集合中
                    newList.add(tawwpYearPlanVO);
                    Collections.sort(newList, new Comparator() {
                        public int compare(Object o1, Object o2) {
                            TawwpYearPlanVO p1 = (TawwpYearPlanVO) o1;
                            TawwpYearPlanVO p2 = (TawwpYearPlanVO) o2;
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
            }

            // 返回月度作业计划VO对象集合
            return newList;
        } catch (Exception e) {
            // 捕获异常
            e.printStackTrace();
            throw new TawwpException("获取待审批年度作业计划列表出现异常");
        } finally {
            list = null;
        }

    }

    /**
     * 年度作业计划导出
     *
     * @param _yearPlanId
     *            String
     * @return String
     */
    public String exportYearToExcel(String _yearPlanId) {
        String exportExcelUrl = "";
        String exportExcel = "";

        String monthFlag = "";
        String cycle = "";

        int tempLength = 0;
        int x = 0;
        int y = 0;

        String wwwDir = TawwpStaticVariable.wwwDir; // web目录
        String newDir = TawwpUtil.getCurrentDateTime("yyyy_MM_dd");

        try {
            // 创建文件夹
            TawwpUtil.mkDir(wwwDir
                    + "workplan/tawwpfile/tempfiledownload/year/" + newDir);
            // 生成的Excel地址
            exportExcelUrl = "/workplan/tawwpfile/tempfiledownload/year/"
                    + newDir + "/" + TawwpUtil.getCurrentDateTime("hhmmss")
                    + _yearPlanId + ".xls";

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

            TawwpYearPlanVO tawwpYearPlanVO = null;
            TawwpYearExecuteVO tawwpYearExecuteVO = null;

            try {
                String[] CYCLENAME = TawwpStaticVariable.CYCLENAME;
                // 浏览月度作业计划
                tawwpYearPlanVO = viewYearPlanVO(_yearPlanId);

                // 如果作业计划模版信息不为空
                if (tawwpYearPlanVO != null) {

                    List yearExecuteVOList = tawwpYearPlanVO
                            .getYearExecuteList();

                    /**
                     * ********************配置常量单元格信息
                     * start************************
                     */
                    // 常量值
                    String[] valueOfLine = {
                            "" + tawwpYearPlanVO.getName() + "",
                            "制表日期：",
                            "" + TawwpUtil.getCurrentDateTime("yyyy-MM-dd")
                                    + "", "本月执行计划", "内　容", "周期"};
                    // 对应的行
                    int[] rowOfLine = {0, 1, 1, 1, 2, 2};
                    // 对应的列
                    int[] colOfLine = {0, 0, 1, 2, 0, 1};
                    // 合并的行列
                    int[][] xyOfLine = {{1, 15}, {1, 1}, {1, 1},
                            {1, 13}, {1, 1}, {1, 1}};
                    /** ********************配置常量单元格信息 end************************ */
                    int[] monthCode = {0, 0, 0, 0, 0, 3, 6, 12, 2, 4};

                    wb.setSheetName(0, "年度作业计划", HSSFWorkbook.ENCODING_UTF_16);
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
                    // 定义其他常量单元格
                    for (int i = 1; i < 13; i++) {
                        row = sheet.createRow((short) 2); // 建立新行
                        cell = row.createCell((short) (i + 1)); // 建立新cell
                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellValue(i + "月");
                        cell.setCellStyle(cellStyle);
                    }

                    y = 3;
                    // 循环处理执行内容
                    for (int i = 0; i < yearExecuteVOList.size(); i++) {

                        tawwpYearExecuteVO = (TawwpYearExecuteVO) (yearExecuteVOList
                                .get(i));
                        // 获得整形
                        monthFlag = tawwpYearExecuteVO.getMonthFlag();
                        cycle = tawwpYearExecuteVO.getCycle();
                        // 新的行
                        row = sheet.createRow((short) y);

                        cell = row.createCell((short) 0);
                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellValue(tawwpYearExecuteVO.getName());
                        cell.setCellStyle(cellStyle);

                        cell = row.createCell((short) 1);
                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellValue(CYCLENAME[Integer
                                .parseInt(tawwpYearExecuteVO.getCycle())]);
                        cell.setCellStyle(cellStyle);

                        if (!monthFlag.equals("")) {
                            int cycleInt = Integer.parseInt(cycle);
                            int monthFlagInt = Integer.parseInt(monthFlag);
                            for (int c = 0; c < (12 / monthCode[cycleInt]); c++) {
                                cell = row
                                        .createCell((short) (c
                                                * monthCode[cycleInt]
                                                + monthFlagInt + 1));
                                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                                cell.setCellValue("√");
                                cell.setCellStyle(cellStyle);
                            }

                        } else {
                            for (int c = 2; c < 14; c++) {
                                cell = row.createCell((short) c);
                                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                                cell.setCellValue("√");
                            }

                        }
                        y++;
                    }
                } else {
                    throw new TawwpException("年度作业计划查询出现异常");
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

    public void setTawwpModelExecuteDao(
            ITawwpModelExecuteDao tawwpModelExecuteDao) {
        this.tawwpModelExecuteDao = tawwpModelExecuteDao;
    }

    public void setTawwpModelGroupDao(ITawwpModelGroupDao tawwpModelGroupDao) {
        this.tawwpModelGroupDao = tawwpModelGroupDao;
    }

    public void setTawwpModelPlanDao(ITawwpModelPlanDao tawwpModelPlanDao) {
        this.tawwpModelPlanDao = tawwpModelPlanDao;
    }

    public void setTawwpNetDao(ITawwpNetDao tawwpNetDao) {
        this.tawwpNetDao = tawwpNetDao;
    }

    public void setTawwpYearCheckDao(ITawwpYearCheckDao tawwpYearCheckDao) {
        this.tawwpYearCheckDao = tawwpYearCheckDao;
    }

    public void setTawwpYearExecuteDao(ITawwpYearExecuteDao tawwpYearExecuteDao) {
        this.tawwpYearExecuteDao = tawwpYearExecuteDao;
    }

    public void setTawwpYearPlanDao(ITawwpYearPlanDao tawwpYearPlanDao) {
        this.tawwpYearPlanDao = tawwpYearPlanDao;
    }

    /**
     * 提交总部并在本地目录生成excel文件
     *
     * @param _yearPlanId
     *            String add by scropioD
     *
     *
     * public void yearReport(String _yearPlanId) {
     *
     * TawwpYearPlanVO tawwpYearPlanVO = null; TawwpYearExecuteVO
     * tawwpYearExecuteVO = null; TawwpNet tawwpNet = null;
     *
     *
     * String wwwDir = TawwpStaticVariable.wwwDir; //从静态变量读取目录 String yearPath =
     * wwwDir + TawwpStaticVariable.yearPath; //生成年度excel的目录 String modelPath =
     * wwwDir + TawwpStaticVariable.modelPath; //源文件的目录 String fileName = "";
     * String reportFlag = "1"; int x = 0; int y = 0; int xaxes = 0; int yaxes =
     * 0;
     *
     * try {
     *
     * tawwpYearPlanVO = viewYearPlanVO(_yearPlanId);
     *
     * if (tawwpYearPlanVO != null) { //生成年度execl文件名 String specialName =
     * tawwpYearPlanVO.getUnicomType(); String originExcelName = "YEAR-" +
     * specialName + ".xls"; //年度作业计划员模板的文件名 String year =
     * tawwpYearPlanVO.getYearFlag(); String netIdS[] =
     * tawwpYearPlanVO.getNetList().split(","); String NetSerial = "";
     *
     * for (int j = 0; j < netIdS.length; j++) {
     *
     * try { tawwpNet = tawwpNetDao.loadNet(netIdS[j]); NetSerial =
     * tawwpNet.getSerialNo(); } catch (Exception ex) { ex.printStackTrace();
     * throw new TawwpException("作业计划没有对应网元信息"); }
     *
     * //拼出excel名 fileName = "RS-" + StaticMethod.getNodeName("STRREGIONCODE") +
     * "-" + year + "0101-Y-" + specialName + "-001-" + NetSerial + ".xls";
     *
     * //读取源文件 jxl.Workbook rw = jxl.Workbook.getWorkbook(new
     * java.io.File(modelPath + originExcelName)); //创建要写出的文件
     * jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new java.io.
     * File(yearPath + fileName), rw); //获取sheet对象 jxl.write.WritableSheet ws =
     * wwb.getSheet(0); int[] monthCode = { 0, 0, 0, 0, 0, 3, 6, 12, 2, 4}; List
     * yearExecuteVOList = tawwpYearPlanVO.getYearExecuteList(); for (int i = 0;
     * i < yearExecuteVOList.size(); i++) {
     *
     * tawwpYearExecuteVO = (TawwpYearExecuteVO) (yearExecuteVOList.get(i));
     * String monthFlag = tawwpYearExecuteVO.getMonthFlag(); String cycle =
     * tawwpYearExecuteVO.getCycle(); xaxes =
     * StaticMethod.null2int(tawwpYearExecuteVO.getXlsX()); yaxes =
     * StaticMethod.null2int(tawwpYearExecuteVO.getXlsY()); if
     * (!monthFlag.equals("")) { int cycleInt = Integer.parseInt(cycle); int
     * monthFlagInt = Integer.parseInt(monthFlag); for (int c = 0; c < (12 /
     * monthCode[cycleInt]); c++) { y = c * monthCode[cycleInt] + monthFlagInt +
     * yaxes - 1; x = xaxes; jxl.write.WritableCell wc = ws.getWritableCell(y,
     * x); //获取可写入的单元格 if (wc.getType() == CellType.EMPTY) { jxl.write.Label
     * labelC = new jxl.write.Label(y, x, "√"); ws.addCell(labelC); } else if
     * (wc.getType() == CellType.LABEL) { jxl.write.Label wL = (jxl.write.Label)
     * wc; wL.setString("√"); } } } else { y = yaxes; x = xaxes; for (int c = 0;
     * c < 12; c++) { jxl.write.WritableCell wc = ws.getWritableCell(y + c, x);
     * //获取可写入的单元格 if (wc.getType() == CellType.EMPTY) { jxl.write.Label labelC =
     * new jxl.write.Label(y + c, x, "√"); ws.addCell(labelC); } else if
     * (wc.getType() == CellType.LABEL) { jxl.write.Label wL = (jxl.write.Label)
     * wc; wL.setString("√"); } } } } wwb.write(); rw.close(); wwb.close(); } }
     * else { throw new TawwpException("年度作业计划查询出现异常"); } //调用接口程序并改变提交总部状态
     * boolean submitstate = reportExecutePortReport(fileName); if
     * (!submitstate) { reportFlag = "2"; } TawwpYearPlan tawwpYearPlan = new
     * TawwpYearPlan(); = new (); tawwpYearPlan =
     * .loadYearPlan(tawwpYearPlanVO.getId());
     * tawwpYearPlan.setReportFlag(reportFlag); .update(tawwpYearPlan); } catch
     * (Exception e) { e.printStackTrace(); } }
     */

    /**
     * 调用总部作业计划的接口
     *
     * @param attachInfoListType
     *            AttachInfoListType
     * @throws Exception
     *             add by scropioD
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
     * TawwpStaticVariable.yearPath + fileName); AttachInfoType[]
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
     * 业务逻辑：通过审批(PASS-YEAR-PLAN-AUDITING-001)
     *
     * @param _yearCheckIdStr
     *            String 年度作业计划审批标识字符串
     * @param _content
     *            String 审批内容
     * @param _currentCheckUser
     *            String 审批人
     * @throws Exception
     *             异常信息
     */
    public void passYearCheck(String _yearCheckIdStr, String _content,
                              String _currentCheckUser) throws Exception {

        // 创建对象
        TawwpYearCheck tawwpYearCheck = null;
        TawwpYearPlan tawwpYearPlan = null;

        try {

            // 处理审批记录编号字符串
            String[] tempStr = _yearCheckIdStr.split(",");

            for (int i = 0; i < tempStr.length; i++) {
                tawwpYearCheck = tawwpYearCheckDao.loadYearCheck(tempStr[i]); // 引导年度作业计划审批信息
                tawwpYearPlan = tawwpYearCheck.getTawwpYearPlan(); // 获取年度作业计划信息

                // 如果年度作业计划审批信息存在
                if (tawwpYearCheck != null) {

                    tawwpYearCheck.setState("1"); // 将当前年度作业计划审批信息状态改为“通过”
                    tawwpYearCheck.setContent(_content); // 审批内容
                    tawwpYearCheck.setChecktime(TawwpUtil.getCurrentDateTime()); // 审批时间
                    tawwpYearCheck.setCheckUser(_currentCheckUser); // 审批人
                    tawwpYearCheckDao.updateYearCheck(tawwpYearCheck); // 更新年度审批

                    // 如果步骤对象为空，整体流程结束
                    tawwpYearPlan.setState("1");
                    tawwpYearPlanDao.updateYearPlan(tawwpYearPlan); // 修改年度作业计划状态“通过”

                    try {
                        TawwpUtil.sendSMS(tawwpYearPlan.getCruser(), "年度作业计划《"
                                + StaticMethod.null2String(tawwpYearPlan
                                .getName()) + "》通过", tawwpYearPlan
                                .getId());
                        TawwpUtil.closeSMS(tawwpYearPlan.getId());
                        TawwpUtil.sendMail(tawwpYearPlan.getCruser(), "年度作业计划《"
                                + StaticMethod.null2String(tawwpYearPlan
                                .getName()) + "》通过", tawwpYearPlan
                                .getId());
                        TawwpUtil.closeEmail(tawwpYearPlan.getId());
                    } catch (Exception e) {
                        System.out.print("消息发送异常" + e);
                    }

                } else {
                    throw new TawwpException("年度作业计划审批不存在");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("提交审批保存出现异常");
        }

    }


    public void crYearReport(String _yearPlanId) {

        TawwpYearPlanVO tawwpYearPlanVO = null;
        TawwpYearExecuteVO tawwpYearExecuteVO = null;
        TawwpNet tawwpNet = null;
        String wwwDir = TawwpStaticVariable.wwwDir; // 从静态变量读取目录
        String yearPath = wwwDir + TawwpStaticVariable.yearPath; // 生成年度excel的目录
        String modelPath = wwwDir + TawwpStaticVariable.modelPath; // 源文件的目录
        String fileName = "";
        int x = 0;
        int y = 0;
        int xaxes = 0;
        int yaxes = 0;
        String cycleStr = "";

        try {

            tawwpYearPlanVO = viewYearPlanVO(_yearPlanId);

            if (tawwpYearPlanVO != null) {
                // 生成年度execl文件名
                String specialName = tawwpYearPlanVO.getUnicomType();
                String originExcelName = "YEAR-" + specialName + ".xls"; // 年度作业计划员模板的文件名
                String year = tawwpYearPlanVO.getYearFlag();
                String netIdS[] = tawwpYearPlanVO.getNetList().split(",");
                String NetSerial = "";

                for (int j = 0; j < netIdS.length; j++) {

                    tawwpNet = this.loadNet(netIdS[j]);
                    if (tawwpNet != null) {

                        NetSerial = tawwpNet.getSerialNo();// 此属性是大纲里写给网元的
                        String province = WorkplanMgrLocator.getAttributes().getNewstrregioncode();


                        // 拼出excel名
                        fileName = "RS--"
                                + province
                                + "--" + year + "0101--Y--" + specialName
                                + "--001--" + NetSerial + ".xls";
                        fileName = fileName.replaceAll(" ", "");
                        // 读取源文件
                        jxl.Workbook rw = jxl.Workbook
                                .getWorkbook(new FileInputStream(modelPath
                                        + originExcelName));

                        // 创建要写出的文件
                        java.io.File dir = new java.io.File(yearPath + "/"
                                + specialName);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }

                        WorkbookSettings workbookSettings = new WorkbookSettings();

                        workbookSettings.setLocale(Locale.getDefault());

                        jxl.write.WritableWorkbook wwb = Workbook
                                .createWorkbook(new FileOutputStream(yearPath
                                                + "/" + specialName + "/" + fileName),
                                        rw, workbookSettings);

                        // 创建execl格式
                        WritableCellFormat format = new WritableCellFormat(
                                new WritableFont(WritableFont.ARIAL));
                        format.setBorder(jxl.format.Border.ALL,
                                jxl.format.BorderLineStyle.THICK);
                        // 获取sheet对象
                        jxl.write.WritableSheet ws = wwb.getSheet(0);
                        int[] monthCode = {0, 0, 0, 0, 0, 3, 6, 12, 2, 4};
                        List yearExecuteVOList = tawwpYearPlanVO
                                .getYearExecuteList();
                        for (int i = 0; i < yearExecuteVOList.size(); i++) {

                            tawwpYearExecuteVO = (TawwpYearExecuteVO) (yearExecuteVOList
                                    .get(i));
                            String monthFlag = tawwpYearExecuteVO
                                    .getMonthFlag();
                            String cycle = tawwpYearExecuteVO.getCycle();
                            xaxes = StaticMethod.null2int(tawwpYearExecuteVO
                                    .getXlsX());
                            yaxes = StaticMethod.null2int(tawwpYearExecuteVO
                                    .getXlsY());

                            cycleStr = TawwpUtil.getCycleStr(Integer.parseInt(cycle));

                            // WY专业自动填入周期
                            if (specialName.equals("WY")) {
                                jxl.write.WritableCell wc = ws.getWritableCell(
                                        yaxes - 2, xaxes); // 获取可写入的单元格
                                if (wc.getType() == CellType.EMPTY) {
                                    jxl.write.Label labelC = new jxl.write.Label(
                                            yaxes - 2, xaxes, cycleStr, format);
                                    ws.addCell(labelC);
                                } else if (wc.getType() == CellType.LABEL) {
                                    jxl.write.Label wL = (jxl.write.Label) wc;
                                    wL.setString(cycleStr);
                                }
                            }


                            //如果周期为 “按需”， 即cycle=0， 则不打勾
                            if (Integer.parseInt(cycle) == 0)
                                continue;

                            if (!monthFlag.equals("")) {
                                int cycleInt = Integer.parseInt(cycle);
                                int monthFlagInt = Integer.parseInt(monthFlag);
                                for (int c = 0; c < (12 / monthCode[cycleInt]); c++) {
                                    y = c * monthCode[cycleInt] + monthFlagInt
                                            + yaxes - 1;
                                    x = xaxes;
                                    jxl.write.WritableCell wc = ws
                                            .getWritableCell(y, x); // 获取可写入的单元格

                                    if (wc.getType() == CellType.EMPTY) {

                                        jxl.write.Label labelC = new jxl.write.Label(
                                                y, x, "√", format);
                                        ws.addCell(labelC);

                                    } else if (wc.getType() == CellType.LABEL) {
                                        jxl.write.Label wL = (jxl.write.Label) wc;
                                        wL.setString("√");
                                    }
                                }
                            } else {
                                y = yaxes;
                                x = xaxes;
                                for (int c = 0; c < 12; c++) {
                                    jxl.write.WritableCell wc = ws
                                            .getWritableCell(y + c, x); // 获取可写入的单元格
                                    if (wc.getType() == CellType.EMPTY) {
                                        jxl.write.Label labelC = new jxl.write.Label(
                                                y + c, x, "√", format);
                                        ws.addCell(labelC);
                                    } else if (wc.getType() == CellType.LABEL) {
                                        jxl.write.Label wL = (jxl.write.Label) wc;
                                        wL.setString("√");
                                    }
                                }
                            }
                        }
                        wwb.write();
                        rw.close();
                        wwb.close();
                    }
                }
            } else {
                throw new TawwpException("年度作业计划查询出现异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public TawwpNet loadNet(String _id) throws Exception {
        TawwpNet tawwpNet = new TawwpNet();
        if (_id.equals("0")) {
            return new TawwpNet("无网元", "", "", "", "", "", "", "", "0", "");
        } else if (_id.equals("")) {
            return new TawwpNet("无网元", "", "", "", "", "", "", "", "0", "");
        } else {
            try {
                tawwpNet = (TawwpNet) tawwpNetDao.loadNet(_id);
            } catch (Exception ex) {
                tawwpNet = null;
                ex.printStackTrace();
                System.out.println("net-" + _id + "-is not found!");
            }
            return tawwpNet;
        }

    }

    public void addYearExecute(String _command, String _cruser, String _cycle,
                               String id, String _format, String id2, String id3, String flag,
                               String _name, String botype, String executedeptlevel,
                               String appdesc, String typeId, String _remark, String planId,
                               String holiday, String weekend, String flag2, String _executer,
                               String duty, String room, String type, String executeDay)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
