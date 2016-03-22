package com.zfx.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by onedayrex on 2016/3/4.
 */
public class SessionUtil {
    public static SqlSession session;
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            session = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        return session;
    }
}
