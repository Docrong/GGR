package com.boco.eoms.wap.platform.duty.webapp.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;

/**
 * @author User
 * 
 */
public class DutyUserAction extends BaseAction {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	/**
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dutyUser(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		int roomId = Integer.parseInt(request.getParameter("id"));
		int day = StaticMethod.null2int(request.getParameter("day"));
		String dutyDay = StaticMethod.null2String(request.getParameter("dutyDate"));
		String time ="";
		TawRmAssignworkBO tawRmAssignworkBO = null;
		Vector QueryResult = null;
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		try {
			
			if("".equals(dutyDay) || dutyDay == null ){
			// 当前时间的前一天和后一天
			time = StaticMethod.getLocalString(day).split(" ")[0];
			}else{
			// 取页面选择的时间的前一天和后一天
			time = StaticMethod.getDateString(dutyDay,day);
			}
			tawRmAssignworkBO = new TawRmAssignworkBO(ds);

			QueryResult = tawRmAssignworkBO.getQueryResultVector(roomId, time);

			tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
					roomId), 0);
			
			request.setAttribute("ROOMNAME",tawApparatusroom.getRoomname());
			request.setAttribute("ROOMID",roomId+"");
			request.setAttribute("QUERYRESULT", QueryResult);
			request.setAttribute("DUTYDATE", time);
			request.setAttribute("DUTY", day+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("dutyUser");
	}
}
