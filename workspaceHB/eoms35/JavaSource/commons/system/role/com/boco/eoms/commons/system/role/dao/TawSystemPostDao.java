
package com.boco.eoms.commons.system.role.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemPost;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;

public interface TawSystemPostDao extends Dao {

    /**
     * Retrieves all of the tawSystemPosts
     */
    public List getTawSystemPosts();

    /**
     * Gets tawSystemPost's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param postId the tawSystemPost's postId
     * @return tawSystemPost populated tawSystemPost object
     */
    public TawSystemPost getTawSystemPost(final long postId);
    public TawSystemPost getTawSystemPost(final long postId,final int deleted);

    /**
     * Saves a tawSystemPost's information
     * @param tawSystemPost the object to be saved
     */    
    public void saveTawSystemPost(TawSystemPost tawSystemPost);

    /**
     * Removes a tawSystemPost from the database by postId
     * @param postId the tawSystemPost's postId
     */
    public void removeTawSystemPost(final long postId) throws TawSystemUserException;
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize);
    public Map getTawSystemPosts(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getPostsByStructureFlag(String structureFlag, int deleted);
    public List getChildPostByPostId(final long postId);
    public String getPostNameById(final long postId);
    public void setLeaf(final long postId,final Integer leaf);
    public String getNewStructureFlag(long parentPostId) throws Exception;
    public List getPostsByDeptId(String deptId);
    public List getUserByPostId(long postId);
    /**
     * 
     * @param deptIds
     * @return <postViewVO>
     */
    public List getPostView(String deptId) throws Exception;
}

