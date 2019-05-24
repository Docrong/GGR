package com.boco.eoms.km.rule.dao.hibernate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.rule.dao.KmRuleDao;
import com.boco.eoms.km.rule.model.KmRule;
import com.boco.eoms.km.table.model.KmTableColumn;

/**
 * <p>
 * Title:规则库 dao的hibernate实现
 * </p>
 * <p>
 * Description:规则库
 * </p>
 * <p>
 * Fri Apr 17 16:06:45 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmRuleDaoHibernate extends BaseDaoHibernate implements KmRuleDao,
		ID2NameDAO {

	/**
	 * 
	 * @see com.boco.eoms.km.rule.KmRuleDao#getKmRules()
	 * 
	 */
	public List getKmRules() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmRule kmRule";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.rule.KmRuleDao#getKmRule(java.lang.String)
	 * 
	 */
	public KmRule getKmRule(final String id) {
		KmRule kmRule = (KmRule) getHibernateTemplate().get(KmRule.class, id);
		if (kmRule == null) {
			kmRule = new KmRule();
		}
		return kmRule;
	}

	/**
	 * 
	 * @see com.boco.eoms.km.rule.KmRuleDao#saveKmRules(com.boco.eoms.km.rule.KmRule)
	 * 
	 */
	public void saveKmRule(final KmRule kmRule) {
		if ((kmRule.getId() == null) || (kmRule.getId().equals("")))
			getHibernateTemplate().save(kmRule);
		else
			getHibernateTemplate().saveOrUpdate(kmRule);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.rule.KmRuleDao#removeKmRules(java.lang.String)
	 * 
	 */
	public void removeKmRule(final String id) {
		getHibernateTemplate().delete(getKmRule(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 * 
	 */
	public String id2Name(String id) throws DictDAOException {
		KmRule kmRule = this.getKmRule(id);
		if (kmRule == null) {
			return "";
		}
		// TODO 请修改代码
		// return kmRule.yourCode();
		return "";
	}

	/**
	 * 
	 * @see com.boco.eoms.km.rule.KmRuleDao#getKmRules(java.lang.Integer,java.lang.Integer,java.lang.String)
	 * 
	 */
	public Map getKmRules(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmRule kmRule";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = new ArrayList();
				if(total >0){
					Query query = session.createQuery(queryStr);
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

	public List getNextLevelKmRules(final String parentNodeId) {
		String hql = " from KmTableGeneral kmTableGeneral where kmTableGeneral.isDeleted='0'";
		hql += " order by kmTableGeneral.createTime desc";
		return getHibernateTemplate().find(hql);
	}

	// public String checkSql(String sql){
	// Session s=this.getSessionFactory().getCurrentSession();
	// String msg="测试成功!!!";
	// try {
	// sql="select * from KM_CONTENTS_1240469055468 where CREATE_USER = ? and
	// CREATE_DEPT != ?";
	// PreparedStatement prepareStatement= s.connection().prepareStatement(sql);
	// prepareStatement.setString(1,"admin");
	// prepareStatement.setString(2,"dept");
	// ResultSet rs=prepareStatement.executeQuery();
	// while(rs.next()){
	// System.out.println(rs.getString("CREATE_USER"));
	// }
	// return msg;
	// } catch (HibernateException e) {
	// msg="测试不成功,请仔细检查!";
	// e.printStackTrace();
	// } catch (SQLException e) {
	// msg="测试不成功,请仔细检查!";
	// e.printStackTrace();
	// }
	// return msg;
	// }

	public String checkSql(String sql) {
		Session s = this.getSessionFactory().getCurrentSession();
		String msg = "测试成功!!!";
		try {
			s.connection().prepareStatement(sql).execute();
		} catch (HibernateException e) {
			msg = "测试不成功,请仔细检查!";
			// e.printStackTrace();
		} catch (SQLException e) {
			msg = "测试不成功,请仔细检查!";
			// e.printStackTrace();
		}
		return msg;
	}
}