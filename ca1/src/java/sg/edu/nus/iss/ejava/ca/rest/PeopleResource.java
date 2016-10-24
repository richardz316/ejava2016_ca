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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.ejava.ca.business.PeopleBean;

/**
 *
 * @author rzhao
 */
@RequestScoped
@Path("/people")
public class PeopleResource {
    @EJB private PeopleBean peopleBean;

    @Resource(mappedName = "concurrent/myThreadPool")
    private ManagedExecutorService executors;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void createPeople(MultivaluedMap<String, String> formData
        , @Suspended AsyncResponse async) {
        String name = formData.getFirst("name");
	String email = formData.getFirst("email");
        
        CreatePeopleTask cpTask = new CreatePeopleTask();
        cpTask.setPeopleData(name, email, peopleBean);
        cpTask.setAsyncResponse(async);
        
        executors.execute(cpTask);
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void findPeopleByEmail(@Context UriInfo info, @Suspended AsyncResponse async) {
        
        String email = info.getQueryParameters().getFirst("email");        
        
        FetchPeopleTask fptTask = new FetchPeopleTask();
        fptTask.setPeopleData(email, peopleBean);
        fptTask.setAsyncResponse(async);
        
        executors.execute(fptTask);
    }
 
    
    
}
