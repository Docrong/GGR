package com.boco.eoms.km.train.webapp.action;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.exam.mgr.KmExamTestMgr;
import com.boco.eoms.km.statics.mgr.PersonalStatisticMgr;
import com.boco.eoms.km.statics.util.PersonalStatisticConstants;
import com.boco.eoms.km.statics.util.StatisticMethod;
import com.boco.eoms.km.train.mgr.TrainRecordMgr;
import com.boco.eoms.km.train.mgr.TrainRequireMgr;
import com.boco.eoms.km.train.model.TrainDeptStatistic;
import com.boco.eoms.km.train.model.TrainPersonalStatistic;
import com.boco.eoms.km.train.model.TrainRecord;
import com.boco.eoms.km.train.model.TrainRequire;
import com.boco.eoms.km.train.model.TrainSpecialtyStatistic;
import com.boco.eoms.km.train.util.TrainRecordConstants;
import com.boco.eoms.km.train.webapp.form.TrainRecordForm;
import com.boco.eoms.km.train.webapp.form.TrainRequireForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:11:13 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public final class TrainRecordAction extends BaseAction {
 
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
	 * 新增培训记录
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
    	TrainRecordForm trainRecordForm = (TrainRecordForm)form;
    	trainRecordForm.setTrainUser(userId);
    	trainRecordForm.setTrainDept(dept);
    	updateFormBean(mapping, request, trainRecordForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改培训记录
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
    	TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainRecord trainRecord = trainRecordMgr.getTrainRecord(id);
		TrainRecordForm trainRecordForm = (TrainRecordForm) convert(trainRecord);
		updateFormBean(mapping, request, trainRecordForm);
		return mapping.findForward("edit");
	}
	
    /**
     * 查看培训记录详情
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
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainRecord trainRecord = trainRecordMgr.getTrainRecord(id);
		TrainRecordForm trainRecordForm = (TrainRecordForm) convert(trainRecord);
		updateFormBean(mapping, request, trainRecordForm);
		return mapping.findForward("detail");
    }
    
	/**
	 * 保存培训记录
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
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		
		TrainRecordForm trainRecordForm = (TrainRecordForm) form; 
		TrainRecord trainRecord = (TrainRecord) convert(trainRecordForm);
		trainRecord.setTrainUser(this.getUserId(request));
		
		boolean isNew = (null == trainRecordForm.getId() || "".equals(trainRecordForm.getId()));
		if (isNew) {
			trainRecordMgr.saveTrainRecord(trainRecord);
		} else {
			trainRecordMgr.saveTrainRecord(trainRecord);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 删除培训记录
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
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		trainRecordMgr.removeTrainRecord(id);
		return mapping.findForward("success");
	}

	/**
	 * 分页显示培训记录列表
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
				TrainRecordConstants.TRAINRECORD_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		Map map = (Map) trainRecordMgr.getTrainRecords(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(TrainRecordConstants.TRAINRECORD_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 根据培训的培训时间段 ，名字和培训人查询培训记录 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TrainRecordConstants.TRAINRECORD_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		TrainRecordForm trainRecordForm = (TrainRecordForm)form;
		String test = StaticMethod.null2String(request.getParameter("trainUserName"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String trainBeginTime = StaticMethod.null2String(request.getParameter("trainBeginTime"));
		request.setAttribute("trainBeginTime", trainBeginTime);
		Date beginTime = (Date)sdf.parse(trainBeginTime);
		System.out.print(beginTime);
		String trianEndTime = StaticMethod.null2String(request.getParameter("trianEndTime"));
		request.setAttribute("trianEndTime", trianEndTime);
		Date endTime = (Date)sdf.parse(trianEndTime);
		System.out.print(test);
//		List list = trainRecordMgr.getTrainRecords();
//		List listRecord = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			TrainRecord trainRecord = (TrainRecord)list.get(i);
//			Date trainDate = trainRecord.getTrainDate();
//			if(trainDate.after(beginTime)&&trainDate.before(endTime)){
//				listRecord.add(trainRecord);
//			}
//			list.clear();
//			list = listRecord;
//		}
		List list = trainRecordMgr.getTrainRecords(beginTime,endTime);
		System.out.println("--------------action中----------------------"+list.size()+"--------------------------------------");
		String trainUser = trainRecordForm.getTrainUser();
		String trainName = trainRecordForm.getTrainName();
		List listUser = new ArrayList();
		List listName = new ArrayList();
		try{
			if(trainUser!=null&&!trainUser.trim().equals("")){
				for(int i=0;i<list.size();i++){
					TrainRecord trianRecord = (TrainRecord)list.get(i);
					if(trainUser.equals(trianRecord.getTrainUser())){
						listUser.add(trianRecord);
					}
				}
				list.clear();
				list=listUser;
			}
			if(trainName!=null&&!trainName.trim().equals("")){
				for(int i=0;i<list.size();i++){
					TrainRecord trianRecord = (TrainRecord)list.get(i);
					if(trianRecord.getTrainName().indexOf(trainName)>-1){
						listName.add(trianRecord);
					}
				}
				list.clear();
				list=listName;
			}		 		  
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("--------------action中----------------------"+list.size()+"--------------------------------------");
		request.setAttribute(TrainRecordConstants.TRAINRECORD_LIST, list);
		request.setAttribute("resultSize",new Integer(list.size()));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 个人培训记录统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPersonal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TrainRecordConstants.TRAINRECORD_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String startDate = StaticMethod.null2String(request.getParameter("startDate"));
		String endDate = StaticMethod.null2String(request.getParameter("endDate"));
		
		//默认统计最近一周的记录
		if("".equals(endDate)){
			Date endDateTime = new Date(System.currentTimeMillis());			
			java.util.Date startDateTime = StatisticMethod.countDate(endDateTime, -6);

			endDate = StatisticMethod.dateToString(endDateTime, "yyyy-MM-dd");
			startDate = StatisticMethod.dateToString(startDateTime, "yyyy-MM-dd");
		}
		
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		Map map = (Map) trainRecordMgr.getPersonalStatistics(pageIndex, pageSize, startDate, endDate);
		List list = (List) map.get("result");
		List listUser = new ArrayList();
		//是否根据姓名查询统计
		String trainUser = StaticMethod.null2String(request.getParameter("trainUser"));
		if(!"".equals(trainUser)){
			for(int i=0;i<list.size();i++){
				TrainPersonalStatistic trainPersonalStatistic = (TrainPersonalStatistic)list.get(i);
				if(trainUser.equals(trainPersonalStatistic.getTrainUser())){
					listUser.add(trainPersonalStatistic);
				}
				list.clear();
				list = listUser;
			}
		}
		request.setAttribute(TrainRecordConstants.TRAINRECORD_LIST, list);
		
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("trainUser", trainUser);
		return mapping.findForward("listPersonal");
	}
	
	/**
	 * 培训名称记录统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward searchAll(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		String pageIndexName = new org.displaytag.util.ParamEncoder(
//				TrainRecordConstants.TRAINRECORD_LIST)
//				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
//		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
//				.getPageSize();
//		final Integer pageIndex = new Integer(GenericValidator
//				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
//				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
//		String startDate = StaticMethod.null2String(request.getParameter("startDate"));
//		String endDate = StaticMethod.null2String(request.getParameter("endDate"));		
//		
//		//默认统计最近一周的记录
//		if("".equals(endDate)){
//			Date endDateTime = new Date(System.currentTimeMillis());			
//			java.util.Date startDateTime = StatisticMethod.countDate(endDateTime, -6);
//
//			endDate = StatisticMethod.dateToString(endDateTime, "yyyy-MM-dd");
//			startDate = StatisticMethod.dateToString(startDateTime, "yyyy-MM-dd");
//		}
//		
//		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
//		Map map = (Map) trainRecordMgr.getAllStatistics(pageIndex, pageSize, startDate, endDate);
//		List list = (List) map.get("result");
//		request.setAttribute(TrainRecordConstants.TRAINRECORD_LIST, list);
//
//		request.setAttribute("resultSize", map.get("total"));
//		request.setAttribute("pageSize", pageSize);
//
//		request.setAttribute("startDate", startDate);
//		request.setAttribute("endDate", endDate);
//		return mapping.findForward("listAll");
//	}
//	
	/**
	 * 专业培训记录统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchSpecialty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TrainRecordConstants.TRAINRECORD_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String startDate = StaticMethod.null2String(request.getParameter("startDate"));
		String endDate = StaticMethod.null2String(request.getParameter("endDate"));		
		
		//默认统计最近一周的记录
		if("".equals(endDate)){
			Date endDateTime = new Date(System.currentTimeMillis());			
			java.util.Date startDateTime = StatisticMethod.countDate(endDateTime, -6);

			endDate = StatisticMethod.dateToString(endDateTime, "yyyy-MM-dd");
			startDate = StatisticMethod.dateToString(startDateTime, "yyyy-MM-dd");
		}
		
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		Map map = (Map) trainRecordMgr.getSpecialitylStatistics(pageIndex, pageSize, startDate, endDate);
		List list = (List) map.get("result");
		List listSpeciallity = new ArrayList();
		//是否根据专业查询统计
		String trainSpeciality = StaticMethod.null2String(request.getParameter("trainSpeciality"));
		if(!"".equals(trainSpeciality)){
			for(int i=0;i<list.size();i++){
				TrainSpecialtyStatistic trainSpecialtyStatistic = (TrainSpecialtyStatistic)list.get(i);
				if(trainSpeciality.equals(trainSpecialtyStatistic.getTrainSpeciality())){
					listSpeciallity.add(trainSpecialtyStatistic);
				}
				list.clear();
				list = listSpeciallity;
			}
		}
		request.setAttribute(TrainRecordConstants.TRAINRECORD_LIST, list);

		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("trainSpeciality", trainSpeciality);
		return mapping.findForward("listSpecialty");
	}
	
	/**
	 * 部门培训记录统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TrainRecordConstants.TRAINRECORD_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String startDate = StaticMethod.null2String(request.getParameter("startDate"));
		String endDate = StaticMethod.null2String(request.getParameter("endDate"));		
		
		//默认统计最近一周的记录
		if("".equals(endDate)){
			Date endDateTime = new Date(System.currentTimeMillis());			
			java.util.Date startDateTime = StatisticMethod.countDate(endDateTime, -6);

			endDate = StatisticMethod.dateToString(endDateTime, "yyyy-MM-dd");
			startDate = StatisticMethod.dateToString(startDateTime, "yyyy-MM-dd");
		}
		
		TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
		Map map = (Map) trainRecordMgr.getDeptStatistics(pageIndex, pageSize, startDate, endDate);
		List list = (List) map.get("result");
		List listDept = new ArrayList();
		//是否根据姓名查询统计
		String trainDept = StaticMethod.null2String(request.getParameter("trainDept"));
		if(!"".equals(trainDept)){
			for(int i=0;i<list.size();i++){
				TrainDeptStatistic trainDeptStatistic = (TrainDeptStatistic)list.get(i);
				if(trainDept.equals(trainDeptStatistic.getTrainDept())){
					listDept.add(trainDeptStatistic);
				}
				list.clear();
				list = listDept;
			}
		}
		request.setAttribute(TrainRecordConstants.TRAINRECORD_LIST, list);

		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("trainDept", trainDept);
		
		return mapping.findForward("listDept");
	}
	
	/**
	 * 分页显示培训记录列表，支持Atom方式接入Portal
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
//			TrainRecordMgr trainRecordMgr = (TrainRecordMgr) getBean("trainRecordMgr");
//			Map map = (Map) trainRecordMgr.getTrainRecords(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			TrainRecord trainRecord = new TrainRecord();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				trainRecord = (TrainRecord) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/trainRecord/trainRecords.do?method=edit&id="
//						+ trainRecord.getId() + "' target='_blank'>"
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