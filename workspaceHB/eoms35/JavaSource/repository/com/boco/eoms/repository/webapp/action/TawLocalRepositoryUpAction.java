package com.boco.eoms.repository.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.repository.webapp.form.TawLocalRepositoryForm;
import com.boco.eoms.repository.webapp.form.TawLocalRepositoryUpForm;
import com.boco.eoms.repository.mgr.TawLocalRepositoryMgr;
import com.boco.eoms.repository.mgr.TawLocalRepositoryUpMgr;
import com.boco.eoms.repository.model.TawLocalRepository;
import com.boco.eoms.repository.model.TawLocalRepositoryUp;
import com.boco.eoms.repository.util.TawLocalRepositoryUpConstants;
import com.boco.eoms.sheet.softchange.model.SoftChangeMain;
import com.boco.eoms.sheet.softchange.service.ISoftChangeMainManager;
import com.mcm.zyzl.zlgl.stationdel;

/**
 * <p>
 * Title:tawLocalRepositoryUp
 * </p>
 * <p>
 * Description:tawLocalRepositoryUp
 * </p>
 * <p>
 * Fri Oct 30 16:52:13 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李锋
 * @moudle.getVersion() 1.0
 * 
 */
public final class TawLocalRepositoryUpAction extends BaseAction {
 
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
	 * 新增tawLocalRepositoryUp
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
    	
		TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
		String repositoryId = StaticMethod.null2String(request.getParameter("repositoryId"));
		TawLocalRepository tawLocalRepository = tawLocalRepositoryMgr.getTawLocalRepository(repositoryId);

		TawLocalRepositoryUpForm tawLocalRepositoryUpForm = (TawLocalRepositoryUpForm) form;
		tawLocalRepositoryUpForm.setBeforeHardwareRepository(tawLocalRepository.getHardwareRepository());
		tawLocalRepositoryUpForm.setBeforeSoftwareRepository(tawLocalRepository.getSoftwareRepository());
		tawLocalRepositoryUpForm.setBeforePatch(tawLocalRepository.getPatch());
		tawLocalRepositoryUpForm.setRepositoryId(repositoryId);
		
		updateFormBean(mapping, request, tawLocalRepositoryUpForm);
		
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改tawLocalRepositoryUp
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
		TawLocalRepositoryUpMgr tawLocalRepositoryUpMgr = (TawLocalRepositoryUpMgr) getBean("tawLocalRepositoryUpMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawLocalRepositoryUp tawLocalRepositoryUp = tawLocalRepositoryUpMgr.getTawLocalRepositoryUp(id);
		TawLocalRepositoryUpForm tawLocalRepositoryUpForm = (TawLocalRepositoryUpForm) convert(tawLocalRepositoryUp);
		updateFormBean(mapping, request, tawLocalRepositoryUpForm);
		
		request.setAttribute("oper", "edit");
		
		//修改升级信息时，还是跳回到修改页面
		if("edit".equals(StaticMethod.nullObject2String(request.getParameter("oper")))){
			
			request.setAttribute("saveFlag", "修改成功！");
		}
		
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存tawLocalRepositoryUp
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
		TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
		TawLocalRepositoryUpMgr tawLocalRepositoryUpMgr = (TawLocalRepositoryUpMgr) getBean("tawLocalRepositoryUpMgr");
		TawLocalRepositoryUpForm tawLocalRepositoryUpForm = (TawLocalRepositoryUpForm) form;
		boolean isNew = (null == tawLocalRepositoryUpForm.getId() || "".equals(tawLocalRepositoryUpForm.getId()));
		TawLocalRepositoryUp tawLocalRepositoryUp = (TawLocalRepositoryUp) convert(tawLocalRepositoryUpForm);

		//修改版本库信息
	    String id=tawLocalRepositoryUp.getRepositoryId();
	    TawLocalRepository tawLocalRepository = tawLocalRepositoryMgr.getTawLocalRepository(id);

		tawLocalRepository.setHardwareRepository(tawLocalRepositoryUp.getHardwareRepository());
		tawLocalRepository.setSoftwareRepository(tawLocalRepositoryUp.getSoftwareRepository());
		tawLocalRepository.setPatch(tawLocalRepositoryUp.getPatch());

		tawLocalRepositoryMgr.saveTawLocalRepository(tawLocalRepository);
		
		//保存或修改升级信息
		if (isNew) {
			tawLocalRepositoryUpMgr.saveTawLocalRepositoryUp(tawLocalRepositoryUp);
		} else {
			tawLocalRepositoryUpMgr.saveTawLocalRepositoryUp(tawLocalRepositoryUp);
		}
		
		//修改升级信息时，还是跳回到修改页面
		if("edit".equals(StaticMethod.nullObject2String(request.getParameter("oper")))){
			
			return edit(mapping, form, request, response);
		}
		
		return mapping.findForward("success");
	}
	
	/**
	 * 删除tawLocalRepositoryUp
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
		TawLocalRepositoryUpMgr tawLocalRepositoryUpMgr = (TawLocalRepositoryUpMgr) getBean("tawLocalRepositoryUpMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		tawLocalRepositoryUpMgr.removeTawLocalRepositoryUp(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示tawLocalRepositoryUp列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryDo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("query");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawLocalRepositoryUpConstants.TAWLOCALREPOSITORYUP_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawLocalRepositoryUpMgr tawLocalRepositoryUpMgr = (TawLocalRepositoryUpMgr) getBean("tawLocalRepositoryUpMgr");
		
		TawLocalRepositoryForm tawLocalRepositoryForm = (TawLocalRepositoryForm) form;
		
		TawLocalRepository tawLocalRepository = (TawLocalRepository) convert(tawLocalRepositoryForm);
		
		String queryStr = "";
		if(!(tawLocalRepository.getProvince()==""))
			queryStr = " and  tawLocalRepository.province = '" +tawLocalRepository.getProvince()+"'";
		if(!(tawLocalRepository.getCity()==""))
			queryStr = " and  tawLocalRepository.city = '" +tawLocalRepository.getCity()+"'";
		if(!(tawLocalRepository.getNet()==""))
			queryStr = " and  tawLocalRepository.net = '" +tawLocalRepository.getNet()+"'";
		if(!(tawLocalRepository.getNetType()==""))
			queryStr = " and  tawLocalRepository.netType = '" +tawLocalRepository.getNetType()+"'";
		if(!(tawLocalRepository.getCompany()==""))
			queryStr = " and  tawLocalRepository.company = '" +tawLocalRepository.getCompany()+"'";
		if(!(tawLocalRepository.getNetModale()==""))
			queryStr = " and  tawLocalRepository.netModle = '" +tawLocalRepository.getNetModale()+"'";
		if(!(tawLocalRepository.getHardwareRepository()==""))
			queryStr = " and  tawLocalRepository.hardwareRepository  = '" +tawLocalRepository.getHardwareRepository()+"'";
		if(!(tawLocalRepository.getSoftwareRepository()==""))
			queryStr = " and  tawLocalRepository.softwareRepository = '" +tawLocalRepository.getSoftwareRepository()+"'";
		if(!(tawLocalRepository.getPatch()==""))
			queryStr = " and  tawLocalRepository.patch  = '" +tawLocalRepository.getPatch()+"'";
		if(!(tawLocalRepository.getNetworkTime()==""))
			queryStr = " and  tawLocalRepository.networkTime   = '" +tawLocalRepository.getNetworkTime()+"'";
//		if(!(tawLocalRepository.getState()==""))
//			queryStr = " and  tawLocalRepository.state  = '" +tawLocalRepository.getState()+"'";
		Map map = (Map) tawLocalRepositoryUpMgr.getTawLocalRepositoryUps(pageIndex, pageSize, queryStr);
		List list = (List) map.get("result");
		request.setAttribute(TawLocalRepositoryUpConstants.TAWLOCALREPOSITORYUP_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	
	/**
	 * 分页显示tawLocalRepositoryUp列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawLocalRepositoryUpConstants.TAWLOCALREPOSITORYUP_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawLocalRepositoryUpMgr tawLocalRepositoryUpMgr = (TawLocalRepositoryUpMgr) getBean("tawLocalRepositoryUpMgr");
		Map map = (Map) tawLocalRepositoryUpMgr.getTawLocalRepositoryUps(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(TawLocalRepositoryUpConstants.TAWLOCALREPOSITORYUP_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	public void update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sheetId = (String)request.getParameter("sheetId");
		ISoftChangeMainManager mgr = (ISoftChangeMainManager) getBean("iSoftChangeMainManager");
		SoftChangeMain obj = (SoftChangeMain) mgr.getMainBySheetId(sheetId);
		String ret = "更新成功";
		TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
		
		TawLocalRepositoryUp repositoryUp = tawLocalRepositoryMgr.getTawLocalRepositorybysheetkey(obj.getId());
		if(null==repositoryUp.getId()){
			if(obj!=null&&!"".equals(obj.getMainCellInfo())){
				String[] cellinfo = obj.getMainCellInfo().split(",");
				String  cdkey = obj.getMainSoftCDKey();
				String pathchkey = obj.getMainSoftPatchKey();
				ret= saveupdate(cellinfo,cdkey,pathchkey,obj.getId());
			}
			if(obj!=null&&!"".equals(obj.getMaincellinfo1())){
				String[] cellinfo1 = obj.getMaincellinfo1().split(",");
				String  cdkey1 = obj.getMainsoftcdkey1();
				String pathchkey1 = obj.getMainsoftcdkey1();
				ret=saveupdate(cellinfo1,cdkey1,pathchkey1,obj.getId());
			}
			if(obj!=null&&!"".equals(obj.getMaincellinfo2())){
				String[] cellinfo2 = obj.getMaincellinfo2().split(",");
				String  cdkey2 = obj.getMainsoftcdkey2();
				String pathchkey2 = obj.getMainsoftcdkey2();
				ret=saveupdate(cellinfo2,cdkey2,pathchkey2,obj.getId());
			}
		}else{
			ret = "网元已入库";
		}
		JSONUtil.success(response, ret);
	}
	
	private String  saveupdate(String[]cellinfo,String cdkey,String pathchkey,String mainid){
		TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
		
//		TawLocalRepositoryUp repositoryUp = tawLocalRepositoryMgr.getTawLocalRepositorybysheetkey(mainid);
//		if(repositoryUp==null||repositoryUp.getId()==null||"".equals(repositoryUp.getId())){
			for (int i = 0; i < cellinfo.length; i++) {
				System.out.println(cellinfo[i]);
				TawLocalRepository tawLocalRepository = tawLocalRepositoryMgr.getTawLocalRepositorybyname(cellinfo[i]);
					
					if(tawLocalRepository!=null&&tawLocalRepository.getId()!=null){
						TawLocalRepositoryUp tawLocalRepositoryUp = new TawLocalRepositoryUp();
						tawLocalRepositoryUp.setBeforeHardwareRepository(tawLocalRepository.getHardwareRepository());
						tawLocalRepositoryUp.setBeforePatch(tawLocalRepository.getPatch());
						tawLocalRepositoryUp.setBeforeSoftwareRepository(tawLocalRepository.getSoftwareRepository());
						tawLocalRepositoryUp.setRepositoryId(tawLocalRepository.getId());
						tawLocalRepositoryUp.setHardwareRepository(tawLocalRepository.getHardwareRepository());
						tawLocalRepositoryUp.setSoftwareRepository(cdkey);
						tawLocalRepositoryUp.setPatch(pathchkey);
						tawLocalRepositoryUp.setUptime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						tawLocalRepositoryUp.setSheetkey(mainid);
						tawLocalRepository.setSoftwareRepository(cdkey);
						tawLocalRepository.setPatch(pathchkey);
						//修改版本库信息
						tawLocalRepositoryMgr.saveTawLocalRepository(tawLocalRepository);
						//保存或修改升级信息
						TawLocalRepositoryUpMgr tawLocalRepositoryUpMgr = (TawLocalRepositoryUpMgr) getBean("tawLocalRepositoryUpMgr");
						tawLocalRepositoryUpMgr.saveTawLocalRepositoryUp(tawLocalRepositoryUp);
					}
			}
//		}else{
//			return "网元已入库";
//		}
		return "更新成功";

		
	}
	
	/**
	 * 分页显示tawLocalRepositoryUp列表，支持Atom方式接入Portal
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
//			TawLocalRepositoryUpMgr tawLocalRepositoryUpMgr = (TawLocalRepositoryUpMgr) getBean("tawLocalRepositoryUpMgr");
//			Map map = (Map) tawLocalRepositoryUpMgr.getTawLocalRepositoryUps(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			TawLocalRepositoryUp tawLocalRepositoryUp = new TawLocalRepositoryUp();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				tawLocalRepositoryUp = (TawLocalRepositoryUp) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/tawLocalRepositoryUp/tawLocalRepositoryUps.do?method=edit&id="
//						+ tawLocalRepositoryUp.getId() + "' target='_blank'>"
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