
package com.boco.eoms.workbench.report.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.report.model.TawWorkbenchReport;

public interface ITawWorkbenchReportDao extends Dao {

    /**
     * Retrieves all of the tawWorkbenchReports
     */
    public List getTawWorkbenchReports(TawWorkbenchReport tawWorkbenchReport);

    /**
     * Gets tawWorkbenchReport's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawWorkbenchReport's id
     * @return tawWorkbenchReport populated tawWorkbenchReport object
     */
    public TawWorkbenchReport getTawWorkbenchReport(final String id);

    /**
     * Saves a tawWorkbenchReport's information
     * @param tawWorkbenchReport the object to be saved
     */    
    public void saveTawWorkbenchReport(TawWorkbenchReport tawWorkbenchReport);

    /**
     * Removes a tawWorkbenchReport from the database by id
     * @param id the tawWorkbenchReport's id
     */
    public void removeTawWorkbenchReport(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawWorkbenchReports(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawWorkbenchReports(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

