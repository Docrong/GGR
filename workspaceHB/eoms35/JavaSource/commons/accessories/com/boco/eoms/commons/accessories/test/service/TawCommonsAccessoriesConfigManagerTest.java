package com.boco.eoms.commons.accessories.test.service;

import java.io.File;
import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.base.util.StaticMethod;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:附件配置管理service测试类
 * </p>
 * <p>Apr 10, 2007 11:04:51 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsAccessoriesConfigManagerTest extends ConsoleTestCase {
	private String configFilePath; // 配置文件存放位置

	protected void onSetUpInTransaction() throws Exception {
		configFilePath = StaticMethod.getClasspath()
				+ "com/boco/eoms/commons/accessories/config/accessoriesConfigInfo.xml";
		super.setUp();
	}


	/**
	 * @see 测试配置文件的读写与保存
	 * @author 秦敏
	 * @throws Exception
	 */
	public void testConfigInfo() throws Exception {
		// 模拟数据
		TawCommonsAccessoriesConfig config = new TawCommonsAccessoriesConfig();
		String allowFileType = "xml,txt";
		String appCode = "1001";
		Integer appId=new Integer(1);
		Integer maxSize = new Integer(10240000);
		String path = "testmodule";
		config.setAppId(appId);
		config.setAllowFileType(allowFileType);
		config.setAppCode(appCode);
		config.setMaxSize(maxSize);
		config.setPath(path);
		// 保存配置信息
		ITawCommonsAccessoriesConfigManager mgr = (ITawCommonsAccessoriesConfigManager) this
				.getBean("ItawCommonsAccessoriesConfigManager");
		mgr.saveTawCommonsAccessoriesConfig(config);
		File file = new File(configFilePath);
		assertNotNull(file);
		// 读取配置信息
		TawCommonsAccessoriesConfig tempConfig = mgr
				.getTawCommonsAccessoriesConfig(appCode);
		assertNotNull(tempConfig);
		assertEquals(allowFileType, tempConfig.getAllowFileType());
		assertEquals(appCode, tempConfig.getAppCode());
		assertEquals(maxSize, tempConfig.getMaxSize());
		assertEquals(path, tempConfig.getPath());
		// 获取配置信息
		List results = mgr.getTawCommonsAccessoriesConfigs();
		assertTrue(results.size() == 1);
		// 删除配置信息
		mgr.removeTawCommonsAccessoriesConfig(appId);
		tempConfig = mgr.getTawCommonsAccessoriesConfig(appCode);
		assertNull(tempConfig);
	}

}
