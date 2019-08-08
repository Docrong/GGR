package com.boco.eoms.commons.mms.mmsreporttemplate.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate;

/**
 * <p>
 * Title:彩信报模板
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Tue Feb 17 14:50:50 CST 2009
 * </p>
 *
 * @author 李志刚
 * @version 3.5
 */
public interface MmsreportTemplateMgr {

    /**
     * 取彩信报模板 列表
     *
     * @return 返回彩信报模板列表
     */
    public List getMmsreportTemplates();

    /**
     * 根据主键查询彩信报模板
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public MmsreportTemplate getMmsreportTemplate(final String id);

    /**
     * 保存彩信报模板
     *
     * @param mmsreportTemplate 彩信报模板
     */
    public void saveMmsreportTemplate(MmsreportTemplate mmsreportTemplate);

    /**
     * 根据主键删除彩信报模板
     *
     * @param id 主键
     */
    public void removeMmsreportTemplate(final String id);

    public MmsreportTemplate getMmsreportTemplateForSubId(final String jobid);

    /**
     * 根据条件分页查询彩信报模板
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回彩信报模板的分页列表
     */
    public Map getMmsreportTemplates(final Integer curPage, final Integer pageSize,
                                     final String whereStr);

}