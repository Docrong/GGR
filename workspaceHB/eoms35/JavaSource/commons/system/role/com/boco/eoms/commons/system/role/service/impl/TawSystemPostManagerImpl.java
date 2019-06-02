
package com.boco.eoms.commons.system.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost;
import com.boco.eoms.commons.system.role.model.TawSystemPost;
import com.boco.eoms.commons.system.role.model.TawSystemSubRoleRefPost;
import com.boco.eoms.commons.system.role.dao.TawSystemDeptRefPostDao;
import com.boco.eoms.commons.system.role.dao.TawSystemPostDao;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao;
import com.boco.eoms.commons.system.role.dao.TawSystemUserRefPostDao;
import com.boco.eoms.commons.system.role.service.ITawSystemPostManager;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;

public class TawSystemPostManagerImpl extends BaseManager implements ITawSystemPostManager {
    private TawSystemPostDao dao;
    private TawSystemUserRefPostDao userPostDao;
    private TawSystemDeptRefPostDao deptPostDao;
    private TawSystemSubRoleRefPostDao subRolePostDao;
	/**
	 * @param dao The dao to set.
	 */
	public void setTawSystemSubRoleRefPostDao(TawSystemSubRoleRefPostDao dao) {
		this.subRolePostDao = dao;
	}

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemDeptRefPostDao(TawSystemDeptRefPostDao dao) {
        this.deptPostDao = dao;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemPostDao(TawSystemPostDao dao) {
        this.dao = dao;
    }
    
    public void setTawSystemUserRefPostDao(TawSystemUserRefPostDao dao) {
        this.userPostDao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemPostManager#getTawSystemPosts(com.boco.eoms.commons.system.role.model.TawSystemPost)
     */
    public List getTawSystemPosts() {
        return dao.getTawSystemPosts();
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemPostManager#getTawSystemPost(String postId)
     */
    public TawSystemPost getTawSystemPost(final long postId) {
        return dao.getTawSystemPost(postId);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemPostManager#saveTawSystemPost(TawSystemPost tawSystemPost)
     */
    public void saveTawSystemPost(TawSystemPost tawSystemPost,String[] subRoleIds) {

    	if (tawSystemPost.getPostId()>0){
    		deptPostDao.removeDeptRefPostByPostId(new Long(tawSystemPost.getPostId()));
    		subRolePostDao.removeSubRoleRefPostByPostId(tawSystemPost.getPostId());
    	}
    	else
    		tawSystemPost.setSingleId(StaticMethod.getCurrentDateTime());
        dao.saveTawSystemPost(tawSystemPost);
        
        TawSystemDeptRefPost tawSystemDeptRefPost = new TawSystemDeptRefPost();
		tawSystemDeptRefPost.setDeptId(tawSystemPost.getDeptId());
		tawSystemDeptRefPost.setPostId(tawSystemPost.getPostId());
        deptPostDao.saveTawSystemDeptRefPost(tawSystemDeptRefPost);
        
        for(int i=0;i<subRoleIds.length;i++){
        	TawSystemSubRoleRefPost rp = new TawSystemSubRoleRefPost();
			rp.setPostId(tawSystemPost.getPostId());
			rp.setSubRoleId(subRoleIds[i]);
			subRolePostDao.saveTawSystemSubRoleRefPost(rp);
        }

    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemPostManager#removeTawSystemPost(String postId)
     */
    public void removeTawSystemPost(final long postId) throws TawSystemUserException{
        dao.removeTawSystemPost(postId);
    }
    /**
     * 
     */
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemPosts(curPage, pageSize,null);
    }
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemPosts(curPage, pageSize, whereStr);
    }

	public TawSystemPost getTawSystemPost(long postId, int deleted) {
		return dao.getTawSystemPost(postId,deleted);
	}

	public List getPostsByStructureFlag(long postId) {
		TawSystemPost post = dao.getTawSystemPost(postId);
		List list = dao.getPostsByStructureFlag(post.getStructureFlag(), StaticVariable.UNDELETED);
		return list;
	}
	public List getChildrenByPostId(long postId) {
		// TODO Auto-generated method stub
		List list = dao.getChildPostByPostId(postId);
		return list;
	}
	public String getPostNameById(final long postId){
		return dao.getPostNameById(postId);
	}
	public void setLeaf(final long postId,final Integer leaf){
		dao.setLeaf(postId, leaf);
	}
	public String getNewStructureFlag(long parentPostId) throws Exception{
		return dao.getNewStructureFlag(parentPostId);
	}
	public List getPostsByDeptId(String deptId){ 
		return dao.getPostsByDeptId(deptId);
	}
	
//	public void savePost(TawSystemPost tawSystemPost,String userIds) {
//        dao.saveTawSystemPost(tawSystemPost);
//        long postId = tawSystemPost.getPostId();
//        
//        String[] userArray = userIds.split(",");
//        for(int i=0;i<userArray.length;i++){
//        	String userid = userArray[i];
//        	TawSystemUserRefPost userPost = new TawSystemUserRefPost();
//        	userPost.setPostId(postId);
//        	userPost.setUserId(userid);
//        	this.userPostDao.saveTawSystemUserRefPost(userPost);
//        	
//        }
//    }
//	public void savePost(TawSystemPost tawSystemPost,String[] subRoleIds, String[] userIds) {
//        dao.saveTawSystemPost(tawSystemPost);
//        long postId = tawSystemPost.getPostId();
//        
//        ITawSystemSubRoleRefPostManager mgr = (ITawSystemSubRoleRefPostManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleRefPostManager");
//        
//        for(int i=0;i<subRoleIds.length;i++){
//        	TawSystemSubRoleRefPost rp = new TawSystemSubRoleRefPost();
//			rp.setPostId(postId);
//			rp.setSubRoleId(subRoleIds[i]);
//			mgr.saveTawSystemSubRoleRefPost(rp);
//        }
//        
////        for(int i=0;i<userIds.length;i++){
////        	String userid = userIds[i];
////        	TawSystemUserRefPost userPost = new TawSystemUserRefPost();
////        	userPost.setPostId(postId);
////        	userPost.setUserId(userid);
////        	this.userPostDao.saveTawSystemUserRefPost(userPost);
////        }
//    }

	public List getUserByPostId(long postId){
		return dao.getUserByPostId(postId);
	}
	
	/**
     * 
     * @param deptIds
     * @return <postViewVO>
     */
    public List getPostView(String[] deptIds) throws Exception{
    	List list = new ArrayList();
    	for(int i=0;i<deptIds.length;i++){
    		list.addAll(dao.getPostView(deptIds[i]));
    	}
    	return list;
    }

}
