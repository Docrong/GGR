package com.boco.eoms.testcard.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.*;
import com.boco.eoms.testcard.dao.*;
import com.boco.eoms.testcard.model.*;
import com.boco.eoms.testcard.util.StaticValue;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.service.bo.TawSystemDictBo;
import com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.testcard.dao.TawTestcardDAO;
import org.apache.struts.upload.*;
import com.boco.eoms.testcard.bo.TawTestcardLoadBO;
import com.boco.eoms.testcard.bo.TawTestcardBO;
import com.boco.eoms.workbench.contact.sample.FMImportSample;

import java.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;

public class TawTestcardAction extends BaseAction {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	/**
	 * ��ʾ�ļ�¼����
	 */
	private static int PAGE_LENGTH = 50;

	static ResourceBundle prop = null;

	private String user_id = "";
	static {
		prop = ResourceBundle.getBundle("resources.application_zh_CN");
		try {
			PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
		} catch (Exception e) {
		}
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		// session��ʱ����
		try {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");

			if (sessionform == null)
				return mapping.findForward("timeout");
			user_id = StaticMethod.null2String(sessionform.getUserid());
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		// Ȩ����֤
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
		} else if ("TOEDIT".equalsIgnoreCase(myaction)) {
			myforward = performToedit(mapping, form, request, response);
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("REMOVE".equalsIgnoreCase(myaction)) {
			myforward = performRemove(mapping, form, request, response);
		} else if ("UPDATE".equalsIgnoreCase(myaction)) {
			myforward = performUpdate(mapping, form, request, response);
		} else if ("UPDATEACTIVATE".equalsIgnoreCase(myaction)) {
			myforward = performUpdateAct(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("ADD".equalsIgnoreCase(myaction)) {
			myforward = performAdd(mapping, form, request, response);
		} else if ("SEARCH".equalsIgnoreCase(myaction)) {
			myforward = performSearch(mapping, form, request, response);
		} else if ("SEARCHDO".equalsIgnoreCase(myaction)) {
			myforward = performSearchDo(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else if ("STAT".equalsIgnoreCase(myaction)) {
			myforward = performStat(mapping, form, request, response);
		} else if ("TOSTATPAGE".equalsIgnoreCase(myaction)) {
			myforward = perform2Stat(mapping, form, request, response);
		} else if ("ACTIVATESTAT".equalsIgnoreCase(myaction)) {
			myforward = performActivateStat(mapping, form, request, response);
		} else if ("STATLIST".equalsIgnoreCase(myaction)) {
			myforward = performStatlist(mapping, form, request, response);
		} else if ("LOAD".equalsIgnoreCase(myaction)) {
			myforward = performLoad(mapping, form, request, response);
		} else if ("EXPORT".equalsIgnoreCase(myaction)) {
			myforward = performExport(mapping, form, request, response);
		} else if ("READY".equalsIgnoreCase(myaction)) {
			myforward = performReady(mapping, form, request, response);
		}
		else if ("BATCHTRASH".equalsIgnoreCase(myaction)) {
			myforward = performBatchTrash(mapping, form, request, response);
		} 
		else if ("TRASH".equalsIgnoreCase(myaction)) {
			myforward = performTrash(mapping, form, request, response); // ��־����
			if (myforward.equals(mapping.findForward("success"))) {

			} else if (myforward.equals(mapping.findForward("failure"))) {

			}
		} else if ("CREATENUMBERPAGE".equalsIgnoreCase(myaction)) {
			myforward = performCreateNumberPage(mapping, form, request, response);
		} else if ("CREATENUMBER".equalsIgnoreCase(myaction)) {
			myforward = performCreateNumber(mapping, form, request, response);
		} else if ("GETNOALIVEPHONE".equalsIgnoreCase(myaction)) {
			myforward = performGetNoAlivePhone(mapping, form, request, response);
		} else {
			myforward = mapping.findForward("failure");
		}

		return myforward;
	}

	/**
	 * @see ���Կ��б�
	 */

	private ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String pageOffset = null;
		List tawTestcard = null;
		String url = null;
		String pagerHeader = null;
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
			/*
			 * String[] objKeys = { "tawTestcardlist", "list"}; objKey =
			 * CacheManager.createKey(objKeys); Integer size = (Integer)
			 * SizeCacheManager.getCache(objKey); if (size == null) { size = new
			 * Integer(tawTestcardDAO.getLengh(""));
			 * SizeCacheManager.putCache(size, objKey, 0); }
			 */
			url = request.getContextPath() + "/" + mapping.getPath() + ".do";
			pagerHeader = Pager.generate(offset, size[0], length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcard", tawTestcard);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {

		} catch (Exception e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ��ѯ���Կ���ʼ��
	 */
	private ActionForward performSearch(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawTestcard tawTestcard = new TawTestcard();
		TawTestcardBO bo = new TawTestcardBO();
		try {
			// -------------------deptid--���ֵ����ȡ���豸����,ѹ��form------------------------
			// ƴ�ַ�
			String StringTree = bo.getMyTreeStr();
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
			// ѹ���ŵ�ѡ��
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave");
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
				entries.add(new LabelValueBean("", ""));
				form.setBeanCollectionDN(entries);
			}
			// ѹ����Ӫ��ѡ��
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
			// ѹ����Կ����ײ�����
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
			List filiales = tawEventDicDAO.findfiliale();
			request.setAttribute("filiales", filiales);
			Vector enti = new Vector(7);

			enti.add(new LabelValueBean(StaticValue.TawCard_ZHENGCHANG, "0"));
			enti.add(new LabelValueBean(StaticValue.TawCard_TINGJI, "1"));
			enti.add(new LabelValueBean(StaticValue.TawCard_YISHI, "2"));
			enti.add(new LabelValueBean(StaticValue.TawCard_SHIBAI, "6"));
			enti.add(new LabelValueBean(StaticValue.TawCard_JIECHU, "3"));
			enti.add(new LabelValueBean(StaticValue.TawCard_BAOFEI, "5"));
			enti.add(new LabelValueBean(StaticValue.TawCard_YISHENHE, "12"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_SHIYONG,"4"));

			form.setBeanCollection(enti);
			tawTestcard = tawTestcardDAO.retrieve(form.getIccid());
			form.setMsgcenterno(tawTestcard.getMsgcenterno());
			form.setLasttesttime(tawTestcard.getLasttesttime());
			form.setTestresult(tawTestcard.getTestresult());
			form.setDealresult(tawTestcard.getDealresult());
			form.setAdder(tawTestcard.getAdder());
			form.setTelnum(tawTestcard.getTelnum());
		} catch (Exception e) {

		}

		return mapping.findForward("success");
	}

	/**
	 */
	private ActionForward performSearchDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawEventDicDAO eventDicDao = new TawEventDicDAO();
		String pageOffset = null;
		List tawTestcard = null;
		String url = null;
		String pagerHeader = null;
		StringBuffer condition = new StringBuffer();
		try {
			if (request.getParameter("submit") != null && !"".equals(request.getParameter("submit"))) {
				if (form.getCardType() != null
						&& !"7".equals(form.getCardType())) {
					condition.append(" and cardType='" + form.getCardType()
							+ "'");
				}
				if (!(form.getIccid().equals(null))
						&& !(form.getIccid().equals(""))) {
					condition.append(" and iccid like'" + form.getIccid()
							+ "%' ");
				}
				if (form.getState()!=null && !"".equals(form.getState())) {
					condition.append(" and state='" + form.getState() + "'");
				}
				if (form.getCardTypeNum().equals("0")) {
					if (!(form.getLeave().equals(null))
							&& !(form.getLeave().equals(""))) {
						condition.append(" and leave='" + form.getLeave()
								+ "' ");
					}
					if (form.getFromCountry() != null && !"".equals(form.getFromCountry())) {
						condition.append(" and fromcountry='"
								+ form.getFromCountry() + "'");
						if (form.getFromCrit() != null) {
							condition.append(" and fromcrit = '"
									+ form.getFromCrit() + "'");
							if (form.getFromCity() != "") {
								condition.append(" and fromcity = '"
										+ form.getFromCity() + "'");
							}
						}
					}
				} else if (form.getCardTypeNum().equals("1")) {
					if (form.getFromCountry() != null
							&& !form.getFromCountry().equals("")) {
						condition.append(" and fromcountry='"
								+ form.getFromCountry() + "'");
						if (!form.getFromCrit().equals("")) {
							condition.append(" and fromcrit = '"
									+ form.getFromCrit() + "'");
							if (!form.getFromCity().equals("")) {
								condition.append(" and fromcity = '"
										+ form.getFromCity() + "'");
							}
						}
					}
					if (form.getToCountry() != null
							&& !form.getToCountry().equals("")) {
						condition.append(" and tocountry='"
								+ form.getToCountry() + "'");
						if (!form.getToCrit().equals("")) {
							condition.append(" and tocrit = '"
									+ form.getToCrit() + "'");
							if (!form.getToCity().equals("")) {
								condition.append(" and tocity = '"
										+ form.getToCity() + "'");
							}
						}
					}
				}
				if (!(form.getMsisdn().equals(null))
						&& !(form.getMsisdn().equals(""))) {
					condition.append(" and msisdn like'" + form.getMsisdn()
							+ "%' ");
				}
				if (!(form.getImsi().equals(null))
						&& !(form.getImsi().equals(""))) {
					condition
							.append(" and imsi like'" + form.getImsi() + "%' ");
				}
				if (!(form.getCardpackage().equals(null))
						&& !(form.getCardpackage().equals(""))) {
					condition.append(" and cardpackage = '"
							+ eventDicDao.findName(form.getCardpackage())
							+ "' ");
				}
				if (!(form.getPhoneNumber().equals(null))
						&& !(form.getPhoneNumber().equals(""))) {
					condition.append(" and phone_number like '%"
							+ eventDicDao.findName(form.getCardpackage())
							+ "%' ");
				}
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (StringBuffer) request.getSession().getAttribute(
						"condition");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = 22;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.list(offset, length, size, condition
					.toString()+"and deleted=0");
			// String[] objKeys = {"tawTestcardse", "list"};
			// objKey = CacheManager.createKey(objKeys);
			// Integer size = (Integer) SizeCacheManager.getCache(objKey);
			// if ((size == null)||(request.getParameter("submit") != null)) {
			// size = new
			// Integer(tawTestcardDAO.getLengh(condition.toString()));
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
	 */
	private ActionForward performStatlist(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		String pageOffset = null;
		List tawTestcard = null;
		String url = null;
		String pagerHeader = null;
		String condition = "";
		try {
			if ((form.getState() != null) && !(form.getState().equals(""))) {
				condition = " and state='" + form.getState() + "'";
			}
			if ((form.getLeave() != null) && !(form.getLeave().equals(""))) {
				condition = condition + " and leave='" + form.getLeave() + "' ";
			}
			if((form.getIsAlive()) != null && !(form.getIsAlive().equals(""))) {
				condition = condition + " and is_alive='" + form.getIsAlive() +"'";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = 3000;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.list(offset, length, size, condition
					.toString());
	
			/*
			 * String[] objKeys = { "tawTestcardse", "list"}; objKey =
			 * CacheManager.createKey(objKeys); Integer size = (Integer)
			 * SizeCacheManager.getCache(objKey); if (size == null) { size = new
			 * Integer(tawTestcardDAO.getLengh(condition));
			 * SizeCacheManager.putCache(size, objKey, 0); }
			 */
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
	 * @see ���Կ���ϸ��Ϣ����ʾ
	 */
	private ActionForward performView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		ID2NameServiceImpl mgr = (ID2NameServiceImpl) getBean("id2nameService");

		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			TawTestcard tawTestcard = new TawTestcard();
			if("".equals(StaticMethod.null2String(form.getIccid()))){
				tawTestcard = tawTestcardDAO.retrievetelnum(form.getPhoneNumber());	
			}
			else{
			tawTestcard = tawTestcardDAO.retrieve(form.getIccid());
			}
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcard));
//			System.out.println(tawTestcard.getCardpackage());
	
			try {
				if (tawTestcard.getCardpackage()!=null &&tawTestcard.getCardpackage().equals("˫ģ")) {
					form.setMsisdn(tawTestcard.getMsisdn() + " / "
							+ tawTestcard.getMsisdn1());
					form.setImsi(tawTestcard.getImsi() + " / "
							+ tawTestcard.getImsi1());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (form.getLeave() != null) {
					// form.setLeave(tawEventDicDAO.findName(form.getLeave()));
					// add dict by gongyufeng

					form.setLeave(mgr.id2Name(form.getLeave(),
							"ItawSystemDictTypeDao"));
				} else {
					form.setLeave("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Added by Wangshouling
			if(tawTestcard!=null){
			form.setFromCountry(tawTestcard.getFromCountry());
			form.setFromOpe(tawTestcard.getFromOpe());
			form.setFromCrit(tawTestcard.getFromCrit());
			form.setFromCity(tawTestcard.getFromCity());
			form.setToCountry(tawTestcard.getToCountry());
			form.setToOpe(tawTestcard.getToOpe());
			form.setToCrit(tawTestcard.getToCrit());
			form.setToCity(tawTestcard.getToCity());

			form.setMsgcenterno(tawTestcard.getMsgcenterno());
			form.setLasttesttime(tawTestcard.getLasttesttime());
			form.setTestresult(tawTestcard.getTestresult());
			form.setDealresult(tawTestcard.getDealresult());
			form.setAdder(tawTestcard.getAdder());
			form.setTelnum(tawTestcard.getTelnum());
			form.setExes(tawTestcard.getExes());
			form.setVolumenum(tawTestcard.getVolumenum());
			form.setPagenum(tawTestcard.getPagenum());
		    try{
		    	Integer.parseInt(tawTestcard.getCardpackage());
		    	form.setCardpackage(tawEventDicDAO.findName(tawTestcard.getCardpackage()));	
		    } catch (Exception e) {
		    	form.setCardpackage(tawTestcard.getCardpackage());	
			}
				
			
			}
			if(form!=null){
			try {
				if (form.getState().equals("0")) {
					form.setState("正常");
				} else if (form.getState().equals("1")) {
					form.setState("停机");
				} else if (form.getState().equals("2")) {
					form.setState("遗失");
				} else if (form.getState().equals("3")) {
					form.setState("借出");
				} else if (form.getState().equals("4")) {
					form.setState("使用");
				} else if (form.getState().equals("5")) {
					form.setState("报废");
				} else if (form.getState().equals("6")) {
					form.setState("SIM卡注册失败");
				} else if (form.getState().equals(StaticValue.STATUS_WAIT)) {
					form.setState("待审核");
				} else if (form.getState().equals(StaticValue.STATUS_PASS)) {
					form.setState("已审核");
				}
				request.setAttribute("cardtypestate", form.getCardType());
				if (form.getCardType().equals("0")) {
					form.setCardType("国际出访卡");
				} else if (form.getCardType().equals("1")) {
					form.setCardType("国际来访卡");
				} else if (form.getCardType().equals("2")) {
					form.setCardType("省际来访卡");
				} else if (form.getCardType().equals("3")) {
					form.setCardType("省际出访卡");
				} else if (form.getCardType().equals("4")) {
					form.setCardType("本地测试卡");
				} else if (form.getCardType().equals("5")) {
					form.setCardType("省内来访卡�ÿ�");
				} else if (form.getCardType().equals("6")) {
					form.setCardType("省内出访卡");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}

	/**
	 * @see ������Կ���Ϣ
	 */
	private ActionForward performSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// System.out.print("aaa");
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawTestcard tawTestcard = new TawTestcard();
		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
		int ifIccid = 0;
		int ifMsisdn = 0;
		int ifImsi = 0;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userName = sessionform.getUsername();
		

		try {
			ifIccid = tawTestcardDAO.ifIccid("iccid", form.getIccid());
			if (form.getStrutsAction() == TawTestcardForm.ADD) {
				if (ifIccid > 0) {
					form.setIfIccid(1);
					performAdd(mapping, form, request, response);
					request.setAttribute("error", "对不起，您输入的iccid重复，请检查后重新填写");
					return mapping.findForward("ifIccid");
				}
				ifMsisdn = tawTestcardDAO.ifIccid("msisdn", form.getMsisdn());
				if (ifMsisdn > 0) {
					performAdd(mapping, form, request, response);
					request.setAttribute("error", "对不起，您输入的msisdn重复，请检查后重新填写");
					return mapping.findForward("ifIccid");
				}
				ifImsi = tawTestcardDAO.ifIccid("imsi", form.getImsi());
				if (ifImsi > 0) {
					performAdd(mapping, form, request, response);
					request.setAttribute("error", "对不起，您输入电话号码重复，请检查后重新填写");
					return mapping.findForward("ifIccid");
				}
			}
			// try{
			// form.setFromCanton(String.valueOf(tawEventDicDAO.findParent_id(Integer.parseInt(form.getFromCountry()))));
			// }catch(Exception e){e.printStackTrace();}
			// try{
			// form.setToCanton(String.valueOf(tawEventDicDAO.findParent_id(Integer.parseInt(form.getToCountry()))));
			// }catch(Exception e){e.printStackTrace();}
			// try{
			// form.setFromCrit(String.valueOf(tawEventDicDAO.findParent_id(Integer.parseInt(form.getFromCrit()))));
			// }catch(Exception e){e.printStackTrace();}
			// try{
			// form.setToCrit(String.valueOf(tawEventDicDAO.findParent_id(Integer.parseInt(form.getToCrit()))));
			// }catch(Exception e){e.printStackTrace();}
			// form.setMsgcenterno(form.getMsgcenterno());
			// form.setLasttesttime(form.getLasttesttime());
			// form.setTestresult(form.getTestresult());
			// form.setDealresult(form.getDealresult());
			
//			if (form.getCardType().equals("0")
//					|| form.getCardType().equals("3")
//					|| form.getCardType().equals("6")) {
//				form.setLeave("");
//			}
			form.setAdder(userName);
			form.setIntime(StaticMethod.getCurrentDateTime());
			form.setId(tawTestcardDAO.getId(form.getIccid()));
			// form.setPosition(form.getPosition());
			// form.setVolumenum(form.getVolumenum());
			// form.setPagenum(form.getPagenum());
			if (!form.getCardpackage().equals("")
					&& !form.getCardpackage().equals(null)) {
				form.setCardpackage(String.valueOf(tawEventDicDAO.findName(form
						.getCardpackage())));
			}
			org.apache.commons.beanutils.BeanUtils.populate(tawTestcard,
					org.apache.commons.beanutils.BeanUtils.describe(form));
			tawTestcard.setPhoneNumber(form.getPhoneNumber());
			if (!(form.getCardpackage() == "˫ģ双模")
					&& !(form.getCardpackage().trim().equals("双模˫ģ"))) {
				tawTestcard.setMsisdn1("");
				tawTestcard.setImsi1("");
			}
			
			if (form.getStrutsAction() == (TawTestcardForm.ADD)) {
				tawTestcardDAO.insert(tawTestcard);
				form = null;
			} else if (form.getStrutsAction() == (TawTestcardForm.EDIT)) {
				if(tawTestcard.getId()==0){
				tawTestcard.setId(tawTestcardDAO.retrievetelnum(form.getPhoneNumber()).getId());
				}
			
				
//				tawTestcard.setIsAlive("0");
				tawTestcard.setState("12");
				tawTestcardDAO.update(tawTestcard);
				form = null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		form = null;
		return mapping.findForward("success");
	}

	/**
	 */
	private ActionForward performEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		try {
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave");
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
				form.setBeanCollectionDN(entries);
			}

			// ѹ����Ӫ��ѡ��
			Vector vmoper = (Vector) tawEventDicDAO.list("testcard_oper");
			if (vmoper == null || vmoper.size() == 0) {
				//return mapping.findForward("failure");
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

			// ѹ��״̬ѡ��
			Vector enti = new Vector(3);
			enti.add(new LabelValueBean("正常", "0"));
			enti.add(new LabelValueBean("停机", "1"));
			enti.add(new LabelValueBean("遗失", "2"));
			enti.add(new LabelValueBean("待审核", StaticValue.STATUS_WAIT));
			enti.add(new LabelValueBean("已审核", StaticValue.STATUS_PASS));
			enti.add(new LabelValueBean("SIM卡注册失败", "6"));
			// enti.add(new LabelValueBean("借出", "3"));
			// enti.add(new LabelValueBean("使用", "4"));
			form.setBeanCollection(enti);
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			TawTestcard tawTestcard = new TawTestcard();
			tawTestcard = tawTestcardDAO.retrieve(form.getIccid());
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcard));
			form.setStrutsAction(TawTestcardForm.EDIT);
		} catch (Exception e) {
			System.out.println("取数据错误，请检查");
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ������Կ�
	 */
	private ActionForward performAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		String deptid = sessionform.getDeptid();
		request.setAttribute("tawTestcardForm", form);
		TawTestcardBO bo = new TawTestcardBO();
		try {
			// ƴ�ַ�
			String StringTree = bo.getMyTreeStr();
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 部门
			TawSystemDept tawSystemDept = mgr.getDeptinfobydeptid(deptid, "0");
			// DeptDAO deptdao= new DeptDAO(ds);
			int tempdeptid = tawEventDicDAO.isprovince(StaticMethod
					.null2int(tawSystemDept.getParentDeptid()));
			
//			while (tempdeptid == -1) {
//				if(deptid!=null){
//			 tawSystemDept = mgr.getDeptinfobydeptid(deptid, "0");
//				// deptid=deptdao.findParent_id(deptid);
//				deptid = tawSystemDept.getParentDeptid();// 取这个部门的父部门
//				tempdeptid = tawEventDicDAO
//						.isprovince(Integer.parseInt(deptid));
//			}
//			}
//			deptid = String.valueOf(tempdeptid);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			if (vm1 == null || vm1.size() == 0) {
				//return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeanCollectionDN(entries);
			}
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
			// ѹ����Կ����ײ�����
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

			// 压入状态选项
			Vector enti = new Vector(3);
			enti.add(new LabelValueBean("正常", "0"));
			enti.add(new LabelValueBean("停机", "1"));
			enti.add(new LabelValueBean("遗失", "2"));
			enti.add(new LabelValueBean("待审核", StaticValue.STATUS_WAIT));
			enti.add(new LabelValueBean("已审核", StaticValue.STATUS_PASS));
			enti.add(new LabelValueBean("SIM卡注册失败", "6"));
			form.setBeanCollection(enti);
			form.setStrutsAction(TawTestcardForm.ADD);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ȷ��ɾ���
	 */
	private ActionForward performRemove(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			TawTestcard tawTestcard = new TawTestcard();
			tawTestcard = tawTestcardDAO.retrieve(form.getIccid());
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcard));
			try {
				form.setFromCanton(tawEventDicDAO
						.findName(form.getFromCanton()));
			} catch (Exception e) {
			}
			try {
				form.setFromCountry(tawEventDicDAO.findName(form
						.getFromCountry()));
			} catch (Exception e) {
			}
			try {
				form.setFromOpe(tawEventDicDAO.findName(form.getFromOpe()));
			} catch (Exception e) {
			}
			try {
				form.setFromCrit(tawEventDicDAO.findName(form.getFromCrit()));
			} catch (Exception e) {
			}
			try {
				form.setFromCity(tawEventDicDAO.findName(form.getFromCity()));
			} catch (Exception e) {
			}
			try {
				form.setToCanton(tawEventDicDAO.findName(form.getToCanton()));
			} catch (Exception e) {
			}
			try {
				form.setToCountry(tawEventDicDAO.findName(form.getToCountry()));
			} catch (Exception e) {
			}
			try {
				form.setToOpe(tawEventDicDAO.findName(form.getToOpe()));
			} catch (Exception e) {
			}
			try {
				form.setToCrit(tawEventDicDAO.findName(form.getToCrit()));
			} catch (Exception e) {
			}
			try {
				form.setToCity(tawEventDicDAO.findName(form.getToCity()));
			} catch (Exception e) {
			}
			try {
				form.setLeave(tawEventDicDAO.findName(form.getLeave()));
			} catch (Exception e) {
			}
			try {
				if (form.getState().equals("0")) {
					form.setState("正常");
				} else if (form.getState().equals("1")) {
					form.setState("停机");
				} else if (form.getState().equals("2")) {
					form.setState("遗失");
				} else if (form.getState().equals("3")) {
					form.setState("借出");
				} else if (form.getState().equals(StaticValue.STATUS_WAIT)) {
					form.setState("待审核");
				} else if (form.getState().equals(StaticValue.STATUS_PASS)) {
					form.setState("已审核");
				}
				if (form.getCardType().equals("0")) {
					form.setCardType("国际出访卡");
				} else if (form.getCardType().equals("1")) {
					form.setCardType("国际来访卡");
				} else if (form.getCardType().equals("2")) {
					form.setCardType("省际来访卡");
				} else if (form.getCardType().equals("3")) {
					form.setCardType("省际出访卡");
				} else if (form.getCardType().equals("4")) {
					form.setCardType("本地测试卡");
				}
			} catch (Exception e) {
			}

		} catch (Exception e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ɾ����Կ�
	 */
	private ActionForward performTrash(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform == null)
			return mapping.findForward("timeout");
		user_id = StaticMethod.null2String(sessionform.getUserid());
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			tawTestcardDAO.delete(form.getIccid());
		} catch (Exception e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * @see �ۺ�ͳ��
	 */
	private ActionForward performStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardDAO tc = new TawTestcardDAO();
		try {
			List stat = tc.statall();
			request.setAttribute("STATALLLIST", stat);
			return mapping.findForward("success");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * 跳转到激活页面
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward perform2Stat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("success");
	}
	/**
	 * 测试卡激活统计
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performActivateStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardDAO tc = new TawTestcardDAO();
		try {
			List stat = tc.statActall();
			request.setAttribute("STATALLLIST", stat);
			return mapping.findForward("success");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * @throws IOException
	 * @see ������Կ���Ϣ
	 */
	private ActionForward performLoad(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userName = sessionform.getUsername();
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		String repeatRecord = "";
		HttpSession session = request.getSession();
		if (id == null || "".equals(id)) {

			id = (String) session.getAttribute("loadId");
			session.removeAttribute("loadId");
			repeatRecord = (String) session.getAttribute("repeatRecord");
			session.removeAttribute("repeatRecord");
		}
		List errorList = new ArrayList();

		String errStr = "";
		FormFile file = form.getTheFile();
		int filetype = form.getFiletype();
		String filePath = request.getRealPath("/testcard/loadfile"); // ȡ��ǰϵͳ·��

		TawTestcardLoadBO bo = new TawTestcardLoadBO(filePath + "/"
				+ file.getFileName());
		errStr = bo.UpLoadFile(file, filePath);
		FileInputStream fIn = null;
		try {
			fIn = new FileInputStream(filePath + "/" + file.getFileName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String leave = form.getLeave();
		HSSFWorkbook readWorkBook = new HSSFWorkbook(fIn);
		// HSSFSheet readSheet = readWorkBook.getSheet("firstSheet");
		HSSFSheet readSheet = readWorkBook.getSheetAt(0);
		TawTestcard tp;
		int maxrow = readSheet.getPhysicalNumberOfRows();
		int col = readSheet.getDefaultColumnWidth();
		String exceptionStr;
		int count = maxrow - 2;
		repeatRecord = "成功导入" + count + "行数据!";
		for (int i = 2; i < maxrow; i++) {
			HSSFRow readRow = readSheet.getRow(i);
			if (readRow != null) {
				tp = new TawTestcard();
				for (int j = 1; j < col + 11; j++) {
					exceptionStr = "";
					HSSFCell readCell = readRow.getCell((short) j);
					int a = i + 1;
					int b = j + 1;
					System.out.println(j);
					if (j == 1) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);

							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setFromCountry(str);
					}
					if (j == 2) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setFromOpe(str);
					}
					if (j == 3) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setFromCrit(str);
					}
					if (j == 4) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setFromCity(str);
					}
					if (j == 5) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						// tp.setFromCity(str);
						tp.setIccid(str);
					}
					if (j == 6) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setOldNo(str);// 单卡编号(OLDNO)
					}
					if (j == 7) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}

						tp.setMsisdn(str);// MSISDN
					}
					if (j == 8) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}

						tp.setImsi(str);// MSISDN
					}
					if (j == 9) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}

						if (str.equals("国际出访卡")) {
							tp.setCardType("0");
						} else if (str.equals("国际来访卡")) {
							tp.setCardType("1");
						} else if (str.equals("省际来访卡")) {
							tp.setCardType("2");
						} else if (str.equals("省际出访卡")) {
							tp.setCardType("3");
						} else if (str.equals("本地测试卡")) {
							tp.setCardType("4");
						} else if (str.equals("省内来访卡")) {
							tp.setCardType("5");
						} else if (str.equals("省内出访卡")) {
							tp.setCardType("6");
						}

					}
					if (j == 10) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						if (str.equals("正常")) {
							tp.setState("0");
						}

						else if (str.equals("停机")) {
							tp.setState("1");
						} else if (str.equals("遗失")) {
							tp.setState("2");
						} else if (str.equals("借出")) {
							tp.setState("3");
						} else if (str.equals("使用")) {
							tp.setState("4");
						} else if (str.equals("报废")) {
							tp.setState("5");
						} else if (str.equals("待审核")) {
							tp.setState(StaticValue.STATUS_WAIT);
						} else if (str.equals("已审核")) {
							tp.setState(StaticValue.STATUS_PASS);
						}
						else{
							tp.setState("0");
						}

					}
					if (j == 11) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setCardpackage(str);// 套餐

					}
					if (j == 12) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setExes(str); // 费用情况

					}
					if (j == 13) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setOffer(str);// 归属HLR厂商

					}
					if (j == 14) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setMsgcenterno(str);

					}
					if (j == 15) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setAdder(str); // 入库人

					}
					if (j == 16) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							// return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setPosition(str); // 入库人

					}
					if (j == 17) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							// return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setEditState(str); // 入库人

					}
					if (j == 18) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						tp.setEditState(str); // 入库人

					}
				}
				Date dt = new Date();
				SimpleDateFormat smpDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String sTime = smpDateFormat.format(dt);
				tp.setIntime(sTime);
				tp.setAdder(userName);
				TawTestcardDAO dao = new TawTestcardDAO();
				System.out.print("i:" + i);

				tp.setLeave(leave);
				if (id.equals("1")) {
					if (dao.getMsisdnCount(tp.getMsisdn(), leave) == 0) {
						try {
							dao.insert(tp);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						repeatRecord += "【MSISDN为" + tp.getMsisdn()
								+ "的测试卡有重复记录】/n";
					}

				} else if (id.equals("2")) {
					if (tp.getEditState().equals("1.0")
							|| tp.getEditState().equals("1")) {
						try {
							if (dao.getMsisdnCount(tp.getMsisdn(), leave) == 1) {
								dao.updateMsisdn(tp);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else if (id.equals("3")) {
					try {
						if (dao.getMsisdnCount(tp.getMsisdn(), leave) == 1) {
							dao.deleteMsisdn(tp.getMsisdn());
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				// int count=i;
				repeatRecord += "你上传的列表第" + i + "行为空";
				request.setAttribute("falseMessage", repeatRecord);
				return mapping.findForward("falsePage");
			}
		}

		// if (errStr.equals("")) {
		// errorList = bo.checkData(filetype);
		// } else {
		// errorList.add(errStr);
		// }
		// if (errorList.size() == 0) {
		// bo.importData(filetype, form.getLeave(), userName,id);
		// } else {
		// request.setAttribute("errorList", errorList);
		// return mapping.findForward("error");
		// }
		session.setAttribute("loadId", id);
		session.setAttribute("repeatRecord", repeatRecord);
		request.setAttribute("id", id);
		request.setAttribute("message", repeatRecord);
		return mapping.findForward("success");
	}

	/**
	 * 
	 */
	private ActionForward performExport(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		int filetype = form.getFiletype();
		TawTestcardBO ttbo = new TawTestcardBO();
		String reaction = "../../testcard/manager/TawTestcard/down.jsp";
		String excelfilename = "";
		if (filetype == 0) {
			excelfilename = ttbo.exportModelIn(form.getLeave());
		} else {
			excelfilename = ttbo.exportModelOut(form.getLeave());
			System.out.println(form.getLeave());
		}
		// ����ҳ��
		ActionForward actionForward = new ActionForward(reaction + "?fileName="
				+ excelfilename + "&&path=tempfiledownload");
		actionForward.setRedirect(true);
		return actionForward;
	}

	/**
	 * @see ���Կ�����׼��
	 */
	private ActionForward performReady(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		int deptid = Integer.parseInt(sessionform.getDeptid());
		request.setAttribute("tawTestcardForm", form);
		try {
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 部门

			// DeptDAO deptdao= new DeptDAO(ds);

			int tempdeptid = tawEventDicDAO.isprovince(deptid);
//			while (tempdeptid == -1) {
//				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
//						deptid));
//				// deptid=deptdao.findParent_id(deptid);
//				deptid = Integer.parseInt(tawSystemDept.getParentDeptid());// 取这个部门的父部门
//				tempdeptid = tawEventDicDAO.isprovince(deptid);
//			}
			deptid = tempdeptid;
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave", deptid);
			if (vm1 == null || vm1.size() == 0) {
				//return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				form.setBeanCollectionDN(entries);
			}
		} catch (Exception e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * 
	 */
	private ActionForward performUpdate(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawTestcard tawTestcard = new TawTestcard();
		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		String cardpackage=request.getParameter("cardpackage");
		String id=(String)request.getParameter("id");
		String iccid=(String)request.getParameter("iccid");
		form.setId(java.lang.Integer.parseInt(id));
		form.setIccid(iccid);
		// int ifMsisdn = 0;
		// int ifImsi = 0;
		try {
			// ifMsisdn = tawTestcardDAO.ifIccid("msisdn", form.getMsisdn());
			// if (ifMsisdn > 0) {
			// performAdd(mapping, form, request, response);
			// request.setAttribute("error",
			// "�Բ����������msisdn�ظ��������������д");
			// return mapping.findForward("ifIccid");
			// }
			// ifImsi = tawTestcardDAO.ifIccid("imsi", form.getImsi());
			// if (ifImsi > 0) {
			// performAdd(mapping, form, request, response);
			// request.setAttribute("error",
			// "�Բ���������绰�����ظ��������������д");
			// return mapping.findForward("ifIccid");
			// }

			org.apache.commons.beanutils.BeanUtils.populate(tawTestcard,
					org.apache.commons.beanutils.BeanUtils.describe(form));
			if (cardpackage!=null
					&& !(cardpackage.equals("35"))) {
				tawTestcard.setMsisdn1("");
				tawTestcard.setImsi1("");
			}
//			<option value="49">国际来访卡</option>
//			<option value="9">省际C网IN卡</option>
//			<option value="8">省际C网VPN卡</option>
//			<option value="7">省际C网普通卡</option>
//			<option value="30">省际G网IN卡</option>
//			<option value="51">省际G网VPN卡</option>
//			<option value="10">省际G网普通卡</option>
//			<option value="32">省内C网IN卡</option>
//			<option value="33">省内C网VPN卡</option>
//			<option value="31">省内C网普通卡</option>
//			<option value="52">省内G网IN卡</option>
//			<option value="53">省内G网VPN卡</option>
//			<option value="34">省内G网普通卡</option>
//			<option value="35">双模卡</option>
			if(cardpackage.equals("49")){
				tawTestcard.setCardpackage("国际来访卡");
			}
			else if(cardpackage.equals("9")){
				tawTestcard.setCardpackage("省际C网IN卡");
			}
			else if(cardpackage.equals("8")){
				tawTestcard.setCardpackage("省际C网VPN卡");
			}
			else if(cardpackage.equals("7")){
				tawTestcard.setCardpackage("省际C网普通卡");
			}
			else if(cardpackage.equals("30")){
				tawTestcard.setCardpackage("省际G网IN卡");
			}
			else if(cardpackage.equals("51")){
				tawTestcard.setCardpackage("省际G网VPN卡");
			}
			else if(cardpackage.equals("10")){
				tawTestcard.setCardpackage("省际G网普通卡");
			}
			else if(cardpackage.equals("32")){
				tawTestcard.setCardpackage("省内C网IN卡");
			}
			else if(cardpackage.equals("33")){
				tawTestcard.setCardpackage("省内C网VPN卡");
			}
			else if(cardpackage.equals("31")){
				tawTestcard.setCardpackage("省内C网普通卡");
			}
			else if(cardpackage.equals("52")){
				tawTestcard.setCardpackage("省内G网IN卡");
			}
			else if(cardpackage.equals("53")){
				tawTestcard.setCardpackage("省内G网VPN卡");
			}
			else if(cardpackage.equals("34")){
				tawTestcard.setCardpackage("省内G网普通卡");
			}
			else if(cardpackage.equals("35")){
				tawTestcard.setCardpackage("双模卡");
			}
			tawTestcardDAO.update(tawTestcard);
		} catch (Exception e) {
			System.out.println(e);
		}
		form = null;
		return mapping.findForward("success");
	}
	private ActionForward performUpdateAct(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawTestcard tawTestcard = new TawTestcard();
		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		// int ifMsisdn = 0;
		// int ifImsi = 0;
		try {
			org.apache.commons.beanutils.BeanUtils.populate(tawTestcard,
					org.apache.commons.beanutils.BeanUtils.describe(form));
			if (!(form.getCardpackage() == "35")
					&& !(form.getCardpackage().trim().equals("35"))) {
				tawTestcard.setMsisdn1("");
				tawTestcard.setImsi1("");
			}
			tawTestcardDAO.activateTestCard(tawTestcard);
		} catch (Exception e) {
			System.out.println(e);
		}
		form = null;
		return mapping.findForward("success");
	}
	/**
	 * 
	 */
	private ActionForward performToedit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		int deptid = Integer.parseInt(sessionform.getDeptid());

		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		TawTestcard tawTestcard = new TawTestcard();
		try {
			tawTestcard = tawTestcardDAO.retrieve(form.getIccid());
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcard));
			form.setCardpackage(new TawEventDicDAO().findId(form
					.getCardpackage()));
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		request.setAttribute("tawTestcardForm", form);
		TawTestcardBO bo = new TawTestcardBO();
		try {
			// ƴ�ַ�
			String StringTree = bo.getMyTreeStr();
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
			String StringToTree = bo.getToTreeStr();
			if (!StringToTree.equals("")) {
				request.setAttribute("StringToTree", StringToTree);
			}
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 部门

			int tempdeptid = tawEventDicDAO.isprovince(deptid);
//			while (tempdeptid == -1) {
//				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
//						deptid));
//				deptid = Integer.parseInt(tawSystemDept.getParentDeptid());// 取这个部门的父部门
//				tempdeptid = tawEventDicDAO.isprovince(deptid);
//			}
			deptid = tempdeptid;
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave", deptid);
			if (vm1 == null || vm1.size() == 0) {
				//return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				form.setBeanCollectionDN(entries);
			}
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
				// entries.add(new LabelValueBean("", ""));
				form.setBeCollep(entries);
			}
			Vector enti = new Vector(5);
			enti.add(new LabelValueBean(StaticValue.TawCard_ZHENGCHANG, "0"));
			enti.add(new LabelValueBean(StaticValue.TawCard_TINGJI, "1"));
			enti.add(new LabelValueBean(StaticValue.TawCard_YISHI, "2"));
			enti.add(new LabelValueBean(StaticValue.TawCard_SHIBAI, "6"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_JIECHU ,"3"));
			// enti.add(new LabelValueBean(StaticValue.TawCard_SHIYONG,"4"));
			form.setBeanCollection(enti);
			form.setStrutsAction(TawTestcardForm.EDIT);
		} catch (Exception e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * 新增号码管理 add by zdl
	 */
	private ActionForward performCreateNumber(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		TawTestcardBO bo = new TawTestcardBO();
		bo.isertPhoneNumber(form);
		return mapping.findForward("success");
	}

	/**
	 * 新增号码管理 add by zdl
	 */
	private ActionForward performCreateNumberPage(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("success");
	}
	
	/**
	 * 新增号码管理 add by zdl
	 */
	private ActionForward performGetNoAlivePhone(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("success");
	}
	
	private ActionForward performBatchTrash(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardForm form = (TawTestcardForm) actionForm;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String[] iccid= request.getParameterValues(("iccid"));
		if (sessionform == null)
			return mapping.findForward("timeout");
		user_id = StaticMethod.null2String(sessionform.getUserid());
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			if(iccid.length>0){
				for(int i=0;i<iccid.length;i++){
			tawTestcardDAO.delete(iccid[i]);
				}
			}
		} catch (Exception e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
}