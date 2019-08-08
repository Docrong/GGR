package com.boco.eoms.commonfault.importexcel.model;

import java.io.Serializable;

public class CommonfaultImportExcels implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5964548665585111138L;

    /**
     * 工单号
     */
    private String sheetId;

    /**
     * 工单主题
     */
    private String title;

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
