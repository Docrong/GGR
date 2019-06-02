<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>



<form method="post" id="theform" action="bureaudataCityzone.do?method=edit">
<table class="formTable">
			<caption>地市信息</caption>

		   
           	<!-- T1受理角色 -->  
			<tr>
	            <td class="label"> 
	              	地市名称*
	            </td>
	            <td  class="content">
	               <input type="text" name="cityname" id=cityname ${type == 'open' ? 'disabled="disabled"': ''} value="${TawBureaudataCityzone.cityname}"/>
	            </td> 
            </tr>   
             	<!-- T1受理对象 -->  
			<tr>
	            <td class="label"> 
	              	区号* 
	            </td>
	            <td  class="content">
	               <input type="text" name="zonenum" id=zonenum ${type == 'open' ? 'disabled="disabled"': ''} value="${TawBureaudataCityzone.zonenum}"/>
	            </td> 
            </tr>
            
             <tr > 
	            <td class="label">
	              	部门 *
	            </td>
	            <td class="content">
	            <logic:notEqual name="type" value="open">
		            <c:set var="id">deptid</c:set>
					<eoms:chooser id="test2"  
						category="[{id:'${id}',text:'派发',allowBlank:false,vtext:'请选择派发人2'}]" 
						data="[
							{id:'${TawBureaudataCityzone.deptid}',nodeType:'dept',categoryId:'${id}'}
						]"
					/>
				</logic:notEqual>
				<logic:equal name="type" value="open">
					<eoms:id2nameDB id="${TawBureaudataCityzone.deptid}" beanId="tawSystemDeptDao"/>
				</logic:equal>
	            </td>
            </tr> 
		   	<tr>
			  <td class="label">负责人员ID号 *：</td>
			  <td>
	            <input type="text" name="userid" id=userid ${type == 'open' ? 'disabled="disabled"': ''} value="${TawBureaudataCityzone.userid}"/> 
			  </td>					    
		   	</tr>
		<logic:notEqual name="type" value="open">
			<tr>
				<td colspan="2">
				 <input type="hidden" name="cityid" id="cityid" value="${TawBureaudataCityzone.cityid}"/> 
				 <input type="hidden" name="usersysid" id="usersysid" value="${TawBureaudataCityzone.usersysid}"/> 
					<input type="submit" value="提交" class="btn" >
				</td>
			</tr>
		</logic:notEqual>
		<logic:equal name="type" value="open">
			<tr>
				<td colspan="2">
					<input type="button" value="编辑" class="btn" onclick="toedit();">
				</td>
			</tr>
		</logic:equal>
        
</table>
</form>
 <script type="text/javascript">
 	function toedit(){
		location.href = "./bureaudataCityzone.do?method=findByid&type=edit&cityid=${TawBureaudataCityzone.cityid}";
	}
 </script>