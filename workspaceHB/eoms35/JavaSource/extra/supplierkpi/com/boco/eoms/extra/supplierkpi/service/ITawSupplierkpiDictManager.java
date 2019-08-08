package com.boco.eoms.extra.supplierkpi.service;

import java.util.ArrayList;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;

/**
 * <p>
 * Title:供应商字典接口类
 * </p>
 * <p>
 * Description:供应商字典接口类
 * </p>
 * <p>
 * Date:2008-7-30 下午01:50:50
 * </p>
 *
 * @author 李秋野
 * @version 3.5.1
 */
public interface ITawSupplierkpiDictManager extends Manager {

    /**
     * 根据字典ID查询字典名称
     *
     * @param dictId 字典ID
     * @return
     */
    public TawSupplierkpiDict getDictByDictId(String dictId);

    /**
     * 保存字典项
     *
     * @param tawSupplierkpiDict 字典项
     */
    public void saveTawSupplierkpiDict(TawSupplierkpiDict tawSupplierkpiDict);

    /**
     * 根据字典ID删除字典项
     *
     * @param dictId 字典ID
     */
    public void delDictByDictId(String dictId);

    /**
     * 查询下一级信息
     *
     * @param parentDictId 父节点ID
     * @return
     */
    public ArrayList getDictSonsByDictId(String parentDictId);

    /**
     * 查询最大的字典ID
     *
     * @param parentDictId 父节点ID
     * @return
     */
    public String getMaxDictid(String parentDictId);

    /**
     * 判断是否有相同级别的字典类型
     *
     * @param parentDictId 父节点ID
     * @return
     */
    public boolean isHaveSameLevel(String parentDictId);

    /**
     * 更新某字典类型的叶子节点
     *
     * @param dictid
     * @param leaf
     */
    public void updateParentDictLeaf(String dictId, String leaf);

    /**
     * 根据字典Id返回名称
     *
     * @param dictId
     * @return
     */
    public String id2Name(String dictId);

}
