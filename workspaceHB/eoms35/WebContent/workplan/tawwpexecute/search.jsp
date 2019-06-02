<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script language="javascript">
function SubmitCheck(){
	var time1 = document.tawwpexecuteform.startDate.value;
	var time2 = document.tawwpexecuteform.endDate.value;
 if(time1==null||time1.length==0){
 	alert("请填写开始时间");
 }else if(time2==null||time2.length==0){
 	alert("请填写结束时间");
 }
 else{
		var setdate,settime,tmptime1,tmptime2;
		setdate = time1.split(" ")[0];
		settime = time1.split(" ")[1];
		tmptime1 = new Date(setdate.split("-")[0],setdate.split("-")[1] - 1,setdate.split("-")[2],settime.split(":")[0],settime.split(":")[1]);	
		setdate = time2.split(" ")[0];
		settime = time2.split(" ")[1];
		tmptime2 = new Date(setdate.split("-")[0],setdate.split("-")[1] - 1,setdate.split("-")[2],settime.split(":")[0],settime.split(":")[1]);		
		var temp = tmptime2.getTime() - tmptime1.getTime();
		if(temp<0||temp==0){
		  alert("结束时间比起始时间小,请重新选择时间！"); 
		}else if(document.tawwpexecuteform.content.value==null||document.tawwpexecuteform.content.value.length==0){
			alert("请填写查询内容");
		}else{
		   document.tawwpexecuteform.submit();
	 }
  }
}

</script>

<form name="tawwpexecuteform" method="POST" action="../tawwpexecute/searchResult.do" > 
  <table width="700" align=center style="margin:0pt 0pt 2pt 0pt">
    <tr>
      <td width="700" align="center" valign="middle" class="table_title">
       作业计划内容查询
      </td>
    </tr>
  </table>
  <table width="500" border="0"  cellspacing="1" cellpadding="1" class="table_show" align=center>
  
  <tr class="tr_show">
    <td class="td_label">
       开始时间：
    </td>
    <td>
      <input type="text" name="startDate" size="20" onclick="popUpCalendar(this, this);" readonly="readonly" class="text">
    </td>
  </tr>
  
  <tr class="tr_show">
    <td class="td_label">
      结束时间：
    </td>
    <td>
      <input type="text" name="endDate" size="20" onclick="popUpCalendar(this, this);" readonly="readonly" class="text">
    </td>
  </tr>

  <tr class="tr_show">
    <td class="td_label">
      查询内容：
    </td>
    <td>
      <input type="text" name="content" size="40" styleClass="clstext">
    </td>
  </tr>

  <tr class="tr_show">
    <td align="center"  width="500" colSpan="2" >
      <input type="button" value="查询" name="B1" Class="submit" onclick="SubmitCheck()">
    </td>
  </tr>

</table>

</form>

<%@ include file="/common/footer_eoms.jsp"%>