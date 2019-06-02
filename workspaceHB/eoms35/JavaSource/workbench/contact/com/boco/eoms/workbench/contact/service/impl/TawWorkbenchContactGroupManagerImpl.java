
package com.boco.eoms.workbench.contact.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager;
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
public class TawWorkbenchContactGroupManagerImpl extends BaseManager implements ITawWorkbenchContactGroupManager {
    private TawWorkbenchContactGroupDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawWorkbenchContactGroupDao(TawWorkbenchContactGroupDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#getTawWorkbenchContactGroups(com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup)
     */
    public List getTawWorkbenchContactGroups(final TawWorkbenchContactGroup tawWorkbenchContactGroup) {
        return dao.getTawWorkbenchContactGroups(tawWorkbenchContactGroup);
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#getTawWorkbenchContactGroup(String id)
     */
    public TawWorkbenchContactGroup getTawWorkbenchContactGroup(final String id) {
        return dao.getTawWorkbenchContactGroup(new String(id));
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#saveTawWorkbenchContactGroup(TawWorkbenchContactGroup tawWorkbenchContactGroup)
     */
    public void saveTawWorkbenchContactGroup(TawWorkbenchContactGroup tawWorkbenchContactGroup) {
        dao.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#removeTawWorkbenchContactGroup(String id)
     */
    public void removeTawWorkbenchContactGroup(final String id) {
        dao.removeTawWorkbenchContactGroup(new String(id));
    }
    /**
     * 
     */
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize) {
        return dao.getTawWorkbenchContactGroups(curPage, pageSize,null);
    }
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawWorkbenchContactGroups(curPage, pageSize, whereStr);
    }
    public List getSonsById(String parentid) {
		return dao.getSonsById(parentid);
	}
    public List getNextLevecGroups(String nodid ,String user_id, String deleted){
		return dao.getNextLevecGroups(nodid,user_id,deleted);
	}
    public int getMaxGroupId(){
    	return dao.getMaxGroupId();
    } 
    public TawWorkbenchContactGroup getTawWorkbenchContactGroupById(final String id){
    	return dao.getTawWorkbenchContactGroupById(id);
    }


   
}
