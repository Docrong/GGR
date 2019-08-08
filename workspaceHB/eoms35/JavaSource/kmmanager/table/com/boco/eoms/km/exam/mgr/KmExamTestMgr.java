package com.boco.eoms.km.exam.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamTest;

/**
 * <p>
 * Title:试卷
 * </p>
 * <p>
 * Description:试卷
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public interface KmExamTestMgr {

    /**
     * 取试卷 列表
     *
     * @return 返回试卷列表
     */
    public List getKmExamTests();

    /**
     * 根据主键查询试卷
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmExamTest getKmExamTest(final String id);

    /**
     * 得到发布的考试试题
     *
     * @return
     */
    public List getKmExamPublicTests();

    /**
     * 得到发布的随机考试试卷
     *
     * @return
     */
    public List getKmExamRandomTests();

    /**
     * 保存试卷
     *
     * @param kmExamTest 试卷
     */
    public void saveKmExamTest(KmExamTest kmExamTest);

    /**
     * 根据主键删除试卷
     *
     * @param id 主键
     */
    public void removeKmExamTest(final String id);

    /**
     * 根据条件分页查询试卷
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回试卷的分页列表
     */
    public Map getKmExamTests(final Integer curPage, final Integer pageSize,
                              final String whereStr);

}