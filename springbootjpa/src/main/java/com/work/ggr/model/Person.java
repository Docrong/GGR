package com.work.ggr.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author : gr
 * @date : 2019/8/29 8:56
 */
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(generator = "system-uuid")//JPA通用策略生成器
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")//自定义主键生成策略
    @Column(name="id",unique = true)
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "remark")
    private String remark;
    @Column(name = "created")
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
