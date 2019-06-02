
package com.boco.eoms.taskplan.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.taskplan.dao.ITaskplanDao;
import com.boco.eoms.taskplan.model.Taskplan;
import com.boco.eoms.taskplan.service.ITaskplanManager;

public class TaskplanManagerImpl extends BaseManager implements ITaskplanManager {
    private ITaskplanDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTaskplanDao(ITaskplanDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#getTaskplans(com.boco.eoms.taskplan.model.Taskplan)
     */
    public List getTaskplans(final Taskplan taskplan) {
        return dao.getTaskplans(taskplan);
    }

    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#getTaskplan(String id)
     */
    public Taskplan getTaskplan(final String id) {
        return dao.getTaskplan(new String(id));
    }

    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#saveTaskplan(Taskplan taskplan)
     */
    public void saveTaskplan(Taskplan taskplan) {
        dao.saveTaskplan(taskplan);
    }

    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#removeTaskplan(String id)
     */
    public void removeTaskplan(final String id) {
        dao.removeTaskplan(new String(id));
    }
    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#getTaskplans(final Integer curPage, final Integer pageSize)
     */
    public Map getTaskplans(final Integer curPage, final Integer pageSize) {
        return dao.getTaskplans(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#getTaskplans(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTaskplans(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTaskplans(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.taskplan.service.ITaskplanManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		/*for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			Taskplan obj = (Taskplan) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getName());
			jitem.put("name", obj.getName());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
			if(obj.getLeaf().equals("1")){
				jitem.put("leaf", true);
			}
			json.put(jitem);
		}*/
		return json;
	}	
}
