package com.zfx.dao;

import com.zfx.entity.User;
import com.zfx.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by onedayrex on 2016/3/4.
 */
public class TestCase {
    @Test
    public void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        UserDao dao = session.getMapper(UserDao.class);
        System.out.println(dao.findByUserName("tom").getId());

//        List<String> list = dao.findLoginUser();
//        for (String u:list){
//            System.out.println(u);
//        }
        dao.userLogined("tom");
        dao.userLoginout("3");
        session.commit();
    }

    @Test
    public void testuser(){
        UserService service = new UserService();
        service.userlogout("0");
    }
}
