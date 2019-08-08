package com.boco.eoms.sheet.commonfaultAlarm.service;

import java.util.Date;
import java.util.List;

import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfaultAlarm.model.AlarmsolvedateConfig;

public interface IAlarmsolveDateManager {
    public Date alarmsolveDate(String alarmId) throws Exception;

    public void updateEomsAlarmsolveDate(AlarmsolvedateConfig obj, CommonFaultMain main) throws Exception;

    public void updateAlarmsolveDate(AlarmsolvedateConfig acf, CommonFaultMain main) throws Exception;

    public List findbyall(int issended) throws Exception;

    public void updateAlarmsolveDate(AlarmsolvedateConfig acf) throws Exception;

    public void updateCheckDate(String sheetid, String checkTime) throws Exception;
}
