<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript" src="${app}/scripts/form/combobox.js"></script>
<script type="text/javascript">
function testc(obj){
	$('testfield').value = obj.value;
}
</script>
 <p>${eoms:a2u("四级联动")}</p>
  <eoms:comboBox name="a1" id="a1" sub="a2" initDicId="10103"/><br/>
  <eoms:comboBox name="a2" id="a2" sub="a3" styleClass="border"/><br/>
  <eoms:comboBox name="a3" id="a3" sub="a4"/><br/>
  <eoms:comboBox name="a4" id="a4"/><br/>

  <br/><p>${eoms:a2u("修改时的写法")}</p>
  <eoms:comboBox name="b1" id="b1" sub="b2" initDicId="1020101" defaultValue="102010103"/><br/>
  <eoms:comboBox name="b2" id="b2" initDicId="102010103" defaultValue="10201010304"/><br/>

  <br/><p>${eoms:a2u("当几个select中有多选的select时，如果此select没有option,由于IE的bug，会报错。所以要添加一个</select>避免此错误")}</p>
  <eoms:comboBox name="c1" id="c1" sub="c2" initDicId="1020101" onchange="javascript:testc(this);"/><br/>
  <eoms:comboBox name="c2" id="c2" styleClass="border" multiple="multiple" sub="c3" /><br/>
  <eoms:comboBox name="c3" id="c3" sub="c4"/><br/>
  <eoms:comboBox name="c4" id="c4"/><br/>
  
  <br/><p>${eoms:a2u("为一个单独的下拉框添加onchange事件")}</p>
  <eoms:comboBox name="d1" id="d1" initDicId="1020101" onchange="javascript:testc(this)"/><br/>
  <input type="text" id="testfield">

<%@ include file="/common/footer_eoms.jsp"%>
