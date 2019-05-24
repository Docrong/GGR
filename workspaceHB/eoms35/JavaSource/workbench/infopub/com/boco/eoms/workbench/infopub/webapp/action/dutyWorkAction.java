package com.boco.eoms.workbench.infopub.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.ConvertUtil;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.util.DeptMgrLocator;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.util.BulletinMgrLocator;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.InterfaceUtilVariable;
import com.boco.eoms.workbench.infopub.dao.dutyWorkDao;
import com.boco.eoms.workbench.infopub.mgr.IForumsManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;
import com.boco.eoms.workbench.infopub.model.dutyWork;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpStubUserVO;
import com.huawei.csp.si.service.BulletinLocator;
import com.huawei.csp.si.service.BulletinPortType;

/**
 * 
 * <p>
 * Title:信息发布中的信息
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 4:57:45 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public final class dutyWorkAction extends BaseAction {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
	.getInstance();

	public ActionForward getduty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String contentPath = request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getLocalPort() +  request.getContextPath();
		
		TawSystemCptroom tawSystemCptroom = null;
		List list = null;
		dutyWorkDao dutyWorkDao = new dutyWorkDao();
		list = dutyWorkDao.getdutyWorkList();
		
		//start
				
//		 创建ATOM源
		Factory factory = Abdera.getNewFactory();
		Feed dutyfeed = factory.newFeed();
		// 分页
		for (int i = 0; i < list.size(); i++) {
			tawSystemCptroom = (TawSystemCptroom) list.get(i);
			Entry entry = dutyfeed.insertEntry();
			entry.setTitle(tawSystemCptroom.getRoomname());
			entry.setSummary(tawSystemCptroom.getId()+"");
			entry.setContent(contentPath+"/workbench/infopub/dutyWorkAction.do?id="+tawSystemCptroom.getId()+"&method=getdutyList");
		}
		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		dutyfeed.getDocument().writeTo(ps);		 
		//end		
		return null;
	}
	public ActionForward getdutyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int roomId = Integer.parseInt(request.getParameter("id"));
		String time =StaticMethod.getCurrentDateTime("yyyy-MM-dd");
		List list = null;
		dutyWorkDao dutyWorkDao = new dutyWorkDao();
		list = dutyWorkDao.getdutyList(roomId,time);
		//start
				
//		 创建ATOM源
		Factory factory = Abdera.getNewFactory();
		Feed dutyfeedList = factory.newFeed();
		// 分页
		for (int i = 0; i < list.size(); i++) {
			dutyWork dutyWork = new dutyWork();
			dutyWork = (dutyWork)list.get(i);
			Entry entry = dutyfeedList.insertEntry();
			entry.setTitle(dutyWork.getUsername());
		}
		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		dutyfeedList.getDocument().writeTo(ps);		 
		//end		
		return null;
	}
	private Hashtable performDailyExecuteList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String deptId = saveSessionBeanForm.getDeptid(); // 当前部门编号
		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// List stubUserList = saveSessionBeanForm.getStubUserList(); //代理信息集合
		List stubUserList = saveSessionBeanForm.getStubUserList();
		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		Hashtable monthPlanVOHash = null;
		Hashtable tempHash = null;
		Enumeration tempkeys = null;
		String monthPlanId = "";
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		List stubMonthPlanList = new ArrayList();
		List listKey = null;
		// 获取执行作业计划集合
		try {
			monthPlanVOHash = tawwpExecuteMgr.listExecutePlanNew(userId, String
					.valueOf(deptId));

			// 如果代理信息存在
			if (stubUserList != null) {

				for (int i = 0; i < stubUserList.size(); i++) {
					// 获取代理信息VO对象
					tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
					// 获取代理申请人需要执行的月度作业计划
					tempHash = new Hashtable();
					tempHash = tawwpExecuteMgr.listExecutePlan(tawwpStubUserVO
							.getCruser(), String.valueOf(deptId));
					if (tempHash.size() != 0) {
						// 取出全部月度作业计划编号
						tempkeys = tempHash.keys();

						while (tempkeys.hasMoreElements()) {
							monthPlanId = (String) tempkeys.nextElement();
							// 获取月度作业计划VO对象,并修改代理标志位
							tawwpMonthPlanVO = (TawwpMonthPlanVO) (tempHash
									.get(monthPlanId));
							tawwpMonthPlanVO.setStubFlag("1");
							tawwpMonthPlanVO.setUserByStub(tawwpStubUserVO
									.getCruser()); // 修改被代理用户名
						}
						stubMonthPlanList.add(tempHash);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monthPlanVOHash;
	}

}
