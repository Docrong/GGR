/*
 * Created on 2007-9-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

/**
 * @author IBM
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RoleConstants {
    public static final String SUBROLE_DEPT = "dept";

    public static final String SUBROLE_TYPE1 = "type1";

    public static final String SUBROLE_TYPE2 = "type2";

    public static final String SUBROLE_TYPE3 = "type3";

    public static final String SUBROLE_TYPE4 = "type4";

    public static final String SUBROLE_BUSINESSDEPT = "dept";

    public static final String SUBROLE_BUSINESS1 = "class1";

    public static final String SUBROLE_BUSINESS2 = "class2";

    public static final String SUBROLE_BUSINESS3 = "class3";

    public static final String SUBROLE_BUSINESS4 = "class4";

    /**
     * 流程角色 role表中的roleTypeId=1
     */
    public static final String flowRole = "1";

    /**
     * 系统角色 role表中的roleTypeId=2
     */
    public static final String systemRole = "2";

    /**
     * 全部角色，包括流程角色，系统角色
     */
    public static final String ALL_ROLE = "3";

    /**
     * 子角色 userrefrole表中的roleType=1
     */
    public static final String ROLETYPE_SUBROLE = "1";

    /**
     * 大角色 userrefrole表中的roleType=2
     */
    public static final String ROLETYPE_ROLE = "2";

    /**
     * 虚拟组 role表中的roleTypeId=3 或 userrefrole表中的roleType=3 这里虚拟组的概念上稍有些模糊,
     * 到底是用来区分大角色类型(流程or系统or虚拟)的, 还是用来区分角色种类(大角色or子角色or虚拟)的,不是很明确
     * 虚拟组是用来设置是否有子角色区分度的，是否应该修改为大角色的一个属性
     */
    public static final String ROLETYPE_VIRTUAL = "3";


    /**
     * 角色中的普通员工
     */
    public static final String groupType_common = "1";

    /**
     * 角色中的负责人(组长)
     */
    public static final String groupType_leader = "2";

    public static final String TEXT_LEADER = "组长";

    public static final String TEXT_VIRTUAL_SUFFIX = " (虚拟组)";

    /**
     * 角色导入上传符件码
     */
    public static final String ROLE_ACCESSORIES_APP_ID = "11";

    /**
     * 角色导入虚拟组标记，是虚拟组
     */
    public static final String ROLE_IMPORT_GROUP = "Y";

    /**
     * 角色导入虚拟组标记，不是虚拟组
     */
    public static final String ROLE_IMPORT_NO_GROUP = "N";

    /**
     * 无子角色id
     */
    public static final String NOSUBROLE_ROLEID = "-1111";
}
