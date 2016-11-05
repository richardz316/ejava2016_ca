/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.web;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ejava.ca2.business.NoteBean;
import sg.edu.nus.iss.ejava.ca2.model.Notes;


@ViewScoped
@Named
public class CreateNoteView implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @EJB private NoteBean noteBean;
    
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
        
        
        
        
        return null;
    }
    
    
}
