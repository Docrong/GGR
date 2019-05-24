<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<style type="text/css">
a {
	text-decoration: none;
	color: #6b696b;
}
a:hover {
	text-decoration: underline;
	color: #6b696b;
}
.trClass {
	height: 30px;
}
</style>

<script language='javascript'>
	function period(){
	    var startDate = document.getElementById("startDate").value;
	    var endDate = document.getElementById("endDate").value;
	    var userId = document.getElementById("userId").value;
	    var deptId = document.getElementById("deptId").value;
	    if(startDate == ""){
	        alert("请选择开始时间！");
	        return false;	    
	    }	    
	    if(startDate > endDate){
	        alert("开始时间要小于结束时间！");
	        return false;
	    }
	    location.href='${app}/kmmanager/kmContentsApplys.do?method=searchRank&startDate='+startDate+'&endDate='+endDate+'&userId='+userId+'&deptId='+deptId;
	}
	Ext.onReady(function() {
		var userConfig = {
			btnId:'userName',
			treeDataUrl:'${app}/xtree.do?method=userFromDept',
			treeRootId:'-1',
			treeRootText:'人员选择树',
			treeChkMode:'single',
			treeChkType:'user',
			showChkFldId:'userName',
			saveChkFldId:'userId'
		};
		
		var deptConfig = {
			btnId:'deptName',
			treeDataUrl:'${app}/xtree.do?method=dept',
			treeRootId:'-1',
			treeRootText:'部门选择树',
			treeChkMode:'single',
			treeChkType:'dept',
			showChkFldId:'deptName',
			saveChkFldId:'deptId'
		};
		userTree = new xbox(userConfig);
		deptTree = new xbox(deptConfig);
});
</script>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <content tag="heading">
	    <b><fmt:message key="contentsApplyRank.show" /></b>
    </content>

	<br><br>

	<tr align = 'center'>	
        <td>
            统计时间：
            从&nbsp;&nbsp;<input type="text" name="startDate" id="startDate" value="${startDate}" size="10" onclick="popUpCalendar(this,this,null,null,null,false,-1);" readonly="true" class="text" alt="allowBlank:false,vtext:'请选择开始时间...'" />&nbsp;&nbsp;
            到&nbsp;&nbsp;<input type="text" name="endDate"   id="endDate"   value="${endDate}"   size="10" onclick="popUpCalendar(this,this,null,null,null,false,-1);" readonly="true" class="text" alt="allowBlank:false,vtext:'请选择结束时间...'" />&nbsp;&nbsp;
          <a style="CURSOR:hand" onclick="period()">统计</a>&nbsp;&nbsp;
          
          <input type="hidden" id="userName" name="userId" />
		  <input type="hidden" id="deptName" name="deptId" />
		  <input type="hidden" id="userId" name="userId" />
		  <input type="hidden" id="deptId" name="deptId" />
	    </td>		
	</tr>

	<div style="width: 240px; margin-top: 15px; float: left; border: 1px #9cc3f7 solid; height: expression(document.body.clientHeight-125 + 'px');">
	    <div style="background-color: #ceebef; width: 240px; height: 25px; padding-top: 5px; padding-left: 5px;">
			<fmt:message key="contentsApplyRank.show" />
			<!-- span style="background-color: #e7f3ff; color: #6396c6; margin-left: 25px; cursor: pointer; border: 1px #b7bbbf solid;" onclick="javascript:onSubmitShowAll();">SHOW ALL</span -->
		</div>
		
		<div style="padding-top: 3px; padding-left: 5px; width: 200px; height: 15px;">
			显示排行前&nbsp;
			<input id="showSize" class="text" style="width: 35px; height: 20px;" />&nbsp;位&nbsp;&nbsp;
			<input type="button" class="btn" value="GO" onclick="javascript:onSubmitShowTop();" />
		</div>
		
		<div style="margin-top: 5px; border-top: 1px #9cc3f7 solid; overflow-y: scroll; height: expression(document.body.clientHeight-179 + 'px');">
			<table style="width: 223px;">
				<logic:iterate id="kmContentsApplyList" name="kmContentsApplyList" indexId="indexid">
					<tr class="trClass">
						<td style="width: 20px; padding-left: 5px;">
							<div style="background-color: #e7e7ef; width: 14px; height: 12px; border: 1px #adbece solid; text-align: center;">
								${indexid + 1}
							</div>
						</td>
						<td>
						    <a href="javascript:var id = '${kmContentsApplyList.id}';
						                        var url='${app}/kmmanager/kmContentsApplys.do?method=rankDetail';
						                        url = url + '&applyTheme=' + id+'&startDate=${startDate }&endDate=${endDate }' ;
						                        location.href=url"
						       title="<eoms:id2nameDB id="${kmContentsApplyList.id}" beanId="kmTableThemeDao" />"
						       target="detail">
						        <script type="text/javascript">
		                     	    var title = "${kmContentsApplyList.applyTitle }";
		                       	    if(title.length>13)
		                        	    title = title.substring(0,11)+"...";
		                       	    document.write(title);
		                        </script>
		                    </a>
						</td>
						<td>
							(${kmContentsApplyList.applyCount })
						</td>
					</tr>
				</logic:iterate>
			</table>
		</div>
	</div>

	<div style="margin: 0px; margin-top: 15px; width: 700px;">
		<iframe name="detail" width=700 height=540 frameborder="0"></iframe>
	</div>
	
	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<script type="text/javascript">
function onSubmitShowTop(){
	var maxSize = document.getElementById("showSize").value;
	if(maxSize=="") return;
    var url='${app}/kmmanager/kmContentsApplys.do?method=searchRank&maxSize=' + maxSize+"&startDate=${startDate}&endDate=${endDate}";
    window.location.href(url);
}
function onSubmitShowAll(){
    var url='${app}/kmmanager/kmContentsApplys.do?method=searchRank&maxSize=0';
    window.location.href(url);
}
</script>
<%@ include file="/common/footer_eoms.jsp"%>