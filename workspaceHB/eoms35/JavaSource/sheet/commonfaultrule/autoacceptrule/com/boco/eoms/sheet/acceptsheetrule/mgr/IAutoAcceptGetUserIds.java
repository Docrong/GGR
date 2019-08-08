package com.boco.eoms.sheet.acceptsheetrule.mgr;

import java.util.HashMap;
import java.util.List;

public interface IAutoAcceptGetUserIds {
    /**
     * 根据已有的用户ID，得到可以分配任务的userId列表
     * 例如查询值班表
     *
     * @param userIdList 所有符合接单条件UserId的List
     * @return list 得到可以分配工单userId的List
     * @throws Exception
     */
    public List getIsUsedUserIdList(List userIdList) throws Exception;


}
