/*
 * Created on 2008-08-09
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.qo.impl;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.qo.DataBaseTypeAdapterQO;

/**
 * @author wangjianhua
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WorkSheetQOhelper {
	
	public static String addLikeClause(String clause, String clauseValue) {
		String str = "";
		if (!StaticMethod.null2String(clauseValue, "").trim().equals("")) {
			str = " and " + clause + "  like '%"
					+ clauseValue.trim() + "%' ";
		}
		return str;
	}	

	public static String addEqualClause( String clause, String clauseValue) {
		String str = "";
		if (!StaticMethod.null2String(clauseValue, "").trim().equals("")) {
			str = " and " + clause + "  = '" + clauseValue.trim() + "' ";
		}
		return str;
	}
	
	public static String addInClause(String clause, String clauseValue) {
		String str = "";
		if (!StaticMethod.null2String(clauseValue, "").trim().equals("")) {
			clauseValue = "'" + clauseValue.replaceAll(",", "','") + "'";
			str = " and " + clause + "  in (" + clauseValue.trim() + ")";
		}
		return str;
	}

	public static String addGreatOrEqualDateClause(String clause,
			String codition, String clauseValue, DataBaseTypeAdapterQO dataBaseTypeAdapterQO) {
		String str = "";
		if (!StaticMethod.null2String(clauseValue, "").trim().equals("")) {
			str =  clause +  codition  + dataBaseTypeAdapterQO.getDateTypeAdapter(clauseValue.trim());
		}
		return str;
	}
	
	public static String addGreatOrEqualNumberClause(String clause,
			String codition, String clauseValue) {
		String str = "";
		if (!StaticMethod.null2String(clauseValue, "").trim().equals("")) {
			str =  clause +  codition + clauseValue.trim();
		}
		return str;
	}
	
	public static String addAndClause() {
		return "and";
	}
	
	public static String addORClause() {
		return "or";
	}
	
	public static String addOrderClause(String clause, String clauseValue) {
	    String str = "";

	    //去掉多余的空格的临时解决方法，可能会去掉有用的空格。
	    if(clauseValue.startsWith(" ") || clauseValue.endsWith(" ")){
	      clauseValue = clauseValue.replaceAll(" ","");
	    }

	    if (!StaticMethod.null2String(clauseValue, "").equals("")) {
	      str = clause + " " + clauseValue ;
	    }
	    return str;
	}
}
