<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<script language='javascript'>
    function onDay(){
    document.statday.submit();
    }
</script>

<%
TawwpMonthPlanVO tawwpMonthPlanVO = null;
List list = (List)request.getAttribute("netlist");
String dayTime = (String)request.getAttribute("dayTime");

%>

<form method="post" action="../tawwpnet/dayplanlist.do" name='statday'>
<table class="listTable" id="listTable" width="700">
<tr>
<td class="label" align='right'>
日期选择：
<input type="text" name="dayTime" size="10" onclick="popUpCalendar(this, this,null,null,null,false,-1);" readonly="readonly" class="text">
<input type='button' value='确定' name='B1' onclick='onDay()' class='button' >
</td>
</tr>
</table>
</form>
<table class="listTable" id="listBody" width="700">
      <caption><%=dayTime%><br/><br/>以下月度计划的相关附加表为空，请查看并重新填写</caption>
      <thead>
          <tr>
            <td width="150">网元名称</td>
            <td width="150">月度作业计划名称</td>
            <td width="150">所属部门</td>
          </tr>
      </thead>
      
      <tbody>
  <%
  if(list.size() > 0){
    for(int i=0; i<list.size(); i++){
          tawwpMonthPlanVO = (TawwpMonthPlanVO)list.get(i);
  %>
  <tr>
    <td>
      <%=tawwpMonthPlanVO.getNetName()%>
    </td>
    <td>
      <%=tawwpMonthPlanVO.getName()%>
    </td>
    <td>
      <%=tawwpMonthPlanVO.getDeptName()%>
    </td>
  </tr>
  <%
    }
    }
  else{
  %>
  <tr>
    <td colspan="3">
      该天数据已全部执行完毕
    </td>
  </tr>
  <%
  }
  %>
      </tbody>
</table>
