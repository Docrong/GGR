/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.webapp.action;

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
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.complaint.model.ComplaintAutoT2;
import com.boco.eoms.sheet.complaint.service.IComplaintAutoT2Manager;

/**
 * @author TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintAutoT2Action extends BaseAction {

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
    public ActionForward showComplaintAutoT2Page(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("showComplaintAutoT2Page");
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

        ComplaintAutoT2 autoT2 = new ComplaintAutoT2();
        SheetBeanUtils.populateMap2Bean(autoT2, pagemap);
        if (autoT2.getId() == null || autoT2.getId().equals("")) {
            autoT2.setId(UUIDHexGenerator.getInstance().getID());
        }
        autoT2.setCreateDate(new Date());
        IComplaintAutoT2Manager complaintAutoT2Manager = (IComplaintAutoT2Manager) ApplicationContextHolder.getInstance().getBean("iComplaintAutoT2Manager");
        complaintAutoT2Manager.saveObject(autoT2);

        return mapping.findForward("success");
    }

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
        ComplaintAutoT2 autoT2 = new ComplaintAutoT2();
        SheetBeanUtils.populateMap2Bean(autoT2, pagemap);
        StringBuffer where = new StringBuffer();
        where.append("where 1=1 ");
        //拼出条件SQL语句
        if (autoT2.getComplaintType1() != null && !autoT2.getComplaintType1().equals("")) {
            where.append(" and complaintType1 = '").append(autoT2.getComplaintType1()).append("'");
        } else {
            where.append(" and complaintType1 is null ");
        }
        if (autoT2.getComplaintType2() != null && !autoT2.getComplaintType2().equals("")) {
            where.append(" and complaintType2 = '").append(autoT2.getComplaintType2()).append("'");
        } else {
            where.append(" and complaintType2 is null ");
        }
        if (autoT2.getComplaintType3() != null && !autoT2.getComplaintType3().equals("")) {
            where.append(" and complaintType3 = '").append(autoT2.getComplaintType3()).append("'");
        } else {
            where.append(" and complaintType3 is null ");
        }
        if (autoT2.getComplaintType4() != null && !autoT2.getComplaintType4().equals("")) {
            where.append(" and complaintType4 = '").append(autoT2.getComplaintType4()).append("'");
        } else {
            where.append(" and complaintType4 is null ");
        }
        if (autoT2.getComplaintType5() != null && !autoT2.getComplaintType5().equals("")) {
            where.append(" and complaintType5 = '").append(autoT2.getComplaintType5()).append("'");
        } else {
            where.append(" and complaintType5 is null ");
        }
        if (autoT2.getComplaintType6() != null && !autoT2.getComplaintType6().equals("")) {
            where.append(" and complaintType6 = '").append(autoT2.getComplaintType6()).append("'");
        } else {
            where.append(" and complaintType6 is null ");
        }
        if (autoT2.getComplaintType7() != null && !autoT2.getComplaintType7().equals("")) {
            where.append(" and complaintType7 = '").append(autoT2.getComplaintType7()).append("'");
        } else {
            where.append(" and complaintType7 is null ");
        }
        if (autoT2.getFaultSite() != null && !autoT2.getFaultSite().equals("")) {
            where.append(" and faultSite = '").append(autoT2.getFaultSite()).append("'");
        }
        if (autoT2.getId() != null && !autoT2.getId().equals("")) {
            where.append(" and id <> '").append(autoT2.getId()).append("'");
        }
        if (autoT2.getRemark2() != null && !autoT2.getRemark2().equals("")) {
            where.append(" and remark2 = '").append(autoT2.getRemark2()).append("'");
        }
        condition.put("where", where.toString());
        Integer pageSize = new Integer(-1);
        int[] aTotal = {0};
        IComplaintAutoT2Manager complaintAutoT2Manager = (IComplaintAutoT2Manager) ApplicationContextHolder.getInstance().getBean("iComplaintAutoT2Manager");
        complaintAutoT2Manager.getObjectsByCondtion(new Integer(0), pageSize, aTotal, condition, "number");

        JSONObject jsonRoot = new JSONObject();
        int allnumber = aTotal[0];
        jsonRoot.put("number", allnumber);
        JSONUtil.print(response, jsonRoot.toString());
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
        condition.put("where", orderCondition);

        String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        IComplaintAutoT2Manager complaintAutoT2Manager = (IComplaintAutoT2Manager) ApplicationContextHolder.getInstance().getBean("iComplaintAutoT2Manager");

        List list = complaintAutoT2Manager.getObjectsByCondtion(pageIndex, pageSize, aTotal, condition, "recorde");
        request.setAttribute("taskList", list);
        request.setAttribute("total", new Integer(aTotal[0]));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("list");
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
        IComplaintAutoT2Manager complaintAutoT2Manager = (IComplaintAutoT2Manager) ApplicationContextHolder.getInstance().getBean("iComplaintAutoT2Manager");
        ComplaintAutoT2 complaintAutoT2 = (ComplaintAutoT2) complaintAutoT2Manager.getObject(ComplaintAutoT2.class, id);
        request.setAttribute("complaintAutoT2", complaintAutoT2);
        request.setAttribute("type", type);
        return mapping.findForward("showComplaintAutoT2Page");
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
        IComplaintAutoT2Manager complaintAutoT2Manager = (IComplaintAutoT2Manager) ApplicationContextHolder.getInstance().getBean("iComplaintAutoT2Manager");
        complaintAutoT2Manager.removeObject(ComplaintAutoT2.class, id);
        return mapping.findForward("success");
    }

    /**
     * 查找根据条件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward find(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("success");
    }


}
