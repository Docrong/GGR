<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
//Ȩ���ж�
//        SaveSessionBeanForm saveSessionBeanForm =
//                (SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
//        if (saveSessionBeanForm == null) {
//           response.sendRedirect("/timeout.jsp");
//        }
//        String userId = saveSessionBeanForm.getWrf_UserID();
//        TawValidatePrivBO tawVPBO = new TawValidatePrivBO();
//        if (!tawVPBO.validatePriv(userId, SchemeMgrDAO.OPERATOR_TOPIC_MGR_ID)) {
//           response.sendRedirect("../nopriv.jsp");
//        }
%>
<%--<FRAMESET frameBorder=1 rows="100%">--%>
<%--	<FRAMESET frameBorder=1 cols="25%,*">--%>
<%--	<FRAME frameBorder=0 name="menu" src="InfoTypeMenuAction.do" scrolling="auto" marginWidth="0" marginHeight="1">--%>
<%--	<FRAME frameBorder=0 name="doc" src="InfoTypeDetailAction.do" scrolling="auto" marginWidth="3" marginHeight="3">--%>
<%--    </FRAMESET>--%>
<%--</FRAMESET>--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>

<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/filemanager/topic/InfoTypeMenuAction.do?pageType=topic",
    treeRootId:'-1',
    treeRootText:"数据上报",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/filemanager/topic/InfoTypeDetailAction.do?actId=0&onlyTopic=1&hdId=",text:"详细内容"},
	ctxMenu:[
		{id:'newTopic', text:'创建新专题',cls:'new-mi',url:'${app}/filemanager/topic/InfoTypeDetailAction.do?actId=4&onlyTopic=1&hdId=', rootCanShow:true},
		{id:'edtTopic', text:'编辑专题',cls:'edit-mi',url:'${app}/filemanager/topic/InfoTypeDetailAction.do?actId=0&onlyTopic=1&hdId='},
		{id:'delTopic', isDelete:true, text:'删除专题',cls:'remove-mi',url:'${app}/filemanager/topic/InfoTypeDetailAction.do?actId=1&onlyTopic=1&hdId='}
	],//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
  </script>
  <style type="text/css">
  	body{background-image:none;}
  </style>
<div id="headerPanel" class="x-layout-inactive-content">
    <h1>数据上报</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>新建专题</dt>
		<dd>只有管理员能够对专题进行操作。新建专题，填写完整后，进行保存。</dd>
		<dt>编辑专题</dt>
		<dd>选择此专题，进入编辑页面，修改完成后保存并在左侧的树中看到刚刚建立的专题。</dd>
		<dt>删除专题</dt>
		<dd>提示信息，确定此专题需要删除，点击确定，此专题被删除。如果不能删除专题，说明此专题下有派发报表。</dd>
	</dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
	<iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
