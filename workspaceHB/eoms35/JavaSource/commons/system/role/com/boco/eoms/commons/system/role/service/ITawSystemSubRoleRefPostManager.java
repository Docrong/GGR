/*
 * Created on 2008-3-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRoleRefPost;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ITawSystemSubRoleRefPostManager extends Manager{
	/**
     * Saves a tawSystemDeptRefPost's information
     * @param tawSystemDeptRefPost the object to be saved
     */    
    public void saveTawSystemSubRoleRefPost(TawSystemSubRoleRefPost tawSystemSubRoleRefPost);
    
    /**
     * @param subRoleid
     * @return <TawSystemPost>
     */
    public List getPostBySubRoleId(String subRoleid);
    /**
     * 
     * @param postId
     * @return <TawSystemSubRole>
     */
    public List getSubRoleByPostId(long postId);
    public void removeSubRoleRefPostByPostId(long postId);
    public void removeSubSoleRefPostBySubRoleId(String subRoleId);
}
