/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dict;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 11:26:43
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class OperateDict extends DictImpl {
    /**
     * 派单
     */
    public final static String OPERATE_SEND = "1";

    /**
     * 转派
     */
    public final static String OPERATE_SEND_ANOTHER = "2";

    /**
     * @param description
     * @param name
     * @param value
     */
    public OperateDict(String description, String name, String value) {
        super(description, name, value);
    }
}
