/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.AsyncContext;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.ejava.ca.business.PeopleBean;

/**
 *
 * @author rzhao
 */
public class CreatePeopleTask implements Runnable{
    
    private PeopleBean peopleBean;
    private String name;
    private String email;
    private AsyncResponse async;
    
    public void setPeopleData(String name, String email, PeopleBean peopleBean){
        this.name = name;
        this.email = email;
        this.peopleBean = peopleBean;
    }
    
    public void setAsyncResponse(AsyncResponse async){
        this.async = async;
    }
    
    @Override
    public void run() {
        
        System.out.println("--> Starting CreatePeopleTask Thread ");
        
//        try {
//            Thread.currentThread().sleep(5000);
//            } catch (InterruptedException ex){ }
        
        peopleBean.add(name, email);
        
        JsonObject json = Json.createObjectBuilder()
				.add("name", name)
				.add("email", email)
				.build();
        
        Response resp = (Response.status(Response.Status.CREATED)
                .entity(json)
                .build());
        
        System.out.println("Request is completed, resume!");
        
        this.async.resume(resp);
    }
    
    
}
