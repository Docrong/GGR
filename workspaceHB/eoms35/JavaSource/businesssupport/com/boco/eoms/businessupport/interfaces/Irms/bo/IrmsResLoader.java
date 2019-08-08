package com.boco.eoms.businessupport.interfaces.Irms.bo;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.irms.framework.resource.resourceoper.bo.tempres.IEomsResTempBOSoapBindingStub;
import com.boco.irms.framework.resource.resourceoper.bo.tempres.IEomsResTempBO_ServiceLocator;


public class IrmsResLoader {
    private static IEomsResTempBOSoapBindingStub loadService() throws Exception {
        String irmsres = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/Irms/config/irmsres-config.xml").getProperty("ServiceUrl");
        System.out.println("irmsres url:" + irmsres);
        URL url = new URL(irmsres);
        IEomsResTempBOSoapBindingStub binding = (IEomsResTempBOSoapBindingStub) (new IEomsResTempBO_ServiceLocator()).getEomsResTempWSPort(url);
        return binding;
    }

    /**
     * 将电路名称传给资源系统
     *
     * @param custName    客户的名称
     * @param prodName    产品的名称
     * @param prodCode    产品的CODE
     * @param circuitName 电路名称
     * @param resType     1是传输 2是光纤 3是基站
     * @return
     * @throws Exception
     */
    public static String addEomsResByProdTypeBO(Map map, List list) throws Exception {

        try {
            String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, list, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/Irms/config/irmsres-config.xml"), "addEomsResByProdTypeBO");

            String result = IrmsResLoader.loadService().addEomsResByProdTypeBO(opDetail);
            BocoLog.info(IrmsResLoader.class, "addEomsResByProdTypeBO result=" + result);
            if (result.equalsIgnoreCase("success"))
                return "0";
            else
                throw new Exception("调用资源系统保存电路信息失败");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("调用资源系统保存电路信息失败:" + e.getMessage());
        }
    }
}
