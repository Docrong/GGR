package com.boco.eoms.workbench.infopub.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jul 29, 2008 9:31:11 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public class ThreadBrowseHistoryListDisplaytagDecorator extends TableDecorator {
    /**
     * 浏览历史
     *
     * @return
     */
    public String getUserId() {
        ThreadHistory threadHistory = (ThreadHistory) getCurrentRowObject();
        String name = "";
        try {
            ID2NameDAO forumsDao = (ID2NameDAO) ApplicationContextHolder
                    .getInstance().getBean("tawSystemUserDao");
            name = forumsDao.id2Name(threadHistory.getUserId());
        } catch (Exception e) {
            name = Util.idNoName();
        }
        return name;
    }

    public String getReplyresult() {
        ThreadHistory threadHistory = (ThreadHistory) getCurrentRowObject();
        String replyresult = "";
        try {
            replyresult = (String) DictMgrLocator.getDictService()
                    .itemId2name(
                            Util.constituteDictId("dict-workbench-infopub",
                                    "replyresult"), threadHistory.getReplyresult());
        } catch (DictServiceException e) {
            replyresult = Util.idNoName();
        }
        return replyresult;
    }
}
