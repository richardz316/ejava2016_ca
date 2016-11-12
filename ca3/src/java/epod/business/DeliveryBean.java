/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.business;

import epod.model.Delivery;
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
 * @author E0015387
 */
@Stateless
public class DeliveryBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public Optional<List<Delivery>> getAllDelivery() {
        TypedQuery<Delivery> query = em.createNamedQuery(
				"Delivery.findAll", Delivery.class);
        return Optional.ofNullable(query.getResultList());
    }
    
}
