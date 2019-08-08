
package com.boco.eoms.sheet.modifytime.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;

public interface IModifyTimeDao extends Dao {

    /**
     * Retrieves all of the modifytimes
     */
    public List getModifyTimes();

    /**
     * Gets modifytime's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the modifytime's id
     * @return modifytime populated modifytime object
     */
    public ModifyTime getModifyTime(final String id);

    /**
     * Saves a modifytime's information
     *
     * @param modifytime the object to be saved
     */
    public void saveModifyTime(ModifyTime modifytime);

    /**
     * Removes a modifytime from the database by id
     *
     * @param id the modifytime's id
     */
    public void removeModifyTime(final String id);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     */
    public Map getModifyTimes(final Integer curPage, final Integer pageSize);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getModifyTimes(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据父节点查询下级子节点
     *
     * @param parentId 子节点中parentId字段即父节点id
     */
    public ArrayList getChildList(String parentId);

    /**
     * 根据查询条件查询列表
     *
     * @param condition
     * @return
     */
    public List getModifyTimesByCondition(String condition);
}

