/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import msg.Msge;


//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;
//import javax.mail.*;
import org.apache.tomcat.util.log.*;
//import HTMLFilter.*;

/**
 *
 * @author ZJX
 */
@ServerEndpoint("/chat")
public class chat {
    
//}
/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
//package websocket.chat;




//public class chat {

//    private static final Log log = LogFactory.getLog(chat.class);

    private static final String GUEST_PREFIX = "Gguest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<chat> connections =new CopyOnWriteArraySet<chat>();

    private final String nickname;
    private Session session;
    private Msge msg;

    public chat() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
        msg = new Msge();
    }


    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        Msge m = new Msge();
        m.setType(0);
        m.setFrom(nickname);
        m.setContent("已经上线");
        String message = getJsonMessage(m);
//        String message = String.format("* %s %s", nickname, "has joined.");
        System.out.println(message);
        broadcast(message);
    }


    @OnClose
    public void end() {
        connections.remove(this);
        Msge m = new Msge();
        m.setType(-1);
        m.setFrom(nickname);
        m.setContent("离线");
        
//        String message = String.format("* %s %s",nickname, "has disconnected.");
        String message = getJsonMessage(m);
        broadcast(message);
    }


    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        JSONObject jo = JSON.parseObject(message);
        msg.setFrom(jo.getString("from"));
        msg.setTo(jo.getString("to"));
        msg.setContent(jo.getString("content"));
        msg.setType(jo.getIntValue("type"));
        msg.setClient(this);
//        String filteredMessage = String.format("%s: %s",nickname, message.toString());
        
//        String filteredMessage = String.format("%s: %s",msg.getFrom(), msg.getContent());
//        System.out.println(filteredMessage);
//        broadcast(filteredMessage);
            broadcast(message);
//            broadcast("aaaa");

    }




    @OnError
    public void onError(Throwable t) throws Throwable {
//        log.error("Chat Error: " + t.toString(), t);
    }


    private static void broadcast(String msg) {
        for (chat client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
//                log.debug("Chat Error: Failed to send Msge to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }
    
    public String getJsonMessage(Msge message){
        //使用JSONObject方法构建Json数据
        JSONObject jsonObjectMessage = new JSONObject();
        jsonObjectMessage.put("from", String.valueOf(message.getFrom()));
        jsonObjectMessage.put("to", new String[] {String.valueOf(message.getTo())});
        jsonObjectMessage.put("content", String.valueOf(message.getContent()));
        jsonObjectMessage.put("type", String.valueOf(message.getType()));
//        jsonObjectMessage.put("time", message.getTime().toString());
        return jsonObjectMessage.toString();
    }
    
    public Msge setJsonMessage(String s)
    {
        JSONObject js = JSON.parseObject(s);
        
        msg.setFrom(js.getString("from"));
        msg.setTo(js.getString("to"));
        msg.setContent(js.getString("content"));
        msg.setType(js.getIntValue("type"));
        msg.setClient(this);
        return msg;
    }
}

