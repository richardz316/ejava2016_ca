/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.web;

import epod.business.DeliveryBean;
import epod.model.Delivery;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author E0015387
 */
@ViewScoped
@Named
public class DeliveryView implements Serializable {

    @EJB
    private DeliveryBean deliveryBean;
    
    private List<Delivery> deliveryItems;
    
    @PostConstruct
    private void init() {

        Optional<List<Delivery>> deliveries = deliveryBean.getAllDelivery();

        if (deliveries.isPresent()) {
            deliveryItems = deliveries.get();
        } 
    }

    public List<Delivery> getAllDelivery() {
        return deliveryItems;
    }
    
}
