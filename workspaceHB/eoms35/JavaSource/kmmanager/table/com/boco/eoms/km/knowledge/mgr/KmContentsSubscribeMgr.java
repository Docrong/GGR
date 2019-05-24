package com.boco.eoms.km.knowledge.mgr;

import java.util.List;

import com.boco.eoms.km.knowledge.model.KmContentsSubscribe;
import com.boco.eoms.km.knowledge.model.KmContentsSubscribeTable;

public interface KmContentsSubscribeMgr {
	/**
	 * 根据订阅人 删除该订阅人所订阅的所用的知识信息
	 * @param subscribeUser
	 */
	public void removeKmContentsSubscribe(final String subscribeUser);
	
	
	/**
	 * 保存订阅信息
	 * @param kmContentsSubscribe
	 */
	public void saveKmContentsSubscribe(KmContentsSubscribe kmContentsSubscribe);
	
	/**
	 * 根据订阅人查询 订阅信息
	 * @param subscribeUser
	 * @return该订阅人的订阅信息列表
	 */
	public List listKmContentsSubscribe(final String subscribeUser);
	
	/**
     * 根据id删除订阅信息
     * @param id
     */
    public void removeKmContentsSubscribeById(final String id);
	
	//---------------------根据分类订阅----------------------------
	
	 /**
     * 根据订阅人 查询所用订阅分类的信息
     * @param subscribeUser
     */
    public List listKmContentsSubscribeTable(final String subscribeUser);
    
    /**
     * 删除根据分类的订阅信息
     * @param subscribeUser
     */
    public void removeKmContentsSubscribeTable(final String subscribeUser);
    
    /**
     * 保存根据分类订阅的知识信息
     * @param kmContentsSubscribeTable
     */
	public void saveKmContentsSubscribeTable(KmContentsSubscribeTable kmContentsSubscribeTable);

	/**
     * 根据id删除订阅信息
     * @param id
     */
    public void removeKmContentsSubscribeTableById(final String id);
}
