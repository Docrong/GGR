package com.boco.eoms.commons.system.priv.webapp.action;


/**
 * <p>
 * Title:为部门赋权
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jul 4, 2008 9:00:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class Assign4DeptAction {

	/**
	 * 初始化赋予部门权限
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * public ActionForward execute(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 *  // List menuList = new ArrayList(); // ITawSystemPrivMenuManager menuMgr =
	 * (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager"); //
	 * TawSystemSessionForm sessionform = (TawSystemSessionForm) request //
	 * .getSession().getAttribute("sessionform"); // // String currentuserid =
	 * sessionform.getUserid(); // TawSystemPrivMenu menu = new
	 * TawSystemPrivMenu(); // if (sessionform.isAdmin()) { // menuList =
	 * menuMgr.getTawSystemPrivMenus(menu); // } else { // TawSystemAssignBo
	 * assignbo = TawSystemAssignBo.getInstance(); // List list = new
	 * ArrayList(); // list = assignbo.getObjectPriv(currentuserid); // if (list !=
	 * null && list.size() > 0) { // for (int i = 0; i < list.size(); i++) { //
	 * TawSystemPrivAssign privassign = new TawSystemPrivAssign(); // privassign =
	 * (TawSystemPrivAssign) list.get(i); // menu =
	 * menuMgr.getTawSystemPrivMenu(privassign.getPrivid()); //
	 * menuList.add(menu); // } // } // // } //
	 * request.setAttribute(Constants.TAWSYSTEMPRIVMENU_LIST, menuList); //
	 * return mapping.findForward("assign4dept");
	 *  }
	 */
}
