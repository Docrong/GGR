<div id="formPanel">
	<div id="formPanel-body" class="app-panel">
	  <div id="curItemInfo"></div>
	  <table width="%95">  	
		<tr>
	
			<td colspan="3">
				<span class="textHeader">${eoms:a2u("继承自所属上级的菜单方案(不可删除):")}</span><br/>
				<select id="extendAsg"></select>
				<br/><br/>
			</td>
		</tr>
	  	<tr>
	  		<td class="textHeader">${eoms:a2u("可分配的菜单方案")}</td>
			<td></td>
	  		<td class="textHeader">${eoms:a2u("已分配的菜单方案")}</td>
	  	</tr>
	  	<tr>
	  		<td valign="top">
	  			<select id="privSource" size="10" class="menuBox">
		        <c:forEach items="${tawSystemPrivMenuList}" var="list">
		          <option value="${list.privid}">${list.name}</option>
		        </c:forEach>
		      </select>
			</td>
			<td>
			  <input type="button" value="${eoms:a2u('添加')}&gt;&gt;" 
			  	onclick="javascript:PrivAssigner.addPriv();"/>
			  
			  <input type="button" value="&lt;&lt;${eoms:a2u('删除')}" 
			  	onclick="javascript:PrivAssigner.delPriv();"/>
			</td>
	  		<td valign="top">
			  <select id="privAsgd" size="10" class="menuBox">
		      </select>
	  		</td>
	  	</tr>
	  </table>
		
	</div>
</div>