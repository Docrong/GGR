
package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.role.model.TawSystemRoleRefWorkflow;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleRefWorkflowDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemRoleRefWorkflowDaoHibernate extends BaseDaoHibernate implements TawSystemRoleRefWorkflowDao {

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleRefWorkflowDao#getTawSystemRoleRefWorkflows(com.boco.eoms.commons.system.role.model.TawSystemRoleRefWorkflow)
     */
    public List getTawSystemRoleRefWorkflows(final TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow) {
        return getHibernateTemplate().find("from TawSystemRoleRefWorkflow");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSystemRoleRefWorkflow == null) {
            return getHibernateTemplate().find("from TawSystemRoleRefWorkflow");
        } else {
            // filter on properties set in the tawSystemRoleRefWorkflow
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSystemRoleRefWorkflow).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSystemRoleRefWorkflow.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleRefWorkflowDao#getTawSystemRoleRefWorkflow(String id)
     */
    public TawSystemRoleRefWorkflow getTawSystemRoleRefWorkflow(final String id) {
        TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow = (TawSystemRoleRefWorkflow) getHibernateTemplate().get(TawSystemRoleRefWorkflow.class, id);
        if (tawSystemRoleRefWorkflow == null) {
            throw new ObjectRetrievalFailureException(TawSystemRoleRefWorkflow.class, id);
        }

        return tawSystemRoleRefWorkflow;
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleRefWorkflowDao#saveTawSystemRoleRefWorkflow(TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow)
     */    
    public void saveTawSystemRoleRefWorkflow(final TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow) {
        if ((tawSystemRoleRefWorkflow.getId() == null) || (tawSystemRoleRefWorkflow.getId().equals("")))
			getHibernateTemplate().save(tawSystemRoleRefWorkflow);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemRoleRefWorkflow);
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleRefWorkflowDao#removeTawSystemRoleRefWorkflow(String id)
     */
    public void removeTawSystemRoleRefWorkflow(final String id) {
        getHibernateTemplate().delete(getTawSystemRoleRefWorkflow(id));
    }
    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSystemRoleRefWorkflows(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSystemRoleRefWorkflow
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSystemRoleRefWorkflow";
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
    public Map getTawSystemRoleRefWorkflows(final Integer curPage, final Integer pageSize) {
			return this.getTawSystemRoleRefWorkflows(curPage,pageSize,null);
		}

    /**
     * 根据流程名称获取有新增权限的大类角色
     * @param flowName 流程名
     * @param type 0自启动；1外部流程启动
     * @return <TawSystemRole>
     */
    public List getRoleBySheetName(String startSheetName,String sheetName){
    	String hql = "from TawSystemRole r,TawSystemRoleRefWorkflow t where r.roleId=t.roleid and t.startSheetName='"+startSheetName+"' and t.sheetName='"+sheetName+"'";
    	List list = getHibernateTemplate().find(hql);
    	List roleList = new ArrayList();
    	for(int i=0;i<list.size();i++){
    		roleList.add(((Object[])list.get(i))[0]);
    	}
    	return roleList;
    }

}
