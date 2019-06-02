
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.Serializable;

/**
 * @author lizhenyou
 *
 * Excel表
 */
public class Excel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6235900032701394061L;

	private Sheet[] sheets = null;
	
	private String fileName = null; 

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Sheet[] getSheets() {
		return sheets;
	}

	public void setSheets(Sheet[] sheets) {
		this.sheets = sheets;
	}
	
	
	public Sheet getSheetByName(String sheetName){
		for (int i = 0; i < sheets.length; i++) {
			Sheet sheet = sheets[i];
			if (sheet.getSheetName().equals(sheetName)) {
				return sheet;
			}
		}
		return null;
	}
	public Sheet getSheetByIndex(String index){
		for (int i = 0; i < sheets.length; i++) {
			Sheet sheet = sheets[i];
			if (sheet.getSheetIndex() ==Integer.parseInt(index)) {
				
				return sheet;
			}
		}
		return null;
	}
}
