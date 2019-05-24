package com.boco.eoms.sheet.complaint.service.bo;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.interfaces.eomsReplyTicket.EomsReplyTicketLocator;
import com.boco.eoms.interfaces.eomsReplyTicket.EomsReplyTicketPortType;
import com.boco.eoms.interfaces.eomsReplyTicket.EomsReply;
import com.boco.eoms.interfaces.eomsReplyTicket.EomsTicket;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.webapp.action.ComplaintAction;

public class EomsReplyTicketClient  extends ComplaintAction {
		
	public static int  setEomsInfo( ComplaintMain mainObject ) {
		  EomsTicket ticket = new EomsTicket();
		   int result=-1;
	      ID2NameService m = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		 if(mainObject!=null ){
			   String  type1 =StaticMethod.null2String(mainObject.getComplaintType1());
			   String  type2 =StaticMethod.null2String(mainObject.getComplaintType2());
			   String  type3 =StaticMethod.null2String(mainObject.getComplaintType());
			   String  type4 =StaticMethod.null2String(mainObject.getComplaintType4());
			   String  type5 =StaticMethod.null2String(mainObject.getComplaintType5());
			   String  type6 =StaticMethod.null2String(mainObject.getComplaintType6());
			   String  type7 =StaticMethod.null2String(mainObject.getComplaintType7());
			   String complaintTypeCn =""+ m.id2Name(type1, "ItawSystemDictTypeDao")+","+m.id2Name(type2, "ItawSystemDictTypeDao")+","+m.id2Name(type3, "ItawSystemDictTypeDao")+","+m.id2Name(type4, "ItawSystemDictTypeDao")+","+m.id2Name(type5, "ItawSystemDictTypeDao")+","+m.id2Name(type6, "ItawSystemDictTypeDao")+","+m.id2Name(type7, "ItawSystemDictTypeDao");
    		   ticket.setEomsSeq(StaticMethod.null2String(mainObject.getSheetId()));
			   ticket.setTicketSeq(StaticMethod.null2String(mainObject.getParentCorrelation()));
			   ticket.setCompContent(StaticMethod.null2String(mainObject.getComplaintDesc()));
			   ticket.setCompTime(StaticMethod.date2String(mainObject.getComplaintTime()));
			   ticket.setPlace(StaticMethod.null2String(mainObject.getFaultSite()));
			   ticket.setServiceName(StaticMethod.null2String(complaintTypeCn));
			   ticket.setUserCity(StaticMethod.null2String(mainObject.getCustomAttribution()));
			   ticket.setUserPhone(StaticMethod.null2String(mainObject.getCustomPhone()));
			   ticket.setUserProv(StaticMethod.null2String(mainObject.getStartDealCity()));
			   ticket.setWorkerPhone(StaticMethod.null2String(mainObject.getBdeptContact()));
		   }
		   
		try
		{
		     System.out.println("-setEomsInfo--complaintTypeCn---"+ticket.getServiceName());
			 System.out.println("-setEomsInfo--EomsSeq---"+ticket.getEomsSeq());
			EomsReplyTicketLocator locator = new EomsReplyTicketLocator();
			locator.setEomsReplyTicketHttpPortEndpointAddress("http://10.30.244.31/jcssService/services/EomsReplyTicket");
			EomsReplyTicketPortType binding = (EomsReplyTicketPortType)locator.getEomsReplyTicketHttpPort();	        
			result = binding.receiveEomsTicket(ticket);	
			System.out.println("--------receiveEomsTicket----result-"+result);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	
	
	public ActionForward  getEomsInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
		   EomsReply result1 =new  EomsReply();
		   String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
		   System.out.println("ph:sheetKey=" + sheetKey);	   
		try
		{
		      ID2NameService m = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
			EomsReplyTicketLocator locator = new EomsReplyTicketLocator();
			locator.setEomsReplyTicketHttpPortEndpointAddress("http://10.30.244.31/jcssService/services/EomsReplyTicket");
			EomsReplyTicketPortType binding = (EomsReplyTicketPortType)locator.getEomsReplyTicketHttpPort();				        
			result1 = binding.replyEomsTicket(sheetKey);
			
			System.out.println("ndeptContact"+StaticMethod.null2String(result1.getContactor()));
			System.out.println("ndeptContactPhone"+StaticMethod.null2String(result1.getContactPhone()));
			System.out.println("issueEliminatReason"+StaticMethod.null2String(result1.getDutyReason()));
			System.out.println("dealDesc"+StaticMethod.null2String(result1.getSolution()));
			System.out.println("linkReplyPerson"+StaticMethod.null2String(result1.getReplier()));
			System.out.println("linkReplayPhone"+StaticMethod.null2String(result1.getReplyTime()));
			System.out.println("linkDealDesc"+StaticMethod.null2String(result1.getReplyContent()));
			System.out.println("linkSpecialty"+StaticMethod.null2String(result1.getMajor()));
			System.out.println("linkQuality"+StaticMethod.null2String(result1.getAreaType()));
			System.out.println("linkAddressCI"+StaticMethod.null2String(result1.getCi()));
			System.out.println("linkAddressName"+StaticMethod.null2String(result1.getCellName()));
			System.out.println("linkEquipmentType"+StaticMethod.null2String(result1.getDeviceType()));
			System.out.println("linkEquipmentFactory"+StaticMethod.null2String(result1.getDeviceFactory()));
			System.out.println("compPropName"+StaticMethod.null2String(result1.getComplaintType()));  
			System.out.println("dealResultName"+StaticMethod.null2String(result1.getResult()));   
			System.out.println("linkIsUserStatisfiedName"+StaticMethod.null2String(result1.getUserSatisfaction())); 				
			System.out.println("compProp"+StaticMethod.null2String(result1.getComplaintType()));
			System.out.println("dealResult"+StaticMethod.null2String(result1.getResult())); 
			System.out.println("linkIsUserStatisfied"+StaticMethod.null2String(result1.getUserSatisfaction())); 
			System.out.println("isReplied"+StaticMethod.null2String(result1.getIsReply()));    
			System.out.println("linkIsComplaintSolve"+StaticMethod.null2String(result1.getIsSolved()));
			System.out.println("linkIsUserConfirm"+StaticMethod.null2String(result1.getIsConfirmed()));
			System.out.println("linkIsRepeatComplaint"+StaticMethod.null2String(result1.getIsRepeat()));
			System.out.println("linkIsContactUser"+StaticMethod.null2String(result1.getIsContacted()));
			System.out.println("linkIsAlarm"+StaticMethod.null2String(result1.getHasAlarm()));
			System.out.println("weakoverLay"+StaticMethod.null2String(result1.getWeakoverlay()));
			System.out.println("issueEliminatTime"+StaticMethod.null2String(result1.getSolvedTime()));
			System.out.println("dutyReason"+StaticMethod.null2String(result1.getDutyReason()));
			JSONArray json = new JSONArray();
			JSONObject jitem = new JSONObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(result1!=null){
				jitem.put("ndeptContact",StaticMethod.null2String(result1.getContactor()));
				jitem.put("ndeptContactPhone",StaticMethod.null2String(result1.getContactPhone()));
				jitem.put("issueEliminatReason",StaticMethod.null2String(result1.getDutyReason()));
				jitem.put("dealDesc",StaticMethod.null2String(result1.getSolution()));
				jitem.put("linkReplyPerson",StaticMethod.null2String(result1.getReplier()));
				jitem.put("linkReplayPhone",StaticMethod.null2String(result1.getReplyTime()));
				jitem.put("linkDealDesc",StaticMethod.null2String(result1.getReplyContent()));
	//			jitem.put("linkIsComplaintSolve",StaticMethod.null2String(result1.getIsSolved()));
//				linkObject.setLinkIsContactUser(StaticMethod.null2String(result1));
				jitem.put("linkSpecialty",StaticMethod.null2String(result1.getMajor()));
				jitem.put("linkQuality",StaticMethod.null2String(result1.getAreaType()));
				jitem.put("linkAddressCI",StaticMethod.null2String(result1.getCi()));
				jitem.put("linkAddressName",StaticMethod.null2String(result1.getCellName()));
				jitem.put("linkEquipmentType",StaticMethod.null2String(result1.getDeviceType()));
				jitem.put("linkEquipmentFactory",StaticMethod.null2String(result1.getDeviceFactory()));
				jitem.put("compPropName",StaticMethod.null2String(result1.getComplaintType()));  
				jitem.put("dealResultName",StaticMethod.null2String(result1.getResult()));   
				jitem.put("linkIsUserStatisfiedName",StaticMethod.null2String(result1.getUserSatisfaction())); 				
				 jitem.put("compProp",StaticMethod.null2String(result1.getComplaintType()));
				 jitem.put("dealResult",StaticMethod.null2String(result1.getResult())); 
				 jitem.put("linkIsUserStatisfied",StaticMethod.null2String(result1.getUserSatisfaction())); 
				jitem.put("isReplied",StaticMethod.null2String(result1.getIsReply()));    
				jitem.put("linkIsComplaintSolve",StaticMethod.null2String(result1.getIsSolved()));
				jitem.put("linkIsUserConfirm",StaticMethod.null2String(result1.getIsConfirmed()));
				jitem.put("linkIsRepeatComplaint",StaticMethod.null2String(result1.getIsRepeat()));
				jitem.put("linkIsContactUser",StaticMethod.null2String(result1.getIsContacted()));
				jitem.put("linkIsAlarm",StaticMethod.null2String(result1.getHasAlarm()));
		    	 String weakoverLay =StaticMethod.null2String(result1.getWeakoverlay());
		    	 System.out.println("------weakoverLay-----"+weakoverLay);
		    	 if(!"".equals(weakoverLay)&& weakoverLay!=null){
		    	   if(weakoverLay.indexOf("+")==-1){
		    		   jitem.put("linkCoverDian",StaticMethod.null2String(weakoverLay));
		    		   jitem.put("linkCoverLian","");
		    	   }else{
		    		 String[] lay =  weakoverLay.split("\\+");
		    		   jitem.put("linkCoverDian",StaticMethod.null2String(lay[0]));
		    		   jitem.put("linkCoverLian",StaticMethod.null2String(lay[1]));
		    	   }
		    	   System.out.println("--weakoverLay2-"+weakoverLay);
		    	 }

				if (result1.getSolvedTime() == null ||"".equals(result1.getSolvedTime()))
					jitem.put("issueEliminatTime", "");
				else
					jitem.put("issueEliminatTime", sdf.format(sdf.parse(result1.getSolvedTime())));
		
			   String dutyReason =StaticMethod.null2String(result1.getDutyReason());	
			   System.out.println("000----dutyReason-000"+dutyReason);
			   if(!"".equals(dutyReason)&& dutyReason!=null){			
				   String[] dictid = dutyReason.split("\\|");
				   for(int i=0; i<dictid.length; i++){
				   System.out.println("00000eomsinfo00000dictid="+dictid[i]);
				   jitem.put("issueReasonType"+i, dictid[i]);					
				   jitem.put("issueReasonTypeName"+i, m.id2Name(dictid[i], "ItawSystemDictTypeDao"));
				   }

			   }
			
			}
			
			json.put(jitem);
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().print(json.toString());
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	  return null;
	}

	
	
	
	
}
