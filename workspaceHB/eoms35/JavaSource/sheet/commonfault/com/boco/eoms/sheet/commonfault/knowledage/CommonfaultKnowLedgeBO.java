package com.boco.eoms.sheet.commonfault.knowledage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.knowledge.service.KnowledgeClient;
import com.boco.eoms.knowledge.util.KnowledgeMethod;

import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;

/**
 * @author IBM
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonfaultKnowLedgeBO {


    /**
     * 新增知识库
     */
    public static String showNewknowLedage(String sheetKey, String userId) throws Exception {
        ICommonFaultMainManager mgr =
                (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
//		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
        CommonFaultMain main = (CommonFaultMain) mgr.getSingleMainPO(sheetKey);
        TawSystemUser user = userManager.getUserByuserid(userId);
        Map mainMap = new HashMap();
        if (main != null) {

//			mainMap.put("sheetNo", main.getSheetId());
//			mainMap.put("title", main.getTitle());
//			mainMap.put("mainFaultResponseLevel", service.id2Name(StaticMethod.nullObject2String(main.getMainFaultResponseLevel()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainAlarmNum", main.getMainAlarmNum());
//			mainMap.put("mainAlarmSolveDate", main.getMainAlarmSolveDate());
//			mainMap.put("mainAlarmSource", main.getMainAlarmSource());
//			mainMap.put("mainAlarmLogicSort", service.id2Name(StaticMethod.nullObject2String(main.getMainAlarmLogicSort()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainAlarmLogicSortSub", service.id2Name(StaticMethod.nullObject2String(main.getMainAlarmLogicSortSub()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainAlarmDesc", main.getMainAlarmDesc());
//			mainMap.put("mainFaultSpecialty", service.id2Name(StaticMethod.nullObject2String(main.getMainFaultSpecialty()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainEquipmentFactory", service.id2Name(StaticMethod.nullObject2String(main.getMainEquipmentFactory()),"tawSystemAreaDao"));
//			mainMap.put("mainEquipmentType", main.getMainEquipmentType());
//			mainMap.put("mainNetName", main.getMainNetName());
//			mainMap.put("mainEquipmentModel", main.getMainEquipmentModel());
//			mainMap.put("mainFaultGenerantTime", main.getMainFaultGenerantTime());
//			mainMap.put("mainIfAffectOperation", service.id2Name(StaticMethod.nullObject2String(main.getMainIfAffectOperation()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainFaultDiscoverableMode", service.id2Name(StaticMethod.nullObject2String(main.getMainFaultDiscoverableMode()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainNetSortOne", service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortOne()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainNetSortTwo", service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortTwo()),"ItawSystemDictTypeDao"));
//			mainMap.put("mainNetSortThree", service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortThree()),"ItawSystemDictTypeDao"));
            mainMap = SheetBeanUtils.bean2Map(main);

            mainMap.put("userName", user.getUsername());
            mainMap.put("deptName", user.getDeptname());
            mainMap.put("mobile", user.getMobile());
        }

        String xml = KnowledgeMethod.getKnowledgeXml(userId, "CommonFault", sheetKey, mainMap, null);
        return KnowledgeClient.loadAddKnowledgeService().saveXmlValue(xml);
    }

    /**
     * 按网络三级类别检索工单，返回知识库查询结果页面。
     *
     * @param sheetKey
     * @param userId
     * @return
     * @throws Exception
     */
    public static String searchSheet(String sheetKey, String userId) throws Exception {
        ICommonFaultMainManager mgr =
                (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");

        CommonFaultMain main = (CommonFaultMain) mgr.getSingleMainPO(sheetKey);
        Map mainMap = new HashMap();
        if (main != null) {
//			String mainNetSortThree = service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortThree()),"ItawSystemDictTypeDao");
//			String mainNetSortOne = service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortOne()),"ItawSystemDictTypeDao");
//			String mainNetSortTwo = service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortTwo()),"ItawSystemDictTypeDao");
            mainMap.put("mainNetSortThree", main.getMainNetSortThree());
            mainMap.put("mainNetSortOne", main.getMainNetSortOne());
            mainMap.put("mainNetSortTwo", main.getMainNetSortTwo());
        }
//		List list = mgr.getMainList(mainMap);
//		
//		String sheetIds = "";
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				CommonFaultMain commonFaultMain = (CommonFaultMain)list.get(i);
//				sheetIds += commonFaultMain.getId() + ",";
//			}
//		}
//		if(sheetIds.length()>0)
//			sheetIds = sheetIds.substring(0,sheetIds.length()-1);

        String xml = KnowledgeMethod.getKnowledgeXml(userId, "CommonFault", sheetKey, mainMap, null);
        return KnowledgeClient.loadAddKnowledgeService().searchXmlValue(xml);

    }

    /**
     * 新增遗留问题库
     *
     * @return
     */
    public static String showNewLeaveKnowLedage(String sheetKey, String userId) throws Exception {

        ICommonFaultMainManager mgr =
                (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        CommonFaultMain main = (CommonFaultMain) mgr.getSingleMainPO(sheetKey);
        TawSystemUser user = userManager.getUserByuserid(userId);

        Map mainMap = new HashMap();
        if (main != null) {
//			mainMap.put("title", main.getTitle());
//			mainMap.put("sheetNo", main.getSheetId());

//			
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String time = dateFormat.format(new Date());
//			mainMap.put("createTime", time);

            mainMap = SheetBeanUtils.bean2Map(main);

            mainMap.put("userName", user.getUsername());
            mainMap.put("deptName", user.getDeptname());
            mainMap.put("mobile", user.getMobile());

        }

        String xml = KnowledgeMethod.getKnowledgeXml(userId, "CommonFaultLeave", sheetKey, mainMap, null);
        return KnowledgeClient.loadAddKnowledgeService().saveXmlValue(xml);
    }

}
