package com.boco.eoms.commons.system.dict.dao;

import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

/**
 * <p>
 * Title:id转成名称
 * </p>
 * <p>
 * Description:当某个表，如用户表，userId要转换成userName，这时需要在UserDAOImpl中实现该接口，并实现该方法
 * </p>
 * <p>
 * Date:2007-10-19 9:47:17
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public interface ID2NameDAO {
    /**
     * id转name
     *
     * @param id 一般为表中的主键
     * @return 返回主键对应的name(自定义)
     * @throws DictDAOException
     */
    public String id2Name(String id) throws DictDAOException;


}
