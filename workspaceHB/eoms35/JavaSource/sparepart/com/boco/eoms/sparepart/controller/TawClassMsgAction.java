package com.boco.eoms.sparepart.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sparepart.bo.TawClassMsgBO;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import com.boco.eoms.sparepart.model.TawClassMsg;
import com.boco.eoms.common.util.StaticObject;
import java.sql.*;
import com.boco.eoms.sparepart.dao.TawClassMsgDAO;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.common.util.*;
import org.apache.struts.action.*;
import java.lang.reflect.*;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawClassMsgAction extends Action{
    private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.
          ConnectionPool.getInstance();

    private static int PAGE_LENGTH=10;
    private String user_id="";

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

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        ActionForward myforward=null;
        String myaction=mapping.getParameter();
        System.out.println(myaction);

        //session��ʱ����
        try{
        	TawSystemSessionForm saveSessionBeanForm=(TawSystemSessionForm)
                  request.getSession().getAttribute("sessionform");
            if(saveSessionBeanForm==null){
                return mapping.findForward("timeout");
            }
            user_id=StaticMethod.null2String(saveSessionBeanForm.getUserid());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*Ȩ����֤
        try{
            TawValidatePrivBO tawValidateBO=new TawValidatePrivBO(ds);
            if(!tawValidateBO.validPriv(user_id,mapping.getPath())){
                return mapping.findForward("nopriv");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        */
        if(isCancelled(request)){
            return mapping.findForward("failure");
        }

        if("".equalsIgnoreCase(myaction)){
            myforward=mapping.findForward("failure");
        }

        else if("CLASS".equalsIgnoreCase(myaction)){
            myforward=performClass(mapping,form,request,response);
        }
        else if("EDITCLASS".equalsIgnoreCase(myaction)){
            myforward=performEditClass(mapping,form,request,response);
        }

        else if("DROPCLASS".equalsIgnoreCase(myaction)){
            myforward=performDropClass(mapping,form,request,response);
        }
        else if("UPDATECLASS".equalsIgnoreCase(myaction)){
            myforward=performUpdateClass(mapping,form,request,response);
        }
        else if("CLASSTREE".equalsIgnoreCase(myaction)){
            myforward=performClassTree(mapping,form,request,response);
        }
        else if("ADDCLASS".equalsIgnoreCase(myaction)){
            myforward=performAddClass(mapping,form,request,response);
        }
        else if("INSERTCLASS".equalsIgnoreCase(myaction)){
            myforward=performInsertClass(mapping,form,request,response);
        }
        else if("SPNAME".equalsIgnoreCase(myaction)){
            myforward=performSpName(mapping,form,request,response);
        }
        else if("NAMEVIEW".equalsIgnoreCase(myaction)){
            myforward=performNameView(mapping,form,request,response);
        }
        else if("VIEW".equalsIgnoreCase(myaction)){
            myforward=performView(mapping,form,request,response);
        }
        else if("ADD".equalsIgnoreCase(myaction)){
            myforward=performAdd(mapping,form,request,response);
        }

        return myforward;
    }

    /**
     * @see ����ά�����г�����ͱ���Ϣ�������������Ϣ��������ά����2.0������ά����
     * û�ж༶l���Ĺ��ܡ�
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performClass(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        List type=bo.getType();
        request.setAttribute("type",type);
        if(bo.checkClassName(StaticMethod.strFromPageToDB(form.getCname()))){
            if(form.getParentId()!=null){
                List classMsg=bo.getMsg(form.getParentId());
                request.setAttribute("classMsg",classMsg);
            }

        }
          return mapping.findForward("ok");
        //else{
          ///  request.setAttribute("msg",StaticPart.CLASS_NAME_HAS);
          //  return mapping.findForward("check");
       // }
    }

    /**
     * @see ���͵ı༭��������}�����͵�ά���ж��õ��ˡ�
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performEditClass(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        TawClassMsg tawClassMsg=null;
        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            tawClassMsg=bo.getEditClass(form);
            //ɾ���ڴ��е���ͼ
            StaticObject obj=StaticObject.getInstance();
            obj.removeObject("TawClassMsgTree");
            request.setAttribute("tawClassMsg",tawClassMsg);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    /**
     * @see ��ɱ༭��update,���ر༭�ɹ���ҳ��
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performUpdateClass(ActionMapping mapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response){
        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        String msg=null;
        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            msg=bo.updateClass(form);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        request.setAttribute("msg",msg);

        return mapping.findForward("ok");
    }

    /**
     * @see ɾ�����ͣ�ȷ����ҳ�����ˣ��Գ��ɾ����������ɾ���־λ
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performDropClass(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        String msg=bo.deleteClass(form.getId());
        //ɾ���ڴ��е���ͼ
        StaticObject obj=StaticObject.getInstance();
        obj.removeObject("TawClassMsgTree");

        request.setAttribute("msg",msg);

        return mapping.findForward("ok");
    }

    //test calss tree
    /**
     * @see ��������ά�����÷�����Ҫ������ʾ�ұߵ�������Ϣ��
     * dept������javascript���������
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performClassTree(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);

            int deptId=StaticMethod.null2int(request.getParameter("dept"));
            if(deptId!=0){
                form.setId(Integer.toString(deptId));
                TawClassMsg tawClassMsg=bo.getEditClass(form);
                request.setAttribute("tawClassMsg",tawClassMsg);
            }
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");

    }

    /**
     * @see �������
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performAddClass(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        String deleted=request.getParameter("deleted");
        if(deleted==null){
            deleted="0";
        }
        if(deleted.equals("3")){
            request.setAttribute("msg",StaticPart.No_CLASS_NAME);
            return mapping.findForward("check");
        }
        form.setParentId(form.getId());
        form.setDeleted(deleted);

        return mapping.findForward("ok");
    }

    /**
     * @see ����ݿ����һ��������Ϣ����ҳ�洫4��id��Ϊparent_id��
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performInsertClass(ActionMapping mapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response){

        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        String msg=null;
        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            msg=bo.insertClass(form.getCname(),form.getNote(),
                               form.getParentId(),form.getDeleted());
        }
        catch(SQLException ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        //ɾ���ڴ��е���ͼ
        StaticObject obj=StaticObject.getInstance();
        obj.removeObject("TawClassMsgTree");

        request.setAttribute("msg",msg);
        return mapping.findForward("ok");
    }

    public ActionForward performSpName(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        if(bo.checkSpName()){
            request.setAttribute("msg",StaticPart.NO_DATA);
            return mapping.findForward("noData");
        }
        else{
            return mapping.findForward("ok");
        }
    }

    public ActionForward performNameView(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        try{
            TawClassMsgDAO dao=new TawClassMsgDAO(ds);
            //������ʾ��ѯ���
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
            if(request.getParameter("differ")!=null){
                condition=bo.getNameSql(form);
                request.getSession().removeAttribute("condition");
                request.getSession().setAttribute("condition",condition);
            }
            else{
                condition=(String)request.getSession().getAttribute("condition");
            }

            form.setDepartment(StaticMethod.strFromBeanToPage(form.
                  getDepartment()));
            form.setNecode(StaticMethod.strFromBeanToPage(form.getNecode()));
            form.setId(form.getId());

            List tawSpClassmsg=dao.getClassMsgList(condition,offset,length);
            Integer size=new Integer(dao.getSize("taw_sp_classmsg",
                                                 condition));
            String url=request.getContextPath()+"/sparepart"+mapping.getPath()+
                  ".do";
            String pagerHeader=Pager.generate(offset,size.intValue(),length,url);
            request.setAttribute("pagerHeader",pagerHeader);
            request.setAttribute("classMsg",tawSpClassmsg);
        }
        catch(Exception e){
            e.printStackTrace();
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performView(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){

        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);

        List classMsg=null;
        try{
            classMsg=bo.getMsg(form.getParentId());
            form.setParentId(form.getParentId());
        }
        catch(Exception e){
            e.printStackTrace();
            return mapping.findForward("failure");
        }
        request.setAttribute("classMsg",classMsg);
        return mapping.findForward("ok");
    }

    public ActionForward performAdd(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){

        TawClassMsgForm form=(TawClassMsgForm)actionForm;
        TawClassMsgBO bo=new TawClassMsgBO(ds);
        form.setParentId(form.getParentId());
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

}
