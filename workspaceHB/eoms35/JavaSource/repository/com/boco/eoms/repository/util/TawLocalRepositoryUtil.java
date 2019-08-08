package com.boco.eoms.repository.util;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;

public class TawLocalRepositoryUtil {

    /**
     * 将地市编码解码成地市名称
     *
     * @param cityCode
     * @return
     */
    public static String decodeCityFromXML(String cityCode) {
        String result = "";
        if (cityCode == null || "".equals(cityCode)) {
            return "";
        }
        try {
            return EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name("dict-repository#city", cityCode).toString();
        } catch (DictServiceException e) {
            e.printStackTrace();
        }
        return "";
    }

}
