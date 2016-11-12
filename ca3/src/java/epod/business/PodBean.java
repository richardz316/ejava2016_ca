/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.business;

import epod.exception.PodNotFoundException;
import epod.model.Delivery;
import epod.model.Pod;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author rzhao
 */
@Stateless
public class PodBean {

    @PersistenceContext
    private EntityManager em;

    @Resource
    SessionContext ctx;

    public Optional<List<Pod>> findAll() {
        TypedQuery<Pod> query = em.createNamedQuery("Pod.findAll", Pod.class);
        return (Optional.ofNullable(query.getResultList()));
    }

    public Pod findPod(int podId) {
        return em.find(Pod.class, podId);
    }

    public List<Pod> checkAckId() {
        TypedQuery<Pod> query = em.createNamedQuery("Pod.checkAckId", Pod.class);
        return query.getResultList();
    }

    public void create(Delivery delivery) {
        Pod pod = new Pod();
        pod.setDelivery(delivery);
        em.persist(pod);
    }

    public void savePod(int podId, String note, byte[] image, Date time) throws PodNotFoundException {

        Pod pod = findPod(podId);
        if (pod == null) {
            throw new PodNotFoundException("Pod is not Found!");
        }

        pod.setNote(note);
        pod.setImage(image);
        pod.setDeliveryDate(time);

    }

    public void updateAckId(Pod pod) {
        em.merge(pod);
    }
    
    public void sendToHq(Pod pod) {
        
        System.out.println("--> Starting sendToHq");
        
        Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

	byte[] buffer = pod.getImage();
        
	//Add other fields
	MultiPart formData = new FormDataMultiPart()
			.field("teamId", "41b26e1c", MediaType.TEXT_PLAIN_TYPE)
			.field("podId", pod.getPodId(), MediaType.TEXT_PLAIN_TYPE)
                        .field("callback", "http://10.10.24.213:8080/ca3/callback", MediaType.TEXT_PLAIN_TYPE)
                        .field("note", pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
			.field("image", buffer, MediaType.APPLICATION_OCTET_STREAM_TYPE);
	formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

	WebTarget target = client.target("http://10.10.0.48:8080/epod/upload");
	Invocation.Builder inv = target.request();

	Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

	System.out.println(">> call resp:" + callResp.getStatus());

    }
}
