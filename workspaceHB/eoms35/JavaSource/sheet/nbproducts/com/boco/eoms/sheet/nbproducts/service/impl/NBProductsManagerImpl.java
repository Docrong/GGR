
package com.boco.eoms.sheet.nbproducts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;
import com.boco.eoms.sheet.nbproducts.dao.INBProductsDao;
import com.boco.eoms.sheet.nbproducts.dao.INBProductsJdbc;
import com.boco.eoms.sheet.nbproducts.service.INBProductsManager;

public class NBProductsManagerImpl extends BaseManager implements INBProductsManager {
    private INBProductsDao nbproductsDao;
    private INBProductsJdbc nproductsJdbc;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setNBProductsDao(INBProductsDao dao) {
        this.dao = dao;
    }

  

    public INBProductsDao getNbproductsDao() {
		return nbproductsDao;
	}



	public void setNbproductsDao(INBProductsDao nbproductsDao) {
		this.nbproductsDao = nbproductsDao;
	}



	/**
     * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#saveNBProducts(NBProducts nbproducts)
     */
    public void saveNBProducts(NBProducts nbproducts) {
    	nbproductsDao.saveNBProducts(nbproducts);
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#removeNBProducts(String id)
     */
    public void removeNBProducts(final String id) {
    	nbproductsDao.removeNBProducts(new String(id));
    }
    
    /**
     * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#removeNBProducts(String id)
     */
    public void restoreNBProducts(final String id) {
    	nbproductsDao.restoreNBProducts(new String(id));
    }
    /**
     * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#getNBProductss(final Integer curPage, final Integer pageSize)
     */
    public Map getNBProductss(final Integer curPage, final Integer pageSize) {
        return nbproductsDao.getNBProductss(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#getNBProductss(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getNBProductss(final Integer curPage, final Integer pageSize, final String whereStr) {
        return nbproductsDao.getNBProductss(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return nbproductsDao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			NBProducts obj = (NBProducts) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			//jitem.put("text", obj.getName());
			//jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			//if(obj.getLeaf().equals("1")){
				//jitem.put("leaf", true);
			//}
			json.put(jitem);
		}
		return json;
	}	
	/**
	 * @see com.boco.eoms.sheet.nbproducts.service.INBProductsManager#getNBProductssByCondition(java.lang.String)
	 */
	public List getNBProductssByProCode(String proCode,String moduleName) {
         
		String hql = " from " + moduleName + " where mainProductCode = '"+"' and templateFlag <> 1";
		return nbproductsDao.getNBProductssByHql(hql);
	}
	
	public List getNBProductssByTxtwords(Integer newDeleted,String txtwords,String moduleName) {
		if(txtwords.trim().equalsIgnoreCase("")){
			String hql = " from " + moduleName + " where keyword ='"+"' and deleted = " + newDeleted +"" ;
			return nbproductsDao.getNBProductssByHql(hql);
		}else{
			String hql = " from " + moduleName + " where keyword like '%" + txtwords +"%'and deleted = " + newDeleted +"";
			return nbproductsDao.getNBProductssByHql(hql);
		}
		
	}



	public NBProducts getNBProducts(String id) {
		// TODO Auto-generated method stub
		return nbproductsDao.getNBProducts(id);
	}



	public List getNBProductss() {
		// TODO Auto-generated method stub
		return nbproductsDao.getNBProductss();
	}	
	
	public List getNBProductssDeleted() {
		// TODO Auto-generated method stub
		return nbproductsDao.getNBProductssDeleted();
	}	
//	select maxcode 
	public String getMaxCode(){
		return nproductsJdbc.getMaxCode();
	}



	public INBProductsJdbc getNproductsJdbc() {
		return nproductsJdbc;
	}



	public void setNproductsJdbc(INBProductsJdbc nproductsJdbc) {
		this.nproductsJdbc = nproductsJdbc;
	}
}
