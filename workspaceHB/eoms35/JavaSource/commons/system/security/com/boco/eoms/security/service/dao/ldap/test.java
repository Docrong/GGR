package com.boco.eoms.security.service.dao.ldap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2003-9-27
 * Time: 9:31:09
 * To change this template use Options | File Templates.
 */
public class test {
    public static void main(String[] args)
    {
        String relationship = "perm1::perm2::perm2::perm3::perm1::perm4-::-range1::range2::range2";
        String[] permission = null;
        String[] range = null;
        String permTemp = new String();
        String rangeTemp = new String();
        Set permissionSet = new HashSet();
        Set rangeSet = null;

        int permPos = relationship.indexOf("-::-");
        permTemp = relationship.substring(0,permPos);
        System.out.println("The perm string is [" + permTemp + "]");
        permission = permTemp.split("::");
        for(int i=0; i<permission.length; i++)
        {
            System.out.println(i + " =[" + permission[i] + "]");
            permissionSet.add(permission[i]);
        }
//        permissionSet.add("perm1");
//        permissionSet.add("perm2");
//        permissionSet.add("perm2");

        System.out.println("The permission set count is: " + permissionSet.size());
        Iterator it = permissionSet.iterator();
        for(int i=0; i<permissionSet.size(); i++)
        {
            System.out.println("permission" + i + "=[" + it.next().toString() + "]");
        }

        int position = relationship.indexOf("-::-");
        rangeTemp = relationship.substring(position+4,relationship.length());
        System.out.println("The range string is [" + rangeTemp + "]");
        range = rangeTemp.split("::");
        for(int j=0; j<range.length; j++)
        {
            System.out.println(j + " = [" + range[j] + "]");
        }

        System.out.println("Successfully");
    }
}
