package sg.edu.nus.iss.ejava.ca2.web;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@RequestScoped
@ServerEndpoint("/note/{category}")
public class NoteEndpoint {

    @Inject SessionStore sessionStore;

    @OnOpen
    public void open(Session session, @PathParam("category") String category) {

        sessionStore.add(category, session);
        System.out.println(">>> category: " + category);
        System.out.println(">>> session id: " + session.getId());
    }

    @OnMessage
    public void message(Session session, String text, @PathParam("category") String category) {
        System.out.println(">>> client sent a message: " + text);
        sessionStore.sendToAllConnectedSessions(category, text);
    }

    @OnClose
    public void onClose(Session session, @PathParam("category") String category) throws IOException {
       System.out.println(">>> on Close. ");
       sessionStore.remove(category, session);
    }
}
