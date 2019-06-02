<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.duty.util.RelativeSheetAttrName" %>

<%
String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");
RelativeSheetAttrName rel = new RelativeSheetAttrName();
String sheetAttrName = rel.strRelativeDrop();
%>

<script language="JavaScript">
var arrSheetAttrName = new Array;
<%=sheetAttrName%>
</script>

<html>

<head>
<title>值班机房和附加表配置</title>

</head>

<body >
<html:form method="post" action="/TawConfRoomSheet/updateDone">
<br>
<br>
<br>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="30%"  class="clsfth">选择所在机房</td>
    <td width="70%">
        <html:select property="roomId" style="width:220">
             <html:optionsCollection name="tawConfRoomSheetForm" property="collRoomSelect"/>
        </html:select>

  　</td>
  </tr>

  <tr class="tr_show">
    <td width="30%"  class="clsfth" >选择附加表</td>
    <td width="70%">
       <html:select property="sheetId" style="width:220">
             <html:optionsCollection name="tawConfRoomSheetForm" property="collSheetSelect"/>
        </html:select>

  　</td>
  </tr>

  <tr class="tr_show">
    <td width="30%"  class="clsfth" >是否是故障表</td>
    <td width="70%">
       <html:select property="isNotFault"  style="width:220" onchange="toDisplay();">
             <html:optionsCollection name="tawConfRoomSheetForm" property="collIsNotFault"/>
        </html:select>

  　</td>
  </tr>
</table>

<div id="hiddenId" style="display:none">
 <table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
     <td width="30%"  class="clsfth">选择进入遗留问题的条件</td>
     <td width="70%">
       <select size=1 name="byAttr" style="width:220">
          <option value=""></option>
       </select>
         为空
  　</td>
   </tr>

   <tr class="tr_show">
    <td width="30%"  class="clsfth">选择进入遗留问题的属性</td>
    <td width="70%">
        <select size=1 name="toAttr" style="width:220">
          <option value=""></option>
       </select>
  　</td>
  </tr>
</table>
  </div>

<table border="0" width="80%" cellspacing="1" cellpadding="1" align="center">
<TR>
        <TD align="right" height="32">
         <input type="submit" value="保存配置" class="clsbtn2">
        </TD>
</TR>
</TABLE>

</html:form>
</body>
</html>

<script language="javascript">
  function toDisplay(){
    var selectedValue = window.document.all.isNotFault.options[document.all.isNotFault.selectedIndex].value;

   // alert("selectedValue =="+selectedValue);
  //  alert("index=="+window.tawConfRoomSheetForm.sheetId.selectedIndex);
    if (selectedValue =="是"){

      if (window.tawConfRoomSheetForm.sheetId.selectedIndex <= 0 ){
          alert("请选择附加表!");
          window.tawConfRoomSheetForm.isNotFault.selectedIndex = 0;
          window.tawConfRoomSheetForm.sheetId.focus;
      }else{
       window.document.all.hiddenId.style.display = "inline";
       //为列表赋值

        var sheet_Id  = window.tawConfRoomSheetForm.sheetId.options[window.tawConfRoomSheetForm.sheetId.selectedIndex].value;
       // alert("sheet_Id=="+sheet_Id);

        var sel_sprlen=document.tawConfRoomSheetForm.byAttr.options.length-1;
        var j=0;
        for(j=sel_sprlen;j>=0;j--)
        {
             document.all.tawConfRoomSheetForm.byAttr.options[j]=null;
        }

        sel_sprlen=document.tawConfRoomSheetForm.toAttr.options.length-1;
        for(j=sel_sprlen;j>=0;j--)
        {
             document.all.tawConfRoomSheetForm.toAttr.options[j]=null;
        }

         var m=0;
         document.tawConfRoomSheetForm.byAttr.options[m]=new Option("- 选择 －","");
         if ((sheet_Id != "0") && (arrSheetAttrName[sheet_Id]))
         {
            var i;
            var k = arrSheetAttrName[sheet_Id].length;
            for (i=0;i*2<k;i++)
            {
             var tempoption=new Option(arrSheetAttrName[sheet_Id][i*2],arrSheetAttrName[sheet_Id][i*2+1]);
             document.tawConfRoomSheetForm.byAttr.options[++m]=tempoption;
            }
         }
         m=0;
         document.tawConfRoomSheetForm.toAttr.options[m]=new Option("- 选择 －","");
         if ((sheet_Id != "0") && (arrSheetAttrName[sheet_Id]))
         {
            var i;
            var k = arrSheetAttrName[sheet_Id].length;
            for (i=0;i*2<k;i++)
            {
             var tempoption=new Option(arrSheetAttrName[sheet_Id][i*2],arrSheetAttrName[sheet_Id][i*2+1]);
             document.tawConfRoomSheetForm.toAttr.options[++m]=tempoption;
            }
         }
      }
    }else{
       window.document.all.hiddenId.style.display = "none";
      //清除列表
        var sel_sprlen=document.tawConfRoomSheetForm.byAttr.options.length-1;
        var j=0;
        for(j=sel_sprlen;j>=0;j--)
        {
             document.all.tawConfRoomSheetForm.byAttr.options[j]=null;
        }

        sel_sprlen=document.tawConfRoomSheetForm.toAttr.options.length-1;
        for(j=sel_sprlen;j>=0;j--)
        {
             document.all.tawConfRoomSheetForm.toAttr.options[j]=null;
        }

    }
  }

  function refreshIt(){
     window.tawConfRoomSheetForm.sheetId.selectedIndex = 0;
     window.tawConfRoomSheetForm.isNotFault.selectedIndex = 0;

     var sel_sprlen=document.tawConfRoomSheetForm.byAttr.options.length-1;
     var j=0;
     for(j=sel_sprlen;j>=0;j--)
        {
             document.all.tawConfRoomSheetForm.byAttr.options[j]=null;
        }

     sel_sprlen=document.tawConfRoomSheetForm.toAttr.options.length-1;
     for(j=sel_sprlen;j>=0;j--)
        {
             document.all.tawConfRoomSheetForm.toAttr.options[j]=null;
        }
  }
</script>


