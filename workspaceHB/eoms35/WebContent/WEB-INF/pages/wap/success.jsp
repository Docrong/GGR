<%@page pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ include file="/wap/common/taglibs.jsp"%>
	<%@ page import="com.boco.eoms.base.Constants;"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
		<title><bean:message key="wap.common.rember.password.title" /></title>
	</head>
	 
	<body>
		<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
			 	<tr>
			    <td class="title_bg_01"><img src="images/s_ico_01.gif" width="15" height="19" />修改密码</td>
			  </tr>
			     <br>		     
			  <tr>   
			   <td class="list_text_03">
						<div class="div_all">
							<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
									<tr class="fb_xx_head_tr">
										<td class="tt_head_bg">密码修改成功</td>
									</tr>
									<tr>
										<td><a href="${app}/wap/"> 返回</a></td>
					  			</tr>
							</table>
						</div>	
					</td>
				 </tr> 
			</table> 
	 
	</body>
</html>
