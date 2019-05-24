/*
 * Created on 2008-1-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.priv.exception;

/**
 * @author panlong
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TawSystemPrivOperationException extends PrivBaseException {

	public TawSystemPrivOperationException() {
		this("TawSystemPrivOperationException!");
	}

	public TawSystemPrivOperationException(String errromessage) {
		super(errromessage);
	}
}
