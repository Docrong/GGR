<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<html>
<head>
  <meta http-equiv="Cache-Control" content="no-store"/>
  <meta http-equiv="Pragma" content="no-cache"/>
  <meta http-equiv="Expires" content="0"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <script type="text/javascript" charset="utf-8" src="../../scripts/local/zh_CN.js"></script>  
  <script type="text/javascript" charset="utf-8" src="../../scripts/base/eoms.js"></script>
   <script type="text/javascript">
	eoms.appPath = "../..";
  </script>
  <link rel="stylesheet" type="text/css" media="all" href="../../styles/default/theme.css" />
</head>
<c:if test="${gprsspeciallineList != null}">
<fieldset>
<legend>Gprs专线列表</legend>
<logic:present name="gprsspeciallineList" scope="request">  
 <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
             <thead>
  				<tr>
  				  <td nowrap width="100">详细信息</td>
  				  <td nowrap width="100">A点经度</td>
                  <td nowrap width="100">A点纬度</td>
                  <td nowrap width="100">GPRS核心网详细地址</td>
  				  <td nowrap width="100">GPRS核心网联系人</td>
                  <td nowrap width="100">GPRS核心网联系人电话</td>
                  <td nowrap width="100">GPRS核心网经度</td>
                  <td nowrap width="100">GPRS核心网纬度</td>
                  <td nowrap width="100">业务带宽</td>
                  <td nowrap width="100">业务数量(传输条数)</td>
                  <td nowrap width="100">连接方式</td>
                  <td nowrap width="100"> 隧道方式</td>
                </tr>
              </thead>
      <logic:iterate id="gprsspecialline" name="gprsspeciallineList" type="com.boco.eoms.businessupport.product.model.GprsSpecialLine"> 
           <tr>
                  <td  class="content">
                     <a href="${app}/businessupport/product/gprsspeciallines.do?method=showDetail&id=${gprsspecialline.id}" target="_blank">详细信息</a>
                  </td>
                  <td  class="content"><bean:write name="gprsspecialline" property="longitudeA" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="latitudeA" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="detailAddGPRS" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="contactUserGPRS" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="contactPhoneGPRS" scope="page"/></td>
                  <td  class="content"><bean:write name="gprsspecialline" property="longitudeGPRS" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="latitudeGPRS" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="businessBandwidth" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="businessAmount" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="connectType" scope="page"/></td>
  				  <td  class="content"><bean:write name="gprsspecialline" property="tunnelType" scope="page"/></td>
                </tr>
      </logic:iterate>
         </table>
</logic:present>
</fieldset>
</c:if>
<c:if test="${ipspeciallineList != null}">
 <fieldset>
<legend>IP数据专线列表</legend>
<logic:present name="ipspeciallineList" scope="request">  
 <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
             <thead>
  				<tr>
  				  <td nowrap width="100">详细信息</td>
  				  <td nowrap width="100">业务带宽</td>
                  <td nowrap width="100">业务数量(传输条数)</td>
                  <td nowrap width="100">IP地址数量 </td>
  				  <td nowrap width="100">接入端详细地址</td>
                  <td nowrap width="100">IP地址1（账号）</td>
                  <td nowrap width="100">IP地址2（账号</td>
                  <td nowrap width="100">子网掩码</td>
                  <td nowrap width="100">网关</td>
                  <td nowrap width="100">网络部联系人</td>
                  <td nowrap width="100">网络部门联系人电话</td>
                </tr>
              </thead>
      <logic:iterate id="ipspecialline" name="ipspeciallineList" type="com.boco.eoms.businessupport.product.model.IPSpecialLine"> 
           <tr>
                  <td  class="content">
                     <a href="${app}/businessupport/product/ipspeciallines.do?method=showDetail&id=${ipspecialline.id}" target="_blank">详细信息</a>
                  </td>
                  <td  class="content"><bean:write name="ipspecialline" property="businessBandwidth" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="businessAmount" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="ipAddressCount" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="RCAPDetailAddress" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="ip1" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="ip2" scope="page"/></td>
                  <td  class="content"><bean:write name="ipspecialline" property="cnetCode" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="gateway" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="portANetDeptUser" scope="page"/></td>
  				  <td  class="content"><bean:write name="ipspecialline" property="portANetDeptPhone" scope="page"/></td>
                </tr>
      </logic:iterate>
         </table>
</logic:present>
</fieldset>
</c:if>
<c:if test="${transferspeciallineList != null}">
<fieldset>
<legend>传输专线列表</legend>
<logic:present name="transferspeciallineList" scope="request">  
 <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
             <thead>
  				<tr>
  				  <td nowrap width="100">详细信息</td>
  				  <td nowrap width="100">城市A</td>
                  <td nowrap width="100">城市Z</td>
                  <td nowrap width="100">带宽</td>
  				  <td nowrap width="100">数量</td>
                  <td nowrap width="100">端点A</td>
                  <td nowrap width="100">端点A接口类型</td>
                  <td nowrap width="100">端点A详细地址</td>
                  <td nowrap width="100">端点A业务设备名称</td>
                  <td nowrap width="100">端点A业务设备端口</td>
                  <td nowrap width="100">端点A联系人及电话</td>
                  <td nowrap width="100">端点Z</td>
                  <td nowrap width="100">端点Z接口类型</td>
                  <td nowrap width="100">端点Z详细地址</td>
                  <td nowrap width="100">端点Z业务设备名称</td>
                  <td nowrap width="100">端点Z业务设备端口</td>
                  <td nowrap width="100">端点Z联系人及电话</td>
                  <td nowrap width="100">是否预占</td>
                </tr>
              </thead>
      <logic:iterate id="transferspecialline" name="transferspeciallineList" type="com.boco.eoms.businessupport.product.model.TransferSpecialLine"> 
           <tr>
                  <td  class="content">
                     <a href="${app}/businessupport/product/transferspeciallines.do?method=showDetail&id=${transferspecialline.id}" target="_blank">详细信息</a>
                  </td>
                  <td  class="content"><bean:write name="transferspecialline" property="cityA" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="cityZ" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="bandwidth" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="amount" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portA" scope="page"/></td>
                  <td  class="content"><bean:write name="transferspecialline" property="portAInterfaceType" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portADetailAdd" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portABDeviceName" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portABDevicePort" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portAContactPhone" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portZ" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portZInterfaceType" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portZDetailAdd" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portZBDeviceName" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portZBDevicePort" scope="page"/></td>
  				  <td  class="content"><bean:write name="transferspecialline" property="portZContactPhone" scope="page"/></td>
                </tr>
      </logic:iterate>
         </table>
</logic:present>
</fieldset>
</c:if>
<c:if test="${ipspeciallineList == null && transferspeciallineList == null && gprsspeciallineList == null }">
根据您选择的查询条件没有查询出结果！
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>