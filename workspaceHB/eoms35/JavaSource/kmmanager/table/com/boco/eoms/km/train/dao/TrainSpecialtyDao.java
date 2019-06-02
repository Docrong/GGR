package com.boco.eoms.km.train.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import com.boco.eoms.km.train.model.TrainSpecialty;

/**
 * <p>
 * Title:专业分类
 * </p>
 * <p>
 * Description:专业分类
 * </p>
 * <p>
 * Thu Jul 09 10:50:06 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public interface TrainSpecialtyDao extends Dao {
	
	/**
    * 根据主键查询专业分类
    * @param id 主键
    * @return 返回某id的专业分类
    */
    public TrainSpecialty getTrainSpecialty(final String id);
	
    /**
    *
    * 保存专业分类    
    * @param trainSpecialty 专业分类
    * 
    */
    public void saveTrainSpecialty(TrainSpecialty trainSpecialty);
	
	/**
	 * 根据节点id查询专业分类
	 * 
	 * @param nodeId
	 *            节点id
	 * @return 返回某节点id的对象
	 */
	public TrainSpecialty getTrainSpecialtyByNodeId(final String nodeId);
	
	/**
	 * 根据节点id删除专业分类
	 * 
	 * @param nodeId
	 *            节点id
	 */
	public void removeTrainSpecialtyByNodeId(final String nodeId);
	
	/**
	 * 查询下一级节点
	 * 
	 * @param parentNodeId
	 *            父节点id
	 * @return 下级节点列表
	 */
	public List getNextLevelTrainSpecialtys(final String parentNodeId);
	
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