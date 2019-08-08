package com.boco.eoms.partner.baseinfo.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.partner.baseinfo.model.TawPartnerCar;

import java.util.List;
import java.util.Map;

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
public interface TawPartnerCarDao extends Dao {

    /**
     * 取车辆管理列表
     *
     * @return 返回车辆管理列表
     */
    public List getTawPartnerCars();

    /**
     * 根据主键查询车辆管理
     *
     * @param id 主键
     * @return 返回某id的车辆管理
     */
    public TawPartnerCar getTawPartnerCar(final String id);

    /**
     * 保存车辆管理
     *
     * @param tawPartnerCar 车辆管理
     */
    public void saveTawPartnerCar(TawPartnerCar tawPartnerCar);

    /**
     * 根据id删除车辆管理
     *
     * @param id 主键
     */
    public void removeTawPartnerCar(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTawPartnerCars(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    /**
     * 判断车辆编号是否唯一
     *
     * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
     */
    public Boolean isunique(final String car_number);

    /**
     * name2Id，即字典id转为字典名称 added by fengshaohong
     *
     * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
     */
    public String name2Id(final String dictName, final String parentDictId);

    /**
     * getDictIdByParentId，即通过parentid得到dictid added by fengshaohong
     *
     * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
     */
    public String[] getDictIdByParentId(final String parentDictId);
}