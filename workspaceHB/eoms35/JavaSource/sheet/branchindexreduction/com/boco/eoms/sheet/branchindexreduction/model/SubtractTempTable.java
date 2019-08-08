package com.boco.eoms.sheet.branchindexreduction.model;

public class SubtractTempTable {

    /**
     * 自增长主键
     */
    private String id;

    /**
     * 表格中的索引号
     */
    private String rowIndex;
    /**
     * 序列号
     */
    private String serialId;
    /**
     * 操作时间戳
     */
    private String serialKey;
    /**
     * 是否成立
     */
    private String isValid;
    /**
     * 说明
     */
    private String remark;

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(String rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getSerialKey() {
        return serialKey;
    }

    public void setSerialKey(String serialKey) {
        this.serialKey = serialKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return "SubtractTempTable[serialKey=" + serialKey + ",rowIndex=" + rowIndex + ",serialId=" + serialId + "]";
    }


}
