package com.boco.eoms.km.statics.webapp.action;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.statics.mgr.PersonalStatisticMgr;
import com.boco.eoms.km.statics.mgr.ScoreOrderStatisticMgr;


	public class ScoreOrderStatisticAction extends BaseAction {
	 
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
		 * 分页显示操作积分定义表列表
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
			
			return mapping.findForward("list");
		}
		/**
		 * 知识库知识使用次数排行
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward knowledgeUsedOrder(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			int orderNumber = StaticMethod.null2int(request.getParameter("orderNumber"));
			String type = StaticMethod.nullObject2String(request.getParameter("type"));
			ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
			List list = scoreOrderStatisticMgr.getKnowledgeUsedOrder(orderNumber);
			request.setAttribute("knowledgeUsedOrderList", list);
			if("main".equals(type)){
				return mapping.findForward("knowledgeUsedOrderForMain");
			}
			return mapping.findForward("knowledgeUsedOrderList");
		}
		/**
		 * 知识使用排名
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward knowledgeMonthUsedOrder(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			int orderNumber = StaticMethod.null2int(request.getParameter("orderNumber"));
			String type = StaticMethod.nullObject2String(request.getParameter("type"));
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(GregorianCalendar.DAY_OF_MONTH,1); 
			SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = simpleFormate.format(cal.getTime());
			cal.add(GregorianCalendar.MONTH,1);   
			cal.set(GregorianCalendar.DAY_OF_MONTH,1); 
			String endDate = simpleFormate.format(cal.getTime());
			ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
			List list = scoreOrderStatisticMgr.getKnowledgeUsedOrder(orderNumber,startDate,endDate);
			request.setAttribute("knowledgeMonthUsedOrderList", list);
			if("main".equals(type)){
				return mapping.findForward("knowledgeMonthUsedOrderForMain");
			}
			return mapping.findForward("knowledgeMonthUsedOrderList");
		}

		/**
		 * 本月员工贡献排名（ok）
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward monthUserScoreOrder(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			int orderNumber = StaticMethod.null2int(request.getParameter("orderNumber"));
			String type = StaticMethod.nullObject2String(request.getParameter("type"));
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(GregorianCalendar.DAY_OF_MONTH,1); 
			SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = simpleFormate.format(cal.getTime());
			cal.add(GregorianCalendar.MONTH,1);   
			cal.set(GregorianCalendar.DAY_OF_MONTH,1); 
			String endDate = simpleFormate.format(cal.getTime());
			ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
			List list = scoreOrderStatisticMgr.getMonthUserScoreOrder(orderNumber,startDate,endDate);
			request.setAttribute("monthUserScoreOrderList", list);
			if("main".equals(type)){
				return mapping.findForward("monthUserScoreOrderForMain");
			}
			return mapping.findForward("monthUserScoreOrderList");
		}

		/**
		 * 员工知识贡献总排名（ok）
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward userScoreOrder(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			int orderNumber = StaticMethod.null2int(request.getParameter("orderNumber"));
			String type = StaticMethod.nullObject2String(request.getParameter("type"));

			ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
			List list = scoreOrderStatisticMgr.getUserScoreOrder(orderNumber);
			request.setAttribute("monthUserScoreOrderList", list);

			if("main".equals(type)){
				return mapping.findForward("monthUserScoreOrderForMain");
			}
			return mapping.findForward("monthUserScoreOrderList");
		}

		/**
		 * 本月知识增长排名（ok）
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward monthKnowledgeCountOrder(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			int orderNumber = StaticMethod.null2int(request.getParameter("orderNumber"));
			String type = StaticMethod.nullObject2String(request.getParameter("type"));
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(GregorianCalendar.DAY_OF_MONTH,1); 
			SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = simpleFormate.format(cal.getTime());
			cal.add(GregorianCalendar.MONTH,1);   
			cal.set(GregorianCalendar.DAY_OF_MONTH,1); 
			String endDate = simpleFormate.format(cal.getTime());
			ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
			List list = scoreOrderStatisticMgr.getMonthKnowledgeCountOrder(orderNumber,startDate,endDate);
//			request.setAttribute("monthKnowledgeCountOrderList", list);
//			return mapping.findForward("monthKnowledgeCountOrderList");
			request.setAttribute("monthKnowledgeCountOrder", list);
			if("main".equals(type)){
				return mapping.findForward("monthKnowledgeCountOrderForMain");
			}
			return mapping.findForward("monthKnowledgeCountOrder");
		}
		
		public ActionForward knowledgeCountOrder(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			int orderNumber = StaticMethod.null2int(request.getParameter("orderNumber"));
			String type = StaticMethod.nullObject2String(request.getParameter("type"));

			ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
			List list = scoreOrderStatisticMgr.getKnowledgeCountOrder(orderNumber);

			request.setAttribute("monthKnowledgeCountOrder", list);
			if("main".equals(type)){
				return mapping.findForward("monthKnowledgeCountOrderForMain");
			}
			return mapping.findForward("monthKnowledgeCountOrder");
		}

		/**
		 * 用户知识积分排名
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward userKnowledScoreOrder(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			int orderNumber = StaticMethod.null2int(request.getParameter("orderNumber"));
			String type = StaticMethod.nullObject2String(request.getParameter("type"));
			ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
			List list = scoreOrderStatisticMgr.getUserKnowledScoreOrder(orderNumber);
			request.setAttribute("userKnowledScoreOrder", list);
			if("main".equals(type)){
				return mapping.findForward("userKnowledScoreOrderForMain");
			}			
			return mapping.findForward("userKnowledScoreOrder");
		}
}
