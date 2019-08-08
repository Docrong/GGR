package com.boco.eoms.sheet.mofficedata.service;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 *
 * @author weichao
 * @version 3.5
 */

public interface IMofficeDataProMatchManager {
    public void saveOrUpdate(MofficeDataProMatch obj) throws Exception;

    public List getProMatchObjects(String mainId) throws Exception;

    public HashMap getProMatchsByCondition(String hql, Integer pageIndex, Integer pageSize) throws Exception;

    public MofficeDataProMatch getProMatchObjectByCorreKey(String tkid) throws Exception;

    public MofficeDataProMatch getProMatchObjectByPreLinkId(String prelinkId) throws Exception;

    public MofficeDataProMatch getProMatchObjectById(String prelinkId) throws Exception;

    public boolean delById(String id) throws Exception;


}