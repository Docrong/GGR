package com.boco.eoms.commons.log.webapp.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.log.dao.impl.LogConfigXmlDom4jDAO;
import com.boco.eoms.commons.log.model.ILogConfig;
import com.boco.eoms.commons.log.service.ITawCommonLogBzBO;
import com.boco.eoms.commons.log.webapp.bo.ITawCommonLogSearchBo;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.ui.util.UIConstants;

public class TawCommonLogSearchBoImpl implements ITawCommonLogSearchBo {

	/**
	 * 通过用户ID 模块ID 操作ID 时间段进行日志查询
	 * 
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	private ITawCommonLogBzBO logbo;
	LogConfigXmlDom4jDAO LogConfigDAO = null;
	
	// ITawCommonLogBzBO logbo ;
	public List getAllBymodelopertiems(HttpServletRequest request,final Integer curPage, 
			final Integer pageSize,final String userid,final String modelid,
			final String operid,final String starttime,final String endtime,final String issucess) {

		List list = new ArrayList();

		list = logbo.getAllBymodelopertiems(request,  curPage, 
				  pageSize,userid, modelid, operid, starttime,
				endtime, issucess);
		return list;

	}

	/**
	 * 查询某个用户的所有操作日志
	 * 
	 * @param userid
	 * @return
	 */
	public List getAllByUserIDs(String userid, String issucess) {

		List list = new ArrayList();

		list = logbo.getAllByUserIDs(userid, issucess);
		return list;

	}

	/**
	 * 查询某个模块的日志信息
	 * 
	 * @param modelid
	 * @return
	 */
	public List getAllBymodelids(String modelid, String issucess) {

		List list = new ArrayList();

		list = logbo.getAllBymodelids(modelid, issucess);
		return list;

	}

	/**
	 * 查询某个用户 对某个模块的操作情况
	 * 
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getAllByuseridandModelids(String userid, String modelid,
			String issucess) {

		List list = new ArrayList();
		list = logbo.getAllByuseridandModelids(userid, modelid, issucess);
		return list;

	}

	/**
	 * 查询某个用户对某个模块某个业务的操作情况
	 * 
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getAllbyUseridModelidOperids(final Integer curPage, 
			final Integer pageSize,String userid, String modelid,
			String operid, String issucess) {

		List list = new ArrayList();

		list = logbo.getAllbyUseridModelidOperids(  curPage, 
				  pageSize,userid, modelid, operid,
				issucess);
		return list;

	}

	/**
	 * 查询某个模块 的某个业务的操作情况
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getAllbyModelidAndOperids(String modelid, String operid,
			String issucess) {

		List list = new ArrayList();

		list = logbo.getAllbyModelidAndOperids(modelid, operid, issucess);
		return list;

	}

	/**
	 * 根据userid查询某个用户某个时间段的日志信息
	 * 
	 * @param userid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllByUseridAndTimes(String userid, String starttime,
			String endtime, String issucess) {
		List list = new ArrayList();

		list = logbo.getAllByUseridAndTimes(userid, starttime, endtime,
				issucess);
		return list;
	}

	/**
	 * 更加模块ID 查询某个模块在某个时间段的日志信息
	 * 
	 * @param modelid
	 * @param starttiem
	 * @param endtime
	 * @return
	 */
	public List getAllByModelidAndTimes(String modelid, String starttiem,
			String endtime, String issucess) {
		List list = new ArrayList();

		list = logbo.getAllByModelidAndTimes(modelid, starttiem, endtime,
				issucess);
		return list;
	}

	/**
	 * 根据用户ID 模块ID 查询某个时间段某个用户对某个模块的日志信息
	 * 
	 * @param userid
	 * @param modelid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllByUMIDandTimes(String userid, String modelid,
			String starttime, String endtime, String issucess) {
		List list = new ArrayList();

		list = logbo.getAllByUMIDandTimes(userid, modelid, starttime, endtime,
				issucess);
		return list;
	}

	/**
	 * 根据模块ID 操作ID 查询某个时间段某个模块的某个操作的日志信息
	 * 
	 * @param modelid
	 * @param operid
	 * @param startime
	 * @param endtime
	 * @return
	 */
	public List getAllByMidAndOperidAndTimes(String modelid, String operid,
			String startime, String endtime, String issucess) {
		List list = new ArrayList();

		list = logbo.getAllByMidAndOperidAndTimes(modelid, operid, startime,
				endtime, issucess);
		return list;
	}

	public ITawCommonLogBzBO getLogbo() {
		return logbo;
	}

	public void setLogbo(ITawCommonLogBzBO logbo) {
		this.logbo = logbo;
	}
	/**
	 * 模块业务树,用XML生成
	 * @param parentid
	 * @param chkType
	 * by 何毅
	 * @return
	 */
	public JSONArray getSmsServiceTreeXml(String parentid, String chkType) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		ArrayList listtest = new ArrayList();
		//ISmsServiceManager mgr = (ISmsServiceManager) ApplicationContextHolder
		//		.getInstance().getBean("IsmsServiceManager");
		
		//list = (ArrayList) mgr.getNextLevelServices(parentid, "0");
		//ILogConfig logConfig = null;
		try {
			LogConfigDAO=(LogConfigXmlDom4jDAO) ApplicationContextHolder.getInstance().getBean("LogConfigDAO");
			//if(chkType.equals("")){
			//listtest = (ArrayList)LogConfigDAO.findAllLogConfig("A");
			//}else{
			listtest = (ArrayList)LogConfigDAO.listChildLogConfig(chkType,parentid);	
			//}
			for (int i = 0; i < listtest.size(); i++) {
				ILogConfig logConfig = (ILogConfig) listtest.get(i);;
				//SmsService subService = (SmsService) list.get(i);
				String subModuleID = logConfig.getOperId().toString();
				String subModuleName = logConfig.getOperName();
				String status = logConfig.getStatus();
				int subModuleLeaf = new Integer(logConfig.getLeaf()).intValue();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subModuleID);
				jitem.put("text", subModuleName);
				if(status != null && !status.equals("") && status.equals("1")) {
					//判断节点为模块
					jitem.put(UIConstants.JSON_NODETYPE, "smsModule");
					jitem.put("allowChild", false);
					jitem.put("allownewnode", false);//模块节点设置右键菜单“新增模块业务”显示
					jitem.put("alloweditnode", false);//模块节点设置右键菜单“编辑”显示
					jitem.put("allownewsnode", false);//模块节点设置右键菜单“新增服务”显示
					//模块的标签
					jitem.put("qtip", "创建人:" + "没有"
							+ "<br \\/>模块名称:" + logConfig.getModelName());
				} else {
					 //判断节点为服务
					jitem.put(UIConstants.JSON_NODETYPE, "smsService");
					jitem.put("allowChild", false);
					jitem.put("allowedtsnode", false);//服务节点设置右键菜单“修改服务”显示
					jitem.put("allowcopynode", false);//服务节点设置右键菜单“复制服务ID至剪切板”显示			
					//服务的标签
					jitem.put("qtip", "服务ID：" + logConfig.getOperId().toString()
							+ "<br \\/>创建人:" + "没有"
							+ "<br \\/>服务名称:" +  logConfig.getOperName());
				}
				jitem.put("allowdeletesnode", false);//设置右键菜单“删除”永久显示				
				jitem.put("leaf", subModuleLeaf);

				if ("service".equalsIgnoreCase(chkType)) {
					jitem.put("checked", true);
				}
				jitem.put("qtipTitle", logConfig.getOperName());				
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成消息平台树图时报错：" + ex);
		}
		return json;
	}
}
