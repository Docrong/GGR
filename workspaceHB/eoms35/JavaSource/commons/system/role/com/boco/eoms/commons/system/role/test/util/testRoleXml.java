/*
 * Created on 2007-9-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlManagerImpl;
import com.boco.eoms.commons.system.role.util.*;

/**
 * @author IBM
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class testRoleXml extends ConsoleTestCase {

    public void testCreateRoleXml() {

        SubRoleSchema subRoleSchema = new SubRoleSchema();
        String[] businessNames = new String[]{"class1", "class2", "class3",
                "class4"};
        for (int i = 1; i < 5; i++) {
            SubRoleMapping m1 = new SubRoleMapping();
            m1.setBusinessName(businessNames[i - 1]);
            m1.setNum(new Integer(i));
            m1.setColumnName("type" + i);
            m1.setPojoName("TawSystemDictItem");
            m1.setPojoPrimaryKey("dictId");
            subRoleSchema.addSubRoleMapping(m1);
        }

        try {
            ParseXmlManagerImpl impl = new ParseXmlManagerImpl();
            impl
                    .object2xml(
                            subRoleSchema,
                            "classpath:com/boco/eoms/commons/system/role/util/SubRoleMapping.xml",
                            "classpath:config/eomsRoleSchema.xml", false);
        } catch (ParseXMLException e) {

            e.printStackTrace();
            fail();
        }
    }

    public void testReadRoleXml() throws Exception {
        try {
            ParseXmlManagerImpl impl = new ParseXmlManagerImpl();
            SubRoleSchema subRoleSchema = (SubRoleSchema) impl
                    .xml2object(
                            SubRoleSchema.class,
                            "classpath:com/boco/eoms/commons/system/role/util/SubRoleMapping.xml",
                            "classpath:config/eomsRoleSchema.xml", false);
            assertNotNull(subRoleSchema);
            String typeName = RoleXmlSchema.getInstance().getColumnName(
                    RoleConstants.SUBROLE_BUSINESS1);
            assertTrue("type1".equals(typeName));

            String buzinessName = RoleXmlSchema.getInstance().getBusinessName(
                    RoleConstants.SUBROLE_TYPE1);
            assertTrue("class1".equals(buzinessName));

        } catch (ParseXMLException e) {
            e.printStackTrace();
            logger.error(e);

            fail("读取配置文件出错");
        }
    }

    public void testCreateRoleMapXml() {

        RoleMappings rm = new RoleMappings();
        List list = new ArrayList();
        for (int i = 1; i < 3; i++) {
            RoleModel m1 = new RoleModel();
            m1.setCondition("111");
            m1.setFilePath("");
            m1.setModelId("333");
            m1.setModelName("eeee");
            list.add(m1);

            List list2 = new ArrayList();
            for (int j = 0; j < 3; j++) {
                RoleFilter m2 = new RoleFilter();
                m2.setBusinessName("rrrrr");
                m2.setDictId("222");
                m2.setHtmlType("etqq");
                m2.setName("ttttttt");
                m2.setSub("class2");
                list2.add(m2);

                List list3 = new ArrayList();
                SheetInfo si = new SheetInfo();
                si.setName("11");
                si.setStyleId("22");
                list3.add(si);
                m2.setSheetInfo(list3);

            }
            m1.setRoleFilter(list2);

        }
        rm.setRoleModel(list);

        try {
            ParseXmlManagerImpl impl = new ParseXmlManagerImpl();
            impl
                    .object2xml(
                            rm,
                            "classpath:com/boco/eoms/commons/system/role/util/RoleMapping.xml",
                            "classpath:config/roleConfigTest.xml", false);
        } catch (ParseXMLException e) {

            e.printStackTrace();
            fail();
        }
    }

    public void testLoadRoleMapXml() throws Exception {
        try {
            ParseXmlManagerImpl impl = new ParseXmlManagerImpl();
            List filters = RoleMapSchema.getInstance().getRoleMappingListById(
                    "1");
            assertNotNull(filters);

        } catch (ParseXMLException e) {
            e.printStackTrace();
            logger.error(e);

            fail("读取配置文件出错");
        }
    }

    public void testGetStyle() {
        try {
            ParseXmlManagerImpl impl = new ParseXmlManagerImpl();
            HashMap filters = RoleMapSchema.getInstance().getStyleIDsBySheet(
                    "01");
            assertNotNull(filters);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);

            fail("读取配置文件出错");
        }

    }
}
