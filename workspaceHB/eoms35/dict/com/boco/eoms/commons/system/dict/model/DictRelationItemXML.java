package com.boco.eoms.commons.system.dict.model;

/**
 * <p>
 * Title:字典关联子项
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-25 18:12:42
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictRelationItemXML implements IDictRelationItem {

    /**
     * 目的项id，以逗号隔开
     * 
     * 
     */
    private String destinationItemIds;

    /**
     * 源item id
     * 
     */
    private String sourceItemId;

    /**
     * 目的字典类
     * 
     */
    private String destinationDictKey;

    /**
     * 目的字典ID
     * 
     */
    private String destinationDictId;

    /**
     * @return the destinationDictId
     */
    public String getDestinationDictId() {
        return destinationDictId;
    }
    /**
     * @param destinationDictId the destinationDictId to set
     */
    public void setDestinationDictId(String destinationDictId) {
        this.destinationDictId = destinationDictId;
    }
    /**
     * @return the destinationDictKey
     */
    public String getDestinationDictKey() {
        return destinationDictKey;
    }
    /**
     * @param destinationDictKey the destinationDictKey to set
     */
    public void setDestinationDictKey(String destinationDictKey) {
        this.destinationDictKey = destinationDictKey;
    }
    /**
     * @return the destinationItemIds
     */
    public String getDestinationItemIds() {
        return destinationItemIds;
    }
    /**
     * @param destinationItemIds the destinationItemIds to set
     */
    public void setDestinationItemIds(String destinationItemIds) {
        this.destinationItemIds = destinationItemIds;
    }
    /**
     * @return the sourceItemId
     */
    public String getSourceItemId() {
        return sourceItemId;
    }
    /**
     * @param sourceItemId the sourceItemId to set
     */
    public void setSourceItemId(String sourceItemId) {
        this.sourceItemId = sourceItemId;
    }
}
