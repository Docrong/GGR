/*
 * Created on 2007-8-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.prm.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p>
 * Title:属性名空异常
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-6 21:16:48
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class PropertyNameIsNullException extends PRMException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1064790562459462146L;

	public PropertyNameIsNullException(String msg) {
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
