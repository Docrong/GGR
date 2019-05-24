
package com.boco.eoms.commons.system.role.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.role.model.TawSystemUserRefPost;
import com.boco.eoms.commons.system.role.dao.TawSystemUserRefPostDao;
import com.boco.eoms.commons.system.role.service.ITawSystemUserRefPostManager;

public class TawSystemUserRefPostManagerImpl extends BaseManager implements ITawSystemUserRefPostManager {
    private TawSystemUserRefPostDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemUserRefPostDao(TawSystemUserRefPostDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemUserRefPostManager#getTawSystemUserRefPosts(com.boco.eoms.commons.system.role.model.TawSystemUserRefPost)
     */
    public List getTawSystemUserRefPosts(final TawSystemUserRefPost tawSystemUserRefPost) {
        return dao.getTawSystemUserRefPosts(tawSystemUserRefPost);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemUserRefPostManager#getTawSystemUserRefPost(String id)
     */
    public TawSystemUserRefPost getTawSystemUserRefPost(final String id) {
        return dao.getTawSystemUserRefPost(new Long(id));
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemUserRefPostManager#saveTawSystemUserRefPost(TawSystemUserRefPost tawSystemUserRefPost)
     */
    public void saveTawSystemUserRefPost(TawSystemUserRefPost tawSystemUserRefPost) {
        dao.saveTawSystemUserRefPost(tawSystemUserRefPost);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemUserRefPostManager#removeTawSystemUserRefPost(String id)
     */
    public void removeTawSystemUserRefPost(final String id) {
        dao.removeTawSystemUserRefPost(new Long(id));
    }
    /**
     * 
     */
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemUserRefPosts(curPage, pageSize,null);
    }
    public Map getTawSystemUserRefPosts(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemUserRefPosts(curPage, pageSize, whereStr);
    }
}
