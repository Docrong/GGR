
package com.boco.eoms.otherwise.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.dao.ITawRmTestcardDao;
import com.boco.eoms.otherwise.service.ITawRmTestcardManager;

public class TawRmTestcardManagerImpl extends BaseManager implements ITawRmTestcardManager {
    private ITawRmTestcardDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawRmTestcardDao(ITawRmTestcardDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#getTawRmTestcards(com.boco.eoms.otherwise.model.TawRmTestcard)
     */
    public List getTawRmTestcards(final TawRmTestcard tawRmTestcard) {
        return dao.getTawRmTestcards(tawRmTestcard);
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#getTawRmTestcard(String id)
     */
    public TawRmTestcard getTawRmTestcard(final String id) {
        return dao.getTawRmTestcard(new String(id));
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#saveTawRmTestcard(TawRmTestcard tawRmTestcard)
     */
    public void saveTawRmTestcard(TawRmTestcard tawRmTestcard) {
        dao.saveTawRmTestcard(tawRmTestcard);
    }

    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#removeTawRmTestcard(String id)
     */
    public void removeTawRmTestcard(final String id) {
        dao.removeTawRmTestcard(new String(id));
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#getTawRmTestcards(final Integer curPage, final Integer pageSize)
     */
    public Map getTawRmTestcards(final Integer curPage, final Integer pageSize) {
        return dao.getTawRmTestcards(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#getTawRmTestcards(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawRmTestcards(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawRmTestcards(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.otherwise.service.ITawRmTestcardManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawRmTestcard obj = (TawRmTestcard) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			//jitem.put("text", obj.getName());
			//jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			//if(obj.getLeaf().equals("1")){
			//	jitem.put("leaf", true);
			//}
			json.put(jitem);
		}
		return json;
	}	
}
