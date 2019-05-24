<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearCheckVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearExecuteVO"%> 
<%@ page import ="com.boco.eoms.workplan.flow.model.Step"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('executeTable');
})

Ext.onReady(function(){
	colorRows('chkTable');
})

function onYearPlanAdd()
{
  location.href="itemadd.do?yearplanid=<%=request.getParameter("yearplanid")%>";
}
  function onAdd(){
    document.form1.action="modeladd.do";
    document.form1.submit();
  }

function onRefer()
{
  document.yearrefer.action= "yearrefer.do?yearplanid=<%=request.getParameter("yearplanid")%>";
  document.yearrefer.submit();
}

</script>

<%
TawwpYearPlanVO tawwpYearPlanVO = (TawwpYearPlanVO)request.getAttribute("tawwpYearPlanVO");
TawwpYearExecuteVO tawwpYearExecuteVO = null;
TawwpYearCheckVO tawwpYearCheckVO = null;
List list = null;
List checkList = null;
%>
<form name="modelexecutelist">
<br>
<table width="700" class="listTable">
	<caption><bean:message key="queryyearplanresult.title.ypFmTitle"/>&lt;&nbsp;<%=tawwpYearPlanVO.getName()%>&nbsp;&gt;</caption>
    <tr>
    	<td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmSysType"/></td>
        <td width="250">
        	<%=tawwpYearPlanVO.getSysTypeName()%>
        </td>
        <td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmNetType"/></td>
        <td width="250">
            <%=tawwpYearPlanVO.getNetTypeName()%>
        </td>
	</tr>
    <tr>
		<td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmDeptName"/></td>
		<td width="250">
        	<%=tawwpYearPlanVO.getDeptName()%>
      	</td>
	    <td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmCruser"/></td>
	    <td width="250">
	    	<%=tawwpYearPlanVO.getCruserName()%>
	    </td>
    </tr>
    <tr>
      <td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmNetList"/></td>
      <td width="250">
         <%=tawwpYearPlanVO.getNetListName()%>
      </td>
      <td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmState"/></td>
      <td width="250">
        <%=tawwpYearPlanVO.getStateName()%>
      </td>
    </tr>
    <tr>
      <td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmContent"/></td>
      <td width="600" colspan="3">
        <%=tawwpYearPlanVO.getContent()%>
      </td>
    </tr>
    <tr>
      <td width="100" class="label"><bean:message key="queryyearplanresult.title.ypFmRemark"/></td>
      <td width="600" colspan="3">
        <%=tawwpYearPlanVO.getRemark()%>
      </td>
    </tr>
</table>
<br/>
<input type="button" value="<bean:message key="queryyearplanresult.title.btnBack"/>" onclick="javascript:window.history.back();" class="button">
<br>
<br>
<br>
  <%
  list = tawwpYearPlanVO.getYearExecuteList();
  %>
  <table width="700" class="listTable" id="executeTable">
    <caption>&lt;&nbsp;<%=tawwpYearPlanVO.getName()%>&nbsp;&gt;&nbsp;<bean:message key="queryyearplanresult.title.exeFmTitle"/></caption>
    <thead>
    <tr>
      <td width="300">
        <bean:message key="queryyearplanresult.title.exeFmName"/>
      </td>
      <td width="50">
        <bean:message key="queryyearplanresult.title.exeFmCycle"/>
      </td>
      <td width="150">
        <bean:message key="queryyearplanresult.title.exeFmFormat"/>
      </td>
      <td width="100">
        <bean:message key="queryyearplanresult.title.exeFmForm"/>
      </td>
    </tr>
    </thead>
    <tbody>
    <%
    for(int i=0; i<list.size(); i++){
      tawwpYearExecuteVO = (TawwpYearExecuteVO)list.get(i);
    %>
    <tr>
      <td width="300">
        <%=tawwpYearExecuteVO.getName()%>
      </td>
      <td width="50">
        <%=tawwpYearExecuteVO.getCycleName()%>
      </td>
      <td width="150">
        <%=tawwpYearExecuteVO.getFormat()%>
      </td>
      <td width="100">
        <%
        if(!tawwpYearExecuteVO.getFormId().equals("0")&&!tawwpYearExecuteVO.getFormId().equals("")&&tawwpYearExecuteVO.getFormId()!=null){
        %>
        <a target="_blank" href="../tawwpaddons/addonsread.do?action=read&window=new&myid=&model=50&addonsid=<%=tawwpYearExecuteVO.getFormId()%>&reaction=/tawwpaddons/addonslist.do"><%=tawwpYearExecuteVO.getFormName()%></a>
      	<%
        }
        %>
      </td>
    </tr>
    <%
    }
    %>
    </tbody>
  </table>
</form>

<br>
<br>
<br>
<form name="yearrefer" method="post" action="yeardispatch.do?yearplanid=<%=request.getParameter("yearplanid")%>" >
    <table width="700" class="listTable" id="chkTable">
      <caption><bean:message key="queryyearplanresult.title.chkFmTitle"/></caption>
      <thead>
      <tr>
        <td width="100">
          <bean:message key="queryyearplanresult.title.chkFmChkUser"/>
        </td>
        <td width="100">
          <bean:message key="queryyearplanresult.title.chkFmChkTime"/>
        </td>
        <td width="400">
          <bean:message key="queryyearplanresult.title.chkFmChkContent"/>
        </td>
        <td width="100">
          <bean:message key="queryyearplanresult.title.chkFmChkState"/>
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
        <td width="100">
          <%=tawwpYearCheckVO.getCheckUserName()%>
        </td>
        <td width="100">
          <%=tawwpYearCheckVO.getCrtime()%>
        </td>
        <td width="400">
          <%=tawwpYearCheckVO.getContent()%>
        </td>
        <td width="100">
          <%=tawwpYearCheckVO.getStateName()%>
        </td>
      </tr>
      <%
      }
      %>
      </tbody>
    </table>

  </form>
  
<%@ include file="/common/footer_eoms.jsp"%>

