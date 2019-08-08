package com.boco.eoms.commons.system.dict.service;

/**
 * <p>
 * Title:id2name
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-19 10:39:32
 * </p>
 *
 * @author 曲静波
 * @version 0.1
 */
public interface ID2NameService {
    /**
     * id转name
     *
     * @param id     一般为表中的主键
     * @param beanId 对应dao(表)的beanId
     * @return 返回主键对应的name(自定义)
     * @throws BusinessException
     * @since 0.1
     */
    public String id2Name(String id, String beanId);
}
