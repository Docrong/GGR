package com.boco.eoms.businessupport.order.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.util.Constants;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;

public class OrderSheetMethod {
	
	public static List getSpecialLineList(String orderid, String specialtyType) throws SheetException {
		  IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder
                 .getInstance().getBean("IOrderSheetManager");
		  List speciallinelist = new ArrayList();
          if(orderid != null && !orderid.equals("")){
          if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._GPRS_LINE)){//GPRS专线
	          GprsSpecialLine objectName = new GprsSpecialLine();
	          List list = iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
              for(int i = 0;list!=null&& i<list.size();i++){
		          Map rasMap = new HashMap();	
		          GprsSpecialLine gprsSpecialLine = (GprsSpecialLine)list.get(i);
		          rasMap.putAll(SheetBeanUtils.bean2Map(gprsSpecialLine));
		          speciallinelist.add(rasMap);
              }
        }else if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._IP_LINE) ){//IP专线
	          IPSpecialLine objectName = new IPSpecialLine();
	          List list = iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
              for(int i = 0;list!=null&& i<list.size();i++){
		          Map rasMap = new HashMap();	
		          IPSpecialLine ipSpecialLine = (IPSpecialLine)list.get(i);
		          rasMap.putAll(SheetBeanUtils.bean2Map(ipSpecialLine));
		          speciallinelist.add(rasMap);
             }
       }else if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._TRANSFER_LINE)){//传输专线
	         TransferSpecialLine objectName = new TransferSpecialLine();
	         List list = iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
             for(int i = 0;list!=null&& i<list.size();i++){
		          Map rasMap = new HashMap();	
		          TransferSpecialLine transferSpecialLine = (TransferSpecialLine)list.get(i);
		          rasMap.putAll(SheetBeanUtils.bean2Map(transferSpecialLine));
		          speciallinelist.add(rasMap);
             }
             
        }
       else if(specialtyType != null && !specialtyType.equals("") && specialtyType.equals(Constants._LANGUAGE_LINE)){//
    	   LanguageSpecialLine objectName = new LanguageSpecialLine();
	         List list = iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
           for(int i = 0;list!=null&& i<list.size();i++){
		          Map rasMap = new HashMap();	
		          LanguageSpecialLine languageSpecialLine = (LanguageSpecialLine)list.get(i);
		          rasMap.putAll(SheetBeanUtils.bean2Map(languageSpecialLine));
		          speciallinelist.add(rasMap);
           }
           
      }else if(specialtyType != null && !specialtyType.equals("") && (specialtyType.equals(Constants._CAIXIN_LINE)||specialtyType.equals(Constants._CAIXIN_LINE))){//
    	  Smsspecialline objectName = new Smsspecialline();
	         List list = iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
             for(int i = 0;list!=null&& i<list.size();i++){
		          Map rasMap = new HashMap();	
		          Smsspecialline smsspecialline = (Smsspecialline)list.get(i);
		          rasMap.putAll(SheetBeanUtils.bean2Map(smsspecialline));
		          speciallinelist.add(rasMap);
             }
             
        }
}
	return speciallinelist;


	}

}
