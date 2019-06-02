package com.boco.eoms.km.expert.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.expert.dao.KmExpertBasicDao;
import com.boco.eoms.km.expert.model.KmExpertBasic;

/**
 * <p>
 * Title:基本信息 dao的hibernate实现
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertBasicDaoHibernate extends BaseDaoHibernate implements KmExpertBasicDao,
		ID2NameDAO {
	
	/**
	 *取基本信息列表
	 * @return 返回基本信息列表
	 */
	public List getKmExpertBasics() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("select kmExpertBasic from KmExpertBasic kmExpertBasic, TawSystemUser tawSystemUser");
				queryStr.append(" where kmExpertBasic.userId=tawSystemUser.userid and tawSystemUser.deleted='0'");
				Query query = session.createQuery(queryStr.toString());
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 根据主键查询基本信息
	 * @param id 主键
	 * @return 返回某id的基本信息
	 */
	public KmExpertBasic getKmExpertBasic(final String id) {
		KmExpertBasic kmExpertBasic = (KmExpertBasic) getHibernateTemplate().get(KmExpertBasic.class, id);
		if (kmExpertBasic == null) {
			kmExpertBasic = new KmExpertBasic();
		}
		return kmExpertBasic;
	}

	/**
	 * 根据用户ID查询基本信息
	 * @param userId 用户ID
	 * @return 返回某用户的基本信息
	 */
	public KmExpertBasic getKmExpertBasicByUserId(final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertBasic kmExpertBasic where kmExpertBasic.userId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, userId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmExpertBasic) result.iterator().next();
				} else {
					return new KmExpertBasic();
				}
			}
		};
		return (KmExpertBasic) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.expert.basic.KmExpertBasicDao#saveKmExpertBasics(com.boco.eoms.expert.basic.KmExpertBasic)
	 *      
	 */
	public void saveKmExpertBasic(final KmExpertBasic kmExpertBasic) {
		if ((kmExpertBasic.getId() == null) || (kmExpertBasic.getId().equals("")))
			getHibernateTemplate().save(kmExpertBasic);
		else
			getHibernateTemplate().saveOrUpdate(kmExpertBasic);
	}

	/**
	 * 
	 * @see com.boco.eoms.expert.basic.KmExpertBasicDao#removeKmExpertBasics(java.lang.String)
	 *      
	 */
    public void removeKmExpertBasic(final String id, final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {				
				// 1、删除对应的证书管理信息
				String query1Str = "delete from KmExpertCet kmExpertCet where kmExpertCet.userId=?";
				Query query1 = session.createQuery(query1Str);
				query1.setString(0, userId);
				query1.executeUpdate();
				
				// 2、删除对应的技术交流竞赛表彰信息
				String query2Str = "delete from KmExpertCom kmExpertCom where kmExpertCom.userId=?";
				Query query2 = session.createQuery(query2Str);
				query2.setString(0, userId);
				query2.executeUpdate();
				
				// 3、删除对应的教育背景信息
				String query3Str = "delete from KmExpertEdu kmExpertEdu where kmExpertEdu.userId=?";
				Query query3 = session.createQuery(query3Str);
				query3.setString(0, userId);
				query3.executeUpdate();
				
				// 4、删除对应的培训经历信息
				String query4Str = "delete from KmExpertTrain kmExpertTrain where kmExpertTrain.userId=?";
				Query query4 = session.createQuery(query4Str);
				query4.setString(0, userId);
				query4.executeUpdate();
				
				// 5、删除对应的工作经验信息
				String query5Str = "delete from KmExpertExp kmExpertExp where kmExpertExp.userId=?";
				Query query5 = session.createQuery(query5Str);
				query5.setString(0, userId);
				query5.executeUpdate();
				
				// 6、删除对应的专家基本信息
				String queryStr = "delete from KmExpertBasic kmExpertBasic where kmExpertBasic.id=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
				int ret = query.executeUpdate();
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
		KmExpertBasic kmExpertBasic = this.getKmExpertBasic(id);
		if(kmExpertBasic==null){
			return "";
		}
		//TODO 请修改代码
		return kmExpertBasic.getUserId();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.expert.basic.KmExpertBasicDao#getKmExpertBasics(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExpertBasics(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				StringBuffer queryCountStr = new StringBuffer("select count(kmExpertBasic.id) from KmExpertBasic kmExpertBasic, TawSystemUser tawSystemUser");
				StringBuffer queryStr = new StringBuffer("select kmExpertBasic from KmExpertBasic kmExpertBasic, TawSystemUser tawSystemUser");	
				if (whereStr != null && whereStr.length() > 0){					
					queryCountStr.append(whereStr);
					queryCountStr.append(" and kmExpertBasic.userId=tawSystemUser.userid and tawSystemUser.deleted='0'");					
					queryStr.append(whereStr);
					queryStr.append(" and kmExpertBasic.userId=tawSystemUser.userid and tawSystemUser.deleted='0'");
				}
				else{
					queryCountStr.append(" where kmExpertBasic.userId=tawSystemUser.userid and tawSystemUser.deleted='0'");
					queryStr.append(" where kmExpertBasic.userId=tawSystemUser.userid and tawSystemUser.deleted='0'");
				}
							
				int total = ((Integer) session.createQuery(queryCountStr.toString())
						.iterate().next()).intValue();

				List result = new ArrayList();
				if(total >0){
					queryStr.append(" order by kmExpertBasic.createTime desc");
					
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

	public Map getKmExpertBasics(final Integer curPage, final Integer pageSize,
			final  KmExpertBasic kmExpertBasic) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria crit = session.createCriteria(KmExpertBasic.class);
				Example ex = Example.create(kmExpertBasic);
				ex.excludeNone();
				ex.excludeZeroes();
//				ex.excludeProperty("specialty");

//				if(kmExpertBasic.getSpercialty() == null || kmExpertBasic.getSpercialty().equals("")){
//					ex.excludeProperty("specialty");
//				}

				ex.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
				crit.add(ex);

				int total = crit.list().size();
				crit.setFirstResult(pageSize.intValue()	* (curPage.intValue()));
				crit.setMaxResults(pageSize.intValue());
				crit.addOrder(Order.desc("createTime"));
				
				List result = crit.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}	
	
}