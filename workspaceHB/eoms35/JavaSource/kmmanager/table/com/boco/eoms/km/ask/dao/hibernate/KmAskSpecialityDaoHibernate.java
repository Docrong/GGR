package com.boco.eoms.km.ask.dao.hibernate;

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
import com.boco.eoms.km.ask.dao.KmAskSpecialityDao;
import com.boco.eoms.km.ask.model.KmAskSpeciality;
import com.boco.eoms.km.ask.util.KmAskSpecialityConstants;


/**
 * <p>
 * Title:问答类型 dao的hibernate实现
 * </p>
 * <p>
 * Description:问答类型
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskSpecialityDaoHibernate extends BaseDaoHibernate implements KmAskSpecialityDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.ask.KmAskSpecialityDao#getKmAskSpeciality(java.lang.String)
     */
    public KmAskSpeciality getKmAskSpeciality(final String id) {
        KmAskSpeciality kmAskSpeciality = (KmAskSpeciality) getHibernateTemplate().get(KmAskSpeciality.class, id);
        if (kmAskSpeciality == null) {
            kmAskSpeciality = new KmAskSpeciality();
        }
        return kmAskSpeciality;
    }

    /**
     * @see com.boco.eoms.km.ask.KmAskSpecialityDao#saveKmAskSpecialitys(com.boco.eoms.km.ask.KmAskSpeciality)
     */
    public void saveKmAskSpeciality(final KmAskSpeciality kmAskSpeciality) {
        if ((kmAskSpeciality.getId() == null) || (kmAskSpeciality.getId().equals("")))
            getHibernateTemplate().save(kmAskSpeciality);
        else
            getHibernateTemplate().saveOrUpdate(kmAskSpeciality);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmAskSpeciality kmAskSpeciality = this.getKmAskSpecialityByNodeId(id);
        if (kmAskSpeciality == null) {
            return "";
        }
        //TODO 请修改代码
        return kmAskSpeciality.getName();
    }

    public KmAskSpeciality getKmAskSpecialityByNodeId(final String nodeId) {
        KmAskSpeciality kmAskSpeciality = new KmAskSpeciality();
        String hql = " from KmAskSpeciality kmAskSpeciality where kmAskSpeciality.nodeId='" + nodeId + "'";
        List list = getHibernateTemplate().find(hql);
        if (list.iterator().hasNext()) {
            kmAskSpeciality = (KmAskSpeciality) list.iterator().next();
        }
        return kmAskSpeciality;
    }

    public void removeKmAskSpecialityByNodeId(final String nodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = "delete from KmAskSpeciality kmAskSpeciality where kmAskSpeciality.nodeId like ?";
                Query query = session.createQuery(hql);
                query.setString(0, nodeId + '%');
                query.executeUpdate();
                return null;
            }
        };
        getHibernateTemplate().execute(callback);
    }

    public List getNextLevelKmAskSpecialitys(final String parentNodeId) {
        String hql = " from KmAskSpeciality kmAskSpeciality where kmAskSpeciality.parentNodeId='"
                + parentNodeId + "'";
        hql += " order by kmAskSpeciality.nodeId";
        return getHibernateTemplate().find(hql);
    }

    public String getUsableNodeId(final String parentNodeId, final int length) {
        String minNodeId = getUsableMinNodeId(parentNodeId, length);
        if (null == minNodeId || "".equals(minNodeId)) {
            minNodeId = KmAskSpecialityConstants.NODEID_DEFAULT_VALUE;
        }
        return minNodeId;
    }

    private String getUsableMinNodeId(final String parentNodeId, final int len) {
        String minUsableNodeId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = " select distinct(nodeId) from KmAskSpeciality kmAskSpeciality where kmAskSpeciality.nodeId like '"
                        + parentNodeId + "%' and length(kmAskSpeciality.nodeId)='" + len + "'";
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
        if (hm.size() >= Integer.parseInt(KmAskSpecialityConstants.NODEID_IF_MAXID)) {
            return minUsableNodeId;
        }
        long nodeIdVar = Long.valueOf(parentNodeId + KmAskSpecialityConstants.NODEID_NOSON)
                .longValue();
        while (null != hm.get(String.valueOf(nodeIdVar))) {
            nodeIdVar = nodeIdVar + 1;
        }
        return String.valueOf(nodeIdVar);
    }

    public List getChildNodes(final String parentNodeId) {
        String hql = " from KmAskSpeciality kmAskSpeciality where kmAskSpeciality.parentNodeId like '"
                + parentNodeId + "%'";
        hql += " order by kmAskSpeciality.nodeId";
        return (List) getHibernateTemplate().find(hql);
    }

}