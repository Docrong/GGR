package com.boco.eoms.sheet.autodistribute.service;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;

public interface IAutoDistributeManager extends Manager {
    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void saveAutoDistribute(AutoDistribute autoDistribute);

    /**
     * Saves a tawSheetExpert's information
     *
     * @param tawSheetExpert the object to be saved
     */
    public void removeAutoDistribute(String id);

    /**
     * 根据id查询autodistribute记录
     *
     * @param id
     * @return AutoDistribute
     */
    public AutoDistribute getAutoDistribute(String id);

    /**
     * 得到所有的记录
     */
    public HashMap getAllAutoDistribute(Integer pageIndex, Integer pageSize);
}
