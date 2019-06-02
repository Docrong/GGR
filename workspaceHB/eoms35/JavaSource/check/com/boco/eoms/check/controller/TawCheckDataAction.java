package com.boco.eoms.check.controller;

import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.util.TawCheckSCOREMethod;
import com.boco.eoms.check.vo.*;
//import com.boco.eoms.sparepart.controller.UpLoadForm;
//import com.boco.eoms.sparepart.util.UpLoad;
import org.apache.struts.action.*;

import javax.servlet.http.*;
import javax.servlet.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.*;

/**
 * @see
 * <p>
 * �����������ڲ��뿼��ָ�����ݿ���̨
 * </p>
 * @author ��Ң
 * @version 3.5
 */
public class TawCheckDataAction extends Action {
	private static int PAGE_LENGTH = 10;
	 private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.
     ConnectionPool.getInstance();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();
		if (isCancelled(request)) {
			return mapping.findForward("cancel");
		}
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
	    if(!TawCheckSCOREMethod.checkUserRoles(saveSessionBeanForm.getUserid())){
	    	return mapping.findForward("nopriv");
	    }
		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("NEW".equalsIgnoreCase(myaction)) {
			myforward = performNew(mapping, form, request, response);
		} else if ("DEL".equalsIgnoreCase(myaction)) {
			myforward = performDel(mapping, form, request, response);
		} else if ("QUERY".equalsIgnoreCase(myaction)) {
			myforward = performQuery(mapping, form, request, response);
		} else if ("QUERYDO".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("TRASH".equalsIgnoreCase(myaction)) {
			myforward = performTrash(mapping, form, request, response);
		} else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} else if ("BATCHDATA".equalsIgnoreCase(myaction)) {
//			myforward = performBatchData(mapping, form, request, response);
		} else if("TOEXPORT".equalsIgnoreCase(myaction)){
		    myforward=performToexport(mapping,form,request,response);
		} else if ("BATCHOVER".equalsIgnoreCase(myaction)) {
//			myforward = performBatchOver(mapping, form, request, response);	
		} else if ("BATCHSELECT".equalsIgnoreCase(myaction)) {
//			myforward = performBatchSelect(mapping, form, request, response);	
		} else if ("LOADDATA".equalsIgnoreCase(myaction)) {
			myforward = performLoadData(mapping, form, request, response);
		} else if ("PUBLISHNEW".equalsIgnoreCase(myaction)) {
			myforward = performPublishNew(mapping, form, request, response);
		}else if ("PUBLISHADD".equalsIgnoreCase(myaction)) {
			myforward = performPublishAdd(mapping, form, request, response);
		} else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}

	/*
	 * ����һ��ָ�������Ϣ
	 */
	public ActionForward performNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("success");
	}

	/*
	 * �����ָ����Ϣ
	 */
	public ActionForward performSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			TawCheckTargerBO tawCheckTargerBO = new TawCheckTargerBO();
			tawCheckTargerBO.setTargerForm(((DynaActionForm) form).getMap());
			tawCheckTargerBO.SaveTarger();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * �༭��ָ����Ϣ
	 */
	public ActionForward performEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String targer_id = request.getParameter("id");
			TawCheckTargerBO tawCheckTargerBO = new TawCheckTargerBO();
			TawCheckTargerVO tawCheckTargerVO = tawCheckTargerBO
					.getTargerVO(targer_id);
			request.setAttribute("TawCheckTargerVO", tawCheckTargerVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * ���¸�ָ��
	 */
	public ActionForward performTrash(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String targer_id = request.getParameter("targer_id");
			String targer_per = request.getParameter("targer_per");
			Map map = ((DynaActionForm) actionForm).getMap();
			map.put("targer_id", targer_id);
			map.put("targer_deleted", "0");
			map.put("targer_per", targer_per);
			TawCheckTargerBO tawCheckTargerBO = new TawCheckTargerBO();
			tawCheckTargerBO.setTargerForm(map);
			tawCheckTargerBO.updateTarger();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * ��ѯָ��
	 */
	public ActionForward performQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("success");

	}

	/*
	 * ����ָ���б�
	 */
	public ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int length = PAGE_LENGTH;
			int offset = StaticMethod.nullObject2int(request
					.getParameter("pager.offset"), 0);
			int size = StaticMethod.nullObject2int(request
					.getParameter("pager.size"), 0);
			int[] pagePra = { offset, length, size };
			ArrayList tarList = new ArrayList();
			TawCheckTargerBO tawTargerBO = new TawCheckTargerBO();
			tawTargerBO.setTargerForm(((DynaActionForm) actionForm).getMap());
			tarList = (ArrayList) tawTargerBO.getList(pagePra);
			request.setAttribute("tarList", tarList);
			String url = "/check" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2],
					pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * ɾ���ָ��
	 */
	public ActionForward performDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String targer_id = request.getParameter("id");
			TawCheckTargerBO tawCheckTargerBO = new TawCheckTargerBO();
			tawCheckTargerBO.delTawCheckTarger(targer_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	// ��ʾ����
	public ActionForward performView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String targer_id = request.getParameter("id");
			TawCheckTargerBO tawCheckTargerBO = new TawCheckTargerBO();
			TawCheckTargerVO tawCheckTargerVO = tawCheckTargerBO
					.getTargerVO(targer_id);
			request.setAttribute("TawCheckTargerVO", tawCheckTargerVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

//	public ActionForward performBatchData(ActionMapping mapping,
//			ActionForm actionForm, HttpServletRequest request,
//			HttpServletResponse response) {
//		UpLoadForm theForm = (UpLoadForm) actionForm;
//		String strLocal = StaticMethod.getLocalString();
//		String sYear = strLocal.substring(0, 4);
//		int iMonth = StaticMethod.null2int(strLocal.substring(5, 7));
//		String zhuanye=StaticMethod.null2String(request.getParameter("zhuanye"));
//		request.setAttribute("sYear", sYear);
//		request.setAttribute("sMonth", String.valueOf(iMonth));
//		request.setAttribute("zhuanye", zhuanye);
//		
//		return mapping.findForward("ok");
//	}
//
//	public ActionForward performBatchOver(ActionMapping mapping,
//			ActionForm actionForm, HttpServletRequest request,
//			HttpServletResponse response) {
//		UpLoadForm theForm = (UpLoadForm) actionForm;
//		List errorList = null;
//		String batchYear=theForm.getScoreYear();
//		String batchMonth=theForm.getScoreMonth();
//		String pathname=request.getSession().getAttribute("EXCELPATHNAME").toString();
//		String filename=request.getSession().getAttribute("EXCELFILENAME").toString();
//		String sheetNames[]=theForm.getSheetNames();
//		String zhuanye="";
////		String zhuanye=theForm.getZhuanye();
//		try {
//			TawCheckDataBatchBO bo = new TawCheckDataBatchBO(pathname, filename,zhuanye);
//			bo.setYearMonth(batchYear, batchMonth);
//			boolean flag = bo.importData(sheetNames);
//			if (flag) {
//				return mapping.findForward("ok");
//			} else {
//				return mapping.findForward("importno");
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return mapping.findForward("no");
//		}
//	}
//	public ActionForward performBatchSelect(ActionMapping mapping,
//			ActionForm actionForm, HttpServletRequest request,
//			HttpServletResponse response) {
//		UpLoadForm theForm = (UpLoadForm) actionForm;
//		List errorList = null;
//		FormFile file = theForm.getTheFile(); // ȡ���ϴ����ļ�
//		String filePath = request.getRealPath("/sparepart/loadFile"); // ȡ��ǰϵͳ·��
//		UpLoad.UpLoadFile(file, filePath);
//		request.getSession().setAttribute("EXCELPATHNAME", filePath + "/"
//					+ file.getFileName());
//		request.getSession().setAttribute("EXCELFILENAME", file.getFileName());
//		String batchYear=request.getParameter("scoreYear");
//		String batchMonth=request.getParameter("scoreMonth");
//		String zhuanye=StaticMethod.null2String(request.getParameter("zhuanye"));
//		theForm.setScoreYear(batchYear);
//		theForm.setScoreMonth(batchMonth);
//		try {
//			TawCheckDataBatchBO bo = new TawCheckDataBatchBO(filePath + "/"
//					+ file.getFileName(), file.getFileName(),zhuanye);
//			bo.setYearMonth(batchYear, batchMonth);
//			String names[] = bo.getExcSheetNames(zhuanye);
//			request.setAttribute("zhuanye", zhuanye);
//			if (names!=null&&names.length>0) {
//				request.setAttribute("sheetnames", names);
//				return mapping.findForward("ok");
//			} else {
//				request.setAttribute("zhuanye", zhuanye);
//				return mapping.findForward("importno");
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return mapping.findForward("no");
//		}
//	}

	public ActionForward performToexport(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String batchYear=request.getParameter("scoreYear");
		String batchMonth=request.getParameter("scoreMonth");
		String zhuanye =request.getParameter("zhuanye");
		if(Integer.parseInt(batchMonth)<10){
			batchMonth="0"+batchMonth;
		}
		String url="";
		try {
			// ������ʾ��ѯ���
			TawCheckBatchDAO tawPartDAO = new TawCheckBatchDAO(ds);
			url=tawPartDAO.getPathName(batchYear, batchMonth,zhuanye);
			if(url.equals("")){
				return mapping.findForward("failure");
			}
			//url="/../sparepart/loadFile/"+url;
			//request.getContextPath();
			request.setAttribute("fileName", url);
			request.setAttribute("path","loadFile");
			//request.setAttribute("sparepart", sparepart);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("ok");
	}
	/**
     * @see ���������
     */
    private void generalError(HttpServletRequest request,Exception e){
        ActionErrors aes=new ActionErrors();
        aes.add(aes.GLOBAL_ERROR,new ActionError("error.general",e.getMessage()));
        saveErrors(request,aes);
        e.printStackTrace();
    }
    
	public ActionForward performLoadData(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String year=request.getParameter("Year");
		String month=request.getParameter("Month");
		double ppp=Double.parseDouble(request.getParameter("ppp"));
		String current_time = StaticMethod.getLocalString();
		String year_next = "";

		if (year == null&&month == null){
			
			 year = current_time.substring(0, 4);
			 
			String aa = current_time.substring(5,6);
			
			if (aa.equals("0")){
				month =current_time.substring(6,7);
			} else{
				month =current_time.substring(5,7);
			}
		}
			String month_next = String.valueOf(Integer.parseInt(month) + 1);
			
			if (month.equals("12")){
				
				year_next = String.valueOf(Integer.parseInt(year) + 1);
				month_next = "1";
			} else {
				year_next = year;
			}
			
			if (Integer.parseInt(month)<10){
				
				month = "0" + month;
			}
			if (Integer.parseInt(month_next)<10){
				
				month_next = "0" + month_next;
			}
			
			String day =  current_time.substring(8, 9);
			
			String start_time = year + "-" + month + "-01 00:00:00";
			//String end_time = year_next + "-" + month_next + "-01 00:00:00";
			
			//String start_time = "2006-05-01 00:00:00";
			//String end_time = "2007-06-01 00:00:00";
		try {	
			TawCheckWanggBO TawCheckWanggBO= new TawCheckWanggBO();
		    TawCheckWanggBO.getGsmData(start_time);
		    TawCheckWanggBO.getCdmaData(start_time,ppp);
		    
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return mapping.findForward("no");
		}
		return mapping.findForward("ok");
	}
	public ActionForward performPublishNew(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String strLocal = StaticMethod.getLocalString();
		String sYear = strLocal.substring(0, 4);
		int iMonth = StaticMethod.null2int(strLocal.substring(5, 7));
		String zhuanye=StaticMethod.null2String(request.getParameter("zhuanye"));
		String scoreYear=StaticMethod.null2String(request.getParameter("scoreYear"));
		String scoreMonth=StaticMethod.null2String(request.getParameter("scoreMonth"));
		if(zhuanye==null||zhuanye.length()==0){
			zhuanye="0";
		}
		if(scoreYear.length()==0){
			scoreYear=sYear;
		}
		if(scoreMonth.length()==0){
			scoreMonth=String.valueOf(iMonth);
		}
		request.setAttribute("sYear", scoreYear);
		request.setAttribute("sMonth", scoreMonth);
		request.setAttribute("zhuanye", zhuanye);
		TawCheckPublishDAO dao= new TawCheckPublishDAO(ds);
		String values[]=dao.load(scoreYear, scoreMonth, zhuanye, TawCheckSCOREMethod.getAreaNams());
		request.setAttribute("values", values);
		return mapping.findForward("ok");
	}	
	public ActionForward performPublishAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
			String zhuanye=StaticMethod.null2String(request.getParameter("zhuanye"));
			String scoreYear=StaticMethod.null2String(request.getParameter("scoreYear"));
			String scoreMonth=StaticMethod.null2String(request.getParameter("scoreMonth"));
			String names[]=request.getParameterValues("publishName");	
			String values[]=request.getParameterValues("publishValue");	
			TawCheckPublishDAO dao= new TawCheckPublishDAO(ds);
			dao.save(scoreYear, scoreMonth, zhuanye, names, values);
		return mapping.findForward("ok");
	}	
}