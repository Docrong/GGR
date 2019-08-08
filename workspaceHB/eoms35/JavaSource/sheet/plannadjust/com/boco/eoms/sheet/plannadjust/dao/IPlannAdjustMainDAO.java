package com.boco.eoms.sheet.plannadjust.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;

/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:09 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public interface IPlannAdjustMainDAO extends IMainDAO {
    public abstract List getNumber(final String sendTimeStartDate, final String sendTimeEndDate, final String queryType) throws HibernateException;

    public abstract Map getDetail(final Integer curPage, final Integer pageSize, final String sendTimeStartDate, final String sendTimeEndDate) throws HibernateException;
}
 



