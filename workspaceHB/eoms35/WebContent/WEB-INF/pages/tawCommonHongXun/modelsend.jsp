<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
	
	List comList = (List) request.getAttribute("comList");
	
%>
<style>
#tabs {
	width:90%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}
</style>
<script type="text/javascript">
var Tabs = {
    init : function(){
        var tabs = new Ext.TabPanel('tabs');
        tabs.addTab('form', '${eoms:a2u('短信发送')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>
<script language="javascript">

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawCommonHongXunForm'});
});
</script>
<div id="tabs">
<div id="form" class="tab-content">
<form action="${app}/hongxun/TawCommonHongXunAction/sendgaojin.do" method="post" 
	name="TawCommonHongXunForm">
<table border="0" width="95%" cellspacing="1">
       
	
 <tr class="tr_show">
		<td width="21%" class="clsfth">${eoms:a2u('短信发送机构添加')}</td>
		<td width="18%">
		  <div align="left">
		    <select size="12" name="netTreeIdDES" style="width:120">
		      <%for(int i=0;i<comList.size();i++){
          out.write("<option value='"+comList.get(i).toString()+"'>"+comList.get(i).toString()+"</option>");
          }
          
           %>
		      </select>
		      </div></td>
		<td width="11%"><div align="center">
            <input type=button class="clsbtn2" name="AddUserAll" value='${eoms:a2u('全加')}' onclick="javascript:add_all_user();">
            </div>
			<div align="center">
			<input type=button Class="clsbtn2" name="AddUser" value='${eoms:a2u('增加')}' onclick="javascript:add_user();">
          </div>
		   
		  <div align="center">
              <input type=button Class="clsbtn2" name="DelUser" value='${eoms:a2u('删除')}' onclick="javascript:del_user();">
            </div>
		  <div align="center">
              <input type=button Class="clsbtn2" name="DelUserAll" value='${eoms:a2u('全删')}' onclick="javascript:del_all_user();">
            </div>
		  <input type='hidden' name='selvalue' value=''>        </td>
		<td width="33%"><select name="seluser" size="12" style="width:100">
        </select></td>
        <td width="17%">&nbsp;</td>
    </tr>
   <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('时间')}</td>
		<td colspan=3>
	  <input id="endDate" name="endDate" type="text" class="text" readonly="readonly" onclick="popUpCalendar(this, this)"/>	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('地点1')}</td>
		<td colspan=3>
	  <eoms:comboBox name="h_bigadd" id="h_bigadd" initDicId="12101"
				sub="h_smadd" styleClass="select-class"
				 />	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('地点2')}</td>
		<td colspan=3>
	  <eoms:comboBox name="h_smadd" id="h_smadd" 
				 styleClass="select-class"
				 />	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('事件')}</td>
		<td colspan=3>
	  <eoms:comboBox name="h_bigev" id="h_bigev" initDicId="12102"
				sub="h_smev" styleClass="select-class"
				 />	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('事件明细')}</td>
		<td colspan=3>
	  <eoms:comboBox name="h_smev" id="h_smev" 
				 styleClass="select-class"
				 />	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('补充内容')}</td>
		<td colspan=3>
	<textarea name="content" cols="50" rows="8"></textarea>	   </td>
 </tr>
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
                    <html:button property="strutsButton" styleClass="clsbtn2" onclick="javascript:checksend();">
                     ${eoms:a2u('短信发送')}
                    </html:button>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</form>
</div>
<SCRIPT  LANGUAGE=javascript>var callerWindowObj = dialogArguments
 function netTreeId_all_move(src,des) {
 var sindex=src.selectedIndex;
 var oindex=src[sindex].value;
 var deslength=des.length;
 if(src[sindex].value !=-1) {
 if(sindex>=0){
 for(i=deslength-1;i>0; i--)
 des.options[i]=null;
 id="netTreeId_ID"+oindex;
 value="netTreeId_Value"+oindex;
 var idstr = document.TawCommonHongXunForm[id].value;
 var valuestr = document.TawCommonHongXunForm[value].value;
 if (idstr!='') {
 des_id = new Array();
 des_value = new Array();
 des_id = idstr.split(",");
 des_value = valuestr.split(",");
 var desnum=des_id.length;
 for(i=0;i<desnum;i++) {
 des.options[i]=new Option(des_value[i],i);
 des.options[i].value=des_id[i];
 }}}
 }
 else {
 for(i=deslength-1;i>=0; i--)
 des.options[i]=null;
 }
 return false;
 }
function add_user() {

if (document.TawCommonHongXunForm.netTreeIdDES.selectedIndex>-1) {
selected_spr_text=document.TawCommonHongXunForm.netTreeIdDES.options[document.TawCommonHongXunForm.netTreeIdDES.selectedIndex].text;
selected_spr_value=document.TawCommonHongXunForm.netTreeIdDES.options[document.TawCommonHongXunForm.netTreeIdDES.selectedIndex].value;
var sel_sprlen=document.TawCommonHongXunForm.seluser.options.length-1; 
var exist_flag=1;
var j=0;
for(j=0;j<=sel_sprlen;j++) {
if(document.TawCommonHongXunForm.seluser.options[j].value==selected_spr_value) {
exist_flag=0;
break; } }
if(exist_flag) { 
var test1=new Option(selected_spr_text);
test1.value=selected_spr_value;
document.TawCommonHongXunForm.seluser.options[++sel_sprlen]=test1; } 
else alert('${eoms:a2u('该人员已存在于右边列表中！请重新选择')}'); 
} 
}



function add_all_user() {
var sel_sprlen0=document.TawCommonHongXunForm.netTreeIdDES.options.length-1;
for(i=0;i<=sel_sprlen0;i++) {
selected_spr_text=document.TawCommonHongXunForm.netTreeIdDES.options[i].text;
selected_spr_value=document.TawCommonHongXunForm.netTreeIdDES.options[i].value;
var sel_sprlen=document.TawCommonHongXunForm.seluser.options.length-1;
var exist_flag=1;
var j=0;
for(j=0;j<=sel_sprlen;j++) {
if(document.TawCommonHongXunForm.seluser.options[j].value==selected_spr_value) { 
exist_flag=0;
break; } }
if(exist_flag) { 
var test1=new Option(selected_spr_text);
test1.value=selected_spr_value;
document.TawCommonHongXunForm.seluser.options[++sel_sprlen]=test1; } 
else alert('${eoms:a2u('该人员已存在于右边列表中！请重新选择')}'); } }
function del_user() 
{var sel_sprindex=document.TawCommonHongXunForm.seluser.selectedIndex;
if(sel_sprindex!=-1)document.TawCommonHongXunForm.seluser.options[sel_sprindex]=null; 
}
function del_all_user() 
{var sel_sprlen=document.TawCommonHongXunForm.seluser.options.length-1;
var j=0;for(j=sel_sprlen;j>=0;j--) 
{document.TawCommonHongXunForm.seluser.options[j]=null; 
} }
function checkForm() 
{var objOptions = document.TawCommonHongXunForm.seluser.options;var i;
var sValue;var sValuestr;
var bFirst = true;
for( i=0; i<objOptions.length; i++ )
{if( bFirst ){sValue = objOptions[i].value;sValuestr = objOptions[i].text;
bFirst = false; } 
else { sValue += ","+objOptions[i].value;sValuestr += ","+objOptions[i].text; 
}  
}
if( bFirst )
{alert('${eoms:a2u('请选择需要发送短信的机构组名称！')}');
return false; }
document.TawCommonHongXunForm.selvalue.value = 
sValue;callerWindowObj.document.forms[0].accompanyName.value=sValuestr;
callerWindowObj.document.forms[0].accompany.value= sValue;window.close(); 
} 

function checksend() {
var objOptions = document.TawCommonHongXunForm.seluser.options;var i;
var sValue;var sValuestr;
var bFirst = true;
for( i=0; i<objOptions.length; i++ )
{if( bFirst ){sValue = objOptions[i].value;sValuestr = objOptions[i].text;
bFirst = false; } 
else { sValue += ","+objOptions[i].value;sValuestr += ","+objOptions[i].text; 
}  
}
if( bFirst )
{alert('${eoms:a2u('请选择需要发送短信的机构组名称！')}');
return false; }

document.TawCommonHongXunForm.selvalue.value = sValue;


aForm=document.TawCommonHongXunForm;

if(aForm.endDate.value==""){
alert('${eoms:a2u('请填写时间')}');
return false;
}
if(aForm.h_bigadd.value==""){
alert('${eoms:a2u('请填写地点')}');
return false;
}
//if(aForm.h_smadd.value==""){
//alert('${eoms:a2u('请填写地点1')}');
//return false;
//}
if(aForm.h_bigev.value==""){
alert('${eoms:a2u('请填写事件')}');
return false;
}
if(aForm.h_smev.value==""){
alert('${eoms:a2u('请填写事件明细')}');
return false;
}
if(confirm('${eoms:a2u('确认发送信息吗？')}'))
    aForm.submit();
  else
    return false;
    

}
</SCRIPT>
  <div id="info">
    ${eoms:a2u('短信发送机构添加,时间,地点,(地点1),事件,事件明细必须填写，所有机构为发送该机构下的所有成员。短信收取格式为：时间+地点+(地点1)+事件+事件明细+补充内容，补充内容可选填，其中短信超过60字分为两条短信')}
  </div>
</div>
</body>
</html>
