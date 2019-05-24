<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title></title>
</head>
<body>
<jsp:include page="../../head.jsp" flush="true"/>
	<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
			 	<tr>
			    <td class="title_bg_01"><img src="${app}/wap/images/s_ico_01.gif"/>工单处理</td>
			  </tr>
			     <br>		     
			  <tr>   
			   <td class="list_text_03">
						<div class="div_all">
							<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
									<tr class="fb_xx_head_tr">
										<td class="tt_head_bg">温馨提示：您的操作未成功!</td>
									</tr>
													<tr>
														<td class="btn_bg">
															<table>
															  <tr>
																	<td>
																		<input name="Button" type="button" value="返回" class="btn_02" 
							onclick="javascript:{var url='${app}/wapback.do?method=performWapBack&wapLogin=wap';location.href=url}"/>	
																	</td>
																</tr>
															</table>
														</td>
													 </tr>
									
							</table>
						</div>	
					</td>
				 </tr> 
			</table> 
	</body>	
</html>