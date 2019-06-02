
package com.boco.eoms.parter.baseinfo.carmgr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;
import com.boco.eoms.parter.baseinfo.carmgr.webapp.form.CarMgrForm;

public interface ICarMgrDao extends Dao {

    /**
     * Retrieves all of the carMgrs
     */
    public List getCarMgrs(CarMgr carMgr);

    /**
     * Gets carMgr's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the carMgr's id
     * @return carMgr populated carMgr object
     */
    public CarMgr getCarMgr(final String id);

    /**
     * Saves a carMgr's information
     * @param carMgr the object to be saved
     */    
    public void saveCarMgr(CarMgr carMgr);

    /**
     * Removes a carMgr from the database by id
     * @param id the carMgr's id
     */
    public void removeCarMgr(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getCarMgrs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getCarMgrs(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
    
}

