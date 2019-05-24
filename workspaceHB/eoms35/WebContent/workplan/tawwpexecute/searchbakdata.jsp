<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script language="javascript">
function SubmitCheck(){
	var time1 = document.tawwpexecuteform.startDate.value;
	var time2 = document.tawwpexecuteform.endDate.value;
		
 if(time1==null||time1.length==0){
 	alert("开始时间不能为空");
 	
 }else if(time2==null||time2.length==0){
 	alert("结束时间不能为空");
 }else if(document.tawwpexecuteform.serialno.value==null||document.tawwpexecuteform.serialno.value.length==0){
			alert("网元编号不能为空");
 }else{
		   document.tawwpexecuteform.submit();
 }
}
</script>

<form name="tawwpexecuteform" method="POST" action="../tawwpexecute/BakdataResult.do" > 
  <table width="700" align=center style="margin:0pt 0pt 2pt 0pt">
    <tr>
      <td width="700" align="center" valign="middle" class="table_title">
       附加表内容查询
      </td>
    </tr>
  </table>

  <table width="500" border="0"  cellspacing="1" cellpadding="1" class="table_show" align=center>
  
  <tr class="tr_show">
    <td width="20%" class="td_label8">
      开始时间：
    </td>
    <td align="left">
      <input type="text" name="startDate" size="15" onclick="popUpCalendar(this, this);" readonly="readonly" class="text">
    </td>
    <td width="20%" class="td_label8">
      结束时间：
    </td>
    <td align="left">
      <input type="text" name="endDate" size="15" onclick="popUpCalendar(this, this);" readonly="readonly" class="text">
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label8">
      网元编号：
    </td>
    <td align="left"  width="500" colSpan="4">
      <input type="text" name="serialno" size="30" styleClass="clstext"> (例如：HEB_SJ_MSC1_G)
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label8">
      执行表格：
    </td>
    <td align="left"  width="500" colSpan="4">
     <select size="1" name="addonstable" style="width:300">
        <option value="8a9282a1084b47af0108509796240006" selected>交换-02-数据备份制作记录</option>
      </select>
    </td>
  </tr>
  <tr class="tr_show">
    <td align="middle"  width="500" colSpan="4" ><br/>
      <input type="button" value="查询" name="B1" Class="submit" onclick="SubmitCheck()">
      <br/>
    </td>
  </tr>
  </table>
</form>
