package com.boco.eoms.sheet.commonfaultAlarm.dao;

import java.util.Date;

public interface IAlarmsolveDateDao {
    public Date findbyid(String alarmsolveid);

    public void update(String alarmsolveid, String canceltime);

    public void updateCheckTime(String sheetid, String checkTime);
}
