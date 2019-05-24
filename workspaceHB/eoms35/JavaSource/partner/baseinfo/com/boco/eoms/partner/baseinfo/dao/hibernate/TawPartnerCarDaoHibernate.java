package com.boco.eoms.partner.baseinfo.dao.hibernate;

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
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.partner.baseinfo.dao.TawPartnerCarDao;
import com.boco.eoms.partner.baseinfo.model.TawPartnerCar;

/**
 * <p>
 * Title:车辆管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:车辆管理
 * </p>
 * <p>
 * Thu Feb 05 13:54:40 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawPartnerCarDaoHibernate extends BaseDaoHibernate implements TawPartnerCarDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.partner.TawPartnerCarDao#getTawPartnerCars()
	 *      
	 */
	public List getTawPartnerCars() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawPartnerCar tawPartnerCar";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.partner.TawPartnerCarDao#getTawPartnerCar(java.lang.String)
	 *
	 */
	public TawPartnerCar getTawPartnerCar(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawPartnerCar tawPartnerCar where tawPartnerCar.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawPartnerCar) result.iterator().next();
				} else {
					return new TawPartnerCar();
				}
			}
		};
		return (TawPartnerCar) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.partner.TawPartnerCarDao#saveTawPartnerCars(com.boco.eoms.partner.TawPartnerCar)
	 *      
	 */
	public void saveTawPartnerCar(final TawPartnerCar tawPartnerCar) {
		if ((tawPartnerCar.getId() == null) || (tawPartnerCar.getId().equals("")))
			getHibernateTemplate().save(tawPartnerCar);
		else
			this.getSession().merge(tawPartnerCar);
			//getHibernateTemplate().saveOrUpdate(tawPartnerCar);
	}

	/**
	 * 
	 * @see com.boco.eoms.partner.TawPartnerCarDao#removeTawPartnerCars(java.lang.String)
	 *      
	 */
    public void removeTawPartnerCar(final String id) {
		getHibernateTemplate().delete(getTawPartnerCar(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		TawPartnerCar tawPartnerCar = this.getTawPartnerCar(id);
		if(tawPartnerCar==null){
			return "";
		}
		//TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.partner.TawPartnerCarDao#getTawPartnerCars(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getTawPartnerCars(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawPartnerCar tawPartnerCar where 1=1";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	/**
	 * 判断车辆编号是否唯一
	 * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
	 *      
	 */
	public Boolean isunique(final String car_number) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawPartnerCar tawPartnerCar where tawPartnerCar.car_number=:car_number";
				Query query = session.createQuery(queryStr);
				query.setString("car_number", car_number);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return Boolean.valueOf(false);
				} else {
					return Boolean.valueOf(true);
				}
			}
		};
		return (Boolean) getHibernateTemplate().execute(callback);
	}
	/*
	 * name2Id，即字典id转为字典名称 added by fengshaohong
	 * 
	 * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
	 */
	public String name2Id(final String dictName,final String parentDictId) {
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
	/*
	 * getDictIdByParentId，即通过parentid得到dictid added by fengshaohong
	 * 
	 * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
	 */
	public String[] getDictIdByParentId(final String parentDictId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以角色ID为条件查询
				String queryStr = " from TawSystemDictType dictType where  dictType.parentDictId=:parentDictId";

				Query query = session.createQuery(queryStr);
				// 角色ID号
				query.setString("parentDictId", parentDictId);
				List list = query.list();
				TawSystemDictType dictType = null;
                String []arrayStr =new String[list.size()]; 
				for (int i =0;i<list.size();i++) {
					// 不为空则取dept
					dictType = (TawSystemDictType) list.get(i);
					arrayStr[i]=dictType.getDictId();
				}
//				} else {
//					// 为空，写入将部门名称设为未知联系人
//					dictType = new TawSystemDictType();
//					dictType.setDictName("");
//				}
				return arrayStr;
			}
		};

		 String []arrayStr ={};
		
		 arrayStr = (String[]) getHibernateTemplate().execute(
					callback);
		
		return arrayStr;
	}

}