/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.model;

import java.io.Serializable;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author E0015387
 */
@Entity
public class Notes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private int noteid;
    
    private String userid;
    
    private String title;
    private String category;
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date postdate;

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }
    
    public JsonObject toJSON() {
        return (Json.createObjectBuilder()
            .add("Title", title)
            .add("Posted", postdate.toString())
            .add("User", userid)
            .add("Content", content)
            .build());
    }
}
