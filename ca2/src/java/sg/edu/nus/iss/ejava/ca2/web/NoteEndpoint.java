package sg.edu.nus.iss.ejava.ca2.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import sg.edu.nus.iss.ejava.ca2.business.NoteBean;
import sg.edu.nus.iss.ejava.ca2.model.Notes;

@RequestScoped
@ServerEndpoint("/note/{category}")
public class NoteEndpoint {

    @Inject SessionStore sessionStore;
    
    @EJB private NoteBean noteBean;

    @OnOpen
    public void open(Session session, @PathParam("category") String category) {
        
        sessionStore.lock(() -> { 
            sessionStore.add(category, session);
            System.out.println(">>> category: " + category);
            System.out.println(">>> session id: " + session.getId());

            Optional<List<Notes>> notes = noteBean.findAllOrCategory(category);
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

            List<Notes> sortedNotes = notes.get();
            sortedNotes.sort((n1,n2) -> n2.getPostdate().compareTo(n1.getPostdate()));
            sortedNotes.stream().map(c -> {return(c.toJSON());})
                .forEach(j -> {arrBuilder.add(j);});

            final JsonObject message = Json.createObjectBuilder()
                                            .add("message", arrBuilder.build().toString())
                                            .build();
            sessionStore.sendToSession(session, message);
        });
    }

    @OnMessage
    public void message(Session session, String text, @PathParam("category") String category) {
        System.out.println(">>> client sent a message: " + text);
        sessionStore.lock(() -> { sessionStore.sendToAllConnectedSessions(category, text); });
    }

    @OnClose
    public void onClose(Session session, @PathParam("category") String category) throws IOException {
       System.out.println(">>> on Close. ");
       sessionStore.lock(() -> { sessionStore.remove(category, session); });
    }
}
