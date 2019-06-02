package com.boco.eoms.km.exam.dao.hibernate;

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
import com.boco.eoms.km.exam.dao.KmExamSpecialtyDao;
import com.boco.eoms.km.exam.model.KmExamSpecialty;
import com.boco.eoms.km.exam.util.KmExamSpecialtyConstants;


/**
 * <p>
 * Title:专业表 dao的hibernate实现
 * </p>
 * <p>
 * Description:专业表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmExamSpecialtyDaoHibernate extends BaseDaoHibernate implements KmExamSpecialtyDao,
		ID2NameDAO {
    
    /**
	 *
	 * @see com.boco.eoms.km.exam.KmExamSpecialtyDao#getKmExamSpecialty(java.lang.String)
	 *
	 */
	public KmExamSpecialty getKmExamSpecialty(final String id) {
		KmExamSpecialty kmExamSpecialty = (KmExamSpecialty) getHibernateTemplate().get(KmExamSpecialty.class, id);
		if (kmExamSpecialty == null) {
			kmExamSpecialty = new KmExamSpecialty();
		}
		return kmExamSpecialty;
	}
    
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamSpecialtyDao#saveKmExamSpecialtys(com.boco.eoms.km.exam.KmExamSpecialty)
	 *      
	 */
	public void saveKmExamSpecialty(final KmExamSpecialty kmExamSpecialty) {
		if ((kmExamSpecialty.getSpecialtyID() == null) || (kmExamSpecialty.getSpecialtyID().equals("")))
			getHibernateTemplate().save(kmExamSpecialty);
		else
			getHibernateTemplate().saveOrUpdate(kmExamSpecialty);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmExamSpecialty kmExamSpecialty = this.getKmExamSpecialtyByNodeId(id);
		if(kmExamSpecialty==null){
			return "";
		}
		//TODO 请修改代码
		return kmExamSpecialty.getSpecialtyName();
		
	}
    
    public KmExamSpecialty getKmExamSpecialtyByNodeId(final String nodeId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql = " from KmExamSpecialty kmExamSpecialty where kmExamSpecialty.nodeId=?";
				Query query = session.createQuery(hql);
				query.setString(0, nodeId);
				List list = query.list();
				if (list.iterator().hasNext()) {
					return (KmExamSpecialty) list.iterator().next();
				}
				return new KmExamSpecialty();
			}
		};
		return (KmExamSpecialty) getHibernateTemplate().execute(callback);
    }
    
    public void removeKmExamSpecialtyByNodeId(final String nodeId) {    	
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {				
				StringBuffer queryStr = new StringBuffer("delete from KmExamSpecialty kmExamSpecialty ");
				queryStr.append("where kmExamSpecialty.nodeId like ? ");

				Query query = session.createQuery(queryStr.toString());
				query.setString(0, nodeId + '%');
				int i = query.executeUpdate();
				return new Integer(i);
			}
		};
		getHibernateTemplate().execute(callback);
    }
    
    public List getNextLevelKmExamSpecialtys(final String parentNodeId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamSpecialty kmExamSpecialty ");
				queryStr.append("where kmExamSpecialty.parentNodeId=? ");
				queryStr.append("order by kmExamSpecialty.nodeId");

				Query query = session.createQuery(queryStr.toString());
				query.setString(0, parentNodeId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
    }
    
    public String getUsableNodeId(final String parentNodeId, final int length) {
    	String minNodeId = getUsableMinNodeId(parentNodeId, length);
		if (null == minNodeId || "".equals(minNodeId)) {
			minNodeId = KmExamSpecialtyConstants.NODEID_DEFAULT_VALUE;
		}
		return minNodeId;
    }
    
    private String getUsableMinNodeId(final String parentNodeId, final int length) {
		String minUsableNodeId = "";
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("select distinct(nodeId) ");
				queryStr.append("from KmExamSpecialty kmExamSpecialty ");
				queryStr.append("where kmExamSpecialty.nodeId like ? ");
				queryStr.append("and length(kmExamSpecialty.nodeId)=?");
				
				Query query = session.createQuery(queryStr.toString());
				query.setString(0, parentNodeId + '%');
				query.setInteger(1, length);
				return query.list();
			}
		};
		List list = (List) getHibernateTemplate().execute(callback);
		HashMap hm = new HashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			String nodeId = it.next().toString();
			hm.put(nodeId, nodeId);
		}
		// 防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
		if (hm.size() >= Integer.parseInt(KmExamSpecialtyConstants.NODEID_IF_MAXID)) {
			return minUsableNodeId;
		}
		long nodeIdVar = Long.valueOf(parentNodeId + KmExamSpecialtyConstants.NODEID_NOSON).longValue();
		while (null != hm.get(String.valueOf(nodeIdVar))) {
			nodeIdVar = nodeIdVar + 1;
		}
		return String.valueOf(nodeIdVar);
	}
	
	public List getChildNodes(final String parentNodeId) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamSpecialty kmExamSpecialty ");
				queryStr.append("where kmExamSpecialty.parentNodeId like ? ");
				queryStr.append("order by kmExamSpecialty.nodeId");
				
				Query query = session.createQuery(queryStr.toString());
				query.setString(0, parentNodeId + '%');
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

}