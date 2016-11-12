/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.rest;

import epod.model.Pod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author E0015387
 */
public class EPodForwardHqTask implements Runnable {
    
    private Pod pod;
    
    public void setPodData(Pod pod){
        this.pod = pod;
    }
    
    @Override
    public void run() {
        
        System.out.println("--> Starting FetchItemTask Thread ");
        
        Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

	byte[] buffer = pod.getImage();
	BodyPart imgPart = new BodyPart(buffer, MediaType.APPLICATION_OCTET_STREAM_TYPE);
        
        imgPart.setContentDisposition(
			FormDataContentDisposition.name("image")
			.fileName("Desert.jpg").build());

	//Add other fields
	MultiPart formData = new FormDataMultiPart()
			.field("teamId", "41b26e1c", MediaType.TEXT_PLAIN_TYPE)
			.field("podId", pod.getPodId(), MediaType.TEXT_PLAIN_TYPE)
                        .field("callback", "http://192.168.56.1:8080/ca3/callback", MediaType.TEXT_PLAIN_TYPE)
                        .field("note", pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
			.bodyPart(imgPart);
	formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

	WebTarget target = client.target("http://10.10.0.48:8080/epod/upload");
	Invocation.Builder inv = target.request();

	Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

	System.out.println(">> call resp:" + callResp.getStatus());

    }
    
    
    
}
