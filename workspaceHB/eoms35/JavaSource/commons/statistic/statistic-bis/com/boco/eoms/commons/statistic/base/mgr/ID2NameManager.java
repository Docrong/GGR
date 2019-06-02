package com.boco.eoms.commons.statistic.base.mgr;

import com.boco.eoms.commons.statistic.base.exception.Id2NameStatException;

/**
 * 通过id转换为需要的名称
 * 
 * @author lizhenyou
 *
 */
public interface ID2NameManager
{
	 /**
     * id转name
     * 
     * @param id
     *            一般为表中的主键
     * @param beanId
     *            对应dao(表)的beanId
     * @return 返回主键对应的name(自定义)
     * @throws BusinessException
     */
	public String id2Name(String id, String beanId);
	
	/**
	 * 根据字典id和type得到唯一的名字
	 * 
	 * @param id 字典的id
	 * @param type 字典的类型
	 * @return
	 * @throws Id2NameStatException
	 */
	public String idType2Name(String id,String type, String beanId) throws Id2NameStatException;
}
