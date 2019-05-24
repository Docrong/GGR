package com.boco.eoms.eva.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.eva.dao.IEvaKpiDao;
import com.boco.eoms.eva.mgr.IEvaKpiInstanceMgr;
import com.boco.eoms.eva.mgr.IEvaKpiMgr;
import com.boco.eoms.eva.mgr.IEvaTreeMgr;
import com.boco.eoms.eva.model.EvaKpi;
import com.boco.eoms.eva.model.EvaKpiInstance;
import com.boco.eoms.eva.model.EvaTree;
import com.boco.eoms.eva.util.EvaConstants;
import com.boco.eoms.log4j.Logger;

public class EvaKpiMgrImpl implements IEvaKpiMgr {

	private IEvaKpiDao evaKpiDao;

	public IEvaKpiDao getEvaKpiDao() {
		return evaKpiDao;
	}

	public void setEvaKpiDao(IEvaKpiDao evaKpiDao) {
		this.evaKpiDao = evaKpiDao;
	}

	public EvaKpi getKpi(String id) {
		return evaKpiDao.getKpi(id);
	}

	public EvaKpi getKpi(String id, String deleted) {
		return evaKpiDao.getKpi(id, deleted);
	}

	public EvaKpi getKpiByNodeId(String nodeId) {
		return evaKpiDao.getKpiByNodeId(nodeId);
	}

	public ArrayList listKpiOfType(String parentNodeId) {
		return evaKpiDao.listKpiOfType(parentNodeId);
	}

	public void removeKpi(EvaKpi kpi) {
		// 假删除指标
		evaKpiDao.removeKpi(kpi);
	}

	public void saveKpi(EvaKpi kpi) {
		evaKpiDao.saveKpi(kpi);
	}

	public void saveKpiAndNode(EvaKpi kpi, String parentNodeId) {
		IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
				.getInstance().getBean("IevaTreeMgr");

		if (null == kpi.getId() || "".equals(kpi.getId())) { // 新指标
			// 保存指标
			kpi.setDeleted(EvaConstants.UNDELETED);
			evaKpiDao.saveKpi(kpi);

			EvaTree evaTree = new EvaTree();

			// 生成新节点ID
			String newNodeId = treeMgr.getMaxNodeId(parentNodeId);

			// 保存树节点
			evaTree.setNodeId(newNodeId);
			evaTree.setParentNodeId(parentNodeId);
			evaTree.setNodeName(kpi.getKpiName());
			evaTree.setNodeType(EvaConstants.NODETYPE_KPI);
			evaTree.setLeaf(EvaConstants.NODE_LEAF);
			evaTree.setObjectId(kpi.getId());
			evaTree.setWeight(kpi.getWeight());
			treeMgr.saveTreeNode(evaTree);

			// 更新父节点leaf
			treeMgr.updateParentNodeLeaf(parentNodeId,
					EvaConstants.NODE_NOTLEAF);
		} else { // 修改指标
			// 获得对应树节点
			EvaTree node = treeMgr.getNodeByObjId(parentNodeId, kpi.getId());
			// 设置权重
			node.setWeight(kpi.getWeight());
			EvaKpi kpi2 = new EvaKpi();
			kpi2.setAlgorithm(kpi.getAlgorithm());
			kpi2.setCreateTime(kpi.getCreateTime());
			kpi2.setCreator(kpi.getCreator());
			kpi2.setCycle(kpi.getCycle());
			kpi2.setDeleted(kpi.getDeleted());
			kpi2.setEvaScore(kpi.getEvaScore());
			kpi2.setKpiName(kpi.getKpiName());
			kpi2.setRemark(kpi.getRemark());
			kpi2.setRuleGroupId(kpi.getRuleGroupId());
			kpi2.setThreshold(kpi.getThreshold());
			kpi2.setWeight(kpi.getWeight());
//			// 保存新指标
//			UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
//			try {
//				//kpi.setId(uu.getID());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Logger logger = Logger.getLogger(this.getClass());
			logger.info("\n保存kpi前");
			evaKpiDao.saveKpi(kpi2);
			logger.info("\n保存kpi后");
			node.setObjectId(kpi2.getId());
			// 更新权重
			treeMgr.saveTreeNode(node);

		}
	}

	public void removeKpiOfType(final String parentNodeId) {
		evaKpiDao.removeKpiOfType(parentNodeId);
	}

	public Float getScoreOfKpi(String templateId, String kpiId, String date) {
		IEvaKpiInstanceMgr instanceMgr = (IEvaKpiInstanceMgr) ApplicationContextHolder
				.getInstance().getBean("IevaKpiInstanceMgr");
		EvaKpiInstance instance = instanceMgr.getKpiInstance(templateId, kpiId,
				date);
		//return instance.getKpiScore();
		return Float.valueOf(0);
	}

	public String id2Name(String id) {
		return evaKpiDao.id2Name(id);
	}

	public Float getNextLevelUsableWt(String parentNodeId) {
		Float usableWt = EvaConstants.DEFAULT_MAX_WT;
		IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
				.getInstance().getBean("IevaTreeMgr");
		List list = treeMgr.listNextLevelNode(parentNodeId,
				EvaConstants.NODETYPE_KPI);
		for (Iterator it = list.iterator(); it.hasNext();) {
			EvaTree node = (EvaTree) it.next();
			usableWt = Float.valueOf(usableWt.floatValue()
					- node.getWeight().floatValue());
		}
		if (usableWt.floatValue() < 0) {
			usableWt = new Float(0);
		}
		return usableWt;
	}

	public Float getMinWt(String parentNodeId, String kpiId) {
		IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
				.getInstance().getBean("IevaTreeMgr");
		Float minWt = EvaConstants.DEFAULT_MIN_WT;
		// 如果是编辑指标
		if (null != kpiId && !"".equals(kpiId)) {
			// 获得当前编辑节点
			EvaTree currentNode = treeMgr.getNodeByObjId(parentNodeId, kpiId);
			// 获得当前节点下级节点列表
			List list = treeMgr.listNextLevelNode(currentNode.getNodeId(),
					EvaConstants.NODETYPE_KPI);
			// 加上当前节点下级的权重
			for (Iterator it = list.iterator(); it.hasNext();) {
				EvaTree node = (EvaTree) it.next();
				minWt = Float.valueOf(minWt.floatValue()
						+ node.getWeight().floatValue());
			}
		}
		return minWt;
	}

	public Float getMaxWt(String parentNodeId, String kpiId) {
		IEvaTreeMgr treeMgr = (IEvaTreeMgr) ApplicationContextHolder
				.getInstance().getBean("IevaTreeMgr");
		// 可分配最大权重
		Float maxWt = EvaConstants.DEFAULT_MAX_WT;
		// 上级节点
		EvaTree parentNode = treeMgr.getTreeNodeByNodeId(parentNodeId);
		if (null != parentNode.getId() && !"".equals(parentNode.getId())) {
			maxWt = parentNode.getWeight();
		}
		// 下级kpi列表
		List list = treeMgr.listNextLevelNode(parentNodeId,
				EvaConstants.NODETYPE_KPI);
		// 减去下级所有节点权重
		for (Iterator it = list.iterator(); it.hasNext();) {
			EvaTree node = (EvaTree) it.next();
			maxWt = Float.valueOf(maxWt.floatValue()
					- node.getWeight().floatValue());
		}
		// 如果是编辑指标
		if (null != kpiId && !"".equals(kpiId)) {
			EvaTree currentNode = treeMgr.getNodeByObjId(parentNodeId, kpiId);
			maxWt = Float.valueOf(maxWt.floatValue()
					+ currentNode.getWeight().floatValue());
		}
		return maxWt;
	}
}
