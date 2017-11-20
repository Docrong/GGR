此程序为Springssh+junit测试中提取出来的
JDK 1.6
java libraries:
	Java EE 5 Libraries;
	JUnit4
	Spring 3.0 Core Libraries
	Spring 3.0 Testing Support Libraries
	Hibernate 3.1 Core Libraries
其他jar包放在lib中了
update 11.20
为了兼容 spring.jar
删除
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
以及 spring 3.0core相关内容
