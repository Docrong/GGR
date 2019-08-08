package com.boco.eoms.km.ask.webapp.action;

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
import com.boco.eoms.km.ask.mgr.KmAskScoreMgr;
import com.boco.eoms.km.ask.model.KmAskScore;
import com.boco.eoms.km.ask.util.KmAskScoreConstants;
import com.boco.eoms.km.ask.webapp.form.KmAskScoreForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:问答积分配置
 * </p>
 * <p>
 * Description:问答积分配置
 * </p>
 * <p>
 * Wed Aug 19 16:41:06 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public final class KmAskScoreAction extends BaseAction {

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
     * 新增问答积分配置
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
     * 修改问答积分配置
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
        KmAskScoreMgr kmAskScoreMgr = (KmAskScoreMgr) getBean("kmAskScoreMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmAskScore kmAskScore = kmAskScoreMgr.getKmAskScore(id);
        KmAskScoreForm kmAskScoreForm = (KmAskScoreForm) convert(kmAskScore);
        updateFormBean(mapping, request, kmAskScoreForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存问答积分配置
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
        KmAskScoreMgr kmAskScoreMgr = (KmAskScoreMgr) getBean("kmAskScoreMgr");
        KmAskScoreForm kmAskScoreForm = (KmAskScoreForm) form;
        boolean isNew = (null == kmAskScoreForm.getId() || "".equals(kmAskScoreForm.getId()));
        KmAskScore kmAskScore = (KmAskScore) convert(kmAskScoreForm);
        if (isNew) {
            kmAskScoreMgr.saveKmAskScore(kmAskScore);
        } else {
            kmAskScoreMgr.saveKmAskScore(kmAskScore);
        }
        return search(mapping, kmAskScoreForm, request, response);
    }

    /**
     * 删除问答积分配置
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
        KmAskScoreMgr kmAskScoreMgr = (KmAskScoreMgr) getBean("kmAskScoreMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        kmAskScoreMgr.removeKmAskScore(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示问答积分配置列表
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
                KmAskScoreConstants.KMASKSCORE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAskScoreMgr kmAskScoreMgr = (KmAskScoreMgr) getBean("kmAskScoreMgr");
        Map map = (Map) kmAskScoreMgr.getKmAskScores(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(KmAskScoreConstants.KMASKSCORE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示问答积分配置列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
    //	 */
//	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			KmAskScoreMgr kmAskScoreMgr = (KmAskScoreMgr) getBean("kmAskScoreMgr");
//			Map map = (Map) kmAskScoreMgr.getKmAskScores(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmAskScore kmAskScore = new KmAskScore();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmAskScore = (KmAskScore) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmAskScore/kmAskScores.do?method=edit&id="
//						+ kmAskScore.getId() + "' target='_blank'>"
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