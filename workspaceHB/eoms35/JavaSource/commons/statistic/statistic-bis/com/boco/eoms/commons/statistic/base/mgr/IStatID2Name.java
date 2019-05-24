package com.boco.eoms.commons.statistic.base.mgr;

import com.boco.eoms.commons.statistic.base.exception.Id2NameDAOException;
import com.boco.eoms.commons.statistic.base.exception.Id2NameStatException;


/**
 * 通过id转换为需要的名称
 * 
 * @author lizhenyou
 *
 */
public interface IStatID2Name 
{
	/**
     * id转name
     * 
     * @param id
     *            一般为表中的主键
     * @return 返回主键对应的name(自定义)
     * @throws DictDAOException
     */
    public String id2Name(String id) throws Id2NameStatException;
    
    public String idType2Name(String id,String type) throws Id2NameDAOException;

}
