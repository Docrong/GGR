/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dict;

import java.util.Map;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 11:47:48
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictServiceImpl implements IDictService {
    /**
     * 通过value值取描述
     * 
     * @param value
     * @param map
     * @return
     */
    public String value2description(String value, Map map) {

        if (null != value && null != map && map.containsKey(value)) {
            return ((IDict) map.get(value)).getDescription();
        }
        return "未知";
    }

    /**
     * 通过value取名称
     * 
     * @param value
     * @param map
     * @return
     */
    public String value2name(String value, Map map) {
        if (null != value && null != map && map.containsKey(value)) {
            return ((IDict) map.get(value)).getName();
        }
        return "未知";
    }

    /**
     * 操作类型
     */
    private Map operateMap;

    
    /**
     * @return the operateMap
     */
    public Map getOperateMap() {
        return operateMap;
    }
    /**
     * @param operateMap the operateMap to set
     */
    public void setOperateMap(Map operateMap) {
        this.operateMap = operateMap;
    }
}
