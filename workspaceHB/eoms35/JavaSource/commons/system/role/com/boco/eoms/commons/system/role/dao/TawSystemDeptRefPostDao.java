
package com.boco.eoms.commons.system.role.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost;

public interface TawSystemDeptRefPostDao extends Dao {

    /**
     * Retrieves all of the tawSystemDeptRefPosts
     */
    public List getTawSystemDeptRefPosts(TawSystemDeptRefPost tawSystemDeptRefPost);

    /**
     * Gets tawSystemDeptRefPost's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawSystemDeptRefPost's id
     * @return tawSystemDeptRefPost populated tawSystemDeptRefPost object
     */
    public TawSystemDeptRefPost getTawSystemDeptRefPost(final Long id);

    /**
     * Saves a tawSystemDeptRefPost's information
     * @param tawSystemDeptRefPost the object to be saved
     */    
    public void saveTawSystemDeptRefPost(TawSystemDeptRefPost tawSystemDeptRefPost);

    /**
     * Removes a tawSystemDeptRefPost from the database by id
     * @param id the tawSystemDeptRefPost's id
     */
    public void removeTawSystemDeptRefPost(final Long id);

    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize);
    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize, final String whereStr);
    
    /**
     * @param deptId
     * @return <TawSystemPost>
     */
    public List getPostByDeptId(String deptId);
    public void removeDeptRefPostByPostId(Long postId);
}

