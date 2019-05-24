<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>

<script language="JavaScript">
function onflag()
{
var flag=document.planForm.flag.value;
window.navigate("timeflag.do?flag="+flag);
}
</script>


<form method="post" action="load.jsp" name="planForm">


<table class="formTable middle">
<caption>月作业计划上报</caption> 

<tr>
<td class="label">
月份
</td>
<td>
 <select size='1' name='year'>
          <%
            for(int i=2003; i<2011; i++){
              if( i == Integer.parseInt(StaticMethod.getCurrentDateTime("yyyy")) ){
          %>
          <option value=<%=i%> selected="selected"><%=i%></option>
          <%
              }else{
          %>
          <option value=<%=i%>><%=i%></option>
          <%
              }
            }
          %>
        </select>年'
        <select size='1' name='time'>
          <%
            for(int j=1; j<13; j++){
              if( j == Integer.parseInt(StaticMethod.getCurrentDateTime("MM")) ){
          %>
          <option value=<%=j%> selected="selected" ><%=j%></option>
          <%
              }else{
          %>
          <option value=<%=j%>><%=j%></option>
          <%
              }
            }
          %>
        </select>月
</td>

</tr>


<tr>
<td class="label">
操作类型
</td>

<td>
<input  type="radio" name="type" value="7" checked="checked" />生成月计划
<input  type="radio" name="type" value="9" />上报月计划
</td>
</tr>

<tr>
<td colSpan="2" >
<input type="submit" value="提交" name="B1"  Class="button">
</td>
</tr>

</table>
</form>

