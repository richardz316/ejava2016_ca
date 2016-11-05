/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.MANDATORY;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.ejava.ca2.model.Groups;

/**
 *
 * @author E0015387
 */

@Stateless
public class GroupBean {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource SessionContext ctx;
    
    @TransactionAttribute(MANDATORY)
    public void add(String groupid, String userid) {
        
        if (!ctx.getRollbackOnly()) {
            Groups groups = new Groups();
        
            groups.setGroupid(groupid);
            groups.setUserid(userid);
        
            em.persist(groups);
        }
        
    }
    
}
