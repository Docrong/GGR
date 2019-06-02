<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>

<%
  List monthPlanVOList = (List)request.getAttribute("monthplanvolist");
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('list-table');
})

function onMonthPlanList()
{
  var itemlist = "";
  var flag = true;

  if(document.forms[0].planid.length != null){
    for(var i =0; i<document.forms[0].planid.length ; i++)
    {
      if(document.forms[0].planid[i].checked)
      {
        if(flag)
        {
          itemlist = document.forms[0].planid[i].value;
          flag = false;
        }
        else
        {
          itemlist = itemlist + "," + document.forms[0].planid[i].value;
        }
      }
    }
  }
  else{
    if(document.forms[0].planid.checked){
      itemlist = document.forms[0].planid.value;
    }
  }
   
  if(itemlist == ""){
    alert("<bean:message key="checklist.title.warnMonth" />");
  }
  else{
    location.href="../tawwpmonth/checklistpass.do?monthcheckidstr="+ itemlist;
  }
}
//全选/反选开关
function selectall(){
  var selobj = document.forms[0].planid;
  if(selobj.length != null){
    for(i=0;i<selobj.length;i++){
      if(selobj[i].checked==true)
        selobj[i].checked = false;
      else
        selobj[i].checked = true;
    }
  }
  //增加了只有一项执行内容的情况
  else{
    if(selobj.checked==true)
      selobj.checked = false;
    else
      selobj.checked = true;
  }
}
</script>

<!--  body begin  -->

<form name="monthplan">

  <table width="700" class="listTable" id="list-table">
    <caption><bean:message key="checklist.title.formMonthTitle" /></caption>
    <thead>
    <tr>
      <td width="5%">
        <input type="checkbox" name="" onclick="selectall();">选择
      </td>
      <td width="15%">
        <bean:message key="checklist.title.formPlanName" />
      </td>
      <td width="15%">
        <bean:message key="checklist.title.formDeptName" />
      </td>
      <td width="15%">
        <bean:message key="checklist.title.formMonthFlag" />
      </td>
      <td width="15%">
        <bean:message key="checklist.title.formConcernedNet" />
      </td>
      <td width="10%">
        <bean:message key="checklist.title.formCheck" />
      </td>
    </tr>
    </thead>
    <tbody>
    <%
      for(int i=0; i<monthPlanVOList.size(); i++){
        tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOList.get(i);
    %>
    <tr>
      <td width="5%">
        <input type="checkbox" name="planid" value="<%=tawwpMonthPlanVO.getMonthCheckId()%>">
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getDeptName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="checklist.title.formYear" />
        <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="checklist.title.formMonth" />
      </td>
      <td width="15%">
      <%
        if(tawwpMonthPlanVO.getNetName().equals("无网元")){
      %>
        <%=tawwpMonthPlanVO.getNetTypeName()%>
      <% }else{%>
        <%=tawwpMonthPlanVO.getNetName()%>
      <% }%>
      </td>
      <td width="10%">
        <a href="../tawwpmonth/checkview.do?monthcheckid=<%=tawwpMonthPlanVO.getMonthCheckId()%>&monthplanid=<%=tawwpMonthPlanVO.getId()%>">
         <img src="../images/bottom/an_pz.gif" width="19" height="18" align="absmiddle">
        </a>
      </td>
    </tr>
    <%
      }
    %>
    <tr>
      <td colspan="6">
        <input type="button" name="b1" value="批量通过" onclick="javascript:onMonthPlanList();" Class="button">
      </td>
    </tr>
    </tbody>
  </table>

</form>

<!--  body end  -->

<%@ include file="/common/footer_eoms.jsp"%>

