package com.boco.eoms.commons.system.dict.service;

// java standard library

import java.util.ArrayList;
import java.util.List;

// eoms classes
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;

public interface ITawSystemDictTypeManager extends Manager {

    /**
     * Retrieves all of the tawSystemDictTypes
     */
    public List getTawSystemDictTypes(TawSystemDictType tawSystemDictType);

    public abstract String id2Name(String s) throws DictDAOException;

    /**
     * Gets tawSystemDictType's information based on id.
     *
     * @param id the tawSystemDictType's id
     * @return tawSystemDictType populated tawSystemDictType object
     */
    public TawSystemDictType getTawSystemDictType(final String id);

    /**
     * Saves a tawSystemDictType's information
     *
     * @param tawSystemDictType the object to be saved
     */
    public void saveTawSystemDictType(TawSystemDictType tawSystemDictType);

    /**
     * Removes a tawSystemDictType from the database by id
     *
     * @param id the tawSystemDictType's id
     */
    public void removeTawSystemDictType(final String id);


    /**
     * Get current dict type's parent type name
     *
     * @param String _strCurCode
     * @return String parent type name
     */
    public String getParentTypeName(String _strCurCode);

    /**
     * 根据字典ID查询字典名称
     */
    public TawSystemDictType getDictByDictId(String _strDictId);

    public List getworkplanDictByDictId();

    /**
     * 查询下一级信息
     *
     * @param parentdictid
     * @return
     */
    public ArrayList getDictSonsByDictid(String parentdictid);

    /**
     * 根据字id查询字典信息
     *
     * @param dictid
     * @return
     */
    public TawSystemDictType getDictTypeByDictid(String dictid);

    /**
     * 查询最大的字典ID
     *
     * @param pardictid
     * @param len
     * @return
     */
    public String getMaxDictid(String pardictid);

    /**
     * 根据字典ID删除记录
     *
     * @param dictid
     */
    public void removeDictByDictid(String dictid);

    /**
     * 判断是否有相同级别的字典类型
     *
     * @param systype
     * @return
     */
    public boolean isHaveSameLevel(String parentdictid, String systype);

    /**
     * 更新某字典类型的叶子节点
     *
     * @param dictid
     * @param leaf
     */
    public void updateParentDictLeaf(String dictid, String leaf);

    /**
     * 查询code的字典信息
     *
     * @param code
     * @return
     */
    public List getDictByCode(String code);

    /**
     * 根据 parentDictId 和 dictCode获取 TawSystemDictType
     *
     * @param tawSystemDictType
     * @param tawSystemDictCode
     * @return String dictId
     * @author liqiuye    2007-11-14
     */
    public TawSystemDictType getDictByDictType(String dictCode, String parentDictId);

    /**
     * 根据  dictCode获取 TawSystemDictType
     *
     * @param tawSystemDictCode
     * @return TawSystemDictType
     * @author liqiuye    2007-11-14
     */
    public TawSystemDictType getDictByDictType(String dictCode);

    /**
     * 判断此code的字典项是否存在(修改时除自己本身)
     *
     * @param dictCode
     * @param dictId
     * @return
     */
    public boolean isCodeExist(String dictCode, String dictId);

    /**
     * 根据code取得字典Id
     *
     * @param dict
     * @return
     */
    public String getDictIdByDictCode(String dictCode);

    /**
     * 根据code取得字典Id
     *
     * @param dict
     * @return
     */
    public String getDictIdByDictCode(String parentId, String dictCode);

    /**
     * 取某父字典下的所有子字典
     * 并子角色属于大角色ID（ROLEiD），地域id（areaId)。
     *
     * @param dictId
     * @param roleId
     * @param areaId
     * @return
     */
    public List listDict(String dictId, String roleId, String areaId);

    public TawSystemDictType getDictByDictName(String dictName, String parentDictId);
}
