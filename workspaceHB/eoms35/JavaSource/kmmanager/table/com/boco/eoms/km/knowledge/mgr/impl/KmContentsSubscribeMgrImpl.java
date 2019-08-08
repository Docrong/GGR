package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.List;

import com.boco.eoms.km.knowledge.dao.KmContentsSubscribeDao;
import com.boco.eoms.km.knowledge.mgr.KmContentsSubscribeMgr;
import com.boco.eoms.km.knowledge.model.KmContentsSubscribe;
import com.boco.eoms.km.knowledge.model.KmContentsSubscribeTable;

public class KmContentsSubscribeMgrImpl implements KmContentsSubscribeMgr {

    private KmContentsSubscribeDao kmContentsSubscribeDao;


    public KmContentsSubscribeDao getKmContentsSubscribeDao() {
        return kmContentsSubscribeDao;
    }

    public void setKmContentsSubscribeDao(
            KmContentsSubscribeDao kmContentsSubscribeDao) {
        this.kmContentsSubscribeDao = kmContentsSubscribeDao;
    }

    /**
     * 根据订阅人查询 订阅信息
     *
     * @param subscribeUser
     * @return该订阅人的订阅信息列表
     */
    public List listKmContentsSubscribe(final String subscribeUser) {
        return kmContentsSubscribeDao.listKmContentsSubscribe(subscribeUser);
    }

    /**
     * 根据订阅人批量删除订阅信息
     *
     * @param subscribeUser
     */
    public void removeKmContentsSubscribe(final String subscribeUser) {
        kmContentsSubscribeDao.removeKmContentsSubscribe(subscribeUser);
    }


    /**
     * 保存根据创建人订阅的信息
     *
     * @param kmContentsSubscribe
     */
    public void saveKmContentsSubscribe(KmContentsSubscribe kmContentsSubscribe) {
        kmContentsSubscribeDao.saveKmContentsSubscribe(kmContentsSubscribe);
    }

    /**
     * 根据id删除订阅信息
     *
     * @param id
     */
    public void removeKmContentsSubscribeById(final String id) {
        kmContentsSubscribeDao.removeKmContentsSubscribeById(id);
    }

    //-----------------------根据分类订阅----------------------------

    /**
     * 根据订阅人 查询所用订阅分类的信息
     *
     * @param subscribeUser
     */
    public List listKmContentsSubscribeTable(final String subscribeUser) {
        return kmContentsSubscribeDao.listKmContentsSubscribeTable(subscribeUser);
    }

    /**
     * 删除根据分类的订阅信息
     *
     * @param subscribeUser
     */
    public void removeKmContentsSubscribeTable(final String subscribeUser) {
        kmContentsSubscribeDao.removeKmContentsSubscribeTable(subscribeUser);
    }


    /**
     * 保存根据分类订阅的信息
     *
     * @param kmContentsSubscribeTable
     */
    public void saveKmContentsSubscribeTable(KmContentsSubscribeTable kmContentsSubscribeTable) {
        kmContentsSubscribeDao.saveKmContentsSubscribeTable(kmContentsSubscribeTable);
    }

    /**
     * 根据id删除订阅信息
     *
     * @param id
     */
    public void removeKmContentsSubscribeTableById(final String id) {
        kmContentsSubscribeDao.removeKmContentsSubscribeTableById(id);
    }
}
