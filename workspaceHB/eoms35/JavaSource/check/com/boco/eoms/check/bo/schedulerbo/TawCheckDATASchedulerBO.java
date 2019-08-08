package com.boco.eoms.check.bo.schedulerbo;

import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.model.*;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.qo.*;
import com.boco.eoms.check.util.*;

import java.util.*;

public class TawCheckDATASchedulerBO {

    public void getMothScore(String nowTime) {
        try {
            nowTime = "2007-02-01";
            TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
            String hsql = this.getSql("TawCheckDATAData", nowTime);
            ArrayList dataList = (ArrayList) tawCheckSchedulerDAO.getDATA(hsql);
            for (int i = 0; i < dataList.size(); i++) {
                TawCheckDATAData tawCheckDATAData = new TawCheckDATAData();
                tawCheckDATAData = (TawCheckDATAData) dataList.get(i);
                this.SaveDATASCORE(tawCheckDATAData, nowTime);
            }
            System.out.print("DATA算分程序已经完成!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //保存分数
    public void SaveDATASCORE(TawCheckDATAData tawCheckDATAData, String nowTime) {
        try {
            TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
            TawCheckTarger tawCheckTarger = new TawCheckTarger();
            TawCheckDATASCORE tawCheckDATASCORE = new TawCheckDATASCORE();
            String year = nowTime.substring(0, 4);
            String month = nowTime.substring(5, 7);
            tawCheckDATASCORE.setScore_year(year);
            tawCheckDATASCORE.setScore_month(month);
            tawCheckDATASCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(tawCheckDATAData
                    .getRegion_zh()));
            tawCheckDATASCORE.setScore_area_name(tawCheckDATAData.getRegion_zh());
            //G42
            tawCheckDATASCORE.setT42(tawCheckDATAData.getT42());
            tawCheckTarger = this.getTarger("T42");
            if (tawCheckTarger != null) {
                tawCheckDATASCORE.setT42_full(tawCheckTarger.getTarger_score_full());
                tawCheckDATASCORE.setT42_score(TawCheckSCOREMethod.getScore(tawCheckDATAData.getT42(), tawCheckTarger));
            }
            //G43

            tawCheckDATASCORE.setT43(tawCheckDATAData.getT43());
            tawCheckTarger = this.getTarger("T43");
            if (tawCheckTarger != null) {
                tawCheckDATASCORE.setT43_full(tawCheckTarger.getTarger_score_full());
                tawCheckDATASCORE.setT43_score(TawCheckSCOREMethod.getScore(tawCheckDATAData.getT43(), tawCheckTarger));
            }
            //G45
            tawCheckDATASCORE.setT45(tawCheckDATAData.getT45());
            tawCheckTarger = this.getTarger("T45");
            if (tawCheckTarger != null) {
                tawCheckDATASCORE.setT45_full(tawCheckTarger.getTarger_score_full());
                tawCheckDATASCORE.setT45_score(TawCheckSCOREMethod.getScore(tawCheckDATAData.getT45(), tawCheckTarger));
            }
            //G65
            tawCheckDATASCORE.setT65(tawCheckDATAData.getT65());
            tawCheckTarger = this.getTarger("T65");
            if (tawCheckTarger != null) {
                tawCheckDATASCORE.setT65_full(tawCheckTarger.getTarger_score_full());
                tawCheckDATASCORE.setT65_score(TawCheckSCOREMethod.getScore(tawCheckDATAData.getT65(), tawCheckTarger));
            }
            //G411
            tawCheckDATASCORE.setT411(tawCheckDATAData.getT411());
            tawCheckTarger = this.getTarger("T411");
            if (tawCheckTarger != null) {
                tawCheckDATASCORE.setT411_full(tawCheckTarger.getTarger_score_full());
                tawCheckDATASCORE.setT411_score(TawCheckSCOREMethod.getScore(tawCheckDATAData.getT411(), tawCheckTarger));
            }
            //G49
            tawCheckDATASCORE.setT49(tawCheckDATAData.getT49());
            tawCheckTarger = this.getTarger("T49");
            if (tawCheckTarger != null) {
                tawCheckDATASCORE.setT49_full(tawCheckTarger.getTarger_score_full());
                tawCheckDATASCORE.setT49_score(TawCheckSCOREMethod.getScore(tawCheckDATAData.getT49(), tawCheckTarger));
            }
            //G66
            tawCheckDATASCORE.setT66(tawCheckDATAData.getT66());
            tawCheckTarger = this.getTarger("T66");
            if (tawCheckTarger != null) {
                tawCheckDATASCORE.setT66_full(tawCheckTarger.getTarger_score_full());
                tawCheckDATASCORE.setT66_score(TawCheckSCOREMethod.getScore(tawCheckDATAData.getT66(), tawCheckTarger));
            }
            tawCheckDATASCORE.setFull_score(0);
            tawCheckDATASCORE.setScore_deleted("0");
            tawCheckSchedulerDAO.saveDATAScore(tawCheckDATASCORE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //得到SQL语句
    public String getSql(String tableName, String nowTime) {
        String hsql = "";
        try {
            TawCheckSchedulerQO tawCheckSchedulerQO = new TawCheckSchedulerQO();
            tawCheckSchedulerQO.setNowTime(nowTime);
            hsql = tawCheckSchedulerQO.gethsql(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hsql;
    }

    //得到具体指标数目
    public TawCheckTarger getTarger(String type) {
        TawCheckTarger tawCheckTarger = new TawCheckTarger();
        TawCheckTargerDAO tawCheckTargerDAO = new TawCheckTargerDAO();
        TawCheckTargerQO tawCheckTargerQO = new TawCheckTargerQO();
        tawCheckTargerQO.setType(type);
        String hsql = tawCheckTargerQO.getHsql();
        try {
            ArrayList list = (ArrayList) tawCheckTargerDAO.getTargerList(hsql);
            if (list.size() > 0) {
                tawCheckTarger = (TawCheckTarger) list.get(0);
            } else if (list.size() <= 0) {
                tawCheckTarger = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tawCheckTarger;
    }

}

