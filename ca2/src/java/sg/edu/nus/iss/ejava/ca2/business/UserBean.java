/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.business;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    public void add(String userid, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Users users = new Users();
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String output = bigInt.toString(16);
        
        users.setUserid(userid);
        users.setPassword(output);
        
        try {
            em.persist(users);
        }
        
        catch(Exception e) {
            ctx.setRollbackOnly();
        }
    }
    

}
