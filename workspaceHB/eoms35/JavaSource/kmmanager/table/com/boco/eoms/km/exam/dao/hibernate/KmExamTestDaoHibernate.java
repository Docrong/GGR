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
import com.boco.eoms.km.exam.dao.KmExamTestDao;
import com.boco.eoms.km.exam.model.KmExamTest;

/**
 * <p>
 * Title:试卷 dao的hibernate实现
 * </p>
 * <p>
 * Description:试卷
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmExamTestDaoHibernate extends BaseDaoHibernate implements KmExamTestDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestDao#getKmExamTests()
	 *      
	 */
	public List getKmExamTests() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamTest kmExamTest where kmExamTest.isDeleted=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, "0");
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 得到发布的普通考试试题
	 * @return
	 */
	public List getKmExamPublicTests(){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamTest kmExamTest where kmExamTest.isDeleted=? and kmExamTest.isPublic=? and kmExamTest.isPermittedOvertime=? order by kmExamTest.createTime desc";
				Query query = session.createQuery(queryStr);
				query.setString(0, "0");
				query.setString(1, "1");
				query.setString(2, "0");
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}

	/**
	 * 得到发布的随机考试试卷
	 * @return
	 */
	public List getKmExamRandomTests(){
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException{
				String queryStr = "from KmExamTest kmExamTest where kmExamTest.isDeleted= ? and kmExamTest.isPublic = ? and kmExamTest.isPermittedOvertime = ?";
				Query query = session.createQuery(queryStr);
				query.setString(0, "0");
				query.setString(1, "1");
				query.setString(2, "1");
				return query.list();
			}
		};
		return (List)getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.exam.KmExamTestDao#getKmExamTest(java.lang.String)
	 *
	 */
	public KmExamTest getKmExamTest(final String id) {
		KmExamTest kmExamTest = (KmExamTest) getHibernateTemplate().get(KmExamTest.class, id);
		if (kmExamTest == null) {
			kmExamTest = new KmExamTest();
		}
		return kmExamTest;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestDao#saveKmExamTests(com.boco.eoms.km.exam.KmExamTest)
	 *      
	 */
	public void saveKmExamTest(final KmExamTest kmExamTest) {
		if ((kmExamTest.getTestID() == null) || (kmExamTest.getTestID().equals("")))
			getHibernateTemplate().save(kmExamTest);
		else
			getHibernateTemplate().saveOrUpdate(kmExamTest);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestDao#removeKmExamTests(java.lang.String)
	 *      
	 */
    public void removeKmExamTest(final String id) {    
		HibernateCallback callback = new HibernateCallback() {
		public Object doInHibernate(Session session)
				throws HibernateException {
			String hql = "update KmExamTest kmExamTest set kmExamTest.isDeleted=1 where kmExamTest.testID=?";    
			Query query = session.createQuery(hql);
			query.setString(0, id);
			query.executeUpdate();				
			return null;
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
		KmExamTest kmExamTest = this.getKmExamTest(id);
		if(kmExamTest==null){
			return "";
		}
		//TODO 请修改代码
		return kmExamTest.getTestName();		
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestDao#getKmExamTests(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExamTests(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamTest kmExamTest ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);
				else
					queryStr.append(" where kmExamTest.isDeleted='0' ");

				StringBuffer queryCountStr = new StringBuffer("select count(kmExamTest.testID) ");
				queryCountStr.append(queryStr);
				
				int total = ((Integer) session.createQuery(queryCountStr.toString())
						.iterate().next()).intValue();
				
				List result = new ArrayList();
				if(total >0){
					queryStr.append(" order by kmExamTest.createTime desc");
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