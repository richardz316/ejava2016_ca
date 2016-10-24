/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca.rest;

import java.util.List;
import java.util.Optional;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.ejava.ca.business.AppointmentBean;
import sg.edu.nus.iss.ejava.ca.model.Appointment;

/**
 *
 * @author yeojc
 */
public class FetchAppointmentTask implements Runnable {
    
    private AppointmentBean appointmentBean;
    private String email;
    private AsyncResponse async;
    
    public void setPeopleData(String email, AppointmentBean appointmentBean){
        this.email = email;
        this.appointmentBean = appointmentBean;
    }
    
    public void setAsyncResponse(AsyncResponse async){
        this.async = async;
    }
    
    @Override
    public void run() {        
        System.out.println("--> Starting FetchAppointmentTask Thread ");
        
        Optional<List<Appointment>> appointmentList = appointmentBean.getAllAppointmentByEmail(email);
        Response resp; 
         
        if (appointmentList.isPresent()) {            
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();         
            
            for(Appointment appointment: appointmentList.get()) {
                jsonArray.add(appointment.toJSON());
            }    
            
            //JsonObject appointments = Json.createObjectBuilder().add("appointments", jsonArray.build()).build();    
            
            resp = Response.ok(jsonArray.build()).build();
        }
        else {
            resp = Response.status(Response.Status.NOT_FOUND)
                .entity("Email not found:" + email)
                .build();
        }              

        this.async.resume(resp);
    }
    
    
}
