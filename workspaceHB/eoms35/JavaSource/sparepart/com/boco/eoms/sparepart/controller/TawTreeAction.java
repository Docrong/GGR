package com.boco.eoms.sparepart.controller;

import org.apache.struts.action.*;
import java.util.*;
import javax.servlet.http.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.common.util.*;
//import com.boco.eoms.jbzl.bo.*;
//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.sparepart.util.*;
import java.sql.*;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.sparepart.bo.*;
import com.boco.eoms.sparepart.model.*;
import javax.naming.*;

/**
 * <p>Title: ��Ʒ����</p>
 * <p>Description: EOMS��ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public class TawTreeAction extends Action{
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
            if(STORAGE.size()==0&&!user_id.equalsIgnoreCase("admin")){
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
        else if("VIEW".equalsIgnoreCase(myaction)){
            myforward=performView(mapping,form,request,response);
        }
        else if("ADD".equalsIgnoreCase(myaction)){
            myforward=performAdd(mapping,form,request,response);
        }
        else if("EDIT".equalsIgnoreCase(myaction)){
            myforward=performEdit(mapping,form,request,response);
        }
        else if("NETROOT".equalsIgnoreCase(myaction)){
            myforward=performNetRoot(mapping,form,request,response);
        }
        else if("TYPE".equalsIgnoreCase(myaction)){
            myforward=performType(mapping,form,request,response);
        }
        else if("DROP".equalsIgnoreCase(myaction)){
            myforward=performDrop(mapping,form,request,response);
        }
        else if("INSERT".equalsIgnoreCase(myaction)){
            myforward=performInsert(mapping,form,request,response);
        }
        else if("UPDATE".equalsIgnoreCase(myaction)){
            myforward=performUpdate(mapping,form,request,response);
        }
        else if("SUBDEPT".equalsIgnoreCase(myaction)){  //Сרҵ��ӵ�һ��(ѡ���רҵ)
            myforward=performSubDept(mapping,form,request,response);
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

        TawTreeForm form=(TawTreeForm)actionForm;
        TawTreeBO bo=new TawTreeBO();

        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            //����form��parentId and layer
            bo.getParentOrLayer(form);
            int i=StaticMethod.getIntValue(form.getLayer());
            List tree=null;
            if(form!=null){
            	tree=bo.getView(form.getParentId(),form.getType());
            }
            
            request.setAttribute("tree",tree);
            request.setAttribute("treeMsg",StaticPart.treeMsg[i-1]);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performAdd(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        TawTreeForm form=(TawTreeForm)actionForm;
        TawTreeBO bo=new TawTreeBO();

        try{
            int id=form.getId();
            form.setId(id);
            int layer=bo.getParentToLayer(id);
            request.setAttribute("treeMsg",StaticPart.ADD_TREE[layer]);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performInsert(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        TawTreeForm form=(TawTreeForm)actionForm;
        TawTreeBO bo=new TawTreeBO();
        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            int layer=bo.insert(form.getId(),form.getCname(),form.getNote());
            if (layer != -1){
              request.setAttribute("msg",StaticPart.CREATE_TREE_OK[layer]);
            }
            else{
              request.setAttribute("msg","���������ݿ����Ѵ��ڣ���������д");
            }
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performEdit(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        TawTreeForm form=(TawTreeForm)actionForm;
        TawTreeBO bo=new TawTreeBO();
        try{
            TawTree tawTree=bo.getTree(form.getId());
            request.setAttribute("tawTree",tawTree);
            int layer=bo.getLayer(tawTree.getLayer());
            request.setAttribute("treeMsg",StaticPart.treeMsg[layer]);
            form.setId(tawTree.getId());
            form.setLayer(Integer.toString(layer));
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performUpdate(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        TawTreeForm form=(TawTreeForm)actionForm;
        TawTreeBO bo=new TawTreeBO();
        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            bo.updateTree(form.getId(),form.getCname(),form.getNote());
            int layer=Integer.parseInt(form.getLayer());
            request.setAttribute("msg",StaticPart.UPDATE_TREE_OK[layer]);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performSubDept(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);
        try{
            TawTreeBO bo=new TawTreeBO();
            List tawTree=bo.getView(0,"%");
            int size=tawTree.size();
            if(size>0){
                request.setAttribute("tawTree",tawTree);
            }
            else{
                request.setAttribute("msg",StaticPart.NO_DEPT);
                return mapping.findForward("check");
            }
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }
    public ActionForward performNetRoot(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response){
//    	����
        StaticPartMethod.setReturnPath(mapping,request);
        try{
            TawTreeBO bo=new TawTreeBO();
            //ƴ�ַ�
            String StringTree=bo.getMyTreeStr(2);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            else{
                request.setAttribute("msg",StaticPart.NO_SUB_DEPT);
                return mapping.findForward("check");
            }
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
		 
	}
    public ActionForward performType(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);
        try{
            TawTreeBO bo=new TawTreeBO();
            //ƴ�ַ�
            String StringTree=bo.getMyTreeStr(3);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            else{
                request.setAttribute("msg",StaticPart.NO_NETROOT);
                return mapping.findForward("check");
            }
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
        TawTreeForm form=(TawTreeForm)actionForm;
        TawTreeBO bo=new TawTreeBO();
        try{
            int id=form.getId();
            int layer=bo.getLayer(id);
            bo.dropTree(id);
            request.setAttribute("msg",StaticPart.DROP_TREE_OK[layer]);
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
