package com.boco.eoms.partner.baseinfo.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.TawPartnerCar;

/**
 * <p>
 * Title:车辆管理
 * </p>
 * <p>
 * Description:车辆管理
 * </p>
 * <p>
 * Thu Feb 05 13:54:40 CST 2009
 * </p>
 *
 * @author fengshaohong
 * @version 3.5
 */
public interface TawPartnerCarMgr {

    /**
     * 取车辆管理 列表
     *
     * @return 返回车辆管理列表
     */
    public List getTawPartnerCars();

    /**
     * 根据主键查询车辆管理
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public TawPartnerCar getTawPartnerCar(final String id);

    /**
     * 保存车辆管理
     *
     * @param tawPartnerCar 车辆管理
     */
    public void saveTawPartnerCar(TawPartnerCar tawPartnerCar);

    /**
     * 根据主键删除车辆管理
     *
     * @param id 主键
     */
    public void removeTawPartnerCar(final String id);

    /**
     * 根据条件分页查询车辆管理
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回车辆管理的分页列表
     */
    public Map getTawPartnerCars(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    /**
     * name2Id，即字典id转为字典名称 added by fengshaohong
     *
     * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
     */
    public String name2Id(final String dictName, final String parentDictId);

    /**
     * 判断车辆编号是否唯一
     *
     * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
     */
    public Boolean isunique(final String car_number);

    /**
     * getDictIdByParentId，即通过parentid得到dictid added by fengshaohong
     *
     * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
     */
    public String[] getDictIdByParentId(final String parentDictId);
}