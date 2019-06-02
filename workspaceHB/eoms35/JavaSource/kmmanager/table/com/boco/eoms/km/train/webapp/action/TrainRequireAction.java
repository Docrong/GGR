package com.boco.eoms.km.train.webapp.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.exam.mgr.KmExamTestMgr;
import com.boco.eoms.km.exam.model.KmExamTest;
import com.boco.eoms.km.train.mgr.TrainRequireMgr;
import com.boco.eoms.km.train.mgr.TrainSpecialtyMgr;
import com.boco.eoms.km.train.model.TrainRequire;
import com.boco.eoms.km.train.util.TrainRequireConstants;
import com.boco.eoms.km.train.webapp.form.TrainRequireForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.ui.util.UIConstants;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 15:34:49 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public final class TrainRequireAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
 	
 	/**
	 * 新增培训需求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	String userId = this.getUser(request).getUserid();
    	String dept = this.getUser(request).getDeptid();
    	TrainRequireForm trainRequireForm = (TrainRequireForm)form;
    	trainRequireForm.setTrainUser(userId);
    	trainRequireForm.setTrainDept(dept);
    	updateFormBean(mapping, request, trainRequireForm);
		return mapping.findForward("edit");
	}
	
	/**
	 *  查看培训需求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainRequire trainRequire = trainRequireMgr.getTrainRequire(id);
		TrainRequireForm trainRequireForm = (TrainRequireForm) convert(trainRequire);
		updateFormBean(mapping, request, trainRequireForm);
		return mapping.findForward("detail");
	}

	/**
	 * 修改培训需求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainRequire trainRequire = trainRequireMgr.getTrainRequire(id);
		TrainRequireForm trainRequireForm = (TrainRequireForm) convert(trainRequire);
		updateFormBean(mapping, request, trainRequireForm);
		return mapping.findForward("edit");
	}
	
    /**
     * 根据需求id得到 需求编号信息 分类 分类名信息 用于ajax调用
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getRequire(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		TrainSpecialtyMgr trainSpecialtyMgr = (TrainSpecialtyMgr) getBean("trainSpecialtyMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainRequire trainRequire = trainRequireMgr.getTrainRequire(id);
		//培训编号
		String  trainNo = trainRequire.getTrainNo().toString();
		//专业
		String trainSpeciality = trainRequire.getTrainSpeciality();
		//专业名称
		String trainSpecialityName = trainSpecialtyMgr.getTrainSpecialtyByNodeId(trainSpeciality).getSpecialtyName();
		//返回文本信息
		String text = trainNo + "|||" + trainSpecialityName +"|||" + trainSpeciality;
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(text.toString());
		return null;
	}
    
	/**
	 * 保存培训需求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		TrainRequireForm trainRequireForm = (TrainRequireForm) form;
		String trainUser = this.getUser(request).getUserid();
		trainRequireForm.setTrainUser(trainUser);
		
		boolean isNew = (null == trainRequireForm.getId() || "".equals(trainRequireForm.getId()));
		TrainRequire trainRequire = (TrainRequire) convert(trainRequireForm);
		if (isNew) {
			List list = trainRequireMgr.getTrainRequires();
			//总记录数
			int count = list.size();
			Integer trainNo = new Integer(Integer.parseInt("0"+StaticMethod.getYYMMDD()+"000")+count+1);
			trainRequire.setIsDelete("0");
			trainRequire.setTrainNo(trainNo);
			trainRequireMgr.saveTrainRequire(trainRequire);
		} else {
			trainRequireMgr.saveTrainRequire(trainRequire);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 删除培训需求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainRequire trainRequire = trainRequireMgr.getTrainRequire(id);
		trainRequire.setIsDelete("1"); //删除标识
		trainRequireMgr.saveTrainRequire(trainRequire);
		return mapping.findForward("success");
	}
	
	/**
	 * 分页显示培训需求列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TrainRequireConstants.TRAINREQUIRE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		String whereStr = " where trainRequire.isDelete = '0'";
		Map map = (Map) trainRequireMgr.getTrainRequires(pageIndex, pageSize, whereStr);
		List list = (List) map.get("result");
		request.setAttribute(TrainRequireConstants.TRAINREQUIRE_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		
		return mapping.findForward("list");
	}
	/**
	 * 分页显示培训需求列表（为了需求和专业的联动）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchMust(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TrainRequireConstants.TRAINREQUIRE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		String whereStr = " where trainRequire.isDelete = '0'";
		Map map = (Map) trainRequireMgr.getTrainRequires(pageIndex, pageSize, whereStr);
		List list = (List) map.get("result");
		request.setAttribute(TrainRequireConstants.TRAINREQUIRE_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		
		return mapping.findForward("listMust");
	}

	/**
	 * 获取模型分类树(第一层)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNodesRadioTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		JSONArray jsonRoot = new JSONArray();
		TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
		List list = (List)trainRequireMgr.getTrainRequires();
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			TrainRequire trainRequire = (TrainRequire) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", trainRequire.getTrainNo());
			// TODO 添加节点名称
			jitem.put("text",trainRequire.getTrainQuestion());
			jitem.put(UIConstants.JSON_NODETYPE, "folder");
			//jitem.put("iconCls", "folder");

			// 设置是否为叶子节点
			boolean leafFlag = true;
		
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			jitem.put("qtip", trainRequire.getTrainNo());

			jitem.put("checked", false);		
			jsonRoot.put(jitem);
		}
		//JSONUtil.print(response, jsonRoot.toString());		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
	}
	
	/**
	 * 实现excel导出
	 * 
	 * @param request
	 * @param excelname
	 * @param list
	 * @return
	 */
//	  public void generalExcelFile(HttpServletRequest request, String excelname,
//			List list) {
//		String configPath = "";
//		try{
//		configPath =  TrainRequireAction.class.getResource("/").toString();
//		configPath = configPath.substring(5)+"com/boco/eoms/km/config/";
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		com.boco.eoms.km.excelmanage.PoiExcel poiExcel = new com.boco.eoms.km.excelmanage.PoiExcel(configPath);
//		String path = poiExcel.getPoiExcel(excelname, list);
//		request.setAttribute("excelfile", path);
//		request.setAttribute("excelfilename", path.substring(path
//				.lastIndexOf(File.separator) + 1, path.length()));
//	}
	
	/**
	 * 分页显示培训需求列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			TrainRequireMgr trainRequireMgr = (TrainRequireMgr) getBean("trainRequireMgr");
//			Map map = (Map) trainRequireMgr.getTrainRequires(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			TrainRequire trainRequire = new TrainRequire();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				trainRequire = (TrainRequire) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/trainRequire/trainRequires.do?method=edit&id="
//						+ trainRequire.getId() + "' target='_blank'>"
//						+ display name for list + "</a>");
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
//				
//				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
//				entry.setUpdated(new java.util.Date());
//				entry.setPublished(new java.util.Date());
//				entry.setEdited(new java.util.Date());
//				
//				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
//			}
//			
//			// 每页显示条数
//			feed.setText(map.get("total").toString());
//		    OutputStream os = response.getOutputStream();
//		    PrintStream ps = new PrintStream(os);
//		    feed.getDocument().writeTo(ps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}