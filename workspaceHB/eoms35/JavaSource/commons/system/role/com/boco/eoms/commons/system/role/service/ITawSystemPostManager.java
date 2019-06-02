
package com.boco.eoms.commons.system.role.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.model.TawSystemPost;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;

public interface ITawSystemPostManager extends Manager {
    /**
     * Retrieves all of the tawSystemPosts
     */
    public List getTawSystemPosts();

    /**
     * Gets tawSystemPost's information based on postId.
     * @param postId the tawSystemPost's postId
     * @return tawSystemPost populated tawSystemPost object
     */
    public TawSystemPost getTawSystemPost(final long postId);
    public TawSystemPost getTawSystemPost(final long postId,final int deleted);

    /**
     * Saves a tawSystemPost's information
     * @param tawSystemPost the object to be saved
     */
    public void saveTawSystemPost(TawSystemPost tawSystemPost,String[] subRoleIds);

    /**
     * Removes a tawSystemPost from the database by postId
     * @param postId the tawSystemPost's postId
     */
    public void removeTawSystemPost(final long postId) throws TawSystemUserException;
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize);
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getChildrenByPostId(long postId);
    public String getPostNameById(final long postId);
    public void setLeaf(final long postId,final Integer leaf);
    public String getNewStructureFlag(long parentPostId) throws Exception;
    public List getPostsByDeptId(String deptId);
    /**
     * 保存岗位
     * @param tawSystemPost
     * @param userIds 岗位人员，用逗号分隔
     */
//    public void savePost(TawSystemPost tawSystemPost,String userIds);
//    public void savePost(TawSystemPost tawSystemPost,String[] subRoleIds, String[] userIds);
    public List getUserByPostId(long postId);
    /**
     * 
     * @param deptIds
     * @return <postViewVO>
     */
    public List getPostView(String[] deptIds) throws Exception;
}

