<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpModelExecuteVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpAddonsTableVO"%> 
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%> 

<script language="javascript">

function onForm()
{
  var _sHeight = 118;
  var _sWidth = 320;
  var sTop=(window.screen.availHeight-_sHeight)/2;
  var sLeft=(window.screen.availWidth-_sWidth)/2;
  var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:No;help: No; resizable: No; status: No;";
  window.showModalDialog("../manager/tawgzplanitem/formselect.jsp",window,sFeatures);
}

function SubmitCheck(){

  frmReg = document.tawwpitemedit;

  if(frmReg.name.value == ''){
    alert("<bean:message key="itemedit.tawwpmodel.warnInputName" />");
    return false;
  }
  if(frmReg.remark.value.length>200)
  {
    alert("执行帮助不能超过200个字符！");
    frmReg.remark.focus();
    return false;
  }
  return true;
}

</script>

<%

ArrayList addonsList=new ArrayList();
if(request.getAttribute("addonslist")!=null){
	addonsList=(ArrayList)request.getAttribute("addonslist");
}

TawwpModelExecuteVO tawwpModelExecuteVO = (TawwpModelExecuteVO)request.getAttribute("modelexecute");
%>
<form name="tawwpitemedit" method="post" action="../tawwpmodel/itemmodify.do?modelplanid=<%=request.getParameter("modelplanid")%>&modelexecuteid=<%=request.getParameter("modelexecuteid")%>" onsubmit='return SubmitCheck()'>

  <table class="formTable">
    <caption><bean:message key="itemedit.tawwpmodel.formTitle" /></caption>
   <tr>
      <td class="label">
         作业项目
      </td>
      <td>
        <input type="text" name="name" class="text" value="<%=tawwpModelExecuteVO.getName()%>" >
      </td>
    </tr>
    
    
    <tr>
      <td class="label">
         业务类型
      </td>
      <td width="400">
        <input type="text" name="botype" size="40" class="text" value="<%=tawwpModelExecuteVO.getBotype() %>">
      </td>
    </tr>
    <tr>
      <td class="label">
         执行单位级别
      </td>
      <td width="400">
        <input type="text" name="executedeptlevel" size="40" class="text" value="<%=tawwpModelExecuteVO.getExecutedeptlevel()%>">
      </td>
    </tr>
    <tr>
      <td class="label">
         适用说明
      </td>
      <td width="400">
        <input type="text" name="appdesc" size="40" class="text" value="<%=tawwpModelExecuteVO.getAppdesc() %>">
      </td>
    </tr>
    
    
    <tr>
      <td class="label">
         执行周期
      </td>
      <td>
       <select size='1' name='cycle' class='select' onchange='javascript:filter(this);'>
         <option  value='1'>天</option>
         <option  value='2'>周</option>
         <option  value='3'>半月</option>
         <option  value='4'>月</option>
         <option  value='8'>两月</option>
         <option  value='5'>季度</option>
         <option  value='9'>四月</option>
         <option  value='6'>半年</option>
         <option  value='7'>年</option>
         <option  value='0'>其他</option>
        </select>
        <br>
       	<%if(StaticMethod.null2String(tawwpModelExecuteVO.getCycle()).equals("2")){%>
       	<select size='1' name='executeDay' id='executeDay'  class='select'>
         <option  value=''>请选择</option>
         <option  value='1'>周一</option>
         <option  value='2'>周二</option>
         <option  value='3'>周三</option>
         <option  value='4'>周四</option>
         <option  value='5'>周五</option>
         <option  value='6'>周六</option>
         <option  value='7'>周日</option>
        </select>
        <%} else {%>
       	<select size='1' name='executeDay' id='executeDay'  class='select' style='display:none' >
         <option  value=''>请选择</option>
         <option  value='1'>周一</option>
         <option  value='2'>周二</option>
         <option  value='3'>周三</option>
         <option  value='4'>周四</option>
         <option  value='5'>周五</option>
         <option  value='6'>周六</option>
         <option  value='7'>周日</option>
        </select>
        <%}%>
      </td>
    </tr>
    <tr>
      <td class="label">
        <bean:message key="itemedit.tawwpmodel.formFormat" />
      </td>
      <td>
        <input type="text" name="format" size="40" class="text" value="<%=tawwpModelExecuteVO.getFormat()%>">
      </td>
    </tr>
    <tr>
      <td class="label">
         <bean:message key="itemedit.tawwpmodel.formHoliday" />
      </td>
      <td>
        <input type=radio name='isHoliday' value='0' 
<%   if(StaticMethod.null2String(tawwpModelExecuteVO.getIsHoliday()).equals("0")) {%>           
        checked
<%   } %>
         >是<input type=radio name='isHoliday' value='1'  
<%   if(StaticMethod.null2String(tawwpModelExecuteVO.getIsHoliday()).equals("1")) {%>           
        checked
<%   } %>         
        >否
      </td>
    </tr>
    <tr>
      <td class="label">
         <bean:message key="itemedit.tawwpmodel.formWeekend" />
      </td>
      <td>
        <input type=radio name='isWeekend' value='0' 
<%   if(StaticMethod.null2String(tawwpModelExecuteVO.getIsWeekend()).equals("0")) {%>           
        checked
<%   } %>  
		 > 是<input type=radio name='isWeekend' value='1' 
<%   if(StaticMethod.null2String(tawwpModelExecuteVO.getIsWeekend()).equals("1")) {%>           
        checked
<%   } %>  
		 
	     > 否
      </td>
    </tr>
    <tr>
      <td class="label">
        记录模版
      </td>
      <td>
        <select name='formid' value='0' class="select">
         <option  value='0' selected="selected">选择记录模版</option>
        <%
         for(int j=0;j<addonsList.size();j++){
           TawwpAddonsTableVO tawwpAddonsTableVO=(TawwpAddonsTableVO)addonsList.get(j);
           if(tawwpAddonsTableVO != null){
         %>
         <option value='<%=tawwpAddonsTableVO.getId()%>'><%=tawwpAddonsTableVO.getName()%></option>
         <%
         	}
         }
         %>
        </select>
      </td>
     </tr>
     <tr>
      <td class="label">
         是否必须上传附件
      </td>
      <td>
        <input type=radio name='fileFlag' value='1' 
<%   if(StaticMethod.null2String(tawwpModelExecuteVO.getFileFlag()).equals("1")) {%>
        checked
<%   } %>> 是
		<input type=radio name='fileFlag' value='0' 
<%   if(StaticMethod.null2String(tawwpModelExecuteVO.getFileFlag()).equals("0")) {%>
        checked
<%   } %>> 否
      </td>
     </tr>
     <tr>
      <td class="label">
       	执行帮助
      </td>
      <td>
        <textarea name="remark" class="textarea max"><%=tawwpModelExecuteVO.getRemark()%></textarea>
      </td>
    </tr>
    
    <tr>
      <td class="label">
       	指导文件
      </td>
      <td>
        <eoms:attachment idList="" idField="accessories" appCode="workplan" scope="request" name="modelexecute" property="accessories"/>
      </td>
    </tr>
    
    
    <tr>
      <td colspan='2'>
        <input type="submit" value="<bean:message key="itemedit.tawwpmodel.btnSubmit" />" name="B1"  Class="submit">
        <input type="button" value="<bean:message key="itemedit.tawwpmodel.btnBack" />" onclick="javascript:window.history.back();" class="button">
      </td>
    </tr>
  </table>

<script language="javascript">
document.forms[0].cycle.value = '<%=tawwpModelExecuteVO.getCycle()%>'
document.forms[0].executeDay.value = '<%=tawwpModelExecuteVO.getExecuteDay()%>'

<%
if(tawwpModelExecuteVO.getFormId() != null){
%>
document.forms[0].formid.value = '<%=tawwpModelExecuteVO.getFormId()%>'
<%
}
%>
</script>

</form>
<%@ include file="/common/footer_eoms.jsp"%>
<script type="text/javascript">
function filter(el){
  if(el.options[el.selectedIndex].value==2){
    eoms.$('executeDay').show();
  }
  else{
    eoms.$('executeDay').hide();
  }
}
</script>
