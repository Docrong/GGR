<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
var frmReg;
Ext.onReady(function(){
	 frmReg = new eoms.form.Validation({form:'newFormPage'});
});

var frmReg1;
Ext.onReady(function(){
	 frmReg1 = new eoms.form.Validation({form:'newFormPage1'});
});
function returnlist(){
	var sheetKey = window.parent.document.getElementById("sheetKey").value;
	var frame1 = window.parent.document.getElementById("frame24");
	frame1.style.display="block";
	frame1.src = "${app}/sheet/numberapply/numberapply.do?method=performManual&sheetKey="+sheetKey+"&actionForword=commond24"; 
}

</script>
<logic:notEmpty name="TawBureaudataHlr">
		HLR24位信令点
		<form name="newFormPage" method="POST" id="newFormPage" action="numberapply.do?method=showQuicQuery">
			<table class="listTable taskList">
				<input type="hidden"   name="actionForword" id="actionForword"   value="commond24"/>
				<input type="hidden"   name="modelname" id="modelname"   value="TawBureaudataHlr"/>
				<tr>
					<td class="label">24位信令点</td>
					<td >	
						<input type="text"   name="hlrsignalid" id="hlrsignalid"   value=""/>
					</td>
					<td class="label">HLR名称</td>
					<td >
						<input type="text"   name="hlrname" id="hlrname"   value=""/>
					</td>
					<td class="label">归属地市区号</td>
					<td >
						<input type="text"   name="belongcityid" id="belongcityid"   value=""/>
					</td>
					<td  >
						<input type="submit"   class="submit"  value="查询"/>
					</td>
					<td  >
						<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
					</td>
				</tr>
			</table>
		</form>
		<display:table name="TawBureaudataHlr" cellspacing="0" cellpadding="0"
			id="TawBureaudataHlr" pagesize="${pageSize}" class="listTable taskList"
			export="false"  sort="list" size="TawBureaudataHlrsize" partialList="true" requestURI="numberapply.do" >
   		
			<display:column headerClass="sortable" title="" >
				<input type="radio" name="radio24" id="radio24" value="${TawBureaudataHlr.id }" />
				<input type="hidden" name="radio24Value" id="radio24Value" value="${TawBureaudataHlr.hlrsignalid }" />
			</display:column>
			<display:column property="hlrsignalid" sortable="true" headerClass="sortable" title="24位信令点" />
			<display:column property="hlrname" sortable="true" headerClass="sortable" title="HLR名称" />
			<display:column property="belongcityid" sortable="true" headerClass="sortable" title="归属地市区号" />
			<display:column property="hlrid" sortable="true" headerClass="sortable" title="HLRID" />
		</display:table>
</logic:notEmpty>
	
<logic:notEmpty name="TawpartMscid">
		MSC24位信令点
		<form name="newFormPage1" method="POST" id="newFormPage1" action="numberapply.do?method=showQuicQuery">
			<table class="listTable taskList">
			<input type="hidden"   name="actionForword" id="actionForword"   value="commond24"/>
			<input type="hidden"   name="modelname" id="modelname"   value="TawpartMscid"/>
			<tr>
				<td class="label">24位信令点</td>
				<td >	
					<input type="text"   name="signalname" id="signalname"   value=""/>
				</td>
				<td class="label">分配地市名称</td>
				<td >
					<input type="text"   name="areaName" id="areaName"   value=""/>
				</td>
				<td class="label">创建人</td>
				<td >
					<input type="text"   name="creator" id="creator"   value=""/>
				</td>
				<td class="label">分配人</td>
				<td >
					<input type="text"   name="assigner" id="assigner"   value=""/>
				</td>
				<td>
					<input type="submit"   class="submit"  value="查询"/>
				</td>
				<td>
					<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
				</td>
			</tr>
			</table>
		</form>
		<display:table name="TawpartMscid" cellspacing="0" cellpadding="0"
			id="TawpartMscid" pagesize="${pageSize}" class="listTable taskList"
			export="false"  sort="list" size="TawpartMscidsize" partialList="true" requestURI="numberapply.do">
			<display:column headerClass="sortable" title="" >
				<input type="radio" name="radio24" id="radio24" value="${TawpartMscid.id }"  />
				<input type="hidden" name="radio24Value" id="radio24Value" value="${TawpartMscid.signalname }"  />
			</display:column>
			<display:column property="signalname" sortable="true" headerClass="sortable" title="24位信令点" />
			<display:column property="areaName" sortable="true" headerClass="sortable" title="分配地市名称" />
			<display:column property="creator" sortable="true" headerClass="sortable" title="创建人" />
			<display:column property="createTime" sortable="true" headerClass="sortable" title="创建时间" />
			<display:column property="assigner" sortable="true" headerClass="sortable" title="分配人" />
			<display:column property="assignTime" sortable="true" headerClass="sortable" title="分配时间" />
		</display:table>
</logic:notEmpty>

	<c:if test="${TawBureaudataHlrsize==0 }">
		HLR24位信令点，没有相关记录！
		<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
	</c:if>
	<c:if test="${TawpartMscidsize==0 }">
		MSC24位信令点，没有相关记录！
		<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
	</c:if>
	

	
<%@ include file="/common/footer_eoms.jsp"%>
