<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.common.util.*" %>
<%@ page import="com.boco.eoms.autosheet.util.*" %>
 
<head>
<%
SheetUtil sheetUtil = new SheetUtil();
String sheetID=request.getParameter("sheetID");
if(sheetID==null||sheetID.equals("")){
  sheetID=(String)session.getAttribute("sheetID");
}
session.setAttribute("sheetID",sheetID);
SheetAttribute shAttribute=new SheetAttribute(sheetID);
int index=sheetUtil.getIndex(sheetID);
String attr_id=sheetUtil.getAttrId(sheetID).trim();
String reFactor=StaticMethod.null2String(request.getParameter("reFactor"),"");
String preview=(String)session.getAttribute("preview");
session.removeAttribute("preview");
//index=StaticMethod.getIntValue((String)session.getAttribute("index"),0);
//index++;
//session.setAttribute("index",index+"");
%>
<title><fmt:message key="autosheet.bddz3"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="javascript">
  function vStoreTypeChange(){

 var index=document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;
 var index1=document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
     if(index=="Date" || index=="DateTime"){
	  //document.addEForm.showtype.selectedIndex=3;
      document.addEForm.showtype.disabled=true;
      document.addEForm.formWidth.disabled=true;
      document.addEForm.formHeight.disabled=true;
      document.addEForm.typeID.disabled=true;
     }else if(index=="Integer" || index=="Float"){
        document.addEForm.showtype.selectedIndex=0;
        document.addEForm.showtype.disabled=true;
        document.addEForm.typeID.selectedIndex=0;
        document.addEForm.typeID.disabled=true;
        if(index1==0 || index1==5){
          document.addEForm.formWidth.disabled=false;
          document.addEForm.formHeight.disabled=false;
          }
        str.innerHTML="??????";
     }else if(index=="String" || index=="LongString"){
        document.addEForm.showtype.disabled=false;
        document.addEForm.typeID.selectedIndex=0;
        document.addEForm.typeID.disabled=true;
         if(index1==0 || index1==5){
          document.addEForm.formWidth.disabled=false;
          document.addEForm.formHeight.disabled=false;
          }
     }
     else{
	  //document.addEForm.showtype.selectedIndex=0;
      document.addEForm.showtype.disabled=false;
      document.addEForm.formWidth.disabled=false;
      document.addEForm.formHeight.disabled=false;
      document.addEForm.typeID.disabled=false;

	 }
  }
  
  function isOutRange(inputVal) {
    var inputInt=parseInt(inputVal);

    var index=document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;
    if(index=="String"){
      var inputInt1=parseInt("255");
    }
    else if (index=="LongString"){
      var inputInt1=parseInt("4000");
    }
    if(inputInt>inputInt1){
      alert("???:"+inputVal+"?????:"+inputInt1);
      return false;
    }
    return true;
  }
  
  function isNull(varStr){
    var varstr=varStr;
    if(varstr==""||varstr==null){
       alert("<fmt:message key="autosheet.xinghao"/>"+varStr+"<fmt:message key="autosheet.notnull"/>");
       return false;
    }
    return true;
  }
  function isNumber(inputVal) {

        inputStr=inputVal;
        if(inputStr.length==1){
          if((inputStr.charAt(0)<'0')||(inputStr.charAt(0)>'9')){
                  alert("<fmt:message key="autosheet.shuruxiang"/>:"+inputStr+"<fmt:message key="autosheet.isnumber"/>");
                   return false;
            }
        }else if(inputStr.length>1){
            if((inputStr.charAt(0)<'0')||(inputStr.charAt(0)>'9')){
              alert("<fmt:message key="autosheet.shuruxiang"/>:"+inputStr+"<fmt:message key="autosheet.isnumber"/>");
                   return false;
            }
            for(n=1;n<inputStr.length;n++){
              if((inputStr.charAt(n)!='.')){
              if((inputStr.charAt(n)> '9')||(inputStr.charAt(n)< '0')){
                   alert("<fmt:message key="autosheet.shuruxiang"/>:"+inputStr+"<fmt:message key="autosheet.isnumber"/>");
                   return false;
                }
              }
            }

        }
        return true;
  }
  function showtypeChange(){
  var index=document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
    if(index==0 || index==5){
      document.addEForm.typeID.disabled=true;
      document.addEForm.formWidth.disabled=false;
      document.addEForm.formHeight.disabled=false;
      document.addEForm.vStoreType.disabled=false;
      if(index==5)
        {str.innerHTML="??????<font color=\"red\">(*)</font>";
         document.addEForm.height.disabled=true;
         if(document.addEForm.isedit.value=="0")
         document.addEForm.formHeight.value=3;}
      else
        {str.innerHTML="??????<font color=\"red\">(*)</font>";
         if(document.addEForm.isedit.value=="0")
         document.addEForm.formHeight.value=20;}
    }else{
      document.addEForm.typeID.disabled=false;
      document.addEForm.formWidth.disabled=true;
      document.addEForm.formHeight.disabled=true;
      document.addEForm.vStoreType.selectedIndex=0;
      document.addEForm.vStoreType.disabled=true;
      str.innerHTML="??????<font color=\"red\">(*)</font>";

   }
  }
  function openColorWin(){
	//	if (window.secondwindow&&window.secondwindow.closed==false){
	//		secondwindow.document.bgColor='red';secondwindow.focus();
	//	}else
		secondwindow=window.showModalDialog("color.jsp",window,"dialogHeight:235px;dialogWidth:230px;center:YES;help:NO;resizable:NO;status:NO;");
		//alert(secondwindow);
		document.addEForm.color.value=secondwindow;
		return true;
	}



	function checkValueInput(){
     var vName=document.addEForm.vName.value;
     var index=document.addEForm.index.value;
     var color   =document.addEForm.color.value;
     var colspan=document.addEForm.colspan.value;
     var rowspan=document.addEForm.rowspan.value;
     var width=document.addEForm.width.value;
     var height=document.addEForm.height.value;
     var formWidth=document.addEForm.formWidth.value;
     var formHeight=document.addEForm.formHeight.value;
     var formWidth2=document.addEForm.formWidth;

    if(isOutRange(formHeight)&&isNull(vName)&&isNull(index)&&isNull(colspan)&&isNull(rowspan)&&
		isNull(formWidth)&&isNull(formHeight)&&isNumber(colspan)&&isNumber(rowspan)&&
		isNumber(width)&&isNumber(index)&&isNumber(height)&&isNumber(formWidth)&&isNumber(formHeight)&&isVariable(document.addEForm.vName)){
      return true;
    }else{
      return false;
    }
  }
  function isVariable(variable){
	var literal='abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var number='0123456789_';
	if(variable.value==null)
		alert('??????');
	var value=trim(variable.value);
	for(i=0;i<value.length;i++){
		if(i==0){
			if(literal.indexOf(value.charAt(i))<0){
				alert('??????????');
				return variable.focus();

			}
		}else{
			if(literal.indexOf(value.charAt(i))<0&&number.indexOf(value.charAt(i))<0){
				alert('????????????"_"??');
				return variable.focus();

			}
		}

	}
	return true;
  }
  
  function addSubmit(n){

    if(checkValueInput()){

      if(n==0){
      document.addEForm.direction.value="0";
      }else if(n==1){
      document.addEForm.direction.value="1";
      }else if(n==2){
      document.addEForm.direction.value="2";
      }else if(n==3){
      document.addEForm.direction.value="3";
      }
      document.addEForm.action="<%=request.getContextPath()%>/sheetvalueservlet?preview=<%=preview%>";
      document.addEForm.submit();
    }else{
      return false;
    }


  }
  function trim(str){
        if (str==null)
        {
                return "";
        }
        for (i=0;i<str.length;i++){
                if (str.charAt(0) == ' ') {
                        str = str.substr(1);
                }
                else {
                        break;
                }
        }
        return str;
  }
</script>


</head>

<div align="center">
  <p><b><font size="5"><fmt:message key="autosheet.bddz3"/></font></b></p>
      <form name="addEForm" method="post" action="" onsubmit="javascript: return checkValueInput();">
    <p>&nbsp;</p>
    <table cellspacing=0 cellpadding=0  border=1 width="760" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF" bgcolor=#F3F3F3 align="center">
      <tr>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.zdm"/></div>
        </td>
        <td width="18%">
          <input type="hidden" name="direction" value="">
          <input type="hidden" name="isedit" value="0">
          <input type="text" name="vName" size="10" maxlength="200" value="<%="column_"+index%>" onchange="isVariable(this);"><font color="red">**</font>
        </td>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.ssbf"/></div>
        </td>
        <td width="18%">
          <select name="isattach">
            <option value="0"><fmt:message key="autosheet.bt"/></option>
         <!--   <option value="1">??</option>
            <option value="2">??</option>-->
          </select>
        </td>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.sxh"/></div>
        </td>
        <td width="18%">
          <input type="text" name="index" size="10" maxlength="10" value="<%=index%>"><font color="red">**</font>
        </td>
      </tr>
      <tr>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.huanhang"/></div>
        </td>
        <td width="18%">
          <input type="checkbox" name="newLine" value="1">
        </td>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.chaxunxiang"/></div>
        </td>
        <td width="18%">
          <input type="checkbox" name="isQuery" value="1" checked>
        </td>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.sssx"/></div>
        </td>
        <td width="18%">
          <select name="attrID">
            <%
             while(shAttribute.next()){
               if(attr_id.equals(shAttribute.getAttrID().trim()))
                 {out.println("<option value="+shAttribute.getAttrID()+ " selected >"+shAttribute.getAttrName()+"</option>");
                 }
               else
                 {out.println("<option value="+shAttribute.getAttrID()+ ">"+shAttribute.getAttrName()+"</option>");
                 }
             }
           %>

          </select>
        </td>
      </tr>
      <tr>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.dygkd"/></div>
        </td>
        <td width="18%">
          <input type="text" name="width" size="10" maxlength="10"> <fmt:message key="autosheet.xiangsu"/>
        </td>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.dyggd"/></div>
        </td>
        <td width="18%">
          <input type="text" name="height" size="10" maxlength="10"> <fmt:message key="autosheet.xiangsu"/>
        </td>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.klk"/></div>
        </td>
        <td width="18%">
          <input type="text" name="colspan" size="10" maxlength="10" value="1"><font color="red">**</font>
        </td>
      </tr>
      <tr>
        <td nowrap width="15%">
          <div align="right"><fmt:message key="autosheet.khk"/></div>
        </td>
        <td width="18%">
          <input type="text" name="rowspan" size="10" maxlength="10" value="1"><font color="red">**</font>
        </td>
        <td nowrap width="15%">
          <div align="right"><fmt:message key="autosheet.hdq"/></div>
        </td>
        <td width="18%">
          <select name="align">
            <option value="left"><fmt:message key="autosheet.left"/></option>
            <option value="center"><fmt:message key="autosheet.center"/></option>
            <option value="right"><fmt:message key="autosheet.right"/></option>
          </select>
        </td>
        <td nowrap width="15%">
          <div align="right"><fmt:message key="autosheet.zdq"/></div>
        </td>
        <td width="18%">
          <select name="valign">
            <option value="middle"><fmt:message key="autosheet.center"/></option>
            <option value="top"><fmt:message key="autosheet.top"/></option>
            <option value="bottom"><fmt:message key="autosheet.bottom"/></option>
          </select>
        </td>
      </tr>
      <tr>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.dyghh"/></div>
        </td>
        <td width="18%">
          <input type="checkbox" name="nowrap" value="">
        </td>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.wzys"/><font size=-2 color=red><b><fmt:message key="autosheet.shuangji"/></b></font></div>
        </td>
        <td width="18%">
          <input type="text" name="color" size="10" maxlength="10" ondblclick="openColorWin()" value="">
        </td>
<td width="15%" height="18" nowrap>
          <div align="right"><fmt:message key="autosheet.cclx"/></div>
        </td>
        <td width="18%" height="18">
          <select name="vStoreType" onchange=" vStoreTypeChange()">
            <option value="varchar(255)">String </option>
            <% if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)){
            out.println("<option value=\"varchar2(4000)\">LongString </option>");
             }
             else if (StaticMethod.getDbType().equals(StaticVariable.INFORMIX)){
            out.println("<option value=\"char(4000)\">LongString </option>");

             }%>
            <option value="date">Date</option>
            <option value="datetime">DateTime</option>
            <option value="integer">Integer</option>
            <option value="float">Float</option>
           <!-- <option value="text">Text</option>-->
          </select>
        </td>
      </tr>
      <tr>
        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.srklx"/></div>
        </td>
        <td width="18%">
          <select name="showtype" onchange="showtypeChange()">
            <option value="0">TextField</option>
            <option value="1">RadioBox</option>
            <option value="2">CheckBox</option>
            <option value="3">Select</option>
            <option value="4">MulSelect</option>
            <option value="5">TextArea</option>
            <!--<option value="6">MenuBox</option>-->
            <option value="7">UserName</option>
          </select>
        </td>

        <td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.srkc"/></div>
        </td>
        <td width="18%">
          <input type="text" name="formWidth" size="10" maxlength="10"  value="20"> <fmt:message key="autosheet.zf"/><font color="red">**</font>
        </td>
        <td width="15%" nowrap>
          <div align="right" id="str"><fmt:message key="autosheet.maxlength"/></div>
        </td>
        <td width="18%">
          <input type="text" name="formHeight" size="10" maxlength="10"  value="20"> <fmt:message key="autosheet.zf"/><font color="red">**</font>
        </td>
      </tr>
      <tr>
		<td width="15%" nowrap>
          <div align="right"><fmt:message key="autosheet.ssbmlb"/></div>
        </td>
        <td width="18%">
          <select name="typeID" disabled>

           <%
            CodeTypeComsInfo codetype=CodeTypeComsInfo.getInstance();
			codetype.removeOSCache();
			codetype=CodeTypeComsInfo.getInstance();
            codetype.reset();
          while(codetype.next()){
           %>
            <option value="<%=codetype.getTypeID()%>"><%=codetype.getTypeName()%></option>
            <%
          }
          %>
		   <option value="0"><fmt:message key="autosheet.wu"/></option>
          </select>

        </td>

        <td width="15%" height="18" nowrap>
          <div align="right"><fmt:message key="autosheet.default"/></div>
        </td>
        <td width="18%" height="18">
          <input type="text" name="defaultval" size="10" maxlength="10" value="" >
          <!--<input type="checkbox" name="isDefault" value="1" disabled=true >-->
        </td>
        <td width="15%" height="18" nowrap>
          <div align="right"><fmt:message key="autosheet.xjsx"/></div>
        </td>
        <td width="18%" height="18">
          <select name="vCtrl">
            <option value="1"><fmt:message key="autosheet.isnull"/></option>
            <option value="0"><fmt:message key="autosheet.notnull"/></option>
          </select>
        </td>
      </tr>
      </div>
    </table>
<table cellspacing=0 cellpadding=0  border=0 width="760" >
  <td width="25%" align="right">
    <input type="hidden" name="reFactor" value=<%=reFactor%>>
    <input type="hidden" name="sheetID" value=<%=sheetID%>>
    <input type="hidden" name="flag" value="new">
    <input type="button" name="Submit" value="<fmt:message key="autosheet.addname"/>" onClick="javascript:addSubmit(0);">
    <input type="button" name="Submit3" value="<fmt:message key="autosheet.zjsrk"/>" onClick="javascript:addSubmit(1);">
    <input type="button" name="Submit2" value="<fmt:message key="autosheet.zjwb"/>" onClick="javascript:addSubmit(2);">
  </td>
        <!--<td width="14%">
          <div align="left">
          <input type="button" name="Submit4" value="??" onClick="javascript:addSubmit(3);">
          </div>
        </td>-->
</table>

    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
