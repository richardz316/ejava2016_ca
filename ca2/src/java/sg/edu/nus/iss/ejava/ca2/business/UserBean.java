/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ejava.ca2.model.Users;

/**
 *
 * @author rzhao
 */
public class UserBean {
    @PersistenceContext
    private EntityManager em;
    
    public void add(String name, String email) {
        Users users = new Users();
//        user.
        
//        em.persist(user);
    }
    

}
