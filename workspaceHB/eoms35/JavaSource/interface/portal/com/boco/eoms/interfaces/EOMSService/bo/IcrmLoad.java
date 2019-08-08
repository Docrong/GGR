package com.boco.eoms.interfaces.EOMSService.bo;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.interfaces.EOMSService.client.IRMSServiceLocator;
import com.boco.eoms.interfaces.EOMSService.client.IRMSServiceSoapBindingStub;
import com.boco.eoms.interfaces.EOMSService.util.IcrmUtil;

public class IcrmLoad {

    private static IRMSServiceSoapBindingStub loadService(String sheetType) throws Exception {
        String icrmServiceUrl = XmlManage.getFile(
                "/com/boco/eoms/interfaces/EOMSService/config/icrm-util.xml").getProperty(
                "ServiceUrl");

        System.out.println("icrmService:" + icrmServiceUrl);
        URL crmUrl = new URL(icrmServiceUrl);

        return (IRMSServiceSoapBindingStub) (new IRMSServiceLocator().getIRMSService(crmUrl));

    }

    /**
     * 推送业务数据(传附件的url)
     *
     * @param sheetType
     * @param opDetail
     * @return
     */
    public static String putBusinessData(String sheetType, String opDetail) {
        BocoLog.info(IcrmLoad.class, "putBusinessData opDetail=" + opDetail);
        String result = "";
        try {
            String serSupplier = "";
            String serCaller = "";
            String callerPwd = "";
            String callTime = "";

            result = IcrmLoad.loadService(sheetType).putBusinessData(serSupplier, serCaller, callerPwd, callTime, opDetail);
        } catch (Exception err) {
            System.out.println("");
            err.printStackTrace();
        }
        System.out.println("putBusinessData result=" + result);
        return result;
    }

    /**
     * 审批结果通知
     *
     * @param sheetType
     * @param opDetail
     * @return
     */
    public static String setCheck(String sheetType, String opDetail) {
        BocoLog.info(IcrmLoad.class, "setCheck opDetail=" + opDetail);
        String result = "";
        try {
            String serSupplier = "";
            String serCaller = "";
            String callerPwd = "";
            String callTime = "";

            result = IcrmLoad.loadService(sheetType).setCheck(serSupplier, serCaller, callerPwd, callTime, opDetail);
        } catch (Exception err) {
            System.out.println("");
            err.printStackTrace();
        }
        System.out.println("setCheck result=" + result);
        return result;
    }

    /**
     * 获取派单地域
     *
     * @param sheetType
     * @param opDetail
     * @return
     */
    public static String getDeptIds(String sheetType, String opDetail) throws Exception {
        BocoLog.info(IcrmLoad.class, "getDeptIds opDetail=" + opDetail);
        String cityId = "";
        try {
            String serSupplier = "";
            String serCaller = "";
            String callerPwd = "";
            String callTime = "";

            String result = IcrmLoad.loadService(sheetType).getDeptIds(serSupplier, serCaller, callerPwd, callTime, opDetail);
            BocoLog.info(IcrmLoad.class, "getDeptIds result=" + result);

            result = "<detail><regionInfo><regionId>北京</regionId><regionName>北京</regionName></regionInfo><regionInfo><regionId>SH</regionId><regionName>SH</regionName></regionInfo></detail>";
            IcrmUtil icrmUtil = new IcrmUtil();
            HashMap map = icrmUtil.xmlElements(result, "//detail/regionInfo", "regionId", "regionName");

            ITawSystemAreaManager areaMgr =
                    (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");

            Set set = map.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String key = StaticMethod.nullObject2String(it.next());
                String value = StaticMethod.nullObject2String(map.get(key));

                TawSystemArea area = areaMgr.getAreaByCode(value);
                if (area != null && area.getId() != null)
                    cityId += area.getAreaid() + ",";
                else
                    throw new Exception("没有找到'" + value + "'映射的地市");
            }
            if (cityId.length() > 0)
                cityId = cityId.substring(0, cityId.length() - 1);

        } catch (Exception err) {
            throw err;
        }

        return cityId;
    }

    /**
     * 资源数据导出
     *
     * @param sheetType
     * @param opDetail
     * @return
     */
    public static String getExcelData(String sheetType, String opDetail) {
        BocoLog.info(IcrmLoad.class, "getExcelData opDetail=" + opDetail);
        String result = "";
        try {
            String serSupplier = "";
            String serCaller = "";
            String callerPwd = "";
            String callTime = "";

            result = IcrmLoad.loadService(sheetType).getExcelData(serSupplier, serCaller, callerPwd, callTime, opDetail);
        } catch (Exception err) {
            System.out.println("");
            err.printStackTrace();
        }
        System.out.println("getExcelData result=" + result);
        return result;
    }

    /**
     * 回单提交(检查施工环境是否可提交)
     *
     * @param sheetType
     * @param opDetail
     * @return
     */
    public static String submitReplySheet(String sheetType, String opDetail) {
        BocoLog.info(IcrmLoad.class, "submitReplySheet opDetail=" + opDetail);
        String result = "";
        try {
            String serSupplier = "";
            String serCaller = "";
            String callerPwd = "";
            String callTime = "";

            result = IcrmLoad.loadService(sheetType).submitReplySheet(serSupplier, serCaller, callerPwd, callTime, opDetail);
        } catch (Exception err) {
            System.out.println("");
            err.printStackTrace();
        }
        System.out.println("submitReplySheet result=" + result);
        return result;
    }

    /**
     * 删除工单
     *
     * @param sheetType
     * @param opDetail
     * @return
     */
    public static String deleteSheet(String sheetType, String opDetail) {
        BocoLog.info(IcrmLoad.class, "deleteSheet opDetail=" + opDetail);
        String result = "";
        try {
            String serSupplier = "";
            String serCaller = "";
            String callerPwd = "";
            String callTime = "";

            result = IcrmLoad.loadService(sheetType).deleteSheet(serSupplier, serCaller, callerPwd, callTime, opDetail);
        } catch (Exception err) {
            System.out.println("");
            err.printStackTrace();
        }
        System.out.println("deleteSheet result=" + result);
        return result;
    }
}
