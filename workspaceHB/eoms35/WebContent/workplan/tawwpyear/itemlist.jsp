<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearCheckVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearExecuteVO"%> 
<%@ page import ="com.boco.eoms.workplan.flow.model.Step"%> 
<%@ page import="com.boco.eoms.commons.system.user.model.*"%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('executeTable');
})
Ext.onReady(function(){
	colorRows('checkTable');
})
function onYearPlanAdd()
{
  location.href="itemadd.do?yearplanid=<%=request.getParameter("yearplanid")%>";
}
function onYearReport()
{
  location.href="reportyear.do?yearplanid=<%=request.getParameter("yearplanid")%>";
}
function onDeploy()
{
  location.href="yeardeploy.do?yearplanid=<%=request.getParameter("yearplanid")%>";
}
  function onAdd(){
    document.form1.action="modeladd.do";
    document.form1.submit();
  }
  function onExport()
  {
  location.href="yearexport.do?yearplanid=<%=request.getParameter("yearplanid")%>&reaction=addons/filedownload.jsp";
}
function onRefer()
{
  var selobj = document.getElementsByName("checkUsers");
  var result=false;
  for(i=0;i<selobj.length;i++){
    if(selobj[i].checked==true){
    	result=true;
    	break;
    }  
  } 
  if(document.getElementById("execute")==null){
	 alert('请选择执行内容'); 
  }else if(!result){
  	alert('请选择执行审核人'); 
  }else{
    document.yearrefer.action= "yearrefer.do?yearplanid=<%=request.getParameter("yearplanid")%>";
	document.yearrefer.submit();
  }
}
function onBack()
{
  document.yearrefer.action= "yearlist.do";
  document.yearrefer.submit();
}
 

</script>

<%

TawwpYearPlanVO tawwpYearPlanVO = (TawwpYearPlanVO)request.getAttribute("tawwpYearPlanVO");
String flag="";
if(tawwpYearPlanVO.getModelPlanId().equals("")){
  	flag="0";
}else{
    	flag="1";
}

TawwpYearExecuteVO tawwpYearExecuteVO = null;
TawwpYearCheckVO tawwpYearCheckVO = null;
List list = null;
List checkList = null;
%>
<form name="modelexecutelist">
<br>
<table class="listTable">
  <caption><bean:message key="itemlist.tawwpyear.planFmTitle" />&lt;&nbsp;<%=tawwpYearPlanVO.getName()%>&nbsp;&gt;</caption>
  <tr>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmSysType" /></td>
    <td width="250">
      <%=tawwpYearPlanVO.getSysTypeName()%>
    </td>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmNetType" /></td>
    <td width="250">
      <%=tawwpYearPlanVO.getNetTypeName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmDeptName" /></td>
    <td width="250">
      <%=tawwpYearPlanVO.getDeptName()%>
    </td>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmCruser" /></td>
    <td width="250">
      <%=tawwpYearPlanVO.getCruserName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmNetList" /></td>
    <td width="250">
       <%=tawwpYearPlanVO.getNetListName()%>
    </td>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmState" /></td>
    <td width="250">
      <%=tawwpYearPlanVO.getStateName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmContent" /></td>
    <td width="600" colspan="3">
      <%=tawwpYearPlanVO.getContent()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label"><bean:message key="itemlist.tawwpyear.planFmRemark" /></td>
    <td width="600" colspan="3">
      <%=tawwpYearPlanVO.getRemark()%>
    </td>
  </tr>
</table>

<br>
<br>
  <%
  list = tawwpYearPlanVO.getYearExecuteList();
  %>
  <table class="listTable" id="executeTable">
    <caption>&lt;&nbsp;<%=tawwpYearPlanVO.getName()%>&nbsp;&gt;<bean:message key="itemlist.tawwpyear.executeFmTitle" /></caption>
    <thead>
    <tr>
       
      <td width="200">
        <bean:message key="itemlist.tawwpyear.executeFmName" />
      </td>
      <td width="50">
        <bean:message key="itemlist.tawwpyear.executeFmCycle" />
      </td>
      <td width="100">
        <bean:message key="itemlist.tawwpyear.executeFmFormat" />
      </td>
      <td width="150">
        <bean:message key="itemlist.tawwpyear.executeFmForm" />
      </td>
      <td>
        是否必须上传附件
      </td>
  <% if(tawwpYearPlanVO.getState()!=null&&(tawwpYearPlanVO.getState().equals("0")||tawwpYearPlanVO.getState().equals("2"))){ %>
      <td width="50">
        <bean:message key="itemlist.tawwpyear.executeFmEdit" />
      </td>
      <td width="50">
        <bean:message key="itemlist.tawwpyear.executeFmRemove" />
      </td>
   <%} %>
    </tr>
    </thead>
    <tbody>
    <%
    for(int i=0; i<list.size(); i++){
      tawwpYearExecuteVO = (TawwpYearExecuteVO)list.get(i);
    %>
    <tr>
      
      <td width="200" id="execute">
        <%=tawwpYearExecuteVO.getName()%>
      </td>
      <td width="50">
        <%=tawwpYearExecuteVO.getCycleName()%>
      </td>
      <td width="100">
        <%=tawwpYearExecuteVO.getFormat()%>
      </td>
      <td width="150">
        <%
        if(!tawwpYearExecuteVO.getFormId().equals("0")&&!tawwpYearExecuteVO.getFormId().equals("")&&tawwpYearExecuteVO.getFormId()!=null){
        	
        %>
        <a target="_blank" href="../tawwpaddons/addonsread.do?action=read&window=new&myid=&model=50&addonsid=<%=tawwpYearExecuteVO.getFormId()%>&reaction=/tawwpaddons/addonslist.do"><%=tawwpYearExecuteVO.getFormName()%></a>
      	<%
      		 
        }
        %>
      </td>
      
      <td>
        <%=tawwpYearExecuteVO.getFileFlagName()%>
      </td>
      
  <% if(tawwpYearPlanVO.getState()!=null&&(tawwpYearPlanVO.getState().equals("0")||tawwpYearPlanVO.getState().equals("2"))){ %>
   
      <td align="middle" width="50">
        <a href="itemedit.do?yearplanid=<%=tawwpYearPlanVO.getId()%>&yearexecuteid=<%=tawwpYearExecuteVO.getId()%>&flag=<%=flag%>">
          <img src="${app }/images/icons/edit.gif" width="18" height="18">
        </a>
      </td>
      <td align="middle" width="50">
        <a href="itemremove.do?yearplanid=<%=tawwpYearPlanVO.getId()%>&yearexecuteid=<%=tawwpYearExecuteVO.getId()%>">
          <img src="${app }/images/icons/icon.gif" width="23" height="30">
        </a>
      </td>
     <%} %>
    </tr>
    <%
    }
    %>
    </tbody>
  </table>
  <br>
  <table>
    <tr>
      <td>
        <!-- input type="button" value="<bean:message key="itemlist.tawwpyear.btnExport" />" onclick="javascript:onExport();" class="button" -->
        <%
        if(null!=tawwpYearPlanVO.getUnicomType()&&!tawwpYearPlanVO.getUnicomType().equals("")&&tawwpYearPlanVO.getState().equals("1") && (tawwpYearPlanVO.getReportFlag()==null||!tawwpYearPlanVO.getReportFlag().equals("!"))){
        %>
        <input type="button" value="<bean:message key="itemlist.tawwpyear.btnYearReport" />" onclick="javascript:onYearReport();" class="button">
        <%
        }
        if(tawwpYearPlanVO.getState().equals("0")  || tawwpYearPlanVO.getState().equals("2")){
        %>
      
        <input type="button" value="<bean:message key="itemlist.tawwpyear.btnAdd" />" onclick="javascript:onYearPlanAdd();" class="button">
        
        <%
        }
  	if(tawwpYearPlanVO.getStep()==null && tawwpYearPlanVO.getState().equals("0")){
  	%>
        <input type="button" value="<bean:message key="itemlist.tawwpyear.btnDeploy" />" onclick="javascript:onDeploy();" class="button">
        <%
        }
        %>
        <input type="button" value="<bean:message key="itemlist.tawwpyear.btnBack" />" onclick="javascript:onBack();" class="button">
      </td>
    </tr>
  </table>
  </form>

<br>
<br>
  <form name="yearrefer" method="post" action="yeardispatch.do?yearplanid=<%=request.getParameter("yearplanid")%>" >
    <table class="listTable" id="checkTable">
      <caption><bean:message key="itemlist.tawwpyear.checkFmTitle" /></caption>
      <thead>
      <tr>
        <td width="80">
          <bean:message key="itemlist.tawwpyear.checkFmUser" />
        </td>
        <td width="100">
          <bean:message key="itemlist.tawwpyear.checkFmCrtime" />
        </td>
        <td width="300">
          <bean:message key="itemlist.tawwpyear.checkFmContent" />
        </td>
        <td width="50">
          <bean:message key="itemlist.tawwpyear.checkFmState" />
        </td>
      </tr>
      </thead>
      <tbody>
      <%
      checkList= tawwpYearPlanVO.getYearCheckList();
      for(int i=0; i<checkList.size(); i++){
        tawwpYearCheckVO = (TawwpYearCheckVO)checkList.get(i);
      %>
      <tr>
        <td width="80">
          <%=tawwpYearCheckVO.getCheckUserName()%>
        </td>
        <td width="100">
          <%=tawwpYearCheckVO.getCrtime()%>
        </td>
        <td width="300">
          <%=tawwpYearCheckVO.getContent()%>
        </td>
        <td width="50">
          <%=tawwpYearCheckVO.getStateName()%>
        </td>
      </tr>
      <%
      }
      %>
      </tbody>
    </table>
  <%
  Step step=tawwpYearPlanVO.getStep();
  //System.out.println(step.getSerial());
  if(step!=null && (tawwpYearPlanVO.getState().equals("0") || tawwpYearPlanVO.getState().equals("2"))){
    if(step.getCheckUserIdStr() != null &&!step.getCheckUserIdStr().equals("")){
  %>
    <table with=100% align=center>
      <tr with=100%  align="center">
        <td with=100%  align="center" >
          <bean:message key="itemlist.tawwpyear.labCanCheckUser" />
<%
         	List listCheckUser=step.getCheckUserList();
		    for(int i=0;i<listCheckUser.size();i++){
				 TawSystemUser user=(TawSystemUser)listCheckUser.get(i);
				 	//if(tawwpYearPlanVO.getDeptId().equals(user.getDeptid())){
%>					<input type="checkbox" value="<%=user.getUserid() %>" name="checkUsers" ><%=user.getDeptname() %>&nbsp;<%=user.getUsername()%>&nbsp;&nbsp;
					 
<%  		 		//}
          	}
%>
          
       
        </td>
      </tr>
     
      
    </table>
        <input type="hidden" name="checkuser" value="<%=step.getCheckUserIdStr()%>">
          <input type="hidden" name="flowserial" value="<%=step.getFlowSerial()%>">
          <input type="hidden" name="deptserial" value="<%=step.getSerial()%>">
          <input type="button" value="<bean:message key="itemlist.tawwpyear.btnCheck" />" onclick="javascript:onRefer();" class="button">
      
  </form>
  <%
  }
  else{
  %>
    <table border="0" width="100%" cellspacing="1" cellpadding="1" align=center>
      <tr>
        <td width="60%" align="center" class="table_title">
          &lt;&nbsp;<%=step.getName()%>&nbsp;&gt;<bean:message key="itemlist.tawwpyear.labNoCheckUser" />
        </td>
      </tr>
    </table>
  <%
    }
  }
  %>
<%@ include file="/common/footer_eoms.jsp"%>