package com.boco.eoms.partner.baseinfo.webapp.form;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:车辆管理
 * </p>
 * <p>
 * Description:车辆管理
 * </p>
 * <p>
 * Thu Feb 05 13:54:40 CST 2009
 * </p>
 *
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.5
 */
public class TawPartnerCarForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private FormFile accessoryName; //批量录入文件

    public FormFile getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(FormFile accessoryName) {
        this.accessoryName = accessoryName;
    }

    /**
     * 添加人
     */
    private java.lang.String addMan;

    public java.lang.String getAddMan() {
        return addMan;
    }

    public void setAddMan(java.lang.String addMan) {
        this.addMan = addMan;
    }

    /**
     * 添加时间
     */
    private java.lang.String addTime;

    public java.lang.String getAddTime() {
        return addTime;
    }

    public void setAddTime(java.lang.String addTime) {
        this.addTime = addTime;
    }

    /**
     * 添加人联系方式
     */
    private java.lang.String connectType;

    public java.lang.String getConnectType() {
        return connectType;
    }

    public void setConnectType(java.lang.String connectType) {
        this.connectType = connectType;
    }

    /**
     * 车牌号
     */
    private java.lang.String car_number;

    public void setCar_number(java.lang.String car_number) {
        this.car_number = car_number;
    }

    public java.lang.String getCar_number() {
        return this.car_number;
    }

    /**
     * 所属公司
     */
    private java.lang.String dept_id;

    public void setDept_id(java.lang.String dept_id) {
        this.dept_id = dept_id;
    }

    public java.lang.String getDept_id() {
        return this.dept_id;
    }

    /**
     * 所属地市
     */
    private java.lang.String area_id;

    public void setArea_id(java.lang.String area_id) {
        this.area_id = area_id;
    }

    public java.lang.String getArea_id() {
        return this.area_id;
    }

    /**
     * 开始使用时间
     */
    private java.lang.String start_time;

    public void setStart_time(java.lang.String start_time) {
        this.start_time = start_time;
    }

    public java.lang.String getStart_time() {
        return this.start_time;
    }

    /**
     * 所属片区
     */
    private java.lang.String piece;

    public void setPiece(java.lang.String piece) {
        this.piece = piece;
    }

    public java.lang.String getPiece() {
        return this.piece;
    }

    /**
     * 所属片区的数组形式
     */
    private java.lang.String[] arrayPiece;

    public void setArrayPiece(java.lang.String[] arrayPiece) {
        this.arrayPiece = arrayPiece;
    }

    public java.lang.String[] getArrayPiece() {
        return arrayPiece;
    }

    /**
     * 全部所属片区的数组形式
     */
    private java.lang.String[] arrayPieceAll;

    public java.lang.String[] getArrayPieceAll() {
        return arrayPieceAll;
    }

    public void setArrayPieceAll(java.lang.String[] arrayPieceAll) {
        this.arrayPieceAll = arrayPieceAll;
    }

    /**
     * 备注
     */
    private java.lang.String remark;

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

}