/*
 * Created on 2007-8-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.prm.model;

/**
 * <p>
 * Title:pojo转成pojo
 * </p>
 * <p>
 * Description:由get/set对象转成另一个get/set对象
 * </p>
 * <p>
 * Date:2007-8-6 17:11:43
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class P2P {

    /**
     * 源属性名
     */
    private String name;

    /**
     * 复制的属性名
     */
    private String toName;

    /**
     * 属性类型
     */
    private String type;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the toName
     */
    public String getToName() {
        return toName;
    }

    /**
     * @param toName
     *            the toName to set
     */
    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
