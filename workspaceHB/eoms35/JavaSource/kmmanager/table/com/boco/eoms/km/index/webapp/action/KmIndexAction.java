package com.boco.eoms.km.index.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.file.model.KmFileTree;
import com.boco.eoms.km.index.mgr.KmIndexMgr;
import com.boco.eoms.km.lesson.model.KmLesson;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.km.util.DateTime;

/**
 * 知识管理首页信息处理 Action
 * 
 * @author ZHANGXB
 * @version 1.0
 * 
 */
public final class KmIndexAction extends BaseAction {

	/**
	 * 未指定方法时默认调用的方法
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 1.0
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	/**
	 * 查询当前用户的知识总积分/专家总积分/专家头像
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward getUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 读取当前用户登录ID
		String operateUserId = this.getUserId(request);

		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		
		// 查询当前用户的知识总积分
		Integer knowledgeScore = kmIndexMgr.getKnowledgeScoreByUserId(operateUserId);
		
		// 查询当前用户的专家总积分
		Integer expertScore = kmIndexMgr.getExpertScoreByUserId(operateUserId);

		// 查询当前用户专家头像
		String expertPhoto = kmIndexMgr.getExpertPhotoByUserId(operateUserId);

		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		
		JSONObject jitem = new JSONObject();				
		jitem.put("knowledgeScore", knowledgeScore); // 知识总积分		
		jitem.put("expertScore", expertScore); // 专家总积分		
		jitem.put("expertPhoto", expertPhoto); // 专家头像
	
		json.put(jitem);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
		return null;
	}

	/**
	 * 查询从某一日期到当前时间内的知识库知识数量排名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward knowledgeTableAmountOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 最大行数，如果 maxResults>0 则返回总排行的前几名
		int maxResults = StaticMethod.null2int(request.getParameter("orderNumber"));
		String timeType = StaticMethod.null2String(request.getParameter("time"));
		Date beginDate = null;
		
		//按年统计(1年内)
		if(timeType.toUpperCase().equals("YEAR")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.YEAR, -1);
		}
		//按季统计(3个月内)
		else if(timeType.toUpperCase().equals("QUARTER")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -3);
		}
		//按月统计(1个月内)
		else if(timeType.toUpperCase().equals("MONTH")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -1);
		}
		//按周统计(7日内)
		else if(timeType.toUpperCase().equals("WEEK")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.DAY_OF_MONTH, -7);
		}

		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listKnowledgeTableAmountOrder(beginDate, maxResults);
		
		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		int listSize = list.size();
		for(int i=0;i<listSize;i++){
			JSONObject jitem = new JSONObject();
			Object[] objs = (Object[])list.get(i);
			jitem.put("id", objs[0]); // 知识库主键
			jitem.put("user", objs[1]); // 知识库中文名称
			jitem.put("score", objs[2]); //数量
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
		return null;
		
	}
	
	/**
	 * 查询从某一日期到当前时间内的知识库知识阅读次数排名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward knowledgeReadCountOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 最大行数，如果 maxResults>0 则返回总排行的前几名
		int maxResults = StaticMethod.null2int(request.getParameter("orderNumber"));
		String timeType = StaticMethod.null2String(request.getParameter("time"));
		Date beginDate = null;
		
		//按年统计(1年内)
		if(timeType.toUpperCase().equals("YEAR")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.YEAR, -1);
		}
		//按季统计(3个月内)
		else if(timeType.toUpperCase().equals("QUARTER")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -3);
		}
		//按月统计(1个月内)
		else if(timeType.toUpperCase().equals("MONTH")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -1);
		}
		//按周统计(7日内)
		else if(timeType.toUpperCase().equals("WEEK")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.DAY_OF_MONTH, -7);
		}

		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listKnowledgeReadCountOrder(beginDate, maxResults);
		
		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		int listSize = list.size();
		for(int i=0;i<listSize;i++){
			JSONObject jitem = new JSONObject();
			Object[] objs = (Object[])list.get(i);
			jitem.put("id", objs[2]); // 知识库主键
			jitem.put("user", objs[3]); // 知识库中文名称
			jitem.put("score", objs[4]); //数量
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
		return null;
		
	}
	
	/**
	 * 查询从某一日期到当前时间内的用户知识积分排行前几名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward userKnowledgeScoreOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 最大行数，如果 maxResults>0 则返回总排行的前几名
		int maxResults = StaticMethod.null2int(request.getParameter("orderNumber"));
		String timeType = StaticMethod.null2String(request.getParameter("time"));
		Date beginDate = null;
		
		//按年统计(1年内)
		if(timeType.toUpperCase().equals("YEAR")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.YEAR, -1);
		}
		//按季统计(3个月内)
		else if(timeType.toUpperCase().equals("QUARTER")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -3);
		}
		//按月统计(1个月内)
		else if(timeType.toUpperCase().equals("MONTH")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -1);
		}
		//按周统计(7日内)
		else if(timeType.toUpperCase().equals("WEEK")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.DAY_OF_MONTH, -7);
		}

		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listUsersKnowledgeScoreOrder(beginDate, maxResults);
	
		
		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		int listSize = list.size();
		for(int i=0;i<listSize;i++){
			JSONObject jitem = new JSONObject();
			Object[] objs = (Object[])list.get(i);
			jitem.put("user", objs[0]); // 用户
			jitem.put("score", objs[1]);
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
		return null;
		
	}

	
	/**
	 * 查询从某一日期到当前时间内的用户知识积分排行前几名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward userKnowledgeContributeOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 最大行数，如果 maxResults>0 则返回总排行的前几名
		int maxResults = StaticMethod.null2int(request.getParameter("orderNumber"));
		String timeType = StaticMethod.null2String(request.getParameter("time"));
		Date beginDate = null;
		
		//按年统计(1年内)
		if(timeType.toUpperCase().equals("YEAR")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.YEAR, -1);
		}
		//按季统计(3个月内)
		else if(timeType.toUpperCase().equals("QUARTER")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -3);
		}
		//按月统计(1个月内)
		else if(timeType.toUpperCase().equals("MONTH")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.MONTH, -1);
		}
		//按周统计(7日内)
		else if(timeType.toUpperCase().equals("WEEK")){
			beginDate = DateTime.getDateAddAmount(new Date(), Calendar.DAY_OF_MONTH, -7);
		}

		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listUsersKnowledgeScoreOrder(beginDate, maxResults);
	
		
		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		int listSize = list.size();
		for(int i=0;i<listSize;i++){
			JSONObject jitem = new JSONObject();
			Object[] objs = (Object[])list.get(i);
			jitem.put("user", objs[0]); // 用户
			jitem.put("score", objs[1]);
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("[]");
		
		return null;
		
	}
	
	
	/**
	 * 查询当前值班专家
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward listOnDutyExperts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String sysDate = dateFormat.format(new Date());
		
		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listOnDutyExperts(sysDate);
	
		
		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		int listSize = list.size();
		for(int i=0;i<listSize;i++){
			JSONObject jitem = new JSONObject();
			Object[] objs = (Object[])list.get(i);
			jitem.put("userName", objs[2]); // 用户
			jitem.put("photo", StaticMethod.null2String((String)objs[3]));
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
		return null;
	}

	/**
	 * 查询知识库知识
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward listKmContents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 最大行数
		int maxResults = StaticMethod.null2int(request.getParameter("count"));

		// 根据 searchType 查询对应的知识列表
		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listKmContents(maxResults);
		
		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			Object[] kmContent = (Object[]) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("contentId", kmContent[0]);
			jitem.put("tableName", kmContent[1]);
			jitem.put("contentTitle", kmContent[3]);
			jitem.put("createTime", kmContent[4].toString().substring(0, 10));
			jitem.put("userName", kmContent[5]);
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
		return null;
	}
	
	/**
	 * 查询文档库知识
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward listKmFiles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 最大行数
		int maxResults = StaticMethod.null2int(request.getParameter("count"));

		// 根据 searchType 查询对应的知识列表
		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listKmFiles(maxResults);

		// 设置返回页面的信息
		JSONArray json = new JSONArray();
		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			Object[] kmContent = (Object[]) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("fileId", kmContent[0]);
			jitem.put("nodeName", kmContent[1]);
			jitem.put("fileName", kmContent[2]);
			jitem.put("uploadTime", kmContent[3].toString().substring(0, 10));
			jitem.put("userName", kmContent[4]);
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		
		return null;		
	}
	
	
	/**
	 * 查询知识管理树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward listKmContentTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		
		//查询知识树的第一级菜单
		List subNodeList = kmIndexMgr.listKmContentTree("1");
		JSONArray jsonRoot = new JSONArray();
		for (int i = 0; i < subNodeList.size(); i++) {
			KmTableTheme kmTableTheme = (KmTableTheme) subNodeList.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmTableTheme.getNodeId());
			jitem.put("text", kmTableTheme.getThemeName());
			jsonRoot.put(jitem);
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonRoot.toString());
		
		return null;
	}
	
	
	/**
	 * 查询文件管理树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward listKmFileTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		
		//查询文件树的第一级菜单
		List subNodeList = kmIndexMgr.listKmFileTree("1");

		JSONArray jsonRoot = new JSONArray();
		for (int i = 0; i < subNodeList.size(); i++) {
			KmFileTree treeNode = (KmFileTree) subNodeList.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("id", treeNode.getNodeId());
			jitem.put("text", treeNode.getNodeName());
			jsonRoot.put(jitem);
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonRoot.toString());
		
		return null;
	}
	
	/**
	 * 查询文件管理树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public ActionForward listKmLessonTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 最大行数
		int maxResults = StaticMethod.null2int(request.getParameter("count"));
		
		KmIndexMgr kmIndexMgr = (KmIndexMgr) getBean("kmIndexMgr");
		List list = kmIndexMgr.listKmLessonTree(maxResults);
		JSONArray json = new JSONArray();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			KmLesson kmLesson = (KmLesson) iterator.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmLesson.getId());
			jitem.put("text", kmLesson.getLessonName());
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		return null;
	}
	
}
