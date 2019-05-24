
package com.boco.eoms.businessupport.product.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.businessupport.product.dao.ILanguageSpecialLineDao;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.sheet.base.dao.hibernate.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LanguageSpecialLineDaoHibernate extends BaseSheetDaoHibernate implements ILanguageSpecialLineDao {

    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.ILanguageSpecialLineDao#getLanguageSpecialLines(com.boco.eoms.sheet.languagespecialline.model.LanguageSpecialLine)
     */
    public List getLanguageSpecialLines() {
        return getHibernateTemplate().find("from LanguageSpecialLine as nbp where nbp.deleted = 0");
      
    }   
  
    public List getLanguageSpecialLinesDeleted() {
        return getHibernateTemplate().find("from LanguageSpecialLine as nbp where nbp.deleted = 1");
      
    }

    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.ILanguageSpecialLineDao#getLanguageSpecialLine(String id)
     */
    public LanguageSpecialLine getLanguageSpecialLine(final String id) {
    		LanguageSpecialLine languagespecialline = (LanguageSpecialLine) getHibernateTemplate().get(LanguageSpecialLine.class, id);
         if (languagespecialline == null) {
            throw new ObjectRetrievalFailureException(LanguageSpecialLine.class, id);
        }
        return languagespecialline;
    }

    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.ILanguageSpecialLineDao#saveLanguageSpecialLine(LanguageSpecialLine languagespecialline)
     */    
    public void saveLanguageSpecialLine(final LanguageSpecialLine languagespecialline) {
        if ((languagespecialline.getId() == null) || (languagespecialline.getId().equals("")))
			getHibernateTemplate().save(languagespecialline);
		else
			getHibernateTemplate().saveOrUpdate(languagespecialline);
    }
    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.ILanguageSpecialLineDao#removeLanguageSpecialLine(String id)
     */
    public void removeLanguageSpecialLine(final String id) {
    	LanguageSpecialLine nbp = getLanguageSpecialLine(id);
    	//nbp.setDeleted(new Integer(1));
    	getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }
    
    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.ILanguageSpecialLineDao#removeLanguageSpecialLine(String id)
     */
    public void restoreLanguageSpecialLine(final String id) {
    	LanguageSpecialLine nbp = getLanguageSpecialLine(id);
    	//nbp.setDeleted(new Integer(0));
    	getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }
    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.LanguageSpecialLineDao#getLanguageSpecialLines(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getLanguageSpecialLines(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the languagespecialline
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from LanguageSpecialLine nbp";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr+"");
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.LanguageSpecialLineDao#getLanguageSpecialLines(final Integer curPage, final Integer pageSize)
     */    
    public Map getLanguageSpecialLines(final Integer curPage, final Integer pageSize) {
			return this.getLanguageSpecialLines(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.sheet.languagespecialline.dao.ILanguageSpecialLineDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from LanguageSpecialLine obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
	
    /**
	 * @see com.boco.eoms.sheet.languagespecialline.dao.ILanguageSpecialLineDao#getLanguageSpecialLinesByCondition(java.lang.String)
	 */
	public List getLanguageSpecialLinesByHql(String hql) {
		return getHibernateTemplate().find(hql);		
	}
	
	public List getSpecialLines(String id){
		String sql = "from LanguageSpecialLine gprsSpecialLine where gprsSpecialLine.gprsSpecialLine_Id ='"
		+ id + "'";
		return getHibernateTemplate().find(sql);
		
	}
	public Map getQueryLanguageSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr){
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStrItem=" where 1=1 and gprsSpecialLine.deleted<>1";
				String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
				Set keySet = queryMap.keySet();				
				Iterator it = keySet.iterator();
				if(hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
				  while (it.hasNext()) { 				 
					String key = (String)it.next();
					if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
						if(key.equals("creatTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.creatTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";		
						}else if(key.equals("creatTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.creatTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitStartDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.completeLimit>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitEndDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.completeLimit<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("changeTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.changeTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("changeTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.changeTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("cancelTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.cancelTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("cancelTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.cancelTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("endTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.endTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("endTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and gprsSpecialLine.endTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else{
							queryStrItem = queryStrItem+" and gprsSpecialLine."+key+" like"+" '%"+queryMap.get(key)+"%'";
						}
					}					 
				 }	
				}else{
					 while (it.hasNext()) {				 
							String key = (String)it.next();
							if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
								if(key.equals("creatTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.creatTime>='"+queryMap.get(key)+"'";		
								}else if(key.equals("creatTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.creatTime<='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitStartDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.completeLimit>='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitEndDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.completeLimit<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("changeTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.changeTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("changeTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.changeTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("cancelTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.cancelTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("cancelTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.cancelTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("endTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.endTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("endTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and gprsSpecialLine.endTime<='"+queryMap.get(key)+"'";                       
								}else{
									queryStrItem = queryStrItem+" and gprsSpecialLine."+key+" like"+" '%"+queryMap.get(key)+"%'";
								}
							}					 
						 }	
				}

				String queryStr = "from LanguageSpecialLine gprsSpecialLine "+queryStrItem;
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
	public void saveOrUpdate(LanguageSpecialLine languagespecialline) throws HibernateException{
        		getHibernateTemplate().saveOrUpdate(languagespecialline);
        		getHibernateTemplate().flush();
            	getHibernateTemplate().clear();
		}
	/**
	 * 通过A端站点名称和Z端站点名称查找电路
	 * @param siteNameA
	 * @param siteNameZ
	 * @return
	 */
	public List getSpecialLinesBySiteName(String siteNameA,String siteNameZ) throws Exception{
		String sql = "from LanguageSpecialLine gprsSpecialLine where gprsSpecialLine.siteNameA ='"	+ siteNameA + "' and gprsSpecialLine.siteNameZ='"+siteNameZ+"'";
		return getHibernateTemplate().find(sql);		
	}
	/**
	 * 通过Z端业务设备端口查找电路
	 */
	public List getSpecialLineByZPort(String portZBDeviceName,String portZBDevicePort) throws Exception{
		String sql = "from LanguageSpecialLine t where t.portZBDevicePort ='" + portZBDevicePort + "' and t.portZBDeviceName='"+portZBDeviceName+"'";
		return getHibernateTemplate().find(sql);
	}
}
