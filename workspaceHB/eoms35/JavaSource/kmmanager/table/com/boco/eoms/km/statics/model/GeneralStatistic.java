package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;

/**
 * <p>
 * Title:人员知识贡献统计
 * </p>
 * <p>
 * Description:人员知识贡献统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class GeneralStatistic extends BaseObject {

    /**
     * 经验库ID
     */
    private String tableId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 经验库名
     */
    private String tableName;

    public String getTableName() {
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) ApplicationContextHolder
                .getInstance().getBean("kmTableGeneralMgr");
        tableName = kmTableGeneralMgr.getKmTableGeneral(tableId).getTableChname();
        return tableName;
    }

    /**
     * 推荐知识数
     */
    private java.lang.Integer isBest;

    public void setIsBest(java.lang.Integer isBest) {
        this.isBest = isBest;
    }

    public java.lang.Integer getIsBest() {
        return this.isBest;
    }

    /**
     * 公开知识数
     */
    private java.lang.Integer isPublic;

    public void setIsPublic(java.lang.Integer isPublic) {
        this.isPublic = isPublic;
    }

    public java.lang.Integer getIsPublic() {
        return this.isPublic;
    }

    /**
     * 一星知识数
     */
    private java.lang.Integer gradeOne;

    public void setGradeOne(java.lang.Integer gradeOne) {
        this.gradeOne = gradeOne;
    }

    public java.lang.Integer getGradeOne() {
        return this.gradeOne;
    }

    /**
     * 二星知识数
     */
    private java.lang.Integer gradeTwo;

    public void setGradeTwo(java.lang.Integer gradeTwo) {
        this.gradeTwo = gradeTwo;
    }

    public java.lang.Integer getGradeTwo() {
        return this.gradeTwo;
    }

    /**
     * 三星知识数
     */
    private java.lang.Integer gradeThree;

    public void setGradeThree(java.lang.Integer gradeThree) {
        this.gradeThree = gradeThree;
    }

    public java.lang.Integer getGradeThree() {
        return this.gradeThree;
    }

    /**
     * 四星知识数
     */
    private java.lang.Integer gradeFour;

    public void setGradeFour(java.lang.Integer gradeFour) {
        this.gradeFour = gradeFour;
    }

    public java.lang.Integer getGradeFour() {
        return this.gradeFour;
    }

    /**
     * 五星知识数
     */
    private java.lang.Integer gradeFive;

    public void setGradeFive(java.lang.Integer gradeFive) {
        this.gradeFive = gradeFive;
    }

    public java.lang.Integer getGradeFive() {
        return this.gradeFive;
    }

    /**
     * 阅读的次数
     */
    private java.lang.Integer readCount;

    public void setReadCount(java.lang.Integer readCount) {
        this.readCount = readCount;
    }

    public java.lang.Integer getReadCount() {
        return this.readCount;
    }

    /**
     * 引用的次数
     */
    private java.lang.Integer useCount;

    public void setUseCount(java.lang.Integer useCount) {
        this.useCount = useCount;
    }

    public java.lang.Integer getUseCount() {
        return this.useCount;
    }

    /**
     * 引用的次数
     */
    private java.lang.Integer modifyCount;

    public void setModifyCount(java.lang.Integer modifyCount) {
        this.modifyCount = modifyCount;
    }

    public java.lang.Integer getModifyCount() {
        return this.modifyCount;
    }

    public boolean equals(Object o) {
        if (o instanceof PersonalStatistic) {
            PersonalStatistic personalStatistic = (PersonalStatistic) o;
            if (this.tableId != null || this.tableId.equals(personalStatistic.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}