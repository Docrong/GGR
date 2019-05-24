package com.boco.eoms.commons.file.exception;


/**
 * <p>
 * Title: 所有文件导入exception 基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 9:27:00 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMImportFileException extends FMException {

	public FMImportFileException() {
		super();
	}

	public FMImportFileException(String errorMessage) {
		super(errorMessage);
	}

	public FMImportFileException(Throwable cause) {
		super(cause);
	}

}
