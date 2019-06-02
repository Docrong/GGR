<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.util.*"%>
 
<head>
<style type="text/css">
 
select.menuBox2{
	width:300px;
}     
 
</style>
<SCRIPT LANGUAGE=javascript>
 function checkform(){
   var workfrom="";
   var workto="";
   var lengthfrom = document.forms[0].applyfrom.length;
   var lengthto = document.forms[0].applyto.length;
  
   if(lengthfrom==0||lengthto==0){
    	alert("${eoms:a2u('您不能申请')}");
    	return false;
    }else if(lengthfrom!=lengthto){
		alert("${eoms:a2u('您不能申请')}");
		return false;
	} else {
	 
    for (i=0;i<lengthfrom;i++){
	workfrom =  workfrom + "@" + document.forms[0].applyfrom.options[i].value;
	} 
    
  
    for (i=0;i<lengthto;i++){
	workto =  workto + "@" + document.forms[0].applyto.options[i].value;
	}
	 
	document.forms[0].action="../TawRmChangeduty/save_batchApply.do?workapplyfrom="+workfrom+"&workapplyto="+workto;
	document.forms[0].submit();
	}
 
  
}

function add_from(obj)
{	
   var selected_spr_text_from = "";
   
   var  selected_spr_value_from=obj.value;

 
   var sel_sprlen=document.forms[0].applyfrom.options.length-1;

   var exist_flag=1;
   var j=0;
   for(j=0;j<=sel_sprlen;j++)
   {
     if(document.forms[0].applyfrom.options[j].value==selected_spr_value_from)
     {
       exist_flag=0;
       break;
     }
   }

   if(exist_flag)
   {
     if (sel_sprlen >= 0){
     	var i=0;
        var k=0;
     	for(j=0;j<=sel_sprlen;j++)
     	{
     		if(selected_spr_value_from > document.forms[0].applyfrom.options[j].value)
     		{
                    i=j;
                    k=1;
     		}
     	}

        if (k==0){
            i--;
        }
        for (j=sel_sprlen;j>i;j--)
        {
 		  selected_spr_text_from = document.forms[0].applyfrom.options[j].text;
     		  var test1 = new Option(selected_spr_text_from);
     		  test1.value=document.forms[0].applyfrom.options[j].value;
     		  document.forms[0].applyfrom.options[j+1]=test1;
 	 }
         var test1=new Option(selected_spr_value_from);
       	 test1.value=selected_spr_value_from;
         document.all.applyfrom.options[i+1]=test1;

     }else{
     	var test1=new Option(selected_spr_value_from);
     	test1.value=selected_spr_value_from;
     	document.all.applyfrom.options[0]=test1;
     }

   }
   else{ 
      
	var object = document.forms[0].applyfrom;
	for(var i=0;i<object.length;i++){
		var valueform = object.options[i].value;
		if(valueform==selected_spr_value_from){
		document.forms[0].applyfrom.options[i]=null;
		}
	  }
    }
    
}



function add_to(obj)
{	
   var selected_spr_text = "";
   
   var  selected_spr_value=obj.value;
   var form = document.forms[0];
   var sel_sprlen=document.forms[0].applyto.options.length-1;

   var exist_flag=1;
   var j=0;
   for(j=0;j<=sel_sprlen;j++)
   {
     if(document.forms[0].applyto.options[j].value==selected_spr_value)
     {
       exist_flag=0;
       break;
     }
   }

   if(exist_flag)
   {
     if (sel_sprlen >= 0){
     	var i=0;
        var k=0;
     	for(j=0;j<=sel_sprlen;j++)
     	{
     		if(selected_spr_value > document.forms[0].applyto.options[j].value)
     		{
                    i=j;
                    k=1;
     		}
     	}

        if (k==0){
            i--;
        }
        for (j=sel_sprlen;j>i;j--)
        {
 		  selected_spr_text = document.forms[0].applyto.options[j].text;
     		  var test1 = new Option(selected_spr_text);
     		  test1.value=document.forms[0].applyto.options[j].value;
     		  document.forms[0].applyto.options[j+1]=test1;
 	 }
         var test1=new Option(selected_spr_value);
       	 test1.value=selected_spr_value;
         document.all.applyto.options[i+1]=test1;

     }else{
     	var test1=new Option(selected_spr_value);
     	test1.value=selected_spr_value;
     	document.all.applyto.options[0]=test1;
     }

   }
   else{ 
   
	var object = document.forms[0].applyto;
	for(var i=0;i<object.length;i++){
		var valueform = object.options[i].value;
		if(valueform==selected_spr_value){
		document.forms[0].applyto.options[i]=null;
		}
	  }
    }
}
 

</SCRIPT>
</head>
<body leftmargin="0" topmargin="0">

<form method="post" >
<input type="hidden" name="workapplyfrom" value=""/>
<center>
<br>
  <!--1-->
<table cellpadding="0" cellspacing="0" border="0" width="700">
  <tr>
<td width="100%" align="center" class="table_title">

    &nbsp;<bean:message key="TawRmChangeduty.tablename"/>
<%
String saveflag = String.valueOf(request.getParameter("SAVEFLAG"));
if (saveflag.trim().equals("true"))
{
%>
<font color="red"><bean:message key="TawRmChangeduty.savesuccess"/></font>
<%
}
%>

  </td>
</tr>

<tr>
<td>
  
	<table cellpadding="1" cellspacing="1" border="0" width="100%" >
	<tr align="center" valign="middle">
	<td width="45%" valign = "top">
		<table cellpadding="1" cellspacing="1" border="0" width="100%" class="listTable">
		<tr class="tr_show">
		<td colspan=3 align="center" class="label"><b>
		<bean:message key="TawRmChangeduty.workserial1"/></b>
		</td>
		</tr>
		<tr class="td_label">
		<td class="label"><bean:message key="TawRmChangeduty.select"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.starttime"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.dutyman"/></td>
		</tr>
		<%
		Vector vector_from = new Vector();
		Vector getWorkserial_from = new Vector();
		Vector getStarttime_from = new Vector();
		Vector getDutyman_from = new Vector();
		Vector getUsername_from = new Vector();
		vector_from = (Vector)request.getAttribute("VECTORFROM");
		getWorkserial_from = (Vector)vector_from.elementAt(0);
		getStarttime_from = (Vector)vector_from.elementAt(1);
		getDutyman_from = (Vector)vector_from.elementAt(2);
		getUsername_from = (Vector)vector_from.elementAt(3);
		%>
		<%
		if (getWorkserial_from.size()>0){
		for (int i=0;i<getWorkserial_from.size();i++){
		%>
		<tr class="tr_show">
		<td>
		<%--<input type=button Class="button" name=add value =<bean:message key="entry.TawRmSysteminfo.btn_add"/> onclick="javascript:add_Time();">
		 --%><input  type="checkbox" name="radio_from" 	value="<%="from,"+String.valueOf(getWorkserial_from.elementAt(i))+","+String.valueOf(getDutyman_from.elementAt(i))+","+String.valueOf(getStarttime_from.elementAt(i))%>" onclick="javascript:add_from(this)";>
		 </td>
		<td>
		<%=String.valueOf(getStarttime_from.elementAt(i))%>
		</td>
		<td>
		<%=String.valueOf(getUsername_from.elementAt(i))%>
		</td>
		</tr>
		<%}%>
		<%}else{%>
		<tr class="tr_show">
		<td colspan=5>
		<bean:message key="TawRmChangeduty.alertnoreceiver"/>
		</td>
		</tr>
		<%}%>
		</table>
	</td>
	<td width="10%">>></td>
	<td width="45%" valign = "top" class="tr_show">
		<table cellpadding="1" cellspacing="1" border="0" width="800" class="listTable">
		<tr class="tr_show">
		<td colspan=3 align="center" class="label"><b>
		<bean:message key="TawRmChangeduty.workserial2"/></b>
		</td>
		</tr>
		<tr class="td_label">
		<td class="label"><bean:message key="TawRmChangeduty.select"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.starttime"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.dutyman"/></td>
		</tr>
		<%
		Vector vector_to = new Vector();
		Vector getWorkserial_to = new Vector();
		Vector getStarttime_to = new Vector();
		Vector getDutyman_to = new Vector();
		Vector getUsername_to = new Vector();
		vector_to = (Vector)request.getAttribute("VECTORTO");
		getWorkserial_to = (Vector)vector_to.elementAt(0);
		getStarttime_to = (Vector)vector_to.elementAt(1);
		getDutyman_to = (Vector)vector_to.elementAt(2);
		getUsername_to = (Vector)vector_to.elementAt(3);
		%>
		<%
		if (getWorkserial_to.size()>0){
		for (int i=0;i<getWorkserial_to.size();i++){
		%>
		<tr class="tr_show">
		<td>
		 <input  type="checkbox" name="radio_to" value="<%="to,"+String.valueOf(getWorkserial_to.elementAt(i))+","+String.valueOf(getDutyman_to.elementAt(i))+","+String.valueOf(getStarttime_to.elementAt(i))%>" onclick="javascript:add_to(this)";>
		 </td>
		<td>
		<%=String.valueOf(getStarttime_to.elementAt(i))%>
		</td>
		<td>
		<%=String.valueOf(getUsername_to.elementAt(i))%>
		</td>
		</tr>
		<%}%>
		<%}else{%>
		<tr class="tr_show">
		<td colspan=5>
		<bean:message key="TawRmChangeduty.alertnoreceiver"/>
		</td>
		</tr>
		<%}%>
		</table>
	</td>
	</tr>
		<tr class="tr_show">
		    <td width="45%">
					 
					<select name="applyfrom" size=5 class="menuBox2">
					</select>
				 
				</td>	
				<td width="10%">
				>>
				</td>
				<td width="45%">
					 
					<select name="applyto" size=5 class="menuBox2">
					</select>
				 
				</td>			 
</tr>
	</table>
</td>
 
</tr>

<tr align="center" valign="middle">
<td>
	      <table cellpadding="0" cellspacing="0" border="0" width="700" class="listTable">
            <tr>
              <td class="label">&nbsp;</td>
            </tr>
            <%if (getWorkserial_from.size()>0 && getWorkserial_to.size()>0){%>
            <tr>
              <td class="label"> <b><bean:message key="TawRmChangeduty.applyreson"/></b>
                <input type="text" name="reason" size="80" value="">
              </td>
            </tr>
            <tr>
              <td height="32" align="left">  
              <input type="button"  value = '<bean:message key="TawRmChangeduty.apply"/>' onclick="return checkform();"  class="button">
                  </td>
            </tr>
            <%}else{%>  
            <tr class="table_title">
              <td> <bean:message key="TawRmChangeduty.alertcannotapply"/> </td>
            </tr>
            <%}%>
          </table>
</td>
</tr>
</table>
  </center>
<%
int roomId = Integer.parseInt(String.valueOf(request.getAttribute("roomId")).trim());
String user_id = String.valueOf(request.getAttribute("USERID")).trim();
String time_from = String.valueOf(request.getAttribute("TIMEFROM")).trim();
String time_to = String.valueOf(request.getAttribute("TIMETO")).trim();
%>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name = "user_id" value = "<%=user_id%>">
<input type="hidden" name = "time_from" value = "<%=time_from%>">
<input type="hidden" name = "time_to" value = "<%=time_to%>">
</form>
</body>
<%@ include file="/common/footer_eoms.jsp"%>
