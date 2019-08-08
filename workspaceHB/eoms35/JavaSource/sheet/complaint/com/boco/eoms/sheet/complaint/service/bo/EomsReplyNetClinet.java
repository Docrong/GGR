package com.boco.eoms.sheet.complaint.service.bo;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;


import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceLocator;
import com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_PortType;

import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.webapp.action.ComplaintAction;
import com.boco.eoms.util.InterfaceUtil;

public class EomsReplyNetClinet extends ComplaintAction {


    public static String sendEomsInfo(ComplaintMain mainObject) throws ServiceException {
        String result = "-1";
        String opDetail = "";
        HashMap sheetMap = new HashMap();
        sheetMap.put("sheet_no", StaticMethod.null2String(mainObject.getSheetId()));
        sheetMap.put("operation_person", StaticMethod.null2String(mainObject.getSendUserId()));
        sheetMap.put("operator_department", StaticMethod.null2String(mainObject.getSendDeptId()));
        sheetMap.put("operationtime", StaticMethod.date2String(mainObject.getSendTime()));
        sheetMap.put("theme", StaticMethod.null2String(mainObject.getTitle()));
        sheetMap.put("operator_contact", StaticMethod.null2String(mainObject.getSendContact()));
        sheetMap.put("processing_time", StaticMethod.date2String(mainObject.getSendTime()));
        sheetMap.put("distribute_contacts", StaticMethod.null2String(mainObject.getBtype1()));
        sheetMap.put("contact_phone", StaticMethod.null2String(mainObject.getBdeptContact()));
        sheetMap.put("customer_name", StaticMethod.null2String(mainObject.getCustomerName()));
        sheetMap.put("scan_start_time", StaticMethod.date2String(mainObject.getComplaintTime()));
        sheetMap.put("customer_number", StaticMethod.null2String(mainObject.getParentCorrelation()));
        sheetMap.put("urgency_level", StaticMethod.null2String(mainObject.getUrgentDegree()));
        sheetMap.put("complaints_classifi1", StaticMethod.null2String(mainObject.getComplaintType1()));
        sheetMap.put("complaints_classifi2", StaticMethod.null2String(mainObject.getComplaintType2()));
        sheetMap.put("complaints_classifi3", StaticMethod.null2String(mainObject.getComplaintType()));
        sheetMap.put("complaints_classifi4", StaticMethod.null2String(mainObject.getComplaintType4()));
        sheetMap.put("complaints_classifi5", StaticMethod.null2String(mainObject.getComplaintType5()));
        sheetMap.put("complaints_classifi6", StaticMethod.null2String(mainObject.getComplaintType6()));
        sheetMap.put("complaints_classifi7", StaticMethod.null2String(mainObject.getComplaintType7()));
        sheetMap.put("is_largeareacomplaints", StaticMethod.null2String(mainObject.getBdeptContactPhone()));
        sheetMap.put("customer_calls", StaticMethod.null2String(mainObject.getCustomPhone()));//customPhone
        sheetMap.put("Is_repeatedcomplaints", StaticMethod.null2String(mainObject.getRepeatComplaintTimes()));
        sheetMap.put("customer_level", StaticMethod.null2String(mainObject.getCustomLevel()));//customLevel
        sheetMap.put("fault_location", StaticMethod.null2String(mainObject.getFaultSite()));
        sheetMap.put("complaint_content", StaticMethod.null2String(mainObject.getComplaintDesc()));
        sheetMap.put("pretreatment_condition", StaticMethod.null2String(mainObject.getPreDealResult()));
        sheetMap.put("region_id", StaticMethod.null2String(mainObject.getCustomAttribution()));
        sheetMap.put("region_name", StaticMethod.null2String(mainObject.getCustomAttribution()));
        sheetMap.put("Customer_brand", StaticMethod.null2String(mainObject.getCustomBrand()));
        sheetMap.put("Accepted_time", StaticMethod.date2String(mainObject.getSheetAcceptLimit()));
        sheetMap.put("T2_Processing_time", StaticMethod.date2String(mainObject.getMainCompleteLimitT2()));
        sheetMap.put("city_id", "");
        sheetMap.put("city_name", "");
        sheetMap.put("Failure_time", StaticMethod.date2String(mainObject.getFaultTime()));
        //mainFaultCity 故障地市  fault_region
        //mainFaultCounty  故障区县  fault_city
        sheetMap.put("fault_region", StaticMethod.nullObject2String(mainObject.getMainFaultCity()));
        sheetMap.put("fault_city", StaticMethod.nullObject2String(mainObject.getMainFaultCounty()));
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            opDetail = interfaceUtil.mapToXml(sheetMap);
            System.out.println("--opDetail--" + opDetail);
            ReceiveEomsServiceLocator locator = new ReceiveEomsServiceLocator();
            ReceiveEomsServiceSoap_PortType binding = (ReceiveEomsServiceSoap_PortType) locator.getReceiveEomsServiceSoap();
            String webresult = binding.updateWorkSheet("", "", "", "", opDetail);
            boolean str = webresult.endsWith("=");
            if (str) {
                result = "0";
            }
            System.out.println("--result--" + result);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }


    public static String withdrawWorkSheet(String sheetid, String opseUser, String rejectDesc) throws ServiceException {
        String result = "-1";
        String opDetail = "";
        HashMap sheetMap = new HashMap();
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sf.format(date);
        if ("".equals(opseUser)) {
            opseUser = "kf1860";
        }
        sheetMap.put("sheetNo", sheetid);
        sheetMap.put("operationPerson", opseUser);
        sheetMap.put("operationTime", currentDate);
        sheetMap.put("rejectReason", "");
        sheetMap.put("rejectDesc", rejectDesc);
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            opDetail = interfaceUtil.mapToXml(sheetMap);
            System.out.println("--opDetail--" + opDetail);
            ReceiveEomsServiceLocator locator = new ReceiveEomsServiceLocator();
            ReceiveEomsServiceSoap_PortType binding = (ReceiveEomsServiceSoap_PortType) locator.getReceiveEomsServiceSoap();
            String webresult = binding.withdrawWorkSheet("", "", "", "", opDetail);
            boolean str = webresult.endsWith("=");
            if (str) {
                result = "0";
            }
            System.out.println("---webresult--" + webresult);
            System.out.println("--result--" + result);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    public static String reminderWorkSheet(String sheetid, String opseUser, String reminderDesc) throws ServiceException {
        String result = "-1";
        String opDetail = "";
        HashMap sheetMap = new HashMap();
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sf.format(date);
        sheetMap.put("sheetNo", sheetid);
        sheetMap.put("operationPerson", opseUser);
        sheetMap.put("operationTime", currentDate);
        sheetMap.put("reminderDesc", reminderDesc);
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            opDetail = interfaceUtil.mapToXml(sheetMap);
            System.out.println("--opDetail--" + opDetail);
            ReceiveEomsServiceLocator locator = new ReceiveEomsServiceLocator();
            ReceiveEomsServiceSoap_PortType binding = (ReceiveEomsServiceSoap_PortType) locator.getReceiveEomsServiceSoap();
            String webresult = binding.reminderWorkSheet("", "", "", "", opDetail);
            boolean str = webresult.endsWith("=");
            if (str) {
                result = "0";
            }
            System.out.println("--result--" + result);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    public static String inspectionWorkSheet(String sheetid, String opseUser, String inspectionResult, String describe) throws ServiceException {
        String result = "-1";
        String opDetail = "";
        HashMap sheetMap = new HashMap();
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sf.format(date);
        sheetMap.put("sheetNo", sheetid);
        sheetMap.put("operationPerson", opseUser);
        sheetMap.put("operationTime", currentDate);
        sheetMap.put("inspectionResult", inspectionResult);
        sheetMap.put("inspectionQuality", "");
        sheetMap.put("inspectionDescribe", describe);

        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            opDetail = interfaceUtil.mapToXml(sheetMap);
            System.out.println("--opDetail--" + opDetail);
            ReceiveEomsServiceLocator locator = new ReceiveEomsServiceLocator();
            ReceiveEomsServiceSoap_PortType binding = (ReceiveEomsServiceSoap_PortType) locator.getReceiveEomsServiceSoap();
            String webresult = binding.inspectionWorkSheet("", "", "", "", opDetail);
            boolean str = webresult.endsWith("=");
            if (str) {
                result = "0";
            }
            System.out.println("--result--" + result);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }


    public static String fileWorkSheet(String sheetid, String opseUser, String satisfactdegree, String isFile, String fileDesc) throws ServiceException {
        String result = "-1";
        String opDetail = "";
        HashMap sheetMap = new HashMap();
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sf.format(date);
        sheetMap.put("sheetNo", sheetid);
        sheetMap.put("operationPerson", opseUser);
        sheetMap.put("operationTime", currentDate);
        sheetMap.put("satisfactdegree", satisfactdegree);
        sheetMap.put("isFile", isFile);
        sheetMap.put("fileDesc", fileDesc);

        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            opDetail = interfaceUtil.mapToXml(sheetMap);
            System.out.println("--opDetail--" + opDetail);
            ReceiveEomsServiceLocator locator = new ReceiveEomsServiceLocator();
            ReceiveEomsServiceSoap_PortType binding = (ReceiveEomsServiceSoap_PortType) locator.getReceiveEomsServiceSoap();
            String webresult = binding.fileWorkSheet("", "", "", "", opDetail);
            boolean str = webresult.endsWith("=");
            if (str) {
                result = "0";
            }
            System.out.println("--result--" + result);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }
}
