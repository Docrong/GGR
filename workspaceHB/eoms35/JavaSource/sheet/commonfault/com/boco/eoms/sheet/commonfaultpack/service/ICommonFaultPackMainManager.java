
package com.boco.eoms.sheet.commonfaultpack.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;

public interface ICommonFaultPackMainManager extends IMainService {
    public Integer getCountByMainId(final String mainId) throws HibernateException, SheetException;

    public List getListByMainId(final Integer curPage, final Integer pageSize, String mainId) throws HibernateException, SheetException;

    public List getListByMainId(String mainId) throws SheetException;

    public String getIfAlarmSolve(String alarmId) throws SheetException;

    /**
     * 通过告警号获取工单
     *
     * @param alarmId 告警号
     * @return
     * @throws HibernateException
     */
    public CommonFaultPackMain getMainByAlarmId(String alarmId);

    public abstract boolean isAllHaveClearTime(String s);

}

