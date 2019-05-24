package com.boco.eoms.commons.log.displaytag;
import java.util.HashMap;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.log.dao.impl.LogConfigXmlDom4jDAO;
import com.boco.eoms.commons.log.model.ILogConfig;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 日志查询对应ID返回NAME
 * @author 何毅
 *
 */
public class logListDispaly extends TableDecorator {
	
	public static  Map cache=new HashMap();
	LogConfigXmlDom4jDAO LogConfigDAO = null;
	

/**
 * 返回MODELID
 * @return
 */
	public String getModelid()  {
		TawCommonLogOperator thread = (TawCommonLogOperator) this.getCurrentRowObject();
		if(cache.containsKey(thread.getOperid() )){
		
	}else{
		ILogConfig logConfig=null;
		try {
			LogConfigDAO=(LogConfigXmlDom4jDAO) ApplicationContextHolder.getInstance().getBean("LogConfigDAO");
			logConfig = (ILogConfig)LogConfigDAO.findLogConfig(thread.getOperid());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取modelid报错：" + ex);
			}
		cache.put(thread.getOperid(), logConfig);
	}
		ILogConfig logConfigs=null;
		logConfigs=(ILogConfig)cache.get(thread.getOperid());
		if (null == logConfigs) {
			return "";
		}
		else {
			return logConfigs.getModelId();
		}
	}
	
/**
 * 返回MODELIDNAME
 * @return
 */
	public String getModelname(){
		TawCommonLogOperator thread = (TawCommonLogOperator) this.getCurrentRowObject();
		if(cache.containsKey(thread.getOperid() )){
		
	}else{
		ILogConfig logConfig=null;
		try {
			LogConfigDAO=(LogConfigXmlDom4jDAO) ApplicationContextHolder.getInstance().getBean("LogConfigDAO");
			logConfig = (ILogConfig)LogConfigDAO.findLogConfig(thread.getOperid());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取modelid报错：" + ex);
			}
		cache.put(thread.getOperid(), logConfig);
	}
		ILogConfig logConfigs=null;
		logConfigs=(ILogConfig)cache.get(thread.getOperid());
		if (null == logConfigs) {
			return "";
		}
		else {
			return logConfigs.getModelName();
		}
		
	}
	/**
	 * 返回OPERNAME
	 * @return
	 */
	public String getOpername(){
		TawCommonLogOperator thread = (TawCommonLogOperator) this.getCurrentRowObject();
		if(cache.containsKey(thread.getOperid() )){
		
	}else{
		ILogConfig logConfig=null;
		try {
			LogConfigDAO=(LogConfigXmlDom4jDAO) ApplicationContextHolder.getInstance().getBean("LogConfigDAO");
			logConfig = (ILogConfig)LogConfigDAO.findLogConfig(thread.getOperid());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取modelid报错：" + ex);
			}
		cache.put(thread.getOperid(), logConfig);
	}
		ILogConfig logConfigs=null;
		logConfigs=(ILogConfig)cache.get(thread.getOperid());
		if (null == logConfigs) {
			return "";
		}
		else {
			return logConfigs.getOperName();
		}
	}
	/**
	 * 返回Operremark
	 * @return
	 */
	public String getOperremark(){
		TawCommonLogOperator thread = (TawCommonLogOperator) this.getCurrentRowObject();
		if(cache.containsKey(thread.getOperid() )){
		
	}else{
		ILogConfig logConfig=null;
		try {
			LogConfigDAO=(LogConfigXmlDom4jDAO) ApplicationContextHolder.getInstance().getBean("LogConfigDAO");
			logConfig = (ILogConfig)LogConfigDAO.findLogConfig(thread.getOperid());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取modelid报错：" + ex);
			}
		cache.put(thread.getOperid(), logConfig);
	}
		ILogConfig logConfigs=null;
		logConfigs=(ILogConfig)cache.get(thread.getOperid());
		if (null == logConfigs) {
			return "";
		}
		else {
			return logConfigs.getOperDesc();
		}
	}
}
