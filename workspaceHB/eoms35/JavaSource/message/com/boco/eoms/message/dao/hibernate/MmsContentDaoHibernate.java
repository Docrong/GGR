
package com.boco.eoms.message.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.message.dao.IMmsContentDao;
import com.boco.eoms.message.model.MmsContent;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:37:38
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class MmsContentDaoHibernate extends BaseDaoHibernate implements IMmsContentDao {

    /**
     * @see com.boco.eoms.message.dao.MmsContentDao#getMmsContents(com.boco.eoms.message.model.MmsContent)
     */
    public List getMmsContents(final MmsContent mmsContent) {
        return getHibernateTemplate().find("from MmsContent");

    }

    /**
     * @see com.boco.eoms.message.dao.MmsContentDao#getMmsContent(String id)
     */
    public MmsContent getMmsContent(final String id) {
        MmsContent mmsContent = (MmsContent) getHibernateTemplate().get(MmsContent.class, id);
        if (mmsContent == null) {
            throw new ObjectRetrievalFailureException(MmsContent.class, id);
        }

        return mmsContent;
    }

    /**
     * @see com.boco.eoms.message.dao.MmsContentDao#saveMmsContent(MmsContent mmsContent)
     */    
    public void saveMmsContent(final MmsContent mmsContent) {
        if ((mmsContent.getId() == null) || (mmsContent.getId().equals("")))
			getHibernateTemplate().save(mmsContent);
		else
			getHibernateTemplate().saveOrUpdate(mmsContent);
    }

    /**
     * @see com.boco.eoms.message.dao.MmsContentDao#removeMmsContent(String id)
     */
    public void removeMmsContent(final String id) {
        getHibernateTemplate().delete(getMmsContent(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getMmsContents(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the mmsContent
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from MmsContent";
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
							map.put("total", new Integer(total));
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getMmsContents(final Integer curPage, final Integer pageSize) {
			return this.getMmsContents(curPage,pageSize,null);
		}

	
	
	public void delete(MmsContent mmsContent) {
		removeMmsContent(mmsContent.getId());
		
	}

	public void deleteForever(String id) {
		removeMmsContent(id);
		
	}
	
	public List retriveMmsContents(String monitorId) {
		String hql = "from MmsContent where monitorId='"+monitorId+"' order by position";		
		return getHibernateTemplate().find(hql);
	}


}
