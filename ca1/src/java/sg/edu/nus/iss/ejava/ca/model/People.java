package sg.edu.nus.iss.ejava.ca.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author E0015387
 */
@NamedQuery(name = "People.findByEmail", 
		query = "select p from People p where p.email = :email")
@Entity
public class People implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String pid;
    
    private String name;
    private String email;
    
    @OneToMany(mappedBy = "people")
    private List<Appointment> appointments;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    
    public JsonObject toJSON() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("pid", pid)
                .add("name", name)
                .add("email", email);

        return builder.build();

    }
}
