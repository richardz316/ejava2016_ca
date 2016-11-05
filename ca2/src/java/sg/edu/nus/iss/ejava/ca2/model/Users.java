package sg.edu.nus.iss.ejava.ca2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author E0015387
 */

@Entity
public class Users implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String userid;
    
    private String username;
    private String password;
    
    @OneToOne(mappedBy = "users")
    private Groups groups;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    
    
    
}
