package com.boco.eoms.km.exam.webapp.action;

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
import com.boco.eoms.km.exam.mgr.KmExamChoiceMgr;
import com.boco.eoms.km.exam.model.KmExamChoice;
import com.boco.eoms.km.exam.util.KmExamChoiceConstants;
import com.boco.eoms.km.exam.webapp.form.KmExamChoiceForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:选项表
 * </p>
 * <p>
 * Description:选项表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 */
public final class KmExamChoiceAction extends BaseAction {

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
     * 新增选项表
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
     * 修改选项表
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
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmExamChoice kmExamChoice = kmExamChoiceMgr.getKmExamChoice(id);
        KmExamChoiceForm kmExamChoiceForm = (KmExamChoiceForm) convert(kmExamChoice);
        updateFormBean(mapping, request, kmExamChoiceForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存选项表
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
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        KmExamChoiceForm kmExamChoiceForm = (KmExamChoiceForm) form;
        boolean isNew = (null == kmExamChoiceForm.getId() || "".equals(kmExamChoiceForm.getId()));
        KmExamChoice kmExamChoice = (KmExamChoice) convert(kmExamChoiceForm);
        if (isNew) {
            kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
        } else {
            kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
        }
        return search(mapping, kmExamChoiceForm, request, response);
    }

    /**
     * 删除选项表
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
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        kmExamChoiceMgr.removeKmExamChoice(id);
        return null;
    }

    /**
     * 分页显示选项表列表
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
                KmExamChoiceConstants.KMEXAMCHOICE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        Map map = (Map) kmExamChoiceMgr.getKmExamChoices(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(KmExamChoiceConstants.KMEXAMCHOICE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示选项表列表，支持Atom方式接入Portal
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
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
//			Map map = (Map) kmExamChoiceMgr.getKmExamChoices(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmExamChoice kmExamChoice = new KmExamChoice();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmExamChoice = (KmExamChoice) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmExamChoice/kmExamChoices.do?method=edit&id="
//						+ kmExamChoice.getId() + "' target='_blank'>"
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
        return null;
    }
}