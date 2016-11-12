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

    public void create(Delivery delivery) {
        Pod pod = new Pod();
        pod.setDelivery(delivery);
        em.persist(pod);
    }
    
     public void savePod(int podId, String note, byte[] image, Date time) throws PodNotFoundException{
        
        Pod pod = findPod(podId);
        if(pod == null){
            throw new PodNotFoundException("Pod is not Found!");
        }
        
        pod.setNote(note);
        pod.setImage(image);
        pod.setDeliveryDate(time);

        
    }
}
