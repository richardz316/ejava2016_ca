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
	public void message(String text) {
            System.out.println(">>> client sent a message: " + text);
	}
        
        @OnClose
        public void onClose(Session session) throws IOException {
            System.out.println(">>> on Close. ");
           //TODO remove the session from category
        }
}
