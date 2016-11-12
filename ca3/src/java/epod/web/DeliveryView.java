/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.web;

import epod.business.DeliveryBean;
import epod.business.PodBean;
import epod.model.Delivery;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    @EJB
    private PodBean podBean;

    private List<Delivery> deliveryItems;
    private Delivery delivery;
    private String name;
    private String address;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String createDelivery() {
        delivery = new Delivery();
        delivery.setName(name);
        delivery.setAddress(address);
        delivery.setPhone(phone);
        delivery.setCreateDate(new Date());

//        deliveryBean.create(delivery);
        podBean.create(delivery);

        FacesMessage m = new FacesMessage("Delivery Created!");
        FacesContext.getCurrentInstance().addMessage(null, m);

        return "createdelivery";

    }
}
