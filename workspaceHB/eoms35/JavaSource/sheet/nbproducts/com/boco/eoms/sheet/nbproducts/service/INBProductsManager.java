
package com.boco.eoms.sheet.nbproducts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;

public interface INBProductsManager extends Manager {
    /**
     * Retrieves all of the nbproductss
     */
    public List getNBProductss();

    /**
     * Retrieves all of the deleted nbproductss
     */
    public List getNBProductssDeleted();


    /**
     * Gets nbproducts's information based on id.
     *
     * @param id the nbproducts's id
     * @return nbproducts populated nbproducts object
     */
    public NBProducts getNBProducts(final String id);

    /**
     * Saves a nbproducts's information
     *
     * @param nbproducts the object to be saved
     */
    public void saveNBProducts(NBProducts nbproducts);

    /**
     * Removes a nbproducts from the database by id
     *
     * @param id the nbproducts's id
     */
    public void removeNBProducts(final String id);

    /**
     * restore a nbproducts from the database by id
     *
     * @param id the nbproducts's id
     */
    public void restoreNBProducts(final String id);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     */
    public Map getNBProductss(final Integer curPage, final Integer pageSize);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getNBProductss(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据父节点查询下级子节点
     *
     * @param parentId 子节点中parentId字段即父节点id
     */
    public List getChildList(String parentId);

    /**
     * 根据父节点id查询下一级节点，返回子节点的JSON数据，一般用于树图的展现
     *
     * @param parentId
     */
    public JSONArray xGetChildNodes(String parentId);

    /**
     * 根据查询条件查询列表
     *
     * @param condition
     * @return
     */
    public List getNBProductssByProCode(String proCode, String moduleName);


    public List getNBProductssByTxtwords(Integer newDeleted, String txtwords, String moduleName);

    //  select maxcode
    public String getMaxCode();
}

