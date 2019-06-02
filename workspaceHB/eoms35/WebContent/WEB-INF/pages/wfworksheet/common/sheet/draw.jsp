<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
function smallit(){            
	var height1=PhotoViewer.images1.height;            
	var width1=PhotoViewer.images1.width;            
	PhotoViewer.images1.height=height1/1.2;            
	PhotoViewer.images1.width=width1/1.2;           
}             
function bigit(){            
	var height1=PhotoViewer.images1.height;            
	var width1=PhotoViewer.images1.width;            
	PhotoViewer.images1.height=height1*1.2;          
	PhotoViewer.images1.width=width1*1.2;           
}             
function fullit()
{
	var width_s=screen.width-10;
	var height_s=screen.height-30;
	window.open(document.getElementById("PhotoViewer").contentWindow.location.href, "PhotoView", "width="+width_s+",height="+height_s+",left=0,top=0,location=no,toolbar=no,status=no,resizable=no,scrollbars=yes,menubar=no,directories=no");
}
function realsize()
{
	PhotoViewer.images1.height=PhotoViewer.images2.height;     
	PhotoViewer.images1.width=PhotoViewer.images2.width;
	PhotoViewer.block1.style.left = 0;
	PhotoViewer.block1.style.top = 0;
}
function featsize()
{
	var width1=PhotoViewer.images2.width;            
	var height1=PhotoViewer.images2.height;            
	var width2=760;            
	var height2=500;            
	var h=height1/height2;
	var w=width1/width2;
	if(height1<height2&&width1<width2)
	{
		PhotoViewer.images1.height=height1;            
		PhotoViewer.images1.width=width1;           
	}
	else
	{
		if(h>w)
		{
			PhotoViewer.images1.height=height2;          
			PhotoViewer.images1.width=width1*height2/height1;           
		}
		else
		{
			PhotoViewer.images1.width=width2;           
			PhotoViewer.images1.height=height1*width2/width1;          
		}
	}
	PhotoViewer.block1.style.left = 0;
	PhotoViewer.block1.style.top = 0;
}
</script>
<style type="text/css">
.input2 {
	background-color: #f9f9f9;
	font-weight: normal;
	color: #666666;
	font-family: "Arial", "Helvetica";
	font-size: 12px;
	border-top: 1px solid #808080;
	border-right: 1px solid #dfdfdf;
	border-bottom: 1px solid #dfdfdf;
	border-left: 1px solid #808080;
}
</style>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border2">
	<tr>
       	<td height="40" align="center" valign="middle" class="tdbg_leftall">
		<input name="smallit" type="button" id="smallit" onClick="smallit();" value="- 缩  小 -" class="input2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="bigit" type="button" id="bigit" onClick="bigit();" value="+ 放  大 +"  class="input2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="fullit" type="button" id="fullit" value="+全屏显示+" onClick="fullit();"  class="input2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="realsize" type="button" id="realsize" value="+实际大小+" onClick="realsize();"  class="input2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="featsize" type="button" id="featsize" value="+合适大小+" onClick="featsize();"  class="input2">
		</td>
    </tr>
    <tr>
		<td align="center" valign="middle" class="tdbg_leftall">
			<iframe id='PhotoViewer' width='100%' frameborder=0 name='down' height='500' scrolling='no' src='${app}/sheet/${module}/${module}.do?method=showPic'  class=input2></iframe>
		</td>
	</tr>
</table>

<%@ include file="/common/footer_eoms.jsp"%>
