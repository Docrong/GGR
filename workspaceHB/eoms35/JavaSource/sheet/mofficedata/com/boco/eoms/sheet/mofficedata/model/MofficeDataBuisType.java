package com.boco.eoms.sheet.mofficedata.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 *
 * @author weichao
 * @version 3.5
 */

public class MofficeDataBuisType implements Serializable {
    /**
     * @date 2016-4-6上午10:48:36
     * @author weichao
     */
    private static final long serialVersionUID = -952431943412706055L;
    /**
     * 主键
     */
    private String id;
    /**
     * 业务类型
     */
    private String buisName;
    /**
     * 业务对应表
     */
    private String buisValue;
    /**
     * 同步时间
     */
    private String createTime;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBuisName() {
        return buisName;
    }

    public void setBuisName(String buisName) {
        this.buisName = buisName;
    }

    public String getBuisValue() {
        return buisValue;
    }

    public void setBuisValue(String buisValue) {
        this.buisValue = buisValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}