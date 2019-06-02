package com.boco.eoms.km.ask.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import com.boco.eoms.km.ask.model.KmAskSpeciality;

/**
 * <p>
 * Title:问答类型
 * </p>
 * <p>
 * Description:问答类型
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public interface KmAskSpecialityDao extends Dao {
	
	/**
    * 根据主键查询问答类型
    * @param id 主键
    * @return 返回某id的问答类型
    */
    public KmAskSpeciality getKmAskSpeciality(final String id);
	
    /**
    *
    * 保存问答类型    
    * @param kmAskSpeciality 问答类型
    * 
    */
    public void saveKmAskSpeciality(KmAskSpeciality kmAskSpeciality);
	
	/**
	 * 根据节点id查询问答类型
	 * 
	 * @param nodeId
	 *            节点id
	 * @return 返回某节点id的对象
	 */
	public KmAskSpeciality getKmAskSpecialityByNodeId(final String nodeId);
	
	/**
	 * 根据节点id删除问答类型
	 * 
	 * @param nodeId
	 *            节点id
	 */
	public void removeKmAskSpecialityByNodeId(final String nodeId);
	
	/**
	 * 查询下一级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 下级节点列表
	 */
	public List getNextLevelKmAskSpecialitys(final String parentNodeId);
	
	/**
	 * 生成一个可用的节点id
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @param length
	 *            节点长度
	 * @return
	 */
	public String getUsableNodeId(final String parentNodeId, final int length);
	
	/**
	 * 查询所有子节点（按nodeId排序）
	 * 
	 * @param parentNodeId
	 *            父节点id
	 */
	public List getChildNodes(final String parentNodeId);
}