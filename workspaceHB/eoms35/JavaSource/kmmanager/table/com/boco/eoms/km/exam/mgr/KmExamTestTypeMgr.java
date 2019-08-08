package com.boco.eoms.km.exam.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamTestType;

/**
 * <p>
 * Title:题型信息表
 * </p>
 * <p>
 * Description:题型信息表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public interface KmExamTestTypeMgr {

    /**
     * 取题型信息表 列表
     *
     * @return 返回题型信息表列表
     */
    public List getKmExamTestTypes();

    /**
     * 查询某试卷下的所有类型
     *
     * @param testID
     * @return
     */
    public List getKmExamTestTypesByTestID(final String testID);

    /**
     * 根据主键查询题型信息表
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmExamTestType getKmExamTestType(final String id);

    /**
     * 保存题型信息表
     *
     * @param kmExamTestType 题型信息表
     */
    public void saveKmExamTestType(KmExamTestType kmExamTestType);

    /**
     * 根据主键删除题型信息表
     *
     * @param id 主键
     */
    public void removeKmExamTestType(final String id);

    /**
     * 根据条件分页查询题型信息表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回题型信息表的分页列表
     */
    public Map getKmExamTestTypes(final Integer curPage, final Integer pageSize,
                                  final String whereStr);

}