
package com.boco.eoms.commons.system.role.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemRoleRefWorkflow;

public interface TawSystemRoleRefWorkflowDao extends Dao {

    /**
     * Retrieves all of the tawSystemRoleRefWorkflows
     */
    public List getTawSystemRoleRefWorkflows(TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow);

    /**
     * Gets tawSystemRoleRefWorkflow's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the tawSystemRoleRefWorkflow's id
     * @return tawSystemRoleRefWorkflow populated tawSystemRoleRefWorkflow object
     */
    public TawSystemRoleRefWorkflow getTawSystemRoleRefWorkflow(final String id);

    /**
     * Saves a tawSystemRoleRefWorkflow's information
     *
     * @param tawSystemRoleRefWorkflow the object to be saved
     */
    public void saveTawSystemRoleRefWorkflow(TawSystemRoleRefWorkflow tawSystemRoleRefWorkflow);

    /**
     * Removes a tawSystemRoleRefWorkflow from the database by id
     *
     * @param id the tawSystemRoleRefWorkflow's id
     */
    public void removeTawSystemRoleRefWorkflow(final String id);

    /**
     * curPage
     * pageSize
     */
    public Map getTawSystemRoleRefWorkflows(final Integer curPage, final Integer pageSize);

    public Map getTawSystemRoleRefWorkflows(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据流程名称获取有新增权限的大类角色
     *
     * @param flowName 流程名
     * @param type     0自启动；1外部流程启动
     * @return <TawSystemRole>
     */
    public List getRoleBySheetName(String startFlowName, String flowName);
}

