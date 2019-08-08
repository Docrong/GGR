package com.boco.eoms.commons.system.session.bo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dept.exception.TawSystemException;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.bo.TawSystemUserAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.duty.dao.TawRmAssignSubDAO;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.dao.TawRmSysteminfoDAO;
import com.boco.eoms.duty.model.TawRmSysteminfo;

public class TawSystemSessionBo {
    private static com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
            .getInstance();

    /**
     * 根据userid得到用户的信息
     *
     * @param userid
     * @return
     */
    public static TawSystemUser getUserByuserid(String userid) {
        TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
        TawSystemUser systemuser = new TawSystemUser();
        systemuser = rolebo.getUserByuserid(userid);
        return systemuser;
    }

    /**
     * 得到某用户的所有角色
     *
     * @param userid
     * @return
     * @throws TawSystemUserException
     */
    public static List getRoleidByuserid(String userid)
            throws TawSystemUserException {
        TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
        List list = new ArrayList();
        list = rolebo.getRoleidByuserid(userid);
        return list;
    }

    /**
     * 得到用户所有的存在班次的机房
     *
     * @param userid
     * @return
     * @throws TawSystemUserException
     */
    public static List getRoomInfo(String userid) {
        Vector vector = new Vector();
        Vector SelectRoom = new Vector();
        Vector SelectRoomName = new Vector();
        Vector SelectRoomId = new Vector();
        TawSystemPrivRegion tawSystemPrivRegion = null;
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;
        TawRmAssignSubDAO tawRmAssignSubDAO = null;
        List list = new ArrayList();
        try {
            tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
            // 得到所有机房域的集合
            SelectRoom = StaticMethod
                    .list2vector(privBO
                            .getPermissions(
                                    userid,
                                    com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
                                    com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));


            Vector removeEle = new Vector();
            for (int i = 0; i < SelectRoom.size(); i++) {
                tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
                        .elementAt(i);
                String roomID = tawSystemPrivRegion.getRegionid();
                TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);

                TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
                TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO
                        .retrieve(roomID);
                int maxerrortime = 0;
                if (tawRmSysteminfo != null) {
                    maxerrortime = tawRmSysteminfo.getMaxerrortime();
                }

//				 当前时间+最大误差时间，是否属于下班次
                GregorianCalendar cal_start = new GregorianCalendar();
                cal_start.add(cal_start.MINUTE, maxerrortime);
                String time_start = String.valueOf(cal_start.get(cal_start.YEAR));
                time_start = time_start + "-"
                        + String.valueOf(cal_start.get(cal_start.MONTH) + 1);
                time_start = time_start + "-"
                        + String.valueOf(cal_start.get(cal_start.DATE));
                time_start = time_start + " "
                        + String.valueOf(cal_start.get(cal_start.HOUR_OF_DAY));
                time_start = time_start + ":"
                        + String.valueOf(cal_start.get(cal_start.MINUTE));
                time_start = time_start + ":"
                        + String.valueOf(cal_start.get(cal_start.SECOND));
                int workserial2 = tawRmRecordDAO.receiverWorkSerial(roomID,
                        StaticMethod.getLocalString(time_start), userid);

//				 当前时间-最大误差时间，是否属于上班次
                GregorianCalendar cal_end = new GregorianCalendar();
                maxerrortime = -maxerrortime;
                cal_end.add(cal_end.MINUTE, maxerrortime);
                String time_end = String.valueOf(cal_end.get(cal_end.YEAR));
                time_end = time_end + "-"
                        + String.valueOf(cal_end.get(cal_end.MONTH) + 1);
                time_end = time_end + "-"
                        + String.valueOf(cal_end.get(cal_end.DATE));
                time_end = time_end + " "
                        + String.valueOf(cal_end.get(cal_end.HOUR_OF_DAY));
                time_end = time_end + ":"
                        + String.valueOf(cal_end.get(cal_end.MINUTE));
                time_end = time_end + ":"
                        + String.valueOf(cal_end.get(cal_end.SECOND));
                int workserial1 = tawRmRecordDAO.receiverWorkSerial(roomID,
                        StaticMethod.getLocalString(time_end), userid);
                if (workserial2 > 0 || workserial1 > 0) {
                    if (tawRmAssignSubDAO.getUserIdEqualsWorkSerial(workserial2 + "", userid) || tawRmAssignSubDAO.getUserIdEqualsWorkSerial(workserial1 + "", userid)) {
                        // 如果是得到机房对象
                        tawApparatusroom = cptroomBO.getTawSystemCptroomById(
                                new Integer(tawSystemPrivRegion.getRegionid()), 0);
                        list.add(tawApparatusroom);
                    }
                }
            }
            SelectRoom.removeAll(removeEle);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static TawSystemSessionForm getSessionForm(String userid) {
        TawSystemUser systemuser = new TawSystemUser();
        TawSystemSessionForm form = new TawSystemSessionForm();
        TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        systemuser = rolebo.getUserByuserid(userid);
        ArrayList rolelist = (ArrayList) rolebo.getRoleByuserid(userid,
                RoleConstants.ROLETYPE_SUBROLE);
        Vector vectorRoom = new Vector();
        // List rolelist = TawSystemUserRoleBo.getRoleidByuserid();
        try {

            TawSystemDept sysdept = TawSystemDeptBo.getInstance()
                    .getDeptinfobydeptid(systemuser.getDeptid(), "0");
            form.setUserid(userid);
            // 为portal使用
            // 用户表id（主键），为portal登陆使用，由于portal使用主键与业务有关，故记入用户表id主键做为登陆条件
            form.setId(systemuser.getId());
            form.setUsername(systemuser.getUsername());
            form.setDeptid(systemuser.getDeptid());
            // 机房
            String roomid = systemuser.getCptroomid();
            form.setRoomId(roomid);
            // FIXME 机房id需修改为动态获取

            form.setRoomname(systemuser.getCptroomname());

            form.setDeptpriid(sysdept.getId());
            form
                    .setDeptname(deptbo.getDeptnameBydeptid(systemuser
                            .getDeptid()));
            form.setPassword(systemuser.getPassword());
            form.setRolelist(rolelist);
            form.setContactMobile(systemuser.getMobile());
            String userdegree = "";
            userdegree = systemuser.getUserdegree();
//			form.setHavePriv(TawSystemUserAssignBo.getInstance()
//					.isExitsUserassign(userid));
            if (userdegree != null) {
                if (userdegree.equals("1")) {
                    form.setIsadmin(true);
                } else {
                    form.setIsadmin(false);
                }
            }
        } catch (TawSystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return form;
    }

}
