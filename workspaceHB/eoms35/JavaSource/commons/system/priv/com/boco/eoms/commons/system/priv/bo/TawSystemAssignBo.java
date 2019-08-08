package com.boco.eoms.commons.system.priv.bo;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivRegionManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 权限分配的内部业务逻辑 =
 *
 * @author panlong May 31, 2007 10:15:13 AM
 */
public class TawSystemAssignBo {

    private TawSystemAssignBo() {

    }

    private static TawSystemAssignBo instance = null;

    public static TawSystemAssignBo getInstance() {
        if (instance == null) {
            instance = init();
        }
        return instance;
    }

    private static TawSystemAssignBo init() {
        instance = new TawSystemAssignBo();
        return instance;
    }

    /**
     * 查询权限分配所有信息
     */
    public List getTawSystemPrivAssigns(TawSystemPrivAssign tawSystemPrivAssign) {
        ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivAssignManager");
        List list = new ArrayList();
        list = assignmanager.getTawSystemPrivAssigns(tawSystemPrivAssign);
        return list;
    }

    /**
     * 根据主键ID查询权限分配记录
     */
    public TawSystemPrivAssign getTawSystemPrivAssign(String id) {
        ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivAssignManager");
        TawSystemPrivAssign assign = new TawSystemPrivAssign();
        assign = assignmanager.getTawSystemPrivAssign(id);
        return assign;
    }

    /**
     * 查询某用户分配的权限信息
     *
     * @param operuserid
     * @return
     */
    public List getUserCreatePrivs(String operuserid) {
        ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivAssignManager");
        List list = new ArrayList();
        list = assignmanager.getUserCreatePrivs(operuserid);
        return list;
    }

    /**
     * 查询OBJECT 对应的权限
     *
     * @param objectid
     * @return
     */
    public List getObjectPriv(String objectid) {
        ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivAssignManager");

        return (ArrayList) assignmanager.getObjectPriv(objectid);
    }

    /**
     * 查询某权限方案被分配的情况
     *
     * @return
     */
    public List getPrivassigninfos(String privid) {
        ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivAssignManager");
        List list = new ArrayList();
        list = assignmanager.getPrivassigninfos(privid);
        return list;
    }

    /**
     * 判断某GROUP是否已经被分配权限 TODO 目前此功能有性能问题
     *
     * @param userid
     * @return
     */
    public boolean hasAssign(String objectid, String type) {
        ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivAssignManager");

        if (assignmanager.hasAssign(objectid)) {
            return true;
        }
        if (type.equals(StaticVariable.PRIV_ASSIGNTYPE_USER)) {
            TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
            List listrole = rolebo.getUserRefRoleByuserid(objectid);
            if (listrole != null && listrole.size() > 0) {
                for (int j = 0; j < listrole.size(); j++) {
                    TawSystemUserRefRole userrefrole = (TawSystemUserRefRole) listrole
                            .get(j);
                    if (assignmanager.hasAssign(userrefrole.getSubRoleid()))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 查询角色或者用户的权限以JSON的方式返回
     *
     * @param type
     * @param objectid
     * @return
     */
    public JSONObject getJSONObjectPriv(String type, String objectid) {
        JSONObject jsonroot = new JSONObject();
        TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
        TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut.getInstance();
        List list = null;
        List listrole = null;
        JSONArray asg = new JSONArray();
        JSONArray extendAsg = new JSONArray();
        list = getObjectPriv(objectid);
        if (list != null && list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                JSONObject jitem = new JSONObject();
                TawSystemPrivAssign assign = (TawSystemPrivAssign) list.get(i);
                jitem.put("value", assign.getId());
                jitem.put("text", assignout.getPrivNameByPrivid(assign
                        .getPrivid()));
                asg.put(jitem);
            }

        }
        if (type.equals("user")) {
            listrole = rolebo.getUserRefRoleByuserid(objectid);
            if (listrole != null && listrole.size() > 0) {
                ITawSystemRoleManager rolemgr = (ITawSystemRoleManager) ApplicationContextHolder
                        .getInstance().getBean("ItawSystemRoleManager");
                ITawSystemSubRoleManager subrolemgr = (ITawSystemSubRoleManager) ApplicationContextHolder
                        .getInstance().getBean("ItawSystemSubRoleManager");
                for (int j = 0; j < listrole.size(); j++) {
                    TawSystemUserRefRole userrefrole = (TawSystemUserRefRole) listrole
                            .get(j);

                    String roleid = userrefrole.getSubRoleid();
                    String roletype = userrefrole.getRoleType();
                    String roleName = "";
                    if (roletype.equals(StaticVariable.ROLETYPE)) {
                        roleName = rolemgr.getTawSystemRole(
                                Long.parseLong(roleid)).getRoleName();
                    } else if (roletype.equals(StaticVariable.SUBROLETYPE)) {
                        TawSystemSubRole subrole = subrolemgr
                                .getTawSystemSubRole(roleid);
                        if (subrole != null) {
                            roleName = subrole.getSubRoleName();
                        }
                    }

                    List extendassignlist = getObjectPriv(roleid);
                    if (extendassignlist != null && extendassignlist.size() > 0) {
                        for (int n = 0; n < extendassignlist.size(); n++) {
                            TawSystemPrivAssign extendassign = (TawSystemPrivAssign) extendassignlist
                                    .get(n);
                            JSONObject extendjitem = new JSONObject();
                            extendjitem.put("text", assignout
                                    .getPrivNameByPrivid(extendassign
                                            .getPrivid()));
                            extendjitem.put("subRoleName", roleName);
                            extendAsg.put(extendjitem);
                        }
                    }
                }
            }
        }

        jsonroot.put("asg", asg);
        jsonroot.put("extendAsg", extendAsg);
        System.out.println(jsonroot.toString());
        return jsonroot;
    }

    /**
     * 获取用户或角色类型的机房域或者部门域
     *
     * @param userId 用户id
     * @param url    唯一标识，url
     * @param type   权限类别,Constants.PERMISSION_MENU为菜单权限;Constants.PERMISSION_ACTION;Constants.PERMISSION_REGION
     * @return 用户所有权限
     * @throws TawSystemUserException
     */
    public List getPermissions(String objectid, String objecttype, String type) {

        ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivRegionManager");
        StringBuffer buffer = new StringBuffer();
        buffer.append("'" + objectid + "'");
        if (objecttype.equals(StaticVariable.PRIV_ASSIGNTYPE_USER)) {
            TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
            try {
                List userrolelist = rolebo.getRoleidByuserid(objectid);
                if (userrolelist != null && userrolelist.size() > 0) {
                    buffer.append(",");
                    for (int i = 0; i < userrolelist.size(); i++) {

                        String roleid = (String) userrolelist.get(i);
                        buffer.append("'");
                        buffer.append(roleid);
                        buffer.append("'");
                        if (i + 1 != userrolelist.size()) {
                            buffer.append(",");
                        }
                    }
                }
            } catch (TawSystemUserException e) {
                BocoLog.error(this, "获取用户角色ID出错:" + e.getMessage());
            }
        }
        return regionmanager.getObjectRegionByTypejdbc(buffer.toString(),
                objecttype, type);
    }

    /**
     * 取某用户区域权限
     *
     * @param userId 用户id
     * @param url    唯一标识url
     * @return 用户拥有区域权限集合
     */
    public List getPermissions4region(String objectid) {
        ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivRegionManager");
        return regionmanager.getObjectRegion(objectid);
    }

    /**
     * 判断某人是否拥有权限
     *
     * @param userId 用户id
     * @param url    唯一标识，url
     * @param type   权限类别,Constants.PERMISSION_MENU为菜单权限;Constants.PERMISSION_ACTION;Constants.PERMISSION_REGION
     * @return 是否拥有权限
     */
    public boolean hasPermission(String objectid, String url, String type) {
        boolean flag = false;
        ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivRegionManager");
        List list = regionmanager.getObjectRegionByUrl(objectid, url, type);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断某用户是否拥有域权限
     *
     * @param userId 用户id
     * @param url    域对应的唯一标识
     * @return 是否拥有操作定义权限
     */
    public boolean hasPermission4region(String userId, String url) {
        return PrivMgrLocator.getPrivMgr().hasPriv(userId, url);
        // return TawSystemUserAssignBo.getInstance().isHaveUrl(url, userId);
    }

    /**
     * 判断某用户是否拥有操作定义权限，如button
     *
     * @param userId 用户id
     * @param url    button的url
     * @return 是否拥有操作定义权限
     */
    public boolean hasPermission4action(String userId, String url) {
        return PrivMgrLocator.getPrivMgr().hasPriv(userId, url);
        // return hasPermission(userId, url, StaticVariable.PRIV_TYPE_BUTTON);
    }

    /**
     * 判断某用户是否拥有菜单权限
     *
     * @param userId 用户id
     * @param url    菜单url
     * @return 是否拥有菜单权限
     */
    public boolean hasPermission4menu(String userId, String url) {
        return PrivMgrLocator.getPrivMgr().hasPriv(userId, url);
//		return hasPermission(userId, url, StaticVariable.PRIV_TYPE_MENU);
    }

    /**
     * 判断用户或者角色是否拥有权限域的操作权限
     *
     * @param code       部门或者机房的菜单项ID
     * @param objecttype 角色或者用户的类型标识
     * @param objectid   角色或者用户ID
     * @return
     */
    public boolean hasRegionBymenuids(String code, String objecttype,
                                      String objectid) {
        boolean flag = false;
        if (objecttype.equals(StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
            ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemPrivRegionManager");
            List assignlist = getObjectPriv(objectid);
            if (assignlist != null && assignlist.size() > 0) {
                StringBuffer buffer = new StringBuffer();

                for (int i = 1; i <= assignlist.size(); i++) {
                    TawSystemPrivAssign assign = (TawSystemPrivAssign) assignlist
                            .get(i);
                    String menuid = assign.getPrivid();
                    buffer.append("'");
                    buffer.append(menuid);
                    buffer.append("'");
                    if (i != assignlist.size()) {
                        buffer.append(",");
                    }
                }
                flag = regionmanager
                        .hasRegionBymenuids(buffer.toString(), code);
            }

        } else if (objecttype.equals(StaticVariable.PRIV_ASSIGNTYPE_USER)) {
            ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemPrivUserAssignManager");
            flag = assignmanager.hasCode(objectid, code);
        }
        return flag;
    }

    /**
     * 保存权限域
     *
     * @param objectid   用户ID 或者角色ID
     * @param objecttype 用户还是角色标识
     * @param regionid   域ID 机房id或者 部门id
     * @param regiontype 域类型 部门或者机房的标识
     */

    public void savePrivRegions(String objectid, String objecttype,
                                String[] regionid, String regiontype) {
        ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivRegionManager");
        if (hasRegionsByObject(objectid, objecttype, regiontype)) {
            regionmanager.removeOjbectregion(objectid, objecttype);
        }
        if (regionid != null && regionid.length > 0) {

            for (int i = 0; i < regionid.length; i++) {
                TawSystemPrivRegion privregion = new TawSystemPrivRegion();
                privregion.setObjectid(objectid);
                privregion.setObjecttype(objecttype);
                privregion.setRegionid(regionid[i]);
                privregion.setIsapp(regiontype);
                // TODO
                privregion.setMenuid("1");
                regionmanager.saveTawSytemPrivRegion(privregion);
            }
        }
    }

    /**
     * 保存权限域
     *
     * @param objectid   用户ID 或者角色ID
     * @param objecttype 用户还是角色标识
     * @param regionid   域ID 机房id或者 部门id
     * @param regiontype 域类型 部门或者机房的标识
     */

    public void savePrivRegions2(String objectid, String objecttype,
                                 String regionid, String regiontype) {
        ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivRegionManager");
        TawSystemPrivRegion privregion = new TawSystemPrivRegion();
        privregion.setObjectid(objectid);
        privregion.setObjecttype(objecttype);
        privregion.setRegionid(regionid);
        privregion.setIsapp(regiontype);
        // TODO
        privregion.setMenuid("1");
        regionmanager.saveTawSytemPrivRegion(privregion);
    }

    /**
     * 判断某对象是否有权限域
     *
     * @param objectid
     * @param objecttype
     * @param regiontype
     * @return
     */
    public boolean hasRegionsByObject(String objectid, String objecttype,
                                      String regiontype) {
        boolean flag = false;
        ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivRegionManager");
        List list = regionmanager.getObjectRegionByType(objectid, objecttype,
                regiontype);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断某对象是否部门权限域
     *
     * @param objectid
     * @param objecttype
     * @param regiontype
     * @return
     */
    public boolean hasRegionsByObject(String objectid, String objecttype,
                                      String region, String regiontype) {
        boolean flag = false;
        ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivRegionManager");
        List list = regionmanager.getObjectRegionByType(objectid, objecttype,
                region, regiontype);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }
}
