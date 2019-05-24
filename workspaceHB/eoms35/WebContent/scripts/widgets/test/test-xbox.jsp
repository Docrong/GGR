<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext_debug.jsp"%>
<script type="text/javascript" src="../Xbox.js"></script>
<input type="button" value="show" id="btn"/>
<script type="text/javascript">
    var x = new eoms.xbox({
    	dataUrl:'${app}/xtree.do?method=dept',
    	checktype : 'dept',
    	handler: 'btn',
    	single:true
    });
</script>
<%@ include file="/common/footer_eoms.jsp"%>