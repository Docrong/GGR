
package com.boco.eoms.message.dao;

import java.util.Map;

import com.boco.eoms.base.dao.Dao;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-7-21 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public interface IContentDao extends Dao {

    /**
     * 短信轮询
     * @param tel
     * @param msg
     * @return
     */
    public String getSendContent(Map infoMap);
    
    
}

