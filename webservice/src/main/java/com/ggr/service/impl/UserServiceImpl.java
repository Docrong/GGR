package com.ggr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ggr.model.User;

//https://cke7in.iteye.com/blog/935364
public class UserServiceImpl {

    /**
     * �ṩһ�������͵ķ��������ڱȶ�
     *
     * @param username
     * @return
     */

    public String getEasyEg(String username) {

        return "You write " + username;

    }


    /**
     * webservice�н�����һ��ʵ������
     *
     * @param username
     * @param age
     * @return User
     */

    public User getUser(String username, int age) {
        System.out.println(new Date() + "���ղ���:" + username + "," + age);
        User theu = new User();

        theu.setUsername(username);

        theu.setAge(age);

        return theu;


    }


    /**
     * webservice�з��� List<User> ����
     *
     * @param username
     * @param length
     * @return List User
     */

    public List<User> getUserList(String username, int size) {

        List<User> Userlist = new ArrayList<User>();


        for (int i = 0; i < size; i++) {

            User tuser = new User();

            tuser.setUsername(username + size);

            tuser.setAge(i);


            Userlist.add(tuser);

        }


        return Userlist;


    }


    /**
     * webservice�з��� User[]���� ����
     *
     * @param username
     * @param length
     * @return User[]
     */

    public User[] getUserGroup(String username, int length) {

        User[] usergroups = new User[length];

        for (int i = 0; i < length; i++) {

            User tuser = new User();

            tuser.setUsername(username + length);

            tuser.setAge(i);

            usergroups[i] = (User) tuser;

        }

        return usergroups;


    }


    /**
     * webservice�з��� Map<String,User>���� ����
     *
     * @return
     */

    public Map<String, User> getUserMap() {

        Map<String, User> Usermap = new LinkedHashMap<String, User>();

        User tusera = new User();

        User tuserb = new User();


        tusera.setAge(20);

        tuserb.setAge(20);


        tusera.setUsername("namea");

        tuserb.setUsername("nameb");


        Usermap.put("tusera", tusera);

        Usermap.put("tuserb", tuserb);

        return Usermap;


    }

}
