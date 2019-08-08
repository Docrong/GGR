package com.boco.eoms.workplan.vo;

import org.jdom.Element;

public class TawwpAddonsElementVO {

    public TawwpAddonsElementVO() {
    }

    private String align;
    private String cols;
    private String index;
    private String name;
    private String newLine;
    private String otherValue;
    private String rows;
    private String showType;
    private String validateType;
    private String valign;
    private String value;
    private String x;
    private String y;
    private String endTime;
    private String startTime;
    private String dicId;
    private String cycle;

    public String getX() {
        if (x != null && !x.equals("")) {
            return x;
        } else {
            return "0";
        }

    }

    public String getName() {
        if (name != null) {
            return name;
        } else {
            return "1";
        }

    }

    public String getY() {
        if (y != null && !y.equals("")) {
            return y;
        } else {
            return "1";
        }

    }

    public String getAlign() {
        if (align == null) {
            align = "";
        }
        return align;
    }

    public String getShowType() {
        return showType;
    }

    public String getNewLine() {
        return newLine;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public String getValidateType() {
        return validateType;
    }

    public String getValign() {
        return valign;
    }

    public String getRows() {
        if (rows != null && !rows.equals("")) {
            return rows;
        } else {
            return "1";
        }
    }

    public String getValue() {
        if (value != null) {
            return value;
        } else {
            return "";
        }

    }

    public String getCols() {
        if (cols != null && !cols.equals("")) {
            return cols;
        } else {
            return "1";
        }
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public void setNewLine(String newLine) {
        this.newLine = newLine;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    public void setValign(String valign) {
        this.valign = valign;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setValue(String value) {
        if (value != null) {
            this.value = value;
        } else {
            this.value = "";
        }
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getIndex() {
        return index;
    }

    public String getStartTime() {
        if (startTime != null && !startTime.equals("")) {
            return startTime;
        } else {
            return "00:00:00";
        }

    }

    public String getEndTime() {
        if (endTime != null && !endTime.equals("")) {
            return endTime;
        } else {
            return "23:59:59";
        }
    }

    public String getDicId() {
        if (dicId != null && !dicId.equals("")) {
            return dicId;
        } else {
            return "";
        }
    }

    public String getCycle() {
        return cycle;
    }

    public TawwpAddonsElementVO getElement(Element element) {

        if (element.getAttributeValue("align") != null &&
                !element.getAttributeValue("align").equals("")) {
            this.setAlign(element.getAttributeValue("align"));
        }
        if (element.getAttributeValue("cols") != null &&
                !element.getAttributeValue("cols").equals("")) {
            this.setCols(element.getAttributeValue("cols"));
        }
        if (element.getAttributeValue("index") != null &&
                !element.getAttributeValue("index").equals("")) {
            this.setIndex(element.getAttributeValue("index"));
        }
        if (element.getAttributeValue("name") != null &&
                !element.getAttributeValue("name").equals("")) {
            this.setName(element.getAttributeValue("name"));
        }
        if (element.getAttributeValue("newLine") != null &&
                !element.getAttributeValue("newLine").equals("")) {
            this.setNewLine(element.getAttributeValue("newLine"));
        }
        if (element.getAttributeValue("otherValue") != null &&
                !element.getAttributeValue("otherValue").equals("")) {
            this.setOtherValue(element.getAttributeValue("otherValue"));
        }
        if (element.getAttributeValue("rows") != null &&
                !element.getAttributeValue("rows").equals("")) {
            this.setRows(element.getAttributeValue("rows"));
        }
        if (element.getAttributeValue("showType") != null &&
                !element.getAttributeValue("showType").equals("")) {
            this.setShowType(element.getAttributeValue("showType"));
        }
        if (element.getAttributeValue("validateType") != null &&
                !element.getAttributeValue("validateType").equals("")) {
            this.setValidateType(element.getAttributeValue("validateType"));
        }
        if (element.getAttributeValue("valign") != null &&
                !element.getAttributeValue("valign").equals("")) {
            this.setValign(element.getAttributeValue("valign"));
        }
        if (element.getAttributeValue("value") != null &&
                !element.getAttributeValue("value").equals("")) {
            this.setValue(element.getAttributeValue("value"));
        }
        if (element.getAttributeValue("x") != null &&
                !element.getAttributeValue("x").equals("")) {
            this.setX(element.getAttributeValue("x"));
        }
        if (element.getAttributeValue("y") != null &&
                !element.getAttributeValue("y").equals("")) {
            this.setY(element.getAttributeValue("y"));
        }
        if (element.getAttributeValue("startTime") != null &&
                !element.getAttributeValue("startTime").equals("")) {
            this.setStartTime(element.getAttributeValue("startTime"));
        }
        if (element.getAttributeValue("endTime") != null &&
                !element.getAttributeValue("endTime").equals("")) {
            this.setEndTime(element.getAttributeValue("endTime"));
        }
        if (element.getAttributeValue("dicId") != null &&
                !element.getAttributeValue("dicId").equals("")) {
            this.setDicId(element.getAttributeValue("dicId"));
        }
        if (element.getAttributeValue("cycle") != null &&
                !element.getAttributeValue("cycle").equals("")) {
            this.setCycle(element.getAttributeValue("cycle"));
        }

        return this;
    }
}
