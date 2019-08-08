package com.boco.eoms.sheet.commonfaultcorrigendum.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.commonfaultcorrigendum.dao.ICommonfaultCorrigendumMainDAO;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumMainManager;

/**
 * <p>
 * Title:故障工单勘误流程
 * </p>
 * <p>
 * Description:故障工单勘误流程
 * </p>
 * <p>
 * Mon Sep 29 11:24:17 CST 2014
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CommonfaultCorrigendumMainManagerImpl extends MainService implements ICommonfaultCorrigendumMainManager {
    public List getNetTeam(String netname) throws Exception {
        ICommonfaultCorrigendumMainDAO dao = (ICommonfaultCorrigendumMainDAO) this.getMainDAO();
        return dao.getNetTeam(netname);
    }

    public void updateNetTeam(String mainnewTeamRoleId, String mainnewccObject, String mainCommonfaultNetName) throws Exception {
        ICommonfaultCorrigendumMainDAO dao = (ICommonfaultCorrigendumMainDAO) this.getMainDAO();
        dao.updateNetTeam(mainnewTeamRoleId, mainnewccObject, mainCommonfaultNetName);
    }
}