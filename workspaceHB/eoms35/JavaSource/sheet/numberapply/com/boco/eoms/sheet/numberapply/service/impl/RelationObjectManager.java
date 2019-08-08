package com.boco.eoms.sheet.numberapply.service.impl;

import java.util.Map;

public class RelationObjectManager {

    /**
     * 需要更新的值所在表的Module对象
     */
    private Map fromQueryDataMoudle;

    /**
     * 根据字典值获得需要更新的资源来源模块的model的列（表中的列也可，因为表中的列是不区分大小写的）
     */
    private Map fromUpdateColumn;

    /**
     * 根据字典值获得手动分配时从页面传过来的选中的id的值的名称
     */
    private Map fromManualId;

    /**
     * 根据字典值获得手动分配时从页面传过来的选中的值的名称
     */
    private Map fromManualValue;

    /**
     * 根据字典值获得手动分配时从页面传过来的该条记录原来的24位信令点、14位信令点或者是hlrid或mscid的值的名称
     */
    private Map fromManualResource;
    /**
     * 根据字典值获得动态表的model类
     */
    private Map fromUpdateDynamic;

    /**
     * 两张动态表格的表名和module
     */
    private Map needUpdateObject;


    /**
     * 两张动态表格需要更新的列名
     *
     * @return
     */
    private Map neeeUpdateColumn;


    /**
     * 需要更新的column取值所对应的对象和属性  key:申请分类＋列名 value：对应表的对象名 + "," + 对象的属性名
     */
    private Map needValueMappingTableColumn;


    public Map getFromManualId() {
        return fromManualId;
    }

    public void setFromManualId(Map fromManualId) {
        this.fromManualId = fromManualId;
    }

    public Map getFromManualResource() {
        return fromManualResource;
    }

    public void setFromManualResource(Map fromManualResource) {
        this.fromManualResource = fromManualResource;
    }

    public Map getFromManualValue() {
        return fromManualValue;
    }

    public void setFromManualValue(Map fromManualValue) {
        this.fromManualValue = fromManualValue;
    }

    public Map getFromUpdateColumn() {
        return fromUpdateColumn;
    }

    public void setFromUpdateColumn(Map fromUpdateColumn) {
        this.fromUpdateColumn = fromUpdateColumn;
    }

    public Map getNeedValueMappingTableColumn() {
        return needValueMappingTableColumn;
    }

    public void setNeedValueMappingTableColumn(Map needValueMappingTableColumn) {
        this.needValueMappingTableColumn = needValueMappingTableColumn;
    }

    public Map getNeedUpdateObject() {
        return needUpdateObject;
    }

    public void setNeedUpdateObject(Map needUpdateObject) {
        this.needUpdateObject = needUpdateObject;
    }

    public Map getNeeeUpdateColumn() {
        return neeeUpdateColumn;
    }

    public void setNeeeUpdateColumn(Map neeeUpdateColumn) {
        this.neeeUpdateColumn = neeeUpdateColumn;
    }

    public Map getFromQueryDataMoudle() {
        return fromQueryDataMoudle;
    }

    public void setFromQueryDataMoudle(Map fromQueryDataMoudle) {
        this.fromQueryDataMoudle = fromQueryDataMoudle;
    }

    public Map getFromUpdateDynamic() {
        return fromUpdateDynamic;
    }

    public void setFromUpdateDynamic(Map fromUpdateDynamic) {
        this.fromUpdateDynamic = fromUpdateDynamic;
    }


}

	

