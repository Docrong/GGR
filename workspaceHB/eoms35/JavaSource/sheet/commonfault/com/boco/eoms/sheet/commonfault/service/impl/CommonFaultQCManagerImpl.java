package com.boco.eoms.sheet.commonfault.service.impl;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultQCDAO;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultQCManager;
import java.util.Map;


public class CommonFaultQCManagerImpl implements ICommonFaultQCManager
{
	private ICommonFaultQCDAO qcDao;
	

	public ICommonFaultQCDAO getQcDao() {
		return qcDao;
	}


	public void setQcDao(ICommonFaultQCDAO qcDao) {
		this.qcDao = qcDao;
	}


	public Map selectObject(String sql, Integer curPage, Integer pageSize) {
		return qcDao.selectObject(sql, curPage, pageSize);
	}


	public String getSqlByCondition(Map actionMap) {
		System.out.println("=========筛选条件为："+actionMap);
		String type=StaticMethod.nullObject2String(actionMap.get("type"));
		String beginTime=StaticMethod.nullObject2String(actionMap.get("beginTime"));
		String endTime=StaticMethod.nullObject2String(actionMap.get("endTime"));
		String toAreaId=StaticMethod.nullObject2String(actionMap.get("toAreaId"));
		String holdtype=StaticMethod.nullObject2String(actionMap.get("holdtype"));
		String mainNetSortOne=StaticMethod.nullObject2String(actionMap.get("mainNetSortOne"));
		String mainNetSortTwo=StaticMethod.nullObject2String(actionMap.get("mainNetSortTwo"));
		String mainNetSortThree=StaticMethod.nullObject2String(actionMap.get("mainNetSortThree"));
		String sql2="";
		StringBuffer str=new StringBuffer();
			str=str.append("select distinct m.* from commonfault_main m,commonfault_task t where m.id=t.sheetkey and m.status=1 ");
			if(beginTime!=null&&!"".equals(beginTime)){
				str=str.append(" and m.endtime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss') ");
			}
			if(endTime!=null&&!"".equals(endTime)){
				str=str.append(" and m.endtime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss') ");
			}
			if(toAreaId!=null&&!"".equals(toAreaId)){
				str=str.append(" and m.todeptid in ("+toAreaId+") ");
			}
			if(mainNetSortOne!=null&&!"".equals(mainNetSortOne)){
				str=str.append(" and m.mainNetSortOne ='"+mainNetSortOne+"' ");
			}
			if(mainNetSortTwo!=null&&!"".equals(mainNetSortTwo)){
				str=str.append(" and m.mainNetSortTwo ='"+mainNetSortTwo+"' ");
			}
			if(mainNetSortThree!=null&&!"".equals(mainNetSortThree)){
				str=str.append(" and m.mainNetSortThree ='"+mainNetSortThree+"' ");
			}
			if(holdtype!=null&&"0".equals(holdtype)){
				str=str.append(" and t.taskname='HoldHumTask' and t.taskstatus='5' and t.taskowner<>'alarm' ");
			}
			if(holdtype!=null&&"1".equals(holdtype)){
				str=str.append(" and t.taskname='HoldHumTask' and t.taskstatus='5' and t.taskowner='alarm' ");
			}
		if("screen".equals(type)){
			str=str.append(" and m.qcmark =0 "); 	
			String flag=StaticMethod.nullObject2String(actionMap.get("flag"));
			if("off".equals(flag)){
				str=str.append(" order by dbms_random.value ");
				int num=StaticMethod.nullObject2int(actionMap.get("num"));
				int ratio=StaticMethod.nullObject2int(actionMap.get("ratio"));
				sql2="select row_.*,rownum rn from ("+str.toString()+") row_  where rownum <="+(num*ratio/100);
			}else{
				sql2=str.toString();
			}
		}else{
			sql2="select row_.*,rownum rn from ("+str.toString()+") row_";
		}
		return sql2;
	}

	public int getTotalBySql(String sql) {
		return qcDao.getTotalBySql(sql);
	}  
}