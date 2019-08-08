package com.boco.eoms.commons.system.priv.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;

public class TawSystemPrivOperationDaoHibernate extends BaseDaoHibernate
        implements TawSystemPrivOperationDao {

    /**
     * 部门mgr
     */
    private ITawSystemDeptManager tawSystemDeptManager;

    private List moduleList = new ArrayList();

    /**
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#getTawSystemPrivOperations(com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation)
     */
    public List getTawSystemPrivOperations(
            final TawSystemPrivOperation tawSystemPrivOperation) {
        return getHibernateTemplate().find(
                "from TawSystemPrivOperation oper where oper.deleted=0");

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (tawSystemPrivOperation ==
         * null) { return getHibernateTemplate().find("from
         * TawSystemPrivOperation"); } else { // filter on properties set in the
         * tawSystemPrivOperation HibernateCallback callback = new
         * HibernateCallback() { public Object doInHibernate(Session session)
         * throws HibernateException { Example ex =
         * Example.create(tawSystemPrivOperation).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return
         * session.createCriteria(TawSystemPrivOperation.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#getTawSystemPrivOperation(String
     * id)
     */
    public TawSystemPrivOperation getTawSystemPrivOperation(final String id) {
        TawSystemPrivOperation tawSystemPrivOperation = (TawSystemPrivOperation) getHibernateTemplate()
                .get(TawSystemPrivOperation.class, id);
        if (tawSystemPrivOperation == null) {
            throw new ObjectRetrievalFailureException(
                    TawSystemPrivOperation.class, id);
        }

        return tawSystemPrivOperation;
    }

    /**
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#saveTawSystemPrivOperation(TawSystemPrivOperation
     * tawSystemPrivOperation)
     */
    public void saveTawSystemPrivOperation(
            final TawSystemPrivOperation tawSystemPrivOperation) {
        if ((tawSystemPrivOperation.getId() == null)
                || (tawSystemPrivOperation.getId().equals("")))
            getHibernateTemplate().save(tawSystemPrivOperation);
        else
            getHibernateTemplate().saveOrUpdate(tawSystemPrivOperation);
    }

    /**
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#removeTawSystemPrivOperation(String
     * id)
     */
    public void removeTawSystemPrivOperation(final String id) {
        getHibernateTemplate().delete(getTawSystemPrivOperation(id));

    }

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
     * where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getTawSystemPrivOperations(final Integer curPage,
                                          final Integer pageSize, final String whereStr) {
        // filter on properties set in the tawSystemPrivOperation
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemPrivOperation";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();
                Query query = session.createQuery(queryStr);
                query
                        .setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getTawSystemPrivOperations(final Integer curPage,
                                          final Integer pageSize) {
        return this.getTawSystemPrivOperations(curPage, pageSize, null);
    }

    /**
     * 得到某一模块下的所有对象（包括子模块与功能项,包括该模块）
     */
    public List getAllSubObjects(String code) {
        List list = new ArrayList();
        String hql = " from TawSystemPrivOperation operation where operation.parentcode like '"
                + code + "%'";
        list = (ArrayList) getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 得到某一模块下的所有子模块 （包括子模块的子模块，,包括该模块）
     */
    public List getAllSubModules(String code) {
        List list = new ArrayList();
        String hql = " from TawSystemPrivOperation operation where operation.parentcode like '"
                + code + "%" + "' and operation.isApp='1'";
        list = (ArrayList) getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 得到某一模块下的所有功能项（包括子模块的功能项）
     */

    public List getAllSubOperations(String code) {
        List list = new ArrayList();
        String hql = " from TawSystemPrivOperation operation where operation.parentcode like '"
                + code + "%" + "' and operation.isApp='0'";
        list = getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 得到某一模块下的对象（仅仅是有关该模块的）
     */
    public List getObjects(String code) {
        List list = new ArrayList();
        String hql = " from TawSystemPrivOperation operation where operation.parentcode ='"
                + code + "'";
        list = getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 得到某一模块下的功能项（仅仅是有关该模块的）
     */
    public List getOperations(String code) {
        List list = new ArrayList();
        String hql = " from TawSystemPrivOperation operation where operation.parentcode ='"
                + code + "' and operation.isApp='0'";

        list = getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 得到某一模块下的子模块(仅仅是有关该模块的)
     */
    public List getModules(String code, String deleted) {
        List list = new ArrayList();
        String hql = "";
        if (deleted.equals(StaticVariable.ALLDELETED)) {
            hql = " from TawSystemPrivOperation operation where operation.parentcode ='"
                    + code + "'";
        } else {
            hql = " from TawSystemPrivOperation operation where operation.parentcode ='"
                    + code
                    + "' and (operation.deleted='"
                    + deleted
                    + "' or operation.isApp='2')";
        }

        list = getHibernateTemplate().find(
                hql + " order by cast(orderby as integer)");
        return list;
    }

    /**
     * 得到某一模块的直接父模块
     *
     * @param childObjectCode
     * @return
     */
    public TawSystemPrivOperation getFatherModule(String childObjectCode) {
        List list = new ArrayList();
        String hql = " from TawSystemPrivOperation operation where operation.code ='"
                + childObjectCode.substring(0, childObjectCode.length() - 2)
                + "'";

        list = getHibernateTemplate().find(hql);
        if (!list.isEmpty())
            return (TawSystemPrivOperation) list.get(0);
        return null;
    }

    /**
     * 得到某一模块的所有父模块集合
     */

    public List getAllFatherModules(String childCode) {
        if (!moduleList.isEmpty()) {
            this.moduleList.clear();
        }
        loadAllFatherModuels(childCode);
        return this.moduleList;
    }

    /**
     * 递归实现，装载该模块的所有父模块
     *
     * @param childCode
     */
    public void loadAllFatherModuels(String childCode) {
        TawSystemPrivOperation tawSystemPrivOperation = null;
        tawSystemPrivOperation = this.getFatherModule(childCode);
        if (tawSystemPrivOperation == null)
            return;
        moduleList.add(tawSystemPrivOperation);
        if (!tawSystemPrivOperation.getParentcode().equals("-1")) {
            loadAllFatherModuels(tawSystemPrivOperation.getCode());
        }
    }

    /**
     * 得到某一模块下最大的CODE值
     *
     * @param fatherId
     * @return
     */
    public String getMaxCodeValue(String fatherId) {
        List list = new ArrayList();
        String hql = "select max(operation.code) from TawSystemPrivOperation operation where operation.parentcode ='"
                + fatherId + "'";
        // + "' and operation.isApp='1'";

        list = getHibernateTemplate().find(hql);
        if (list.size() > 0)
            return (String) list.get(0);
        return "";
    }

    public String getMaxCodeValue() {
        String _strRtn = "54";

        String _strHQL = "select max(opt.code) from TawSystemPrivOperation opt where opt.parentcode='-1'";
        List _objResult = getHibernateTemplate().find(_strHQL);
        if (_objResult.get(0) != null) {
            _strRtn = (String) _objResult.get(0);
        }

        return _strRtn;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#getTawSystemPrivOpt(java.lang.String)
     */
    public TawSystemPrivOperation getTawSystemPrivOpt(String code) {
        String _strHQL = "from TawSystemPrivOperation operation where operation.code='"
                + code + "'";
        return (TawSystemPrivOperation) getHibernateTemplate().find(_strHQL)
                .get(0);
    }

    public String getParentModuleName(String _strParentId) {
        String _strRtn = "root";

        if (!"-1".equals(_strParentId)) {
            String _strHQL = "select opt.name from TawSystemPrivOperation opt where opt.code='"
                    + _strParentId + "'";
            List _objResult = getHibernateTemplate().find(_strHQL);
            if (_objResult.get(0) != null) {
                _strRtn = (String) _objResult.get(0);
            }
        }
        return _strRtn;
    }

    /**
     * 查询下一级菜单
     */
    public List getDirectSubModules(String _strParentId) {
        String _strHQL = "from TawSystemPrivOperation opt where opt.parentcode='"
                + _strParentId
                + "' and opt.isApp<>'2' and opt.deleted='0' and opt.hided<>'"
                + PrivConstants.MENU_HIDDEN
                + "' and (opt.loginType is null or opt.loginType = '"
                + PrivConstants.PRIV_OPERATION_EOMS_LOING
                + "') order by cast(opt.orderby as integer) ";
        return getHibernateTemplate().find(_strHQL);
    }

    public void removeTawSystemPrivOperationByCode(String _strCode) {
        String _strHQL = "from TawSystemPrivOperation opt where opt.code='"
                + _strCode + "'";
        getSession().delete(_strHQL, TawSystemPrivOperation.class);
    }

    //2009.03.24加，删除节点，顺带删除所有子节点
    public void removeAllNodeByCode(final String _strCode) {
        final String hql = "delete from TawSystemPrivOperation opt where opt.code like '"
                + _strCode + "%'";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                query.executeUpdate();
                return null;
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 根据CODE取得对应的NAME
     *
     * @param code
     * @return
     */
    public TawSystemPrivOperation getPrivOperationbyCode(String code) {

        String hql = "  from TawSystemPrivOperation operation where operation.code='"
                + code
                + "' and (operation.deleted<>'"
                + Constants.DELETED_FLAG
                + "' or operation.isApp='2')";
        List list = getHibernateTemplate().find(hql);
        TawSystemPrivOperation operation = new TawSystemPrivOperation();
        if (null != list && 0 != list.size()) {
            operation = (TawSystemPrivOperation) list.get(0);
        }
        return operation;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#getTawSystemPrivOerationsByParentCode(java.lang.String)
     */
    public List getTawSystemPrivOerationsByParentCode(String parentCode) {
        String sqlall = "from TawSystemPrivOperation " + "where parentcode='"
                + parentCode + "' and deleted<>'" + Constants.DELETED_FLAG
                + "' and hided<>'" + PrivConstants.MENU_HIDDEN
                + "' and (isApp='" + PrivConstants.OPERATION_APP_TYPE_MODULE
                + "' or isApp='" + PrivConstants.OPERATION_APP_TYPE_FUNCTION
                + "') order by cast(orderby as integer)";
        return (List) getHibernateTemplate().find(sqlall);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#listModuleMenu(java.lang.String,
     *      java.lang.String, java.util.List,java.lang.String,java.lang.String)
     */
    public List listOperation(String userId, String deptId, List roleIds,
                              String type, String parentCode) {
        // select * from taw_system_priv_operation operation
        // ,taw_system_priv_menuitem menuitem where operation.code=menuitem.code
        // and
        // menuitem.menuid in(select menuid from taw_system_priv_assign where
        // objectid='qujingbo') and operation.parentcode='-1'
        String inHql = getOperationInhsql(userId, deptId, roleIds);
        String hql = "select distinct operation from TawSystemPrivOperation operation ,TawSystemPrivMenuItem menuitem "
                + "where operation.code=menuitem.code and menuitem.menuid in("
                + inHql + ") and operation.parentcode='" + parentCode + "' ";

        if (PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION.equals(type)) {
            hql += " and (operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_MODULE
                    + "' or operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_FUNCTION + "')";
        }

        hql += " and operation.hided<>'" + PrivConstants.MENU_HIDDEN
                + "' and operation.deleted<>'" + Constants.DELETED_FLAG
                + "' and (operation.loginType is null or operation.loginType = '" + PrivConstants.PRIV_OPERATION_EOMS_LOING + "' ) order by operation.orderby";
        // + "' order by cast(operation.orderby as integer)";
        /*
         * List list = new ArrayList(); list =
         * (ArrayList)getHibernateTemplate().find(hql); Comparator comp = new
         * Mycomparator(); Collections.sort(list,comp);
         */
        List a = getHibernateTemplate().find(hql);
        return a;

    }

    // wap
    public List listOperationwap(String userId, String deptId, List roleIds,
                                 String type, String parentCode) {
        // select * from taw_system_priv_operation operation
        // ,taw_system_priv_menuitem menuitem where operation.code=menuitem.code
        // and
        // menuitem.menuid in(select menuid from taw_system_priv_assign where
        // objectid='qujingbo') and operation.parentcode='-1'
        String inHql = getOperationInhsqls(userId, deptId, roleIds, StaticVariable.NATURE_WAP);
        String hql = "select distinct operation from TawSystemPrivOperation operation ,TawSystemPrivMenuItem menuitem "
                + "where operation.code=menuitem.code and menuitem.menuid in("
                + inHql + ") and operation.parentcode='" + parentCode + "' ";

        if (PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION.equals(type)) {
            hql += " and (operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_MODULE
                    + "' or operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_FUNCTION + "')";
        }
///" and operation.hided<>'" + PrivConstants.MENU_HIDDEN	+
        hql += " and operation.deleted<>'" + Constants.DELETED_FLAG
                + "' and operation.loginType = '" + PrivConstants.PRIV_OPERATION_WAP_LOING + "' order by operation.orderby";
        // + "' order by cast(operation.orderby as integer)";
        /*
         * List list = new ArrayList(); list =
         * (ArrayList)getHibernateTemplate().find(hql); Comparator comp = new
         * Mycomparator(); Collections.sort(list,comp);
         */
        return getHibernateTemplate().find(hql);

    }

    public List listOperationWap(String userId, String deptId, List roleIds,
                                 String type, String parentCode, String loginNature) {
        String inHql = getOperationInhsqls(userId, deptId, roleIds, loginNature);
        String hql = "select distinct operation from TawSystemPrivOperation operation ,TawSystemPrivMenuItem menuitem "
                + "where operation.code=menuitem.code and menuitem.menuid in("
                + inHql + ") and operation.parentcode='" + parentCode + "' ";

        if (PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION.equals(type)) {
            hql += " and (operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_MODULE
                    + "' or operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_FUNCTION + "')";
        }

        hql += " and operation.deleted<>'" + Constants.DELETED_FLAG
                + "' and operation.loginType = '" + PrivConstants.PRIV_OPERATION_WAP_LOING + "' order by operation.orderby";
        // + "' order by cast(operation.orderby as integer)";
        /*
         * List list = new ArrayList(); list =
         * (ArrayList)getHibernateTemplate().find(hql); Comparator comp = new
         * Mycomparator(); Collections.sort(list,comp);
         */
        return getHibernateTemplate().find(hql);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#listOperation(java.lang.String,
     *      java.lang.String, java.util.List, java.lang.String,java.lang.String,
     *      java.lang.String)
     */
    public List listOperation(String userId, String deptId, List roleIds,
                              String menuId, String type, String parentCode) {
        String hql = "select distinct operation from TawSystemPrivOperation operation ,TawSystemPrivMenuItem menuitem "
                + "where operation.code=menuitem.code and menuitem.menuid ='"
                + menuId + "' and operation.parentcode='" + parentCode + "'";
        if (PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION.equals(type)) {
            hql += " and (operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_MODULE
                    + "' or operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_FUNCTION + "')";
        }

        hql += " and operation.hided<>'" + PrivConstants.MENU_HIDDEN
                + "' and operation.deleted<>'" + Constants.DELETED_FLAG
                + "' order by operation.orderby ";
        // return getHibernateTemplate().find(hql);
        /*
         * List list = new ArrayList(); list = (ArrayList) Comparator comp = new
         * Mycomparator(); Collections.sort(list,comp);
         */
        return getHibernateTemplate().find(hql);
    }

    /**
     * 获取查询菜单的sql的in中hql语句
     *
     * @param userId  用户id
     * @param deptId  部门id
     * @param roleIds 角色id列表
     * @return 返回查询菜单的sql中的in中的hql语句
     */
    private String getOperationInhsql(String userId, String deptId, List roleIds) {
        String inHql = "select distinct assign.privid from TawSystemPrivAssign assign,TawSystemPrivMenu menu where ((assign.objectid='"
                + userId
                + "' and assign.assigntype='"
                + StaticVariable.PRIV_ASSIGNTYPE_USER
                + "')"; // StaticVariable.NATURE_WAP
        // 该用户有所属部门
        if (deptId != null && !"".equals(deptId.toString())) {
            deptId = this.tawSystemDeptManager.deptId2deptIds(deptId);
            inHql += " or assign.objectid in(" + deptId
                    + ") and assign.assigntype='"
                    + StaticVariable.PRIV_ASSIGNTYPE_DEPT + "'";
        }
        // 该用户有角色
        if (roleIds != null && !roleIds.isEmpty()) {
            String roleHql = "";
            // 遍历角色列表
            for (Iterator it = roleIds.iterator(); it.hasNext(); ) {
                TawSystemSubRole role = (TawSystemSubRole) it.next();
                roleHql += " assign.objectid='" + role.getId() + "' or";
            }
            // 去掉末尾的"or"
            if (roleHql.endsWith("or")) {
                roleHql = roleHql
                        .substring(0, roleHql.length() - "or".length());
            }
            inHql += " or ((" + roleHql + ") and assign.assigntype='"
                    + StaticVariable.PRIV_ASSIGNTYPE_ROLE + "')";
        }
        inHql = inHql + " ) and assign.privid = menu.privid and ( menu.nature is null or menu.nature = '"
                + StaticVariable.NATURE_EOMS + "')";
        return inHql;
    }

    /**
     * 获取查询菜单的sql的in中hql语句
     *
     * @param userId  用户id
     * @param deptId  部门id
     * @param roleIds 角色id列表
     * @return 返回查询菜单的sql中的in中的hql语句
     * <p>
     * add by gongyufeng 为wap登陆准备菜单数据
     */
    private String getOperationInhsqls(String userId, String deptId,
                                       List roleIds, String loginNature) {
        String inHql = "select distinct assign.privid from TawSystemPrivAssign assign,TawSystemPrivMenu menu where ((assign.objectid='"
                + userId
                + "' and assign.assigntype='"
                + StaticVariable.PRIV_ASSIGNTYPE_USER
                + "')"; // StaticVariable.NATURE_WAP
        // 该用户有所属部门
        if (deptId != null && !"".equals(deptId.toString())) {
            deptId = this.tawSystemDeptManager.deptId2deptIds(deptId);
            inHql += " or assign.objectid in(" + deptId
                    + ") and assign.assigntype='"
                    + StaticVariable.PRIV_ASSIGNTYPE_DEPT + "'";
        }
        // 该用户有角色
        if (roleIds != null && !roleIds.isEmpty()) {
            String roleHql = "";
            // 遍历角色列表
            for (Iterator it = roleIds.iterator(); it.hasNext(); ) {
                TawSystemSubRole role = (TawSystemSubRole) it.next();
                roleHql += " assign.objectid='" + role.getId() + "' or";
            }
            // 去掉末尾的"or"
            if (roleHql.endsWith("or")) {
                roleHql = roleHql
                        .substring(0, roleHql.length() - "or".length());
            }
            inHql += " or ((" + roleHql + ") and assign.assigntype='"
                    + StaticVariable.PRIV_ASSIGNTYPE_ROLE + "')";
        }
        inHql = inHql + " ) and assign.privid = menu.privid and menu.nature = '"
                + loginNature + "'";
        return inHql;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#listModuleMenu(java.lang.String,
     *      java.lang.String, java.util.List,java.lang.String)
     */
    public List listOperation(final String userId, final String deptId,
                              final List roleIds, final String url, final Integer total) {
        // select * from taw_system_priv_operation operation
        // ,taw_system_priv_menuitem menuitem where operation.code=menuitem.code
        // and
        // menuitem.menuid in(select menuid from taw_system_priv_assign where
        // objectid='qujingbo') and operation.parentcode='-1'

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String inHql = getOperationInhsql(userId, deptId, roleIds);
                String hql = "select distinct operation from TawSystemPrivOperation operation ,TawSystemPrivMenuItem menuitem "
                        + "where operation.code=menuitem.code and menuitem.menuid in("
                        + inHql
                        + ") and operation.hided<>'"
                        + PrivConstants.MENU_HIDDEN
                        + "' and operation.deleted<>'"
                        + Constants.DELETED_FLAG
                        + "' and operation.url='"
                        + url
                        + "' order by operation.orderby";

                Query query = session.createQuery(hql);
                // 取total条记录，否则取全部记录
                if (total != null) {
                    query.setFirstResult(0);
                    query.setMaxResults(total.intValue());
                }
                return query.list();

            }
        };
        return (List) getHibernateTemplate().execute(callback);

    }

    /**
     * @param tawSystemDeptManager the tawSystemDeptManager to set
     */
    public void setTawSystemDeptManager(
            ITawSystemDeptManager tawSystemDeptManager) {
        this.tawSystemDeptManager = tawSystemDeptManager;
    }

    public List listOpertion(String userId, String deptId, List roleIds,
                             String type, String operationId) {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * 得到某一模块下的所有未删除未隐藏的对象（包括子模块与功能项,包括该模块）
     */
    public List getAllEnableSubObjects(String code) {
        List list = new ArrayList();

        String hql = " from TawSystemPrivOperation operation where (operation.parentcode like '"
                + code + "%' or code='" + code + "') and deleted<>'" + Constants.DELETED_FLAG
                + "' and hided<>'" + PrivConstants.MENU_HIDDEN
                + "' and isApp in('" + PrivConstants.OPERATION_APP_TYPE_MODULE
                + "','" + PrivConstants.OPERATION_APP_TYPE_FUNCTION
                + "') order by cast(orderby as integer)";
        list = (ArrayList) getHibernateTemplate().find(hql);
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao#listModuleMenu(java.lang.String,
     *      java.lang.String, java.util.List,java.lang.String,java.lang.String)
     */
    public List listOperationAll(String userId, String deptId, List roleIds,
                                 String type, String parentCode) {

        String inHql = getOperationInhsql(userId, deptId, roleIds);
        String hql = "select distinct operation from TawSystemPrivOperation operation ,TawSystemPrivMenuItem menuitem "
                + "where operation.code=menuitem.code and menuitem.menuid in("
                + inHql + ") and (operation.parentcode in (select code from TawSystemPrivOperation where (parentcode like '"
                + parentCode + "%' or code='" + parentCode + "') and deleted<>'" + Constants.DELETED_FLAG
                + "' and hided<>'" + PrivConstants.MENU_HIDDEN
                + "' and isApp in('" + PrivConstants.OPERATION_APP_TYPE_MODULE
                + "','" + PrivConstants.OPERATION_APP_TYPE_FUNCTION
                + "')) or operation.code='" + parentCode + "')";

        if (PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION.equals(type)) {
            hql += " and (operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_MODULE
                    + "' or operation.isApp='"
                    + PrivConstants.OPERATION_APP_TYPE_FUNCTION + "')";
        }

        hql += " and operation.hided<>'" + PrivConstants.MENU_HIDDEN
                + "' and operation.deleted<>'" + Constants.DELETED_FLAG
                + "' order by operation.orderby";

        return getHibernateTemplate().find(hql);

    }

}
