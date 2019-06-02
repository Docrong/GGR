package com.boco.eoms.sheet.tool.limit.dao;
import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:29:53
 * </p>
 * 
 * @author 张影
 * @version 1.0
 * 
 */
public interface ISheetLimitDAO extends Dao{


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
   public SheetLimit getSheetLimitBySpecial(String  special1,String  special2,String  special3,String  special4);
   public SheetLimit getSheetLimitBySpecial(String  special);
   public List getDealLimitList(final SheetLimit sheetLimit);
   public SheetLimit getSheetLimitByLastSpecial(String  special4,String  special2);
}
