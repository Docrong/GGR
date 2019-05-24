<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<link rel="stylesheet" type="text/css" href="${app}/styles/default/lunar-calendar.css"></link>
<script type="text/javascript" src="../../util/Lunar.js"></script>
<script type="text/javascript" src="../LunarCalendar.js"></script>

<div id="calendar"></div>

<script type="text/javascript">
    new eoms.LunarCalendar("calendar");
</script>
<%@ include file="/common/footer_eoms.jsp"%>