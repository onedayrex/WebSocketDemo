package com.zfx.dao;

import com.zfx.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by onedayrex on 2016/3/4.
 */
public interface UserDao {

    public User findByUserName(String username);

    public List<String> findLoginUser();

    public void userLogined(String username);

    public void userLoginout(String sessionid);

    public void bindsessionid(@Param(value = "sessionid") String sessionid, @Param(value = "username") String username);
}
