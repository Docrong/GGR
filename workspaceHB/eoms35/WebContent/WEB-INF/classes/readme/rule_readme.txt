规则使用说明：

优点：

1、规则规范业务流程，且现有规则已经实现不仅使用在流程平台上且可以其他模块，如：作业计划。仅需配置。
2、支持xml中配置表达式，工程人员将输入/输出参数使用表达式配置进行赋值（不需书写任何代码），故而影响流程的流向。
3、支持xml中配置规则分组，并将规则赋予优先级，规则会按照规则分组优先级执行规则，直到满足规则条件退出。
4、支持除表达式复杂的业务逻辑，工程人员可以开发java代码实现业务方法，在xml中简单配置即可以实现复杂业务逻辑。
5、支持listener，在调用规则之前、之后都会触发before(),after()方法。工程人员可按业务编写多个lisener，使lisener有效只需要简单xml配置。
6、支持输入输出参数的验证，根据二次开发人员的xml配置，按输入输出参数配置类型进行验证，若输入/输出参数不符合业务要求则抛出异常。

发展：
1、xml的web页面配置，提供eclipse plugin配置，将需要手动xml配置的地方使用web,eclipse plugin的方式实现工具配置，
其中可实现，若需要判断值，如执行类型，工程人员在配置xml时每次要查数据库对应找出值配上，而现在只要求二次二发人员提供取数据库表的方法，
规则配置工具会将表中的内存如：1,草稿;2,转派;这些内容以下拉列表方式供工程人员选对，以节省工程人员的麻烦。
2、支持drools。
3、与使用规则的组件更有机的结果，如流程平台、作业计划。

依赖关系：
1、文件配置
2、castor
3、ongl

使用：
规则关心的是输入/输出参数，也即将输入参数以java.util.Map传入，通过key="参数名称",value="参数类型"
根据传入的参数组合条件，将输出参数以java.util.Map类型返回，也通过key="参数名称",value="参数类型"

以com.boco.eoms.commons.rule.test.service.RuleServiceFacadeTest.java 为例测试自定义java规则及规则分组调用

为说明例子，我编写了com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample.java及同包下的Rule1InputParameter2Sample.java
做为输入参数

com.boco.eoms.commons.rule.Rule1OutputParameter1Sample.java及同包下Rule1OutputParameter2Sample.java做为输出参数

com.boco.eoms.commons.rule.Rule1Sample.java及com.boco.eoms.commons.rule.Rule2Sample.java为例说明自定义java规则及规则分组

com.boco.eoms.commons.rule.sample.Rule1ListenerSample.java为自定义listener

com.boco.eoms.commons.rule.sample.RuleSample.xml配置规则，先介绍轮廓，之后会详细介绍

rule的说明是个比较不好说明的东西，所以有些枯燥，:-)

配置文件如下说明：

<?xml version="1.0" encoding="UTF-8"?>
<ruleEngine>
	
	<groups>
		<!--规则分组（路由）-->
		<group id="group1">
			<!-- 引用Rule2Sample，Rule1Sample两个规则，优先级为2，优先级别以-1...正无穷递增，即-1为最低优先级，默认规则优先级为-1 -->
			<groupRef ruleId="Rule2Sample" pri="2" />
			<groupRef ruleId="Rule1Sample" pri="1" />
		</group>
	</groups>
	<!-- 表达式样式 ,使用自定义java规则不需定义，先不用观注-->
	<expStyles>
		<expStyle id="rule1Express"
			style=" ( $parameter != $l{springbean.getNames} || false ) &amp;&amp; $text ? $r{springbean.getName} : 3 " />
	</expStyles>
	<rules>
		<!-- 规则 className为规则包+类名 ，id为唯一标识，记好了，将来调用时要用RuleId-->
		<rule className="com.boco.eoms.commons.rule.sample.Rule1Sample"
			id="Rule1Sample">
			<!-- 输入参数定义 ，由使用规则组件的开发人员定义,rule1InputParameter1Sample为key，-->
			<!--类型为com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample，这里要定义正确呀，否则验证不通过，也不通路由的~-->
			<input>
				<parameter name="rule1InputParameter1Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample" />
				<parameter name="rule1InputParameter2Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample" />
			</input>
			<!-- 输出参数,expression为表达式，暂不关注，name及type和输入参数一样，drl是支持drools的预留接口，expStyleId表达式样式ID，暂不关心-->
			<output>
				<parameter
					expression=""
					name="rule1OutputParameter1Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample"
					drl=""
					expStyleId="rule1Express" />

				<parameter
					expression=""
					name="rule1OutputParameter2Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample"
					drl=""
					expStyleId="rule1Express" />
			</output>
			<!--自定义listener，Rule1ListenerSample为自定义,RuleCheckListener为系统提供验证listener验证，若不需要验证将RuleCheckListener去掉即可（不推荐）-->
			<listeners>
				<listener
					name="com.boco.eoms.commons.rule.sample.Rule1ListenerSample" />
				<listener
					name="com.boco.eoms.commons.rule.listener.RuleCheckListener" />
			</listeners>
		</rule>

		<rule className="com.boco.eoms.commons.rule.sample.Rule2Sample"
			id="Rule2Sample">
			<input>
				<parameter name="rule1InputParameter1Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample" />
				<parameter name="rule1InputParameter2Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample" />
			</input>
			<output>
				<parameter
					expression=" ( ${rule1InputParameter1Sample.name} != 'value3' || false) &amp;&amp; true ? $b{springbean.getName} : 3 "
					name="rule1OutputParameter1Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample"
					drl="classpath:com.boco.eoms.commons.rule.sample.rule1Parameter1Sample.drl"
					expStyleId="rule1Express" />

				<parameter
					expression=" rule1InputParameter1Sample.name != '234' &amp;&amp; rule1InputParameter1Sample.age >10 ? rule1InputParameter1Sample.name : rule1InputParameter1Sample.age "
					name="rule1OutputParameter2Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample"
					drl="classpath:com.boco.eoms.commons.rule.sample.rule1Parameter2Sample.drl"
					expStyleId="rule1Express" />
			</output>
			<listeners>
			<listener
					name="com.boco.eoms.commons.rule.sample.Rule1ListenerSample" />
				<listener
					name="com.boco.eoms.commons.rule.listener.RuleCheckListener" />
			</listeners>
		</rule>
	</rules>
</ruleEngine>


OK,现在我们看看自定义java写了什么


public class Rule1Sample extends RuleService {

	/**
	 * @param ruleListeners
	 * @param rules
	 * @param rule
	 * @param xmlPath
	 */
	public Rule1Sample(List ruleListeners, RuleEngine rules, Rule rule,
			String xmlPath) {
		super(ruleListeners, rules, rule, xmlPath);
	}

	/**
	 * 二次开发人员需实现的业务接口
	 * 
	 * @param map
	 *            根据配置文件中input的配置，二次开发人员取出配置的参数，
	 *            如sample配置key="rule1InputParameter1Sample",value="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample"
	 *            key="rule1Parameter2InputSample",value="com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample"
	 *            这时二次发人员在execute中按这种方式取参数
	 *            com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample
	 *            param1=(com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample)
	 *            map.get("rule1InputParameter1Sample");
	 *            com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample
	 *            param2=(com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample)
	 *            map.get("rule1InputParameter2Sample");
	 * 
	 * @return 根据配置文件output的配置，二次开发人员需返回配置的参数 如sample配置
	 *         key="rule1OutputParameter1Sample",value="com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample"
	 *         key="rule1OutputParameter2Sample",value="com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample"
	 *         配置人员需按业务要求，返回
	 *         map.put("rule1OutputParameter1Sample",com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample);
	 *         map.put("rule1OutputParameter2Sample",com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample);
	 *         return map;
	 * @throws RuleException
	 */
	public Map execute(Map map,Rule rule) throws RuleException {
		// 规则业务
		// 取输入参数
		Rule1InputParameter1Sample inputParam1 = (Rule1InputParameter1Sample) map
				.get("rule1InputParameter1Sample");
		Rule1InputParameter2Sample inputParam2 = (Rule1InputParameter2Sample) map
				.get("rule1InputParameter2Sample");

		Map outMap = new HashMap();
		Rule1OutputParameter1Sample outParam1 = new Rule1OutputParameter1Sample();
		Rule1OutputParameter2Sample outParam2 = new Rule1OutputParameter2Sample();
		// 模拟规则,年龄大于10数，姓名不为匿名，则outParam1设为可通过
		if (inputParam1.getAge() > 10
				&& !"anonym".equals(inputParam1.getName())) {
			// 为说明业务逻辑所以将setName放入判断条件
			outParam1.setName(inputParam1.getName());
			outParam1.setOk(true);
		} else {
			// 为说明业务逻辑所以将setName放入判断条件
			outParam1.setName(inputParam1.getName());
			outParam1.setOk(false);
		}
		// 性别为男则通过
		if ("male".equals(inputParam2.getSex())) {
			outParam2.setSex("male");
			outParam2.setOk(true);
		} else {
			outParam2.setSex("male");
			outParam2.setOk(false);
		}
		outMap.put("rule1OutputParameter1Sample", outParam1);
		outMap.put("rule1OutputParameter2Sample", outParam2);
		// 返回输出参数
		return outMap;
	}

}

以Rule1Sample.java为例，需继承RuleService并实现execute方法，业务逻辑写在该方法内，如Rule1Sample.java

按照配置的输入/输出参数进行操作。

同时开发人员需要 像RuleServiceFacadeTest这样调用，按注释调用，请看注释




public class RuleServiceFacadeTest extends TestCase {
	private RuleServiceFacade facade;

	private Map<String, Object> inputMap;

	private Rule1InputParameter1Sample inputParam1;

	private Rule1InputParameter2Sample inputParam2;

	private String xmlPath = "";

	private String groupId = "";

	/**
	 * 输入参数初始化
	 */
	protected void setUp() throws Exception {
		facade = RuleServiceFacade.create();
		// 测试输入参数类弄与xml是否匹配
		inputMap = new HashMap<String, Object>();
		inputParam1 = new Rule1InputParameter1Sample();
		inputParam2 = new Rule1InputParameter2Sample();
		inputParam1.setAge(10);
		inputParam1.setName("qjb");
		inputParam2.setSex("male");

		inputMap.put("rule1InputParameter1Sample", inputParam1);
		inputMap.put("rule1InputParameter2Sample", inputParam2);
		xmlPath = "classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml";
		groupId = "group1";
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * 以classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml为配置文件，调用rule1Sample为ruleId，以inputMap为输入参数，
	 * 
	 */
	public void testInvokeRuleService() {
		try {
			facade.invokeRuleService(xmlPath, "Rule1Sample", inputMap);
		} catch (RuleException e) {
			fail();
		}
	}

	/**
	 * 以规则分组（路由）方式调用，调用groupId即group1，按照xml配置是调用了两个rule，按照优先级（数字由大到小）先后调用
	 * 以不同输入参数调用
	 */
	public void testInvokeRuleGroupForDiffInputMap() {

		Map<String, Map> map = new HashMap<String, Map>();
		Map outMap = null;
		// 以不同输入参数调用
		map.put("Rule1Sample", inputMap);
		map.put("Rule2Sample", inputMap);
		try {
			outMap = facade.invokeRuleGroupForDiffInputMap(xmlPath, groupId,
					map);

		} catch (RuleException e) {
			fail();
		}
		checkOutMap(outMap);
	}

	/**
	 * 以规则分组（路由）方式调用，调用groupId即group1，按照xml配置是调用了两个rule，按照优先级（数字由大到小）先后调用
	 * 以相同输入参数调用
	 * 
	 */
	public void testInvokeRuleGroupForSampeInputMap() {
		Map outMap = null;
		try {
			outMap = facade.invokeRuleGroupForSampeInputMap(xmlPath, groupId,
					inputMap);
		} catch (RuleException e) {
			e.printStackTrace();
			fail();
		}
		checkOutMap(outMap);
	}

	/**
	 * 验证输出参数，用于测试
	 * 
	 * @param outMap
	 */
	private void checkOutMap(Map outMap) {
		Rule1OutputParameter2Sample outPram2 = (Rule1OutputParameter2Sample) outMap
				.get("rule1OutputParameter2Sample");
		assertEquals(outPram2.getSex(), "male");
		assertEquals(outPram2.isOk(), true);
	}
}

这时再看自定义的listener

com.boco.eoms.commons.rule.sample.Rule1ListenerSample.java



public class Rule1ListenerSample implements IRuleListener {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 执行规则后调用
	 */
	public void after(Map inputMap, Rule rule) throws RuleException {
		logger.debug(rule.getId() + " after");

	}

	/**
	 * 执行规则前调用
	 */
	public void before(Map outputMap, Rule rule) throws RuleException {
		logger.debug(rule.getId() + " before");
	}
}

需要实现IRuleListener.java的after及before方法

ok，运行com.boco.eoms.commons.rule.RuleServiceFacadeTest.java测试用例。

晕了吧~，重新看下，OK，以上内容为自定义调用，下面说说表达式方式调用







以com.boco.eoms.commons.rule.test.service.ExpressionRuleServiceTest.java 为例测试表达式的规则


按com.boco.eoms.commons.rule.sample.ExpressionRuleSample.xml中配置

<?xml version="1.0" encoding="UTF-8"?>
<ruleEngine>
	<!-- 表达式样式，用于规则工具定义，暂不关心 -->
	<expStyles>
		<expStyle id="rule1Express"
			style=" ( $parameter != $l{springbean.getNames} || false ) &amp;&amp; $text ? $r{springbean.getName} : 3 " />
	</expStyles>
	<rules>
		<!-- 这里一定要配className="com.boco.eoms.commons.rule.service.ExpressionRuleService"这个类，表达式类-->
		<rule
			className="com.boco.eoms.commons.rule.service.ExpressionRuleService"
			id="ExpressionRule">
			<!-- 输入参数定义 ，由使用规则组件的开发人员定义,rule1InputParameter1Sample为key，-->
			<!--类型为com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample，这里要定义正确呀，否则验证不通过，也不通路由的~-->
			<input>
				<parameter name="rule1InputParameter1Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample" />
				<parameter name="rule1InputParameter2Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample" />
			</input>
			<!-- 输出参数,expression为表达式，暂不关注，name及type和输入参数一样，drl是支持drools的预留接口，expStyleId表达式样式ID，暂不关心-->
			<output>
				<!--expression 表达式 rule1InputParameter1Sample.age指com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample中的getAge()方法。 -->
				<!-- 其中&amp;&amp;在读入程序时，将用&&（与）替换， -->
				<!-- 解释一下,其中要使用输入参数必须要以"#"号开头
				rule1InputParameter1Sample 及rule2InputParameter1Sample从map中取出（这里开发人员不必关心，由规则帮你处理)
				
				if(rule1InputParameter1Sample.getAge()>5 && "qjb".equals(rule1InputParameter1Sample.getName()){
					return rule1InputParameter1Sample.getResult();
				}
				else{
					return rule1InputParameter1Sample.getStr(rule1InputParameter1Sample.getName());
				}
				
				规则容器将返回值写入输出map中，以name=rule1OutputParameter1Sample为key值,value即为返回值
				-->
				<parameter
					expression="#rule1InputParameter1Sample.age>5 &amp;&amp; #rule1InputParameter1Sample.name=='qjb'?#rule1InputParameter1Sample.result:#rule1InputParameter1Sample.getStr(#rule1InputParameter1Sample.name)"
					name="rule1OutputParameter1Sample"
					type="java.lang.String"
					drl="" expStyleId="" />


				<!-- 再来解释下第二个输出参数表达式
					
					if(rule1InputParameter1Sample.getAge()>5)){
						rule1InputParameter1Sample.setAge(5);
					}
					else{
						rule1InputParameter1Sample.setAge(6);
					}
					return rule1InputParameter1Sample;
				-->
				<parameter
					expression="#rule1InputParameter1Sample.age>5?#rule1InputParameter1Sample.setAge(5):#rule1InputParameter1Sample.setAge(6),#rule1InputParameter1Sample"
					name="rule1OutputParameter2Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample"
					drl="" expStyleId="" />
			</output>
			<!--listener第一个例子，不需要表达式配置功能一样-->
			<listeners>
				<listener
					name="com.boco.eoms.commons.rule.sample.Rule1ListenerSample" />
				<listener
					name="com.boco.eoms.commons.rule.listener.RuleCheckListener" />
			</listeners>
		</rule>
	</rules>
</ruleEngine>

运行com.boco.eoms.commons.rule.test.service.ExpressionRuleServiceTest.java测试用例

仔细看下测试用例，应该很好理解，

恭喜，恭喜，熟练掌握rule了吧？:-)



痛苦的再写下去，再说说rule的扩展吧~

基于rule去扩展是很简单的~

举个例子，其实表达式的支持，就是我扩展的一个类，看下
com.boco.eoms.commons.rule.service.ExpressionRuleService.java

public class ExpressionRuleService extends RuleService {

	@Override
	protected Map<String, Object> execute(Map<String, Object> inputMap,
			Rule rule) throws RuleException {
		// 创建表达式解析service
		OgnlExpressionService oes = OgnlExpressionService.create(null);
		Map<String, Object> outMap = new HashMap<String, Object>();
		// 取输出参数
		for (Iterator it = rule.getOutput().getParameters().iterator(); it
				.hasNext();) {

			Parameter para = (Parameter) it.next();
			// 将输出参数配置的表达式结果按照配置名称写入outMap
			outMap.put(para.getName(), oes.getValue(para.getExpression(),
					inputMap));

		}

		return outMap;
	}

	/**
	 * @param ruleListeners
	 * @param rules
	 * @param rule
	 * @param xmlPath
	 */
	public ExpressionRuleService(List ruleListeners, RuleEngine rules,
			Rule rule, String xmlPath) {
		super(ruleListeners, rules, rule, xmlPath);
	}

}


看下，没什么内容吧，只需继承RuleService,重写execute方法，在里面实现你的业务逻辑，
其实就是自定义规则，但也可以写成通用的呀。就像将来要扩展的drools一样，将来新增个
DroolsRuleService就OK了。这时在xml配置时,在className配置刚刚写的DroolsRuleService的类就OK了。
这样就实现了对drools的支持

		<rule
			className="com.boco.eoms.commons.rule.service.ExpressionRuleService"
			id="ExpressionRule">
			<!-- 输入参数定义 ，由使用规则组件的开发人员定义,rule1InputParameter1Sample为key，-->
			<!--类型为com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample，这里要定义正确呀，否则验证不通过，也不通路由的~-->
			<input>
				<parameter name="rule1InputParameter1Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample" />
				<parameter name="rule1InputParameter2Sample"
					type="com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample" />
			</input>
	
	
再说说监听，有人说你的监听不是没用的吗，我要实现自定义业务逻辑，只需在execute方法最前端及后端加上我要在listener中要做的事，
这不一样能解决吗，OK，我们来看下，完全没错，可以解决，除非你想将listener的东西藕荷在业务逻辑，当然listener的before(),after()方法
不包含你的业务逻辑。再说下，若你不实现自定义规则呢，你怎么实现在before(),after()方法的内容呢，哈哈。再以一个例子说明，其实rule的
参数验证机制就是一个listener实现的，看一下com.boco.eoms.rule.listener.RuleCheckListener.java


public class RuleCheckListener implements IRuleListener {

	public void after(Map map, Rule rule) throws RuleException {
		// 取输入参数
		for (Iterator outputIt = rule.getOutput().getParameters().iterator(); outputIt
				.hasNext();) {
			Parameter para = (Parameter) outputIt.next();
			// 验证map中的所存的对象与xml配置是否相符
			RuleConfigWrapper.checkMapType(map, para);
		}

	}

	public void before(Map map, Rule rule) throws RuleException {
		// 取输入参数
		for (Iterator inputIt = rule.getInput().getParameters().iterator(); inputIt
				.hasNext();) {
			Parameter para = (Parameter) inputIt.next();
			// 验证map中的所存的对象与xml配置是否相符
			RuleConfigWrapper.checkMapType(map, para);
		}

	}

}

这就是输入/输出参数与xml配置的验证呀，呵呵~

OK，现在编写你的listenr吧

只需实现IRuleListener，并实现after(),befor()方法，

配置时在xml中配置,贴一段




			
			<listeners>
				<listener
					name="com.boco.eoms.commons.rule.sample.Rule1ListenerSample" />
				<listener
					name="com.boco.eoms.commons.rule.listener.RuleCheckListener" />
			</listeners>
			
			
			
很简单吧~
			








		

