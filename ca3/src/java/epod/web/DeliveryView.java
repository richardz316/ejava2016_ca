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
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author E0015387
 */
@RequestScoped
@Named
public class DeliveryView {

    @EJB
    private DeliveryBean deliveryBean;

    public List<Delivery> getAllDelivery() {
        List<Delivery> result = new LinkedList<>();
        Optional<List<Delivery>> deliveries = deliveryBean.getAllDelivery();

        if (deliveries.isPresent()) {
            result = deliveries.get();
        } 
        return result;
    }
    
}
