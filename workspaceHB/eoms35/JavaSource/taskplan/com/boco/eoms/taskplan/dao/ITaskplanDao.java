
package com.boco.eoms.taskplan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.taskplan.model.Taskplan;

public interface ITaskplanDao extends Dao {

    /**
     * Retrieves all of the taskplans
     */
    public List getTaskplans(Taskplan taskplan);

    /**
     * Gets taskplan's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the taskplan's id
     * @return taskplan populated taskplan object
     */
    public Taskplan getTaskplan(final String id);

    /**
     * Saves a taskplan's information
     * @param taskplan the object to be saved
     */    
    public void saveTaskplan(Taskplan taskplan);

    /**
     * Removes a taskplan from the database by id
     * @param id the taskplan's id
     */
    public void removeTaskplan(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTaskplans(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTaskplans(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

