<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script language="JavaScript">
function SubmitCheck(){
    var time1 = document.form1.startdate.value;
    var time2 = document.form1.enddate.value;
    if(time1==null||time1.length==0){
      alert("请填写开始时间");
      return false;
    }
    else if(time2==null||time2.length==0){
      alert("'请填写结束时间");
      return false;
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
      if(temp<0){
        alert("结束时间比起始时间小,请重新选择时间！");
        document.form1.enddate.focus();
        return false;
      }else{
        return true;
      }
    }
}
</script>

<form name="form1" action="<%=request.getContextPath()%>/workplan/logShow/searchDone.do" method="post" onSubmit="return SubmitCheck()">
<table class="formTable middle">
    <caption>部省接口监控内容查询</caption>
		<tr>
			<td class="label">
				开始时间
			</td>
			<td class="content">
				<input type="text" name="startdate" size="20"
					onclick="popUpCalendar(this, this,null,null,null,true,-1);"
					readonly="readonly" class="text">
			</td>
		</tr>
		<tr>
			<td class="label">
				结束时间
			</td>
			<td class="content">
				<input type="text" name="enddate" size="20"
					onclick="popUpCalendar(this, this,null,null,null,true,-1);"
					readonly="readonly" class="text">
			</td>
		</tr>
		<tr>
            <td class="label">
                   日志类型
            </td>
            <td class="content">
                <input name="sheetId" type="text" id="sheetId">
            </td>
    </tr>    
  </table>
  <input type="submit" class="clsbtn2" value="查询"> 
</form>