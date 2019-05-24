
package com.boco.eoms.businessupport.product.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.businessupport.product.dao.IIPSpecialLineDao;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.sheet.base.dao.hibernate.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class IPSpecialLineDaoHibernate extends BaseSheetDaoHibernate implements IIPSpecialLineDao {

    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#getIPSpecialLines(com.boco.eoms.sheet.ipspecialline.model.IPSpecialLine)
     */
    public List getIPSpecialLines() {
        return getHibernateTemplate().find("from IPSpecialLine as ip where ip.deleted = 0");
      
    }   
  
    public List getIPSpecialLinesDeleted() {
        return getHibernateTemplate().find("from IPSpecialLine as ip where ip.deleted = 1");
      
    }

    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#getIPSpecialLine(String id)
     */
    public IPSpecialLine getIPSpecialLine(final String id) {
    		IPSpecialLine ipspecialline = (IPSpecialLine) getHibernateTemplate().get(IPSpecialLine.class, id);
         if (ipspecialline == null) {
            throw new ObjectRetrievalFailureException(IPSpecialLine.class, id);
        }
        return ipspecialline;
    }

    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#saveIPSpecialLine(IPSpecialLine ipspecialline)
     */    
    public void saveIPSpecialLine(final IPSpecialLine ipspecialline) {
        if ((ipspecialline.getId() == null) || (ipspecialline.getId().equals("")))
			getHibernateTemplate().save(ipspecialline);
		else
			getHibernateTemplate().saveOrUpdate(ipspecialline);
    }
    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#removeIPSpecialLine(String id)
     */
    public void removeIPSpecialLine(final String id) {
    	IPSpecialLine nbp = getIPSpecialLine(id);
    	//nbp.setDeleted(new Integer(1));
    	getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }
    
    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#removeIPSpecialLine(String id)
     */
    public void restoreIPSpecialLine(final String id) {
    	IPSpecialLine nbp = getIPSpecialLine(id);
    	//nbp.setDeleted(new Integer(0));
    	getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }
    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#getIPSpecialLines(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getIPSpecialLines(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the ipspecialline
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from IPSpecialLine nbp";
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
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#getIPSpecialLines(final Integer curPage, final Integer pageSize)
     */    
    public Map getIPSpecialLines(final Integer curPage, final Integer pageSize) {
			return this.getIPSpecialLines(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.IPSpecialLineDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from IPSpecialLine obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
	
    /**
	 * @see com.boco.eoms.sheet.ipspecialline.dao.IIPSpecialLineDao#getIPSpecialLinesByCondition(java.lang.String)
	 */
	public List getIPSpecialLinesByHql(String hql) {
		return getHibernateTemplate().find(hql);		
	}
	
	public List getSpecialLines(String id){
		String sql = "from IPSpecialLine ipSpecialLine where ipSpecialLine.ipSpecialLine_Id ='"
		+ id + "'";
		return getHibernateTemplate().find(sql);
		
	}
	public Map getQueryIPSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr){
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStrItem=" where 1=1 and ipSpecialLine.deleted<>1";
				String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
				Set keySet = queryMap.keySet();				
				Iterator it = keySet.iterator();
				if(hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
				  while (it.hasNext()) { 				 
					String key = (String)it.next();
					if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
						if(key.equals("creatTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.creatTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";		
						}else if(key.equals("creatTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.creatTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitStartDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.completeLimit>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("completeLimitEndDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.completeLimit<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("changeTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.changeTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("changeTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.changeTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("cancelTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.cancelTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("cancelTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.cancelTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else if(key.equals("endTimeStartDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.endTime>=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";
						}else if(key.equals("endTimeEndDateExpression")){
							queryStrItem = queryStrItem+" and ipSpecialLine.endTime<=to_date('"+queryMap.get(key)+"'"+",'yyyy-MM-dd hh24:mi:ss')";                       
						}else{
							queryStrItem = queryStrItem+" and ipSpecialLine."+key+" like"+" '%"+queryMap.get(key)+"%'";
						}
					}					 
				 }	
				}else{
					 while (it.hasNext()) {				 
							String key = (String)it.next();
							if(queryMap.get(key)!=null&&!queryMap.get(key).equals("")){
								if(key.equals("creatTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.creatTime>='"+queryMap.get(key)+"'";		
								}else if(key.equals("creatTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.creatTime<='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitStartDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.completeLimit>='"+queryMap.get(key)+"'";
								}else if(key.equals("completeLimitEndDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.completeLimit<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("changeTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.changeTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("changeTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.changeTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("cancelTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.cancelTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("cancelTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.cancelTime<='"+queryMap.get(key)+"'";                       
								}else if(key.equals("endTimeStartDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.endTime>='"+queryMap.get(key)+"'";
								}else if(key.equals("endTimeEndDateExpression")){
									queryStrItem = queryStrItem+" and ipSpecialLine.endTime<='"+queryMap.get(key)+"'";                       
								}else{
									queryStrItem = queryStrItem+" and ipSpecialLine."+key+" like"+" '%"+queryMap.get(key)+"%'";
								}
							}					 
						 }	
				}

				String queryStr = "from IPSpecialLine ipSpecialLine "+queryStrItem;
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
	public void saveOrUpdate(IPSpecialLine ipspecialline) throws HibernateException{
        		getHibernateTemplate().saveOrUpdate(ipspecialline);
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
		String sql = "from IPSpecialLine s where s.siteNameA ='" + siteNameA + "' and s.siteNameZ='"+siteNameZ+"'";
		return getHibernateTemplate().find(sql);		
	}
	/**
	 * 通过Z端业务设备端口查找电路
	 */
	public List getSpecialLineByZPort(String portZBDeviceName,String portZBDevicePort) throws Exception{
		String sql = "from IPSpecialLine t where t.portZBDevicePort ='"	+ portZBDevicePort + "' and t.portZBDeviceName='"+portZBDeviceName+"'";
		return getHibernateTemplate().find(sql);
	}
	
}
