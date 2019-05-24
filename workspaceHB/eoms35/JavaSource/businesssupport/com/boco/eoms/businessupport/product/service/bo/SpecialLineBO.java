package com.boco.eoms.businessupport.product.service.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;
import com.boco.eoms.businessupport.product.service.IIPSpecialLineManager;
import com.boco.eoms.businessupport.product.service.ILanguageSpecialLineManager;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;
import com.boco.eoms.businessupport.product.service.TrancePointMgr;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmMainManager;

public class SpecialLineBO {
	public String save(String sheetType,String businessType,List mapList) throws Exception{
		
		if(sheetType.equals("31")){
			String orderId = UUIDHexGenerator.getInstance().getID();
			this.saveProduct(orderId, sheetType, businessType, mapList);
			return orderId;
		}else if(sheetType.equals("32")){
			IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
			Map map = (Map)mapList.get(0);
			String confCRMTicketNo = StaticMethod.nullObject2String(map.get("confCRMTicketNo"));
			OrderSheet os = iOrderSheetManager.getOrderSheetByCRMNo(confCRMTicketNo);
			if(os!=null){
				this.updateProduct(os, businessType, mapList);
				return os.getId();
			}
		}
		return null;
	}
	/**
	 * 保存专线和接入点信息
	 * @param orderId
	 * @param sheetType
	 * @param businessType
	 * @param mapList
	 * @throws Exception
	 */
	private void saveProduct(String orderId,String sheetType,String businessType,List mapList) throws Exception{
		try{
			if(mapList != null)
	        {
				Map pMap = new HashMap();
				
				String filePath = StaticMethod.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml");
				InterfaceUtilProperties properties = new InterfaceUtilProperties();
	            for(int i = 0; i < mapList.size(); i++){
                    Map map = (Map)mapList.get(i);
                    map = properties.getMapFromXml(map, filePath, "specialInfo");
                    
                    if(businessType.equals("1")){//GPRS专线
                    	GprsSpecialLine gprs = new GprsSpecialLine();
                    	SheetBeanUtils.populateMap2Bean(gprs, map);
                    	gprs.setOrderSheet_Id(orderId);
                    	IGprsSpecialLineManager gprsmgr = (IGprsSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IGprsSpecialLineManager");
                    	gprsmgr.saveOrUpdate(gprs);
                    }else if(businessType.equals("3")){//IP专线
                    	IPSpecialLine ip = new IPSpecialLine();
                    	SheetBeanUtils.populateMap2Bean(ip, map);
                    	ip.setOrderSheet_Id(orderId);
                    	IIPSpecialLineManager ipmgr = (IIPSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");
                    	ipmgr.saveOrUpdate(ip);
                    }else if(businessType.equals("4")){//传输专线
                    	TransferSpecialLine transfer = new TransferSpecialLine();
                    	SheetBeanUtils.populateMap2Bean(transfer, map);
                    	transfer.setOrderSheet_Id(orderId);
                    	ITransferSpecialLineManager transfermgr = (ITransferSpecialLineManager)ApplicationContextHolder.getInstance().getBean("ITransferSpecialLineManager");
                    	transfermgr.saveOrUpdate(transfer);
                    }
                    else if(businessType.equals("2")){//语音专线
                    	LanguageSpecialLine line = new LanguageSpecialLine();
                    	SheetBeanUtils.populateMap2Bean(line, map);
                    	line.setOrderSheet_Id(orderId);
                    	ILanguageSpecialLineManager mgr = (ILanguageSpecialLineManager)ApplicationContextHolder.getInstance().getBean("ILanguageSpecialLineManager");
                    	mgr.saveOrUpdate(line);
                    }
                    
                    
                    if(businessType.equals("4")){//传输专线
                    	String portADetailAdd = StaticMethod.nullObject2String(map.get("portADetailAdd"));
                    	String portZDetailAdd = StaticMethod.nullObject2String(map.get("portZDetailAdd"));
                    	if(!pMap.containsKey(portADetailAdd)){
                    		Map tempMap = new HashMap();
                    		tempMap.putAll(map);
                    		tempMap = properties.getMapFromXml(tempMap, filePath, "portA");
                    		TrancePoint p = new TrancePoint();
                    		SheetBeanUtils.populate(p, tempMap);
                    		p.setOrderSheetId(orderId);
                    		pMap.put(portADetailAdd, p);
                    	}
	                    if(!pMap.containsKey(portZDetailAdd)){
	                    	Map tempMap = new HashMap();
	                    	tempMap.putAll(map);
	                    	tempMap = properties.getMapFromXml(tempMap, filePath, "portZ");
	                    	TrancePoint p = new TrancePoint();
	                    	SheetBeanUtils.populate(p, tempMap);
	                    	p.setOrderSheetId(orderId);
	                    	pMap.put(portZDetailAdd, p);
	                    }
                    }
	            }
	            
	            TrancePointMgr businessupportMgr = (TrancePointMgr)ApplicationContextHolder.getInstance().getBean("businessupportMgr");
	            Set set = pMap.keySet();
				Iterator it = set.iterator();
				while (it.hasNext()) {
					String key = StaticMethod.nullObject2String(it.next());
					TrancePoint p = (TrancePoint)pMap.get(key);
					businessupportMgr.saveBusinessupport(p);
				}
	        }
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("save specialInfo err:"+e.getMessage());
		}
		
	}

	/**
	 * 删除定单和专线信息
	 * @param crmNo
	 * @param sheetType
	 * @param businessType
	 * @param mapList
	 * @throws Exception
	 */
	public void delete(String crmNo,String sheetType,String businessType,List mapList) throws Exception{
		if(sheetType.equals("31")){			
			IResourceConfirmMainManager iMainService = (IResourceConfirmMainManager)ApplicationContextHolder.getInstance().getBean("iResourceConfirmMainManager");
			List list = iMainService.getMainListByParentSheetId(crmNo);
			System.out.println("");
			if(list!=null&&list.size()>0){ 
				ResourceConfirmMain main = (ResourceConfirmMain)list.get(0);
				String orderId = main.getOrderSheetId();
				
				IOrderSheetManager mgr = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
				mgr.removeOrderSheet(orderId);
			}
		}
	}
	public void updateProduct(OrderSheet os,String businessType,List mapList) throws Exception{
		IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder
        .getInstance().getBean("IOrderSheetManager");
		
		List lines = new ArrayList();
		if(businessType.equals("1")){//GPRS专线
			 GprsSpecialLine objectName = new GprsSpecialLine();
			 List list = iOrderSheetManager.getSpecialLinesByType(os.getId(),objectName);
			 if(list.size()!=mapList.size()){
				 IGprsSpecialLineManager mgr = (IGprsSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IGprsSpecialLineManager");
				 
				 for(int i = 0;list!=null&& i<list.size();i++){
	       			GprsSpecialLine gprsSpecialLine = (GprsSpecialLine)list.get(i);
	       			boolean b = false;
					for(int j=0;j<mapList.size();j++){
						Map tempMap = (Map)mapList.get(j);
						String tradeID = StaticMethod.nullObject2String(tempMap.get("tradeID"));
						if(gprsSpecialLine.getTradeId().equals(tradeID));{
							b = true;
							break;
						}
					}
					if(!b){
						mgr.removeGprsSpecialLine(gprsSpecialLine.getId());
					}
	             }
			 }
		}else if(businessType.equals("2")){//语音专线
        	IPSpecialLine objectName = new IPSpecialLine();
        	List list = iOrderSheetManager.getSpecialLinesByType(os.getId(),objectName);
        	if(list.size()!=mapList.size()){
        		ILanguageSpecialLineManager mgr = (ILanguageSpecialLineManager) ApplicationContextHolder.getInstance().getBean("ILanguageSpecialLineManager");
        		for(int i = 0;list!=null&& i<list.size();i++){
        			LanguageSpecialLine languageSpecialLine = (LanguageSpecialLine)list.get(i);
	       			boolean b = false;
					for(int j=0;j<mapList.size();j++){
						Map tempMap = (Map)mapList.get(j);
						String tradeID = StaticMethod.nullObject2String(tempMap.get("tradeID"));
						if(languageSpecialLine.getTradeId().equals(tradeID));{
							b = true;
							break;
						}
					}
					if(!b){
						mgr.removeLanguageSpecialLine(languageSpecialLine.getId());
					}
	            }
        	}
        }else if(businessType.equals("3")){//IP专线
        	IPSpecialLine objectName = new IPSpecialLine();
        	List list = iOrderSheetManager.getSpecialLinesByType(os.getId(),objectName);
        	if(list.size()!=mapList.size()){
        		IIPSpecialLineManager mgr = (IIPSpecialLineManager) ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");
        		for(int i = 0;list!=null&& i<list.size();i++){
	       			IPSpecialLine ipSpecialLine = (IPSpecialLine)list.get(i);
	       			boolean b = false;
					for(int j=0;j<mapList.size();j++){
						Map tempMap = (Map)mapList.get(j);
						String tradeID = StaticMethod.nullObject2String(tempMap.get("tradeID"));
						if(ipSpecialLine.getTradeId().equals(tradeID));{
							b = true;
							break;
						}
					}
					if(!b){
						mgr.removeIPSpecialLine(ipSpecialLine.getId());
					}
	            }
        	}
        }else if(businessType.equals("4")){//传输专线
        	TransferSpecialLine objectName = new TransferSpecialLine();
     	   	List list = iOrderSheetManager.getSpecialLinesByType(os.getId(),objectName);
     	   if(list.size()!=mapList.size()){
     		  ITransferSpecialLineManager mgr = (ITransferSpecialLineManager) ApplicationContextHolder.getInstance().getBean("ITransferSpecialLineManager");
     		  for(int i = 0;list!=null&& i<list.size();i++){
       			TransferSpecialLine transferSpecialLine = (TransferSpecialLine)list.get(i);
       			boolean b = false;
				for(int j=0;j<mapList.size();j++){
					Map tempMap = (Map)mapList.get(j);
					String tradeID = StaticMethod.nullObject2String(tempMap.get("tradeID"));
					System.out.println(transferSpecialLine.getTradeId());
					System.out.println(tradeID);
					if(transferSpecialLine.getTradeId().equals(tradeID)){
						b = true;
						break;
					}
				}
				if(!b){
					mgr.removeTransferSpecialLine(transferSpecialLine.getId());
				}
              }
     	   }
        }
	}
}
