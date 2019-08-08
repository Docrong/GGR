package com.boco.eoms.km.log.util;

public class KmOperateDefine {

    /*
     * 1: add 新增
     * 2: modify 修改
     * 3: over 失效
     * 4: delete 删除
     * 5: up 上传
     * 6: down 下载
     * 7: use 引用
     * 8: opinion 评论
     * 9: advice 提出修改见议
     * 10: auditOk 审核通过
     * 11: auditBack 审核驳回
     * 12: read 阅读知识
     */

    // 知识条目-增加
    public final static Integer KM_OPERATE_NAME_CONTENTS_ADD = new Integer(1);
    // 知识条目-修改
    public final static Integer KM_OPERATE_NAME_CONTENTS_MODIFY = new Integer(2);
    // 知识条目-失效
    public final static Integer KM_OPERATE_NAME_CONTENTS_OVER = new Integer(3);
    // 知识条目-删除
    public final static Integer KM_OPERATE_NAME_CONTENTS_DELETE = new Integer(4);

    // 附件-上传
    public final static Integer KM_OPERATE_NAME_FILE_UP = new Integer(5);
    // 附件-下载
    public final static Integer KM_OPERATE_NAME_FILE_DOWN = new Integer(6);

    // 知识条目-引用
    public final static Integer KM_OPERATE_NAME_CONTENTS_USE = new Integer(7);
    // 知识条目-评论（即执行评论操作）
    public final static Integer KM_OPERATE_NAME_CONTENTS_OPINION = new Integer(8);
    // 知识条目-提出修改见议（即执行评论时是否提出修改）
    public final static Integer KM_OPERATE_NAME_CONTENTS_ADVICE = new Integer(9);
    // 知识条目-阅读
    public final static Integer KM_OPERATE_NAME_CONTENTS_READ = new Integer(12);

    // 知识条目-审核通过（即执行审核操作）
    public final static Integer KM_OPERATE_NAME_CONTENTS_AUDIT_OK = new Integer(10);
    // 知识条目-审核驳回（即执行审核操作）
    public final static Integer KM_OPERATE_NAME_CONTENTS_AUDIT_BACK = new Integer(11);

    // 模型分类-增加
    public final static Integer KM_OPERATE_NAME_THEME_ADD = new Integer(101);
    // 模型分类-修改
    public final static Integer KM_OPERATE_NAME_THEME_MODIFY = new Integer(102);
    // 模型分类-删除
    public final static Integer KM_OPERATE_NAME_THEME_DELETE = new Integer(103);

    private final static String[] KM_OPERATE_PROPERTY_NAME = {null, "add",
            "modify", "over", "delete", "up", "down", "use", "opinion",
            "advice", "auditOk", "auditBack", "read"};

    public final static String getKmOperatePropertyName(int operateId) {
        try {
            return KM_OPERATE_PROPERTY_NAME[operateId];
        } catch (Exception e) {
            return null;
        }
    }
}
