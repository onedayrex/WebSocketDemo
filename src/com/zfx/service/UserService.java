package com.zfx.service;

import com.zfx.dao.UserDao;
import com.zfx.entity.User;
import com.zfx.result.UserResult;
import com.zfx.util.SessionUtil;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by onedayrex on 2016/3/4.
 */
public class UserService {
    private SqlSession session = null;

    public UserResult checkuser(String username, String password){
        UserResult result = new UserResult();
        session = SessionUtil.getSession();
        UserDao dao = session.getMapper(UserDao.class);
        User user = dao.findByUserName(username);
        if(user==null){
            result.setStatu(1);
            result.setMsg("没有此用户");
        }else if(!user.getPassword().equals(password)){
            result.setStatu(2);
            result.setMsg("用户密码错误");
        }else if(user.getLoginstatu().equals("1")){
            result.setStatu(3);
            result.setMsg("用户已在其它地方登陆");
        } else {
            result.setStatu(0);
            result.setObject(user);
        }
        session.commit();
        return result;
    }

    public UserResult findLoginUser(){
        UserResult result = new UserResult();
        session = SessionUtil.getSession();
        UserDao dao = session.getMapper(UserDao.class);
        List<String> list = dao.findLoginUser();
        result.setObject(list);
        session.commit();
        return result;
    }

    public void bindsessionid(String sessionid,String username){
        session = SessionUtil.getSession();
        UserDao dao = session.getMapper(UserDao.class);
        dao.bindsessionid(sessionid,username);
        session.commit();
    }

    public void userlogout(String sessionid){
        session = SessionUtil.getSession();
        UserDao dao = session.getMapper(UserDao.class);
        dao.userLoginout(sessionid);
        session.commit();
    }

    public void userLogin(String username){
        session = SessionUtil.getSession();
        UserDao dao = session.getMapper(UserDao.class);
        dao.userLogined(username);
        session.commit();
    }
}
