
package com.boco.eoms.km.cptroom.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.cptroom.dao.KmsystemCptroomDao;

public interface IKmsystemCptroomManager extends Manager {
    /**
     * Retrieves all of the kmsystemCptrooms
     */
    public List getKmsystemCptrooms(KmsystemCptroom kmsystemCptroom);

    /**
     * Gets kmsystemCptroom's information based on id.
     *
     * @param id the kmsystemCptroom's id
     * @return kmsystemCptroom populated kmsystemCptroom object
     */
    public KmsystemCptroom getKmsystemCptroom(final String id);

    /**
     * Saves a kmsystemCptroom's information
     *
     * @param kmsystemCptroom the object to be saved
     */
    public void saveKmsystemCptroom(KmsystemCptroom kmsystemCptroom);

    /**
     * Removes a kmsystemCptroom from the database by id
     *
     * @param id the kmsystemCptroom's id
     */
    public void removeKmsystemCptroom(final String id);

    /**
     * Deletes a a kmsystemCptroom's information
     *
     * @param kmsystemCptroom
     */
    public void removeKmsystemCptroom(KmsystemCptroom kmsystemCptroom);

    public Map getKmsystemCptrooms(final Integer curPage, final Integer pageSize);

    public Map getKmsystemCptrooms(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * @param Integer id 机房ID
     * @param int     deleted 删除标记
     * @return KmsystemCptroom 机房model
     * @author 孙圣泰
     * @see 通过机房ID、删除标记取机房model
     */
    public KmsystemCptroom getKmsystemCptroomById(Integer id, int deleted);

    /**
     * @param String String 机房名称
     * @param int    deleted 删除标记
     * @return KmsystemCptroom 机房model
     * @author 孙圣泰
     * @see 通过机房名称、删除标记取机房model
     */
    public KmsystemCptroom getKmsystemCptroomByRoomname(String name, int deleted);

    /**
     * @param Integer id 机房ID
     * @param String  roomname 机房名称
     * @param int     deleted 删除标记
     * @return KmsystemCptroom 机房model
     * @author 孙圣泰
     * @see 通过机房ID、机房名称和删除标记取机房model
     */
    public KmsystemCptroom getKmsystemCptroom(Integer id, String name, int deleted);

    /**
     * @param id
     * @return String 机房名称
     * @see根据机房ID查找机房名称
     * @author 孙圣泰
     */
    public String getKmsystemCptroomName(Integer id);

    /**
     * @param deptId String  查询条件
     * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
     * @author 孙圣泰
     * @see 通过用户的部门编号、删除标志得到该部门的机房列表
     */
    public List getKmsystemCptroomList(String deptid, int deleted);

    /**
     * @param Integer id 机房ID
     * @return String 机房地址
     * @author 孙圣泰
     * @see 根据机房ID取机房地址
     */
    public String getKmsystemCptroomAddress(Integer id);

    /**
     * @param deptid
     * @return List 机房model的list
     * @see 通过用户的部门编号得到该部门的机房列表
     */
    public List getKmsystemCptroomListByDeptid(String deptid);

    /**
     * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
     * @author 孙圣泰
     * @see 得到全部的机房列表
     */
    public List getKmsystemCptroomList();

    /**
     * @param parentid
     * @param delid
     * @return List
     * @author 孙圣泰
     * @see 根据父节点和删除标志得到下一级子机房的部门信息
     */
    public List getNextLevelCptrooms(String parentid, String delid);
}

