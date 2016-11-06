/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    
    public Optional<List<Notes>> findByUserId(String userId){
        TypedQuery<Notes> query = em.createNamedQuery("Notes.findByUserid", Notes.class);
        query.setParameter("userid", userId);
        
        return (Optional.ofNullable(query.getResultList()));       
    }
    
    public Optional<List<Notes>> findAllOrCategory(String category){
        return "All".equals(category) ? findAll() : findByCategory(category);
    }
    
    private Optional<List<Notes>> findByCategory(String category){
        TypedQuery<Notes> query = em.createNamedQuery("Notes.findByCategory", Notes.class);
        query.setParameter("category", category);
        
        return (Optional.ofNullable(query.getResultList()));       
    }
    
    private Optional<List<Notes>> findAll(){
        TypedQuery<Notes> query = em.createNamedQuery("Notes.findAll", Notes.class);
        return (Optional.ofNullable(query.getResultList()));       
    }
}
