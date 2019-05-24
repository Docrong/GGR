package com.boco.eoms.sheet.commonfaultcorrigendum.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.IMainService;

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
 * 
 */
 
 public interface ICommonfaultCorrigendumMainManager extends IMainService {
     public abstract List getNetTeam(String netname) throws Exception;
     public abstract void updateNetTeam(String mainnewTeamRoleId,String mainnewccObject,String mainCommonfaultNetName) throws Exception;
 }