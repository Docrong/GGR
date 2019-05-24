<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<style type="text/css">
#calendar{
  margin:10px;
  width:200px
}
</style>
<script type="text/javascript" src="${app}/scripts/layout/FrameLayout.js"></script>

<div id="headerPanel" class="x-layout-inactive-content">
  <h1><bean:message key='schedule'/></h1>
</div>

<div id="westPanel" class="x-layout-inactive-content">
  <div id="westPanel-tb" class="tb"></div>
    <div id="westPanel-body">
      <div id="calendar"></div>
      <div><bean:message key='help'/><br><bean:message key='helpcontent'/></div>
    </div>
 </div>
	
<iframe id="centerPanel" name="centerPanel" frameborder="no" class="x-layout-inactive-content"></iframe>

<script type="text/javascript">
    var layout, c, cp,cpp;
    Ext.onReady(function(){
    	var layoutCfg = {
    		west:{
    			titlebar : false,
    			initialSize : 220
    		}
    	};
    	layout = new eoms.layout.FrameLayout(layoutCfg);
    	cp = layout.getLayout();
    	cpp = cp.regions.center.activePanel;

    	var calendarCfg = {
			keyFormat : 'Y-m-d',
	  		getKey : function(/*Date*/date){
				var d = date || this.value;
				return d.format(this.keyFormat);
	  		},
	  		handler : function(){
	    		window.frames['centerPanel'].location.href = "networkcalendarmain.do?method=networkcalendarList&date="+this.getKey();
	    		cpp.setTitle("${eoms:a2u('日期')}"+this.getKey()+"${eoms:a2u('的日程安排:')}");  		
	  		}
		};
    	c = new Ext.DatePicker(calendarCfg);
    	c.render('calendar');
    	c.handler();
    });
    
</script>

<%@ include file="/common/footer_eoms.jsp"%>