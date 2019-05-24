package com.boco.eoms.commons.system.dict.dao;

import java.util.List;

import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.IDictRelation;
import com.boco.eoms.commons.system.dict.model.IDictRelationItem;

/**
 * <p>
 * Title: 操作关联关系
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-25 16:29:10
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDictRelationDao {
    /**
     * 通过关联id获取关联关系
     * 
     * @param relationId
     *            关联id
     * @return 返回关联关系
     * @throws DictDAOException
     */
    public IDictRelation findRelation(Object relationId)
            throws DictDAOException;

    /**
     * 通过关联关系id+源字典项id获取关联关系
     * 
     * @param relationId
     * @param sourceItemId
     * @return
     * @throws DictServiceException
     */
    public IDictRelationItem findRelationItem(Object relationId,
            Object sourceItemId) throws DictDAOException;

    /**
     * 通过关联关系id+取所有关联子项
     * 
     * @param relationId
     * @param sourceItemId
     * @return
     * @throws DictServiceException
     */
    public List findRelationItems(Object relationId) throws DictDAOException;

    /**
     * 通过relationId取关联关系，关系中不包括关联项内容
     * 
     * @param relationId
     * @return
     * @throws DictDAOException
     */
    public IDictRelation findRelationButItems(Object relationId)
            throws DictDAOException;

}
