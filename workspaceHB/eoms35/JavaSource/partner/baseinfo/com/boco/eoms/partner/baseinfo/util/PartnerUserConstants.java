package com.boco.eoms.partner.baseinfo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>
 * Title:��f��Ϣ
 * </p>
 * <p>
 * Description:��f��Ϣ
 * </p>
 * <p>
 * Tue Feb 10 17:33:14 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public class PartnerUserConstants {

    /**
     * list key
     */
    public final static String PARTNERUSER_LIST = "partnerUserList";

    /**
     * 合作伙伴人力资源管理对应的roleId
     */
    public final static int PARTNER_USER_ROLEID = 386;

    /**
     * 验证邮箱格式
     *
     * @param line
     * @return
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

}