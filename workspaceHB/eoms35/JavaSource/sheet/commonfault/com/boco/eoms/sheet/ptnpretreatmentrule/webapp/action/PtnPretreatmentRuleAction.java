package com.boco.eoms.sheet.ptnpretreatmentrule.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.PoiExcelImpl;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.ptnpretreatmentrule.model.PtnPretreatmentRule;
import com.boco.eoms.sheet.ptnpretreatmentrule.service.IPtnPretreatmentRuleManger;
import com.boco.eoms.sheet.ptnpretreatmentrule.util.PtnPretreatmentRuleIPoiWork;

public class PtnPretreatmentRuleAction extends SheetAction {

	/**
	 * 展示已配置规则列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String alarmId = StaticMethod.nullObject2String(request
				.getParameter("alarmId"));
		String pageIndexName = new org.displaytag.util.ParamEncoder("ruleList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String exportType = StaticMethod
				.null2String(request
						.getParameter((new ParamEncoder("ruleList"))
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		String condtion = "";
		if (!"".equals(alarmId)) {
			condtion = " where alatmID like '%" + alarmId + "%'";
		}
		IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger)
			ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
		Integer total = Integer.valueOf(0);
		List ruleList = new ArrayList();
		Map resultMap = null;
		if (exportType != null && !"".equals(exportType)) {
			resultMap = ptnPretreatmentRuleManger.getRuleListByCondition(
					Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(0),
					condtion);
		} else {
			resultMap = ptnPretreatmentRuleManger.getRuleListByCondition(
					pageSize, pageIndex, condtion);
		}
		if (resultMap != null) {
			ruleList = (List) resultMap.get("list");
			total = (Integer) resultMap.get("total");
		}
		request.setAttribute("alarmId", alarmId);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("total", total);
		request.setAttribute("ruleList", ruleList);
		return mapping.findForward("list");
	}

	/**
	 * 展示新增规则页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInputNewPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		if (!"".equals(id)) {
			IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) 
				ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
			PtnPretreatmentRule ptnPretreatmentRule = (PtnPretreatmentRule) ptnPretreatmentRuleManger
					.getObject(com.boco.eoms.sheet.ptnpretreatmentrule.model.PtnPretreatmentRule.class,id);
			request.setAttribute("ptnPretreatmentRule", ptnPretreatmentRule);
		}
		return mapping.findForward("add");
	}

	/**
	 * 展示导入页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showImportPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("import");
	}

	/**
	 * 新增记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map paramMap = request.getParameterMap();
		if (paramMap != null) {
			Map modelMap = new HashMap();
			Iterator iterator = paramMap.entrySet().iterator();
			Entry entry = null;
			String key = null;
			String[] values = null;
			while (iterator.hasNext()) {
				entry = (Entry) iterator.next();
				key = (String) entry.getKey();
				values = (String[]) entry.getValue();
				modelMap.put(key, values[0]);
			}
			PtnPretreatmentRule ptnPretreatmentRule = new PtnPretreatmentRule();
			SheetBeanUtils.populateMap2Bean(ptnPretreatmentRule, modelMap);
			IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) 
				ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
			ptnPretreatmentRuleManger.saveObject(ptnPretreatmentRule);
		}
		return mapping.findForward("success");
	}

	/**
	 * 删除记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) 
			ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
		ptnPretreatmentRuleManger.deleteObjectById(id);
		JSONObject obejct = new JSONObject();
		obejct.put("deleted", "yes");
		JSONUtil.print(response, obejct.toString());
	}

	/**
	 * 提交验证
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performPreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = StaticMethod.nullObject2String(request.getParameter("type"));
		JSONObject jsonRoot = new JSONObject();
		Map validateMap = new HashMap();
		if("addOrEdit".equals(type)){
			validateMap = this.validateAddOrEdit(request);
		}else if("import".equals(type)){
			validateMap = this.validateImport(request);
		}
		jsonRoot.put("status", StaticMethod.nullObject2String(validateMap.get("status")));
		jsonRoot.put("text", StaticMethod.nullObject2String(validateMap.get("text")));
		JSONUtil.print(response, jsonRoot.toString());
		return;
	}
	
	/**
	 * 导入时验证
	 * @param request
	 * @return
	 * @throws AccessoriesException 
	 */
	public Map validateImport(HttpServletRequest request) throws Exception{
		Map validateMap = new HashMap();
		String attachment = StaticMethod.nullObject2String(request.getParameter("attachment"));
		String status = "0";
		String text = "";
		if("".equals(attachment)){
			status = "2";
			text = "请先上传附件。";
		}else{
			String[] attachments = attachment.split(",");
			String fileType = "";
			/*验证类型*/
			StringBuilder validateFileType = new StringBuilder();
			for(int i = 0; i < attachments.length; i++){
				fileType = attachments[i].substring(attachments[i].lastIndexOf(".") + 1,attachments[i].length() - 1);
				if(!"xls".equals(fileType)){
					validateFileType.append(";附件" + (i + 1) + "的类型不是xls类型，请删除重新上传。");
				}
			}
			text = validateFileType.toString();
			if(text.length() > 0){
				validateMap.put("status", "2");
				validateMap.put("text", text.substring(1));
				return validateMap;
			}
			/*验证数据的有效性及一致性*/
			ITawCommonsAccessoriesManager tawCommonsAccessoriesManager = (ITawCommonsAccessoriesManager)
				ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
			List queryList = tawCommonsAccessoriesManager.getAllFileById(attachment);
			if(queryList != null && !queryList.isEmpty()){
				String validateResult = this.validateFile(queryList);
				if(validateResult.length() > 0){
					status = "2";
					text = validateResult;
				}
			}else{
				status = "2";
				text = "上传的附件可能被删除，请重新上传。";
			}
		}
		validateMap.put("status", status);
		validateMap.put("text", text);
		return validateMap;
	}
	
	/**
	 * 验证附件内容
	 * @param tawCommonsAccessoriesList
	 * @return
	 */
	public String validateFile(List tawCommonsAccessoriesList)throws Exception{
		StringBuilder validateResult = new StringBuilder();
		try{
			ITawCommonsAccessoriesManager tawCommonsAccessoriesManager = (ITawCommonsAccessoriesManager)
				ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
			String basePath = StaticMethod.nullObject2String(tawCommonsAccessoriesManager.getFilePath("commonfault"));
			TawCommonsAccessories tawCommonsAccessories = null;
			String accessoriesName= "";
			String chName = "";
			for(int i = 0; i < tawCommonsAccessoriesList.size(); i++){
				StringBuilder validate = new StringBuilder();
				tawCommonsAccessories = (TawCommonsAccessories)tawCommonsAccessoriesList.get(i);
				accessoriesName = StaticMethod.nullObject2String(tawCommonsAccessories.getAccessoriesName());
				chName = StaticMethod.nullObject2String(tawCommonsAccessories.getAccessoriesCnName());
				PoiExcelImpl pe = new PoiExcelImpl();
				PtnPretreatmentRuleIPoiWork spw = new PtnPretreatmentRuleIPoiWork();
				List list = pe.importExcel(spw, basePath + accessoriesName);
				if(list != null && !list.isEmpty()){
					Map faultDealDescMap = this.getDictNameMapByParentDictId("1010323");
					Map faultReasonSort1Map = this.getDictNameMapByParentDictId("1010303");
					Map faultReasonSort2Map = new HashMap();
					Iterator iterator = faultReasonSort1Map.entrySet().iterator();
					Entry entry = null;
					while(iterator.hasNext()){
						entry = (Entry)iterator.next();
						String value = (String)entry.getValue();
						faultReasonSort2Map.put(value, this.getDictNameMapByParentDictId(value));
					}
					Map faultReasonSort3Map = new HashMap();
					iterator = faultReasonSort2Map.entrySet().iterator();
					while(iterator.hasNext()){
						entry = (Entry)iterator.next();
						Map temMap = (Map)entry.getValue();
						Iterator temIterator = temMap.entrySet().iterator();
						while(temIterator.hasNext()){
							entry = (Entry)temIterator.next();
							String value = (String)entry.getValue();
							faultReasonSort3Map.put(value, this.getDictNameMapByParentDictId(value));
						}
					}
					Map preDealRelationMap = this.getDictNameMapByParentDictId("1010321");
					/*查询已保存的数据*/
					PtnPretreatmentRule ptnPretreatmentRule = null;
					IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) 
						ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
					List query = ptnPretreatmentRuleManger.getListByCondition("");
					Set dbSet1 = new HashSet();
					Set dbSet2 = new HashSet();
					for(int n = 0; query != null && n < query.size(); n++){
						ptnPretreatmentRule = (PtnPretreatmentRule)query.get(n);
						String alarmId = StaticMethod.nullObject2String(ptnPretreatmentRule.getAlatmID());
						String faultDealDesc = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultDealDesc());
						String faultReasonSort1 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort1());
						String faultReasonSort2 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort2());
						String faultReasonSort3 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort3());
						if("101032110".equals(ptnPretreatmentRule.getPreDealRelation())){
							dbSet2.add(alarmId + faultDealDesc +faultReasonSort1 + faultReasonSort2 + faultReasonSort3);
						}else{
							dbSet1.add(alarmId + faultDealDesc +faultReasonSort1 + faultReasonSort2 + faultReasonSort3);
						}
					}
					Set keySetExcel1 = new HashSet();
					Set keySetExcel2 = new HashSet();
					for(int j = 0; j < list.size(); j++){
						boolean flag = true;
						ptnPretreatmentRule = (PtnPretreatmentRule)list.get(j);
						String alarmId = StaticMethod.nullObject2String(ptnPretreatmentRule.getAlatmID());
						String faultDealDesc = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultDealDesc());
						String faultReasonSort1 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort1());
						String faultReasonSort2 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort2());
						String faultReasonSort3 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort3());
						String preDealRelation = StaticMethod.nullObject2String(ptnPretreatmentRule.getPreDealRelation());
						String faultDealDescId = "";
						String faultReasonSort1Id = "";
						String faultReasonSort2Id = "";
						String faultReasonSort3Id = "";
						if("".equals(alarmId)){
							validate.append("；第" + (j + 2) + "行第4列的\"告警\"ID为空");
							flag = false;
						}
						if("".equals(faultDealDesc)){
							validate.append("；第" + (j + 2) + "行第5列的\"故障原因及处理措施\"为空");
							flag = false;
						}else{
							if(!faultDealDescMap.containsKey(faultDealDesc)){
								validate.append("；第" + (j + 2) + "行第5列的\"故障原因及处理措施\"在字典表中不存在");
								flag = false;
							}else{
								faultDealDescId = StaticMethod.nullObject2String(faultDealDescMap.get(faultDealDesc));
							}
						}
						if("".equals(faultReasonSort1)){
							validate.append("；第" + (j + 2) + "行第6列的\"归因一级\"为空");
							flag = false;
						}else{
							if(!faultReasonSort1Map.containsKey(faultReasonSort1)){
								validate.append("；第" + (j + 2) + "行第6列的\"归因一级\"在字典表中不存在");
								flag = false;
							}else{
								faultReasonSort1Id = StaticMethod.nullObject2String(faultReasonSort1Map.get(faultReasonSort1));
								if("".equals(faultReasonSort2)){
									validate.append("；第" + (j + 2) + "行第7列的\"归因二级\"为空");
									flag = false;
								}else{
									Map temp = (Map)faultReasonSort2Map.get(faultReasonSort1Id);
									if(!temp.containsKey(faultReasonSort2)){
										validate.append("；第" + (j + 2) + "行第7列的\"归因二级\"在字典表中不存在");
										flag = false;
									}else{
										faultReasonSort2Id = StaticMethod.nullObject2String(temp.get(faultReasonSort2));
										if("".equals(faultReasonSort3)){
											validate.append("；第" + (j + 2) + "行第8列的\"归因三级\"为空");
											flag = false;
										}else{
											temp = (Map)faultReasonSort3Map.get(faultReasonSort2Id);
											if(!temp.containsKey(faultReasonSort3)){
												validate.append("；第" + (j + 2) + "行第8列的\"归因三级\"在字典表中不存在");
												flag = false;
											}else{
												faultReasonSort3Id = StaticMethod.nullObject2String(temp.get(faultReasonSort3));
											}
										}
									}
								}
							}
						}
						if("".equals(preDealRelation)){
							validate.append("；第" + (j + 2) + "行第9列的\"预处理对应关系\"为空");
							flag = false;
						}else{
							if(!preDealRelationMap.containsKey(preDealRelation)){
								validate.append("；第" + (j + 2) + "行第9列的\"预处理对应关系\"在字典表中不存在");
								flag = false;
							}
						}
						if(flag){
							String excel = alarmId + faultDealDesc + faultReasonSort1 + faultReasonSort2 + faultReasonSort3;
							String db= alarmId + faultDealDescId + faultReasonSort1Id + faultReasonSort2Id + faultReasonSort3Id;
							if("不涉及".equals(preDealRelation) ){
								keySetExcel2.add(excel);
								if(keySetExcel1.contains(excel)){
									validate.append("；第" + (j + 2) + "行配置为\"不涉及\"，在上面的行中不能再配置为其他规则。");
									continue;
								}
								if(dbSet1.contains(db)){
									validate.append("；第" + (j + 2) + "行配置为\"不涉及\"，在数据库中已经配置为其他规则。");
								}
							}else{
								keySetExcel1.add(excel);
								if(keySetExcel2.contains(excel)){
									validate.append("；第" + (j + 2) + "行数据，在上面的行中已经配置为\"不涉及\"，不能再配置为其他规则。");
									continue;
								}
								if(dbSet2.contains(db)){
									validate.append("；第" + (j + 2) + "行数据，在数据库中已经配置为\"不涉及\"，不能再配置为其他规则。");
								}
							}
						}
					}
					String result = validate.toString();
					if(result.length() > 0){
						validateResult.append("附件（" + chName + "）下列数据有误：" + result.substring(1));
					}
				}else{
					validate.append("附件（" + chName + "）没有数据，请核实。");
					validateResult.append(validate.toString());
				}	
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		BocoLog.info(PtnPretreatmentRuleAction.class, "导入验证：" + validateResult.toString());
		return validateResult.toString();
	}
	
	/**
	 * 页面新增或编辑时验证
	 * @param request
	 * @return
	 */
	public Map validateAddOrEdit(HttpServletRequest request){
		Map validateMap = new HashMap();
		Map paramMap = request.getParameterMap();
		StringBuilder condition = new StringBuilder(" where 1=1 ");
		Iterator iterator = paramMap.entrySet().iterator();
		Entry entry = null;
		String key = null;
		String[] values = null;
		String status = "0";
		String text = "";
		String id = "";
		String preDealRelation = "";		
		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();
			key = (String) entry.getKey();
			values = (String[]) entry.getValue();
			if("id".equals(key) && !"".equals(values[0])){
				id =  values[0] ;
			}
			if("alatmID".equals(key) && !"".equals(values[0])){
				condition.append(" and " + key + "='" + values[0] + "'");
			}
			if("faultDealDesc".equals(key) && !"".equals(values[0])){
				condition.append(" and " + key + "='" + values[0] + "'");			
			}
			if("faultReasonSort1".equals(key) && !"".equals(values[0])){
				condition.append(" and " + key + "='" + values[0] + "'");
			}
			if("faultReasonSort2".equals(key) && !"".equals(values[0])){
				condition.append(" and " + key + "='" + values[0] + "'");
			}
			if("faultReasonSort3".equals(key) && !"".equals(values[0])){
				condition.append(" and " + key + "='" + values[0] + "'");
			}
			if("preDealRelation".equals(key) && !"".equals(values[0])){
				preDealRelation =  values[0];
			}
		}
		IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) 
			ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
		List result = ptnPretreatmentRuleManger.getListByCondition(condition.toString());
		PtnPretreatmentRule ptnPretreatmentRule = null;
		String tempPreDealRelation = "";
		if(result != null && !result.isEmpty()){
			if("".equals(id)){/*新增*/
				if("101032110".equals(preDealRelation)){/*新增为不涉及*/
					status = "2";
					text = "该组合已经配置为其他规则，不能新增为\"不涉及\"";
					validateMap.put("status", status);
					validateMap.put("text", text);
					return validateMap;
				}
				for(int i = 0; i < result.size(); i++){
					ptnPretreatmentRule = (PtnPretreatmentRule)result.get(i);
					tempPreDealRelation = StaticMethod.nullObject2String(ptnPretreatmentRule.getPreDealRelation());
					if("101032110".equals(tempPreDealRelation)){
						status = "2";
						text = "该组合已配置为\"不涉及\"，不能再配置为其他规则。";
						break;
					}else if(!"".equals(preDealRelation) && preDealRelation.equals(tempPreDealRelation)){
						status = "2";
						text = "该组合规则已存在，请配置其他组合规则。";
						break;
					}
				}
			}else{/*编辑*/
				if("101032110".equals(preDealRelation) && result.size() != 1){/*编辑为不涉及*/
					status = "2";
					text = "该组合已经配置为其他规则，不能修改为\"不涉及\"";
					validateMap.put("status", status);
					validateMap.put("text", text);
					return validateMap;
				}
				for(int i = 0; i < result.size(); i++){
					ptnPretreatmentRule = (PtnPretreatmentRule)result.get(i);
					tempPreDealRelation = StaticMethod.nullObject2String(ptnPretreatmentRule.getPreDealRelation());
					String tempId = StaticMethod.nullObject2String(ptnPretreatmentRule.getId());
					if("101032110".equals(tempPreDealRelation) && !id.equals(tempId)){
						status = "2";
						text = "改组合已配置为\"不涉及\"，不能再编辑为其他规则。";
						break;
					}else if(!"".equals(preDealRelation) && preDealRelation.equals(tempPreDealRelation)){
						status = "2";
						text = "改组合规则已存在，请编辑为其他组合规则。";
						break;
					}
				}
			}
		}
		validateMap.put("status", status);
		validateMap.put("text", text);
		return validateMap;
	}
	
	/**
	 * 导入新增
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward importSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String attachment = StaticMethod.nullObject2String(request.getParameter("attachment"));
		ITawCommonsAccessoriesManager tawCommonsAccessoriesManager = (ITawCommonsAccessoriesManager)
			ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
		IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) 
			ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
		String basePath = StaticMethod.nullObject2String(tawCommonsAccessoriesManager.getFilePath("commonfault"));
		List tawCommonsAccessoriesList = tawCommonsAccessoriesManager.getAllFileById(attachment);
		TawCommonsAccessories tawCommonsAccessories = null;
		Map factoryMap = this.getDictNameMapByParentDictId("1010322");
		Map faultDealDescMap = this.getDictNameMapByParentDictId("1010323");
		Map faultReasonSort1Map = this.getDictNameMapByParentDictId("1010303");
		Map faultReasonSort2Map = new HashMap();
		Iterator iterator = faultReasonSort1Map.entrySet().iterator();
		Entry entry = null;
		while(iterator.hasNext()){
			entry = (Entry)iterator.next();
			String value = (String)entry.getValue();
			faultReasonSort2Map.put(value, this.getDictNameMapByParentDictId(value));
		}
		Map faultReasonSort3Map = new HashMap();
		iterator = faultReasonSort2Map.entrySet().iterator();
		while(iterator.hasNext()){
			entry = (Entry)iterator.next();
			Map temMap = (Map)entry.getValue();
			Iterator temIterator = temMap.entrySet().iterator();
			while(temIterator.hasNext()){
				entry = (Entry)temIterator.next();
				String value = (String)entry.getValue();
				faultReasonSort3Map.put(value, this.getDictNameMapByParentDictId(value));
			}
		}
		Map preDealRelationMap = this.getDictNameMapByParentDictId("1010321");
		PtnPretreatmentRule ptnPretreatmentRule = null;
		Set dbSet = new HashSet();
		List query = ptnPretreatmentRuleManger.getListByCondition("");
		for(int n = 0; query != null && n < query.size(); n++){
			ptnPretreatmentRule = (PtnPretreatmentRule)query.get(n);
			String alarmId = StaticMethod.nullObject2String(ptnPretreatmentRule.getAlatmID());
			String faultDealDesc = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultDealDesc());
			String faultReasonSort1 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort1());
			String faultReasonSort2 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort2());
			String faultReasonSort3 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort3());
			String preDealRelation = StaticMethod.nullObject2String(ptnPretreatmentRule.getPreDealRelation());
			dbSet.add(alarmId + faultDealDesc + faultReasonSort1 + faultReasonSort2 + faultReasonSort3 + preDealRelation);
		}
		String accessoriesName= "";
		List persistentList = new ArrayList();
		String factory = "";
		String equipmentType = "";
		String alarmName = "";
		String alatmID = "";
		String faultDealDesc = "";
		String faultReasonSort1 = "";
		String faultReasonSort2 = "";
		String faultReasonSort3 = "";
		String preDealRelation = ""; 
		for(int i = 0; i < tawCommonsAccessoriesList.size(); i++){
			tawCommonsAccessories = (TawCommonsAccessories)tawCommonsAccessoriesList.get(i);
			accessoriesName = StaticMethod.nullObject2String(tawCommonsAccessories.getAccessoriesName());
			PoiExcelImpl pe = new PoiExcelImpl();
			PtnPretreatmentRuleIPoiWork spw = new PtnPretreatmentRuleIPoiWork();
			List list = pe.importExcel(spw, basePath + accessoriesName);
			if(list != null && !list.isEmpty()){
				for(int j = 0; j < list.size(); j++){
					ptnPretreatmentRule = (PtnPretreatmentRule)list.get(j);
					factory = StaticMethod.nullObject2String(ptnPretreatmentRule.getFactory()).trim();
					factory = StaticMethod.nullObject2String(factoryMap.get(factory));
					equipmentType = StaticMethod.nullObject2String(ptnPretreatmentRule.getEquipmentType()).trim();
					alarmName = StaticMethod.nullObject2String(ptnPretreatmentRule.getAlarmName()).trim();
					alatmID = StaticMethod.nullObject2String(ptnPretreatmentRule.getAlatmID()).trim();
					faultDealDesc = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultDealDesc()).trim();
					faultDealDesc = StaticMethod.nullObject2String(faultDealDescMap.get(faultDealDesc));
					faultReasonSort1 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort1()).trim();
					faultReasonSort1 = StaticMethod.nullObject2String(faultReasonSort1Map.get(faultReasonSort1));
					Map temp = (Map)faultReasonSort2Map.get(faultReasonSort1);
					faultReasonSort2 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort2()).trim();
					faultReasonSort2 = StaticMethod.nullObject2String(temp.get(faultReasonSort2));
					temp = (Map)faultReasonSort3Map.get(faultReasonSort2);
					faultReasonSort3 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort3()).trim();
					faultReasonSort3 = StaticMethod.nullObject2String(temp.get(faultReasonSort3));
					preDealRelation = StaticMethod.nullObject2String(ptnPretreatmentRule.getPreDealRelation()).trim();
					preDealRelation = StaticMethod.nullObject2String(preDealRelationMap.get(preDealRelation));
					String key = alatmID + faultDealDesc + faultReasonSort1 + faultReasonSort2 + faultReasonSort3 + preDealRelation;
					/*过滤重复数据*/
					if(!dbSet.contains(key)){
						Map dataMap = new HashMap();
						dataMap.put("id", UUIDHexGenerator.getInstance().getID());
						dataMap.put("factory", factory);
						dataMap.put("equipmentType", equipmentType);
						dataMap.put("alarmName", alarmName);
						dataMap.put("alatmID", alatmID);
						dataMap.put("faultDealDesc", faultDealDesc);
						dataMap.put("faultReasonSort1", faultReasonSort1);
						dataMap.put("faultReasonSort2", faultReasonSort2);
						dataMap.put("faultReasonSort3", faultReasonSort3);
						dataMap.put("preDealRelation", preDealRelation);
						persistentList.add(dataMap);
					}
					dbSet.add(key);
				}
			}	
		}
		/*批量更新到database*/
		IDownLoadSheetAccessoriesService jdbcService = (IDownLoadSheetAccessoriesService)
			ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//		jdbcService.batchExcuteSql(persistentList, new PtnPretreatmentRule(), "insert");
		return mapping.findForward("success");
	}
	
	/**
	 * 下载模板
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String realPath = request.getSession().getServletContext().getRealPath("") 
			+ "WEB-INF/classes/com/boco/eoms/sheet/ptnpretreatmentrule/util/template.xls";
		System.out.println("---------" + realPath);
		InputStream input = null;
		OutputStream out = null;
		try{
			File file = new File(realPath);
			if(file != null){
				String fileName = "传输自动归档模板.xls";
				input = new FileInputStream(file);
				response.reset();
				response.setContentType("application/x-msdownload;charset=GBK");
				response.setCharacterEncoding("GB2312");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + new String(fileName.getBytes("gbk"),"iso8859-1"));
				byte[] b = new byte[1024];
				int len = 0;
				out = response.getOutputStream();
				while ((len = input.read(b)) > 0) {
					out.write(b, 0, len);
				}
				out.flush();
			}else{
				BocoLog.info(PtnPretreatmentRuleAction.class,"传输自动归档模板不存在。");
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
		}finally{
			if(input != null){
				input.close();
			}
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 根据告警Id和处理措施获取归因一到三
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFaultReason(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String alarmID = StaticMethod.nullObject2String(request.getParameter("alarmID"));
		String faultDealDesc = StaticMethod.nullObject2String(request.getParameter("faultDealDesc"));
		IPtnPretreatmentRuleManger ptnPretreatmentRuleManger = (IPtnPretreatmentRuleManger) 
			ApplicationContextHolder.getInstance().getBean("iPtnPretreatmentRuleManger");
		TawSystemDictTypeDaoHibernate dictDao = (TawSystemDictTypeDaoHibernate)
		ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeDao");
		TawSystemDictType tawSystemDictType = dictDao.getDictByDictName(faultDealDesc, "1010323");
		if(tawSystemDictType != null){
			faultDealDesc = StaticMethod.nullObject2String(tawSystemDictType.getDictId());
		}
		String condition = " where alatmID='" + alarmID + "' and faultDealDesc='" + faultDealDesc + "'";
		List result = ptnPretreatmentRuleManger.getListByCondition(condition);
		JSONObject object = new JSONObject();
		String faultReasonSort1 = "";
		String faultReasonSort2 = "";
		String faultReasonSort3 = "";
		if(result != null && !result.isEmpty()){
			PtnPretreatmentRule ptnPretreatmentRule = (PtnPretreatmentRule)result.get(0);
			faultReasonSort1 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort1());
			faultReasonSort2 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort2());
			faultReasonSort3 = StaticMethod.nullObject2String(ptnPretreatmentRule.getFaultReasonSort3());
			object.put("faultReasonSort1", faultReasonSort1);
			object.put("faultReasonSort2", faultReasonSort2);
			object.put("faultReasonSort3", faultReasonSort3);
			List subDict = dictDao.getDictSonsByDictid("1010303");
			JSONArray dictId1Array = new JSONArray();
			JSONArray dictName1Array = new JSONArray();
			for(int i = 0; subDict != null && i < subDict.size(); i++){
				tawSystemDictType = (TawSystemDictType)subDict.get(i);
				dictId1Array.put(StaticMethod.nullObject2String(tawSystemDictType.getDictId()));
				dictName1Array.put(StaticMethod.nullObject2String(tawSystemDictType.getDictName()));
			}
			object.put("sort1DictId", dictId1Array);
			object.put("sort1DictName", dictName1Array);
			subDict = dictDao.getDictSonsByDictid(faultReasonSort1);
			JSONArray dictId2Array = new JSONArray();
			JSONArray dictName2Array = new JSONArray();
			for(int i = 0; subDict != null && i < subDict.size(); i++){
				tawSystemDictType = (TawSystemDictType)subDict.get(i);
				dictId2Array.put(StaticMethod.nullObject2String(tawSystemDictType.getDictId()));
				dictName2Array.put(StaticMethod.nullObject2String(tawSystemDictType.getDictName()));
			}
			object.put("sort2DictId", dictId2Array);
			object.put("sort2DictName", dictName2Array);
			subDict = dictDao.getDictSonsByDictid(faultReasonSort2);
			JSONArray dictId3Array = new JSONArray();
			JSONArray dictName3Array = new JSONArray();
			for(int i = 0; subDict != null && i < subDict.size(); i++){
				tawSystemDictType = (TawSystemDictType)subDict.get(i);
				dictId3Array.put(StaticMethod.nullObject2String(tawSystemDictType.getDictId()));
				dictName3Array.put(StaticMethod.nullObject2String(tawSystemDictType.getDictName()));
			}
			object.put("sort3DictId", dictId3Array);
			object.put("sort3DictName", dictName3Array);
		}
		JSONUtil.print(response, object.toString());
	}

	/**
	 * 根据parentdictid获取下级字典值;key=dictName,value=dictid
	 * @param parnetDictId
	 * @return
	 */
	public Map getDictNameMapByParentDictId(String parnetDictId){
		Map dictNameMap = new HashMap();
		TawSystemDictTypeDaoHibernate dictDao = (TawSystemDictTypeDaoHibernate)
			ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeDao");
		List queryList = dictDao.getDictSonsByDictid(parnetDictId);
		TawSystemDictType tawSystemDictType = null;
		for(int i = 0; queryList != null && !queryList.isEmpty() && i < queryList.size(); i++){
			tawSystemDictType = (TawSystemDictType)queryList.get(i);
			dictNameMap.put(StaticMethod.nullObject2String(tawSystemDictType.getDictName()).trim(), tawSystemDictType.getDictId().trim());
		}
		return dictNameMap;
	}
	
}
