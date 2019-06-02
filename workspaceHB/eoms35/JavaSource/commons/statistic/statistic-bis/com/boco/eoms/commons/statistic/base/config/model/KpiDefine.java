/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.boco.eoms.commons.statistic.base.reference.StaticMethod;



/**
 * @author liuxy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KpiDefine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2286128158108598014L;
	
	private String kpiCaption;
	private String title;
	private String name;
	private String useJspHead;
	private int viewRowSpanMax;
	private String statDetailVo;
	
	private boolean enableSummarySpan = true;
	private SummaryDefine[] summaryDefines;
	private QueryDefine[] queryDefines;
	private ConditionParam[] conditionParams;
	
	private ResultSort[] resultSorts = null;//对结果集排序
	
	//动态行的动态条件
	private DynamicRowParam[] dynamicRowParams = null;
	
	/**
	 * 是否把group by中
	 */
	private boolean autoAppendGroupby = true;
	
	public QueryDefine getQueryDefineByName(String name)
		throws Exception {
		QueryDefine queryDefine = null;
			boolean isDefined = false;
			for (int i = 0; i < queryDefines.length; i++) {
				if (queryDefines[i].getName().equals(name)) {
					if (isDefined == true) {
						throw new Exception("重复定义!");
					}
					isDefined = true;
					queryDefine = queryDefines[i];
				}
			}
			if (isDefined == false) {
				throw new Exception("没有定义!");
			}

			return queryDefine;
	}
	/**
	 * 根据summaryId得到SummaryDefine, 如果没有找到id返回null
	 * @param summaryID
	 * @return
	 */
	public SummaryDefine getSummaryDefineByID(String summaryID) throws Exception {
		if (summaryDefines != null) {

			for (int i = 0; i < summaryDefines.length; i++) {
				SummaryDefine define = summaryDefines[i];
				if (define.getId().equals(summaryID)) {
					return define;
				}
			}
			
			throw new Exception("\n summaryID is " + summaryID + " 没找到相对应的summaryID，请查看summaryID配置是否正确");
		}
		
		return null;
	}
	
	/**
	 * 获取kpiDefine中所有的queryDefine中定义的所有的fieldDefine
	 * 并且根据fieldDefine的sequence属性排序
	 * @return List <FieldDefine>
	 * @throws Exception
	 */
	public List getAllFieldDefines() {
		List fieldDefines = new ArrayList();
		for (int iQueryDef = 0; iQueryDef < getQueryDefines().length; iQueryDef++) {
			for (int iFieldDef = 0; iFieldDef < getQueryDefines()[iQueryDef]
					.getFieldDefines().length; iFieldDef++) {

				FieldDefine fieldDefine = getQueryDefines()[iQueryDef]
						.getFieldDefines()[iFieldDef];
				fieldDefines.add(fieldDefine);
			}
		}

		Collections.sort(fieldDefines, new Comparator() {
			public int compare(Object o1, Object o2) {

				int i1 = ((FieldDefine) o1).getSequence();
				int i2 = ((FieldDefine) o2).getSequence();
				return i1 - i2;
			}
		});

		return fieldDefines;
	}

	public FieldDefine getFieldDefineByID(String fieldId)  {
		for (int iQueryDef = 0; iQueryDef < getQueryDefines().length; iQueryDef++) {
			for (int iFieldDef = 0; iFieldDef < getQueryDefines()[iQueryDef]
					.getFieldDefines().length; iFieldDef++) {

				FieldDefine fieldDefine = getQueryDefines()[iQueryDef]
						.getFieldDefines()[iFieldDef];
				if(fieldDefine.getId().equals(fieldId))return fieldDefine;
			}
		}

		return null;
	}
	public QueryDefine getQueryDefineByFieldID(String fieldId)  {
		for (int iQueryDef = 0; iQueryDef < getQueryDefines().length; iQueryDef++) {
			for (int iFieldDef = 0; iFieldDef < getQueryDefines()[iQueryDef]
					.getFieldDefines().length; iFieldDef++) {

				FieldDefine fieldDefine = getQueryDefines()[iQueryDef]
						.getFieldDefines()[iFieldDef];
				if(fieldDefine.getId().equals(fieldId))return getQueryDefines()[iQueryDef];
			}
		}

		return null;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the queryDefines.
	 */
	public QueryDefine[] getQueryDefines() {
		return queryDefines;
	}
	/**
	 * @param queryDefines The queryDefines to set.
	 */
	public void setQueryDefines(QueryDefine[] queryDefines) {
		this.queryDefines = queryDefines;
	}


	/**
	 * @return Returns the useJspHead.
	 */
	public String getUseJspHead() {
		return useJspHead;
	}
	/**
	 * @param useJspHead The useJspHead to set.
	 */
	public void setUseJspHead(String useJspHead) {
		this.useJspHead = useJspHead;
	}
	/**
	 * @return Returns the summaryDefines.
	 */
	public SummaryDefine[] getSummaryDefines() {
		return summaryDefines;
	}
	/**
	 * @param summaryDefines The summaryDefines to set.
	 */
	public void setSummaryDefines(SummaryDefine[] summaryDefines) {
		this.summaryDefines = summaryDefines;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return StaticMethod.null2String(title).equals("")? "统计报表" : title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the conditionParams.
	 */
	public ConditionParam[] getConditionParams() {
		return conditionParams;
	}
	/**
	 * @param conditionParams The conditionParams to set.
	 */
	public void setConditionParams(ConditionParam[] conditionParams) {
		this.conditionParams = conditionParams;
	}
	
	public void setConditionParamsForList(List list) {
		ConditionParam[] tmp = new ConditionParam[list.size()];
		for(int f=0;f<list.size();f++)
		{
			tmp[f] = (ConditionParam)list.get(f);
		}
		
		this.conditionParams = tmp;
	}
	
	public List addCp(ConditionParam cp) {
		List list = null;
		if(conditionParams != null)
		{
			list = new ArrayList();
			for(int i=0;i<conditionParams.length;i++)
			{
				list.add(conditionParams[i]);
			}
			list.add(cp);
		}
		
		setConditionParamsForList(list);
		return list;
	}
	
	public ConditionParam getConditionParamByPageName(String pageName){
		ConditionParam result = null;
		for (int i = 0; i < conditionParams.length; i++) {
			result = conditionParams[i];
			if(pageName.equals(result.getPageName())){
				return result;
			}
		}
			
		return result;
	}
	/**
	 * @return Returns the kpiCaption.
	 */
	public String getKpiCaption() {
		return kpiCaption;
	}
	/**
	 * @param kpiCaption The kpiCaption to set.
	 */
	public void setKpiCaption(String kpiCaption) {
		this.kpiCaption = kpiCaption;
	}
	/**
	 * @return Returns the viewRowSpanMax.
	 */
	public int getViewRowSpanMax() {
		return viewRowSpanMax;
	}
	/**
	 * @param viewRowSpanMax The viewRowSpanMax to set.
	 */
	public void setViewRowSpanMax(int viewRowSpanMax) {
		this.viewRowSpanMax = viewRowSpanMax;
	}

	public boolean isEnableSummarySpan() {
		return enableSummarySpan;
	}

	public void setEnableSummarySpan(boolean enableSummarySpan) {
		this.enableSummarySpan = enableSummarySpan;
	}
	public String getStatDetailVo() {
		return statDetailVo;
	}
	public void setStatDetailVo(String statDetailVo) {
		this.statDetailVo = statDetailVo;
	}
	public boolean isAutoAppendGroupby() {
		return autoAppendGroupby;
	}
	public void setAutoAppendGroupby(boolean autoAppendGroupby) {
		this.autoAppendGroupby = autoAppendGroupby;
	}
	public ResultSort[] getResultSorts() {
		return resultSorts;
	}
	public void setResultSorts(ResultSort[] resultSorts) {
		this.resultSorts = resultSorts;
	}
	public DynamicRowParam[] getDynamicRowParams() {
		return dynamicRowParams;
	}
	public void setDynamicRowParams(DynamicRowParam[] dynamicRowParams) {
		this.dynamicRowParams = dynamicRowParams;
	}
	public DynamicRowParam getDynamicRowParams(int i) {
		if(dynamicRowParams != null)
		{
			return dynamicRowParams[i];
		}
		else
		{
			return null;
		}
	}
}
