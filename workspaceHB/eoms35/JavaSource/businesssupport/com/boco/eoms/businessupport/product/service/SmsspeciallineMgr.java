package com.boco.eoms.businessupport.product.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.businessupport.product.model.Smsspecialline;

/**
 * <p>
 * Title:短彩信
 * </p>
 * <p>
 * Description:短彩信工单
 * </p>
 * <p>
 * Wed Jun 02 19:47:25 CST 2010
 * </p>
 *
 * @author 李志刚
 * @version 3.5
 */
public interface SmsspeciallineMgr {

    /**
     * 取短彩信 列表
     *
     * @return 返回短彩信列表
     */
    public List getSmsspeciallines();

    /**
     * 根据主键查询短彩信
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public Smsspecialline getSmsspecialline(final String id);

    /**
     * 保存短彩信
     *
     * @param smsspecialline 短彩信
     */
    public void saveSmsspecialline(Smsspecialline smsspecialline);

    /**
     * 根据主键删除短彩信
     *
     * @param id 主键
     */
    public void removeSmsspecialline(final String id);

    /**
     * 根据条件分页查询短彩信
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回短彩信的分页列表
     */
    public Map getSmsspeciallines(final Integer curPage, final Integer pageSize,
                                  final String whereStr);

}