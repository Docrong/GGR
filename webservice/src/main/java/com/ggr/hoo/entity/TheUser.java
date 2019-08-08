package com.ggr.hoo.entity;

import java.io.Serializable;

public class TheUser implements Serializable {

    /**
     * �ṩ���л���ţ�Զ�̵���ʱҪ�õ�
     */
    private static final long serialVersionUID = -971720598087640397L;

    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "username=" + username + " ;age=" + age;
    }

}
