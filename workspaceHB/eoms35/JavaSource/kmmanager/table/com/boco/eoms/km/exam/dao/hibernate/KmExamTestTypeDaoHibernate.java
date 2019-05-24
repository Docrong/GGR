package com.boco.eoms.km.exam.dao.hibernate;

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
import com.boco.eoms.km.exam.dao.KmExamTestTypeDao;
import com.boco.eoms.km.exam.model.KmExamTestType;

/**
 * <p>
 * Title:题型信息表 dao的hibernate实现
 * </p>
 * <p>
 * Description:题型信息表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmExamTestTypeDaoHibernate extends BaseDaoHibernate implements KmExamTestTypeDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeDao#getKmExamTestTypes()
	 *      
	 */
	public List getKmExamTestTypes() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamTestType kmExamTestType";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 查询某试卷下的所有类型
	 * @param testID
	 * @return
	 */
	public List getKmExamTestTypesByTestID(final String testID) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamTestType kmExamTestType where kmExamTestType.testID=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, testID);
				List result = query.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.exam.KmExamTestTypeDao#getKmExamTestType(java.lang.String)
	 *
	 */
	public KmExamTestType getKmExamTestType(final String id) {
		KmExamTestType kmExamTestType = (KmExamTestType) getHibernateTemplate().get(KmExamTestType.class, id);
		if (kmExamTestType == null) {
			kmExamTestType = new KmExamTestType();
		}
		return kmExamTestType;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeDao#saveKmExamTestTypes(com.boco.eoms.km.exam.KmExamTestType)
	 *      
	 */
	public void saveKmExamTestType(final KmExamTestType kmExamTestType) {
		if ((kmExamTestType.getTestTypeId() == null) || (kmExamTestType.getTestTypeId().equals("")))
			getHibernateTemplate().save(kmExamTestType);
		else
			getHibernateTemplate().saveOrUpdate(kmExamTestType);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeDao#removeKmExamTestTypes(java.lang.String)
	 *      
	 */
    public void removeKmExamTestType(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer generalHql = new StringBuffer("delete from KmExamTestType kmExamTestType ");
				generalHql.append("where kmExamTestType.testTypeId=?");			
				Query query = session.createQuery(generalHql.toString());
				query.setString(0, id);
				query.executeUpdate();
				
				StringBuffer themeHql = new StringBuffer("delete from KmExamTestTypeContent kmTableColumnContent ");
				themeHql.append("where kmTableColumnContent.testTypeID=?");
				Query query3 = session.createQuery(themeHql.toString());
				query3.setString(0, id);
				int ret = query3.executeUpdate();
				
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmExamTestType kmExamTestType = this.getKmExamTestType(id);
		if(kmExamTestType==null){
			return "";
		}
		//TODO 请修改代码
		//return kmExamTestType.yourCode();
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeDao#getKmExamTestTypes(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExamTestTypes(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamTestType kmExamTestType ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmExamTestType.testTypeId) ");
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
	
}