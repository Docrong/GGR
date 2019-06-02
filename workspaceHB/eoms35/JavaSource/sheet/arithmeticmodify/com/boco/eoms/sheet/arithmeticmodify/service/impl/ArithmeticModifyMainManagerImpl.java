
package com.boco.eoms.sheet.arithmeticmodify.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain;
import com.boco.eoms.sheet.arithmeticmodify.service.IArithmeticModifyMainManager;

public class ArithmeticModifyMainManagerImpl extends MainService implements IArithmeticModifyMainManager {
	
	/**
	 * 通过工单的父流水号，获取工单mian对象
	 * @param mainYuLiuA 工单父流程sheetKey
	 * @return
	 * @throws SheetException
	 */
	public ArithmeticModifyMain getMainObjByMainYuLiuA(String mainYuLiuA) throws SheetException{
		ArithmeticModifyMain arithmeticmodifymain=null;
	    String hql = "from "+this.getMainObject().getClass().getName()+" main where main.mainYuLiuA='"+mainYuLiuA+"' order by main.sendTime asc";
	    int count[] = {0};
	    try{
	       List list = this.getMainDAO().getQuerySheetByCondition(hql,new Integer(0),new Integer(15),count, "");
	       if(list!=null && list.size()>=1){
	    	   arithmeticmodifymain = (ArithmeticModifyMain)list.get(0); 
	       }
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return arithmeticmodifymain;
	}
}
