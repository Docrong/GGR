
package com.boco.eoms.workbench.contact.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchDeptContactGroupDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContactGroup;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchDeptContactGroupManager;
/**
 * <p>
 * Title:部门通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 30, 2008 20:30:30 PM
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class TawWorkbenchDeptContactGroupManagerImpl extends BaseManager implements ITawWorkbenchDeptContactGroupManager {
    private TawWorkbenchDeptContactGroupDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawWorkbenchDeptContactGroupDao(TawWorkbenchDeptContactGroupDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#getTawWorkbenchContactGroups(com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup)
     */
    public List getTawWorkbenchDeptContactGroups(final TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup) {
        return dao.getTawWorkbenchDeptContactGroups(tawWorkbenchDeptContactGroup);
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#getTawWorkbenchContactGroup(String id)
     */
    public TawWorkbenchDeptContactGroup getTawWorkbenchDeptContactGroup(final String id) {
        return dao.getTawWorkbenchDeptContactGroup(new String(id));
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#saveTawWorkbenchContactGroup(TawWorkbenchContactGroup tawWorkbenchContactGroup)
     */
    public void saveTawWorkbenchDeptContactGroup(TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup) {
        dao.saveTawWorkbenchDeptContactGroup(tawWorkbenchDeptContactGroup);
    }

    /**
     * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager#removeTawWorkbenchContactGroup(String id)
     */
    public void removeTawWorkbenchDeptContactGroup(final String id) {
        dao.removeTawWorkbenchDeptContactGroup(new String(id));
    }
    /**
     * 
     */
    public Map getTawWorkbenchDeptContactGroups(final Integer curPage, final Integer pageSize) {
        return dao.getTawWorkbenchDeptContactGroups(curPage, pageSize,null);
    }
    public Map getTawWorkbenchDeptContactGroups(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawWorkbenchDeptContactGroups(curPage, pageSize, whereStr);
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
    public TawWorkbenchDeptContactGroup getTawWorkbenchDeptContactGroupById(final String id){
    	return dao.getTawWorkbenchDeptContactGroupById(id);
    }

	public List getOwnerGroup(String nodid, String userId, String deleted) {
		return dao.getOwnerGroup(nodid, userId, deleted);
	}
   
}
