package sg.edu.nus.iss.ejava.ca.model;


import java.io.Serializable;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author E0015387
 */
@NamedQuery(name = "Appointment.findByEmail", 
		query = "select a from People p join p.appointments a where p.email = :email")
@Entity
public class Appointment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @Column(name = "appt_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int apptId;
    
    private String description;
    
    @Column(name = "appt_date")
    private Date apptDate;
    
    @ManyToOne
    @JoinColumn( name = "pid", referencedColumnName = "pid")
    private People people;

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
    
    public JsonObject toJSON() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("dateTime", apptDate.toString())
                .add("description", description);

        return builder.build();

    }

}
