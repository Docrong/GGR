package com.boco.eoms.sparepart.util;

import java.util.Date;

import org.apache.struts.action.*;

import com.boco.eoms.common.util.StaticMethod;

import javax.servlet.http.*;

/**
 * <p>Title: 备品备件</p>
 * <p>Description: EOMS子系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public class StaticPartMethod{
	public static String ordernumber="";
	public static String stateIN="(11,12,19)";
	public static String stateOUT="(13,14,16,17,18,20,21)";
	public static String stateNO="(15)";//报废数
    public StaticPartMethod(){
    }

    public static void setReturnPath(ActionMapping mapping,
                                     HttpServletRequest request){
        String path=request.getContextPath();

        path=path + "/sparepart" + mapping.getPath() +".do";
        request.getSession().removeAttribute("path");
        request.getSession().setAttribute("path",path);
    }
    
    public static String getOrdernumber(String type){
    	Date date=new Date();
    	
    	ordernumber=""+StaticMethod.getCurrentDateTime("yyyy-MM-dd")+"-"+type+"-"
    	              +(int)(Math.random()*10)+(int)(Math.random()*10)
    	              +(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10);
    	return ordernumber;
    }

}