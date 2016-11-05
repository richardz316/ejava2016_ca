/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.ejava.ca2.model.Notes;

/**
 *
 * @author rzhao
 */
public class NoteBean {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource SessionContext ctx;
    
    public void add(String userid, String title, String category, String content, Date postdate){
        
        Notes notes = new Notes();
        notes.setUserid(userid);
        notes.setTitle(title);
        notes.setCategory(category);
        notes.setContent(content);
        notes.setPostdate(postdate);
        
        em.persist(notes);
    }
    
}
