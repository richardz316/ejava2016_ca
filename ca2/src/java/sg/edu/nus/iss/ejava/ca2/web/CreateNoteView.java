/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.web;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import sg.edu.nus.iss.ejava.ca2.business.NoteBean;
import sg.edu.nus.iss.ejava.ca2.model.Notes;


@ViewScoped
@Named
public class CreateNoteView implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @EJB private NoteBean noteBean;
    
    @Inject Event<NotesEvent> events;
    
    private Notes note;
    private String title;
    private String category;
    private String content;
    private Date postdate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }
    
    public String createNote(){
        note = new Notes();
        note.setTitle(title);
        
        note.setCategory(category);
        note.setContent(content);
        note.setPostdate(postdate);
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        note.setUserid(p.getName());
        
        noteBean.add(note);
       
        events.fire(new NotesEvent(note.getCategory()));
      
        FacesMessage m = new FacesMessage("Note "+ title + " has been created!");
	FacesContext.getCurrentInstance().addMessage(null, m);
        
        return "menu";
    }
    

    
    

    

}
