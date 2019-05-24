package com.boco.eoms.sparepart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import java.util.Vector;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;

//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.sparepart.dao.TawStorageDAO;
import com.boco.eoms.sparepart.bo.TawStorageBO;
import com.boco.eoms.sparepart.bo.TawQueryBO;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.sparepart.model.*;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawStorageAction extends Action{
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
        	String privFlag=StaticMethod.nullObject2String(request.getSession().getAttribute("privFlag"));//Ȩ�޹��˲���.�������������˿���
        	if(privFlag.equals("true")){
        		//request.getSession().removeAttribute("privFlag");  
        		TawQueryBO tawQueryBO = new TawQueryBO(ds);
                STORAGE = tawQueryBO.getStorage();
        	}else{
        		TawQueryBO tawQueryBO = new TawQueryBO(ds);
                STORAGE = tawQueryBO.getStorage();
                /*
                TawValidatePrivBO tawValidateBO=new TawValidatePrivBO(ds);
                if(!tawValidateBO.validPriv(user_id,mapping.getPath())){
                    return mapping.findForward("nopriv");
                }
                //20040712
                
                Vector v=tawValidateBO.validatePriv(user_id,7014,17);//��7014�޸�Ϊ7000. ԭΪ�ֿ�ѡ���Ϊ�ֿ����
                TawReturnDom TawReturnDom=new TawReturnDom(ds);
                STORAGE=TawReturnDom.getStorage(v);
                if (user_id.equalsIgnoreCase("admin")) {
                  TawQueryBO tawQueryBO = new TawQueryBO(ds);
                  STORAGE = tawQueryBO.getStorage();
                } else if (STORAGE.size() == 0) {
                  return mapping.findForward("nopriv");
                }
                //20040712
                 * 
                 */
        	}
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

        else if("VIEW".equalsIgnoreCase(myaction)){
            myforward=performView(mapping,form,request,response);
        }
        else if("CREATE".equalsIgnoreCase(myaction)){
            myforward=performCreate(mapping,form,request,response);
        }
        else if("INSERT".equalsIgnoreCase(myaction)){
            myforward=performInsert(mapping,form,request,response);
        }
        else if("EDIT".equalsIgnoreCase(myaction)){
            myforward=performEdit(mapping,form,request,response);
        }
        else if("UPDATE".equalsIgnoreCase(myaction)){
            myforward=performUpdate(mapping,form,request,response);
        }
        else if("DROP".equalsIgnoreCase(myaction)){
            myforward=performDrop(mapping,form,request,response);
        }
        else if("CHOOSE".equalsIgnoreCase(myaction)){
            myforward=performChoose(mapping,form,request,response);
        }
        else if("CHOOSED".equalsIgnoreCase(myaction)){
            myforward=performChoosed(mapping,form,request,response);
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

    public ActionForward performView(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);
        try{
            TawStorageBO bo=new TawStorageBO(ds);
            ArrayList storage=(ArrayList)bo.getStorageList();
            request.setAttribute("storage",storage);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performCreate(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
    	List list = null;
    	TawStorageBO bo=new TawStorageBO(ds);
    	try {
    		list = bo.getDept("1");
    	} catch(Exception e) {

    	}
    	request.setAttribute("DEPT",list);
        return mapping.findForward("ok");
    }

    public ActionForward performInsert(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        TawStorageForm form=(TawStorageForm)actionForm;
        TawStorageBO bo=new TawStorageBO(ds);
        String aaa=form.getStoragename();
        if(bo.checkStorageName(aaa)){
            try{
                //ת��
                //MyBeanUtils.copyPropertiesFromPageToDB(form);
                String msg=bo.insertStorage(form.getStoragename(),form.getNote(),form.getDeptId());
                request.setAttribute("msg",msg);

            }
            catch(Exception ex){
                generalError(request,ex);
                return mapping.findForward("failure");
            }

            return mapping.findForward("ok");
        }
        else{
            request.setAttribute("msg",StaticPart.STOTAGE_NAME_HAS);
            return mapping.findForward("check");
        }
    }

    public ActionForward performEdit(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        List list = null;
        TawStorageForm form=(TawStorageForm)actionForm;
        TawStorageBO bo=new TawStorageBO(ds);
        try{
            TawStorage storage=bo.getStorage(form.getId());
            request.setAttribute("storage",storage);
    		list = bo.getDept("1");
        	request.setAttribute("DEPT",list);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performUpdate(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        TawStorageForm form=(TawStorageForm)actionForm;
        TawStorageBO bo=new TawStorageBO(ds);
        String strID=form.getId();
        int id=Integer.parseInt(strID);

        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            String updataOK=bo.updateStorage(id,form.getStoragename(),
                                             form.getNote(),form.getDeptId());
            request.setAttribute("msg",updataOK);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performDrop(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        TawStorageForm form=(TawStorageForm)actionForm;
        TawStorageBO bo=new TawStorageBO(ds);
        try{
            String msg=bo.deleteStorage(form.getId());
            request.setAttribute("msg",msg);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performChoose(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        try{
            TawStorageBO bo=new TawStorageBO(ds);
            if(user_id.equalsIgnoreCase("admin")){
                ArrayList storage=(ArrayList)bo.getStorageList();
                request.setAttribute("storage",storage);
            }
            else{
                request.setAttribute("storage",STORAGE);
            }
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performChoosed(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        TawStorageBO bo=new TawStorageBO(ds);
        TawStorageForm form=(TawStorageForm)actionForm;

        request.getSession().removeAttribute("storageid");
        request.getSession().setAttribute("storageid",form.getId());
        request.getSession().removeAttribute("storage");
        request.getSession().setAttribute("storage",
                                          bo.getStorageName(form.getId()));
        request.getSession().removeAttribute("storage_dept_id");
        request.getSession().setAttribute("storage_dept_id",
                                          bo.getStorage(form.getId()).getDeptid());
        request.getSession().removeAttribute("storage_dept_name");
        request.getSession().setAttribute("storage_dept_name",
                                          bo.getStorage(form.getId()).getDeptname());

        request.setAttribute("msg",StaticPart.CHOOSED_OK+"请从左边选择你要进行的操作!");
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
