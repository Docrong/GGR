/*
 * Created on 2008-3-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao;
import com.boco.eoms.commons.system.role.model.TawSystemSubRoleRefPost;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemSubRoleRefPostDaoHibernate extends BaseDaoHibernate implements TawSystemSubRoleRefPostDao{

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao#saveTawSystemSubRoleRefPost(com.boco.eoms.commons.system.role.model.TawSystemSubRoleRefPost)
	 */
	public void saveTawSystemSubRoleRefPost(TawSystemSubRoleRefPost tawSystemSubRoleRefPost) {
		 if (tawSystemSubRoleRefPost.getId()==null)
			getHibernateTemplate().save(tawSystemSubRoleRefPost);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemSubRoleRefPost);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao#getPostBySubRoleId(java.lang.String)
	 */
	public List getPostBySubRoleId(String subRoleid) {
		List list = getHibernateTemplate().find("from TawSystemSubRoleRefPost t,TawSystemPost p where t.postId=p.postId and t.subRoleId='"+subRoleid+"'");
		List postList = new ArrayList();
    	for(int i=0;i<list.size();i++){
    		postList.add(((Object[])list.get(i))[1]);
    	}
    	return postList;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao#getSubRoleByPostId(long)
	 */
	public List getSubRoleByPostId(long postId) {
		List list = getHibernateTemplate().find("from TawSystemSubRoleRefPost t,TawSystemSubRole r where t.subRoleId=r.id and t.postId="+postId);
		List subRoleList = new ArrayList();
    	for(int i=0;i<list.size();i++){
    		subRoleList.add(((Object[])list.get(i))[1]);
    	}
    	return subRoleList;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao#removeSubRoleRefPostByPostId(long)
	 */
	public void removeSubRoleRefPostByPostId(long postId) {
		List list = getHibernateTemplate().find("from TawSystemSubRoleRefPost t where t.postId="+postId);
		for(int i=0;i<list.size();i++){
			TawSystemSubRoleRefPost sp = (TawSystemSubRoleRefPost)list.get(i);
			getHibernateTemplate().delete(sp);
		}
		getHibernateTemplate().flush();
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao#removeSubSoleRefPostBySubRoleId(java.lang.String)
	 */
	public void removeSubSoleRefPostBySubRoleId(String subRoleId) {
		List list = getHibernateTemplate().find("from TawSystemSubRoleRefPost t where t.subRoleId='"+subRoleId+"'");
		for(int i=0;i<list.size();i++){
			TawSystemSubRoleRefPost sp = (TawSystemSubRoleRefPost)list.get(i);
			getHibernateTemplate().delete(sp);
		}
		getHibernateTemplate().flush();
	}

}
