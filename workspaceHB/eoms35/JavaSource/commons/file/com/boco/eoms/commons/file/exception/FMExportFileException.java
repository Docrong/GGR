package com.boco.eoms.commons.file.exception;


/**
 * <p>
 * Title: 所有导出文件Exception的基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 9:27:10 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportFileException extends FMException {

	public FMExportFileException() {
		super();
	}

	public FMExportFileException(String errorMessage) {
		super(errorMessage);
	}

	public FMExportFileException(Throwable cause) {
		super(cause);
	}

}
