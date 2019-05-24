package com.boco.eoms.testcard.controller;

import java.util.*;
import java.util.Date;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.*;
import com.boco.eoms.testcard.controller.*;
import com.boco.eoms.testcard.dao.*;
import com.boco.eoms.testcard.controller.TawTestcardForm;
import com.boco.eoms.testcard.model.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.testcard.controller.TawTestcardTestingForm;
import java.sql.*;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.testcard.util.StaticValue;
import com.boco.eoms.testcard.util.TawTestMsg;
import com.boco.eoms.testcard.bo.TawTestcardBO;

public class TawTestcardManagerAction extends BaseAction {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	/**
	 * @see ?????????????
	 */
	private static int PAGE_LENGTH = 50;

	static ResourceBundle prop = null;

	private String user_id = "";

	private String leaveString = "";
	static {
		prop = ResourceBundle.getBundle("resources.application_zh_CN");
		try {
			// PAGE_LENGTH =
			// Integer.parseInt(prop.getString("list.page.length"));
		} catch (Exception e) {
		}
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		// session???????
		try {
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null)
				return mapping.findForward("timeout");
			user_id = StaticMethod.null2String(saveSessionBeanForm.getUserid());
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		// ??????
		try {
			/*
			 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds); if
			 * (!tawValidateBO.validPriv(user_id, mapping.getPath())) return
			 * mapping.findForward("nopriv");
			 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("REMOVE".equalsIgnoreCase(myaction)) {
			myforward = performRemove(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("ADD".equalsIgnoreCase(myaction)) {
			myforward = performAdd(mapping, form, request, response);
		} else if ("CLEARADD".equalsIgnoreCase(myaction)) {
			myforward = performClearadd(mapping, form, request, response);
		} else if ("RECEEDIT".equalsIgnoreCase(myaction)) {
			myforward = performReceEdit(mapping, form, request, response);
		} else if ("SEARCH".equalsIgnoreCase(myaction)) {
			myforward = performSearch(mapping, form, request, response);
		} else if ("SEARCHUSED".equalsIgnoreCase(myaction)) {
			myforward = performSearchused(mapping, form, request, response);
		} else if ("SEARCHDO".equalsIgnoreCase(myaction)) {
			myforward = performSearchDo(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else if ("USESAVE".equalsIgnoreCase(myaction)) {
			myforward = performUsesave(mapping, form, request, response);
		} else if ("SELECT".equalsIgnoreCase(myaction)) {
			myforward = performSelect(mapping, form, request, response);
		} else if ("CLEARDO".equalsIgnoreCase(myaction)) { // add by xudongsuo
			// 20071214
			// ??????????????????
			myforward = performCleardo(mapping, form, request, response);
		} else if ("CLEARQUERY".equalsIgnoreCase(myaction)) { // add by
			// xudongsuo
			// 20071214
			// ??????????????????
			myforward = performClearquery(mapping, form, request, response);
		} else if ("RETURNSELECT".equalsIgnoreCase(myaction)) {
			myforward = performReturnselect(mapping, form, request, response);
		}  else if ("SELECTLIST".equalsIgnoreCase(myaction)) {
			myforward = performSelectlist(mapping, form, request, response);
		}  else if ("UNACTIVATELIST".equalsIgnoreCase(myaction)) {
			myforward = performUnactivateList(mapping, form, request, response);
		} else if ("CLEARLIST".equalsIgnoreCase(myaction)) { // add by
			// xudongsuo
			// 20071214
			// ??????????????????
			myforward = performClearlist(mapping, form, request, response);
		} else if ("CLEARQUERYLIST".equalsIgnoreCase(myaction)) { // add by
			// xudongsuo
			// 20071214
			// ??????????????????
			myforward = performClearquerylist(mapping, form, request, response);
		} else if ("RETURN".equalsIgnoreCase(myaction)) {
			myforward = performReturn(mapping, form, request, response);
		} else if ("ACCEDE".equalsIgnoreCase(myaction)) {
			myforward = performAccede(mapping, form, request, response);
		} else if ("RETURNUPDATE".equalsIgnoreCase(myaction)) {
			myforward = performReturnupdate(mapping, form, request, response);
		} else if ("RENEW".equalsIgnoreCase(myaction)) {
			myforward = performReturn(mapping, form, request, response);
		} else if ("RETURNBORROWNOTE".equalsIgnoreCase(myaction)) {
			myforward = performReturnBorrowNote(mapping, form, request,
					response);
		} else if ("CLEARHISTORY".equalsIgnoreCase(myaction)) {
			myforward = performClearhistory(mapping, form, request, response);// add
			// by
			// xudongsuo
			// ?????????
		} else if ("TOUSE".equalsIgnoreCase(myaction)) {
			myforward = performTouse(mapping, form, request, response);
		} else if ("RENEWUPDATE".equalsIgnoreCase(myaction)) {
			myforward = performRenewupdate(mapping, form, request, response);
		} else if ("RETURNSELECTLIST".equalsIgnoreCase(myaction)) {
			myforward = performReturnselectlist(mapping, form, request,
					response);

		} else if ("INCEPT".equalsIgnoreCase(myaction)) {
			myforward = performIncept(mapping, form, request, response);

		} else if ("RETURNBORROWLIST".equals(myaction)) {
			myforward = performBorrowlist(mapping, form, request, response);
		} else if ("PRESENTIMENT".equalsIgnoreCase(myaction)) {
			myforward = performPresentiment(mapping, form, request, response);
		} else if ("TRASH".equalsIgnoreCase(myaction)) {
			myforward = performTrash(mapping, form, request, response); // ???????
			if (myforward.equals(mapping.findForward("success"))) {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (myforward.equals(mapping.findForward("failure"))) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} else if (myforward.equals(mapping.findForward("failure"))) {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if ("REMOVE".equalsIgnoreCase(myaction)) {
			myforward = performRemove(mapping, form, request, response);
		} else if ("TRASH".equalsIgnoreCase(myaction)) {
			myforward = performTrash(mapping, form, request, response);
			// ???????
			if (myforward.equals(mapping.findForward("success"))) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (myforward.equals(mapping.findForward("failure"))) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} else if ("CREATENUMBER".equalsIgnoreCase(myaction)) {
			myforward = performCreateNumber(mapping, form, request, response);// add
																				// by
																				// zhaodongliang
		} else {
			myforward = mapping.findForward("failure");
		}

		return myforward;
	}

	/**
	 * @see ??????锟斤拷?
	 */

	private ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		String pageOffset = null;
		List tawTestcard = null;
		String objKey = null;
		String url = null;
		String pagerHeader = null;

		// ?锟斤拷???
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String user_id = saveSessionBeanForm.getUserid();
		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.list(offset, length, size, "");

			// String[] objKeys = {"tawTestcardmanager", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = (Integer)SizeCacheManager.getCache(objKey);
			// if(size == null) {
			// size =new Integer( tawTestcardDAO.getLengh(""));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			url = request.getContextPath() + "/" + mapping.getPath() + ".do";
			pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcard", tawTestcard);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {

		} catch (Exception e) {
			// generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	/**
	 * @see ??????????
	 */
	private ActionForward performSearch(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String WrfUserName = saveSessionBeanForm.getUsername();
		int deptid = Integer.parseInt(saveSessionBeanForm.getDeptid());
		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */

			// -------------------deptid--????????????锟斤拷????,???form------------------------
			// ?????????
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			// DeptDAO deptdao= new DeptDAO(ds);

			int tempdeptid = tawEventDicDAO.isprovince(deptid);
			while (tempdeptid == -1) {
				// deptid=deptdao.findParent_id(deptid);
				tempdeptid = tawEventDicDAO.isprovince(deptid);
			}
			deptid = tempdeptid;
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave", deptid);
			// Vector vm1=(Vector)tawEventDicDAO.list("test_leave");
			if (vm1 == null || vm1.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				int size = vm1.size();
				Vector entries = new Vector(size);
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				// entries.add(new LabelValueBean("",""));
				form.setBeanCollectionDN(entries);
			}
			// ????????
			Vector enti = new Vector(2);

			enti.add(new LabelValueBean(StaticValue.TawCard_BAOFEI, "5"));

			enti.add(new LabelValueBean(StaticValue.TawCard_SHIBAI, "6"));
			enti.add(new LabelValueBean(StaticValue.TawCard_JIECHU, "3"));
			enti.add(new LabelValueBean(StaticValue.TawCard_SHIYONG, "4"));
			form.setBeanCollection(enti);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return mapping.findForward("success");
	}

	private ActionForward performSearchused(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardTestingForm form = (TawTestcardTestingForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String deptid = saveSessionBeanForm.getDeptid();
		try {
			// -------------------deptid--????????????锟斤拷????,???form------------------------
			// ?????????
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 锟斤拷锟斤拷

			int tempdeptid = tawEventDicDAO.isprovince(StaticMethod
					.null2int(deptid));
			while (tempdeptid == -1) {
				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
						Integer.parseInt(deptid)));
				// deptid=deptdao.findParent_id(deptid);
				deptid = tawSystemDept.getParentDeptid();// 取锟斤拷锟斤拷诺母锟斤拷锟斤拷锟?
				tempdeptid = tawEventDicDAO.isprovince(StaticMethod
						.null2int(deptid));
			}
			deptid = Integer.toString(tempdeptid);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			int size = vm1.size();
			Vector entries = new Vector(size);
			for (int i = 0; i < size; i++) {
				TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
				String sId = (Integer.toString(vm2.getId()));
				entries.add(new LabelValueBean((String) vm2.getName(), sId));
			}
			form.setBeanCollectionDN(entries);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????--???
	 */
	private ActionForward performSearchDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		int offset;
		int length = PAGE_LENGTH;
		String pageOffset = request.getParameter("pager.offset");

		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		StringBuffer sql = new StringBuffer();
		String condition = "";
		try {
			// -----------------???SQL???--------------------
			if (request.getParameter("submit") != null) {
				if (form.getStrutsAction() == (TawTestcardForm.LOANED)) {
					sql.append(" state=3 ");
					if (!(form.getLeantime().equals(null))
							&& !(form.getLeantime().equals(""))) {
						sql.append(" and leantime>='" + form.getLeantime()
								+ "' ");
					}
					if (!(form.getBelongtime().equals(null))
							&& !(form.getBelongtime().equals(""))) {
						sql.append(" and belongtime<='" + form.getBelongtime()
								+ "' ");
					}
					if (!(form.getLenddept().equals(null))
							&& !(form.getLenddept().equals(""))) {
						sql.append(" and lenddept like '%" + form.getLenddept()
								+ "%'");
					}
				} else if (form.getStrutsAction() == (TawTestcardForm.USED)) {
					sql.append("state=4 ");
				} else if (form.getStrutsAction() == (TawTestcardForm.SCRAP)) {
					sql.append("state=5 ");
				}
				if (!(form.getDealer().equals(null))
						&& !(form.getDealer().equals(""))) {
					sql.append(" and dealer like '%" + form.getDealer().trim()
							+ "%' ");
				}
				if (!(form.getLeave().equals(null))
						&& !(form.getLeave().equals(""))) {
					sql.append(" and leave='" + form.getLeave() + "' ");
				}
				if (!(form.getCardid().equals(null))
						&& !(form.getCardid().equals(""))) {
					sql.append(" and cardid like'" + form.getCardid().trim()
							+ "%' ");
				}
				if (!(form.getMsisdn().equals(null))
						&& !(form.getMsisdn().trim().equals(""))) {
					sql.append(" and msisdn like'" + form.getMsisdn().trim()
							+ "%' ");
				}
				if (!(form.getStrutsAction() == (TawTestcardForm.SCRAP))
						&& !(form.getLender().equals(null))
						&& !(form.getLender().equals(""))) {
					sql.append(" and lender like '%" + form.getLender().trim()
							+ "%'");
				}
				condition = sql.toString();
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			List tawTestcardManager = null;
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}

			tawTestcardManager = tawTestcardManagerDAO.list(offset, length,
					condition);
			String[] objKeys = { "tawTestcardManager", "list" };
			String objKey = CacheManager.createKey(objKeys);
			Integer size = (Integer) SizeCacheManager.getCache(objKey);
			if (size == null || (request.getParameter("submit") != null)) {
				size = new Integer(tawTestcardManagerDAO.getLengh(condition));
				SizeCacheManager.putCache(size, objKey, 0);
			}
			String url = request.getContextPath() + "/eventmanager"
					+ mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(offset, size.intValue(),
					length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcardManager", tawTestcardManager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????????????
	 */
	private ActionForward performView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			TawTestcardManager tawTestcardManager = new TawTestcardManager();
			tawTestcardManager = tawTestcardManagerDAO.retrieve(form.getId());
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardManager));
			form.setCardid(tawTestcardManager.getCardid());
			form.setOldid(tawTestcardManager.getOldid());
			form.setDealer(tawTestcardManager.getDealer());
			form.setLenddept(tawTestcardManager.getLenddept());
			form.setLender(tawTestcardManager.getLender());
			form.setContect(tawTestcardManager.getContect());
			form.setLeantime(tawTestcardManager.getLeantime());
			form.setBelongtime(tawTestcardManager.getBelongtime());
			form.setReturntime(tawTestcardManager.getReturntime());
			form.setRenewlimit(tawTestcardManager.getRenewlimit());
			form.setReason(tawTestcardManager.getReason());

			// System.out.println(form.getId());
		} catch (Exception e) {
			// generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????????????
	 */
	/**
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// FormFile file= form.getTheFile();
		// String filePath=request.getRealPath("/testcard/serverfile");
		// //??????锟斤拷??
		String iccidlist = request.getParameter("iccidlist");
		String[] idlist = iccidlist.split(",");
		if (idlist != null) {
			for (int i = 0; i < idlist.length; i++) {
				TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
				// form.setLeave(request.getSession().getAttribute("leave").toString());
				form.setLeave(leaveString);
				TawTestcardManager tawTestcardManager = new TawTestcardManager();
				TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
						ds);
				TawTestcardBO tawtestcardbo = new TawTestcardBO();

				// if ( tawTestcardManagerDAO.check(file,filePath)){
				// if (UpLoad.UpLoadFile(file, filePath) == "UPLOADOK") {
				// String path = "/testcard/serverfile/" + file.getFileName();
				// tawTestcardManager.setAccessory(path);
				try {
					org.apache.commons.beanutils.BeanUtils.populate(
							tawTestcardManager,
							org.apache.commons.beanutils.BeanUtils
									.describe(form));

					Date dt = new Date();
					TawTestcardDAO tawTestcardDAO =new TawTestcardDAO(ds);
					SimpleDateFormat smpDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String sTime = smpDateFormat.format(dt);
					tawTestcardManager.setCardid(idlist[i]);
					String msisdn = tawtestcardbo.getMsisdn(idlist[i]);
					tawTestcardManager.setMsisdn(msisdn);
					TawTestcard tawTestcard=tawTestcardDAO.retrieveMsisdn(msisdn);
					tawTestcardManager.setFromcity(tawTestcard.getFromCity());
					tawTestcardManager.setFromcrit(tawTestcard.getFromCrit());
					String username = tawTestcardManagerDAO.getUserName(form
							.getSort1_userid());

					tawTestcardManager.setLenderid(form.getSort1_userid());
					

					tawTestcardManager.setLender(username);

					if (tawTestcardManager.getLeantime().equals("")
							|| tawTestcardManager.getLeantime().equals(null)) {
						tawTestcardManager.setLeantime(sTime);
					}
					// tawTestcardManager.setAccessory(path);
					
					TawTestMsg.sendSMS(form.getSort1_userid(),form.getBelongtime(),msisdn);
					if (form.getStrutsAction() == (TawTestcardForm.EDIT)) {
						tawTestcardManagerDAO.update(tawTestcardManager);
						form = null;
					} else if (form.getStrutsAction() == (TawTestcardForm.USED)) {
						tawTestcardManagerDAO.touse(tawTestcardManager);
						form = null;
					} else if (form.getStrutsAction() == (TawTestcardForm.SCRAP)) {
						tawTestcardManagerDAO.toscrap(tawTestcardManager);
						form = null;
					} else {
						tawTestcardManagerDAO.insert(tawTestcardManager);
						
						form = null;
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (NoSuchMethodException ex) {
					ex.printStackTrace();
				} catch (InvocationTargetException ex) {
					ex.printStackTrace();
				} catch (IllegalAccessException ex) {
					ex.printStackTrace();
				}
			}
		}
		// else{
		// return mapping.findForward("checkfileno");
		//
		return mapping.findForward("success");
	}

	/**
	 * @see ???????????????
	 */
	private ActionForward performEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			TawTestcardManager tawTestcardManager = new TawTestcardManager();
			tawTestcardManager = tawTestcardManagerDAO.retrieve(form.getId());
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardManager));
			form.setStrutsAction(TawTestcardForm.EDIT);
		} catch (Exception e) {
			System.out.println("???????????");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ???????????????
	 */
	private ActionForward performSelect(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// //??????
		// try{
		// TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
		// if (tawValidateBO.validatePriv(user_id,3003) == false)
		// return mapping.findForward("nopriv");
		// }
		// catch(Exception ee){
		// ee.printStackTrace();
		// }
		String deptid = saveSessionBeanForm.getDeptid();
		try {
			// ---------------------????????????锟斤拷????,???form------------------------
			// ????
			TawTestcardBO bo = new TawTestcardBO();
			String StringTree = bo.getMyTreeStr();
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
			// ?????????
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 锟斤拷锟斤拷
			int tempdeptid = tawEventDicDAO.isprovince(StaticMethod
					.null2int(deptid));
//			while (tempdeptid == -1) {
//				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
//						Integer.parseInt(deptid)));
//				// deptid=deptdao.findParent_id(deptid);
//				deptid = tawSystemDept.getParentDeptid();// 取锟斤拷锟斤拷诺母锟斤拷锟斤拷锟?
//				tempdeptid = tawEventDicDAO.isprovince(StaticMethod
//						.null2int(deptid));
//			}
			deptid = Integer.toString(tempdeptid);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			// Vector vm1=(Vector)tawEventDicDAO.list("test_leave");
			if (vm1 == null || vm1.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				// entries.add(new LabelValueBean("",""));
				form.setBeanCollectionDN(entries);
			}
			// ???????????
			Vector vmoper = (Vector) tawEventDicDAO.list("testcard_oper");
			if (vmoper == null || vmoper.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmoper.size());
				int size = vmoper.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmoper.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollec(entries);
			}
			// ????????????????
			Vector vmpackage = (Vector) tawEventDicDAO.list("packages");
			if (vmpackage == null || vmpackage.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmpackage.size());
				int size = vmpackage.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmpackage.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollep(entries);
			}
			// ?????????????
			List filiales = tawEventDicDAO.findfiliale();
			request.setAttribute("filiales", filiales);

			// 压锟斤拷状态选锟斤拷
			Vector enti = new Vector(1);
			enti.add(new LabelValueBean(StaticValue.TawCard_ZHENGCHANG, "0"));
			enti.add(new LabelValueBean(StaticValue.TawCard_TINGJI, "1"));
			enti.add(new LabelValueBean(StaticValue.TawCard_YISHI, "2"));
			enti.add(new LabelValueBean(StaticValue.TawCard_SHIBAI, "6"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_JIECHU ,"3"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_SHIYONG,"4"));
			form.setBeanCollection(enti);
		} catch (Exception e) {

		}
		return mapping.findForward("success");
	}

	/**
	 * @see ?????????????
	 */
	private ActionForward performReturnselect(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		String deptid = sessionform.getDeptid();
		try {
			// -------------------deptid--????????????锟斤拷????,???form------------------------
			// ?????????
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 锟斤拷锟斤拷
			// DeptDAO deptdao= new DeptDAO(ds);
			int tempdeptid = tawEventDicDAO.isprovince(StaticMethod
					.null2int(deptid));
//			while (tempdeptid == -1) {
//				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
//						Integer.parseInt(deptid)));
//				// deptid=deptdao.findParent_id(deptid);
//				tempdeptid = tawEventDicDAO.isprovince(StaticMethod
//						.null2int(deptid));
//				deptid = tawSystemDept.getParentDeptid();// 取锟斤拷锟斤拷诺母锟斤拷锟斤拷锟?
//				tempdeptid = tawEventDicDAO
//						.isprovince(Integer.parseInt(deptid));
//			}
			deptid = Integer.toString(tempdeptid);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			// Vector vm1=(Vector)tawEventDicDAO.list("test_leave");
			if (vm1 == null || vm1.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				// entries.add(new LabelValueBean("",""));
				form.setBeanCollectionDN(entries);
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ?锟斤拷???????????????????????
	 */
	private ActionForward performSelectlist(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawEventDicDAO eventdao = new TawEventDicDAO(ds);
		String pageOffset = null;
		List tawTestcard = null;
		String objKey = null;
		String url = null;
		String pagerHeader = null;
		StringBuffer sql = new StringBuffer();
		String condition = "";
		try {
			if (request.getParameter("submit") != null) {
				if (form.getCardType() != null
						&& !(form.getCardType().equals("7"))) {
					sql.append(" and cardType='" + form.getCardType() + "'");
				} else if (form.getOperation().equals("1")) {
					sql.append(" and cardType in ('1','2','4','5')");
				}
				// ??
				if (!(form.getState() == (null))
						&& !(form.getState().equals("5"))) {
					sql.append(" and state='" + form.getState() + "'");
				}
				// ?????
				if (form.getCardTypeNum().equals("0")) {
					if (!(form.getLeave() == (null))
							&& !(form.getLeave().equals(""))) {
						sql.append(" and leave='" + form.getLeave() + "' ");
					}
				} else {
					if (form.getFromCity() != null) {
						if (!form.getFromCity().equals("")) {
							sql.append(" and fromcity ='" + form.getFromCity()
									+ "'");
						}
					}
					if ((form.getToCountry() != null)
							&& !(form.getToCountry().equals(""))) {
						sql.append(" and tocountry = '" + form.getToCountry()
								+ "'");
						if ((form.getToCrit() != null && !(form.getToCrit()
								.equals("")))) {
							sql.append(" and tocrit = '" + form.getToCrit()
									+ "'");
							if ((form.getToCity() != null)
									&& !(form.getToCity().equals(""))) {
								sql.append(" and tocity = '" + form.getToCity()
										+ "'");
							}
						}
					}
				}
				// ???
				if (!(form.getCardpackage() == (null))
						&& !(form.getCardpackage().equals(""))) {
					sql.append(" and cardpackage='"
							+ eventdao.findName(form.getCardpackage()) + "'");
				}
				// ????
				if (!(form.getIccid() == (null))
						&& !(form.getIccid().trim().equals(""))) {
					sql.append(" and iccid like'" + form.getIccid().trim()
							+ "%' ");
				}
				// imsi
				if (!(form.getImsi() == (null))
						&& !(form.getImsi().trim().equals(""))) {
					sql.append(" and ( imsi like'" + form.getImsi().trim()
							+ "%' " + "or imsi1 like'" + form.getImsi().trim()
							+ "%') ");
				}
				// msisdn
				if (!(form.getMsisdn() == (null))
						&& !(form.getMsisdn().trim().equals(""))) {
					sql.append(" and ( msisdn like'" + form.getMsisdn().trim()
							+ "%' " + " or msisdn1 like'" + form.getMsisdn()
							+ "%')");
				}
				condition = sql.toString();
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.list(offset, length, size, condition+"and deleted=0");
			// String[] objKeys = {"tawTestcardmase", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = 0;//(Integer)SizeCacheManager.getCache(objKey);
			// if(size == null||(request.getParameter("submit")!=null)) {
			// size =new Integer( tawTestcardDAO.getLengh(condition));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			url = request.getContextPath() + "/testcard"
					+ mapping.getPath() + ".do";
			pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcard", tawTestcard);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");

	}
	private ActionForward performUnactivateList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawEventDicDAO eventdao = new TawEventDicDAO(ds);
		String pageOffset = null;
		List tawTestcard = null;
		String objKey = null;
		String url = null;
		String pagerHeader = null;
		StringBuffer sql = new StringBuffer();
		String condition = "";
		try {
			if (request.getParameter("submit") != null) {
				if (form.getCardType() != null
						&& !(form.getCardType().equals("7"))) {
					sql.append(" and cardType='" + form.getCardType() + "'");
				} else if (form.getOperation().equals("1")) {
					sql.append(" and cardType in ('1','2','4','5')");
				}
				// ??
//				if (!(form.getState() == (null))
//						&& !(form.getState().equals("5"))) {
//					sql.append(" and state='" + form.getState() + "'");
//				}
				// ?????
				if (form.getCardTypeNum().equals("0")) {
					if (!(form.getLeave() == (null))
							&& !(form.getLeave().equals(""))) {
						sql.append(" and leave='" + form.getLeave() + "' ");
					}
				} else {
					if (form.getFromCity() != null) {
						if (!form.getFromCity().equals("")) {
							sql.append(" and fromcity ='" + form.getFromCity()
									+ "'");
						}
					}
					if ((form.getToCountry() != null)
							&& !(form.getToCountry().equals(""))) {
						sql.append(" and tocountry = '" + form.getToCountry()
								+ "'");
						if ((form.getToCrit() != null && !(form.getToCrit()
								.equals("")))) {
							sql.append(" and tocrit = '" + form.getToCrit()
									+ "'");
							if ((form.getToCity() != null)
									&& !(form.getToCity().equals(""))) {
								sql.append(" and tocity = '" + form.getToCity()
										+ "'");
							}
						}
					}
				}
				// ???
				if (!(form.getCardpackage() == (null))
						&& !(form.getCardpackage().equals(""))) {
					sql.append(" and cardpackage='"
							+ eventdao.findName(form.getCardpackage()) + "'");
				}
				// ????
				if (!(form.getIccid() == (null))
						&& !(form.getIccid().trim().equals(""))) {
					sql.append(" and iccid like'" + form.getIccid().trim()
							+ "%' ");
				}
				// imsi
				if (!(form.getImsi() == (null))
						&& !(form.getImsi().trim().equals(""))) {
					sql.append(" and ( imsi like'" + form.getImsi().trim()
							+ "%' " + "or imsi1 like'" + form.getImsi().trim()
							+ "%') ");
				}
				// msisdn
				if (!(form.getMsisdn() == (null))
						&& !(form.getMsisdn().trim().equals(""))) {
					sql.append(" and ( msisdn like'" + form.getMsisdn().trim()
							+ "%' " + " or msisdn1 like'" + form.getMsisdn()
							+ "%')");
				}
				if (!(form.getPhoneNumber() == null) && !(form.getPhoneNumber().equals(""))) {
					sql.append(" and phone_number like '%" + form.getPhoneNumber() + "%'");
				}
				condition = sql.toString() + " and state='"+StaticValue.STATUS_PASS+"' and is_alive='"+StaticValue.STATUS_UNALIVE+"'";
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.list(offset, length, size, condition);
			// String[] objKeys = {"tawTestcardmase", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = 0;//(Integer)SizeCacheManager.getCache(objKey);
			// if(size == null||(request.getParameter("submit")!=null)) {
			// size =new Integer( tawTestcardDAO.getLengh(condition));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			url = request.getContextPath() + "/testcard"
					+ mapping.getPath() + ".do";
			pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcard", tawTestcard);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ?锟斤拷????????????????????????????????
	 */
	private ActionForward performReturnselectlist(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		int offset;
		int length = PAGE_LENGTH;
		String condition = "";
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String WrfUserName = saveSessionBeanForm.getUsername();

		System.out.println(WrfUserName);

		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		String sql = "";
		try {
			if (request.getParameter("submit") != null) {
				// -----------------???SQL???--------------------
				if (!(form.getDealer().equals(null))
						&& !(form.getDealer().equals(""))) {
					sql = sql + " and dealer like '%" + form.getDealer().trim()
							+ "%' ";
				}
				if (!(form.getMsisdn().equals(null))
						&& !(form.getMsisdn().trim().equals(""))) {
					sql = sql + " and msisdn like '" + form.getMsisdn().trim()
							+ "%' ";
				}
				if (!(form.getCardid() == (null))
						&& !(form.getCardid().trim().equals(""))) {
					sql = sql + " and cardid like'" + form.getCardid().trim()
							+ "%' ";
				}
				if (!(form.getLeave().equals(null))
						&& !(form.getLeave().equals(""))) {
					sql = sql + " and leave='" + form.getLeave() + "' ";
				}
				if (!(form.getLenddept().equals(null))
						&& !(form.getLenddept().equals(""))) {
					sql = sql + " and lenddept like '%"
							+ form.getLenddept().trim() + "%'";
				}
				if (!(form.getLender().equals(null))
						&& !(form.getLender().equals(""))) {
					sql = sql + " and lender like '%" + form.getLender().trim()
							+ "%'";
				}
				if (!(form.getLeantime().length() == 0)) {
					sql = sql + " and leantime>='" + form.getLeantime() + "' ";
				}
				if (!(form.getBelongtime().length() == 0)) {
					sql = sql + " and belongtime<='" + form.getBelongtime()
							+ "' ";
				} else {

				}
				condition = sql.toString();
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}
			// System.out.println(sql);
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			List tawTestcardManager = null;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcardManager = tawTestcardManagerDAO.returnlist(offset,
					length, size, condition);
			// String[] objKeys = {"tawTestcardManager", "list"};
			// String objKey = CacheManager.createKey(objKeys);
			// Integer size = (Integer)SizeCacheManager.getCache(objKey);
			// int size =
			// StaticMethod.nullObject2int(request.getParameter("pager.size"),0);
			// //?????????????锟斤拷??0
			// if(size == null) {
			// size =new Integer( tawTestcardManagerDAO.getLengh(sql));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			String objKey = null;

			// String[] objKeys = {"tawTestcardmase", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = 0;//(Integer)SizeCacheManager.getCache(objKey);
			// if(size == null) {
			// size =new Integer( tawTestcardManagerDAO.getLengh(condition));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			String url = request.getContextPath() + "/testcard"
					+ mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcardManager", tawTestcardManager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ?锟斤拷????????????????????????????????
	 */
	private ActionForward performIncept(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		int offset;
		int length = PAGE_LENGTH;
		String condition = "";
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String WrfUserName = saveSessionBeanForm.getUsername();

		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		String sql = "";
		try {

			sql = sql + " and state=8 and lenderid='" + user_id + "' ";

			// System.out.println(sql);

			condition = sql.toString();
			request.getSession().removeAttribute("condition");
			request.getSession().setAttribute("condition", condition);

			// System.out.println(sql);
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			List tawTestcardManager = null;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcardManager = tawTestcardManagerDAO.returnlist(offset,
					length, size, condition);
			// String[] objKeys = {"tawTestcardManager", "list"};
			// String objKey = CacheManager.createKey(objKeys);
			// Integer size = (Integer)SizeCacheManager.getCache(objKey);
			// int size =
			// StaticMethod.nullObject2int(request.getParameter("pager.size"),0);
			// //?????????????锟斤拷??0
			// if(size == null) {
			// size =new Integer( tawTestcardManagerDAO.getLengh(sql));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			String objKey = null;

			// String[] objKeys = {"tawTestcardmase", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = 0;//(Integer)SizeCacheManager.getCache(objKey);
			// if(size == null) {
			// size =new Integer( tawTestcardManagerDAO.getLengh(condition));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			String url = request.getContextPath() + "/testcard"
					+ mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcardManager", tawTestcardManager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	private ActionForward performBorrowlist(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		int offset;
		int length = PAGE_LENGTH;
		String condition = "";
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		String sql = "";
		try {
			if (request.getParameter("submit") != null) {
				// -----------------???SQL???--------------------
				if (!(form.getDealer() == null)
						&& !(form.getDealer().equals(""))) {
					sql = sql + " and dealer like '%" + form.getDealer().trim()
							+ "%' ";
				}
				if (!(form.getMsisdn()== null)
						&& !(form.getMsisdn().trim().equals(""))) {
					sql = sql + " and msisdn like '" + form.getMsisdn().trim()
							+ "%' ";
				}
				if (!(form.getCardid() == null)
						&& !(form.getCardid().trim().equals(""))) {
					sql = sql + " and cardid like'" + form.getCardid().trim()
							+ "%' ";
				}
				if (!(form.getLeave() == null)
						&& !(form.getLeave().equals(""))) {
					sql = sql + " and leave='" + form.getLeave() + "' ";
				}
				if (!(form.getLenddept() == null)
						&& !(form.getLenddept().equals(""))) {
					sql = sql + " and lenddept like '%"
							+ form.getLenddept().trim() + "%'";
				}
				if (!(form.getLender()==null)
						&& !(form.getLender().equals(""))) {
					sql = sql + " and lender like '%" + form.getLender().trim()
							+ "%'";
				}
				if (!(form.getLeantime().length() == 0)) {
					sql = sql + " and leantime>=to_date('" + form.getLeantime() + "','YYYY-MM-DD HH24:MI:SS') ";
				}
				if (!(form.getBelongtime().length() == 0)) {
					sql = sql + " and belongtime<=to_date('" + form.getBelongtime()
							+ "','YYYY-MM-DD HH24:MI:SS') ";
				} else {

				}
				condition = sql.toString();
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}
			// System.out.println(sql);

			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			List tawTestcardManager = null;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcardManager = tawTestcardManagerDAO.returnBorrowList(offset,
					length, size, condition);
			// String[] objKeys = {"tawTestcardManager", "list"};
			// String objKey = CacheManager.createKey(objKeys);
			// Integer size = (Integer)SizeCacheManager.getCache(objKey);
			// int size =
			// StaticMethod.nullObject2int(request.getParameter("pager.size"),0);
			// //?????????????锟斤拷??0
			// if(size == null) {
			// size =new Integer( tawTestcardManagerDAO.getLengh(sql));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			/*
			 * String objKey=null; String[] objKeys = {"tawTestcardManager",
			 * "list"}; objKey = CacheManager.createKey(objKeys); Integer size =
			 * 0;//(Integer)SizeCacheManager.getCache(objKey); if(size == null) {
			 * size =new Integer( tawTestcardManagerDAO.getLengh(condition));
			 * SizeCacheManager.putCache(size, objKey, 0); }
			 */
			String url = request.getContextPath() + "/testcard"
					+ mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcardManager", tawTestcardManager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????
	 */
	private ActionForward performAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// TawTestcardManagerForm form=(TawTestcardManagerForm)actionForm;
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		String[] idlist = request.getParameterValues("iccid");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String WrfUserName = saveSessionBeanForm.getUsername();

		// //??????
		// try{/*
		// TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
		// if (tawValidateBO.validatePriv(user_id,3003) == false)
		// return mapping.findForward("nopriv");*/
		// }
		// catch(Exception ee){
		// ee.printStackTrace();
		// }
		// ???????????锟斤拷???????
		String iccid = "";
		String msisdn = "";
		String leave = "";
		String iccidlist = "";
		iccid = request.getParameter(iccid);
		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		TawTestcard tawTestcard = new TawTestcard();
		if (idlist != null) {
			for (int i = 0; i < idlist.length; i++) {
				iccidlist = iccidlist + "," + idlist[i];
			}
		}
		if (iccidlist.length() >= 1) {
			iccidlist = iccidlist.substring(1, iccidlist.length());
		}
		try {
			tawTestcard = tawTestcardDAO.retrieve(form.getIccid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		msisdn = tawTestcard.getMsisdn();
		leave = tawTestcard.getLeave();
		try {
			leaveString = leave;
			request.setAttribute("cardid", form.getIccid());
			request.setAttribute("iccidlist", iccidlist);
			request.setAttribute("leave", leave);
			request.setAttribute("msisdn", msisdn);
			request.setAttribute("filler", WrfUserName);
			request.setAttribute("statusAction", "1");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	private ActionForward performReceEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		List list = null;
		try {
			String id = StaticMethod
					.null2String(request.getParameter("id"), "");
			// TawInformationDAO tawInformationDAO = new TawInformationDAO(ds);
			if (!id.equals(""))
				// list =(ArrayList)tawInformationDAO.getReceiverList(new
				// Integer(id).intValue());
				request.setAttribute("KBSRECEIVERS", list);
			request.setAttribute("id", id);
			form.setStrutsAction(form.EDIT);

		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ??????????
	 */
	private ActionForward performRemove(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			TawTestcardManager tawTestcardManager = new TawTestcardManager();
			tawTestcardManager = tawTestcardManagerDAO.retrieve(form.getId());
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardManager));
			// System.out.println(form.getId());
		} catch (Exception e) {
			// generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ?锟斤拷????
	 */
	private ActionForward performReturn(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		String WrfUserName = saveSessionBeanForm.getUsername();
		String WrfUserRelenddept = saveSessionBeanForm.getDeptname();
		String[] id = request.getParameterValues("id");
		String idlist = "";
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// ??????
		try {
			/*TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(ds);
			TawTestcardManager tawTestcardManager = new TawTestcardManager();
			tawTestcardManager = tawTestcardManagerDAO.retrieve(form.getId());
			System.out.println(tawTestcardManager.getLender());
			System.out.println(tawTestcardManager.getLenddept());
			request.setAttribute("tawTestcardManager",
					tawTestcardManager);*/
			
				 /* TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");*/
				 
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if (idlist != null) {
			for (int i = 0; i < id.length; i++) {
				idlist = idlist + "," + id[i];
			}
		}
		if (idlist.length() >= 1) {
			idlist = idlist.substring(1, idlist.length());
		}
		try {
			
			  TawTestcardManagerDAO tawTestcardManagerDAO=new TawTestcardManagerDAO(ds); 
			  TawTestcardManager tawTestcardManager=new TawTestcardManager();
			  tawTestcardManager=tawTestcardManagerDAO.retrieve(form.getId());
			  tawTestcardManager.setReturner(WrfUserName);
			  tawTestcardManager.setRenewer(WrfUserName);
			  tawTestcardManager.setRelenddept(WrfUserRelenddept);
			  org.apache.commons.beanutils.BeanUtils.populate(form,
			  org.apache.commons.beanutils.BeanUtils.describe(tawTestcardManager));
			 
			request.setAttribute("idlist", idlist);

			// System.out.println(form.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// generalError(request, e);
			return mapping.findForward("failure");
			
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ????????
	 */
	private ActionForward performAccede(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");

		String[] idlist = request.getParameterValues("id");
		if (idlist != null) {
			for (int i = 0; i < idlist.length; i++) {
				if (saveSessionBeanForm == null) {
					return mapping.findForward("timeout");
				}
				try {
					Date dt = new Date();
					SimpleDateFormat smpDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
							ds);
					TawTestcardManager tawTestcardManager = new TawTestcardManager();
					org.apache.commons.beanutils.BeanUtils.populate(
							tawTestcardManager,
							org.apache.commons.beanutils.BeanUtils
									.describe(form));
					tawTestcardManager.setId(Integer.parseInt(idlist[i]));
					tawTestcardManagerDAO.accedeupdate(tawTestcardManager);
					// System.out.println(form.getId());
				} catch (Exception e) {
					// generalError(request, e);
					return mapping.findForward("failure");
				}
			}
		}
		return mapping.findForward("success");
	}

	private ActionForward performReturnBorrowNote(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		String WrfUserName = saveSessionBeanForm.getUsername();
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			TawTestcardManager tawTestcardManager = new TawTestcardManager();
			tawTestcardManager = tawTestcardManagerDAO.retrieve(form.getId());
			// tawTestcardManager.setReturner(WrfUserName);
			// tawTestcardManager.setRenewer(WrfUserName);
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardManager));
			// System.out.println(form.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// generalError(request, e);
			return mapping.findForward("failure");
		}

		int offset;
		int length = PAGE_LENGTH;
		String pageOffset = request.getParameter("pager.offset");

		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		int parentId = form.getId();
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			List tawTestcardManagerNote = null;
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcardManagerNote = tawTestcardManagerDAO
					.returnBorrowListNote(offset, length, size, parentId);
			/*
			 * String objKey=null; String condition=""; String[] objKeys =
			 * {"tawTestcardmase", "list"}; objKey =
			 * CacheManager.createKey(objKeys); Integer size =
			 * (Integer)SizeCacheManager.getCache(objKey); if(size == null) {
			 * size =new Integer( tawTestcardManagerDAO.getLengh(condition));
			 * SizeCacheManager.putCache(size, objKey, 0); }
			 */
			String url = request.getContextPath() + "/" + mapping.getPath()
					+ ".do";
			String pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcardManagerNote",
					tawTestcardManagerNote);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ?锟斤拷??????
	 */
	private ActionForward performReturnupdate(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		String id = request.getParameter("idlist");
		String[] idlist = id.split(",");
		if (idlist != null) {
			for (int i = 0; i < idlist.length; i++) {
				if (saveSessionBeanForm == null) {
					return mapping.findForward("timeout");
				}
				// ??????
				try {/*
						 * TawValidatePrivBO tawValidateBO = new
						 * TawValidatePrivBO(ds); if
						 * (tawValidateBO.validatePriv(user_id,3003) == false)
						 * return mapping.findForward("nopriv");
						 */
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				try {
					Date dt = new Date();
					SimpleDateFormat smpDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String sTime = smpDateFormat.format(dt);
					TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
							ds);
					TawTestcardManager tawTestcardManager = new TawTestcardManager();
					org.apache.commons.beanutils.BeanUtils.populate(
							tawTestcardManager,
							org.apache.commons.beanutils.BeanUtils
									.describe(form));
					tawTestcardManager.setId(Integer.parseInt(idlist[i]));
					if (tawTestcardManager.getReturntime().equals("")
							|| tawTestcardManager.getReturntime().equals(null)) {
						tawTestcardManager.setReturntime(sTime);
					}
					tawTestcardManagerDAO.returnupdate(tawTestcardManager);
					// System.out.println(form.getId());
				} catch (Exception e) {
					// generalError(request, e);
					return mapping.findForward("failure");
				}
			}
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ?????????
	 */
	private ActionForward performRenewupdate(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		String id = request.getParameter("idlist");
		String[] idlist = id.split(",");
		if (idlist != null) {
			for (int i = 0; i < idlist.length; i++) {
				if (saveSessionBeanForm == null) {
					return mapping.findForward("timeout");
				}
				if (saveSessionBeanForm == null) {
					return mapping.findForward("timeout");
				}
				// ??????
				try {/*
						 * TawValidatePrivBO tawValidateBO = new
						 * TawValidatePrivBO(ds); if
						 * (tawValidateBO.validatePriv(user_id,3003) == false)
						 * return mapping.findForward("nopriv");
						 */
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				try {
					TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
							ds);
					TawTestcardManager tawTestcardManager = new TawTestcardManager();
					org.apache.commons.beanutils.BeanUtils.populate(
							tawTestcardManager,
							org.apache.commons.beanutils.BeanUtils
									.describe(form));
					tawTestcardManager.setId(Integer.parseInt(idlist[i]));
					tawTestcardManagerDAO.retrieve(Integer.parseInt(idlist[i]));
					
					
					tawTestcardManagerDAO.renew(tawTestcardManager);
					// System.out.println(form.getId());
				} catch (Exception e) {
					// generalError(request, e);
					return mapping.findForward("failure");
				}
			}
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????????
	 */
	private ActionForward performTrash(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			tawTestcardManagerDAO.delete(form.getId());
		} catch (Exception e) {
			// generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????????????
	 */

	/**
	 * @see ?????????
	 */

	/**
	 * @see ?锟斤拷??????????
	 */
	private ActionForward performPresentiment(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		int offset;
		int length = PAGE_LENGTH;
		String pageOffset = request.getParameter("pager.offset");

		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		Date dt = new Date();
		SimpleDateFormat smpDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String sTime = smpDateFormat.format(dt);
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			List tawTestcardManager = null;
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcardManager = tawTestcardManagerDAO.presentimentlist(offset,
					length, size, sTime);
			/*
			 * String[] objKeys = {"tawTestcardManager", "list"}; String objKey =
			 * CacheManager.createKey(objKeys); Integer size = (Integer)
			 * SizeCacheManager.getCache(objKey); if (size == null) { size = new
			 * Integer(tawTestcardManagerDAO.getPresentimentLengh(sTime));
			 * SizeCacheManager.putCache(size, objKey, 0); }
			 */
			String url = request.getContextPath() + "/eventmanager"
					+ mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcardManager", tawTestcardManager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????????
	 */
	private ActionForward performTouse(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String WrfUserName = saveSessionBeanForm.getUsername();
		// ???????????锟斤拷???????
		String iccid = form.getIccid();
		String msisdn = "";
		String leave = "";
		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		TawTestcard tawTestcard = new TawTestcard();
		try {
			tawTestcard = tawTestcardDAO.retrieve(iccid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		msisdn = tawTestcard.getMsisdn();
		leave = tawTestcard.getLeave();
		try {
			request.setAttribute("iccid", iccid);
			request.setAttribute("msisdn", msisdn);
			request.setAttribute("conner", WrfUserName);
			request.setAttribute("leave", leave);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ????????????????
	 */
	private ActionForward performUsesave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		TawTestcardTestingForm form = (TawTestcardTestingForm) actionForm;
		TawTestcardTesting tawTestcardTesting = new TawTestcardTesting();
		TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
				ds);
		try {
			org.apache.commons.beanutils.BeanUtils.populate(tawTestcardTesting,
					org.apache.commons.beanutils.BeanUtils.describe(form));
			String iccid=request.getParameter("iccid");
			Date dt = new Date();
			SimpleDateFormat smpDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String sTime = smpDateFormat.format(dt);
			if (tawTestcardTesting.getTesttime().equals("")
					|| tawTestcardTesting.getTesttime() == (null)) {
				tawTestcardTesting.setTesttime(sTime);
			}
			tawTestcardManagerDAO.inserttesting(tawTestcardTesting);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
		form = null;
		return mapping.findForward("success");
	}

	/**
	 * @ add by xudongsuo 20071214 ?????????锟斤拷??
	 * @see ??????????????????
	 */
	private ActionForward performCleardo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// //??????
		// try{
		// TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
		// if (tawValidateBO.validatePriv(user_id,3003) == false)
		// return mapping.findForward("nopriv");
		// }
		// catch(Exception ee){
		// ee.printStackTrace();
		// }
		String deptid = saveSessionBeanForm.getDeptid();
		try {
			// ---------------------????????????锟斤拷????,???form------------------------
			// ????
			TawTestcardBO bo = new TawTestcardBO();
			String StringTree = bo.getMyTreeStr();
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
			// ?????????
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 锟斤拷锟斤拷
			int tempdeptid = tawEventDicDAO.isprovince(StaticMethod
					.null2int(deptid));
			while (tempdeptid == -1) {
				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
						Integer.parseInt(deptid)));
				// deptid=deptdao.findParent_id(deptid);
				deptid = tawSystemDept.getParentDeptid();// 取锟斤拷锟斤拷诺母锟斤拷锟斤拷锟?
				tempdeptid = tawEventDicDAO.isprovince(StaticMethod
						.null2int(deptid));
			}
			deptid = Integer.toString(tempdeptid);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			// Vector vm1=(Vector)tawEventDicDAO.list("test_leave");
			if (vm1 == null || vm1.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				// entries.add(new LabelValueBean("",""));
				form.setBeanCollectionDN(entries);
			}
			// ???????????
			Vector vmoper = (Vector) tawEventDicDAO.list("testcard_oper");
			if (vmoper == null || vmoper.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmoper.size());
				int size = vmoper.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmoper.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollec(entries);
			}
			// ????????????????
			Vector vmpackage = (Vector) tawEventDicDAO.list("packages");
			if (vmpackage == null || vmpackage.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmpackage.size());
				int size = vmpackage.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmpackage.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollep(entries);
			}
			// ?????????????
			List filiales = tawEventDicDAO.findfiliale();
			request.setAttribute("filiales", filiales);

			// ????????
			Vector enti = new Vector(1);
			enti.add(new LabelValueBean(StaticValue.TawCard_ZHENGCHANG, "0"));
			enti.add(new LabelValueBean(StaticValue.TawCard_TINGJI, "1"));
			enti.add(new LabelValueBean(StaticValue.TawCard_YISHI, "2"));
			enti.add(new LabelValueBean(StaticValue.TawCard_SHIBAI, "6"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_JIECHU ,"3"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_SHIYONG,"4"));
			form.setBeanCollection(enti);
		} catch (Exception e) {

		}
		return mapping.findForward("success");
	}

	/**
	 * @xudongsuo ???????锟斤拷?
	 * @see ?锟斤拷???????????????????????
	 */
	private ActionForward performClearlist(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawEventDicDAO eventdao = new TawEventDicDAO(ds);
		String pageOffset = null;
		List tawTestcard = null;
		String objKey = null;
		String url = null;
		String pagerHeader = null;
		StringBuffer sql = new StringBuffer();
		String condition = "";
		try {
			if (request.getParameter("submit") != null) {
				// --------------------????????----------------------
				// ??????
				if (!(form.getCardType().equals("7"))) {
					sql.append(" and cardType='" + form.getCardType() + "'");
				} else if (form.getOperation().equals("1")) {
					sql.append(" and cardType in ('1','2','4','5')");
				}
				// ??
				if (!(form.getState() == (null))
						&& !(form.getState().equals("5"))) {
					sql.append(" and state='" + form.getState() + "'");
				}
				// ?????
				if (form.getCardTypeNum().equals("0")) {
					if (!(form.getLeave() == (null))
							&& !(form.getLeave().equals(""))) {
						sql.append(" and leave='" + form.getLeave() + "' ");
					}
				} else {
					if (form.getFromCity() != null) {
						if (!form.getFromCity().equals("")) {
							sql.append(" and fromcity ='" + form.getFromCity()
									+ "'");
						}
					}
					if ((form.getToCountry() != null)
							&& !(form.getToCountry().equals(""))) {
						sql.append(" and tocountry = '" + form.getToCountry()
								+ "'");
						if ((form.getToCrit() != null && !(form.getToCrit()
								.equals("")))) {
							sql.append(" and tocrit = '" + form.getToCrit()
									+ "'");
							if ((form.getToCity() != null)
									&& !(form.getToCity().equals(""))) {
								sql.append(" and tocity = '" + form.getToCity()
										+ "'");
							}
						}
					}
				}
				// ???
				if (!(form.getCardpackage() == (null))
						&& !(form.getCardpackage().equals(""))) {
					sql.append(" and cardpackage='"
							+ eventdao.findName(form.getCardpackage()) + "'");
				}
				// ????
				if (!(form.getIccid() == (null))
						&& !(form.getIccid().trim().equals(""))) {
					sql.append(" and iccid like'" + form.getIccid().trim()
							+ "%' ");
				}
				// imsi
				if (!(form.getImsi() == (null))
						&& !(form.getImsi().trim().equals(""))) {
					sql.append(" and ( imsi like'" + form.getImsi().trim()
							+ "%' " + "or imsi1 like'" + form.getImsi().trim()
							+ "%') ");
				}
				// msisdn
				if (!(form.getMsisdn() == (null))
						&& !(form.getMsisdn().trim().equals(""))) {
					sql.append(" and ( msisdn like'" + form.getMsisdn().trim()
							+ "%' " + " or msisdn1 like'" + form.getMsisdn()
							+ "%')");
				}
				condition = sql.toString();
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.clearlist(offset, length, size,
					condition);
			// String[] objKeys = {"tawTestcardmase", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = 0;//(Integer)SizeCacheManager.getCache(objKey);
			// if(size == null||(request.getParameter("submit")!=null)) {
			// size =new Integer( tawTestcardDAO.getLengh(condition));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			url = request.getContextPath() + "/eventmanager"
					+ mapping.getPath() + ".do";
			pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcard", tawTestcard);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ????????
	 */
	private ActionForward performClearadd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// TawTestcardManagerForm form=(TawTestcardManagerForm)actionForm;
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		String[] idlist = request.getParameterValues("iccid");
		String newstate = form.getNewstate();
		String clearresan = form.getClearresan();

		/*
		 * if (newstate.equals("0")) { newstate ="??"; } else if
		 * (newstate.equals("1")) { newstate ="???"; } else if
		 * (newstate.equals("2")) { newstate ="???"; } else if
		 * (newstate.equals("6")) { newstate ="SIM????????"; }
		 */

		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String WrfUserName = saveSessionBeanForm.getUsername();

		// //??????
		// try{/*
		// TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
		// if (tawValidateBO.validatePriv(user_id,3003) == false)
		// return mapping.findForward("nopriv");*/
		// }
		// catch(Exception ee){
		// ee.printStackTrace();
		// }
		// ???????????锟斤拷???????
		String iccid = "";
		String msisdn = "";
		String leave = "";
		String iccidlist = "";
		iccid = request.getParameter(iccid);
		TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
				ds);
		TawTestcard tawTestcard = new TawTestcard();
		if (idlist != null) {
			for (int i = 0; i < idlist.length; i++) {
				iccidlist = idlist[i];
				try {
					Date dt = new Date();
					SimpleDateFormat smpDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String sTime = smpDateFormat.format(dt);
					tawTestcardManagerDAO.clearinsert(iccidlist, newstate,
							sTime, WrfUserName, clearresan);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		msisdn = tawTestcard.getMsisdn();
		leave = tawTestcard.getLeave();
		try {
			leaveString = leave;
			request.setAttribute("cardid", form.getIccid());
			request.setAttribute("iccidlist", iccidlist);
			request.setAttribute("leave", leave);
			request.setAttribute("msisdn", msisdn);
			request.setAttribute("filler", WrfUserName);
			request.setAttribute("statusAction", "1");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @ add by xudongsuo 20071214 ?????????锟斤拷??
	 * @see ??????????????????
	 */
	private ActionForward performClearquery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// //??????
		// try{
		// TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
		// if (tawValidateBO.validatePriv(user_id,3003) == false)
		// return mapping.findForward("nopriv");
		// }
		// catch(Exception ee){
		// ee.printStackTrace();
		// }
		String deptid = saveSessionBeanForm.getDeptid();
		try {
			// ---------------------????????????锟斤拷????,???form------------------------
			// ????
			TawTestcardBO bo = new TawTestcardBO();
			String StringTree = bo.getMyTreeStr();
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
			// ?????????
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 锟斤拷锟斤拷
			int tempdeptid = tawEventDicDAO.isprovince(StaticMethod
					.null2int(deptid));
			while (tempdeptid == -1) {
				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
						Integer.parseInt(deptid)));
				// deptid=deptdao.findParent_id(deptid);
				deptid = tawSystemDept.getParentDeptid();// 取锟斤拷锟斤拷诺母锟斤拷锟斤拷锟?
				tempdeptid = tawEventDicDAO.isprovince(StaticMethod
						.null2int(deptid));
			}
			deptid = Integer.toString(tempdeptid);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			// Vector vm1=(Vector)tawEventDicDAO.list("test_leave");
			if (vm1 == null || vm1.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				// entries.add(new LabelValueBean("",""));
				form.setBeanCollectionDN(entries);
			}
			// ???????????
			Vector vmoper = (Vector) tawEventDicDAO.list("testcard_oper");
			if (vmoper == null || vmoper.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmoper.size());
				int size = vmoper.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmoper.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollec(entries);
			}
			// ????????????????
			Vector vmpackage = (Vector) tawEventDicDAO.list("packages");
			if (vmpackage == null || vmpackage.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmpackage.size());
				int size = vmpackage.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmpackage.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollep(entries);
			}
			// ?????????????
			List filiales = tawEventDicDAO.findfiliale();
			request.setAttribute("filiales", filiales);

			// ????????
			Vector enti = new Vector(1);
			enti.add(new LabelValueBean(StaticValue.TawCard_ZHENGCHANG, "0"));
			enti.add(new LabelValueBean(StaticValue.TawCard_TINGJI, "1"));
			enti.add(new LabelValueBean(StaticValue.TawCard_YISHI, "2"));
			enti.add(new LabelValueBean(StaticValue.TawCard_SHIBAI, "6"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_JIECHU ,"3"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_SHIYONG,"4"));
			form.setBeanCollection(enti);
		} catch (Exception e) {

		}
		return mapping.findForward("success");
	}

	/**
	 * @xudongsuo ???????锟斤拷?
	 * @see ?锟斤拷???????????????????????
	 */
	private ActionForward performClearquerylist(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawEventDicDAO eventdao = new TawEventDicDAO(ds);
		String pageOffset = null;
		List tawTestcard = null;
		String objKey = null;
		String url = null;
		String pagerHeader = null;
		StringBuffer sql = new StringBuffer();
		String condition = "";
		try {
			if (request.getParameter("submit") != null) {
				// --------------------????????----------------------
				// ??????
				if (!(form.getCardType().equals("7"))) {
					sql.append(" and cardType='" + form.getCardType() + "'");
				} else if (form.getOperation().equals("1")) {
					sql.append(" and cardType in ('1','2','4','5')");
				}
				// ??
				if (!(form.getState() == (null))
						&& !(form.getState().equals("5"))) {
					sql.append(" and state='" + form.getState() + "'");
				}
				// ?????
				if (form.getCardTypeNum().equals("0")) {
					if (!(form.getLeave() == (null))
							&& !(form.getLeave().equals(""))) {
						sql.append(" and leave='" + form.getLeave() + "' ");
					}
				} else {
					if (form.getFromCity() != null) {
						if (!form.getFromCity().equals("")) {
							sql.append(" and fromcity ='" + form.getFromCity()
									+ "'");
						}
					}
					if ((form.getToCountry() != null)
							&& !(form.getToCountry().equals(""))) {
						sql.append(" and tocountry = '" + form.getToCountry()
								+ "'");
						if ((form.getToCrit() != null && !(form.getToCrit()
								.equals("")))) {
							sql.append(" and tocrit = '" + form.getToCrit()
									+ "'");
							if ((form.getToCity() != null)
									&& !(form.getToCity().equals(""))) {
								sql.append(" and tocity = '" + form.getToCity()
										+ "'");
							}
						}
					}
				}
				// ???
				if (!(form.getCardpackage() == (null))
						&& !(form.getCardpackage().equals(""))) {
					sql.append(" and cardpackage='"
							+ eventdao.findName(form.getCardpackage()) + "'");
				}
				// ????
				if (!(form.getIccid() == (null))
						&& !(form.getIccid().trim().equals(""))) {
					sql.append(" and iccid like'" + form.getIccid().trim()
							+ "%' ");
				}
				// imsi
				if (!(form.getImsi() == (null))
						&& !(form.getImsi().trim().equals(""))) {
					sql.append(" and ( imsi like'" + form.getImsi().trim()
							+ "%' " + "or imsi1 like'" + form.getImsi().trim()
							+ "%') ");
				}
				// msisdn
				if (!(form.getMsisdn() == (null))
						&& !(form.getMsisdn().trim().equals(""))) {
					sql.append(" and ( msisdn like'" + form.getMsisdn().trim()
							+ "%' " + " or msisdn1 like'" + form.getMsisdn()
							+ "%')");
				}
				condition = sql.toString();
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.list(offset, length, size, condition);
			// String[] objKeys = {"tawTestcardmase", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = 0;//(Integer)SizeCacheManager.getCache(objKey);
			// if(size == null||(request.getParameter("submit")!=null)) {
			// size =new Integer( tawTestcardDAO.getLengh(condition));
			// SizeCacheManager.putCache(size, objKey, 0);
			// }
			url = request.getContextPath() + "/eventmanager"
					+ mapping.getPath() + ".do";
			pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcard", tawTestcard);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");

	}

	/**
	 * @xudongsuo ???????锟斤拷?
	 * @see ?锟斤拷???????????????????????
	 */
	private ActionForward performClearhistory(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardManagerForm form = (TawTestcardManagerForm) actionForm;
		// System.out.println(form.getId());
		TawSystemSessionForm saveSessionBeanForm = null;
		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		String WrfUserName = saveSessionBeanForm.getUsername();
		String idiccid = (String) request.getParameter("iccid");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// ??????
		try {/*
				 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				 * if (tawValidateBO.validatePriv(user_id,3003) == false) return
				 * mapping.findForward("nopriv");
				 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			TawTestcardManager tawTestcardManager = new TawTestcardManager();
			tawTestcardManager = tawTestcardManagerDAO.retrieve(form.getId());
			// tawTestcardManager.setReturner(WrfUserName);
			// tawTestcardManager.setRenewer(WrfUserName);
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardManager));
			// System.out.println(form.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// generalError(request, e);
			return mapping.findForward("failure");
		}

		int offset;
		int length = PAGE_LENGTH;
		String pageOffset = request.getParameter("pager.offset");

		saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		try {
			TawTestcardManagerDAO tawTestcardManagerDAO = new TawTestcardManagerDAO(
					ds);
			List tawTestcardClearNote = null;
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcardClearNote = tawTestcardManagerDAO.returnClearNote(
					offset, length, size, idiccid);
			/*
			 * String objKey=null; String condition=""; String[] objKeys =
			 * {"tawTestcardmase", "list"}; objKey =
			 * CacheManager.createKey(objKeys); Integer size =
			 * (Integer)SizeCacheManager.getCache(objKey); if(size == null) {
			 * size =new Integer( tawTestcardManagerDAO.getLengh(condition));
			 * SizeCacheManager.putCache(size, objKey, 0); }
			 */
			String url = request.getContextPath() + "/" + mapping.getPath()
					+ ".do";
			String pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcardClearNote", tawTestcardClearNote);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	private ActionForward performCreateNumber(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}
}
