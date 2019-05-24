package com.boco.eoms.partdata.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partdata.mgr.TawPartFourteenSignalMgr;
import com.boco.eoms.partdata.model.TawPartFourteenSignal;
import com.boco.eoms.partdata.util.TawPartFourteenSignalConstants;
import com.boco.eoms.partdata.webapp.form.TawPartFourteenSignalForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import java.util.Date;

/**
 * <p>
 * Title:14位信令点
 * </p>
 * <p>
 * Description:14位信令点范围
 * </p>
 * <p>
 * Tue Jul 06 14:18:23 CST 2010
 * </p>
 * 
 * @moudle.getAuthor() Josh
 * @moudle.getVersion() 3.5
 * 
 */
public final class TawPartFourteenSignalAction extends BaseAction {
 
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
	 * 新增14位信令点
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
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改14位信令点
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
    	TawPartFourteenSignalMgr tawPartFourteenSignalMgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawPartFourteenSignal tawPartFourteenSignal = tawPartFourteenSignalMgr.getTawPartFourteenSignal(id);
		Map map = (Map) tawPartFourteenSignalMgr.getExistTawPartFourteenSignals(Integer.valueOf(tawPartFourteenSignal.getSignalnum()),
				Integer.valueOf(tawPartFourteenSignal.getSignalnum()));
		request.getSession().setAttribute("startNum", tawPartFourteenSignal.getSignalnum());
		request.getSession().setAttribute("endNum", tawPartFourteenSignal.getSignalnum());
		request.getSession().setAttribute("map", map);
		
		//String start = StaticMethod.nullObject2String(request.getSession().getAttribute("startNum"));
		//updateFormBean(mapping, request, tawPartLacForm);
		return mapping.findForward("editsingle");
	}
	
	/**
	 * 保存14位信令点
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
		TawPartFourteenSignalMgr tawPartFourteenSignalMgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
		TawPartFourteenSignalForm tawPartFourteenSignalForm = (TawPartFourteenSignalForm) form;
		boolean isNew = (null == tawPartFourteenSignalForm.getId() || "".equals(tawPartFourteenSignalForm.getId()));
		TawPartFourteenSignal tawPartFourteenSignal = (TawPartFourteenSignal) convert(tawPartFourteenSignalForm);
		if (isNew) {
			tawPartFourteenSignalMgr.saveTawPartFourteenSignal(tawPartFourteenSignal);
		} else {
			tawPartFourteenSignalMgr.saveTawPartFourteenSignal(tawPartFourteenSignal);
		}
		return search(mapping, tawPartFourteenSignalForm, request, response);
	}
	
	/**
	 * 删除14位信令点
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
		TawPartFourteenSignalMgr tawPartFourteenSignalMgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		tawPartFourteenSignalMgr.removeTawPartFourteenSignal(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示14位信令点列表
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
				TawPartFourteenSignalConstants.TAWPARTFOURTEENSIGNAL_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawPartFourteenSignalMgr tawPartFourteenSignalMgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
		Map map = (Map) tawPartFourteenSignalMgr.getTawPartFourteenSignals(pageIndex, pageSize, " order by tawPartFourteenSignal.signalnum");
		List list = (List) map.get("result");
		request.setAttribute(TawPartFourteenSignalConstants.TAWPARTFOURTEENSIGNAL_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("listall");
	}
	
	/**
	 * 分页显示14位信令点列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			TawPartFourteenSignalMgr tawPartFourteenSignalMgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
			Map map = (Map) tawPartFourteenSignalMgr.getTawPartFourteenSignals(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			TawPartFourteenSignal tawPartFourteenSignal = new TawPartFourteenSignal();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				tawPartFourteenSignal = (TawPartFourteenSignal) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle(""
						//张晓杰修改
//						"<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/tawPartLac/tawPartLacs.do?method=edit&id="
//						+ tawPartLac.getId() + "' target='_blank'>"
//						+ display name for list + "</a>"
						);
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
				
				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
				entry.setUpdated(new java.util.Date());
				entry.setPublished(new java.util.Date());
				entry.setEdited(new java.util.Date());
				
				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
			}
			
			// 每页显示条数
			feed.setText(map.get("total").toString());
		    OutputStream os = response.getOutputStream();
		    PrintStream ps = new PrintStream(os);
		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward distribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startNum = StaticMethod.null2String(request.getParameter("start"));
		String endNum = StaticMethod.null2String(request.getParameter("end"));
		TawPartFourteenSignalMgr mgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
		Map map = (Map) mgr.getExistTawPartFourteenSignals(Integer.valueOf(startNum),
				Integer.valueOf(endNum));
		request.getSession().setAttribute("startNum", startNum);
		request.getSession().setAttribute("endNum", endNum);
		request.getSession().setAttribute("map", map);
		return mapping.findForward("list");//search(mapping, tawPartLacForm, request, response);
	}
	
	public ActionForward confirmDistribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startNum = StaticMethod.nullObject2String(request.getSession().getAttribute("startNum"));
		String endNum = StaticMethod.nullObject2String(request.getSession().getAttribute("endNum"));
		
		TawPartFourteenSignalMgr mgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
		Map map = contextMap();
		
		for(int i=Integer.parseInt(startNum); i <= Integer.parseInt(endNum); i++)
		{
			if(map.containsKey(String.valueOf(i)))
			{
				TawPartFourteenSignal tawPartFourteenSignal = (TawPartFourteenSignal)map.get(String.valueOf(i));
				tawPartFourteenSignal.setSignalvalue(StaticMethod.nullObject2String(
						request.getParameter(
								String.valueOf(i))));
				tawPartFourteenSignal.setUpdatedate(StaticMethod.getLocalString());
				tawPartFourteenSignal.setUserid(((TawSystemSessionForm)request
						.getSession().getAttribute("sessionform")).getUserid()
						);
				mgr.saveTawPartFourteenSignal(tawPartFourteenSignal);
			}
			else
			{
				TawPartFourteenSignal tawPartFourteenSignal = new TawPartFourteenSignal();
			tawPartFourteenSignal.setSignalnum(String.valueOf(i));
			tawPartFourteenSignal.setSignalvalue(StaticMethod.nullObject2String(
					request.getParameter(
							String.valueOf(i))));
			tawPartFourteenSignal.setUpdatedate(StaticMethod.getLocalString());
			tawPartFourteenSignal.setUserid(((TawSystemSessionForm)request
					.getSession().getAttribute("sessionform")).getUserid()
					);
			mgr.saveTawPartFourteenSignal(tawPartFourteenSignal);
			}
		}
		
		//TawPartLacMgr mgr = (TawPartLacMgr) getBean("tawPartLacMgr");
		
		return mapping.findForward("success");//mapping.findForward("listall");
	}	
	
	private Map contextMap()
	{
		TawPartFourteenSignalMgr mgr = (TawPartFourteenSignalMgr) getBean("tawPartFourteenSignalMgr");
		List list = (List)((Map)mgr.getAllTawPartFourteenSignals("")).get("result");
		Map resultMap = new HashMap();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			TawPartFourteenSignal tawlac = (TawPartFourteenSignal)itr.next();
			resultMap.put(tawlac.getSignalnum(), tawlac);
		}
		return resultMap;
	
	}
	
 	
    public ActionForward forwardList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("listall");
	}
}