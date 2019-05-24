package com.boco.eoms.commons.rule.service;

import java.util.Iterator;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import com.boco.eoms.commons.rule.exception.ExpressionComputeException;
import com.boco.eoms.commons.rule.exception.ExpressionParseException;

/**
 * <p>
 * Title:ognl service
 * </p>
 * <p>
 * Description: OGNL是Object-Graph Navigation
 * Language的缩写，它是一种功能强大的表达式语言（Expression
 * Language，简称为EL），通过它简单一致的表达式语法，可以存取对象的任意属性，调用对象的方法，遍历整个对象的结构图，实现字段类型转化等功能。它使用相同的表达式去存取对象的属性。
 * 
 * OGNL特性： 1，一个OGNL 表达式的基本组成是：navigation chain。组成如下： Properity name Method calls
 * Array Indices 如：name.toCharArray()[0].numericValue.toString()
 * name.toCharArray()转化为数组; numericValue相当于Character类的getNumericValue()方法:static
 * int getNumericValue(char ch);
 * 
 * 
 * 2，简化JavaBeans的get/set机制 如：public Object getAttribute(String name) public void
 * setAttribute(String name, Object value) 简化为：session.attribute[“foo”]
 * 
 * 
 * 3，OGNL有一个简化变量机制（在变量前加符号#），所有OGNL变量在整个表达式里是全局的。 如：#var #var = 99
 * listeners.size().( #this > 100 ? 2 * #this : 20 + #this )
 * 调用listeners的size()并与100比较。
 * 
 * 
 * 4，chain subexpressions 如：headline.parent.(ensureLoaded(), name)
 * headline.parent <-root object
 * 
 * ensureLoaded()被root object调用，然后set/get name property。
 * 
 * 
 * 
 * 5，Maps
 * 
 * 可以这样创建一个Map：
 * 
 * #{“foo”: “foo value”, “bar”: “bar value” }
 * 
 * #@java.util.LinkedHashMap@{“foo”: “foo value”, “bar”: “bar value” }
 * 
 * 
 * 
 * 6，Projecting Across Collections
 * 
 * 从一个Collection中的每一个元素里提取或调用相同的方法或property，然后将结果储存为一个新的collection，称为projection。
 * 
 * Listeners.{delegate} 返回一个List，这个List是listeners中所有delegate的集合。
 * 
 * Objects.{#this instanceof String ? #this : #this.toString}
 * 创建一个新的List，并将Object List中所有元素转换为String,存入新的List。
 * 
 * 
 * 
 * 7，检查List的第一和最后一个元素。
 * 
 * Listeners.{?ture}[0]
 * 这样可以检测，但是如果是一个空List，就会发生ArrayIndexOutOfBoundsException。为防止这种情况，可以用下面方式检测第一个和最后一个元素：
 * 
 * Objects.{^#this instanceof String} 第一个元素
 * 
 * Objects.{$#this instanceof String} 最后一个元素
 * 
 * 
 * 
 * 8，调用静态方法
 * 
 * @Class@method(args)
 * 
 * 如果Class不指明，默认为java.lang.Math
 * 
 * 也可以用实例化的方式来调用静态方法。
 * 
 * 同样，调用静态成员变量的方法为：@Class@field
 * 
 * 
 * 
 * 9，#fact(30H) <-注意与#fact.(30H)的区别。
 * 
 * 如果一个OGNL表达式在括号前没有逗号，那么OGNL会把第一表达式的结果作为另一个表达式来赋值，而括号中表达式的结果会作为root
 * object指向那个表达式。
 * 
 * 如：#fact(30H) <- 查找fact 变量，将这个变量解析为一个OGNL表达式，使用30H作为该表达式的root object。
 * 
 * fact(30H) <-令人混淆的用法，OGNL可能会将其作为一个方法调用。
 * 
 * (fact)(30H) <-调用fact方法。
 * 
 * 
 * 
 * 10，虚拟Lambda表达式（Pseudo-Lambda Expression）
 * 
 * OGNL有一个简化的Lambda表达式句法，能够让你写一些简单功能。需要注意的是，所有OGNL变量是全局范围的。
 * 
 * 如：#fact=:[#this <=1 ? 1 : #this*#fact(#this-1)],#fact(30H)
 * 
 * 这是一个OGNL表达式，声明一个递归功能函数，然后调用它。[]中的是Lambda表达式，
 * #this变量指向的是初始化30H的表达式。OGNL将Lambda表达式作为常量来处理。Lambda表达式的值是一个OGNL使用的AST。
 * 
 * 
 * 
 * 11， 虚拟 Properties集合（Pseudo-Properties）
 * 
 * OGNL将集合中的一些特殊Properties变为可利用，因为集合并不遵循JavaBean模式的命名规则。
 * 
 * Collection Special Properties Collection (被Map,List,Set继承) Size isEmpty List
 * iterator Map keys values 注意：这些properties附加size 和 isEmpty，不同于索引形式访问Maps，
 * 如someMap[“size”]从Map中获得 “size”key，而someMap.size得到Map的大小 Set iterator Iterater
 * naxt hasNext Enumeration next hasNext nextElemet hasMoreElement
 * 
 * 
 * 
 * 
 * 12，Operators不同于Java OGNL operator伪造于JAVA，并有相似的工作机制，下面是与JAVA不同的地方。 1） comma(,
 * )或序列Operator，来源于C, 逗号被用来分隔两个表达式，第二个表达式的值是这个comma表达式的值。 如：ensureLoaded(), name
 * 调用ensureLoaded()方法，然后get/set name property 2) 用 {} 来构造List 如：{null, true,
 * false } 3) in operator (或 not in , in的相反)，这是用来检测某个值是否在集合中。 如：name in { null,
 * “Untitled “ } || name
 * 
 * 
 * 13，getting value 与 setting value。 并不是所有能够get的值都可以set。 如：names[0].location
 * <-这是一个set表达式。 names[0].length+1 <-这个表达式不能set，因为他不能解析为一个object，而仅仅是一个是单的计算。
 * 
 * 
 * 14，将Object强制转换为各种类Tpye。 1） 转换Objects为Boolean l 如果Object是一个Boolean，获得值并返回； l
 * 如果Object是一个Number，它的双精度浮点数将与 0 比较，非 0 处理为false， 0处理为ture； l
 * 如果Object是Character，仅当其值为非0时为ture； l 其它的Object，仅当其值为非0时为ture； 2）
 * 转换Object为Numbers 数字运算符试图将其参数处理为numbers。基本的原始类型封装类（Integer,
 * Double等，包括被处理为Integer的Character和Boolean），和java.math包的 “big” numeric
 * classes（BigInteger和BigDecimal），被认可的特殊数字类型。某个类的一个对象，OGNL解析这个对象的string值为一个number。
 * 数字运算符通过两个参数并使用下面的算法，以决定运算结果的转换类型。如果结果超出给定类型，那么这个类型的实际结果范围可能很广。 l
 * 如果两个参数都是同一个类型，那么结果将尽可能为同一个类型。 l 如果其中一个参数不是认可的数字类型（Float,
 * Double,或BigDecimal），则结果会是两个参数中的最大范围类型。 l 如果两个参数是real number的近似值 (Float,
 * Double, 或BigDecimal)，则结果为两个参数中的最大范围类型。 l 如果两个参数都是integers (Boolean, Byte,
 * Character, Short, Integers, Long 或 BigInteger )，则结果为两个参数中的最大范围类型。 l
 * 如果一个参数为real type 而另一个参数为integer type， 结果将会是real type，如果integer小于
 * “int”，结果将会是BigDecimal，如果integer是BigInteger，或者大于real type和Double。 3） 转换Object
 * 为Integers 运算符仅操作integers，比如位操作符，将其参数处理为numbers,
 * 除了BigDecimals和BigIntegers被处理为BigIntegers，其它所有的numbers被处理为Longs。对于BigInteger情况，处理结果仍然保持为Biginteger，对于Long情况，结果被处理为与参数相同的类型。
 * 4） 转换Objects为Collections projection和selection operators（ e1.{e2}和e1.{?e2} ）,和
 * in 运算符，都将它们的其中一个参数处理为一个collection并遍历。根据参数的不同，有不同的处理方式。 l Java arrays从头至尾遍历。 l
 * Java.util.Colleciton的成员通过遍历它们的iterations来实现遍历。 l
 * Java.util.Map的成员通过遍历它们的iterators越过他们的value来实现遍历。 l
 * java.util.Iterator和java.util.Enumeration的成员通过iterate它们来实现遍历。 l
 * java.lang.Number的成员遍历的方式为：从0开始，返回的integers小于给予的number。
 * 
 * </p>
 * <p>
 * Apr 22, 2007 1:59:04 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class OgnlExpressionService {

	/**
	 * 单例
	 */
	private static OgnlExpressionService instance;

	/**
	 * 如果一个OGNL表达式在括号前没有逗号，那么OGNL会把第一表达式的结果作为另一个表达式来赋值，而括号中表达式的结果会作为root
	 * object指向那个表达式。
	 * 
	 * 如：#fact(30H) <- 查找fact 变量，将这个变量解析为一个OGNL表达式，使用30H作为该表达式的root object。
	 * 
	 * fact(30H) <-令人混淆的用法，OGNL可能会将其作为一个方法调用。
	 * 
	 * (fact)(30H) <-调用fact方法。
	 *  
	 */
	private Object root;

	/**
	 * 单例构造方法
	 * 
	 * @param root
	 *            可传入null
	 */
	private OgnlExpressionService(Object root) {
		this.root = root;

	}

	/**
	 * 表达式解析
	 * 
	 * @param expression
	 *            表达式
	 * @return
	 * @throws ExpressionParseException
	 */
	public Object parseExpression(String expression)
			throws ExpressionParseException {
		try {
			return Ognl.parseExpression(expression);
		} catch (OgnlException e) {
			throw new ExpressionParseException(e);
		}
	}

	/**
	 * 检查表达式
	 * 
	 * @param expression
	 * @return
	 */
	public boolean checkExpression(String expression) {
		try {
			Ognl.parseExpression(expression);
		} catch (OgnlException e) {
			return false;
		}
		return true;
	}

	/**
	 * 计算表达式的值
	 * 
	 * @param expression
	 *            表达式
	 * @param inputMap
	 *            输入参数
	 * @return 计算结果
	 * @throws OgnlException
	 */
	public Object getValue(String expression, Map inputMap)
			throws ExpressionComputeException {

		OgnlContext context = (OgnlContext) Ognl.createDefaultContext(root);
		// 将输入参数存入context
		context.putAll(inputMap);
		System.out.println(expression);
		try {
			return Ognl.getValue(expression, context, this.root);
		} catch (OgnlException e) {
			e.printStackTrace();
			throw new ExpressionComputeException(expression
					+ ",表达式计算问题，请查看表达式与输入参数是否区配" + "\n"
					+ this.context2string(inputMap));
		}
	}

	public Object getRoot() {
		return this.root;
	}

	/**
	 * 单例创建service
	 * 
	 * @param root
	 *            如果一个OGNL表达式在括号前没有逗号，那么OGNL会把第一表达式的结果作为另一个表达式来赋值，而括号中表达式的结果会作为root
	 *            object指向那个表达式。
	 * 
	 * 如：#fact(30H) <- 查找fact 变量，将这个变量解析为一个OGNL表达式，使用30H作为该表达式的root object。
	 * 
	 * fact(30H) <-令人混淆的用法，OGNL可能会将其作为一个方法调用。
	 * 
	 * (fact)(30H) <-调用fact方法。
	 * @return
	 */
	public static OgnlExpressionService create(Object root) {
		if (instance == null) {
			instance = new OgnlExpressionService(root);
		}
		return instance;
	}

	/**
	 * 将inputMap转成String 描述
	 * 
	 * @param inputMap
	 *            输入参数
	 * @return 将所有map的key遍历，将value值以串形式相加
	 */
	private String context2string(Map inputMap) {
		if (inputMap.isEmpty()) {
			{
				return "inputMap is empty";
			}
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator it = inputMap.keySet().iterator(); it.hasNext();) {
			Object key = it.next();
			sb.append("key:" + key);
			sb.append(",value:" + inputMap.get(key));
			sb.append("\n");
		}
		return sb.toString();
	}
}
