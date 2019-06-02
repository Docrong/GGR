<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.boco.eoms.base.util.StaticMethod;"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
String photoName = StaticMethod.nullObject2String(request.getAttribute("photoName"));
String filePath =  StaticMethod.nullObject2String(request.getAttribute("filePath"));
String step = StaticMethod.nullObject2String(request.getAttribute("step"));

String defaultPic = "images/head/man.gif";

if("1".equals(step) && !photoName.equals(""))
   defaultPic = "images/head/photo/zoom/" + photoName;

if("3".equals(step) && !photoName.equals(""))
   defaultPic = "images/head/photo/zoom/" + photoName;
%>

<link type="text/css" rel="Stylesheet"	href="${app}/scripts/kmmanager/upphoto/main.css" />
<script type="text/javascript"	src="${app}/scripts/kmmanager/upphoto/jquery1.2.6.pack.js"></script>
<script type="text/javascript"	src="${app}/scripts/kmmanager/upphoto/ui.core.packed.js"></script>
<script type="text/javascript"	src="${app}/scripts/kmmanager/upphoto/ui.draggable.packed.js"></script>
<script type="text/javascript"	src="${app}/scripts/kmmanager/upphoto/CutPic.js"></script>
<script type="text/javascript">
    function Step1() {
        $("#Step2Container").hide();           
    }
    function Step2() {
        $("#CurruntPicContainer").hide();
    }
    function Step3() {
        $("#Step2Container").hide();          
    }
</script>

<div>
    <!-- 界面左半部分 -->
	<div class="left">
		<!--当前照片-->
		<div id="CurruntPicContainer">
			<div class="title">
				<br><b>当前照片</b>
			</div>
			<div class="photocontainer">
				<img id="imgphoto" src="${app}/<%=defaultPic%>" style="border-width: 0px;" />
			</div>
		</div>

		<!--Step 2-->
		<div id="Step2Container">
			<div class="title">
				<b> 裁切头像照片</b>
			</div>
			<div class="uploadtooltip">
				您可以拖动照片以裁剪满意的头像
			</div>
			<div id="Canvas" class="uploaddiv">
				<div id="ImageDragContainer">
					<img id="ImageDrag" class="imagePhoto"
						src="${app}/<%=filePath%>" style="border-width: 0px;" />
				</div>
				<div id="IconContainer">
					<img id="ImageIcon" class="imagePhoto"
						src="${app}/<%=filePath%>" style="border-width: 0px;" />
				</div>
			</div>
			<div class="uploaddiv">
				<table>
					<tr>
						<td id="Min">
							<img alt="缩小" src="${app}/images/head/_c.gif"
								onmouseover="this.src='${app}/images/head/_c.gif';"
								onmouseout="this.src='${app}/images/head/_h.gif';" id="moresmall"
								class="smallbig" />
						</td>
						<td>
							<div id="bar">
								<div class="child">
								</div>
							</div>
						</td>
						<td id="Max">
							<img alt="放大" src="${app}/images/head/c.gif"
								onmouseover="this.src='${app}/images/head/c.gif';"
								onmouseout="this.src='${app}/images/head/h.gif';" id="morebig"
								class="smallbig" />
						</td>
					</tr>
				</table>
			</div>

			<form action="${app}/kmmanager/kmExpertPhoto.do?method=zoomPhotoDo" method="post">				
				<div class="uploaddiv">
				    <html:hidden property="userId" value="${kmExpertPhotoForm.userId}" />
				    <input type="hidden" name="picture" value="<%=photoName%>" />
					<input type="submit" class="btn" name="btn_Image" value="保存头像" id="btn_Image" />
				</div>
				<div>
                    <input type="hidden" name="txt_width"      id="txt_width"      value="1"  /><!-- 图片实际宽度 -->
                    <input type="hidden" name="txt_height"     id="txt_height"     value="1"  /><!-- 图片实际高度 -->
                    <input type="hidden" name="txt_top"        id="txt_top"        value="82" /><!-- 距离顶部 -->
                    <input type="hidden" name="txt_left"       id="txt_left"       value="73" /><!-- 距离左边 -->
                    <input type="hidden" name="txt_DropWidth"  id="txt_DropWidth"  value="120" /><!-- 截取框的宽 -->
                    <input type="hidden" name="txt_DropHeight" id="txt_DropHeight" value="120" /><!-- 截取框的高 -->
                    <input type="hidden" name="txt_Zoom"       id="txt_Zoom" /><!-- 放大倍数 -->
				</div>
			</form>
		</div>
	</div>
	
	<!-- 界面右半部分 -->
	<form name="kmExpertPhotoForm" id="kmExpertPhotoForm" method="post" action="${app}/kmmanager/kmExpertPhoto.do?method=uploadPhotoDo" enctype="multipart/form-data"  styleId="kmExpertPhotoForm"> 	
		<div class="right">
			<!--Step 1-->
			<div id="Step1Container">
				<div class="title">
				</div>
				<div id="uploadcontainer">
					<div class="uploadtooltip">
					</div>
					<div class="uploaddiv">
					</div>
					<div class="uploaddiv">
					</div>
				</div>

			</div>
		</div>
	</form>
</div>

<% if("".equals(filePath) || "".equals(photoName)) {%>
<script type='text/javascript'>Step1();</script>
<%}else if(!"".equals(filePath)&& "2".equals(step)){%>
<script type='text/javascript'>Step2();</script>
<%}else if(!"".equals(filePath)&& "3".equals(step)){ %>
<script type='text/javascript'>Step3();</script>
<%}%>

<%@ include file="/common/footer_eoms.jsp"%>