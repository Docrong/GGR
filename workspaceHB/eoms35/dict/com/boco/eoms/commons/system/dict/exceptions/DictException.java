package com.boco.eoms.commons.system.dict.exceptions;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title:字典exception基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 10:00:48
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictException extends BusinessException {

    /**
     *  
     */
    public DictException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param errorMessage
     */
    public DictException(String errorMessage) {
        super(errorMessage);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public DictException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
