package com.boco.eoms.eva.mgr.impl;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.eva.dao.IEvaTemplateDao;
import com.boco.eoms.eva.mgr.IEvaTaskDetailMgr;
import com.boco.eoms.eva.mgr.IEvaTaskMgr;
import com.boco.eoms.eva.mgr.IEvaTemplateMgr;
import com.boco.eoms.eva.mgr.IEvaTreeMgr;
import com.boco.eoms.eva.model.EvaTask;
import com.boco.eoms.eva.model.EvaTaskDetail;
import com.boco.eoms.eva.model.EvaTemplate;
import com.boco.eoms.eva.model.EvaTree;
import com.boco.eoms.eva.util.EvaConstants;

public class EvaTemplateMgrImpl implements IEvaTemplateMgr {

    private IEvaTemplateDao evaTemplateDao;

    public IEvaTemplateDao getEvaTemplateDao() {
        return evaTemplateDao;
    }

    public void setEvaTemplateDao(IEvaTemplateDao evaTemplateDao) {
        this.evaTemplateDao = evaTemplateDao;
    }

    public EvaTemplate getTemplate(String id) {
        return evaTemplateDao.getTemplate(id);
    }

    public void removeTemplate(EvaTemplate template) {
        evaTemplateDao.removeTemplate(template);
    }

    public void removeTplLogical(String tplNodeId) {
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTreeMgr");
        treeMgr.removeTreeNodeByNodeId(tplNodeId);
    }

    public void removeTplPhysical(String tplNodeId) {
        // 删除树图中对应节点
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTreeMgr");
        EvaTree tplNode = treeMgr.getTreeNodeByNodeId(tplNodeId);
        treeMgr.removeTreeNodeByNodeId(tplNodeId);

        // 获得节点对应模板
        EvaTemplate tpl = getTemplate(tplNode.getObjectId());

        // 删除模板对应任务
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTaskMgr");
        taskMgr.removeTaskOfTemplate(tpl.getId());
        // 删除模板
        removeTemplate(tpl);
    }

    public void saveTemplate(EvaTemplate template) {
        evaTemplateDao.saveTemplate(template);
    }

    public void saveTemplateWithNodeAndTask(EvaTemplate template,
                                            String parentNodeId, String[] orgIds) {
        // 保存模板 并将NodeId存入模板中
        template.setTemplateTypeId(parentNodeId);
        evaTemplateDao.saveTemplate(template);
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTreeMgr");
        // 保存任务及相关信息
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTaskMgr");
        // 删除模板对应所有任务
        taskMgr.removeTaskOfTemplate(template.getId());
        for (int i = 0; i < orgIds.length; i++) {
            // 保存任务
            EvaTask task = new EvaTask();
            task.setOrgId(orgIds[i]);
            task.setOrgType(template.getOrgType());
            task.setTemplateId(template.getId());
            task.setCreator(template.getCreator());
            task.setCreateTime(template.getCreateTime());
            taskMgr.saveTask(task);
        }

        // 保存新节点
        EvaTree newNode = new EvaTree();
        newNode.setLeaf(EvaConstants.NODE_LEAF);
        newNode.setNodeId(treeMgr.getMaxNodeId(parentNodeId));
        newNode.setParentNodeId(parentNodeId);
        newNode.setObjectId(template.getId());
        newNode.setNodeType(EvaConstants.NODETYPE_TEMPLATE);
        newNode.setWeight(EvaConstants.DEFAULT_MAX_WT);
        treeMgr.saveTreeNode(newNode);


        // 设置父节点为非叶子节点
        EvaTree parentNode = treeMgr.getTreeNodeByNodeId(parentNodeId);
        parentNode.setLeaf(EvaConstants.NODE_NOTLEAF);
        treeMgr.saveTreeNode(parentNode);
    }

    public void updateTemplate(EvaTemplate template, String parentNodeId,
                               String[] orgIds) {
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTreeMgr");
        if (EvaConstants.TEMPLATE_NOTACTIVATED.equals(template.getActivated())) { // 如果未激活，仅修改模板信息即可
            evaTemplateDao.saveTemplate(template);
        } else { // 如果是已激活模板，由于修改会产生新的模板，所以要修改对应树节点记录的objectId
            // 保存新模板
            template.setId("");
            evaTemplateDao.saveTemplate(template);

            // 修改对应树节点记录的objectId
            EvaTree tplNode = treeMgr.getTreeNodeByNodeId(parentNodeId);
            tplNode.setObjectId(template.getId());
            treeMgr.saveTreeNode(tplNode);
        }
        // 保存新任务及相关信息
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTaskMgr");
        // 删除模板对应所有任务
        taskMgr.removeTaskOfTemplate(template.getId());
        for (int i = 0; i < orgIds.length; i++) {
            // 保存任务
            EvaTask task = new EvaTask();
            task.setOrgId(orgIds[i]);
            task.setOrgType(template.getOrgType());
            task.setTemplateId(template.getId());
            task.setCreator(template.getCreator());
            task.setCreateTime(template.getCreateTime());
            taskMgr.saveTask(task);
        }
    }

    public void activeTemplate(String templateId, String tplNodeId) {
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTreeMgr");
        IEvaTemplateMgr tplMgr = (IEvaTemplateMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTemplateMgr");
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTaskMgr");
        IEvaTaskDetailMgr taskDetailMgr = (IEvaTaskDetailMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTaskDetailMgr");

        // 修改模板为激活状态
        EvaTemplate tpl = tplMgr.getTemplate(templateId);
        tpl.setActivated(EvaConstants.TEMPLATE_ACTIVATED);
        tplMgr.saveTemplate(tpl);

        // 取模板对应任务列表
        List taskList = taskMgr.listTaskOfTpl(templateId);
        for (Iterator taskIt = taskList.iterator(); taskIt.hasNext(); ) {
            EvaTask task = (EvaTask) taskIt.next();
            // 取模板下所有指标节点
            List kpiNodeList = treeMgr.listChildNodes(tplNodeId,
                    EvaConstants.NODETYPE_KPI, "");
            // 行列表编号
            int listNo = 1;
            // 模板下属指标最大层数
            int maxLevel = treeMgr.getMaxLevelOfChildNode(tplNodeId);
            for (Iterator kpiNodeIt = kpiNodeList.iterator(); kpiNodeIt
                    .hasNext(); ) {
                EvaTree kpiNode = (EvaTree) kpiNodeIt.next();
                // 设置任务详细信息
                EvaTaskDetail taskDetail = new EvaTaskDetail();
                taskDetail.setTaskId(task.getId());
                taskDetail.setKpiId(kpiNode.getObjectId());
                taskDetail.setWeight(kpiNode.getWeight());
                taskDetail.setNodeId(kpiNode.getNodeId());
                taskDetail.setParentNodeId(kpiNode.getParentNodeId());
                taskDetail.setLeaf(kpiNode.getLeaf());
                if (EvaConstants.NODE_LEAF.equals(kpiNode.getLeaf())) {// 如果是叶子节点
                    int currentLevel = (kpiNode.getNodeId().length() - tplNodeId
                            .length())
                            / EvaConstants.NODEID_BETWEEN_LENGTH; // 当前节点所在层数
                    taskDetail.setRowspan("1"); // 叶子节点占一行
                    taskDetail.setColspan(String.valueOf(maxLevel
                            - currentLevel + 1)); // 叶子节点列数 = 最大level -
                    // 当前level + 1
                    taskDetail.setListNo(String.valueOf(listNo));
                    listNo++; // 遍历到叶子节点以后，行号加一
                } else {// 如果是非叶子节点
                    int childLeafNum = treeMgr.listChildNodes(
                            kpiNode.getNodeId(), "", EvaConstants.NODE_LEAF)
                            .size();// 当前节点下的叶子节点数量
                    taskDetail.setRowspan(String.valueOf(childLeafNum)); // 叶子节点所占行数是下属叶子节点数
                    taskDetail.setColspan("1"); // 非叶子节点占一列
                    taskDetail.setListNo(String.valueOf(listNo));
                }
                taskDetailMgr.saveTask(taskDetail);
            }
        }
    }

    public String id2Name(String id) {
        return evaTemplateDao.id2Name(id);
    }

    public Float getTotalWtOfTemplate(String tplNodeId) {
        float totalWt = 0;
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
                .getInstance().getBean("IevaTreeMgr");
        List list = treeMgr.listChildNodes(tplNodeId,
                EvaConstants.NODETYPE_KPI, EvaConstants.NODE_LEAF);
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            EvaTree kpiNode = (EvaTree) it.next();
            totalWt += kpiNode.getWeight().floatValue();
        }
        return Float.valueOf(totalWt);
    }
}
