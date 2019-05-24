package com.boco.eoms.check.bo.schedulerbo;

import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.model.*;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.qo.*;
import com.boco.eoms.check.util.*;

import java.util.*;

public class TawCheckCDMASchedulerBO {

	public void getMothScore(String nowTime) {
		try {
			// nowTime="2006-09-01";
			TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
			String hsql = this.getSql("TawCheckCDMADATA", nowTime);
			ArrayList dataList = (ArrayList) tawCheckSchedulerDAO.getDATA(hsql);
			// by changhongxiang 2007-07-26 删除该年月的分数数据 begin
			if (dataList.size() > 0) {
				TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
				tawCheckDataDAO.delScoreData(nowTime.substring(0, 4), nowTime
						.substring(5, 7), TawCheckCDMASCORE.class
						.getSimpleName(), "");
			}
			// by changhongxiang 2007-07-26 删除该年月的分数数据 end
			for (int i = 0; i < dataList.size(); i++) {
				TawCheckCDMADATA tawCheckCDMADATA = new TawCheckCDMADATA();
				tawCheckCDMADATA = (TawCheckCDMADATA) dataList.get(i);
				this.SaveCDAMSCORE(tawCheckCDMADATA, nowTime);
			}
			System.out.print("CDMA算分程序已经完成!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 保存分数
	public void SaveCDAMSCORE(TawCheckCDMADATA tawCheckCDMADATA, String nowTime) {
		try {
			TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
			TawCheckTarger tawCheckTarger = new TawCheckTarger();
			TawCheckCDMASCORE tawCheckCDMASCORE = new TawCheckCDMASCORE();
			String year = nowTime.substring(0, 4);
			String month = nowTime.substring(5, 7);
			tawCheckCDMASCORE.setScore_year(year);
			tawCheckCDMASCORE.setScore_month(month);
			double sumdata=0;
			tawCheckCDMASCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(tawCheckCDMADATA
					.getRegion_zh()));
			tawCheckCDMASCORE.setScore_area_name(tawCheckCDMADATA
					.getRegion_zh());
			// C42
			tawCheckCDMASCORE.setC42(tawCheckCDMADATA.getC42());
			tawCheckTarger = this.getTarger("C42");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setC42_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE.setC42_score(TawCheckSCOREMethod.getScore(
						tawCheckCDMADATA.getC42(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getC42_score();
			}
			// C43

			tawCheckCDMASCORE.setC43(tawCheckCDMADATA.getC43());
			tawCheckTarger = this.getTarger("C43");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setC43_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE.setC43_score(TawCheckSCOREMethod.getScore(
						tawCheckCDMADATA.getC43(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getC43_score();
			}
			// C45
			tawCheckCDMASCORE.setC45(tawCheckCDMADATA.getC45());
			tawCheckTarger = this.getTarger("C45");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setC45_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE.setC45_score(TawCheckSCOREMethod.getScore(
						tawCheckCDMADATA.getC45(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getC45_score();
			}
			// C65
			tawCheckCDMASCORE.setC65(tawCheckCDMADATA.getC65());
			tawCheckTarger = this.getTarger("C65");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setC65_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE.setC65_score(TawCheckSCOREMethod.getScore(
						tawCheckCDMADATA.getC65(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getC65_score();
			}
			// C411
			tawCheckCDMASCORE.setC411(tawCheckCDMADATA.getC411());
			tawCheckTarger = this.getTarger("C411");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setC411_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE.setC411_score(TawCheckSCOREMethod.getScore(
						tawCheckCDMADATA.getC411(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getC411_score();
			}
			// p26
			tawCheckCDMASCORE.setVir_p26(tawCheckCDMADATA.getVir_p26());
			tawCheckTarger = this.getTarger("VIR_P26");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setVir_p26_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE
						.setVir_p26_score(TawCheckSCOREMethod.getScore(
								tawCheckCDMADATA.getVir_p26(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getVir_p26_score();
			}
			// C64
			tawCheckCDMASCORE.setC64(tawCheckCDMADATA.getC64());
			tawCheckTarger = this.getTarger("C64");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setC64_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE.setC64_score(TawCheckSCOREMethod.getScore(
						tawCheckCDMADATA.getC64(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getC64_score();
			}
			// C412
			tawCheckCDMASCORE.setC412(tawCheckCDMADATA.getC412());
			tawCheckTarger = this.getTarger("C412");
			if (tawCheckTarger != null) {
				tawCheckCDMASCORE.setC412_full(tawCheckTarger
						.getTarger_score_full());
				tawCheckCDMASCORE.setC412_score(TawCheckSCOREMethod.getScore(
						tawCheckCDMADATA.getC412(), tawCheckTarger));
				sumdata+=tawCheckCDMASCORE.getC412_score();
			}
			tawCheckCDMASCORE.setFull_score(sumdata);
			tawCheckCDMASCORE.setScore_deleted("0");
			tawCheckSchedulerDAO.saveCDMAScore(tawCheckCDMASCORE);
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
