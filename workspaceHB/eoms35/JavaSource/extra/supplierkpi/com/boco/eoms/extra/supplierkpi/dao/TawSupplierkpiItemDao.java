
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;

public interface TawSupplierkpiItemDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiItems
     */
    public List getTawSupplierkpiItems(TawSupplierkpiItem tawSupplierkpiItem);

    /**
     * Gets tawSupplierkpiItem's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the tawSupplierkpiItem's id
     * @return tawSupplierkpiItem populated tawSupplierkpiItem object
     */
    public TawSupplierkpiItem getTawSupplierkpiItem(final String id, int deleted);

    /**
     * Saves a tawSupplierkpiItem's information
     *
     * @param tawSupplierkpiItem the object to be saved
     */
    public void saveTawSupplierkpiItem(TawSupplierkpiItem tawSupplierkpiItem);

    /**
     * Removes a tawSupplierkpiItem from the database by id
     *
     * @param id the tawSupplierkpiItem's id
     */
    public void removeTawSupplierkpiItem(final String id);

    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiItems(final int curPage, final int pageSize);

    public Map getTawSupplierkpiItems(final int curPage, final int pageSize, final String whereStr);

    public List getSpecialkpi(String specialType);

    public void removeItemByItemType(final String dictId);

    public TawSupplierkpiItem getItemByItemType(final String dictType);

    /**
     * 假删除
     *
     * @param dictId
     */
    public void removeItem(final TawSupplierkpiItem tawSupplierkpiItem);

    /**
     * 根据特定条件查询评估指标
     *
     * @param whereStr
     * @return
     */
    public List getItems(final String whereStr);

    public List getSpecialkpiContainDeletedRecently(final String specialType);
}

