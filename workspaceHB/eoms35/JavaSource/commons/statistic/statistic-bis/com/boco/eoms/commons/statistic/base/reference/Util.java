package com.boco.eoms.commons.statistic.base.reference;

import com.boco.eoms.commons.statistic.base.util.Constants;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-19 10:46:58
 * </p>
 *
 * @author ��
 * @version 1.0
 *
 */
public class Util {

    /**
     * �����Ҳ�����Ӧid���ʱ��ʾ
     *
     * @return
     */
    public static String idNoName() {
        return Constants.ID_NO_NAME;
    }

    /**
     * ͨ��ע����dict.xml��key����ֵ����(dictId)�γ�xml dao��Ҫ���dictId
     * �磺key=sample,dictId=major ��������sampe&major
     *
     * @param key
     *            ע����dict.xml��key
     * @param id
     *            �ֵ����
     * @return key=sample,dictId=major ��������sampe&major
     */
    public static String constituteDictId(String key, String id) {
        return key + Constants.DICT_ID_SPLIT_CHAR + id;
    }

    /**
     * ȡ�ֵ�xml��ַ
     *
     * @param dictId
     *            �磺key&major
     * @return classpath:com/boco/eoms/sample.xml
     */
    public static String getDictKey(String dictId) {
        return dictId.split(Constants.DICT_ID_SPLIT_CHAR)[0];
    }

    /**
     * ȡ�ֵ����id
     *
     * @param dictId
     *            �磺key&major
     * @return major
     */
    public static String getId(String dictId) {
        return dictId.split(Constants.DICT_ID_SPLIT_CHAR)[1];
    }

    /**
     * �ڹ�j��ϵdict-relation.xml�����У�Ŀ�Ĺ�j�Ķ��id,�Զ��Ÿ�
     *
     * @return
     */
    public static String getDestinationItemIdsSplit() {
        return Constants.DESTINATION_ITEM_IDS_SPLIT;
    }
}
