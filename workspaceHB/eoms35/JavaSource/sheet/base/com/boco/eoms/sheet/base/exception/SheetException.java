/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.boco.eoms.commons.exceptions.base.BusinessException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 16:25:18
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class SheetException extends BusinessException {
    public SheetException(Throwable cause) {
        super(cause);
    }

    public SheetException(String msg) {
        super(msg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage() {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace()
     */
    public void printStackTrace() {
        // TODO Auto-generated method stub
        super.printStackTrace();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
     */
    public void printStackTrace(PrintStream s) {
        // TODO Auto-generated method stub
        super.printStackTrace(s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     */
    public void printStackTrace(PrintWriter w) {
        // TODO Auto-generated method stub
        super.printStackTrace(w);
    }
}
