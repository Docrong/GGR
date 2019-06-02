package com.boco.eoms.sheet.ptnpretreatmentrule.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.ptnpretreatmentrule.dao.IPtnPretreatmentRuleDAO;
import com.boco.eoms.sheet.ptnpretreatmentrule.model.PtnPretreatmentRule;

public class PtnPretreatmentRuleDAOHibernate extends BaseSheetDaoHibernate implements IPtnPretreatmentRuleDAO {

	public Map getRuleListByCondition(final Integer pageSize, final Integer pageIndex, final String condition) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException {
				String sql = "from PtnPretreatmentRule";
				if(condition != null && condition.length() > 0){
					sql += condition;
				}
				sql += " order by faultReasonSort1,faultReasonSort2,faultReasonSort3";
				Map resultMap = new HashMap();
				String totalSql = "select count(*) " + sql;
				int total = ((Integer) session.createQuery(totalSql)
						.iterate().next()).intValue();
				Query query = session.createQuery(sql);
				query.setFirstResult(pageIndex.intValue() * pageSize.intValue());
				query.setMaxResults(pageSize.intValue());
				List queryList = query.list();
				resultMap.put("total", Integer.valueOf(total));
				resultMap.put("list", queryList);
				return resultMap;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public void saveObject(PtnPretreatmentRule obj) {
		if(obj.getId() != null){
			this.getHibernateTemplate().save(obj);
		}else{
			this.getHibernateTemplate().update(obj);
		}
	}

	public List getListByCondition(String condition) {
		String hql = "from PtnPretreatmentRule " + condition;
		return this.getHibernateTemplate().find(hql);
	}
	
}
