
package com.boco.eoms.km.cptroom.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.cptroom.dao.KmsystemCptroomDao;
import com.boco.eoms.km.cptroom.service.IKmsystemCptroomManager;

public class KmsystemCptroomManagerImpl extends BaseManager implements IKmsystemCptroomManager {
    private KmsystemCptroomDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setKmsystemCptroomDao(KmsystemCptroomDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.IKmsystemCptroomManager#getKmsystemCptrooms(com.boco.eoms.commons.system.cptroom.model.KmsystemCptroom)
     */
    public List getKmsystemCptrooms(final KmsystemCptroom kmsystemCptroom) {
        return dao.getKmsystemCptrooms(kmsystemCptroom);
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.IKmsystemCptroomManager#getKmsystemCptroom(String id)
     */
    public KmsystemCptroom getKmsystemCptroom(final String id) {
        return dao.getKmsystemCptroom(new Integer(id));
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.IKmsystemCptroomManager#saveKmsystemCptroom(KmsystemCptroom kmsystemCptroom)
     */
    public void saveKmsystemCptroom(KmsystemCptroom kmsystemCptroom) {
        dao.saveKmsystemCptroom(kmsystemCptroom);
    }

    /**
     * @see com.boco.eoms.commons.system.cptroom.service.IKmsystemCptroomManager#removeKmsystemCptroom(String id)
     */
    public void removeKmsystemCptroom(final String id) {
        dao.removeKmsystemCptroom(new Integer(id));
    }
    /**
     * 
     */
    public Map getKmsystemCptrooms(final Integer curPage, final Integer pageSize) {
        return dao.getKmsystemCptrooms(curPage, pageSize,null);
    }
    public Map getKmsystemCptrooms(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getKmsystemCptrooms(curPage, pageSize, whereStr);
    }
    /**
     * @author 孙圣泰
     * @see 通过机房ID、删除标记取机房model
     * @param Integer id 机房ID
     * @param int deleted 删除标记
     * @return KmsystemCptroom 机房model
     */
	public KmsystemCptroom getKmsystemCptroomById(Integer id, int deleted) {
		return dao.getKmsystemCptroomById(id, deleted);
	}
	/**
     * @author 孙圣泰
     * @see 通过机房名称、删除标记取机房model
     * @param String String 机房名称
     * @param int deleted 删除标记
     * @return KmsystemCptroom 机房model
     */
	public KmsystemCptroom getKmsystemCptroomByRoomname(String roomname, int deleted) {
		return dao.getKmsystemCptroomByRoomname(roomname, deleted);
	}
	
	/**
     * @author 孙圣泰
     * @see 通过机房ID、机房名称和删除标记取机房model
     * @param Integer id 机房ID
     * @param String roomname 机房名称
     * @param int deleted 删除标记
     * @return KmsystemCptroom 机房model
     */
	public KmsystemCptroom getKmsystemCptroom(Integer id, String roomname, int deleted) {
		return dao.getKmsystemCptroom(id, roomname, deleted);
	}
	/**
	 * @author 孙圣泰
	 * @see 根据机房ID，检索机房信息！
	 * @see 该方法是单表检索，只对taw_system_cptroom表检索。
	 * @param id  Integer  机房ID
	 * @return String类型值。
	 */
	public String getKmsystemCptroomName(Integer id) {
		return dao.getKmsystemCptroomName(id);
	}
	/**
	   * @see 通过用户的部门编号、删除标志得到该部门的机房列表
	   * @author 孙圣泰
	   * @param  deptId  String  查询条件
	   * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
	   */
	public List getKmsystemCptroomList(String deptid, int deleted) {
		return dao.getKmsystemCptroomList(deptid,deleted);
	}
	/**
	 * @see 根据model删除
	 * @param kmsystemCptroom
	 */
	public void removeKmsystemCptroom(KmsystemCptroom kmsystemCptroom) {
		dao.removeKmsystemCptroom(kmsystemCptroom);
	}
	/**
     * @author 孙圣泰
     * @see 根据机房ID取机房地址
     * @param Integer id 机房ID
     * @return String 机房地址
     */
	public String getKmsystemCptroomAddress(Integer id) {
		return dao.getKmsystemCptroomAddress(id);
	}
	/**
     * @author 孙圣泰
	 * @see 得到全部的机房列表
	 * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
	 */
	public List getKmsystemCptroomList() {
		return dao.getKmsystemCptroomList();
	}
	/**
     * @author 孙圣泰
     * @see 通过用户的部门编号得到该部门的机房列表
     * @param deptid 部门ID
     * @return List 机房model的list
     */
	public List getKmsystemCptroomListByDeptid(String deptid) {
		return dao.getKmsystemCptroomListByDeptid(deptid);
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
