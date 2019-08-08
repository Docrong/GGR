package com.boco.eoms.commons.system.dept.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDaoJdbc;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.util.DeptConstants;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

public class TawSystemDeptManagerImpl extends BaseManager implements
        ITawSystemDeptManager {
    private TawSystemDeptDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSystemDeptDao(TawSystemDeptDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#getTawSystemDepts(com.boco.eoms.commons.system.dept.model.TawSystemDept)
     */
    public List getTawSystemDepts(final TawSystemDept tawSystemDept) {
        return dao.getTawSystemDepts(tawSystemDept);
    }

    /**
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#getTawSystemDept(String
     * id)
     */
    public TawSystemDept getTawSystemDept(final Integer id) {
        return dao.getTawSystemDept(id);
    }

    /**
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#saveTawSystemDept(TawSystemDept
     * tawSystemDept)
     */
    public void saveTawSystemDept(TawSystemDept tawSystemDept) {
        dao.saveTawSystemDept(tawSystemDept);
    }

    /**
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#removeTawSystemDept(String
     * id)
     */
    public void removeTawSystemDept(final Integer id) {
        dao.removeTawSystemDept(id);
    }

    /**
     * 为缓存提供的删除
     */
    public void removeTawSystemDeptforCatch(final Integer id,
                                            final String parentdeptid, TawSystemDept systemdept) {
        dao.removeTawSystemDept(id);

    }

    /**
     * 根据部门ID和删除标志 查询某部门信息
     *
     * @param deptid
     * @param delid  0表示全部查询 1表示查询停职或被删除的
     * @return
     */
    public TawSystemDept getDeptinfobydeptid(String deptid, String delid) {

        TawSystemDept sysdept = new TawSystemDept();
        sysdept = dao.getDeptinfobydeptid(deptid, delid);
        if (null == sysdept) {
            return new TawSystemDept();
        }
        return sysdept;
    }

    /**
     * 根据部门名称和删除标志查询部门信息
     *
     * @param deptname
     * @param delid    0表示全部查询 1表示查询停职或被删除的
     * @return
     */
    public TawSystemDept getDeptinfoBydeptname(String deptname, String delid) {

        TawSystemDept sysdept = new TawSystemDept();
        sysdept = dao.getDeptinfoBydeptname(deptname, delid);
        return sysdept;

    }

    /**
     * 根据部门ID 和特殊排序标志 删除标志 查询同级别的部门信息
     *
     * @param deptid
     * @param delid  0表示全部查询 1表示查询停职或被删除的
     * @return
     */
    public List getSamelevelDeptbyDeptids(String deptid, String ordercode,
                                          String delid) {

        List list = new ArrayList();
        list = dao.getSamelevelDeptbyDeptids(deptid, ordercode, delid);
        return list;
    }

    /**
     * 根据删除标志 查询部门的增删情况
     *
     * @param delid 0表示全部查询 1表示查询停职或被删除的
     * @return
     */
    public List geDeptdelInfos(String delid) {

        List list = new ArrayList();
        list = dao.geDeptdelInfos(delid);
        return list;

    }

    /**
     * 查询某用户创建的部门情况
     *
     * @param operuserid
     * @param delid      0表示全部查询 1表示查询停职或被删除的
     * @return
     */
    public List getDeptdelInfosByoperuserid(String operuserid, String delid) {

        List list = new ArrayList();
        list = dao.getDeptdelInfosByoperuserid(operuserid, delid);
        return list;
    }

    /**
     * 根据deptids 和 delid 进行IN查询部门信息
     *
     * @param deptids
     * @param delid   delid 0表示全部查询 1表示查询停职或被删除的
     * @return
     */
    public List getDeptByinDeptidsandDelids(String deptids, String delid) {

        List list = new ArrayList();
        list = dao.getDeptByinDeptidsandDelids(deptids, delid);
        return list;
    }

    /**
     * 判断部门名称是否存在
     *
     * @param deptname
     * @return
     */
    public boolean getDeptnameIsExist(String deptname, String delid) {

        boolean flag = dao.getDeptnameIsExist(deptname, delid);
        return flag;
    }

    /**
     * 查询所有的部门名称
     *
     * @return
     */
    public List getDeptnames() {

        List list = new ArrayList();
        list = dao.getDeptnames();
        return list;
    }

    /**
     * 得到下一级子部门的部门信息
     *
     * @param pardeptid
     * @param delid
     * @return
     */
    public List getNextLevecDepts(String pardeptid, String delid) {
        List list = new ArrayList();
        list = dao.getNextLevecDepts(pardeptid, delid);
        return list;
    }

    /**
     * 得到下一级非代维部门信息 2008-12-2 wangsixuan
     *
     * @param pardeptid
     * @param delid
     * @return
     */
    public List getNextLevecDeptsNoPartner(String pardeptid, String delid) {

        List list = new ArrayList();
        list = dao.getNextLevecDeptsNoPartner(pardeptid, delid);
        return list;

    }

    /**
     * 得到是否是代维部门信息 2008-12-2 wangsixuan
     *
     * @param pardeptid
     * @param delid
     * @return
     */
    public boolean isPartner(String delid) {
        return dao.isPartner(delid);
    }

    /**
     * 根据关键字查询部门名称，得到部门列表，只是非代维部门 2008-12-3 wangsixuan
     *
     * @param word 部门名称关键字
     * @return List
     */
    public List searchByNameNoPartner(String word) {
        List list = new ArrayList();
        list = dao.searchByNameNoPartner(word);
        return list;
    }

    /**
     * 根据关键字查询部门名称，得到部门列表，只是代维部门 2008-12-3 wangsixuan
     *
     * @param word 部门名称关键字
     * @return List
     */
    public List searchByNamePartner(String word, String contacter, String deptphone) {
        List list = new ArrayList();
        list = dao.searchByNamePartner(word, contacter, deptphone);
        return list;
    }

    /**
     * 得到下一级代维子部门的部门信息 2008-11-17 liujinlong
     *
     * @param pardeptid
     * @param delid
     * @return
     */
    public List getNextLevecDaiweiDepts(String pardeptid, String delid) {
        List list = new ArrayList();
        list = dao.getNextLevecDaiweiDepts(pardeptid, delid);
        return list;
    }

    /**
     * 查询某个地区的部门信息
     *
     * @param regionid
     * @return
     */
    public List getDeptRegion(Integer regionid, String delid) {

        List list = new ArrayList();
        list = dao.getDeptRegion(regionid, delid);
        return list;

    }

    /**
     * 查询所有的地市
     *
     * @param delid
     * @return
     */
    public List getAllRegion(String delid) {

        List list = new ArrayList();
        list = dao.getAllRegion(delid);
        return list;

    }

    /**
     * 查询所有子部门 LINK查询
     *
     * @param deptid
     * @param delid
     * @return
     */
    public List getALLSondept(String deptid, String delid) {

        List list = new ArrayList();
        list = dao.getALLSondept(deptid, delid);
        return list;

    }

    /**
     * 查询所有子部门 值班用
     *
     * @param deptid
     * @param delid
     * @return
     */
    public List getALLdept(String deptid, String delid) {

        List list = new ArrayList();
        list = dao.getALLdept(deptid, delid);
        return list;

    }

    /**
     * 根据部门ID得到最大的子部门ID
     *
     * @param deptid
     * @return
     */
    public String getNewDeptid(String pardeptid, String deleted) {
        String usableDeptId = "";
        // long deptidvar = 0;
        TawSystemDeptDaoJdbc daojdbc = (TawSystemDeptDaoJdbc) ApplicationContextHolder
                .getInstance().getBean("tawSystemDeptDaoJdbc");
        usableDeptId = daojdbc.getMaxDeptid(pardeptid, pardeptid.length()
                + DeptConstants.DEPTID_BETWEEN_LENGTH, deleted);
        // if (maxdeptid.equals(pardeptid)) {
        // maxdeptid = pardeptid + "01";
        // } else {
        // // modify by wangbeiying 2008-8-11
        // // 修改父部门为1，并且已经有9个子部门时子，增加子部门结果永远为20的bug
        // String subdeptid = maxdeptid.substring(pardeptid.length());
        // deptidvar = Long.valueOf(subdeptid).longValue();
        // if (maxdeptid.compareTo(pardeptid + "99") < 0) {
        // maxdeptid = pardeptid + String.valueOf(deptidvar + 1);
        // } else {
        // maxdeptid = "-10";
        // }
        // }
        return usableDeptId;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#getNewLinkid(java.lang.String,
     *      java.lang.String)
     */
    public String getNewLinkid(String parentLinkId, String deleted) {
        String usableLinkId = "";
        TawSystemDeptDaoJdbc daojdbc = (TawSystemDeptDaoJdbc) ApplicationContextHolder
                .getInstance().getBean("tawSystemDeptDaoJdbc");
        usableLinkId = daojdbc.getMaxLinkid(parentLinkId, parentLinkId.length()
                + DeptConstants.LINKID_BETWEEN_LENGTH, deleted);
        return usableLinkId;
    }

    /**
     * 查询所有父部门信息
     *
     * @param deptid
     * @param len
     * @param deleted
     * @return
     */
    public List getFdeptInfo(String deptid, String deleted) {

        List list = new ArrayList();
        int len = deptid.length();

        if (len > 3) {
            deptid = deptid.substring(0, 3);
        }
        list = dao.getFdeptInfo(deptid, len, deleted);
        return list;
    }

    /**
     * 查询所有父部门信息 值班
     *
     * @param deptid
     * @param len
     * @param deleted
     * @return
     */
    public List getFdeptInfoduty(String deptid, String deleted) {

        List list = new ArrayList();
        int len = deptid.length();

        if (len > 3) {
            deptid = deptid.substring(0, 4);
        }
        list = dao.getFdeptInfo(deptid, len, deleted);
        return list;
    }

    /*
     * id2name，即部门id转为部门名称 added by qinmin
     *
     * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(final String id) throws DictDAOException {
        return dao.id2Name(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#getParentDepts(java.lang.String)
     */
    public List getParentDepts(final String deptId) {
        List depts = new ArrayList();
        // 为防止改变参数，新增一临时变量
        String pDeptId = new String(deptId);
        TawSystemDept dept = dao.getDeptinfobydeptid(pDeptId,
                DeptConstants.NO_DELETED);

        if (dept != null) {
            depts.add(dept);
            pDeptId = dept.getParentDeptid();
            while (true) {
                TawSystemDept item = dao.getDeptinfobydeptid(pDeptId,
                        DeptConstants.NO_DELETED);
                // 若未取到部门则返回结果
                if (null == item || null == item.getId()
                        || "".equals(item.getId())) {
                    return depts;
                } else {
                    depts.add(item);
                    pDeptId = item.getParentDeptid();
                }
            }
        }
        // getParentDept(deptId, depts);
        return depts;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#getSubDepts(java.lang.String)
     */
    public List getSubDepts(String deptId) {
        TawSystemDept dept = dao.getDeptinfobydeptid(deptId,
                DeptConstants.NO_DELETED);
        List result = new ArrayList();
        getSubDepts(dept, result);
        result.add(dept);
        return result;
    }

    private void getSubDepts(TawSystemDept dept, List result) {
        List list = dao.getNextLevecDepts(dept.getDeptId(),
                DeptConstants.NO_DELETED);
        // 逐级取子部门
        if (list != null && !list.isEmpty()) {
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                TawSystemDept item = (TawSystemDept) it.next();
                getSubDepts(item, result);
            }
            result.addAll(list);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#getRootDept()
     */
    public List getRootDept() {
        return dao.getRootDept();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#searchByName()
     */
    public List searchByName(String word) {
        List list = new ArrayList();
        list = dao.searchByName(word);
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager#deptId2deptIds(java.lang.String)
     */
    public String deptId2deptIds(String deptId) {
        List list = getParentDepts(deptId);
        if (list == null || list.isEmpty()) {
            return "";
        }
        String result = "";
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            TawSystemDept dept = (TawSystemDept) it.next();
            result += "'" + dept.getDeptId() + "',";
        }
        // 去尾部空格
        result = StaticMethod.removeLastStr(result, ",");
        return result;
    }

    public List getNextLikeDepts(String parentLinkId, String deleted) {
        List list = new ArrayList();
        list = dao.getNextLikeDepts(parentLinkId, deleted);
        return list;
    }

    public String getNewTmpDeptid(String pardeptid, String deleted) {
        String usableDeptId = "";
        // long deptidvar = 0;
        TawSystemDeptDaoJdbc daojdbc = (TawSystemDeptDaoJdbc) ApplicationContextHolder
                .getInstance().getBean("tawSystemDeptDaoJdbc");
        usableDeptId = daojdbc.getMaxTmpDeptid(pardeptid, pardeptid.length()
                + DeptConstants.DEPTID_BETWEEN_LENGTH, deleted);
        // if (maxdeptid.equals(pardeptid)) {
        // maxdeptid = pardeptid + "01";
        // } else {
        // // modify by wangbeiying 2008-8-11
        // // 修改父部门为1，并且已经有9个子部门时子，增加子部门结果永远为20的bug
        // String subdeptid = maxdeptid.substring(pardeptid.length());
        // deptidvar = Long.valueOf(subdeptid).longValue();
        // if (maxdeptid.compareTo(pardeptid + "99") < 0) {
        // maxdeptid = pardeptid + String.valueOf(deptidvar + 1);
        // } else {
        // maxdeptid = "-10";
        // }
        // }
        return usableDeptId;
    }

    public String getDeptidUserCount(String deptId) {
        // TODO Auto-generated method stub
        return null;
    }

    public List getworkplanDeptnames() {
        List list = new ArrayList();
        list = dao.getworkplanDeptnames();
        return list;
    }

    /**
     * 仅适用于动态流程图所使用
     *
     * @param id
     * @return
     * @author : wangjianhua
     * @date : 2009-07-06
     */
    public TawSystemDept getTawSystemDeptById(Integer id) {
        return dao.getTawSystemDeptById(id);
    }

    /**
     * 根据linkID和删除标志 查询某部门信息
     *
     * @param linkid
     * @param delid  0表示全部查询 1表示查询停职或被删除的
     * @return
     */
    public TawSystemDept getDeptinfobylinkid(String linkid, String delid) {

        TawSystemDept sysdept = new TawSystemDept();
        sysdept = dao.getDeptinfobylinkid(linkid, delid);
        if (null == sysdept) {
            return new TawSystemDept();
        }
        return sysdept;
    }
}
