<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","1860");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/industrysms/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "industrysms.do?method=newShowLimit&flowName=IndustrySms",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("sheetAcceptLimit").value = "";
        	   // $('sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
   
   var v2 = eoms.form;
	function dispre(input){
		if(input != null &&input=='101250101'){
			v2.enableArea('new');
			v2.disableArea('edit',true);
			v2.disableArea('del',true);  
		}else 
		if(input != null &&input=='101250102'){
			v2.enableArea('edit');
			v2.disableArea('new',true);
			v2.disableArea('del',true);
		}else 
		if(input != null &&input=='101250103'){
			v2.enableArea('del');
			v2.disableArea('new',true);
			v2.disableArea('edit',true);
		}else {
			v2.disableArea('new',true);
			v2.disableArea('edit',true);
			v2.disableArea('del',true);
		}
	}
	var spareOne = document.getElementById("spareOne");
	dispre(spareOne.value);
   </script>
	<input type="hidden" name="processTemplateName" value="IndustrySms" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="AuditHumTask" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iIndustrySmsMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.industrysms.model.IndustrySmsMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.industrysms.model.IndustrySmsLink"/>
    <input type="hidden" value="" id="cilentServerIpAddrs" name="cilentServerIpAddrs" />
    <input type="hidden" value="" id="cilentServerIpAddrsNew" name="cilentServerIpAddrsNew" />
    <br>

    <!-- 工单基本信息 --> 
<table class="formTable" >
                 <tr>
					   <td class="label">受理时限*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
							id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
							onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>   
					  </td>					  
					  <td class="label">回复时限*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
							   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
							   onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>   
					  </td>
			    </tr>
 		<tr><td class="label">
				<!-- spareOne -->
				工单任务性质*
			</td>
			<td>
				<eoms:comboBox name="${sheetPageName}spareOne" id="${sheetPageName}spareOne" defaultValue="${sheetMain.spareOne}"
					onchange="dispre(this.value);" initDicId="1012501"  alt="allowBlank:false" />
			</td> 
		</tr>
		
</table>

<table style="border: 0px" width="100%">
	<tr id="new">
	<td colspan="4">
		<table class="formTable" >		
				<tr><td class="label">
						<!-- ADC/MAS地域 -->
						<bean:message bundle="industrysms" key="industrySmsMain.regional"/>*
					</td>
					<td>
						<eoms:comboBox name="regional" id="regional" 
								 initDicId="1012502"  
								defaultValue="${sheetMain.regional}" alt="allowBlank:false"/>
					</td>
		 <td class="label">
						<!-- EC/SI类型 -->
						<bean:message bundle="industrysms" key="industrySmsMain.ecsiType"/>*
					</td>
					<td>
						<eoms:comboBox name="ecsiType" id="ecsiType" 
								 initDicId="1012503"  
								defaultValue="101250301"  alt="allowBlank:false"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- EC/SI简称 -->
						<bean:message bundle="industrysms" key="industrySmsMain.abbreviation"/>*
					</td>
					<td>
						<input type="text"  class="text" name="abbreviation" id="abbreviation"  alt="allowBlank:false,maxLength:28,vtext:'请填入 EC/SI简称 信息，最多输入 14 字符'" value="${sheetMain.abbreviation}"/></br>
						<span style="color:red">总长不超过14个汉字，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 企业代码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.enterpriseCode"/>*
					</td>
					<td>
						<input type="text"  class="text" name="enterpriseCode" id="enterpriseCode" alt="vtype:'number',allowBlank:false,maxLength:64,vtext:'请填入 企业代码 信息，最多输入 64 数字'" value="${sheetMain.enterpriseCode}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- EC/SI服务代码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.serviceCode"/>*
					</td>
					<td>
						<input type="text"  class="text" name="serviceCode" id="serviceCode" alt="vtype:'number',allowBlank:false,maxLength:64,vtext:'请填入 EC/SI服务代码 信息，最多输入 64 数字'" value="${sheetMain.serviceCode}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 客户服务器IP -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddr"/>*
					</td>
					<td>
						<input type="text"  class="text" name="cilentServerIpAddr" id="cilentServerIpAddr"  alt="allowBlank:false,maxLength:16,vtext:'请填入 客户服务器IP 信息，最多输入 16 字符'" value="${sheetMain.cilentServerIpAddr}"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 是否为多IP -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrs"/>*
					</td>
					<td>
						<input type="text"  class="text" name="proportion" alt="allowBlank:false,maxLength:255,vtext:'请填入 是否为多IP 信息，最多输入 255 字符'"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
                    <td>     
                        <input   name="Submit1"   type="button"   onclick="javascript:add1()"   value="增加">       
                    </td>  
				</tr>         
                <tr><td class="label">
                		</td>    
                        <td>           
                        <div   id="upid1"></div>       
                        </td>           
                </tr>  
				
		 		<tr><td class="label">
						<!-- 登录网关用户名 -->
						<bean:message bundle="industrysms" key="industrySmsMain.user"/>*
					</td>
					<td>
						<input type="text"  class="text" name="userValue" id="userValue"  alt="allowBlank:false,maxLength:40,vtext:'请填入 登录网关用户名 信息，最多输入 40 字符'" value="${sheetMain.userValue}"/></br>
						<span style="color:red">数字格式，不得有空格或回车，必须与企业代码相同</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 登录网关密码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.password"/>*
					</td>
					<td>
						<input type="text"  class="text" name="passwordValue" id="passwordValue"  alt="allowBlank:false,maxLength:40,vtext:'请填入 登录网关密码 信息，最多输入 40 字符'" value="${sheetMain.passwordValue}"/></br>
						<span style="color:red">长度6-10位，不得有空格或回车，必须包含大写、小写和数字，不可包含特殊字符</span>
					</td>
		 <td class="label">
						<!-- 最大连接数 -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxConnections"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxConnections" id="maxConnections"  alt="vtype:'number',allowBlank:false,maxLength:10,vtext:'请填入 最大连接数 信息，最多输入 10 数字'" value="${sheetMain.maxConnections}"/></br>
						<span style="color:red">数字格式，不得有空格或回车，不得大于15</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 最大下发流量 -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxInBytes"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxInBytes" id="maxInBytes"  alt="vtype:'number',allowBlank:false,maxLength:10,vtext:'请填入 最大下发流量 信息，最多输入 10 数字'" value="${sheetMain.maxInBytes}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 最大上行流量 -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxOutBytes"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxOutBytes" id="maxOutBytes"  alt="vtype:'number',allowBlank:false,maxLength:10,vtext:'请填入 最大上行流量 信息，最多输入 10 数字'" value="${sheetMain.maxOutBytes}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- EC/SI使用协议 -->
						<bean:message bundle="industrysms" key="industrySmsMain.protocol"/>*
					</td>
					<td>
						<eoms:comboBox name="protocol" id="protocol" 
								 initDicId="1012504"  
								defaultValue="${sheetMain.protocol}" alt="allowBlank:false"/>
					</td>
		 <td class="label">
						<!-- 是否M模块鉴权 -->
						<bean:message bundle="industrysms" key="industrySmsMain.isAuthentication"/>*
					</td>
					<td>
						<eoms:comboBox name="isAuthentication" id="isAuthentication" 
								 initDicId="1012505"  
								defaultValue="${sheetMain.isAuthentication}" alt="allowBlank:false"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 业务联系人 -->
						<bean:message bundle="industrysms" key="industrySmsMain.seviceContact"/>*
					</td>
					<td>
						<input type="text"  class="text" name="seviceContact" id="seviceContact"  alt="allowBlank:false,maxLength:32,vtext:'请填入 业务联系人 信息，最多输入 32 字符'" value="${sheetMain.seviceContact}"/>
					</td>
		 <td class="label">
						<!-- 业务联系电话 -->
						<bean:message bundle="industrysms" key="industrySmsMain.sevicePhone"/>*
					</td>
					<td>
						<input type="text"  class="text" name="sevicePhone" id="sevicePhone"  alt="allowBlank:false,maxLength:32,vtext:'请填入 业务联系电话 信息，最多输入 32 字符'" value="${sheetMain.sevicePhone}"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 客户联系人 -->
						<bean:message bundle="industrysms" key="industrySmsMain.customerContact"/>*
					</td>
					<td>
						<input type="text"  class="text" name="customerContact" id="customerContact"  alt="allowBlank:false,maxLength:32,vtext:'请填入 客户联系人 信息，最多输入 32 字符'" value="${sheetMain.customerContact}"/>
					</td>
		 <td class="label">
						<!-- 客户联系电话 -->
						<bean:message bundle="industrysms" key="industrySmsMain.customerPhone"/>*
					</td>
					<td>
						<input type="text"  class="text" name="customerPhone" id="customerPhone"  alt="allowBlank:false,maxLength:32,vtext:'请填入 客户联系电话 信息，最多输入 32 字符'" value="${sheetMain.customerPhone}"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 业务数据申请单位 -->
						<bean:message bundle="industrysms" key="industrySmsMain.unit"/>*
					</td>
					<td>
						<eoms:comboBox name="unit" id="unit" 
								 initDicId="1012030"  
								defaultValue="${sheetMain.unit}" alt="allowBlank:false"/>
					</td>
		 <td class="label">
						<!-- 业务数据据申请日期 -->
						<bean:message bundle="industrysms" key="industrySmsMain.applyDate"/>*
					</td>
					<td>					
						<input type="text" class="text" name="applyDate" readonly="readonly" 
								id="applyDate" value="${eoms:date2String(sheetMain.applyDate)}" 
								onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 硬件型号 -->
						<bean:message bundle="industrysms" key="industrySmsMain.hardwareModel"/>*
					</td>
					<td>
						<input type="text"  class="text" name="hardwareModel" id="hardwareModel"  alt="allowBlank:false,maxLength:32,vtext:'请填入 硬件型号 信息，最多输入 32 字符'" value="${sheetMain.hardwareModel}"/>
					</td>
		 <td class="label">
						<!-- 软件版本 -->
						<bean:message bundle="industrysms" key="industrySmsMain.softVersion"/>*
					</td>
					<td>
						<input type="text"  class="text" name="softVersion" id="softVersion"  alt="allowBlank:false,maxLength:32,vtext:'请填入 软件版本 信息，最多输入 32 字符'" value="${sheetMain.softVersion}"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 集成商 -->
						<bean:message bundle="industrysms" key="industrySmsMain.integrator"/>*
					</td>
					<td>
						<input type="text"  class="text" name="integrator" id="integrator"  alt="allowBlank:false,maxLength:128,vtext:'请填入 集成商 信息，最多输入 128 字符'" value="${sheetMain.integrator}"/>
					</td>
		 <td class="label">
						<!-- 是否为加急流程 -->
						<bean:message bundle="industrysms" key="industrySmsMain.isUrgent"/>*
					</td>
					<td>
						<eoms:comboBox name="isUrgent" id="isUrgent" 
								 initDicId="10301"  
								defaultValue="${sheetMain.isUrgent}" alt="allowBlank:false"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 集团批复工单号 -->
						<bean:message bundle="industrysms" key="industrySmsMain.groupNumber"/>*
					</td>
					<td>
						<input type="text"  class="text" name="groupNumber" id="groupNumber"  alt="maxLength:64,vtext:'请填入 集团批复工单号 信息，最多输入 64 字符'" value="${sheetMain.groupNumber}"/>
					</td>
		 <td class="label">
						<!-- MASID -->
						<bean:message bundle="industrysms" key="industrySmsMain.masId"/>*
					</td>
					<td>
						<input type="text"  class="text" name="masId" id="masId"  alt="allowBlank:false,maxLength:64,vtext:'请填入 MASID 信息，最多输入 64 字符'" value="${sheetMain.masId}"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 工单回复时限 -->
						<bean:message bundle="industrysms" key="industrySmsMain.timeLimit"/>*
					</td>
					<td>					
						<input type="text" class="text" name="timeLimit" readonly="readonly" 
								id="timeLimit" value="${eoms:date2String(sheetMain.timeLimit)}" 
								onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
					</td>
				</tr>
			</table>
		</td>
 	</tr>

	<tr id="edit">
	<td colspan="4"> 				
		<table class="formTable" >
		
				<tr><td class="label">
						<!-- ADC/MAS地域 -->
						<bean:message bundle="industrysms" key="industrySmsMain.regional"/>*
					</td>
					<td>
						<eoms:comboBox name="regional" id="regional" 
								 initDicId="1012502"  
								defaultValue="${sheetMain.regional}" alt="allowBlank:false"/>
					</td>
		 <td class="label">
						<!-- EC/SI类型 -->
						<bean:message bundle="industrysms" key="industrySmsMain.ecsiType"/>*
					</td>
					<td>
						<eoms:comboBox name="ecsiType" id="ecsiType" 
								 initDicId="1012503"  
								defaultValue="101250301" alt="allowBlank:false"/>
					</td>
				</tr>
				
				<tr><td class="label">
						<!-- EC/SI简称 -->
						<bean:message bundle="industrysms" key="industrySmsMain.abbreviation"/>*
					</td>
					<td>
						<input type="text"  class="text" name="abbreviation" id="abbreviation"  alt="maxLength:28,vtext:'请填入 EC/SI简称 信息，最多输入 14 字符'" value="${sheetMain.abbreviation}"/></br>
						<span style="color:red">总长不超过14个汉字，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 企业代码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.enterpriseCode"/>*
					</td>
					<td>
						<input type="text"  class="text" name="enterpriseCode" id="enterpriseCode"  alt="vtype:'number',allowBlank:false,maxLength:64,vtext:'请填入 企业代码 信息，最多输入 64 数字'" value="${sheetMain.enterpriseCode}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
				</tr>
				
				<tr><td class="label">
						<!-- EC/SI服务代码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.serviceCode"/>*
					</td>
					<td>
						<input type="text"  class="text" name="serviceCode" id="serviceCode"  alt="vtype:'number',allowBlank:false,maxLength:64,vtext:'请填入 EC/SI服务代码 信息，最多输入 64 数字'" value="${sheetMain.serviceCode}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 客户服务器IP -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddr"/>*
					</td>
					<td>
						<input type="text"  class="text" name="cilentServerIpAddr" id="cilentServerIpAddr"  alt="allowBlank:false,maxLength:16,vtext:'请填入 客户服务器IP 信息，最多输入 16 字符'" value="${sheetMain.cilentServerIpAddr}"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
				</tr>

				<tr><td class="label">
						<!-- 是否为多IP -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrs"/>*
					</td>
					<td>
						<input type="text"  class="text" name="proportion" alt="allowBlank:false,maxLength:255,vtext:'请填入 是否为多IP 信息，最多输入 255 字符'"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
                    <td>     
                        <input   name="Submit2"   type="button"   onclick="javascript:add2()"   value="增加">       
                    </td> 
                </tr>

                <tr><td class="label">
                		</td>    
                        <td>           
                        <div   id="upid2"></div>       
                        </td>           
                </tr>  
                    
		 		<tr><td class="label">
						<!-- 登录网关用户名 -->
						<bean:message bundle="industrysms" key="industrySmsMain.user"/>*
					</td>
					<td>
						<input type="text"  class="text" name="userValue" id="userValue"  alt="vtype:'number',allowBlank:false,maxLength:40,vtext:'请填入 登录网关用户名 信息，最多输入 40 数字'" value="${sheetMain.userValue}"/></br>
						<span style="color:red">数字格式，不得有空格或回车，必须与企业代码相同</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 登录网关密码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.password"/>*
					</td>
					<td>
						<input type="text"  class="text" name="passwordValue" id="passwordValue"  alt="maxLength:40,vtext:'请填入 登录网关密码 信息，最多输入 40 字符'" value="${sheetMain.passwordValue}"/></br>
						<span style="color:red">长度6-10位，不得有空格或回车，必须包含大写、小写和数字，不可包含特殊字符</span>
					</td>
		 <td class="label">
						<!-- 最大连接数 -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxConnections"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxConnections" id="maxConnections"  alt="vtype:'number',maxLength:10,vtext:'请填入 最大连接数 信息，最多输入 10 数字'" value="${sheetMain.maxConnections}"/></br>
						<span style="color:red">数字格式，不得有空格或回车，不得大于15</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 最大下发流量 -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxInBytes"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxInBytes" id="maxInBytes"  alt="vtype:'number',maxLength:10,vtext:'请填入 最大下发流量 信息，最多输入 10 数字'" value="${sheetMain.maxInBytes}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 最大上行流量 -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxOutBytes"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxOutBytes" id="maxOutBytes"  alt="vtype:'number',maxLength:10,vtext:'请填入 最大上行流量 信息，最多输入 10 数字'" value="${sheetMain.maxOutBytes}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
				</tr>
								
				<tr><td class="label">
						<!-- EC/SI使用协议 -->
						<bean:message bundle="industrysms" key="industrySmsMain.protocol"/>*
					</td>
					<td>
						<eoms:comboBox name="protocol" id="protocol" 
								 initDicId="1012504"  
								defaultValue="${sheetMain.protocol}" alt="allowBlank:false"/>
					</td>
		<td class="label">
						<!-- 业务数据申请单位 -->
						<bean:message bundle="industrysms" key="industrySmsMain.unit"/>*
					</td>
					<td>
						<eoms:comboBox name="unit" id="unit" 
								 initDicId="1012030"  
								defaultValue="${sheetMain.unit}" alt="allowBlank:false"/>
					</td>
				</tr>
				
				<tr><td class="label">
						<!-- 是否M模块鉴权 -->
						<bean:message bundle="industrysms" key="industrySmsMain.isAuthentication"/>*
					</td>
					<td>
						<eoms:comboBox name="isAuthentication" id="isAuthentication" 
								 initDicId="1012505"  
								defaultValue="${sheetMain.isAuthentication}" alt="allowBlank:false"/>
					</td>
		 <td class="label">
						<!-- 是否为加急流程 -->
						<bean:message bundle="industrysms" key="industrySmsMain.isUrgent"/>*
					</td>
					<td>
						<input type="text"  class="text" name="isUrgentValue" id="isUrgentValue" readonly="readonly" value="否"/>
						<input type="hidden" name="isUrgent" id="isUrgent" value="1030102" />
					</td>
				</tr>
				
				<tr><td class="label">
						<!-- 集团批复工单号 -->
						<bean:message bundle="industrysms" key="industrySmsMain.groupNumber"/>*
					</td>
					<td>
						<input type="text"  class="text" name="groupNumber" id="groupNumber"  alt="maxLength:64,vtext:'请填入 集团批复工单号 信息，最多输入 64 字符'" value="${sheetMain.groupNumber}"/>
					</td>
		<td class="label">
						<!-- 工单回复时限 -->
						<bean:message bundle="industrysms" key="industrySmsMain.timeLimit"/>*
					</td>
					<td>					
						<input type="text" class="text" name="timeLimit" readonly="readonly" 
								id="timeLimit" value="${eoms:date2String(sheetMain.timeLimit)}" 
								onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
					</td>
				</tr>
				
		 		<tr><td class="label">
						<!-- EC/SI简称(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.abbreviationNew"/>*
					</td>
					<td>
						<input type="text"  class="text" name="abbreviationNew" id="abbreviationNew"  alt="maxLength:28,vtext:'请填入 EC/SI简称(新) 信息，最多输入 14 字符'" value="${sheetMain.abbreviationNew}"/></br>
						<span style="color:red">总长不超过14个汉字，不得有空格或回车</span>
					</td>
		<td class="label">
						<!-- EC/SI使用协议(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.protocolNew"/>*
					</td>
					<td>
						<eoms:comboBox name="protocolNew" id="protocolNew" 
								 initDicId="1012504"  
								defaultValue="${sheetMain.protocolNew}"/>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 客户服务器IP(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrNew"/>*
					</td>
					<td>
						<input type="text"  class="text" name="cilentServerIpAddrNew" id="cilentServerIpAddrNew"  alt="maxLength:16,vtext:'请填入 客户服务器IP(新) 信息，最多输入 16 字符'" value="${sheetMain.cilentServerIpAddrNew}"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
				</tr>
				
		 		<tr><td class="label">
						<!-- 是否为多IP(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrsNew"/>*
					</td>
					<td>
						<input type="text"  class="text" name="proportionNew" alt="maxLength:255,vtext:'请填入 是否为多IP 信息，最多输入 255 字符'"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
                    <td>     
                        <input   name="Submit3"   type="button"   onclick="javascript:add3()"   value="增加">       
                    </td> 
				</tr>
				
                <tr><td class="label">
                		</td>    
                        <td>           
                        <div   id="upid3"></div>       
                        </td>           
                </tr>  
		 
				<tr><td class="label">
						<!-- 登录网关密码(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.passwordNew"/>*
					</td>
					<td>
						<input type="text"  class="text" name="passwordNew" id="passwordNew"  alt="maxLength:40,vtext:'请填入 登录网关密码(新) 信息，最多输入 40 字符'" value="${sheetMain.passwordNew}"/></br>
						<span style="color:red">长度6-10位，不得有空格或回车，必须包含大写、小写和数字，不可包含特殊字符</span>
					</td>
		 <td class="label">
						<!-- 最大连接数(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxConnectionsNew"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxConnectionsNew" id="maxConnectionsNew"  alt="vtype:'number',maxLength:10,vtext:'请填入 最大连接数(新) 信息，最多输入 10 数字'" value="${sheetMain.maxConnectionsNew}"/></br>
						<span style="color:red">数字格式，不得有空格或回车，不得大于15</span>
					</td>
				</tr>
		 
				<tr><td class="label">
						<!-- 最大下发流量(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxInBytesNew"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxInBytesNew" id="maxInBytesNew"  alt="vtype:'number',maxLength:10,vtext:'请填入 最大下发流量(新) 信息，最多输入 10 数字'" value="${sheetMain.maxInBytesNew}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 最大上行流量(新) -->
						<bean:message bundle="industrysms" key="industrySmsMain.maxOutBytesNew"/>*
					</td>
					<td>
						<input type="text"  class="text" name="maxOutBytesNew" id="maxOutBytesNew"  alt="vtype:'number',maxLength:10,vtext:'请填入 最大上行流量(新) 信息，最多输入 10 数字'" value="${sheetMain.maxOutBytesNew}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
				</tr>
		 
		</table>
		</td>
 	</tr>
 	
 	<tr id="del">
	<td colspan="4"> 				
		<table class="formTable" >
				<tr><td class="label">
						<!-- ADC/MAS地域 -->
						<bean:message bundle="industrysms" key="industrySmsMain.regional"/>*
					</td>
					<td>
						<eoms:comboBox name="regional" id="regional" 
								 initDicId="1012502"  
								defaultValue="${sheetMain.regional}" alt="allowBlank:false"/>
					</td>
		 <td class="label">
						<!-- EC/SI类型 -->
						<bean:message bundle="industrysms" key="industrySmsMain.ecsiType"/>*
					</td>
					<td>
						<eoms:comboBox name="ecsiType" id="ecsiType" 
								 initDicId="1012503"  
								defaultValue="101250301" alt="allowBlank:false"/>
					</td>
				</tr>
				<tr><td class="label">
						<!-- EC/SI简称 -->
						<bean:message bundle="industrysms" key="industrySmsMain.abbreviation"/>*
					</td>
					<td>
						<input type="text"  class="text" name="abbreviation" id="abbreviation"  alt="allowBlank:false,maxLength:28,vtext:'请填入 EC/SI简称 信息，最多输入 14 字符'" value="${sheetMain.abbreviation}"/></br>
						<span style="color:red">总长不超过14个汉字，不得有空格或回车</span>
					</td>
		 <td class="label">
						<!-- 企业代码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.enterpriseCode"/>*
					</td>
					<td>
						<input type="text"  class="text" name="enterpriseCode" id="enterpriseCode"  alt="vtype:'number',allowBlank:false,maxLength:64,vtext:'请填入 企业代码 信息，最多输入 64 数字'" value="${sheetMain.enterpriseCode}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
				</tr>
				<tr><td class="label">
						<!-- EC/SI服务代码 -->
						<bean:message bundle="industrysms" key="industrySmsMain.serviceCode"/>*
					</td>
					<td>
						<input type="text"  class="text" name="serviceCode" id="serviceCode"  alt="vtype:'number',allowBlank:false,maxLength:64,vtext:'请填入 EC/SI服务代码 信息，最多输入 64 数字'" value="${sheetMain.serviceCode}"/></br>
						<span style="color:red">数字格式，不得有空格或回车</span>
					</td>
		<td class="label">
						<!-- EC/SI使用协议 -->
						<bean:message bundle="industrysms" key="industrySmsMain.protocol"/>*
					</td>
					<td>
						<eoms:comboBox name="protocol" id="protocol" 
								 initDicId="1012504"  
								defaultValue="${sheetMain.protocol}" alt="allowBlank:false"/>
					</td>
				</tr>
				<tr><td class="label">
						<!-- 是否为加急流程 -->
						<bean:message bundle="industrysms" key="industrySmsMain.isUrgent"/>*
					</td>
					<td>
						<input type="text"  class="text" name="isUrgentValue" id="isUrgentValue" readonly="readonly" value="否"/>
						<input type="hidden" name="isUrgent" id="isUrgent" value="1030102" />
					</td>
		<td class="label">
						<!-- 业务数据申请单位 -->
						<bean:message bundle="industrysms" key="industrySmsMain.unit"/>*
					</td>
					<td>
						<eoms:comboBox name="unit" id="unit" 
								 initDicId="1012030"  
								defaultValue="${sheetMain.unit}" alt="allowBlank:false"/>
					</td>
				</tr>
				<tr><td class="label">
						<!-- 集团批复工单号 -->
						<bean:message bundle="industrysms" key="industrySmsMain.groupNumber"/>*
					</td>
					<td>
						<input type="text"  class="text" name="groupNumber" id="groupNumber"  alt="maxLength:64,vtext:'请填入 集团批复工单号 信息，最多输入 64 字符'" value="${sheetMain.groupNumber}"/>
					</td>
		<td class="label">
						<!-- 工单回复时限 -->
						<bean:message bundle="industrysms" key="industrySmsMain.timeLimit"/>*
					</td>
					<td>					
						<input type="text" class="text" name="timeLimit" readonly="readonly" 
								id="timeLimit" value="${eoms:date2String(sheetMain.timeLimit)}" 
								onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
					</td>
				</tr>
				<tr><td class="label">
						<!-- 客户服务器IP -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddr"/>*
					</td>
					<td>
						<input type="text"  class="text" name="cilentServerIpAddr" id="cilentServerIpAddr"  alt="allowBlank:false,maxLength:16,vtext:'请填入 客户服务器IP 信息，最多输入 16 字符'" value="${sheetMain.cilentServerIpAddr}"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
				</tr>
				
				<tr><td class="label">
						<!-- 是否为多IP -->
						<bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrs"/>*
					</td>
					<td>
						<input type="text"  class="text" name="proportion" alt="allowBlank:false,maxLength:255,vtext:'请填入 是否为多IP 信息，最多输入 255 字符'"/></br>
						<span style="color:red">不得带空格、回车或汉字描述</span>
					</td>
                    <td>     
                        <input   name="Submit4"   type="button"   onclick="javascript:add4()"   value="增加">       
                    </td>  
				</tr>         
                <tr><td class="label">
                		</td>    
                        <td>           
                        <div   id="upid4"></div>       
                        </td>           
                </tr>  
		</table>
		</td>
 	</tr>
</table>

	
<!-- 附件 -->
<table id="sheet" class="formTable">
		<!--附件模板-->
		<tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
			    <eoms:attachment name="tawSheetAccess" property="accesss" 
			            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		</tr>		
	    <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="industrysms" alt="allowBlank:true"/> 				
		    </td>
	   </tr>			  
</table>


<!--派单树-->
<br/>
<fieldset>
 	<legend>
     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
		 <span id="roleName">
		 	 审核人
		 </span>
  	</legend>
    <div class="x-form-item" >
			<eoms:chooser id="sendObject"  type="role" roleId="1861" flowId="616" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>
<script type="text/javascript">
  function add1()                                   
  {   
        //option   =   new   Array();   
        //proportion   =   new   Array();                           
        //str='<table>';   
        //str=str+'<tr   align=center   valign=middle   bgcolor=#FFFFFF>';   
       // str=str+'<td   width="100%"   height="25"><input   type="text"   name=proportion></td>';   
       // str=str+'<td   width="100%"   height="25"><input   type="button"   name=del   onclick=""   value="删除"></td>';   
       // str=str+'</tr></table>';   
        //window.upid.innerHTML+=str+''; 


          str= '<input   type="text" class="text"  name=proportion />';
          //document.write(str);
          window.upid1.innerHTML+=str+'</br>';
  } 
  
  function add2()                                   
  {   
          str= '<input   type="text" class="text"  name=proportion />';
          window.upid2.innerHTML+=str+'</br>';
  } 
  
  function add3()                                   
  {   
          str= '<input   type="text" class="text"  name=proportionNew />';
          window.upid3.innerHTML+=str+'</br>';
  } 
  
  function add4()                                   
  {   
          str= '<input   type="text" class="text"  name=proportion />';
          window.upid4.innerHTML+=str+'</br>';
  } 
  
  function integrated()                                   
  {
  var ipFormat = /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])\.(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])\.(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])\.(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/;
  var loginUserFormat = /^\d{6}$/;
  //var pwdFormat = /(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{6,10}$/;
  var pwdFormat = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{6,10}$/;
  var maxConnectionsFormat = /^([1-9]|[1][0-5])$/;
  var numFormat = /^\d{5,14}$/;
  var spaceFormat = /\s/;
	  
  var cilentServerIpAddrsValue = '';
  var proportionArray = document.getElementsByName("proportion");
  for(var i=0;i<proportionArray.length;i++)
  {
  //alert(proportionArray[i].value);
  	if (proportionArray[i].value != "") {
  		if (!ipFormat.test(proportionArray[i].value) && proportionArray[i].value != "无") {
  			alert('是否为多IP中存在非法IP地址');
  			return false;
  		} else {
		  	if (cilentServerIpAddrsValue == "") {
		  		cilentServerIpAddrsValue = proportionArray[i].value;
		  	} else {
		  		cilentServerIpAddrsValue = cilentServerIpAddrsValue + ',' + proportionArray[i].value;
		  	}
  			if (proportionArray[i].value == "无") {
  				cilentServerIpAddrsValue = '';
  			}
  		}
  	}
  }
  var cilentServerIpAddrs = document.getElementById("cilentServerIpAddrs");
  cilentServerIpAddrs.value = cilentServerIpAddrsValue;
  //alert(cilentServerIpAddrsValue);
  
  var spareOne = document.getElementById("spareOne");
  var cilentServerIpAddr = document.getElementsByName("cilentServerIpAddr");
  var userValue = document.getElementsByName("userValue");
  var passwordValue = document.getElementsByName("passwordValue");
  var maxConnections = document.getElementsByName("maxConnections");
  var serviceCode = document.getElementsByName("serviceCode");
  var abbreviation = document.getElementsByName("abbreviation");
  var regional = document.getElementsByName("regional");
  var ecsiType = document.getElementsByName("ecsiType");
  var groupNumber = document.getElementsByName("groupNumber");
  var isUrgent = document.getElementsByName("isUrgent");
  var maxInBytes = document.getElementsByName("maxInBytes");
  var maxOutBytes = document.getElementsByName("maxOutBytes");
  var protocol = document.getElementsByName("protocol");
  
  if (spareOne.value == "101250101") {
	  if (!ipFormat.test(cilentServerIpAddr[0].value)) {
	  	alert('客户服务器IP为非法IP地址');
	  	return false;
	  }
	  if (!loginUserFormat.test(userValue[0].value)) {
	  	alert('登录网关用户名必须是6位数字格式');
	  	return false;
	  }
	  if (!pwdFormat.test(passwordValue[0].value)) {
	  	alert('登录网关密码必须包含大写、小写和数字,且长度6-10位');
	  	return false;
	  }
	  if (!maxConnectionsFormat.test(maxConnections[0].value)) {
	  	alert('最大连接数必须在1到15之间');
	  	return false;
	  }
	  if (!numFormat.test(serviceCode[0].value)) {
	  	alert('EC/SI服务代码必须是5到14位数字');
	  	return false;
	  }
	  if (spaceFormat.test(abbreviation[0].value)) {
	  	alert('EC/SI简称不能有空格');
	  	return false;
	  }
	  if (isUrgent[0].value == "1030102" && regional[0].value == "101250202" && groupNumber[0].value == "") {
	  	alert('集团批复工单号不能为空');
	  	return false;
	  } else if (isUrgent[0].value == "1030102" && regional[0].value == "101250202" && groupNumber[0].value != "") {
		  if (spaceFormat.test(groupNumber[0].value)) {
		  	alert('集团批复工单号不能有空格');
		  	return false;
		  }
	  }
  } else if (spareOne.value == "101250102") {
	  if (!loginUserFormat.test(userValue[1].value)) {
	  	alert('登录网关用户名必须是6位数字格式');
	  	return false;
	  }
	  if (!numFormat.test(serviceCode[1].value)) {
	  	alert('EC/SI服务代码必须是5到14位数字');
	  	return false;
	  }
	  
  	  var cilentServerIpAddrNew = document.getElementById("cilentServerIpAddrNew");
  	  var passwordNew = document.getElementById("passwordNew");
  	  var maxConnectionsNew = document.getElementById("maxConnectionsNew");
  	  var abbreviationNew = document.getElementById("abbreviationNew");
  	  var maxInBytesNew = document.getElementById("maxInBytesNew");
  	  var maxOutBytesNew = document.getElementById("maxOutBytesNew");
  	  var protocolNew = document.getElementById("protocolNew");
  	  if (maxInBytesNew.value != "" && maxInBytes[1].value == "") {
	  	  alert('最大下发流量不能为空');
	  	  return false;
	  } else if (maxInBytesNew.value == "" && maxInBytes[1].value != "") {
	  	  alert('最大下发流量无更改无需填写');
	  	  return false;
	  }
  	  if (maxOutBytesNew.value != "" && maxOutBytes[1].value == "") {
	  	  alert('最大上行流量不能为空');
	  	  return false;
	  } else if (maxOutBytesNew.value == "" && maxOutBytes[1].value != "") {
	  	  alert('最大上行流量无更改无需填写');
	  	  return false;
	  }
	  if (cilentServerIpAddrNew.value == "" && cilentServerIpAddr[1].value != "") {
	  	  if (!ipFormat.test(cilentServerIpAddr[1].value)) {
		  	alert('客户服务器IP为非法IP地址');
		  	return false;
		  }
	  } else if (cilentServerIpAddrNew.value != "" && cilentServerIpAddr[1].value != "") {
	  	  if (!ipFormat.test(cilentServerIpAddr[1].value)) {
		  	alert('客户服务器IP为非法IP地址');
		  	return false;
		  }
		  if (!ipFormat.test(cilentServerIpAddrNew.value)) {
		  	alert('客户服务器IP(新)为非法IP地址');
		  	return false;
		  }
	  } else {
	  	  alert('客户服务器IP不能为空');
	  	  return false;
	  }
	  if (passwordValue[1].value != "") {
		  	alert('登录网关密码无需填写');
		  	return false;
	  } else if (passwordNew.value != "" && passwordValue[1].value == "") {
		  if (!pwdFormat.test(passwordNew.value)) {
		  	alert('登录网关密码(新)必须包含大写、小写和数字,且长度6-10位');
		  	return false;
		  }
	  }
	  if (maxConnectionsNew.value == "" && maxConnections[1].value != "") {
		  	alert('最大连接数无更改无需填写');
		  	return false;
	  } else if (maxConnectionsNew.value != "" && maxConnections[1].value != "") {
	  	  if (!maxConnectionsFormat.test(maxConnections[1].value)) {
		  	alert('最大连接数必须在1到15之间');
		  	return false;
		  }
		  if (!maxConnectionsFormat.test(maxConnectionsNew.value)) {
		  	alert('最大连接数(新)必须在1到15之间');
		  	return false;
		  }
	  } else if (maxConnectionsNew.value != "" && maxConnections[1].value == "") {
	  	  alert('最大连接数不能为空');
	  	  return false;
	  }
	  if (abbreviationNew.value == "" && abbreviation[1].value != "") {
		  if (spaceFormat.test(abbreviation[1].value)) {
		  	alert('EC/SI简称不能有空格');
		  	return false;
		  }
	  } else if (abbreviationNew.value != "" && abbreviation[1].value != "") {
	  	  if (spaceFormat.test(abbreviation[1].value)) {
		  	alert('EC/SI简称不能有空格');
		  	return false;
		  }
		  if (spaceFormat.test(abbreviationNew.value)) {
		  	alert('EC/SI简称(新)不能有空格');
		  	return false;
		  }
	  } else {
	  	  alert('EC/SI简称不能为空');
	  	  return false;
	  }
	  if (regional[1].value == "101250202" && groupNumber[1].value == "") {
	  	alert('集团批复工单号不能为空');
	  	return false;
	  } else if (regional[1].value == "101250202" && groupNumber[1].value != "") {
		  if (spaceFormat.test(groupNumber[1].value)) {
		  	alert('集团批复工单号不能有空格');
		  	return false;
		  }
	  }

	  
	  var cilentServerIpAddrsNewValue = '';
	  var proportionNewArray = document.getElementsByName("proportionNew");
	  for(var i=0;i<proportionNewArray.length;i++)
	  {
	  //alert(proportionNewArray[i].value);
	  	if (proportionNewArray[i].value != "") {
	  		if (!ipFormat.test(proportionNewArray[i].value) && proportionNewArray[i].value != '无') {
	  			alert('是否为多IP(新)中存在非法IP地址');
	  			return false;
	  		} else {
			  	if (cilentServerIpAddrsNewValue == "") {
			  		cilentServerIpAddrsNewValue = proportionNewArray[i].value;
			  	} else {
			  		cilentServerIpAddrsNewValue = cilentServerIpAddrsNewValue + ',' + proportionNewArray[i].value;
			  	}
				if (proportionNewArray[i].value == "无") {
	  				cilentServerIpAddrsNewValue = '';
	  			}
	  		}
	  	}
	  }
	  var cilentServerIpAddrsNew = document.getElementById("cilentServerIpAddrsNew");
	  cilentServerIpAddrsNew.value = cilentServerIpAddrsNewValue;
  } else if (spareOne.value == "101250103") {
	  if (!ipFormat.test(cilentServerIpAddr[2].value)) {
	  	alert('客户服务器IP为非法IP地址');
	  	return false;
	  }
  	  if (!numFormat.test(serviceCode[2].value)) {
	  	alert('EC/SI服务代码必须是5到14位数字');
	  	return false;
	  }
	  if (spaceFormat.test(abbreviation[2].value)) {
	  	alert('EC/SI简称不能有空格');
	  	return false;
	  }
	  if (regional[2].value == "101250202" && groupNumber[2].value == "") {
	  	alert('集团批复工单号不能为空');
	  	return false;
	  } else if (regional[2].value == "101250202" && groupNumber[2].value != "") {
		  if (spaceFormat.test(groupNumber[2].value)) {
		  	alert('集团批复工单号不能有空格');
		  	return false;
		  }
	  }
  }
  return true;
  //alert(maxConnections.value);
  //alert(maxConnectionsFormat.test(maxConnections.value));
  }
</script>