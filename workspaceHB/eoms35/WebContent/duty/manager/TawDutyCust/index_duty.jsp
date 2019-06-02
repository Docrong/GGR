<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="java.util.*,java.lang.*,com.boco.eoms.common.util.StaticMethod"%>
<%
String webapp = request.getContextPath();
String imgPath = webapp + "/images";
String skinPath = webapp + "/skins/themes/unicom";
Vector vectorQueryResult = (Vector)request.getAttribute("QUERYRESULT");

%>
<head>
<title>值班记录列表</title>
<link href="<%=skinPath%>/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.STYLE1 {color: #666666}
-->
</style>
<script type="text/javascript">
  function h(id){
    if(document.getElementById(id)){
      var obj = document.getElementById(id);
      obj.style.display = (obj.style.display == "none" ? "block" : "none");
      event.srcElement.src = (obj.style.display == "none" ? 
                             "<%=imgPath%>/hljunicom/arrow_2.gif" : 
                             "<%=imgPath%>/hljunicom/arrow_1.gif");
    }
    else{
      return;
    }     
  }
</script>
</head>
<body class="bgbody">
<form name="form1" method="post" >
<input type="hidden" name="id" value="">
<br>
<table cellpadding="0" cellspacing="5" width="98%" border="0" class="clsbkgd" align="center">
  <tr class="top_table_1">
    <td colspan=2><table cellpadding="0" cellspacing="0" class="top_table_1" width="100%" align="center">
          <tr>
            <td class="index_1_td"><img src="<%=imgPath%>/hljunicom/mtable_1.gif" width="6" height="27"></td>
            <td background="<%=imgPath%>/hljunicom/mtable_1_bg.gif"><table cellpadding="1" cellspacing="0" class="maintable">
                <tr>
                  <td class="index_1_td_2"><img src="<%=imgPath%>/hljunicom/arrow_7.gif" width="14" height="14"></td>
                  <td class="letter_2">班次信息_message </td>
                  <td class="index_1_td_2"><img src="<%=imgPath%>/hljunicom/arrow_1.gif" width="16" height="16" onClick="h('duty')"></td>
                </tr>
            </table></td>
          <td align="right" class="index_1_td"><img src="<%=imgPath%>/hljunicom/mtable_2.gif" width="6" height="27"></td>
        </tr>
        <tr id="duty">
          <td background="<%=imgPath%>/hljunicom/mtable_2_bg.gif">&nbsp;</td> 
          <td class="index_1_td_1_bg"><table width="100%" border="0">
            <tr>
<td>
<table cellpadding="1" cellspacing="1" border="0" width="98%" class="table_show" align="center">
<% if(vectorQueryResult.size()>0) { %>
<tr align="center" valign="middle" class="td_label">
<td  >机房名称</td>
<td  >值班日期</td>
<td  class="td_label">
星期</td>
<td  class="td_label">
开始时间</td>
<td  class="td_label">
结束时间</td>
<td  class="td_label">
值班班长</td>
<td  class="td_label">
值班人员</td>
</tr>
<% } %>
<%
for(int y=0;y<vectorQueryResult.size();y++){
	Vector getVector = (Vector)vectorQueryResult.elementAt(y);	
	
	Vector getDutydate = (Vector)getVector.elementAt(0);
	Vector getStarttime = (Vector)getVector.elementAt(1);
	Vector getEndtime = (Vector)getVector.elementAt(2);
	Vector getDutymaster =  (Vector)getVector.elementAt(3);
	Vector getDutyman = (Vector)getVector.elementAt(4);
	Vector getRoomName = (Vector)getVector.elementAt(5);
	
	Vector td_num = new Vector();
	String temp_Dutydate = "";
	int tdNum = 0;
	for(int i=0;i<getDutydate.size();i++){
	td_num.add("0");
	}
	for(int i=0;i<getDutydate.size();){
	temp_Dutydate = String.valueOf(getDutydate.elementAt(i));
	tdNum=0;
	for (int j=i;j<getDutydate.size();j++){
	  if (temp_Dutydate.equals(String.valueOf(getDutydate.elementAt(j)))){
	    tdNum++;
	  }
	}
	td_num.setElementAt(String.valueOf(tdNum),i);
	i+=tdNum;
	}
	
	GregorianCalendar cal = new GregorianCalendar();
	String weekName[] = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
	for(int i=0;i<getDutydate.size();i++){
%>
<tr  align="center" valign="middle" class="tr_show">


<%if (!String.valueOf(td_num.elementAt(i)).equals("0")){%>

<td  rowspan="<%=String.valueOf(td_num.elementAt(i))%>" >
<%=String.valueOf(getRoomName.elementAt(0))%>
</td>


<td  rowspan="<%=String.valueOf(td_num.elementAt(i))%>" >
<%=String.valueOf(getDutydate.elementAt(i))%>
</td>
<%
   Vector date_vector = StaticMethod.getVector(String.valueOf(getDutydate.elementAt(i)).trim(),"-");
   int year=Integer.parseInt(String.valueOf(date_vector.elementAt(0)).trim());
   int month=Integer.parseInt(String.valueOf(date_vector.elementAt(1)).trim());
   int day=Integer.parseInt(String.valueOf(date_vector.elementAt(2)).trim());
   java.util.Date date = new java.util.Date(year-1900,month-1,day-0);

   int numOfWeek = date.getDay();
%>
<td rowspan="<%=String.valueOf(td_num.elementAt(i))%>"  <%if (numOfWeek==0 || numOfWeek==6){%> <%}else{%> <%}%>>
<%=weekName[numOfWeek]%>
</td>
<%}%>
<td>
<%=String.valueOf(getStarttime.elementAt(i)).substring(11,19)%>
</td>
<td>
<%=String.valueOf(getEndtime.elementAt(i)).substring(11,19)%>
</td>
<td>
<%=String.valueOf(getDutymaster.elementAt(i))%>
</td>
<td>
<%=String.valueOf(getDutyman.elementAt(i))%>
</td>
</tr>
<%
} }
%>
</table>
          </table></td>
          <td background="<%=imgPath%>/hljunicom/mtable_3_bg.gif">&nbsp;</td>
        </tr>
        <tr>
          <td><img src="<%=imgPath%>/hljunicom/mtable_3.gif" width="6" height="6"></td>
          <td background="<%=imgPath%>/hljunicom/mtable_4_bg.gif" class="index_1_td_1"></td>
          <td><img src="<%=imgPath%>/hljunicom/mtable_4.gif" width="6" height="6"></td>
        </tr>
    </table></td>
  </tr>
    <tr>
<td valign="top"><table cellpadding="0" cellspacing="0" width="100%" align="center">
          <tr>
            <td class="index_1_td"><img src="<%=imgPath%>/hljunicom/mtable_1.gif" width="6" height="27"></td>
            <td background="<%=imgPath%>/hljunicom/mtable_1_bg.gif"><table cellpadding="1" cellspacing="0" class="maintable">
                <tr>
                  <td class="index_1_td_2"><img src="<%=imgPath%>/hljunicom/arrow_7.gif" width="14" height="14"></td>
                  <td class="letter_2">值班信息_message </td>
                  <td class="index_1_td_2"><img src="<%=imgPath%>/hljunicom/arrow_1.gif" width="16" height="16" onClick="h('board')"></td>
                </tr>
            </table></td>
            <td align="right" class="index_1_td"><img src="<%=imgPath%>/hljunicom/mtable_2.gif" width="6" height="27"></td>
          </tr>
          <tr id="board">
            <td background="<%=imgPath%>/hljunicom/mtable_2_bg.gif">&nbsp;</td>
            <td class="index_1_td_1_bg"><table width="100%" align="center">
                              <tr>
                <td height="10"></td>
              </tr>
              <tr width="515">
                <td class="index_1_td_1_bg" height="117" valign="top">
                <jsp:include page="/infopub/TawInformation/list_inc.do?boardId=2055&infoType=1" flush='true'/>
               </td>
              </tr>
             <tr>
                <td align="right" class="letter_3"><img src="<%=imgPath%>/hljunicom/more2.gif" onClick="window.location='<%=webapp%>/infopub/TawInformation/viewNew.do?boardId=2055&infoType=1&boardId2=10121&viewFlag=1'" align="absmiddle"></td>
              </tr>
                      </table></td>
            <td background="<%=imgPath%>/hljunicom/mtable_3_bg.gif">&nbsp;</td>
          </tr>
          <tr>
            <td><img src="<%=imgPath%>/hljunicom/mtable_3.gif" width="6" height="6"></td>
            <td background="<%=imgPath%>/hljunicom/mtable_4_bg.gif" class="index_1_td_1"></td>
            <td><img src="<%=imgPath%>/hljunicom/mtable_4.gif" width="6" height="6"></td>
          </tr>
        </table>&nbsp;</td>
        <td valign="top">
        <table cellpadding="0" cellspacing="0" class="top_table_1" border="0" width="100%">
          <tr>
            <td class="index_1_td"><img src="<%=imgPath%>/hljunicom/table_1.gif" width="6" height="27"></td>
            <td background="<%=imgPath%>/hljunicom/table_1_bg.gif"><table cellpadding="1" cellspacing="0" class="maintable">
                <tr>
                  <td class="index_1_td_2"><img src="<%=imgPath%>/hljunicom/arrow_9.gif" width="16" height="18"></td>
                  <td class="letter_1">短信</td>
                  <td class="index_1_td_2"><img src="<%=imgPath%>/hljunicom/arrow_1.gif" width="16" height="16" onclick="h('sms')"></td>           
                 </tr>
            </table></td>
            <td align="right" class="index_1_td"><img src="<%=imgPath%>/hljunicom/table_2.gif" width="6" height="27"></td>
          </tr>
          <tr id="sms">
            <td background="<%=imgPath%>/hljunicom/table_2_bg.gif">&nbsp;</td>
            <td class="index_1_td_1_bg"><table cellpadding="1" cellspacing="1" class="top_table_1">
             <form action="../template/sm.jsp" method="POST" name="smform">
              <tr>
                <td width="24%">手机号</td>
                <td width="76%"><input name="textfield" type="text" class="table" size="15"></td>
              </tr>
              <tr>
                <td valign="top">内容</td>
                <td><textarea name="textfield2" cols="15" rows="5" class="table"></textarea></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><span class="STYLE1">内容可以输入70个字</span></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><input name="Submit" type="submit" class="clsbtn2" value="提 交"></td>
              </tr>
              </form>
            </table></td>
            <td background="<%=imgPath%>/hljunicom/table_3_bg.gif">&nbsp;</td>
          </tr>
          <tr>
            <td><img src="<%=imgPath%>/hljunicom/table_3.gif" width="6" height="6"></td>
            <td background="<%=imgPath%>/hljunicom/table_4_bg.gif" class="index_1_td_1"></td>
            <td><img src="<%=imgPath%>/hljunicom/table_4.gif" width="6" height="6"></td>
          </tr>
        </table>
        
        </td>
</tr>
</table>
</form>
</body>
