<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/footer_eoms.jsp"%>

<%@page import="com.boco.eoms.interSwitchAlarm.*" %>
<%@page import="com.boco.eoms.sheet.commonfault.service.bo.*" %>
<%@page import="com.boco.eoms.handheldoperation.*" %>
<%@page import="java.util.*" %>
<%
System.out.println("ssssssssssssssssssssssssssssssssssssssss");
String xml = "<opDetail><recordInfo><fieldInfo><fieldChName>Sheet_id</fieldChName><fieldEnName>Sheet_id</fieldEnName><fieldContent>HB-051-091010-1-10276</fieldContent></fieldInfo></recordInfo></opDetail>";
		
		
	HandheldOperation2EomsService ia = new HandheldOperation2EomsService();

	String result = ia.getSheetInfoService(xml);
			
			//ia.eomsAuthentication("","","","",xml);
	System.out.println("ffffffffffffffffffffffffffffff="+result);
	
	//CommonFaultBO.updateAlarm("8aa082851ce55ca6011ce5619f870005","草稿");
	//ia.syncAlarm("","","","",xml);
 %>
<DIV class=list-title><b>${eoms:a2u('系统介绍')}</b></DIV>
<TABLE cellSpacing=10 cellPadding=10 width="98%" border=0>
	<TBODY>
		<TR>
		<TD style="color:#333">
		&nbsp;&nbsp;&nbsp;&nbsp;${eoms:a2u('中国移动通信集团湖北有限公司（以下简称湖北移动）运行维护部门从自身的运行维护工作模式和特点出发，利用现有的MDCN网络构建了一套满足湖北移动运行维护工作需要的集中化、流程化、电子化、自动化、智能化的系统。电子运维系统的专业范围包括移动互联网、传送网、话务汇接网、信令网、GSM网、GPRS网、环境动力、支撑系统等。该系统将主要用于支撑省公司内部及各地市公司的网络运行维护工作，并辅助省公司根据维护记录，总结相关经验，进行维护工作的统计考核，从而提高运行维护工作的质量和全网维护人员的技能水平。') }
		</TD>
		</TR>
		<TR>
		<TD align="center"><img src="${app}/images/introduction/totle1.JPG">
		</TD>
		</TR>
	</TBODY>
</TABLE>
