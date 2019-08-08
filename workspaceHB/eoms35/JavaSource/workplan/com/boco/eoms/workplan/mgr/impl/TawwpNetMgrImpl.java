package com.boco.eoms.workplan.mgr.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.gzjhhead.interfaces.AttachInfoListType;
import com.boco.eoms.gzjhhead.interfaces.AttachInfoType;
import com.boco.eoms.gzjhhead.interfaces.FaultDetails;
import com.boco.eoms.gzjhhead.interfaces.NoteType;
import com.boco.eoms.gzjhhead.interfaces.ReportExecuteServiceLocator;
import com.boco.eoms.gzjhhead.interfaces.ReportInventoryBindingStub;
import com.boco.eoms.gzjhhead.interfaces._ReportInventoryRequest;
import com.boco.eoms.gzjhhead.interfaces._ReportInventoryResponse;
import com.boco.eoms.workplan.dao.ITawwpNetDao;
import com.boco.eoms.workplan.dao.ITawwpNewLogDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpNewLog;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpNetVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 2:49:26 PM
 * </p>
 *
 * @author eoms
 * @version 3.5.1
 */
public class TawwpNetMgrImpl implements ITawwpNetMgr {

    private ITawwpNetDao tawwpNetDao;
    private ITawwpNewLogDao tawwpNewLogDao;

    public void setTawwpNewLogDao(ITawwpNewLogDao tawwpNewLogDao) {
        this.tawwpNewLogDao = tawwpNewLogDao;
    }

    /**
     * @param tawwpNetDao the tawwpNetDao to set
     */
    public void setTawwpNetDao(ITawwpNetDao tawwpNetDao) {
        this.tawwpNetDao = tawwpNetDao;
    }

    /**
     * 业务逻辑：列表网元信息（LIST-NET-ELEMENT-001）
     *
     * @param pagePra int[] 分页信息
     * @return List 网元列表
     * @throws TawwpException 异常信息
     */
    public List listNet(int[] pagePra) throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

        TawwpNet tawwpNet = null;
        TawwpNetVO tawwpNetVO = null;
        List list = null;
        List newList = new ArrayList();

        try {
            list = tawwpNetDao.ListNet(pagePra);  // 查询分页网元信息

            // 循环处理网元信息
            for (int i = 0; i < list.size(); i++) {
                tawwpNet = (TawwpNet) list.get(i);
                tawwpNetVO = new TawwpNetVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpNetVO, tawwpNet);

                tawwpNetVO.setNetTypeName(tawwpUtilDAO
                        .getNetTypeName(tawwpNetVO.getNetTypeId()));
                tawwpNetVO.setSysTypeName(tawwpUtilDAO
                        .getSysTypeName(tawwpNetVO.getSysTypeId()));
                tawwpNetVO.setRoomName(tawwpUtilDAO.getRoomName(tawwpNetVO
                        .getRoomId()));
                tawwpNetVO.setDeptName(tawwpUtilDAO.getDeptNames(tawwpNetVO
                        .getDeptId()));
                newList.add(tawwpNetVO);
                Collections.sort(newList, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        TawwpNetVO p1 = (TawwpNetVO) o1;
                        TawwpNetVO p2 = (TawwpNetVO) o2;
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
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划网元查询出现异常");
        }
    }

    /**
     * 业务逻辑：增加网元（ADD-NET-ELEMENT-001）
     *
     * @param _name      String 网元名称
     * @param _deptId    String 所属部门
     * @param _roomId    String 所在机房
     * @param _sysTypeId String 系统类别
     * @param _netTypeId String 网元类型
     * @param _serialNo  String 序列号
     * @param _vendor    String 厂商
     * @throws TawwpException 异常信息
     */
    public void addNet(String _name, String _deptId, String _roomId,
                       String _sysTypeId, String _netTypeId, String _mynetTypeId, String _serialNo,
                       String _vendor) throws TawwpException {

        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpNet tawwpNet = new TawwpNet(_name, _deptId, _roomId, _sysTypeId,
                _netTypeId, _mynetTypeId, _serialNo, _vendor, "0", "0");

        try {
            tawwpNetDao.saveNet(tawwpNet);// 保存网元
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划网元保存出现异常");
        }
    }

    /**
     * 业务逻辑：增加网元通过网元接口读取数据（ADD-NET-ELEMENT-002）
     *
     * @param _name     String 网元名称
     * @param _serialNo String 序列号
     * @param _vendor   String 厂商
     * @throws TawwpException 异常信息
     */
    public void addNetByInterface(String _name, String _serialNo, String _vendor)
            throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpNet tawwpNet = new TawwpNet(_name, "1", "1", "1", "1", "", _serialNo,
                _vendor, "0", "0");
        try {
            tawwpNetDao.saveNet(tawwpNet); // 保存网元
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 业务逻辑：修改网元（EDIT-NET-ELEMENT-001）
     *
     * @param _id   String 网元标识
     * @param _name String 网元名称
     * @throws TawwpException 异常信息
     */
    public void editNet(String _id, String _name) throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpNet tawwpNet = null;

        try {
            tawwpNet = tawwpNetDao.loadNet(_id); // 获取作业计划网元对象
            if (!tawwpNet.getName().equals(_name)) {
                tawwpNet.setName(_name);
                tawwpNetDao.updateNet(tawwpNet); // 保存网元
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划网元修改出现异常");
        }
    }

    public void editNet(String _id, String _name, String _serialNo) throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpNet tawwpNet = null;

        try {
            tawwpNet = tawwpNetDao.loadNet(_id); // 鑾峰彇浣滀笟璁″垝缃戝厓瀵硅薄
            tawwpNet.setName(_name);
            tawwpNet.setSerialNo(_serialNo);
            tawwpNetDao.updateNet(tawwpNet); // 淇濆瓨缃戝厓

        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("浣滀笟璁″垝缃戝厓淇敼鍑虹幇寮傚父");
        }
    }

    /**
     * 业务逻辑：删除网元（DELETE-NET-ELEMENT-001）�
     *
     * @param _id String
     * @throws TawwpException
     */
    public void removeNet(String _id) throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpNet tawwpNet = null;

        try {
            tawwpNet = tawwpNetDao.loadNet(_id);// 获取作业计划网元对象
            if (true) {
                if (tawwpNet != null) {
                    tawwpNet.setDeleted("1");
                    tawwpNetDao.updateNet(tawwpNet);  // 逻辑删除作业计划模板
                }
            } else {
                if (tawwpNet != null) {
                    tawwpNetDao.deleteNet(tawwpNet); // 物理删除作业计划模板
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划网元删除出现异常");
        }
    }

    /**
     * 业务逻辑：浏览网元信息（BROWSE-NET-ELEMENT-001）
     *
     * @param _id String 网元信息标识
     * @return TawwpNetVO 网元信息
     * @throws TawwpException 异常信息
     */
    public TawwpNetVO viewNet(String _id) throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        TawwpNet tawwpNet = null;
        TawwpNetVO tawwpNetVO = null;

        try {
            tawwpNet = tawwpNetDao.loadNet(_id); // 获取作业计划网元对象
            tawwpNetVO = new TawwpNetVO();

            MyBeanUtils.copyPropertiesFromDBToPage(tawwpNetVO, tawwpNet); // 网元数据转换
            tawwpNetVO.setNetTypeName(tawwpUtilDAO.getNetTypeName(tawwpNetVO
                    .getNetTypeId()));
            tawwpNetVO.setSysTypeName(tawwpUtilDAO.getSysTypeName(tawwpNetVO
                    .getSysTypeId()));
            tawwpNetVO.setDeptName(tawwpUtilDAO.getDeptNames(tawwpNetVO
                    .getDeptId()));
            tawwpNetVO.setRoomName(tawwpUtilDAO.getRoomName(tawwpNetVO
                    .getRoomId()));
            tawwpNetVO.setMynettypeName(tawwpUtilDAO.getMyNetTypeName(tawwpNetVO.getMynettypeid()));
            return tawwpNetVO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("网元浏览出现异常");
        }
    }

    /**
     * 业务逻辑：查询网元信息（QUERY-NET-ELEMENT-001）
     *
     * @param _mapQuery Map 查询条件
     * @return List 网元列表
     * @throws TawwpException 异常信息
     */
    public List searchNet(Map _mapQuery) throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        List list = null;
        List newList = null;
        TawwpNet tawwpNet = null;
        TawwpNetVO tawwpNetVO = null;

        try {
            list = tawwpNetDao.searchNet(_mapQuery); // 查询符合条件的网元�
            newList = new ArrayList();

            for (int i = 0; i < list.size(); i++) {
                tawwpNet = (TawwpNet) list.get(i);
                tawwpNetVO = new TawwpNetVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpNetVO, tawwpNet); // 网元数据转换
                tawwpNetVO.setNetTypeName(tawwpUtilDAO
                        .getNetTypeName(tawwpNetVO.getNetTypeId()));
                tawwpNetVO.setSysTypeName(tawwpUtilDAO
                        .getSysTypeName(tawwpNetVO.getSysTypeId()));
                tawwpNetVO.setDeptName(tawwpUtilDAO.getDeptNames(tawwpNetVO
                        .getDeptId()));
                tawwpNetVO.setRoomName(tawwpUtilDAO.getRoomName(tawwpNetVO
                        .getRoomId()));
                tawwpNetVO.setMynettypeName(tawwpUtilDAO.getMyNetTypeName(tawwpNetVO.getMynettypeid()));
                newList.add(tawwpNetVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划网元查询出现异常");
        } finally {
            list = null;
        }

        return newList;
    }

    /**
     * 业务逻辑：查询网元信息（QUERY-NET-ELEMENT-001）
     *
     * @param _mapQuery Map 查询条件
     * @return List 网元列表
     * @throws TawwpException 异常信息
     */
    public List searchNet(Map _mapQuery, String flag) throws TawwpException {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        List list = null;
        List newList = null;
        TawwpNet tawwpNet = null;
        TawwpNetVO tawwpNetVO = null;

        try {
            list = tawwpNetDao.searchNet(_mapQuery, flag); // 查询符合条件的网元�
            newList = new ArrayList();

            for (int i = 0; i < list.size(); i++) {
                tawwpNet = (TawwpNet) list.get(i);
                tawwpNetVO = new TawwpNetVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpNetVO, tawwpNet); // 网元数据转换
                tawwpNetVO.setNetTypeName(tawwpUtilDAO
                        .getNetTypeName(tawwpNetVO.getNetTypeId()));
                tawwpNetVO.setSysTypeName(tawwpUtilDAO
                        .getSysTypeName(tawwpNetVO.getSysTypeId()));
                tawwpNetVO.setDeptName(tawwpUtilDAO.getDeptNames(tawwpNetVO
                        .getDeptId()));
                tawwpNetVO.setRoomName(tawwpUtilDAO.getRoomName(tawwpNetVO
                        .getRoomId()));
                tawwpNetVO.setMynettypeName(tawwpUtilDAO.getMyNetTypeName(tawwpNetVO.getMynettypeid()));
                newList.add(tawwpNetVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划网元查询出现异常");
        } finally {
            list = null;
        }

        return newList;
    }

    /**
     * 获取网元信息，按网元类型和部门。
     *
     * @param _netTypeId String 网元类型标识
     * @param _deptId    String 部门
     * @return List 网元列表
     * @throws TawwpException 异常信息
     */
    public List listNetByTypeDept(String _netTypeId, String _deptId)
            throws TawwpException {
        Map map = new HashMap();
        ArrayList list = new ArrayList();
        ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        if (_netTypeId != null && !_netTypeId.equals("")) {
            map.put("netTypeId", _netTypeId);
        }

        if (_deptId != null && !_deptId.equals("")) {

            // 取出父部门
            List tawSystemdept = mgr.getFdeptInfo(_deptId, "0");
            if (tawSystemdept != null && tawSystemdept.size() > 0) {
                for (int i = 0; i < tawSystemdept.size(); i++) {
                    TawSystemDept dept = (TawSystemDept) tawSystemdept.get(i);
                    list.add(dept.getDeptId());
                }
                map.put("deptId", list);
            }

        }
        map.put("deleted", "0");

        return this.searchNet(map, "");
    }

    /**
     * 获取网元信息，按网元类型和部门。
     *
     * @param _netTypeId String 网元类型标识
     * @param _deptId    String 部门
     * @return List 网元列表
     * @throws TawwpException 异常信息
     */
    public List listNetByTypeDept(String _netTypeId, String _deptId, String flag)
            throws TawwpException {
        Map map = new HashMap();
        if (flag.equals("")) {
            if (_netTypeId != null && !_netTypeId.equals("")) {
                map.put("netTypeId", _netTypeId);
            }

            if (_deptId != null && !_deptId.equals("")) {
                map.put("deptId", _deptId);
            }
        } else {
            map.put("netTypeId", _netTypeId);
            List deptList = new ArrayList();
            deptList.add(_deptId);
            map.put("deptId", deptList);
        }
        map.put("deleted", "0");

        return this.searchNet(map);
    }

    public List searchNetNoExecute(String _startTime) throws TawwpException {
        TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        List list = null;
        List newList = null;
        TawwpMonthPlanVO tawwpMonthPlanVO = null;
        try {
            list = tawwpNetDao.ListNetNoExecute(_startTime); //查询符合条件的网元�
            newList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                tawwpMonthPlan = (TawwpMonthPlan) list.get(i);
                tawwpMonthPlanVO = new TawwpMonthPlanVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO, tawwpMonthPlan); //网元数据转换
                if (tawwpMonthPlan.getTawwpNet() != null) {
                    tawwpMonthPlanVO.setNetName(StaticMethod.null2String(
                            tawwpMonthPlan.
                                    getTawwpNet().getName()));
                } else {
                    tawwpMonthPlanVO.setNetName("无网元");
                }
                tawwpMonthPlanVO.setDeptName(tawwpUtilDAO.getDeptName(tawwpMonthPlanVO.
                        getDeptId()));
                tawwpMonthPlanVO.setName(tawwpMonthPlanVO.getName());
                newList.add(tawwpMonthPlanVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划执行情况统计查询出现异常");
        } finally {
            list = null;
        }

        return newList;
    }

    public String netXml(Map _mapQuery) {

        String filePath = null;
        String nettypeid = null;
        List serialList = null;
        List netList = null;
        DataOutputStream output = null;

        Map map = new HashMap();
        StringBuffer str = new StringBuffer();
        TawwpUtilDAO utilDAO = new TawwpUtilDAO();

        try {
            netList = tawwpNetDao.listNetType(_mapQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }// 获取网元类型
        String dateSign = StaticMethod.getCurrentDateTime("yyyyMMdd");
        filePath = TawwpStaticVariable.wwwDir + TawwpStaticVariable.netPath;
        String netType = StaticMethod.nullObject2String(_mapQuery
                .get("myNetTypeId"));
        if (netType.equals("")) {
            netType = "AL";
        }
        String numDesign = StaticMethod.getCurrentDateTime("ss");
        String ReportType = (String) _mapQuery.get("netReportType");

        str.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        str.append("\n");
        str.append("<InventoryReport xmlns=\"http://www.chinaunicom.com/worktaskschedule/inventory\""
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"xxx\\Inventory.xsd\" ReportType=\""
                + ReportType + "\">");
        str.append("\n");
        for (int i = 0; i < netList.size(); i++) {
            map = (HashMap) netList.get(i);
            nettypeid = (String) map.get("mynettypeid");
            String nettypename = null;
            try {
                nettypename = utilDAO.getMyNetTypeName(nettypeid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (netList.size() == 1) {
                netType = nettypename;
            }
            serialList = (List) map.get("list");
            if (serialList.size() != 0) {
                str.append("<NEInfo type=\"" + nettypename + "\" total=\""
                        + serialList.size() + "\">");
                str.append("\n");
                for (int j = 0; j < serialList.size(); j++) {
                    String serialno = (String) serialList.get(j);
                    str.append("<NENumber>" + serialno + "</NENumber>");
                    str.append("\n");
                }
                str.append("</NEInfo>");
                str.append("\n");
            }
        }
        str.append("</InventoryReport>");

        String fileName = WorkplanMgrLocator.getAttributes().getNewstrregioncode() + "--"
                + dateSign + "--" + netType + "--0" + numDesign + ".xml";

        try {
            output = new DataOutputStream(new FileOutputStream(new File(filePath
                    + fileName)));
            output.writeBytes(new String(str.toString().getBytes("GB2312"),
                    "ISO8859-1"));
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public String newNetXml(Map _mapQuery) {

        String filePath = null;
        String nettypeid = null;
        List serialList = null;
        List netList = null;
        DataOutputStream output = null;

        Map map = new HashMap();
        StringBuffer str = new StringBuffer();
        TawwpUtilDAO utilDAO = new TawwpUtilDAO();

        try {
            netList = tawwpNetDao.listNetType(_mapQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } // 获取网元类型
        String dateSign = StaticMethod.getCurrentDateTime("yyyyMMdd");
        filePath = TawwpStaticVariable.wwwDir + TawwpStaticVariable.netPath;
        String netType = StaticMethod.nullObject2String(_mapQuery
                .get("myNetTypeId"));
        if (netType.equals("")) {
            netType = "ALL";
        }
        String numDesign = StaticMethod.getCurrentDateTime("ss");
        String ReportType = (String) _mapQuery.get("netReportType");

        str.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        str.append("\n");
        str.append("<InventoryReport xmlns=\"http://www.chinaunicom.com/worktaskschedule/inventory\""
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"xxx\\Inventory.xsd\" ReportType=\""
                + ReportType + "\">");
        str.append("\n");
        for (int i = 0; i < netList.size(); i++) {
            map = (HashMap) netList.get(i);
            nettypeid = (String) map.get("mynettypeid");
            String nettypename = null;
            try {
                nettypename = utilDAO.getMyNetTypeName(nettypeid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String netTypeId = (String) map.get("netTypeId");
            String sysType = (String) map.get("sysType");
            TawSystemDictTypeDaoHibernate dictdao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemDictTypeDao");
            TawSystemDictType dict = null;
            try {
                dict = dictdao.getDictType(StaticMethod.null2int(netTypeId), StaticMethod.null2int(sysType));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (netList.size() == 1) {
                netType = dict.getDictCode();
            }
            serialList = (List) map.get("list");
            if (serialList.size() != 0) {
                str.append("<NEInfo type=\"" + nettypename + "\"   profession=\"" + dict.getDictCode() + "\" total=\""
                        + serialList.size() + "\">");
                str.append("\n");
                for (int j = 0; j < serialList.size(); j++) {
                    String serialno = (String) serialList.get(j);
                    str.append("<NENumber>" + serialno + "</NENumber>");
                    str.append("\n");
                }
                str.append("</NEInfo>");
                str.append("\n");
            }
        }
        str.append("</InventoryReport>");
        String fileName = WorkplanMgrLocator.getAttributes().getNewstrregioncode() + "--"
                + dateSign + "--" + netType + "--" + numDesign + ".xml";
        try {
            output = new DataOutputStream(new FileOutputStream(new File(filePath
                    + fileName)));
            output.writeBytes(new String(str.toString().getBytes("GB2312"),
                    "ISO8859-1"));
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;

    }

    /**
     * 生成上报网元信息
     *
     * @param fileName String　文件名
     * @throws Exception
     * @throws Exception
     */
    public void netReport(String _fileName) throws Exception {
        ReportInventoryPortReportInventory(_fileName);
    }

    public void newNetReport(String _fileName) throws Exception {
        newReportInventoryPortReportInventory(_fileName);
    }

    /**
     * 调用接口
     *
     * @param fileName String　文件名
     * @throws Exception
     */
    public boolean ReportInventoryPortReportInventory(String fileName)
            throws Exception {

        TawwpNewLog tawwpNewLog = new TawwpNewLog();

        Date dateNow = new Date();

        tawwpNewLog.setCreateTime(dateNow);

        tawwpNewLog.setLogType("网元作业计划总部接口上报");

        StringBuffer fileLog = new StringBuffer("");

        String submitStr = null;

        boolean flag = true;
        ReportInventoryBindingStub binding;
        try {
            binding = (ReportInventoryBindingStub) new ReportExecuteServiceLocator()
                    .getReportInventoryPort();

        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError(
                    "JAX-RPC ServiceException caught: " + jre);
        }

        binding.setTimeout(60000);

        // 数据封装
        AttachInfoType attachInfoType = new AttachInfoType();
        AttachInfoListType attachInfoListType = new AttachInfoListType();
        File file = new File(TawwpStaticVariable.wwwDir
                + TawwpStaticVariable.netPath + fileName);
        attachInfoType.setAttachLength((int) (file.length()));
        attachInfoType.setAttachName(fileName);
        attachInfoType.setAttachURL(TawwpStaticVariable.serverIp
                + TawwpStaticVariable.netPath + fileName);
        AttachInfoType[] attachInfoTypeArray = new AttachInfoType[1];
        attachInfoTypeArray[0] = attachInfoType;
        attachInfoListType.setAttachInfo(attachInfoTypeArray);

        for (int w = 0; w < attachInfoTypeArray.length; w++) {

            fileLog.append("第" + w + "记录:");
            fileLog.append("attachInfo.AttachName:"
                    + attachInfoTypeArray[w].getAttachName());

            fileLog.append("attachInfo.AttachLength:"
                    + attachInfoTypeArray[w].getAttachLength());
            fileLog.append("attachInfo.AttachURL:"
                    + attachInfoTypeArray[w].getAttachURL());

        }
        try {
            _ReportInventoryResponse value = null;
            _ReportInventoryRequest ReportInventoryRequest = new com.boco.eoms.gzjhhead.interfaces._ReportInventoryRequest();
            ReportInventoryRequest.setAttachInfoList(attachInfoListType);
            ReportInventoryRequest.setAttNum(1);
            NoteType noteType = new NoteType();
            noteType.setValue("");
            ReportInventoryRequest.setNoteReportInventory(noteType);
            ReportInventoryRequest.setCodeA(StaticMethod
                    .getNodeName("STRREGIONCODE"));
            ReportInventoryRequest.setCodeB("ZB");
            value = binding.reportInventory(ReportInventoryRequest);
            if (value.getResultReportInventory() == null) {
                tawwpNewLog.setResultState("上报成功");
                System.out.println("上报成功");
            } else {
                tawwpNewLog.setResultState(value.getResultReportInventory());
                System.out.println(value.getResultReportInventory());
            }
        } catch (FaultDetails e1) {
            throw new junit.framework.AssertionFailedError(
                    "ReportInventoryFault Exception caught: " + e1);
        }
        tawwpNewLog.setMessage(fileLog.toString());
        tawwpNewLogDao.saveLog(tawwpNewLog);
        return flag;
    }

    /**
     * 调用天元接口
     *
     * @param fileName String　文件名
     * @throws Exception
     */
    public boolean newReportInventoryPortReportInventory(String fileName)
            throws Exception {

        TawwpNewLog tawwpNewLog = new TawwpNewLog();

        Date dateNow = new Date();

        tawwpNewLog.setCreateTime(dateNow);

        tawwpNewLog.setLogType("网元作业计划总部接口上报");

        StringBuffer fileLog = new StringBuffer("");

        String submitStr = null;

        boolean flag = true;
        com.boco.eoms.gzjhead.interfaces.ReportInventoryBindingStub binding;
        try {
            binding = (com.boco.eoms.gzjhead.interfaces.ReportInventoryBindingStub) new com.boco.eoms.gzjhead.interfaces.ReportExecuteServiceLocator()
                    .getReportInventoryPort();

        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError(
                    "JAX-RPC ServiceException caught: " + jre);
        }

        binding.setTimeout(60000);

        // 数据封装
        com.boco.eoms.gzjhead.interfaces.AttachInfoType attachInfoType = new com.boco.eoms.gzjhead.interfaces.AttachInfoType();
        com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoListType = new com.boco.eoms.gzjhead.interfaces.AttachInfoListType();
        File file = new File(TawwpStaticVariable.wwwDir
                + TawwpStaticVariable.netPath + fileName);

        System.out.println(TawwpStaticVariable.wwwDir
                + TawwpStaticVariable.netPath + fileName);
        attachInfoType.setAttachLength((int) (file.length()));
        System.out.println(file.length());
        attachInfoType.setAttachName(fileName);
        attachInfoType.setAttachURL(TawwpStaticVariable.serverIp
                + TawwpStaticVariable.netPath + fileName);
        System.out.println(TawwpStaticVariable.serverIp
                + TawwpStaticVariable.netPath + fileName);
        com.boco.eoms.gzjhead.interfaces.AttachInfoType[] attachInfoTypeArray = new com.boco.eoms.gzjhead.interfaces.AttachInfoType[1];
        attachInfoTypeArray[0] = attachInfoType;
        attachInfoListType.setAttachInfo(attachInfoTypeArray);

        for (int w = 0; w < attachInfoTypeArray.length; w++) {

            fileLog.append("第" + w + "记录:");
            fileLog.append("attachInfo.AttachName:"
                    + attachInfoTypeArray[w].getAttachName());

            fileLog.append("attachInfo.AttachLength:"
                    + attachInfoTypeArray[w].getAttachLength());
            fileLog.append("attachInfo.AttachURL:"
                    + attachInfoTypeArray[w].getAttachURL());

        }
        try {
            com.boco.eoms.gzjhead.interfaces._ReportInventoryResponse value = null;
            com.boco.eoms.gzjhead.interfaces._ReportInventoryRequest ReportInventoryRequest = new com.boco.eoms.gzjhead.interfaces._ReportInventoryRequest();
            ReportInventoryRequest.setAttachInfoList(attachInfoListType);
            ReportInventoryRequest.setAttNum(1);
            com.boco.eoms.gzjhead.interfaces.NoteType noteType = new com.boco.eoms.gzjhead.interfaces.NoteType();
            noteType.setValue("");
            ReportInventoryRequest.setNoteReportInventory(noteType);
            ReportInventoryRequest.setCodeA(StaticMethod
                    .getNodeName("NEWSTRREGIONCODE"));
            ReportInventoryRequest.setCodeB("ZB");
            value = binding.reportInventory(ReportInventoryRequest);
            if (value.getResultReportInventory() == null) {
                tawwpNewLog.setResultState("上报成功");
                System.out.println("上报成功");
            } else {
                tawwpNewLog.setResultState(value.getResultReportInventory());
                System.out.println(value.getResultReportInventory());
            }
        } catch (com.boco.eoms.gzjhead.interfaces.FaultDetails e1) {
            throw new junit.framework.AssertionFailedError(
                    "ReportInventoryFault Exception caught: " + e1);
        }
        tawwpNewLog.setMessage(fileLog.toString());
        tawwpNewLogDao.saveLog(tawwpNewLog);
        return flag;
    }

    /**
     * 业务逻辑：网元上报成功
     *
     * @param type String　上报类型
     * @throws Exception
     */
    public void netReportOK(String type) throws Exception {

        List list = new ArrayList();
        List newList = new ArrayList();
        Map map = new HashMap();

        if (type.equals("Yearly")) {


            list = tawwpNetDao.ListNetByYearReport();

            for (int i = 0; i < list.size(); i++) {
                TawwpNet tawwpNet = (TawwpNet) list.get(i);
                tawwpNet.setReportFlag("1");
                tawwpNetDao.updateNet(tawwpNet);
            }


        } else if (type.equals("Add_NE")) {

            map.put("reportFlag", "0");
            map.put("deleted", "0");

            list = tawwpNetDao.ListNetNewUpdate();

            for (int i = 0; i < list.size(); i++) {
                TawwpNet tawwpNet = (TawwpNet) list.get(i);
                tawwpNet.setReportFlag("1");
                tawwpNetDao.updateNet(tawwpNet);
            }

        } else if (type.equals("Remove_NE")) {

            map.put("reportFlag", "1");
            map.put("deleted", "1");

            list = searchNet(map);

            for (int i = 0; i < list.size(); i++) {
                TawwpNetVO tawwpNetVO = (TawwpNetVO) list.get(i);
                newList = tawwpNetDao.searchListNet(tawwpNetVO.getId());
            }
            for (int i = 0; i < newList.size(); i++) {
                TawwpNet tawwpNet = (TawwpNet) newList.get(i);
                tawwpNet.setReportFlag("-1");
                tawwpNetDao.updateNet(tawwpNet);
            }
        }
    }


    public List searchNetPage(Map _mapQuery, int[] pagePra, String sql) throws TawwpException {
        // TODO Auto-generated method stub
        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        List list = null;
        List newList = null;
        TawwpNet tawwpNet = null;
        TawwpNetVO tawwpNetVO = null;


        try {
            list = tawwpNetDao.searchNetpage(_mapQuery, pagePra, sql); //查询符合条件的网元
            newList = new ArrayList();

            for (int i = 0; i < list.size(); i++) {
                tawwpNet = (TawwpNet) list.get(i);
                tawwpNetVO = new TawwpNetVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpNetVO, tawwpNet); //网元数据转换
                tawwpNetVO.setNetTypeName(tawwpUtilDAO
                        .getNetTypeName(tawwpNetVO.getNetTypeId()));
                tawwpNetVO.setSysTypeName(tawwpUtilDAO
                        .getSysTypeName(tawwpNetVO.getSysTypeId()));
                tawwpNetVO.setDeptName(tawwpUtilDAO.getDeptNames(tawwpNetVO
                        .getDeptId()));
                tawwpNetVO.setRoomName(tawwpUtilDAO.getRoomName(tawwpNetVO
                        .getRoomId()));
                tawwpNetVO.setMynettypeName(tawwpUtilDAO.getMyNetTypeName(tawwpNetVO.getMynettypeid()));
                newList.add(tawwpNetVO);
                Collections.sort(newList, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        TawwpNetVO p1 = (TawwpNetVO) o1;
                        TawwpNetVO p2 = (TawwpNetVO) o2;
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划执行情况统计查询出现异常");
        } finally {
            list = null;
        }

        return newList;
    }

    public String loadNetBySerial(String _netSerial) {
        return tawwpNetDao.loadNetBySerial(_netSerial);
    }

    public List searchWorkplanNet() {

        List list = new ArrayList();
        list = tawwpNetDao.searchWorkplanNet();
        return list;

    }
}
