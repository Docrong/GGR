package com.boco.eoms.sparepart.controller;

import java.util.ResourceBundle;
import java.util.List;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.sparepart.bo.TawOrderBO;
import com.boco.eoms.sparepart.bo.TawOrderDetailBO;
import com.boco.eoms.sparepart.bo.TawSparepartBO;
import com.boco.eoms.sparepart.bo.TawTreeBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sparepart.model.TawOrder;
import com.boco.eoms.sparepart.model.TawOrderDetail;
import com.boco.eoms.sparepart.bo.TawClassMsgBO;
import com.boco.eoms.sparepart.model.TawClassMsg;
import java.util.ArrayList;
import com.boco.eoms.sparepart.bo.*;
import org.apache.struts.action.*;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.common.util.*;
import org.apache.struts.upload.*;
import com.boco.eoms.sparepart.dao.TawOrderDAO;

import com.boco.eoms.sparepart.bo.TawStorageBO;
import com.boco.eoms.sparepart.dao.TawStorageDAO;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawOrderAction extends Action{
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
            user_name=StaticMethod.null2String(saveSessionBeanForm.getUsername());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*Ȩ����֤
        try{
            if(mapping.getPath().equals("/part/outapp") || mapping.getPath().equals("/part/inapp") 
            	|| mapping.getPath().equals("/part/term")){
            	request.getSession().setAttribute("privFlag", "true");//���⴦�?.Ȩ�޿���
        	}else{
        		request.getSession().removeAttribute("privFlag");  
        	}
            TawValidatePrivBO tawValidateBO=new TawValidatePrivBO(ds);
            if(!tawValidateBO.validPriv(user_id,mapping.getPath())){
                return mapping.findForward("nopriv");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        */
        /**
         * @ ѡ��ֿ�
         */
        if(request.getSession().getAttribute("storage")==null){
            //����
            StaticPartMethod.setReturnPath(mapping,request);

            ActionForward actionForward=new ActionForward(
                  "/storage/choose.do");
            //System.out.println("Hello"+mapping.getPath());
            return actionForward;
        }
        //
        if(isCancelled(request)){
            return mapping.findForward("failure");
        }

        if("".equalsIgnoreCase(myaction)){
            myforward=mapping.findForward("failure");
        }
        else if("LOAD".equalsIgnoreCase(myaction)){
            myforward=performLoad(mapping,form,request,response);
        }
        else if("USED".equalsIgnoreCase(myaction)){
          myforward=performUsed(mapping,form,request,response);
       }
        else if("SERVICEOUT".equalsIgnoreCase(myaction)){
            myforward=performServiceout(mapping,form,request,response);
        }
        else if("FAULT".equalsIgnoreCase(myaction)){
            myforward=performFault(mapping,form,request,response);
        }
        else if("REJECT".equalsIgnoreCase(myaction)){
            myforward=performReject(mapping,form,request,response);
        }
        else if("ENLARGE".equalsIgnoreCase(myaction)){
            myforward=performEnlarge(mapping,form,request,response);
        }
        else if("TRANSFER".equalsIgnoreCase(myaction)){
            myforward=performTransfer(mapping,form,request,response);
        }
        else if("TERM".equalsIgnoreCase(myaction)){
            myforward=performTerm(mapping,form,request,response);
        }
        else if("Forremove".equalsIgnoreCase(myaction)){
        	myforward=performForRemove(mapping,form,request,response);
        }
        else if("RETURN".equalsIgnoreCase(myaction)){
            myforward=performReturn(mapping,form,request,response);
        }
        else if("FORRETURN".equalsIgnoreCase(myaction)){
        	myforward=performForReturn(mapping,form,request,response);
        }
        else if("RETURNCONTENT".equalsIgnoreCase(myaction)){
            myforward=performReturnContent(mapping,form,request,response);
        }
        else if("SERVICEIN".equalsIgnoreCase(myaction)){
            myforward=performServicein(mapping,form,request,response);
        }
        else if("SERVICECONTENT".equalsIgnoreCase(myaction)){
            myforward=performServiceContent(mapping,form,request,response);
        }
        else if("BACK".equalsIgnoreCase(myaction)){
            myforward=performBack(mapping,form,request,response);
        }
        else if("BACKCONTENT".equalsIgnoreCase(myaction)){
            myforward=performBackContent(mapping,form,request,response);
        }
        else if("UPLOAD".equalsIgnoreCase(myaction)){
            myforward=performUpLoad(mapping,form,request,response);
        }
        else if("UPLOADOVER".equalsIgnoreCase(myaction)){
            myforward=performUpLoadOver(mapping,form,request,response);
        }
        else if("OUTAPP".equalsIgnoreCase(myaction)){//������뵥
            myforward=performOutApp(mapping,form,request,response);
        }       
        else if("INAPP".equalsIgnoreCase(myaction)){//������뵥
            myforward=performInApp(mapping,form,request,response);
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

    public ActionForward performLoad(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);
        TawOrderForm form=(TawOrderForm)actionForm;

        List list = null;
        TawStorageBO bo=new TawStorageBO(ds);
        try{
            list = bo.getDept("1");
            String storageTree = bo.getStorageTreeStr();
            request.setAttribute("DEPT",list);
            request.setAttribute("StorageTree", storageTree);
          
            //���� type=1
            //�¹涨 ���õ��� type=6
            form.setOrderType("6");
            request.setAttribute("userName",user_name);
            request.setAttribute("userId",this.user_id);
            request.setAttribute("msg",StaticPart.ORDER_NAME[1]);
            String sheetid=StaticPartMethod.getOrdernumber("6");//ȡ�ù���ID
            request.setAttribute("sheetid",sheetid);
            ArrayList storage=(ArrayList)bo.getStorageList();
            request.setAttribute("storage",storage);

            return mapping.findForward("ok");
        }
        catch(Exception e){
            generalError(request,e);
            return mapping.findForward("failure");
        }


    }
    public ActionForward performUsed(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
       //����
       StaticPartMethod.setReturnPath(mapping,request);

       TawOrderForm form=(TawOrderForm)actionForm;
       //���õ��� type=2
       form.setOrderType("2");
       request.setAttribute("userId",user_name);
       request.setAttribute("msg",StaticPart.ORDER_NAME[2]);
       return mapping.findForward("ok");
   }


    public ActionForward performServiceout(ActionMapping mapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);
        TawOrderForm form=(TawOrderForm)actionForm;
        //ά�޳�ⵥ�� type=3
        form.setOrderType("3");
        request.setAttribute("userId",user_name);
        request.setAttribute("msg",StaticPart.ORDER_NAME[3]);
        return mapping.findForward("ok");
    }

    public ActionForward performFault(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawOrderForm form=(TawOrderForm)actionForm;
        //���޵��� type=4
        form.setOrderType("4");
        request.setAttribute("userId",user_name);
        request.setAttribute("msg",StaticPart.ORDER_NAME[4]);
        return mapping.findForward("ok");
    }

    public ActionForward performReject(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawOrderForm form=(TawOrderForm)actionForm;
        //���ϵ��� type=5
        form.setOrderType("5");
        request.setAttribute("userId",user_name);
        request.setAttribute("msg",StaticPart.ORDER_NAME[5]);
        return mapping.findForward("ok");
    }

    public ActionForward performEnlarge(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);
        TawOrderForm form=(TawOrderForm)actionForm;
        //)�ݵ��� type=7
        form.setOrderType("7");
        request.setAttribute("userId",user_name);
        request.setAttribute("msg",StaticPart.ORDER_NAME[7]);
        return mapping.findForward("ok");
    }


    public ActionForward performTransfer(ActionMapping mapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

        TawOrderForm form=(TawOrderForm)actionForm;
        //ת�ⵥ�� type=6
        form.setOrderType("*");
        request.setAttribute("userId",user_name);
        request.setAttribute("msg",StaticPart.ORDER_NAME[6]);
        return mapping.findForward("ok");
    }

    public ActionForward performTerm(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        TawOrderForm form=(TawOrderForm)actionForm;
        /**
         * ��ɵ��ݵĲ��빦��
         */
        TawOrderBO BO=new TawOrderBO(ds);
        TawTreeBO bobo=new TawTreeBO();
        TawOrderDAO orderdao=new TawOrderDAO(ds);
        TawSparepartBO partbo=new TawSparepartBO(ds);
        TawStorageBO storagebo=new TawStorageBO(ds);
        try{
            if(request.getParameter("proposer")!=null){
                String storageid=(String)request.getSession().getAttribute("storageid");
                form.setStorageid(storageid);
                //ת��
                //MyBeanUtils.copyPropertiesFromPageToDB(form);
                //orderdao.deletePart();//ɾ��taw-sp-order����stateΪ0�ļ�¼��Ϊ��Ч��¼ ��ֹ����������Ҫ����.��Ҫ��һ��ǰ������
                orderdao.deletePart_byuserId(this.user_id);
                int order_id=BO.insertOrder(form);
                BO.deleteTempId(user_id,Integer.toString(order_id));//�����ʱ������ڵ�ѡ��ı���
                form.setOrderId(Integer.toString(order_id));
                form.setOrderType(request.getParameter("orderType"));
            }
            //ƴ�ַ�
            String StringTree="";//bobo.getMyTreeStr(4);
            if(!StringTree.equals("")){
                request.setAttribute("StringTree",StringTree);
            }
            //������
            List supplier=partbo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
        }
        catch(Exception ex){
            generalError(request,ex);
            return mapping.findForward("failure");
        }

        request.getSession().removeAttribute("orderId");
        request.getSession().removeAttribute("orderType");
        request.getSession().removeAttribute("SumId");
        request.getSession().setAttribute("orderId",form.getOrderId());
        request.getSession().setAttribute("orderType",form.getOrderType());

        request.getSession().setAttribute("destStorageId",storagebo.getStorageIdByName(form.getDestStorageId()));
//        if(form.getOrderType().equals("1") || form.getOrderType().equals("5") || form.getOrderType().equals("7")){
//          return mapping.findForward("upload");
//        }
        if(form.getOrderType().equals("1") || form.getOrderType().equals("3") || 
           form.getOrderType().equals("31") || form.getOrderType().equals("33")||//��������Ǳ��������33
        	form.getOrderType().equals("21")|| form.getOrderType().equals("23") ){//������ʹ����������33
    	  return mapping.findForward("newsparepart");
        }else{
        	return mapping.findForward("toview");
        }
        //return mapping.findForward("ok");
    }
    
    
     
    
    public ActionForward performForRemove(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response){
    	TawOrderForm form=(TawOrderForm)actionForm;

        
        TawTreeBO bobo=new TawTreeBO();        
        TawSparepartBO partbo=new TawSparepartBO(ds);
        TawStorageBO storagebo=new TawStorageBO(ds);
        try{
        	if(request.getParameter("proposer")!=null){
        		String storageid=(String)request.getSession().getAttribute("storageid");
                form.setStorageid(storageid);
                //ת��
                //MyBeanUtils.copyPropertiesFromPageToDB(form);    
            }
            //ƴ�ַ�
            String StringTree=bobo.getMyTreeStr(4);
            if(!StringTree.equals("")){
            	request.setAttribute("StringTree",StringTree);
            }
            //������
            List supplier=partbo.getClassMsg(6);
            request.setAttribute("supplier",supplier);
        }
        catch(Exception ex){
        	generalError(request,ex);
            return mapping.findForward("failure");
        }
        request.getSession().setAttribute("destStorageId",storagebo.getStorageIdByName(form.getDestStorageId()));

        return mapping.findForward("ok");
}
    
    public ActionForward performReturn(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

       // TawOrderForm form=(TawOrderForm)actionForm;
        TawOrderDetailBO bo=new TawOrderDetailBO(ds);
        //�õ�δ�����Ľ��ݣ�type=8�����뵥�ݣ�state=1 δ����
        //�˴�Ӧ�ò��ҽ��� type=1 ���ܻ����ȷ�Ĺ黹�����б? ����κ dww �޸� 070308
        String storageId=(String)request.getSession().getAttribute("storageid");
        //List tawOrderDetail=bo.getTawOrderDetailByStorageId(storageId,8,1);
        String supplier=request.getParameter("supplier");        
        String serialno=request.getParameter("serialno");        
        String objectname=request.getParameter("objectname");       
        //update by wqw �����黹ʱ��Ӳ�ѯ
        List tawOrderDetail=bo.getTawOrderDetailByStorageId(storageId,1,1,supplier,serialno,objectname);
        request.setAttribute("tawOrderDetail",tawOrderDetail);

        return mapping.findForward("ok");
    }

    public ActionForward performReturnContent(ActionMapping mapping,
                                              ActionForm actionForm,
                                              HttpServletRequest request,
                                              HttpServletResponse response){
        TawOrderForm form=(TawOrderForm)actionForm;
        TawOrderBO bo=new TawOrderBO(ds);
        //���orderId����δ����ı�����Ϣ
        List sparepart=bo.getSparepart(form.getId());
        request.setAttribute("order",Integer.toString(form.getId()));
        request.setAttribute("sparepart",sparepart);

        return mapping.findForward("ok");
    }
    
    public ActionForward performForReturn(ActionMapping mapping,
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

    public ActionForward performServicein(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
      //����
      StaticPartMethod.setReturnPath(mapping,request);

      // TawOrderForm form=(TawOrderForm)actionForm;
      TawOrderDetailBO bo=new TawOrderDetailBO(ds);
      //�õ�δ������ά�޳�ⵥ�ݣ�type=1��ά�޳�ݣ�state=1 δ����
      String storageId=(String)request.getSession().getAttribute("storageid");
      List tawOrderDetail=bo.getTawOrderDetailByStorageId(storageId,3,1);
      request.setAttribute("tawOrderDetail",tawOrderDetail);

      return mapping.findForward("ok");
    }

    public ActionForward performServiceContent(ActionMapping mapping,
                                               ActionForm actionForm,
                                               HttpServletRequest request,
                                               HttpServletResponse response){
        TawOrderForm form=(TawOrderForm)actionForm;
        TawOrderBO bo=new TawOrderBO(ds);
        //���orderId����δ����ı�����Ϣ
        List sparepart=bo.getSparepart(form.getId());
        request.setAttribute("order",Integer.toString(form.getId()));
        request.setAttribute("sparepart",sparepart);

        return mapping.findForward("ok");
    }

    public ActionForward performBack(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        //����
        StaticPartMethod.setReturnPath(mapping,request);

       // TawOrderForm form=(TawOrderForm)actionForm;
        TawOrderBO bo=new TawOrderBO(ds);
        //�õ�δ������ά�޳�ⵥ�ݣ�type=1��ά�޳�ݣ�state=1 δ����
        String storageId=(String)request.getSession().getAttribute("storageid");
        String sql=" where state=1 and storageid='"+storageId+"'";
        List taworder=bo.getTawOrder(sql);
        request.setAttribute("taworder",taworder);

        return mapping.findForward("ok");
    }

    public ActionForward performBackContent(ActionMapping mapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response){
        TawOrderForm form=(TawOrderForm)actionForm;
        TawOrderBO bo=new TawOrderBO(ds);
        //���orderId����δ����ı�����Ϣ
        List sparepart=bo.getSparepart(form.getId());
        request.setAttribute("order",Integer.toString(form.getId()));
        request.setAttribute("sparepart",sparepart);

        return mapping.findForward("ok");
    }

    /**
     * �ϴ�����
     */
    public ActionForward performUpLoad(ActionMapping mapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response){
        UpLoadForm form=(UpLoadForm)actionForm;
        return mapping.findForward("ok");
    }
    //ԭ�ϴ��ļ�����
    public ActionForward performUpLoadOverOld(ActionMapping mapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response){
       UpLoadForm theForm=(UpLoadForm)actionForm;
       TawOrderBO BO = new TawOrderBO(ds);
       TawTreeBO bobo=new TawTreeBO();
       TawSparepartBO partbo=new TawSparepartBO(ds);
       FormFile file=theForm.getTheFile(); //ȡ���ϴ����ļ�
       String filePath=request.getRealPath("/sparepart/serverfile"); //ȡ��ǰϵͳ·��
       String orderid = (String)request.getSession().getAttribute("orderId");
       String ordertype = (String)request.getSession().getAttribute("orderType");
       String sql = "";
       theForm.setOrderId(orderid);
       theForm.setOrderType(ordertype);
       try{
         //ƴ�ַ�
         String StringTree=bobo.getMyTreeStr(4);
         if(!StringTree.equals("")){
             request.setAttribute("StringTree",StringTree);
         }
         //������
         List supplier=partbo.getClassMsg(6);
         request.setAttribute("supplier",supplier);
       }
       catch(Exception ex){
         generalError(request, ex);
         return mapping.findForward("failure");
       }
       request.getSession().removeAttribute("orderId");
       request.getSession().removeAttribute("orderType");
       request.getSession().setAttribute("orderId",theForm.getOrderId());
       request.getSession().setAttribute("orderType",theForm.getOrderType());
       //����ļ��Ƿ��ظ�
       if (BO.check(file,filePath) == true){
         //�ϴ��Ƿ�ɹ�
         if (UpLoad.UpLoadFile(file,filePath) == "UPLOADOK"){
           sql = "set ACCESSORY = '/sparepart/serverfile/"
               + file.getFileName() + "' where id = " + orderid;
           BO.updateOrder(sql);
           return mapping.findForward("ok");
         }
         else{
           return mapping.findForward("uploadfileno");
         }
       }
       else{
         return mapping.findForward("checkfileno");
       }
   }

   public ActionForward performUpLoadOver(ActionMapping mapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response){
       TawSparepartForm sparepartForm = (TawSparepartForm) request.getAttribute("sparepartForm");
       UpLoadForm theForm=(UpLoadForm)actionForm;
       TawOrderBO BO = new TawOrderBO(ds);
       TawTreeBO bobo=new TawTreeBO();
       TawSparepartBO partbo=new TawSparepartBO(ds);
       FormFile file=theForm.getTheFile(); //ȡ���ϴ����ļ�
       String filePath=request.getRealPath("/sparepart/serverfile"); //ȡ��ǰϵͳ·��
       String sql = "";
       try{
         //ƴ�ַ�
         String StringTree=bobo.getMyTreeStr(4);
         if(!StringTree.equals("")){
             request.setAttribute("StringTree",StringTree);
         }
         //������
         List supplier=partbo.getClassMsg(6);
         request.setAttribute("supplier",supplier);
       }
       catch(Exception ex){
         generalError(request, ex);
         return mapping.findForward("failure");
       }
       request.getSession().removeAttribute("orderId");
       request.getSession().removeAttribute("orderType");
       request.getSession().setAttribute("orderId",theForm.getOrderId());
       request.getSession().setAttribute("orderType",theForm.getOrderType());
       /*
       //����ļ��Ƿ��ظ�
       if (BO.check(file,filePath) == true){
         //�ϴ��Ƿ�ɹ�
         if (UpLoad.UpLoadFile(file,filePath) == "UPLOADOK"){
           sql = "set ACCESSORY = '/sparepart/serverfile/"
               + file.getFileName() + "' where id = " + theForm.getOrderId();
           BO.updateOrder(sql);
           sparepartForm.setAccessory(file.getFileName());
           request.setAttribute("sparepart",sparepartForm);
           return mapping.findForward("ok");
         }
         else{
           return mapping.findForward("uploadfileno");
         }
       }
       else{
         return mapping.findForward("checkfileno");
       }
       */
      return mapping.findForward("failure");
  }
   public ActionForward performOutApp(ActionMapping mapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response){
				//����
				StaticPartMethod.setReturnPath(mapping,request);
				TawOrderForm form=(TawOrderForm)actionForm;
				String crrstoragename=(String)request.getSession().getAttribute("storage");
				List list = null;
				TawStorageBO bo=new TawStorageBO(ds);
				TawTreeBO bobo=new TawTreeBO();
				TawSparepartBO partbo=new TawSparepartBO(ds);
			try{
				list = bo.getDept("1");
				String storageTree = bo.getStorageTreeStr();
				String xmltree=bobo.getTreeNodesForXml(0);//��רҵ����Ƶ�jϵ
                List objtypelist=partbo.getobjTypeList(bo.getStorageIdByName(crrstoragename));
                request.setAttribute("objtypelist",objtypelist);//�������͡�				
                List positionlist=partbo.getPositionList(bo.getStorageIdByName(crrstoragename));
                request.setAttribute("positionlist",positionlist);//����������λ����-ʵ��ѡ��
				request.setAttribute("DEPT",list);
				request.setAttribute("StorageTree", storageTree);
				request.setAttribute("xmltree", xmltree);
				
				request.setAttribute("userName",user_name);
				request.setAttribute("userId",this.user_id);
				request.setAttribute("msg",StaticPart.ORDER_NAME[1]);
				String sheetid=StaticPartMethod.getOrdernumber("*");//ȡ�ù���ID
				request.setAttribute("sheetid",sheetid);
				ArrayList storage=(ArrayList)bo.getStorageList();
				request.setAttribute("storage",storage);
				//�趨�������
				  String type=(String)request.getParameter("type");
				  request.getSession().setAttribute("partType", type);//����session��,����������ʱʹ�á�
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
					  String[] str7={"26","维护出库"};
					  typelist.add(str7);
					  String[] st8r={"27","维修出库"};
					  typelist.add(st8r);
					  String[] str9={"28","扩容出库"};
					  typelist.add(str9);
					  String[] str10={"29","报废出库"};
					  typelist.add(str10);
					  String[] str11={"30","其他出库"};
					  typelist.add(str11);					  
					  
				  }else if(type.equals("2")){//仪器仪表
					  String[] str7={"35","借用出库"};
					  typelist.add(str7);
					  String[] st8r={"36","检测出库"};
					  typelist.add(st8r);
					
				  }
				  request.setAttribute("typelist", typelist);
				return mapping.findForward("ok");
			}
			catch(Exception e){
				generalError(request,e);
				return mapping.findForward("failure");
			}		
	}
   public ActionForward performInApp(ActionMapping mapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response){
				//����
				StaticPartMethod.setReturnPath(mapping,request);
				TawOrderForm form=(TawOrderForm)actionForm;
				String crrstoragename=(String)request.getSession().getAttribute("storage");
				List list = null;
				TawStorageBO bo=new TawStorageBO(ds);
				TawTreeBO bobo=new TawTreeBO();
				TawSparepartBO partbo=new TawSparepartBO(ds);
			try{
				list = bo.getDept("1");
				String storageTree = bo.getStorageTreeStr();
				String xmltree=bobo.getTreeNodesForXml(0);//��רҵ����Ƶ�jϵ
                List objtypelist=partbo.getobjTypeList(bo.getStorageIdByName(crrstoragename));
                request.setAttribute("objtypelist",objtypelist);//�������͡�				
                List positionlist=partbo.getPositionList(bo.getStorageIdByName(crrstoragename));
                request.setAttribute("positionlist",positionlist);//����������λ����-ʵ��ѡ��
				request.setAttribute("DEPT",list);
				request.setAttribute("StorageTree", storageTree);
				request.setAttribute("xmltree", xmltree);
				
				request.setAttribute("userName",user_name);
				request.setAttribute("userId",this.user_id);
				request.setAttribute("msg",StaticPart.ORDER_NAME[1]);
				String sheetid=StaticPartMethod.getOrdernumber("*");//ȡ�ù���ID
				request.setAttribute("sheetid",sheetid);
				ArrayList storage=(ArrayList)bo.getStorageList();
				request.setAttribute("storage",storage);
				//�趨�������
				  String type=(String)request.getParameter("type");
				  request.getSession().setAttribute("partType", type);//����session��,����������ʱʹ�á�
				  List typelist=new ArrayList();
				  if(type.equals("1")){//备品备件
					  String[] str1={"1","新件入库"};
					  typelist.add(str1);
					  String[] str2={"2","维修入库"};
					  typelist.add(str2);
					  String[] str3={"3","其他入库"};
					  typelist.add(str3);
//					  String[] str10={"10","报废出库"};
//					  typelist.add(str10);
//					  String[] str11={"11","其他出库"};
//					  typelist.add(str11);		  
				  
				  }else if(type.equals("0")){//物资管理
					  String[] str1={"21","新件入库"};
					  typelist.add(str1);
					  String[] str2={"22","维修入库"};
					  typelist.add(str2);
					  String[] str3={"23","其他入库"};
					  typelist.add(str3);
					  
				  }else if(type.equals("2")){//仪器仪表
					  String[] str7={"31","新件入库"};
					  typelist.add(str7);
					  String[] st8r={"32","归还入库"};
					  typelist.add(st8r);
					  String[] st9r={"33","其他入库"};//增加其他入库
					  typelist.add(st9r);
					
				  }
				  request.setAttribute("typelist", typelist);
				return mapping.findForward("ok");
			}
			catch(Exception e){
				generalError(request,e);
				return mapping.findForward("failure");
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

}
