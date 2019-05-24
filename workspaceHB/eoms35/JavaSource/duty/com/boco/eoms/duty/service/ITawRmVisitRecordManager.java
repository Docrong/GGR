
package com.boco.eoms.duty.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmVisitRecord;

public interface ITawRmVisitRecordManager extends Manager {
     
    public List getTawRmVisitRecords(TawRmVisitRecord tawRmVisitRecord);

    
    public TawRmVisitRecord getTawRmVisitRecord(final String id);

   
    public void saveTawRmVisitRecord(TawRmVisitRecord tawRmVisitRecord);

     
    public void removeTawRmVisitRecord(final String id);
      
    public Map getTawRmVisitRecords(final Integer curPage, final Integer pageSize);
     
    public Map getTawRmVisitRecords(final Integer curPage, final Integer pageSize, final String whereStr);
         
    public List getChildList(String parentId);
    
    public JSONArray xGetChildNodes(String parentId);
}

