package com.boco.eoms.check.bo.schedulerbo;

import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.model.*;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.qo.*;
import com.boco.eoms.check.util.*;

import java.util.*;

public class TawCheckTransSchedulerBO {

    public void getMothScore(String nowTime) {
        try {
            nowTime = "2007-01-01";
            TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
            String hsql = this.getSql("TawCheckTransData", nowTime);
            ArrayList dataList = (ArrayList) tawCheckSchedulerDAO.getDATA(hsql);
            for (int i = 0; i < dataList.size(); i++) {
                TawCheckTransData tawCheckGSMDATA = new TawCheckTransData();
                tawCheckGSMDATA = (TawCheckTransData) dataList.get(i);
                this.SaveTransSCORE(tawCheckGSMDATA, nowTime);
            }
            System.out.print("trans算分程序已经完成!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //保存分数
    public void SaveTransSCORE(TawCheckTransData tawCheckGSMDATA, String nowTime) {
        try {
            TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
            TawCheckTarger tawCheckTarger = new TawCheckTarger();
            TawCheckTransSCORE tawCheckTransSCORE = new TawCheckTransSCORE();
            String year = nowTime.substring(0, 4);
            String month = nowTime.substring(5, 7);
            tawCheckTransSCORE.setScore_year(year);
            tawCheckTransSCORE.setScore_month(month);
            tawCheckTransSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(tawCheckGSMDATA
                    .getRegion_zh()));
            tawCheckTransSCORE.setScore_area_name(tawCheckGSMDATA.getRegion_zh());
            //G42
            tawCheckTransSCORE.setT42(tawCheckGSMDATA.getT42());
            tawCheckTarger = this.getTarger("T42");
            if (tawCheckTarger != null) {
                tawCheckTransSCORE.setT42_full(tawCheckTarger.getTarger_score_full());
                tawCheckTransSCORE.setT42_score(TawCheckSCOREMethod.getScore(tawCheckGSMDATA.getT42(), tawCheckTarger));
            }
            //G43

            tawCheckTransSCORE.setT43(tawCheckGSMDATA.getT43());
            tawCheckTarger = this.getTarger("T43");
            if (tawCheckTarger != null) {
                tawCheckTransSCORE.setT43_full(tawCheckTarger.getTarger_score_full());
                tawCheckTransSCORE.setT43_score(TawCheckSCOREMethod.getScore(tawCheckGSMDATA.getT43(), tawCheckTarger));
            }
            //G45
            tawCheckTransSCORE.setT45(tawCheckGSMDATA.getT45());
            tawCheckTarger = this.getTarger("T45");
            if (tawCheckTarger != null) {
                tawCheckTransSCORE.setT45_full(tawCheckTarger.getTarger_score_full());
                tawCheckTransSCORE.setT45_score(TawCheckSCOREMethod.getScore(tawCheckGSMDATA.getT45(), tawCheckTarger));
            }
            //G65
            tawCheckTransSCORE.setT65(tawCheckGSMDATA.getT65());
            tawCheckTarger = this.getTarger("T65");
            if (tawCheckTarger != null) {
                tawCheckTransSCORE.setT65_full(tawCheckTarger.getTarger_score_full());
                tawCheckTransSCORE.setT65_score(TawCheckSCOREMethod.getScore(tawCheckGSMDATA.getT65(), tawCheckTarger));
            }
            //G411
            tawCheckTransSCORE.setT411(tawCheckGSMDATA.getT411());
            tawCheckTarger = this.getTarger("T411");
            if (tawCheckTarger != null) {
                tawCheckTransSCORE.setT411_full(tawCheckTarger.getTarger_score_full());
                tawCheckTransSCORE.setT411_score(TawCheckSCOREMethod.getScore(tawCheckGSMDATA.getT411(), tawCheckTarger));
            }
            //G49
            tawCheckTransSCORE.setT49(tawCheckGSMDATA.getT49());
            tawCheckTarger = this.getTarger("T49");
            if (tawCheckTarger != null) {
                tawCheckTransSCORE.setT49_full(tawCheckTarger.getTarger_score_full());
                tawCheckTransSCORE.setT49_score(TawCheckSCOREMethod.getScore(tawCheckGSMDATA.getT49(), tawCheckTarger));
            }
            //G66
            tawCheckTransSCORE.setT66(tawCheckGSMDATA.getT66());
            tawCheckTarger = this.getTarger("T66");
            if (tawCheckTarger != null) {
                tawCheckTransSCORE.setT66_full(tawCheckTarger.getTarger_score_full());
                tawCheckTransSCORE.setT66_score(TawCheckSCOREMethod.getScore(tawCheckGSMDATA.getT66(), tawCheckTarger));
            }
            tawCheckTransSCORE.setFull_score(0);
            tawCheckTransSCORE.setScore_deleted("0");
            tawCheckSchedulerDAO.saveTransScore(tawCheckTransSCORE);
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

