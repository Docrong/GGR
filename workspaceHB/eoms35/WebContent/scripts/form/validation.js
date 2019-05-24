/**
 * @class eoms.form.Validation
 * @param {Object} config 设置参数对象
 * @cfg {String} form 表单id
 * @description 表单验证类;目前是通过表单域的alt属性设置验证的类型等信息的。
 * @version 0.2.1 080927
 * @example 
 *  给表单"theform"添加验证的初始化：
 *  var v = new eoms.form.Validation({form:"theform"});
 * @example
 *  必填：
 *  &lt;input type="text" alt="allowBlank:false,vtext:'请输入工单主题'"/&gt;
 * @example
 *  下拉框必选：("请选择"选项必须是第一个选项，并且其值为"")
 *  &lt;select alt="allowBlank:false"&gt;&lt;/select&gt;
 * @example
 *  验证类型: 
 *  数字：&lt;input type="text" alt="vtype:'number'"&gt;
 *  邮箱: &lt;input type="text" alt="vtype:'email'"&gt;
 * @example
 *  两个表单项比较:
 *  &lt;input id="time1" type="text" alt="vtype:'lessthen',link:'time2'"&gt;
 *  &lt;input id="time2" type="text" alt="vtype:'morethen',link:'time1'"&gt;
 * @example
 *  页面自定义验证： (注意必须返回true或false,可以用alert显示验证提示信息)
 *  &lt;script type="text/javascript"&gt;
 *   v = new eoms.form.Validation({form:'theform'});
 *   v.custom = function(){ 
 *     if(eoms.$('time1').value.trim()==""){
 *       alert("回复时限不能为空"); return false; 
 *     } 
 *     return true;
 *   }
 *   &lt;/script&gt;
 * @example
 *  直接提交不验证：(此时提交只验证maxLength)
 *  &lt;input type="submit" value="保存草稿" onclick="v.passing=true"&gt;
 * @example
 *  异步后台验证:
 *  v.preCommitUrl = "${app}/sheet/netdata/netdata.do?method=performPreCommit";
 *  设置preCommitUrl后,在提交前会先将表单数据提交到这个url进行验证,
 *  这个url需要返回JSON格式的验证结果，该JSON格式为：
 *  {
 *    status:1, 	//状态标识: 0=通过验证直接提交; 1=弹出后台信息等待用户确认,用户确认后(执行fn)提交; 2=弹出提示,不提交
 *    data :[ 	//多组信息返回一个数组
 *      {
 *        text:"您确认提交给无线子角色吗"	//等待用户确认的提示文字 
 *        fn：function(){document.getElementById("ss").value="";}//status=1时,用户确认后需执行的javascript
 *      },
 *      ...
 *    ]
 *  }
 */
eoms.form.Validation = function(config) {
	eoms.apply(this, config);
	this.vAttr = 'alt';
	this.preCommitQueried = false; // 是否请求过preCommit后台
	this.form = $(this.form);
	this.init();
	this.form.on('submit', this.onSubmit, this);
	this.passing = false; // 设置为true时，本次提交不验证直接提交
}

eoms.form.Validation.prototype = {
	//private 是否是需要验证的表单项
	isVElement : function(el) {
		if (el.disabled) {
			return false;
		}
		if (!el[this.vAttr] && !el.getAttribute(this.vAttr)) {
			return false;
		} else {
			var config = this.getConfig(el);
			if (config.allowBlank == false || config.vtype || config.maxLength) {
				return true;
			}
			return false;
		}
	},
	//private 初始化验证状态，所有含有alt属性，并且不是disabled的元素将被绑定事件
	init : function() {
		for (var i = 0; i < this.form.elements.length; i++) {
			var el = this.form.elements[i];
			if (!this.isVElement(el))
				continue;
			$(el).on('blur', this.validate, this);
		}
	},
	/**
	 * 验证所有元素，并提示验证信息
	 * @return {Boolean}
	 * @example
	 *  var v = new eoms.form.Validation({form:"theform"});
	 *  v.check();
	 */
	check : function() {
		var firstInvalid = null, valid = true;
		var els = this.form.elements;
		var fLength = els.length;
		for (var i = 0; i < fLength; i++) {
			var el = els[i];
			if (!this.isVElement(el))
				continue;
			// $(el).on('blur',this.validate,this);
			if (!this.validate(el)) {
				if (valid) {
					firstInvalid = el;
				}
				valid = false;
			}
		}
		if (!valid) {
			this.alertInvalid(firstInvalid);
		}

		// 页面自定义验证
		if (valid && typeof this.custom == "function") {
			if (!this.custom()) {
				valid = false;
			}
		}

		return valid;
	},
	//private 
	onSubmit : function(form, event) {
		if(Ext.get(this.form)._maskMsg) Ext.get(this.form)._maskMsg = null; // fix ajax load form bug
		Ext.get(this.form).mask('数据验证中，请稍候...');
		if (!this.check()) {
			Ext.get(this.form).unmask();
			eoms.stopEvent(event);
		} else {
			if (this.preCommitUrl && !this.preCommitQueried && !this.passing) { // 显示后台确认信息
				eoms.stopEvent(event);
				var data = Ext.lib.Ajax.serializeForm(this.form);
				Ext.Ajax.request({
					method : 'post',
					url : this.preCommitUrl,
					success : this.handleResponse,
					failure : this.handleFailure,
					scope : this,
					params : data
				});
			}
		}
		this.passing = false;
	},
	//private
	//处理后台返回信息，格式为JSON JSON字段： status: 状态标识 0 为通过验证直接提交 1 为等待用户确认 2 为不提交
	//text:等待用户确认的提示文字 fn:需执行的javascript
	handleResponse : function(x) {
		var o = eoms.JSONDecode(x.responseText);

		if (o.status == 0) {
			this.preCommitQueried = true;
			this.form.submit();
		}

		var arrText = [];
		o.data.each(function(item) {
			arrText.push(item.text);
		});

		if (o.status == 1) {
			if (window.confirm(arrText.join("\n\n"))) {
				o.data.each(function(item) {
					if (typeof item.fn == "function") {
						item.fn();
					}
				});
				this.preCommitQueried = true;
				this.form.submit();
			} else {
				Ext.get(this.form).unmask();
			}
		}
		if (o.status == 2) {
			alert(arrText.join("\n\n"));
			Ext.get(this.form).unmask();
		}
	},
	handleFailure : function() {
		this.preCommitQueried = true;
		eoms.log("handle fail");
		this.form.submit();
	},
	/**
	 * 验证某个表单项
	 * @param {HTMLElement} el 表单项
	 * @return {Boolean}
	 * @example
	 *  var v = new eoms.form.Validation({form:"theform"});
	 *  v.validate(document.forms[0].title);
	 */
	validate : function(el) {
		var config = this.getConfig(el);
		if (typeof config != 'object') {
			return false;
		}
		if (el.disabled || this.validateValue(el.value, el, config)) {
			this.clearInvalid(el);
			return true;
		}
		return false;
	},
	//private 验证表单项的值
	validateValue : function(value, el, config) {
		var blankText = "请输入内容";
		if (config.allowBlank === false && !this.passing) {
			if (el.tagName == 'SELECT') {
				blankText = "请至少选择一项";
			}
			if (el.type == 'radio' || el.type == 'checkbox') {

			}
			if (el.value.trim() == "") {
				this.markInvalid(el, blankText);
				return false;
			}
		}
		if (config.vtype && !this.passing) {
			var vt = eoms.form.VTypes;
			if (!vt[config.vtype](value, el, config)) {
				this.markInvalid(el, config.vtext || vt[config.vtype + 'Text']);
				return false;
			}
		}
		if (config.maxLength && parseInt(config.maxLength) > 0
				&& (el.tagName == 'INPUT' || el.tagName == 'TEXTAREA')) {

			var vLen = el.value.length, mLen = parseInt(config.maxLength), tLen = 0;
			for (var i = 0; i < vLen; i++) {
				var intCode = el.value.charCodeAt(i);
				if (intCode >= 0 && intCode <= 128) {
					tLen += 1; // 非中文单个字符长度加 1
				} else {
					tLen += 2; // 中文字符长度则加 2
				}
				if (tLen > mLen) {
					this.markInvalid(el, "字段最多可输入" + mLen + "个英文字符,或"
							+ Math.floor(mLen / 2) + "个中文字符,请减少该字段的字符数量");
					return false;
				}
			}
		}
		return true;
	},
	//private 提示验证信息,第一个未通过验证的表单项将获得焦点
	alertInvalid : function(el) {
		var config = this.getConfig(el);
		var info = config.vtext || config.blankText || el.invalidText || '请检查表单项';
		info += "\n\n...\n表单至少有1项不符合要求，请检查表单项。"
		alert(info);
		Ext.get(this.form).unmask();
		setTimeout(function() {
			try {
				el.focus();
			} catch (e) {
			}
		}, 50);
	},
	/**
	 * 将表单项标记为未通过
	 * @param {HTMLElement} el 表单项
	 * @param {String} text 提示信息
	 * @example
	 *  var v = new eoms.form.Validation({form:"theform"});
	 *  v.markInvalid(document.forms[0].title,"请输入工单主题");
	 */
	markInvalid : function(el, text) {
		if (eoms.isIE && el.tagName == "SELECT") {
			$(el.options[0]).addClass('invalid');
		}
		$(el).addClass('invalid');
		$(el).title = el.invalidText = text;
	},
	/**
	 * 清除表单项的未通过标记
	 * @param {HTMLElement} el 表单项
	 * @example
	 *  var v = new eoms.form.Validation({form:"theform"});
	 *  v.clearInvalid(document.forms[0].title);
	 */
	clearInvalid : function(el) {
		if (eoms.isIE && el.tagName == "SELECT") {
			$(el.options[0]).removeClass('invalid');
		} else {
			$(el).removeClass('invalid');
		}
		$(el).title = '';
	},
	//private 获取表单项上的验证设置，一般是在alt属性中设置
	getConfig : function(el) {
		var cfg = el[this.vAttr] || el.getAttribute(this.vAttr);
		try {
			return eval('({' + cfg + '})');
		} catch (e) {
			return {};
		}
	}
};

/**
 * 表单项验证类型，要和eoms.form.Validation类配合使用
 * @ignore 
 */
eoms.form.VTypes = function() {
	var number = /^[0-9]*$/, integer = /^-?[0-9]*$/;
	var email = /^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/;
	
	/** @scope eoms.form.VTypes */
	return {
		'number' : function(v) {
			return number.test(v);
		},
		/**
		 * 数字类型验证提示
		 * @type {String}
		 */
		'numberText' : '请输入数字',
		/**
		 * 整数验证
		 */
		'integer' : function(v) {
			return integer.test(v);
		},
		'integerText' : '请输入整数',
		
		/**
		 * 验证是否大于通过link关联的表单项的值
		 * @type {Boolean}
		 */
		'moreThen' : function(value, el, config) {
			if (!config.link) {
				return true;
			};
			var linkEl = $(config.link);
			if(!linkEl || value.trim()=="" || linkEl.value.trim()==""){
				return true;
			}
			eoms.form.Validation.prototype.validate(linkEl);
			return value > linkEl.value;
		},
		/**
		 * 较大比较的验证提示
		 * @type {Stirng}
		 */
		'moreThenText' : '',
		/**
		 * 验证是否小于通过link关联的表单项的值
		 * @type {Boolean}
		 */
		'lessThen' : function(value, el, config) {
			if (!config.link) {
				return true
			};
			var linkEl = $(config.link);
			if(!linkEl || value.trim()=="" || linkEl.value.trim()==""){
				return true;
			}
			eoms.form.Validation.prototype.validate(linkEl);
			return value < linkEl.value;
		},
		/**
		 * 较小比较的验证提示
		 * @type {Stirng}
		 */
		'lessThenText' : '',
		/**
		 * 验证是否为email地址
		 * @type {Boolean}
		 */
		'email' : function(v) {
			return email.test(v);
		},
		/**
		 * 邮件类型的验证提示
		 * @type {Stirng}
		 */
		'emailText' : '请输入邮件地址'
	}
}();
