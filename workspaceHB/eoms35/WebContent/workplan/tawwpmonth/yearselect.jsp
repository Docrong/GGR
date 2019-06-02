<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<form action="${app}/workplan/tawwpmonthplan/yearexport.do" method="post">
  
   <select name="year">
   		 
          <option value="2007">2007</option>
          <option value="2008">2008</option>
          <option value="2009" selected>2009</option>
          <option value="2010">2010</option>
          <option value="2011">2011</option>
   </select>
 
<BR> 
<input type="submit" value="导出" class="button" />

</form>
<%@ include file="/common/footer_eoms.jsp"%>
