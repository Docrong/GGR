package com.boco.eoms.workplan.util;

/**
 * <p>Title: 作业计划异常类</p>
 * <p>Description: 记录作业计划各种操作的异常信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */


public class TawwpException extends Exception {

    protected int errNo;
    protected String errInfo;

    public TawwpException(String _errInfo) {
        errInfo = _errInfo;
    }

    public String getMessage() {
        return errInfo;
    }


}
