package com.boco.eoms.duty.util;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.duty.dao.TawDutySheetDAO;
import com.boco.eoms.duty.dao.TawRmCycleTableSubDAO;

public class StaticDutycheck {

    public static int dutycheckcount = 10;

    public StaticDutycheck() {
    }

    public static String getDutycheckName(String id) {
        String checkId = StaticMethod.null2String(id);
        String name = "";

        if ("01".equalsIgnoreCase(checkId)) name = "端局";
        else if ("02".equalsIgnoreCase(checkId)) name = "关口局";
        else if ("03".equalsIgnoreCase(checkId)) name = "HLR";
        else if ("04".equalsIgnoreCase(checkId)) name = "智能网";
        else if ("05".equalsIgnoreCase(checkId)) name = "短信";
        else if ("06".equalsIgnoreCase(checkId)) name = "彩信";
        else if ("07".equalsIgnoreCase(checkId)) name = "HSTP";
        else if ("08".equalsIgnoreCase(checkId)) name = "TMG/TMSC";
        else if ("09".equalsIgnoreCase(checkId)) name = "彩铃";
        else if ("10".equalsIgnoreCase(checkId)) name = "新业务平台(USSD、LBS等)";

        return name;
    }

    public static String getDutycheckId(int id) {
        String checkId = id < 10 ? "0" + id : String.valueOf(id);
        return checkId;
    }

    public String getUrl(String id, String workid) {
        String url = "";
        try {

            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO();
            url = tawDutySheetDAO.getUrl(id, workid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public String getCycleUrl(String id, String workid, String cycle) {
        String url = "";
        try {

            TawRmCycleTableSubDAO tawRmCycleTableSubDAO = new TawRmCycleTableSubDAO();
            url = tawRmCycleTableSubDAO.getCycleUrl(id, workid, cycle);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
