<%@page import="java.util.*" pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ include file="/wap/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">


<html>
<head>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title></title>
<body>
   <form action="${app}/wapback.do?method=performWapBack&wapLogin=wap" method="post" >
 	<jsp:include page="../../head.jsp" flush="true"/>
		<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
			 	<tr>
			    <td class="title_bg_01"><img src="images/s_ico_01.gif" width="15" height="19" />作业计划</td>
			  </tr>
			     <br>		     
			  <tr>   
			   <td class="list_text_03">
						<div class="div_all">
							<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
									<tr class="fb_xx_head_tr">
										<td class="tt_head_bg">提交成功！</td>
									</tr>
													<tr>
														<td class="btn_bg">
															<table>
															  <tr>
																	<td><input name="Submit222" type="submit" class="btn_02" value="返回" />
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
		</form>
</body> 
  
  
</html>