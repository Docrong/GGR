
package com.boco.eoms.commons.system.role.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.role.model.TawSystemRoleRefWorkflow;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleRefWorkflowDao;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleRefWorkflowManager;

public class TawSystemRoleRefWorkflowManagerImpl extends BaseManager implements ITawSystemRoleRefWorkflowManager {
    private TawSystemRoleRefWorkflowDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemRoleRefWorkflowDao(TawSystemRoleRefWorkflowDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleRefWorkflowManager#getTawSystemRoleRefWorkflows(com.boco.eoms.commons.system.role.model.TawSystemRoleRefWorkflow)
     */
    public List getTawSystemRoleRefWorkflows(final TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow) {
        return dao.getTawSystemRoleRefWorkflows(tawSystemRoleRefWorkflow);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleRefWorkflowManager#getTawSystemRoleRefWorkflow(String id)
     */
    public TawSystemRoleRefWorkflow getTawSystemRoleRefWorkflow(final String id) {
        return dao.getTawSystemRoleRefWorkflow(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleRefWorkflowManager#saveTawSystemRoleRefWorkflow(TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow)
     */
    public void saveTawSystemRoleRefWorkflow(TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow) {
        dao.saveTawSystemRoleRefWorkflow(tawSystemRoleRefWorkflow);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleRefWorkflowManager#removeTawSystemRoleRefWorkflow(String id)
     */
    public void removeTawSystemRoleRefWorkflow(final String id) {
        dao.removeTawSystemRoleRefWorkflow(new String(id));
    }
    /**
     * 
     */
    public Map getTawSystemRoleRefWorkflows(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemRoleRefWorkflows(curPage, pageSize,null);
    }
    public Map getTawSystemRoleRefWorkflows(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemRoleRefWorkflows(curPage, pageSize, whereStr);
    }
    /**
     * 根据流程名称获取有新增权限的大类角色
     * @param flowName 流程名
     * @param type 0自启动；1外部流程启动
     * @return <TawSystemRole>
     */
    public List getRoleBySheetName(String startSheetName,String sheetName){
    	return dao.getRoleBySheetName(startSheetName,sheetName);
    }

	public List getTawSystemWorkflows() {
		// TODO 自动生成方法存根
		return null;
	}
}
