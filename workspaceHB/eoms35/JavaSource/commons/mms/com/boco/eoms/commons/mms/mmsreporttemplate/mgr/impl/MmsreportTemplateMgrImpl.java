package com.boco.eoms.commons.mms.mmsreporttemplate.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.mmsreporttemplate.dao.MmsreportTemplateDao;
import com.boco.eoms.commons.mms.mmsreporttemplate.mgr.MmsreportTemplateMgr;
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
public class MmsreportTemplateMgrImpl implements MmsreportTemplateMgr {

    private MmsreportTemplateDao mmsreportTemplateDao;

    public MmsreportTemplateDao getMmsreportTemplateDao() {
        return this.mmsreportTemplateDao;
    }

    public void setMmsreportTemplateDao(MmsreportTemplateDao mmsreportTemplateDao) {
        this.mmsreportTemplateDao = mmsreportTemplateDao;
    }

    public List getMmsreportTemplates() {
        return mmsreportTemplateDao.getMmsreportTemplates();
    }

    public MmsreportTemplate getMmsreportTemplate(final String id) {
        return mmsreportTemplateDao.getMmsreportTemplate(id);
    }

    public void saveMmsreportTemplate(MmsreportTemplate mmsreportTemplate) {
        mmsreportTemplateDao.saveMmsreportTemplate(mmsreportTemplate);
    }

    public void removeMmsreportTemplate(final String id) {
        mmsreportTemplateDao.removeMmsreportTemplate(id);
    }

    public Map getMmsreportTemplates(final Integer curPage, final Integer pageSize,
                                     final String whereStr) {
        return mmsreportTemplateDao.getMmsreportTemplates(curPage, pageSize, whereStr);
    }

    public MmsreportTemplate getMmsreportTemplateForSubId(String jobid) {
        return mmsreportTemplateDao.getMmsreportTemplateForSubId(jobid);
    }

}