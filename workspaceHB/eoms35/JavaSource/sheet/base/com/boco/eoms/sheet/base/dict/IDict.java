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
 * Date:2007-8-3 11:05:46
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDict {

    /**
     * 查询全部默认数值，供以后查询判断是否为查询全部，select标签是否应加入全部 <option
     * value="QUERY_ALL_DEFAULT">全部 </option>
     */
    public static final String QUERY_ALL_VALUE = "-1";

    /**
     * 查询全部默认数值，供以后查询判断是否为查询全部，select标签是否应加入全部 <option
     * value="QUERY_ALL_DEFAULT">全部 </option>
     */
    public static final String QUERY_ALL_NAME = "全部";

    /**
     * 名称，例 public static final String QUERY_ALL_NAME = "全部";
     * 
     * @return
     */
    public String getName();

    /**
     * 取值，例查询全部默认数值，供以后查询判断是否为查询全部，select标签是否应加入全部 <option
     * value="QUERY_ALL_DEFAULT">全部 </option>
     * 
     * @return
     */
    public String getValue();

    /**
     * 取描述信息
     * 
     * @return
     */
    public String getDescription();

 
}
