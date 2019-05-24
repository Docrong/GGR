package com.boco.eoms.infmanage.controller;

import java.util.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.bo.*;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;
//import com.boco.eoms.jbzl.bo.TawDeptBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import org.apache.struts.util.LabelValueBean;

import org.apache.struts.action.Action;

public class CollsheetAction extends Action{
  private static int PAGE_LENGTH = 10;
 private String user_id = "";
 private String userName="";
 private int deptId;
 private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
     ConnectionPool.getInstance();

 public ActionForward execute(ActionMapping actionMapping,
                                  ActionForm actionForm
                                  , HttpServletRequest request,
                                  HttpServletResponse response)
     {
         ActionForward myforward = null;
         String myaction = actionMapping.getParameter();

         //session��ʱ����
         try
         {
     	    //	edit by wangheqi 2.7 to 3.5
    	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
    	      request.getSession().getAttribute("sessionform");
    	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
    	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    	 	 //edit end

             if (saveSessionBeanForm == null)
                 return actionMapping.findForward("timeout");
             user_id = saveSessionBeanForm.getUserid();
             userName = saveSessionBeanForm.getUsername();
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }

         if ("".equalsIgnoreCase(myaction))
         {
             myforward = actionMapping.findForward("failure");
         }

         if ("ADD".equalsIgnoreCase(myaction))
         {
             myforward = performAdd(actionMapping, actionForm, request, response);
         }

         if ("SAVE".equalsIgnoreCase(myaction))
         {
             myforward = performSave(actionMapping, actionForm, request,
                                     response);
         }

         if ("QUERY".equalsIgnoreCase(myaction))
        {
            myforward = performQuery(actionMapping, actionForm, request,
                                     response);
        }

         if ("QUERYDONE".equalsIgnoreCase(myaction))
         {
             myforward = performQueryDone(actionMapping, actionForm, request,
                                      response);
         }

         if ("LIST".equalsIgnoreCase(myaction))
         {
             myforward = performList(actionMapping, actionForm, request,
                                     response);
         }

         if ("VIEW".equalsIgnoreCase(myaction))
         {
             myforward = performView(actionMapping, actionForm, request,
                                     response);
         }

         if ("UPDATE".equalsIgnoreCase(myaction))
         {
             myforward = performUpdate(actionMapping, actionForm, request,
                                       response);
         }

         if ("UPDATEDONE".equalsIgnoreCase(myaction))
         {
             myforward = performUpdatedone(actionMapping, actionForm, request,
                                           response);
         }

         if ("DEL".equalsIgnoreCase(myaction))
         {
             myforward = performDel(actionMapping, actionForm, request,
                                    response);
         }

         if ("DELDONE".equalsIgnoreCase(myaction))
         {
             myforward = performDeldone(actionMapping, actionForm, request,
                                        response);
         }

         return myforward;
     }
     /**
        * ��ʾ���ҳ��
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performAdd(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
       {
           CollsheetForm form = (CollsheetForm) actionForm;
   	    //	edit by wangheqi 2.7 to 3.5
  	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
  	      request.getSession().getAttribute("sessionform");
  	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
  	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
  	 	 //edit end

           if (saveSessionBeanForm == null)
           {
               return actionMapping.findForward("timeout");
           }
           try
           {
             request.setAttribute("userName",userName);
           form.setAchieve_person(user_id);
           form.setAchieve_time(com.boco.eoms.common.util.StaticMethod.getLocalString());
           form.setFault_anolyize("");
           form.setFault_description("");
           form.setkey_word("");
           form.setRegion_code("");
           form.setResovl_process("");

           }
           catch (Exception e)
           {
               e.printStackTrace();
               actionMapping.findForward("failure");
           }
           finally
           {

           }
           return actionMapping.findForward("success");

       }

       /**
        * ִ����ӵĲ���
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performSave(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
       {
           CollsheetDAO collsheetDAO =new CollsheetDAO(ds);
           CollsheetForm form = (CollsheetForm) actionForm;
           Collsheet collsheet = new Collsheet();
           int worksheet_type = StaticMethod.null2int(request.getParameter("worksheet_type"));
           form.setWorksheet_type(worksheet_type);
           try
           {
               HttpSession session = request.getSession();
       	    //	edit by wangheqi 2.7 to 3.5
      	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
      	      request.getSession().getAttribute("sessionform");
      	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
      	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
      	 	 //edit end

               if (saveSessionBeanForm == null)
               {
                   return actionMapping.findForward("timeout");
               }

               org.apache.commons.beanutils.BeanUtils.populate(collsheet,
                   org.apache.commons.beanutils.BeanUtils.describe(form));

               collsheetDAO.insert(collsheet);
           }
           catch (Exception e)
           {
               e.printStackTrace();
               actionMapping.findForward("failure");
           }
           finally
           {

           }
           return actionMapping.findForward("success");
       }

       /**
        * ��ʾ��ѯ��ҳ��
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performQuery(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response)
       {
         CollsheetForm form = (CollsheetForm) actionForm;
 	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }
            try
            {
            form.setAchieve_person(userName);
            form.setAchieve_time("");
            form.setFault_anolyize("");
            form.setFault_description("");
            form.setkey_word("");
            form.setRegion_code("");
            form.setResovl_process("");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                actionMapping.findForward("failure");
            }
            finally
            {

            }
            return actionMapping.findForward("success");

       }



       public ActionForward performQueryDone(ActionMapping actionMapping,
                                                ActionForm actionForm,
                                                HttpServletRequest request,
                                                HttpServletResponse response)
              {
                CollsheetForm form = (CollsheetForm) actionForm;

               try
               {
                   int length = PAGE_LENGTH;
                   int offset;
                   String pageOffset = request.getParameter("pager.offset");
                   if (pageOffset == null || pageOffset.equals(""))
                   {
                       offset = 0;
                   }
                   else
                   {
                       offset = Integer.parseInt(pageOffset);
                   }

                   // ��ѯ
                   CollsheetBO collsheetBO = new CollsheetBO(ds);
                   ArrayList list = (ArrayList)collsheetBO.getQueryList(form,offset,length);

                   int size = collsheetBO.getSize(form);
                   String url = request.getContextPath() + "/infmanage" +
                       actionMapping.getPath() + ".do";
                   String pagerHeader = Pager.generate(offset, size, length, url);

                   request.setAttribute("pagerHeader", pagerHeader);
                   request.setAttribute("Collsheet_LIST", list);
               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
               finally
               {

               }
               return actionMapping.findForward("success");

              }

       /**
        * ���б����ʽ��ʾ��ѯ���
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performList(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
       {
           CollsheetForm form = (CollsheetForm) actionForm;

           try
           {
               int length = PAGE_LENGTH;
               int offset;
               String pageOffset = request.getParameter("pager.offset");
               if (pageOffset == null || pageOffset.equals(""))
               {
                   offset = 0;
               }
               else
               {
                   offset = Integer.parseInt(pageOffset);
               }

               // ��ѯ
               CollsheetBO collsheetBO = new CollsheetBO(ds);
               ArrayList list = (ArrayList)collsheetBO.getList(offset,length);

               int size = collsheetBO.getSize();
               String url = request.getContextPath() + "/infmanage" +
                   actionMapping.getPath() + ".do";
               String pagerHeader = Pager.generate(offset, size, length, url);

               request.setAttribute("pagerHeader", pagerHeader);
               request.setAttribute("Collsheet_LIST", list);
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
           finally
           {

           }
           return actionMapping.findForward("success");
       }

       /**
        * �鿴�ۺϾ����������Ϣ
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performView(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
       {
           CollsheetForm form = (CollsheetForm) actionForm;
           CollsheetDAO collsheetDAO = new CollsheetDAO(ds);
           Collsheet collsheet = new Collsheet();
           ArrayList worksheet_type=new ArrayList();
           worksheet_type.add(0,"");
           try
           {
               // �жϳ�ʱ
               HttpSession httpSession = request.getSession();
       	    //	edit by wangheqi 2.7 to 3.5
      	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
      	      request.getSession().getAttribute("sessionform");
      	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
      	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
      	 	 //edit end

               if (saveSessionBeanForm == null)
               {
                   return actionMapping.findForward("timeout");
               }

               //��
               int id = StaticMethod.null2int(request.getParameter(
                   "id"));
               collsheet = collsheetDAO.getById(id);
               org.apache.commons.beanutils.BeanUtils.populate(form,
                  org.apache.commons.beanutils.BeanUtils.describe(
                  collsheet));
                form.setLabels(collsheet.getLabels());
                form.setValues(collsheet.getValues());

           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
           finally
           {

           }
           return actionMapping.findForward("success");
       }

       /**
        * ��ʾ�޸������������Ϣҳ��
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performUpdate(ActionMapping actionMapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response)
       {
         CollsheetForm form = (CollsheetForm) actionForm;
        CollsheetDAO collsheetDAO = new CollsheetDAO(ds);
        Collsheet collsheet = new Collsheet();
        ArrayList worksheet_type=new ArrayList();
        worksheet_type.add(0,"");
        try
        {
            // �жϳ�ʱ
            HttpSession httpSession = request.getSession();
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }

            //��
            int id = StaticMethod.null2int(request.getParameter(
                "id"));
            collsheet = collsheetDAO.getById(id);
            org.apache.commons.beanutils.BeanUtils.populate(form,
               org.apache.commons.beanutils.BeanUtils.describe(
               collsheet));
             form.setLabels(collsheet.getLabels());
             form.setValues(collsheet.getValues());

           }
           catch (Exception e)
           {
               e.printStackTrace();
               actionMapping.findForward("failure");
           }
           finally
           {

           }
           return actionMapping.findForward("success");
       }

       /**
        * ִ���޸ĵĲ���
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performUpdatedone(ActionMapping actionMapping,
                                              ActionForm actionForm,
                                              HttpServletRequest request,
                                              HttpServletResponse response)
       {
         CollsheetDAO collsheetDAO =new CollsheetDAO(ds);
         CollsheetForm form = (CollsheetForm) actionForm;
         Collsheet collsheet = new Collsheet();
         int worksheet_type = StaticMethod.null2int(request.getParameter("worksheet_type"));
         form.setWorksheet_type(worksheet_type);
         try
         {
             HttpSession session = request.getSession();
     	    //	edit by wangheqi 2.7 to 3.5
    	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
    	      request.getSession().getAttribute("sessionform");
    	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
    	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    	 	 //edit end

             if (saveSessionBeanForm == null)
             {
                 return actionMapping.findForward("timeout");
             }

             org.apache.commons.beanutils.BeanUtils.populate(collsheet,
                 org.apache.commons.beanutils.BeanUtils.describe(form));

             collsheetDAO.update(collsheet);
         }
         catch (Exception e)
         {
             e.printStackTrace();
             actionMapping.findForward("failure");
         }
         finally
         {

         }
         return actionMapping.findForward("success");

       }

       /**
        * ��ʾɾ��ҳ��
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performDel(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
       {
         CollsheetForm form = (CollsheetForm) actionForm;
        CollsheetDAO collsheetDAO = new CollsheetDAO(ds);
        Collsheet collsheet = new Collsheet();
        ArrayList worksheet_type=new ArrayList();
        worksheet_type.add(0,"");
        try
        {
            // �жϳ�ʱ
            HttpSession httpSession = request.getSession();
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }

            //��
            int id = StaticMethod.null2int(request.getParameter(
                "id"));
            collsheet = collsheetDAO.getById(id);
            org.apache.commons.beanutils.BeanUtils.populate(form,
               org.apache.commons.beanutils.BeanUtils.describe(
               collsheet));
             form.setLabels(collsheet.getLabels());
             form.setValues(collsheet.getValues());

           }
           catch (Exception e)
           {
               e.printStackTrace();
               actionMapping.findForward("failure");
           }
           finally
           {

           }
           return actionMapping.findForward("success");
       }

       /**
        * ִ��ɾ�����
        * @param actionMapping
        * @param actionForm
        * @param request
        * @param response
        * @return
        */
       public ActionForward performDeldone(ActionMapping actionMapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response)
       {
         CollsheetDAO collsheetDAO =new CollsheetDAO(ds);
       CollsheetForm form = (CollsheetForm) actionForm;
       Collsheet collsheet = new Collsheet();
       try
       {
           HttpSession session = request.getSession();
   	    //	edit by wangheqi 2.7 to 3.5
  	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
  	      request.getSession().getAttribute("sessionform");
  	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
  	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
  	 	 //edit end

           if (saveSessionBeanForm == null)
           {
               return actionMapping.findForward("timeout");
           }

           org.apache.commons.beanutils.BeanUtils.populate(collsheet,
               org.apache.commons.beanutils.BeanUtils.describe(form));

           collsheetDAO.del(collsheet);
       }
       catch (Exception e)
       {
           e.printStackTrace();
           actionMapping.findForward("failure");
       }
       finally
       {

       }
       return actionMapping.findForward("success");
       }
   }
