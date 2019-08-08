package com.boco.eoms.workplan.dao;

/**
 * <p>Title: 公用方法类</p>
 * <p>Description: 提供作业计划模块的公用方法</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.workplan.model.TawwpAddonsTable;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpAddonsTableVO;

public class TawwpUtilDAO {

    // 不带参数的构造方法,默认指定连接池
    public TawwpUtilDAO() {
    }

    /**
     * 使用cache缓存获取部门名称
     *
     * @param _deptId 部门编号
     * @return 部门名称
     * @throws DictDAOException
     */
    public String getDeptName(String _deptId) throws DictDAOException {
        // 初始化数据
        ITawSystemDeptManager tawDeptBO = (ITawSystemDeptManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDeptManager");
        TawwpUtil tawwpUtil = new TawwpUtil();
        // 从缓存里取deptName
        String deptName = tawwpUtil.id2name("dept", _deptId);
        // 如果缓存里面没有，则在数据库里面取
        if (deptName == null || "".equals(deptName)) {
            deptName = tawDeptBO.id2Name(_deptId);
        }
        // 返回部门名称
        return deptName;
    }

    /**
     * 使用cache缓存获取部门名称
     *
     * @param _deptIdStr 部门编号字符串(以“,”分隔)
     * @return 部门名称字符串
     */
    public String getDeptNames(String _deptIdStr) {
        // 初始化数据
        String deptNameStr = "";
        String[] tempStr = _deptIdStr.split(",");
        // 获得部门名称字符串
        for (int i = 0; i < tempStr.length; i++) {
            try {
                deptNameStr += this.getDeptName(tempStr[i]) + ",";
            } catch (DictDAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // 获得部门名称
        }
        if (!"".equals(deptNameStr)) {
            // 如果结果不为空则去掉字符串末尾
            deptNameStr = deptNameStr.substring(0, deptNameStr.length() - 1);
        }
        // 返回部门名称字符串
        return deptNameStr;
    }

    /**
     * 使用cache缓存获取用户姓名
     *
     * @param _userId 用户登录名
     * @return 用户姓名
     */
    public String getUserName(String _userId) {
        // 初始化数据

        ITawSystemUserManager tawRmUserBO = (ITawSystemUserManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserManager");
        TawwpUtil tawwpUtil = new TawwpUtil();
        String userName = "";
//		 如果缓存里面没有，则在数据库里面取
        if (_userId == null || "".equals(_userId)) {
            //try {
            userName = "";
            //} catch (DictDAOException e) {
            //e.printStackTrace();
            //}
        }
        // 从缓存里取userName
        else {
            userName = tawwpUtil.id2name("user", _userId.trim());
        }

        // 返回用户姓名
        return userName;
    }

    /**
     * 根据用户ID获取用户NAME
     *
     * @param _userId 用户登录名
     * @return 用户姓名
     */
    public HashMap getUserIdToName() {
        ITawSystemUserManager tawRmUserBO = (ITawSystemUserManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserManager");

        List list = null;
        HashMap hashmap = new HashMap();
        TawSystemUser labelValueBean = null;
        try {
            list = tawRmUserBO.getUsersByName("");

            // 循环处理人员信息
            for (int i = 0; i < list.size(); i++) {
                labelValueBean = (TawSystemUser) list.get(i);
                hashmap.put(labelValueBean.getUserid(), labelValueBean.getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            list = null;
        }
        return hashmap;
    }

    /**
     * 根据部门ID获取部门NAME
     *
     * @param _deptId 用户登录名
     * @return 用户姓名
     */
    public HashMap getDeptIdToName() {
        ITawSystemDeptManager tawDeptMgr = (ITawSystemDeptManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDeptManagerFlush");

        List list = null;
        HashMap hashmap = new HashMap();
        TawSystemDept labelValueBean = null;
        try {
            list = tawDeptMgr.getALLdept("-1", "0");

            // 循环处理人员信息
            for (int i = 0; i < list.size(); i++) {
                labelValueBean = (TawSystemDept) list.get(i);
                hashmap.put(labelValueBean.getDeptId(), labelValueBean.getDeptName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            list = null;
        }
        return hashmap;
    }

    /**
     * 使用cache缓存获取用户姓名
     *
     * @param _userIdStr 用户登录名(以“,”分隔)
     * @return 用户姓名字符串
     */
    public String getUserNames(String _userIdStr) {
        // 初始化数据
        String userNameStr = "";
        String[] tempStr = _userIdStr.split(",");
        // 获得部门名称字符串
        for (int i = 0; i < tempStr.length; i++) {
            if (!tempStr[i].equals("null")) {
                userNameStr += this.getUserName(tempStr[i]) + ","; // 获得用户姓名
            }
        }
        if (!"".equals(userNameStr)) {
            // 如果结果不为空则去掉字符串末尾
            userNameStr = userNameStr.substring(0, userNameStr.length() - 1);
        }
        // 返回用户姓名字符串
        return userNameStr;
    }

    /**
     * 获取用户的对象列表
     *
     * @param _userIdStr String 用户编号字符串
     * @return List 用户对象列表
     */
    public List getUser(String _userIdStr) {

        ITawSystemUserManager tawRmUserBO = (ITawSystemUserManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserManager");

        List list = new ArrayList();
        String[] userId = _userIdStr.split(",");

        for (int i = 0; i < userId.length; i++) {
            Object object = tawRmUserBO.getUserByuserid(_userIdStr);
            if (object != null) {
                list.add(object);
            }
        }

        return list;
    }

    /**
     * 获取部门中的所有人员信息
     *
     * @param _deptId String 部门编号
     * @return Hashtable 人员信息 key 人员标识 value 人员名称
     */
    public Hashtable getUserByDept(String _deptId) {

        ITawSystemUserManager tawRmUserBO = (ITawSystemUserManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserManager");

        List list = null;
        Hashtable hashtable = null;
        // LabelValueBean labelValueBean = null;
        TawSystemUser labelValueBean = null;
        try {
            list = tawRmUserBO.getUserBydeptids(_deptId);
            // tawRmUserDAO.getRmUserSelectBylabel(Integer.parseInt(_deptId),
            // 0); //获取部门中的人员集合

            hashtable = new Hashtable();

            // 循环处理人员信息
            for (int i = 0; i < list.size(); i++) {
                // labelValueBean = (LabelValueBean) list.get(i);
                labelValueBean = (TawSystemUser) list.get(i);
                // hashtable.put(labelValueBean.getValue(),
                // labelValueBean.getLabel());
                hashtable.put(labelValueBean.getUserid(), labelValueBean
                        .getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            list = null;
        }
        return hashtable;
    }

    /**
     * 获取周期名称
     *
     * @param _cycle 周期编号
     * @return 周期名称
     */
    public static String getCycleName(int _cycle) {
        // 初始化数据
        String cycleName = "";
        // 如果周期编号在周期范围以内
        if (_cycle < TawwpStaticVariable.CYCLENAME.length && _cycle >= 0) {
            // 取出周期显示名称
            cycleName = TawwpStaticVariable.CYCLENAME[_cycle];
        }
        // 返回周期名称
        return cycleName;
    }

    /**
     * 获取周期名称
     *
     * @param _cycle 周期编号
     * @return 周期名称
     */
    public static String getCycleName(String _cycle) {
        return getCycleName(Integer.parseInt(_cycle));
    }

    /**
     * 获取附加表名称
     *
     * @param _addonsId String 附加表标识
     * @return String 附加表名称
     * @throws Exception 查询异常
     */
    public static String getAddonsName(String _addonsId) throws Exception {
        // 初始化数据
        String addonsName = "";
        if (_addonsId != null && !_addonsId.equals("0")) {
            TawwpAddonsTableVO tawwpAddonsTableVO = null;
            Hashtable hashtable = TawwpStaticVariable.ADDONS_INF;

            tawwpAddonsTableVO = (TawwpAddonsTableVO) hashtable.get(_addonsId);
            if (tawwpAddonsTableVO != null) {
                addonsName = tawwpAddonsTableVO.getName();
            }

            if (addonsName == null) {

                // TawwpAddonsTableDAO tawwpAddonsTableDAO = new
                // TawwpAddonsTableDAO();

                ITawwpAddonsTableDao tawwAddonsTableDao = (ITawwpAddonsTableDao) ApplicationContextHolder
                        .getInstance().getBean("tawwpAddonsTableDao");
                TawwpAddonsTable tawwpAddonsTable = null;
                try {
                    tawwpAddonsTable = tawwAddonsTableDao
                            .loadAddonsTable(String.valueOf(_addonsId));
                    if (tawwpAddonsTable != null) {
                        addonsName = tawwpAddonsTable.getName();
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // 捕获异常
                    throw new TawwpException("获取系统类型名称出现异常");
                }
            }
        }
        return addonsName;
    }

    /**
     * 获取系统类型名称
     *
     * @param _sysTypeId 系统类型编号
     * @return 系统类型名称
     * @throws Exception 异常信息
     */
    public String getSysTypeName(String _sysTypeId) throws Exception {
        // 初始化数据
        String sysTypeName = null;
        TawwpUtil tawwpUtil = new TawwpUtil();
        sysTypeName = tawwpUtil.id2name("dict", _sysTypeId);
        ; // 从缓存中获取系统类别信息

        if (sysTypeName == null) {

            // 初始化数据
            ITawSystemDictTypeManager tawWsDictTypeDAO = (ITawSystemDictTypeManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemDictTypeManager");
            try {
                // 获取系统类型名称
                TawSystemDictType dictType = new TawSystemDictType();
                dictType = tawWsDictTypeDAO.getDictTypeByDictid(_sysTypeId);
                if (dictType != null) {
                    sysTypeName = dictType.getDictName();
                } else {
                    sysTypeName = "";
                }
            } catch (Exception e) {
                // 捕获异常
                e.printStackTrace();
                throw new TawwpException("获取系统类型名称出现异常");
            }
        }
        return sysTypeName; // 返回系统类型名称
    }

    /**
     * 获取网元类型名称
     *
     * @param _netTypeId 网元类型名称
     * @return 网元类型名称
     */
    public String getNetTypeName(String _netTypeId) {
        // 初始化数据
        TawwpUtil tawwpUtil = new TawwpUtil();
        String netTypeName = tawwpUtil.id2name("dict", _netTypeId);

        // netTypeName = (String) (TawwpStaticVariable.NET_INF.get(String
        // .valueOf(_netTypeId))); // 从缓存中获取网元类型信息

        // 如果缓存中不存在
        if (netTypeName == null) {

            // TawWsDictBO tawWsDictBO = new TawWsDictBO();
            ITawSystemDictTypeManager tawWsDictBO = (ITawSystemDictTypeManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemDictTypeManager");

            // TawWsDict tawWsDict = null;
            TawSystemDictType tawWsDict = null;
            // 获得字典对象
            tawWsDict = tawWsDictBO.getDictTypeByDictid(_netTypeId);
            // 如果字典对象不为空
            if (tawWsDict != null) {
                // 获得网元类型名称
                netTypeName = tawWsDict.getDictName();

            }
        }
        // 返回网元类型名称
        return netTypeName;
    }

    /**
     * 获取网元类型名称
     *
     * @param _netTypeId 网元类型名称
     * @return 网元类型名称
     */
    public String getMyNetTypeName(String _myNetTypeId) {
        String myNetTypeName = "";
        if (_myNetTypeId != null && !_myNetTypeId.equals("") && !_myNetTypeId.equals("0")) {
            TawwpUtil tawwpUtil = new TawwpUtil();
            myNetTypeName = tawwpUtil.id2name("dict", _myNetTypeId);
            // String mynetType[];
            // Hashtable myhashtable = TawwpStaticVariable.MY_NET_TYPE_INF;
            // Enumeration mynetEnumeration = myhashtable.keys();
            // while (mynetEnumeration.hasMoreElements()) {
            // mynetType = (String[]) mynetEnumeration.nextElement();
            // if (mynetType[1].equals(_myNetTypeId)) {
            // myNetTypeName = mynetType[0];
            // break;
            // }
            //
            // }
            // 如果缓存中不存在
            if (myNetTypeName.equals("")) {

                ITawSystemDictTypeManager tawWsDictBO = (ITawSystemDictTypeManager) ApplicationContextHolder
                        .getInstance().getBean("ItawSystemDictTypeManager");

                TawSystemDictType tawWsDict = null;
                tawWsDict = tawWsDictBO.getDictTypeByDictid(_myNetTypeId);
                // 如果字典对象不为空
                if (tawWsDict != null) {
                    // 获得网元类型名称
                    myNetTypeName = tawWsDict.getDictName();
                }
            }
        }
        // 返回网元类型名称
        return myNetTypeName;
    }

    /**
     * 获取机房名称
     *
     * @param _roomId 机房编号
     * @return 机房名称
     * @throws SQLException 数据库操作异常
     */
    public String getRoomName(int _roomId) throws SQLException {

        // 初始化数据
        String roomName = null;

        roomName = (String) TawwpStaticVariable.ROOM_INF.get(String
                .valueOf(_roomId));

        if (roomName == null) {
            ITawSystemCptroomManager cptRoomDao = (ITawSystemCptroomManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemCptroomManager");
            roomName = cptRoomDao.getTawSystemCptroomName(new Integer(_roomId));

        }

        return roomName; // 返回机房名称
    }

    /**
     * 获取机房名称
     *
     * @param _roomId String 机房编号
     * @return String 机房名称
     * @throws SQLException 数据库操作异常
     */
    public String getRoomName(String _roomId) throws SQLException {
        return this.getRoomName(Integer.parseInt(_roomId));
    }

    /**
     * 获取机房名称
     *
     * @param _roomIdStr 机房编号字符串(以“,”分隔)
     * @return 机房名称字符串
     * @throws Exception 异常信息
     */
    public String getRoomNames(String _roomIdStr) throws Exception {
        // 初始化数据
        String roomNameStr = "";
        String[] tempStr = _roomIdStr.split(",");
        try {
            // 获得机房名称字符串
            for (int i = 0; i < tempStr.length; i++) {
                roomNameStr += this.getRoomName(StaticMethod
                        .null2int(tempStr[i]))
                        + ","; // 获得部门名称
            }
            if (!"".equals(roomNameStr)) {
                // 如果结果不为空则去掉字符串末尾
                roomNameStr = roomNameStr
                        .substring(0, roomNameStr.length() - 1);
            }

            // 返回机房名称字符串
            return roomNameStr;
        } catch (Exception e) {
            // 捕获异常
            e.printStackTrace();
            throw new TawwpException("获取机房名称出现异常");
        }

    }

    /**
     * 获取岗位名称
     *
     * @param _orgPostId
     *            岗位编号
     * @throws Exception
     *             异常信息
     * @return 岗位名称
     */
    /*
     * public String getOrgPostName(int _orgPostId) throws Exception { //初始化数据
     * OrgPostDAO orgPostDAO = new OrgPostDAO(ds); String orgPostName = "";
     *
     * try { //获取岗位名称 orgPostName = orgPostDAO.getPostName(_orgPostId); //返回岗位名称
     * return StaticMethod.db Null2String(orgPostName); } catch (Exception e) {
     * //捕获异常 e.printStackTrace(); throw new TawwpException("获取岗位名称出现异常"); } }
     */

    /**
     * 获取岗位名称字符串
     *
     * @param _orgPostIdStr
     *            岗位编号字符串(以“,”分隔)
     * @throws Exception
     *             异常信息
     * @return 岗位名称
     */
    /*
     * public String getOrgPostNames(String _orgPostIdStr) throws Exception {
     * //初始化数据 String orgPostNameStr = ""; String[] tempStr =
     * _orgPostIdStr.split(",");
     *
     * //获得岗位名称字符串 for (int i = 0; i < tempStr.length; i++) { orgPostNameStr +=
     * this.getOrgPostName(Integer.parseInt(tempStr[i])) + ","; //获得岗位名称 } if
     * (!"".equals(orgPostNameStr)) { //如果结果不为空则去掉字符串末尾
     * orgPostNameStr.substring(0, orgPostNameStr.length() - 1); } //返回岗位名称字符串
     * return orgPostNameStr; }
     */

    /**
     * 获取指定用户所在岗位编号集合(用于组合SQL语句)
     *
     * @param _userId
     *            String 用户登录名
     * @throws Exception
     *             异常
     * @return String 岗位编号字符串
     */
    /*
     * public String getOrgPostIdStr(String _userId) throws Exception { //初始化数据
     * OrgPostDAO orgPostDAO = new OrgPostDAO(ds); String orgPostIdStr = "";
     *
     * try { //获取OrgUnit岗位单元集合 List orgUnitList =
     * orgPostDAO.getPostList(_userId); //获取用户所属岗位编号字符串,循环是因为一个用户可能属于多个岗位 for
     * (int i = 0; i < orgUnitList.size(); i++) { orgPostIdStr += "'" + (
     * (OrgUnit) orgUnitList.get(i)).getUnitId() + "',"; } //去掉字符串最后一个"," if
     * (orgPostIdStr != null && !"".equals(orgPostIdStr)) { orgPostIdStr =
     * orgPostIdStr.substring(0, orgPostIdStr.length() - 1); } else {
     * orgPostIdStr = "''"; } //返回岗位编号集合 return orgPostIdStr; } catch (Exception
     * e) { //捕获异常 e.printStackTrace(); throw new
     * TawwpException("获取岗位编号集合出现异常"); } }
     */

    /**
     * 获取指定用户所在岗位编号集合
     *
     * @param _userId
     *            String 用户登录名
     * @throws Exception
     *             异常
     * @return List 岗位编号字符串
     */
    /*
     * public List getOrgPostIdList(String _userId) throws Exception { //初始化数据
     * //OrgPostDAO orgPostDAO = new OrgPostDAO(ds); ITawSystemPostManager
     * orgPostDAO=(ITawSystemPostManager)ApplicationContextHolder.getInstance().getBean("ItawSystemPostManager");
     * List orgUnitList = null; List orgPostIdList = new ArrayList();
     *
     * try { //获取OrgUnit岗位单元集合 orgUnitList = orgPostDA.getPostList(_userId);
     *
     * //循环取出岗位编号,添加到集合中 for (int i = 0; i < orgUnitList.size(); i++) {
     * orgPostIdList.add( String.valueOf(((OrgUnit)
     * orgUnitList.get(i)).getUnitId())); }
     *
     * //返回岗位编号集合 return orgPostIdList; } catch (Exception e) { //捕获异常
     * e.printStackTrace(); throw new TawwpException("获取岗位编号集合出现异常"); } }
     */

    /**
     * 获取审批状态名称
     *
     * @param _checkState 审批状态编号
     * @return 审批状态名称
     */
    public static String getCheckStateName(int _checkState) {
        // 初始化数据
        String checkStateName = "";
        // 如果审批状态编号在审批状态范围以内
        if (_checkState < TawwpStaticVariable.CHECKSTATE.length
                && _checkState >= 0) {
            // 取出审批状态显示名称
            checkStateName = TawwpStaticVariable.CHECKSTATE[_checkState];
        }
        // 返回审批状态名称
        return checkStateName;
    }

    /**
     * 获取系统类别网元类型结构集合,系统类别信息缓存，网元类型信息缓存
     */
    public static void getSysNetInf() {
        // 初始化数据
        /*
         * TawWsDictTypeDAO tawWsDictTypeDAO = new TawWsDictTypeDAO();
         * TawWsDictBO tawWsDictBO = new TawWsDictBO();
         */
        ITawSystemDictTypeManager tawWsDictTypeDAO = (ITawSystemDictTypeManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDictTypeManager");

        LabelValueBean netLabelValueBean = null;

        List list = null;
        List netList = null;
        List newNetList = null;
        List myNetList = null;

        String[] sysType = null;
        String[] netType = null;
        String[] mynetType = null;

        Hashtable hashtable = new Hashtable(); // 创建字典类型结构对象

        Hashtable sysHashtable = new Hashtable(); // 创建系统类型对象
        Hashtable netHashtable = new Hashtable(); // 创建网元类别对象

        Hashtable mynettypetable = new Hashtable();// 联通专有网元类型分类

        if (TawwpStaticVariable.SYS_NET_INF == null) {

            try {
                list = tawWsDictTypeDAO.getDictSonsByDictid(String
                        .valueOf(TawwpStaticVariable.GZJHDICTFLAG));

                // getDictTypeSelect(TawwpStaticVariable. GZJHDICTFLAG);
                // //获取作业计划的所有系统类别

                for (int i = 0; i < list.size(); i++) {
                    TawSystemDictType tawSystemDictType = new TawSystemDictType();
                    tawSystemDictType = (TawSystemDictType) list.get(i);
                    sysType = new String[2];
                    sysType[0] = tawSystemDictType.getDictName();
                    sysType[1] = tawSystemDictType.getDictId();

                    sysHashtable.put(tawSystemDictType.getDictId(),
                            tawSystemDictType.getDictName());

                    newNetList = new ArrayList();
                    hashtable.put(sysType, newNetList);

                    // netList =
                    // tawWsDictBO.getWsDictSelect(Integer.parseInt(sysType[1]));
                    netList = tawWsDictTypeDAO.getDictSonsByDictid(sysType[1]);

                    for (int j = 0; j < netList.size(); j++) {
                        TawSystemDictType netSystemDictType = (TawSystemDictType) netList
                                .get(j);
                        netType = new String[2];
                        netType[0] = netSystemDictType.getDictName();
                        netType[1] = netSystemDictType.getDictId();
                        netHashtable.put(netSystemDictType.getDictId(),
                                netSystemDictType.getDictName());
                        newNetList.add(netType);

                        myNetList = tawWsDictTypeDAO
                                .getDictSonsByDictid(netType[1]);

                        for (int m = 0; m < myNetList.size(); m++) {
                            mynetType = new String[2];
                            TawSystemDictType myNetSystemDictType = (TawSystemDictType) myNetList
                                    .get(m);
                            mynetType[0] = myNetSystemDictType.getDictName();
                            mynetType[1] = myNetSystemDictType.getDictId();
                            mynettypetable.put(mynetType, netType[1]);
                        }
                    }
                }
                TawwpStaticVariable.SYS_NET_INF = hashtable;
                TawwpStaticVariable.SYS_INF = sysHashtable;
                TawwpStaticVariable.NET_INF = netHashtable;
                TawwpStaticVariable.MY_NET_TYPE_INF = mynettypetable;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取所有机房信息，放入缓存中
     */
    public static void getRoomInf() {
        /* TawApparatusroomDAO tawApparatusroomDAO = new TawApparatusroomDAO(); */
        ITawSystemCptroomManager cptRoomDao = (ITawSystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemCptroomManager");
        LabelValueBean labelValueBean = null;
        List list = null;
        Hashtable hashtable = new Hashtable();
        try {
            list = cptRoomDao.getTawSystemCptroomList();
            for (int j = 0; j < list.size(); j++) {
                labelValueBean = (LabelValueBean) list.get(j);
                hashtable.put(labelValueBean.getValue(), labelValueBean
                        .getLabel());
            }
            TawwpStaticVariable.ROOM_INF = hashtable;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有厂商信息，放入缓存中
     */
    public static void getVendorInf() {
        // TawwpNetDAO tawwpNetDAO = new TawwpNetDAO();
        ITawwpNetDao tawwpNetDao = (ITawwpNetDao) ApplicationContextHolder
                .getInstance().getBean("tawwpNetDao");

        try {
            TawwpStaticVariable.VENDOR_INF = tawwpNetDao.listVerdor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取作业作业计划的特殊表格信息，放入缓存中
     */
    public static void getAddonsInf() {
        // TawwpAddonsTableDAO tawwpAddonsTableDAO = new TawwpAddonsTableDAO();
        ITawwpAddonsTableDao tawwpAddonsTableDao = (ITawwpAddonsTableDao) ApplicationContextHolder
                .getInstance().getBean("tawwpAddonsTableDao");
        List list = null;
        TawwpAddonsTable tawwpAddonsTable = null;
        TawwpAddonsTableVO tawwpAddonsTableVO = null;
        Hashtable hastable = new Hashtable();

        try {
            list = tawwpAddonsTableDao.listAddonsTable(String
                    .valueOf(TawwpStaticVariable.GZJHDICTFLAG)); // 获取所有附加表的集合
            // 循环对每个附加表进行分类处理
            for (int i = 0; i < list.size(); i++) {
                tawwpAddonsTable = (TawwpAddonsTable) list.get(i); // 获取一个附加表
                tawwpAddonsTableVO = new TawwpAddonsTableVO();
                // 防止实例化
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
                        tawwpAddonsTable); // 将附加表信息导入到附加表显示类中
                hastable.put(tawwpAddonsTableVO.getId(), tawwpAddonsTableVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TawwpStaticVariable.ADDONS_INF = hastable;
    }

    /**
     * 获取作业作业计划的特殊表格信息，放入缓存中
     */
    public static void getAddonsRank() {
        // TawwpAddonsTableDAO tawwpAddonsTableDAO = new TawwpAddonsTableDAO();
        ITawwpAddonsTableDao tawwpAddonsTableDao = (ITawwpAddonsTableDao) ApplicationContextHolder
                .getInstance().getBean("tawwpAddonsTableDao");
        List list = null;
        TawwpAddonsTable tawwpAddonsTable = null;
        TawwpAddonsTableVO tawwpAddonsTableVO = null;
        Hashtable hastableRank = new Hashtable();

        try {
            list = tawwpAddonsTableDao.listAddonsTable(String
                    .valueOf(TawwpStaticVariable.GZJHDICTFLAG)); // 获取所有附加表的集合
            // 循环对每个附加表进行分类处理
            for (int i = 0; i < list.size(); i++) {
                tawwpAddonsTable = (TawwpAddonsTable) list.get(i); // 获取一个附加表
                tawwpAddonsTableVO = new TawwpAddonsTableVO();
                // 防止实例化
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
                        tawwpAddonsTable); // 将附加表信息导入到附加表显示类中
                // modefied by lijia 2005-12-09
                // hastable.put(tawwpAddonsTableVO.getId(), tawwpAddonsTableVO);
                hastableRank.put(String.valueOf(i), tawwpAddonsTableVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TawwpStaticVariable.ADDONS_RANK = hastableRank;
    }

    /**
     * 系统初始化，基础信息放入缓存中
     */
    public static void initInfor() {
        getSysNetInf(); // 系统类别网元类型
        getRoomInf(); // 机房信息
        getVendorInf(); // 厂商信息
        getAddonsInf(); // 附加表信息
        getAddonsRank();
    }

    public List getformdataid(String _startday, String _endday, String netid,
                              String _addonid) throws SQLException {
        ITawwpExecuteContentDao tawwpExecuteContentDao = (ITawwpExecuteContentDao) ApplicationContextHolder
                .getInstance().getBean("tawwpExecuteContentDao");
        List list = null;
        list = tawwpExecuteContentDao.getformdataid(_startday, _endday, netid,
                _addonid);
        return list;
    }

    /**
     * 获取部门中的子部门编号集合(包括当前部门)
     *
     * @param _deptId int 部门编号
     * @return String 子部门编号集合
     */
    public String getChildDept(String _deptId) {
        // 初始化数据
        ITawSystemDeptManager tawSystemDeptManager = (ITawSystemDeptManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDeptManager");
        String deptStr = "";
        List list = null;
        // 取所有子部门id
        list = tawSystemDeptManager.getSubDepts(_deptId);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                deptStr = deptStr + ((TawSystemDept) list.get(i)).getDeptId()
                        + ",";
            }
        }
        deptStr = deptStr.substring(0, deptStr.length() - 1);
        return deptStr;
    }

    /**
     * 获取字典名称
     *
     * @param _netTypeId 网元类型名称
     * @return 网元类型名称
     */
    public String getDictName(int _netTypeId, String type) throws Exception {
        TawwpUtil tawwpUtil = new TawwpUtil();
        String dictName = tawwpUtil.id2name("dict", _netTypeId + "");
        if (dictName == null || "".equals(dictName)) {
            TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemDictTypeDao");
            TawSystemDictType dict = dao.getDictType(_netTypeId, type);
            if (dict != null) {
                dictName = dict.getDictName();
            }
        }
        return dictName;
    }
}
