
package com.boco.eoms.commons.system.cptroom.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;

public class TawSystemCptroomManagerImpl extends BaseManager implements ITawSystemCptroomManager {
    private TawSystemCptroomDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemCptroomDao(TawSystemCptroomDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager#getTawSystemCptrooms(com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom)
     */
    public List getTawSystemCptrooms(final TawSystemCptroom tawSystemCptroom) {
        return dao.getTawSystemCptrooms(tawSystemCptroom);
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager#getTawSystemCptroom(String id)
     */
    public TawSystemCptroom getTawSystemCptroom(final String id) {
        return dao.getTawSystemCptroom(new Integer(id));
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager#saveTawSystemCptroom(TawSystemCptroom tawSystemCptroom)
     */
    public void saveTawSystemCptroom(TawSystemCptroom tawSystemCptroom) {
        dao.saveTawSystemCptroom(tawSystemCptroom);
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager#removeTawSystemCptroom(String id)
     */
    public void removeTawSystemCptroom(final String id) {
        dao.removeTawSystemCptroom(new Integer(id));
    }
    /**
     * 
     */
    public Map getTawSystemCptrooms(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemCptrooms(curPage, pageSize,null);
    }
    public Map getTawSystemCptrooms(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemCptrooms(curPage, pageSize, whereStr);
    }
    /**
     * @author 孙圣泰
     * @see 通过机房ID、删除标记取机房model
     * @param Integer id 机房ID
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
	public TawSystemCptroom getTawSystemCptroomById(Integer id, int deleted) {
		return dao.getTawSystemCptroomById(id, deleted);
	}
	/**
     * @author 孙圣泰
     * @see 通过机房名称、删除标记取机房model
     * @param String String 机房名称
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
	public TawSystemCptroom getTawSystemCptroomByRoomname(String roomname, int deleted) {
		return dao.getTawSystemCptroomByRoomname(roomname, deleted);
	}
	
	/**
     * @author 孙圣泰
     * @see 通过机房ID、机房名称和删除标记取机房model
     * @param Integer id 机房ID
     * @param String roomname 机房名称
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
	public TawSystemCptroom getTawSystemCptroom(Integer id, String roomname, int deleted) {
		return dao.getTawSystemCptroom(id, roomname, deleted);
	}
	/**
	 * @author 孙圣泰
	 * @see 根据机房ID，检索机房信息！
	 * @see 该方法是单表检索，只对taw_system_cptroom表检索。
	 * @param id  Integer  机房ID
	 * @return String类型值。
	 */
	public String getTawSystemCptroomName(Integer id) {
		return dao.getTawSystemCptroomName(id);
	}
	/**
	   * @see 通过用户的部门编号、删除标志得到该部门的机房列表
	   * @author 孙圣泰
	   * @param  deptId  String  查询条件
	   * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	   */
	public List getTawSystemCptroomList(String deptid, int deleted) {
		return dao.getTawSystemCptroomList(deptid,deleted);
	}
	/**
	 * @see 根据model删除
	 * @param tawSystemCptroom
	 */
	public void removeTawSystemCptroom(TawSystemCptroom tawSystemCptroom) {
		dao.removeTawSystemCptroom(tawSystemCptroom);
	}
	/**
     * @author 孙圣泰
     * @see 根据机房ID取机房地址
     * @param Integer id 机房ID
     * @return String 机房地址
     */
	public String getTawSystemCptroomAddress(Integer id) {
		return dao.getTawSystemCptroomAddress(id);
	}
	/**
     * @author 孙圣泰
	 * @see 得到全部的机房列表
	 * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	 */
	public List getTawSystemCptroomList() {
		return dao.getTawSystemCptroomList();
	}
	/**
     * @author 孙圣泰
     * @see 通过用户的部门编号得到该部门的机房列表
     * @param deptid 部门ID
     * @return List 机房model的list
     */
	public List getTawSystemCptroomListByDeptid(String deptid) {
		return dao.getTawSystemCptroomListByDeptid(deptid);
	}
	/**
	 * @author 孙圣泰
	 * @see 根据父节点和删除标志得到下一级子机房的部门信息
	 * @param parentid
	 * @param delid
	 * @return List 
	 */
	public List getNextLevelCptrooms(String parentid, String delid) {
		return dao.getNextLevelCptrooms(parentid, delid);
	}
}
