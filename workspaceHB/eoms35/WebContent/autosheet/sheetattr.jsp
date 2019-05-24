<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.autosheet.util.*" %>
<%@ page import="com.boco.eoms.common.util.*"%>
<head>
<%
SheetUtil sheetUtil = new SheetUtil();
String sheetID=request.getParameter("sheetID");
if(sheetID==null||sheetID.equals("")){
  sheetID=(String)session.getAttribute("sheetID");
}
 session.setAttribute("sheetID",sheetID);
//SheetAttribute shAttribute=new SheetAttribute(sheetID);
int index=sheetUtil.getIndex(sheetID);
String reFactor=StaticMethod.null2String(request.getParameter("reFactor"),"");
System.out.println("sheetattr.jsp sheetID :"+sheetID);

//try{
//  index=StaticMethod.getIntValue((String)session.getAttribute("index"),0);
//}catch(Exception e){
//  index=0;
//}
//index++;
//session.setAttribute("index",index+"");
%>
<title>??????????????????</title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="javascript">
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
  function isColor(varColor){
          /*var varcolor=varColor;
          if(varcolor!=""){
            if(varcolor.length!=7){
              alert("????????????????,??????????:#000000??#FFFFFF.");
              return false;
            }else if(varcolor.length==7){
              if(varcolor.charAt(0)!="#"){
                alert("????????????????,??????????:#000000??#FFFFFF.");
                return false;
              }else{
                for(i=1;i<7;i++){
                  if((varcolor.charAt(i)<="0"||varcolor.charAt(i)>="9")&&(varcolor.charAt(i)<="a"||varcolor.charAt(i)>="f")&&(varcolor.charAt(i)<="A"||varcolor.charAt(i)>="F")){
                    alert("????????????????,??????????:#000000??#FFFFFF.");
                    return false;
                  }
                }
              }
            }
          }*/
          return true;
  }
  function checkInput(){
     var attrName=document.addEForm.attrName.value;
     var color   =document.addEForm.color.value;
     var colspan=document.addEForm.colspan.value;
     var rowspan=document.addEForm.rowspan.value;
     var width=document.addEForm.width.value;
     var height=document.addEForm.height.value;
     var index=document.addEForm.index.value;
    if(isNull(attrName)&&(isNumber(colspan))&&isNumber(rowspan)&&isNumber(index)&&isNumber(width)&&isNumber(height)&&isColor(color)){
      return true;
    }else{
      return false;
    }
  }
 function addESubmit(n){
     document.addEForm.action="<%=request.getContextPath()%>/sheetattrservlet";
     if(n == 2){
          document.addEForm.direction.value="2"
          document.addEForm.submit();
        }
     else if(checkInput()){
          if(n == 0 ){
            document.addEForm.direction.value="0"
          }else if(n == 1){
            document.addEForm.direction.value="1"
          }
          document.addEForm.submit();
      }
    else{
        return false;
      }

  }
</script>
</head>

<div align="center">
  <p><b><font size="5"><fmt:message key="autosheet.bddz2"/></font></b></p>

   <form name="addEForm" method="post" action=""   onsubmit="javascript: return checkInput();">
    <table cellspacing=0 cellpadding=0  border=1 width="779" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF" bgcolor=#F3F3F3 align="center">
      <tr>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.srkmc"/></div>
        </td>
        <td width="13%" nowrap>
          <input type="text" name="attrName" size="10" maxlength="200"><font color="red">**</font>
          <input type="hidden" name="direction" value="">
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.ssbf"/></div>
        </td>
        <td width="13%" nowrap>
          <select name="isattach">
            <option value="0"><fmt:message key="autosheet.bt"/></option>
           <!-- <option value="1">??</option>
            <option value="2">??</option>-->
          </select>
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.sxh"/></div>
        </td>
        <td width="13%" nowrap>
          <input type="text" name="index" size="10" maxlength="10" value="<%=index%>"><font color="red">**</font>
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.huanhang"/> </div>
        </td>
        <td width="13%" nowrap>
          <input type="checkbox" name="newLine" value="1">
        </td>
      </tr>

      <tr>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.hdq"/></div>
        </td>
        <td width="13%" nowrap>
          <select name="align">
            <option value="center"><fmt:message key="autosheet.center"/></option>
            <option value="left"><fmt:message key="autosheet.left"/></option>
            <option value="right"><fmt:message key="autosheet.right"/></option>
          </select>
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.zdq"/></div>
        </td>
        <td width="13%" nowrap>
          <select name="valign">
            <option value="middle"><fmt:message key="autosheet.center"/></option>
            <option value="top"><fmt:message key="autosheet.top"/></option>
            <option value="bottom"><fmt:message key="autosheet.bottom"/></option>
          </select>
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.klk"/></div>
        </td>
        <td width="13%" nowrap>
          <input type="text" name="colspan" size="10" maxlength="10" value="1"><font color="red">**</font>
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.khk"/></div>
        </td>
        <td width="13%" nowrap>
          <input type="text" name="rowspan" size="10" maxlength="10" value="1"><font color="red">**</font>
        </td>
      </tr>
      <tr>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.dygkd"/></div>
        </td>
        <td width="13%" nowrap>
          <input type="text" name="width" size="10" maxlength="10"> <fmt:message key="autosheet.xiangsu"/>
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.dyggd"/></div>
        </td>
        <td width="13%" nowrap>
          <input type="text" name="height" size="10" maxlength="10"> <fmt:message key="autosheet.xiangsu"/>
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.wzys"/><font size=-2 color=red><b><fmt:message key="autosheet.shuangji"/></b></font></div>
        </td>
        <td width="13%" nowrap>
          <input type="text" name="color" size="10" maxlength="10" ondblclick="openColorWin()">
        </td>
        <td width="12%" nowrap>
          <div align="right"><fmt:message key="autosheet.dyghh"/></div>
        </td>
        <td width="13%" nowrap>
          <input type="checkbox" name="nowrap" value="">
        </td>
      </tr>
      <tr>





</tr>
</table>
 <table cellspacing=0 cellpadding=0  border=0 width="779" >
  <td width="20%" align="right">
    <input type="hidden" name="parentID" value="0">
    <input type="hidden" name="level" value="0">
    <input type="hidden" name="reFactor" value=<%=reFactor%>>
    <input type="hidden" name="sheetID" value=<%=sheetID%>>
    <input type="hidden" name="flag" value="new">
    <input type="button" name="Submit" value="<fmt:message key="autosheet.addname"/>" onClick="javascript:addESubmit(0);">
    <input type="button" name="Submit3" value="<fmt:message key="autosheet.zjsrk"/>" onClick="javascript:addESubmit(1);">
        </td>
    <!-- <td width="11%">
          <input type="button" name="Submit2" value="??" onClick="javascript:addESubmit(2);">
        </td>-->
 </table>
    </form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
