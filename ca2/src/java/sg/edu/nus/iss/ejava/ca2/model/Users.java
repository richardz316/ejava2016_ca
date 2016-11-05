package sg.edu.nus.iss.ejava.ca2.model;

import java.io.Serializable;

/**
 *
 * @author E0015387
 */
public class Users implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String userid;
    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
