package com.boco.eoms.commons.system.dict.exceptions;

/**
 * <p>
 * Title:字典service exception
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 10:02:16
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictServiceException extends DictException {

    /**
     *  
     */
    public DictServiceException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param errorMessage
     */
    public DictServiceException(String errorMessage) {
        super(errorMessage);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public DictServiceException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
