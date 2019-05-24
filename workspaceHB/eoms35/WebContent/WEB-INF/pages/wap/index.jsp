<%@page import="java.util.List,com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation,com.boco.eoms.wap.util.*,org.apache.abdera.model.Entry,com.boco.eoms.commons.system.session.form.TawSystemSessionForm" pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ include file="/wap/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

	<head>
		<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
		<title>电子运维wap系统</title>
	</head>
	<%TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userid = sessionform.getUserid();
			%>
	<%java.util.List menuMain = (java.util.List) request.getSession()
					.getAttribute("menuMain");
			java.util.Map menuLeaf = (java.util.Map) request.getSession()
					.getAttribute("menuLeaf");
			java.util.Map menuLeafNext = (java.util.Map) request.getSession()
					.getAttribute("menuLeafNext");
			String flagNull = (String) request.getSession().getAttribute(
					"flagNull");
			// 系统路径
		    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		    String realPath = request.getContextPath();
			%>
	<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ico_bg">
	 	<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_width" >
			 
				 
					<%for (int i = 0; i < menuMain.size(); i++) {
				com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation privOper = (com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation) menuMain
						.get(i);

				%> 
				<%if ((i + 1) % 3 == 1) {%>
			      <tr class="nav_text">
				<%}%>
				<!-- 验证这个最顶级菜单是什么类型。如果为function 则可以直接herf 如果为model 则向下显示-->
					<td> 
						<%if("1".equals(privOper.getIsApp())){
					    
					   	String realUrl = WapUtil.checkHttpUrl(request.getContextPath(),privOper.getUrl());
					%><a href="<%=realUrl%>" accesskey="<%=i+1%>" ><img src="${app}/wap/images/ico0<%=i+1%>.gif"/> </a><br />
					<!-- 按照4个截位。-->
					 <a href="<%=realUrl%>" accesskey="<%=i+1%>" ><%=AtomUtil.substring(privOper.getName(), 0, 4)%> </a>
					   <%}else{%>
					   <a href="#help<%=i%>" accesskey="<%=i+1%>" ><img src="${app}/wap/images/ico0<%=i+1%>.gif"/></a><br />
						<a href="#help<%=i%>" accesskey="<%=i+1%>" ><%=AtomUtil.substring(privOper.getName(), 0, 4)%> </a>
						<%}%>
					</td>
				<%if ((i + 1) % 3 == 0 || (i + 1) == menuMain.size()) {%>
			      </tr>
				<%}%>
			  <%}
			%>			 
			</table>
   </td>
  </tr>
</table>

		 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="sub_table_bg" >
				   
				<%if ("0".equals(flagNull)) {%>
				<%for (int i = 0; i < menuMain.size(); i++) {
					com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation privOper = (com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation) menuMain
							.get(i);
					if(i==0)
					{
						String id = privOper.getUrl().substring(privOper.getUrl().indexOf("id=")+3,privOper.getUrl().indexOf("id=")+5);
						session.setAttribute("id",id);
					}
					List leaves = (List) menuLeaf.get(privOper.getId());
					String url = "";
				%>
				<tr>
					<td class="title_bg_0<%if(0==i){%>1<%}else{%>2<%}%>" align="left"><img src="${app}/wap/images/s_ico_0<%=i+1%>.gif">
					<a href ="${app}<%=privOper.getUrl()%>" >
						<%=AtomUtil
								.substring(privOper.getName(), 0, 4)%>
						</a>
					<%if("0".equals(privOper.getIsApp())){
					%>
					<a name="help<%=i%>"/> 
					
					<%}%>
					</a></td>
                 <td align="right" class="title_bg_0<%if(0==i){%>1<%}else{%>2<%}%>"><a href ="${app}<%=privOper.getUrl()%>"><img src="${app}/wap/images/ico_more_1.gif"></a></td>
				</tr>
				
				<%
					for (int j = 0; j < leaves.size(); j++) {
                      url = url
								+ ","
								+ WapUtil.checkHttpUrl(basePath,((TawSystemPrivOperation) leaves.get(j))
										.getUrl());
					}
					List EntryList = AtomUtil.getFeedByURLandFilter4Pager(url,
							userid,realPath,"");

					
					%>
					<tr>
					<td><div class="content_text_01"><ul>
					<%
					if (EntryList != null) {
						for (int k = 0; k < EntryList.size(); k++) {
							%>
				
				   
						<li><a class="a1" href="<%=((Entry)EntryList.get(k)).getContent()%>"><%=((Entry) EntryList.get(k)).getTitle()%></a></li>
					
					
				
				<%}%>
					</td>
				</tr>
				<%
					}

					%>
				
				 <%}
			} else {
				for (int i = 0; i < menuMain.size(); i++) {
					com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation privOper = (com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation) menuMain
							.get(i);
					List leaves = (List) menuLeaf.get(privOper.getId());
					String url = "";

					%>
				<tr>
					<td align=center>
					<a href ="${app}<%=privOper.getUrl()%>" >
						<%=AtomUtil
								.substring(privOper.getName(), 0, 4)%>
					</a>
					<%if("0".equals(privOper.getIsApp())){
					%>
					<a name="help<%=i%>"/> 
					
					<%}%>
					</td>
				</tr>
				<%for (int j = 0; j < leaves.size(); j++) {

						%>
				<tr>
					<td align=center>
						<%out.println(AtomUtil.substring(
								((TawSystemPrivOperation) leaves.get(j))
										.getName(), 0, 4)
								+ "<br/>");

						%>
					</td>
				</tr>
			 
				<%List leafNext = (List) menuLeafNext
								.get(((TawSystemPrivOperation) leaves.get(j))
										.getId());
						for (int k = 0; k < leafNext.size(); k++) {
							url = ","
									+ WapUtil.checkHttpUrl(basePath,((TawSystemPrivOperation) leafNext.get(k))
											.getUrl());

							List EntryList = AtomUtil
									.getFeedByURLandFilter4Pager(url, userid,realPath,"");

							if (EntryList != null) {
								for (int z = 0; z < EntryList.size(); z++) {

									%>
				<tr>
					<td>
						<a href="<%=((Entry)EntryList.get(z)).getContent()%>"><%=((Entry) EntryList.get(z))
													.getTitle()%></a>
						<br />
					</td>
				</tr>
				<%}
							}

							%>                              
				   
				<%}
					}
				}
			  }
		%>
			</table> 		
	</body>
</html>
