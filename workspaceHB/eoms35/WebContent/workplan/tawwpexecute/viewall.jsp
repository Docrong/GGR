<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteContentVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteAssessVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteReportVO"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import ="com.boco.eoms.workplan.flow.model.Step"%>

<%@ page import="com.boco.eoms.commons.system.user.model.*"%>
<%@ page import="java.text.*" %>
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

  TawwpExecuteContentVO tawwpExecuteContentVO = null;
  float totalCount = Float.parseFloat(tawwpMonthPlanVO.getTotalCount()); //需要执行总数
  float executedCount = Float.parseFloat(tawwpMonthPlanVO.getExecutedCount()); //已经执行总数
  float inTimeCount = Float.parseFloat(tawwpMonthPlanVO.getInTimeCount()); //及时执行总数
  float timeOutCount = Float.parseFloat(tawwpMonthPlanVO.getTimeOutCount()); //超时执行总数

  TawwpExecuteAssessVO tawwpExecuteAssessVO = null;
  TawwpExecuteReportVO tawwpExecuteReportVO = null;

  Step step = tawwpMonthPlanVO.getStep();
  String flowSerial = null;
  String stepSerial = null;
  if(step != null){
    flowSerial = String.valueOf(step.getFlowSerial());
    stepSerial = step.getSerial();
  }
%>
<script language="JavaScript">
Ext.onReady(function(){
	colorRows('detailTable');
})
Ext.onReady(function(){
	colorRows('reportTable');
})
Ext.onReady(function(){
	colorRows('checkTable');
})

function onExecute(_executeContentId)
{
  location.href="../tawwpstat/viewmonthcontent.do?executecontentid=" +  _executeContentId;
}

function onReport()
{
  var monthPlanId = "<%=tawwpMonthPlanVO.getId()%>";
  var monthFlag = "<%=tawwpMonthPlanVO.getMonthFlag()%>";
  var yearFlag = "<%=tawwpMonthPlanVO.getYearFlag()%>";
  location.href="../tawwpexecute/reportadd.do?monthplanid=" + monthPlanId + "&monthflag=" + monthFlag + "&yearflag=" + yearFlag;
}

function onReportEdit(_executeReportId)
{
  var monthPlanId = "<%=tawwpMonthPlanVO.getId()%>";
  location.href="../tawwpexecute/reportedit.do?executereportid=" + _executeReportId + "&monthplanid=" + monthPlanId;
}


function onCheck(_monthPlanId)
{

  var checkDept = document.forms[0].checkdept.value;
   
  var checkUser="";
  var selobj = document.getElementsByName("checkUsers");
   
  for(i=0;i<selobj.length;i++){
    if(selobj[i].checked==true){
       checkUser=checkUser+","+	selobj[i].value; 
    }  
    
  } 
  if(checkUser==""){
    alert("<bean:message key="viewall.title.warnNotSelChecker" />");
    return false;
  }else{
    if( !confirm("<bean:message key="viewall.title.warnToSubmit" />") ) return;
    var flowSerial = "<%=flowSerial%>";
    var stepSerial = "<%=stepSerial%>";
    checkUser=checkUser.substring(1);
    location.href="../tawwpexecute/executerefer.do?monthplanid="+ _monthPlanId + "&flowserial="+ flowSerial + "&stepserial="+ stepSerial + "&checkuser="
                       + checkUser;
               
  }
}

function onPublic(_monthPlanId) //直接进行发布
{
  location.href="../tawwpexecute/executepublic.do?monthplanid=" + _monthPlanId;
}
</script>

<!--  body begin  -->
<form name="monthplan">
<table class="listTable" id="detailTable">
  <thead>
  <tr>
    <td width="20%" colspan="34" height="10">
      <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="viewall.title.labYear" />
      <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="viewall.title.labMonth" />
      &lt;&nbsp <%=tawwpMonthPlanVO.getName()%> &nbsp;&gt;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <font color="red"><bean:message key="viewall.title.labFinishRate" />
<%		DecimalFormat   df=new   DecimalFormat(".##"); 
        if(executedCount==0){
            out.print(0);
        }else{
        	 
        	
            String temp=df.format((executedCount/totalCount)*100);   
            
            out.print(temp);
        }
%>%
       &nbsp;&nbsp;<bean:message key="viewall.title.labInTimeRate" />
<%
		if(executedCount==0){
            out.print(0);
        }else{
        	  
			String temp=df.format((inTimeCount/executedCount)*100);
			out.print(temp);
 		}
%>		%</font>
    </td>
  </tr>
  <tr>
    <td width="20%" rowspan="2" height="36"><bean:message key="viewall.title.detailFmContent" /></td>
    <td width="30%" rowspan="2" height="36"><bean:message key="viewall.title.detailFmCycle" /></td>
    <td width="3%" height="16"><bean:message key="viewall.title.detailFmCount" /></td>
    <%
    for(int i=1; i<(dayCount+1); i++){
    %>
    <td width="3%" height="16">
      <%=i%>
    </td>
    <%
    }
    %>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td width="3%" height="14"> </td>
    <td width="30%" height="14"> </td>
    <td width="3%" height="14"><bean:message key="viewall.title.detailFmWeek" /></td>
     
    <%
                          for(int j=1; j<(dayCount+1); j++){
                          if(week == 7){
         %>
    <td width="3%" height="16">
      <bean:message key="viewall.title.detailFmDay" />
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
        for(int k=0; k<monthExecuteVOList.size(); k++){
          tawwpMonthExecuteVO = (TawwpMonthExecuteVO)monthExecuteVOList.get(k);
          executeContentVOHash = (Hashtable)monthExecuteVOHash.get(tawwpMonthExecuteVO);
  %>
  <tr>
    <td width="20%" rowspan="2" height="38">
      <%=tawwpMonthExecuteVO.getName()%>
    </td>
    <td width="30%" rowspan="2" height="38">
      <%=tawwpMonthExecuteVO.getCycleName()%>
      <nobr><b><%=tawwpMonthExecuteVO.getStartHH()%>:00-<%=tawwpMonthExecuteVO.getEndHH()%>:59</b></nobr>
    </td>
    <td width="3%" height="16"><bean:message key="viewall.title.detailFmCount" /></td>
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
    %>
    <td width="3%" height="16">
       <img src="${app }/images/icons/yes.gif">
    </td>
    <%
    }else{
    %>
    <td width="3%" height="16">
    </td>
    <%
              }
              }
    %>
  </tr>
  <tr>
    <td width="3%" height="14">
       <bean:message key="viewall.title.detailFmExecute" />
    </td>
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
    %>
    <td width="3%" height="16">
      <a href="javascript:onExecute('<%=tawwpExecuteContentVO.getId()%>');">
        <img src="${app }/images/icons/yes.gif">
      </a>
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
     %>
  </tr>
  <%
  }
  %>
  <tr>
    <td width="20%" colspan="34" height="10">
    <%
    if(tawwpMonthPlanVO.getExecuteState().equals("4") || tawwpMonthPlanVO.getExecuteState().equals("2")){
    %><%
		if (step != null && step.getCheckUserList().size() > 0) {
		%>
		 
				<bean:message key="monthview.title.labCanCheckUser" />
				 
<%  		
					List listCheckUser=step.getCheckUserList();
					for(int i=0;i<listCheckUser.size();i++){
						TawSystemUser user=(TawSystemUser)listCheckUser.get(i);
					 
						if(tawwpMonthPlanVO.getDeptId().equals(user.getDeptid())){
%>					<input type="checkbox" value="<%=user.getUserid() %>" name="checkUsers" ><%=user.getDeptname() %>&nbsp;<%=user.getUsername()%>&nbsp;&nbsp;
					
<%  					 }
				  }
%>
		 
<%
		}
%>    <br>
      <input type="hidden" name="checkdept" value="<%=step.getCheckUserIdStr()%>">  
      <INPUT type=button value="<bean:message key="viewall.title.btnReport" />"  name="button" Onclick="onReport();" class="button">
      <%
      if(step !=null && step.getCheckUserList().size()>0){
      %>
      <input type="button" value="<bean:message key="viewall.title.btnToCheck" />" name="B1" class="button" onclick="javascript:onCheck('<%=tawwpMonthPlanVO.getId()%>');">
      <%
                  }
                  else{
      %>
      <input type="button" value="<bean:message key="viewall.title.btnToPublic" />" name="B1" class="button" onclick="javascript:onPublic('<%=tawwpMonthPlanVO.getId()%>');">
    <%
            }
            }
    %>
      <input type="button" value="<bean:message key="viewall.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
    </td>
  </tr>
  </tbody>
</table>

<br>
<br>

<table class="listTable" id="reportTable">
  <caption><bean:message key="viewall.title.reportFmTitle" /></caption>
  <thead>
  <tr>
    <td width="10%"><bean:message key="viewall.title.reportFmType" /></td>
    <td width="20%"><bean:message key="viewall.title.reportFmDate" /></td>
    <td width="25%"><bean:message key="viewall.title.reportFmContent" /></td>
    <td width="5%"><bean:message key="viewall.title.reportFmReportFlag" /></td>
    <td width="20%"><bean:message key="viewall.title.reportFmAdvice" /></td>
    <td width="15%"><bean:message key="viewall.title.reportFmRemark" /></td>
    <td width="5%"><bean:message key="viewall.title.reportFmEdit" /></td>
  </tr>
  </thead>
  <tbody>
  <%
        List executeReportVOList = tawwpMonthPlanVO.getExecuteReportVOList();
        for(int y=0; y<executeReportVOList.size(); y++){
          tawwpExecuteReportVO = (TawwpExecuteReportVO)executeReportVOList.get(y);
  %>
  <tr>
    <td width="10%">
      <%=tawwpExecuteReportVO.getReportTypeName()%>
    </td>
    <td width="20%">
      <%=tawwpExecuteReportVO.getStartDate()%> &nbsp;-&nbsp; <%=tawwpExecuteReportVO.getEndDate()%>
    </td>
    <td width="25%">
      <%=tawwpExecuteReportVO.getContent()%>
    </td>
    <td width="10%">
      <%
      if("1".equals(tawwpExecuteReportVO.getReportFlag())){
      %>
      <font color="red"><%=tawwpExecuteReportVO.getReportFlagName()%></font>
      <%
      }else{
      %>
      <%=tawwpExecuteReportVO.getReportFlagName()%>
      <%
      }
      %>
    </td>
    <td width="20%">
      <%=tawwpExecuteReportVO.getAdvice()%>
    </td>
    <td width="15%">
      <%=tawwpExecuteReportVO.getRemark()%>
    </td>
    <td width="5%">
      <a href="javascript:onReportEdit('<%=tawwpExecuteReportVO.getId()%>');">
        <img src="${app }/images/icons/edit.gif">
      </a>
    </td>
  </tr>
  <%
  }
  %>
  </tbody>
 </table>

<br>
<br>

<table class="listTable" id="checkTable">
  <caption><bean:message key="viewall.title.checkFmTitle" /></caption>
  <thead>
  <tr>
    <td width="15%"><bean:message key="viewall.title.checkFmCheckUser" /></td>
    <td width="25%"><bean:message key="viewall.title.checkFmCrtime" /></td>
    <td width="50%"><bean:message key="viewall.title.checkFmContent" /></td>
    <td width="10%"><bean:message key="viewall.title.checkFmState" /></td>
  </tr>
  </thead>
  <tbody>
  <%
        List executeAssessVOList = tawwpMonthPlanVO.getExecuteAssessVOList();
        for(int y=0; y<executeAssessVOList.size(); y++){
          tawwpExecuteAssessVO = (TawwpExecuteAssessVO)executeAssessVOList.get(y);
  %>
  <tr>
    <td width="15%"><%=tawwpExecuteAssessVO.getCheckUserName()%></td>
    <td width="25%"><%=tawwpExecuteAssessVO.getCrtime()%></td>
    <td width="50%"><%=tawwpExecuteAssessVO.getContent()%></td>
    <td width="10%"><%=tawwpExecuteAssessVO.getStateName()%></td>
  </tr>
  <%
  }
  %>

  <%
      if(tawwpMonthPlanVO.getExecuteState().equals("4") || tawwpMonthPlanVO.getExecuteState().equals("2")){
      if(step!=null){
        if(step.getCheckUserIdStr().equals("")){
  %>
    <table border="0" width="100%" cellspacing="1" cellpadding="1" align=center>
      <tr>
        <td width="60%" class="header">
          &lt;&nbsp;<%=step.getName()%>&nbsp;&gt;<bean:message key="viewall.title.checkFmNoCheck" />
        </td>
      </tr>
    </table>
  <%
        }
        }
      }
  %>
  </tbody>
</table>
</form>
<!--  body end  -->

<%@ include file="/common/footer_eoms.jsp"%>

