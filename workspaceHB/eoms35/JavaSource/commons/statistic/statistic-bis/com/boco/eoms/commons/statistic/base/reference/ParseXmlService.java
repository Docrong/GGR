package com.boco.eoms.commons.statistic.base.reference;


/**
 * <p>
 * Title:????????ParseXmlService??
 * </p>
 * <p>
 * Description: ???ParseXmlService.create().xml2object()??????xml????
 * </p>
 * <p>
 * Date:Apr 22, 2007 7:51:19 PM
 * </p>
 *
 * @author ??
 * @version 1.0
 *
 */
public class ParseXmlService {
        /**
         * ????
         */
        private static ParseXmlService instance;

        /**
         * ????xml??manager
         */
        private IParseXmlManager mgr;

        /**
         * ??????xml2sheets?????,????xml
         *
         * @param cls
         *            ??
         * @param key
         *            xml mapping?????????key?
         * @param filePath
         *            xml???????·??,???????
         * @return ????????
         * @see ParseXmlManagerImpl#xml2object(Class, String, String)
         * @throws ParseXMLException
         *             ??????????
         */
        public Object xml2object(Class cls, String key, String filePath)
                        throws Exception {
                return this.mgr.xml2object(cls, key, filePath);
        }

        /**
         * ??obj?????filePath(abc.xml)
         *
         * @param obj
         *            OR????
         * @param filePath
         *            ????????
         * @param key
         *            xml mapping?????????key?
         * @throws ParseXMLException
         *             ???????????
         */
        public void object2xml(Object obj, String key, String filePath)
                        throws Exception {
                mgr.object2xml(obj, key, filePath);
        }

        public Object xml2object(Class cls, String key, String filePath,boolean isMapPath)
        throws Exception {
                return this.mgr.xml2object(cls, key, filePath,isMapPath);
        }

        public void object2xml(Object obj, String key, String filePath,boolean isMapPath)
        throws Exception {
                mgr.object2xml(obj, key, filePath,isMapPath);
        }

        public Object xml2object(Class cls, String filePath)
        throws Exception {
                  return this.mgr.xml2object(cls, filePath);
        }

        public Object xml2objectWithKey(Class cls, String key)
        throws Exception {
                return this.mgr.xml2objectWithKey(cls, key);
        }

        /**
         * ??й?????
         *
         */
        private ParseXmlService() {
//                mgr = (IParseXmlManager) ApplicationContextHolder.getInstance()
//                                .getBean("ParseXmlManagerImpl");
                mgr = new  ParseXmlManagerImpl();
        }

        /**
         * ????ParseXmlManagerService
         *
         * @return ????????????
         */
        public static ParseXmlService create() {
                if (instance == null) {
                        instance = new ParseXmlService();
                }
                return instance;
        }

}
