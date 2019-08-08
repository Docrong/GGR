package com.boco.eoms.commons.accessories.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:附件配置管理service类
 * </p>
 * <p>
 * Apr 10, 2007 11:00:08 AM
 * </p>
 *
 * @author 秦敏
 * @version 1.0
 */
public interface ITawCommonsAccessoriesConfigManager extends Manager {
    /**
     * 获取配置信息
     *
     * @return
     * @throws Exception
     * @author 秦敏
     */
    public List getTawCommonsAccessoriesConfigs()
            throws AccessoriesConfigException;

    /**
     * 保存配置信息
     *
     * @param configObject 配置信息
     * @author 秦敏
     */
    public void saveTawCommonsAccessoriesConfig(
            TawCommonsAccessoriesConfig config)
            throws AccessoriesConfigException;

    /**
     * 查询配置信息
     *
     * @param appCode 应用模板ID
     * @author
     */
    public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(
            Integer appId) throws AccessoriesConfigException;

    /**
     * 查询配置信息
     *
     * @param appCode 应用模板ID
     * @author
     */
    public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(String id)
            throws AccessoriesConfigException;

    /**
     * 查询配置信息
     *
     * @param appCode 应用模板编码
     * @author
     */
    public TawCommonsAccessoriesConfig getAccessoriesConfigByAppcode(
            String appCode) throws AccessoriesConfigException;

    /**
     * 删除配置信息
     *
     * @param appCode 应用模板ID
     * @author 秦敏
     */
    public void removeTawCommonsAccessoriesConfig(Integer appId)
            throws AccessoriesConfigException;

    /**
     * 获取应用模块信息
     *
     * @return
     * @author 秦敏
     */
    public List getApplicationInfo();


}
