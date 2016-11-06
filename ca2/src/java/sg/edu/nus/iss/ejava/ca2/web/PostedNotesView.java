/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.web;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ejava.ca2.business.NoteBean;
import sg.edu.nus.iss.ejava.ca2.model.Notes;

/**
 *
 * @author yeojc
 */
@ViewScoped
@Named
public class PostedNotesView implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private NoteBean noteBean;

    private String userName;
    private List<Notes> noteItems;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Notes> getNoteItems() {
        return noteItems;
    }

    public void setNoteItems(List<Notes> noteItems) {
        this.noteItems = noteItems;
    }

    @PostConstruct
    private void init() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        userName = p.getName();

        Optional<List<Notes>> userNotes = noteBean.findByUserId(userName);

        if (userNotes.isPresent()) {
            noteItems = userNotes.orElse(noteItems);
        } 
    }

}
