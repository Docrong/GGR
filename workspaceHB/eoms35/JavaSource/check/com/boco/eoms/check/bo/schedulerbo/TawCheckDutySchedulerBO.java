package com.boco.eoms.check.bo.schedulerbo;
import java.util.*;

import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.model.*;
import com.boco.eoms.check.qo.TawCheckTargerQO;
import com.boco.eoms.check.util.TawCheckSCOREMethod;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.dao.*;
public class TawCheckDutySchedulerBO
{
//	public void checkDutyInterfaces(String nowTime)
//	{
//		try
//		{
//		  this.insertFinish(nowTime);
//		  this.insertRate(nowTime);
//		  System.out.println("ֵ�࿼�˳��������");
//		}catch(Exception e)
//		{e.printStackTrace();}
//	}
/**
 * 
 * @param nowTime
 */
//	public void insertRate(String nowTime)
//	{
//	 try
//	 {
//		TawCheckEomsDAO tawCheckEomsDAO=new TawCheckEomsDAO();
//		ArrayList rateList=new ArrayList();
//		rateList=(ArrayList)tawCheckEomsDAO.getTawCheckDutyDATA(nowTime, "1");
//        for(int i=0;i<rateList.size();i++)
//        { 
//        	TawDutyScheduler tawDutyScheduler=(TawDutyScheduler)rateList.get(i);
//            TawCheckDutyScore tawCheckDutyScore=new TawCheckDutyScore();
//            TawCheckTarger tawCheckTarger=new TawCheckTarger();
//            tawCheckDutyScore.setScore_user_id(tawDutyScheduler.getDutymanId());
//            tawCheckDutyScore.setScore_user_name(new TawRmUserDAO().getUserName(tawDutyScheduler.getDutymanId()));
//            tawCheckDutyScore.setScore_year(tawDutyScheduler.getYear());
//            tawCheckDutyScore.setScore_month(tawDutyScheduler.getMonth());
//            //rate
//            tawCheckDutyScore.setRate(tawDutyScheduler.getDutyRate());
//            tawCheckTarger=this.getTarger("duty_rate");
//if(tawCheckTarger!=null)
//{
//            tawCheckDutyScore.setRate_full(tawCheckTarger.getTarger_score_full());
//            tawCheckDutyScore.setRate_score(TawCheckSCOREMethod.getScore(tawDutyScheduler.getDutyRate(),tawCheckTarger));
//}
//            tawCheckDutyScore.setFlag_score("1");	
//            tawCheckDutyScore.setScore_deleted("0");
//            TawCheckSchedulerDAO tawCheckSchedulerDAO=new TawCheckSchedulerDAO();
//            tawCheckSchedulerDAO.saveDutyScore(tawCheckDutyScore);
//}
//	 }catch(Exception e)
//        {e.printStackTrace();}
//	}
/**
 * 
 * @param nowTime
 */
//	public void insertFinish(String nowTime)
//	{
//		 try
//		 {
//			TawCheckEomsDAO tawCheckEomsDAO=new TawCheckEomsDAO();
//			ArrayList finishList=new ArrayList();
//			finishList=(ArrayList)tawCheckEomsDAO.getTawCheckDutyDATA(nowTime, "2");
//	        for(int i=0;i<finishList.size();i++)
//	        { 
//	        	TawDutyScheduler tawDutyScheduler=(TawDutyScheduler)finishList.get(i);
//	            TawCheckDutyScore tawCheckDutyScore=new TawCheckDutyScore();
//	            TawCheckTarger tawCheckTarger=new TawCheckTarger();
//	            tawCheckDutyScore.setScore_room_id(String.valueOf(tawDutyScheduler.getRoom_id()));
//	//            tawCheckDutyScore.setScore_room_name(new TawUserRoomDAO().getRoomName(tawDutyScheduler.getRoom_id()));
//	            tawCheckDutyScore.setScore_year(tawDutyScheduler.getYear());
//	            tawCheckDutyScore.setScore_month(tawDutyScheduler.getMonth());
//	            //finish
//	            tawCheckDutyScore.setFinish(tawDutyScheduler.getDutyFinish());
//	            tawCheckTarger=this.getTarger("duty_finish");
//	if(tawCheckTarger!=null)
//	{
//	            tawCheckDutyScore.setFinish_full(tawCheckTarger.getTarger_score_full());
//	            tawCheckDutyScore.setFinish_score(TawCheckSCOREMethod.getScore(tawDutyScheduler.getDutyFinish(),tawCheckTarger));
//	}
//	            tawCheckDutyScore.setFlag_score("2");	
//	            tawCheckDutyScore.setScore_deleted("0");
//	            TawCheckSchedulerDAO tawCheckSchedulerDAO=new TawCheckSchedulerDAO();
//	            tawCheckSchedulerDAO.saveDutyScore(tawCheckDutyScore);
//	}
//		 }catch(Exception e)
//	        {e.printStackTrace();}		
//	}
	public TawCheckTarger getTarger(String type)
	{
		TawCheckTarger tawCheckTarger=new TawCheckTarger();
		TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
		TawCheckTargerQO tawCheckTargerQO=new TawCheckTargerQO();
		tawCheckTargerQO.setType(type);
		String hsql=tawCheckTargerQO.getHsql();
		try{
			ArrayList list =(ArrayList)tawCheckTargerDAO.getTargerList(hsql);
			if(list.size()>0)
			{
				tawCheckTarger=(TawCheckTarger)list.get(0);
			}
			else if(list.size()<=0)
			{
				tawCheckTarger=null;
			}
	        }catch(Exception e)
		{e.printStackTrace();}
		return tawCheckTarger;
	}
}
