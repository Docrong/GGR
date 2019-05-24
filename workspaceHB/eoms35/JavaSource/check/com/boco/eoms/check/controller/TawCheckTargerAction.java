package com.boco.eoms.check.controller;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.util.TawCheckSCOREMethod;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.qo.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import org.apache.struts.util.*;
/**
 * @see
 * <p>�����������ڲ��뿼��ָ�����ݿ���̨</p>
 * @author ��Ң
 * @version 3.5
 */
public class TawCheckTargerAction extends Action
{
	 private static int PAGE_LENGTH = 10;
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
		              HttpServletRequest request, HttpServletResponse response)	
	 {
		 ActionForward myforward=null;
		 String myaction=mapping.getParameter();
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
		      } 
		      else if ("EDIT".equalsIgnoreCase(myaction)) {
		        myforward = performEdit(mapping, form, request, response);
		      } 
		      else if ("SAVE".equalsIgnoreCase(myaction)) {
		        myforward = performSave(mapping, form, request, response);
		      }
		      else if ("LIST".equalsIgnoreCase(myaction)) {
		        myforward = performList(mapping, form, request, response);
		      }
		      else if ("NEW".equalsIgnoreCase(myaction)) {
		        myforward = performNew(mapping, form, request, response);
		      }
		      else if("DEL".equalsIgnoreCase(myaction))
		      {
		    	  myforward = performDel(mapping, form, request, response);
		      }
		      else if("QUERY".equalsIgnoreCase(myaction))
		      {
		    	  myforward = performQuery(mapping, form, request, response);
		      }
		      else if("QUERYDO".equalsIgnoreCase(myaction))
		      {
		    	  myforward = performList(mapping, form, request, response);
		      }
		      else if("TRASH".equalsIgnoreCase(myaction))
		      {
		    	  myforward = performTrash(mapping, form, request, response);
		      }
		      else if("VIEW".equalsIgnoreCase(myaction))
		      {
		    	  myforward = performView(mapping, form, request, response);
		      }
		      else {
		        myforward = mapping.findForward("failure");
		      }		 
		 return myforward;
	 }
/*
 * ����һ��ָ�������Ϣ
 */
	 public ActionForward performNew(ActionMapping mapping, ActionForm form,
			                         HttpServletRequest request, 
			                         HttpServletResponse response )
	 {
		 
		 return mapping.findForward("success");
	 }
/*
 * �����ָ����Ϣ
 */
	 public ActionForward performSave(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response )
    {
		     try
		     {
		     TawCheckTargerBO tawCheckTargerBO=new TawCheckTargerBO(); 
		     tawCheckTargerBO.setTargerForm(((DynaActionForm)form).getMap());
		     tawCheckTargerBO.SaveTarger();
		     }catch(Exception e)
		     {
		    	 e.printStackTrace();
		     }
             return mapping.findForward("success");
    }
/*
 * �༭��ָ����Ϣ
 */
	 public ActionForward performEdit(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response )
    {
		 	 try
		 	 {
			 		String targer_id=request.getParameter("id");
			 		TawCheckTargerBO tawCheckTargerBO=new TawCheckTargerBO();
			 		TawCheckTargerVO tawCheckTargerVO=tawCheckTargerBO.getTargerVO(targer_id);
			 		request.setAttribute("TawCheckTargerVO", tawCheckTargerVO);		 		 
		 	 }catch(Exception e)
		 	 {
		 		 e.printStackTrace();
		 	 }
             return mapping.findForward("success");
    }
/*
 * ���¸�ָ��
 */
	 public ActionForward performTrash(ActionMapping mapping, ActionForm actionForm,
             HttpServletRequest request, 
             HttpServletResponse response )
    {
		 	 try
		 	 {
		 		 String targer_id=request.getParameter("targer_id");
		 		 String targer_per=request.getParameter("targer_per");
		 		 Map map=((DynaActionForm)actionForm).getMap();
		 		 map.put("targer_id", targer_id);
		 		 map.put("targer_deleted","0");
		 		 map.put("targer_per",targer_per);
		 		 TawCheckTargerBO tawCheckTargerBO=new TawCheckTargerBO();
		 		 tawCheckTargerBO.setTargerForm(map);
		 		 tawCheckTargerBO.updateTarger();
             }catch(Exception e)
		 	 {e.printStackTrace();}
             return mapping.findForward("success");
    }
/*
 * ��ѯָ��
 */
	 public ActionForward performQuery(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response )
    {

             return mapping.findForward("success");

    }
/*
 * ����ָ���б�
 */
	 public ActionForward performList(ActionMapping mapping, ActionForm actionForm,
             HttpServletRequest request, 
             HttpServletResponse response )
    {
		 	try
		 	{
		 	    int length = PAGE_LENGTH;
		 	    int offset = StaticMethod.nullObject2int(request.getParameter("pager.offset"), 0);
		 	    int size = StaticMethod.nullObject2int(request.getParameter("pager.size"), 0);
		 	    int[] pagePra = {offset, length, size};
		 		ArrayList tarList=new ArrayList();
		 		TawCheckTargerBO tawTargerBO=new TawCheckTargerBO();
		 		tawTargerBO.setTargerForm(((DynaActionForm)actionForm).getMap());
		 		tarList=(ArrayList)tawTargerBO.getList(pagePra);
		 		request.setAttribute("tarList", tarList);
		 	    String url = "/check" + mapping.getPath() + ".do";
		 	    String pagerHeader = Pager.generate(pagePra[0], pagePra[2], pagePra[1], url);
		 	    request.setAttribute("pagerHeader", pagerHeader);
		 	}catch(Exception e)
		 	{
		 		e.printStackTrace();
		 	}
             return mapping.findForward("success");
    }
/*
 * ɾ���ָ��
 */
	 public ActionForward performDel(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response )
    {
		 	 try
		 	 {
		 		 String targer_id=request.getParameter("id");
		 		 TawCheckTargerBO tawCheckTargerBO=new TawCheckTargerBO();
		 		 tawCheckTargerBO.delTawCheckTarger(targer_id);
		 	 }catch(Exception e)
		 	 {e.printStackTrace();}
             return mapping.findForward("success");
    }
//��ʾ����
	 public ActionForward performView(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response )
    {
		 	try
		 	{
		 		String targer_id=request.getParameter("id");
		 		TawCheckTargerBO tawCheckTargerBO=new TawCheckTargerBO();
		 		TawCheckTargerVO tawCheckTargerVO=tawCheckTargerBO.getTargerVO(targer_id);
		 		request.setAttribute("TawCheckTargerVO", tawCheckTargerVO);
		 	}catch(Exception e)
		 	{e.printStackTrace();}
             return mapping.findForward("success");
    }
}