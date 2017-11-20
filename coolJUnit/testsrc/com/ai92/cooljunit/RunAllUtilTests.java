package com.ai92.cooljunit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 批量测试 工具包 中测试类
 *
 * @author Ai92
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestWordDealUtil.class})
public final class RunAllUtilTests {
	public RunAllUtilTests(){}
}
