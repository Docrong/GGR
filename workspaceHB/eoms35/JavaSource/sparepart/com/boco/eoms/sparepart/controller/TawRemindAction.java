package com.boco.eoms.sparepart.controller;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Vector;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.common.util.StaticMethod;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import com.boco.eoms.sparepart.util.TawReturnDom;
import com.boco.eoms.sparepart.bo.TawRemindBO;
import com.boco.eoms.sparepart.bo.TawQueryBO;
import com.boco.eoms.sparepart.bo.*;
import org.apache.struts.action.*;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.common.util.*;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawRemindAction extends Action{
    public TawRemindAction(){
    }

    public static final String NoPartName="�ñ�����Ʋ����ڣ�";

    private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.
          ConnectionPool.getInstance();

    private static int PAGE_LENGTH=10;
    private String user_id="";
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
        //Ȩ����֤
        try{
            /*
        	TawValidatePrivBO tawValidateBO=new TawValidatePrivBO(ds);
            if(!tawValidateBO.validPriv(user_id,mapping.getPath())){
                return mapping.findForward("nopriv");
            }
            //20040712
            Vector v=tawValidateBO.validatePriv(user_id,7014,17);
            TawReturnDom TawReturnDom=new TawReturnDom(ds);
            STORAGE=TawReturnDom.getStorage(v);

            if(user_id.equalsIgnoreCase("admin")){
                TawQueryBO tawQueryBO=new TawQueryBO(ds);
                STORAGE=tawQueryBO.getStorage();
            }
            else if(STORAGE.size()==0){
                return mapping.findForward("nopriv");
            }
            */
            //20040712
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //
        if(isCancelled(request)){
            return mapping.findForward("failure");
        }

        if("".equalsIgnoreCase(myaction)){
            myforward=mapping.findForward("failure");
        }
        else if("SETPART".equalsIgnoreCase(myaction)){
            myforward=performSetPart(mapping,form,request,response);
        }
        else if("OVERPART".equalsIgnoreCase(myaction)){
            myforward=performOverPart(mapping,form,request,response);
        }
        else if("SETORDER".equalsIgnoreCase(myaction)){
            myforward=performSetOrder(mapping,form,request,response);
        }
        else if("OVERORDER".equalsIgnoreCase(myaction)){
            myforward=performOverOrder(mapping,form,request,response);
        }

        return myforward;
    }

    public ActionForward performSetPart(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawTreeBO bo=new TawTreeBO();
        try{
            //ƴ�ַ�
            String StringTree=bo.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            request.setAttribute("storage",STORAGE);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performOverPart(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        TawRemindBO tawRemindBO=new TawRemindBO(ds);
        TawRemindForm tawRemindForm=(TawRemindForm)actionForm;

        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(tawRemindForm);
            String classId=tawRemindBO.getClassId(tawRemindForm.getDepartment(),
                                                  tawRemindForm.getNecode(),
                                                  tawRemindForm.getObjectname());
            //�Ƿ����Ψһ�ı������
            if(classId!=""){
                tawRemindForm.setObjectname(classId);
                //setType(10)�������
                tawRemindForm.setType(10);

                String msg=tawRemindBO.insertRemindPart(tawRemindForm);
                request.setAttribute("msg",msg);
                return mapping.findForward("ok");
            }
            else{
                request.setAttribute("msg",NoPartName);
                return mapping.findForward("ok");
            }
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
    }

    public ActionForward performSetOrder(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawQueryBO tawQueryBO=new TawQueryBO(ds);
        request.setAttribute("storage",STORAGE);

        return mapping.findForward("ok");
    }

    public ActionForward performOverOrder(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
        TawRemindBO tawRemindBO=new TawRemindBO(ds);
        TawRemindForm tawRemindForm=(TawRemindForm)actionForm;
        try{  //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(tawRemindForm);
            String msg=tawRemindBO.insertRemindOrder(tawRemindForm);
            request.setAttribute("msg",msg);
        }
        catch(Exception ex){
            generalError(request,ex);
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

}
