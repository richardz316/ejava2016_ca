/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.rest;

import epod.business.PodBean;
import epod.model.Pod;
import java.util.List;
import java.util.Optional;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author E0015387
 */
public class FetchItemTask implements Runnable {
    
    private PodBean podBean;
    private AsyncResponse async;
    
    public void setPodData(PodBean podBean){
        this.podBean = podBean;
    }
    
    public void setAsyncResponse(AsyncResponse async){
        this.async = async;
    }
    
    @Override
    public void run() {
        
        System.out.println("--> Starting FetchItemTask Thread ");
        
        Response resp;
        
        Optional<List<Pod>> opt = podBean.findAll();
        if (opt.isPresent()) {
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        
            opt.get().stream()
                    .map(p -> {return(p.toJSON());})
                    .forEach(j -> {arrBuilder.add(j);});
            resp = Response.ok(arrBuilder.build()).build();
        }
        
        else {
            resp = Response.status(Response.Status.NOT_FOUND)
                        .entity("No Item:")
                        .build();
        }
        
        this.async.resume(resp);
    }
    
}
