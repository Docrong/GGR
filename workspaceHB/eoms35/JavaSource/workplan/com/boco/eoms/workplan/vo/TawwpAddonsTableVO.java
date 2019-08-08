package com.boco.eoms.workplan.vo;

public class TawwpAddonsTableVO {

    private String id; //标识
    private String name; //中文名
    private String text; //备注
    private String model; //模块
    private String executeId; //执行内容ID
    private String url;//地址


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExecuteId() {
        return executeId;
    }

    public String getModel() {
        return model;
    }

    public String getUrl() {
        return url;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExecuteId(String executeId) {
        this.executeId = executeId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

}
