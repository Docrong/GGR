package com.boco.eoms.operuser.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.operuser.dao.OperuserDao;
import com.boco.eoms.operuser.model.Operuser;
import com.boco.eoms.operuser.util.OperuserConstants;


/**
 * <p>
 * Title:operuser dao的hibernate实现
 * </p>
 * <p>
 * Description:operuser
 * </p>
 * <p>
 * Tue Mar 31 09:42:13 CST 2009
 * </p>
 *
 * @author xiang
 * @version 35
 */
public class OperuserDaoHibernate extends BaseDaoHibernate implements OperuserDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.operuser.OperuserDao#getOperuser(java.lang.String)
     */
    public Operuser getOperuser(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Operuser operuser where operuser.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (Operuser) result.iterator().next();
                } else {
                    return new Operuser();
                }
            }
        };
        return (Operuser) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.operuser.OperuserDao#saveOperusers(com.boco.eoms.operuser.Operuser)
     */
    public void saveOperuser(final Operuser operuser) {
        if ((operuser.getId() == null) || (operuser.getId().equals("")))
            getHibernateTemplate().save(operuser);
        else
            getHibernateTemplate().saveOrUpdate(operuser);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        Operuser operuser = this.getOperuserByNodeId(id);
        if (operuser == null) {
            return "";
        }
        //TODO 请修改代码
        return operuser.getName();
    }

    public Operuser getOperuserByNodeId(final String nodeId) {
        Operuser operuser = new Operuser();
        String hql = " from Operuser operuser where operuser.nodeId='" + nodeId + "'";
        List list = getHibernateTemplate().find(hql);
        if (list.iterator().hasNext()) {
            operuser = (Operuser) list.iterator().next();
        }
        return operuser;
    }

    /**
     * 得到部门的所有人员
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptid(String deptid) {
        String hql = " from Operuser t where t.deptid='"
                + deptid + "' order by name";
        List list = new ArrayList();
        list = (ArrayList) getHibernateTemplate().find(hql);

        return list;
    }

    /**
     * 通过用户名称或部门查询人员
     *
     * @param deptid
     * @return
     */
    public List getUserByNameOrdeptid(String name, String deptname) {
        List list = new ArrayList();
        if (name.equals("") && !deptname.equals("")) {
            String hql = "from Operuser u where u.deptname like '%" + deptname
                    + "%' order by name";
            list = (ArrayList) getHibernateTemplate().find(hql);
        }
        if (!name.equals("") && deptname.equals("")) {
            String hql = "from Operuser u where u.name like '%" + name
                    + "%' order by name";
            list = (ArrayList) getHibernateTemplate().find(hql);
        }
        if (!name.equals("") && !deptname.equals("")) {
            String hql = "from Operuser u where u.name like '%" + name
                    + "%'and u.deptname like '%" + deptname + "%' order by name";
            list = (ArrayList) getHibernateTemplate().find(hql);
        }
        return list;
    }

    public void removeOperuserByNodeId(final String nodeId) {
        final String hql = "delete from Operuser operuser where operuser.nodeId like '"
                + nodeId + "%'";
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

    public List getNextLevelOperusers(final String parentNodeId) {
        String hql = " from Operuser operuser where operuser.parentNodeId='"
                + parentNodeId + "'";
        hql += " order by operuser.nodeId";
        return getHibernateTemplate().find(hql);
    }

    public String getUsableNodeId(final String parentNodeId, final int length) {
        String minNodeId = getUsableMinNodeId(parentNodeId, length);
        if (null == minNodeId || "".equals(minNodeId)) {
            minNodeId = OperuserConstants.NODEID_DEFAULT_VALUE;
        }
        return minNodeId;
    }

    private String getUsableMinNodeId(final String parentNodeId, final int len) {
        String minUsableNodeId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = " select distinct(nodeId) from Operuser operuser where operuser.nodeId like '"
                        + parentNodeId + "%' and length(operuser.nodeId)='" + len + "'";
                Query query = session.createQuery(hql);
                return query.list();
            }
        };
        List list = (List) getHibernateTemplate().execute(callback);
        HashMap hm = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            String nodeId = it.next().toString();
            hm.put(nodeId, nodeId);
        }
        // 防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
        if (hm.size() >= Integer.parseInt(OperuserConstants.NODEID_IF_MAXID)) {
            return minUsableNodeId;
        }
        long nodeIdVar = Long.valueOf(parentNodeId + OperuserConstants.NODEID_NOSON)
                .longValue();
        while (null != hm.get(String.valueOf(nodeIdVar))) {
            nodeIdVar = nodeIdVar + 1;
        }
        return String.valueOf(nodeIdVar);
    }

    public List getChildNodes(final String parentNodeId) {
        String hql = " from Operuser operuser where operuser.parentNodeId like '"
                + parentNodeId + "%'";
        hql += " order by operuser.nodeId";
        return (List) getHibernateTemplate().find(hql);
    }


    /*
     * name2Id，即字典id转为字典名称 added by fengshaohong
     *
     * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
     */
    public String name2Id(final String dictName, final String parentDictId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // hql，以角色ID为条件查询
                String queryStr = " from TawSystemDictType dictType where dictType.dictName=:dictName and dictType.parentDictId=:parentDictId";

                Query query = session.createQuery(queryStr);
                // 角色ID号
                query.setString("dictName", dictName);
                query.setString("parentDictId", parentDictId);
                // 仅查一条
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSystemDictType dictType = null;

                if (list != null && !list.isEmpty()) {
                    // 不为空则取dept
                    dictType = (TawSystemDictType) list.iterator().next();
                } else {
                    // 为空，写入将部门名称设为未知联系人
                    dictType = new TawSystemDictType();
                    dictType.setDictName("");
                }
                return dictType;
            }
        };

        TawSystemDictType dictType = null;

        dictType = (TawSystemDictType) getHibernateTemplate().execute(
                callback);

        return dictType.getDictId();
    }


}