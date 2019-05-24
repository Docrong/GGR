package com.boco.eoms.sparepart.controller;

import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;

//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.common.util.StaticMethod;

//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.sparepart.bo.*;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.common.util.*;
import com.jspsmart.upload.*;
import javax.servlet.*;
import java.io.*;
import org.apache.struts.upload.*;


public class TawBorrowAction  extends Action{
  public TawBorrowAction(){
    }
    public static final String noName="�ñ�����Ʋ����ڣ�";
    private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.
          ConnectionPool.getInstance();

    private static int PAGE_LENGTH=10;
    private String user_id="",user_name="";
    List STORAGE=new ArrayList();
    static{
        ResourceBundle prop=ResourceBundle.getBundle(
              "resources.application_zh_CN");
        try{
            PAGE_LENGTH=Integer.parseInt(prop.getString("list.page.length"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
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

    /**
     * @see ϵͳ��־����
     */
    private void insertLog(ActionMapping mapping,
                           HttpServletRequest request,
                           String ret,String name){
        try{
            //logBO logbo=new logBO(ds);
            if(ret.equals("success")){
                /*boolean bool=logbo.insertLogToDbPathNew(user_id,mapping.getPath(),
                      StaticVariable.OPER,
                      request.getRemoteAddr(),name);*/

            }
            else if(ret.equals("failure")){
                /*
            	boolean bool=logbo.insertLogToDbPathNew(user_id,mapping.getPath(),
                      StaticVariable.ERROR,
                      request.getRemoteAddr(),name);*/
            }
        }
        catch(Exception e){
            BocoLog.error(this,0,"ϵͳ��־����(����taw_log)����",e);
        }
    }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
      ActionForward myforward = null;
      String myaction = mapping.getParameter();
      System.out.println(myaction);

      //session��ʱ����
      try {
        request.setCharacterEncoding("GB2312");
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
            request.getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
          return mapping.findForward("timeout");
        }
        user_id = StaticMethod.null2String(saveSessionBeanForm.getUserid());
        user_name = StaticMethod.null2String(saveSessionBeanForm.getUsername());
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      /*Ȩ����֤
      try {
        TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
        if (!tawValidateBO.validPriv(user_id, mapping.getPath())) {
          return mapping.findForward("nopriv");
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      /**
       * @ ѡ��ֿ�
       */
//      if (request.getSession().getAttribute("storage") == null) {
//        //����
//        StaticPartMethod.setReturnPath(mapping, request);
//
//        ActionForward actionForward = new ActionForward(
//            "/storage/choose.do");
//        return actionForward;
//      }
      //
      if (isCancelled(request)) {
        return mapping.findForward("failure");
      }
      else if("ADD".equalsIgnoreCase(myaction)){
            myforward=performAdd(mapping,form,request,response);
          }
        else if("VIEW".equalsIgnoreCase(myaction)){
            myforward=performView(mapping,form,request,response);
        }
        return myforward;
    }
    public ActionForward performAdd(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response){

       TawBorrowForm form=(TawBorrowForm)actionForm;
       TawBorrowBO bo=new TawBorrowBO(ds);
       TawTreeBO bobo=new TawTreeBO();
       String stotageId=(String)request.getSession().getAttribute("storageid");
       form.setStorageid(stotageId);
       String parameter=request.getParameter("differ");

       try{
           //ת��
           //MyBeanUtils.copyPropertiesFromPageToDB(form);
           String msg=bo.insertPart(form);

           if(parameter.equals("1")){
               bobo=null;
               bo=null;
               request.setAttribute("msg",msg);
               return mapping.findForward("ok");
           }
           else{
               List supplier=bo.getClassMsg(6);
               request.setAttribute("supplier",supplier);
               request.setAttribute("storageName",stotageId);
               //ƴ�ַ�
               String StringTree=bobo.getMyTreeStr(4);
               if(!StringTree.equals("")){
                   request.setAttribute("StringTree",StringTree);
               }

               request.setAttribute("ename",
                                    StaticMethod.strFromBeanToPage(form.
                     getEname()));
               request.setAttribute("productcode",
                                    StaticMethod.
                                    strFromBeanToPage(form.getProductcode()));
               request.setAttribute("managecode",
                                    StaticMethod.
                                    strFromBeanToPage(form.getManagecode()));
               request.setAttribute("serialno",
                                    StaticMethod.strFromBeanToPage(form.
                     getSerialno()));
               request.setAttribute("operator",
                                    StaticMethod.strFromBeanToPage(form.
                     getOperator()));
               request.setAttribute("position",
                                    StaticMethod.strFromBeanToPage(form.
                     getPosition()));
                 request.setAttribute("contract",
                                      StaticMethod.strFromBeanToPage(form.getContract()));
               request.setAttribute("note",
                                    StaticMethod.strFromBeanToPage(form.
                     getNote()));
               return mapping.findForward("borrow_renew");
           }
       }
       catch(Exception ex){
           generalError(request,ex);
           return mapping.findForward("failure");
       }
   }
   public ActionForward performView(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){

        TawBorrowForm form=(TawBorrowForm)actionForm;
        TawBorrowBO bo=new TawBorrowBO(ds);

        try{
            //������ʾ��ѯ���
//            form.setStorageid((String)request.getSession().getAttribute("storageid"));
            TawBorrowDAO tawBorrowDAO=new TawBorrowDAO(ds);
            int offset;
            int length=PAGE_LENGTH;
            String pageOffset=request.getParameter("pager.offset");
            if(pageOffset==null||pageOffset.equals("")){
                offset=0;
            }
            else{
                offset=Integer.parseInt(pageOffset);
            }
            String condition="";
//            if(form.getTimestart()!=null){
//            if(request.getParameter("timestart")!=null){
                condition=bo.getCondition(form);
//                request.getSession().removeAttribute("condition");
//                request.getSession().setAttribute("condition",condition);
//            }
//            else{
//                condition=(String)request.getSession().getAttribute("condition");
//            }
            List sparepart=tawBorrowDAO.getPartList(condition,offset,length);
            Integer size=new Integer(tawBorrowDAO.getSize("taw_sp_borrow",
                  condition));
            String url=request.getContextPath()+"/sparepart"+mapping.getPath()+
                  ".do";
            String pagerHeader=Pager.generate(offset,size.intValue(),length,url);
            request.setAttribute("pagerHeader",pagerHeader);
            request.setAttribute("sparepart",sparepart);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

}
