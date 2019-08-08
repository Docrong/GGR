package com.boco.eoms.commons.system.dict.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.system.dict.dao.IDictDao;
import com.boco.eoms.commons.system.dict.dao.IDictRelationDao;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.IDict;
import com.boco.eoms.commons.system.dict.model.IDictItem;
import com.boco.eoms.commons.system.dict.model.IDictRelation;
import com.boco.eoms.commons.system.dict.model.IDictRelationItem;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 10:04:17
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class DictServiceImpl implements IDictService {

    private Logger logger = Logger.getLogger(DictServiceImpl.class);

    /**
     * 字典访问对象，可为xml,db访问或更多持久层技术
     */
    private IDictDao dictDao;

    /**
     * 字典关系操作,可为xml,db技术
     */
    private IDictRelationDao dictRelationDao;

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getDict(java.lang.Object,
     *      java.lang.Object)
     */
    public IDictItem getDictItem(Object dictId, Object itemId)
            throws DictServiceException {
        IDictItem dict;
        try {
            dict = this.dictDao.findItem(dictId, itemId);
        } catch (DictDAOException e) {
            throw new DictServiceException(e);
        }
        return dict;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getList(java.lang.Object)
     */
    public List getDictItems(Object dictId) throws DictServiceException {
        List list = null;
        try {
            list = this.dictDao.findItemList(dictId);
        } catch (DictDAOException e) {
            System.out.println(e.getMessage());
            throw new DictServiceException(e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#id2description(java.lang.Object,
     *      java.lang.Object)
     */
    public Object itemId2description(Object dictId, Object itemId)
            throws DictServiceException {
        IDictItem dict = this.getDictItem(dictId, itemId);
        return dict != null ? dict.getItemDescription() : "";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#id2name(java.lang.Object,
     *      java.lang.Object)
     */
    public Object itemId2name(Object dictId, Object itemId)
            throws DictServiceException {
        IDictItem dict = this.getDictItem(dictId, itemId);
        return dict != null ? dict.getItemName() : "";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#setDictDao(com.boco.eoms.commons.system.dict.dao.IDictDao)
     */
    public void setDictDao(IDictDao dictDao) throws DictServiceException {
        this.dictDao = dictDao;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#type2description(java.lang.Object)
     */
    public Object dictId2description(Object dictId) throws DictServiceException {
        IDict dict;
        try {
            dict = dictDao.findDict(dictId);
        } catch (DictDAOException e) {
            throw new DictServiceException(e);
        }
        return dict.getDictDescription();
    }

    /**
     * @param dictRelationDao the dictRelationDao to set
     */
    public void setDictRelationDao(IDictRelationDao dictRelationDao) {
        this.dictRelationDao = dictRelationDao;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getRelationItems(java.lang.Object)
     */
    public List getRelationItems(Object relationId) throws DictServiceException {
        try {
            return this.dictRelationDao.findRelationItems(relationId);
        } catch (DictDAOException e) {
            logger.error(e);
            throw new DictServiceException(e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getRelationItem(java.lang.Object,
     *      java.lang.Object)
     */
    public IDictRelationItem getRelationItem(Object relationId,
                                             Object sourceItemId) throws DictServiceException {
        try {
            return this.dictRelationDao.findRelationItem(relationId,
                    sourceItemId);
        } catch (DictDAOException e) {
            logger.error(e);
            throw new DictServiceException(e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getRelationButItems(java.lang.Object)
     */
    public IDictRelation getRelationButItems(Object relationId)
            throws DictServiceException {
        try {
            return this.dictRelationDao.findRelationButItems(relationId);
        } catch (DictDAOException e) {
            logger.error(e);
            throw new DictServiceException(e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getRelatedList(java.lang.String,
     *      java.lang.String)
     */
    public List getRelatedList(String id, String relationId)
            throws DictServiceException {
        List list = new ArrayList();
        IDictRelationItem item = null;
        try {
            //取关联关系
            item = this.dictRelationDao.findRelationItem(relationId, id);
        } catch (DictDAOException e) {
            logger.error(e);
            //若关联关系都取不到则直接返回一个empty list
            return list;
        }
        if (item != null
                && (item.getDestinationItemIds() != null || !"".equals(item
                .getDestinationItemIds().trim()))) {
            //去掉分隔符转成数组
            String[] items = item.getDestinationItemIds().split(
                    Util.getDestinationItemIdsSplit());
            //取映射后的结果
            list = this.getDictItems(Util.constituteDictId(item
                            .getDestinationDictKey(), item.getDestinationDictId()),
                    items);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getDictItems(java.lang.String,
     *      java.lang.String[])
     */
    public List getDictItems(String dictId, String[] itemIds)
            throws DictServiceException {
        //结果
        List list = new ArrayList();
        //若为空则直接返回list
        if (itemIds != null && itemIds.length > 0) {
            //遍历ids
            for (int i = 0; i < itemIds.length; i++) {
                try {
                    //通过id取 dictItem(字典项)
                    IDictItem item = this.dictDao.findItem(dictId, itemIds[i]);
                    //加入结果
                    list.add(item);
                } catch (DictDAOException e) {
                    logger.error(e);
                }
            }
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.service.IDictService#getDictItems(java.lang.String)
     */
    public List getDictItemsForRelation(String relationId) throws DictServiceException {
        IDictRelation relation = this.getRelationButItems(relationId);
        List list = this.getDictItems(Util.constituteDictId(relation
                .getSourceDictKey(), relation.getSourceDictId()));
        return list;
    }
}
