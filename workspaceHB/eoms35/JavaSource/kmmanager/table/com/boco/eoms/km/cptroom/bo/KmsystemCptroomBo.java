package com.boco.eoms.km.cptroom.bo;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.cptroom.service.IKmsystemCptroomManager;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 权限分配的内部业务逻辑
 *
 * @author panlong May 31, 2007 10:15:13 AM
 */
public class KmsystemCptroomBo {

    private KmsystemCptroomBo() {

    }

    private static KmsystemCptroomBo instance = null;

    public static KmsystemCptroomBo getInstance() {
        if (instance == null) {
            instance = init();
        }
        return instance;
    }

    private static KmsystemCptroomBo init() {
        instance = new KmsystemCptroomBo();
        return instance;
    }

    /**
     * @param kmsystemCptroom
     * @see 根据model删除
     */
    public void removeKmsystemCptroom(KmsystemCptroom kmsystemCptroom) {
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        cptroommanager.removeKmsystemCptroom(kmsystemCptroom);
    }

    /**
     * @param Integer id 机房ID
     * @param String  roomname 机房名称
     * @param int     deleted 删除标记
     * @return KmsystemCptroom 机房model
     * @author 孙圣泰
     * @see 通过机房ID、机房名称和删除标记取机房model
     */
    public KmsystemCptroom getKmsystemCptroom(Integer id, String name, int deleted) {
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        KmsystemCptroom cptroom = new KmsystemCptroom();
        cptroom = cptroommanager.getKmsystemCptroom(id, name, deleted);
        return cptroom;
    }

    /**
     * @param Integer id 机房ID
     * @param int     deleted 删除标记
     * @return KmsystemCptroom 机房model
     * @author 孙圣泰
     * @see 通过机房ID、删除标记取机房model
     */
    public KmsystemCptroom getKmsystemCptroomById(Integer id, int deleted) {
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        KmsystemCptroom cptroom = new KmsystemCptroom();
        cptroom = cptroommanager.getKmsystemCptroomById(id, deleted);
        return cptroom;
    }

    /**
     * @param String String 机房名称
     * @param int    deleted 删除标记
     * @return KmsystemCptroom 机房model
     * @author 孙圣泰
     * @see 通过机房名称、删除标记取机房model
     */
    public KmsystemCptroom getKmsystemCptroomByRoomname(String name, int deleted) {
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        KmsystemCptroom cptroom = new KmsystemCptroom();
        cptroom = cptroommanager.getKmsystemCptroomByRoomname(name, deleted);
        return cptroom;
    }

    /**
     * @param id Integer  机房ID
     * @return String类型值。
     * @see 根据机房ID，检索机房信息！
     * @see 该方法是单表检索，只对taw_system_cptroom表检索。
     */
    public String getKmsystemCptroomName(Integer id) {
        String cptroom = "";
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        cptroom = cptroommanager.getKmsystemCptroomName(id);
        return cptroom;
    }

    /**
     * @param deptId String  查询条件
     * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
     * @author 孙圣泰
     * @see 通过用户的部门编号、删除标志得到该部门的机房列表
     */
    public List getKmsystemCptroomList(String deptid, int deleted) {
        ArrayList list = new ArrayList();
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        list = (ArrayList) cptroommanager.getKmsystemCptroomList(deptid, deleted);
        return list;
    }

    /**
     * @param Integer id 机房ID
     * @return String 机房地址
     * @author 孙圣泰
     * @see 根据机房ID取机房地址
     */
    public String getKmsystemCptroomAddress(Integer id) {
        String address = "";
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        address = cptroommanager.getKmsystemCptroomAddress(id);
        return address;
    }

    /**
     * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
     * @author 孙圣泰
     * @see 得到全部的机房列表
     */
    public List getKmsystemCptroomList() {
        ArrayList list = new ArrayList();
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        list = (ArrayList) cptroommanager.getKmsystemCptroomList();
        return list;
    }

    /**
     * @param deptid 部门ID
     * @return List 机房model的list
     * @author 孙圣泰
     * @see 通过用户的部门编号得到该部门的机房列表
     */
    public List getKmsystemCptroomListByDeptid(String deptid) {
        ArrayList list = new ArrayList();
        IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("IkmsystemCptroomManager");
        list = (ArrayList) cptroommanager.getKmsystemCptroomListByDeptid(deptid);
        return list;
    }

    public List getNextLevelCptrooms(String parentid, String delid) {
        List list = null;
        try {
            IKmsystemCptroomManager cptroommanager = (IKmsystemCptroomManager) ApplicationContextHolder
                    .getInstance().getBean("IkmsystemCptroomManager");
            list = new ArrayList();
            list = cptroommanager.getNextLevelCptrooms(parentid, delid);
        } catch (Exception ex) {
            BocoLog.error(KmsystemCptroomBo.class, " 查询机房 " + parentid
                    + " 的所有子机房信息出错 " + ex.getMessage());
        }
        return list;
    }
}
