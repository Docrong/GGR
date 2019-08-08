package com.boco.eoms.sheet.netoptimize.model;

import com.boco.eoms.sheet.base.model.BaseMain;

import java.util.Date;


/**
 * @author TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetOptimizeMain extends BaseMain {

    private String mainNetSortOne;
    private String mainNetSortTwo;
    private String mainNetSortThree;
    private String mainOptimizeDemand;
    private String mainOptmizeScheme;
    private Date mainCompleteTime;

    public Date getMainCompleteTime() {
        return mainCompleteTime;
    }

    public void setMainCompleteTime(Date mainCompleteTime) {
        this.mainCompleteTime = mainCompleteTime;
    }

    public String getMainNetSortOne() {
        return mainNetSortOne;
    }

    public void setMainNetSortOne(String mainNetSortOne) {
        this.mainNetSortOne = mainNetSortOne;
    }

    public String getMainNetSortThree() {
        return mainNetSortThree;
    }

    public void setMainNetSortThree(String mainNetSortThree) {
        this.mainNetSortThree = mainNetSortThree;
    }

    public String getMainNetSortTwo() {
        return mainNetSortTwo;
    }

    public void setMainNetSortTwo(String mainNetSortTwo) {
        this.mainNetSortTwo = mainNetSortTwo;
    }

    public String getMainOptimizeDemand() {
        return mainOptimizeDemand;
    }

    public void setMainOptimizeDemand(String mainOptimizeDemand) {
        this.mainOptimizeDemand = mainOptimizeDemand;
    }

    public String getMainOptmizeScheme() {
        return mainOptmizeScheme;
    }

    public void setMainOptmizeScheme(String mainOptmizeScheme) {
        this.mainOptmizeScheme = mainOptmizeScheme;
    }


}
