package com.boco.eoms.eva.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.eva.dao.IEvaKpiInstanceDao;
import com.boco.eoms.eva.mgr.IEvaKpiInstanceMgr;
import com.boco.eoms.eva.mgr.IEvaOrgMgr;
import com.boco.eoms.eva.mgr.IEvaTemplateMgr;
import com.boco.eoms.eva.model.EvaKpi;
import com.boco.eoms.eva.model.EvaKpiInstance;
import com.boco.eoms.eva.model.EvaOrg;
import com.boco.eoms.eva.model.EvaTemplate;
import com.boco.eoms.eva.util.DateTimeUtil;
import com.boco.eoms.eva.util.EvaConstants;
import com.boco.eoms.eva.util.EvaStaticMethod;

public class EvaKpiInstanceMgrImpl implements IEvaKpiInstanceMgr {

	private IEvaKpiInstanceDao evaKpiInstanceDao;

	public IEvaKpiInstanceDao getEvaKpiInstanceDao() {
		return evaKpiInstanceDao;
	}

	public void setEvaKpiInstanceDao(IEvaKpiInstanceDao evaKpiInstanceDao) {
		this.evaKpiInstanceDao = evaKpiInstanceDao;
	}

	public EvaKpiInstance getKpiInstance(String id) {
		return evaKpiInstanceDao.getKpiInstance(id);
	}

	public List listKpiInstanceOfTemplate(String orgId, String date) {
		IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) ApplicationContextHolder
				.getInstance().getBean("IevaTemplateMgr");
		IEvaOrgMgr orgMgr = (IEvaOrgMgr) ApplicationContextHolder.getInstance()
				.getBean("IevaOrgMgr");
		// 获得任务
		EvaOrg org = orgMgr.getEvaOrg(orgId);
		// 获得模板
		EvaTemplate template = templateMgr.getTemplate(org.getTemplateId());
		// 根据模板周期获得开始时间
		String startDate = EvaStaticMethod.getStartTimeByCycle(template
				.getCycle(), date);
		// 根据模板周期获得结束时间
		String endDate = EvaStaticMethod.getEndTimeByCycle(template.getCycle(),
				date);
		return evaKpiInstanceDao.listKpiInstanceOfTemplate(orgId, startDate,
				endDate);
	}

	public void removeKpiInstance(EvaKpiInstance kpiInstance) {
		evaKpiInstanceDao.removeKpiInstance(kpiInstance);
	}

	public void saveKpiInstance(EvaKpiInstance kpiInstance) {
		evaKpiInstanceDao.saveKpiInstance(kpiInstance);
	}

	public void genKpiInstance(String orgId, String genOperatorId) {
		// IEvaTemplateKpiMgr templateKpiMgr = (IEvaTemplateKpiMgr)
		// ApplicationContextHolder
		// .getInstance().getBean("IevaTemplateKpiMgr");
		// IEvaOrgMgr orgMgr = (IEvaOrgMgr)
		// ApplicationContextHolder.getInstance()
		// .getBean("IevaOrgMgr");
		// // 获得任务
		// EvaOrg org = orgMgr.getEvaOrg(orgId);
		// List kpiList = templateKpiMgr.listKpiOfTemplate(org.getTemplateId());
		// for (Iterator kpiIter = kpiList.iterator(); kpiIter.hasNext();) {
		// EvaKpiInstance instance = new EvaKpiInstance();
		// EvaKpi kpi = (EvaKpi) kpiIter.next();
		// instance.setTemplateId(org.getTemplateId());
		// instance.setKpiId(kpi.getId());
		// instance.setGenOperator(genOperatorId);
		// instance.setGenTime(DateTimeUtil
		// .getCurrentDateTime(EvaConstants.DATE_FORMAT));
		// instance.setOrgId(orgId);
		// saveKpiInstance(instance);
		//		}
	}

	public boolean isInstanceExistsInTime(String orgId, String date) {
		List instanceList = listKpiInstanceOfTemplate(orgId, date);
		boolean flag = false;
		if (null != instanceList && 0 < instanceList.size()) {
			flag = true;
		}
		return flag;
	}

	
	/**
	 * 查询某任务某时间所属周期的所有指标考核实例
	 * 王思轩 2009-1-20
	 * @param  任务id，时间类型，时间，合作伙伴ID
	 * @return 指标考核实例
	 */
	public EvaKpiInstance getKpiInstanceByTimeAndPartner(final String taskDetailId,
			final String timeType,final String time, final String partnerId){
		return evaKpiInstanceDao.getKpiInstanceByTimeAndPartner(taskDetailId, timeType, time, partnerId);
	}
	
	/**
	 * 查询某任务某时间所属周期的所有指标考核实例
	 * 王思轩 2009-1-22
	 * @param  任务id，合作伙伴ID，时间类型，时间范围，
	 * @return 指标考核实例
	 */
	public List getKpiInstanceListByTimeAndPartner(final String taskDetailId, 
			final String partnerId, final String timeType,
			final String startTime, final String endTime, final String isPublish){
		return evaKpiInstanceDao.getKpiInstanceListByTimeAndPartner(taskDetailId, partnerId, timeType, startTime, endTime, isPublish);
	}
	
	public EvaKpiInstance getKpiInstance(String orgId, String kpiId, String date) {
		IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) ApplicationContextHolder
				.getInstance().getBean("IevaTemplateMgr");
		IEvaOrgMgr orgMgr = (IEvaOrgMgr) ApplicationContextHolder.getInstance()
				.getBean("IevaOrgMgr");
		// 获得任务
		EvaOrg org = orgMgr.getEvaOrg(orgId);
		// 获得模板
		EvaTemplate template = templateMgr.getTemplate(org.getTemplateId());
		// 根据模板周期获得开始时间
		String startDate = EvaStaticMethod.getStartTimeByCycle(template
				.getCycle(), date);
		// 根据模板周期获得结束时间
		String endDate = EvaStaticMethod.getEndTimeByCycle(template.getCycle(),
				date);
		return evaKpiInstanceDao.getKpiInstanceOfTemplate(orgId, kpiId,
				startDate, endDate);
	}

	public List listKpiOfTemplateWithScore(String orgId, String date) {
		// IEvaTemplateKpiMgr templateKpiMgr = (IEvaTemplateKpiMgr)
		// ApplicationContextHolder
		// .getInstance().getBean("IevaTemplateKpiMgr");
		// IEvaOrgMgr orgMgr = (IEvaOrgMgr)
		// ApplicationContextHolder.getInstance()
		// .getBean("IevaOrgMgr");
		// // 获得任务
		// EvaOrg org = orgMgr.getEvaOrg(orgId);
		// List kpiList = templateKpiMgr.listKpiOfTemplate(org.getTemplateId());
		// List list = new ArrayList();
		// for (Iterator kpiIter = kpiList.iterator(); kpiIter.hasNext();) {
		// EvaKpi kpi = (EvaKpi) kpiIter.next();
		// EvaKpiInstance instance = getKpiInstance(orgId, kpi.getId(), date);
		// // 未生成实例则不加入列表
		// if (null != instance.getId() && !"".equals(instance.getId())) {
		// kpi.setEvaScore(instance.getKpiScore());
		// list.add(kpi);
		//			}
		//		}
		//		return list;
		return new ArrayList();
	}

	public Float getTotalScoreOfTemplate(String orgId, String date) {
		float totalScore = 0;
		List kpiList = listKpiOfTemplateWithScore(orgId, date);
		for (Iterator kpiIter = kpiList.iterator(); kpiIter.hasNext();) {
			EvaKpi kpi = (EvaKpi) kpiIter.next();
			if (null != kpi.getEvaScore()) {
				totalScore = totalScore + kpi.getEvaScore().floatValue();
			}
		}
		return Float.valueOf(totalScore);
	}
	
	/**
	 * 详细任务ID转换为模板名称
	 * 王思轩 09-1-21
	 * @return
	 */
	public String id2Name(String id){
		return evaKpiInstanceDao.id2Name(id);
	}

}
