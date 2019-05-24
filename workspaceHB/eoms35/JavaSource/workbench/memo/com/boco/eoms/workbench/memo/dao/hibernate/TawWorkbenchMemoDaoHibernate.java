
package  com.boco.eoms.workbench.memo.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawWorkbenchMemoDaoHibernate extends BaseDaoHibernate implements TawWorkbenchMemoDao {
  
    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoDao#getTawWorkbenchMemos(com.boco.eoms.commons.workbench.model.TawWorkbenchMemo)
     */
    public List getTawWorkbenchMemos(final TawWorkbenchMemo tawWorkbenchMemo) {
        return getHibernateTemplate().find("from TawWorkbenchMemo");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawWorkbenchMemo == null) {
            return getHibernateTemplate().find("from TawWorkbenchMemo");
        } else {
            // filter on properties set in the tawWorkbenchMemo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawWorkbenchMemo).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawWorkbenchMemo.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoDao#getTawWorkbenchMemo(String id)
     */
    public TawWorkbenchMemo getTawWorkbenchMemo(final String id) {
        TawWorkbenchMemo tawWorkbenchMemo = (TawWorkbenchMemo) getHibernateTemplate().get(TawWorkbenchMemo.class, id);
        if (tawWorkbenchMemo == null) {
            throw new ObjectRetrievalFailureException(TawWorkbenchMemo.class, id);
        }

        return tawWorkbenchMemo;
    }

    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoDao#saveTawWorkbenchMemo(TawWorkbenchMemo tawWorkbenchMemo)
     */    
    public void saveTawWorkbenchMemo(final TawWorkbenchMemo tawWorkbenchMemo) {
        if ((tawWorkbenchMemo.getId() == null) || (tawWorkbenchMemo.getId().equals("")))
			getHibernateTemplate().save(tawWorkbenchMemo);
		else
			getHibernateTemplate().saveOrUpdate(tawWorkbenchMemo);
        
    }

    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoDao#removeTawWorkbenchMemo(String id)
     */
    public void removeTawWorkbenchMemo(final String id) {
        getHibernateTemplate().delete(getTawWorkbenchMemo(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getTawWorkbenchMemos(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawWorkbenchMemo
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawWorkbenchMemo";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							//int total = ((Integer) session.createQuery(queryCountStr).iterate().next()).intValue();
            	Integer total = (Integer) session.createQuery(queryCountStr).iterate()
							.next();
							Query query = session.createQuery(queryStr + " order by creattime desc");
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
    public Map getTawWorkbenchMemos(final Integer curPage, final Integer pageSize) {
			return this.getTawWorkbenchMemos(curPage,pageSize,null);
		}
    public String saveTawWorkbenchMemoReturnId(TawWorkbenchMemo tawWorkbenchMemo) {
        if ((tawWorkbenchMemo.getId() == null) || (tawWorkbenchMemo.getId().equals(""))){
			getHibernateTemplate().save(tawWorkbenchMemo);
        }
		else{
			getHibernateTemplate().saveOrUpdate(tawWorkbenchMemo);
		}
        return  tawWorkbenchMemo.getId();
    }
    
    public boolean ifSystemUser(String user){
    	boolean bool = false;
    	String queryStr = " from TawSystemUser where username = '"+user+"'";
    	List tawSystemUser = (List) getHibernateTemplate().find(queryStr);
    	if(tawSystemUser.size()>0){
    		bool = true;
    	}
    	return bool;
    }
}
