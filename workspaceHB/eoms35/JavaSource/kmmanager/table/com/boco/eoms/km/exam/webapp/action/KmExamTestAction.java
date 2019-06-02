package com.boco.eoms.km.exam.webapp.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.exam.mgr.KmExamAnswersMgr;
import com.boco.eoms.km.exam.mgr.KmExamAttendMgr;
import com.boco.eoms.km.exam.mgr.KmExamChoiceMgr;
import com.boco.eoms.km.exam.mgr.KmExamQuestionsMgr;
import com.boco.eoms.km.exam.mgr.KmExamSpecialtyMgr;
import com.boco.eoms.km.exam.mgr.KmExamTestMgr;
import com.boco.eoms.km.exam.mgr.KmExamTestTypeContentMgr;
import com.boco.eoms.km.exam.mgr.KmExamTestTypeMgr;
import com.boco.eoms.km.exam.model.KmExamAnswers;
import com.boco.eoms.km.exam.model.KmExamAttend;
import com.boco.eoms.km.exam.model.KmExamChoice;
import com.boco.eoms.km.exam.model.KmExamQuestions;
import com.boco.eoms.km.exam.model.KmExamSpecialty;
import com.boco.eoms.km.exam.model.KmExamTest;
import com.boco.eoms.km.exam.model.KmExamTestType;
import com.boco.eoms.km.exam.model.KmExamTestTypeContent;
import com.boco.eoms.km.exam.util.KmExamQuestionsConstants;
import com.boco.eoms.km.exam.util.KmExamTestConstants;
import com.boco.eoms.km.exam.webapp.form.KmExamQuestionsForm;
import com.boco.eoms.km.exam.webapp.form.KmExamTestForm;
import com.boco.eoms.km.exam.webapp.form.KmExamTestTypeForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.base.util.ConvertUtil;
/**
 * <p>
 * Title:试卷
 * </p>
 * <p>
 * Description:试卷
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmExamTestAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
 	
 	/**
	 * 新增试卷
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	//是不是随机试卷的标示
    	String isRandom = StaticMethod.null2String(request.getParameter("isRandom"));
    	request.setAttribute("isRandom", isRandom);
    	if(isRandom.equals("1")){
    		return mapping.findForward("editRandom");
    	}
		return mapping.findForward("edit");
	}
    
    /**
     * 发布试卷 isPublic为1是已经发布 0为未发布 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward isPublic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		String testID = StaticMethod.null2String(request.getParameter("testID"));
		KmExamTest kmExamTest = kmExamTestMgr.getKmExamTest(testID);
		kmExamTest.setIsPublic("1");
		kmExamTestMgr.saveKmExamTest(kmExamTest);
		return mapping.findForward("success");
	}
    
     /**
	 * 生成试卷文件 html格式
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward generateFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	String result = StaticMethod.null2String(request.getParameter("result"));  
    	try {
    		   File f = new File("d://hello.html");
    		   if (!f.exists()) {
    		    f.createNewFile();
    		   }
    		   OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
    		   BufferedWriter writer=new BufferedWriter(write);    		 
    		   writer.write(result);
    		   writer.close();
    		  } catch (Exception e) {
    		   System.out.println("写文件内容操作出错");
    		   e.printStackTrace();
    		  }    		
    	return search(mapping, form, request, response);
	}
    
    /**
	 * 复制试卷
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward copyTest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
		KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
		String testID = StaticMethod.null2String(request.getParameter("testID"));		
		KmExamTest kmExamTest = kmExamTestMgr.getKmExamTest(testID);		
		KmExamTest kmExamTest1 = new KmExamTest();		
		ConvertUtil.populateObject(kmExamTest1, kmExamTest);
		kmExamTest1.setTestName(kmExamTest.getTestName()+"(复制)");
		kmExamTest1.setIsPublic("0");
		kmExamTest1.setTestID(null);
		Date createTime = StaticMethod.getLocalTime();
		kmExamTest1.setCreateTime(createTime);
		TawSystemSessionForm tawSystemSessionForm = this.getUser(request);
		kmExamTest1.setCreateDept(tawSystemSessionForm.getDeptid());
		kmExamTest1.setCreateUser(tawSystemSessionForm.getUserid());
		kmExamTestMgr.saveKmExamTest(kmExamTest1);
		
		List testTypeList=kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);		
		List choicelist=new ArrayList();		
		for(Iterator iter=testTypeList.iterator();iter.hasNext();){
			KmExamTestType kmExamTestType=(KmExamTestType)iter.next();
			KmExamTestType kmExamTestType1 = new KmExamTestType();
			ConvertUtil.populateObject(kmExamTestType1, kmExamTestType);
			kmExamTestType1.setTestTypeId(null);
			kmExamTestType1.setTestID(kmExamTest1.getTestID());
			kmExamTestTypeMgr.saveKmExamTestType(kmExamTestType1);
			String testTypeID=kmExamTestType.getTestTypeId();			
		    List contentList=kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);			
		    for(Iterator iter1=contentList.iterator();iter1.hasNext();){
		    	KmExamTestTypeContent kmExamTestTypeContent=(KmExamTestTypeContent)iter1.next();
		    	KmExamTestTypeContent kmExamTestTypeContent1 = new KmExamTestTypeContent();
		    	ConvertUtil.populateObject(kmExamTestTypeContent1, kmExamTestTypeContent);
		    	kmExamTestTypeContent1.setTypeContentID(null);
		    	kmExamTestTypeContent1.setTestTypeID(kmExamTestType1.getTestTypeId());
		    	kmExamTestTypeContentMgr.saveKmExamTestTypeContent(kmExamTestTypeContent1);
		    }
		 
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 修改试卷
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
		KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		String testID = StaticMethod.null2String(request.getParameter("testID"));
		KmExamTest kmExamTest = kmExamTestMgr.getKmExamTest(testID);
		KmExamTestForm kmExamTestForm = (KmExamTestForm) convert(kmExamTest);
		updateFormBean(mapping, request, kmExamTestForm);
		
		List testTypeList=kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
		request.setAttribute("testTypeList", testTypeList);
		List choicelist=new ArrayList();
		
		for(Iterator iter=testTypeList.iterator();iter.hasNext();){
			KmExamTestType kmExamTestType=(KmExamTestType)iter.next();
			String testTypeID=kmExamTestType.getTestTypeId();
			String type=kmExamTestType.getType();
		    List contentList=kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
			KmExamTestTypeForm kmExamTestTypeForm = (KmExamTestTypeForm) convert(kmExamTestType);
			request.setAttribute("kmExamTestTypeForm"+type, kmExamTestTypeForm);
		    request.setAttribute("contentList"+type, contentList);
		    int count=0;
		    String questionIDStr="";
		    String contentIDStr="";
		    String scoreStr="";
		    for(Iterator iter1=contentList.iterator();iter1.hasNext();){
		    	KmExamTestTypeContent kmExamTestTypeContent=(KmExamTestTypeContent)iter1.next();			    	
		    	String questionID=kmExamTestTypeContent.getQuestionID();
		    	String contentID=kmExamTestTypeContent.getTypeContentID();
		    	String score=kmExamTestTypeContent.getScore();
		    	if(count>0){
		    		questionIDStr+=",";
		    		contentIDStr+=",";
		    		scoreStr+=","; 
		    	}
		    	questionIDStr=questionIDStr+questionID;
		    	contentIDStr=contentIDStr+contentID;
		    	scoreStr=scoreStr+score;
		    	count++;
		    }
		    request.setAttribute("questionIDStr"+type, questionIDStr);
		    request.setAttribute("contentIDStr"+type, contentIDStr);
		    request.setAttribute("scoreStr"+type, scoreStr);
		    request.setAttribute("type"+type, type);
		    request.setAttribute("num"+type, count+"");
		}
		//得到该试卷是否是随机试卷 "1"是随机随卷  0不是随机试卷
		String isRandom = kmExamTest.getIsPermittedOvertime();
		if(isRandom.equals("1")){
			return mapping.findForward("editRandom");
		}
		return mapping.findForward("edit");
	}
    
   /**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
   public ActionForward getTestPaper(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
		KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
		String testID = StaticMethod.null2String(request.getParameter("testID"));
		KmExamTest kmExamTest = kmExamTestMgr.getKmExamTest(testID);
		KmExamTestForm kmExamTestForm = (KmExamTestForm) convert(kmExamTest);
		updateFormBean(mapping, request, kmExamTestForm);
		
		List testTypeList=kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
		request.setAttribute("testTypeList", testTypeList);
		
		for(Iterator iter=testTypeList.iterator();iter.hasNext();){
			KmExamTestType kmExamTestType=(KmExamTestType)iter.next();
			String testTypeID=kmExamTestType.getTestTypeId();
			String type=kmExamTestType.getType();
		    List contentList=kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
			KmExamTestTypeForm kmExamTestTypeForm = (KmExamTestTypeForm) convert(kmExamTestType);
			request.setAttribute("kmExamTestTypeForm"+type, kmExamTestTypeForm);
		    request.setAttribute("contentList"+type, contentList);
		    int count=1;
		    String questionIDStr="";
		    String contentIDStr="";
		    String scoreStr="";		  
			List questionlist=new ArrayList();
			List choiceList=new ArrayList();
		    for(Iterator iter1=contentList.iterator();iter1.hasNext();){
		    	KmExamTestTypeContent kmExamTestTypeContent=(KmExamTestTypeContent)iter1.next();			    	
		    	String questionID=kmExamTestTypeContent.getQuestionID();
		    	KmExamQuestions kmExamQuestions = kmExamQuestionsMgr.getKmExamQuestions(questionID);
				KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) convert(kmExamQuestions);
				questionlist.add(kmExamQuestionsForm);
				request.setAttribute("kmExamQuestionsForm"+type+""+count, kmExamQuestionsForm);				
				List choiceList1=kmExamChoiceMgr.getKmExamChoicesByQuestionID(questionID);
				choiceList.addAll(choiceList1);
				count++;
		    }
		    request.setAttribute("choiceList"+type, choiceList);
		    request.setAttribute("questionlist"+type, questionlist);	
		}		
	
		return mapping.findForward("paper");
	}
	
	/**
	 * 保存试卷
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
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
		KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
		KmExamTestForm kmExamTestForm = (KmExamTestForm) form;
		boolean isNew = (null == kmExamTestForm.getTestID() || "".equals(kmExamTestForm.getTestID()));
		KmExamTest kmExamTest = (KmExamTest) convert(kmExamTestForm);
		Date createTime = StaticMethod.getLocalTime();
		kmExamTest.setCreateTime(createTime);
		TawSystemSessionForm tawSystemSessionForm = this.getUser(request);
		kmExamTest.setCreateDept(tawSystemSessionForm.getDeptid());
		kmExamTest.setCreateUser(tawSystemSessionForm.getUserid());
		kmExamTest.setDeptID("-1");//预留“所属部门”
		
		if (isNew) {
			kmExamTestMgr.saveKmExamTest(kmExamTest);
		} else {
//			String testID = kmExamTestForm.getTestID();
//			//先删除以前问题下的所有题目
//			List list = kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
//			for(int i=0;i<list.size();i++){
//				KmExamTestType kmExamTestType = (KmExamTestType)list.get(i);
//				kmExamTestTypeMgr.removeKmExamTestType(kmExamTestType.getTestTypeId());
//			}
			kmExamTestMgr.saveKmExamTest(kmExamTest);
		}
		
		String description1 = StaticMethod.null2String(request.getParameter("description1"));
		if(!description1.equals("")){
			KmExamTestType kmExamTestType1 =new KmExamTestType();
			kmExamTestType1.setType("1");
			kmExamTestType1.setDescription(description1);
			kmExamTestType1.setQuantity(StaticMethod.nullObject2Integer(request.getParameter("quantity1")));
			kmExamTestType1.setScore(StaticMethod.null2String(request.getParameter("score1")));
			kmExamTestType1.setTestTypeId(StaticMethod.null2String(request.getParameter("testTypeId1")));
			kmExamTestType1.setTestID(kmExamTest.getTestID());
			String result1 = StaticMethod.null2String(request.getParameter("result1"));
			kmExamTestTypeMgr.saveKmExamTestType(kmExamTestType1);
			if(!result1.equals("")){
				JSONArray data = JSONArray.fromString(result1);						    
				for(Iterator iter=data.iterator();iter.hasNext();){
			       JSONObject  obj=(JSONObject)iter.next();
			       KmExamTestTypeContent kmExamTestTypeContent=new KmExamTestTypeContent();
			       kmExamTestTypeContent.setQuestionID(obj.getString("questionID"));
			       kmExamTestTypeContent.setTypeContentID(obj.getString("contentID"));
			       kmExamTestTypeContent.setTestTypeID(kmExamTestType1.getTestTypeId());
			       kmExamTestTypeContent.setScore(obj.getString("score"));
			       kmExamTestTypeContentMgr.saveKmExamTestTypeContent(kmExamTestTypeContent);
				}	
			}
		}else if(request.getParameter("description1")==null&&!request.getParameter("testTypeId1").equals("")){//删除掉隐去的题型
			String testTypeID=StaticMethod.null2String(request.getParameter("testTypeId1"));
			kmExamTestTypeMgr.removeKmExamTestType(testTypeID);
		}
		String description2 = StaticMethod.null2String(request.getParameter("description2"));
		if(!description2.equals("")){
			KmExamTestType kmExamTestType2 =new KmExamTestType();
			kmExamTestType2.setType("2");
			kmExamTestType2.setDescription(description2);
			kmExamTestType2.setQuantity(StaticMethod.nullObject2Integer(request.getParameter("quantity2")));
			kmExamTestType2.setScore(StaticMethod.null2String(request.getParameter("score2")));
			String result2 = StaticMethod.null2String(request.getParameter("result2"));
			kmExamTestType2.setTestTypeId(StaticMethod.null2String(request.getParameter("testTypeId2")));
			kmExamTestType2.setTestID(kmExamTest.getTestID());
			kmExamTestTypeMgr.saveKmExamTestType(kmExamTestType2);
			if(!result2.equals("")){
				JSONArray data = JSONArray.fromString(result2);			
				for(Iterator iter=data.iterator();iter.hasNext();){
			       JSONObject  obj=(JSONObject)iter.next();
			       KmExamTestTypeContent kmExamTestTypeContent=new KmExamTestTypeContent();
			       kmExamTestTypeContent.setQuestionID(obj.getString("questionID"));
			       kmExamTestTypeContent.setTestTypeID(kmExamTestType2.getTestTypeId());
			       kmExamTestTypeContent.setTypeContentID(obj.getString("contentID"));
			       kmExamTestTypeContent.setScore(obj.getString("score"));
			       kmExamTestTypeContentMgr.saveKmExamTestTypeContent(kmExamTestTypeContent);
				}	
			}
		}else if(request.getParameter("testTypeId2")!=null){//删除掉隐去的题型
			kmExamTestTypeMgr.removeKmExamTestType(StaticMethod.null2String(request.getParameter("testTypeId2")));
		}
		String description3 = StaticMethod.null2String(request.getParameter("description3"));
		if(!description3.equals("")){
			KmExamTestType kmExamTestType3 =new KmExamTestType();
			kmExamTestType3.setType("3");
			kmExamTestType3.setDescription(description3);
			kmExamTestType3.setQuantity(StaticMethod.nullObject2Integer(request.getParameter("quantity3")));
			kmExamTestType3.setScore(StaticMethod.null2String(request.getParameter("score3")));
			String result3 = StaticMethod.null2String(request.getParameter("result3"));
			kmExamTestType3.setTestTypeId(StaticMethod.null2String(request.getParameter("testTypeId3")));
			kmExamTestType3.setTestID(kmExamTest.getTestID());
			kmExamTestTypeMgr.saveKmExamTestType(kmExamTestType3);
			if(!result3.equals("")){
				JSONArray data = JSONArray.fromString(result3);			
				for(Iterator iter=data.iterator();iter.hasNext();){
			       JSONObject  obj=(JSONObject)iter.next();
			       KmExamTestTypeContent kmExamTestTypeContent=new KmExamTestTypeContent();
			       kmExamTestTypeContent.setQuestionID(obj.getString("questionID"));
			       kmExamTestTypeContent.setTestTypeID(kmExamTestType3.getTestTypeId());
			       kmExamTestTypeContent.setTypeContentID(obj.getString("contentID"));
			       kmExamTestTypeContent.setScore(obj.getString("score"));
			       kmExamTestTypeContentMgr.saveKmExamTestTypeContent(kmExamTestTypeContent);
				}	
			}
		}else if(request.getParameter("testTypeId3")!=null){//删除掉隐去的题型
			kmExamTestTypeMgr.removeKmExamTestType(StaticMethod.null2String(request.getParameter("testTypeId3")));
		}
		String description4 = StaticMethod.null2String(request.getParameter("description4"));
		if(!description4.equals("")){
			KmExamTestType kmExamTestType4 =new KmExamTestType();
			kmExamTestType4.setType("4");
			kmExamTestType4.setDescription(description4);
			kmExamTestType4.setQuantity(StaticMethod.nullObject2Integer(request.getParameter("quantity4")));
			kmExamTestType4.setScore(StaticMethod.null2String(request.getParameter("score4")));
			String result4 = StaticMethod.null2String(request.getParameter("result4"));
			kmExamTestType4.setTestTypeId(StaticMethod.null2String(request.getParameter("testTypeId4")));
			kmExamTestType4.setTestID(kmExamTest.getTestID());
			kmExamTestTypeMgr.saveKmExamTestType(kmExamTestType4);
			if(!result4.equals("")){
				JSONArray data = JSONArray.fromString(result4);			
				for(Iterator iter=data.iterator();iter.hasNext();){
			       JSONObject  obj=(JSONObject)iter.next();
			       KmExamTestTypeContent kmExamTestTypeContent=new KmExamTestTypeContent();
			       kmExamTestTypeContent.setQuestionID(obj.getString("questionID"));
			       kmExamTestTypeContent.setTestTypeID(kmExamTestType4.getTestTypeId());
			       kmExamTestTypeContent.setTypeContentID(obj.getString("contentID"));
			       kmExamTestTypeContent.setScore(obj.getString("score"));
			       kmExamTestTypeContentMgr.saveKmExamTestTypeContent(kmExamTestTypeContent);
				}	
			}
		}else if(request.getParameter("testTypeId4")!=null){//删除掉隐去的题型
			kmExamTestTypeMgr.removeKmExamTestType(StaticMethod.null2String(request.getParameter("testTypeId4")));
		}
		String description5 = StaticMethod.null2String(request.getParameter("description5"));
		if(!description5.equals("")){
			KmExamTestType kmExamTestType5 =new KmExamTestType();
			kmExamTestType5.setType("5");
			kmExamTestType5.setDescription(description5);
			kmExamTestType5.setQuantity(StaticMethod.nullObject2Integer(request.getParameter("quantity5")));
			kmExamTestType5.setScore(StaticMethod.null2String(request.getParameter("score5")));
			String result5 = StaticMethod.null2String(request.getParameter("result5"));
			kmExamTestType5.setTestTypeId(StaticMethod.null2String(request.getParameter("testTypeId5")));
			kmExamTestType5.setTestID(kmExamTest.getTestID());
			kmExamTestTypeMgr.saveKmExamTestType(kmExamTestType5);
			if(!result5.equals("")){
				JSONArray data = JSONArray.fromString(result5);			
				for(Iterator iter=data.iterator();iter.hasNext();){
			       JSONObject  obj=(JSONObject)iter.next();
			       KmExamTestTypeContent kmExamTestTypeContent=new KmExamTestTypeContent();
			       kmExamTestTypeContent.setQuestionID(obj.getString("questionID"));
			       kmExamTestTypeContent.setTestTypeID(kmExamTestType5.getTestTypeId());
			       kmExamTestTypeContent.setTypeContentID(obj.getString("contentID"));
			       kmExamTestTypeContent.setScore(obj.getString("score"));
			       kmExamTestTypeContentMgr.saveKmExamTestTypeContent(kmExamTestTypeContent);
				}	
			}
		}else if(request.getParameter("testTypeId5")!=null){//删除掉隐去的题型
			kmExamTestTypeMgr.removeKmExamTestType(StaticMethod.null2String(request.getParameter("testTypeId5")));
		}
		
		if(request.getAttribute("kmExamTestForm")!=null){
			request.removeAttribute("kmExamTestForm");
		}
		
		return mapping.findForward("success");
	}
	
	/**
	 * 删除试卷
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmExamTestMgr.removeKmExamTest(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示试卷列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExamTestConstants.KMEXAMTEST_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		Map map = (Map) kmExamTestMgr.getKmExamTests(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmExamTestConstants.KMEXAMTEST_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		if(request.getAttribute("kmExamTestForm")!=null){
			request.removeAttribute("kmExamTestForm");
		}
		return mapping.findForward("list");
	}
	
	/**
	 * 根据查询条件分页显示模块信息表列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExamTestConstants.KMEXAMTEST_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmExamTestForm kmExamTestForm = (KmExamTestForm) form;		
		
		 String whereStr=" where 1=1";
		  if(kmExamTestForm.getSpecialtyID()!=null&&kmExamTestForm.getSpecialtyID()!=""){
			  whereStr+=" and kmExamTest.specialtyID like '"+kmExamTestForm.getSpecialtyID()+"%'"; 
		  }
		  if(kmExamTestForm.getTestName()!=null&&kmExamTestForm.getTestName()!=""){
			  whereStr+=" and kmExamTest.testName like '%"+kmExamTestForm.getTestName()+"%'"; 
		  }		 		  
		  whereStr+=" and kmExamTest.isDeleted='0'";
		  
		  KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
		Map map = (Map) kmExamTestMgr.getKmExamTests(pageIndex, pageSize, whereStr);
		List list = (List) map.get("result");
		request.setAttribute(KmExamTestConstants.KMEXAMTEST_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("kmExamTestForm", kmExamTestForm);
		return mapping.findForward("list");
	}
	
	/**
	 * 获取模型分类树(第一层)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNodesRadioTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		JSONArray jsonRoot = new JSONArray();
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");		
		List list = (List)kmExamTestMgr.getKmExamTests();
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmExamTest kmExamTest = (KmExamTest) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmExamTest.getTestID());
			// TODO 添加节点名称
			jitem.put("text",kmExamTest.getTestName());
			jitem.put(UIConstants.JSON_NODETYPE, "folder");
			//jitem.put("iconCls", "folder");
			jitem.put("qtip", kmExamTest.getTestName());
			
			// 设置是否为叶子节点
			boolean leafFlag = true;
		
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			//jitem.put("qtip", your tips here);
			jitem.put("checked", false);		
			jsonRoot.put(jitem);
		}
		//JSONUtil.print(response, jsonRoot.toString());		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
	}
	
	/**
	 * 获取部门树 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getUserTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		JSONArray jsonRoot = new JSONArray();
		KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");		
		List list = (List)kmExamTestMgr.getKmExamTests();
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmExamTest kmExamTest = (KmExamTest) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmExamTest.getTestID());
			// TODO 添加节点名称
			jitem.put("text",kmExamTest.getTestName());
			jitem.put(UIConstants.JSON_NODETYPE, "folder");
			//jitem.put("iconCls", "folder");
			jitem.put("qtip", kmExamTest.getTestName());
			
			// 设置是否为叶子节点
			boolean leafFlag = true;
		
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			//jitem.put("qtip", your tips here);
			jitem.put("checked", false);		
			jsonRoot.put(jitem);
		}
		//JSONUtil.print(response, jsonRoot.toString());		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
	}
	
	/**
	 * 分页显示试卷列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
//			Map map = (Map) kmExamTestMgr.getKmExamTests(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmExamTest kmExamTest = new KmExamTest();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmExamTest = (KmExamTest) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmExamTest/kmExamTests.do?method=edit&id="
//						+ kmExamTest.getTestID() + "' target='_blank'>"
//						+ display name for list + "</a>");
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
//				
//				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
//				entry.setUpdated(new java.util.Date());
//				entry.setPublished(new java.util.Date());
//				entry.setEdited(new java.util.Date());
//				
//				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
//			}
//			
//			// 每页显示条数
//			feed.setText(map.get("total").toString());
//		    OutputStream os = response.getOutputStream();
//		    PrintStream ps = new PrintStream(os);
//		    feed.getDocument().writeTo(ps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}
}