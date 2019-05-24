
package com.boco.eoms.commons.system.cptroom.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao;

public interface ITawSystemCptroomManager extends Manager {
    /**
     * Retrieves all of the tawSystemCptrooms
     */
    public List getTawSystemCptrooms(TawSystemCptroom tawSystemCptroom);

    /**
     * Gets tawSystemCptroom's information based on id.
     * @param id the tawSystemCptroom's id
     * @return tawSystemCptroom populated tawSystemCptroom object
     */
    public TawSystemCptroom getTawSystemCptroom(final String id);

    /**
     * Saves a tawSystemCptroom's information
     * @param tawSystemCptroom the object to be saved
     */
    public void saveTawSystemCptroom(TawSystemCptroom tawSystemCptroom);

    /**
     * Removes a tawSystemCptroom from the database by id
     * @param id the tawSystemCptroom's id
     */
    public void removeTawSystemCptroom(final String id);
    /**
     * Deletes a a tawSystemCptroom's information
     * @param tawSystemCptroom
     */
    public void removeTawSystemCptroom(TawSystemCptroom tawSystemCptroom);
    public Map getTawSystemCptrooms(final Integer curPage, final Integer pageSize);
    public Map getTawSystemCptrooms(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * @author 孙圣泰
     * @see 通过机房ID、删除标记取机房model
     * @param Integer id 机房ID
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
    public TawSystemCptroom getTawSystemCptroomById(Integer id, int deleted);
    /**
     * @author 孙圣泰
     * @see 通过机房名称、删除标记取机房model
     * @param String String 机房名称
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
    public TawSystemCptroom getTawSystemCptroomByRoomname(String name, int deleted);
    /**
     * @author 孙圣泰
     * @see 通过机房ID、机房名称和删除标记取机房model
     * @param Integer id 机房ID
     * @param String roomname 机房名称
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
    public TawSystemCptroom getTawSystemCptroom(Integer id, String name, int deleted);
    /**
     * @see根据机房ID查找机房名称
     * @param id
     * @return String 机房名称
     * @author 孙圣泰
     */
    public String getTawSystemCptroomName(Integer id);
    /**
	   * @see 通过用户的部门编号、删除标志得到该部门的机房列表
	   * @author 孙圣泰
	   * @param  deptId  String  查询条件
	   * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	   */
    public List getTawSystemCptroomList(String deptid,int deleted);
    /**
     * @author 孙圣泰
     * @see 根据机房ID取机房地址
     * @param Integer id 机房ID
     * @return String 机房地址
     */
    public String getTawSystemCptroomAddress(Integer id);
    /**
     * @see 通过用户的部门编号得到该部门的机房列表
     * @param deptid
     * @return List 机房model的list
     */
    public List getTawSystemCptroomListByDeptid(String deptid);
    /**
     * @author 孙圣泰
	 * @see 得到全部的机房列表
	 * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	 */
	public List getTawSystemCptroomList();
	/**
	 * @author 孙圣泰
	 * @see 根据父节点和删除标志得到下一级子机房的部门信息
	 * @param parentid
	 * @param delid
	 * @return List 
	 */
	public List getNextLevelCptrooms(String parentid, String delid);
}

