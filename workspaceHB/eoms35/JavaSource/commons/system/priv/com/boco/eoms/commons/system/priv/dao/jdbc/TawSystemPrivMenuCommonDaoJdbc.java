package com.boco.eoms.commons.system.priv.dao.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;

/**
 * @author panlong
 * @version 3.5
 */
public class TawSystemPrivMenuCommonDaoJdbc extends BaseDaoJdbc implements
        TawSystemPrivMenuCommonDao {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao#getSpecMenuItems(java.lang.String)
     */
    public List getSpecMenuItems(String _strMenuCode) {
        String _strSQLClause = "select distinct a.code,a.menuid,b.name as itmeName,b.deleted,b.isapp,a.parentcode,c.name as parentItemName,a.isLeaf,a.isHide,b.url "
                + "from taw_system_priv_menuitem a "
                + "left join taw_system_priv_operation b "
                + "on a.code=b.code "
                + "left join taw_system_priv_operation c "
                + "on a.parentcode=c.code "
                // + "where a.menuCode='"
                // + _strMenuCode + "'";
                + "where a.menuid in (" + _strMenuCode + ") ";

        List _objReturnList = new ArrayList();
        List _objList = getJdbcTemplate().queryForList(_strSQLClause);
        Iterator _objIterator = _objList.iterator();
        while (_objIterator.hasNext()) {
            TawSystemPrivUserAssign _objTmp = new TawSystemPrivUserAssign();
            Map _objMap = (Map) _objIterator.next();
            _objTmp.setCurrentprivid(StaticMethod.nullObject2String(_objMap
                    .get("code")));
            _objTmp.setCurrentprivname(StaticMethod.nullObject2String(_objMap
                    .get("itmeName")));
            String _strParentItemId = StaticMethod.nullObject2String(_objMap
                    .get("parentcode"));
            _objTmp.setParentprivid(_strParentItemId);
            String _obj = StaticMethod.nullObject2String(_objMap
                    .get("parentItemName"));
            if (_obj != null && !"".equals(_obj)) {
                _objTmp.setParentprivname(_obj);
            } else {
                _objTmp.setParentprivname(_strParentItemId);
            }

            _objTmp.setUrl(StaticMethod.nullObject2String(_objMap.get("url")));

            _objTmp.setLeaf(StaticMethod.nullObject2Integer(_objMap
                    .get("isLeaf")));
            _objTmp.setHide(StaticMethod.nullObject2Integer(_objMap
                    .get("deleted")));
            _objTmp.setIsapp(StaticMethod.nullObject2String("isapp"));
            String menuCode = StaticMethod.nullObject2String("menuid");
            _objTmp.setMenuid(menuCode);
            if (_strParentItemId.equals("-1")) {
                _objTmp.setIsonepriv(new Integer(1));
            } else {
                _objTmp.setIsonepriv(new Integer(0));
            }
            _objReturnList.add(_objTmp);
        }
        return _objReturnList;
    }

    public boolean hasChild(String _strMenuCode, String _strItemId) {
        boolean _bRtn = false;

        String _strSQLClause = " select * from taw_system_priv_menuitem where menuid='"
                + _strMenuCode + "' and parentcode='" + _strItemId + "'";
        List _objList = getJdbcTemplate().queryForList(_strSQLClause);
        if (0 < _objList.size()) {
            _bRtn = true;
        }

        return _bRtn;
    }

    /**
     * 根据PRIVID和CODE删除CODE的所有子节点
     *
     * @param privid
     * @param code
     */
    public void removeMenuItemAndSon(String menuid, String code) {
        String sql = " delete from taw_system_priv_menuitem where menuid='"
                + menuid + "' and code like '" + code + "%'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * 根据PRIVID和CODE删除已分配用户权限的所有子节点
     *
     * @param privid
     * @param code
     */
    public void removeUserAssignPriv(String menuid, String code) {
        String sql = " delete from taw_system_priv_userassign where menuid='"
                + menuid + "' and currentprivid like '" + code + "%'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * 删除某用户的权限taw_system_priv_userassign
     *
     * @param userid
     */
    public void removeUserAssignByUserid(String userid) {
        String sql = " delete from taw_system_priv_userassign where userid='"
                + userid + "'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * 删除某用户的权限taw_system_priv_userassign
     *
     * @param userid
     */
    public void removeUserAssignByUseridAndPrivid(String userid,
                                                  String currentprivid) {
        String sql = " delete from taw_system_priv_userassign where userid='"
                + userid + "' and currentprivid='" + currentprivid + "'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * 删除某菜单方案
     *
     * @param menuid
     */
    public void removeUserAssignByMenuid(String menuid) {
        String sql = " delete from taw_system_priv_userassign where menuid='"
                + menuid + "'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * 删除某菜单方案
     *
     * @param menuid
     */
    public void removeAssignByMenuid(String menuid) {
        String sql = " delete from taw_system_priv_assign where privid='"
                + menuid + "'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * 查询用户所拥有的菜单方案
     *
     * @param userid
     * @return
     */
    public List getPrivMenubyUserid(String userid) {
        String sql = " select privmenu.* from taw_system_priv_menu privmenu where privmenu.privid in (select privassign.privid from taw_system_priv_assign privassign where privassign.objectid='"
                + userid
                + "' or privassign.objectid in ( select userrole.subroleid from taw_system_userrefrole userrole where userrole.userid='"
                + userid + "'))";
        List list = getJdbcTemplate().queryForList(sql);
        List usermenulist = new ArrayList();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            TawSystemPrivMenu privmenus = new TawSystemPrivMenu();
            Map map = (Map) iter.next();
            privmenus.setName(map.get("name").toString().trim());
            privmenus.setOwnerId(map.get("ownerid").toString().trim());
            privmenus.setPrivid(map.get("privid").toString());
            privmenus.setRemark(map.get("remark").toString().trim());
            usermenulist.add(privmenus);

        }
        return usermenulist;

    }

    /**
     * 根据权限ID 删除所有本身以及所有的子权限
     *
     * @param privid
     */
    public void removeMenuItemAndSonByPrivid(String privid, String menuid) {
        String sql = " delete from taw_system_priv_userassign where currentprivid like '"
                + privid + "%' and menuid='" + menuid + "'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * 验证菜单名称是否已经存在
     *
     * @param menuname
     * @return
     */
    public boolean isExits(String menuname) {
        String sql = " select * from taw_system_priv_menu where name='"
                + menuname + "'";
        List list = (List) getJdbcTemplate().queryForList(sql);
        boolean flag = false;
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;

    }

    /**
     * 根据USERID和是否一级菜单标志查询当前用户的一级菜单
     *
     * @param userid
     * @param isonepriv
     * @return
     */
    public List getOnePriv(String userid, String isonepriv) {
        // FIXME 修改添加菜单排序功能，去掉distinct，可能存在问题
        // String sql = " select
        // distinct(assign.isonepriv),assign.url,assign.currentprivid,assign.currentprivname
        // from taw_system_priv_userassign assign where assign.userid='"
        String sql = " select distinct(assign.isonepriv),assign.url,assign.currentprivid,assign.currentprivname,orderby from taw_system_priv_userassign assign where assign.userid='"
                + userid
                + "' and assign.isonepriv='"
                + Integer.valueOf(isonepriv)
                + "' and hide=0 and isapp<>'2' order by orderby ";
        List list = getJdbcTemplate().queryForList(sql);
        Iterator iter = list.iterator();
        List oneprivlist = new ArrayList();
        while (iter.hasNext()) {
            TawSystemPrivUserAssign userassign = new TawSystemPrivUserAssign();
            Map map = (Map) iter.next();
            userassign.setUrl(map.get("url").toString().trim());
            userassign.setCurrentprivname(map.get("currentprivname").toString()
                    .trim());
            userassign.setCurrentprivid(map.get("currentprivid").toString()
                    .trim());
            oneprivlist.add(userassign);
        }
        //顶层菜单可能会出现重复的情况 edit by liqiuye 20080901
        List noRepeatList = new ArrayList();
        HashMap hm = new HashMap();
        for (Iterator it = oneprivlist.iterator(); it.hasNext(); ) {
            TawSystemPrivUserAssign privUserAssign = (TawSystemPrivUserAssign) it.next();
            if (null == hm.get(privUserAssign.getCurrentprivid())) {
                hm.put(privUserAssign.getCurrentprivid(), privUserAssign);
                noRepeatList.add(privUserAssign);
            }
        }
        return noRepeatList;
    }

    /**
     * 根据用户ID 菜单ID 获取下一级的菜单
     *
     * @param userid
     * @param menuid
     * @return
     */
    public List getLowerleveMenus(final String userid, final String menuid) {

        String sql = " select distinct(assign.currentprivid),assign.url,assign.currentprivname from taw_system_priv_userassign assign where assign.userid='"
                + userid
                + "'"
                + " and assign.parentprivid='"
                + menuid
                + "' and assign.hide=0 and isapp<>'2' ";
        List list = getJdbcTemplate().queryForList(sql);
        Iterator iter = list.iterator();
        List privlist = new ArrayList();
        while (iter.hasNext()) {
            TawSystemPrivUserAssign userassign = new TawSystemPrivUserAssign();
            Map map = (Map) iter.next();
            userassign.setUrl(map.get("url").toString().trim());
            userassign.setCurrentprivname(map.get("currentprivname").toString()
                    .trim());
            userassign.setCurrentprivid(map.get("currentprivid").toString()
                    .trim());
            privlist.add(userassign);
        }
        return privlist;
    }

    /**
     * 修改用户已经拥有的菜单项
     *
     * @param privid
     */
    public void updateUserAssignByPrivid(String privid, String hide) {

        String sql = " update taw_system_priv_userassign  set hide=" + hide
                + " where currentprivid='" + privid + "'";
        getJdbcTemplate().update(sql);
    }

    /**
     * 删除某对象的权限域
     *
     * @param objectid
     * @param objecttype
     */
    public void removeOjbectregion(String objectid, String objecttype) {
        String sql = " delete from taw_system_priv_region where objectid='"
                + objectid + "' and objecttype='" + objecttype + "'";
        getJdbcTemplate().execute(sql);
    }

    /**
     * @param objectid
     * @param type
     * @return
     */
    public List getObjectRegionBytype(String objectid, String types, String flag) {

        String sql = " select * from taw_system_priv_region where objectid='"
                + objectid + "' and flag='" + flag + "' and isapp in(" + types
                + ")";
        return getJdbcTemplate().queryForList(sql);
    }

    /**
     * 判断某菜单项是否存在于某些菜单方案
     *
     * @param menuids
     * @return
     */
    public boolean hasRegionBymenuids(String menuids, String code) {
        boolean flag = false;
        String sql = " select * from taw_system_priv_menuitem where menuid in("
                + menuids + ") and code='" + code + "'";
        List list = getJdbcTemplate().queryForList(sql);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询某对象 某种类型的域
     *
     * @param objectid
     * @param type
     * @return
     */
    public List getObjectRegionByType(String objectid, String objecttype,
                                      String type) {

        String sql = "";
        if (type.equals(StaticVariable.PRIV_TYPE_REGION_ALL)) {
            sql = " select * from taw_system_priv_region privregion where privregion.objectid in("
                    + objectid
                    + ") and privregion.objecttype='"
                    + objecttype
                    + "'";
        } else {
            sql = " select * from taw_system_priv_region privregion where privregion.objectid in("
                    + objectid
                    + ") and privregion.isapp='"
                    + type
                    + "' and privregion.objecttype='" + objecttype + "'";
        }
        List list = getJdbcTemplate().queryForList(sql);
        List datalist = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Map map = (Map) iter.next();
            TawSystemPrivRegion region = new TawSystemPrivRegion();
            region.setObjectid(map.get("objectid").toString());
            region.setObjecttype(map.get("objecttype").toString());
            region.setRegionid(map.get("regionid").toString());
            datalist.add(region);
        }
        return datalist;
    }

    /**
     * 删除某用戶的权限部门域
     *
     * @param objectid
     * @param objecttype
     */
    public void removedeptregion(String objectid, String regionid) {
        String sql = " delete from taw_system_priv_region where objectid='"
                + objectid + "' and regionid='" + regionid + "'";
        getJdbcTemplate().execute(sql);
    }
}
