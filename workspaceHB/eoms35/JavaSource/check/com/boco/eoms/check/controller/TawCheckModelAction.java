package com.boco.eoms.check.controller;


import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.model.*;
import com.boco.eoms.check.qo.*;
import com.boco.eoms.check.util.TawCheckSCOREMethod;
import org.apache.struts.action.*;

import javax.servlet.http.*;
import javax.servlet.*;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

import org.apache.struts.util.*;

/**
 * @see
 * <p>
 * �����������ڲ��뿼��ָ�����ݿ���̨
 * </p>
 * @author ��Ң
 * @version 3.5
 */
public class TawCheckModelAction extends Action {
	private static int PAGE_LENGTH = 10;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();
		if (isCancelled(request)) {
			return mapping.findForward("cancel");
		}
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
	    if(!TawCheckSCOREMethod.checkUserRoles(saveSessionBeanForm.getUserid())&&!("REPORT".equalsIgnoreCase(myaction)||"REPORTDO".equalsIgnoreCase(myaction)||"SEARCH".equalsIgnoreCase(myaction)||"SEARCHDO".equalsIgnoreCase(myaction)||"ATTATCHFILE".equalsIgnoreCase(myaction))){
	    	return mapping.findForward("nopriv");
	    }
		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("NEW".equalsIgnoreCase(myaction)) {
			myforward = performNew(mapping, form, request, response);
		} else if ("DEL".equalsIgnoreCase(myaction)) {
			myforward = performDel(mapping, form, request, response);
		} else if ("QUERY".equalsIgnoreCase(myaction)) {
			myforward = performQuery(mapping, form, request, response);
		} else if ("QUERYDO".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("TRASH".equalsIgnoreCase(myaction)) {
			myforward = performTrash(mapping, form, request, response);
		} else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} else if ("NEXT".equalsIgnoreCase(myaction)) {
			myforward = performNext(mapping, form, request, response);
		} else if ("REPORT".equalsIgnoreCase(myaction)) {
			myforward = performReport(mapping, form, request, response);
		} else if ("SEARCH".equalsIgnoreCase(myaction)) {
			myforward = performReport(mapping, form, request, response);
		} else if ("REPORTDO".equalsIgnoreCase(myaction)) {
			myforward = performReportDo(mapping, form, request, response);
		}else if ("ATTATCHLOAD".equalsIgnoreCase(myaction)) {
			myforward = performAttachload(mapping, form, request, response);
		}else if ("ATTATCHLOADDO".equalsIgnoreCase(myaction)) {
			myforward = performAttachloadDo(mapping, form, request, response);
		}else if ("ATTATCHFILE".equalsIgnoreCase(myaction)) {
			myforward = performAttachFile(mapping, form, request, response);
		}else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}

	/*
	 * ����һ��ָ�������Ϣ
	 */
	public ActionForward performNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ��ǰ�û���session�е���Ϣ
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		String userId = saveSessionBeanForm.getUserid(); // ��ǰ�û���
		request.setAttribute("USERID", userId);
		return mapping.findForward("success");
	}

	/*
	 * �����ָ����Ϣ
	 */
	public ActionForward performSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			TawCheckModelBO tawCheckModelBO = new TawCheckModelBO();
			tawCheckModelBO.setModelForm(((DynaActionForm) form).getMap());
			tawCheckModelBO.SaveModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * �༭��ָ����Ϣ
	 */
	public ActionForward performEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String targer_id = request.getParameter("id");
			TawCheckTargerBO tawCheckTargerBO = new TawCheckTargerBO();
			TawCheckTargerVO tawCheckTargerVO = tawCheckTargerBO
					.getTargerVO(targer_id);
			request.setAttribute("TawCheckTargerVO", tawCheckTargerVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * ���¸�ָ��
	 */
	public ActionForward performTrash(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String targer_id = request.getParameter("targer_id");
			String targer_per = request.getParameter("targer_per");
			Map map = ((DynaActionForm) actionForm).getMap();
			map.put("targer_id", targer_id);
			map.put("targer_deleted", "0");
			map.put("targer_per", targer_per);
			TawCheckTargerBO tawCheckTargerBO = new TawCheckTargerBO();
			tawCheckTargerBO.setTargerForm(map);
			tawCheckTargerBO.updateTarger();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * ��ѯָ��
	 */
	public ActionForward performQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("success");

	}

	/*
	 * ����ָ���б�
	 */
	public ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int length = PAGE_LENGTH;
			int offset = StaticMethod.nullObject2int(request
					.getParameter("pager.offset"), 0);
			int size = StaticMethod.nullObject2int(request
					.getParameter("pager.size"), 0);
			int[] pagePra = { offset, length, size };
			ArrayList modelList = new ArrayList();
			TawCheckModelBO tawCheckModelBO = new TawCheckModelBO();
			tawCheckModelBO
					.setModelForm(((DynaActionForm) actionForm).getMap());
			modelList = (ArrayList) tawCheckModelBO.getList(pagePra);
			request.setAttribute("modelList", modelList);
			String url = "/check" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2],
					pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * ɾ���ָ��
	 */
	public ActionForward performDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			TawCheckModelBO tawCheckModelBO = new TawCheckModelBO();
			tawCheckModelBO.delTawCheckModel(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	// ��ʾ����
	public ActionForward performView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/*
	 * ���ڴ��б���ģ����Ϣ
	 */
	public ActionForward performNext(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Map nMap = ((DynaActionForm) form).getMap();
			TawCheckModel tawCheckModel = new TawCheckModel();
			com.boco.eoms.common.util.MyBeanUtils.populate(tawCheckModel, nMap);
			request.setAttribute("modelName", tawCheckModel.getModelName());
			request.setAttribute("modelPerson", tawCheckModel.getModelPerson());
			request.setAttribute("modelType", tawCheckModel.getModelType());
			request.setAttribute("modelRemark", tawCheckModel.getModelRemark());
			request.setAttribute("modelDeleted", tawCheckModel
					.getModelDeleted());
			request.setAttribute("targerModelType", String
					.valueOf(tawCheckModel.getTargerModelType()));

			ArrayList tarList = new ArrayList();
			TawCheckTargerBO tawTargerBO = new TawCheckTargerBO();
			tarList = (ArrayList) tawTargerBO.getList(tawCheckModel
					.getTargerModelType(), Integer.parseInt(tawCheckModel
					.getModelType()));
			request.setAttribute("tarList", tarList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * �ṩ�����ѯѡ���������
	 */
	public ActionForward performReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strLocal = StaticMethod.getLocalString();
		String sYear = strLocal.substring(0, 4);
		int iMonth = StaticMethod.null2int(strLocal.substring(5, 7));
		request.setAttribute("sYear", sYear);
		request.setAttribute("sMonth", String.valueOf(iMonth));
		return mapping.findForward("success");

	}

	/**
	 * �ṩ�����ѯ���
	 */
	public ActionForward performReportDo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String sMonth = "";
		TawCheckModelBO tawCheckModelBO = new TawCheckModelBO();
		int iYear = StaticMethod.null2int(request.getParameter("scoreYear"));
		int iMonth = StaticMethod.null2int(request.getParameter("scoreMonth"));
		String id = StaticMethod.null2String(request.getParameter("modelType"));
		int eMonth=StaticMethod.null2int(request.getParameter("eMonth"));
		String pingjun=StaticMethod.null2String(request.getParameter("pingjun"));
		String regoin="";
		String regoinList[]=request.getParameterValues("regoin");
		if(regoinList==null){
		    regoin=StaticMethod.null2String(request.getParameter("regoin"));
		}
		else{    
			for(int i=0;i<regoinList.length;i++){
				if(regoin.length()==0){
					regoin="'"+regoinList[i]+"'";
				}else{
					regoin+=",'"+regoinList[i]+"'";
				}
			}
		}
		if (iMonth < 10) {
			sMonth = "0" + iMonth;
		} else {
			sMonth = String.valueOf(iMonth);
		}

		TawCheckModel tawCheckModel = tawCheckModelBO.getTawCheckModel(id);
		String modelTargerName = tawCheckModel.getModelTargerName();
		String modelRemark = StaticMethod.null2String(tawCheckModel
				.getModelRemark());
		String targetModelType = String.valueOf(tawCheckModel
				.getTargerModelType());
		int targerNum = TawCheckSCOREMethod.getStrNum(modelTargerName);
		request.setAttribute("SDATE", String.valueOf(iYear) + "-" + sMonth);
		request.setAttribute("modelName", tawCheckModel.getModelName());
		request.setAttribute("TABLENAME", tawCheckModel.getTableName());
		request.setAttribute("MODELTARGER", tawCheckModel.getModelTarger()
				.toLowerCase());
		request.setAttribute("TARGETMODELTYPE", targetModelType);
		request.setAttribute("MODELTARGERNAME", modelTargerName);
		request.setAttribute("MODELREMARK", modelRemark);
		request.setAttribute("TARGERNUM", String.valueOf(targerNum));
		request.setAttribute("REGOIN", regoin);
		request.setAttribute("pingjun", pingjun);
		TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
		// by changhongxiang 2007-7-27 ���ܱ������
		if (tawCheckModel.getTableName().equalsIgnoreCase("TawCheckSumSCORE")
				|| tawCheckModel.getTableName().equalsIgnoreCase(
						"TawCheckSumYunweiSCORE")) {
			calculateSum(tawCheckModel, iYear, sMonth);
		}
		// by changhongxiang 2007-7-27 ���ܱ������
		// ���HSQL
		String hsql="";
		if(regoin==null||regoin.length()==0){
		hsql = "from " + tawCheckModel.getTableName()
				+ " tableName WHERE tableName.score_year='" + iYear
				+ "' and tableName.score_month='" + sMonth + "'";
		}else{
			hsql = "from " + tawCheckModel.getTableName()
			+ " tableName WHERE tableName.score_year='" + iYear
			+ "' and to_number(tableName.score_month)>=" + iMonth + " and to_number(tableName.score_month)<="+eMonth
			+" and tableName.score_area_name in ("+regoin.toString()+")";
		}
		if (tawCheckModel.getTableName().equals("TawCheckYunweiSCORE")
				|| (tawCheckModel.getModelAliasName() != null && tawCheckModel
						.getModelAliasName().equals("�Ӽ��"))) {
			hsql += " and modelflag='" + tawCheckModel.getModelAliasName()
					+ "' ";
		}
		hsql+=" order by to_number(score_area_id),score_month";
		ArrayList list = new ArrayList();
		list = (ArrayList) tawCheckSchedulerDAO.getList(hsql);
		List list1=new ArrayList(0);
		Map map=new HashMap();
		if(pingjun!=null&&!pingjun.equals("")){
			String names[]=modelTargerName.split(",");
			for(int i=0;i<list.size();i++){
				Object obj=list.get(i);
				Object obj1=null;
				String area_name=getColStringValue(obj,"score_area_name");
				if(map.get(area_name)==null){
//					map.put(area_name, 1);
					list1.add(obj);
					
				}else{
					Integer num=(Integer)map.get(area_name);
//					num=num+1;
					map.put(area_name, num);
					for(int j=0;j<list1.size();j++){
						obj1=list1.get(j);
						if(getColStringValue(obj1,"score_area_name").equals(area_name)){
							break;
						}
					}
				if (obj1 != null) {
						for (int j = 0; j < names.length; j++) {
							double value1 = getColValue(obj, names[j]);
							double value2 = getColValue(obj1, names[j]);
							setColValue(obj1,names[j],String.valueOf(value1+value2));
						}
					}
				}
			}
		}
		if(regoin==null||regoin.length()==0){
		calculateSortNumber(list);
		}
		request.setAttribute("LIST", list);
		return mapping.findForward("success");

	}

	public double getColValue(Object scoreObject, String targetName) {
		String retname = "0";
		try {
			Method method = null;
			if (targetName.equals("FULL_SCORE")) {
				method = scoreObject.getClass().getMethod("getFull_score",
						new Class[] {});//
			} else {
				method = scoreObject.getClass().getMethod("get" + targetName,
						new Class[] {});//
			}
			Object result = method.invoke(scoreObject, new Object[0]);
			retname = result.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Double.parseDouble(retname);
	}
	public String getColStringValue(Object scoreObject, String targetName) {
		String retname = "0";
		try {
			Method method = null;
				method = scoreObject.getClass().getMethod("get" + targetName,
						new Class[] {});//
			Object result = method.invoke(scoreObject, new Object[0]);
			retname = result.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retname;
	}
	public void setColValue(Object tawCheckWanggSCORE, String colName,
			String colValue) {
		try {
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = double.class;
			Method method = null;
			if (colName.equals("FULL_SCORE")) {
				method = tawCheckWanggSCORE.getClass().getMethod(
						"setFull_score", parameterTypes);//
			} else {
				method = tawCheckWanggSCORE.getClass().getMethod(
						"set" + colName, parameterTypes);//
			}
			Object[] args = new Object[1];
//			args[0] = Double.parseDouble(colValue);
			method.invoke(tawCheckWanggSCORE, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
/**
 * ���ܱ����:���tawchecksumdefine�еĶ��������ܱ�
 * @param tawCheckModel
 * @param iYear
 * @param sMonth
 */
	private void calculateSum(TawCheckModel tawCheckModel, int iYear,
			String sMonth) {
		TawCheckSchedulerDAO tawCheckSchedulerDAO = new TawCheckSchedulerDAO();
		TawCheckDataBatchBO tawCheckBatchBO = new TawCheckDataBatchBO("", "","");
		String typeId = "1";
		TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
		if (tawCheckModel.getTableName().equalsIgnoreCase(//��ά�Ļ��ܱ�
				"TawCheckSumYunweiSCORE")) {
			tawCheckDataDAO.delScoreData(String.valueOf(iYear), sMonth,
					TawCheckSumYunweiSCORE.class.getSimpleName(), "");
			typeId = "2";
		} else {//������ĵĻ��ܱ�
			tawCheckDataDAO.delScoreData(String.valueOf(iYear), sMonth,
					TawCheckSumSCORE.class.getSimpleName(), "");
		}
		List sumDefineList = tawCheckBatchBO.getSumdefines(typeId);
		String areaNames[] = TawCheckSCOREMethod.getAreaNams();//ȡ�����������
		for (int i = 0; i < areaNames.length; i++) {
			if(typeId.equals("2")&&i==areaNames.length-1){//��ά���Ļ��ܱ�û�С�ȫʡ��
				break;
			}
			TawCheckSumSCORE tawCheckSumSCORE = new TawCheckSumSCORE();
			TawCheckSumYunweiSCORE tawCheckSumYunweiSCORE = new TawCheckSumYunweiSCORE();
			if(typeId.equals("1")){
				tawCheckSumSCORE.setScore_area_name(areaNames[i]);
				tawCheckSumSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(areaNames[i]));
				tawCheckSumSCORE.setScore_year(String.valueOf(iYear));
				tawCheckSumSCORE.setScore_month(sMonth);
			}else{
				tawCheckSumYunweiSCORE.setScore_area_name(areaNames[i]);
				tawCheckSumYunweiSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(areaNames[i]));
				tawCheckSumYunweiSCORE.setScore_year(String.valueOf(iYear));
				tawCheckSumYunweiSCORE.setScore_month(sMonth);
			}
			for (int k = 0; k < sumDefineList.size(); k++) {//��ݶ���ȡ��ر����ֶε�
				TawCheckSumDefine define = (TawCheckSumDefine) sumDefineList
						.get(k);
				// �ֽ���tablename����:��:TawCheckTransSCORE-T42,T43/TawCheckDATASCORE-T41,T42
				String tableNames[] = define.getTableName().split("/");
				double colValue = 0;
				for (int l = 0; l < tableNames.length; l++) {
					String tempStr[] = tableNames[l].split("-");
					String tableName = tempStr[0];
					String colNames[] = tempStr[1].split(",");
					String sql = "from " + tableName
							+ " tableName where tableName.score_year='" + iYear
							+ "' and tableName.score_month='" + sMonth
							+ "' and tableName.score_area_name='"
							+ areaNames[i] + "'";
					if (define.getModelflag() != null
							&& !define.getModelflag().equals("")) {
						sql = sql + " and tableName.modelflag='"
								+ define.getModelflag() + "'";
					}
					List list1 = (ArrayList) tawCheckSchedulerDAO.getList(sql);

					if (list1.size() > 0) {
						for (int m = 0; m < colNames.length; m++) {
							colValue += getColValue(list1.get(0), colNames[m]);
						}
					}
				}
				// ��Ȩ����
				DecimalFormat f = new DecimalFormat("#.00");

				double scaleValue = colValue * define.getScale();
				colValue = Double.parseDouble(f.format(scaleValue));
				double fullScore = 0;
				if(typeId.equals("1")){
					setColValue(tawCheckSumSCORE, define.getColName(), String
							.valueOf(colValue));
					fullScore=getColValue(tawCheckSumSCORE, "FULL_SCORE");
				}else{
					setColValue(tawCheckSumYunweiSCORE, define.getColName(), String
							.valueOf(colValue));
					fullScore=getColValue(tawCheckSumYunweiSCORE, "FULL_SCORE");
				}
				fullScore = Double.parseDouble(f.format(colValue + fullScore));
				if(typeId.equals("1")){
					setColValue(tawCheckSumSCORE, "FULL_SCORE", String
							.valueOf(fullScore));
				}else{
					setColValue(tawCheckSumYunweiSCORE, "FULL_SCORE", String
							.valueOf(fullScore));
				}
			}
			TawCheckDataBatchBO bo = new TawCheckDataBatchBO("", "","");
			if(typeId.equals("1")){
				bo.save(tawCheckSumSCORE);
			}else{
				bo.save(tawCheckSumYunweiSCORE);
			}
		}
	}
	/**
	 * �����������ܷ�,ǰ�������еĴ�ֱ��Ѿ�������ܷ�,by changhongxiang
	 * @param list
	 */
	public void calculateSortNumber(List list){
		List fullScoreList=new ArrayList(0);
		List sortList=new ArrayList(0);
		int size=list.size();
		if(size>11) size=11;
		for(int i=0;i<size;i++){
			Object obj=list.get(i);
			double fullScore=getColValue(obj,"FULL_SCORE");
//			fullScoreList.add(fullScore);
		}
		Comparator comp = Collections.reverseOrder();
		Collections.sort(fullScoreList,comp);
		//��������
		double score1=Double.parseDouble(fullScoreList.get(0).toString());
		int num=1;
//		sortList.add(num);
		for(int i=1;i<fullScoreList.size();i++){
			double score=Double.parseDouble(fullScoreList.get(i).toString());
			if(score!=score1){
				num=i+1;
				score1=score;
			}
//			sortList.add(num);
		}
		//
		for(int i=0;i<size;i++){
			Object obj=list.get(i);
			double fullScore=getColValue(obj,"FULL_SCORE");
//			int sortNumber=((Integer)sortList.get(fullScoreList.indexOf(fullScore))).intValue();
//			setColStringValue(obj,"SortNumber",String.valueOf(sortNumber));
			}
	}
	/**
	 * ���÷������Stringֵ by changhongxiang
	 * @param tawCheckWanggSCORE
	 * @param colName
	 * @param colValue
	 */
	public void setColStringValue(Object tawCheckWanggSCORE, String colName,
			String colValue) {
		try {
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = String.class;
			Method method = null;
			if (colName.equals("FULL_SCORE")) {
				method = tawCheckWanggSCORE.getClass().getMethod(
						"setFull_score", parameterTypes);//
			} else {
				method = tawCheckWanggSCORE.getClass().getMethod(
						"set" + colName, parameterTypes);//
			}
			Object[] args = new Object[1];
			args[0] = colValue;
			method.invoke(tawCheckWanggSCORE, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	public ActionForward performAttachload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ��ǰ�û���session�е���Ϣ
		String strLocal = StaticMethod.getLocalString();
		String sYear = strLocal.substring(0, 4);
		int iMonth = StaticMethod.null2int(strLocal.substring(5, 7));
		request.setAttribute("sYear", sYear);
		request.setAttribute("sMonth", String.valueOf(iMonth));
		return mapping.findForward("success");
	}	
	public ActionForward performAttachloadDo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawCheckModelBO bo=new TawCheckModelBO();
		Map nMap = ((DynaActionForm) form).getMap();
		bo.updateModelAttatch(nMap);
		return mapping.findForward("success");
	}
	public ActionForward performAttachFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawCheckModelBO bo=new TawCheckModelBO();
		Map nMap = ((DynaActionForm) form).getMap();
		TawCheckModelAttatch model=bo.getAttatchFile(nMap);
		request.setAttribute("TAWCHECKMODEL", model);
		return mapping.findForward("success");
	}
}