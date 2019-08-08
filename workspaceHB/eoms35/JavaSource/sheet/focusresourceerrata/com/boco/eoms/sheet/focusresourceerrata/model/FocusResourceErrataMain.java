package com.boco.eoms.sheet.focusresourceerrata.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:集客资源勘误
 * </p>
 * <p>
 * Description:集客资源勘误
 * </p>
 * <p>
 * Thu May 10 09:23:09 CST 2018
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public class FocusResourceErrataMain extends BaseMain {

    /**
     * 产品实例标识
     */
    private java.lang.String mainProductInstance;


    /**
     * 客户名称
     */
    private java.lang.String mainCustomerName;


    /**
     * 业务类型
     */
    private java.lang.String mainBusinessType;


    /**
     * 电路代号
     */
    private java.lang.String mainCircuitCode;


    /**
     * 电路核查失败所属地市
     */
    private java.lang.String mainFailCity;


    /**
     * 电路核查状态
     */
    private java.lang.String mainCheckState;


    /**
     * 预留1
     */
    private java.lang.String mainReserved1;


    /**
     * 预留2
     */
    private java.lang.String mainReserved2;


    /**
     * 预留3
     */
    private java.lang.String mainReserved3;


    /**
     * 预留4
     */
    private java.lang.String mainReserved4;


    /**
     * 预留5
     */
    private java.lang.String mainReserved5;

    /**
     * 开通工单号
     */
    private java.lang.String mainSheetNun;

    /**
     * 专线所属地市
     */
    private java.lang.String mainLineArea;

    /**
     * 接口派单来源
     */
    private java.lang.String mainCaller;

    /**
     * 保存派发对象
     */
    private java.lang.String sendObject;

    public void setMainProductInstance(java.lang.String mainProductInstance) {
        this.mainProductInstance = mainProductInstance;
    }

    public java.lang.String getMainProductInstance() {
        return this.mainProductInstance;
    }


    public void setMainCustomerName(java.lang.String mainCustomerName) {
        this.mainCustomerName = mainCustomerName;
    }

    public java.lang.String getMainCustomerName() {
        return this.mainCustomerName;
    }


    public void setMainBusinessType(java.lang.String mainBusinessType) {
        this.mainBusinessType = mainBusinessType;
    }

    public java.lang.String getMainBusinessType() {
        return this.mainBusinessType;
    }


    public void setMainCircuitCode(java.lang.String mainCircuitCode) {
        this.mainCircuitCode = mainCircuitCode;
    }

    public java.lang.String getMainCircuitCode() {
        return this.mainCircuitCode;
    }


    public void setMainFailCity(java.lang.String mainFailCity) {
        this.mainFailCity = mainFailCity;
    }

    public java.lang.String getMainFailCity() {
        return this.mainFailCity;
    }


    public void setMainCheckState(java.lang.String mainCheckState) {
        this.mainCheckState = mainCheckState;
    }

    public java.lang.String getMainCheckState() {
        return this.mainCheckState;
    }


    public void setMainReserved1(java.lang.String mainReserved1) {
        this.mainReserved1 = mainReserved1;
    }

    public java.lang.String getMainReserved1() {
        return this.mainReserved1;
    }


    public void setMainReserved2(java.lang.String mainReserved2) {
        this.mainReserved2 = mainReserved2;
    }

    public java.lang.String getMainReserved2() {
        return this.mainReserved2;
    }


    public void setMainReserved3(java.lang.String mainReserved3) {
        this.mainReserved3 = mainReserved3;
    }

    public java.lang.String getMainReserved3() {
        return this.mainReserved3;
    }


    public void setMainReserved4(java.lang.String mainReserved4) {
        this.mainReserved4 = mainReserved4;
    }

    public java.lang.String getMainReserved4() {
        return this.mainReserved4;
    }


    public void setMainReserved5(java.lang.String mainReserved5) {
        this.mainReserved5 = mainReserved5;
    }

    public java.lang.String getMainReserved5() {
        return this.mainReserved5;
    }


    public java.lang.String getSendObject() {
        return sendObject;
    }

    public void setSendObject(java.lang.String sendObject) {
        this.sendObject = sendObject;
    }

    public java.lang.String getMainCaller() {
        return mainCaller;
    }

    public void setMainCaller(java.lang.String mainCaller) {
        this.mainCaller = mainCaller;
    }

    public java.lang.String getMainLineArea() {
        return mainLineArea;
    }

    public void setMainLineArea(java.lang.String mainLineArea) {
        this.mainLineArea = mainLineArea;
    }

    public java.lang.String getMainSheetNun() {
        return mainSheetNun;
    }

    public void setMainSheetNun(java.lang.String mainSheetNun) {
        this.mainSheetNun = mainSheetNun;
    }
}