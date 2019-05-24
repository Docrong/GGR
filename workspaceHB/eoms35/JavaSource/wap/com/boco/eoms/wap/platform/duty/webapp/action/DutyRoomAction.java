package com.boco.eoms.wap.platform.duty.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * @author gongyufeng
 * 
 */
public class DutyRoomAction extends BaseAction {

	/**
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return 选择机房
	 */
	public ActionForward roomselect(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = saveSessionBeanForm.getUserid();
		Vector SelectRoom = new Vector();
		List list = new ArrayList();
		TawSystemCptroom tawApparatusroom = null;
		TawSystemPrivRegion tawSystemPrivRegion = null;
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		try {
			// 取本身属于的机房
			if (saveSessionBeanForm.getRoomId() != null
					&& !"".equals(saveSessionBeanForm.getRoomId())) {
				String[] roomArray = saveSessionBeanForm.getRoomId().split(",");
				for (int i = 0; i < roomArray.length; i++) {
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(roomArray[i]), 0);
					list.add(tawApparatusroom);
				}
			}
			// 取所有机房域的对象
			SelectRoom = StaticMethod
					.list2vector(privBO
							.getPermissions(
									userId,
									com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
									com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

			for (int i = 0; i < SelectRoom.size(); i++) {
				tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
						.elementAt(i);
				tawApparatusroom = cptroomBO.getTawSystemCptroomById(
						new Integer(tawSystemPrivRegion.getRegionid()), 0);
				list.add(tawApparatusroom);
			}
			for (int i = 0; i < list.size(); i++) {
				TawSystemCptroom tawApparatusrooms = (TawSystemCptroom) list
						.get(i);
				System.out.println(tawApparatusrooms.getId()
						+ "-------------------"
						+ tawApparatusrooms.getRoomname());
			}

			request.setAttribute("ROOMLIST", list);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return mapping.findForward("roomselect");

	}

}
