
package com.boco.eoms.commons.system.role.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemUserRefPost;

public interface TawSystemUserRefPostDao extends Dao {

    /**
     * Retrieves all of the tawSystemUserRefPosts
     */
    public List getTawSystemUserRefPosts(TawSystemUserRefPost tawSystemUserRefPost);

    /**
     * Gets tawSystemUserRefPost's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawSystemUserRefPost's id
     * @return tawSystemUserRefPost populated tawSystemUserRefPost object
     */
    public TawSystemUserRefPost getTawSystemUserRefPost(final Long id);

    /**
     * Saves a tawSystemUserRefPost's information
     * @param tawSystemUserRefPost the object to be saved
     */    
    public void saveTawSystemUserRefPost(TawSystemUserRefPost tawSystemUserRefPost);

    /**
     * Removes a tawSystemUserRefPost from the database by id
     * @param id the tawSystemUserRefPost's id
     */
    public void removeTawSystemUserRefPost(final Long id);
    /**
     * curPage
     * pageSize
     */
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize);
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize, final String whereStr);
}

