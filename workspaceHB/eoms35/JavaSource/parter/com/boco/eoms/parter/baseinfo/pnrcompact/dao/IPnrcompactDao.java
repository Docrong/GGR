
package com.boco.eoms.parter.baseinfo.pnrcompact.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;

public interface IPnrcompactDao extends Dao {

    /**
     * Retrieves all of the pnrcompacts
     */
    public List getPnrcompacts(Pnrcompact pnrcompact);

    /**
     * Gets pnrcompact's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the pnrcompact's id
     * @return pnrcompact populated pnrcompact object
     */
    public Pnrcompact getPnrcompact(final String id);

    /**
     * Saves a pnrcompact's information
     * @param pnrcompact the object to be saved
     */    
    public void savePnrcompact(Pnrcompact pnrcompact);

    /**
     * Removes a pnrcompact from the database by id
     * @param id the pnrcompact's id
     */
    public void removePnrcompact(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getPnrcompacts(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getPnrcompacts(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
}

