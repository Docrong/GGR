<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="java.util.*"%>

<%
	String str = (String) request.getAttribute("StringTree");
	List filiales = (List) request.getAttribute("filiales");
%>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/css/area.js"></script>
<SCRIPT LANGUAGE=JavaScript>
var s=["s1","s2","s3"];
var opt0 = ["","",""];
var dsy = new Dsy();
<%=str%>
function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	change(dsy,0,s,opt0);
}
</SCRIPT>



<script language="JavaScript">
var d = document;
var saver = null;
var inArr = new Array('${eoms:a2u("所有测试卡")},7','${eoms:a2u("省际来访卡")},2','${eoms:a2u("省内来访卡")},5','${eoms:a2u("本地测试卡")},4','${eoms:a2u("国际来访卡")},1');
var outArr = new Array('${eoms:a2u("所有测试卡")},7','${eoms:a2u("省际出访卡")},3','${eoms:a2u("省内出访卡")},6','${eoms:a2u("国际出访卡")},0');
var allArr = new Array(inArr,outArr);
function checkCardNum(v) {
  d.getElementById("localleave").style.display=(v==1)? "none" : "block";
  d.getElementById("_toCountry").style.display=(v==0)? "none" : "block";
  d.getElementById("_toCrit").style.display=(v==0)? "none" : "block";
  d.getElementById("_toCity").style.display=(v==0)? "none" : "block";
  d.getElementById("_filiales").style.display=(v==0)? "none" : "block";
  var sel = d.getElementById("cardType");
  sel.innerHTML = "";
  for(var i=0;i<allArr[v].length;i++){
    sel.add(makeOpt(allArr[v][i]));
  }
}
function makeOpt(str){
  var oOption = document.createElement("option");
  oOption.text = str.substring(0,str.indexOf(","));
  oOption.value = str.substring(str.indexOf(",")+1);
  return oOption;
}
</script>
<script LANGUAGE=JavaScript>
Ext.onReady(function(){
	checkCardNum(0);
	setup(); 
}); 
</script>
<br>
<form method="post" action="../TawTestcardManager/activatelist.do" onsubmit="true">
	<input type="hidden" name="operation" value="0" />
	<center>
		<table width="50%" align="center" class="formTable">
			<caption>
					<b>${eoms:a2u("测试卡激活")}</b>
			</caption>
			<tr>
				<td rowspan="2" noWrap width="100" class="label">
					${eoms:a2u("卡类型")}
				</td>
				<td>
					&nbsp;
					<input type="radio" name="cardTypeNum" value="0" checked="checked"
						onclick="checkCardNum(this.value)" />
					${eoms:a2u("来访卡")}&nbsp;&nbsp;&nbsp;
					<input type="radio" name="cardTypeNum" value="1"
						onclick="checkCardNum(this.value)" />
					${eoms:a2u("出访卡")}&nbsp;
				</td>
			</tr>
			<tr>
				<td align="left">
					<select name="cardType" style="width: 4.0cm;">
					</select>
				</td>
			</tr>
			<tr>
				<td class="label">
					${eoms:a2u("套餐类型")}
				</td>
				<td>
					<html:select property="cardpackage" style="width: 4.0cm;" value="">
						<html:optionsCollection name="tawTestcardForm" property="beCollep" />
					</html:select>
				</td>
			</tr>
			<tr id="localleave">
				<td noWrap width="100" class="label">
					${eoms:a2u("存放公司")}
				</td>
				<td width="380">
				<eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/>
					<%--<html:select property="leave" style="width: 4.0cm;" value="">
						<html:optionsCollection name="tawTestcardForm"
							property="beanCollectionDN" />
					</html:select>
				--%></td>
			</tr>
			<tr id="_filiales">
				<td class="label">
					${eoms:a2u("归属地市")}
				</td>
				<td>
					<select name="fromCity" style="width: 4.0cm;">
						<%
						for (int i = 0; i < filiales.size(); i++) {
						%>
						<option value="<%=filiales.get(i)%>">
							<%=filiales.get(i)%>
						</option>
						<%
						}
						%>
					</select>
				</td>
			</tr>
			<tr id="_toCountry">
				<td class="label">
					${eoms:a2u("拜访国家")}
				</td>
				<td>
					<select name="toCountry" id="s1" style="width: 4cm;"></select>
				</td>
			</tr>
			<tr id="_toCrit">
				<td class="label">
					${eoms:a2u("拜访省份")}
				</td>
				<td>
					<select name="toCrit" id="s2" style="width: 4cm;"></select>
				</td>
			</tr>
			<tr id="_toCity">
				<td class="label">
					${eoms:a2u("拜访地市")}
				</td>
				<td>
					<select name="toCity" id="s3" style="width: 4cm;"></select>
				</td>
			</tr>
			<tr>
				<td noWrap width="100" class="label">
					${eoms:a2u("当前状态")}
				</td>
				<td width="380">
					<html:select property="state" style="width: 4.0cm;" value="">
						<html:optionsCollection name="tawTestcardForm"
							property="beanCollection" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td noWrap width="100" class="label">
					${eoms:a2u("卡号")}(iccid)
				</td>
				<td width="380">
					<input type="text" name="iccid" size="20" value="" class="clstext">
				</td>
			</tr>
			<tr>
				<td noWrap width="100" class="label">
					msisdn
				</td>
				<td width="380">
					<input type="text" name="msisdn" size="20" value="" class="clstext">
				</td>
			</tr>
			<tr>
				<td noWrap width="100" class="label">
					imsi
				</td>
				<td width="380">
					<input type="text" name="imsi" size="20" value="" class="clstext">
				</td>
			</tr>
			<tr align="left" >
				<td colspan="2">
				<INPUT type="submit" class="button"  value="${eoms:a2u('查询')}"  name="submit">&nbsp;&nbsp;
					 
					&nbsp;&nbsp;
					<html:reset  styleClass="button">${eoms:a2u("重置")}</html:reset>
				</td>
			</tr>
		</table>
	</center>
</form>
<%@ include file="/common/footer_eoms.jsp"%>
