package com.ggr.blog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "blog_usertemp")
@GenericGenerator(name = "system-uuid",strategy = "uuid.hex")
public class UserTemp {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id",nullable = false,unique = true)
    private String id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "createtime")
    private String createtime;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
