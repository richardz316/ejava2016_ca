/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.rest;

import epod.business.PodBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author E0015387
 */
@RequestScoped
@Path("/items")
public class ItemResource {
    
    @EJB private PodBean podBean;
    
    @Resource(mappedName = "concurrent/myThreadPool")
    private ManagedExecutorService executors;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getAllItems(@Suspended AsyncResponse async) { 
        FetchItemTask fetchTask = new FetchItemTask(); 
        fetchTask.setPodData(podBean);
        fetchTask.setAsyncResponse(async);
        
        executors.execute(fetchTask);
    }
}
