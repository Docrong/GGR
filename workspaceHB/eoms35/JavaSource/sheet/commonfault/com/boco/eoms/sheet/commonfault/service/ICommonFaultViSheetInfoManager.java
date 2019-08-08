package com.boco.eoms.sheet.commonfault.service;

import com.boco.eoms.sheet.commonfault.model.CommonFaultViSheetInfo;

/**
 * @author weichao
 */
public interface ICommonFaultViSheetInfoManager {
    /**
     * @param obj
     * @throws Exception
     */
    public void saveOrUpdate(CommonFaultViSheetInfo obj) throws Exception;

    /**
     * 根据子单的mainId找被修改的时间信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public CommonFaultViSheetInfo getCommonFaultViSheetInfoBymainId(String id) throws Exception;

    /**
     * 根据子单的mainId和vid找被修改的时间信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public CommonFaultViSheetInfo getCommonFaultViSheetInfoByVisId(String mid, String vid) throws Exception;
}
