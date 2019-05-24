package com.boco.eoms.commons.system.dict.dao;

import java.util.List;

import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.model.IDict;
import com.boco.eoms.commons.system.dict.model.IDictItem;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 9:47:27
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDictDao {
    /**
     * 取字典中某类列表
     * 
     * @param dictId
     *            字典中某类别
     * @return 字典中列表
     */
    public List findItemList(Object dictId) throws DictDAOException;

    /**
     * 通过类别+唯一标识取出某字典对象
     * 
     * @param dictId
     *            类别
     * @param itemId
     *            唯一标识
     * @return 字典对象
     */
    public IDictItem findItem(Object dictId, Object itemId)
            throws DictDAOException;

    /**
     * 取某类字典
     * 
     * @param dictId
     *            字典id
     * @return
     * @throws DictDAOException
     */
    public IDict findDict(Object dictId) throws DictDAOException;

}
