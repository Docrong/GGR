/**
 * Created by IntelliJ IDEA.
 * User: lxl
 * Date: 2003-8-30
 * Time: 15:56:34
 * To change this template use Options | File Templates.
 */

package com.boco.eoms.filemanager.topic.action;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.filemanager.topic.*;
import com.boco.eoms.filemanager.topic.form.InfoTypeDetailForm;
import com.boco.eoms.filemanager.SchemeMgrDAO;
import com.boco.eoms.common.resource.Util;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;


public class InfoTypeDetailAction extends Action {
    private static final String FILE_SEPARATOR = "/";
    private static final String STR_MACRO_QUERY = "可查询";
    private static final String STR_MACRO_MODIFY = "可修改";
    private static final String STR_MACRO_SAVE_SUCESS = " alert(\"保存成功\")\n parent.menu.location=\"InfoTypeMenuAction.do\";";
    private static final String STR_MACRO_MODIFY_SUCESS = " alert(\"修改成功\")\n parent.menu.location=\"InfoTypeMenuAction.do\";";
    private static final String STR_MACRO_DEL_SUCESS = " alert(\"删除成功\")\n parent.menu.location=\"InfoTypeMenuAction.do\";";

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
    	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
    	if(!"admin".equals(saveSessionBeanForm.getUserid())){
    		return mapping.findForward("nopriv");
    	}
        String onlyTopic = request.getParameter("onlyTopic");

        try {
            Connection m_con = ConnectionPool.getInstance().getConnection();
//request.setAttribute("successed","ddddddd");
            //�õ�session
            //boolean create = true;
            //HttpSession session = request.getSession(create);
            InfoTypeDetailForm objForm = (InfoTypeDetailForm) form;

//            objForm.setTopicName(StaticMethod.strReverse(objForm.getTopicName(),"ISO-8859-1","UTF-8"));
//			objForm.setTopicMemo(StaticMethod.strReverse(objForm.getTopicMemo(),"ISO-8859-1","UTF-8"));
            //�õ�hdId��ֲ
            String strHdId = objForm.getHdId();

            if (strHdId == null || "".equalsIgnoreCase(strHdId)) {
                if (request.getAttribute("hdId") != null) {
                    strHdId = request.getAttribute("hdId").toString();
                    objForm.setHdId(strHdId);
                }
            }

            //System.out.println("strHdId value="+strHdId);

            //��ʾ���е�����
            String strFlag = objForm.getActId();
            if (strFlag == null || "".equalsIgnoreCase(strFlag)) {
                if (request.getAttribute("actId") != null) {
                    strFlag = request.getAttribute("actId").toString();
                    objForm.setActId(strFlag);
                }

            }

            if (strFlag.equals("")) {
/*
                strFlag="0";
				objForm.setHdClassId("0");
				objForm.setHdTopicPath("Topic");
				request.setAttribute("htmlBegin",LBCommonMacro.MACRO_NOTE_BEGIN);
				request.setAttribute("htmlEnd",LBCommonMacro.MACRO_NOTE_END);
				request.setAttribute("newenable","");
				request.setAttribute("saveenable",LBCommonMacro.MACRO_HTML_DISABLED);
				request.setAttribute("deleteenable",LBCommonMacro.MACRO_HTML_DISABLED);
                */
            } else {
                request.setAttribute("htmlBegin", "");
                request.setAttribute("htmlEnd", "");
            }

            if (strFlag.equals("0") || strFlag.equals("")) {
//���ID��ѯ��ϸ
                if (strHdId.equals("")) {
                    strHdId = "0";
                }
                if (strHdId.equals("0")) {
                    //����Ǹ�ڵ�
                    objForm.setHdClassId("0");
                    objForm.setHdTopicPath("Topic");
                    request.setAttribute("htmlBegin", LBCommonMacro.MACRO_NOTE_BEGIN);
                    request.setAttribute("htmlEnd", LBCommonMacro.MACRO_NOTE_END);
                    request.setAttribute("newenable", "");
                    request.setAttribute("saveenable", LBCommonMacro.MACRO_HTML_DISABLED);
                    request.setAttribute("deleteenable", LBCommonMacro.MACRO_HTML_DISABLED);

                } else {
                    //��ϸ��Ϣ
                    request.setAttribute("newenable", "");
                    request.setAttribute("saveenable", "");
                    //�ж��Ƿ�ҳ�ӽڵ�,����ǽڵ�,����ɾ��
                    String[] strParamArray = {strHdId};
                    LbSelectInfoTypeChildId objSelId = new LbSelectInfoTypeChildId(m_con);
                    objSelId.executeProcess(strParamArray);
                    HashMap hmId = objSelId.getResult();
                    Vector vTemp1 = (Vector) hmId.get("topic_id");
                    if (vTemp1 != null && vTemp1.size() > 0) {
                        request.setAttribute("deleteenable", LBCommonMacro.MACRO_HTML_DISABLED);
                    } else {
                        request.setAttribute("deleteenable", "");
                    }

                }
                showDetail(strHdId, request, objForm, m_con);
            }
            if (strFlag.equals("1")) {
//ɾ���¼
                LbDelInfoType objDel = new LbDelInfoType(m_con);
                String[] strParamArray = {strHdId};
                objDel.executeProcess(strParamArray);
                objForm.setHdId("");
                objForm.setHdClassId("0");
                objForm.setHdTopicPath("Topic");
                objForm.setActId("");
                request.setAttribute("htmlBegin", LBCommonMacro.MACRO_NOTE_BEGIN);
                request.setAttribute("htmlEnd", LBCommonMacro.MACRO_NOTE_END);
                request.setAttribute("newenable", "");
                request.setAttribute("saveenable", LBCommonMacro.MACRO_HTML_DISABLED);
                request.setAttribute("deleteenable", LBCommonMacro.MACRO_HTML_DISABLED);
//                request.setAttribute("successed", STR_MACRO_DEL_SUCESS);
                return mapping.findForward("success");
            }

            if (strFlag.equals("3")) {
//保存记录
            	String[] strPrivilege = objForm.getSelectPrvlg();
//System.out.println("getSelectPrvlg go here "+strPrivilege)  ;
                if (strPrivilege != null && strPrivilege.length > 0) {
                    for (int i = 0; i < strPrivilege.length; i++) {
//System.out.println(strPrivilege[i]);
                    }
                }

                if (!strHdId.equals("")) {
                	//得到有效途径
                	String strPath = LBCommonFunc.getForPathFolder(objForm.getHdTopicPath(), FILE_SEPARATOR) + objForm.getTopicPath();
                	//更新
                	LbUpdateInfoType objUpdate = new LbUpdateInfoType(m_con);
                    String[] strParamArray = {
                            objForm.getTopicName(),
                            strPath,
                            objForm.getTopicMemo(),
                            objForm.getTopicOrder(),
                            objForm.getTopicTypeId(),
                            objForm.getTopicTypeName(),
                            strHdId
                    };
                    objUpdate.setPrivilege(strPrivilege);
                    objUpdate.executeProcess(strParamArray);

                    showDetail(strHdId, request, objForm, m_con);
                    //request.setAttribute("successed", STR_MACRO_MODIFY_SUCESS);
                } else {
                    //����par_topic_id,topic_name,topic_memo,topic_path,topic_order,class_id
                    String strPath = objForm.getHdTopicPath() + FILE_SEPARATOR + objForm.getHdClassId();
                    //System.out.println("strPath="+strPath);
                    LbSaveInfoType objSave = new LbSaveInfoType(m_con);
                    String strParentId = objForm.getHdParentId();
                    if (strParentId.equals("")) {
                        strParentId = "0";
                    }
                    String[] strParamArray = {
                            objForm.getTopicTypeId(),
                            objForm.getTopicTypeName(),
                            strParentId,
                            objForm.getTopicName(),
                            objForm.getTopicMemo(),
                            strPath,
                            objForm.getTopicOrder(),
                            objForm.getHdClassId()
                    };
                    objSave.setPrivilege(strPrivilege);
                    objSave.executeProcess(strParamArray);

                    strHdId = objSave.getInfoTypeId();
                    objForm.setHdId(strHdId);
                    showDetail(strHdId, request, objForm, m_con);
                    //request.setAttribute("successed", STR_MACRO_SAVE_SUCESS);
                }

                request.setAttribute("newenable", "");
                request.setAttribute("saveenable", "");
                request.setAttribute("deleteenable", "");
                
                return mapping.findForward("success");
            }
            if (strFlag.equals("4")) {
            	//创建新类别
            	
            	if ("-1".equals(strHdId)){
                	strHdId = "0";
                	objForm.setHdId(strHdId);
                	objForm.setHdClassId("1");
                    objForm.setHdTopicPath("Topic");
//                    String strPath = objForm.getHdTopicPath() + FILE_SEPARATOR + objForm.getHdClassId();
//                    objForm.setHdTopicPath(strPath);
                    
                }else{
                	showDetail(strHdId, request, objForm, m_con);
                	String strPath = LBCommonFunc.getForPathFolder(objForm.getHdTopicPath(), FILE_SEPARATOR) + objForm.getTopicPath();
                	String strClassId = objForm.getHdClassId();
                    strClassId = Integer.toString(Integer.parseInt(strClassId) + 1);
                    objForm.setHdClassId(strClassId);
                }
            	
                objForm.setHdParentId(objForm.getHdId());
                objForm.setHdId("");
                objForm.setTopicName("");
                //objForm.setTopicPath("");
                objForm.setTopicOrder("");
                objForm.setTopicMemo("");
                objForm.setTopicTypeId("");
                objForm.setTopicTypeName("");
                
                

                request.setAttribute("htmlTopicId", "&nbsp;");
                request.setAttribute("htmlTopicPath", objForm.getHdTopicPath());
                request.setAttribute("htmlClassId", objForm.getHdClassId());
                request.setAttribute("newenable", LBCommonMacro.MACRO_HTML_DISABLED);
                request.setAttribute("saveenable", "");
                request.setAttribute("deleteenable", LBCommonMacro.MACRO_HTML_DISABLED);
            }
//�õ����������Ϣ
//if(!strFlag.equals(""))
//{
//    if(!strHdId.equals("0"))
//    {

/*
                    Vector vTemp1=(Vector)hmId.get("dept_id");
                    Vector vTemp2=(Vector)hmId.get("dept_name");
                    String[] strGroupId=LBCommonFunc.vector2StringArray(vTemp1);
                    String[] strGroupName=LBCommonFunc.vector2StringArray(vTemp2);

                    //�õ��������select option
                    String strAllGroup="";
                    if(strGroupId!=null && strGroupId.length>0)
                    {
                        for(int i=0;i<strGroupId.length;i++)
                        {
                            strAllGroup=strAllGroup+"<Option value='"+strGroupId[i]+"' >"+strGroupName[i]+"</Option>\n";
                        }
                    }      */
            request.setAttribute("allGroup", "");
//     }
// }
            if (m_con != null) {
                m_con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            return mapping.findForward("Error");
        }
//        if(onlyTopic==null)
//            return mapping.findForward("detail");  //new ActionForward("/topic/document.jsp"));
//        else
        return mapping.findForward("onlyTopic");
    }

    /*
     * ���Id ��ѯ��ݿ�
     * @param String id
     * @param HttpServletRequest request
     * @param InfoTypeDetailForm objForm
     * @return void
     */
    private void showDetail(String strHdId, HttpServletRequest request, InfoTypeDetailForm objForm, Connection m_con) throws Exception {
        try {
            LbSelectInfoTypeDetail objDetail = new LbSelectInfoTypeDetail(m_con);
            String[] strParamArray = {strHdId};
            objDetail.executeProcess(strParamArray);
            HashMap hmDetail = objDetail.getResult();

            Vector vTemp1 = (Vector) hmDetail.get("topic_name");
            String[] strTopicName = LBCommonFunc.vector2StringArray(vTemp1);
            Vector vTemp2 = (Vector) hmDetail.get("topic_id");
            String[] strTopicId = LBCommonFunc.vector2StringArray(vTemp2);
            Vector vTemp3 = (Vector) hmDetail.get("topic_path");
            String[] strTopicPath = LBCommonFunc.vector2StringArray(vTemp3);
            Vector vTemp4 = (Vector) hmDetail.get("topic_memo");
            String[] strTopicMemo = LBCommonFunc.vector2StringArray(vTemp4);
            Vector vTemp5 = (Vector) hmDetail.get("topic_order");
            String[] strTopicOrder = LBCommonFunc.vector2StringArray(vTemp5);
            Vector vTemp6 = (Vector) hmDetail.get("class_id");
            String[] strClassId = LBCommonFunc.vector2StringArray(vTemp6);
            Vector vTemp7 = (Vector) hmDetail.get("topic_type");
            String[] strTopicTypeId = LBCommonFunc.vector2StringArray(vTemp7);
            Vector vTemp8 = (Vector) hmDetail.get("topic_type_name");
            String[] strTopicTypeName = LBCommonFunc.vector2StringArray(vTemp8);
            if (strTopicName != null) {
                objForm.setTopicName(strTopicName[0]);
                request.setAttribute("htmlTopicId", strTopicId[0]);
                String strPath = LBCommonFunc.getPathFolder(strTopicPath[0], FILE_SEPARATOR);
                objForm.setTopicPath(strPath);
                request.setAttribute("htmlTopicPath", strTopicPath[0]);
                objForm.setTopicMemo(strTopicMemo[0]);
                objForm.setTopicOrder(strTopicOrder[0]);
                request.setAttribute("htmlClassId", strClassId[0]);
                objForm.setHdClassId(strClassId[0]);
                objForm.setHdTopicPath(strTopicPath[0]);
                objForm.setTopicTypeId(strTopicTypeId[0]);
                objForm.setTopicTypeName(strTopicTypeName[0]);
            } else {

                objForm.setTopicName("");
                request.setAttribute("htmlTopicId", "&nbsp;");
                objForm.setTopicPath("");
                request.setAttribute("htmlTopicPath", "&nbsp;");
                objForm.setTopicMemo("");
                objForm.setTopicOrder("");
                objForm.setTopicTypeName("");
                objForm.setTopicTypeId("");
                request.setAttribute("htmlClassId", "&nbsp;");
            }

//�õ�Ȩ�� select option
            Vector vDept_name = new Vector();
            String[] strParamTemp = {strHdId};
//            LbSelectPurview objSel = new LbSelectPurview(m_con);
//            objSel.executeProcess(strParamTemp);
//            HashMap hmPrivilege = objSel.getResult();
//            Vector vUser_grp_id = (Vector) hmPrivilege.get("user_grp_id");
//            Vector vPrivilege = (Vector) hmPrivilege.get("privilege");
//            for (int j = 0; j < vUser_grp_id.size(); j++) {
//                String strgrpId = (String) vUser_grp_id.get(j);
//                if (strgrpId.equals("-1")) {
//                    vDept_name.add("Everyone");
//                } else {
//                    vDept_name.add("");
//                }
//            }
////            String[] strUser_grp_id = LBCommonFunc.vector2StringArray(vUser_grp_id);
////            String[] strDept_name = LBCommonFunc.vector2StringArray(vDept_name);
////            String[] strPrivilege = LBCommonFunc.vector2StringArray(vPrivilege);
//
//            String strShow = "";
//            if (strUser_grp_id != null && strUser_grp_id.length > 0) {
//                for (int i = 0; i < strUser_grp_id.length; i++) {
//                    String strTemp = "";
//                    if (strPrivilege[i].equals("0")) {
//                        strTemp = STR_MACRO_QUERY;
//                    } else {
//                        strTemp = STR_MACRO_MODIFY;
//                    }
//                    strShow = strShow + "<Option value='" + strUser_grp_id[i] + "," + strPrivilege[i] + "'>" + strDept_name[i] + "  " + strTemp + "</Option>\n";
//                }
//            }
////System.out.println("strShow="+strShow);
//            request.setAttribute("allPrivilege", strShow);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
