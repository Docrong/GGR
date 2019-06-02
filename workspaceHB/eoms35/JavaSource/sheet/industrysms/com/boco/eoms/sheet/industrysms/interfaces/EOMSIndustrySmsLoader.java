package com.boco.eoms.sheet.industrysms.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.industrysms.model.IndustrySmsMain;
import com.boco.eoms.sheet.industrysms.service.impl.IndustrySmsCrmServiceManageImpl;

public class EOMSIndustrySmsLoader {
	public static final String WORKSHEET_REPLY = "replyWorkSheet";
	public String replyWorkSheet(HashMap interfaceMap,List attach) throws Exception{
		System.out.println("进入replyWorkSheet()");
		IndustrySmsCrmServiceManageImpl iscs = new IndustrySmsCrmServiceManageImpl();
		Map map = iscs.initMap(interfaceMap, attach, EOMSIndustrySmsLoader.WORKSHEET_REPLY);
 		if(map.get("phaseId")==null)
			throw new Exception("phaseId为空");
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(iscs.getMainBeanId());
		
		map = iscs.setBaseMap(map);
		String sheetNo = StaticMethod.nullObject2String(map.get("sheetId"));
		System.out.println("serialNo="+sheetNo);
		IndustrySmsMain main = (IndustrySmsMain)iMainService.getMainBySheetId(sheetNo);
		String spareTwo = StaticMethod.nullObject2String(main.getSpareTwo());
		if(main!=null && "1".equals(spareTwo)){
			map.put("id", main.getId());
			map.put("operateUserId", main.getSendUserId());
			return iscs.dealSheet(main,map, attach);
		}else{
			throw new Exception("没找到sheetNo＝"+sheetNo+"对应的工单或者此工单未开启自动处理功能");
		}
	}
}
