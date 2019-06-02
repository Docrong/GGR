<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearCheckVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearExecuteVO"%>
<%@ page import ="com.boco.eoms.workplan.flow.model.Step"%> 

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

function onPass()
{
  if( !confirm("<bean:message key="checkview.title.warnYearCheckPass" />") ) return;
  document.yearcheck.action= "checkpass.do?yearcheckid=<%=request.getParameter("yearcheckid")%>&yearplanid=<%=request.getParameter("yearplanid")%>";
  document.yearcheck.submit();
}

function onReject()
{
  if( !confirm("<bean:message key="checkview.title.warnYearCheckReject" />") ) return;
  document.yearcheck.action= "checkreject.do?yearcheckid=<%=request.getParameter("yearcheckid")%>&yearplanid=<%=request.getParameter("yearplanid")%>";
  document.yearcheck.submit();
}

</script>

<%
TawwpYearPlanVO tawwpYearPlanVO = (TawwpYearPlanVO)request.getAttribute("tawwpyearplan");
TawwpYearExecuteVO tawwpYearExecuteVO = null;
TawwpYearCheckVO tawwpYearCheckVO = null;
List list = null;
List checkList = null;

%>
<form name="modelexecutelist">

	<table width="100%" class="listTable">
		<caption>
			<bean:message key="checkview.title.yearFmTitle" />&lt;&nbsp;<%=tawwpYearPlanVO.getName()%>&nbsp;&gt;
		</caption>  
		<tr>
			<td width="10%" class="label"><bean:message key="checkview.title.yearFmSysTypeName" /></td>
            <td width="40%">
            	<%=tawwpYearPlanVO.getSysTypeName()%>
            </td>
            <td width="10%"  class="label"><bean:message key="checkview.title.yearFmNetTypeName" /></td>
            <td width="40%">
            	<%=tawwpYearPlanVO.getNetTypeName()%>
            </td>
        </tr>
        <tr>
            <td width="10%" class="label"><bean:message key="checkview.title.yearFmDeptName" /></td>
            <td width="40%">
            	<%=tawwpYearPlanVO.getDeptName()%>
            </td>
            <td width="10%" class="label"><bean:message key="checkview.title.yearFmCruserName" /></td>
            <td width="40%">
                <%=tawwpYearPlanVO.getCruserName()%>
            </td>
        </tr>
        <tr>
            <td width="10%" class="label"><bean:message key="checkview.title.yearFmCrtime" /></td>
            <td width="40%">
				<%=tawwpYearPlanVO.getCrtime()%>
            </td>
            <td width="10%" class="label"><bean:message key="checkview.title.yearFmNetList" /></td>
            <td width="40%">
                <%=tawwpYearPlanVO.getNetListName()%>
            </td>
		</tr>
		<tr>
			<td width="15%" class="label"><bean:message key="checkview.title.yearFmPlanContent" /></td>
            <td width="85%" colspan="4">
            	<%=tawwpYearPlanVO.getContent()%>
            </td>
        </tr>
        <tr>
            <td width="10%" class="label"><bean:message key="checkview.title.yearFmRemark" /></td>
            <td width="90%" colspan="4">
                <%=tawwpYearPlanVO.getRemark()%>
            </td>
		</tr>
	</table>

	<br>
	<br>

  <%
  list = tawwpYearPlanVO.getYearExecuteList();
  %>
	<table width="100%" class="listTable" id="executeTable">
		<caption>&lt;&nbsp;<%=tawwpYearPlanVO.getName()%>&nbsp;&gt;<bean:message key="checkview.title.executeFmTitle" /></caption>
		<thead>
			<tr class="header">
				<td width="100">
					<bean:message key="checkview.title.executeFmName" />
				</td>
				<td width="300">
					<bean:message key="checkview.title.executeFmCycle" />
				</td>
				<td width="100">
					<bean:message key="checkview.title.executeFmFormat" />
				</td>
				<td width="300">
					<bean:message key="checkview.title.executeFmForm" />
				</td>
				<td class="label">
			        是否必须上传附件
			    </td>
			    <td class="label">
			        业务类型
			    </td>
       			<td class="label">
       			    执行单位级别
     			 </td>
	       		<td class="label">
        			适用说明
      			</td>
			</tr>
		</thead>
		<tbody>
    <%
    for(int i=0; i<list.size(); i++){
      tawwpYearExecuteVO = (TawwpYearExecuteVO)list.get(i);
    %>
		<tr>
			<td width="200">
				<%=tawwpYearExecuteVO.getName()%>
			</td>
			<td width="50">
				<%=tawwpYearExecuteVO.getCycleName()%>
			</td>
			<td width="150">
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
			<%=tawwpYearExecuteVO.getFileFlagName() %> 
			</td>
		  <td width="200"  >
        	<%=tawwpYearExecuteVO.getBotype()%> 
      	  </td>
         <td width="100"  >
	        <%=tawwpYearExecuteVO.getExecutedeptlevel()%>  
	    </td>
      	 <td width="100"  >
	        <%=tawwpYearExecuteVO.getAppdesc()%>  
	      </td>
		</tr>
    <%
    }
    %>
    	</tbody>
	</table>
	<br>
</form>
<br>

<table width="100%" class="listTable" id="checkTable">
	<caption><bean:message key="checkview.title.checkFmTitle" /></caption>
	<thead>
	<tr class="header">
		<td width="80">
			<bean:message key="checkview.title.checkFmCheckUser" />
		</td>
		<td width="100">
			<bean:message key="checkview.title.checkFmCheckTime" />
		</td>
		<td width="300">
			<bean:message key="checkview.title.checkFmCheckContent" />
		</td>
		<td width="50">
			<bean:message key="checkview.title.checkFmState" />
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

  if(step!=null){
    if(step.getCheckUserIdStr() != null &&!step.getCheckUserIdStr().equals("")){
%>
    <form name="yearcheck" method="post" action="" >
    <table class="listTable">
      <tr width="700" align="center">
        <td width="700" align="center" >
          <bean:message key="checkview.title.checkFmCheckUsers" />&lt;&nbsp;<%=tawwpYearPlanVO.getCheckUsers()%>&nbsp;&gt;
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
          <input type="hidden" name="checkuser" value="<%=step.getCheckUserIdStr()%>">
          <input type="hidden" name="flowserial" value="<%=step.getFlowSerial()%>">
          <input type="hidden" name="deptserial" value="<%=step.getSerial()%>">
        </td>
      </tr>
    </table>
    
    <br>

    <table width="100%">
      <caption><bean:message key="checkview.title.checkFmContent" /></caption>
      <tr>
        <td width="100%">
          <textarea name="content" class="textarea max"></textarea>
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
         <input type="button" value="<bean:message key="checkview.title.btnPass" />" onclick="javascript:onPass();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnReject" />" onclick="javascript:onReject();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
        </td>
      </tr>
    </table>
  </form>
  <%
  }
  else{
  %>
    <table width="100%" class="listTable">
      <tr>
        <td width="60%" align="center">
          &lt;&nbsp;<%=step.getName()%>&nbsp;&gt;<bean:message key="checkview.title.noCheckUser" />
        </td>
      </tr>
    </table>
  <%
    }
  }
  else{
  %>
    <form name="yearcheck" method="post" action="" >
    <br/>
    <br/>
    <table width="100%" class="listTable">
      <tr>
        <td width="100%">
          <textarea name="content" class="textarea max"></textarea>
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
         <input type="button" value="<bean:message key="checkview.title.btnPass" />" onclick="javascript:onPass();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnReject" />" onclick="javascript:onReject();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
        </td>
      </tr>
    </table>
  </form>
  <%
  }
  %>
  
<%@ include file="/common/footer_eoms.jsp"%>