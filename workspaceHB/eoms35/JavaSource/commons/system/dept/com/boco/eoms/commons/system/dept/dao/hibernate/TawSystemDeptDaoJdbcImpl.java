package com.boco.eoms.commons.system.dept.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDaoJdbc;
import com.boco.eoms.commons.system.dept.util.DeptConstants;

public class TawSystemDeptDaoJdbcImpl extends BaseDaoJdbc implements
        TawSystemDeptDaoJdbc {

    /**
     * 查询最大的部门ID
     */
    public String getMaxDeptid(String pardeptid, int len, String deleted) {
        //		String sql = " select max(deptid) as deptid from taw_system_dept where deptid like '"
        //				+ pardeptid
        //				+ "%' and length(deptid)<='"
        //				+ len
        //				+ "' and deleted='" + deleted + "'";
        //		String maxdeptid = "";
        //
        //		maxdeptid = String.valueOf(getJdbcTemplate().queryForMap(sql).get(
        //				"deptid"));
        //		return maxdeptid;
        String minDeptId = getUsableMinDeptId(pardeptid, len, deleted);
        if ((minDeptId.equals("") || minDeptId.equals("null"))) {
            minDeptId = DeptConstants.DEPTID_MAXID;
        }
        return minDeptId;
    }

    /**
     * 查询最大的部门ID
     */
    public String getMaxLinkid(String parentLinkId, int len, String deleted) {
        String minLinkId = getUsableMinLinkId(parentLinkId, len, deleted);
        if ((minLinkId.equals("") || minLinkId.equals("null"))) {
            minLinkId = DeptConstants.LINKID_MAXID;
        }
        return minLinkId;
    }

    /**
     * 得到下一级子部门的部门信息
     *
     * @param pardeptid
     * @param delid
     * @return
     */
    public List getNextLevecDepts(String pardeptid, String delid) {

        String hql = " select * from taw_system_dept  where parentDeptid='"
                + pardeptid + "'" + " and deleted='" + delid
                + "' order by substr(deptname,'0','1')";
        List list = new ArrayList();
        list = getJdbcTemplate().queryForList(hql);
        return list;
    }

    /**
     * 返回当前最小的未被占用的deptId liqiuye 20080925
     *
     * @param parentDeptId
     * @param len
     * @param deleted
     */
    public String getUsableMinDeptId(String parentDeptId, int len,
                                     String deleted) {
        String maxUsableDeptId = "";
        String sql = " select distinct(deptid) from taw_system_dept where deptid like '"
                + parentDeptId
                + "%' and length(deptid)='"
                + len
                + "' and deleted='" + deleted + "'";
        List list = getJdbcTemplate().queryForList(sql);
        HashMap hm = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map deptMap = (Map) it.next();
            String deptId = deptMap.get("deptid").toString();
            hm.put(deptId, deptId);
        }
        //防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
        if (hm.size() >= Integer.parseInt(DeptConstants.DEPTID_IF_MAXID)) {
            return maxUsableDeptId;
        }
        long deptIdVar = Long
                .valueOf(parentDeptId + DeptConstants.DEPTID_NOSON).longValue();
        while (null != hm.get(String.valueOf(deptIdVar))) {
            deptIdVar = deptIdVar + 1;
        }
        return String.valueOf(deptIdVar);
    }

    /**
     * 返回当前最小的未被占用的linkId liqiuye 20081224
     *
     * @param parentLinkId
     * @param len
     * @param deleted
     */
    public String getUsableMinLinkId(String parentLinkId, int len,
                                     String deleted) {
        String maxUsableLinkId = "";
        String sql = " select distinct(linkid) from taw_system_dept where linkid like '"
                + parentLinkId
                + "%' and length(linkid)='"
                + len
                + "' and deleted='" + deleted + "'";
        List list = getJdbcTemplate().queryForList(sql);
        HashMap hm = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map linkMap = (Map) it.next();
            String linkId = linkMap.get("linkid").toString();
            hm.put(linkId, linkId);
        }
        //防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
        if (hm.size() >= Integer.parseInt(DeptConstants.LINKID_IF_MAXID)) {
            return maxUsableLinkId;
        }
        long linkIdVar = Long
                .valueOf(parentLinkId + DeptConstants.LINKID_NOSON).longValue();
        while (null != hm.get(String.valueOf(linkIdVar))) {
            linkIdVar = linkIdVar + 1;
        }
        return String.valueOf(linkIdVar);
    }

    public String getUsableMinTmpDeptId(String parentDeptId, int len,
                                        String deleted) {
        String maxUsableDeptId = "";
        String sql = " select distinct(tmpdeptid) from taw_system_dept where tmpdeptid like '"
                + parentDeptId
                + "%' and length(tmpdeptid)='"
                + len
                + "' and deleted='" + deleted + "'";
        List list = getJdbcTemplate().queryForList(sql);
        HashMap hm = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map deptMap = (Map) it.next();
            String deptId = StaticMethod.nullObject2String(deptMap.get("tmpdeptid"));
            if (!"".equals(deptId)) {
                hm.put(deptId, deptId);
            }


        }
        //防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
        if (hm.size() >= Integer.parseInt(DeptConstants.DEPTID_IF_MAXID)) {
            return maxUsableDeptId;
        }
        long deptIdVar = Long
                .valueOf(parentDeptId + DeptConstants.DEPTID_NOSON).longValue();
        while (null != hm.get(String.valueOf(deptIdVar))) {
            deptIdVar = deptIdVar + 1;
        }
        return String.valueOf(deptIdVar);
    }

    public String getMaxTmpDeptid(String pardeptid, int len, String deleted) {
        //		String sql = " select max(deptid) as deptid from taw_system_dept where deptid like '"
        //		+ pardeptid
        //		+ "%' and length(deptid)<='"
        //		+ len
        //		+ "' and deleted='" + deleted + "'";
        //String maxdeptid = "";
        //
        //maxdeptid = String.valueOf(getJdbcTemplate().queryForMap(sql).get(
        //		"deptid"));
        //return maxdeptid;
        String minDeptId = getUsableMinTmpDeptId(pardeptid, len, deleted);
        if ((minDeptId.equals("") || minDeptId.equals("null"))) {
            minDeptId = DeptConstants.DEPTID_MAXID;
        }
        return minDeptId;

    }
}
