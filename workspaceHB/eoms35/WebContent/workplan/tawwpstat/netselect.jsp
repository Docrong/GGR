<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpModelPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpNetVO"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>

<link rel="stylesheet" href="../css/table_style.css" type="text/css">

<script language="JavaScript">

var callerWindowObj = dialogArguments;

	function JustifyNull1(field) {
		var Ret = true
		var str = "" + field.value

		if(str.length) {
			for(var i=0;i<str.length;i++)
				if(str.charAt(i)!=" ")		break
			if(i>=str.length)	field.value = ""
		}

		if (field.value.length==0)	Ret = false
		return (Ret)
	}// 判断输入字段是否为空

	function  displayuser() {
		if (document.frmAddress.chkuser.checked)
			window.div1.style.display="";
		else
			window.div1.style.display="none";
	}

	function  displaygroup() {
		if (document.frmAddress.chkgroup.checked)
			window.div3.style.display="";
		else
			window.div3.style.display="none";
	}

	function CheckValue() {
		var ret=true;
		if ( (!frmAddress.chkuser.checked)&&(!frmAddress.chkgroup.checked) ) {
			alert("请您先选择导入方式！");
			ret=false;
		}
		else  if ( !checkForm() ) {
			return false;
		}
		else if ( (frmAddress.chkuser.checked)&&(!JustifyNull1(frmAddress.UserName)) ) {
			alert("请输入您想要查找的网元名称！");
			frmAddress.UserName.focus();
			ret=false;
		}

		return ret;
	}

	function checkForm() {
		//if ( frmAddress.chkgroup.checked ) {
			var objOptions = document.frmAddress.select6.options;
			var i;
			var sValue;
                        var sValuestr;
			var bFirst = true;
			for( i=0; i<objOptions.length; i++ ){
				//if( objOptions[i].selected ){
				if( bFirst ){
				 sValue = objOptions[i].value;
                                 sValuestr = objOptions[i].text;
				 bFirst = false;
				}
				else{
				 sValue += ","+objOptions[i].value;
                                 sValuestr += ","+objOptions[i].text;
				}
				//}//end if
			}
			if( bFirst ){
				alert("请选择需要导入的网元！");
				return false;
			}
			document.frmAddress.selvalue.value = sValue;
                        callerWindowObj.document.forms[0].netnamelist.value=sValuestr;
                        callerWindowObj.document.forms[0].netlist.value= sValue;
			window.close();

	}

 function AddItem(ObjName, DesName, CatName)
	{
		ObjID    = GetObjID(ObjName);
		DesObjID = GetObjID(DesName);
		k=0;
		i = document.frmAddress.elements[ObjID].options.length;
		if (i==0)		return;
		maxselected=0
		for (h=0; h<i; h++)
			if (document.frmAddress.elements[ObjID].options[h].selected )
			{
				k=k+1;
				maxselected=h+1;
			}
			if (maxselected>=i)   maxselected=0;

			if (CatName != "")    CatObjID = GetObjID(CatName);
			else   CatObjID = 0;
			if ( ObjID != -1 && DesObjID != -1 && CatObjID != -1 )
			{
				jj = document.frmAddress.elements[CatObjID].selectedIndex;
				if ( CatName != "")
				{
					CatValue = document.frmAddress.elements[CatObjID].options[jj].text;
					CatCode  = document.frmAddress.elements[CatObjID].options[jj].value;
				}
				else   CatValue = "";
				i = document.frmAddress.elements[ObjID].options.length;
				j = document.frmAddress.elements[DesObjID].options.length;
				for (h=0; h<i; h++)
				{
					if (document.frmAddress.elements[ObjID].options[h].selected )
					{
						Code = document.frmAddress.elements[ObjID].options[h].value;
						Text = document.frmAddress.elements[ObjID].options[h].text;
						j = document.frmAddress.elements[DesObjID].options.length;
						if (Text.indexOf('--')!=-1)
						{
							for (k=j-1; k>=0; k-- )
							{
								document.frmAddress.elements[DesObjID].options[k]=null;
							}
							j=0;
						}
						if (Text.substring(0,1)=='-' && Text.substring(1,2)!='-')
						{
							for (k=j-1; k>=0; k-- )
							{
								if (((document.frmAddress.elements[DesObjID].options[k].value).substring(0,2))==(Code.substring(0,2)))
									document.frmAddress.elements[DesObjID].options[k]=null;
							}
							j= document.frmAddress.elements[DesObjID].options.length;
						}
						HasSelected = false;
						for (k=0; k<j; k++ )
						{
							if ((document.frmAddress.elements[DesObjID].options[k].text).indexOf('--')!=-1)
							{
								HasSelected = true;
								window.alert('已经包括本选项：'+Text);
								break;
							}else if ((document.frmAddress.elements[DesObjID].options[k].text).indexOf('-')!=-1 && ((document.frmAddress.elements[DesObjID].options[k].value).substring(0,2)==Code.substring(0,2)))
							{
								HasSelected = true;
								window.alert('已经包括本选项：'+Text);
								break;
							}
							if (document.frmAddress.elements[DesObjID].options[k].value == Code)
							{
								HasSelected = true;
								break;
							}
						}
						if ( HasSelected == false)
						{
							if (CatValue !="")
							{
								Location = GetLocation(DesObjID, CatValue);
								if ( Location == -1 )
								{
									document.frmAddress.elements[DesObjID].options[j] =  new Option("---"+CatValue+"---",CatCode);
									document.frmAddress.elements[DesObjID].options[j+1] = new Option(Text, Code);
								}//if
								else
								{
									InsertItem(DesObjID, Location+1);
									document.frmAddress.elements[DesObjID].options[Location+1] = new Option(Text, Code);
								}//else
							}
							else
								document.frmAddress.elements[DesObjID].options[j] = new Option(Text, Code);
						}//if
						document.frmAddress.elements[ObjID].options[h].selected =false;
					}//if
				}//for
		document.frmAddress.elements[ObjID].options[maxselected].selected =true;
		}//if
	}//end of function

  function DeleteItem(ObjName)
	{
		ObjID = GetObjID(ObjName);
		minselected=0;
		if ( ObjID != -1 )
		{
			for (i=window.frmAddress.elements[ObjID].length-1; i>=0; i--)
			{
				if (window.frmAddress.elements[ObjID].options[i].selected)
				{
					if (minselected==0 || i<minselected)
					minselected=i;
					window.frmAddress.elements[ObjID].options[i] = null;
				}
			}
			i=window.frmAddress.elements[ObjID].length;

			if (i>0)
			{
				if (minselected>=i)		minselected=i-1;
				window.frmAddress.elements[ObjID].options[minselected].selected=true;
			}
		}
	}

  function GetObjID(ObjName)
	{
		for (var ObjID=0; ObjID < window.frmAddress.elements.length; ObjID++)
			if ( window.frmAddress.elements[ObjID].name == ObjName )
			{
				return(ObjID);
				break;
			}
		return(-1);
	}
 </script>

<html>
<head>
<title>选择网元</title>
</head>

<body>

<FORM name='frmAddress' method='post' onsubmit='return checkForm();'>
  <TABLE cellSpacing=0 cellPadding=0 align=center border=0 class="fromTable">
    <TR>
      <TD vAlign=top align=center width='200'><P>可选网元</P></TD>
      <TD align=center width='80'></TD>
      <TD vAlign=top align=center width='200'><P>&nbsp;已选网元</P></TD>
    </TR>
    <tr>
      <TD vAlign=top align=center width='200'>
        <SELECT size='15' width='100%' name='select5' multiple style='width:65pt'>
          <option value="0">无网元</option>
          <%
          List netlist = (List)request.getAttribute("netlist"); //获取年度作业计划集合结构
            for (int i=0;i<netlist.size();i++){
              TawwpNetVO tawwpNetVO = (TawwpNetVO)netlist.get(i);
          %>
          <option value="<%=tawwpNetVO.getId()%>"><%=tawwpNetVO.getName()%></option>
          <%
            }
          %>
        </SELECT>
      </TD>
      <TD vAlign=top align=center width='80'>
        <input type='button'  value='增加' name='sub' onclick="AddItem('select5', 'select6', '')">
        <p>
        <input type='button' value='删除' name='sub1' onclick="DeleteItem('select6')">
      </TD>
      <TD vAlign=top align=center width='200'>
        <SELECT name='select6' width='100%' id='id_select6' size='15' multiple style='width:65pt'></SELECT>
      </TD>
    </TR>
    <TR>
      <TD borderColor=#008000 width='100%' height=37 colspan='3'><P align=center>
	<input type='button' value='完成' name='sub3' onclick="javascript:checkForm()"> &nbsp;&nbsp;&nbsp;&nbsp;
	<input type='button' value='取消' name='sub4' onclick="javascript:window.close()">
	</P>
      </TD>
    </TR>
  </TABLE>
  <input type='hidden' name='deptid'  value=''>
  <input type='hidden' name='selvalue' value=''>
</FORM>
</body>
