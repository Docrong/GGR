
package com.boco.eoms.message.mgr;

import java.util.Map;

import com.boco.eoms.base.service.Manager;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-3-11 下午02:00:09
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface IContentManager extends Manager {
    public String getSendContent(Map infoMap);
}

