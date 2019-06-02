package com.boco.eoms.km.file.dao.hibernate;

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
import com.boco.eoms.km.file.model.KmFileTree;
import com.boco.eoms.km.file.dao.KmFileTreeDao;
import com.boco.eoms.km.file.util.KmFileTreeConstants;


/**
 * <p>
 * Title:文件管理树 dao的hibernate实现
 * </p>
 * <p>
 * Description:文件管理树
 * </p>
 * <p>
 * Wed Mar 25 17:09:37 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public class KmFileTreeDaoHibernate extends BaseDaoHibernate implements KmFileTreeDao,
		ID2NameDAO {
    
    /**
	 *
	 * @see com.boco.eoms.km.file.KmFileTreeDao#getKmFileTree(java.lang.String)
	 *
	 */
	public KmFileTree getKmFileTree(final String id) {
		KmFileTree kmFileTree = (KmFileTree) getHibernateTemplate().get(KmFileTree.class, id);
		if (kmFileTree == null) {
			kmFileTree = new KmFileTree();
		}
		return kmFileTree;
	}
    
	/**
	 * 
	 * @see com.boco.eoms.km.file.KmFileTreeDao#saveKmFileTrees(com.boco.eoms.km.file.KmFileTree)
	 *      
	 */
	public void saveKmFileTree(final KmFileTree kmFileTree) {
		if ((kmFileTree.getId() == null) || (kmFileTree.getId().equals("")))
			getHibernateTemplate().save(kmFileTree);
		else
			getHibernateTemplate().saveOrUpdate(kmFileTree);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		String themeName = "";
		if(id != null && !id.equals("")){
			KmFileTree kmFileTree = this.getKmFileTreeByNodeId(id);
			if(kmFileTree==null){
				return "";
			}
			else{
				themeName = kmFileTree.getNodeName();
			}

			while(kmFileTree.getParentNodeId().length() >= 3){
				kmFileTree = this.getKmFileTreeByNodeId(kmFileTree.getParentNodeId());
				 if(kmFileTree==null){
					 return themeName;
				 }
				 else{
					 themeName = kmFileTree.getNodeName() + " -> " + themeName;
				 }
			}
		}
		return themeName;
	}
    
	public KmFileTree getKmFileTreeByNodeId(final String nodeId) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmFileTree kmFileTree where kmFileTree.nodeId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, nodeId);
				List list = query.list();
				if (list.iterator().hasNext()) {
					return (KmFileTree) list.iterator().next();
				}
				return new KmFileTree();
			}
		};
		return (KmFileTree) getHibernateTemplate().execute(callback);
    }
    
    public void removeKmFileTreeByNodeId(final String nodeId) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlUpdate = "delete from KmFileTree kmFileTree where kmFileTree.nodeId like ?";
				Query query = session.createQuery(hqlUpdate);
				query.setString(0, nodeId + '%');
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
    }
    
	public List getNextLevelKmFileTrees(final String parentNodeId) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmFileTree kmFileTree where kmFileTree.parentNodeId=? order by kmFileTree.nodeId";
				Query query = session.createQuery(queryStr);
				query.setString(0, parentNodeId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
    }
    
    public String getUsableNodeId(final String parentNodeId, final int length) {
    	String minNodeId = getUsableMinNodeId(parentNodeId, length);
		if (null == minNodeId || "".equals(minNodeId)) {
			minNodeId = KmFileTreeConstants.NODEID_DEFAULT_VALUE;
		}
		return minNodeId;
    }
    
	private String getUsableMinNodeId(final String parentNodeId, final int length) {
		String minUsableNodeId = "";
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("select distinct(nodeId) ");
				queryStr.append("from KmFileTree kmFileTree ");
				queryStr.append("where kmFileTree.nodeId like ? ");
				queryStr.append("and length(kmFileTree.nodeId)=?");
				
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
		if (hm.size() >= Integer.parseInt(KmFileTreeConstants.NODEID_IF_MAXID)) {
			return minUsableNodeId;
		}
		long nodeIdVar = Long.valueOf(parentNodeId + KmFileTreeConstants.NODEID_NOSON).longValue();
		while (null != hm.get(String.valueOf(nodeIdVar))) {
			nodeIdVar = nodeIdVar + 1;
		}
		return String.valueOf(nodeIdVar);
	}
	
	public List getChildNodes(final String parentNodeId) {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmFileTree kmFileTree where kmFileTree.parentNodeId like ? order by kmFileTree.nodeId";
				Query query = session.createQuery(queryStr);
				query.setString(0, parentNodeId + '%');
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

}