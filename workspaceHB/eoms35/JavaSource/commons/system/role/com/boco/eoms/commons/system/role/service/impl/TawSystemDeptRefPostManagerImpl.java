
package com.boco.eoms.commons.system.role.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost;
import com.boco.eoms.commons.system.role.dao.TawSystemDeptRefPostDao;
import com.boco.eoms.commons.system.role.service.ITawSystemDeptRefPostManager;

public class TawSystemDeptRefPostManagerImpl extends BaseManager implements ITawSystemDeptRefPostManager {
    private TawSystemDeptRefPostDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemDeptRefPostDao(TawSystemDeptRefPostDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemDeptRefPostManager#getTawSystemDeptRefPosts(com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost)
     */
    public List getTawSystemDeptRefPosts(final TawSystemDeptRefPost tawSystemDeptRefPost) {
        return dao.getTawSystemDeptRefPosts(tawSystemDeptRefPost);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemDeptRefPostManager#getTawSystemDeptRefPost(String id)
     */
    public TawSystemDeptRefPost getTawSystemDeptRefPost(final String id) {
        return dao.getTawSystemDeptRefPost(new Long(id));
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemDeptRefPostManager#saveTawSystemDeptRefPost(TawSystemDeptRefPost tawSystemDeptRefPost)
     */
    public void saveTawSystemDeptRefPost(TawSystemDeptRefPost tawSystemDeptRefPost) {
        dao.saveTawSystemDeptRefPost(tawSystemDeptRefPost);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemDeptRefPostManager#removeTawSystemDeptRefPost(String id)
     */
    public void removeTawSystemDeptRefPost(final String id) {
        dao.removeTawSystemDeptRefPost(new Long(id));
    }
    /**
     * 
     */
    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemDeptRefPosts(curPage, pageSize,null);
    }
    public Map getTawSystemDeptRefPosts(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemDeptRefPosts(curPage, pageSize, whereStr);
    }
    public List getPostByDeptId(String deptId){
    	return dao.getPostByDeptId(deptId);
    }
}
