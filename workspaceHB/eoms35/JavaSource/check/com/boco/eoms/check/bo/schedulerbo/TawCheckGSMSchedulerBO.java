package com.boco.eoms.check.bo.schedulerbo;

import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.model.*;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.qo.*;
import com.boco.eoms.check.util.*;

import java.util.*;

public class TawCheckGSMSchedulerBO {

    public void getMothScore(String nowTime) {
        try {
            // nowTime="2006-09-01";
            TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
            String hsql = this.getSql("TawCheckGSMDATA", nowTime);
            ArrayList dataList = (ArrayList) tawCheckSchedulerDAO.getDATA(hsql);
            // by changhongxiang 2007-07-26 删除该年月的分数数据　begin
            if (dataList.size() > 0) {
                TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
                tawCheckDataDAO.delScoreData(nowTime.substring(0, 4), nowTime.substring(5, 7), TawCheckGSMSCORE.class.getSimpleName(), "");
            }
            // by changhongxiang 2007-07-26 删除该年月的分数数据　end
            for (int i = 0; i < dataList.size(); i++) {
                TawCheckGSMDATA tawCheckGSMDATA = new TawCheckGSMDATA();
                tawCheckGSMDATA = (TawCheckGSMDATA) dataList.get(i);
                this.SaveCDAMSCORE(tawCheckGSMDATA, nowTime);
            }
            System.out.print("GSM算分程序已经完成!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 保存分数
    public void SaveCDAMSCORE(TawCheckGSMDATA tawCheckGSMDATA, String nowTime) {
        try {
            TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
            TawCheckTarger tawCheckTarger = new TawCheckTarger();
            TawCheckGSMSCORE tawCheckGSMSCORE = new TawCheckGSMSCORE();
            String year = nowTime.substring(0, 4);
            String month = nowTime.substring(5, 7);
            double sumdata = 0;
            tawCheckGSMSCORE.setScore_year(year);
            tawCheckGSMSCORE.setScore_month(month);
            tawCheckGSMSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(tawCheckGSMDATA
                    .getRegion_zh()));
            tawCheckGSMSCORE.setScore_area_name(tawCheckGSMDATA.getRegion_zh());
            // G42
            tawCheckGSMSCORE.setG42(tawCheckGSMDATA.getG42());
            tawCheckTarger = this.getTarger("G42");
            if (tawCheckTarger != null) {
                tawCheckGSMSCORE.setG42_full(tawCheckTarger
                        .getTarger_score_full());
                tawCheckGSMSCORE.setG42_score(TawCheckSCOREMethod.getScore(
                        tawCheckGSMDATA.getG42(), tawCheckTarger));
                sumdata += tawCheckGSMSCORE.getG42_score();
            }
            // G43

            tawCheckGSMSCORE.setG43(tawCheckGSMDATA.getG43());
            tawCheckTarger = this.getTarger("G43");
            if (tawCheckTarger != null) {
                tawCheckGSMSCORE.setG43_full(tawCheckTarger
                        .getTarger_score_full());
                tawCheckGSMSCORE.setG43_score(TawCheckSCOREMethod.getScore(
                        tawCheckGSMDATA.getG43(), tawCheckTarger));
                sumdata += tawCheckGSMSCORE.getG43_score();
            }
            // G45
            tawCheckGSMSCORE.setG45(tawCheckGSMDATA.getG45());
            tawCheckTarger = this.getTarger("G45");
            if (tawCheckTarger != null) {
                tawCheckGSMSCORE.setG45_full(tawCheckTarger
                        .getTarger_score_full());
                tawCheckGSMSCORE.setG45_score(TawCheckSCOREMethod.getScore(
                        tawCheckGSMDATA.getG45(), tawCheckTarger));
                sumdata += tawCheckGSMSCORE.getG45_score();
            }
            // G65
            tawCheckGSMSCORE.setG65(tawCheckGSMDATA.getG65());
            tawCheckTarger = this.getTarger("G65");
            if (tawCheckTarger != null) {
                tawCheckGSMSCORE.setG65_full(tawCheckTarger
                        .getTarger_score_full());
                tawCheckGSMSCORE.setG65_score(TawCheckSCOREMethod.getScore(
                        tawCheckGSMDATA.getG65(), tawCheckTarger));
                sumdata += tawCheckGSMSCORE.getG65_score();
            }
            // G411
            tawCheckGSMSCORE.setG411(tawCheckGSMDATA.getG411());
            tawCheckTarger = this.getTarger("G411");
            if (tawCheckTarger != null) {
                tawCheckGSMSCORE.setG411_full(tawCheckTarger
                        .getTarger_score_full());
                tawCheckGSMSCORE.setG411_score(TawCheckSCOREMethod.getScore(
                        tawCheckGSMDATA.getG411(), tawCheckTarger));
                sumdata += tawCheckGSMSCORE.getG411_score();
            }
            // G49
            tawCheckGSMSCORE.setG49(tawCheckGSMDATA.getG49());
            tawCheckTarger = this.getTarger("G49");
            if (tawCheckTarger != null) {
                tawCheckGSMSCORE.setG49_full(tawCheckTarger
                        .getTarger_score_full());
                tawCheckGSMSCORE.setG49_score(TawCheckSCOREMethod.getScore(
                        tawCheckGSMDATA.getG49(), tawCheckTarger));
                sumdata += tawCheckGSMSCORE.getG49_score();
            }
            // G66
            tawCheckGSMSCORE.setG66(tawCheckGSMDATA.getG66());
            tawCheckTarger = this.getTarger("G66");
            if (tawCheckTarger != null) {
                tawCheckGSMSCORE.setG66_full(tawCheckTarger
                        .getTarger_score_full());
                tawCheckGSMSCORE.setG66_score(TawCheckSCOREMethod.getScore(
                        tawCheckGSMDATA.getG66(), tawCheckTarger));
                sumdata += tawCheckGSMSCORE.getG66_score();
            }
            tawCheckGSMSCORE.setFull_score(sumdata);
            tawCheckGSMSCORE.setScore_deleted("0");
            tawCheckSchedulerDAO.saveGSMScore(tawCheckGSMSCORE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 得到SQL语句
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

    // 得到具体指标数目
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
