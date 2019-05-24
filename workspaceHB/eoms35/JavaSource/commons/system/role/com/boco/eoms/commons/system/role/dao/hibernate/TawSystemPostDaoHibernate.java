
package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.role.model.TawSystemPost;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.vo.PostViewVO;
import com.boco.eoms.commons.system.role.dao.TawSystemPostDao;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemPostDaoHibernate extends BaseDaoHibernate implements TawSystemPostDao {

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemPostDao#getTawSystemPosts(com.boco.eoms.commons.system.role.model.TawSystemPost)
     */
    public List getTawSystemPosts() {
        return getHibernateTemplate().find("from TawSystemPost t where t.deleted="+StaticVariable.UNDELETED);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSystemPost == null) {
            return getHibernateTemplate().find("from TawSystemPost");
        } else {
            // filter on properties set in the tawSystemPost
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSystemPost).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSystemPost.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemPostDao#getTawSystemPost(String postId)
     */
    public TawSystemPost getTawSystemPost(final long postId) {
        TawSystemPost tawSystemPost = (TawSystemPost) getHibernateTemplate().get(TawSystemPost.class, new Long(postId));
        if (tawSystemPost == null) {
            throw new ObjectRetrievalFailureException(TawSystemPost.class, new Long(postId));
        }

        return tawSystemPost;
    }
    public TawSystemPost getTawSystemPost(final long postId,final int deleted) {
    	TawSystemPost tawSystemPost = null;
    	List list = getHibernateTemplate().find("from TawSystemPost p where p.postId="+postId+" and p.deleted="+deleted);
    	if(list.size()>0){
    		tawSystemPost = (TawSystemPost)list.get(0);
    	}

        return tawSystemPost;
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemPostDao#saveTawSystemPost(TawSystemPost tawSystemPost)
     */    
    public void saveTawSystemPost(final TawSystemPost tawSystemPost) {
        if (tawSystemPost.getPostId()==0)
			getHibernateTemplate().save(tawSystemPost);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemPost);
    }

    /**
     * @throws TawSystemUserException 
     * @see com.boco.eoms.commons.system.role.dao.TawSystemPostDao#removeTawSystemPost(String postId)
     */
    public void removeTawSystemPost(final long postId) throws TawSystemUserException {    	
    	TawSystemPost tawSystemPost = getTawSystemPost(postId);
    	tawSystemPost.setDeleted(new Integer(StaticVariable.DELETED));
        getHibernateTemplate().saveOrUpdate(tawSystemPost);
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSystemPost
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSystemPost";
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
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize) {
			return this.getTawSystemPosts(curPage,pageSize,null);
		}

	public List getPostsByStructureFlag(String structureFlag, int deleted) {
		String hql = "from TawSystemPost p WHERE p.structureFlag like '"+ structureFlag +"%' and p.deleted=" + deleted;
		List list = getHibernateTemplate().find(hql);
		return list;
	}
	
	public List getChildPostByPostId(final long postId){
		String hql = "from TawSystemPost p WHERE p.parentId="+ postId +" and p.deleted=" + StaticVariable.UNDELETED;
		List list = getHibernateTemplate().find(hql);
		return list;
	}
	
	public String getPostNameById(final long postId){
		TawSystemPost tawSystemPost = null;
    	List list = getHibernateTemplate().find("from TawSystemPost p where p.postId="+postId);
    	if(list.size()>0){
    		tawSystemPost = (TawSystemPost)list.get(0);
    	}
    	if(tawSystemPost!=null)
    		return tawSystemPost.getPostName();
    	return null;
	}
	/**
	 * 
	 * @param postId
	 * @param leaf 1叶子,0非叶子
	 */
	public void setLeaf(final long postId,final Integer leaf){
		TawSystemPost tawSystemPost = this.getTawSystemPost(postId);
		if(tawSystemPost.getLeaf()!=leaf){
			tawSystemPost.setLeaf(leaf);
			getHibernateTemplate().saveOrUpdate(tawSystemPost);
		}
	}
	
	public String getNewStructureFlag(long parentPostId) throws Exception {
        String ret = StaticVariable.defaultnull;
        String parentPostStructflag = "";
        long temp;

    	TawSystemPost post = this.getTawSystemPost(parentPostId);
    	parentPostStructflag = post.getStructureFlag();
    	if(parentPostStructflag==null)
    		return null;

		  ret = this.getMaxStructureFlag
		      (parentPostStructflag, parentPostStructflag.length() + 2, StaticVariable.UNDELETED);
		  if (ret.equals(parentPostStructflag)) {
		    ret = parentPostStructflag + "01";
		  }
		  else {
		    temp = StaticMethod.null2Long(ret);
		    if (ret.compareTo(parentPostStructflag + "99") < 0) {
		      ret = String.valueOf(temp + 1);
		    }
		    else {
		      ret = StaticVariable.defaultnull;
		    }
		  }

        return ret;
    }
	public String getMaxStructureFlag(String parentPostStructflag, int len, int undel) throws
	   Exception {
	     String ret = "";

	         String hql = "SELECT max(p.structureFlag) FROM TawSystemPost p " +
	             "WHERE p.structureFlag like \'" + parentPostStructflag + "%\' and length(p.structureFlag) <=" + len +
	             " and p.deleted=" + undel;
	         List list = getHibernateTemplate().find(hql);
	         if (list.size()>0) {
	           ret = list.get(0).toString();
	         }

	       return ret;
	   }
	
	public List getPostsByDeptId(String deptId) {
        return getHibernateTemplate().find("from TawSystemPost t where t.deptId='"+deptId+"' and t.deleted="+StaticVariable.UNDELETED);
	}
	
	public List getUserByPostId(long postId){
		List list = getHibernateTemplate().find("from TawSystemUser u, TawSystemUserRefPost t where u.userid=t.userId and t.postId="+postId);
		List userList = new ArrayList();
		for(int i=0;i<list.size();i++){
			userList.add(((Object[])list.get(i))[0]);
    	}
    	
    	return userList;
	}
	
	/**
     * 
     * @param deptIds
     * @return <postViewVO>
     */
    public List getPostView(String deptId) throws Exception{
    	String deptName = "";
    	String hql = "from TawSystemDept d where d.deptId='"+deptId+"'";
    	List deptList = getHibernateTemplate().find(hql);
    	if(deptList.size()>0)
    		deptName = ((TawSystemDept)deptList.get(0)).getDeptName();
    	
    	hql = "from TawSystemPost p,TawSystemSubRole r,TawSystemSubRoleRefPost rp,TawSystemUser u,TawSystemUserRefRole ur where p.postId=rp.postId and rp.subRoleId=r.id and rp.subRoleId=ur.subRoleid and ur.userid=u.userid and p.deleted<>"+StaticVariable.DELETED+" and p.deptId='"+deptId+"' order by p.postName,r.subRoleName";
    	System.out.println("hql="+hql);
    	List list = getHibernateTemplate().find(hql);
    	List postList = new ArrayList();
    	for(int i=0;i<list.size();i++){
    		PostViewVO vo = new PostViewVO();
    		TawSystemPost post = (TawSystemPost)(((Object[])list.get(i))[0]);
    		TawSystemSubRole subRole = (TawSystemSubRole)(((Object[])list.get(i))[1]);
    		TawSystemUser user = (TawSystemUser)(((Object[])list.get(i))[3]);
    		vo.setDeptId(deptId);
    		vo.setPostId(post.getPostId());
    		vo.setSubRoleId(subRole.getId());
    		vo.setUserId(user.getUserid());
    		vo.setDeptName(deptName);
    		vo.setPostName(post.getPostName());
    		vo.setSubRoleName(subRole.getSubRoleName());
    		vo.setUserName(user.getUsername());
    		
    		postList.add(vo);
    	}
    	return postList;
    }

}
