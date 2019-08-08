package com.boco.eoms.commons.system.priv.test.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager;
import com.boco.eoms.commons.system.priv.service.impl.TawSystemPrivMenuItemManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemPrivMenuItemManagerTest extends ConsoleTestCase {

    /**
     * 删除所以的子菜单项
     */
    public void testRemoveMentitemson() {

        ITawSystemPrivMenuItemManager mentitem = (ITawSystemPrivMenuItemManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivMenuItemManager");
        mentitem.removeMenuItemAndSon("2", "1009");
    }

    private final String tawSystemPrivMenuItemId = "1";

    private TawSystemPrivMenuItemManagerImpl tawSystemPrivMenuItemManager = new TawSystemPrivMenuItemManagerImpl();

    private Mock tawSystemPrivMenuItemDao = null;

    private Mock tawSystemPrivMenuCommonDao = null;

    protected void onSetUpInTransaction() throws Exception {
        super.setUp();
        tawSystemPrivMenuItemDao = new Mock(TawSystemPrivMenuItemDao.class);
        tawSystemPrivMenuCommonDao = new Mock(TawSystemPrivMenuCommonDao.class);
        tawSystemPrivMenuItemManager
                .setTawSystemPrivMenuItemDao((TawSystemPrivMenuItemDao) tawSystemPrivMenuItemDao
                        .proxy());
        tawSystemPrivMenuItemManager
                .setTawSystemPrivMenuCommonDao((TawSystemPrivMenuCommonDao) tawSystemPrivMenuCommonDao
                        .proxy());
    }


    public void testSaveMenuitemAndParentMueuitem() {

        ITawSystemPrivMenuItemManager mentitem = (ITawSystemPrivMenuItemManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivMenuItemManager");
        TawSystemPrivMenuItem privmentitem = mentitem
                .getMenuItemByPrividAndCode("2", "1004");
        mentitem.saveMenuItemAndParentMenu(privmentitem);
        TawSystemPrivMenuItem parentmenuitem = mentitem
                .getMenuItemByPrividAndCode(privmentitem.getMenuid(),
                        privmentitem.getParentcode());
        System.out.println("IsLeaf=====" + parentmenuitem.getIsLeaf());

    }

    public void testGetNextLevelMenus() {
        List results = new ArrayList();
        TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();
        ITawSystemPrivMenuItemManager tawSystemPrivMenuItemManager = (ITawSystemPrivMenuItemManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivMenuItemManager");

        // set expected behavior on dao

        String _strMenuCode = "2";
        String _strItemId = "-1";
        List list = tawSystemPrivMenuItemManager.getNextLevelMenus(
                _strMenuCode, _strItemId);
        for (int i = 0; i < list.size(); i++) {
            tawSystemPrivMenuItem = (TawSystemPrivMenuItem) list.get(i);
            System.out.println(tawSystemPrivMenuItem.getCode());
            System.out.println(tawSystemPrivMenuItem.getMenuid());
            System.out.println(tawSystemPrivMenuItem.getParentcode());
            System.out.println("######################################");
        }
    }

    public void testGetNameBycode() {
        TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut.getInstance();

        String name = "";
        name = assignout.getNameBycode("10");
        System.out.println(name + ":######################################");
    }

    public void testGetTawSystemPrivMenuItems() throws Exception {
        List results = new ArrayList();
        TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();
        results.add(tawSystemPrivMenuItem);

        // set expected behavior on dao

        List tawSystemPrivMenuItems = tawSystemPrivMenuItemManager
                .getTawSystemPrivMenuItems(null);
        assertTrue(tawSystemPrivMenuItems.size() == 1);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testGetTawSystemPrivMenuItem() throws Exception {
        // set expected behavior on dao

        TawSystemPrivMenuItem tawSystemPrivMenuItem = tawSystemPrivMenuItemManager
                .getTawSystemPrivMenuItem(tawSystemPrivMenuItemId);
        assertTrue(tawSystemPrivMenuItem != null);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testSaveTawSystemPrivMenuItem() throws Exception {
        TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();

        // set expected behavior on dao

        tawSystemPrivMenuItemManager
                .saveTawSystemPrivMenuItem(tawSystemPrivMenuItem);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testAddAndRemoveTawSystemPrivMenuItem() throws Exception {
        TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();

        // set required fields
        tawSystemPrivMenuItem
                .setMenuid("DnLaBlJsBlNlUuZvEtXqWuGfMpMmCmBwMoWpJsCnLlCpPdZmOnJxCjIdJvUrEaIuNjDfXrZqLxZmKbEhYyIjOpHcOuPuQaVqChPa");
        tawSystemPrivMenuItem
                .setCode("XuCbZeQsJoLsCiPfCyHmIjVlReAiVaQuXoDsGiKyAtQeSwZdOlStQaZfVjTdCsEqVpCqIcLsUeVxRnZhXxXuGfEsQzLgLyJhBaGv");
        tawSystemPrivMenuItem
                .setParentcode("HjZrTxWkRsHoMiJzOqPwKhYuKlJkVwJeIrVeQiBoZuEcSnYyEaEnOiIuFxTkDyCoEzDwZaFxJwNdUaPfFeFiUqBqAuQaJvTjAvGr");
        tawSystemPrivMenuItem.setIsLeaf("ZkNxSvUsHq");
        tawSystemPrivMenuItem.setIsHide("KsSvVgGcFp");

        // set expected behavior on dao

        tawSystemPrivMenuItemManager
                .saveTawSystemPrivMenuItem(tawSystemPrivMenuItem);
        tawSystemPrivMenuItemDao.verify();

        // reset expectations
        tawSystemPrivMenuItemDao.reset();

        tawSystemPrivMenuItemManager
                .removeTawSystemPrivMenuItem(tawSystemPrivMenuItemId);
        tawSystemPrivMenuItemDao.verify();

        // reset expectations
        tawSystemPrivMenuItemDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(
                TawSystemPrivMenuItem.class, tawSystemPrivMenuItem.getId());

        tawSystemPrivMenuItemManager
                .removeTawSystemPrivMenuItem(tawSystemPrivMenuItemId);
        try {
            tawSystemPrivMenuItemManager
                    .getTawSystemPrivMenuItem(tawSystemPrivMenuItemId);
            fail("TawSystemPrivMenuItem with identifier '"
                    + tawSystemPrivMenuItemId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSystemPrivMenuItemDao.verify();
    }

    /**
     * 娴嬭瘯getTawSystemPrivMenuItems(final Integer curPage, final Integer
     * pageSize)鍑芥暟
     */
    public void testGetTawSystemPrivMenuItems_Ex1() throws Exception {
        // set expected behavior on dao

        Integer curPage = new Integer(1);
        Integer pageSize = new Integer(5);
        Map tawSystemPrivMenuItem = tawSystemPrivMenuItemManager
                .getTawSystemPrivMenuItems(curPage, pageSize);
        assertTrue(tawSystemPrivMenuItem != null);
        tawSystemPrivMenuItemDao.verify();
    }

    /**
     * 娴嬭瘯getTawSystemPrivMenuItems(final Integer curPage, final Integer
     * pageSize, final String whereStr)鍑芥暟
     */
    public void testGetTawSystemPrivMenuItems_Ex2() throws Exception {
        // set expected behavior on dao

        Integer curPage = new Integer(1);
        Integer pageSize = new Integer(5);
        String whereStr = "";
        Map tawSystemPrivMenuItem = tawSystemPrivMenuItemManager
                .getTawSystemPrivMenuItems(curPage, pageSize, whereStr);
        assertTrue(tawSystemPrivMenuItem != null);
        tawSystemPrivMenuItemDao.verify();
    }

    /**
     * 锟剿碉拷锟斤拷锟斤拷锟斤拷锟襟集猴拷
     */
    public void testGetAllMenus() throws Exception {
        List results = new ArrayList();
        TawSystemPrivMenu tawSystemPrivMenu = new TawSystemPrivMenu();
        results.add(tawSystemPrivMenu);

        // set expected behavior on dao

        List tawSystemPrivMenus = tawSystemPrivMenuItemManager.getAllMenus();
        assertTrue(tawSystemPrivMenus.size() >= 0);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testGetSelfMenus() throws Exception {
        List results = new ArrayList();
        TawSystemPrivMenu tawSystemPrivMenu = new TawSystemPrivMenu();
        results.add(tawSystemPrivMenu);

        // set expected behavior on dao

        String _strOwnerId = "admin";
        List tawSystemPrivMenus = tawSystemPrivMenuItemManager
                .getSelfMenus(_strOwnerId);
        assertTrue(tawSystemPrivMenus.size() >= 0);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testGetSpecMenuItems() throws Exception {

        List results = new ArrayList();
        TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
        results.add(tawSystemPrivUserAssign);

        // set expected behavior on dao

        String _strMenuCode = "1010";
        List tawSystemPrivUserAssigns = tawSystemPrivMenuItemManager
                .getSpecMenuItems(_strMenuCode);
        assertTrue(tawSystemPrivUserAssigns.size() >= 0);
        tawSystemPrivMenuCommonDao.verify();
    }

    public void testGetFirLevelMenus() throws Exception {
        List results = new ArrayList();
        TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();
        results.add(tawSystemPrivMenuItem);

        // set expected behavior on dao

        String _strMenuCode = "1010";
        List tawSystemPrivMenuItems = tawSystemPrivMenuItemManager
                .getFirLevelMenus(_strMenuCode);
        assertTrue(tawSystemPrivMenuItems.size() >= 0);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testGetValidSubMenuItems() throws Exception {
        List results = new ArrayList();
        TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
        results.add(tawSystemPrivUserAssign);

        // set expected behavior on dao

        String _strUserId = "admin";
        String _strParentItemId = "1010";
        List tawSystemPrivUserAssigns = tawSystemPrivMenuItemManager
                .getValidSubMenuItems(_strUserId, _strParentItemId);
        assertTrue(tawSystemPrivUserAssigns.size() >= 0);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testGetSubMenuItems() throws Exception {
        List results = new ArrayList();
        TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();
        results.add(tawSystemPrivMenuItem);

        // set expected behavior on dao

        List _objTotals = null;
        List _objExists = null;
        List tawSystemPrivMenuItems = tawSystemPrivMenuItemManager
                .getSubMenuItems(_objTotals, _objExists);
        assertTrue(tawSystemPrivMenuItems.size() >= 0);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testSaveParentMenuItems() throws Exception {
        TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();

        // set expected behavior on dao

        String _strMenuCode = "1010";
        String _strMenuId = "99";
        tawSystemPrivMenuItemManager.saveParentMenuItems(_strMenuCode,
                _strMenuId);
        tawSystemPrivMenuItemDao.verify();
    }

    public void testGetMenuName() throws Exception {
        // set expected behavior on dao

        String _strMenuCode = "1010";
        Object _obj = tawSystemPrivMenuItemManager.getMenuName(_strMenuCode);
        assertTrue(_obj != null);
        tawSystemPrivMenuItemDao.verify();
    }
}
