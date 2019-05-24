/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dict;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 11:06:42
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictImpl implements IDict {

    /**
     * @param description
     * @param name
     * @param value
     */
    public DictImpl(String description, String name, String value) {
        super();
        this.description = description;
        this.name = name;
        this.value = value;
    }

    /**
     * 描述
     */
    private String description;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 取描述信息
     * 
     * @return
     */
    public String getDescription() {
        return null;
    }

    /**
     * 名称，例 public static final String QUERY_ALL_NAME = "全部";
     * 
     * @return
     */
    public String getName() {
        return null;
    }

    /**
     * 取值，例查询全部默认数值，供以后查询判断是否为查询全部，select标签是否应加入全部 <option
     * value="QUERY_ALL_DEFAULT">全部 </option>
     * 
     * @return
     */
    public String getValue() {
        return null;
    }

}
