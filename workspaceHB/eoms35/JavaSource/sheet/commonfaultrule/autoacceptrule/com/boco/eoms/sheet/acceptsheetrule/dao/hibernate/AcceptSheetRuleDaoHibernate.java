package com.boco.eoms.sheet.acceptsheetrule.dao.hibernate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.sheet.acceptsheetrule.dao.AcceptSheetRuleDao;
import com.boco.eoms.sheet.acceptsheetrule.model.AcceptSheetRule;

/**
 * <p>
 * Title:自动接单规则配置 dao的hibernate实现
 * </p>
 * <p>
 * Description:自动接单规则配置
 * </p>
 * <p>
 * Wed Apr 22 09:19:35 CST 2009
 * </p>
 * 
 * @author 史闯科
 * @version 3.5
 * 
 */
public class AcceptSheetRuleDaoHibernate extends BaseDaoHibernate implements AcceptSheetRuleDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.sheet.acceptsheetrule.AcceptSheetRuleDao#getAcceptSheetRules()
	 *      
	 */
	public List getAcceptSheetRules() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AcceptSheetRule acceptSheetRule where acceptSheetRule.id=:id and acceptSheetRule.deleted = 0";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.sheet.acceptsheetrule.AcceptSheetRuleDao#getAcceptSheetRule(java.lang.String)
	 *
	 */
	public AcceptSheetRule getAcceptSheetRule(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AcceptSheetRule acceptSheetRule where acceptSheetRule.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (AcceptSheetRule) result.iterator().next();
				} else {
					return new AcceptSheetRule();
				}
			}
		};
		return (AcceptSheetRule) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.sheet.acceptsheetrule.AcceptSheetRuleDao#saveAcceptSheetRules(com.boco.eoms.sheet.acceptsheetrule.AcceptSheetRule)
	 *      
	 */
	public void saveAcceptSheetRule(final AcceptSheetRule acceptSheetRule) {
		if ((acceptSheetRule.getId() == null) || (acceptSheetRule.getId().equals("")))
			getHibernateTemplate().save(acceptSheetRule);
		else
			getHibernateTemplate().saveOrUpdate(acceptSheetRule);
	}

	/**
	 * 
	 * @see com.boco.eoms.sheet.acceptsheetrule.AcceptSheetRuleDao#removeAcceptSheetRules(java.lang.String)
	 *      
	 */
    public void removeAcceptSheetRule(final String id) {
		getHibernateTemplate().delete(getAcceptSheetRule(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		AcceptSheetRule acceptSheetRule = this.getAcceptSheetRule(id);
		if(acceptSheetRule==null){
			return "";
		}
		//TODO 请修改代码
		return acceptSheetRule.getId();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.sheet.acceptsheetrule.AcceptSheetRuleDao#getAcceptSheetRules(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getAcceptSheetRules(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AcceptSheetRule acceptSheetRule where 1=1 ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr ;
				queryStr += " and acceptSheetRule.deleted = 0 ";
				String queryCountStr = "select count(*) " + queryStr ;

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
	 * 根据子角色查询相应的人员
	 * @param area
	 * @param one TODO
	 * @param two TODO
	 * @param three TODO
	 * @param factory TODO
	 */
   public String getDealHumanByFilter(String area, String one, String two, String three, String factory)throws HibernateException{
	   
	   String queryStr = "from AcceptSheetRule as acceptSheetRule where (acceptSheetRule.faultArea='"+area+"' or acceptSheetRule.faultArea is null or acceptSheetRule.faultArea='')"
		            +" and (acceptSheetRule.netSortOne='"+one+"' or acceptSheetRule.netSortOne is null or acceptSheetRule.netSortOne='')"
		            +" and (acceptSheetRule.netSortTwo='"+two+"' or acceptSheetRule.netSortTwo is null or acceptSheetRule.netSortTwo='')"
		            +" and (acceptSheetRule.netSortThree='"+three+"' or acceptSheetRule.netSortThree is null or acceptSheetRule.netSortThree='')"
		            +" and (acceptSheetRule.equipmentFactory='"+factory+"' or acceptSheetRule.equipmentFactory is null or acceptSheetRule.equipmentFactory='')"
		            +" and (acceptSheetRule.dealHuman is not null and acceptSheetRule.dealHuman<>'')"
					+" and deleted=0 ";
	   List list = getHibernateTemplate().find(queryStr);
	   HashMap map = new HashMap();
		map.put("count", "0");
		map.put("subrole", null);
	   for (int j = 0; list != null && j < list.size(); j++) {
			int count = 0;
			AcceptSheetRule rule = (AcceptSheetRule) list.get(j);
			
			if(rule.getFaultArea()!=null && !rule.getFaultArea().equals("") &&!rule.getFaultArea().equals("null")){
				count += 1;
			}
			if(rule.getNetSortOne()!=null && !rule.getNetSortOne().equals("") &&!rule.getNetSortOne().equals("null")){
				count += 1;
			}
			if(rule.getNetSortTwo()!=null && !rule.getNetSortTwo().equals("") &&!rule.getNetSortTwo().equals("null")){
				count += 1;
			}
			if(rule.getNetSortThree()!=null && !rule.getNetSortThree().equals("") &&!rule.getNetSortThree().equals("null")){
				count += 1;
			}
			if(rule.getEquipmentFactory()!=null && !rule.getEquipmentFactory().equals("") &&!rule.getEquipmentFactory().equals("null")){
				count += 1;
			}
			int tempCount = StaticMethod.nullObject2int(map.get("count"));
			if(count>tempCount || (count==0 && tempCount ==0)){
				map.put("count", ""+count+"");
				map.put("rule", rule);
			}
		}
	   AcceptSheetRule acceptSheetRule = (AcceptSheetRule)map.get("rule"); 
	   if(acceptSheetRule.getDealHuman()!=null && !acceptSheetRule.getDealHuman().equals("")){
		   return acceptSheetRule.getDealHuman();
	   }
	 return "";
	   
   }
}