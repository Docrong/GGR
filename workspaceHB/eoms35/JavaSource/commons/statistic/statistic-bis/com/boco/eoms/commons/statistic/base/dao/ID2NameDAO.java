package com.boco.eoms.commons.statistic.base.dao;

import com.boco.eoms.commons.statistic.base.exception.Id2NameDAOException;

//import com.boco.eoms.commons.statistic.base.exception.Id2NameDAOException;


/**
 * 通过id转换为需要的名称
 *
 * @author lizhenyou
 *
 */
public interface ID2NameDAO
{
	/**
     * id转name
     *
     * @param id
     *            �\uFFFD般为表中的主�\uFFFD
     * @return 返回主键对应的name(自定�\uFFFD)
     * @throws DictDAOException
     */
    public String id2Name(String id) throws Exception;
    
    /**
     * id转name 
     * @param id  id值
     * @param type 类型值（字典类型）
     * @return
     * @throws Id2NameDAOException
     */
    public String idType2Name(String id,String type) throws Id2NameDAOException;

}
