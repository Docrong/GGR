package com.boco.eoms.commons.system.dict.dao;

import com.boco.eoms.base.dao.Dao;

/**
 *
 *panlong
 *下午09:11:51
 */
public interface ITawSystemDictJDBC extends Dao {

	/**
	 * 查询最大的字典ID
	 * @param pardictid
	 * @param len
	 * @return
	 */
	public String getMaxDeptid(String pardictid,int len) ;
	
	/**
	 * 查询最大的字典ID，版本发布时，改为降序
	 * @param pardictid
	 * @param len
	 * @return
	 */
	public String getMaxDeptidDESC(String pardictid,int len) ;
	
	/**
	 * 根据字典ID删除记录
	 * @param dictid
	 */
	public void removeDictByDictid(String dictid); 
	
	   /**
     * 更新某字典类型的叶子节点
     * @param dictid
     * @param leaf
     */
    public void updateParentDictLeaf(String dictid,String leaf);
}

