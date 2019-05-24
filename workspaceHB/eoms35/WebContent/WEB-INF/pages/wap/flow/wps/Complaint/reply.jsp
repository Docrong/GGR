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
<jsp:include page="../../../head.jsp" flush="true"/>
<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
<form action="${app}/wap/ComplaintAction.do?method=performDeal" method="post">
  <input name="url" type="hidden" value='<%=(String) request.getAttribute("url")%>'/>
  <input name="hiddenStr" type="hidden" value='<%=(String) request.getAttribute("hiddenStr")%>' />
  	<tr>
    	<td class="title_bg_01"><img src="images/s_ico_01.gif" /> 工单处理</td>
  	</tr>
  
  <tr>
    <td class="list_text_03"><div class="div_all">
    	<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg" style="table-layout:fixed;word-break:break-all">
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">联系人:</td>
        <td class="fb_add_content">
        <input type="text" name="ndeptContact"/>
        </td>
      </tr>
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">联系人电话:</td>
        <td class="fb_add_content">
        	<input type="text" name="ndeptContactPhone"/>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">是否启动变更配置:</td>
        <td class="fb_add_content">
        	<select name="linkIfInvokeChange">
        		<option value="1030101">是</option>
        		<option value="1030102">否</option>
        	</select>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">变更流程工单编号:</td>
        <td class="fb_add_content">
        	<input type="text" name="linkChangeSheetId"/>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">故障开始时间:<br/>(如2009-01-12 12:08:59): </td>
        <td class="fb_add_content">
        	<input type="text" name="linkFaultStartTime" value="<%=StaticMethod.getCurrentDateTime() %>"/>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">故障结束时间:<br/>(如2009-01-12 12:08:59): </td>
        <td class="fb_add_content">
        	<input type="text" name="linkFaultEndTime" value="<%=StaticMethod.getCurrentDateTime() %>"/>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">故障地点:</td>
        <td class="fb_add_content">
        	<input type="text" name="linkFaultGenerantPlace"/>
        </td>
      </tr>
      
     <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">具体位置描述:</td>
        <td class="fb_add_content">
        	<input type="text" name="linkPlaceDesc"/>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">问题消除时间:<br/>(如2009-01-12 12:08:59): </td>
        <td class="fb_add_content">
        	<input type="text" name="issueEliminatTime" value="<%=StaticMethod.getCurrentDateTime() %>"/>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">问题原因:</td>
        <td class="fb_add_content">
        	<input type="text" name="issueEliminatReason"/>
        </td>
      </tr>
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">处理结果:</td>
        <td class="fb_add_content">
        	<select name="dealResult">
        		<option value="101100701">解决</option>
        		<option value="101100702">未解决</option>
        	</select>
        </td>
      </tr>
      
      
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg">解决措施:</td>
        <td class="fb_add_content" style="word-wrap:break-word;word-break:break-all"><textarea name="dealDesc"></textarea></td>
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
