package com.boco.eoms.sparepart.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;
import org.apache.struts.upload.*;

/**
 * <p>Title:UpLoadForm</p>
 */

public class UpLoadForm extends ActionForm{

    protected FormFile theFile;
    private String note;
    private String orderId;
    private String orderType;
    private String sheetNames[];
    private String scoreYear;
    private String scoreMonth;
    public FormFile getTheFile(){
        return theFile;
    }

    public void setTheFile(FormFile theFile){
        this.theFile=theFile;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }
    public String getOrderId() {
    return orderId;
    }
    public void setOrderId(String orderId) {
    this.orderId = orderId;
    }
    public String getOrderType() {
    return orderType;
    }
    public void setOrderType(String orderType) {
    this.orderType = orderType;
    }

	public String[] getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(String[] sheetNames) {
		this.sheetNames = sheetNames;
	}

	public String getScoreMonth() {
		return scoreMonth;
	}

	public void setScoreMonth(String scoreMonth) {
		this.scoreMonth = scoreMonth;
	}

	public String getScoreYear() {
		return scoreYear;
	}

	public void setScoreYear(String scoreYear) {
		this.scoreYear = scoreYear;
	}
}
