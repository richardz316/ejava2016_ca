/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.business;

import epod.model.Pod;
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
    
    @Resource SessionContext ctx;
    
    public Optional<List<Pod>> findAll(){
        TypedQuery<Pod> query = em.createNamedQuery("Pod.findAll", Pod.class);
        return (Optional.ofNullable(query.getResultList()));       
    }
    
    public Optional<List<Pod>> findByPodId(String podId){
        TypedQuery<Pod> query = em.createNamedQuery("Pod.findByPodId", Pod.class);
        query.setParameter("podId", podId);
        
        return (Optional.ofNullable(query.getResultList()));       
    }
    
}
