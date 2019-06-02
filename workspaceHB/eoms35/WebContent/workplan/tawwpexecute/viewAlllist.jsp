<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.model.TawwpMonthPlan"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import ="java.util.List"%>

<script language="javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
	userViewer = new Ext.JsonView("user-list",	
			'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
			{ 
				multiSelect: true,		
				emptyText : '<div></div>'								
			}
		);
		userViewer.refresh();
		
		userTree = new xbox({
			btnId:'userTreeBtn',dlgId:'dlg-user',	
			treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'部门',treeChkMode:'',treeChkType:'dept',
			viewer:userViewer,saveChkFldId:'deptId' 
		});
	})
</script>

<%
  Hashtable monthPlanVOHash = (Hashtable)request.getAttribute("monthplanvohash");
   List listKey = (List)request.getAttribute("listKey");
  Enumeration hashKeys = null;
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
  TawwpMonthPlan tawwpMonthPlan = null;
  String monthPlanId = "";
  
  String _yearFlag ="";
  String _monthFlag =""; 
  String _deptId="";
   if(request.getAttribute("yearFlag")==null||request.getAttribute("yearFlag").equals("")){
		  _yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
	 }else{
			_yearFlag=(String)request.getAttribute("yearFlag");
	}
   if(request.getAttribute("monthFlag")==null||request.getAttribute("monthFlag").equals("")){
			_monthFlag=TawwpUtil.getCurrentDateTime("MM");
	}else{
			_monthFlag=(String)request.getAttribute("monthFlag");
	}
	if(request.getAttribute("deptId")!=null&&!request.getAttribute("deptId").equals("")){
			_deptId=(String)request.getAttribute("deptId");
	}
    
%>

<!--  body begin  -->

<form name="dailyexecuteplan" action="viewAllList.do">
     <br>
      <br>
	<table class="listTable" id="list" width="700">
	<tr>
      <td width="100" class="label" rowspan=2>
        选择条件
      </td>
      <td width="500"  >
        <eoms:dict key="dict-workplan" dictId="yearFlag" isQuery="false" defaultId="<%=_yearFlag%>"  onchange="changeYear()" selectId="yearFlag" beanId="selectXML" />
        年<eoms:dict key="dict-workplan" dictId="monthFlag" isQuery="false" defaultId="<%=_monthFlag%>"  onchange="changeYear()" selectId="monthFlag" beanId="selectXML" />
        月
       
      </td>
    </tr>
    <tr>
    <td>
     <div id="user-list" class="viewer-list"></div><input type="button" value="选择部门" id="userTreeBtn" class="btn"/>
    	
        <INPUT TYPE="hidden" name="deptId" id="deptId" value="<%=_deptId%>">
       
    </td>
    </tr>
    </table>
     <br>
    <input type="submit" value="查询" name="B1" class="submit">
    <br>
     <br>
  <table class="listTable" id="list" width="700">
    <caption><bean:message key="viewlist.title.formTitle" /></caption>
    
    <%
      if(monthPlanVOHash.size() != 0){
    %>
     
    <thead>
    <tr>
      <td width="200">
        <bean:message key="viewlist.title.formPlanName"/>
      </td>
      <td width="100">
        <bean:message key="viewlist.title.formConcernedNet"/>
      </td>
      <td width="100">
        <bean:message key="viewlist.title.formDeptName"/>
      </td>
      <td width="100">
        <bean:message key="viewlist.title.formExecuteMonth"/>
      </td>
      <td width="100">
        <bean:message key="viewlist.title.formExecuteUser"/>
      </td>
      <td width="50">
        <bean:message key="viewlist.title.formState"/>
      </td>
      <td width="50">
        <bean:message key="viewlist.title.formView"/>
      </td>
    </tr>
    </thead>
    <tbody>

    <%
        hashKeys = monthPlanVOHash.keys();
        for(int z=0;z<listKey.size();z++){
           tawwpMonthPlan = (TawwpMonthPlan)listKey.get(z);
           //if(tawwpYearPlan.getTypeIndex()==null){
            //tawwpYearPlan.setTypeIndex("");
          //}

          //monthPlanId = (String)(hashKeys.nextElement());
          //System.out
          //monthPlanId=(String)tawwpMonthPlan.getId();
          tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOHash.get(tawwpMonthPlan);
    %>

    <tr class="tr_show">
      <td width="200">
        <%=tawwpMonthPlanVO.getName()%>
      </td>
      <td width="100">
        <%=tawwpMonthPlanVO.getNetName()%>
      </td>
      <td width="100">
        <%=tawwpMonthPlanVO.getDeptName()%>
      </td>
      <td width="100">
        <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="viewlist.title.formYear"/>
        <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="viewlist.title.formMonth"/>
      </td>
      <td width="100">
        <%=tawwpMonthPlanVO.getPrincipal()%>
      </td>
      <td width="50">
        <%=tawwpMonthPlanVO.getExecuteStateName()%>
      </td>
      <td width="50">
        <a href="../tawwpexecute/viewall.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>">
          <bean:message key="viewlist.title.formView"/>
        </a>
      </td>
    </tr>

    <%
        }
      }else{
    %>
    <tr>
      <td height="25" colspan="7">
        <bean:message key="viewlist.title.nolist"/>
      </td>
    </tr>
    <%
      }
    %>

  </tbody>
  </table>

</form>

<!--  body end  -->

<%@ include file="/common/footer_eoms.jsp"%>

