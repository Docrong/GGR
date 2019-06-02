package com.boco.eoms.commons.statistic.base.reference;

//import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;

/**
 * <p>
 * Title: ����xmlҵ��ӿ�
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 2:09:59 PM
 * </p>
 *
 * @author ��
 * @version 1.0
 *
 */
public interface IParseXmlManager {

        /**
         * ͨ��ʵ��xml2sheetsҵ�񷽷�,����xml
         *
         * @param cls
         *            ��
         * @param key
         *            xml mapping�����ļ���keyֵ
         * @param filePath
         *            xml�����ļ�·��,���ļ���
         * @return ���ؽ���Ķ���
         * @throws ParseXMLException
         */
        public Object xml2object(Class cls, String key, String filePath)
                        throws Exception;

        /**
         * ��obj����ΪfilePath(abc.xml)
         *
         * @param obj
         *            OR����
         * @param filePath
         *            Ҫ����ĵ�ַ
         * @param key
         *            xml mapping�����ļ���keyֵ
         *
         * @throws ParseXMLException
         *             �������׳��쳣
         */
        public void object2xml(Object obj, String key, String filePath)
                        throws Exception;

        /**
         * ͨ��ʵ��xml2sheetsҵ�񷽷�,����xml
         *
         * @param cls
         *            ��
         * @param key
         *            xml mapping�����ļ���keyֵ
         * @param filePath
         *            xml�����ļ�·��,���ļ���
         * @param isMapPath
         *            ��ʶkey��mapping�����ļ���keyֵ����mapping�ļ���·����true����������ļ�keyֵ
         * @return ���ؽ���Ķ���
         * @throws ParseXMLException
         */
        public Object xml2object(Class cls, String key, String filePath, boolean isMapPath)
        throws Exception;
        /**
         * ͨ��ʵ��xml2sheetsҵ�񷽷�,����xml
         *
         * @param cls
         *            ��
         * @param key
         *            xml mapping�����ļ���keyֵ
         * @param filePath
         *            xml�����ļ�·��,���ļ���
         * @param isMapPath
         *            ��ʶkey��mapping�����ļ���keyֵ����mapping�ļ���·����true����������ļ�keyֵ
         * @return ���ؽ���Ķ���
         * @throws ParseXMLException
         */
        public void object2xml(Object obj, String key, String filePath, boolean isMapPath)
        throws Exception;

        /**
         * ͨ��ʵ��xml2sheetsҵ�񷽷�,����xml
         *
         * @param cls
         *            ��
         * @param filePath
         *            xml�����ļ�·��,���ļ���
         * @return ���ؽ���Ķ���
         * @throws ParseXMLException
         */
        public Object xml2object(Class cls, String filePath)
        throws Exception;
        /**
         * ͨ��ʵ��xml2sheetsҵ�񷽷�,����xml������mapping�ļ���
         *
         * @param cls
         *            ��
         * @param key
         *            applicationContext-fileconfig.xml��key
         * @return ���ؽ���Ķ���
         * @throws ParseXMLException
         */
        public Object xml2objectWithKey(Class cls, String key)
                        throws Exception;

}
