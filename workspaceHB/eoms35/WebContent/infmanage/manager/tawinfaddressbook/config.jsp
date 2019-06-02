<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<SCRIPT LANGUAGE=javascript>
<!--
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
      else alert("''" +selected_spr_text + "''  "+"<bean:message key="label.alerto"/>");
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
              alert("''" +selected_spr_text + "''  "+ "<bean:message key="label.alertr"/>");
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
var temp = "";
var sel_sprlen=document.form1.sOper_O.options.length-1;

for(j=0;j<=sel_sprlen;j++)
  {
     temp  = temp + document.form1.sOper_O.options[j].value + ";";
  }

        temp=temp.substring(0,temp.length-1)
        document.form1.operIds.value = temp;
	window.location.href='mailto:'+temp;
}
function toQuery(){
  window.location.href="mail.do";
}
//-->
</SCRIPT>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<form  name="form1" id="form1" method="post" >
<table cellSpacing="0" cellPadding="0" align="center" border="0">
<tr>
<td>


<table cellpadding="0" cellspacing="0" border="0" width="390">
  <tr>
<td>


<table width="390" cellspacing="1" cellpadding="1" border="0" class="table_show">
<tr class="tr_show" >
<td colspan="3" align="center">
    <bean:message key="TawInfAddressBook.groupMail"/>
</td>
</tr>

<tr class="tr_show">
<td width="40%" align="center">
<%
ArrayList operIds = new ArrayList();
ArrayList operName = new ArrayList();
operIds = (ArrayList)request.getAttribute("TAWOPERATES_ID");
operName = (ArrayList)request.getAttribute("TAWOPERATES_NAME");
%>
<b><bean:message key="TawInfAddressBook.maySelect"/></b>
<select size=25 name="sOper" style="width:250" multiple="true" ondblclick=javascript:add_RoomTrue();>
<%
for (int i=0;i<operIds.size();i++)
{
%>
<option value="<%out.print(operIds.get(i));%>">
    <%out.print(operName.get(i));%>
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
<br>
<br>
<br>
<br>
<br>
<br>
<input type="button" name="btnAddItem" value="<bean:message key="label.add1"/>" onclick=javascript:add_MoreTrue(); class="clsbtn2">
<p>
<input type="button" name="btnDelItem" value="<bean:message key="label.remove1"/>" onclick=javascript:del_MoreTrue(); class="clsbtn2">
<p>
<input type="button" name="btnAddAll" value="<bean:message key="label.alladd"/>" onclick=javascript:add_all_RoomTrue(); class="clsbtn2">
<p>
<input type="button" name="btnDelAll" value="<bean:message key="label.alldel"/>" onclick=javascript:del_all_RoomTrue(); class="clsbtn2">
</td>


<td width="40%" align="center">
<%
ArrayList operIds_O = new ArrayList();
ArrayList operName_O = new ArrayList();
operIds_O = (ArrayList)request.getAttribute("O_TAWOPERATES_ID");
operName_O = (ArrayList)request.getAttribute("O_TAWOPERATES_NAME");

%>
<b><bean:message key="TawInfAddressBook.receive"/></b>

<select size=25 name="sOper_O" style="width:250"  multiple="true" ondblclick=javascript:del_RoomTrue();>
<%
for (int i=0;i<operIds_O.size();i++)
{
%>
<option value="<%out.print(operIds_O.get(i));%>">
	<%out.print(operName_O.get(i));%>
</option>
<%
}
%>
</select>

</td>
</tr>
</tr>

  </table>
</td>
</tr>
  <tr>
<td>
<table height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="right" height="32">
<td>
<input type="button" name="save" value="<bean:message key="label.rigor"/>" onclick="toQuery()" class="clsbtn2">
<input type="button" name="save" value="<bean:message key="label.sent"/>" onclick="sent_room_user();" class="clsbtn2">
<input type="hidden" size = 390 name = "operIds" value = "">
  </td>
</tr>
</table>
</td>
</tr>
  </table>
</td>
</tr>
</table>


</form>
