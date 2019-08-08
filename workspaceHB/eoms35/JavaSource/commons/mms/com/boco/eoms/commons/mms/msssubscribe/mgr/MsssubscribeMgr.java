package com.boco.eoms.commons.mms.msssubscribe.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;
import com.boco.eoms.commons.mms.msssubscribe.model.Msssubscribe;

/**
 * <p>
 * Title:彩信报订阅
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Fri Feb 20 11:32:20 CST 2009
 * </p>
 *
 * @author 李志刚
 * @version 3.5
 */
public interface MsssubscribeMgr {

    /**
     * 取彩信报订阅 列表
     *
     * @return 返回彩信报订阅列表
     */
    public List getMsssubscribes();

    /**
     * 根据主键查询彩信报订阅
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public Msssubscribe getMsssubscribe(final String id);

    /**
     * 保存彩信报订阅
     *
     * @param msssubscribe 彩信报订阅
     */

    public void saveMsssubscribe(Msssubscribe msssubscribe);

    /**
     * 根据主键删除彩信报订阅
     *
     * @param id 主键
     */
    public void removeMsssubscribe(final String id);

    /**
     * 根据条件分页查询彩信报订阅
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回彩信报订阅的分页列表
     */
    public Map getMsssubscribes(final Integer curPage, final Integer pageSize,
                                final String whereStr);

    /*
     * 根据已生成彩信报订阅发送模型
     */
    public Msssubscribe getMmsreport(Mmsreport mmsreport);


    /*
     * 1.在订阅发送模型内数据完整后调用发送彩信接口
     * //2.删除本条订阅发送模型记录
     * msssubscribe:订阅模型
     *
     * mmsreport：彩信报模型
     *
     * 返回彩信报发送的状态 成功或失败 若原因是什么
     *
     */
    public String sendMmsreport(Msssubscribe msssubscribe, Mmsreport mmsreport) throws Exception;

//	
//    /*
//     * 保存
//     */
//	public void saveCustomstatRemind(CustomstatRemind _customstatremind);
//	
//    /*
//     * 删除
//     */	
//	public void deleteCustomstatRemind(CustomstatRemind _customstatremind);
//	
//	/**
//	 * 刷新内存
//	 */
//	public void flush();

}