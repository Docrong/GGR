package com.boco.eoms.sheet.tool.limit.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;

import com.boco.eoms.sheet.tool.limit.dao.ISheetLimitDAO;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;

public class SheetLimitManagerImpl extends BaseManager implements ISheetLimitManager {

	private ISheetLimitDAO dao;
	 /**
    * Saves a tawSheetExpert's information
    * @param tawSheetExpert the object to be saved
    */    
   public void saveSheetLimit(SheetLimit sheetLimit){
   	dao.saveSheetLimit(sheetLimit);
   }
   /**
    * Saves a tawSheetExpert's information
    * @param tawSheetExpert the object to be saved
    */  
   public void removeSheetLimit(final Integer id){
   	dao.removeSheetLimit(id);
   }
   
   public SheetLimit getSheetLimit(final Integer id){
   	return dao.getSheetLimit(id);
   }
	
	public List getSheetLimitList(final SheetLimit sheetLimit){
	
		return dao.getSheetLimitList(sheetLimit);
	}
	public ISheetLimitDAO getDao() {
		return dao;
	}
	public void setDao(ISheetLimitDAO dao) {
		this.dao = dao;
	}
	public SheetLimit getSheetLimitBySpecial(String  special1,String  special2){
		 return dao.getSheetLimitBySpecial(special1,special2);
	 }
	public SheetLimit getSheetLimitBySpecial(String  special1,String  special2,String  special3,String  special4){
		 return dao.getSheetLimitBySpecial(special1,special2,special3,special4);
	 }
   public SheetLimit getSheetLimitBySpecial(String  special){
		 return dao.getSheetLimitBySpecial(special);
	 }
  
   public List getDealLimitList(final SheetLimit sheetLimit){
		
		return dao.getDealLimitList(sheetLimit);
	}
   public SheetLimit getSheetLimitByLastSpecial(String  special4,String  special2){
	   return dao.getSheetLimitByLastSpecial(special4,special2);
   }
}
