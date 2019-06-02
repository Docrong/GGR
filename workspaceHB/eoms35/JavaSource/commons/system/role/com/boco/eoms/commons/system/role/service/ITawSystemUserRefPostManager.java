
package com.boco.eoms.commons.system.role.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.model.TawSystemUserRefPost;

public interface ITawSystemUserRefPostManager extends Manager {
    /**
     * Retrieves all of the tawSystemUserRefPosts
     */
    public List getTawSystemUserRefPosts(TawSystemUserRefPost tawSystemUserRefPost);

    /**
     * Gets tawSystemUserRefPost's information based on id.
     * @param id the tawSystemUserRefPost's id
     * @return tawSystemUserRefPost populated tawSystemUserRefPost object
     */
    public TawSystemUserRefPost getTawSystemUserRefPost(final String id);

    /**
     * Saves a tawSystemUserRefPost's information
     * @param tawSystemUserRefPost the object to be saved
     */
    public void saveTawSystemUserRefPost(TawSystemUserRefPost tawSystemUserRefPost);

    /**
     * Removes a tawSystemUserRefPost from the database by id
     * @param id the tawSystemUserRefPost's id
     */
    public void removeTawSystemUserRefPost(final String id);
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize);
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize, final String whereStr);
}

