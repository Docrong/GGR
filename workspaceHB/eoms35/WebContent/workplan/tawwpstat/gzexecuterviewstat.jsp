<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteContentVO"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>

<%
  TawwpMonthPlanVO tawwpMonthPlanVO = (TawwpMonthPlanVO)request.getAttribute("tawwpmonthplanvo");
  String firstDay = tawwpMonthPlanVO.getYearFlag() + "-" + tawwpMonthPlanVO.getMonthFlag() + "-"
                  + "01 00:00:00";
  int week = TawwpUtil.getWeek(firstDay)-1;
  int dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlanVO.getYearFlag(),tawwpMonthPlanVO.getMonthFlag());
  TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
  Hashtable monthExecuteVOHash = tawwpMonthPlanVO.getMonthExecuteVOHash();
  Hashtable executeContentVOHash = null;
  String tempStr = "";
  String currUser = (String)request.getAttribute("curruser");
  String userId = "";
  if("1".equals(tawwpMonthPlanVO.getStubFlag())){
    userId = tawwpMonthPlanVO.getUserByStub();
  }
  else{
    userId = currUser;
  }
  TawwpExecuteContentVO tawwpExecuteContentVO = null;
  //add by denden （贵州本地）
    String startDategz="",endDategz="";

    startDategz=request.getAttribute("startDategz").toString();
   endDategz=request.getAttribute("endDategz").toString();
//           System.out.println("startDategz1"+startDategz);
//                    System.out.println("endDategz1"+endDategz);
 //end by denden （贵州本地）
%>
<script language="JavaScript">
function GoBack()
{
  location.href="../tawwpstat/gzquerymonthplanuser.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&startDategz=<%=startDategz%>&endDategz=<%=endDategz%>";
}

function onExecuteAdd(_executeContentId)
{
  var monthPlanId = "<%=tawwpMonthPlanVO.getId()%>";
  var userByStub = "<%=tawwpMonthPlanVO.getUserByStub()%>";
  location.href="../tawwpexecute/executeadd.do?executecontentid=" + _executeContentId + "&monthplanid=" + monthPlanId + "&userbystub=" + userByStub;
}

function onExecuteEdit(_executeContentId)
{
  var monthPlanId = "<%=tawwpMonthPlanVO.getId()%>";
  var executeType = "<%=tawwpMonthPlanVO.getExecuteType()%>";
  var userByStub = "<%=tawwpMonthPlanVO.getUserByStub()%>";
  location.href="../tawwpexecute/executeedit.do?executecontentid=" + _executeContentId + "&monthplanid=" + monthPlanId + "&executetype=" + executeType + "&userbystub=" + userByStub;
}
</script>

<!--  body begin  -->
<br><br><br>
<table class="listTable">
  <tr>
    <td width="20%" colspan="34" height="10">
      <%=tawwpMonthPlanVO.getYearFlag()%> <bean:message key="gzexecuterviewstat.title.labYear" />
      <%=tawwpMonthPlanVO.getMonthFlag()%> <bean:message key="gzexecuterviewstat.title.labMonth" />
      &lt;&nbsp; <%=tawwpMonthPlanVO.getName()%> &nbsp;&gt;
      <%
          if("1".equals(tawwpMonthPlanVO.getStubFlag())){
      %>
      &lt;<font color="red"> <bean:message key="gzexecuterviewstat.title.labAgent" /></font>&gt;
      <%
          }
      %>
    </td>
  </tr>
  <tr>
    <td width="20%" rowspan="2" height="36"> <bean:message key="gzexecuterviewstat.title.labContent" /></td>
    <td width="5%" rowspan="2" height="36"> <bean:message key="gzexecuterviewstat.title.labCycle" /></td>
    <td width="3%" height="16"><bean:message key="gzexecuterviewstat.title.labCount" /></td>
    <%
      for(int i=1; i<(dayCount+1); i++){
    %>
    <td width="3%" height="16">
      <%
        if("1".equals(tawwpMonthPlanVO.getStubFlag())){
      %>
      <a href="../tawwpexecute/executeadds.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&flag=daily&userbystub=<%=tawwpMonthPlanVO.getUserByStub()%>&date=<%=i%>"><%=i%></a>
      <%
        }else{
      %>
      <a href="../tawwpexecute/executeadds.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&flag=daily&date=<%=i%>"><%=i%></a>
      <%
        }
      %>
    </td>
    <%
      }
    %>
  </tr>
  <tr>
    <td width="3%" height="14"> <bean:message key="gzexecuterviewstat.title.labWeek" /></td>
     
    <%
      for(int j=1; j<(dayCount+1); j++){
        if(week == 7){
    %>
    <td width="3%" height="16">
       <bean:message key="gzexecuterviewstat.title.labDay" />
    </td>
    <%
        week = 1;
        }
        else
        {
    %>
    <td width="3%" height="16">
      <%=week%>
    </td>
    <%
        week++;
        }
      }
    %>
  </tr>
  <%
    List monthExecuteVOList = tawwpMonthPlanVO.getMonthExecuteVOList();
    for(int k=0;k<monthExecuteVOList.size(); k++){
      tawwpMonthExecuteVO = (TawwpMonthExecuteVO)monthExecuteVOList.get(k);
      executeContentVOHash = (Hashtable)monthExecuteVOHash.get(tawwpMonthExecuteVO);
  %>
  <tr>
    <td width="20%" rowspan="2" height="38">
      <%=tawwpMonthExecuteVO.getName()%>
    </td>
    <td width="5%" rowspan="2" height="38">
      <%=tawwpMonthExecuteVO.getCycleName()%>
    </td>
    <td width="3%" height="16"><bean:message key="gzexecuterviewstat.title.labCount" /></td>
    <%
      char[] temp = (tawwpMonthExecuteVO.getExecuteDate()).toCharArray();
      for(int x=1; x<(temp.length+1); x++){
        if(temp[x-1] == '1'){
          if(x<10){
            tempStr = tawwpMonthExecuteVO.getId()+"0"+ String.valueOf(x);
          }else{
            tempStr = tawwpMonthExecuteVO.getId()+String.valueOf(x);
          }
          //System.out.println("调试信息：tempStr="+tempStr);
          tawwpExecuteContentVO = (TawwpExecuteContentVO)(executeContentVOHash.get(tempStr));
          if(tawwpExecuteContentVO!=null)
	          if (!"0".equals(tawwpExecuteContentVO.getExecuteFlag())) {
	              if ("0".equals(tawwpMonthPlanVO.getExecuteType()) ||
	               ( ("," + tawwpExecuteContentVO.getCruser() + ",").indexOf("," +
	               userId + ",") >= 0)) {
	    %>
	    <td width="3%" height="16">
	      <a href="javascript:onExecuteEdit('<%=tawwpExecuteContentVO.getId()%>');">
	        <img src="${app }/images/icons/yes.gif">
	      </a>
	    </td>
	    <%
	              }
	              else
	              {
	    %>
	    <td width="3%" height="16">
	      <a href="javascript:onExecuteAdd('<%=tawwpExecuteContentVO.getId()%>');">
	        <img src="${app }/images/icons/yes.gif">
	      </a>
	    </td>
	    <%
	              }
	          }
	          else
	          {
	    %>
	    <td width="3%" height="16">
	      <a href="javascript:onExecuteAdd('<%=tawwpExecuteContentVO.getId()%>');">
	        <img src="${app }/images/icons/yes.gif">
	      </a>
	    </td>
	    <%
	          }
	      else{
%>
    <td width="3%" height="16">
    </td>
<%
	      }
        }else{
    %>
    <td width="3%" height="16">
    </td>
    <%
        }
      }
    %>
  </tr>
  <tr class="complete">
    <td width="3%" height="14"> <bean:message key="gzexecuterviewstat.title.labExecute" /></td>
    <%
      for(int x=1; x<(dayCount+1); x++){
          if(x<10){
            tempStr = tawwpMonthExecuteVO.getId()+"0"+ String.valueOf(x);
          }else{
            tempStr = tawwpMonthExecuteVO.getId()+String.valueOf(x);
          }

          tawwpExecuteContentVO = (TawwpExecuteContentVO)(executeContentVOHash.get(tempStr));
          if(tawwpExecuteContentVO!=null){
            if (!"0".equals(tawwpExecuteContentVO.getExecuteFlag())) {
              if ("0".equals(tawwpMonthPlanVO.getExecuteType()) ||
               ( ("," + tawwpExecuteContentVO.getCruser() + ",").indexOf("," +
               userId + ",") >= 0)) {
    %>
    <td width="3%" height="16" class="icon">
 		 <img src="${app }/images/icons/yes.gif" />
    </td>
    <%
              }
              else
              {
     %>
     <td width="3%" height="16">
     </td>
     <%
            }
          }
          else
          {
     %>
     <td width="3%" height="16">
     </td>
     <%
          }
        }
        else
        {
    %>
    <td width="3%" height="16">
     </td>
    <%
        }
      }
    %>
  </tr>
  <%
    }
  %>
</table>
<INPUT type=button value=" <bean:message key="gzexecuterviewstat.title.btnBack" />"  name="button" Onclick="GoBack();" class="button">


<!--  body end  -->

<%@ include file="/common/footer_eoms.jsp"%>
