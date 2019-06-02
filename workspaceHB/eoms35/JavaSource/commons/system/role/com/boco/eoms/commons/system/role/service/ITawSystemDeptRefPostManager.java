
package com.boco.eoms.commons.system.role.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost;

public interface ITawSystemDeptRefPostManager extends Manager {
    /**
     * Retrieves all of the tawSystemDeptRefPosts
     */
    public List getTawSystemDeptRefPosts(TawSystemDeptRefPost tawSystemDeptRefPost);

    /**
     * Gets tawSystemDeptRefPost's information based on id.
     * @param id the tawSystemDeptRefPost's id
     * @return tawSystemDeptRefPost populated tawSystemDeptRefPost object
     */
    public TawSystemDeptRefPost getTawSystemDeptRefPost(final String id);

    /**
     * Saves a tawSystemDeptRefPost's information
     * @param tawSystemDeptRefPost the object to be saved
     */
    public void saveTawSystemDeptRefPost(TawSystemDeptRefPost tawSystemDeptRefPost);

    /**
     * Removes a tawSystemDeptRefPost from the database by id
     * @param id the tawSystemDeptRefPost's id
     */
    public void removeTawSystemDeptRefPost(final String id);
    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize);
    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 获取部门下的岗位列表 
     * @param deptId
     * @return <TawSystemPost>
     */
    public List getPostByDeptId(String deptId);
}

