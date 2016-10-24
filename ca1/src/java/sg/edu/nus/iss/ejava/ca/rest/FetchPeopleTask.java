/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca.rest;

import java.util.Optional;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.ejava.ca.business.PeopleBean;
import sg.edu.nus.iss.ejava.ca.model.People;

/**
 *
 * @author benjaminng
 */
public class FetchPeopleTask implements Runnable {
    
    private PeopleBean peopleBean;
    private String email;
    private AsyncResponse async;
    
    public void setPeopleData(String email, PeopleBean peopleBean){
        this.email = email;
        this.peopleBean = peopleBean;
    }
    
    public void setAsyncResponse(AsyncResponse async){
        this.async = async;
    }
    
    @Override
    public void run() {
        
        System.out.println("--> Starting FetchPeopleTask Thread ");
        
        Response resp;
        
        Optional<People> opt = peopleBean.getPeopleByEmail(email);
        if (opt.isPresent()) {
            resp = Response.ok(opt.get().toJSON()).build();
        }
        
        else {
            resp = Response.status(Response.Status.NOT_FOUND)
                        .entity("people not found:" + email)
                        .build();
        }
        
        this.async.resume(resp);
    }
}
