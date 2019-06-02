<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearPlanVO"%>

<%
  List list = (List)request.getAttribute("yearplanvolist");
  TawwpYearPlanVO tawwpYearPlanVO = null;
%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('list-table');
})

function onYearPlanList(){
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
  }else{
    if(document.forms[0].planid.checked){
      itemlist = document.forms[0].planid.value;
    }
  }
  if(itemlist == ""){
    alert("<bean:message key='checklist.title.warnYear' />");
  }
  else{
    location.href="../tawwpyear/checklistpass.do?yearcheckidstr="+ itemlist;
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

<form name="yearplan">

  <table width="700" class="listTable" id="list-table">
    <caption><bean:message key="checklist.title.formYearTitle" /></caption>
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
        <bean:message key="checklist.title.formYearFlag" />
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
    for(int i=0; i<list.size(); i++) {
      tawwpYearPlanVO = (TawwpYearPlanVO)list.get(i);
    %>
    <tr>
      <td width="5%">
        <input type="checkbox" name="planid" value="<%=tawwpYearPlanVO.getYearCheckId()%>">
      </td>
      <td width="15%">
        <%=tawwpYearPlanVO.getName()%>
      </td>
      <td width="15%">
        <%=tawwpYearPlanVO.getDeptName()%>
      </td>
      <td width="15%">
        <%=tawwpYearPlanVO.getYearFlag()%>
        <bean:message key="checklist.title.formYear" />
      </td>
      <td width="15%">
        <%=tawwpYearPlanVO.getNetListName()%>
      </td>
      <td width="5%">
        <a href="../tawwpyear/checkview.do?yearcheckid=<%=tawwpYearPlanVO.getYearCheckId()%>&yearplanid=<%=tawwpYearPlanVO.getId()%>">
         <img src="../images/bottom/an_pz.gif" width="19" height="18" align="absmiddle">
        </a>
      </td>
    </tr>
    <%
      }
    %>
    <tr>
      <td colspan="6">
        <input type="button" name="b1" value="批量通过" onclick="javascript:onYearPlanList();" Class="button">
      </td>
    </tr>
    </tbody>
  </table>
</form>

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>
 

