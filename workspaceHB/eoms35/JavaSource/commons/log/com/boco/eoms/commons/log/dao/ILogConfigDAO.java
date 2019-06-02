/**
 * 
 */
package com.boco.eoms.commons.log.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.log.exceptions.LogConfigDuplicateException;
import com.boco.eoms.commons.log.exceptions.LogConfigNotFoundException;
import com.boco.eoms.commons.log.exceptions.LogConfigReflashException;
import com.boco.eoms.commons.log.model.ILogConfig;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 11, 2008 1:29:38 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public interface ILogConfigDAO {
	
	/**
	 * 根据modelId和operId展现它对应的子节点
	 * @param modelId
	 * @param operId
	 * @return List<ILogConfig>
	 * @throws LogConfigNotFoundException
	 */
	public List listChildLogConfig(Object modelId, Object operId) throws LogConfigNotFoundException;
	
	/**
	 * 根据modelId和operId寻找节点
	 * @param modelId
	 * @param operId
	 * @return
	 * @throws LogConfigNotFoundException,LogConfigDuplicateException
	 */
	public ILogConfig findLogConfig(Object operId) throws LogConfigNotFoundException,LogConfigDuplicateException;
	
	/**
	 * 保存日志配置对象
	 * @param modelId
	 * @param logConfig
	 * @return
	 * @throws LogConfigNotFoundException,LogConfigDuplicateException
	 */
	public ILogConfig saveLogConfig(Object modelId,ILogConfig logConfig) throws LogConfigNotFoundException,LogConfigDuplicateException;
	
	/**
	 * 修改日志配置对象
	 * @param modelId
	 * @param logConfig
	 * @throws LogConfigNotFoundException,LogConfigDuplicateException
	 */
	public void modifyLogConfig(Object modelId,ILogConfig logConfig) throws LogConfigNotFoundException,LogConfigDuplicateException;
	
	/**
	 * 删除日志配置对象
	 * @param modelId
	 * @param logConfig
	 * @throws LogConfigNotFoundException,LogConfigDuplicateException
	 */
	public void deleteLogConfig(Object modelId,ILogConfig logConfig) throws LogConfigNotFoundException,LogConfigDuplicateException;
	
	/**
	 * 根据modelId刷新对应的日志配置文件
	 * @param modelId
	 * @throws LogConfigReflashException
	 */
	public void reflashLogConfigByModelId(Object modelId) throws LogConfigReflashException;
	
	/**
	 * 根据operId刷新日志配置对象
	 * @param operId
	 * @throws LogConfigReflashException
	 */
	public void reflashLogConfigByOperId(Object operId) throws LogConfigReflashException;;
	
	/**
	 * 刷新整个日志配置文件
	 * @throws LogConfigReflashException
	 */
	public void reflashLogConfigs() throws LogConfigReflashException;
	/**
	 * 得到所有树的根节点
	 * @param modelId
	 * @param operId
	 * @return
	 * @throws LogConfigNotFoundException
	 * @throws LogConfigDuplicateException
	 */
	public List findAllLogConfig(Object modelId) throws LogConfigNotFoundException,LogConfigDuplicateException;

}
