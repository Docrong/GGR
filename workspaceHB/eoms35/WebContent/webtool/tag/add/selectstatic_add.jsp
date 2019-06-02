<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page autoFlush="true" %>
<%
String app = request.getContextPath(); 
String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");
int n=0;
 %>
<script language="javascript">

var k = 3;

/**
 * 关闭
 */
function bt(){
    window.close();
}

/**
 * 提交check
 */
function checkInput(){
   //下拉框名称不能为空
   var nameValue = form1.name.value;
   if(nameValue == null || nameValue.length==0) {
      alert("下拉框名称不能为空!");
      document.form1.name.focus();
      return false;
   }       
    // 参数名称不能为空
    if(form1.value!=null){
        if(form1.value.value==''){
            if(form1.text.value=="" || form1.text.value==null) {
                alert("'文本'不能为空!");
                form1.text.focus();
                return false;
            }
            if(form1.value.value=="" || form1.value.value==null) {
                alert("'值'不能为空!");
                form1.value.focus();
                return false;
            }
        }else{
            var paraNames = form1.text;
            var values= form1.value;
            for(i=0;i<paraNames.length;i++){
                if(paraNames[i].value=="" || paraNames[i].value==null){
                    alert("'文本'不能为空!");
                    paraNames[i].focus();
                    return false;
                }
                if(values[i].value=="" || values[i].value==null){
                    alert("'值'不能为空!");
                    values[i].focus();
                    return false;
                }
            }
        }
    }
}

/**
 * 增加参数
 */
function addPara(a) {
    if(a>k) {
        k=a;
    }
    var idVal = "str" + k;

    Para.innerHTML = Para.innerHTML  + 
    			'<span id ="'+idVal+'">'+
    			'<table class="form_table">'+
    			'<tr><td nowrap>值：<br/><input name="value"  type="text" value="" size="10" maxlength="10" class="smallInput"></td>'+
    			'<td nowrap>文本：<br/><input name="text" type="text" value="" size="10" maxlength="10" class="smallInput"></td>'+
    			'<td nowrap>提交地址：</br><input type="text" value="" size="10" maxlength="50" id="onpick'+k+'" name="onpick" class="smallInput">'+
    			
    			'</td>'+
    			'<td class="nobg"><input name="del" type="button" value="删除" onClick="delOpt('+idVal+')" class="submit"></td></tr>'+
    			'</table></span>';
    flags.innerHTML ='<input type="hidden" name="i_flag" value="'+k+'">';
    k++;
}

/**
 * 删除指定参数
 */
function delOpt(str) {
    if(confirm("您确实要删除该项吗?")){
        str.innerHTML ='';
    }
}

/**
 * 选择按钮
 */
function btt1(con){
    var retValue = window.showModalDialog("WapParamAllPop.jsp",
        "","help:0;resizable:0;status=0;scrollbars=0;dialogWidth=25;dialogHeight=30;center=true");

    if(retValue == null || retValue == ''){
        return;
    }

    con.value = con.value + retValue;
}

</script>


<html>
<head>
<title>管理员WAP配置工具</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css" />
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="save"	>
  <input name="tagType" type="hidden" value="<%=tagType %>"	>
  
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapSelectStaticTag">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
<table width="100%">
  <tr>
    <td class="title">增加一个静态下拉框</td>
    <td align="right"></td>
  </tr>
</table>
<table class="form_table">
	<tr>
		<th>下拉框名称：</th>
		<td>
			<input id="name" name="name" type="text" value=""  size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr >
		<th>是否为多选：</th>
        <td>
			<select name="multiple">
              <option value="false" >单选</option>
              <option value="true">多选</option>
            </select>
        </td>
	</tr>
	<tr>
		<th>新增option参数：</td>
		<td>
			<input type="button" name="btadd" value="新增" onclick="javascript:addPara(form1.i_flag.value)">
			<span id ="Para"></span>
            <span id ="flags"><input type="hidden" name="i_flag" value="<%=n%>"></span>
		</td>
	</tr>
</table>

<table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit"  value="提交"></td></tr>
</table>
</form>
</body>
</html>
