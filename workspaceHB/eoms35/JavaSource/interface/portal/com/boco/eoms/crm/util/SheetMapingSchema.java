package com.boco.eoms.crm.util;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;

public class SheetMapingSchema {
    private static SheetMappings nodes = null;
    private static SheetMapingSchema sheetMapingSchema = null;

    public static SheetMapingSchema getInstance() throws Exception {
        if (sheetMapingSchema == null) {
            sheetMapingSchema = new SheetMapingSchema();
            try {
                loadXml();
            } catch (Exception e) {
                throw e;
            }
        }
        return sheetMapingSchema;
    }

    private static void loadXml() throws Exception {
        try {
            nodes = (SheetMappings) ParseXmlService
                    .create()
                    .xml2object(SheetMappings.class, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/crm/config/crm-sheet-config.xml"));

        } catch (ParseXMLException e) {
            e.printStackTrace();
            throw new Exception("读取配置文件'crm-sheet-config.xml'出错");
        }
    }

    public String getBeanId(String sheetType, String serviceType) {
        if (nodes != null) {
            SheetType[] sheetTypeList = nodes.getSheetType();
            for (int i = 0; i < sheetTypeList.length; i++) {
                SheetType st = (SheetType) sheetTypeList[i];
                if (st.getTypeid().equals(sheetType)) {
                    ServiceType[] serviceTypeList = st.getServiceType();
                    for (int j = 0; j < serviceTypeList.length; j++) {
                        ServiceType service = (ServiceType) serviceTypeList[j];
                        if (service.getTypeid().equals(serviceType)) {
                            return service.getBeanid();
                        }
                    }
                }
            }
        }
        return "";
    }
}
