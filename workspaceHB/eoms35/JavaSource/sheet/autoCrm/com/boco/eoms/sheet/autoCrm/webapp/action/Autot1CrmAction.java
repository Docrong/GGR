package com.boco.eoms.sheet.autoCrm.webapp.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import atg.taglib.json.util.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.autoCrm.model.Autot1Crm;
import com.boco.eoms.sheet.autoCrm.service.IAutoT1CrmManager;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.complaint.model.ComplaintAutoT2;
import com.boco.eoms.sheet.complaint.service.IComplaintAutoT2Manager;

public class Autot1CrmAction extends SheetAction {


    /**
     * 栓查是否有重复的记录
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void checkRepeateRecord(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map condition = new HashMap();
        Map pagemap = request.getParameterMap();
        Autot1Crm autoT1 = new Autot1Crm();
        SheetBeanUtils.populateMap2Bean(autoT1, pagemap);
        StringBuffer where = new StringBuffer();
        where.append("where 1=1 ");
        //拼出条件SQL语句
        if (autoT1.getFaultsite() != null && !autoT1.getFaultsite().equals("")) {
            where.append(" and faultSite = '").append(autoT1.getFaultsite()).append("'");
        }
        if (autoT1.getId() != null && !autoT1.getId().equals("")) {
            where.append(" and id <> '").append(autoT1.getId()).append("'");
        }
        if (autoT1.getRemark2() != null && !autoT1.getRemark2().equals("")) {
            where.append(" and remark2 = '").append(autoT1.getRemark2()).append("'");
        }
        if (autoT1.getDealrolet2() != null && !autoT1.getDealrolet2().equals("")) {
            where.append(" and dealrolet2 = '").append(autoT1.getDealrolet2()).append("'");
        }
        if (autoT1.getTemplatename() != null && !autoT1.getTemplatename().equals("")) {
            where.append(" and templatename = '").append(autoT1.getTemplatename()).append("'");
        }

        condition.put("where", where.toString());
        Integer pageSize = new Integer(-1);
        int[] aTotal = {0};
        IAutoT1CrmManager autoT1CrmManager = (IAutoT1CrmManager) ApplicationContextHolder.getInstance().getBean("iAutoT1CrmManagerImpl");
        autoT1CrmManager.getObjectsByCondtion(new Integer(0), pageSize, aTotal, condition, "number");

        JSONObject jsonRoot = new JSONObject();
        int allnumber = aTotal[0];
        jsonRoot.put("number", allnumber);
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 删除
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
        String id = request.getParameter("id");
        IAutoT1CrmManager autoT1CrmManager = (IAutoT1CrmManager) ApplicationContextHolder.getInstance().getBean("iAutoT1CrmManagerImpl");
        autoT1CrmManager.removeObject(Autot1Crm.class, id);
        return mapping.findForward("success");
    }

    /**
     * 编辑配置文件
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
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        IAutoT1CrmManager autoT1CrmManager = (IAutoT1CrmManager) ApplicationContextHolder.getInstance().getBean("iAutoT1CrmManagerImpl");
        Autot1Crm autoT = (Autot1Crm) autoT1CrmManager.getObject(Autot1Crm.class, id);
        String flowId = (String) request.getParameter("flowId");
        request.setAttribute("flowId", flowId);
        request.setAttribute("autot1Crm", autoT);
        request.setAttribute("type", type);
        return mapping.findForward("showAutoT1Page");
    }

    /**
     * 显示配置文件页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showAutoT1Page(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String flowId = (String) request.getParameter("flowId");
        request.setAttribute("flowId", flowId);
        return mapping.findForward("showAutoT1Page");
    }

    /**
     * 得到所有的配置文件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        //页数的参数名
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // 当前页数
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        // 分页取得列表
        int[] aTotal = {0};
//		String order = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
//								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER)));
//		String sort = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
//								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_SORT)));
        String flowId = (String) request.getParameter("flowId");
        String where = "where 1=1 ";
        //拼出条件SQL语句
        if (flowId != null && !flowId.equals("")) {
            where += " and templatename = '" + flowId + "'";
        }


        String orderCondition = " order by ";
//		if (!order.equals("")) {
//			if (order.equals("1")) {
//				order = " asc";
//			} else {
//				order = " desc";
//			}
//		}
//		if (!sort.equals("")) {
//			orderCondition = orderCondition + sort + order;
//		} else {
        orderCondition = orderCondition + " createDate desc";
//		}

        Map condition = new HashMap();
        condition.put("where", where + " " + orderCondition);

        String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        IAutoT1CrmManager autoT1CrmManager = (IAutoT1CrmManager) ApplicationContextHolder.getInstance().getBean("iAutoT1CrmManagerImpl");

        List list = autoT1CrmManager.getObjectsByCondtion(pageIndex, pageSize, aTotal, condition, "recorde");

        request.setAttribute("flowId", flowId);
        request.setAttribute("taskList", list);
        request.setAttribute("total", new Integer(aTotal[0]));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("list");
    }


    /**
     * 处理配置
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

        Map pagemap = request.getParameterMap();

        Autot1Crm autoT1 = new Autot1Crm();
        SheetBeanUtils.populateMap2Bean(autoT1, pagemap);
        if (autoT1.getId() == null || autoT1.getId().equals("")) {
            autoT1.setId(null);
        }
        autoT1.setCreatedate(new Date());
        IAutoT1CrmManager autoT1CrmManager = (IAutoT1CrmManager) ApplicationContextHolder.getInstance().getBean("iAutoT1CrmManagerImpl");
        autoT1CrmManager.saveObject(autoT1);

        return mapping.findForward("success");
    }
}
