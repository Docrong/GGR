<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.tree.WKTree"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<html>
	<head>
		<title>测试卡新增</title>

		<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>
		<SCRIPT LANGUAGE=JavaScript>

function check(){
	var leave = document.forms[0].leave.value;
	if (leave == '') {
		alert("请选择存放公司！");
		return false;
	}
	var beginNumberPartOne = document.forms[0].beginNumberPartOne.value;
	var beginNumberPartTow = document.forms[0].beginNumberPartTow.value;
	var beginNumberPartThree = document.forms[0].beginNumberPartThree.value;
	var endNumberPartOne = document.forms[0].endNumberPartOne.value;
	var endNumberPartTow = document.forms[0].endNumberPartTow.value;
	var endNumberPartThree = document.forms[0].endNumberPartThree.value;
	var i,j;
	i=0;
	j=0;
	if(beginNumberPartOne.length != 3){
		alert("您输入的起始电话号码的第一部分不足3位，请修正！");
		return false;
	}
	if(fucCheckNUM(beginNumberPartOne)==0){
		alert("您输入的起始电话号码的第一部分中存在非数字字符，请修正！");
		return false;
	}

	if(beginNumberPartTow.length != 4){
		alert("您输入的起始电话号码的第二部分不足4位，请修正！");
		return false;
	}
	if(fucCheckNUM(beginNumberPartTow)==0){
		alert("您输入的起始电话号码的第二部分中存在非数字字符，请修正！");
		return false;
	}

	if(beginNumberPartThree.length != 4){
		alert("您输入的起始电话号码的第三部分不足4位，请修正！");
		return false;
	}
	if(fucCheckNUM(beginNumberPartThree)==0){
		alert("您输入的起始电话号码的第三部分中存在非数字字符，请修正！");
		return false;
	}

	if(endNumberPartOne.length != 3){
		alert("您输入的终止电话号码的第一部分不足3位，请修正！");
		return false;
	}
	if(fucCheckNUM(endNumberPartOne)==0){
		alert("您输入的终止电话号码的第一部分中存在非数字字符，请修正！");
		return false;
	}

	if(endNumberPartTow.length != 4){
		alert("您输入的终止电话号码的第二部分不足4位，请修正！");
		return false;
	}
	if(fucCheckNUM(endNumberPartTow)==0){
		alert("您输入的终止电话号码的第二部分中存在非数字字符，请修正！");
		return false;
	}

	if(endNumberPartThree.length != 4){
		alert("您输入的终止电话号码的第三部分不足4位，请修正！");
		return false;
	}
	if(fucCheckNUM(endNumberPartThree)==0){
		alert("您输入的终止电话号码的第三部分中存在非数字字符，请修正！");
		return false;
	}

	
	j = Ext.DomQuery.select("input:checked",eoms.$("tawTestcardForm")).length;
	
	if(j != 2){
		alert("您只能选择两个号码段，请检查！");
		return false;
	}
	
	if(beginNumberPartOne > endNumberPartOne){
		alert("起始号码应该小于终止号码，请检查！");
		return false;
	}			
	if(beginNumberPartTow > endNumberPartTow){
		alert("起始号码应该小于终止号码，请检查！");
		return false;
	}	
	if(beginNumberPartThree > endNumberPartThree){
		alert("起始号码应该小于终止号码，请检查！");
		return false;
	}if(document.forms[0].checkBeginNumberPartOne.checked==true){	
		if(beginNumberPartTow!=endNumberPartTow){
			alert("您输入的第二部分起始电话号码和终止电话号码内容不同，请修正！");
			return false;
		}
		else if(beginNumberPartThree!=endNumberPartThree){
			alert("您输入的第三部分起始电话号码和终止电话号码内容不同，请修正！");
			return false;
			}	
	}
	if(document.forms[0].checkBeginNumberPartTow.checked==true){	
		if(beginNumberPartOne!=endNumberPartOne){
			alert("您输入的第一部分起始电话号码和终止电话号码内容不同，请修正！");
			return false;
		}
		else if(beginNumberPartThree!=endNumberPartThree){
			alert("您输入的第三部分起始电话号码和终止电话号码内容不同，请修正！");
			return false;
		}	
	}
	if(document.forms[0].checkBeginNumberPartThree.checked==true){
		if(beginNumberPartOne!=endNumberPartOne){
			alert("您输入的第一部分起始电话号码和终止电话号码内容不同，请修正！");
			return false;
		
		}
		else if(beginNumberPartTow!=endNumberPartTow){
			alert("您输入的第二部分起始电话号码和终止电话号码内容不同，请修正！");
			return false;
		}	
	}
	return true;
}
//检测输入是否全部为数字
function fucCheckNUM(NUM){
	var i,j,strTemp;
	strTemp="0123456789*";
	if ( NUM.length== 0)
		return 0
		for (i=0;i<NUM.length;i++){
  			j=strTemp.indexOf(NUM.charAt(i));
  			if (j==-1){
  				//说明有字符不是数字
   				return 0;
  			}
 	}
 	//说明是数字
 	return 1;
}

function clickCheckBox(checkNum){
	var beginNumberPartOne = document.forms[0].beginNumberPartOne.value;
	var beginNumberPartTow = document.forms[0].beginNumberPartTow.value;
	var beginNumberPartThree = document.forms[0].beginNumberPartThree.value;
	var endNumberPartOne = document.forms[0].endNumberPartOne.value;
	var endNumberPartTow = document.forms[0].endNumberPartTow.value;
	var endNumberPartThree = document.forms[0].endNumberPartThree.value;
	if(checkNum == 1){
		if(document.forms[0].checkBeginNumberPartOne.checked){
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = true;
			document.forms[0].checkEndNumberPartTow.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}else{
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}
		
	}else if(checkNum == 2){
		if(document.forms[0].checkBeginNumberPartTow.checked){
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = true;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}else{
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}
	}else if(checkNum == 3){
		if(document.forms[0].checkBeginNumberPartThree.checked){
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = true;
		}else{
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}
	}else if(checkNum == 4){
		if(document.forms[0].checkEndNumberPartOne.checked){
			document.forms[0].checkBeginNumberPartOne.checked = true;
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}else{
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}
	}else if(checkNum == 5){
		if(document.forms[0].checkEndNumberPartTow.checked){
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkBeginNumberPartTow.checked = true;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}else{
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartThree.checked = false;
		}
	}else if(checkNum == 6){
		if(document.forms[0].checkEndNumberPartThree.checked){
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = true;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
		}else{
			document.forms[0].checkBeginNumberPartOne.checked = false;
			document.forms[0].checkBeginNumberPartTow.checked = false;
			document.forms[0].checkBeginNumberPartThree.checked = false;
			document.forms[0].checkEndNumberPartOne.checked = false;
			document.forms[0].checkEndNumberPartTow.checked = false;
		}
	}
	
}

</SCRIPT>
	</head>
	<body>
		<html:form method="post" action="/TawTestcard/createNumber" onsubmit="return check();">

			<br>

			<html:hidden property="strutsAction" />
			<html:hidden property="id" />

			<table width="100%" class="formTable" align="center">
				<caption>
					<b>生成测试卡号码</b>
				</caption>
				<tr>
					<td noWrap width="80" class="label">
						起始电话号码：
					</td>
					<td>
						<input type="text" id="beginNumberPartOne" name="beginNumberPartOne" size="3" title="号码第一部分" MaxLength="3"  />
						<input type="checkbox" id="checkBeginNumberPartOne" name="checkBeginNumberPartOne" onClick="javascript:clickCheckBox(1);"/>
					</td>
					<td>
						<input type="text" id="beginNumberPartTow" name="beginNumberPartTow" size="4" title="号码第二部分" MaxLength="4"/>
						<input type="checkbox" id="checkBeginNumberPartTow" name="checkBeginNumberPartTow" onClick="javascript:clickCheckBox(2);"/>
					</td>
					<td>
						<input type="text" id="beginNumberPartThree" name="beginNumberPartThree" size="4" title="号码第三部分" MaxLength="4" />
						<input type="checkbox" id="checkBeginNumberPartThree" name="checkBeginNumberPartThree" onClick="javascript:clickCheckBox(3);"/>
					</td>
				</tr>
				<tr>
					<td noWrap width="80" class="label">
						终止电话号码：
					</td>
					<td>
						<input type="text" id="endNumberPartOne" name="endNumberPartOne" size="3" title="号码第一部分" MaxLength="3" />
						<input type="checkbox" id="checkEndNumberPartOne" name="checkEndNumberPartOne" onClick="javascript:clickCheckBox(4);"/>
					</td>
					<td>
						<input type="text" id="endNumberPartTow" name="endNumberPartTow" size="4" title="号码第二部分" MaxLength="4"/>
						<input type="checkbox" id="checkEndNumberPartTow" name="checkEndNumberPartTow" onClick="javascript:clickCheckBox(5);"/>
					</td>
					<td>
						<input type="text" id="endNumberPartThree" name="endNumberPartThree" size="4" title="号码第三部分" MaxLength="4"/>
						<input type="checkbox" id="checkEndNumberPartThree" name="checkEndNumberPartThree" onClick="javascript:clickCheckBox(6);"/>
					</td>
				</tr>
				<tr>
					<td class="label">
						存放公司：
					</td>
					<td colspan="3">
						<eoms:comboBox name="leave" id="leave" initDicId="10401"/>
					</td>
				</tr>
				<tr>
					<td colspan=4 align="left">

						<html:submit styleClass="button">
        				生成
      					</html:submit>
						&nbsp;&nbsp;
						<html:reset styleClass="button">
                           重置
      					</html:reset>
					</td>
				</tr>
			</table>

			</center>
		</html:form>
		<%@ include file="/common/footer_eoms.jsp"%>