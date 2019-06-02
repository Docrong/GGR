package com.boco.eoms.sparepart.controller;

import java.util.List;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.sparepart.bo.TawSparepartBO;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticVariable;
//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.sparepart.bo.TawQueryBO;
import com.boco.eoms.sparepart.dao.TawQueryDAO;
import com.boco.eoms.sparepart.bo.TawClassMsgBO;
import com.boco.eoms.sparepart.model.EarlyWarning;
import com.boco.eoms.sparepart.model.TawClassMsg;
import com.boco.eoms.sparepart.dao.TawOrderDAO;
import com.boco.eoms.sparepart.bo.TawOrderBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sparepart.dao.TawSparepartDAO;
import java.util.Vector;
import com.boco.eoms.sparepart.util.TawReturnDom;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.sparepart.bo.*;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.sparepart.model.*;


/**
 * <p>
 * Title: EOMS
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: BOCO
 * </p>
 *
 * @author HAO
 * @version 2.0
 */

public class TawQueryAction extends Action {
	public TawQueryAction() {
	}

	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	private static int PAGE_LENGTH = 10;

	private String user_id = "";
	private String dept_id = "";

	List STORAGE = new ArrayList();

	static {
		ResourceBundle prop = ResourceBundle
				.getBundle("resources.application_zh_CN");
		try {
			PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ���������
	 */
	private void generalError(HttpServletRequest request, Exception e) {
		ActionErrors aes = new ActionErrors();
		aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e
				.getMessage()));
		saveErrors(request, aes);
		e.printStackTrace();
	}

	/**
	 * @see ϵͳ��־����
	 
	private void insertLog(ActionMapping mapping, HttpServletRequest request,
			String ret, String name) {
		try {
			//logBO logbo = new logBO(ds);
			if (ret.equals("success")) {
				boolean bool = logbo.insertLogToDbPathNew(user_id, mapping
						.getPath(), StaticVariable.OPER, request
						.getRemoteAddr(), name);

			} else if (ret.equals("failure")) {
				boolean bool = logbo.insertLogToDbPathNew(user_id, mapping
						.getPath(), StaticVariable.ERROR, request
						.getRemoteAddr(), name);
			}
		} catch (Exception e) {
			BocoLog.error(this, 0, "ϵͳ��־����(����taw_log)����", e);
		}
	}
    */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();
		System.out.println(myaction);

		//session��ʱ����
		try {
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			user_id = StaticMethod.null2String(saveSessionBeanForm.getUserid());
			dept_id = StaticMethod.null2String(saveSessionBeanForm.getDeptid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Ȩ����֤
		try {
			if(mapping.getPath().equals("/query/term")||mapping.getPath().equals("/query/view")){
				TawStorageBO tawStorageBO = new TawStorageBO(ds);
				STORAGE = tawStorageBO.getStorageListByDeptId(dept_id);
			}else{
				/*
				TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
				if (!tawValidateBO.validPriv(user_id, mapping.getPath())) {
					return mapping.findForward("nopriv");
				}
				//20040712
				Vector v = tawValidateBO.validatePriv(user_id, 7014, 17);
				TawReturnDom TawReturnDom = new TawReturnDom(ds);
				STORAGE = TawReturnDom.getStorage(v);

				if (user_id.equalsIgnoreCase("admin")) {
					TawQueryBO tawQueryBO = new TawQueryBO(ds);
					STORAGE = tawQueryBO.getStorage();
				} else if (STORAGE.size() == 0) {
					return mapping.findForward("nopriv");
				}
				*/
				TawStorageBO tawStorageBO = new TawStorageBO(ds);
				STORAGE = tawStorageBO.getStorageListByDeptId(dept_id);
			}


			//20040712

		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * @see ѡ��ֿ�, ��ݾ���Ŀͻ�����ѡ���ܡ�
		 * @see ѡ��ֿ��ֻ�ܲ鵽��ѡ��Ĳֿ�ı�����Ϣ��
		 */
		//if(request.getSession().getAttribute("storage")==null){
		// ActionForward actionForward=new ActionForward(
		//       "/storage/choose.do");
		//  return actionForward;
		// }
		//
		if (isCancelled(request)) {
			return mapping.findForward("failure");
		} else if ("TERM".equalsIgnoreCase(myaction)) {
			myforward = performTerm(mapping, form, request, response);
		}else if ("FINDLOAD".equalsIgnoreCase(myaction)) {
			myforward = performFindload(mapping, form, request, response);
		}else if("UPDATETERM".equalsIgnoreCase(myaction)){
			myforward=performUpdateterm(mapping,form,request,response);
	    }else if("UPDATEPAGE".equalsIgnoreCase(myaction)){
	    	myforward=performUpdatepage(mapping,form,request,response);
	    }
	    else if("SERIALNO".equalsIgnoreCase(myaction)){
	    	myforward=performSerialno(mapping,form,request,response);
	    }
	    else if("SERIALNOEND".equalsIgnoreCase(myaction)){
	        myforward=performSerialnoEnd(mapping,form,request,response);
	    }
	    else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} 
	    else if ("VIEWTOEXP".equalsIgnoreCase(myaction)) {
			myforward = performViewtoExport(mapping, form, request, response);
		}		
	    else if ("LOAD".equalsIgnoreCase(myaction)) {
			myforward = performLoad(mapping, form, request, response);
		}else if ("SECONDSTAT".equalsIgnoreCase(myaction)) {
			myforward = performSecondstat(mapping, form, request, response);
	    }else if ("STATTERM".equalsIgnoreCase(myaction)) {
			myforward = performStatTerm(mapping, form, request, response);
		} else if ("STATVIEW".equalsIgnoreCase(myaction)) {
			myforward = performStatView(mapping, form, request, response);
		}
	    else if("UPDATEVIEW".equalsIgnoreCase(myaction)){
	      myforward=performUpdateview(mapping,form,request,response);
	    }
	    else if ("ORDERTERM".equalsIgnoreCase(myaction)) {
			myforward = performOrderTerm(mapping, form, request, response);
		} else if ("ORDERVIEW".equalsIgnoreCase(myaction)) {
			myforward = performOrderView(mapping, form, request, response);
		} else if ("ORDERVIEWTOEXP".equalsIgnoreCase(myaction)) {
		    myforward = performOrderViewtoExp(mapping, form, request, response);
	    } else if ("ORDERPART".equalsIgnoreCase(myaction)) {
			myforward = performOrderPart(mapping, form, request, response);
		} else if ("SERVICETERM".equalsIgnoreCase(myaction)) {
			myforward = performServiceTerm(mapping, form, request, response);
		} else if ("SERVICEVIEW".equalsIgnoreCase(myaction)) {
			myforward = performServiceView(mapping, form, request, response);
		} else if ("CHARGETERM".equalsIgnoreCase(myaction)) {
			myforward = performChargeTerm(mapping, form, request, response);
		} else if ("CHARGEVIEW".equalsIgnoreCase(myaction)) {
			myforward = performChargeView(mapping, form, request, response);
		} else if ("OUTTERM".equalsIgnoreCase(myaction)) {
			myforward = performOutTerm(mapping, form, request, response);
		} else if ("OUTVIEW".equalsIgnoreCase(myaction)) {
			myforward = performOutView(mapping, form, request, response);
		} else if ("LOANTERM".equalsIgnoreCase(myaction)) {
			myforward = performLoanTerm(mapping, form, request, response);
		} else if ("LOANVIEW".equalsIgnoreCase(myaction)) {
			myforward = performLoanView(mapping, form, request, response);
		} else if ("REPAIRTERM".equalsIgnoreCase(myaction)) {
			myforward = performRepairTerm(mapping, form, request, response);
		} else if ("REPAIRVIEW".equalsIgnoreCase(myaction)) {
			myforward = performRepairView(mapping, form, request, response);
		} else if ("CHECKTERM".equalsIgnoreCase(myaction)) {
			myforward = performCheckTerm(mapping, form, request, response);
		} else if ("CHECKVIEW".equalsIgnoreCase(myaction)) {
			myforward = performCheckView(mapping, form, request, response);			
		} else if ("EARLYWARINGLIST".equalsIgnoreCase(myaction)) {
			myforward = performEarlyWarningList(mapping, form, request, response);
		} else if ("EARLYWARING".equalsIgnoreCase(myaction)) {
			myforward = performEarlyWarning(mapping, form, request, response);
		} else if("AMPLYVIEW".equalsIgnoreCase(myaction)){
			myforward=performAmplyview(mapping,form,request,response);
		}
//      ��־���� ����� return myforward; ֮ǰ
	    if(myforward.equals(mapping.findForward("success"))){
	    	
	        //logBO.insertlog(this.user_id, StaticVariable.OPER,  request);
	    }
	    else if(myforward.equals(mapping.findForward("failure"))){
	    	//logBO.insertlog(this.user_id, StaticVariable.ERROR,  request);
	    }
		return myforward;
	}

	public ActionForward performTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);
		TawSparepartBO tawSparepartBO = new TawSparepartBO(ds);
		TawTreeBO tawTreeBO = new TawTreeBO();
		TawStorageBO tawStorageBO=new TawStorageBO(ds);

		try {
			List supplier = tawSparepartBO.getClassMsg(6);
			List state = tawSparepartBO.getClassMsg(10);
			request.setAttribute("supplier", supplier);
			request.setAttribute("storage", STORAGE);
			request.setAttribute("state", state);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		   String storageTree = tawStorageBO.getStorageTreeStr();
		   request.setAttribute("StorageTree", storageTree);
		} catch (Exception ex) {
			generalError(request, ex);
			return mapping.findForward("failure");
		}
		return mapping.findForward("ok");
	}
	public ActionForward performFindload(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);
		TawSparepartBO tawSparepartBO = new TawSparepartBO(ds);
		TawTreeBO tawTreeBO = new TawTreeBO();
		TawStorageBO tawStorageBO=new TawStorageBO(ds);

		try {
			List supplier = tawSparepartBO.getClassMsg(6);
			List state = tawSparepartBO.getClassMsg(10);
			request.setAttribute("supplier", supplier);
			request.setAttribute("storage", STORAGE);
			request.setAttribute("state", state);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		   String storageTree = tawStorageBO.getStorageTreeStr();
		   request.setAttribute("StorageTree", storageTree);
		} catch (Exception ex) {
			generalError(request, ex);
			return mapping.findForward("failure");
		}
		return mapping.findForward("ok");
	}

  public ActionForward performUpdateterm(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
    //����
    StaticPartMethod.setReturnPath(mapping, request);
    TawSparepartBO tawSparepartBO = new TawSparepartBO(ds);
    TawTreeBO tawTreeBO = new TawTreeBO();
    TawStorageBO tawStorageBO=new TawStorageBO(ds);

    try {
      List supplier = tawSparepartBO.getClassMsg(6);
      List state = tawSparepartBO.getClassMsg(10);
      request.setAttribute("supplier", supplier);
      request.setAttribute("storage", STORAGE);
      request.setAttribute("state", state);
      String StringTree = tawTreeBO.getMyTreeStr(4);
      if (!StringTree.equals("")) {
        request.setAttribute("StringTree", StringTree);
      }
      String storageTree = tawStorageBO.getStorageTreeStr();
      request.setAttribute("StorageTree", storageTree);
    } catch (Exception ex) {
      generalError(request, ex);
      return mapping.findForward("failure");
    }
    return mapping.findForward("ok");
  }


  public ActionForward performUpdatepage(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
      // TawSparepartForm form=(TawSparepartForm)actionForm;
      TawSparepartBO bo=new TawSparepartBO(ds);
      try{
          List supplier=bo.getClassMsg(6);
          request.setAttribute("supplier",supplier);

          String id=request.getParameter("id");
          String sql=" where id="+id;
          List sparepart=bo.getSparepart(sql);
          request.setAttribute("sparepart",sparepart);
      }
      catch(Exception e){
          generalError(request,e);
          return mapping.findForward("failure");
      }
      return mapping.findForward("ok");
  }

  public ActionForward performSerialno(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
    TawSparepartForm form=(TawSparepartForm)actionForm;
    TawPart tawPart=new TawPart();
    List sparepart=new ArrayList();
    sparepart.add(tawPart);
    request.setAttribute("sparepart",sparepart);
    return mapping.findForward("ok");
  }

  public ActionForward performSerialnoEnd(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
    TawSparepartForm form=(TawSparepartForm)actionForm;
    TawSparepartBO bo=new TawSparepartBO(ds);
    //String storageid = (String)request.getSession().getAttribute("storageid");
    if (bo.checkPart(form.getSerialno()) == true){
      bo.updatePart(form.getSerialno(),form.getBadserialno());
      return mapping.findForward("ok");
    }
    else{
      return mapping.findForward("no");
    }
  }

  public ActionForward performView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);

		try {
			//������ʾ��ѯ���
			TawPartDAO tawPartDAO = new TawPartDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if(request.getParameter("submit")!=null){
        condition = bo.getCondition(form);
        request.getSession().removeAttribute("condition");
        request.getSession().setAttribute("condition", condition);
			  //form.setSql(StaticMethod.strFromBeanToPage(condition));
			}
			else{
        condition=(String)request.getSession().getAttribute("condition");
        //condition=form.getSql();
			   //form.setSql(StaticMethod.strFromBeanToPage(condition));
			}
			List sparepart = tawPartDAO.getPartList(condition, offset, length);
			Integer size = new Integer(tawPartDAO
					.getSize("taw_part", condition));
			String url = request.getContextPath() + "/sparepart"
					+ mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(offset, size.intValue(),
					length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("sparepart", sparepart);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");

	}
  /**
   * ���ڵ������в�ѯ���,��Ҫҳ�����.
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performViewtoExport(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);

		try {
			//������ʾ��ѯ���
			TawPartDAO tawPartDAO = new TawPartDAO(ds);
			String condition = "";

            condition=(String)request.getSession().getAttribute("condition");

			List sparepart = tawPartDAO.getSparepart(condition);
			
			request.setAttribute("sparepart", sparepart);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");

	}  
  //add by wqw 20070705 Ϊ�˵������еİ����״̬
  public ActionForward performLoad(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);

		try {
			//������ʾ��ѯ���
			TawPartDAO tawPartDAO = new TawPartDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if(request.getParameter("submit")!=null){
      condition = bo.getCondition(form);
      request.getSession().removeAttribute("condition");
      request.getSession().setAttribute("condition", condition);
			  
			}
			else{
      condition=(String)request.getSession().getAttribute("condition");     
			}
			List vawOrderDetail = tawPartDAO.getOldPartList(condition);			
			
			request.setAttribute("vawOrderDetail", vawOrderDetail);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");

	}
  

  public ActionForward performSecondstat(ActionMapping mapping,
      ActionForm actionForm, HttpServletRequest request,
      HttpServletResponse response) {

    TawQueryForm form = (TawQueryForm) actionForm;
    TawQueryBO bo = new TawQueryBO(ds);
    TawStorageDAO storagedao = new TawStorageDAO(ds);
    TawClassMsgDAO classmsgdao = new TawClassMsgDAO(ds);

    String storage = request.getParameter("storage");
    String nettype = request.getParameter("nettype");
    String subdept = request.getParameter("subdept");
    String necode = request.getParameter("necode");
    String objecttype = request.getParameter("objecttype");
    String state = request.getParameter("state");
    try {//ת�봦�� 070308 dww
	storage = new String(storage.getBytes("ISO8859_1"),"GB2312");
    nettype = new String(nettype.getBytes("ISO8859_1"),"GB2312");
    subdept = new String(subdept.getBytes("ISO8859_1"),"GB2312");
    necode = new String(necode.getBytes("ISO8859_1"),"GB2312");
    objecttype = new String(objecttype.getBytes("ISO8859_1"),"GB2312");
    state = new String(state.getBytes("ISO8859_1"),"GB2312");
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    try {
       state = classmsgdao.getClassMsgStatid(state);
       storage = storagedao.getStorageIdByName(storage)+"";
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }

    form.setStorage(storage);
    form.setNettype(nettype);
    form.setSubdept(subdept);
    form.setNecode(necode);
    form.setObjecttype(objecttype);
    form.setState(state);

    try {
      //������ʾ��ѯ���
      TawPartDAO tawPartDAO = new TawPartDAO(ds);
      int offset;
      int length = PAGE_LENGTH;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      } else {
        offset = Integer.parseInt(pageOffset);
      }
      String condition = "";
      condition = bo.getCondition(form);
      System.out.println(condition);
      //request.getSession().removeAttribute("condition");
      //request.getSession().setAttribute("condition", condition);
      List sparepart = tawPartDAO.getPartList(condition, offset, length);
      Integer size = new Integer(tawPartDAO
          .getSize("taw_part", condition));
      String url = request.getContextPath() + "/sparepart"
          + mapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size.intValue(),
          length, url);
      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("sparepart", sparepart);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }
    return mapping.findForward("ok");
  }


	public ActionForward performStatTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);
		TawTreeBO tawTreeBO = new TawTreeBO();
		TawSparepartBO tawSparepartBO = new TawSparepartBO(ds);
		TawQueryBO tawQueryBO = new TawQueryBO(ds);
		try {
			List state = tawSparepartBO.getClassMsg(10);

			request.setAttribute("storage", STORAGE);
			request.setAttribute("state", state);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performStatView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		try {
			//������ʾ��ѯ���
			TawQueryDAO dao = new TawQueryDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("statterm") != null) {
        String storageList = "";
        for(int i=0;i<STORAGE.size();i++){
          TawStorage tawStorage=(TawStorage)STORAGE.get(i);
          storageList = storageList + "," + Integer.toString(tawStorage.getId());
        }
        if(storageList!=null&&storageList.length()>1){
        	storageList = storageList.substring(1,storageList.length());
        }
        form.setStorageList(storageList);

				condition = bo.getCondition(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List sparepart = dao.getStatPart(condition, offset, length);
			Integer size = new Integer(dao.getStatPartCount(condition));

			request.setAttribute("sparepart", sparepart);
			if (sparepart.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("sparepart", sparepart);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

  public ActionForward performUpdateview(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){

    TawQueryForm form = (TawQueryForm) actionForm;
    TawQueryBO bo = new TawQueryBO(ds);

    try {
      //������ʾ��ѯ���
      TawPartDAO tawPartDAO = new TawPartDAO(ds);
      int offset;
      int length = PAGE_LENGTH;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      } else {
        offset = Integer.parseInt(pageOffset);
      }
      String condition = "";
      if(request.getParameter("submit")!=null){
        condition = bo.getCondition(form);
        request.getSession().removeAttribute("condition");
        request.getSession().setAttribute("condition", condition);
        //form.setSql(StaticMethod.strFromBeanToPage(condition));
      }
      else{
        condition=(String)request.getSession().getAttribute("condition");
        //condition=form.getSql();
         //form.setSql(StaticMethod.strFromBeanToPage(condition));
      }
      List sparepart = tawPartDAO.getPartList(condition, offset, length);
      Integer size = new Integer(tawPartDAO
          .getSize("taw_part", condition));
      String url = request.getContextPath() + "/sparepart"
          + mapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size.intValue(),
          length, url);
      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("sparepart", sparepart);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }

    return mapping.findForward("ok");

  }

  public ActionForward performOrderTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);
		TawTreeBO tawTreeBO = new TawTreeBO();
		try {
			request.setAttribute("storage", STORAGE);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performOrderView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		String Type = form.getType();
		try {
			//������ʾ��ѯ���
			TawOrderDetailBO orderDetailBo = new TawOrderDetailBO(ds);
      TawOrderDetailDAO orderDetailDao = new TawOrderDetailDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("order") != null) {
				condition = bo.getOrderDetailSql(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List tawOrderDetail = orderDetailBo.getTawOrderDetail(condition, offset, length);
			Integer size = new Integer(orderDetailDao.getSize("vaw_sp_order_detail", condition));

			if (tawOrderDetail.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("tawOrder", tawOrderDetail);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

	}
	/**
	 * ����ͳ�ƽ��ĵ���.
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward performOrderViewtoExp(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		String Type = form.getType();
		try {
			//������ʾ��ѯ���
			TawOrderDetailBO orderDetailBo = new TawOrderDetailBO(ds);
      TawOrderDetailDAO orderDetailDao = new TawOrderDetailDAO(ds);

			String condition = "";

				condition = (String) request.getSession().getAttribute(
						"condition");

			List tawOrderDetail = orderDetailBo.getTawOrderDetail(condition);

				request.setAttribute("tawOrder", tawOrderDetail);
				return mapping.findForward("ok");
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

	}
	public ActionForward performOrderPart(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawOrderForm form = (TawOrderForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		//���orderId����δ����ı�����Ϣ
		try {
			List sparepart = bo.getSparepart(form.getId());
			if (sparepart.size() != 0) {
				request.setAttribute("sparepart", sparepart);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

	}

	public ActionForward performServiceTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);

		TawTreeBO tawTreeBO = new TawTreeBO();
		try {
			request.setAttribute("storage", STORAGE);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performServiceView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		try {
			//������ʾ��ѯ���
			TawQueryDAO dao = new TawQueryDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("service") != null) {
				condition = bo.getCondition(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List sparepart = dao.getServicePart(condition, offset, length);
			Integer size = new Integer(dao.getServicePartCount(condition));

			request.setAttribute("sparepart", sparepart);
			if (sparepart.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("sparepart", sparepart);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	public ActionForward performChargeTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);

		TawTreeBO tawTreeBO = new TawTreeBO();
		TawSparepartBO tawSparepartBO = new TawSparepartBO(ds);
		TawQueryBO tawQueryBO = new TawQueryBO(ds);
		try {
			List supplier = tawSparepartBO.getClassMsg(6);

			request.setAttribute("supplier", supplier);
			request.setAttribute("storage", STORAGE);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performChargeView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		try {
			//������ʾ��ѯ���
			TawQueryDAO dao = new TawQueryDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("service") != null) {
				condition = bo.getCondition(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List sparepart = dao.getChargePart(condition, offset, length);
			Integer size = new Integer(dao.getChargePartCount(condition));

			if (sparepart.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("sparepart", sparepart);
				String rate = bo.getSumCharge(sparepart);
				request.setAttribute("rate", rate);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	public ActionForward performOutTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);

		TawTreeBO tawTreeBO = new TawTreeBO();
		try {
			request.setAttribute("storage", STORAGE);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performOutView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		try {
			//������ʾ��ѯ���
			TawQueryDAO dao = new TawQueryDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("service") != null) {
				condition = bo.getCondition(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List sparepart = dao.getOutPart(condition, offset, length);
			Integer size = new Integer(dao.getOutPartCount(condition));

			request.setAttribute("sparepart", sparepart);
			if (sparepart.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("sparepart", sparepart);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	public ActionForward performLoanTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);

		TawTreeBO tawTreeBO = new TawTreeBO();
		try {
			request.setAttribute("storage", STORAGE);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performLoanView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		try {
			//������ʾ��ѯ���
			TawQueryDAO dao = new TawQueryDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("service") != null) {
				condition = bo.getCondition(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List sparepart = dao.getLoanNum(condition, offset, length);
			Integer size = new Integer(dao.getLoanNumCount(condition));

			request.setAttribute("sparepart", sparepart);
			if (sparepart.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("sparepart", sparepart);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	public ActionForward performRepairTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);

		TawTreeBO tawTreeBO = new TawTreeBO();
		try {
			request.setAttribute("storage", STORAGE);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performRepairView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		try {
			//������ʾ��ѯ���
			TawQueryDAO dao = new TawQueryDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("service") != null) {
				condition = bo.getCondition(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List sparepart = dao.getRepairNum(condition, offset, length);
			Integer size = new Integer(dao.getRepairNumCount(condition));

			request.setAttribute("sparepart", sparepart);
			if (sparepart.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("sparepart", sparepart);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}
	public ActionForward performCheckTerm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//����
		StaticPartMethod.setReturnPath(mapping, request);

		TawTreeBO tawTreeBO = new TawTreeBO();
		try {
			request.setAttribute("storage", STORAGE);
			String StringTree = tawTreeBO.getMyTreeStr(4);
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}

		return mapping.findForward("ok");
	}

	public ActionForward performCheckView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryForm form = (TawQueryForm) actionForm;
		TawQueryBO bo = new TawQueryBO(ds);
		try {
			//������ʾ��ѯ���
			TawQueryDAO dao = new TawQueryDAO(ds);
			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String condition = "";
			if (request.getParameter("service") != null) {
				condition = bo.getCondition(form);
				request.getSession().removeAttribute("condition");
				request.getSession().setAttribute("condition", condition);
			} else {
				condition = (String) request.getSession().getAttribute(
						"condition");
			}

			List sparepart = dao.getCheckNum(condition, offset, length);
			Integer size = new Integer(dao.getRepairNumCount(condition));

			request.setAttribute("sparepart", sparepart);
			if (sparepart.size() != 0) {
				String url = request.getContextPath() + "/sparepart"
						+ mapping.getPath() + ".do";
				String pagerHeader = Pager.generate(offset, size.intValue(),
						length, url);
				request.setAttribute("pagerHeader", pagerHeader);
				request.setAttribute("sparepart", sparepart);
				return mapping.findForward("ok");
			} else {
				request.setAttribute("msg", StaticPart.NO_DATA);
				return mapping.findForward("noData");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}	

  public ActionForward performAmplyview(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
		TawSparepartBO bo=new TawSparepartBO(ds);
		TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
		try{
		    String id=request.getParameter("id");
		    String sql=" where id="+id;
		    List sparepart=bo.getSparepart(sql);
		    request.setAttribute("sparepart",sparepart);
		    List order=detailBo.getTawOrderDetailBySparepartId(id);
		    request.setAttribute("order", order);
		}
		catch(Exception e){
		    generalError(request,e);
		    return mapping.findForward("failure");
		}
		return mapping.findForward("ok");
  }


	public ActionForward performEarlyWarning(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawQueryBO bo = new TawQueryBO(ds);
		List list = null;
		try {
			list = bo.getEarlyWarning();
		} catch(Exception e) {}
		request.setAttribute("LIST",list);
		EarlyWarning ew = null;
		if(list!=null){
			for(int i = 0; i < list.size(); i++) {
				ew = (EarlyWarning)list.get(i);
				request.getSession().removeAttribute(ew.getId()+"EARLYWARNING1");
				request.getSession().removeAttribute(ew.getId()+"EARLYWARNING2");
				request.getSession().removeAttribute(ew.getId()+"EARLYWARNING3");
				request.getSession().removeAttribute(ew.getId()+"EARLYWARNING4");
				request.getSession().removeAttribute(ew.getId()+"EARLYWARNING5");
				request.getSession().setAttribute(ew.getId()+"EARLYWARNING1",ew.getList1());
				request.getSession().setAttribute(ew.getId()+"EARLYWARNING2",ew.getList2());
				request.getSession().setAttribute(ew.getId()+"EARLYWARNING3",ew.getList3());
				request.getSession().setAttribute(ew.getId()+"EARLYWARNING4",ew.getList4());
				request.getSession().setAttribute(ew.getId()+"EARLYWARNING5",ew.getList5());
			}
		}
		return mapping.findForward("ok");
	}

	public ActionForward performEarlyWarningList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		EarlyWarningForm form = (EarlyWarningForm)actionForm;
		List list = null;
		String id = "";
		String type = "";
		String forward = "";
	    try {
			  id = (String)form.getId();
			  type = (String)form.getType();
			  if(!"".equals(type) && !"".equals(id)) {
			  	list = (List)request.getSession().getAttribute(id+"EARLYWARNING"+type);
			  	if(type.equals("3") || type.equals("4")) {
			  		forward = "storage";
			  		request.setAttribute("storage",list);
			  	} else {
			  		forward = "sparepart";
			  		request.setAttribute("sparepart",list);
			  		request.setAttribute("pagerHeader","");
			  	}
			  }
	    } catch (Exception e) {
	      	  e.printStackTrace();
	    }

		return mapping.findForward(forward);
	}
}
