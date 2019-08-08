/**
 *
 */
package com.boco.eoms.commons.system.priv.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public interface TawSystemPrivMenuCommonDao extends Dao {

    /**
     * 根据菜单方案ID 查询所有菜单项
     *
     * @param _strMenuCode
     *            菜单方案编码
     * @return List 菜单项VO对象集合
     */
    public List getSpecMenuItems(String _strMenuCode);

    /**
     * 判断某菜单方案下面某节点是否有子节点
     *
     * @param String
     *            _strMenuCode
     * @param String
     *            _strItemId
     *
     * @return boolean
     */
    public boolean hasChild(String _strMenuCode, String _strItemId);

    /**
     * 根据PRIVID和CODE删除CODE的属于子节点
     *
     * @param privid
     * @param code
     */
    public void removeMenuItemAndSon(String privid, String code);

    /**
     * 删除某用户的权限taw_system_priv_userassign
     *
     * @param userid
     */
    public void removeUserAssignByUserid(String userid);

    /**
     * 查询用户所拥有的菜单方案
     *
     * @param userid
     * @return
     */
    public List getPrivMenubyUserid(String userid);

    /**
     * 删除某用户的权限taw_system_priv_userassign
     *
     * @param userid
     */
    public void removeUserAssignByUseridAndPrivid(String userid,
                                                  String currentprivid);

    /**
     * 根据权限ID 删除所有本身以及所有的子权限
     *
     * @param privid
     */
    public void removeMenuItemAndSonByPrivid(String privid, String menuid);

    /**
     * 验证菜单名称是否已经存在
     *
     * @param menuname
     * @return
     */
    public boolean isExits(String menuname);

    /**
     * 根据PRIVID和CODE删除已分配用户权限的所有子节点
     *
     * @param privid
     * @param code
     */
    public void removeUserAssignPriv(String menuid, String code);

    /**
     * 根据USERID和是否一级菜单标志查询当前用户的一级菜单
     *
     * @param userid
     * @param isonepriv
     * @return
     */
    public List getOnePriv(String userid, String isonepriv);

    /**
     * 根据用户ID 菜单ID 获取下一级的菜单
     *
     * @param userid
     * @param menuid
     * @return
     */
    public List getLowerleveMenus(final String userid, final String menuid);

    /**
     * 修改用户已经拥有的菜单项
     *
     * @param privid
     */
    public void updateUserAssignByPrivid(String privid, String hide);

    /**
     *
     * @param objectid
     * @param type
     * @return
     */
    public List getObjectRegionBytype(String objectid, String reginids,
                                      String flag);

    /**
     * 判断某菜单项是否存在于某些菜单方案
     *
     * @param menuids
     * @return
     */
    public boolean hasRegionBymenuids(String menuids, String code);

    /**
     * 删除某菜单方案
     *
     * @param menuid
     */
    public void removeUserAssignByMenuid(String menuid);

    /**
     * 删除某菜单方案
     *
     * @param menuid
     */
    public void removeAssignByMenuid(String menuid);

    /**
     * 删除某对象的权限域
     *
     * @param objectid
     * @param objecttype
     */
    public void removeOjbectregion(String objectid, String objecttype);

    /**
     * 查询某对象 某种类型的域
     *
     * @param objectid
     * @param type
     * @return
     */
    public List getObjectRegionByType(String objectid, String objecttype,
                                      String type);

    public void removedeptregion(String objectid, String regionid);
}
