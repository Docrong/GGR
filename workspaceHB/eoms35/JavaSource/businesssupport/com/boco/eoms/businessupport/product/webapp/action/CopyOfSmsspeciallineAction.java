package com.boco.eoms.businessupport.product.webapp.action;


import java.io.OutputStream;
import java.io.PrintStream;
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

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.service.SmsspeciallineMgr;
import com.boco.eoms.businessupport.product.util.SmsspeciallineConstants;
import com.boco.eoms.businessupport.product.webapp.form.SmsspeciallineForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:短彩信
 * </p>
 * <p>
 * Description:彩信报工单
 * </p>
 * <p>
 * Wed Jun 02 19:47:25 CST 2010
 * </p>
 *
 * @moudle.getAuthor() 李志刚
 * @moudle.getVersion() 3.5
 */
public final class CopyOfSmsspeciallineAction extends BaseAction {

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
     * 新增短彩信
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
     * 修改短彩信
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("@xedit");
        SmsspeciallineMgr smsspeciallineMgr = (SmsspeciallineMgr) getBean("smsspeciallineMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        String type = StaticMethod.nullObject2String(request.getParameter("type"));
        String orderSheet_Id = StaticMethod.nullObject2String(request.getParameter("orderId"));

        if (type.equals("add")) {
            return mapping.findForward("edit");
        }

        Smsspecialline smsspecialline = smsspeciallineMgr.getSmsspecialline(id);
        SmsspeciallineForm smsspeciallineForm = (SmsspeciallineForm) convert(smsspecialline);
        updateFormBean(mapping, request, smsspeciallineForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存短彩信
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
        SmsspeciallineMgr smsspeciallineMgr = (SmsspeciallineMgr) getBean("smsspeciallineMgr");
        SmsspeciallineForm smsspeciallineForm = (SmsspeciallineForm) form;
        boolean isNew = (null == smsspeciallineForm.getId() || "".equals(smsspeciallineForm.getId()));
        Smsspecialline smsspecialline = (Smsspecialline) convert(smsspeciallineForm);
        if (isNew) {
            smsspeciallineMgr.saveSmsspecialline(smsspecialline);
        } else {
            smsspeciallineMgr.saveSmsspecialline(smsspecialline);
        }
        return search(mapping, smsspeciallineForm, request, response);
    }

    /**
     * 删除短彩信
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
        SmsspeciallineMgr smsspeciallineMgr = (SmsspeciallineMgr) getBean("smsspeciallineMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        smsspeciallineMgr.removeSmsspecialline(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示短彩信列表
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
                SmsspeciallineConstants.SMSSPECIALLINE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        SmsspeciallineMgr smsspeciallineMgr = (SmsspeciallineMgr) getBean("smsspeciallineMgr");
        Map map = (Map) smsspeciallineMgr.getSmsspeciallines(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(SmsspeciallineConstants.SMSSPECIALLINE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示短彩信列表，支持Atom方式接入Portal
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
            SmsspeciallineMgr smsspeciallineMgr = (SmsspeciallineMgr) getBean("smsspeciallineMgr");
            Map map = (Map) smsspeciallineMgr.getSmsspeciallines(pageIndex, pageSize, "");
            List list = (List) map.get("result");
            Smsspecialline smsspecialline = new Smsspecialline();

            //创建ATOM源
            Factory factory = Abdera.getNewFactory();
            Feed feed = factory.newFeed();

            // 分页
//			for (int i = 0; i < list.size(); i++) {
//				smsspecialline = (Smsspecialline) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/smsspecialline/smsspeciallines.do?method=edit&id="
//						+ smsspecialline.getId() + "' target='_blank'>"
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
}