package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpMonthCheckDao;
import com.boco.eoms.workplan.model.TawwpMonthCheck;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:26:00 AM
 * </p>
 *
 * @author 曲静汿
 * @version 3.5.1
 */
public class TawwpMonthCheckDaoHibernate extends BaseDaoHibernate implements
        ITawwpMonthCheckDao {
    /**
     * 保存月度作业计划审批信息
     *
     * @param _tawwpMonthCheck TawwpMonthCheck 月度作业计划审批信息篿@ 操作异常
     */
    public void saveMonthCheck(TawwpMonthCheck _tawwpMonthCheck) {
        // this.save(_tawwpMonthCheck);
        this.getHibernateTemplate().save(_tawwpMonthCheck);
    }

    /**
     * 删除月度作业计划审批信息
     *
     * @param _tawwpMonthCheck TawwpMonthCheck 月度作业计划审批信息篿@ 操作异常
     */
    public void deleteMonthCheck(TawwpMonthCheck _tawwpMonthCheck) {
        // this.delete(_tawwpMonthCheck);
        getHibernateTemplate().delete(_tawwpMonthCheck);
    }

    /**
     * 修改月度作业计划审批信息
     *
     * @param _tawwpMonthCheck TawwpMonthCheck 月度作业计划审批信息篿@ 操作异常
     */
    public void updateMonthCheck(TawwpMonthCheck _tawwpMonthCheck) {
        // this.update(_tawwpMonthCheck);
        getHibernateTemplate().update(_tawwpMonthCheck);
    }

    /**
     * 查询月度作业计划审批信息
     *
     * @param id String 月度作业计划审批信息标识 @ 操作异常
     * @return TawwpMonthCheck 月度作业计划审批信息篿
     */
    public TawwpMonthCheck loadMonthCheck(String id) {
        // return (TawwpMonthCheck) this.load(id, TawwpMonthCheck.class);

        return (TawwpMonthCheck) getHibernateTemplate().get(
                TawwpMonthCheck.class, id);
    }

    /**
     * 查询需要当前用户进行审批的月度作业计划,相应的审批信忿
     *
     * @param _userId String 用户 @ 操作异常
     * @return List 月度作业计划审批信息类列蟿
     */
    public List listMonthCheck(String _userId) {
        // Session s = HibernateUtil.currentSession();
        // String hSql = "";
        // hSql = "from TawwpMonthCheck as tawwpmonthcheck "
        // + "where tawwpmonthcheck.checkUser like '%" + _userId
        // + "%' and tawwpmonthcheck.state = '0'";
        //
        // Query query = s.createQuery(hSql);
        // query.setCacheable(true);
        // return query.list();

        // TODO 可能需要加入query.setCacheable(true);
        return getHibernateTemplate().find(
                "from TawwpMonthCheck as tawwpmonthcheck "
                        + "where tawwpmonthcheck.checkUser like '%" + _userId
                        + "%' and tawwpmonthcheck.state = '0'");
    }


    /**
     * 查询需要当前用户进行审批的月度作业计划,相应的审批信忿
     *
     * @param _userId String 用户 @ 操作异常
     * @return List 月度作业计划审批信息类列蟿
     */
    public List listMonthCheck(String _userId, String _monthPlanId) {
        // Session s = HibernateUtil.currentSession();
        // String hSql = "";
        // hSql = "from TawwpMonthCheck as tawwpmonthcheck "
        // + "where tawwpmonthcheck.checkUser like '%" + _userId
        // + "%' and tawwpmonthcheck.state = '0'";
        //
        // Query query = s.createQuery(hSql);
        // query.setCacheable(true);
        // return query.list();

        // TODO 可能需要加入query.setCacheable(true);
        return getHibernateTemplate().find(
                "from TawwpMonthCheck as tawwpmonthcheck "
                        + "where tawwpmonthcheck.checkUser like '%" + _userId
                        + "%' and tawwpmonthcheck.state = '0' and tawwpmonthcheck.tawwpMonthPlan in (" + _monthPlanId + ")");
    }

    public List listUnPassMonthCheck() {
        return getHibernateTemplate().find(
                "from TawwpMonthCheck as tawwpmonthcheck where tawwpmonthcheck.state = '0'");

    }
}
