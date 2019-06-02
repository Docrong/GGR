
package com.boco.eoms.sheet.tool.access.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.dao.ITawSheetAccessDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSheetAccessDaoHibernate extends BaseDaoHibernate implements ITawSheetAccessDao {

    /**
     * @see com.boco.eoms.sheet.tool.access.dao.TawSheetAccessDao#getTawSheetAccesss(com.boco.eoms.sheet.tool.access.model.TawSheetAccess)
     */
    public List getTawSheetAccesss(final TawSheetAccess tawSheetAccess) {
        return getHibernateTemplate().find("from TawSheetAccess");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSheetAccess == null) {
            return getHibernateTemplate().find("from TawSheetAccess");
        } else {
            // filter on properties set in the tawSheetAccess
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSheetAccess).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSheetAccess.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.dao.TawSheetAccessDao#getTawSheetAccess(Integer id)
     */
    public TawSheetAccess getTawSheetAccess(final Integer id) {
        TawSheetAccess tawSheetAccess = (TawSheetAccess) getHibernateTemplate().get(TawSheetAccess.class, id);
        if (tawSheetAccess == null) {
            throw new ObjectRetrievalFailureException(TawSheetAccess.class, id);
        }

        return tawSheetAccess;
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.dao.TawSheetAccessDao#saveTawSheetAccess(TawSheetAccess tawSheetAccess)
     */    
    public void saveTawSheetAccess(final TawSheetAccess tawSheetAccess) {
        if ((tawSheetAccess.getId() == null) || (tawSheetAccess.getId().equals("")))
			getHibernateTemplate().save(tawSheetAccess);
		else
			getHibernateTemplate().saveOrUpdate(tawSheetAccess);
    }

    /**
     * @see com.boco.eoms.sheet.tool.access.dao.TawSheetAccessDao#removeTawSheetAccess(Integer id)
     */
    public void removeTawSheetAccess(final Integer id) {
        getHibernateTemplate().delete(getTawSheetAccess(id));
    }
    /**
     * @see com.boco.eoms.sheet.tool.access.dao.TawSheetAccessDao#getTawSheetAccesss(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawSheetAccesss(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSheetAccess
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSheetAccess";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
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
     * @see com.boco.eoms.sheet.tool.access.dao.TawSheetAccessDao#getTawSheetAccesss(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawSheetAccesss(final Integer curPage, final Integer pageSize) {
			return this.getTawSheetAccesss(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.sheet.tool.access.dao.TawSheetAccessDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawSheetAccess obj where obj.parAccessid='"
			+ parentId + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public TawSheetAccess getAccessByAccessId(String accessid) {
		String hql = " from TawSheetAccess obj where obj.accessid='"+accessid+"'";
        return (TawSheetAccess)getHibernateTemplate().find(hql).get(TawSystemAreaUtil.AREA_DEFAULT_INTVAL);
	}

	public List getAllSonAccessByAreaid(String accessid) {
		String hql = " from TawSheetAccess obj where obj.accessid like '"+accessid+"%'";
   	 return (ArrayList)getHibernateTemplate().find(hql);
	}

	public List getSameLevelAccess(String paraccessid, Integer ordercode) {
		 String hql = " from TawSheetAccess obj where obj.parAccessid='"+paraccessid+"' and sysarea.ordercode='"+ordercode+"'";
    	 return (ArrayList)getHibernateTemplate().find(hql);
	}

	public List getSonAccessByAccessId(String accessid) {
		 String hql = " from TawSheetAccess obj where obj.accessid like '"+accessid+"%'";
    	 return (ArrayList)getHibernateTemplate().find(hql);
	}

	public boolean isExitTaskName(String processname,String taskname) {
		 String hql = " from TawSheetAccess obj where obj.processname='"+processname+"' and obj.taskname='"+taskname+"'";
    	 List list = (ArrayList)getHibernateTemplate().find(hql);
    	 if( list != null && list.size() >0 ){
    		 return true;
    	 }else{
    	   return false;
    	 }
	}

	/**
	 * 根据PROCESSNAME TASKNAME查询流程附件模板
	 * @param processname
	 * @param taskname
	 * @return
	 */
	public TawSheetAccess getAccessByPronameAndTaskname(String processname,String taskname){
		String hql = " from TawSheetAccess obj where obj.processname='"+processname+"' and obj.taskname='"+taskname+"'";
		 if( taskname==null || taskname.equals("")){
			 hql=" from TawSheetAccess obj where obj.processname='"+processname+"' and (obj.taskname is null or obj.taskname='')";
		 }
		 
    	 List list = (ArrayList)getHibernateTemplate().find(hql);
    	
    	 TawSheetAccess access = new TawSheetAccess();
    	 
    	 if( list !=null && list.size()>0){
    		 access = (TawSheetAccess)list.get(0);
    	 }else{
    		 access = null;
    	 }
    	 return access;
	}
	
	
}
