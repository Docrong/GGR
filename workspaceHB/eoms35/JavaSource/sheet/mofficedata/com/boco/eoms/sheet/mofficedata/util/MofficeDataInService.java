package com.boco.eoms.sheet.mofficedata.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataMain;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataTask;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataProMatchManager;
import com.boco.eoms.sheet.mofficedata.service.client.DATAProcessSheetLocator;
import com.boco.eoms.sheet.mofficedata.service.client.DATAProcessSheetSoapBindingStub;

/**
 * 接口服务类
 *
 * @author weichao
 * @date 2016-4-7下午02:05:09
 */
public class MofficeDataInService {

    /**
     * 局数据系统新建派单接口
     *
     * @param main
     * @param preLinkId
     * @return
     * @throws Exception
     * @date 2016-4-7下午03:48:40
     * @author weichao
     */
    public static String neWorkSheet(MofficeDataMain main, String preLinkId, String de, String linkIsNeedTest) {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub) new DATAProcessSheetLocator().getEomsDataImportImplPort();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        try {
            IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager) ApplicationContextHolder.getInstance()
                    .getBean("iMofficeDataProMatchManager");
            List list = proMgr.getProMatchObjects(main.getId());
            String mainAccesss = "";
            String busiName = "";
            Map map = new HashMap();
            if (null != list && !list.isEmpty()) {
                MofficeDataProMatch mat = (MofficeDataProMatch) list.get(0);
                mainAccesss = mat.getAccessories();
                busiName = mat.getBuissType();
            } else {
                mainAccesss = StaticMethod.nullObject2String(main.getSheetAccessories());// 附件信息
                busiName = main.getMainStyle1();
            }
            map.put("sheetID", preLinkId);
            map.put("serialID", main.getSheetId());
            map.put("operators", de);
            map.put("attachRef", mainAccesss);
            map.put("busiName", busiName);
            if ("101400601".equals(linkIsNeedTest)) {
                map.put("isDial", "true");
                map.put("isSignal", "false");
                map.put("isBill", "false");
            } else if ("101400602".equals(linkIsNeedTest)) {
                map.put("isDial", "false");
                map.put("isSignal", "false");
                map.put("isBill", "true");
            } else if ("101400603".equals(linkIsNeedTest)) {
                map.put("isDial", "false");
                map.put("isSignal", "true");
                map.put("isBill", "false");
            }
            map.put("sheetMemo", main.getTitle());
            map.put("startTime", main.getSendTime());
            map.put("endTime", main.getSheetCompleteLimit());
            map.put("subversion", main.getMainDataNo());
            java.lang.String value = null;
            try {
                String opdetail = MofficeUtil.getXmlFromMap(map,
                        StaticMethod.getFilePathForUrl("classpath:config/mofficedata-util.xml"), "newWorkSheet");
                String serSupplier = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serSupplier");
                String serCaller = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serCaller");
                String callerPwd = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.callerPwd");
                int timeout = StaticMethod.nullObject2int(XmlManage.getFile("/config/mofficedata-util.xml").getProperty(
                        "interfaces.timeout"));
                // Time out after a minute
                binding.setTimeout(timeout);
                value = binding.newWorkSheet(serSupplier, serCaller, callerPwd, StaticMethod.getCurrentDateTime(), opdetail);
                BocoLog.info(MofficeDataInService.class, "neWorkSheet return value==" + value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("==局数据接口调用失败（interface failed!!!）==");
        }


    }

    /**
     * 获取业务标准表模板文件路径
     *
     * @param opdetail
     * @return
     * @throws Exception
     * @date 2016-4-7下午03:27:34
     * @author weichao
     */
    public static String getTemplatePath(String opdetail) {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub) new DATAProcessSheetLocator().getEomsDataImportImplPort();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }

        String serSupplier = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serSupplier");
        String serCaller = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serCaller");
        String callerPwd = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.callerPwd");
        int timeout = StaticMethod.nullObject2int(XmlManage.getFile("/config/mofficedata-util.xml").getProperty(
                "interfaces.timeout"));
        //Time out after a minute
        binding.setTimeout(timeout);

        String value = null;
        try {
            value = binding.getTemplatePath(serSupplier, serCaller, callerPwd, StaticMethod.getCurrentDateTime(), opdetail);
            BocoLog.info(MofficeDataInService.class, "getTemplatePath return value==" + value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != value && value.indexOf("<attachRef><attachInfo>") > -1) {
            return value;
        }
        return null;
    }

    /**
     * 局数据系统同步业务类型接口
     *
     * @return
     * @throws Exception
     * @date 2016-4-7下午03:29:41
     * @author weichao
     */
    public static String getBusiType() {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub) new DATAProcessSheetLocator().getEomsDataImportImplPort();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }

        String serSupplier = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serSupplier");
        String serCaller = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serCaller");
        String callerPwd = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.callerPwd");
        int timeout = StaticMethod.nullObject2int(XmlManage.getFile("/config/mofficedata-util.xml").getProperty(
                "interfaces.timeout"));
        //Time out after a minute
        binding.setTimeout(timeout);

        java.lang.String value = null;
        try {
            value = binding.getBusiTypeFilePath(serSupplier, serCaller, callerPwd, StaticMethod.getCurrentDateTime(), "");
            BocoLog.info(MofficeDataInService.class, "getBusiType return value==" + value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != value && value.indexOf("<opDetail><recordInfo><fieldInfo>") > -1) {
            return value;
        }
        return null;
    }

    /**
     * 审批通过后多次调用派单接口
     *
     * @param main
     * @param preLinkId
     * @param de
     * @param linkIsNeedTest
     * @return
     */
    public static String neWorkSheet4More(MofficeDataMain main, MofficeDataTask task, String de, String linkIsNeedTest) {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub) new DATAProcessSheetLocator().getEomsDataImportImplPort();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager) ApplicationContextHolder.getInstance()
                .getBean("iMofficeDataProMatchManager");
        java.lang.String value = null;
        try {
            List list = proMgr.getProMatchObjects(main.getId());
            if (null != list && !list.isEmpty()) {
                /*如果是多次调用接口，给对方传的sheetID为correKey*/
                if (list.size() > 1) {
                    for (int i = 0; i < list.size(); i++) {
                        MofficeDataProMatch mat = (MofficeDataProMatch) list.get(i);
                        String mainAccesss = mat.getAccessories();
                        Map map = new HashMap();
                        map.put("sheetID", mat.getCorreKey());
                        map.put("serialID", main.getSheetId());
                        if ("user".equals(mat.getProducerType())) {
                            map.put("operators", mat.getProducerId());
                        } else if ("subrole".equals(mat.getProducerType())) {
                            map.put("operators", "");
                        }
                        map.put("attachRef", mainAccesss);
                        map.put("busiName", mat.getBuissType());
                        if ("101400601".equals(linkIsNeedTest)) {
                            map.put("isDial", "true");
                            map.put("isSignal", "false");
                            map.put("isBill", "false");
                        } else if ("101400602".equals(linkIsNeedTest)) {
                            map.put("isDial", "false");
                            map.put("isSignal", "false");
                            map.put("isBill", "true");
                        } else if ("101400603".equals(linkIsNeedTest)) {
                            map.put("isDial", "false");
                            map.put("isSignal", "true");
                            map.put("isBill", "false");
                        }
                        map.put("sheetMemo", main.getTitle());
                        map.put("startTime", main.getSendTime());
                        map.put("endTime", main.getSheetCompleteLimit());
                        map.put("subversion", main.getMainDataNo());

                        String opdetail = MofficeUtil.getXmlFromMap(map,
                                StaticMethod.getFilePathForUrl("classpath:config/mofficedata-util.xml"), "newWorkSheet");
                        String serSupplier = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serSupplier");
                        String serCaller = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serCaller");
                        String callerPwd = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.callerPwd");
                        int timeout = StaticMethod.nullObject2int(XmlManage.getFile("/config/mofficedata-util.xml").getProperty(
                                "interfaces.timeout"));
                        binding.setTimeout(timeout);
                        value = binding.newWorkSheet(serSupplier, serCaller, callerPwd, StaticMethod.getCurrentDateTime(), opdetail);
                        BocoLog.info(MofficeDataInService.class, "neWorkSheet4More return value==" + i + value);
                    }
                } else {/*如果是一次调用接口，给对方传的sheetID为审批环节的tkid*/
                    MofficeDataProMatch mat = (MofficeDataProMatch) list.get(0);
                    String mainAccesss = mat.getAccessories();
                    Map map = new HashMap();
                    map.put("sheetID", task.getCorrelationKey());
                    map.put("serialID", main.getSheetId());
                    if ("user".equals(mat.getProducerType())) {
                        map.put("operators", mat.getProducerId());
                    } else if ("subrole".equals(mat.getProducerType())) {
                        map.put("operators", "");
                    }
                    map.put("attachRef", mainAccesss);
                    map.put("busiName", mat.getBuissType());
                    if ("101400601".equals(linkIsNeedTest)) {
                        map.put("isDial", "true");
                        map.put("isSignal", "false");
                        map.put("isBill", "false");
                    } else if ("101400602".equals(linkIsNeedTest)) {
                        map.put("isDial", "false");
                        map.put("isSignal", "false");
                        map.put("isBill", "true");
                    } else if ("101400603".equals(linkIsNeedTest)) {
                        map.put("isDial", "false");
                        map.put("isSignal", "true");
                        map.put("isBill", "false");
                    }
                    map.put("sheetMemo", main.getTitle());
                    map.put("startTime", main.getSendTime());
                    map.put("endTime", main.getSheetCompleteLimit());
                    map.put("subversion", main.getMainDataNo());

                    String opdetail = MofficeUtil.getXmlFromMap(map,
                            StaticMethod.getFilePathForUrl("classpath:config/mofficedata-util.xml"), "newWorkSheet");
                    String serSupplier = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serSupplier");
                    String serCaller = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serCaller");
                    String callerPwd = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.callerPwd");
                    int timeout = StaticMethod.nullObject2int(XmlManage.getFile("/config/mofficedata-util.xml").getProperty(
                            "interfaces.timeout"));
                    binding.setTimeout(timeout);
                    value = binding.newWorkSheet(serSupplier, serCaller, callerPwd, StaticMethod.getCurrentDateTime(), opdetail);
                    BocoLog.info(MofficeDataInService.class, "neWorkSheet4More return value==" + value);
                }
            } else {
                BocoLog.info(MofficeDataInService.class, "=can not find the promatch object!!!=");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("--局数据接口调用失败（interface failed!!!）--");
        }

        return value;
    }

    /**
     * 在局数据制作环节，确认受理的时候调派单接口
     *
     * @param main
     * @param tkid
     * @param dealperformer
     * @return
     * @date 20161207
     * @author weichao
     */
    public static String neWorkSheet2T(MofficeDataMain main, String correKey, String de) {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub) new DATAProcessSheetLocator().getEomsDataImportImplPort();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager) ApplicationContextHolder.getInstance()
                .getBean("iMofficeDataProMatchManager");
        java.lang.String value = null;
        try {
            MofficeDataProMatch mat = proMgr.getProMatchObjectByCorreKey(correKey);
            Map map = new HashMap();
            map.put("sheetID", mat.getCorreKey());
            map.put("serialID", main.getSheetId());
            map.put("operators", de);
            map.put("busiName", mat.getBuissType());
            map.put("sheetMemo", main.getTitle());
            map.put("startTime", main.getSendTime());
            map.put("endTime", main.getSheetCompleteLimit());
            map.put("subversion", main.getMainDataNo());

            String opdetail = MofficeUtil.getXmlFromMap(map,
                    StaticMethod.getFilePathForUrl("classpath:config/mofficedata-util.xml"), "newWorkSheet");
            String serSupplier = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serSupplier");
            String serCaller = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.serCaller");
            String callerPwd = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.callerPwd");
            int timeout = StaticMethod.nullObject2int(XmlManage.getFile("/config/mofficedata-util.xml").getProperty(
                    "interfaces.timeout"));
            binding.setTimeout(timeout);
            value = binding.newWorkSheet(serSupplier, serCaller, callerPwd, StaticMethod.getCurrentDateTime(), opdetail);
            BocoLog.info(MofficeDataInService.class, "neWorkSheet2T return value==" + value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("--局数据接口调用失败（interface failed!!!）neWorkSheet2T--");
        }

        return value;

    }
}
