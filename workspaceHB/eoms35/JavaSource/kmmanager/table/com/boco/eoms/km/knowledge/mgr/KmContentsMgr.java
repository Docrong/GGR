package com.boco.eoms.km.knowledge.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.servlet.context.RequestContext;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:32:12 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public interface KmContentsMgr {

    /**
     * 根据知识模型所属分类 THEME_ID 查询知识条目
     *
     * @param THEME_ID 知识模型所属分类
     * @param curPage  当前所处的页码
     * @param pageSize 每页显示最大记录条数
     * @return Map 知识内容对应的Map对象<字段名, 字段值>
     */
    public Map searchKmContentss(final String THEME_ID, final boolean isLeaf, final Integer curPage, final Integer pageSize);

    /**
     * 根据知识模型所属分类 THEME_ID 查询某状态知识条目
     *
     * @param THEME_ID 知识模型所属分类
     * @param curPage  当前所处的页码
     * @param pageSize 每页显示最大记录条数
     * @return Map 知识内容对应的Map对象<字段名, 字段值>
     */
    public Map searchKmContentss(final String THEME_ID, final boolean isLeaf, String state, final String userId, final Integer curPage, final Integer pageSize);

    /**
     * 保存知识条目
     *
     * @param reqContext 知识条目内容
     */
    public void saveKmContents(RequestContext reqContext);

    /**
     * 根据知识条目的 ID、TABLE_ID、THEME_ID 失效知识条目
     *
     * @param ID       知识的ID
     * @param TABLE_ID 知识所属模型ID
     * @param THEME_ID 知识模型所属分类ID
     */
    public void overKmContents(final String ID, final String TABLE_ID, final String THEME_ID);

    /**
     * 根据知识条目的 ID、TABLE_ID、THEME_ID 删除知识条目
     *
     * @param ID       知识的ID
     * @param TABLE_ID 知识所属模型ID
     * @param THEME_ID 知识模型所属分类ID
     */
    public void removeKmContents(final String ID, final String TABLE_ID, final String THEME_ID);

    /**
     * 根据知识条目的 THEME_ID 删除知识条目
     *
     * @param ID       知识的ID
     * @param TABLE_ID 知识所属模型ID
     * @param THEME_ID 知识模型所属分类ID
     */
    public void removeKmContentsByThemeId(final String THEME_ID);

    /**
     * 修改知识条目
     *
     * @param reqContext 知识条目内容，注意：reqContext 中必须包含 ID、TABLE_ID、
     */
    public void updateKmContents(RequestContext reqContext);

    /**
     * 修改知识条目(用于审核修改状态)
     *
     * @param reqContext 知识条目内容，注意：reqContext 中必须包含 ID、TABLE_ID、
     */
    public void updateKmContentsForAudit(RequestContext reqContext);

    /**
     * 根据知识条目的 ID、TABLE_ID、THEME_ID 查询知识条目
     *
     * @param ID       知识的ID
     * @param TABLE_ID 知识所属模型ID
     * @param THEME_ID 知识模型所属分类ID
     */
    public Map getKmContents(final String ID, final String TABLE_ID, final String THEME_ID);

    /**
     * 根据页面录入的查询条件进行知识查询
     *
     * @param reqContext 查询条件
     * @param curPage    当前所处的页码
     * @param pageSize   每页显示最大记录条数
     * @return Map 知识内容对应的Map对象<字段名, 字段值>
     */
    public Map searchKmContentss(RequestContext reqContext, Integer curPage, Integer pageSize);

    /**
     * 根据页面录入的查询条件进行组合知识查询
     *
     * @param reqContext 查询条件
     * @param curPage    当前所处的页码
     * @param pageSize   每页显示最大记录条数
     * @return Map 知识内容对应的Map对象<字段名, 字段值>
     */
    public Map complexQuery(String sql, Integer curPage, Integer pageSize);


    /**
     * 修改知识条目的评论等级
     *
     * @param TABLE_ID   知识所属模型ID
     * @param CONTENT_ID 知识的ID
     * @param grade      知识等级
     */
    public void updateKmContentsGrade(final String TABLE_ID, final String CONTENT_ID, final int grade);

    /**
     * 根据知识条目的 ID、TABLE_ID、THEME_ID 查询知识条目的历史版本
     *
     * @param ID       知识的ID
     * @param TABLE_ID 知识所属模型ID
     * @param THEME_ID 知识模型所属分类ID
     */
    public List getKmContentsHistoryList(final String ID, final String TABLE_ID, final String THEME_ID);

    /**
     * 根据知识条目的 ID、TABLE_ID、THEME_ID 查询知识条目版本信息
     *
     * @param ID       知识的ID
     * @param TABLE_ID 知识所属模型ID
     * @param THEME_ID 知识模型所属分类ID
     */
    public Map getKmContentsHistory(final String ID, final String TABLE_ID, final String THEME_ID, final String VERSION);

    /**
     * 查询某作者处于草稿状态的知识
     *
     * @param operateUserId 知识作者
     * @return
     */
    public List getKmContentsIsDraftByCreateUser(final String operateUserId);

    /**
     * 查询某作者处于删除状态的知识
     *
     * @param operateUserId 知识作者
     * @return
     */
    public List getKmContentsIsDeletedByCreateUser(final String operateUserId);


    //下面的方法暂时没有使用到---------------------------------------------


    /**
     * 取知识管理 列表
     *
     * @return 返回知识管理列表
     */
    public List getKmContentss();

    /**
     * 根据主键查询知识管理
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmContents getKmContents(final String id);

    /**
     * 保存知识内容中不变的部分到 KM_CONTENTS中
     *
     * @param kmContents
     */
    public void saveKmContents(KmContents kmContents);

    /**
     * 根据主键删除知识管理
     *
     * @param id 主键
     */
    public void removeKmContents(final String id);

    /**
     * 根据条件分页查询知识管理
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识管理的分页列表
     */
    public Map getKmContentss(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据知识创建人查询知识信息 KM_CONTENTS
     *
     * @param createUser
     * @return
     */
    public List getKmContentsList(final String createUser);

    /**
     * 根据知识id删除KM_CONTENTS中的知识记录
     *
     * @param contentId
     */
    public void removeKmContentsByContentId(final String contentId);

    /**
     * 查询某模型下的所用知识
     *
     * @param themeId
     * @return
     */
    public List getKmContentsListByThemeId(final String themeId);

    public List getKmContentsListLinkThemeId(final String themeId);

    /**
     * 根据知识id查询 订阅中间表中的知识记录 KM_CONTNETS
     *
     * @param contentId
     * @return
     */
    public KmContents getKmContentsByContentId(final String contentId);

    /**
     * 回收站删除
     *
     * @param themeId
     */
    public void deleteKmContentsByThemeId(final String themeId);

    /**
     * 清空回收站
     */
    public void deleteKmContents();

    public void revivifyAllKmContents();

    public void revivifyKmContents(final String themeId);
}