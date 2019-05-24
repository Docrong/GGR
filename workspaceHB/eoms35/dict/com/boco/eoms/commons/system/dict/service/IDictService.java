package com.boco.eoms.commons.system.dict.service;

import java.util.List;

import com.boco.eoms.commons.system.dict.dao.IDictDao;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.IDictItem;
import com.boco.eoms.commons.system.dict.model.IDictRelation;
import com.boco.eoms.commons.system.dict.model.IDictRelationItem;

/**
 * <p>
 * Title:对自典的操作接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-23 19:42:44
 * </p>
 * 
 * @author 曲静波
 * @version 0.1
 * 
 */
public interface IDictService {
	/**
	 * 唯一标识转成名称
	 * 
	 * @param itemId
	 *            唯一标识
	 * @throws DictServiceException
	 * @return 名称
	 * @since 0.1
	 */
	public Object itemId2name(Object dictId, Object itemId)
			throws DictServiceException;

	/**
	 * 唯一标识转成描述信息
	 * 
	 * @param dictId
	 *            字典中的分类
	 * @param itemId
	 *            唯一标识 type+id=定位某一项
	 * @throws DictServiceException
	 * @return 描述信息
	 * @since 0.1
	 */
	public Object itemId2description(Object dictId, Object itemId)
			throws DictServiceException;

	/**
	 * type+id 获取字典对象
	 * 
	 * @param dictId
	 *            字典中的分类
	 * @param itemId
	 *            唯一标识 type+id=定位某一项
	 * @throws DictServiceException
	 * @return 字典项
	 * @since 0.1
	 */
	public IDictItem getDictItem(Object dictId, Object itemId)
			throws DictServiceException;

	/**
	 * 取某类字典列表
	 * 
	 * @param dictId
	 *            字典中的分类
	 * @throws DictServiceException
	 * @return 字典项列表
	 * @since 0.1
	 */
	public List getDictItems(Object dictId) throws DictServiceException;

	/**
	 * 该类别的描述
	 * 
	 * @param dictId
	 *            类别，字典中的分类
	 * @throws DictServiceException
	 * @return 一描为描述（String）
	 * @since 0.1
	 */
	public Object dictId2description(Object dictId) throws DictServiceException;

	/**
	 * 策略模式，动态更换行为（行为即xmldao或dbdao)
	 * 
	 * @param dictDao
	 *            xmldao或dbdao或.....
	 * @throws DictServiceException
	 */
	public void setDictDao(IDictDao dictDao) throws DictServiceException;

	/**
	 * 通过关联id获取关联关系列表
	 * 
	 * @param relationId
	 *            关联id
	 * @return 返回关联关系列表
	 * @throws DictServiceException
	 * @since 0.1
	 */
	public List getRelationItems(Object relationId) throws DictServiceException;

	/**
	 * 根据关联ID取出字典项
	 * 
	 * @param relationId
	 *            关联id
	 * @param sourceItemId
	 *            源字典项id
	 * @return 关联的字典项
	 * @throws DictServiceException
	 * @since 0.1
	 */
	public IDictRelationItem getRelationItem(Object relationId,
			Object sourceItemId) throws DictServiceException;

	/**
	 * 通过relationId取关联关系，关系中不包括关联项内容
	 * 
	 * @param relationId
	 * @return 关联字典
	 * @throws DictServiceException
	 * @since 0.1
	 */
	public IDictRelation getRelationButItems(Object relationId)
			throws DictServiceException;

	/**
	 * 取出已关联的值，一般用于select 关联 select1，通过关系文件dictRelation.xml取出具体select1应显示的值
	 * 
	 * @param id
	 *            select 所选的id
	 * @param relationId
	 *            关联关系id
	 * @return 关联列表
	 * @throws DictServiceException
	 * @since 0.1
	 */
	public List getRelatedList(String id, String relationId)
			throws DictServiceException;

	/**
	 * 返回dictId字典的多个子项
	 * 
	 * @param dictId
	 *            字典唯一标识
	 * @param itemIds
	 *            多个字典子项id
	 * @return 字典项列表
	 * @throws DictServiceException
	 * @since 0.1
	 */
	public List getDictItems(String dictId, String[] itemIds)
			throws DictServiceException;

	/**
	 * 获取关系表中配置的源字典项
	 * 
	 * @param relationId
	 *            关联id
	 * @return 字典项列表
	 * @throws DictServiceException
	 * @since 0.1
	 */
	public List getDictItemsForRelation(String relationId)
			throws DictServiceException;
}
