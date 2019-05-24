<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpNetVO"%>

<%
  String yearPlanId = (String)request.getAttribute("yearplanid");
  String monthFlag = (String)request.getAttribute("monthid");
  TawwpNetVO tawwpNetVO = null;
%>

<SCRIPT LANGUAGE=javascript>
function GoBack()
{
  window.history.back();
}

function add_RoomTrue()
{
  if (document.all.form1.sOper.selectedIndex>-1)
  {
    selected_spr_text=document.all.form1.sOper.options[document.all.form1.sOper.selectedIndex].text;
    selected_spr_value=document.form1.sOper.options[document.form1.sOper.selectedIndex].value;
    var sel_sprlen=document.form1.sOper_O.options.length-1;
    var exist_flag=1;
    var j=0;
    for(j=0;j<=sel_sprlen;j++)
    {
      if(document.form1.sOper_O.options[j].value==selected_spr_value)
      {
        exist_flag=0;
        break;
      }
    }
    if(exist_flag)
    {
      var test1=new Option(selected_spr_text);
      test1.value=selected_spr_value;
      document.all.sOper_O.options[++sel_sprlen]=test1;
    }
    else alert("''" +selected_spr_text + "''  "+"<bean:message key="netselect.tawwpmonth.warnReset" />");
  }
}

function add_MoreTrue()
{
  if (document.all.form1.sOper.selectedIndex>-1)
  {
    var j=0;
    var k=0;
    for(k=0;k<document.form1.sOper.options.length;k++){
      if(document.form1.sOper.options[k].selected == true)
      {
        var exist_flag=1;
        selected_spr_text=document.all.form1.sOper.options[k].text;
        selected_spr_value=document.form1.sOper.options[k].value;
        var sel_sprlen=document.form1.sOper_O.options.length-1;
        for(j=0;j<=sel_sprlen;j++)
        {
          if(document.form1.sOper_O.options[j].value==selected_spr_value)
        {
          exist_flag=0;
          break;
        }
      }
      if(exist_flag)
      {
        var test1=new Option(selected_spr_text);
        test1.value=selected_spr_value;
        document.all.sOper_O.options[++sel_sprlen]=test1;
      }
      else
        alert("''" +selected_spr_text + "''  "+ "<bean:message key="netselect.tawwpmonth.warnReset" />");
      }
    }
  }
}


function add_all_RoomTrue()
{
  var sel_sprlen=document.form1.sOper_O.options.length-1;
  var j=0;
  for(j=sel_sprlen;j>=0;j--)
  {
    document.all.form1.sOper_O.options[j]=null;
  }
  sel_sprlen=document.form1.sOper.options.length-1;
  for(j=0;j<=sel_sprlen;j++)
  {
    var test1=new Option(document.all.form1.sOper.options[j].text);
    test1.value=document.form1.sOper.options[j].value;
    document.all.form1.sOper_O.options[j] = test1;
  }
}

function del_RoomTrue()
{
  var sel_sprindex=document.all.form1.sOper_O.selectedIndex;
  if(sel_sprindex!=-1)
    document.all.form1.sOper_O.options[sel_sprindex]=null;
}

function del_MoreTrue()
{
  var k=0;
  var temp=document.all.form1.sOper_O.options.length - 1;
  for(k=temp;k>=0;k--){
    if(document.form1.sOper_O.options[k].selected == true)
    {
      document.all.form1.sOper_O.options[k]=null;
    }
  }
}

function del_all_RoomTrue()
{
  var sel_sprlen=document.form1.sOper_O.options.length-1;
  var j=0;
  for(j=sel_sprlen;j>=0;j--)
  {
    document.all.form1.sOper_O.options[j]=null;
  }
}

function sent_room_user()
{

 if(confirm("确认生成所选月份月计划？"))
    {   
        var status=false; 
        var boxlength= document.getElementsByName("months").length;
       
        for(var i=0;i<boxlength;i++){
        	if(document.getElementsByName("months")[i].checked){
        	   status=true;
        	   break;
        	}
        }
        var temp = "";
		var sel_sprlen=document.form1.sOper_O.options.length-1;
		  for(j=0;j<=sel_sprlen;j++)
		  {
		    temp  = temp + document.form1.sOper_O.options[j].value + ",";
		  }
		  document.form1.netids.value = temp;
		  if(!status){
		    alert("请选择月份");
		    return false;
		  }
		  if(temp==""){
		    alert("<bean:message key="netselect.tawwpmonth.warnNoSelect" />");
		    return false;
		  }
		  document.form1.action="monthadd.do";
		  document.form1.submit();
    	 
    } 
 
}
function checkAll(){
 
	var e=document.getElementById("monthsAll"); 
	var cbxList = document.getElementsByName("months");
	for(var i=0;i<cbxList.length;i++){
       cbxList[i].checked=e.checked;
   }
}
</SCRIPT>

<form  name="form1" id="form1" method="post" >

<br>

<table cellSpacing="0" cellPadding="0" border="0">
<caption><bean:message key="netselect.tawwpmonth.formTitle" /></caption>
  <tr>
    <td>
    <bean:message key="netselect.tawwpmonth.labSelectMore" />
    </td>
  </tr>
  <tr>
    <td>
	    <table width="390" cellspacing="1" cellpadding="1" border="0">
	     
	     <tr height="50px">
	     <td colspan=3>月份: <input type="checkbox" mane="monthsAll" value="" onclick='checkAll()' id="monthsAll" />全选
 
	     </td>
	     </tr> 
	     <tr height="50px">
	     <td colspan=3> 
<%
	        for(int i=1;i<=12;i++){
	           if(i==7){
	             out.print("<br>");
	           }
	           out.print("<input type=checkbox name=months value='"+i+"'/>"+i);%>&#26376;
<%   		}
%>
	     </td>
	     </tr>
	      <tr height="50px">
	        <td colspan="3" align="center">
	          <bean:message key="netselect.tawwpmonth.formNet" />
	        </td>
	     </tr>
	     <tr>
	       <td width="40%" align="center">
	
	       <%
	         List netVOList = new ArrayList();
	         if(request.getAttribute("netvolist")!=null){
	         	netVOList = (ArrayList)request.getAttribute("netvolist");
	         }
	       %>
	
	         <b><bean:message key="netselect.tawwpmonth.formNetList" /></b>
	         <select size=15 name="sOper" style="width:150" multiple="true" ondblclick=javascript:add_RoomTrue();>
<% 			if(request.getAttribute("isNull")==null||!request.getAttribute("isNull").equals("1")){
%>	         
	         <option value="0"><bean:message key="netselect.tawwpmonth.formNoNet" /></option>
<%          }
 %>
	         <%
		           for (int i=0;i<netVOList.size();i++)
		           {
		         %>
		
		         <option value="<%=((TawwpNetVO)netVOList.get(i)).getId()%>">
		         <%=((TawwpNetVO)netVOList.get(i)).getName()%>
		         </option>
		
		         <%
		          }
	           
	         %>
	         </select>
	       </td>
	       <td width="20%" align="center" valign = "top">
	         <br>
	         <br>
	         <br>
	         <input type="button" name="btnAddItem" value="<bean:message key="label.add1"/>" onclick=javascript:add_MoreTrue(); class="button">
	         <p>
	         <input type="button" name="btnDelItem" value="<bean:message key="label.remove1"/>" onclick=javascript:del_MoreTrue(); class="button">
	         <p>
	         <input type="button" name="btnAddAll" value="<bean:message key="label.alladd"/>" onclick=javascript:add_all_RoomTrue(); class="button">
	         <p>
	         <input type="button" name="btnDelAll" value="<bean:message key="label.alldel"/>" onclick=javascript:del_all_RoomTrue(); class="button">
	       </td>
	       <td width="40%" align="center">
	         <b><bean:message key="netselect.tawwpmonth.formAddNet" /></b>
	         <select size=15 name="sOper_O" style="width:150"  multiple="true" ondblclick=javascript:del_RoomTrue();>
	
	         </select>
	       </td>
	     </tr>
	     
	 	</table>
       </td>
     </tr>
</table>
<br>

<input type="hidden" size = 390 name = "netids" value = "">
<input type="hidden" size = 390 name = "yearplanid" value = "<%=yearPlanId%>">
<input type="hidden" size = 390 name = "monthid" value = "<%=monthFlag%>">
<input type="button" name="save" value="<bean:message key="label.save"/>" onclick="sent_room_user();" class="button">
<input type="button" value="<bean:message key="netselect.tawwpmonth.btnBack" />" name="B1" class="button" onclick="javascript:GoBack();">

</form>
<%@ include file="/common/footer_eoms.jsp"%>
