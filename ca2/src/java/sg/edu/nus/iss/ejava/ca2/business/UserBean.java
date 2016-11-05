/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.ejava.ca2.model.Users;

/**
 *
 * @author rzhao
 */

@Stateless
public class UserBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public void add(String name, String password) {
        Users users = new Users();
        
        users.setUserid(UUID.randomUUID().toString().substring(0, 32));
        users.setUsername(name);
        users.setPassword(password);
        
        em.persist(users);
    }
    

}
