package com.zfx.websocket;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.zfx.entity.User;
import com.zfx.service.UserService;
import net.sf.json.JSONObject;

@ServerEndpoint("/websocket")
public class WebSocket {
    private static final UserService userService = new UserService();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");	// 日期格式化

    public WebSocket(){
        System.out.println("WebSocket对象创建");
    }
    @OnOpen
    public void open(Session session) {
        // 添加初始化操作
        System.out.println("已经连接到客户" + session.getId());
    }
    /**
     * 接受客户端的消息，并把消息发送给所有连接的会话
     * @param message 客户端发来的消息
     * @param session 客户端的会话
     */
    @OnMessage
    public void getMessage(String message, Session session) {
        // 把客户端的消息解析为JSON对象
        JSONObject jsonObject = JSONObject.fromObject(message);
        // 在消息中添加发送日期
        int statu = jsonObject.getInt("statu");
        if (statu==1) {
            jsonObject.put("date", DATE_FORMAT.format(new Date()));
            jsonObject.put("putstatu",0);
            // 把消息发送给所有连接的会话
            for (Session openSession : session.getOpenSessions()) {//遍历用户
                // 检测是否为本次会话所发送的标志，并添加到JSON对象里
                jsonObject.put("isSelf", openSession.equals(session));
                // 发送JSON格式的消息
                openSession.getAsyncRemote().sendText(jsonObject.toString());
            }
            System.out.println(message);
        } else if(statu==0){
            //当用户登陆后向所有用户通知该用户已经登陆的消息
            userService.bindsessionid(session.getId(),jsonObject.getString("username"));
            userService.userLogin(jsonObject.getString("username"));
            JSONObject userlog = new JSONObject();
            userlog.put("putstatu",1);
            userlog.put("username",jsonObject.getString("username"));
            for(Session open:session.getOpenSessions()){
                open.getAsyncRemote().sendText(userlog.toString());
            }
        }

    }
    @OnClose
    public void close(Session session) {
        // 添加关闭会话时的操作
        String sessionid = session.getId();
        userService.userlogout(sessionid);
        System.out.println("断开连接"+session.getId());

    }
    @OnError
    public void error(Throwable t) {
        // 添加处理错误的操作
        System.out.println("出错,错误信息"+t);
    }
}
