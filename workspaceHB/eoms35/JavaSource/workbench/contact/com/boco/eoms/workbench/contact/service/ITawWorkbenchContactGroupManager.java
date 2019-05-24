
package com.boco.eoms.workbench.contact.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao;
/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */
public interface ITawWorkbenchContactGroupManager extends Manager {
    /**
     * Retrieves all of the tawWorkbenchContactGroups
     */
    public List getTawWorkbenchContactGroups(TawWorkbenchContactGroup tawWorkbenchContactGroup);

    /**
     * Gets tawWorkbenchContactGroup's information based on id.
     * @param id the tawWorkbenchContactGroup's id
     * @return tawWorkbenchContactGroup populated tawWorkbenchContactGroup object
     */
    public TawWorkbenchContactGroup getTawWorkbenchContactGroup(final String id);

    /**
     * Saves a tawWorkbenchContactGroup's information
     * @param tawWorkbenchContactGroup the object to be saved
     */
    public void saveTawWorkbenchContactGroup(TawWorkbenchContactGroup tawWorkbenchContactGroup);
    /*
     *根据人员id得到这个人的所有组
     */
    public List getNextLevecGroups(String nodeId,String user_id, String deleted);
    /**
     * Removes a tawWorkbenchContactGroup from the database by id
     * @param id the tawWorkbenchContactGroup's id
     */
    public void removeTawWorkbenchContactGroup(final String id);
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize);
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getSonsById(String parentid);
    
    // 得到组的最大数
    public int getMaxGroupId();
    public TawWorkbenchContactGroup getTawWorkbenchContactGroupById(final String id);
   
}

