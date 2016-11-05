/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.ejava.ca2.model.Notes;

/**
 *
 * @author rzhao
 */
@Stateless
public class NoteBean {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource SessionContext ctx;
    
    public void add(Notes note){
        em.persist(note);
    }
    
}
