package com.boco.eoms.km.table.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.table.dao.KmTableColumnDao;
import com.boco.eoms.km.table.model.KmTableColumn;

/**
 * <p> Title:模型字段定义表 dao的hibernate实现 </p>
 * <p> Description:模型字段表 </p>
 * <p> Mon Mar 02 14:55:43 CST 2009 </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
public class KmTableColumnDaoHibernate extends BaseDaoHibernate implements
		KmTableColumnDao, ID2NameDAO {

	/**
	 * 取模型字段定义表列表
	 * 
	 * @return 返回模型字段定义表列表
	 */
	public List getKmTableColumns() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmTableColumn kmTableColumn";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 根据主键查询模型字段定义表
	 * 
	 * @param id 主键
	 * @return 返回某id的模型字段定义表
	 */
	public KmTableColumn getKmTableColumn(final String id) {
		KmTableColumn kmTableColumn = (KmTableColumn) getHibernateTemplate().get(KmTableColumn.class, id);
		if (kmTableColumn == null) {
			kmTableColumn = new KmTableColumn();
		}
		return kmTableColumn;
	}

	/**
	 * 保存模型字段定义表
	 * 
	 * @param kmTableColumn 模型字段定义表
	 */
	public void saveKmTableColumn(final KmTableColumn kmTableColumn) {
		if ((kmTableColumn.getId() == null)
				|| (kmTableColumn.getId().equals(""))) {
			// 1：保存模型字段定义
			getHibernateTemplate().save(kmTableColumn);
			// 2：如果当前字段类型是字典，并且有子节点与之关联进行字典联动功能
			if (kmTableColumn.getColType().equals("5") && 
					kmTableColumn.getColDictType().equals("1")) {
				String subNode = kmTableColumn.getSubNode();
				if (subNode != null && !subNode.equals("")) {
					// 将父字典与子字典绑定
					this.upSubColumnsParentNode(subNode, kmTableColumn.getColName());
				}
			}
		} else {
			// 1：更新模型字段定义
			getHibernateTemplate().saveOrUpdate(kmTableColumn);
			// 2：解除原来与当前字段管理的子字段
			upSubColumnsParentNodeIsNull(kmTableColumn.getColName());
			// 3：如果当前字段类型是字典，并且有子节点与之关联进行字典联动功能
			if (kmTableColumn.getColType().equals("5") && 
					kmTableColumn.getColDictType().equals("1")) {
				String subNode = kmTableColumn.getSubNode();
				if (subNode != null && !subNode.equals("")) {
					// 解除父字典与子字典的绑定
					this.upSubColumnsParentNode(subNode, kmTableColumn.getColName());
				}
			}
		}
	}

	/**
	 * 根据id删除模型字段定义表
	 * 
	 * @param id 主键
	 */
	public void removeKmTableColumn(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlUpdate = "update KmTableColumn kmTableColumn set kmTableColumn.isDeleted='1' where kmTableColumn.id=?";
				Query query = session.createQuery(hqlUpdate);
				query.setString(0, id);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}

	/**
	 * 分页取列表
	 * 
	 * @param curPage 当前页
	 * @param pageSize 每页显示条数
	 * @param whereStr where条件
	 * @return map中total为条数,result(list) curPage页的记录
	 */
	public Map getKmTableColumns(final Integer curPage, final Integer pageSize, final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {				
				StringBuffer queryStr = new StringBuffer("from KmTableColumn kmTableColumn ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmTableColumn.id) ");
				queryCountStr.append(queryStr);
				
				int total = ((Integer) session.createQuery(queryCountStr.toString())
						.iterate().next()).intValue();
				
				List result = new ArrayList();
				if(total >0){
					Query query = session.createQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					result = query.list();					
				}
				
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

    /**
     * id转name
     * 
     * @param id 一般为表中的主键
     * @return 返回主键对应的name(自定义)
     * @throws DictDAOException
     */
	public String id2Name(String id) throws DictDAOException {
		if(id == null || id.equals("")){
			return "";
		}		
		KmTableColumn kmTableColumn = this.getKmTableColumn(id);
		if (kmTableColumn == null) {
			return "";
		}
		return kmTableColumn.getColChname();
	}

	/**
	 * 根据模型主键查询某模型的所有有效字段
	 * 
	 * @param id 模型主键
	 * @return 返回某id的对象
	 * @author zhangxb
	 */
	public List getKmTableColumnsByTableId(final String tableId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmTableColumn kmTableColumn ");
				queryStr.append("where kmTableColumn.tableId=? ");
				queryStr.append("and kmTableColumn.isVisibl='1' ");
				queryStr.append("and kmTableColumn.isDeleted='0' ");
				queryStr.append("order by kmTableColumn.orderBy" );
				
				Query query = session.createQuery(queryStr.toString());
				query.setString(0, tableId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 将父字典与子字典绑定
	 * 
	 * @param subColName 子字段的名称
	 * @param parentNode 父字段的名称
	 * @author zhangxb
	 */
	public void upSubColumnsParentNode(final String subColName, final String parentNode) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("update KmTableColumn kmTableColumn ");
				queryStr.append("set kmTableColumn.parentNode=? ");
				queryStr.append("where kmTableColumn.colName=?");
				
				Query query = session.createQuery(queryStr.toString());
				query.setString(0, parentNode);
				query.setString(1, subColName);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}

	/**
	 * 解除父字典与子字典的绑定
	 * 
	 * @param parentNode 父字段的名称
	 * @author zhangxb
	 */
	public void upSubColumnsParentNodeIsNull(final String parentNode) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("update KmTableColumn kmTableColumn ");
				queryStr.append("set kmTableColumn.parentNode='' ");
				queryStr.append("where kmTableColumn.parentNode=?");
				
				Query query = session.createQuery(queryStr.toString());
				query.setString(0, parentNode);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
}