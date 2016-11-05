/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
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
    
    @Resource SessionContext ctx;
    
    public void add(String userid, String password) {
        Users users = new Users();
        
        
        
        users.setUserid(userid);
        users.setPassword(password);
        
        try {
            em.persist(users);
        }
        
        catch(Exception e) {
            ctx.setRollbackOnly();
        }
    }
    

}
