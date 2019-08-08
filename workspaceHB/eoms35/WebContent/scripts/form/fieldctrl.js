/**
 *
 * page code sample
 fieldCtrlConfig ={
	handler:"issendimediat",
	fields:["issendnight","messagetype","urgency"],
	rules:[
		{value:"110301",dis:["issendnight","messagetype"]},
		{value:"110302",dis:["urgency"]}
	]
};
 */

var fieldCtrlConfig;

function fieldCtl() {
    if (fieldCtrlConfig == null || fieldCtrlConfig == "undefined") return;
    var c = fieldCtrlConfig;
    var h = $(c.handler);
    var hv = h.value;
    var rules = c.rules;
    var fields = c.fields;
    var disFlds = new Array();
    var undisFlds = new Array();
    for (i = 0; i < rules.length; i++) {
        disFlds[rules[i].value] = rules[i].dis;
        undisFlds[rules[i].value] = rules[i].undis;
    }
    toggleFields(fields, disFlds[hv], undisFlds[hv]);

    h.onchange = function () {
        toggleFields(fields, disFlds[h.value], undisFlds[h.value]);
    };
}

function toggleFields(fields, arrDis, arrUndis) {
    fields = fields || [];
    arrDis = arrDis || [];
    arrUndis = arrUndis || [];
    //console.log(fields);
    //console.log(arrDis);
    //console.log(arrUndis);
    fields.each(
        function (id) {
            if (arrDis.indexOf(id) > -1) {
                $(id).disabled = true;
            } else if (arrUndis.indexOf(id) > -1) {
                $(id).disabled = false;
            } else {
                $(id).disabled = false;
            }
        }
    );
}

Event.observe(window, 'load', fieldCtl, false);