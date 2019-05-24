
package com.boco.eoms.extra.supplierkpi.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;

public interface ITawSupplierkpiItemManager extends Manager {
    /**
     * Retrieves all of the tawSupplierkpiItems
     */
    public List getTawSupplierkpiItems(TawSupplierkpiItem tawSupplierkpiItem);

    /**
     * Gets tawSupplierkpiItem's information based on id.
     * @param id the tawSupplierkpiItem's id
     * @return tawSupplierkpiItem populated tawSupplierkpiItem object
     */
    public TawSupplierkpiItem getTawSupplierkpiItem(final String id, final int deleted);

    /**
     * Saves a tawSupplierkpiItem's information
     * @param tawSupplierkpiItem the object to be saved
     */
    public void saveTawSupplierkpiItem(TawSupplierkpiItem tawSupplierkpiItem);

    /**
     * Removes a tawSupplierkpiItem from the database by id
     * @param id the tawSupplierkpiItem's id
     */
    public void removeTawSupplierkpiItem(final String id);
    public Map getTawSupplierkpiItems(final int curPage, final int pageSize);
    public Map getTawSupplierkpiItems(final int curPage, final int pageSize, final String whereStr);
    
    public List getSpecialkpi(final String specialType);
    public List getSpecialkpiJSON(final String specialType);
    
    /**
     * 先保存字典，根据字典id再保存kpi项
     * @param tawSystemDictType
     * @param tawSupplierkpiItem
     */
    public String saveDictAndKpiItem(TawSupplierkpiDict tawSupplierkpiDict, TawSupplierkpiItem tawSupplierkpiItem);
    
    /**
     * 根据字典id删除字典项，再删除kpi项
     * @param dictId
     */
    public void removeDictAndKpiItem(String dictId);
    
    public TawSupplierkpiItem getItemByItemType(final String dictType);
    /**
     * 假删除
     * @param dictId
     */
    public void removeItem(final TawSupplierkpiItem tawSupplierkpiItem);
    
    /**
     * 根据条件查询指标
     * @param whereStr
     * @return
     */
    public List getItems(final String whereStr);
}

