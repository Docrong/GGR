package com.boco.eoms.eva.mgr;

import com.boco.eoms.eva.model.EvaTemplate;

/**
 * <p>
 * Title:考核模板业务方法类
 * </p>
 * <p>
 * Description:考核模板业务方法类
 * </p>
 * <p>
 * Date:2008-11-20 上午11:06:40
 * </p>
 *
 * @author 李秋野
 * @version 3.5.1
 */
public interface IEvaTemplateMgr {

    /**
     * 根据主键id查询模板
     *
     * @param id 主键
     * @return
     */
    public EvaTemplate getTemplate(String id);

    /**
     * 保存模板
     *
     * @param template 模板
     */
    public void saveTemplate(EvaTemplate template);

    /**
     * 删除模板（逻辑删除，仅从树图中删除）
     *
     * @param tplNodeId 模板对应树节点Id
     */
    public void removeTplLogical(String tplNodeId);

    /**
     * 删除模板（物理删除） *
     *
     * @param template  模板
     * @param tplNodeId 模板对应树节点Id
     */
    public void removeTplPhysical(String tplNodeId);

    /**
     * 保存模板，树节点和任务
     *
     * @param template     模板
     * @param parentNodeId 父节点Id
     * @param orgIds       组织Id
     */
    public void saveTemplateWithNodeAndTask(EvaTemplate template,
                                            String parentNodeId, String[] orgIds);

    /**
     * 修改模板
     *
     * @param template     模板
     * @param parentNodeId 父节点Id
     * @param orgIds       组织Id
     */
    public void updateTemplate(EvaTemplate template, String parentNodeId,
                               String[] orgIds);

    /**
     * 根据模板Id返回模板名称
     *
     * @param id 模板Id
     * @return
     */
    public String id2Name(String id);

    /**
     * 激活模板（生成任务详细信息）
     *
     * @param templateId 模板Id
     * @param tplNodeId  模板对应树节点Id
     */
    public void activeTemplate(String templateId, String tplNodeId);

    /**
     * 计算模板下属指标权重总和（仅计算叶子节点）
     *
     * @param tplNodeId 模板对应节点Id
     */
    public Float getTotalWtOfTemplate(String tplNodeId);

}
