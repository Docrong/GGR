package com.boco.eoms.partdata.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawpartLacBsc;

/**
 * <p>
 * Title:LAC的BSC分配
 * </p>
 * <p>
 * Description:LAC的BSC分配
 * </p>
 * <p>
 * Mon Jul 12 17:27:34 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public interface TawpartLacBscDao extends Dao {

    /**
     * 取LAC的BSC分配列表
     *
     * @return 返回LAC的BSC分配列表
     */
    public List getTawpartLacBscs();

    /**
     * 根据主键查询LAC的BSC分配
     *
     * @param id 主键
     * @return 返回某id的LAC的BSC分配
     */
    public TawpartLacBsc getTawpartLacBsc(final String id);

    /**
     * 保存LAC的BSC分配
     *
     * @param tawpartLacBsc LAC的BSC分配
     */
    public void saveTawpartLacBsc(TawpartLacBsc tawpartLacBsc);

    /**
     * 根据id删除LAC的BSC分配
     *
     * @param id 主键
     */
    public void removeTawpartLacBsc(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTawpartLacBscs(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    public List getTawpartLacBscList(final String rangeId);

    public void removeTawpartLacBscbyRangeid(final String rangeId);
}