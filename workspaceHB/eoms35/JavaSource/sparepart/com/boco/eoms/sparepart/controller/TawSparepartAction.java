package com.boco.eoms.sparepart.controller;

import java.util.HashMap;
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
import com.boco.eoms.sparepart.bo.TawOrderBO;
import com.boco.eoms.sparepart.bo.TawOrderDetailBO;
import com.boco.eoms.sparepart.bo.TawSparepartBO;
import com.boco.eoms.sparepart.dao.TawOrderDAO;
import com.boco.eoms.sparepart.dao.TawOrderPartDAO;
import com.boco.eoms.sparepart.dao.TawSparepartDAO;
import com.boco.eoms.sparepart.model.TawOrder;

//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.sparepart.bo.TawClassMsgBO;
import com.boco.eoms.sparepart.model.TawClassMsg;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.sparepart.bo.*;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.common.util.*;
import com.jspsmart.upload.*;
import javax.servlet.*;
import java.io.*;
import java.util.Vector;
import org.apache.struts.upload.*;

import com.boco.eoms.sparepart.model.TawOrderPart;
import com.boco.eoms.sparepart.model.TawPart;
import com.boco.eoms.sparepart.model.TawSparepart;
import com.boco.eoms.sparepart.model.TawStorage;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawSparepartAction extends Action{
    public TawSparepartAction(){
    }

    public static final String noName="�ñ�����Ʋ����ڣ�";
    private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.
          ConnectionPool.getInstance();

    private static int PAGE_LENGTH=10;
    private String user_id="",user_name="";
    private String dept_id = "";
    private String dept_name="";
    List STORAGE=new ArrayList();
    String storageListStr="";
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
            /*
        	logBO logbo=new logBO(ds);
            if(ret.equals("success")){
                boolean bool=logbo.insertLogToDbPathNew(user_id,mapping.getPath(),
                      StaticVariable.OPER,
                      request.getRemoteAddr(),name);

            }
            else if(ret.equals("failure")){
                boolean bool=logbo.insertLogToDbPathNew(user_id,mapping.getPath(),
                      StaticVariable.ERROR,
                      request.getRemoteAddr(),name);
            }
            */
        }
        catch(Exception e){
            BocoLog.error(this,0,"ϵͳ��־����(����taw_log)����",e);
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
            request.setCharacterEncoding("GB2312");
            TawSystemSessionForm saveSessionBeanForm=(TawSystemSessionForm)
                  request.getSession().getAttribute("sessionform");
            if(saveSessionBeanForm==null){
                return mapping.findForward("timeout");
            }
            user_id=StaticMethod.null2String(saveSessionBeanForm.getUserid());
            user_name=StaticMethod.null2String(saveSessionBeanForm.getUsername());
            dept_id=StaticMethod.null2String(saveSessionBeanForm.getDeptid());
            dept_name=StaticMethod.null2String(saveSessionBeanForm.getDeptname());
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
            if(mapping.getPath().equals("/part/newview2") || mapping.getPath().equals("/part/view") 
            		|| mapping.getPath().equals("/part/over")|| mapping.getPath().equals("/part/add")){
            	
            }else{
            	Vector v = tawValidateBO.validatePriv(user_id, 7014, 17);
                TawReturnDom TawReturnDom = new TawReturnDom(ds);
                STORAGE = TawReturnDom.getStorage(v);    
                if (user_id.equalsIgnoreCase("admin")) {
                  TawQueryBO tawQueryBO = new TawQueryBO(ds);
                  STORAGE = tawQueryBO.getStorage();
                } else if (STORAGE.size() == 0) {
                  return mapping.findForward("nopriv");
                }
            }
             */
           
            storageListStr="";
            for(int i=0;i<STORAGE.size();i++){
              TawStorage tawStorage = (TawStorage)STORAGE.get(i);
              storageListStr = storageListStr + ","+Integer.toString(tawStorage.getId());
            }
            if(!storageListStr.equalsIgnoreCase("")){
              storageListStr = storageListStr.substring(1,storageListStr.length());
            }

            //20040712
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /**
         * @ ѡ��ֿ�
         */
        if(request.getSession().getAttribute("storage")==null){
            //����
            StaticPartMethod.setReturnPath(mapping,request);

            ActionForward actionForward=new ActionForward(
                  "/storage/choose.do");
            return actionForward;
        }
        //
        if(isCancelled(request)){
            return mapping.findForward("failure");
        }
        else if("NEWVIEW".equalsIgnoreCase(myaction)){
            myforward=performNewView(mapping,form,request,response);
        }
        else if("NEWVIEW2".equalsIgnoreCase(myaction)){
            myforward=performNewView2(mapping,form,request,response);
        }        
        else if("BORROW".equalsIgnoreCase(myaction)){
            myforward=performBorrow(mapping,form,request,response);
        }
        else if("ADDFAULTPART".equalsIgnoreCase(myaction)){
            myforward=performAddFaultPart(mapping,form,request,response);
        }
        else if("ADDFAULTPARTSUBMIT".equalsIgnoreCase(myaction)){
            myforward=performAddFaultPartSubmit(mapping,form,request,response);
        }
        else if("REVIEW".equalsIgnoreCase(myaction)){
            myforward=performReView(mapping,form,request,response);
        }
        else if("ADD".equalsIgnoreCase(myaction)){
            myforward=performAdd(mapping,form,request,response);
        }
        else if("VIEW".equalsIgnoreCase(myaction)){
            myforward=performView(mapping,form,request,response);
        }
        else if("OVER".equalsIgnoreCase(myaction)){
            myforward=performOver(mapping,form,request,response);
        }
        else if("UPDATETERM".equalsIgnoreCase(myaction)){
            myforward=performUpdateterm(mapping,form,request,response);
        }
        else if("FINDTERM".equalsIgnoreCase(myaction)){
            myforward=performFindterm(mapping,form,request,response);
        }
        else if("UPDATEVIEW".equalsIgnoreCase(myaction)){
            myforward=performUpdateview(mapping,form,request,response);
        }
        else if("LOADVIEW".equalsIgnoreCase(myaction)){
            myforward=performLoadview(mapping,form,request,response);
        }
        else if("UPDATEPAGE".equalsIgnoreCase(myaction)){
            myforward=performUpdatepage(mapping,form,request,response);
        }
        else if("UPDATEOVER".equalsIgnoreCase(myaction)){
            myforward=performUpdateover(mapping,form,request,response);
        }
        else if("RETURNOVER".equalsIgnoreCase(myaction)){
            myforward=performReturnover(mapping,form,request,response);
        }
        else if("SERVICEOVER".equalsIgnoreCase(myaction)){
            myforward=performServiceover(mapping,form,request,response);
        }
        else if("DROP".equalsIgnoreCase(myaction)){
            myforward=performDrop(mapping,form,request,response);
        }
        else if("BACKOVER".equalsIgnoreCase(myaction)){
            myforward=performBackOver(mapping,form,request,response);
        }
        else if("BATCHPART".equalsIgnoreCase(myaction)){
            myforward=performBatchPart(mapping,form,request,response);
        }
        else if("BATCHOVER".equalsIgnoreCase(myaction)){
            myforward=performBatchOver(mapping,form,request,response);
        }
        else if("REMOVELOAD".equalsIgnoreCase(myaction)){
            myforward=performRemoveLoad(mapping,form,request,response);
        }
        else if("REMOVE".equalsIgnoreCase(myaction)){
            myforward=performRemove(mapping,form,request,response);
        }
        else if("REMOVEOVER".equalsIgnoreCase(myaction)){
            myforward=performRemoveOver(mapping,form,request,response);
        }
        else if("SERIALNO".equalsIgnoreCase(myaction)){
           myforward=performSerialno(mapping,form,request,response);
       }
       else if("SERIALNOEND".equalsIgnoreCase(myaction)){
           myforward=performSerialnoEnd(mapping,form,request,response);
       }
       else if("TOEXPORT".equalsIgnoreCase(myaction)){
           myforward=performToexport(mapping,form,request,response);
       }
       else if("TOEXPORTALL".equalsIgnoreCase(myaction)){
           myforward=performToexportall(mapping,form,request,response);
       }
       else if("ACCESSORY".equalsIgnoreCase(myaction)){
           myforward=performAccessory(mapping,form,request,response);
       }
       else if("INAPP".equalsIgnoreCase(myaction)){//�������
           myforward=performInApp(mapping,form,request,response);
       }      
       else if("OUTAPP".equalsIgnoreCase(myaction)){//�������
           myforward=performOutApp(mapping,form,request,response);
       }
       else if("INCHECK".equalsIgnoreCase(myaction)){//�������б�
           myforward=performInCheck(mapping,form,request,response);
       }  
       else if("INPASS".equalsIgnoreCase(myaction)){//��������б�
           myforward=performInPass(mapping,form,request,response);
       }          
       else if("INCHECKDETAIL".equalsIgnoreCase(myaction)){//��������ϸ
           myforward=performInCheckDetail(mapping,form,request,response);
       }  
       else if("INPASSDETAIL".equalsIgnoreCase(myaction)){//���������ϸ
           myforward=performInPassDetail(mapping,form,request,response);
       }         
       else if("INCHECKOK".equalsIgnoreCase(myaction)){//������ͨ��
           myforward=performInCheckOK(mapping,form,request,response);
       }     
       else if("INPASSOK".equalsIgnoreCase(myaction)){//�������ͨ��
           myforward=performInPassOK(mapping,form,request,response);
       } 
       else if("INCHECKNO".equalsIgnoreCase(myaction)){//�����˲�ͨ��
           myforward=performInCheckNO(mapping,form,request,response);
       }   
       else if("INPASSNO".equalsIgnoreCase(myaction)){//�������ͨ��
           myforward=performInCheckNO(mapping,form,request,response);
       } 
       else if("APPBACK".equalsIgnoreCase(myaction)){//���������˲�ͨ����б�
           myforward=performInAppBack(mapping,form,request,response);
       }  
       else if("APPBACKDETAIL".equalsIgnoreCase(myaction)){//���������˲�ͨ�����ϸ
           myforward=performAppBackDetail(mapping,form,request,response);
       }         
       else if("APPBACKCHECK".equalsIgnoreCase(myaction)){//���������˲�ͨ�����ϸ
           myforward=performAppBackCheck(mapping,form,request,response);
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

    public ActionForward performNewView(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        StaticPartMethod.setReturnPath(mapping,request);

        TawSparepartForm sparepart=(TawSparepartForm)actionForm;
        //�����
        TawSparepartBO bobo=new TawSparepartBO(ds);
        TawTreeBO bo=new TawTreeBO();

        try{
            List supplier=bobo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
//            request.setAttribute("userId",user_name);
            sparepart.setOperator(user_name);
            sparepart.setSheetid(StaticPartMethod.getOrdernumber("1"));
            sparepart.setProposer(user_name);
            //ƴ�ַ�
            String StringTree=bo.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            request.setAttribute("sparepart",sparepart);
            bo=null;
            bobo=null;
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }
	    public ActionForward performNewView2(ActionMapping mapping,
	            ActionForm actionForm,
	            HttpServletRequest request,
	            HttpServletResponse response){
		StaticPartMethod.setReturnPath(mapping,request);
		
		TawSparepartForm sparepart=(TawSparepartForm)actionForm;
		//�����
		TawSparepartBO bobo=new TawSparepartBO(ds);
		TawTreeBO bo=new TawTreeBO();
		
		try{
			List supplier=bobo.getClassMsg(6);
			request.setAttribute("supplier",supplier);//������
			List company=bobo.getClassMsg(450);//��������˾
			request.setAttribute("company",company);
			List fixe=bobo.getClassMsg(460);//�豸����
			request.setAttribute("fixe",fixe);			
			//request.setAttribute("userId",user_name);
			sparepart.setOperator(user_name);
			//sparepart.setSheetid(StaticPartMethod.getOrdernumber("1"));
			sparepart.setProposer(user_name);
			//ƴ�ַ�
			String StringTree=bo.getMyTreeStr(4);
		if(!StringTree.equals("")){
			request.setAttribute("StringTree",StringTree);
		}
		request.setAttribute("sparepart",sparepart);
		bo=null;
		bobo=null;
		}
		catch(Exception ex){
			generalError(request,ex);
		return mapping.findForward("failure");
		}
		return mapping.findForward("ok");
	}
    public ActionForward performBorrow(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        StaticPartMethod.setReturnPath(mapping,request);

        //�����
        TawSparepartBO bobo=new TawSparepartBO(ds);
        TawTreeBO bo=new TawTreeBO();

        try{
            List supplier=bobo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
            request.setAttribute("userId",user_name);
            //ƴ�ַ�
            String StringTree=bo.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            bo=null;
            bobo=null;
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performAddFaultPart(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        StaticPartMethod.setReturnPath(mapping,request);

        TawSparepartForm sparepart=(TawSparepartForm)actionForm;
        //�����
        TawSparepartBO bobo=new TawSparepartBO(ds);
        TawTreeBO bo=new TawTreeBO();

        try{
            List supplier=bobo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
//            request.setAttribute("userId",user_name);
            sparepart.setOperator(user_name);
            //ƴ�ַ�
            String StringTree=bo.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            request.setAttribute("sparepart",sparepart);
            bo=null;
            bobo=null;
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performAddFaultPartSubmit(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){

      TawSparepartForm form = (TawSparepartForm) actionForm;
      TawSparepartBO bo = new TawSparepartBO(ds);
      String stotageId = (String) request.getSession().getAttribute("storageid");
      String EQFaultManagerId = (String) request.getSession().getAttribute("EQFaultManagerId");
      form.setStorageid(stotageId);
      TawOrderBO orderBO = new TawOrderBO(ds);
      String msg = "";
      msg = bo.insertPart(form);
      if(!EQFaultManagerId.equals("")&&EQFaultManagerId!=null){
        String serialNo = form.getSerialno();

      }
      bo = null;
      request.setAttribute("msg", msg);
      return mapping.findForward("ok");
    }

    public ActionForward performReView(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        StaticPartMethod.setReturnPath(mapping,request);
        String sheetid = request.getParameter("sheetid");
        TawSparepartForm sparepart=(TawSparepartForm)request.getSession().getAttribute("sparepartForm");
        request.getSession().removeAttribute("sparepartForm");
        sparepart.setSheetid(sheetid);
        //�����
        TawSparepartBO bobo=new TawSparepartBO(ds);
        TawTreeBO bo=new TawTreeBO();

        try{
            List supplier=bobo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
//            request.setAttribute("userId",user_name);
            sparepart.setOperator(user_name);
            //ƴ�ַ�
            String StringTree=bo.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            request.setAttribute("sparepart",sparepart);
            bo=null;
            bobo=null;
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
      int partType=StaticMethod.nullObject2int(request.getSession().getAttribute("partType"));//������������,����0����1����2
      TawSparepartForm form = (TawSparepartForm) actionForm;
      TawSparepartBO bo = new TawSparepartBO(ds);
      String stotageId = (String) request.getSession().getAttribute("storageid");
      form.setStorageid(stotageId);
      form.setVersion(form.getProductcode());  
      form.setDeleted("1");//��һ�������Ҫ�����.
      form.setPartType(partType);//��������������.
      TawOrderBO orderBO = new TawOrderBO(ds);
      String msg = "";

        //ת��
        //MyBeanUtils.copyPropertiesFromDBToPage(form);
        msg = bo.insertPart_away(form);//�������ר��
//�����ⵥ�� dww
        TawPartDAO partDAO =new TawPartDAO(ds);
        HashMap map = null;
		try {
			//map = partDAO.getPartId();
			map=partDAO.getPartId_formanagecode();//Ψһ��ʾ�����кŸ�Ϊʵ�����..
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        TawOrderDAO orderdao=new TawOrderDAO(ds);
        //int sparepart_id=bo.getSparepartIDbyManagecode(form.getManagecode());
        String orderId=(String)request.getSession().getAttribute("orderId");
        String orderType=(String)request.getSession().getAttribute("orderType");
        orderdao.updateOrder("set state=1 where id="+orderId);//���µ���״̬Ϊ�����
        TawOrderPartDAO daopart=new TawOrderPartDAO(ds);
        daopart.insertOrderPart(orderId, (String)map.get(form.getManagecode()), "", "");//����ҵ�񵥾��뱸����j��
//        tawOrderBO.deleteTempId(user_id,Integer.toString(order_id));//�����ǰɾ��,����ݡ��������
        
//      �����ⵥ�� dww
        bo = null;
        request.setAttribute("msg", msg);//���ص���������б�
        request.setAttribute("path", "/EOMS_J2EE/sparepart/part/inapp.do");//���ص���������б�
        //��������
        if (form.getState().equals("12")) {
          return mapping.findForward("addfaultpartend");
        }
        else {
          return mapping.findForward("ok");
        }
    }

    public ActionForward performView(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        form.setStorageid((String)request.getSession().getAttribute("storageid"));
        int partType=StaticMethod.nullObject2int(request.getSession().getAttribute("partType"));
        form.setPartType(partType);//�����������.
        //request.setAttribute("orderId",request.getParameter("orderId"));
        //request.setAttribute("orderType",request.getParameter("orderType"));
        //��¼�Ѿ�ѡ�еı���id����ʱ����
        if(request.getParameter("pp")!=null){
            String SumId=bo.getChoosedId(request.getParameterValues("id"),
                                         user_id,
                                         (String)request.getSession().
                                         getAttribute("orderId"));//ȡ����ѡ����������ʱ�?��ȡ��IDֵ.���ŷֿ�.
            List choosed=bo.getTemppart(SumId);//ȡ�����б�ѡ��ı�������.��tawpart��ʽ���ڡ�
            form.setSumid(SumId);
            request.getSession().removeAttribute("SumId");
            request.getSession().setAttribute("SumId",SumId);
            request.setAttribute("choosed",choosed);
        }
        else{
            String id=(String)request.getSession().getAttribute("SumId");
            form.setSumid(id);
            if(id!=null){
                List choosed=bo.getTemppart(id);
                request.setAttribute("choosed",choosed);
            }
        }

        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            //������ʾ��ѯ���
            TawPartDAO tawPartDAO=new TawPartDAO(ds);
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
            if(request.getParameter("submit")!=null){
                request.getSession().removeAttribute("SumId");
                condition=bo.getViewSql(form);
                request.getSession().removeAttribute("condition");
                request.getSession().setAttribute("condition",condition);
            }
            else{
                condition=(String)request.getSession().getAttribute("condition");
            }
            List sparepart=tawPartDAO.getPartList(condition,offset,length);
            Integer size=new Integer(tawPartDAO.getSize("taw_part",
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

    public ActionForward performOver(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        TawSparepartForm form=(TawSparepartForm)actionForm;
        form.setUserId(user_id);
        String orderId=(String)request.getSession().getAttribute("orderId");
        String orderType=(String)request.getSession().getAttribute("orderType");
        request.getSession().removeAttribute("orderId");
        request.getSession().removeAttribute("orderType");
        String stotageId=(String)request.getSession().getAttribute("storageid");

        //add by xpz
        String destStorageId=(String)request.getSession().getAttribute("destStorageId");
        request.getSession().removeAttribute("destStorageId");

        TawSparepartBO bo=new TawSparepartBO(ds);
        try{
            /**
             * ��ɵ��ݱ�����j��ı�����Ϣ���룬����state=1 �˴�û���⡣.״̬��Ϊ1.������.
             * �˳��Ѿ������˶������stateΪ1�ˡ�
             */
            bo.insertOrderPart(form.getUserId(),orderId);
            /**
             * ��ɱ�����״̬�޸�   ״̬��Ҫ�޸�Ϊ 14 �� 6 �Ĺ�j
             */
          //  String over=bo.updateSparePart(orderType,form.getSumid()); //�˴�����ͨ�����Ч
            /**
             * ��ɱ�����������޸�  ���Ϊ6 ԭ4Ϊ1 ά��Ϊ8,ԭ4Ϊ3
             */
         //   bo.updateNum(orderType,form.getSumid()); //�˴�����ͨ�����Ч
//            //���
//            if (orderType.equals("1")) {
//              /**
//               * ���뱸�����ֿ��У�����״̬��Ϊ���룬org_serial_no���䣩
//               */
//              bo.insertBorrowInSparePart(form.getSumid(),destStorageId);
//              /**
//               * ������뵥
//               */
//              TawOrderDAO orderdao=new TawOrderDAO(ds);
//              TawOrder tawOrder=new TawOrder();
//              tawOrder.setOperater(user_name);
//              tawOrder.setPropDept(dept_name);
//              tawOrder.setProposer(user_name);
//              tawOrder.setPropTel("");
//              tawOrder.setType(8);
//              tawOrder.setStorageid(Integer.parseInt(destStorageId));
//              tawOrder.setAccessory("");
//              tawOrder.setSheetid("");
//
//              String BorrowInOrderId=Integer.toString(orderdao.insertOrder(tawOrder));
//
//              /**
//               * ��ɵ��ݱ�����j��ı�����Ϣ���룬����state=1
//               */
//              bo.insertBorrowInOrderPart(form.getUserId(),orderId,BorrowInOrderId,stotageId);
//            }  add by lq 20070320


            request.setAttribute("msg","����ɹ�");
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performUpdateterm(ActionMapping mapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        StaticPartMethod.setReturnPath(mapping,request);

        TawTreeBO tawTreeBO=new TawTreeBO();
        //�����
        TawSparepartBO bobo=new TawSparepartBO(ds);
        try{
            List supplier=bobo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
            String StringTree=tawTreeBO.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }
    public ActionForward performFindterm(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response){
    	StaticPartMethod.setReturnPath(mapping,request);

        TawTreeBO tawTreeBO=new TawTreeBO();
        //�����
        TawSparepartBO bobo=new TawSparepartBO(ds);
        try{
        	List supplier=bobo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
            String StringTree=tawTreeBO.getMyTreeStr(4);
            if(!StringTree.equals("")){
            	request.setAttribute("StringTree",StringTree);
            }
        }
        catch(Exception ex){
        	generalError(request,ex);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }
    
    public ActionForward performUpdateview(ActionMapping mapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        form.setType("");
        form.setStorageid((String)request.getSession().getAttribute("storageid"));

        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            //������ʾ��ѯ���
            TawPartDAO tawPartDAO=new TawPartDAO(ds);
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
            if(request.getParameter("submit")!=null){
                condition=bo.getUpdateSql(form);
                request.getSession().removeAttribute("condition");
                request.getSession().setAttribute("condition",condition);

            }
            else{
                condition=(String)request.getSession().getAttribute("condition");
            }
            List sparepart=tawPartDAO.getPartList(condition,offset,length);
            Integer size=new Integer(tawPartDAO.getSize("taw_part",
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
    //add by wqw in hainan ��ѯ����
    public ActionForward performLoadview(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response){
    	TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        form.setType("");
        form.setStorageid((String)request.getSession().getAttribute("storageid"));

        try{
        	//ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            //������ʾ��ѯ���
            TawPartDAO tawPartDAO=new TawPartDAO(ds);
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
            if(request.getParameter("submit")!=null){
            	condition=bo.getUpdateSql(form);
                request.getSession().removeAttribute("condition");
                request.getSession().setAttribute("condition",condition);

            }
            else{
            	condition=(String)request.getSession().getAttribute("condition");
            }
            List sparepart=tawPartDAO.getPartList(condition,offset,length);
            Integer size=new Integer(tawPartDAO.getSize("taw_part",
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
     
    public ActionForward performUpdatepage(ActionMapping mapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        // TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        TawTreeBO treebo=new TawTreeBO();
        try{
            List supplier=bo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
            List company=bo.getClassMsg(450);
            request.setAttribute("company",company);
            List fixe=bo.getClassMsg(460);
            request.setAttribute("fixe",fixe);
            //ƴ�ַ�fixe
            String StringTree=treebo.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
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

    public ActionForward performUpdateover(ActionMapping mapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        try{
            //ת��
            //MyBeanUtils.copyPropertiesFromPageToDB(form);
            String msg=bo.updateTawSparePart(form);
            request.setAttribute("msg",msg);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performReturnover(ActionMapping mapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        String stotageId=(String)request.getSession().getAttribute("storageid");
        String overtime=form.getOuttime();
        try{
            bo.updateReturn(request.getParameterValues("return"),
                            request.getParameterValues("mangle"),
                            stotageId,overtime);

            request.setAttribute("msg",StaticPart.ORDER_OVER);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performServiceover(ActionMapping mapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response){
        //TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        try{
            bo.updateService(request.getParameterValues("servicein"));

            request.setAttribute("msg",StaticPart.ORDER_OVER);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performDrop(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        TawSparepartForm form=(TawSparepartForm)actionForm;
        TawSparepartBO bo=new TawSparepartBO(ds);
        try{
            String id=form.getId();
            String msg=bo.deletePart(id);

            request.setAttribute("msg",msg);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }

    public ActionForward performBackOver(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        //TawSparepartForm form=(TawSparepartForm)actionForm;

        TawSparepartBO bo=new TawSparepartBO(ds);
        String orderId=request.getParameter("order");
        try{
            String msg=bo.updateBack(request.getParameterValues("back"),orderId);
            request.setAttribute("msg",msg);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("ok");
    }

    public ActionForward performBatchPart(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
        UpLoadForm theForm=(UpLoadForm)actionForm;

        return mapping.findForward("ok");
    }

    public ActionForward performBatchOver(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
        UpLoadForm theForm=(UpLoadForm)actionForm;
        List errorList = null;
        FormFile file=theForm.getTheFile(); //ȡ���ϴ����ļ�
        String filePath=request.getRealPath("/sparepart/loadFile"); //ȡ��ǰϵͳ·��
        String storageid = (String)request.getSession().getAttribute("storageid");
        UpLoad.UpLoadFile(file,filePath);
        //System.out.println(filePath+"/"+file.getFileName());
        //
        try{
            TawBatchBO bo=new TawBatchBO(filePath+"/"+file.getFileName(),storageid,file.getFileName());
            errorList = bo.checkData();
            if(errorList.size()==0){
              boolean flag = bo.importData();
              if(flag){
                return mapping.findForward("ok");
              }
              else{
                return mapping.findForward("importno");
              }
            }
            else{
              request.setAttribute("errorList",errorList);
              return mapping.findForward("checkno");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            return mapping.findForward("no");
        }
    }

    public ActionForward performRemove(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
    TawSparepartForm form=(TawSparepartForm)actionForm;
    TawSparepartBO bo=new TawSparepartBO(ds);
    TawStorageBO bobo = new TawStorageBO(ds);
    form.setStorageid((String)request.getSession().getAttribute("storageid"));
    //�õ��ֿ��б�
    if(user_id.equalsIgnoreCase("admin")){
        ArrayList storage=(ArrayList)bobo.getStorageList();
        request.setAttribute("storage",storage);
    }
    else{
        request.setAttribute("storage",STORAGE);
    }
    bo.deleteRemoveTempId(user_id);
    //��¼�Ѿ�ѡ�еı���id����ʱ����
    if(request.getParameter("pp")!=null){
        String SumId=bo.getRemoveChoosedId(request.getParameterValues("id"),
                                     user_id);
        List choosed=bo.getTemppart(SumId);
        form.setSumid(SumId);
        request.getSession().removeAttribute("SumId");
        request.getSession().setAttribute("SumId",SumId);
        request.setAttribute("choosed",choosed);
    }
    else{
        String id=(String)request.getSession().getAttribute("SumId");
        form.setSumid(id);
        if(id!=null){
            List choosed=bo.getTemppart(id);
            request.setAttribute("choosed",choosed);
        }
    }
    try{
        //ת��
        //MyBeanUtils.copyPropertiesFromPageToDB(form);
        //������ʾ��ѯ���
        TawPartDAO tawPartDAO=new TawPartDAO(ds);
        int offset;
        int length=PAGE_LENGTH;
        String pageOffset=request.getParameter("pager.offset");
        if(pageOffset==null||pageOffset.equals("")){
            offset=0;
        }
        else{
            offset=Integer.parseInt(pageOffset);
        }
        /*
        String condition= " where storageid="+(String)request.getSession().getAttribute("storageid")+
            " and stateid in (11,12,15,19) "; //11���п���12����15����19��
            */
        String condition="";
        if(request.getParameter("submit")!=null){
            request.getSession().removeAttribute("SumId");
            condition=bo.getRemoveViewSql(form);
            request.getSession().removeAttribute("condition");
            request.getSession().setAttribute("condition",condition);
        }
        else{
            condition=(String)request.getSession().getAttribute("condition");
        }
        List sparepart=tawPartDAO.getPartList(condition,offset,length);
        Integer size=new Integer(tawPartDAO.getSize("taw_part",
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

    public ActionForward performRemoveOver(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        TawSparepartForm form=(TawSparepartForm)actionForm;
        form.setUserId(user_id);
        TawSparepartBO bo=new TawSparepartBO(ds);
        String storage_id = request.getParameter("storageId");
        try{
            /**
             * ��ɱ�����״̬�޸�
             */
            String over=bo.updateRemovePart(form.getSumid(),storage_id);

            request.setAttribute("msg",over);
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("ok");
    }
    public ActionForward performRemoveLoad(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
    TawTreeBO bo=new TawTreeBO();
    TawSparepartBO partbo=new TawSparepartBO(ds);
    try{
      //ƴ�ַ�
      String StringTree=bo.getMyTreeStr(4);
      if(!StringTree.equals("")){
          request.setAttribute("StringTree",StringTree);
      }
      //������
      List supplier=partbo.getClassMsg(6);
      request.setAttribute("supplier",supplier);
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
    String storageid = (String)request.getSession().getAttribute("storageid");
    if (bo.checkPart(form.getSerialno()) == true){
      bo.updatePart(form.getSerialno(),form.getBadserialno(), storageid);
      return mapping.findForward("ok");
    }
    else{
      return mapping.findForward("no");
    }
  }

  public ActionForward performToexport(ActionMapping mapping,
                                 ActionForm actionForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response){

    try{
        //������ʾ��ѯ���
        TawPartDAO tawPartDAO=new TawPartDAO(ds);
        int offset;
        int length = 1000000;
        String pageOffset=request.getParameter("pager.offset");
        if(pageOffset==null||pageOffset.equals("")){
            offset=0;
        }
        else{
            offset=Integer.parseInt(pageOffset);
        }
        String condition="";

        condition=" where storageid ='"+ request.getSession().getAttribute("storageid")+"'";
//            request.getSession().removeAttribute("condition");
//            request.getSession().setAttribute("condition",condition);

        List sparepart=tawPartDAO.getPartList(condition,offset,length);
        Integer size=new Integer(tawPartDAO.getSize("taw_part",
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

  public ActionForward performToexportall(ActionMapping mapping,
                                 ActionForm actionForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response){

    try{
        //������ʾ��ѯ���
        TawPartDAO tawPartDAO=new TawPartDAO(ds);
        int offset;
        int length = 1000000;
        String pageOffset=request.getParameter("pager.offset");
        if(pageOffset==null||pageOffset.equals("")){
            offset=0;
        }
        else{
            offset=Integer.parseInt(pageOffset);
        }
        String condition="";

        condition=" where storageid in ("+storageListStr+")";
//            request.getSession().removeAttribute("condition");
//            request.getSession().setAttribute("condition",condition);

        List sparepart=tawPartDAO.getPartList(condition,offset,length);
        Integer size=new Integer(tawPartDAO.getSize("taw_part",
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

  public ActionForward performAccessory(ActionMapping mapping,
                                 ActionForm actionForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
  TawSparepartForm form =(TawSparepartForm) actionForm;
  request.setAttribute("sparepartForm",form);
  return mapping.findForward("succes");
  }
  public ActionForward performInApp(ActionMapping mapping,
          ActionForm actionForm,
          HttpServletRequest request,
          HttpServletResponse response){
	  String type=(String)request.getParameter("type");
	  List typelist=new ArrayList();
	  if(type.equals("1")){//备品备件
		  String[] str7={"7","维护出库"};
		  typelist.add(str7);
		  String[] st8r={"8","维修出库"};
		  typelist.add(st8r);
		  String[] str9={"9","扩容出库"};
		  typelist.add(str9);
		  String[] str10={"10","报废出库"};
		  typelist.add(str10);
		  String[] str11={"11","其他出库"};
		  typelist.add(str11);		  
	  }else if(type.equals("0")){//物资管理
		  String[] str7={"7","维护出库"};
		  typelist.add(str7);
		  String[] st8r={"8","维修出库"};
		  typelist.add(st8r);
		  String[] str9={"9","扩容出库"};
		  typelist.add(str9);
		  String[] str10={"10","报废出库"};
		  typelist.add(str10);
	  }else if(type.equals("2")){//仪器仪表
		  String[] str7={"7","维护出库"};
		  typelist.add(str7);
		  String[] st8r={"8","维修出库"};
		  typelist.add(st8r);
		  String[] str9={"9","扩容出库"};
		  typelist.add(str9);
		  String[] str10={"10","报废出库"};
		  typelist.add(str10);
	  }
	  return mapping.findForward("ok");
  }
  public ActionForward performOutApp(ActionMapping mapping,
          ActionForm actionForm,
          HttpServletRequest request,
          HttpServletResponse response){
	  return mapping.findForward("ok");
  }  
  public ActionForward performInCheck(ActionMapping mapping,
          ActionForm actionForm,
          HttpServletRequest request,
          HttpServletResponse response){
	    int partType=StaticMethod.nullObject2int(request.getParameter("type"));
		TawSparepartBO bo=new TawSparepartBO(ds);
		TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
		TawOrderBO orderBo=new TawOrderBO(ds);
		TawStorageBO storagebo=new TawStorageBO(ds);
		TawSparepart sparepartpart=new TawSparepart();
		TawOrder order=new TawOrder();
		ArrayList orderlist= new ArrayList();
		try{
			List list=null;
			list=orderBo.getOrderPart("1");//��ȡ����˵��б�
			for (int i=0;i<list.size();i++){
				TawOrderPart tawOrderPart=new TawOrderPart();
				tawOrderPart=(TawOrderPart)list.get(i);
				sparepartpart=bo.getSparepart_a(Integer.parseInt(tawOrderPart.getSparepartId()));//�õ�һ�����Ϣ
				order=orderBo.getTawOrder(tawOrderPart.getOrderId());//�õ�һ�����Ϣ
				if(sparepartpart.getStorageid()==Integer.parseInt((String)request.getSession().getAttribute("storageid")) &&
						sparepartpart.getParttype()==partType){//����������͵��ж�.
					order.setSparepart_id(sparepartpart.getId());
					order.setOrderpart_id(tawOrderPart.getId());
					order.setStoragename(storagebo.getStorageName(String.valueOf(order.getStorageid())));//ѹ��ֿ�������
					orderlist.add(order);
				}
				
			}


		request.setAttribute("orderlist", orderlist);
		}
		catch(Exception e){
		generalError(request,e);
		return mapping.findForward("failure");
		}
		return mapping.findForward("ok");
	} 
  public ActionForward performInPass(ActionMapping mapping,
          ActionForm actionForm,
          HttpServletRequest request,
          HttpServletResponse response){
	    int partType=StaticMethod.nullObject2int(request.getParameter("type"));
		TawSparepartBO bo=new TawSparepartBO(ds);
		TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
		TawOrderBO orderBo=new TawOrderBO(ds);
		TawStorageBO storagebo=new TawStorageBO(ds);
		TawSparepart sparepartpart=new TawSparepart();
		TawOrder order=new TawOrder();		
		ArrayList orderlist= new ArrayList();
		try{
			List list=null;
			list=orderBo.getOrderPart("3");//��ȡ��������б�
			for (int i=0;i<list.size();i++){
				TawOrderPart tawOrderPart=new TawOrderPart();
				tawOrderPart=(TawOrderPart)list.get(i);
				sparepartpart=bo.getSparepart_a(Integer.parseInt(tawOrderPart.getSparepartId()));//�õ�һ�����Ϣ
				order=orderBo.getTawOrder(tawOrderPart.getOrderId());//�õ�һ�����Ϣ
				if(sparepartpart.getStorageid()==Integer.parseInt((String)request.getSession().getAttribute("storageid"))
						&& sparepartpart.getParttype()==partType){//����������͵��ж�.
					order.setSparepart_id(sparepartpart.getId());
					order.setOrderpart_id(tawOrderPart.getId());
					order.setState(tawOrderPart.getState());
					order.setStoragename(storagebo.getStorageName(String.valueOf(order.getStorageid())));//ѹ��ֿ�������
					orderlist.add(order);
				}
				
			}


		request.setAttribute("orderlist", orderlist);
		}
		catch(Exception e){
		generalError(request,e);
		return mapping.findForward("failure");
		}
		return mapping.findForward("ok");
	}   
	  public ActionForward performInCheckDetail(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawStorageBO storagebo=new TawStorageBO(ds);			
			TawSparepart sparepartpart=new TawSparepart();
			TawPart part=new TawPart();
			TawOrder order=new TawOrder();
			TawOrderPart tawOrderPart=new TawOrderPart();
			TawSparepartDAO sparepartdao=new TawSparepartDAO(ds);
			TawOrderPartDAO orderpartdao=new TawOrderPartDAO(ds);
			String order_id = request.getParameter("id");
			String sparepart_id = request.getParameter("sparepart_id");
			String orderpart_id = request.getParameter("orderpart_id");//��j��ID
			try{
				tawOrderPart=orderpartdao.getyOrderPartbyID(Integer.parseInt(orderpart_id));
				order=orderBo.getTawOrder(Integer.parseInt(order_id));
				part=sparepartdao.getSparepart_forTawPart(Integer.parseInt(sparepart_id));
				order.setState(tawOrderPart.getState());//state ��tawOrderPart�е�stateΪ׼.
				order.setStoragename(storagebo.getStorageName(String.valueOf(order.getStorageid())));//ѹ��ֿ�������
			request.setAttribute("orderpart_id", orderpart_id);
		    request.setAttribute("sparepart", part);
			request.setAttribute("order", order);
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		} 
	  public ActionForward performInPassDetail(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawStorageBO storagebo=new TawStorageBO(ds);
			TawSparepart sparepartpart=new TawSparepart();
			TawPart part=new TawPart();
			TawOrder order=new TawOrder();
			TawOrderPart tawOrderPart=new TawOrderPart();
			TawSparepartDAO sparepartdao=new TawSparepartDAO(ds);
			TawOrderPartDAO orderpartdao=new TawOrderPartDAO(ds);

			String order_id = request.getParameter("id");
			String sparepart_id = request.getParameter("sparepart_id");
			String orderpart_id = request.getParameter("orderpart_id");//��j��ID
			try{
				tawOrderPart=orderpartdao.getyOrderPartbyID(Integer.parseInt(orderpart_id));
				order=orderBo.getTawOrder(Integer.parseInt(order_id));
				part=sparepartdao.getSparepart_forTawPart(Integer.parseInt(sparepart_id));
				order.setState(tawOrderPart.getState());//state ��tawOrderPart�е�stateΪ׼.
				order.setStoragename(storagebo.getStorageName(String.valueOf(order.getStorageid())));//ѹ��ֿ�������
			request.setAttribute("orderpart_id", orderpart_id);
		    request.setAttribute("sparepart", part);
			request.setAttribute("order", order);
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		} 	  
	  public ActionForward performInCheckOK(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawSparepart sparepartpart=new TawSparepart();
			TawPart part=new TawPart();
			TawOrder order=new TawOrder();
			TawSparepartDAO sparepartdao=new TawSparepartDAO(ds);
			TawOrderPartDAO oderpartdao=new TawOrderPartDAO(ds);
			String advices = StaticMethod.nullObject2String(request.getParameter("advices")) ;//������
			String order_id = StaticMethod.nullObject2String(request.getSession().getAttribute("order_id")) ;
			String sparepart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("sparepart_id"));
			String orderpart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("orderpart_id"));//��j��ID
			try{
				//�������ű�.
				
				String sql_sparepart="set deleted='1' where id="+sparepart_id;
				String sql_order2="set advices='"+advices+"' , overgay='"+this.user_id+"' where id="+order_id;//����������
				String sql_order="set state='3' where id="+order_id;//�ɴ���˽��������
				String sql_oderpart="set state='3' where id="+orderpart_id;//�ɴ���˽��������
				sparepartdao.updateSparePart(sql_sparepart);
				orderBo.updateOrder(sql_order);
				orderBo.updateOrder(sql_order2);
				oderpartdao.updateOrderPart(sql_oderpart);
				request.setAttribute("msg","您的操作已成功");
				request.getSession().setAttribute("path","/EOMS_J2EE/sparepart/part/incheck.do");//�������ҳ�� ok.jsp.���÷��ش�����б�
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		} 	
	  public ActionForward performInPassOK(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawSparepart sparepartpart=new TawSparepart();
			TawPart part=new TawPart();
			TawOrder order=new TawOrder();
			TawOrderPart orderpart=new TawOrderPart();
			TawSparepartDAO sparepartdao=new TawSparepartDAO(ds);
			TawOrderPartDAO oderpartdao=new TawOrderPartDAO(ds);
			String advices = StaticMethod.nullObject2String(request.getParameter("advices")) ;//������
			String order_id = StaticMethod.nullObject2String(request.getSession().getAttribute("order_id")) ;
			String sparepart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("sparepart_id"));
			String orderpart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("orderpart_id"));//��j��ID
			try{
				//��������롡��������ű�.����
				String sql_sparepart="";
				String sql_order2="set advices='"+advices+"' , overgay='"+this.user_id+"' where id="+order_id;//����������
				String sql_order="set state='2' where id="+order_id;//�ɴ�����������ͨ��
				String sql_oderpart="set state='2' where id="+orderpart_id;//�ɴ�����������ͨ��
				order=orderBo.getTawOrder(StaticMethod.getIntValue(order_id));
				if(order.getType()==1){//�¼����
					 sql_sparepart="set deleted='0',state='11' where id="+sparepart_id;				
				}else if(order.getType()==2){//ά�����
					sql_sparepart="set deleted='0',state='12' where id="+sparepart_id;
				}else if(order.getType()==3){//�������
					sql_sparepart="set deleted='0',state='19' where id="+sparepart_id;
				}else if(order.getType()==7){//ά�����
					sql_sparepart="set deleted='0',state='14' where id="+sparepart_id;
				}else if(order.getType()==8){//ά�޳��
					sparepartdao.updateSpartpartRepairnum(StaticMethod.getIntValue(sparepart_id));
					sql_sparepart="set deleted='0',state='13' where id="+sparepart_id;
				}else if(order.getType()==9){//)�ݳ��
					sql_sparepart="set deleted='0',state='16' where id="+sparepart_id;
				}else if(order.getType()==10){//���ϳ��
					sql_sparepart="set deleted='0',state='15' where id="+sparepart_id;
				}else if(order.getType()==11){//������
					sql_sparepart="set deleted='0',state='17' where id="+sparepart_id;
				}else if(order.getType()==21){//���ʹ����¼����
					sql_sparepart="set deleted='0',state='11' where id="+sparepart_id;
				}else if(order.getType()==22){//���ʹ���ά�����
					sql_sparepart="set deleted='0',state='17' where id="+sparepart_id;
				}else if(order.getType()==23){//���ʹ����������
					sql_sparepart="set deleted='0',state='17' where id="+sparepart_id;
				}else if(order.getType()==31){//�����Ǳ��¼����
					sql_sparepart="set deleted='0',state='11' where id="+sparepart_id;
				}else if(order.getType()==32){//�����Ǳ�黹���
					sparepartdao.updateSpartpartLoannum(StaticMethod.getIntValue(sparepart_id));
					sql_sparepart="set deleted='0',state='18' where id="+sparepart_id;//18�ǹ黹��� ����.
				}else if(order.getType()==33){//�����Ǳ��������
					sql_sparepart="set deleted='0',state='19' where id="+sparepart_id;					
				}else if(order.getType()==26){//���ʹ���ά�����
					sql_sparepart="set deleted='0',state='14' where id="+sparepart_id;
				}else if(order.getType()==27){//���ʹ���ά�޳��
					sparepartdao.updateSpartpartRepairnum(StaticMethod.getIntValue(sparepart_id));
					sql_sparepart="set deleted='0',state='13' where id="+sparepart_id;
				}else if(order.getType()==28){//���ʹ���)�ݳ��
					sql_sparepart="set deleted='0',state='15' where id="+sparepart_id;
				}else if(order.getType()==29){//���ʹ��?�ϳ��
					sql_sparepart="set deleted='0',state='16' where id="+sparepart_id;
				}else if(order.getType()==30){//���ʹ���������
					sql_sparepart="set deleted='0',state='17' where id="+sparepart_id;
				}else if(order.getType()==35){//�����Ǳ���ó��
					sql_sparepart="set deleted='0',state='20' where id="+sparepart_id;
				}else if(order.getType()==36){//�����Ǳ�����
					sparepartdao.updateSpartpartChecksum(StaticMethod.getIntValue(sparepart_id));
					sql_sparepart="set deleted='0',state='21' where id="+sparepart_id;
				}
				sparepartdao.updateSparePart(sql_sparepart);
				orderBo.updateOrder(sql_order);
				orderBo.updateOrder(sql_order2);
				oderpartdao.updateOrderPart(sql_oderpart);
				request.setAttribute("msg","��Ĳ����ѳɹ�");
				request.getSession().setAttribute("path","/EOMS_J2EE/sparepart/part/inpass.do");//�������ҳ�� ok.jsp.���÷��ش������б�
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		} 	  
	  public ActionForward performInCheckNO(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawSparepart sparepartpart=new TawSparepart();
			TawPart part=new TawPart();
			TawOrder order=new TawOrder();
			TawSparepartDAO sparepartdao=new TawSparepartDAO(ds);
			TawOrderPartDAO oderpartdao=new TawOrderPartDAO(ds);
			String advices = StaticMethod.nullObject2String(request.getParameter("advices")) ;//������
			String order_id = StaticMethod.nullObject2String(request.getSession().getAttribute("order_id")) ;
			String sparepart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("sparepart_id"));
			String orderpart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("orderpart_id"));//��j��ID
			try{
				order=orderBo.getTawOrder(Integer.parseInt(order_id));
				String url="";
				if(order.getState()==3){
					url="/EOMS_J2EE/sparepart/part/inpass.do";//�������б�
				}else{
					url="/EOMS_J2EE/sparepart/part/incheck.do";//������б�
				}
				//�������ű�.
				//String sql_sparepart="set deleted='0' where id="+sparepart_id;
				String sql_order="set advices='"+advices+"' , overgay='"+this.user_id+"' where id="+order_id;//����������
				String sql_order2="set state='4' where id="+order_id;//�ɴ���˽��벵��������
				String sql_oderpart="set state='4' where id="+orderpart_id;//����������
				//sparepartdao.updateSparePart(sql_sparepart);
				orderBo.updateOrder(sql_order);
				orderBo.updateOrder(sql_order2);
				oderpartdao.updateOrderPart(sql_oderpart);
				request.setAttribute("msg","您的操作已成功");
				request.getSession().setAttribute("path",url);//�������ҳ�� ok.jsp.���÷��ش������б�			
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		} 	  
	  	  
	  public ActionForward performInAppBack(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
		    int partType=StaticMethod.nullObject2int(request.getParameter("type"));
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawStorageBO storagebo=new TawStorageBO(ds);
			TawSparepart sparepartpart=new TawSparepart();
			TawOrder order=new TawOrder();		
			ArrayList orderlist= new ArrayList();
			try{
				List list=null;
				list=orderBo.getOrderPart("4");//��ȡ�����ص��б�
				for (int i=0;i<list.size();i++){
					TawOrderPart tawOrderPart=new TawOrderPart();
					tawOrderPart=(TawOrderPart)list.get(i);
					sparepartpart=bo.getSparepart_a(Integer.parseInt(tawOrderPart.getSparepartId()));//�õ�һ�����Ϣ
					order=orderBo.getTawOrder(tawOrderPart.getOrderId());//�õ�һ�����Ϣ
					if(sparepartpart.getStorageid()==Integer.parseInt((String)request.getSession().getAttribute("storageid"))
							&& sparepartpart.getParttype()==partType){//����������͵��ж�.
						order.setSparepart_id(sparepartpart.getId());
						order.setOrderpart_id(tawOrderPart.getId());
						order.setState(tawOrderPart.getState());
						order.setStoragename(storagebo.getStorageName(String.valueOf(order.getStorageid())));//ѹ��ֿ�������
						orderlist.add(order);
					}
					
				}


			request.setAttribute("orderlist", orderlist);
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		}  	  
	  public ActionForward performAppBackDetail(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawStorageBO storagebo=new TawStorageBO(ds);			
			TawSparepart sparepartpart=new TawSparepart();
			TawPart part=new TawPart();
			TawOrder order=new TawOrder();
			TawOrderPart tawOrderPart=new TawOrderPart();
			TawSparepartDAO sparepartdao=new TawSparepartDAO(ds);
			TawOrderPartDAO orderpartdao=new TawOrderPartDAO(ds);
			String order_id = request.getParameter("id");
			String sparepart_id = request.getParameter("sparepart_id");
			String orderpart_id = request.getParameter("orderpart_id");//��j��ID
			try{
				tawOrderPart=orderpartdao.getyOrderPartbyID(Integer.parseInt(orderpart_id));
				order=orderBo.getTawOrder(Integer.parseInt(order_id));
				part=sparepartdao.getSparepart_forTawPart(Integer.parseInt(sparepart_id));
				order.setState(tawOrderPart.getState());//state ��tawOrderPart�е�stateΪ׼.
				order.setStoragename(storagebo.getStorageName(String.valueOf(order.getStorageid())));//ѹ��ֿ�������
			request.setAttribute("orderpart_id", orderpart_id);
		    request.setAttribute("sparepart", part);
			request.setAttribute("order", order);
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		} 	  
	  public ActionForward performAppBackCheck(ActionMapping mapping,
	          ActionForm actionForm,
	          HttpServletRequest request,
	          HttpServletResponse response){
			TawSparepartBO bo=new TawSparepartBO(ds);
			TawOrderDetailBO detailBo=new TawOrderDetailBO(ds);
			TawOrderBO orderBo=new TawOrderBO(ds);
			TawSparepart sparepartpart=new TawSparepart();
			TawPart part=new TawPart();
			TawOrder order=new TawOrder();
			TawSparepartDAO sparepartdao=new TawSparepartDAO(ds);
			TawOrderPartDAO oderpartdao=new TawOrderPartDAO(ds);
			String advices = StaticMethod.nullObject2String(request.getParameter("advices")) ;//������
			String order_id = StaticMethod.nullObject2String(request.getSession().getAttribute("order_id")) ;
			String sparepart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("sparepart_id"));
			String orderpart_id = StaticMethod.nullObject2String(request.getSession().getAttribute("orderpart_id"));//��j��ID
			try{
				order=orderBo.getTawOrder(Integer.parseInt(order_id));

				//����һ�ű�.
				//String sql_sparepart="set deleted='0' where id="+sparepart_id;
//				String sql_order="set advices='"+advices+"' , overgay='"+this.user_id+"' where id="+order_id;//����������
//				String sql_order2="set state='4' where id="+order_id;//�ɴ���˽��벵��������
				String sql_oderpart="set state='5' where id="+orderpart_id;//������ȷ�ϲ��� .����ɾ��״̬
				//sparepartdao.updateSparePart(sql_sparepart);
//				orderBo.updateOrder(sql_order);
//				orderBo.updateOrder(sql_order2);
				oderpartdao.updateOrderPart(sql_oderpart);
				request.setAttribute("msg","您的操作已成功");
				request.getSession().setAttribute("path","/EOMS_J2EE/sparepart/part/appback.do");//���ز����б�ҳ��			
			}
			catch(Exception e){
			generalError(request,e);
			return mapping.findForward("failure");
			}
			return mapping.findForward("ok");
		} 		  
}
