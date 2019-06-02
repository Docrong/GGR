<%@page
	import="java.util.List,com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation,com.boco.eoms.wap.util.*,org.apache.abdera.model.Entry,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"
	pageEncoding="UTF-8"%>
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
	  java.util.List leaves = (java.util.List) request
					.getAttribute("MENULIST");
	  java.util.Map menuNext = (java.util.Map) request
					.getAttribute("MAPNEXT");
	 String flagNull = (String)request.getAttribute("flagNull");
			// 系统路径
	 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	 String realPath = request.getContextPath();
	 int curPage = 0;
		 if(request.getParameter("curPage")!=null)
		 {
		 	try{
		 		curPage = (Integer.parseInt(request.getParameter("curPage")));
		 	}catch(NumberFormatException e){
		 		e.printStackTrace();
		 	}
		 }
		 if("0".equals(flagNull)){
			String url = "";
			for (int j = 0; j < leaves.size(); j++) {
				url = url   
					+ ","
					+ WapUtil.checkHttpUrl(basePath,WapUtil.spiturl(((TawSystemPrivOperation) leaves.get(j))
							.getUrl()));
			}
					
			url = url.replaceAll("pageSize=0","pageSize=5");
			url = url.replaceAll("curPage=0","curPage="+(curPage));
					
			List EntryList = AtomUtil.getFeedByURLandFilter4Pager(url,
					userid,realPath,"");
			%>
<body>
<form action="${app}/wapback.do?method=performWapBack&wapLogin=wap"
	method="post">
<jsp:include page="head.jsp" flush="true" />
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="sub_table_bg">
	<tr>
		<td class="title_bg_01">
			<% 
				if(curPage!=0){
			%>								    	
				<a href="action.do?method=menu&id=<%=session.getAttribute("id")%>&curPage=<%=curPage-1%>">上一页</a>
			<%
				}
				else
				{
			%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%
				}
				if(EntryList!=null&&EntryList.size()!=0)
				{
			%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="action.do?method=menu&id=<%=session.getAttribute("id")%>&curPage=<%=curPage+1%>">下一页</a>
			<%
				}
			%>
		</td>
	</tr>
	<%
					if (EntryList != null) {
						if(EntryList.size()==0){
					%>
							<tr>
							<td class="tt_head_bg">
								已经是最后一页了。
							</td>
						</tr>
					<%
						}
						else{
							for (int k = 0; k < EntryList.size(); k++) {
				%>
				<div style="padding-top: 5px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="sub_table_bg">
						<tr>
							<td class="tt_head_bg">
								<img src="${app}/wap/images/tu01_1.gif" />
								<a href="<%=((Entry) EntryList.get(k)).getContent()%>"><%=((Entry) EntryList.get(k)).getTitle()%></a>
							</td>
						</tr>
						<%
							if (null != ((Entry) EntryList.get(k)).getSummary()) {
						%>
						<tr>
							<td class="content_text_03">
								<%
									if (9 > ((Entry) EntryList.get(k)).getSummary()
															.length()) {
								%>
								<%=AtomUtil.substring(((Entry) EntryList
												.get(k)).getSummary(), 0, 8)%>
								<%
									} else {
								%>
								<%=AtomUtil.substring(((Entry) EntryList
												.get(k)).getSummary(), 0, 8)%>...
								<%
									}
								%>
							</td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
				<%
							}
						}
					}
				%>

	<%
			}else{
			for(int i=0;i<leaves.size();i++){
			TawSystemPrivOperation oper = (TawSystemPrivOperation)leaves.get(i);
						%>

	<tr>
		<td align=center class="tt_head_bg"><img
			src="${app}/wap/images/tu01_1.gif" /> <%out.println(AtomUtil.substring(
								oper.getName(), 0, 4)
								+ "<br/>");

						%>
		</td>
	</tr>
	<%
			  List list = (List)menuNext.get(oper.getId());
			  String url = "";
			    for(int k =0;k<list.size();k++){
			  		  		url = ","
									+ WapUtil.checkHttpUrl(basePath,WapUtil.spiturl(((TawSystemPrivOperation) list.get(k))
											.getUrl()));
							List EntryList = AtomUtil
									.getFeedByURLandFilter4Pager(url, userid,realPath,"");

							if (EntryList != null) {
								for (int z = 0; z < EntryList.size(); z++) {

									%>
	<tr>
		<td align=left class="tt_head_bg"><img
			src="${app}/wap/images/tu01_1.gif" /> <a
			href="<%=((Entry)EntryList.get(z)).getContent()%>"><%=((Entry) EntryList.get(z))
													.getTitle()%></a> <br />
		</td>
	</tr>
	<%}
							}

							%>


	<%
				 }
			}
			}
			
		%>




	<tr colspan="2" class="table_td">
		<td><input align=center name="Button" type="button" value="返回"
			class="btn_02"
			onclick="javascript:{var url='${app}/wapback.do?method=performWapBack&wapLogin=wap';location.href=url}" /></td>

	</tr>
</table>

</form>
</body>
</html>
