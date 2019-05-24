package com.boco.eoms.partner.baseinfo.dao.hibernate;

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
import com.boco.eoms.partner.baseinfo.dao.AreaDeptTreeDao;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.util.AreaDeptTreeConstants;


/**
 * <p>
 * Title:地域部门树 dao的hibernate实现
 * </p>
 * <p>
 * Description:地域部门树
 * </p>
 * <p>
 * Fri Feb 06 11:46:50 CST 2009
 * </p>
 * 
 * @author liujinlong
 * @version 3.5
 * 
 */
public class AreaDeptTreeDaoHibernate extends BaseDaoHibernate implements AreaDeptTreeDao,
		ID2NameDAO {
    
    /**
	 *
	 * @see com.boco.eoms.partner.baseinfo.AreaDeptTreeDao#getAreaDeptTree(java.lang.String)
	 *
	 */
	public AreaDeptTree getAreaDeptTree(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AreaDeptTree areaDeptTree where areaDeptTree.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (AreaDeptTree) result.iterator().next();
				} else {
					return new AreaDeptTree();
				}
			}
		};
		return (AreaDeptTree) getHibernateTemplate().execute(callback);
	}
    
	/**
	 * 
	 * @see com.boco.eoms.partner.baseinfo.AreaDeptTreeDao#saveAreaDeptTrees(com.boco.eoms.partner.baseinfo.AreaDeptTree)
	 *      
	 */
	public void saveAreaDeptTree(final AreaDeptTree areaDeptTree) {
		if ((areaDeptTree.getId() == null) || (areaDeptTree.getId().equals("")))
			getHibernateTemplate().save(areaDeptTree);
		else
			getHibernateTemplate().saveOrUpdate(areaDeptTree);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(final String id) {
		AreaDeptTree areaDeptTree = this.getAreaDeptTreeByNodeId(id);
		if(areaDeptTree==null){
			return "";
		}
		//TODO 请修改代码
		return areaDeptTree.getNodeName();
	}
    
    public AreaDeptTree getAreaDeptTreeByNodeId(final String nodeId) {
    	AreaDeptTree areaDeptTree = new AreaDeptTree();
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeId='" + nodeId + "'";
		List list = getHibernateTemplate().find(hql);
		if (list.iterator().hasNext()) {
			areaDeptTree = (AreaDeptTree) list.iterator().next();
		}
		return areaDeptTree;
    }
    
    public void removeAreaDeptTreeByNodeId(final String nodeId) {
    	final String hql = "delete from AreaDeptTree areaDeptTree where areaDeptTree.nodeId like '"
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
    
    
    public List getNextLevelAreaDeptTrees(final String parentNodeId) {
    	String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.parentNodeId='"
				+ parentNodeId + "'";
		hql += " order by areaDeptTree.nodeId";
		return getHibernateTemplate().find(hql);
    }
    
    //09.03.12 加方法 不同用户的人可以看到不同的节点,当登录用户是地市用户时执行这个方法
    public List getNextLevelAreaDeptTrees(final String parentNodeId,String areaNames) {
    	String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.parentNodeId='"
				+ parentNodeId + "' and nodeName in ("+ areaNames +")";
		hql += " order by areaDeptTree.nodeId";
		return getHibernateTemplate().find(hql);
    }
    
    public List getAreaDeptTreesByType(final String nodeType) {
    	String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeType='"
				+ nodeType + "'";
		hql += " order by areaDeptTree.nodeId";
		return getHibernateTemplate().find(hql);
    }
    public String getUsableNodeId(final String parentNodeId, final int length) {
    	String minNodeId = getUsableMinNodeId(parentNodeId, length);
		if (null == minNodeId || "".equals(minNodeId)) {
			minNodeId = AreaDeptTreeConstants.NODEID_DEFAULT_VALUE;
		}
		return minNodeId;
    }
    
    private String getUsableMinNodeId(final String parentNodeId, final int len) {
		String minUsableNodeId = "";
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql = " select distinct(nodeId) from AreaDeptTree areaDeptTree where areaDeptTree.nodeId like '"
						+ parentNodeId + "%' and length(areaDeptTree.nodeId)='" + len + "'";
				Query query = session.createQuery(hql);
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
		if (hm.size() >= Integer.parseInt(AreaDeptTreeConstants.NODEID_IF_MAXID)) {
			return minUsableNodeId;
		}
		long nodeIdVar = Long.valueOf(parentNodeId + AreaDeptTreeConstants.NODEID_NOSON)
				.longValue();
		while (null != hm.get(String.valueOf(nodeIdVar))) {
			nodeIdVar = nodeIdVar + 1;
		}
		return String.valueOf(nodeIdVar);
	}
	
	public List getChildNodes(final String parentNodeId) {
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.parentNodeId like '"
				+ parentNodeId + "%'";
		hql += " order by areaDeptTree.nodeId";
		return (List) getHibernateTemplate().find(hql);
	}
	
	//由节点id，得到叶子节点
	public List getLeavesByNodeId(final String nodeId,String nodeType){
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeId like '"
			+ nodeId + "%' and leaf=1 ";
		if(nodeType!=null&&!nodeType.equals("")){
			hql += " and nodeType = '"+ nodeType +"'" ;
		}
	    hql += " order by areaDeptTree.nodeId";
	    return (List) getHibernateTemplate().find(hql);
	}
	
	//根据父节点parentNodeId和节点类型nodeType得到某个省、某个地市、某个厂商下的节点（人力信息、仪器仪表、车辆管理、油机管理）
	public List getChildLeafNodes(final String parentNodeId,String nodeType) {
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.parentNodeId like '"
				+ parentNodeId + "%' and leaf=1 and nodeType ='"+nodeType+"'" ;
		return (List) getHibernateTemplate().find(hql);
		 
	}
	//由节点名得到节点ID 用于根据地市名查询地市nodeId，用于批量导入
	public String getNodeIdByNodeName(String name){
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeName = '"
			+ name +"'";
		List list = (List) getHibernateTemplate().find(hql);
		if(list.size()>0){
			AreaDeptTree areaDeptTree = (AreaDeptTree)list.get(0);
			return areaDeptTree.getNodeId();
		}
		else return null;
	}
	//由父节点ID和节点名得到节点Id，用于根据地市nodeId和厂商名查询厂商nodeId，用于批量导入
	public String getNodeIdByParentAndName(String parentNodeId,String name){
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeName = '"
			+ name +"' and areaDeptTree.parentNodeId = '" + parentNodeId + "'";
		List list = (List) getHibernateTemplate().find(hql);
		if(list.size()>0){
			AreaDeptTree areaDeptTree = (AreaDeptTree)list.get(0);
			return areaDeptTree.getNodeId();
		}
		else return null;
	}
	
	//由地市名、厂商名得到叶子节点的nodeId（人力信息、车辆管理、仪器仪表、油机管理）
	public String getLeafNodeIdByNames(String areaName,String factoryName,String type){
		String areaNodeId = this.getNodeIdByNodeName(areaName);//得到地市nodeId
		String facNodeId = this.getNodeIdByParentAndName(areaNodeId, factoryName);//得到厂商nodeId
		String hql = " from AreaDeptTree areaDeptTree where nodeType = '"+ type +"' and areaDeptTree.parentNodeId = '" + facNodeId + "'";
		List list = (List) getHibernateTemplate().find(hql);
		if(list.size()>0){
			AreaDeptTree areaDeptTree = (AreaDeptTree)list.get(0);
			return areaDeptTree.getNodeId();
		}
		else return null;
	}
	
	//由省名得到省下全部地市
	public List getAreaByProvince(String provinceName){
		if(provinceName!=null&&!provinceName.equals("")){
			String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeType = 'area'" +
				" and areaDeptTree.parentNodeId in (select nodeId from AreaDeptTree where nodeName = '"+provinceName+"')";
			return (List) getHibernateTemplate().find(hql);
		}
		else {//省名为空则得到全部地市
			String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeType = 'area'" ;
		    return (List) getHibernateTemplate().find(hql);
		}
	}
	
	//返回查询条件得到的结果
	public List getInfoByCondition(String sql){
		String hql = sql;
		List list = (List) getHibernateTemplate().find(hql);
		return list;
	}
	
	
	//------------------------------用于统计报表-----------------
	
	
	//由省名得到省下全部合作伙伴名称，不重复。
	public List getDeptByDeptName(String provinceName){
		if(provinceName!=null&&!provinceName.equals("")){
			String hql = " select distinct(nodeName) from AreaDeptTree areaDeptTree where areaDeptTree.nodeType = 'factory'  " +
				" and areaDeptTree.parentNodeId in (select nodeId from AreaDeptTree where nodeType = 'area' and parentNodeId in (select nodeId from AreaDeptTree where nodeName = '"+provinceName+"'))";
			return (List) getHibernateTemplate().find(hql);
		}
		else {//省名为空则得到所有地市下的合作伙伴
			String hql = " select distinct(nodeName) from AreaDeptTree areaDeptTree where areaDeptTree.nodeType = 'factory'" +
			" and areaDeptTree.parentNodeId in (select nodeId from AreaDeptTree where nodeType = 'area')" ;
		    return (List) getHibernateTemplate().find(hql);
		}
	}
	//得到省的列表
	public List getProvinceNodes() {
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeType = 'province'";
		return (List) getHibernateTemplate().find(hql);
		 
	}
	//得到一列地市名下的树节点集合
	public List getNodesByAreas(String areaNames){
		String hql = " from AreaDeptTree areaDeptTree where areaDeptTree.nodeName in (" + areaNames + ")";
		return (List) getHibernateTemplate().find(hql);
	}

}