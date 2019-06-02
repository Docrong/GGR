<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.lang.String"%>
<%@ page import="java.util.List"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<script language="javascript" type="text/javascript">
function checkForm(){
var hlrName = document.getElementById("hlrName").value;
var hlrSignalId = document.getElementById("hlrSignalId").value;
var hlrId = document.getElementById("hlrId").value;
  if(hlrName == ''){
  	alert('请输入HLR名称！');
        document.getElementById("hlrName").select();
        return false;
  }
  
   if(document.getElementById("hlrId").value == ''){
  	alert('请输入HLR ID！');
        document.getElementById("hlrId").select();
        return false;
  }
  if(hlrSignalId.value == ''){
  	alert('请输入HLR信令点！');
        document.getElementById("hlrSignalId").select();
        return false;
  }else{
  	//alert(hlrSignalId);
  	Ext.Ajax.request({
  		form: "",
		method:"post",
		url: "bureaudataHlr.do?method=checkForm&hlrSignalId="+hlrSignalId,
		success: function(v,c){
			var returnValue = v.responseText
			//alert(returnValue);
			if(returnValue=='0'){
			  	alert('您输入HLR信令点已存在！');
			  	document.getElementById("hlrSignalId").select();
			  	return false;
			}else{
				document.forms[0].action="bureaudataHlr.do?method=save";
				document.forms[0].submit()
			}
 		}
    });
  }
}
</script>
<html:form action="/bureaudataHlr.do?method=save" styleId="theform" >
<div align="center">
  <center>
<br>
<table class="formTable" >
  <tr>
    <td class= "label" align="center">
        HLR数据</td>
  </tr>
</table>


<table class="formTable" >
   <tr>
		<td  class= "label">HLR名称</td>
		<td ><input type="text" class="text" id="hlrname"  name="hlrname"></td>
	</tr>
   <tr>
		<td  class= "label">HLR信令点</td>
		<td ><input type="text" class="text" id="hlrname"  name="hlrsignalid"></td>
	</tr>
   <tr>
		<td  class= "label">HLR ID</td>
		<td ><input type="text" class="text" id="hlrname" name="hlrid"></td>
	</tr>

	</table>
<table class="formTable" >
  <tr>
    <td width="100%" height="32" align="right">
    	<input type="button" onclick="return checkForm();" class="btn" value="保存"> 
&nbsp;
      <html:reset styleClass="btn">重置
      </html:reset>&nbsp;&nbsp;</td>
  </tr>
</table>
  </center>
</div>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
