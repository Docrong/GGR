<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="com.boco.eoms.testcard.controller.TawTestcardForm,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>

 <html>
<head>
<%
 TawTestcardForm taw_testcard = (TawTestcardForm)request.getAttribute("tawTestcardForm");
 String str =(String)request.getAttribute("StringTree");
 String tostr = (String)request.getAttribute("StringToTree");
%>
<% 
String flag = taw_testcard.getCardType();
int temp =0;
if(flag!=null){
temp = Integer.parseInt(flag);
}
String temp0="",temp1="",temp2="",temp3="",temp4="",temp5="",temp6="";
switch(temp){
case 0:
temp0 = "checked";
break;
case 1:
temp1 = "checked";
break;
case 2:
temp2 = "checked";
break;
case 3:
temp3 = "checked";
break;
case 4:
temp4 = "checked";
break;
case 5:
temp5 = "checked";
break;
case 6:
temp6 = "checked";
break;
}
%>
<title> ${eoms:a2u("测试卡新增")}</title> 
<style>
body,select
{
	font-size:9pt;
	font-family:Verdana;
}
select {background-color:#F0F0F0;}
</style>
<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>
<SCRIPT LANGUAGE = JavaScript>
var s=["s1","s2","s3"];
var x=["x1","x2","x3"];
var opt0 = ["<%=StaticMethod.null2String(taw_testcard.getFromCountry())%>","<%=StaticMethod.null2String(taw_testcard.getFromCrit())%>","<%=StaticMethod.null2String(taw_testcard.getFromCity())%>"];
var dsy = new Dsy();
<% System.out.println("FromCountry=" + StaticMethod.null2String(taw_testcard.getFromCountry()));%>
<% System.out.println("FromCrit=" + StaticMethod.null2String(taw_testcard.getFromCrit()));%>
<% System.out.println("FromCity=" + StaticMethod.null2String(taw_testcard.getFromCity()));%>

<%=str%>
function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	  change(dsy,0,s,opt0);
	  
}



var toopt0=["<%=StaticMethod.null2String(taw_testcard.getToCountry())%>","<%=StaticMethod.null2String(taw_testcard.getToCrit())%>","<%=StaticMethod.null2String(taw_testcard.getToCity())%>"];
var todsy = new Dsy();

<% System.out.println("ToCountry=" + StaticMethod.null2String(taw_testcard.getToCountry()));%>
<% System.out.println("ToCrit=" + StaticMethod.null2String(taw_testcard.getToCrit()));%>
<% System.out.println("ToCity=" + StaticMethod.null2String(taw_testcard.getToCity()));%>


<%=tostr%>
function setup1()
{

	for(j=0;j<x.length-1;j++)	  
		document.getElementById(x[j]).onchange=new Function("change(todsy,"+(j+1)+",x,toopt0)");
	  change(todsy,0,x,toopt0);	
		
}

function selectcardType(){
 <%
  int isCardType = StaticMethod.null2int(taw_testcard.getCardType());
  System.out.println("isCardType=" + isCardType);
  switch (isCardType)
  {
   	 case 0: %>
    document.getElementById("cardType0").checked="checked";
 		<%
    break;
    case 1: %>
  	document.getElementById("cardType1").checked="checked";
   	changeCardType(1);
    <%
    break;
    case 2: %>
    document.getElementById("cardType2").checked="checked";
    changeCardType(2);
    <%
    break;
    case 3: %>
    document.getElementById("cardType3").checked="checked";
    changeCardType(3);
    <%
    break;
    case 4: %>
    document.getElementById("cardType4").checked="checked";
    changeCardType(4);
    <%
    break;
    case 5: %>
    document.getElementById("cardType5").checked="checked";
    changeCardType(5);
    <%
    break;
    case 6: %>
    document.getElementById("cardType6").checked="checked";
    changeCardType(6);
    <%
  }
 %>
}
</SCRIPT>
</head>
<body onload="setup();setup1();selectcardType()">
<html:form  method="post" action="/TawTestcard/editsave" onsubmit="return check(this);">
 
<%
TawSystemSessionForm saveSessionBeanForm
    = (TawSystemSessionForm)session.getAttribute("sessionform");
String iccid = taw_testcard.getIccid();
String userName=saveSessionBeanForm.getUsername();
String userId=saveSessionBeanForm.getUserid();
String deptId=String.valueOf(saveSessionBeanForm.getDeptid()).toString();
%>
 <html:hidden property="userId" value="userId"/>
 <html:hidden property="deptId" value="deptId"/>

<SCRIPT LANGUAGE=javascript>

function isnumeric(p)
{
 if (p == "")
  return false;
 var l = p.length;
 var count=0;
 for(var i=0; i<l; i++)
 {
  var digit = p.charAt(i);
  if(digit == "." )
 {
    ++count;
    if(count>1) {
	return false; }
   }
  else if(digit < "0" || digit > "9")
  {
  return false;
  }
 }
 return true;
}
</SCRIPT>

<script language="JavaScript" >
function bb(main){
if (document.tawTranfaultreportForm.city.options.length!=0) {
for (i=1;i<=document.tawTranfaultreportForm.city.options.length;i++){
if(document.tawTranfaultreportForm.city.options[i].value==main)
{document.tawTranfaultreportForm.city.options[i].selected="true";
document.tawTranfaultreportForm.city.disabled="false";
}
}
}
}

function countrySelect(){
   var con=document.tawTestcardForm.fromCanton.value;
    alert(document.tawTestcardForm.fromCanton.value);
}

function check(){
if(document.tawTestcardForm.msisdn.value=="")
{
  alert("msisdn不得为空")
  return false;
}
if(document.tawTestcardForm.iccid.value=="")
{
  alert("卡号不得为空")
  return false;
}

else
{
  return true;
}

}
function changeCardType(v){
  document.getElementById("visitField").style.display = (v==1 || v==2 || v==5 || v==4) ? "none" : "block";
  document.getElementById("visitField2").style.display = (v==0 || v==3 || v==6) ? "none" : "block";
  document.getElementById("visitField3").style.display = (v==0 || v==3 || v==6) ? "block" : "none";
}

function showbutton(v){
  if(v==35){
    ms0.style.display="none";
    ms1.style.display="block";
    im0.style.display="none";
    im1.style.display="block";
    tr1.style.display="block";
  }
  else{
    ms0.style.display="block";
    ms1.style.display="none";
    im0.style.display="block";
    im1.style.display="none";
    tr1.style.display="none";
  }
}

</script>
<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
	<tr>
        <td align="center" class="table_title">
        	<b> ${eoms:a2u("测试卡修改")}</b>
        </td>
        </tr>
</table>
<table border="0" width="95%" cellspacing="1" class="formTable" align="center">
<html:hidden property="strutsAction"/>
<html:hidden property="id"/>
<html:hidden property="iccid"/>
       <tr>
       <td align="right" width="100%" bgColor="#f5f5f5">
       <div align="center">
       <table cellSpacing="0" borderColorDark="#ffffff" cellPadding="4" width="100%" borderColorLight="#808080" border="1" align="center">
       <tr>
                <td noWrap width="80" class="label"> ${eoms:a2u("卡类型")}</td>
                <td colspan = 3 width="80%" align="left">
                  <input type="radio" id="cardType1" name="cardType" value="1" onclick="changeCardType(this.value);" <%=temp1%>/> ${eoms:a2u("国际来访卡")}&nbsp;
                  <input type="radio" id="cardType0" name="cardType" value="0" onclick="changeCardType(this.value);" <%=temp0%>/> ${eoms:a2u("国际出访卡")}&nbsp;
                  <input type="radio" id="cardType2" name="cardType" value="2" onclick="changeCardType(this.value);" <%=temp2%>/> ${eoms:a2u("省际来访卡")}&nbsp;
                  <input type="radio" id="cardType3" name="cardType" value="3" onclick="changeCardType(this.value);" <%=temp3%>/> ${eoms:a2u("省际出访卡")}&nbsp;
                  <input type="radio" id="cardType5" name="cardType" value="5" onclick="changeCardType(this.value);" <%=temp5%>/> ${eoms:a2u("省内来访卡")}&nbsp;
                  <input type="radio" id="cardType6" name="cardType" value="6" onclick="changeCardType(this.value);" <%=temp6%>/> ${eoms:a2u("省内出访卡")}&nbsp;
                  <input type="radio" id="cardType4" name="cardType" value="4" onclick="changeCardType(this.value);" <%=temp4%>/> ${eoms:a2u("本地测试卡")}&nbsp;
                </td>
       </tr>
       <tr>
                <td noWrap width="80" class="label"> ${eoms:a2u("归属地")}</td>
                <td width="100%" height="25" colspan="3" nowrap="nowrap">
                   ${eoms:a2u("国家")}<select  name="fromCountry" id="s1" style="width: 3cm;"></select> &nbsp&nbsp
                  ${eoms:a2u(" 省份")}<select name="fromCrit" id="s2" style="width: 3cm;"></select>
                  &nbsp&nbsp
                   ${eoms:a2u("地市")}<select name="fromCity" id="s3" style="width: 3cm;"></select>
                  &nbsp&nbsp
                   ${eoms:a2u("运营商")}<html:text styleClass="clstext" property="fromOpe"/>
                  </td>
        </tr>
        <tr id="visitField">
                <td noWrap width="80" class="label"> ${eoms:a2u("拜访地")}</td>
                <td width="100%" height="25" colspan="3" nowrap="nowrap">
                   ${eoms:a2u("国家")}<select name="toCountry" id="x1" style="width: 3cm;"></select>
                  &nbsp&nbsp
                   ${eoms:a2u("省份")}<select name="toCrit" id="x2" style="width: 3cm;"></select>
                  &nbsp&nbsp
                   ${eoms:a2u("地市")}<select name="toCity" id="x3" style="width: 3cm;"></select>
                  &nbsp&nbsp
                   ${eoms:a2u("运营商")}<html:text styleClass="clstext" property="toOpe"/>
                  </td>
        </tr>
          <tr  id="visitField2">
                <td noWrap width="80"  class="label">
                       ${eoms:a2u("存放公司")}
                </td>
                <td width="380">
              
                  <eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401" defaultValue="${tawTestcardForm.leave}"/>   
                </td>
                <td class="clsfth"> ${eoms:a2u("套餐类型")}
          </td>
          <td>
            <html:select property="cardpackage" style="width: 4.0cm;" onchange="showbutton(this.value);">
              <html:optionsCollection name="tawTestcardForm" property="beCollep"/>
            </html:select>
          </td>
       </tr>

       <tr>
          <td noWrap width="80"  class="label"> ${eoms:a2u("卡号(iccid)")}</td>
          <td width="380"><%=iccid%></td>
          <td noWrap width="80"  class="label"> ${eoms:a2u("单卡编号")}</td>
          <td width="380"><html:text styleClass="clstext" property="oldNo" size="20"/></td>
          
        </tr>
        <%try{ if(!(taw_testcard.getCardpackage()=="双模")&&!(taw_testcard.getCardpackage().trim().equals("双模"))){ %>  
         <tr>
               <td id="ms0" noWrap width="80"  class="label" style="display:block">
                      msisdn
                </td>
                <td id="ms1" noWrap width="80"  class="label" style="display:none">
                      msisdn1
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="msisdn" size="20"/>
                </td>
                <td id="im0" noWrap width="80"  class="label" style="display:block">
                      IMSI
                </td>
                <td id="im1" noWrap width="80"  class="label" style="display:none">
                      IMSI1
                </td>
               <td width="380">
                       <html:text styleClass="clstext" property="imsi" size="20"/>
                </td>              
       </tr>
       <tr id="tr1" style="display:none">
               <td noWrap width="80"  class="label">
                      msisdn2
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="msisdn1" size="20"/>
                </td>
                <td  noWrap width="80"  class="label">
                      IMSI2
                </td>
               <td width="380">
                       <html:text styleClass="clstext" property="imsi1" size="20"/>
                </td>              
       </tr>
       <% }else{ %>
       
         <tr>
               <td id="ms0" noWrap width="80"  class="label" style="display:none">
                      msisdn
                </td>
                <td id="ms1" noWrap width="80"  class="label" style="display:block">
                      msisdn1
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="msisdn" size="20"/>
                </td>
                <td id="im0" noWrap width="80"  class="label" style="display:none">
                      IMSI
                </td>
                <td id="im1" noWrap width="80"  class="label" style="display:block">
                      IMSI1
                </td>
               <td width="380">
                       <html:text styleClass="clstext" property="imsi" size="20"/>
                </td>              
       </tr>
       <tr id="tr1" style="display:block">
               <td noWrap width="80"  class="label">msisdn2</td>
                <td width="380">
                       <html:text styleClass="clstext" property="msisdn1" size="20"/>
                </td>
                <td  noWrap width="80"  class="label">IMSI2</td>
               <td width="380">
                       <html:text styleClass="clstext" property="imsi1" size="20"/>
                </td>              
       </tr>
 
       <% }}catch(Exception e){%>
       <tr>
               <td id="ms0" noWrap width="80"  class="label" style="display:block">
                      msisdn
                </td>
                <td id="ms1" noWrap width="80"  class="label" style="display:none">
                      msisdn1
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="msisdn" size="20"/>
                </td>
                <td id="im0" noWrap width="80"  class="label" style="display:block">
                      IMSI
                </td>
                <td id="im1" noWrap width="80"  class="label" style="display:none">
                      IMSI1
                </td>
               <td width="380">
                       <html:text styleClass="clstext" property="imsi" size="20"/>
                </td>              
       </tr>
       <tr id="tr1" style="display:none">
               <td noWrap width="80"  class="label">
                      msisdn2
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="msisdn1" size="20"/>
                </td>
                <td  noWrap width="80"  class="label">
                      IMSI2
                </td>
               <td width="380">
                       <html:text styleClass="clstext" property="imsi1" size="20"/>
                </td>              
       </tr>
         
       <%} %>
        <%--<tr>
          <td class="clsfth"> ${eoms:a2u("册号")}</td>
          <td> <html:text property="volumenum" size="20" styleClass="clstext"></html:text>     </td>
          <td class="clsfth"> ${eoms:a2u("页号")}</td>
          <td><html:text property="pagenum" size="20" styleClass="clstext"/>
          </td>
        </tr>       
       <tr>
               <td noWrap width="80"  class="label">
                       ${eoms:a2u("个人识别码1")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="pin1" size="20"/>
                </td>
                <td noWrap width="80"  class="label">
                       ${eoms:a2u("个人识别码2")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="pin2" size="20"/>
                </td>
       </tr>--%>
<!--        <tr>
               <td noWrap width="80"  class="label">
                       ${eoms:a2u("解锁码1")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="puk1" size="20"/>
                </td>
                <td noWrap width="80"  class="label">
                       ${eoms:a2u("解锁码2")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="puk2" size="20"/>
                </td>
       </tr>
       <tr>
               <td noWrap width="80"  class="label">
                       ${eoms:a2u("开机密码")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="password" size="20"/>
                </td>
                <td noWrap width="80"  class="label">
                      ${eoms:a2u("旧系统编号")}
               </td>
               <td width="380">
                    <html:text property="oldNo" styleClass="clstext" />
               </td>
       </tr>  
       <tr>
            <td  noWrap width="80"  class="label">
             	 ${eoms:a2u("测试卡单卡编号")}
            </td>
            <td width="380" colspan="3" >
            	<html:text property="oldNo" styleClass="clstext" />
            </td>
      </tr>-->
      
             <tr>
               <td noWrap width="80"  class="label">
                       ${eoms:a2u("个人识别码")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="pin1" size="20"/>
                </td>
                <td noWrap width="80"  class="label">
                       ${eoms:a2u("解锁码")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="puk1" size="20"/>
                </td>
       </tr>
        <tr>
               <td noWrap width="80"  class="label">
                       ${eoms:a2u("开机密码")}
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="password" size="20"/>
                </td>
                <td noWrap width="80"  class="label">
                  
               </td>
               <td width="380">
                   
               </td>
       </tr> 
       <tr>
              <td noWrap width="80"  class="label">
                     ${eoms:a2u("当前状态")}
              </td>
              <td width="380">
                    <html:select property="state" style="width: 4.0cm;">
                    <html:optionsCollection name="tawTestcardForm" property="beanCollection"/>
                    </html:select>
              </td>
              <td class="clsfth"> ${eoms:a2u("费用情况")}</td>
          <td> <html:text styleClass="clstext" property="exes" size="20"/>         </td>
       </tr>
       <tr>
            <td noWrap width="80"  class="label"> ${eoms:a2u("归属HLR厂商")}</td>
                <td width="380"><html:text styleClass="clstext" property="offer" size="20"/></td>
                <td noWrap width="80"  class="label"> ${eoms:a2u("归属HLR GT")}</td>
                <td width="380"><html:text styleClass="clstext" property="msgcenterno" size="20"/></td>
       </tr>
       <%--<tr>
               <td noWrap width="80"  class="label">
                      ${eoms:a2u("开始时间
              </td>
              <td width="380">
                     <html:text property="begintime" styleClass="clstext" onfocus="setday(this)" readonly="true"/>
              </td>
              <td noWrap width="80"  class="label">
                      ${eoms:a2u("注销时间
              </td>
              <td width="380">
                    <html:text property="endtime" styleClass="clstext" onfocus="setday(this)" readonly="true"/>
              </td>
       </tr>
        --%>
        <tr>
           <td  noWrap width="80"  class="label"> ${eoms:a2u("入库人")}</td>
           <td width="380" ><%=userName%></td>
           <td  width="80"  class="label"> ${eoms:a2u("入库时间")}</td>
           <td width="380" ><html:text styleClass="clstext" property="begintime" onfocus="setday(this)" onclick="popUpCalendar(this, this);" readonly="true"/></td>
       </tr><%--
       <tr>
            <td  width="80"  class="label">
             	 ${eoms:a2u("测试结果
            </td>
            <td width="380">
            	<html:text styleClass="clstext" property="testresult" size="20"/>
            </td>
            <td width="80"  class="label">
             	 ${eoms:a2u("处理结果
            </td>
            <td width="380" >
            	<html:text styleClass="clstext" property="dealresult" size="20"/>
            </td>
       </tr>
       --%>
       <tr id="visitField3">
       <td class="clsfth"> ${eoms:a2u("套餐类型")}
          </td>
          <td>
            <html:select property="cardpackage" style="width: 4.0cm;" onchange="showbutton(this.value);">
              <html:optionsCollection name="tawTestcardForm" property="beCollep"/>
            </html:select>
          </td>
       </tr>
       <tr>
            <td width="80"  class="label">
             	 ${eoms:a2u("存放位置")}
            </td>
            <td width="380" colspan="3" >
            	<html:text styleClass="clstext" property="position" size="77"/>
            </td>
      </tr>
       <tr>
            <td width="80"  class="label">
                       ${eoms:a2u("备注")}
                </td>
                <td width="380" colspan="3">
                       <html:textarea  property="operation" rows="4" cols="88"/>
                </td>
       </tr>
       <tr>
		<td colspan=4>
                <table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
                <tr align="right" valign="middle">
                <td>
                        <html:submit styleClass="button" onclick="return check()">
        			 ${eoms:a2u("保存")}
      			</html:submit>
      			<html:reset styleClass="button">
                        	 ${eoms:a2u("重置")}
      			</html:reset>&nbsp;&nbsp;
      		 </td>
        </tr>
	</table>
        </td>
	</tr>
</table>
</div>
</td>
</tr>
</table>
</html:form>
</body>
</html>
