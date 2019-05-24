<%@page pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title>工单处理</title>
</head>
<body >
<jsp:include page="../../../head.jsp" flush="true"/>
<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
<form action="${app}/wap/CommonTaskAction.do?method=performDeal" method="post">
  <input name="url" type="hidden" value='<%=(String) request.getAttribute("url")%>'/>
  <input name="hiddenStr" type="hidden" value='<%=(String) request.getAttribute("hiddenStr")%>' />
  	<tr>
    	<td class="title_bg_01"><img src="images/s_ico_01.gif" /> 工单处理</td>
  	</tr>
  
  <tr>
    <td class="list_text_03"><div class="div_all">
    	<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
            <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">审批结果:</td>
        <td class="fb_add_content">
        	<nobr>通过</nobr><input type="hidden" name="linkAuditResult" value="101040101"/>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg"><nobr>审批意见:</nobr></td>
        <td class="fb_add_content"><textarea name="linkAuditIdea"></textarea></td>
      </tr>
    </table></div> </td>
  </tr>
  
  
  		<tr>
			    <td class="btn_bg">
			    	 <table>
					  		<tr>
					    	<td><input name="completePass" type="submit" class="btn_02" value="提交" /></td>  	
							  </tr>
							</table>
						
					</td>
				</tr>

  </form>
</table>
</body>
</html>
