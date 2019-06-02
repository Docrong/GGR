package com.boco.eoms.commons.system.dict.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;

/**
 * <p>
 * Title:id2name的factory，其中应包括db,xml的id2nameService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-11-8 15:47:03
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class ID2NameServiceFactory {

    /**
     * 获取db的id2nameService
     * 
     * @return db的id2nameService
     */
    public static ID2NameService getId2nameServiceDB() {
        return (ID2NameService) ApplicationContextHolder.getInstance().getBean(
                "id2nameService");
    }
}
