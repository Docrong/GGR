<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import = "java.util.*,java.lang.*"%>

<html>
<head>
<title>WEB页面操作演示</title>
<!--引入js集-->
<script src="<%=request.getContextPath()%>/css/common/js/comm.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/page.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/table.js"></script>

<script language="javascript">
//设置表格样式
function window.onload(){
  //参数为你要定制的表格id
  setTableStyle(table1);
}
</script>
</head>
<body>
<span class="Page_Tools">

<!--以下onclick事件的参数一般为：第一个是form名，第二个是要提交处理的页面地址-->
<P><LI>表格排序及页面操作：</LI></P>
<input type=checkbox id=chkSelectAll name=chkSelectAll onclick="selectCheckBox('document.del')">
<a href="javascript:document.getElementsByName('chkSelectAll')[0].click()">全选</a>
<a href="javascript:switchCheckBox('document.del')">反选</a>
<a href="javascript:doModify('document.del','handel.do')">编辑</a>
<a href="javascript:doModify('document.del','handel.do')">处理</a>
<a href="javascript:doDel('document.del','del.do')">删除</a>
</span>

<!--以下代码要按格式写，注意thead，tbody,标题列的class必须为SortTableTitle-->
<form name=del>
<table id=table1>
  <thead>
    <tr class="SortTableTitle"><td>　</td><td width=100>工单流水号</td><td width=150>主题</td><td width=80>申告部门</td><td width=100>联系方式</td><td width=60>紧急程度</td></tr>
  </thead>
  <tbody>
    <tr><td><input type=checkbox name=mid></td><td>啊啊</td><td>abd</td><td>1123</td><td>2003-1-1</td><td>重要</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>啊才</td><td>abc</td><td>123</td><td>2003-1-1 00:02:01</td><td>紧急</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>啊不</td><td>地华人民共和国</td><td>1.23</td><td>2003-01-1 00:00:00</td><td>紧急</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>的</td><td>中华人民共和国</td><td>1,233</td><td>2003-2-1</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>饿</td><td>Abc</td><td>-123</td><td>2003-01-01</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>发</td><td>中华人民共和国</td><td>123</td><td>2003-04-1</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>月</td><td>Bbc</td><td>123</td><td>2003-3-2</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>在</td><td>中华人民共和国</td><td>123</td><td>2003-5-1</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>可</td><td>bac</td><td>123</td><td>2003-8-01</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>为</td><td>中华人民共和国</td><td>123</td><td>2003/1/1</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>这</td><td>中华人民共和国</td><td>123</td><td>2003/01/01</td><td>一般</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>人</td><td>中华人民共和国</td><td>123</td><td>2003-1-1 00:02:01</td><td>次要</td></tr>
    <tr><td><input type=checkbox name=mid></td><td>上</td><td>中华人民共和国</td><td>123</td><td>2003-1-1</td><td>次要</td></tr>
  </tbody>
</table>
</form>



</body>
</html>