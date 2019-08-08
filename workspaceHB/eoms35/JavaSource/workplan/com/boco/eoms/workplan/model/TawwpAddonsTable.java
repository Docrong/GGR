package com.boco.eoms.workplan.model;

/**
 * <p>Title: 作业计划填写表格管理类</p>
 * <p>Description:作业计划填写表格管理类信息，其中包括表格名称，所属模块，备注等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.*;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_ADDONSTABLE"
 */

public class TawwpAddonsTable
        implements Serializable, DataObject {

    private String id; //标识
    private String name; //中文名
    private String text; //备注
    private String model; //模块
    private String executeId; //执行内容ID
    private String url;//地址

    public TawwpAddonsTable() {
    }

    public TawwpAddonsTable(String _name, String _text,
                            String _model, String _executeId,
                            String _url) {
        this.name = _name;
        this.text = _text;
        this.model = _model;
        this.executeId = _executeId;
        this.url = _url;
    }

    /**
     * @hibernate.id column="ID"
     * length="32"
     * unsaved-value="null"
     * generator-class="uuid.hex"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column="MODEL"
     * length="2"
     * not-null="true"
     * update="true"
     */
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @hibernate.property column="NAME"
     * length="40"
     * not-null="true"
     * update="true"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property column="TEXT"
     * length="200"
     * not-null="false"
     * update="true"
     */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * @hibernate.property column="EXECUTEID"
     * length="40"
     * not-null="false"
     * update="true"
     */
    public String getExecuteId() {
        return executeId;
    }

    public void setExecuteId(String executeId) {
        this.executeId = executeId;
    }


    /**
     * @hibernate.property column="URL"
     * length="60"
     * not-null="false"
     * update="true"
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
