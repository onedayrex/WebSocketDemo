package com.zfx.servlet;

import com.zfx.entity.User;
import com.zfx.result.UserResult;
import com.zfx.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by onedayrex on 2016/3/4.
 */
public class MainServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if(path.equals("/login.do")){
            req.setCharacterEncoding("UTF-8");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            UserResult result = userService.checkuser(username,password);
            resp.setContentType("text/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            JSONObject object = JSONObject.fromObject(result);
            out.print(object);
            out.close();
        }else if(path.equals("/loaduser.do")){
            UserResult result = userService.findLoginUser();
            resp.setContentType("text/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            JSONObject object = JSONObject.fromObject(result);
            out.print(object);
            out.close();
        }
    }
}
