package com.boco.eoms.commons.mms.mmsreporttemplate.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
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
public interface MmsreportTemplateDao extends Dao {

    /**
     * 取彩信报模板列表
     *
     * @return 返回彩信报模板列表
     */
    public List getMmsreportTemplates();

    /**
     * 根据主键查询彩信报模板
     *
     * @param id 主键
     * @return 返回某id的彩信报模板
     */
    public MmsreportTemplate getMmsreportTemplate(final String id);

    /**
     * 保存彩信报模板
     *
     * @param mmsreportTemplate 彩信报模板
     */
    public void saveMmsreportTemplate(MmsreportTemplate mmsreportTemplate);

    /**
     * 根据id删除彩信报模板
     *
     * @param id 主键
     */
    public void removeMmsreportTemplate(final String id);

    public MmsreportTemplate getMmsreportTemplateForSubId(final String jobid);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getMmsreportTemplates(final Integer curPage, final Integer pageSize,
                                     final String whereStr);

}