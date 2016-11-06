/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;

/**
 *
 * @author rzhao
 */
@ApplicationScoped
public class SessionStore implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Map<String, List<Session>> categories = new HashMap<>();
    
    private final Lock lock = new ReentrantLock(); //

    public void add(String category, Session session) {
        
        List<Session> allSessions = categories.computeIfAbsent(category, s -> new LinkedList<>());
        allSessions.add(session);
        
        /*List<Session> sessionsForAll = categories.get("All");
        
        //Create category for categoryType "ALL" if not created yet
        if(sessionsForAll == null){
            sessionsForAll = new ArrayList<>();
            categories.put("All", sessionsForAll);
        }
        
        //Add the session into categoryType "ALL" if session is for other category
        if(!"All".equalsIgnoreCase(category)){
            sessionsForAll.add(session);
        }*/
        
    }
    
     public void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
            System.out.println(">>> Server sent a message to client: "+ message);
        } catch (IOException ex) {
            Logger.getLogger(CreateNoteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendToAllConnectedSessions(String category, JsonObject message) {
        List<Session> sessions = categories.get(category);
        if(sessions != null && sessions.size() > 0){
            sessions.stream().forEach((session) -> {
                sendToSession(sessions, session, message.toString());
            });
        }
    }

    private void sendToSession(List<Session> sessions, Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
            System.out.println(">>> Server sent a message to client, " +  session.getId() + ": "+ message);
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(CreateNoteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(String category,Session session){
        List<Session> sessions = categories.get(category);
        if(sessions != null){
            sessions.remove(session);
        }
    }
    
    public void lock(Runnable block) {
        lock.lock();
        try { block.run(); } finally { lock.unlock(); }
    }
}
