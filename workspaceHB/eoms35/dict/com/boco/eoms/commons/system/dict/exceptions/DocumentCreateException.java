package com.boco.eoms.commons.system.dict.exceptions;

/**
 * <p>
 * Title:创建document时抛出的异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 14:01:39
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DocumentCreateException extends DictException {

    /**
     *  
     */
    public DocumentCreateException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param errorMessage
     */
    public DocumentCreateException(String errorMessage) {
        super(errorMessage);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public DocumentCreateException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
