package com.boco.eoms.commons.sheet.special.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialJdbc;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;

/**
 *
 *panlong
 *下午04:43:30
 */
public class TawSheetSpecialJdbcImpl extends BaseDaoJdbc implements
		TawSheetSpecialJdbc {
	/* 
     * 得到最大的专业ID
     */
	public String getMaxSpecialId(String paraspeid,int len){
		String sql = " select max(speid) as speid from taw_common_sheet_special where speid like '"+paraspeid+"%' and length(speid)<='"+len+"'";
		
		String maxspeid = "";
		
		maxspeid = StaticMethod.nullObject2String(getJdbcTemplate().queryForMap(sql).get("speid"));
		if ( (maxspeid.equals("") || maxspeid.equals("null")) ) {
			maxspeid = TawSystemAreaUtil.AREA_DEFAULT_STRONE;
		}
		return maxspeid;
	}
	
	/**
	 * 保存专业与用户关联
	 * @param speid
	 * @param userid
	 * @return
	 */
	public void saveSpecialRefUser(String speid,String userid){
		String sql = " insert into taw_sheet_special_refuser(specialid,userid) values('"+speid+"','"+userid+"')";
        getJdbcTemplate().execute(sql);
	}
	
	/**
	 * 查询专业用户表是否已经存在
	 * @param speid
	 * @return
	 */
	public boolean isExitsSpecialRefUser(String speid){
		String sql = " select * from taw_sheet_special_refuser where specialid='"+speid+"'";
		List list =null;
		try{
			list = getJdbcTemplate().queryForList(sql);
		if( null==list ||list.isEmpty()){
			return false;
		}else{
			return true;
		}
		}catch(Exception ex){
			return false;
		}
	}
	
	/**
	 * 删除某专业对应的专业专家记录
	 * @param speid
	 */
	public void removeSpecialRefUser(String speid){
		String sql = " delete from taw_sheet_special_refuser where specialid='"+speid+"'";
		getJdbcTemplate().execute(sql);
	}
	
	/**
	 * 删除某专业已经子专业
	 * @param speid
	 */
	public void removeTawSheetSpecial(String speid){
	    String sql = " delete from taw_common_sheet_special where speid like '"+speid+"%'";
	    getJdbcTemplate().execute(sql);
	}
	
	/**
	 * 某专业下的人员列表
	 * @param speid
	 */
	public List getSpecialRefUserList(String speid){
		String sql = " select userid from taw_sheet_special_refuser where specialid='"+speid+"'";
		List list1 =null;
		try{
		list1 = getJdbcTemplate().queryForList(sql);		
		}catch(Exception ex){
			return new ArrayList();
		}
		return list1;
	}

}

