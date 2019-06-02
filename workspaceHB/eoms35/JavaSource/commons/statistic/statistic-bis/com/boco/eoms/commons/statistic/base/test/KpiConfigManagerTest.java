/*
 * Created on 2008-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.test;


import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.dao.IStatJdbcDAO;
import com.boco.eoms.commons.statistic.base.reference.ConsoleTestCase;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

/**
 * @author liuxy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KpiConfigManagerTest extends ConsoleTestCase {



		public KpiConfig loadXml(String path) throws Exception {
			try {

				KpiConfig config = (KpiConfig) ParseXmlService.create()
						.xml2object(
								KpiConfig.class,
								StaticMethod.getFilePathForUrl(path));
				
				System.out.println(config.getKpiDefines()[0].getName());
				
				return config;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("读取配置文件'"+path+"'出错");
			}
		}

		public void testconfig() throws Exception {
		KpiConfig config =	loadXml("classpath:kpi-config-nop.xml");

		
		IStatJdbcDAO dao = (IStatJdbcDAO)getBean("iKpiJdbcDAO");
		//StatJdbcDAOImpl jdbcDAOImpl = new StatJdbcDAOImpl();
//		dao.getKpiResult(config.getKpiDefines()[0],"","");
			
		}
	}
