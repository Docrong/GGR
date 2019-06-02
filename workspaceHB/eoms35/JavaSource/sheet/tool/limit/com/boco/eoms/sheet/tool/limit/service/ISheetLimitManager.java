package com.boco.eoms.sheet.tool.limit.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;

public interface ISheetLimitManager extends Manager {
	 /**
	    * Saves a tawSheetExpert's information
	    * @param tawSheetExpert the object to be saved
	    */    
	   public void saveSheetLimit(SheetLimit sheetLimit);

	   /**
	    * Saves a tawSheetExpert's information
	    * @param tawSheetExpert the object to be saved
	    */  
	   public void removeSheetLimit(final Integer id); 
	   /**
	    * Gets Proxy's information based on primary key. An
	    * ObjectRetrievalFailureException Runtime Exception is thrown if 
	    * nothing is found.
	    * 
	    * @param id the Proxy's id
	    * @return Proxy populated Proxy object
	    */
	   public SheetLimit getSheetLimit(final Integer id);
	   public List getSheetLimitList(final SheetLimit sheetLimit);
	   public SheetLimit getSheetLimitBySpecial(String  special1,String  special2);
	   /**
	    * 
	    * @param special1 网络分类(一级)
	    * @param special2 故障处理响应级别
	    * @param special3 网络分类(二级)
	    * @param special4 网络分类(三级)
	    * @return
	    */
	   public SheetLimit getSheetLimitBySpecial(String  special1,String  special2,String  special3,String  special4);
	   public SheetLimit getSheetLimitBySpecial(String  special1);
	   public List getDealLimitList(final SheetLimit sheetLimit);
	   /**
		 * 根据网络三级分类和故障处理响应级别查询时限
		 * special4 网络三级分类
		 */
	   public SheetLimit getSheetLimitByLastSpecial(String  special4,String  special2);
}
