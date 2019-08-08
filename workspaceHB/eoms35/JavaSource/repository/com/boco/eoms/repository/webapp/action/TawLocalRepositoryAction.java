package com.boco.eoms.repository.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Hibernate;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.repository.mgr.TawLocalRepositoryMgr;
import com.boco.eoms.repository.model.TawLocalRepository;
import com.boco.eoms.repository.util.TawLocalRepositoryConstants;
import com.boco.eoms.repository.util.TawLocalRepositoryUpConstants;
import com.boco.eoms.repository.util.TawLocalRepositoryUtil;
import com.boco.eoms.repository.webapp.form.TawLocalRepositoryForm;
import com.boco.eoms.repository.webapp.form.TawLocalRepositoryUpForm;

/**
 * <p>
 * Title:tawLocalRepository
 * </p>
 * <p>
 * Description:tawLocalRepository
 * </p>
 * <p>
 * Fri Oct 30 09:14:26 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 李锋
 * @moudle.getVersion() 1.0
 */
public final class TawLocalRepositoryAction extends BaseAction {

    /**
     * 未指定方法时默认调用的方法
     *
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
     * 新增tawLocalRepository
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
        TawLocalRepository tawLocalRepository = new TawLocalRepository();
        tawLocalRepository.setState("1");
        TawLocalRepositoryForm tawLocalRepositoryForm = (TawLocalRepositoryForm) convert(tawLocalRepository);
        updateFormBean(mapping, request, tawLocalRepositoryForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改tawLocalRepository
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
        TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        TawLocalRepository tawLocalRepository = tawLocalRepositoryMgr.getTawLocalRepository(id);
        TawLocalRepositoryForm tawLocalRepositoryForm = (TawLocalRepositoryForm) convert(tawLocalRepository);
        updateFormBean(mapping, request, tawLocalRepositoryForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存tawLocalRepository
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
        TawLocalRepositoryForm tawLocalRepositoryForm = (TawLocalRepositoryForm) form;
        boolean isNew = (null == tawLocalRepositoryForm.getId() || "".equals(tawLocalRepositoryForm.getId()));
        TawLocalRepository tawLocalRepository = (TawLocalRepository) convert(tawLocalRepositoryForm);
        if (isNew) {
            tawLocalRepositoryMgr.saveTawLocalRepository(tawLocalRepository);
        } else {
            tawLocalRepositoryMgr.saveTawLocalRepository(tawLocalRepository);
        }
        return mapping.findForward("search");
    }

    /**
     * 删除tawLocalRepository
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
        TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        tawLocalRepositoryMgr.removeTawLocalRepository(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示tawLocalRepository列表
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
                TawLocalRepositoryConstants.TAWLOCALREPOSITORY_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
        Map map = (Map) tawLocalRepositoryMgr.getTawLocalRepositorys(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(TawLocalRepositoryConstants.TAWLOCALREPOSITORY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }


    /**
     * 分页显示tawLocalRepository列表
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

    public ActionForward query(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                TawLocalRepositoryConstants.TAWLOCALREPOSITORY_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        TawLocalRepositoryForm tawLocalRepositoryForm = (TawLocalRepositoryForm) form;
        TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
        TawLocalRepository tawLocalRepository = (TawLocalRepository) convert(tawLocalRepositoryForm);
        String queryStr = "";

        if (!(tawLocalRepository.getCity() == null || tawLocalRepository.getCity().equals(""))) {
//			String cityNameFromCode = TawLocalRepositoryUtil.decodeCityFromXML(tawLocalRepository.getCity()) ;

            queryStr += " and  tawLocalRepository.city = '" + tawLocalRepository.getCity() + "'";
        }

        String state = tawLocalRepository.getState();
        if (!(state == null || state.trim().equals(""))) {
            queryStr += " and  tawLocalRepository.state = '" + state + "'";
        }
        if (!(tawLocalRepository.getNet() == null || tawLocalRepository.getNet().equals("")))
            queryStr += " and  tawLocalRepository.net like '%" + tawLocalRepository.getNet() + "%'";

        if (!(tawLocalRepository.getNetType() == null || tawLocalRepository.getNetType().equals("")))
            queryStr += " and  tawLocalRepository.netType = '" + tawLocalRepository.getNetType() + "'";

        if (!(tawLocalRepository.getDriverTpye() == null || tawLocalRepository.getDriverTpye().equals("")))
            queryStr += " and  tawLocalRepository.driverTpye = '" + tawLocalRepository.getDriverTpye() + "'";

        if (!(tawLocalRepository.getCompany() == null || tawLocalRepository.getCompany().equals("")))
            queryStr += " and  tawLocalRepository.company = '" + tawLocalRepository.getCompany() + "'";

        if (!(tawLocalRepository.getNetModale() == null || tawLocalRepository.getNetModale().equals("")))
            queryStr += " and  tawLocalRepository.netModale = '" + tawLocalRepository.getNetModale() + "'";

        if (!(tawLocalRepository.getHardwareRepository() == null || tawLocalRepository.getHardwareRepository().equals("")))
            queryStr += " and  tawLocalRepository.hardwareRepository = '" + tawLocalRepository.getHardwareRepository() + "'";

        if (!(tawLocalRepository.getSoftwareRepository() == null || tawLocalRepository.getSoftwareRepository().equals("")))
            queryStr += " and  tawLocalRepository.softwareRepository = '" + tawLocalRepository.getSoftwareRepository() + "'";

        if (!(tawLocalRepository.getNetworkTimeFrom() == null || tawLocalRepository.getNetworkTimeFrom().equals("")))
            queryStr += " and  tawLocalRepository.networkTime   >= '" + tawLocalRepository.getNetworkTimeFrom() + "'";

        if (!(tawLocalRepository.getNetworkTimeTo() == null || tawLocalRepository.getNetworkTimeTo().equals("")))
            queryStr += " and  tawLocalRepository.networkTime   <='" + tawLocalRepository.getNetworkTimeTo() + "'";

//		System.out.println(" --queryStr :------" +queryStr);
        Map map = (Map) tawLocalRepositoryMgr.getTawLocalRepositorys(pageIndex, pageSize, queryStr);
        List list = (List) map.get("result");
        request.setAttribute(TawLocalRepositoryConstants.TAWLOCALREPOSITORY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    public ActionForward queryHistoryDo(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("queryHistory");
    }

    public ActionForward queryHistory(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(TawLocalRepositoryUpConstants.TAWLOCALREPOSITORYUP).encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        TawLocalRepositoryForm tawLocalRepositoryForm = (TawLocalRepositoryForm) form;
        TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
        TawLocalRepository tawLocalRepository = (TawLocalRepository) convert(tawLocalRepositoryForm);
        String queryStr = "";
        if (!(tawLocalRepository.getCity() == null || tawLocalRepository.getCity().equals(""))) {
//			String cityNameFromCode = TawLocalRepositoryUtil.decodeCityFromXML(tawLocalRepository.getCity()) ;

            queryStr += " and  tawLocalRepository.city = '" + tawLocalRepository.getCity() + "'";
        }

        String state = tawLocalRepository.getState();
        if (!(state == null || state.trim().equals(""))) {
            queryStr += " and  tawLocalRepository.state = '" + state + "'";
        }
        if (!(tawLocalRepository.getNet() == null || tawLocalRepository.getNet().equals("")))
            queryStr += " and  tawLocalRepository.net like '%" + tawLocalRepository.getNet() + "%'";

        if (!(tawLocalRepository.getNetType() == null || tawLocalRepository.getNetType().equals("")))
            queryStr += " and  tawLocalRepository.netType = '" + tawLocalRepository.getNetType() + "'";

        if (!(tawLocalRepository.getDriverTpye() == null || tawLocalRepository.getDriverTpye().equals("")))
            queryStr += " and  tawLocalRepository.driverTpye = '" + tawLocalRepository.getDriverTpye() + "'";

        if (!(tawLocalRepository.getCompany() == null || tawLocalRepository.getCompany().equals("")))
            queryStr += " and  tawLocalRepository.company = '" + tawLocalRepository.getCompany() + "'";

        if (!(tawLocalRepository.getNetModale() == null || tawLocalRepository.getNetModale().equals("")))
            queryStr += " and  tawLocalRepository.netModale = '" + tawLocalRepository.getNetModale() + "'";

        if (!(tawLocalRepository.getHardwareRepository() == null || tawLocalRepository.getHardwareRepository().equals("")))
            queryStr += " and  tawLocalRepository.hardwareRepository = '" + tawLocalRepository.getHardwareRepository() + "'";

        if (!(tawLocalRepository.getSoftwareRepository() == null || tawLocalRepository.getSoftwareRepository().equals("")))
            queryStr += " and  tawLocalRepository.softwareRepository = '" + tawLocalRepository.getSoftwareRepository() + "'";

        if (!(tawLocalRepository.getNetworkTimeFrom() == null || tawLocalRepository.getNetworkTimeFrom().equals("")))
            queryStr += " and  u.uptime   >= '" + tawLocalRepository.getNetworkTimeFrom() + "'";

        if (!(tawLocalRepository.getNetworkTimeTo() == null || tawLocalRepository.getNetworkTimeTo().equals("")))
            queryStr += " and  u.uptime   <='" + tawLocalRepository.getNetworkTimeTo() + "'";

//		System.out.println(" --queryStr :------" +queryStr);

        Map map = (Map) tawLocalRepositoryMgr.getTawLocalRepositorysHistory(pageIndex, pageSize, queryStr);
        List list = (List) map.get("result");
        List lists = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            Map maps = new HashMap();
            maps.put("patchs", objs[0]);
            maps.put("sheetkeys", objs[1]);
            maps.put("repositoryId", objs[2]);
            maps.put("beforeHardwareRepository", objs[3]);
            maps.put("beforeSoftwareRepository", objs[4]);
            maps.put("softwareRepository", objs[5]);
            maps.put("hardwareRepository", objs[6]);
            maps.put("upNum", objs[7]);
            lists.add(maps);

        }
        request.setAttribute(TawLocalRepositoryUpConstants.TAWLOCALREPOSITORYUP_LIST, lists);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("queryResult");
    }


    /**
     * 修改tawLocalRepository
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward upgrade(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
        String repositoryId = StaticMethod.null2String(request.getParameter("id"));
        TawLocalRepository tawLocalRepository = tawLocalRepositoryMgr.getTawLocalRepository(repositoryId);

        TawLocalRepositoryUpForm tawLocalRepositoryUpForm = new TawLocalRepositoryUpForm();
        tawLocalRepositoryUpForm.setBeforeHardwareRepository(tawLocalRepository.getHardwareRepository());
        tawLocalRepositoryUpForm.setBeforeSoftwareRepository(tawLocalRepository.getSoftwareRepository());
        tawLocalRepositoryUpForm.setBeforePatch(tawLocalRepository.getPatch());
        tawLocalRepositoryUpForm.setRepositoryId(repositoryId);

        updateFormBean(mapping, request, tawLocalRepositoryUpForm);
        return mapping.findForward("upgrade");
    }

    /**
     * 软件变更工单派单页面选择网元功能
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward softqueryDo(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("softquery");
    }

    /**
     * 软件变更工单派单页面选择网元功能
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward softquery(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                TawLocalRepositoryConstants.TAWLOCALREPOSITORY_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        TawLocalRepositoryForm tawLocalRepositoryForm = (TawLocalRepositoryForm) form;
        TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
        TawLocalRepository tawLocalRepository = (TawLocalRepository) convert(tawLocalRepositoryForm);
        String queryStr = "";

        if (!(tawLocalRepository.getCity() == null || tawLocalRepository.getCity().equals(""))) {
//			String cityNameFromCode = TawLocalRepositoryUtil.decodeCityFromXML(tawLocalRepository.getCity()) ;

            queryStr += " and  tawLocalRepository.city = '" + tawLocalRepository.getCity() + "'";
        }

        String state = tawLocalRepository.getState();
        if (!(state == null || state.trim().equals(""))) {
            queryStr += " and  tawLocalRepository.state = '" + state + "'";
        }
        if (!(tawLocalRepository.getNet() == null || tawLocalRepository.getNet().equals("")))
            queryStr += " and  tawLocalRepository.net like '%" + tawLocalRepository.getNet() + "%'";

        if (!(tawLocalRepository.getNetType() == null || tawLocalRepository.getNetType().equals("")))
            queryStr += " and  tawLocalRepository.netType = '" + tawLocalRepository.getNetType() + "'";

        if (!(tawLocalRepository.getDriverTpye() == null || tawLocalRepository.getDriverTpye().equals("")))
            queryStr += " and  tawLocalRepository.driverTpye = '" + tawLocalRepository.getDriverTpye() + "'";

        if (!(tawLocalRepository.getCompany() == null || tawLocalRepository.getCompany().equals("")))
            queryStr += " and  tawLocalRepository.company = '" + tawLocalRepository.getCompany() + "'";

        if (!(tawLocalRepository.getNetModale() == null || tawLocalRepository.getNetModale().equals("")))
            queryStr += " and  tawLocalRepository.netModale = '" + tawLocalRepository.getNetModale() + "'";

        if (!(tawLocalRepository.getHardwareRepository() == null || tawLocalRepository.getHardwareRepository().equals("")))
            queryStr += " and  tawLocalRepository.hardwareRepository = '" + tawLocalRepository.getHardwareRepository() + "'";

        if (!(tawLocalRepository.getSoftwareRepository() == null || tawLocalRepository.getSoftwareRepository().equals("")))
            queryStr += " and  tawLocalRepository.softwareRepository = '" + tawLocalRepository.getSoftwareRepository() + "'";

        if (!(tawLocalRepository.getNetworkTimeFrom() == null || tawLocalRepository.getNetworkTimeFrom().equals("")))
            queryStr += " and  tawLocalRepository.networkTime   >= '" + tawLocalRepository.getNetworkTimeFrom() + "'";

        if (!(tawLocalRepository.getNetworkTimeTo() == null || tawLocalRepository.getNetworkTimeTo().equals("")))
            queryStr += " and  tawLocalRepository.networkTime   <='" + tawLocalRepository.getNetworkTimeTo() + "'";

//		System.out.println(" --queryStr :------" +queryStr);
        Map map = (Map) tawLocalRepositoryMgr.getTawLocalRepositorys(pageIndex, pageSize, queryStr);
        List list = (List) map.get("result");
        request.setAttribute(TawLocalRepositoryConstants.TAWLOCALREPOSITORY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("softlist");
    }

    public ActionForward softmain(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("softmain");
    }
    /**
     * 分页显示tawLocalRepository列表，支持Atom方式接入Portal
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
//			TawLocalRepositoryMgr tawLocalRepositoryMgr = (TawLocalRepositoryMgr) getBean("tawLocalRepositoryMgr");
//			Map map = (Map) tawLocalRepositoryMgr.getTawLocalRepositorys(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			TawLocalRepository tawLocalRepository = new TawLocalRepository();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				tawLocalRepository = (TawLocalRepository) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/tawLocalRepository/tawLocalRepositorys.do?method=edit&id="
//						+ tawLocalRepository.getId() + "' target='_blank'>"
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