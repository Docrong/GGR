package com.boco.eoms.businessupport.interfaces.service;

import java.util.List;
import java.util.Map;

public interface IResourceXml {
    /**
     * 将数据转换成接口xml,资源初始化接口
     *
     * @param orderMap         定单信息map
     * @param productList<map> 产品实例列表
     * @return
     * @throws Exception
     */
    public String getXmlFromMapInit(Map orderMap, List productList) throws Exception;

    /**
     * 将数据转换成接口xml,资源初始化接口
     *
     * @param orderMap 定单信息map
     * @return
     * @throws Exception
     */
    public String getXmlFromMapInit(Map orderMap) throws Exception;

    /**
     * 将数据转换成接口xml,资源实占、取消等接口
     *
     * @param orderMap 定单信息map
     * @return
     * @throws Exception
     */
    public String getXmlFromMap(Map orderMap, List productList) throws Exception;

    /**
     * 将数据转换成接口xml,资源实占、取消等接口
     *
     * @param orderMap 定单信息map
     * @return
     * @throws Exception
     */
    public String getXmlFromMap(Map orderMap) throws Exception;
}
