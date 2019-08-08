package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.util.DeptConstants;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleDao;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemRoleDaoHibernate extends BaseDaoHibernate implements
        TawSystemRoleDao, ID2NameDAO {

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleDao#getTawSystemRoles(com.boco.eoms.commons.system.role.model.TawSystemRole)
     */
    public List getTawSystemRoles() {
        return getHibernateTemplate().find("from TawSystemRole");

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (tawSystemRole == null) {
         * return getHibernateTemplate().find("from TawSystemRole"); } else { //
         * filter on properties set in the tawSystemRole HibernateCallback
         * callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex =
         * Example.create(tawSystemRole).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return session.createCriteria(TawSystemRole.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleDao#getTawSystemRole(String
     * roleId)
     */
    public TawSystemRole getTawSystemRole(final long roleId) {
        TawSystemRole tawSystemRole = (TawSystemRole) getHibernateTemplate()
                .get(TawSystemRole.class, new Long(roleId));
        if (tawSystemRole == null) {
            return new TawSystemRole();
        }
        return tawSystemRole;
    }

    public TawSystemRole getTawSystemRole(final long roleId, final int deleted) {
        TawSystemRole tawSystemRole = null;
        List list = getHibernateTemplate().find(
                "from TawSystemRole p where p.roleId=" + roleId
                        + " and p.deleted=" + deleted);
        if (list.size() > 0) {
            tawSystemRole = (TawSystemRole) list.get(0);
        }

        return tawSystemRole;
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleDao#saveTawSystemRole(TawSystemRole
     * tawSystemRole)
     */
    public void saveTawSystemRole(final TawSystemRole tawSystemRole) {
        if (tawSystemRole.getRoleId() == 0)
            getHibernateTemplate().save(tawSystemRole);
        else
            getHibernateTemplate().saveOrUpdate(tawSystemRole);
    }

    /**
     * @throws TawSystemUserException
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleDao#removeTawSystemRole(String
     * roleId)
     */
    public void removeTawSystemRole(final long roleId)
            throws TawSystemUserException {
        TawSystemRole tawSystemRole = getTawSystemRole(roleId);
        tawSystemRole.setDeleted(new Integer(StaticVariable.DELETED));
        getHibernateTemplate().saveOrUpdate(tawSystemRole);
    }

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
     * where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getTawSystemRoles(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        // filter on properties set in the tawSystemRole
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemRole";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                Integer total = (Integer) session.createQuery(queryCountStr)
                        .iterate().next();
                Query query = session.createQuery(queryStr);
                query
                        .setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getTawSystemRoles(final Integer curPage, final Integer pageSize) {
        return this.getTawSystemRoles(curPage, pageSize, null);
    }

    public List getRolesByStructureFlag(String structureFlag, int deleted) {
        String hql = "from TawSystemRole p WHERE p.structureFlag like '"
                + structureFlag + "%' and deleted=" + deleted;
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    public List getChildRoleByRoleIdPirv(final long roleId) {

        String flag = DeptConstants.SYSTEM_ROLE; // 系统角色
        String hql = "from TawSystemRole p WHERE p.parentId=" + roleId
                + " and p.roleTypeId='" + flag + "' and deleted="
                + StaticVariable.UNDELETED;
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    public List getChildRoleByRoleId(final long roleId) {
        String hql = "from TawSystemRole p WHERE p.parentId=" + roleId
                + " and " + StaticMethod.noDeletedCon("p");
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    public List getSysRolesByRoleId(final long roleId) {
        String hql = "from TawSystemRole r WHERE r.parentId=" + roleId
                + " and r.roleTypeId in (" + RoleConstants.systemRole + ","
                + RoleConstants.ROLETYPE_VIRTUAL + ")" + " and deleted="
                + StaticVariable.UNDELETED;
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    public List getFlwRolesByWorkflowFlag(final int workflowFlag) {
        String hql = "from TawSystemRole r WHERE r.workflowFlag ="
                + workflowFlag + " and r.roleTypeId in ("
                + RoleConstants.flowRole + "," + RoleConstants.ROLETYPE_VIRTUAL
                + ")" + " and deleted=" + StaticVariable.UNDELETED;
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    public String getRoleNameById(final long roleId) {
        TawSystemRole tawSystemRole = null;
        List list = getHibernateTemplate().find(
                "from TawSystemRole p where p.roleId=" + roleId);
        if (list.size() > 0) {
            tawSystemRole = (TawSystemRole) list.get(0);
        }
        if (tawSystemRole != null)
            return tawSystemRole.getRoleName();
        return null;
    }

    /**
     * @param roleId
     * @param leaf   1叶子,0非叶子
     */
    public void setLeaf(final long roleId, final Integer leaf) {
        TawSystemRole tawSystemRole = this.getTawSystemRole(roleId);
        if (tawSystemRole.getLeaf() != leaf) {
            tawSystemRole.setLeaf(leaf);
            getHibernateTemplate().saveOrUpdate(tawSystemRole);
        }
    }

    public String getNewStructureFlag(long parentRoleId) throws Exception {
        String ret = StaticVariable.defaultnull;
        String parentRoleStructflag = "";
        long temp;

        TawSystemRole role = this.getTawSystemRole(parentRoleId);
        parentRoleStructflag = role.getStructureFlag();
        if (parentRoleStructflag == null)
            return null;

        ret = this.getMaxStructureFlag(parentRoleStructflag,
                parentRoleStructflag.length() + 2, StaticVariable.UNDELETED);
        if (ret.equals(parentRoleStructflag)) {
            ret = parentRoleStructflag + "01";
        } else {
            temp = StaticMethod.null2Long(ret);
            if (ret.compareTo(parentRoleStructflag + "99") < 0) {
                ret = String.valueOf(temp + 1);
            } else {
                ret = StaticVariable.defaultnull;
            }
        }

        return ret;
    }

    public String getMaxStructureFlag(String parentRoleStructflag, int len,
                                      int undel) throws Exception {
        String ret = "";

        String hql = "SELECT max(p.structureFlag) FROM TawSystemRole p "
                + "WHERE p.structureFlag like \'" + parentRoleStructflag
                + "%\' and length(p.structureFlag) <=" + len
                + " and p.deleted=" + undel;
        List list = getHibernateTemplate().find(hql);
        if (list.size() > 0) {
            ret = list.get(0).toString();
        }

        return ret;
    }

    public List getRolesByPostId(long postId) {
        String hql = "from TawSystemRole p WHERE p.postId =" + postId
                + " and deleted=" + StaticVariable.UNDELETED;
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    /*
     * id2name，即角色id转为角色名称 added by qinmin
     *
     * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(final String id) throws DictDAOException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // hql，以角色ID为条件查询
                String queryStr = " from TawSystemRole role where role.roleId=:roleId";

                Query query = session.createQuery(queryStr);
                // 角色ID号
                query.setString("roleId", id);
                // 仅查一条
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSystemRole role = null;

                if (list != null && !list.isEmpty()) {
                    // 不为空则取dept
                    role = (TawSystemRole) list.iterator().next();
                } else {
                    // 为空，写入将部门名称设为未知联系人
                    role = new TawSystemRole();
                    role.setRoleName(Util.idNoName());
                }
                return role;
            }
        };

        TawSystemRole role = null;
        try {
            role = (TawSystemRole) getHibernateTemplate().execute(callback);
        } catch (Exception e) {
            // 若有异常则抛出daoexception,加入DAoException是为了解藕，若抛出hibernateException，这样在换orm时，如ibatis，service就要换异常捕捉
            throw new DictDAOException(e);
        }
        return role.getRoleName();
    }

    public List getRolesByWorkflowFlag(final int workflowFlag) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // hql，以workflow_flag为条件查询
                String queryStr = " from TawSystemRole role where role.roleTypeId="
                        + RoleConstants.flowRole
                        + " and role.workflowFlag=:workflowFlag and deleted="
                        + StaticVariable.UNDELETED;

                Query query = session.createQuery(queryStr);
                // workflow_flag
                query.setInteger("workflowFlag", workflowFlag);

                List list = query.list();
                return list;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 获取大角色对应的所有部门
     *
     * @param roleId
     * @return <TawSystemDept>
     */
    public List getDeptByRoleId(final long roleId) {
        String hql = "select distinct d from TawSystemDept d,TawSystemSubRole r where r.roleId="
                + roleId
                + " and r.deptId=d.deptId and r.deleted='0' order by d.deptId";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 获取大角色对应的所有地域
     *
     * @param roleId
     * @return <TawSystemDept>
     */
    public List getAreaByRoleId(final long roleId) {
        String hql = "select distinct d from TawSystemArea d,TawSystemSubRole r where r.roleId="
                + roleId
                + " and r.deptId=d.areaid and r.deleted='0' order by d.areaid";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 获取角色对象
     *
     * @param workflowFlag 流程编号
     * @param roleName     角色名称
     * @return
     */
    public TawSystemRole getTawSystemRole(Integer workflowFlag, String roleName) {
        TawSystemRole tawSystemRole = null;
        List list = getHibernateTemplate().find(
                "from TawSystemRole p where p.workflowFlag=" + workflowFlag
                        + " and p.roleName='" + roleName + "'");
        if (list.size() > 0) {
            tawSystemRole = (TawSystemRole) list.get(0);
        }

        return tawSystemRole;
    }

    public List getRolesOfArea(String areaId) {
        return this
                .getHibernateTemplate()
                .find(
                        "select distinct role from TawSystemRole role ,TawSystemSubRole subrole where subrole.area='"
                                + areaId
                                + "' and role.roleId=subrole.roleId and "
                                + StaticMethod.noDeletedCon("role"));
    }

}
