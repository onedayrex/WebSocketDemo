package com.zfx.entity;

/**
 * Created by onedayrex on 2016/3/4.
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String loginstatu;
    private String sessonid;

    public String getLoginstatu() {
        return loginstatu;
    }

    public void setLoginstatu(String loginstatu) {
        this.loginstatu = loginstatu;
    }

    public String getSessonid() {
        return sessonid;
    }

    public void setSessonid(String sessonid) {
        this.sessonid = sessonid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
