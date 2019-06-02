<%@page import="java.util.Date,com.boco.eoms.base.util.StaticMethod" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title>工单处理</title>
</head>
<body >
<jsp:include page="../../head.jsp" flush="true"/>
<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
<form action="${app}/wap/WPSAction.do?method=performDeal" method="POST">
  <input name="url" type="hidden" value='<%=(String) request.getAttribute("url")%>'/>
  <input name="hiddenStr" type="hidden" value='<%=(String) request.getAttribute("hiddenStr")%>' />
  	<tr>
    	<td class="title_bg_01"><img src="images/s_ico_01.gif" /> 工单处理</td>
  	</tr>
  
  <tr>
    <td class="list_text_03"><div class="div_all">
    	<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg" style="table-layout:fixed;word-break:break-all">
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">故障处理结果:</td>
        <td class="fb_add_content">
        	<select name="linkFaultDealResult">
        		<option value="101030601">已解决</option>
        		<option value="101030602">延期解决</option>
        		<option value="101030603">无需解决</option>
        	</select>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">故障原因类别:</td>
        <td class="fb_add_content">
        	<select name="linkFaultReasonSort">
        		<option value="101030301">系统</option>
        		<option value="101030302">传输</option>
        		<option value="101030303">人为</option>
        		<option value="101030304">外部</option>
        		<option value="101030305">环境</option>
        		<option value="101030306">自然灾害</option>
        		<option value="101030307">其它</option>
        	</select>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">处理措施:</td>
        <td class="fb_add_content" style="word-wrap:break-word;word-break:break-all"><textarea name="linkDealStep"></textarea></td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">是否实施网络变更:</td>
        <td class="fb_add_content">
        	<select name="linkIfExcuteNetChange">
        		<option value="1030101">是</option>
        		<option value="1030102">否</option>
        	</select>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">是否为最终解决方案:</td>
        <td class="fb_add_content">
        	<select name="linkIfFinallySolveProject">
        		<option value="1030101">是</option>
        		<option value="1030102">否</option>
        	</select>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">是否申请入案例库:</td>
        <td class="fb_add_content">
        	<select name="linkIfAddCaseDataBase">
        		<option value="1030101">是</option>
        		<option value="1030102">否</option>
        	</select>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">故障消除时间<br/>(如2009-01-12 12:08:59): </td>
        <td class="fb_add_content">
        	<input type="text" name="linkFaultAvoidTime" value="<%=StaticMethod.getCurrentDateTime() %>"/>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">业务恢复时间<br/>(如2009-01-12 12:08:59): </td>
        <td class="fb_add_content">
        	<input type="text" name="linkOperRenewTime" value="<%=StaticMethod.getCurrentDateTime() %>"/>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">影响业务时长:</td>
        <td class="fb_add_content">
        	<input type="text" name="linkAffectTimeLength" />
        </td>
      </tr>
    </table></div> </td>
  </tr>
  
  		<tr>
			    <td class="btn_bg">
			    	 <table>
					  		<tr>
					    	<td><input name="reply" type="submit" class="btn_02" value="提交" /></td>  	
							  </tr>
							</table>
						
					</td>
				</tr>
  </form>
</table>
</body>
</html>
