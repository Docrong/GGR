/*
 * Created on 2008-5-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.sheet.expert.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.sheet.expert.dao.TawSheetExpertDao;
import com.boco.eoms.commons.sheet.expert.model.TawSheetExpert;
import com.boco.eoms.commons.sheet.expert.service.ITawSheetExpertManager;


/**
 * @author admin
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSheetExpertManagerImpl extends BaseManager implements ITawSheetExpertManager {

    private TawSheetExpertDao dao;

    public List getTawSheetExperts(TawSheetExpert tawSheetExpert) {
        return dao.getTawSheetExperts(tawSheetExpert);

    }

    public TawSheetExpert getTawSheetExpert(final Integer id) {
        return dao.getTawSheetExpert(id);

    }

    public void saveTawSheetExpert(TawSheetExpert tawSheetExpert) {
        dao.saveTawSheetExpert(tawSheetExpert);
    }

    public void removeTawSheetExpert(final Integer id) {
        dao.removeTawSheetExpert(id);
    }

    public TawSheetExpert getExpertBySpecialId(String specialid) {
        return dao.getExpertBySpecialId(specialid);
    }

    /**
     * @return Returns the dao.
     */
    public TawSheetExpertDao getDao() {
        return dao;
    }

    /**
     * @param dao The dao to set.
     */
    public void setDao(TawSheetExpertDao dao) {
        this.dao = dao;
    }

    /**
     * 根据专业ID查询专家信息
     *
     * @param areaid
     * @return
     */
    public List getExpertsBySpecialId(String specialid) {

        return dao.getExpertsBySpecialId(specialid);
    }

    /**
     * 根据专业userId更新
     *
     * @param areaid
     * @return
     */
    public void updtaeExpertByExpertName(String expertName, String oldExpertName) {

        dao.updtaeExpertByExpertName(expertName, oldExpertName);
    }

    /**
     * 根据专业userId删除
     *
     * @param areaid
     * @return
     */
    public void deleteTawSheetExpert(String expertName) {
        dao.deleteTawSheetExpert(expertName);
    }

    public void updateTawSheetExpert(String expertName) {
        dao.updateTawSheetExpert(expertName);
    }
}
