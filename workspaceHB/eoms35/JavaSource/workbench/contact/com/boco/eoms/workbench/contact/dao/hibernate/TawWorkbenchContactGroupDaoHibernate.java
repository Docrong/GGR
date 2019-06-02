
package com.boco.eoms.workbench.contact.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */
public class TawWorkbenchContactGroupDaoHibernate extends BaseDaoHibernate implements TawWorkbenchContactGroupDao {

    /**
     * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#getTawWorkbenchContactGroups(com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup)
     */
    public List getTawWorkbenchContactGroups(final TawWorkbenchContactGroup tawWorkbenchContactGroup) {
        return getHibernateTemplate().find("from TawWorkbenchContactGroup");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawWorkbenchContactGroup == null) {
            return getHibernateTemplate().find("from TawWorkbenchContactGroup");
        } else {
            // filter on properties set in the tawWorkbenchContactGroup
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawWorkbenchContactGroup).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawWorkbenchContactGroup.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#getTawWorkbenchContactGroup(String id)
     */
    public TawWorkbenchContactGroup getTawWorkbenchContactGroup(final String id) {
        TawWorkbenchContactGroup tawWorkbenchContactGroup = (TawWorkbenchContactGroup) getHibernateTemplate().get(TawWorkbenchContactGroup.class, id);
        if (tawWorkbenchContactGroup == null) {
            throw new ObjectRetrievalFailureException(TawWorkbenchContactGroup.class, id);
        }

        return tawWorkbenchContactGroup;
    }

    /**
     * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#saveTawWorkbenchContactGroup(TawWorkbenchContactGroup tawWorkbenchContactGroup)
     */    
    public void saveTawWorkbenchContactGroup(final TawWorkbenchContactGroup tawWorkbenchContactGroup) {
        if ((tawWorkbenchContactGroup.getId() == null) || (tawWorkbenchContactGroup.getId().equals("")))
			getHibernateTemplate().save(tawWorkbenchContactGroup);
		else
			getHibernateTemplate().saveOrUpdate(tawWorkbenchContactGroup);
    }

    /**
     * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#removeTawWorkbenchContactGroup(String id)
     */
    public void removeTawWorkbenchContactGroup(final String id) {
        getHibernateTemplate().delete(getTawWorkbenchContactGroup(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawWorkbenchContactGroup
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawWorkbenchContactGroup";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							int total = ((Integer) session.createQuery(queryCountStr).iterate()
									.next()).intValue();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total+"");
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize) {
			return this.getTawWorkbenchContactGroups(curPage,pageSize,null);
		}
     /**
	 * 查询下一级信�?
	 * @param parentdictid
	 * @return
	 */
	public  List getSonsById(String parentid){	
		String hql = " from TawWorkbenchContactGroup work where work.parentId='"
			+ parentid + "' order by work.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public  List getNextLevecGroups(String nodid,String user_id, String deleted) {
		String hql = "";
	
		if(nodid.equals("-1")){
		 hql = " from TawWorkbenchContactGroup work where work.userId='"
			+ user_id + "' and work.deleted = '"+deleted+"' order by work.id";
		}else{
			hql = " from TawWorkbenchContactGroup work where work.groupid = '"+nodid+"' and  work.userId='"
			+ user_id + "' and work.deleted = '"+deleted+"' order by work.id";
		}
		return (ArrayList) getHibernateTemplate().find(hql);
		
	}
	 
	//得到最大数
	public int getMaxGroupId()  {
		Session session = getSession();
		String hql = " select count(*) from TawWorkbenchContactGroup work " ;
		int groupid = ((Integer) session.createQuery(hql).iterate()
				.next()).intValue();
		return groupid +1;
		
	}
	 public  TawWorkbenchContactGroup getTawWorkbenchContactGroupById(final String id){
		 
			String hql = " from TawWorkbenchContactGroup work where work.groupId='"
				+ id + "'";
			TawWorkbenchContactGroup tawWorkbenchContactGroup = (TawWorkbenchContactGroup)getHibernateTemplate().find(hql).iterator().next();
			return tawWorkbenchContactGroup;
	      
	 }
	 
}
