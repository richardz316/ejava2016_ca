/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca.rest;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.ejava.ca.business.AppointmentBean;

/**
 *
 * @author benjaminng
 */
@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @EJB private AppointmentBean appointmentBean; 
    
    @Resource(mappedName = "concurrent/myThreadPool")
    private ManagedExecutorService executors;
    
    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void findAppointmentByEmail(@PathParam("email") String email, @Suspended AsyncResponse async) {      
        
        FetchAppointmentTask fptTask = new FetchAppointmentTask(); 
        fptTask.setPeopleData(email, appointmentBean);
        fptTask.setAsyncResponse(async);
        
        executors.execute(fptTask);
        
        
//        Optional<List<Appointment>> appointmentList = appointmentBean.getAllAppointmentByEmail(email);
//      
//        if (appointmentList.isPresent()) {            
//            JsonArrayBuilder jsonArray = Json.createArrayBuilder(); 
//            List<Appointment> appointments = appointmentList.get(); 
//            for(Appointment appointment: appointments) {
//                jsonArray.add(appointment.toJSON());
//            }           
//            
//            return (Response.ok(jsonArray.build()).build());
//        }
//        
//        return (Response.status(Response.Status.NOT_FOUND)
//                .entity("Email not found:" + email)
//                .build());
    }
    
    
    
    
}
