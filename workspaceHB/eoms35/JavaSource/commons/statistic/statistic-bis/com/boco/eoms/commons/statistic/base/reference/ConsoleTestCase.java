package com.boco.eoms.commons.statistic.base.reference;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 5:29:15 PM
 * </p>
 *
 * @author ��
 * @version 1.0
 *
 */
public class ConsoleTestCase extends TestCase {

        protected Logger logger = Logger.getLogger(this.getClass());

        protected void setUp() throws Exception {
                super.setUp();
        }

        protected void tearDown() throws Exception {
                super.tearDown();
        }

        protected Object getBean(String beanId) {
                return ApplicationContextHolder.getInstance().getBean(beanId);
        }

        /**
         * Ŀ����Ϊ�������Զ�����ʱ������ConsoleTestCase no test method
         *
         */
        public void testTrue() {
                Assert.assertEquals(true, true);
        }

}
